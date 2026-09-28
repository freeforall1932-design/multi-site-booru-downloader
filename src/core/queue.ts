import { BooruError } from '../shared/errors.js';
import type { HttpClient } from '../shared/http.js';
import type {
  ExtensionSettings,
  FailureKind,
  QueueItem,
  QueueSummary,
  Rating,
  ServerConfig,
} from '../shared/types.js';
import { historyKey, type DownloadHistoryStore } from './history.js';
import { createId, deepClone } from '../shared/util.js';
import type { BooruClient } from './client.js';
import type { Downloader } from './downloads.js';
import { buildDownloadPath } from './naming.js';
import { createAdapterContext, requireAdapter } from './registry.js';
import type { ServerStore } from './servers.js';
import type { SettingsStore } from './settings.js';
import { STORAGE_KEYS, type StorageArea } from './storage.js';

export interface QueueDeps {
  storage: StorageArea;
  client: BooruClient;
  servers: ServerStore;
  settings: SettingsStore;
  downloader: Downloader;
  /** Optional download notebook: written on success, read by "skip downloaded". */
  history?: DownloadHistoryStore;
  http?: HttpClient;
  /** Explicit per-server rate spacing override (defaults to adapter + settings). */
  spacingMs?: number;
  now?: () => number;
}

export interface EnqueueInput {
  serverId: string;
  postId: string;
  label?: string;
  postUrl?: string;
  rating?: Rating | null;
}

/**
 * Minimal shape a caller needs to queue a listing result: the queue itself
 * looks up the server, the adapter and the canonical post.
 */
export interface EnqueueCandidate {
  id: string;
  label?: string;
  postUrl?: string;
  rating?: Rating;
}

/**
 * Batch download queue.
 *
 * - one worker per concurrency slot, respecting per-server rate limits
 * - every attempt is re-validated against the server's rating filter
 * - state is persisted so a service-worker restart does not lose the queue
 */
export class DownloadQueue {
  private items: QueueItem[] = [];
  private paused = false;
  private running = false;
  private loaded = false;
  private readonly listeners = new Set<(summary: QueueSummary, items: QueueItem[]) => void>();
  private readonly now: () => number;

  constructor(private readonly deps: QueueDeps) {
    this.now = deps.now ?? (() => Date.now());
  }

  // ------------------------------------------------------------------ state

  private async ensureLoaded(): Promise<void> {
    if (this.loaded) return;
    const stored = (await this.deps.storage.get<unknown>(STORAGE_KEYS.queue)) as
      | { items?: QueueItem[]; paused?: boolean }
      | null;
    const items = Array.isArray(stored?.items) ? stored.items : [];
    // A service worker restart mid-flight leaves items marked "running"; treat
    // them as pending again so the work is not silently dropped.
    this.items = items.map((item) => ({
      ...item,
      // Rows queued before the rating/media columns existed have no rating.
      rating: item.rating ?? null,
      status: item.status === 'running' ? ('pending' as const) : item.status,
    }));
    this.paused = stored?.paused ?? false;
    this.loaded = true;
  }

  private async persist(): Promise<void> {
    await this.deps.storage.set(STORAGE_KEYS.queue, { items: this.items, paused: this.paused, updatedAt: new Date().toISOString() });
    this.emit();
  }

  private emit(): void {
    const summary = this.summary();
    const snapshot = this.list();
    this.listeners.forEach((listener) => listener(summary, snapshot));
  }

  onChange(listener: (summary: QueueSummary, items: QueueItem[]) => void): () => void {
    this.listeners.add(listener);
    return () => this.listeners.delete(listener);
  }

  list(): QueueItem[] {
    return this.items.map((item) => deepClone(item));
  }

  summary(): QueueSummary {
    const counts: QueueSummary = {
      pending: 0,
      running: 0,
      done: 0,
      failed: 0,
      skipped: 0,
      canceled: 0,
      total: this.items.length,
      paused: this.paused,
      active: this.running,
    };
    for (const item of this.items) counts[item.status] += 1;
    return counts;
  }

  // --------------------------------------------------------------- mutation

  async enqueue(inputs: EnqueueInput[]): Promise<{ added: number; skipped: number }> {
    await this.ensureLoaded();
    const existing = new Set(
      this.items.filter((item) => item.status === 'pending' || item.status === 'running').map((item) => `${item.serverId}:${item.postId}`),
    );
    let added = 0;
    let skipped = 0;
    for (const input of inputs) {
      const key = `${input.serverId}:${input.postId}`;
      if (existing.has(key)) {
        skipped += 1;
        continue;
      }
      existing.add(key);
      const timestamp = new Date(this.now()).toISOString();
      this.items.push({
        id: createId('q'),
        serverId: input.serverId,
        postId: input.postId,
        label: input.label ?? `post ${input.postId}`,
        postUrl: input.postUrl ?? '',
        rating: input.rating ?? null,
        status: 'pending',
        attempts: 0,
        error: null,
        errorKind: null,
        filename: null,
        bytes: null,
        createdAt: timestamp,
        updatedAt: timestamp,
      });
      added += 1;
    }
    await this.persist();
    return { added, skipped };
  }

  /** Enqueue every post from a listing result, honouring the rating filter. */
  async enqueuePosts(serverId: string, posts: readonly EnqueueCandidate[]): Promise<{ added: number; skipped: number }> {
    const server = await this.deps.servers.get(serverId);
    if (!server) {
      throw new BooruError(`Unknown server "${serverId}"`, {
        kind: 'unknown',
        hint: 'The profile may have been deleted - refresh the server list and try again.',
      });
    }
    const adapter = requireAdapter(server.siteType);
    const allowed = posts.filter((post) => this.deps.client.isRatingAllowed(server, adapter, post.rating ?? 'unknown'));
    const denied = posts.length - allowed.length;
    const result = await this.enqueue(
      allowed.map((post) => ({
        serverId,
        postId: post.id,
        label: post.label ?? `${server.siteType} #${post.id}`,
        postUrl: post.postUrl,
        rating: post.rating ?? null,
      })),
    );
    // Rating-filtered posts count as skipped so the UI can say "3 skipped".
    return { added: result.added, skipped: result.skipped + denied };
  }

  async cancel(itemId: string): Promise<void> {
    await this.ensureLoaded();
    const item = this.items.find((entry) => entry.id === itemId);
    if (!item || item.status === 'done') return;
    item.status = 'canceled';
    item.updatedAt = new Date(this.now()).toISOString();
    await this.persist();
  }

  async retryFailed(): Promise<number> {
    await this.ensureLoaded();
    let requeued = 0;
    for (const item of this.items) {
      if (item.status === 'failed' || item.status === 'skipped') {
        item.status = 'pending';
        item.error = null;
        item.errorKind = null;
        item.attempts = 0;
        item.updatedAt = new Date(this.now()).toISOString();
        requeued += 1;
      }
    }
    await this.persist();
    return requeued;
  }

  async clear(scope: 'completed' | 'failed' | 'all'): Promise<number> {
    await this.ensureLoaded();
    const before = this.items.length;
    if (scope === 'all') {
      this.items = [];
    } else if (scope === 'completed') {
      this.items = this.items.filter((item) => item.status !== 'done');
    } else {
      this.items = this.items.filter((item) => item.status !== 'failed' && item.status !== 'skipped');
    }
    await this.persist();
    return before - this.items.length;
  }

  /** Drop specific rows (the panel's "Remove selected"), whatever their status. */
  async remove(itemIds: readonly string[]): Promise<number> {
    await this.ensureLoaded();
    const wanted = new Set(itemIds);
    const before = this.items.length;
    this.items = this.items.filter((item) => !wanted.has(item.id));
    await this.persist();
    return before - this.items.length;
  }

  async pause(): Promise<void> {
    await this.ensureLoaded();
    this.paused = true;
    await this.persist();
  }

  async resume(): Promise<void> {
    await this.ensureLoaded();
    this.paused = false;
    await this.persist();
  }

  // ---------------------------------------------------------------- running

  /**
   * Process pending items until the queue is empty or paused.
   *
   * `onlyIds` restricts the run to the ticked rows (the panel's "Download
   * selected"); every other pending row is left untouched for later.
   */
  async run(options: { onlyIds?: readonly string[] } = {}): Promise<QueueSummary> {
    await this.ensureLoaded();
    if (this.running) return this.summary();
    this.paused = false;
    this.running = true;
    this.emit();
    const settings = await this.deps.settings.get();
    const concurrency = Math.max(1, Math.min(settings.maxConcurrency, 8));
    const only = options.onlyIds ? new Set(options.onlyIds) : null;

    try {
      const workers = Array.from({ length: concurrency }, () => this.worker(settings, only));
      await Promise.all(workers);
    } finally {
      this.running = false;
      await this.persist();
    }
    return this.summary();
  }

  private async worker(settings: ExtensionSettings, only: Set<string> | null): Promise<void> {
    for (;;) {
      if (this.paused) return;
      const next = this.items.find((item) => item.status === 'pending' && (!only || only.has(item.id)));
      if (!next) return;
      next.status = 'running';
      next.attempts += 1;
      next.updatedAt = new Date(this.now()).toISOString();
      this.emit();

      try {
        const outcome = await this.processItem(next, settings);
        next.status = 'done';
        next.filename = outcome.filename;
        next.bytes = outcome.bytes;
        next.error = null;
        next.errorKind = null;
      } catch (error) {
        const booruError =
          error instanceof BooruError ? error : new BooruError(error instanceof Error ? error.message : String(error), { kind: 'unknown' });
        next.status = booruError.kind === 'unsupported-site' ? 'skipped' : 'failed';
        next.error = booruError.message;
        next.errorKind = booruError.kind as FailureKind;
      } finally {
        next.updatedAt = new Date(this.now()).toISOString();
        await this.persist();
      }
    }
  }

  private async processItem(item: QueueItem, settings: ExtensionSettings): Promise<{ filename: string; bytes: number | null }> {
    // A deleted profile means the work cannot ever succeed: mark it skipped so
    // it does not keep retrying.
    const known = await this.deps.servers.get(item.serverId);
    if (!known) {
      throw new BooruError(`Server profile for "${item.label}" no longer exists`, {
        kind: 'unsupported-site',
        hint: 'Re-add the server, then re-queue the post.',
      });
    }
    const { server, adapter } = await this.deps.client.resolve(item.serverId);
    const post = await this.deps.client.getPost(server.id, item.postId);

    if (settings.enforceRatingFilterOnDownload && !this.deps.client.isRatingAllowed(server, adapter, post.rating)) {
      throw new BooruError(
        `Skipped: rating "${post.rating}" is not allowed by this server's filter`,
        { kind: 'incomplete-config', hint: 'Enable the rating in the server config or turn off rating enforcement in Settings.' },
      );
    }
    if (!post.fileUrl) {
      throw new BooruError(`Post ${post.id} has no downloadable file`, { kind: 'parse-failure', status: null });
    }

    const path = buildDownloadPath({
      post,
      server,
      settings: {
        folderTemplate: settings.folderTemplate,
        filenameTemplate: settings.filenameTemplate,
        maxTagsInFilename: settings.maxTagsInFilename,
        tagSeparator: settings.tagSeparator,
      },
    });

    const target = settings.filePreference === 'sample' && post.sampleUrl ? post.sampleUrl : post.fileUrl;
    const outcome = await this.deps.downloader.download({
      url: target,
      filename: path.fullPath,
      conflictAction: settings.duplicateBehaviour === 'overwrite' ? 'overwrite' : 'uniquify',
    });
    // Remember the success so the listing can skip this post next time. A history
    // failure must never fail the download itself.
    if (this.deps.history) {
      try {
        await this.deps.history.add({
          key: historyKey(server.id, post.id),
          serverId: server.id,
          siteType: server.siteType,
          postId: post.id,
          label: item.label,
          filename: path.fullPath,
          postUrl: post.postUrl,
          bytes: outcome.bytes,
          at: new Date(this.now()).toISOString(),
        });
      } catch {
        /* non-fatal: the file is on disk either way */
      }
    }
    return { filename: path.fullPath, bytes: outcome.bytes };
  }
}

/** Minimum spacing between requests to one server, per adapter + settings. */
export function requestSpacingFor(server: ServerConfig, settings: ExtensionSettings, adapterMinIntervalMs: number): number {
  return Math.max(settings.minRequestIntervalMs, adapterMinIntervalMs);
}

/** Helper used by the service worker to build an adapter context for a server. */
export async function contextForServer(server: ServerConfig, settings: ExtensionSettings) {
  return createAdapterContext(requireAdapter(server.siteType), server, settings);
}
