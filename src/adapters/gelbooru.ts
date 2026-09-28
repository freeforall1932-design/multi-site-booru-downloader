import type { AdapterContext, AdapterFailure, BooruAdapter, CredentialField, ProbeInterpretation, ValidationProbe } from '../core/adapter.js';
import { BooruError } from '../shared/errors.js';
import type { HttpResponseSnapshot, HttpRequestSpec } from '../shared/http.js';
import type { BooruPost, Rating, RouteMatch, SearchResult, SearchSpec, SiteType } from '../shared/types.js';
import {
  absolutize,
  accountInfo,
  asRecord,
  classifyStatus,
  composeTags,
  detectAuthWordsInBody,
  pickBoolean,
  pickNumber,
  pickString,
  ratingExclusions,
  readJson,
  shapeError,
} from './base.js';
import { hostMatchesPattern, splitTags, unique } from '../shared/util.js';

export const GELBOORU_VERSION = '0.1.0';
export const GELBOORU_DEFAULT_BASE = 'https://gelbooru.com';

/** Gelbooru ratings: general / sensitive / questionable / explicit. */
const SITE_RATING_TOKENS = ['general', 'sensitive', 'questionable', 'explicit'] as const;

const CANONICAL_BY_TOKEN: Record<string, Rating> = {
  general: 'general',
  // `safe` is the historical Gelbooru token; new posts use `general`.
  safe: 'general',
  sensitive: 'sensitive',
  questionable: 'questionable',
  explicit: 'explicit',
};

export interface GelbooruLikeOptions {
  siteType: SiteType;
  displayName: string;
  baseUrl: string;
  label: string;
  hostPatterns: string[];
  apiDocsUrl: string;
  notes?: string[];
  /**
   * The instance refuses anonymous API reads (rule34.xxx since 2024). The
   * endpoint probe then runs with credentials and the UI marks them required.
   */
  requiresCredentials?: boolean;
  /** Optional hint shown for the API-key field (where to find it on this site). */
  apiKeyPlaceholder?: string;
}

/**
 * Search endpoint used by every Gelbooru-compatible instance.
 * `json=1` is what makes the API answer with the `{"@attributes":…,"post":[…]}`
 * envelope instead of XML.
 */
export function gelbooruSearchUrl(
  baseUrl: string,
  params: { tags?: string; limit?: number; pid?: number; postId?: string; credentials?: { apiKey: string; userId: string } },
): string {
  const url = new URL(`${baseUrl.replace(/\/+$/, '')}/index.php`);
  url.searchParams.set('page', 'dapi');
  url.searchParams.set('s', 'post');
  url.searchParams.set('q', 'index');
  url.searchParams.set('json', '1');
  if (params.postId) url.searchParams.set('id', params.postId);
  if (params.tags) url.searchParams.set('tags', params.tags);
  if (params.limit !== undefined) url.searchParams.set('limit', String(params.limit));
  if (params.pid !== undefined) url.searchParams.set('pid', String(params.pid));
  // Gelbooru's documented authentication method is query parameters.
  if (params.credentials?.apiKey) url.searchParams.set('api_key', params.credentials.apiKey);
  if (params.credentials?.userId) url.searchParams.set('user_id', params.credentials.userId);
  return url.toString();
}

/** Unwrap the Gelbooru envelope, tolerating the single-post object quirk. */
export function unwrapGelbooruList(payload: unknown): Record<string, unknown>[] {
  if (Array.isArray(payload)) {
    return payload.filter((entry): entry is Record<string, unknown> => !!asRecord(entry));
  }
  const record = asRecord(payload);
  if (!record) return [];
  const list = record.post;
  if (Array.isArray(list)) return list.filter((entry): entry is Record<string, unknown> => !!asRecord(entry));
  const single = asRecord(list);
  if (single) return [single];
  return [];
}

function credentialsFor(ctx: AdapterContext): { apiKey: string; userId: string } | null {
  const apiKey = ctx.server.apiKey.trim();
  const userId = ctx.server.userId.trim();
  if (!apiKey && !userId) return null;
  return { apiKey, userId };
}

function classifyFailure(snapshot: HttpResponseSnapshot, displayName: string): AdapterFailure | null {
  const generic = classifyStatus(snapshot, displayName);
  if (generic) return generic;
  const words = detectAuthWordsInBody(snapshot.bodyText);
  if (words) return words;
  // rule34.xxx answers 200 with a bare JSON string when api_key/user_id are missing.
  if (/missing authentication|authentication required/i.test(snapshot.bodyText.slice(0, 200))) {
    return {
      kind: 'auth-failure',
      message: `${displayName} requires api_key + user_id for every API request`,
      hint: 'Create an API key on the site account page and fill in both the API key and the numeric user id.',
    };
  }
  // Gelbooru answers 200 with a plain-text error body in some failure modes.
  if (snapshot.status === 200 && !snapshot.contentType.includes('json') && !snapshot.bodyText.trim().startsWith('{') && !snapshot.bodyText.trim().startsWith('[')) {
    const head = snapshot.bodyText.trim().slice(0, 160);
    if (head && !head.startsWith('<')) {
      return {
        kind: 'parse-failure',
        message: `${displayName} returned an unexpected body: ${head}`,
        hint: 'Gelbooru-compatible APIs usually need `json=1` on /index.php?page=dapi&s=post&q=index.',
      };
    }
  }
  return null;
}

function normalizeRating(raw: unknown): Rating {
  if (typeof raw !== 'string') return 'unknown';
  return CANONICAL_BY_TOKEN[raw.trim().toLowerCase()] ?? 'unknown';
}

/** Build an image URL from Gelbooru's `directory` + `image` fields as a fallback. */
function fallbackFileUrl(baseUrl: string, post: Record<string, unknown>): string | null {
  const directory = pickString(post, 'directory');
  const image = pickString(post, 'image');
  if (!directory || !image) return null;
  return `${baseUrl.replace(/\/+$/, '')}/images/${directory}/${image}`;
}

function normalizePost(raw: unknown, ctx: AdapterContext, displayName: string): BooruPost {
  const post = asRecord(raw);
  if (!post) throw shapeError(`${displayName} post`, 'post entry was not an object');
  const id = pickString(post, 'id') ?? '';
  const fileUrl =
    absolutize(ctx.baseUrl, pickString(post, 'file_url')) ?? absolutize(ctx.baseUrl, fallbackFileUrl(ctx.baseUrl, post)) ?? '';
  const ext = deriveExt(post, fileUrl);
  const previewUrl = absolutize(ctx.baseUrl, pickString(post, 'preview_url')) ?? fileUrl;
  const sampleUrl = absolutize(ctx.baseUrl, pickString(post, 'sample_url'));
  const tags = splitTags(pickString(post, 'tags'));
  const rawRating = pickString(post, 'rating');
  const sourceRaw = pickString(post, 'source');
  const sources = sourceRaw ? unique(sourceRaw.split(/[\s\n]+/).filter((value) => /^https?:\/\//i.test(value))) : [];

  return {
    serverId: ctx.server.id,
    siteType: ctx.server.siteType,
    id,
    postUrl: `${ctx.baseUrl}/index.php?page=post&s=view&id=${id}`,
    fileUrl,
    previewUrl,
    sampleUrl,
    width: pickNumber(post, 'width'),
    height: pickNumber(post, 'height'),
    ext,
    sizeBytes: pickNumber(post, 'file_size') ?? null,
    rating: normalizeRating(rawRating),
    rawRating,
    tags,
    // Gelbooru's post API returns one flat, alphabetically sorted tag string.
    tagCategories: { general: tags },
    artistTags: [],
    characterTags: [],
    score: pickNumber(post, 'score'),
    md5: pickString(post, 'md5', 'hash'),
    sources,
    createdAt: pickString(post, 'created_at'),
    isVideo: !!ext && ['webm', 'mp4'].includes(ext),
    isAnimated: !!ext && ['gif', 'webm', 'mp4'].includes(ext),
    parentId: pickString(post, 'parent_id'),
    hasChildren: pickBoolean(post, 'has_children') ?? false,
    description: pickString(post, 'title', 'description'),
  };
}

function deriveExt(post: Record<string, unknown>, fileUrl: string): string | null {
  const direct = pickString(post, 'file_ext');
  if (direct) return direct.toLowerCase();
  const match = /\.([a-z0-9]{1,5})(?:[?#]|$)/i.exec(fileUrl);
  return match?.[1]?.toLowerCase() ?? null;
}

function interpretEndpointProbe(snapshot: HttpResponseSnapshot, displayName: string): ProbeInterpretation {
  const failure = classifyFailure(snapshot, displayName);
  if (failure) {
    return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  }
  let parsed: unknown;
  try {
    parsed = readJson(snapshot, `${displayName} listing endpoint`);
  } catch (error) {
    const booruError = error as BooruError;
    return { ok: false, kind: booruError.kind, message: booruError.message, warnings: booruError.hint ? [booruError.hint] : [] };
  }
  if (Array.isArray(parsed)) {
    // Gelbooru 0.1.11 forks (safebooru.org, rule34.xxx, xbooru, …) answer with a bare array.
    return { ok: true, kind: 'ok', message: `Endpoint OK - legacy DAPI listing responded with ${parsed.length} post(s)` };
  }
  const record = asRecord(parsed);
  if (!record) {
    return {
      ok: false,
      kind: 'endpoint-mismatch',
      message: `${displayName} did not return the expected JSON envelope`,
      warnings: ['Expected `{"@attributes": {…}, "post": […]}` or a bare post array; add `json=1` for Gelbooru-compatible APIs.'],
    };
  }
  const hasEnvelope = !!asRecord(record['@attributes']) || Array.isArray(record.post) || !!asRecord(record.post);
  if (!hasEnvelope) {
    return {
      ok: false,
      kind: 'endpoint-mismatch',
      message: `${displayName} answered with JSON, but without the DAPI envelope`,
      warnings: ['A Gelbooru-compatible API must expose /index.php?page=dapi&s=post&q=index&json=1.'],
    };
  }
  const count = unwrapGelbooruList(parsed).length;
  return { ok: true, kind: 'ok', message: `Endpoint OK - DAPI listing responded with ${count} post(s)` };
}

function interpretAuthProbe(snapshot: HttpResponseSnapshot, displayName: string, ctx: AdapterContext): ProbeInterpretation {
  const failure = classifyFailure(snapshot, displayName);
  if (failure) {
    return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  }
  return {
    ok: true,
    kind: 'ok',
    message: `Credentials accepted${ctx.server.username ? ` (${ctx.server.username})` : ''}`,
    account: accountInfo(ctx.server.username || null, ctx.server.userId || null, null),
  };
}

/**
 * Build a Gelbooru-family adapter. The same behaviour covers gelbooru.com and
 * its many compatible forks (Rule34, Safebooru, Xbooru, …); a fork only needs a
 * different site type, display name, base URL and host patterns.
 */
export function createGelbooruLikeAdapter(options: GelbooruLikeOptions): BooruAdapter {
  const displayName = options.displayName;
  const hostPatterns = options.hostPatterns;

  const matchesHost = (host: string): boolean => hostPatterns.some((pattern) => hostMatchesPattern(host, pattern));

  const buildSearchRequest = (ctx: AdapterContext, spec: SearchSpec): HttpRequestSpec => {
    const limit = Math.min(Math.max(spec.limit ?? 100, 1), 100);
    const filters = adapter.ratingQueryTags(spec.ratingFilter?.allowed ?? []).tags;
    const active = spec.ratingFilter?.enabled ? filters : [];
    const tags = composeTags(spec.tags, active, ctx.settings.globalTagSuffix);
    return {
      url: gelbooruSearchUrl(ctx.baseUrl, {
        tags: tags || undefined,
        limit,
        pid: spec.page ?? 0,
        ...(credentialsFor(ctx) ? { credentials: credentialsFor(ctx)! } : {}),
      }),
      method: 'GET',
      headers: { Accept: 'application/json' },
      tag: options.siteType,
      label: 'Search posts',
    };
  };

  const adapter: BooruAdapter = {
    siteType: options.siteType,
    displayName,
    hostPatterns,
    siteRatingTokens: SITE_RATING_TOKENS,

    capabilities: {
      supportsAnonymousAccess: !options.requiresCredentials,
      supportsRatingFilter: true,
      requiresUsername: false,
      requiresUserId: true,
      requiresApiKey: true,
      requiresUserAgent: false,
      authStyle: 'query-with-userid',
      supportsBasicAuthHeader: false,
      maxPostsPerRequest: 100,
      // Gelbooru caps the API at pid < 20000; deeper paging needs `id:` filters.
      maxPage: 20_000,
      minRequestIntervalMs: 1000,
      supportsPostLookup: true,
      supportsTagSearch: true,
      supportsIdPagination: false,
      apiDocsUrl: options.apiDocsUrl,
      notes: [
        'Authentication is api_key + user_id sent as query parameters (username is UI context only).',
        'A `pid` page number is used for paging; results shift as posts are added.',
        'Some instances sit behind Cloudflare and reject unusual User-Agents.',
        ...(options.notes ?? []),
      ],
    },

    defaults: {
      baseUrl: options.baseUrl,
      label: options.label,
      ratings: ['general', 'sensitive'],
      userAgentHint: `BooruServerManager/${GELBOORU_VERSION}`,
    },

    normalizeBaseUrl(raw: string): string {
      return raw
        .trim()
        .replace(/\/+$/, '')
        .replace(/\/index\.php.*$/i, '')
        .replace(/\/(posts|post)\/?$/i, '');
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
      const canonicalUrl = `${parsed.origin}${parsed.pathname}`;
      const page = parsed.searchParams.get('page');
      const section = parsed.searchParams.get('s');
      const id = parsed.searchParams.get('id');
      if (page === 'post' && section === 'view' && id && /^\d+$/.test(id)) {
        return { siteType: options.siteType, kind: 'post', postId: id, tags: null, page: null, canonicalUrl };
      }
      const tags = parsed.searchParams.get('tags');
      const pid = parsed.searchParams.get('pid');
      if (page === 'post' && section === 'list') {
        return {
          siteType: options.siteType,
          kind: tags ? 'search' : 'index',
          postId: null,
          tags,
          page: pid && /^\d+$/.test(pid) ? Number(pid) : null,
          canonicalUrl,
        };
      }
      return { siteType: options.siteType, kind: 'index', postId: null, tags: null, page: null, canonicalUrl };
    },

    postUrl(baseUrl: string, postId: string): string {
      return `${baseUrl.replace(/\/+$/, '')}/index.php?page=post&s=view&id=${postId}`;
    },

    buildSearchRequest,

    buildPostRequest(ctx, postId): HttpRequestSpec {
      return {
        url: gelbooruSearchUrl(ctx.baseUrl, {
          postId,
          limit: 1,
          ...(credentialsFor(ctx) ? { credentials: credentialsFor(ctx)! } : {}),
        }),
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: options.siteType,
        label: `Fetch post ${postId}`,
      };
    },

    buildValidationProbes(ctx): ValidationProbe[] {
      const hasCredentials = !!credentialsFor(ctx);
      return [
        {
          id: `${options.siteType}-endpoint`,
          label: options.requiresCredentials ? 'DAPI listing endpoint (credentials required)' : 'DAPI listing endpoint (anonymous)',
          purpose: 'endpoint',
          request: {
            url: gelbooruSearchUrl(ctx.baseUrl, {
              limit: 1,
              ...(options.requiresCredentials && credentialsFor(ctx) ? { credentials: credentialsFor(ctx)! } : {}),
            }),
            method: 'GET',
            headers: { Accept: 'application/json' },
            tag: options.siteType,
            label: 'Endpoint check',
          },
          interpret: (snapshot) => interpretEndpointProbe(snapshot, displayName),
        },
        {
          id: `${options.siteType}-auth`,
          label: 'Authenticated listing request (api_key + user_id)',
          purpose: 'auth',
          enabled: hasCredentials,
          request: {
            url: gelbooruSearchUrl(ctx.baseUrl, { limit: 1, credentials: credentialsFor(ctx) ?? { apiKey: '', userId: '' } }),
            method: 'GET',
            headers: { Accept: 'application/json' },
            tag: options.siteType,
            label: 'Credential check',
          },
          interpret: (snapshot) => interpretAuthProbe(snapshot, displayName, ctx),
        },
      ];
    },

    parseSearchResponse(snapshot, spec, ctx): SearchResult {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} listing endpoint`);
      // Gelbooru 0.2 wraps posts in `{"@attributes":…,"post":[…]}`; 0.1.11 forks
      // (safebooru.org, rule34.xxx, xbooru, tbib, …) return a bare array.
      const record = Array.isArray(parsed) ? null : asRecord(parsed);
      if (!record && !Array.isArray(parsed)) throw shapeError(`${displayName} listing endpoint`, 'expected the DAPI JSON envelope or a post array');
      // A DAPI response always carries `@attributes` and/or a `post` key; JSON
      // without them means this URL is not a Gelbooru-compatible API.
      if (record && record['@attributes'] === undefined && record.post === undefined) {
        throw new BooruError(`${displayName} answered with JSON, but not the DAPI envelope`, {
          kind: 'endpoint-mismatch',
          status: snapshot.status,
          hint: 'Gelbooru-compatible APIs answer /index.php?page=dapi&s=post&q=index&json=1 with {"@attributes":…,"post":[…]}',
        });
      }
      const attributes = record ? asRecord(record['@attributes']) : null;
      const limit = Math.min(Math.max(spec.limit ?? 100, 1), 100);
      const offset = attributes ? pickNumber(attributes, 'offset') : null;
      const count = attributes ? pickNumber(attributes, 'count') : null;
      const posts = unwrapGelbooruList(parsed).map((raw) => normalizePost(raw, ctx, displayName));
      const page = spec.page ?? 0;
      const hasMore = count !== null && offset !== null ? offset + posts.length < count : posts.length >= limit;
      return {
        posts,
        page,
        limit,
        hasMore,
        totalCount: count,
        appliedTags: composeTags(
          spec.tags,
          spec.ratingFilter?.enabled ? adapter.ratingQueryTags(spec.ratingFilter.allowed).tags : [],
          ctx.settings.globalTagSuffix,
        ),
        requestUrl: snapshot.redactedUrl,
        siteType: options.siteType,
        serverId: ctx.server.id,
      };
    },

    parsePostResponse(snapshot, postId, ctx): BooruPost {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} post ${postId}`);
      const list = unwrapGelbooruList(parsed);
      const first = list[0];
      if (!first) {
        throw new BooruError(`${displayName} has no post ${postId}`, {
          kind: 'parse-failure',
          status: snapshot.status,
          hint: 'Gelbooru returns an empty envelope for missing or deleted posts.',
        });
      }
      return normalizePost(first, ctx, displayName);
    },

    normalizePost: (raw, ctx) => normalizePost(raw, ctx, displayName),

    normalizeRating,

    canonicalRatingFor(siteToken: string): Rating | null {
      return CANONICAL_BY_TOKEN[siteToken.trim().toLowerCase()] ?? null;
    },

    ratingQueryTags(allowed: Rating[]) {
      return ratingExclusions(SITE_RATING_TOKENS, (token) => CANONICAL_BY_TOKEN[token] ?? null, allowed);
    },

    classifyFailure: (snapshot) => classifyFailure(snapshot, displayName),

    credentialFields(): CredentialField[] {
      return [
        {
          key: 'apiKey',
          label: 'API key',
          type: 'password',
          required: !!options.requiresCredentials,
          requiredForAuth: true,
          secret: true,
          placeholder: options.apiKeyPlaceholder ?? 'from Account > Options > API Access',
          help: options.requiresCredentials
            ? `${displayName} rejects anonymous API requests: api_key + user_id are required.`
            : 'Gelbooru authenticates with api_key + user_id.',
        },
        {
          key: 'userId',
          label: 'User ID (numeric)',
          type: 'text',
          required: !!options.requiresCredentials,
          requiredForAuth: true,
          secret: false,
          placeholder: 'e.g. 1234567',
          help: 'Shown on the account options/profile page. Required alongside the API key.',
        },
        {
          key: 'username',
          label: 'Username (optional, UI context only)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: 'your_gelbooru_username',
          help: 'Not used for authentication; kept so the server list shows which account this is.',
        },
        {
          key: 'customUserAgent',
          label: 'User-Agent (only if the instance needs it)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: `BooruServerManager/${GELBOORU_VERSION}`,
          help: 'Some Gelbooru-compatible instances sit behind Cloudflare.',
        },
      ];
    },

    suggestUserAgent(): string {
      return `BooruServerManager/${GELBOORU_VERSION}`;
    },
  };

  return adapter;
}

/** gelbooru.com itself. */
export const gelbooruAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'gelbooru',
  displayName: 'Gelbooru',
  baseUrl: GELBOORU_DEFAULT_BASE,
  label: 'Gelbooru (main)',
  hostPatterns: ['gelbooru.com', 'www.gelbooru.com'],
  apiDocsUrl: 'https://gelbooru.com/index.php?page=wiki&s=view&id=18780',
});
