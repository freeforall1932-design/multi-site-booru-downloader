import { BooruError } from '../shared/errors.js';
import type { AdapterContext, BooruAdapter } from './adapter.js';
import type { ExtensionSettings, Rating, RouteMatch, ServerConfig, SiteType } from '../shared/types.js';
import { hostMatchesPattern, normalizeBaseUrl as normalizeUrl, safeUrl } from '../shared/util.js';

const registry = new Map<SiteType, BooruAdapter>();

/** Register an adapter. Later registrations may override when explicitly allowed. */
export function registerAdapter(adapter: BooruAdapter, options: { override?: boolean } = {}): BooruAdapter {
  const existing = registry.get(adapter.siteType);
  if (existing && !options.override) {
    throw new Error(`An adapter for site type "${adapter.siteType}" is already registered`);
  }
  registry.set(adapter.siteType, adapter);
  return adapter;
}

export function unregisterAdapter(siteType: SiteType): boolean {
  return registry.delete(siteType);
}

export function getAdapter(siteType: SiteType): BooruAdapter | null {
  return registry.get(siteType) ?? null;
}

/** Adapter or a typed failure - the only "unsupported site" branch in the app. */
export function requireAdapter(siteType: SiteType): BooruAdapter {
  const adapter = registry.get(siteType);
  if (!adapter) {
    throw new BooruError(`No adapter is registered for site type "${siteType}"`, {
      kind: 'unsupported-site',
      siteType,
      hint: 'Register an adapter for this site type (see docs/ADAPTER_CONTRACT.md) or pick a supported type.',
    });
  }
  return adapter;
}

export function listAdapters(): BooruAdapter[] {
  return Array.from(registry.values());
}

export function knownSiteTypes(): SiteType[] {
  return Array.from(registry.keys());
}

/** Build the request context every adapter call needs. */
export function createAdapterContext(
  adapter: BooruAdapter,
  server: ServerConfig,
  settings: ExtensionSettings,
): AdapterContext {
  const baseUrl = normalizeUrl(adapter.normalizeBaseUrl ? adapter.normalizeBaseUrl(server.baseUrl) : server.baseUrl) || server.baseUrl;
  return {
    server: { ...server, baseUrl },
    settings,
    userAgent: effectiveUserAgent(adapter, server, baseUrl),
    baseUrl,
  };
}

export function effectiveUserAgent(
  adapter: BooruAdapter,
  server: Pick<ServerConfig, 'customUserAgent' | 'username' | 'userId'>,
  baseUrl?: string,
): string {
  const custom = (server.customUserAgent ?? '').trim();
  if (custom) return custom;
  return adapter.suggestUserAgent({
    server: { username: server.username, userId: server.userId, customUserAgent: server.customUserAgent ?? '' },
    baseUrl: baseUrl ?? adapter.defaults.baseUrl,
  });
}

/**
 * Host-based route detection.
 * 1. A saved server matching the URL host wins (this is what makes custom
 *    Danbooru/Gelbooru instances work without any special casing).
 * 2. Otherwise the first adapter claiming the host answers.
 */
export function detectRoute(url: string, servers: ServerConfig[] = []): RouteMatch | null {
  const parsed = safeUrl(url);
  if (!parsed) return null;
  const host = parsed.host;

  const candidates = servers
    .map((server) => ({ server, host: safeUrl(server.baseUrl)?.host ?? '' }))
    .filter((entry) => entry.host && hostMatchesPattern(host, entry.host))
    .sort((a, b) => b.host.length - a.host.length);

  for (const candidate of candidates) {
    const adapter = getAdapter(candidate.server.siteType);
    if (!adapter) continue;
    const route = adapter.matchRoute(url);
    if (route) return { ...route, siteType: candidate.server.siteType, serverId: candidate.server.id };
  }

  for (const adapter of registry.values()) {
    const route = adapter.matchRoute(url);
    if (route) return route;
  }
  return null;
}

export function detectAdapterForUrl(url: string, servers: ServerConfig[] = []): { adapter: BooruAdapter; server: ServerConfig | null } | null {
  const route = detectRoute(url, servers);
  if (!route) return null;
  const adapter = getAdapter(route.siteType);
  if (!adapter) return null;
  const server = route.serverId ? servers.find((entry) => entry.id === route.serverId) ?? null : null;
  return { adapter, server };
}

/** New server entries inherit sensible defaults from their adapter. */
export function buildDefaultRatings(adapter: BooruAdapter): Rating[] {
  return [...adapter.defaults.ratings];
}
