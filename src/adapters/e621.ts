import type { AdapterContext, AdapterFailure, BooruAdapter, CredentialField, ProbeInterpretation, ValidationProbe } from '../core/adapter.js';
import { BooruError } from '../shared/errors.js';
import type { HttpResponseSnapshot, HttpRequestSpec } from '../shared/http.js';
import type { BooruPost, Rating, RouteMatch, SearchResult, SearchSpec } from '../shared/types.js';
import {
  absolutize,
  accountInfo,
  asRecord,
  classifyStatus,
  composeTags,
  detectAuthWordsInBody,
  encodeBase64,
  pickBoolean,
  pickGroup,
  pickNumber,
  pickString,
  ratingExclusions,
  readJson,
  shapeError,
} from './base.js';
import { hostMatchesPattern, splitTags, unique } from '../shared/util.js';

export const E621_VERSION = '0.1.0';
export const E621_DEFAULT_BASE = 'https://e621.net';
export const E621_SAFE_BASE = 'https://e926.net';

/** Rating tokens documented by e621: safe / questionable / explicit. */
const SITE_RATING_TOKENS = ['s', 'q', 'e'] as const;

const CANONICAL_BY_TOKEN: Record<string, Rating> = {
  s: 'safe',
  safe: 'safe',
  q: 'questionable',
  questionable: 'questionable',
  e: 'explicit',
  explicit: 'explicit',
};

const HOST_PATTERNS = ['e621.net', 'www.e621.net', 'e926.net', 'www.e926.net'];

function matchesHost(host: string): boolean {
  return HOST_PATTERNS.some((pattern) => hostMatchesPattern(host, pattern));
}

/** Authorization header value, or null when the profile has no credentials yet. */
export function e621AuthHeader(ctx: AdapterContext): string | null {
  const { username, apiKey } = ctx.server;
  if (!username.trim() || !apiKey.trim()) return null;
  return `Basic ${encodeBase64(`${username}:${apiKey}`)}`;
}

function withAuth(ctx: AdapterContext, spec: HttpRequestSpec): HttpRequestSpec {
  const header = e621AuthHeader(ctx);
  if (!header) return spec;
  return { ...spec, headers: { ...(spec.headers ?? {}), Authorization: header } };
}

/** e621 documents `_client` for clients (like extensions) that cannot set a UA header. */
function decorate(ctx: AdapterContext, spec: HttpRequestSpec): HttpRequestSpec {
  if (!ctx.settings.sendClientParam || !ctx.userAgent) return spec;
  try {
    const url = new URL(spec.url);
    if (!url.searchParams.has('_client')) url.searchParams.set('_client', ctx.userAgent);
    return { ...spec, url: url.toString() };
  } catch {
    return spec;
  }
}

function resolvedTags(ctx: AdapterContext, spec: SearchSpec, extra: string[]): string {
  const active = spec.ratingFilter?.enabled ? extra : [];
  return composeTags(spec.tags, active, ctx.settings.globalTagSuffix);
}

function interpretAuthProbe(snapshot: HttpResponseSnapshot, ctx: AdapterContext): ProbeInterpretation {
  const failure = classifyFailure(snapshot);
  if (failure) {
    return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  }
  return {
    ok: true,
    kind: 'ok',
    message: `Credentials accepted${ctx.server.username ? ` for ${ctx.server.username}` : ''}`,
    account: accountInfo(ctx.server.username || null, ctx.server.userId || null, null),
  };
}

function classifyFailure(snapshot: HttpResponseSnapshot): AdapterFailure | null {
  return classifyStatus(snapshot, 'e621') ?? detectAuthWordsInBody(snapshot.bodyText);
}

/** e621 exposes tags either as a category map (modern legacy/v2) or flat strings. */
function extractTags(post: Record<string, unknown>): { tags: string[]; categories: Record<string, string[]> } {
  const categories: Record<string, string[]> = {};
  const tagsRaw = post.tags;
  if (typeof tagsRaw === 'string') {
    categories.general = splitTags(tagsRaw);
  } else if (tagsRaw && typeof tagsRaw === 'object' && !Array.isArray(tagsRaw)) {
    for (const [category, value] of Object.entries(tagsRaw as Record<string, unknown>)) {
      if (Array.isArray(value)) {
        categories[category] = value.filter((tag): tag is string => typeof tag === 'string' && !!tag.trim());
      } else if (typeof value === 'string') {
        categories[category] = splitTags(value);
      }
    }
  }
  const flat: Array<[string, string]> = [
    ['tag_string_general', 'general'],
    ['tag_string_species', 'species'],
    ['tag_string_character', 'character'],
    ['tag_string_copyright', 'copyright'],
    ['tag_string_artist', 'artist'],
    ['tag_string_meta', 'meta'],
    ['tag_string_invalid', 'invalid'],
    ['tag_string_lore', 'lore'],
  ];
  for (const [key, category] of flat) {
    const value = post[key];
    if (typeof value === 'string' && value.trim()) categories[category] = splitTags(value);
  }
  return { tags: unique(Object.values(categories).flat()), categories };
}

function normalizePost(raw: unknown, ctx: AdapterContext): BooruPost {
  const post = asRecord(raw);
  if (!post) throw shapeError('e621 post', 'post entry was not an object');
  const file = pickGroup(post, 'file');
  const preview = pickGroup(post, 'preview');
  const sample = pickGroup(post, 'sample');

  const id = pickString(post, 'id') ?? '';
  const ext = ((pickString(file, 'ext') ?? pickString(post, 'file_ext') ?? '') as string).toLowerCase() || null;
  const fileUrl = absolutize(ctx.baseUrl, pickString(file, 'url') ?? pickString(post, 'file_url')) ?? '';
  const previewUrl = absolutize(ctx.baseUrl, pickString(preview, 'url') ?? pickString(post, 'preview_url')) ?? fileUrl;
  const sampleHas = pickBoolean(sample, 'has') ?? pickBoolean(post, 'has_sample');
  const sampleUrl = sampleHas === false ? null : absolutize(ctx.baseUrl, pickString(sample, 'url') ?? pickString(post, 'sample_url'));

  const { tags, categories } = extractTags(post);
  const rawRating = pickString(post, 'rating');
  const sourcesRaw = post.sources ?? post.source;
  const sources = Array.isArray(sourcesRaw)
    ? unique(sourcesRaw.filter((value): value is string => typeof value === 'string' && !!value.trim()))
    : splitTags(typeof sourcesRaw === 'string' ? sourcesRaw : '');

  const scoreGroup = pickGroup(post, 'score');
  const score = pickNumber(scoreGroup, 'total') ?? pickNumber(post, 'score');

  return {
    serverId: ctx.server.id,
    siteType: 'e621',
    id,
    postUrl: `${ctx.baseUrl}/posts/${id}`,
    fileUrl,
    previewUrl,
    sampleUrl,
    width: pickNumber(file, 'width') ?? pickNumber(post, 'width'),
    height: pickNumber(file, 'height') ?? pickNumber(post, 'height'),
    ext,
    sizeBytes: pickNumber(file, 'size') ?? pickNumber(post, 'file_size'),
    rating: normalizeRating(rawRating),
    rawRating,
    tags,
    tagCategories: categories,
    artistTags: categories.artist ?? [],
    characterTags: categories.character ?? [],
    score,
    md5: pickString(file, 'md5') ?? pickString(post, 'md5'),
    sources,
    createdAt: pickString(post, 'created_at'),
    isVideo: !!ext && ['webm', 'mp4'].includes(ext),
    isAnimated: !!ext && ['gif', 'webm', 'mp4', 'swf'].includes(ext),
    parentId: pickString(post, 'parent_id'),
    hasChildren: pickBoolean(post, 'has_children') ?? false,
    description: pickString(post, 'description'),
  };
}

function normalizeRating(raw: unknown): Rating {
  if (typeof raw !== 'string') return 'unknown';
  return CANONICAL_BY_TOKEN[raw.trim().toLowerCase()] ?? 'unknown';
}

/**
 * e621 / e926 adapter.
 *
 * - Auth: username + API key. Preferred transport is HTTP Basic (documented).
 * - User-Agent: a descriptive, non-browser UA is mandatory; extensions cannot
 *   set the header from `fetch`, so the documented `_client` query parameter is
 *   appended when enabled in Settings (see docs/SECURITY.md and README).
 * - Reference: https://e621.net/help/api
 */
export const e621Adapter: BooruAdapter = {
  siteType: 'e621',
  displayName: 'e621 / e926',
  hostPatterns: HOST_PATTERNS,
  siteRatingTokens: SITE_RATING_TOKENS,

  capabilities: {
    supportsAnonymousAccess: true,
    supportsRatingFilter: true,
    requiresUsername: true,
    requiresUserId: false,
    requiresApiKey: true,
    requiresUserAgent: true,
    authStyle: 'basic-or-query',
    supportsBasicAuthHeader: true,
    maxPostsPerRequest: 320,
    maxPage: null,
    minRequestIntervalMs: 1000,
    supportsPostLookup: true,
    supportsTagSearch: true,
    supportsIdPagination: true,
    apiDocsUrl: 'https://e621.net/help/api',
    notes: [
      'Hard limit of 2 requests/second; sustained use should stay near 1 request/second.',
      'A descriptive User-Agent is required. Do not impersonate a browser.',
      'Extensions that cannot set headers may send the documented `_client` query parameter instead.',
      'Listing pagination should use page=b<lowest id> for stable iteration.',
    ],
  },

  defaults: {
    baseUrl: E621_DEFAULT_BASE,
    label: 'e621 (main)',
    ratings: ['safe'],
    userAgentHint: `BooruServerManager/${E621_VERSION} (browser extension; by <your e621 username>)`,
  },

  normalizeBaseUrl(raw: string): string {
    return raw.trim().replace(/\/+$/, '').replace(/\/(posts\.json|posts|post)\/?$/i, '');
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
    if (segments[0] === 'posts' && segments[1] && /^\d+$/.test(segments[1])) {
      return { siteType: 'e621', kind: 'post', postId: segments[1], tags: null, page: null, canonicalUrl };
    }
    if (segments[0] === 'posts') {
      const tags = parsed.searchParams.get('tags');
      return { siteType: 'e621', kind: tags ? 'search' : 'index', postId: null, tags, page: null, canonicalUrl };
    }
    if (segments[0] === 'pools' && segments[1]) {
      return { siteType: 'e621', kind: 'pool', postId: null, tags: null, page: null, canonicalUrl };
    }
    return { siteType: 'e621', kind: 'index', postId: null, tags: null, page: null, canonicalUrl };
  },

  postUrl(baseUrl: string, postId: string): string {
    return `${baseUrl.replace(/\/+$/, '')}/posts/${postId}`;
  },

  buildSearchRequest(ctx, spec): HttpRequestSpec {
    const limit = Math.min(Math.max(spec.limit ?? 50, 1), this.capabilities.maxPostsPerRequest);
    const filters = this.ratingQueryTags(spec.ratingFilter?.allowed ?? []).tags;
    const tags = resolvedTags(ctx, spec, filters);
    const params = new URLSearchParams();
    if (tags) params.set('tags', tags);
    params.set('limit', String(limit));
    const page = spec.pageToken ?? (spec.page !== undefined && spec.page !== null ? String(spec.page) : null);
    if (page) params.set('page', page);
    const bare: HttpRequestSpec = {
      url: `${ctx.baseUrl}/posts.json?${params.toString()}`,
      method: 'GET',
      headers: { Accept: 'application/json' },
      tag: 'e621',
      label: 'Search posts',
    };
    return decorate(ctx, withAuth(ctx, bare));
  },

  buildPostRequest(ctx, postId): HttpRequestSpec {
    const bare: HttpRequestSpec = {
      url: `${ctx.baseUrl}/posts/${encodeURIComponent(postId)}.json`,
      method: 'GET',
      headers: { Accept: 'application/json' },
      tag: 'e621',
      label: `Fetch post ${postId}`,
    };
    return decorate(ctx, withAuth(ctx, bare));
  },

  buildValidationProbes(ctx): ValidationProbe[] {
    const endpointProbe: ValidationProbe = {
      id: 'e621-endpoint',
      label: 'Listing endpoint (anonymous)',
      purpose: 'endpoint',
      request: decorate(ctx, {
        url: `${ctx.baseUrl}/posts.json?limit=1`,
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: 'e621',
        label: 'Endpoint check',
      }),
      interpret: (snapshot) => {
        const failure = classifyFailure(snapshot);
        if (failure) {
          return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
        }
        let parsed: unknown;
        try {
          parsed = readJson(snapshot, 'e621 listing endpoint');
        } catch (error) {
          const booruError = error as BooruError;
          return {
            ok: false,
            kind: booruError.kind,
            message: booruError.message,
            warnings: booruError.hint ? [booruError.hint] : [],
          };
        }
        if (!Array.isArray(parsed)) {
          return {
            ok: false,
            kind: 'endpoint-mismatch',
            message: 'The endpoint answered with JSON, but not an e621 post listing',
            warnings: ['Expected a JSON array of posts at /posts.json.'],
          };
        }
        return { ok: true, kind: 'ok', message: `Endpoint OK - received ${parsed.length} post(s) anonymously` };
      },
    };

    const canProbeAuth = !!e621AuthHeader(ctx);
    // Private user data (dmails) requires authorization, which makes it a
    // reliable credential probe. Favourites act as a documented fallback for
    // forks that do not expose the dmail route.
    const authProbes: ValidationProbe[] = [
      {
        id: 'e621-auth-dmail',
        label: 'Authenticated request (dmails)',
        purpose: 'auth',
        enabled: canProbeAuth,
        optional: true,
        request: decorate(ctx, withAuth(ctx, { url: `${ctx.baseUrl}/dmail.json?limit=1`, tag: 'e621', label: 'Credential check' })),
        interpret: (snapshot) => interpretAuthProbe(snapshot, ctx),
      },
      {
        id: 'e621-auth-favorites',
        label: 'Authenticated request (favourites, fallback)',
        purpose: 'auth',
        enabled: canProbeAuth,
        optional: true,
        request: decorate(ctx, withAuth(ctx, { url: `${ctx.baseUrl}/favorites.json?limit=1`, tag: 'e621', label: 'Credential check (fallback)' })),
        interpret: (snapshot) => interpretAuthProbe(snapshot, ctx),
      },
    ];

    return [endpointProbe, ...authProbes];
  },

  parseSearchResponse(snapshot, spec, ctx): SearchResult {
    const failure = classifyFailure(snapshot);
    if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
    const parsed = readJson<unknown>(snapshot, 'e621 listing endpoint');
    const list = Array.isArray(parsed) ? parsed : asRecord(parsed)?.posts;
    if (!Array.isArray(list)) throw shapeError('e621 listing endpoint', 'expected a JSON array of posts');
    const limit = Math.min(Math.max(spec.limit ?? 50, 1), this.capabilities.maxPostsPerRequest);
    return {
      posts: list.map((raw) => normalizePost(raw, ctx)),
      page: spec.page ?? 1,
      limit,
      hasMore: list.length >= limit,
      totalCount: null,
      appliedTags: resolvedTags(ctx, spec, this.ratingQueryTags(spec.ratingFilter?.allowed ?? []).tags),
      requestUrl: snapshot.redactedUrl,
      siteType: 'e621',
      serverId: ctx.server.id,
    };
  },

  parsePostResponse(snapshot, postId, ctx): BooruPost {
    const failure = classifyFailure(snapshot);
    if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
    const parsed = readJson<unknown>(snapshot, `e621 post ${postId}`);
    const record = asRecord(parsed);
    const raw = record && asRecord(record.post) ? (record.post as Record<string, unknown>) : record;
    if (!raw) throw shapeError(`e621 post ${postId}`, 'expected a `{ post: { ... } }` object');
    return normalizePost(raw, ctx);
  },

  normalizePost,

  normalizeRating,

  canonicalRatingFor(siteToken: string): Rating | null {
    return CANONICAL_BY_TOKEN[siteToken.trim().toLowerCase()] ?? null;
  },

  ratingQueryTags(allowed: Rating[]) {
    return ratingExclusions(SITE_RATING_TOKENS, (token) => CANONICAL_BY_TOKEN[token] ?? null, allowed);
  },

  classifyFailure,

  credentialFields(): CredentialField[] {
    return [
      {
        key: 'username',
        label: 'Username',
        type: 'text',
        required: false,
        requiredForAuth: true,
        secret: false,
        placeholder: 'your_e621_username',
        help: 'e621 authenticates with username + API key.',
      },
      {
        key: 'apiKey',
        label: 'API key',
        type: 'password',
        required: false,
        requiredForAuth: true,
        secret: true,
        placeholder: 'from Account > Manage API Access',
        help: 'Enable API access under Account > Manage API Access, then create a key.',
      },
      {
        key: 'customUserAgent',
        label: 'User-Agent (required by e621 policy)',
        type: 'text',
        required: false,
        requiredForAuth: false,
        secret: false,
        placeholder: `BooruServerManager/${E621_VERSION} (by your_username on e621)`,
        help: 'Must be descriptive and include your e621 username. Browser User-Agents are blocked.',
      },
    ];
  },

  suggestUserAgent({ server }): string {
    return `BooruServerManager/${E621_VERSION} (by ${server.username?.trim() || 'your_username'} on e621)`;
  },

  decorateRequest: decorate,
};
