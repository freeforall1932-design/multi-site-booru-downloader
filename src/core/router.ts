import { BooruError } from '../shared/errors.js';
import type { BooruPost, ExtensionSettings, FailureKind, ServerConfig } from '../shared/types.js';
import { deepClone } from '../shared/util.js';
import type { BooruClient } from './client.js';
import type { Downloader } from './downloads.js';
import type { DiagnosticsInfo, RouterResponse, UiRequest } from './messages.js';
import { buildDownloadPath } from './naming.js';
import type { DownloadQueue } from './queue.js';
import { getAdapter } from './registry.js';
import type { ServerStore } from './servers.js';
import { toServerView } from './servers.js';
import type { SettingsStore } from './settings.js';
import { STORAGE_KEYS } from './storage.js';

export interface RouterDeps {
  client: BooruClient;
  queue: DownloadQueue;
  servers: ServerStore;
  settings: SettingsStore;
  downloader: Downloader;
  syncUserAgentRules?: (servers: ServerConfig[], settings: ExtensionSettings) => Promise<{ ok: boolean; applied: number; error?: string }>;
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
    const outcome = await deps.downloader.download({ url: post.fileUrl, filename: path.fullPath, conflictAction: 'uniquify' });
    return { filename: path.fullPath, post, viaFallback: outcome.viaFallback, downloadId: outcome.downloadId };
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
      case 'browse/search':
        return deps.client.search(request.payload.serverId, request.payload.spec);

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

      case 'queue/run':
        // Fire and forget - the UI polls `queue/list` for progress.
        void deps.queue.run();
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
