import { BooruError } from '../shared/errors.js';
import type {
  BooruPost,
  DownloadHistoryEntry,
  ExtensionSettings,
  FailureKind,
  MirrorLink,
  SearchHistoryEntry,
  ServerConfig,
} from '../shared/types.js';
import { deepClone, splitTags, uniqueBy } from '../shared/util.js';
import type { BooruClient } from './client.js';
import type { Downloader } from './downloads.js';
import type { DiagnosticsInfo, RouterResponse, UiRequest } from './messages.js';
import { buildDownloadPath, sanitizePathSegment } from './naming.js';
import type { DownloadQueue } from './queue.js';
import { getAdapter } from './registry.js';
import type { ServerStore } from './servers.js';
import { toServerView } from './servers.js';
import { DownloadHistoryStore, SearchHistoryStore } from './history.js';
import {
  classifyMirrorLink,
  collectMirrorLinks,
  formatLinkExport,
  linkLabel,
  ownHostsOf,
  parseLinkExport,
  toMirrorLink,
} from './links.js';
import { MirrorLinkStore } from './link-store.js';
import { composeSearchTags } from './search.js';
import type { SettingsStore } from './settings.js';
import { STORAGE_KEYS, type StorageArea } from './storage.js';

export interface RouterDeps {
  client: BooruClient;
  queue: DownloadQueue;
  servers: ServerStore;
  settings: SettingsStore;
  downloader: Downloader;
  syncUserAgentRules?: (servers: ServerConfig[], settings: ExtensionSettings) => Promise<{ ok: boolean; applied: number; error?: string }>;
  /** Storage used by the side panel's history notebooks (optional in tests). */
  storage?: StorageArea;
  /** Pre-built history stores (the worker reuses them with the queue). */
  history?: DownloadHistoryStore;
  searches?: SearchHistoryStore;
  /** Collected mirror links (the Links tab); built from `storage` when omitted. */
  links?: MirrorLinkStore;
  environment: 'extension' | 'preview';
  version: string;
}

export type UiMessageHandler = (request: UiRequest) => Promise<RouterResponse>;

/**
 * One request handler for every surface (service worker, popup, options page and
 * the browser preview). The UI only ever sends `UiRequest`s - it never touches
 * an adapter, a credential or a network call itself.
 *
 * No response in this file contains an unmasked secret.
 */
export function createRouter(deps: RouterDeps): UiMessageHandler {
  const historyStore = deps.history ?? (deps.storage ? new DownloadHistoryStore(deps.storage) : null);
  const searchStore = deps.searches ?? (deps.storage ? new SearchHistoryStore(deps.storage) : null);
  const linkStore = deps.links ?? (deps.storage ? new MirrorLinkStore(deps.storage) : null);

  /** The mirror-link rules currently in force, from settings + the profile. */
  async function linkRulesFor(server: ServerConfig | null): Promise<Parameters<typeof collectMirrorLinks>[2]> {
    const settings = await deps.settings.get();
    return {
      filter: settings.mirrorLinksFilter,
      extraHosts: splitTags(settings.mirrorExtraHosts),
      ownHosts: ownHostsOf(server),
    };
  }

  /** Empty answer for every links/* request when no storage is available. */
  function noLinks() {
    return { links: [] as MirrorLink[], stats: { total: 0, new: 0, queued: 0, done: 0, failed: 0 } };
  }
  /** Resolve a post (by URL, by id, or the default server) and save the file. */
  async function downloadPost(options: { serverId?: string | null; postId?: string; url?: string }) {
    let post: BooruPost;
    let server: ServerConfig;
    if (options.url) {
      const resolved = await deps.client.resolvePostFromUrl(options.url);
      post = resolved.post;
      server = resolved.server;
    } else {
      if (!options.postId) throw new BooruError('A post id or post URL is required', { kind: 'incomplete-config' });
      const target = await deps.client.resolve(options.serverId ?? null);
      server = target.server;
      post = await deps.client.getPost(server.id, options.postId);
    }

    const settings = await deps.settings.get();
    const adapter = getAdapter(server.siteType);
    if (settings.enforceRatingFilterOnDownload && adapter && !deps.client.isRatingAllowed(server, adapter, post.rating)) {
      throw new BooruError(`Post ${post.id} is rated "${post.rating}", which this server's filter excludes`, {
        kind: 'incomplete-config',
        hint: 'Allow that rating in the server config, or turn off rating enforcement in Settings.',
      });
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
    const outcome = await deps.downloader.download({
      url: target,
      filename: path.fullPath,
      conflictAction: settings.duplicateBehaviour === 'overwrite' ? 'overwrite' : 'uniquify',
    });
    return { filename: path.fullPath, post, viaFallback: outcome.viaFallback, downloadId: outcome.downloadId };
  }

  /**
   * Removing a link from the Links tab also drops the *pending* queue row it
   * created, so the user does not have to clean up twice. Finished rows are
   * history and are left alone (the Queue tab has "Clear finished" for those).
   */
  async function dropPendingQueueRowsFor(linkIds: readonly string[]): Promise<void> {
    const wanted = new Set(linkIds);
    const rows = deps.queue
      .list()
      .filter((item) => item.kind === 'link' && item.status === 'pending' && wanted.has(item.postId))
      .map((item) => item.id);
    if (rows.length) await deps.queue.remove(rows);
  }

  async function handle(request: UiRequest): Promise<RouterResponse> {
    try {
      return { ok: true, data: await route(request) };
    } catch (error) {
      const booruError =
        error instanceof BooruError
          ? error
          : new BooruError(error instanceof Error ? error.message : String(error), { kind: 'unknown' });
      return {
        ok: false,
        error: { message: booruError.message, kind: booruError.kind as FailureKind, hint: booruError.hint },
      };
    }
  }

  async function route(request: UiRequest): Promise<unknown> {
    switch (request.type) {
      // ------------------------------------------------------------- servers
      case 'servers/list':
        return deps.servers.listViews();

      case 'servers/save':
        return toServerView(await deps.servers.save(request.payload));

      case 'servers/remove':
        return (await deps.servers.remove(request.payload.id)).map(toServerView);

      case 'servers/duplicate':
        return toServerView(await deps.servers.duplicate(request.payload.id));

      case 'servers/setDefault':
        return (await deps.servers.setDefault(request.payload.id)).map(toServerView);

      case 'servers/validate':
        return deps.client.validateServer(request.payload.id);

      case 'servers/validateDraft':
        return deps.client.validateDraft(await draftServerConfig(request.payload.server));

      case 'servers/clearCredentials': {
        const updated = await deps.servers.clearCredentials(request.payload.id);
        return updated ? toServerView(updated) : null;
      }

      case 'servers/export': {
        const includeSecrets = request.payload?.includeSecrets === true;
        return { json: await deps.servers.exportServers({ includeSecrets }), includesSecrets: includeSecrets };
      }

      case 'servers/import':
        return deps.servers.importServers(request.payload.json, { includeSecrets: request.payload.includeSecrets === true });

      // -------------------------------------------------------------- browse
      case 'browse/search': {
        // The shared layer adds the tag blacklist (adapters do not know about it);
        // the *global suffix* is appended by the adapters themselves, so it must
        // not be added here as well.
        const settings = await deps.settings.get();
        const composed = composeSearchTags({ tags: request.payload.spec.tags ?? '', blacklist: settings.tagBlacklist });
        return deps.client.search(request.payload.serverId, { ...request.payload.spec, tags: composed.tags });
      }

      case 'posts/get':
        return deps.client.getPost(request.payload.serverId, request.payload.postId);

      case 'posts/resolveUrl': {
        const { post, server, route: resolved } = await deps.client.resolvePostFromUrl(request.payload.url);
        return { post, server: toServerView(server), route: resolved };
      }

      case 'posts/download':
        return downloadPost(request.payload);

      case 'routes/detect': {
        const detected = await deps.client.resolveRoute(request.payload.url);
        if (!detected) return null;
        return { ...detected, server: detected.server ? toServerView(detected.server) : null };
      }

      // --------------------------------------------------------------- links
      case 'links/list': {
        if (!linkStore) return noLinks();
        return { links: await linkStore.list(), stats: await linkStore.stats() };
      }

      /**
       * One listing page, reduced to the *posts* it contains.
       *
       * Creator archives answer with one row per attachment, so a post with
       * four files appears four times with the same `postUrl` - the scan wants
       * the post once. Nothing is fetched per post here: the panel walks the
       * refs one at a time through `links/scanPost`, which keeps progress
       * visible and the request spacing honest.
       */
      case 'links/posts': {
        const settings = await deps.settings.get();
        const composed = composeSearchTags({ tags: request.payload.query, blacklist: settings.tagBlacklist });
        const result = await deps.client.search(request.payload.serverId, {
          tags: composed.tags,
          page: request.payload.page,
          limit: 50,
        });
        const posts = uniqueBy(
          result.posts.map((post) => ({
            id: post.id,
            label: post.description?.trim() || `#${post.id}`,
            postUrl: post.postUrl,
          })),
          (post) => post.postUrl,
        );
        return { posts, hasMore: result.hasMore, totalCount: result.totalCount, page: result.page };
      }

      /**
       * Read one post's raw payload and harvest its off-site download links.
       *
       * This is the extension's version of the "open every post of the creator
       * and read the links" userscript loop - except the page is never parsed:
       * the same public API the downloader already uses answers with the post
       * body, and the URLs are pulled out of that.
       */
      case 'links/scanPost': {
        if (!linkStore) return { added: [] as MirrorLink[], duplicates: 0, total: 0 };
        const { serverId, postId } = request.payload;
        const server = await deps.servers.get(serverId);
        if (!server) {
          throw new BooruError(`Unknown server "${serverId}"`, {
            kind: 'incomplete-config',
            hint: 'Pick the profile the links should be collected with, then try again.',
          });
        }
        const payload = await deps.client.getPostPayload(server.id, postId);
        const found = collectMirrorLinks(
          payload,
          {
            serverId: server.id,
            siteType: server.siteType,
            postId,
            postTitle: request.payload.postTitle ?? null,
            postUrl: request.payload.postUrl ?? null,
            creator: request.payload.creator ?? null,
          },
          await linkRulesFor(server),
        );
        const result = await linkStore.add(found);
        return {
          added: found.filter((link) => result.addedIds.includes(link.id)),
          duplicates: result.updated,
          total: result.total,
        };
      }

      /** Put collected links on the download queue (they download as `link` rows). */
      case 'links/queue': {
        if (!linkStore) return { queued: 0, skipped: 0, invalid: 0, ...noLinks() };
        const wanted = new Set(request.payload.ids);
        const chosen = (await linkStore.list()).filter((link) => wanted.has(link.id));
        const result = await deps.queue.enqueueLinks(
          chosen.map((link) => ({
            url: link.url,
            label: linkLabel(link),
            postUrl: link.postUrl,
            serverId: link.serverId,
          })),
        );
        for (const link of chosen) {
          if (link.status !== 'done') await linkStore.mark(link.id, 'queued');
        }
        return { queued: result.added, skipped: result.skipped, invalid: result.invalid, links: await linkStore.list(), stats: await linkStore.stats() };
      }

      case 'links/remove': {
        if (!linkStore) return { removed: 0, ...noLinks() };
        const removed = await linkStore.remove(request.payload.ids);
        await dropPendingQueueRowsFor(request.payload.ids);
        return { removed, links: await linkStore.list(), stats: await linkStore.stats() };
      }

      case 'links/clear': {
        if (!linkStore) return { removed: 0, ...noLinks() };
        const scope = request.payload.scope ?? 'all';
        const before = (await linkStore.list()).map((link) => link.id);
        const removed = await linkStore.clear(scope);
        const surviving = new Set((await linkStore.list()).map((link) => link.id));
        await dropPendingQueueRowsFor(before.filter((id) => !surviving.has(id)));
        return { removed, links: await linkStore.list(), stats: await linkStore.stats() };
      }

      /** Render the `.txt` the Links tab downloads (and can import again). */
      case 'links/export': {
        if (!linkStore) return { text: '', filename: 'mirror-links.txt', count: 0, grouping: 'post' as const };
        const links = await linkStore.list();
        const grouping = request.payload.grouping ?? 'post';
        const firstServerId = links.find((link) => link.serverId)?.serverId ?? null;
        const server = firstServerId ? await deps.servers.get(firstServerId) : null;
        const creator = links.find((link) => link.creator)?.creator ?? null;
        const text = formatLinkExport(links, {
          grouping,
          creator,
          server: server ? `${server.label} (${server.baseUrl})` : (links[0]?.siteType ?? null),
          version: deps.version,
        });
        const safeCreator = sanitizePathSegment(creator ?? server?.label ?? 'mirror', 'mirror').replace(/\s+/g, '_');
        return { text, filename: `${safeCreator}_download_links.txt`, count: links.length, grouping };
      }

      /** Read one back: rebuild the list (and optionally the queue) from a file. */
      case 'links/import': {
        if (!linkStore) return { parsed: 0, added: 0, updated: 0, queued: 0, invalid: 0, ...noLinks() };
        const parsed = parseLinkExport(request.payload.text);
        const server = request.payload.serverId ? await deps.servers.get(request.payload.serverId) : null;
        const now = new Date();
        const records: MirrorLink[] = [];
        let invalid = 0;
        for (const url of parsed.links) {
          // An imported file is an explicit instruction: no provider/format
          // filter and no own-host rule is applied, only validity.
          const verdict = classifyMirrorLink(url, { filter: 'any' });
          if (!verdict.ok) {
            invalid += 1;
            continue;
          }
          const context = parsed.contexts.get(url) ?? {};
          records.push(
            toMirrorLink(verdict, {
              serverId: server?.id ?? null,
              siteType: server?.siteType ?? null,
              postId: null,
              postTitle: context.postTitle ?? null,
              postUrl: context.postUrl ?? null,
              creator: context.creator ?? null,
            }, now),
          );
        }
        const result = await linkStore.add(records);
        let queued = 0;
        if (request.payload.queue && records.length) {
          const enqueued = await deps.queue.enqueueLinks(
            records.map((record) => ({ url: record.url, label: linkLabel(record), postUrl: record.postUrl, serverId: record.serverId })),
          );
          queued = enqueued.added;
          for (const record of records) {
            const stored = await linkStore.byId(record.id);
            if (stored && stored.status !== 'done') await linkStore.mark(record.id, 'queued');
          }
        }
        return {
          parsed: parsed.links.length,
          added: result.added,
          updated: result.updated,
          queued,
          invalid,
          links: await linkStore.list(),
          stats: await linkStore.stats(),
        };
      }

      // --------------------------------------------------------------- queue
      case 'queue/list': {
        const settings = await deps.settings.get();
        return { summary: deps.queue.summary(), items: deps.queue.list(), maxConcurrency: settings.maxConcurrency };
      }

      case 'queue/enqueue': {
        const result = await deps.queue.enqueue(request.payload.items);
        return { ...result, summary: deps.queue.summary() };
      }

      case 'queue/enqueuePosts': {
        // Rating filtering plus de-duplication live in the queue so the message
        // layer stays a thin translation of UI intent.
        const result = await deps.queue.enqueuePosts(request.payload.serverId, request.payload.posts);
        return { ...result, summary: deps.queue.summary() };
      }

      case 'queue/enqueueLinks': {
        // Mirror links skip the adapter and the rating filter: the file is not
        // on the site, so there is nothing to look up and nothing to rate.
        const result = await deps.queue.enqueueLinks(request.payload.links, request.payload.serverId ?? '');
        return { ...result, summary: deps.queue.summary() };
      }

      case 'queue/run':
        // Fire and forget - the UI polls `queue/list` for progress. `itemIds`
        // restricts the run to the ticked rows (panel "Download selected").
        void deps.queue.run({ onlyIds: request.payload?.itemIds });
        return { started: true, summary: deps.queue.summary() };

      case 'queue/pause':
        await deps.queue.pause();
        return { summary: deps.queue.summary() };

      case 'queue/resume':
        await deps.queue.resume();
        void deps.queue.run();
        return { started: true, summary: deps.queue.summary() };

      case 'queue/retryFailed': {
        const requeued = await deps.queue.retryFailed();
        return { requeued, summary: deps.queue.summary() };
      }

      case 'queue/clear': {
        const removed = await deps.queue.clear(request.payload.scope);
        return { removed, summary: deps.queue.summary() };
      }

      case 'queue/cancel':
        await deps.queue.cancel(request.payload.itemId);
        return { summary: deps.queue.summary() };

      // ------------------------------------------------------------ settings
      case 'queue/remove': {
        const removed = await deps.queue.remove(request.payload.itemIds);
        const summary = deps.queue.summary();
        return { removed, summary };
      }

      // ------------------------------------------------------------- history
      case 'history/list': {
        const entries = historyStore ? await historyStore.list() : [];
        return { entries, total: entries.length };
      }

      case 'history/remove': {
        if (!historyStore) return { removed: 0, total: 0 };
        const removed = await historyStore.remove(request.payload.serverId, request.payload.postId);
        return { removed, total: await historyStore.size() };
      }

      case 'history/clear': {
        const removed = historyStore ? await historyStore.clear() : 0;
        return { removed };
      }

      case 'searches/list':
        return { entries: searchStore ? await searchStore.list(request.payload?.serverId ?? null) : [] };

      case 'searches/add': {
        if (!searchStore) return { entries: [] };
        const settings = await deps.settings.get();
        if (!settings.searchHistoryEnabled || settings.searchHistoryLimit === 0) {
          return { entries: await searchStore.list(request.payload.serverId) };
        }
        return { entries: await searchStore.add(request.payload.serverId, request.payload.query, settings.searchHistoryLimit) };
      }

      case 'searches/remove':
        return { entries: searchStore ? await searchStore.remove(request.payload.serverId, request.payload.query) : [] };

      case 'searches/clear':
        return { removed: searchStore ? await searchStore.clear(request.payload?.serverId ?? null) : 0 };

      case 'settings/get':
        return deps.settings.get();

      case 'settings/save': {
        const saved = await deps.settings.save(request.payload);
        await resyncUserAgent(saved);
        return saved;
      }

      case 'settings/reset': {
        const saved = await deps.settings.reset();
        await resyncUserAgent(saved);
        return saved;
      }

      case 'userAgent/sync': {
        const settings = await deps.settings.get();
        const servers = await deps.servers.list();
        return (await deps.syncUserAgentRules?.(servers, settings)) ?? { ok: true, applied: 0 };
      }

      // --------------------------------------------------------- diagnostics
      case 'diagnostics/info':
        return buildDiagnostics(deps, true);

      default: {
        const exhaustive: never = request;
        throw new BooruError(`Unsupported request: ${JSON.stringify(exhaustive)}`, { kind: 'unknown' });
      }
    }
  }

  async function resyncUserAgent(settings: ExtensionSettings): Promise<void> {
    if (!deps.syncUserAgentRules) return;
    const servers = await deps.servers.list();
    await deps.syncUserAgentRules(servers, settings);
  }

  /** Build a ServerConfig from unsaved form input so it can be validated. */
  async function draftServerConfig(input: Partial<ServerConfig>): Promise<ServerConfig> {
    const adapter = input.siteType ? getAdapter(input.siteType) : null;
    const draft: ServerConfig = {
      id: input.id ?? 'draft',
      label: (input.label ?? adapter?.defaults.label ?? 'Draft').trim(),
      siteType: input.siteType ?? '',
      baseUrl: (input.baseUrl ?? adapter?.defaults.baseUrl ?? '').trim(),
      ratingFilterEnabled: input.ratingFilterEnabled ?? true,
      username: (input.username ?? '').trim(),
      apiKey: (input.apiKey ?? '').trim(),
      userId: (input.userId ?? '').trim(),
      customUserAgent: (input.customUserAgent ?? '').trim(),
      isDefault: false,
      validationStatus: 'unknown',
      validationMessage: '',
      lastValidatedAt: null,
      allowedRatings: input.allowedRatings,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
    };
    // A blank secret on an existing profile means "keep the stored one" - the UI
    // never receives the stored key, so validation must fill it back in.
    if (input.id) {
      const existing = await deps.servers.get(input.id);
      if (existing) {
        if (!draft.apiKey) draft.apiKey = existing.apiKey;
        if (!draft.username) draft.username = existing.username;
        if (!draft.userId) draft.userId = existing.userId;
        if (!draft.customUserAgent) draft.customUserAgent = existing.customUserAgent;
      }
    }
    return draft;
  }

  return handle;
}

export async function buildDiagnostics(deps: RouterDeps, includeUserAgentSync = false): Promise<DiagnosticsInfo> {
  const settings = await deps.settings.get();
  const servers = await deps.servers.list();
  const adapters = deps.client.adapters.map((adapter) => ({
    siteType: adapter.siteType,
    displayName: adapter.displayName,
    capabilitySummary: [
      adapter.capabilities.supportsRatingFilter ? 'rating filter' : 'no rating filter',
      adapter.capabilities.authStyle,
      adapter.capabilities.requiresUserAgent ? 'user-agent required' : 'user-agent optional',
      `${adapter.capabilities.minRequestIntervalMs}ms spacing`,
    ].join(' · '),
    baseUrl: adapter.defaults.baseUrl,
    authStyle: adapter.capabilities.authStyle,
    requiresUserAgent: adapter.capabilities.requiresUserAgent,
    maxPostsPerRequest: adapter.capabilities.maxPostsPerRequest,
    ratingTokens: [...adapter.siteRatingTokens],
    defaultRatings: [...adapter.defaults.ratings],
    supportsRatingFilter: adapter.capabilities.supportsRatingFilter,
    supportsPostLookup: adapter.capabilities.supportsPostLookup,
    apiDocsUrl: adapter.capabilities.apiDocsUrl,
    notes: adapter.capabilities.notes,
    credentialFields: adapter.credentialFields().map((field) => ({
      key: field.key,
      label: field.label,
      required: field.required,
      secret: field.secret,
      help: field.help,
    })),
  }));
  const userAgentRules =
    includeUserAgentSync && deps.syncUserAgentRules
      ? await deps.syncUserAgentRules(servers, settings)
      : { ok: true, applied: 0 };
  return {
    version: deps.version,
    environment: deps.environment,
    storageKeys: Object.values(STORAGE_KEYS),
    settings,
    adapters,
    servers: servers.map((server) => ({
      id: server.id,
      label: server.label,
      siteType: server.siteType,
      baseUrl: server.baseUrl,
      ratingFilterEnabled: server.ratingFilterEnabled,
      hasApiKey: !!server.apiKey,
      validationStatus: server.validationStatus,
      lastValidatedAt: server.lastValidatedAt,
      trace: deepClone(server.lastValidationTrace ?? []),
    })),
    userAgentRules: { ok: userAgentRules.ok, applied: userAgentRules.applied, error: userAgentRules.error },
  };
}
