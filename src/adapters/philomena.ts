import type { AdapterContext, AdapterFailure, BooruAdapter, CredentialField, ProbeInterpretation, ValidationProbe } from '../core/adapter.js';
import { BooruError } from '../shared/errors.js';
import type { HttpResponseSnapshot, HttpRequestSpec } from '../shared/http.js';
import type { BooruPost, Rating, RouteMatch, SearchResult, SearchSpec, SiteType } from '../shared/types.js';
import { absolutize, accountInfo, asRecord, classifyStatus, detectAuthWordsInBody, pickBoolean, pickGroup, pickNumber, pickString, readJson, shapeError } from './base.js';
import { hostMatchesPattern, splitTags, unique } from '../shared/util.js';

export const PHILOMENA_VERSION = '0.1.0';

/**
 * Philomena ratings are plain tags, exactly one per image. They are richer than
 * the shared vocabulary, so several collapse onto one canonical rating.
 */
const SITE_RATING_TOKENS = ['safe', 'suggestive', 'questionable', 'explicit', 'semi-grimdark', 'grimdark', 'grotesque'] as const;

const CANONICAL_BY_TOKEN: Record<string, Rating> = {
  safe: 'safe',
  suggestive: 'sensitive',
  questionable: 'questionable',
  explicit: 'explicit',
  'semi-grimdark': 'questionable',
  grimdark: 'explicit',
  grotesque: 'explicit',
};

export interface PhilomenaOptions {
  siteType: SiteType;
  displayName: string;
  baseUrl: string;
  label: string;
  hostPatterns: string[];
  apiDocsUrl?: string;
  notes?: string[];
  /**
   * The instance's "Everything" filter id. Anonymous requests otherwise get the
   * site default filter, which hides explicit content regardless of our own
   * rating allow-list. Users can override it per profile (User ID field).
   */
  everythingFilterId?: string;
}

/**
 * Philomena search syntax: terms separated by commas (tags may contain spaces),
 * `-term` negates, `*` matches everything. The user types the site's own syntax;
 * the global tag suffix (space separated in Settings) is appended term by term.
 */
export function composePhilomenaQuery(userQuery: string | undefined, exclusions: string[], globalSuffix: string): string {
  const seen = new Set<string>();
  const terms: string[] = [];
  const push = (raw: string) => {
    const term = raw.trim();
    if (!term) return;
    const key = term.toLowerCase();
    if (seen.has(key)) return;
    seen.add(key);
    terms.push(term);
  };
  for (const term of (userQuery ?? '').split(',')) push(term);
  for (const term of exclusions) push(term);
  for (const term of splitTags(globalSuffix)) push(term);
  return terms.length ? terms.join(', ') : '*';
}

export function philomenaSearchUrl(
  baseUrl: string,
  params: { query: string; perPage?: number; page?: number; key?: string | null; filterId?: string | null; sort?: string | null },
): string {
  const url = new URL(`${baseUrl.replace(/\/+$/, '')}/api/v1/json/search/images`);
  url.searchParams.set('q', params.query);
  if (params.perPage !== undefined) url.searchParams.set('per_page', String(params.perPage));
  if (params.page !== undefined) url.searchParams.set('page', String(params.page));
  if (params.sort) url.searchParams.set('sf', params.sort);
  if (params.filterId) url.searchParams.set('filter_id', params.filterId);
  if (params.key) url.searchParams.set('key', params.key);
  return url.toString();
}

function keyFor(ctx: AdapterContext): string | null {
  const key = ctx.server.apiKey.trim();
  return key || null;
}

function filterIdFor(ctx: AdapterContext, options: PhilomenaOptions): string | null {
  const override = ctx.server.userId.trim();
  if (override && /^\d+$/.test(override)) return override;
  return options.everythingFilterId ?? null;
}

function classifyFailure(snapshot: HttpResponseSnapshot, displayName: string): AdapterFailure | null {
  const generic = classifyStatus(snapshot, displayName);
  if (generic) return generic;
  return detectAuthWordsInBody(snapshot.bodyText);
}

function normalizeRating(raw: unknown): Rating {
  if (typeof raw !== 'string') return 'unknown';
  return CANONICAL_BY_TOKEN[raw.trim().toLowerCase()] ?? 'unknown';
}

function ratingTagOf(tags: string[]): string | null {
  return tags.find((tag) => (SITE_RATING_TOKENS as readonly string[]).includes(tag.toLowerCase())) ?? null;
}

function normalizePost(raw: unknown, ctx: AdapterContext, displayName: string): BooruPost {
  const image = asRecord(raw);
  if (!image) throw shapeError(`${displayName} image`, 'image entry was not an object');
  const id = pickString(image, 'id') ?? '';
  const representations = pickGroup(image, 'representations');
  const format = (pickString(image, 'format') ?? '').toLowerCase() || null;
  const fileUrl =
    absolutize(ctx.baseUrl, pickString(image, 'view_url')) ?? absolutize(ctx.baseUrl, pickString(representations, 'full')) ?? '';
  const previewUrl =
    absolutize(ctx.baseUrl, pickString(representations, 'thumb', 'thumb_small', 'small')) ?? fileUrl;
  const sampleUrl = absolutize(ctx.baseUrl, pickString(representations, 'large', 'medium'));
  const tagsRaw = image.tags;
  const tags = Array.isArray(tagsRaw) ? unique(tagsRaw.filter((tag): tag is string => typeof tag === 'string' && !!tag.trim())) : [];
  const rawRating = ratingTagOf(tags);
  const categories: Record<string, string[]> = { general: [], artist: [], character: [], meta: [], rating: [] };
  for (const tag of tags) {
    if (tag.startsWith('artist:')) categories.artist!.push(tag.slice('artist:'.length));
    else if (tag.startsWith('oc:')) categories.character!.push(tag);
    else if (tag === rawRating) categories.rating!.push(tag);
    else if (/^(spoiler|editor|photographer|generator|prompter):/.test(tag)) categories.meta!.push(tag);
    else categories.general!.push(tag);
  }
  const sourcesRaw = image.source_urls ?? image.source_url;
  const sources = Array.isArray(sourcesRaw)
    ? unique(sourcesRaw.filter((value): value is string => typeof value === 'string' && !!value.trim()))
    : typeof sourcesRaw === 'string' && sourcesRaw.trim()
      ? [sourcesRaw.trim()]
      : [];
  const mime = pickString(image, 'mime_type') ?? '';
  const isVideo = mime.startsWith('video/') || format === 'webm' || format === 'mp4';

  return {
    serverId: ctx.server.id,
    siteType: ctx.server.siteType,
    id,
    postUrl: `${ctx.baseUrl}/images/${id}`,
    fileUrl,
    previewUrl,
    sampleUrl,
    width: pickNumber(image, 'width'),
    height: pickNumber(image, 'height'),
    ext: format,
    sizeBytes: pickNumber(image, 'size'),
    rating: normalizeRating(rawRating),
    rawRating,
    tags,
    tagCategories: categories,
    artistTags: categories.artist ?? [],
    characterTags: categories.character ?? [],
    score: pickNumber(image, 'score'),
    // Philomena hashes with SHA-512, not MD5; kept null so the name template's {md5} stays honest.
    md5: null,
    sources,
    createdAt: pickString(image, 'created_at'),
    isVideo,
    isAnimated: isVideo || (pickBoolean(image, 'animated') ?? false) || format === 'gif',
    parentId: pickString(image, 'duplicate_of'),
    hasChildren: false,
    description: pickString(image, 'description'),
  };
}

function interpretEndpointProbe(snapshot: HttpResponseSnapshot, displayName: string): ProbeInterpretation {
  const failure = classifyFailure(snapshot, displayName);
  if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  let parsed: unknown;
  try {
    parsed = readJson(snapshot, `${displayName} search endpoint`);
  } catch (error) {
    const booruError = error as BooruError;
    return { ok: false, kind: booruError.kind, message: booruError.message, warnings: booruError.hint ? [booruError.hint] : [] };
  }
  const record = asRecord(parsed);
  if (!record || !Array.isArray(record.images)) {
    return {
      ok: false,
      kind: 'endpoint-mismatch',
      message: `${displayName} answered with JSON, but not the Philomena search envelope`,
      warnings: ['Expected `{"images": […], "total": N}` at /api/v1/json/search/images.'],
    };
  }
  return { ok: true, kind: 'ok', message: `Endpoint OK - search reports ${pickNumber(record, 'total') ?? record.images.length} image(s)` };
}

/**
 * Build a Philomena adapter (Derpibooru, Furbooru, Ponybooru and other
 * instances of https://github.com/philomena-dev/philomena).
 *
 * - API: `/api/v1/json/search/images?q=…&per_page=50&page=N`, single image at
 *   `/api/v1/json/images/{id}` (`{"image": {…}}`).
 * - Auth: optional user API key as the `key` query parameter (Account > API key).
 * - Filters: the site applies a content filter to every search; the adapter
 *   pins the instance's "Everything" filter (overridable per profile) so the
 *   extension's own rating allow-list is the only thing hiding posts.
 */
export function createPhilomenaAdapter(options: PhilomenaOptions): BooruAdapter {
  const displayName = options.displayName;
  const matchesHost = (host: string): boolean => options.hostPatterns.some((pattern) => hostMatchesPattern(host, pattern));

  const appliedQuery = (ctx: AdapterContext, spec: SearchSpec): string =>
    composePhilomenaQuery(
      spec.tags,
      spec.ratingFilter?.enabled ? adapter.ratingQueryTags(spec.ratingFilter.allowed).tags : [],
      ctx.settings.globalTagSuffix,
    );

  const adapter: BooruAdapter = {
    siteType: options.siteType,
    displayName,
    hostPatterns: options.hostPatterns,
    siteRatingTokens: SITE_RATING_TOKENS,

    capabilities: {
      supportsAnonymousAccess: true,
      supportsRatingFilter: true,
      requiresUsername: false,
      requiresUserId: false,
      requiresApiKey: false,
      requiresUserAgent: false,
      authStyle: 'query',
      supportsBasicAuthHeader: false,
      maxPostsPerRequest: 50,
      maxPage: null,
      minRequestIntervalMs: 500,
      supportsPostLookup: true,
      supportsTagSearch: true,
      supportsIdPagination: false,
      apiDocsUrl: options.apiDocsUrl ?? 'https://derpibooru.org/pages/api',
      notes: [
        'Search syntax is Philomena\'s own: comma-separated terms, tags may contain spaces, `-term` excludes, `*` matches all.',
        'Ratings are tags (safe, suggestive, questionable, explicit, semi-grimdark, grimdark, grotesque).',
        'A user API key (query `key`) is optional; it unlocks your favourites/watch list and raises rate limits.',
        options.everythingFilterId
          ? `The "Everything" filter (${options.everythingFilterId}) is pinned so only this extension's rating filter hides posts; set a Filter ID to change that.`
          : 'Set a Filter ID to control which site filter applies to searches.',
        ...(options.notes ?? []),
      ],
    },

    defaults: {
      baseUrl: options.baseUrl,
      label: options.label,
      ratings: ['safe', 'sensitive'],
      userAgentHint: `BooruServerManager/${PHILOMENA_VERSION}`,
    },

    normalizeBaseUrl(raw: string): string {
      return raw.trim().replace(/\/+$/, '').replace(/\/(api\/v1\/json.*|images|search)\/?.*$/i, '');
    },

    matchesHost,

    matchRoute(url: string): RouteMatch | null {
      let parsed: URL;
      try {
        parsed = new URL(url);
      } catch {
        return null;
      }
      if (!matchesHost(parsed.host)) return null;
      const segments = parsed.pathname.split('/').filter(Boolean);
      const canonicalUrl = `${parsed.origin}${parsed.pathname}`;
      if (segments[0] === 'images' && segments[1] && /^\d+$/.test(segments[1])) {
        return { siteType: options.siteType, kind: 'post', postId: segments[1], tags: null, page: null, canonicalUrl };
      }
      // Philomena also serves images at the bare `/{id}` short URL.
      if (segments.length === 1 && /^\d+$/.test(segments[0]!)) {
        return { siteType: options.siteType, kind: 'post', postId: segments[0]!, tags: null, page: null, canonicalUrl };
      }
      if (segments[0] === 'search') {
        const q = parsed.searchParams.get('q');
        const page = parsed.searchParams.get('page');
        return {
          siteType: options.siteType,
          kind: q ? 'search' : 'index',
          postId: null,
          tags: q,
          page: page && /^\d+$/.test(page) ? Number(page) : null,
          canonicalUrl,
        };
      }
      if (segments[0] === 'tags' && segments[1]) {
        return { siteType: options.siteType, kind: 'tag', postId: null, tags: decodeURIComponent(segments[1]), page: null, canonicalUrl };
      }
      if (segments[0] === 'galleries' && segments[1]) {
        return { siteType: options.siteType, kind: 'pool', postId: null, tags: null, page: null, canonicalUrl };
      }
      return { siteType: options.siteType, kind: 'index', postId: null, tags: null, page: null, canonicalUrl };
    },

    postUrl(baseUrl: string, postId: string): string {
      return `${baseUrl.replace(/\/+$/, '')}/images/${postId}`;
    },

    buildSearchRequest(ctx, spec): HttpRequestSpec {
      const perPage = Math.min(Math.max(spec.limit ?? 50, 1), adapter.capabilities.maxPostsPerRequest);
      return {
        url: philomenaSearchUrl(ctx.baseUrl, {
          query: appliedQuery(ctx, spec),
          perPage,
          page: spec.page ?? 1,
          key: keyFor(ctx),
          filterId: filterIdFor(ctx, options),
          sort: spec.order ?? null,
        }),
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: options.siteType,
        label: 'Search images',
      };
    },

    buildPostRequest(ctx, postId): HttpRequestSpec {
      const url = new URL(`${ctx.baseUrl}/api/v1/json/images/${encodeURIComponent(postId)}`);
      const key = keyFor(ctx);
      if (key) url.searchParams.set('key', key);
      const filterId = filterIdFor(ctx, options);
      if (filterId) url.searchParams.set('filter_id', filterId);
      return { url: url.toString(), method: 'GET', headers: { Accept: 'application/json' }, tag: options.siteType, label: `Fetch image ${postId}` };
    },

    buildValidationProbes(ctx): ValidationProbe[] {
      const key = keyFor(ctx);
      const authUrl = new URL(`${ctx.baseUrl}/api/v1/json/filters/user`);
      if (key) authUrl.searchParams.set('key', key);
      return [
        {
          id: `${options.siteType}-endpoint`,
          label: 'Search endpoint (anonymous)',
          purpose: 'endpoint',
          request: {
            url: philomenaSearchUrl(ctx.baseUrl, { query: '*', perPage: 1, filterId: filterIdFor(ctx, options) }),
            method: 'GET',
            headers: { Accept: 'application/json' },
            tag: options.siteType,
            label: 'Endpoint check',
          },
          interpret: (snapshot) => interpretEndpointProbe(snapshot, displayName),
        },
        {
          id: `${options.siteType}-auth`,
          label: 'User filters (requires API key)',
          purpose: 'auth',
          enabled: !!key,
          request: { url: authUrl.toString(), method: 'GET', headers: { Accept: 'application/json' }, tag: options.siteType, label: 'Credential check' },
          interpret: (snapshot) => {
            const failure = classifyFailure(snapshot, displayName);
            if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
            let parsed: unknown;
            try {
              parsed = readJson(snapshot, `${displayName} user filters`);
            } catch (error) {
              const booruError = error as BooruError;
              return { ok: false, kind: booruError.kind, message: booruError.message };
            }
            const record = asRecord(parsed);
            if (!record || !Array.isArray(record.filters)) {
              return { ok: false, kind: 'auth-failure', message: `${displayName} did not return the user's filters - the API key is probably wrong` };
            }
            return {
              ok: true,
              kind: 'ok',
              message: `API key accepted (${record.filters.length} personal filter(s))`,
              account: accountInfo(ctx.server.username || null, null, null),
            };
          },
        },
      ];
    },

    parseSearchResponse(snapshot, spec, ctx): SearchResult {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} search endpoint`);
      const record = asRecord(parsed);
      if (!record || !Array.isArray(record.images)) throw shapeError(`${displayName} search endpoint`, 'expected `{"images": […]}`');
      const perPage = Math.min(Math.max(spec.limit ?? 50, 1), adapter.capabilities.maxPostsPerRequest);
      const page = spec.page ?? 1;
      const total = pickNumber(record, 'total');
      const posts = record.images.map((raw) => normalizePost(raw, ctx, displayName));
      return {
        posts,
        page,
        limit: perPage,
        hasMore: total !== null ? page * perPage < total : posts.length >= perPage,
        totalCount: total,
        appliedTags: appliedQuery(ctx, spec),
        requestUrl: snapshot.redactedUrl,
        siteType: options.siteType,
        serverId: ctx.server.id,
      };
    },

    parsePostResponse(snapshot, postId, ctx): BooruPost {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} image ${postId}`);
      const record = asRecord(parsed);
      const image = record && asRecord(record.image) ? record.image : record;
      if (!image || !('id' in (image as Record<string, unknown>))) throw shapeError(`${displayName} image ${postId}`, 'expected `{"image": {…}}`');
      return normalizePost(image, ctx, displayName);
    },

    normalizePost: (raw, ctx) => normalizePost(raw, ctx, displayName),
    normalizeRating,

    canonicalRatingFor(siteToken: string): Rating | null {
      return CANONICAL_BY_TOKEN[siteToken.trim().toLowerCase()] ?? null;
    },

    /** Ratings are tags on Philomena, so the exclusion is simply `-tag`. */
    ratingQueryTags(allowed: Rating[]) {
      const allowedSet = new Set(allowed);
      const tags = SITE_RATING_TOKENS.filter((token) => !allowedSet.has(CANONICAL_BY_TOKEN[token]!)).map((token) => `-${token}`);
      const warnings: string[] = [];
      if (tags.length === 0 && allowed.length > 0) warnings.push('The rating filter allows every rating this site has - no exclusion terms were added.');
      return { tags, warnings };
    },

    classifyFailure: (snapshot) => classifyFailure(snapshot, displayName),

    credentialFields(): CredentialField[] {
      return [
        {
          key: 'apiKey',
          label: 'API key (optional)',
          type: 'password',
          required: false,
          requiredForAuth: true,
          secret: true,
          placeholder: 'from Account > API Key',
          help: 'Sent as the `key` query parameter. Unlocks your favourites/watch list; anonymous reads work without it.',
        },
        {
          key: 'userId',
          label: 'Filter ID (optional)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: options.everythingFilterId ?? 'e.g. 56027',
          help: 'Which site content filter applies to searches. Defaults to the instance\'s "Everything" filter when known.',
        },
        {
          key: 'username',
          label: 'Username (optional, UI context only)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
        },
        {
          key: 'customUserAgent',
          label: 'User-Agent (optional)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: `BooruServerManager/${PHILOMENA_VERSION}`,
        },
      ];
    },

    suggestUserAgent(): string {
      return `BooruServerManager/${PHILOMENA_VERSION}`;
    },
  };

  return adapter;
}

export const derpibooruAdapter: BooruAdapter = createPhilomenaAdapter({
  siteType: 'derpibooru',
  displayName: 'Derpibooru (Philomena)',
  baseUrl: 'https://derpibooru.org',
  label: 'Derpibooru',
  hostPatterns: ['derpibooru.org', 'www.derpibooru.org', 'trixiebooru.org', 'www.trixiebooru.org'],
  apiDocsUrl: 'https://derpibooru.org/pages/api',
  everythingFilterId: '56027',
});

export const furbooruAdapter: BooruAdapter = createPhilomenaAdapter({
  siteType: 'furbooru',
  displayName: 'Furbooru (Philomena)',
  baseUrl: 'https://furbooru.org',
  label: 'Furbooru',
  hostPatterns: ['furbooru.org', 'www.furbooru.org'],
  apiDocsUrl: 'https://furbooru.org/pages/api',
});

export const ponybooruAdapter: BooruAdapter = createPhilomenaAdapter({
  siteType: 'ponybooru',
  displayName: 'Ponybooru (Philomena)',
  baseUrl: 'https://ponybooru.org',
  label: 'Ponybooru',
  hostPatterns: ['ponybooru.org', 'www.ponybooru.org'],
  apiDocsUrl: 'https://ponybooru.org/pages/api',
});

export const PHILOMENA_ADAPTERS: BooruAdapter[] = [derpibooruAdapter, furbooruAdapter, ponybooruAdapter];
