import type { AdapterContext, AdapterFailure, BooruAdapter, CredentialField, ProbeInterpretation, ValidationProbe } from '../core/adapter.js';
import { BooruError } from '../shared/errors.js';
import type { HttpResponseSnapshot, HttpRequestSpec } from '../shared/http.js';
import type { BooruPost, Rating, RouteMatch, SearchResult, SearchSpec, SiteType } from '../shared/types.js';
import { asRecord, classifyStatus, pickString, readJson, shapeError } from './base.js';
import { hostMatchesPattern, unique } from '../shared/util.js';

export const KEMONO_VERSION = '0.1.0';
/** Kemono's API always pages by 50. */
export const KEMONO_PAGE_SIZE = 50;

const SITE_RATING_TOKENS: readonly string[] = [];
const IMAGE_EXTS = ['jpg', 'jpeg', 'png', 'gif', 'webp', 'avif', 'bmp'];
const VIDEO_EXTS = ['mp4', 'webm', 'mov', 'mkv', 'm4v'];

export interface KemonoOptions {
  siteType: SiteType;
  displayName: string;
  baseUrl: string;
  label: string;
  hostPatterns: string[];
  notes?: string[];
  /**
   * Where `/data/<path>` files are served. Kemono serves them from the site
   * host (which redirects to a CDN node); Pawchive names the node per file
   * (`node: 2` → `https://n2.pawchive.pw`). Receives the file's node, if any.
   */
  fileHost?: (baseUrl: string, node: number | null) => string;
  /** Where `/thumbnail/data/<path>` previews are served (defaults to the site host). */
  thumbnailHost?: (baseUrl: string) => string;
}

/**
 * Composite post ids. Kemono posts are addressed by service + creator + post,
 * and one post carries many attachments, so a downloadable row is
 * `service/creator/post/attachmentIndex`. Without the index the primary file
 * (`post.file`) is meant.
 */
export interface KemonoPostRef {
  service: string;
  user: string;
  post: string;
  index: number | null;
}

export function parseKemonoPostId(postId: string): KemonoPostRef | null {
  const parts = postId.split('/').filter(Boolean);
  if (parts.length < 3 || parts.length > 4) return null;
  const [service, user, post, index] = parts;
  if (!service || !user || !post) return null;
  if (index !== undefined && !/^\d+$/.test(index)) return null;
  return { service, user, post, index: index === undefined ? null : Number(index) };
}

export function formatKemonoPostId(ref: KemonoPostRef): string {
  return `${ref.service}/${ref.user}/${ref.post}${ref.index === null ? '' : `/${ref.index}`}`;
}

/**
 * What the user may type in the tag box:
 *   - `service/creatorId` (or a pasted creator URL)  → that creator's posts
 *   - `tag:foo`                                       → posts tagged foo
 *   - anything else                                   → full-text title search (`q`)
 */
export function parseKemonoQuery(raw: string | undefined): { kind: 'creator'; service: string; user: string } | { kind: 'tag'; tag: string } | { kind: 'text'; q: string } {
  const text = (raw ?? '').trim();
  const creatorUrl = /(?:^|\/)([a-z0-9_-]+)\/user\/([^/?#\s]+)/i.exec(text);
  if (creatorUrl) return { kind: 'creator', service: creatorUrl[1]!.toLowerCase(), user: creatorUrl[2]! };
  const creatorShort = /^([a-z0-9_-]+)\/([^/\s]+)$/i.exec(text);
  if (creatorShort && !text.includes(' ')) return { kind: 'creator', service: creatorShort[1]!.toLowerCase(), user: creatorShort[2]! };
  const tag = /^tag:(.+)$/i.exec(text);
  if (tag) return { kind: 'tag', tag: tag[1]!.trim() };
  return { kind: 'text', q: text };
}

function classifyFailure(snapshot: HttpResponseSnapshot, displayName: string): AdapterFailure | null {
  return classifyStatus(snapshot, displayName);
}

function fileUrlFor(host: string, path: string, name: string | null): string {
  const url = new URL(`${host.replace(/\/+$/, '')}/data${path.startsWith('/') ? path : `/${path}`}`);
  if (name) url.searchParams.set('f', name);
  return url.toString();
}

function thumbnailFor(host: string, path: string, ext: string | null): string | null {
  if (!ext || !IMAGE_EXTS.includes(ext)) return null;
  return `${host.replace(/\/+$/, '')}/thumbnail/data${path.startsWith('/') ? path : `/${path}`}`;
}

function extOf(name: string | null, path: string): string | null {
  const source = name && /\.[a-z0-9]{1,5}$/i.test(name) ? name : path;
  const match = /\.([a-z0-9]{1,5})$/i.exec(source);
  return match?.[1]?.toLowerCase() ?? null;
}

interface KemonoFile {
  name: string | null;
  path: string;
  /** Storage node hint (Pawchive); null when the site does not expose one. */
  node: number | null;
  /** The site only holds a preview rendition of this file (Pawchive `preview_only`). */
  previewOnly: boolean;
}

/** Per-site URL resolution shared by listing and single-post parsing. */
interface KemonoHosts {
  fileHost: (baseUrl: string, node: number | null) => string;
  thumbnailHost: (baseUrl: string) => string;
}

const DEFAULT_HOSTS: KemonoHosts = { fileHost: (baseUrl) => baseUrl, thumbnailHost: (baseUrl) => baseUrl };

/** Primary file first, then attachments, de-duplicated by storage path. */
function collectFiles(post: Record<string, unknown>, extraAttachments: unknown[] = []): KemonoFile[] {
  const seen = new Set<string>();
  const out: KemonoFile[] = [];
  const push = (raw: unknown) => {
    const record = asRecord(raw);
    const path = record ? pickString(record, 'path') : null;
    if (!path || seen.has(path)) return;
    seen.add(path);
    const nodeRaw = record?.node;
    out.push({
      name: record ? pickString(record, 'name') : null,
      path,
      node: typeof nodeRaw === 'number' && Number.isFinite(nodeRaw) ? nodeRaw : null,
      previewOnly: record?.preview_only === true,
    });
  };
  push(post.file);
  for (const attachment of Array.isArray(post.attachments) ? post.attachments : []) push(attachment);
  for (const attachment of extraAttachments) push(attachment);
  return out;
}

function buildPost(post: Record<string, unknown>, file: KemonoFile, index: number, ctx: AdapterContext, hosts: KemonoHosts = DEFAULT_HOSTS): BooruPost {
  const service = pickString(post, 'service') ?? 'unknown';
  const user = pickString(post, 'user') ?? '';
  const postId = pickString(post, 'id') ?? '';
  const ext = extOf(file.name, file.path);
  const tagsRaw = post.tags;
  const tags = Array.isArray(tagsRaw)
    ? unique(tagsRaw.filter((tag): tag is string => typeof tag === 'string' && !!tag.trim()))
    : typeof tagsRaw === 'string'
      ? unique(tagsRaw.replace(/^\{|\}$/g, '').split(',').map((tag) => tag.trim()).filter(Boolean))
      : [];
  const fileUrl = fileUrlFor(hosts.fileHost(ctx.baseUrl, file.node), file.path, file.name);
  // Storage paths are `/ab/cd/<sha256>.<ext>`; keep the hash where the UI shows md5 (labelled as such in notes).
  const hash = /\/([a-f0-9]{64})\.[a-z0-9]+$/i.exec(file.path)?.[1] ?? null;
  return {
    serverId: ctx.server.id,
    siteType: ctx.server.siteType,
    id: formatKemonoPostId({ service, user, post: postId, index }),
    postUrl: `${ctx.baseUrl}/${service}/user/${user}/post/${postId}`,
    fileUrl,
    previewUrl: thumbnailFor(hosts.thumbnailHost(ctx.baseUrl), file.path, ext) ?? fileUrl,
    sampleUrl: null,
    width: null,
    height: null,
    ext,
    sizeBytes: null,
    rating: 'unknown',
    rawRating: null,
    tags,
    tagCategories: { general: tags, artist: [user], copyright: [service] },
    artistTags: [user],
    characterTags: [],
    score: null,
    md5: hash,
    sources: [],
    createdAt: pickString(post, 'published', 'added'),
    isVideo: !!ext && VIDEO_EXTS.includes(ext),
    isAnimated: !!ext && (ext === 'gif' || VIDEO_EXTS.includes(ext)),
    parentId: postId,
    hasChildren: false,
    description: file.previewOnly ? `${pickString(post, 'title') ?? ''} [preview only]`.trim() : pickString(post, 'title'),
  };
}

/** Expand one Kemono post into one row per file (posts without files yield nothing). */
export function expandKemonoPost(raw: unknown, ctx: AdapterContext, hosts: KemonoHosts = DEFAULT_HOSTS): BooruPost[] {
  const post = asRecord(raw);
  if (!post) return [];
  return collectFiles(post).map((file, index) => buildPost(post, file, index, ctx, hosts));
}

function interpretEndpointProbe(snapshot: HttpResponseSnapshot, displayName: string): ProbeInterpretation {
  const failure = classifyFailure(snapshot, displayName);
  if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
  let parsed: unknown;
  try {
    parsed = readJson(snapshot, `${displayName} posts endpoint`);
  } catch (error) {
    const booruError = error as BooruError;
    return { ok: false, kind: booruError.kind, message: booruError.message, warnings: booruError.hint ? [booruError.hint] : [] };
  }
  const record = asRecord(parsed);
  const list = Array.isArray(parsed) ? parsed : record && Array.isArray(record.posts) ? record.posts : null;
  if (!list) {
    return { ok: false, kind: 'endpoint-mismatch', message: `${displayName} answered with JSON, but not a posts listing`, warnings: ['Expected `{"count": N, "posts": […]}` at /api/v1/posts.'] };
  }
  return { ok: true, kind: 'ok', message: `Endpoint OK - /api/v1/posts responded with ${list.length} post(s)` };
}

/**
 * Kemono / Coomer adapter (https://kemono.cr, https://coomer.st).
 *
 * Not a tag booru: content is organised by *service* (patreon, fanbox, …) and
 * *creator*. The adapter maps every attachment to one downloadable row.
 *
 * - Listing: `/api/v1/posts?q=…&o=offset` (site-wide text search, 50 per page),
 *   `/api/v1/posts?tag=…`, or `/api/v1/{service}/user/{id}/posts?o=offset`.
 * - Single post: `/api/v1/{service}/user/{id}/post/{postId}`.
 * - Files: `/data/{path}?f={name}` on the site host (redirects to a CDN node).
 * - No authentication and no ratings. The API is undocumented and has changed
 *   before; validation confirms the current shape before anything is queued.
 */
export function createKemonoAdapter(options: KemonoOptions): BooruAdapter {
  const displayName = options.displayName;
  const matchesHost = (host: string): boolean => options.hostPatterns.some((pattern) => hostMatchesPattern(host, pattern));
  const hosts: KemonoHosts = {
    fileHost: options.fileHost ?? DEFAULT_HOSTS.fileHost,
    thumbnailHost: options.thumbnailHost ?? DEFAULT_HOSTS.thumbnailHost,
  };

  const adapter: BooruAdapter = {
    siteType: options.siteType,
    displayName,
    hostPatterns: options.hostPatterns,
    siteRatingTokens: SITE_RATING_TOKENS,

    capabilities: {
      supportsAnonymousAccess: true,
      supportsRatingFilter: false,
      requiresUsername: false,
      requiresUserId: false,
      requiresApiKey: false,
      requiresUserAgent: false,
      authStyle: 'query',
      supportsBasicAuthHeader: false,
      maxPostsPerRequest: KEMONO_PAGE_SIZE,
      maxPage: null,
      minRequestIntervalMs: 1500,
      supportsPostLookup: true,
      supportsTagSearch: true,
      supportsIdPagination: false,
      apiDocsUrl: `${options.baseUrl}/documentation/api`,
      notes: [
        'Creator archive, not a tag booru: type `service/creatorId` (or paste a creator URL) to list a creator, `tag:name` for a tag, anything else is a title search.',
        'Every attachment of a post becomes one row; ids look like service/creator/post/index.',
        'No login and no ratings; the rating filter does nothing here. Please keep the request interval generous - the site is volunteer-run.',
        'The md5 column shows the SHA-256 storage hash the site uses.',
        ...(options.notes ?? []),
      ],
    },

    defaults: {
      baseUrl: options.baseUrl,
      label: options.label,
      ratings: ['general', 'safe', 'sensitive', 'questionable', 'explicit'],
      userAgentHint: `BooruServerManager/${KEMONO_VERSION}`,
    },

    normalizeBaseUrl(raw: string): string {
      return raw.trim().replace(/\/+$/, '').replace(/\/(api\/v1.*|posts|artists|[a-z0-9_-]+\/user\/.*)$/i, '');
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
      if (segments[1] === 'user' && segments[2] && segments[3] === 'post' && segments[4]) {
        return {
          siteType: options.siteType,
          kind: 'post',
          postId: formatKemonoPostId({ service: segments[0]!, user: segments[2], post: segments[4], index: null }),
          tags: null,
          page: null,
          canonicalUrl,
        };
      }
      if (segments[1] === 'user' && segments[2]) {
        const offset = parsed.searchParams.get('o');
        return {
          siteType: options.siteType,
          kind: 'search',
          postId: null,
          tags: `${segments[0]}/${segments[2]}`,
          page: offset && /^\d+$/.test(offset) ? Math.floor(Number(offset) / KEMONO_PAGE_SIZE) + 1 : null,
          canonicalUrl,
        };
      }
      if (segments[0] === 'posts') {
        const q = parsed.searchParams.get('q');
        const tag = parsed.searchParams.get('tag');
        return { siteType: options.siteType, kind: q || tag ? 'search' : 'index', postId: null, tags: tag ? `tag:${tag}` : q, page: null, canonicalUrl };
      }
      return { siteType: options.siteType, kind: 'index', postId: null, tags: null, page: null, canonicalUrl };
    },

    postUrl(baseUrl: string, postId: string): string {
      const ref = parseKemonoPostId(postId);
      if (!ref) return `${baseUrl.replace(/\/+$/, '')}/posts`;
      return `${baseUrl.replace(/\/+$/, '')}/${ref.service}/user/${ref.user}/post/${ref.post}`;
    },

    buildSearchRequest(ctx, spec): HttpRequestSpec {
      const page = Math.max(spec.page ?? 1, 1);
      const offset = (page - 1) * KEMONO_PAGE_SIZE;
      const query = parseKemonoQuery(spec.tags);
      let url: URL;
      if (query.kind === 'creator') {
        url = new URL(`${ctx.baseUrl}/api/v1/${encodeURIComponent(query.service)}/user/${encodeURIComponent(query.user)}/posts`);
      } else {
        url = new URL(`${ctx.baseUrl}/api/v1/posts`);
        if (query.kind === 'tag') url.searchParams.set('tag', query.tag);
        else if (query.q) url.searchParams.set('q', query.q);
      }
      url.searchParams.set('o', String(offset));
      return { url: url.toString(), method: 'GET', headers: { Accept: 'application/json' }, tag: options.siteType, label: 'List posts' };
    },

    buildPostRequest(ctx, postId): HttpRequestSpec {
      const ref = parseKemonoPostId(postId);
      if (!ref) {
        throw new BooruError(`"${postId}" is not a ${displayName} post id`, {
          kind: 'parse-failure',
          hint: 'Kemono ids look like service/creatorId/postId (optionally /attachmentIndex).',
        });
      }
      return {
        url: `${ctx.baseUrl}/api/v1/${encodeURIComponent(ref.service)}/user/${encodeURIComponent(ref.user)}/post/${encodeURIComponent(ref.post)}`,
        method: 'GET',
        headers: { Accept: 'application/json' },
        tag: options.siteType,
        label: `Fetch post ${ref.post}`,
      };
    },

    buildValidationProbes(ctx): ValidationProbe[] {
      return [
        {
          id: `${options.siteType}-endpoint`,
          label: 'Recent posts endpoint',
          purpose: 'endpoint',
          request: { url: `${ctx.baseUrl}/api/v1/posts?o=0`, method: 'GET', headers: { Accept: 'application/json' }, tag: options.siteType, label: 'Endpoint check' },
          interpret: (snapshot) => interpretEndpointProbe(snapshot, displayName),
        },
      ];
    },

    parseSearchResponse(snapshot, spec, ctx): SearchResult {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const parsed = readJson<unknown>(snapshot, `${displayName} posts endpoint`);
      const record = Array.isArray(parsed) ? null : asRecord(parsed);
      const list = Array.isArray(parsed) ? parsed : record && Array.isArray(record.posts) ? record.posts : null;
      if (!list) throw shapeError(`${displayName} posts endpoint`, 'expected `{"posts": […]}` or a post array');
      const page = Math.max(spec.page ?? 1, 1);
      const count = record ? (typeof record.count === 'number' ? record.count : null) : null;
      const offset = (page - 1) * KEMONO_PAGE_SIZE;
      return {
        posts: list.flatMap((raw) => expandKemonoPost(raw, ctx, hosts)),
        page,
        limit: KEMONO_PAGE_SIZE,
        hasMore: count !== null ? offset + list.length < count : list.length >= KEMONO_PAGE_SIZE,
        totalCount: count,
        appliedTags: (spec.tags ?? '').trim(),
        requestUrl: snapshot.redactedUrl,
        siteType: options.siteType,
        serverId: ctx.server.id,
      };
    },

    parsePostResponse(snapshot, postId, ctx): BooruPost {
      const failure = classifyFailure(snapshot, displayName);
      if (failure) throw new BooruError(failure.message, { kind: failure.kind, status: snapshot.status, hint: failure.hint ?? null });
      const ref = parseKemonoPostId(postId);
      const parsed = readJson<unknown>(snapshot, `${displayName} post ${postId}`);
      const record = asRecord(parsed);
      // New API: `{post: {...}, attachments: [...], previews: [...]}`; old API: the post itself.
      const post = (record && asRecord(record.post)) ?? record;
      if (!post) throw shapeError(`${displayName} post ${postId}`, 'expected a post object');
      const extra = record && Array.isArray(record.attachments) ? record.attachments : [];
      const files = collectFiles(post, extra);
      const index = ref?.index ?? 0;
      const file = files[index];
      if (!file) {
        throw new BooruError(`${displayName} post ${ref?.post ?? postId} has no file #${index}`, {
          kind: 'parse-failure',
          status: snapshot.status,
          hint: files.length ? `The post has ${files.length} file(s).` : 'The post has no downloadable attachments.',
        });
      }
      return buildPost(post, file, index, ctx, hosts);
    },

    normalizePost(raw, ctx): BooruPost {
      const first = expandKemonoPost(raw, ctx, hosts)[0];
      if (!first) throw shapeError(`${displayName} post`, 'post has no files');
      return first;
    },

    normalizeRating(): Rating {
      return 'unknown';
    },

    canonicalRatingFor(): Rating | null {
      return null;
    },

    ratingQueryTags() {
      return { tags: [], warnings: [`${displayName} has no rating system; the rating filter is ignored for this server.`] };
    },

    classifyFailure: (snapshot) => classifyFailure(snapshot, displayName),

    credentialFields(): CredentialField[] {
      return [
        {
          key: 'customUserAgent',
          label: 'User-Agent (optional)',
          type: 'text',
          required: false,
          requiredForAuth: false,
          secret: false,
          placeholder: `BooruServerManager/${KEMONO_VERSION}`,
          help: `${displayName} needs no login. Nothing else to configure.`,
        },
      ];
    },

    suggestUserAgent(): string {
      return `BooruServerManager/${KEMONO_VERSION}`;
    },
  };

  return adapter;
}

export const kemonoAdapter: BooruAdapter = createKemonoAdapter({
  siteType: 'kemono',
  displayName: 'Kemono',
  baseUrl: 'https://kemono.cr',
  label: 'Kemono',
  hostPatterns: ['kemono.cr', 'www.kemono.cr', 'kemono.su', 'www.kemono.su', 'kemono.party', 'www.kemono.party'],
  notes: ['kemono.su / kemono.party are older domains of the same site.'],
});

export const coomerAdapter: BooruAdapter = createKemonoAdapter({
  siteType: 'coomer',
  displayName: 'Coomer',
  baseUrl: 'https://coomer.st',
  label: 'Coomer',
  hostPatterns: ['coomer.st', 'www.coomer.st', 'coomer.su', 'www.coomer.su', 'coomer.party', 'www.coomer.party'],
  notes: ['coomer.su / coomer.party are older domains of the same site.'],
});

/**
 * Pawchive - the community's Kemono successor (launched 2026, moved from
 * pawchive.st to pawchive.pw). Same API; files are served per storage node
 * (`n<node>.pawchive.pw`) and thumbnails from `img.pawchive.pw`.
 */
export const pawchiveAdapter: BooruAdapter = createKemonoAdapter({
  siteType: 'pawchive',
  displayName: 'Pawchive',
  baseUrl: 'https://pawchive.pw',
  label: 'Pawchive',
  hostPatterns: ['pawchive.pw', 'www.pawchive.pw', 'pawchive.st', 'www.pawchive.st'],
  fileHost: (baseUrl, node) => {
    const host = new URL(baseUrl).host.replace(/^www\./, '');
    return `https://n${node ?? 1}.${host}`;
  },
  thumbnailHost: (baseUrl) => `https://img.${new URL(baseUrl).host.replace(/^www\./, '')}`,
  notes: [
    'Kemono-compatible API; pawchive.st is the previous domain of the same site.',
    'Some files are marked preview-only or need a logged-in session on the website; those rows may download a preview rendition or fail with 403.',
  ],
});

export const KEMONO_ADAPTERS: BooruAdapter[] = [kemonoAdapter, coomerAdapter, pawchiveAdapter];
