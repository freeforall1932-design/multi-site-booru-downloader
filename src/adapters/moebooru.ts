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

export const MOEBOORU_VERSION = '0.1.0';

/** Moebooru ratings: s / q / e (same vocabulary as e621, same as legacy Danbooru 1). */
const SITE_RATING_TOKENS = ['s', 'q', 'e'] as const;

const CANONICAL_BY_TOKEN: Record<string, Rating> = {
  s: 'safe',
  safe: 'safe',
  q: 'questionable',
  questionable: 'questionable',
  e: 'explicit',
  explicit: 'explicit',
};

export interface MoebooruOptions {
  siteType: SiteType;
  displayName: string;
  baseUrl: string;
  label: string;
  hostPatterns: string[];
  apiDocsUrl?: string;
  notes?: string[];
}

/**
 * Moebooru search URL (`/post.json`). Authentication is `login` +
 * `password_hash` as query parameters; the hash is
 * `SHA1("choujin-steiner--" + password + "--")` per the Moebooru API docs, which
 * is what the profile's "API key" field carries for this site type.
 */
export function moebooruSearchUrl(
  baseUrl: string,
  params: { tags?: string; limit?: number; page?: number; credentials?: { login: string; passwordHash: string } | null },
): string {
  const url = new URL(`${baseUrl.replace(/\/+$/, '')}/post.json`);
  if (params.tags) url.searchParams.set('tags', params.tags);
  if (params.limit !== undefined) url.searchParams.set('limit', String(params.limit));
  if (params.page !== undefined) url.searchParams.set('page', String(params.page));
  if (params.credentials?.login && params.credentials.passwordHash) {
    url.searchParams.set('login', params.credentials.login);
    url.searchParams.set('password_hash', params.credentials.passwordHash);
  }
  return url.toString();
}

function credentialsFor(ctx: AdapterContext): { login: string; passwordHash: string } | null {
  const login = ctx.server.username.trim();
  const passwordHash = ctx.server.apiKey.trim();
  if (!login || !passwordHash) return null;
  return { login, passwordHash };
}

function classifyFailure(snapshot: HttpResponseSnapshot, displayName: string): AdapterFailure | null {
  const generic = classifyStatus(snapshot, displayName);
  if (generic) return generic;
  const words = detectAuthWordsInBody(snapshot.bodyText);
  if (words) return words;
  // Moebooru answers `{"success":false,"reason":"..."}` with HTTP 200/403 in some cases.
  const trimmed = snapshot.bodyText.trim();
  if (trimmed.startsWith('{') && /"success"\s*:\s*false/.test(trimmed)) {
    const reason = /"reason"\s*:\s*"([^"]*)"/.exec(trimmed)?.[1] ?? 'unspecified';
    return { kind: /login|password|auth/i.test(reason) ? 'auth-failure' : 'server-error', message: `${displayName} refused the request: ${reason}` };
  }
  return null;
}

function normalizeRating(raw: unknown): Rating {
  if (typeof raw !== 'string') return 'unknown';
  return CANONICAL_BY_TOKEN[raw.trim().toLowerCase()] ?? 'unknown';
}

function deriveExt(post: Record<string, unknown>, fileUrl: string): string | null {
  const direct = pickString(post, 'file_ext');
  if (direct) return direct.toLowerCase();
  const match = /\.([a-z0-9]{1,5})(?:[?#]|$)/i.exec(fileUrl);
  return match?.[1]?.toLowerCase() ?? null;
}

function normalizePost(raw: unknown, ctx: AdapterContext, displayName: string): BooruPost {
  const post = asRecord(raw);
  if (!post) throw shapeError(`${displayName} post`, 'post entry was not an object');
  const id = pickString(post, 'id') ?? '';
  const fileUrl = absolutize(ctx.baseUrl, pickString(post, 'file_url')) ?? '';
  const ext = deriveExt(post, fileUrl);
  const previewUrl = absolutize(ctx.baseUrl, pickString(post, 'preview_url')) ?? fileUrl;
  // Moebooru offers `sample_url` (resized) and `jpeg_url` (full-size JPEG of PNG originals).
  const sampleUrl = absolutize(ctx.baseUrl, pickString(post, 'sample_url', 'jpeg_url'));
  const tags = splitTags(pickString(post, 'tags'));
  const rawRating = pickString(post, 'rating');
  const sourceRaw = pickString(post, 'source');
  const sources = sourceRaw ? unique(sourceRaw.split(/[\s\n]+/).filter((value) => /^https?:\/\//i.test(value))) : [];
  const createdAtRaw = post.created_at;
  const createdAt =
    typeof createdAtRaw === 'number' ? new Date(createdAtRaw * 1000).toISOString() : pickString(post, 'created_at');

  return {
    serverId: ctx.server.id,
    siteType: ctx.server.siteType,
    id,
    postUrl: `${ctx.baseUrl}/post/show/${id}`,
    fileUrl,
    previewUrl,
    sampleUrl,
    width: pickNumber(post, 'width'),
    height: pickNumber(post, 'height'),
    ext,
    sizeBytes: pickNumber(post, 'file_size'),
    rating: normalizeRating(rawRating),
    rawRating,
    tags,
    // `/post.json` returns one flat tag string; categories need /tag.json lookups.
    tagCategories: { general: tags },
    artistTags: [],
    characterTags: [],
    score: pickNumber(post, 'score'),
    md5: pickString(post, 'md5'),
    sources,
    createdAt,
    isVideo: !!ext && ['webm', 'mp4'].includes(ext),
    isAnimated: !!ext && ['gif', 'webm', 'mp4', 'swf'].includes(ext),
    parentId: pickString(post, 'parent_id'),
    hasChildren: pickBoolean(post, 'has_children') ?? false,
    description: null,
  };
}

function interpretEndpointProbe(snapshot: HttpResponseSnapshot, displayName: string): ProbeInterpretation {
  const failure = classifyFailure(snapshot, displayName);
  if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  let parsed: unknown;
  try {
    parsed = readJson(snapshot, `${displayName} listing endpoint`);
  } catch (error) {
    const booruError = error as BooruError;
    return { ok: false, kind: booruError.kind, message: booruError.message, warnings: booruError.hint ? [booruError.hint] : [] };
  }
  if (!Array.isArray(parsed)) {
    return {
      ok: false,
      kind: 'endpoint-mismatch',
      message: `${displayName} answered with JSON, but not a Moebooru post array`,
      warnings: ['Expected a JSON array at /post.json.'],
    };
  }
  const first = asRecord(parsed[0]);
  if (first && !('file_url' in first) && !('md5' in first)) {
    return {
      ok: false,
      kind: 'endpoint-mismatch',
      message: `${displayName} returned an array, but its entries do not look like Moebooru posts`,
      warnings: ['Moebooru posts carry `file_url`, `preview_url`, `md5` and `rating` keys.'],
    };
  }
  return { ok: true, kind: 'ok', message: `Endpoint OK - /post.json responded with ${parsed.length} post(s)` };
}

/**
 * Build a Moebooru adapter (yande.re, konachan.com and self-hosted instances).
 *
 * - API: `/post.json?tags=&limit=&page=` (bare array), `/post.json?tags=id:N`
 *   for a single post. Reference: https://yande.re/help/api
 * - Auth: optional `login` + `password_hash`; anonymous reads are unrestricted.
 */
export function createMoebooruAdapter(options: MoebooruOptions): BooruAdapter {
  const displayName = options.displayName;
  const matchesHost = (host: string): boolean => options.hostPatterns.some((pattern) => hostMatchesPattern(host, pattern));

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
      maxPostsPerRequest: 100,
      maxPage: null,
      minRequestIntervalMs: 1000,
      supportsPostLookup: true,
      supportsTagSearch: true,
      supportsIdPagination: false,
      apiDocsUrl: options.apiDocsUrl ?? 'https://yande.re/help/api',
      notes: [
        'Moebooru API (Danbooru 1.x lineage): /post.json returns a bare JSON array.',
        'Optional auth is login + password_hash = SHA1("choujin-steiner--" + password + "--"); paste the hash into the API key field.',
        'Ratings use the s / q / e tokens.',
        ...(options.notes ?? []),
      ],
    },

    defaults: {
      baseUrl: options.baseUrl,
      label: options.label,
      ratings: ['safe'],
      userAgentHint: `BooruServerManager/${MOEBOORU_VERSION}`,
    },

    normalizeBaseUrl(raw: string): string {
      return raw.trim().replace(/\/+$/, '').replace(/\/(post\.json|post|posts)\/?.*$/i, '');
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
      if (segments[0] === 'post' && segments[1] === 'show' && segments[2] && /^\d+$/.test(segments[2])) {
        return { siteType: options.siteType, kind: 'post', postId: segments[2], tags: null, page: null, canonicalUrl };
      }
      if (segments[0] === 'post' && (segments.length === 1 || segments[1] === 'index')) {
        const tags = parsed.searchParams.get('tags');
        const page = parsed.searchParams.get('page');
        return {
          siteType: options.siteType,
          kind: tags ? 'search' : 'index',
          postId: null,
          tags,
          page: page && /^\d+$/.test(page) ? Number(page) : null,
          canonicalUrl,
        };
      }
      if (segments[0] === 'pool' && segments[1] === 'show') {
        return { siteType: options.siteType, kind: 'pool', postId: null, tags: null, page: null, canonicalUrl };
      }
      return { siteType: options.siteType, kind: 'index', postId: null, tags: null, page: null, canonicalUrl };
    },

    postUrl(baseUrl: string, postId: string): string {
      return `${baseUrl.replace(/\/+$/, '')}/post/show/${postId}`;
    },

    buildSearchRequest(ctx, spec): HttpRequestSpec {
      const limit = Math.min(Math.max(spec.limit ?? 40, 1), adapter.capabilities.maxPostsPerRequest);
      const filters = adapter.ratingQueryTags(spec.ratingFilter?.allowed ?? []).tags;
      const tags = composeTags(spec.tags, spec.ratingFilter?.enabled ? filters : [], ctx.settings.globalTagSuffix);
      return {
        url: moebooruSearchUrl(ctx.baseUrl, {
          tags: tags || undefined,
          limit,
          page: spec.page ?? 1,
          credentials: credentialsFor(ctx),
        }),
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: options.siteType,
        label: 'Search posts',
      };
    },

    buildPostRequest(ctx, postId): HttpRequestSpec {
      return {
        url: moebooruSearchUrl(ctx.baseUrl, { tags: `id:${postId}`, limit: 1, credentials: credentialsFor(ctx) }),
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: options.siteType,
        label: `Fetch post ${postId}`,
      };
    },

    buildValidationProbes(ctx): ValidationProbe[] {
      const credentials = credentialsFor(ctx);
      return [
        {
          id: `${options.siteType}-endpoint`,
          label: 'Listing endpoint (anonymous)',
          purpose: 'endpoint',
          request: {
            url: moebooruSearchUrl(ctx.baseUrl, { limit: 1 }),
            method: 'GET',
            headers: { Accept: 'application/json' },
            tag: options.siteType,
            label: 'Endpoint check',
          },
          interpret: (snapshot) => interpretEndpointProbe(snapshot, displayName),
        },
        {
          id: `${options.siteType}-auth`,
          label: 'Authenticated listing request (login + password_hash)',
          purpose: 'auth',
          enabled: !!credentials,
          request: {
            url: moebooruSearchUrl(ctx.baseUrl, { limit: 1, credentials }),
            method: 'GET',
            headers: { Accept: 'application/json' },
            tag: options.siteType,
            label: 'Credential check',
          },
          interpret: (snapshot) => {
            const failure = classifyFailure(snapshot, displayName);
            if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
            return {
              ok: true,
              kind: 'ok',
              message: `Credentials accepted for ${ctx.server.username}`,
              warnings: ['Moebooru does not reject wrong password hashes on read endpoints; a definitive check needs a write action.'],
              account: accountInfo(ctx.server.username || null, null, null),
            };
          },
        },
      ];
    },

    parseSearchResponse(snapshot, spec, ctx): SearchResult {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} listing endpoint`);
      if (!Array.isArray(parsed)) throw shapeError(`${displayName} listing endpoint`, 'expected a JSON array of posts');
      const limit = Math.min(Math.max(spec.limit ?? 40, 1), adapter.capabilities.maxPostsPerRequest);
      return {
        posts: parsed.map((raw) => normalizePost(raw, ctx, displayName)),
        page: spec.page ?? 1,
        limit,
        hasMore: parsed.length >= limit,
        totalCount: null,
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
      const list = Array.isArray(parsed) ? parsed : [];
      const first = list.find((entry) => pickString(asRecord(entry), 'id') === postId) ?? list[0];
      if (!first) {
        throw new BooruError(`${displayName} has no post ${postId}`, {
          kind: 'parse-failure',
          status: snapshot.status,
          hint: 'Moebooru returns an empty array for missing or deleted posts.',
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
          key: 'username',
          label: 'Login (optional)',
          type: 'text',
          required: false,
          requiredForAuth: true,
          secret: false,
          placeholder: 'your_username',
          help: 'Only needed for account-level features; anonymous reads work.',
        },
        {
          key: 'apiKey',
          label: 'Password hash (optional)',
          type: 'password',
          required: false,
          requiredForAuth: true,
          secret: true,
          placeholder: 'SHA1("choujin-steiner--" + password + "--")',
          help: 'Moebooru has no API keys: it takes a salted SHA1 of your password as `password_hash`. Never paste the plain password.',
        },
        {
          key: 'customUserAgent',
          label: 'User-Agent (optional)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: `BooruServerManager/${MOEBOORU_VERSION}`,
        },
      ];
    },

    suggestUserAgent(): string {
      return `BooruServerManager/${MOEBOORU_VERSION}`;
    },
  };

  return adapter;
}

export const yandereAdapter: BooruAdapter = createMoebooruAdapter({
  siteType: 'yandere',
  displayName: 'yande.re (Moebooru)',
  baseUrl: 'https://yande.re',
  label: 'yande.re',
  hostPatterns: ['yande.re', 'www.yande.re', 'files.yande.re'],
  apiDocsUrl: 'https://yande.re/help/api',
});

export const konachanAdapter: BooruAdapter = createMoebooruAdapter({
  siteType: 'konachan',
  displayName: 'Konachan (Moebooru)',
  baseUrl: 'https://konachan.com',
  label: 'Konachan',
  hostPatterns: ['konachan.com', 'www.konachan.com', 'konachan.net', 'www.konachan.net'],
  apiDocsUrl: 'https://konachan.com/help/api',
  notes: ['konachan.net is the SFW mirror of konachan.com; both share the same API.'],
});

export const MOEBOORU_ADAPTERS: BooruAdapter[] = [yandereAdapter, konachanAdapter];
