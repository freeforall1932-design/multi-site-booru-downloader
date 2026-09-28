/**
 * Shared domain types for the Booru Server Manager extension.
 *
 * Design note: everything the UI talks about lives here, and nothing here is
 * specific to a single booru site. Site-specific shapes (JSON field names,
 * rating tokens, auth query params) stay inside `src/adapters/*` and are never
 * leaked into the UI layer.
 */

/** Site types are plain strings so that new booru-like sites need no type churn. */
export type SiteType = string;

export const KNOWN_SITE_TYPES = ['e621', 'danbooru', 'gelbooru'] as const;
export type KnownSiteType = (typeof KNOWN_SITE_TYPES)[number];

/** Site-agnostic rating vocabulary used by the shared layer. */
export type Rating = 'general' | 'safe' | 'sensitive' | 'questionable' | 'explicit' | 'unknown';

/** Ratings considered SFW-ish; used as the default allow-list when filtering. */
export const DEFAULT_SAFE_RATINGS: Rating[] = ['general', 'safe', 'sensitive'];

export const ALL_RATINGS: Rating[] = ['general', 'safe', 'sensitive', 'questionable', 'explicit'];

export type ValidationStatus = 'unknown' | 'valid' | 'invalid' | 'unreachable' | 'partial';

/**
 * Failure taxonomy shared by validation, the HTTP layer and the UI.
 * The UI must be able to tell these apart (product policy: UX rule 4).
 */
export type FailureKind =
  /** Credentials were rejected (401 / site-specific auth error payload). */
  | 'auth-failure'
  /** Reached *something*, but it does not speak the expected API. */
  | 'endpoint-mismatch'
  /** DNS / TLS / offline / timeout. */
  | 'network-failure'
  /** 429 or 503 / "slow down" payloads. */
  | 'rate-limited'
  /** No adapter registered for the configured site type. */
  | 'unsupported-site'
  /** Missing required fields (e.g. Gelbooru userId without apiKey). */
  | 'incomplete-config'
  /** 5xx that is not rate limiting. */
  | 'server-error'
  /** Request was blocked by the site or an intermediary (403, CF challenge). */
  | 'blocked'
  /** Response could not be parsed into the adapter's expected shape. */
  | 'parse-failure'
  /** Anything else. */
  | 'unknown';

export type ValidationKind = 'ok' | FailureKind;

export interface ScoreResult {
  ok: boolean;
  kind: ValidationKind;
}

/** One step of a validation run, sanitized for display (never contains secrets). */
export interface ProbeTraceEntry {
  id: string;
  label: string;
  purpose: 'endpoint' | 'auth';
  /** Redacted URL - api_key/login/password params stripped. */
  requestUrl: string;
  method: string;
  status: number | null;
  ok: boolean;
  durationMs: number;
  message: string;
  kind?: ValidationKind;
}

export interface AccountInfo {
  username: string | null;
  userId: string | null;
  level: string | null;
}

export interface ValidationResult {
  kind: ValidationKind;
  ok: boolean;
  /** Did the endpoint look like the expected API? `null` = not probed. */
  endpointOk: boolean | null;
  /** Did credentials verify? `null` = no credentials supplied / not probed. */
  authOk: boolean | null;
  message: string;
  /** Extra human-readable notes (warnings that do not block success). */
  warnings: string[];
  account: AccountInfo | null;
  checkedAt: string;
  httpStatus: number | null;
  durationMs: number | null;
  trace: ProbeTraceEntry[];
}

export const UNKNOWN_VALIDATION: Pick<
  ValidationResult,
  'kind' | 'ok' | 'endpointOk' | 'authOk' | 'message' | 'warnings' | 'account' | 'checkedAt' | 'httpStatus' | 'durationMs' | 'trace'
> = {
  kind: 'ok',
  ok: false,
  endpointOk: null,
  authOk: null,
  message: '',
  warnings: [],
  account: null,
  checkedAt: '',
  httpStatus: null,
  durationMs: null,
  trace: [],
};

/**
 * A saved server / account profile - one site connection.
 * Field set is fixed by product policy (see README "Server entry schema").
 */
export interface ServerConfig {
  id: string;
  label: string;
  siteType: SiteType;
  baseUrl: string;
  ratingFilterEnabled: boolean;
  username: string;
  apiKey: string;
  userId: string;
  customUserAgent: string;
  isDefault: boolean;
  validationStatus: ValidationStatus;
  validationMessage: string;
  lastValidatedAt: string | null;
  /** Extension fields (safe defaults, optional everywhere). */
  allowedRatings?: Rating[];
  /** Redacted trace of the last validation run, for the diagnostics tab. */
  lastValidationTrace?: ProbeTraceEntry[];
  createdAt?: string;
  updatedAt?: string;
  notes?: string;
}

/** Server config with secrets removed - this is what crosses into the UI. */
export interface ServerConfigView extends Omit<ServerConfig, 'apiKey'> {
  /** Masked preview such as `•••••••4f2a`, or `''` when no key is stored. */
  apiKeyMask: string;
  hasApiKey: boolean;
}

/** Everything required to describe a post, independent of site. */
export interface BooruPost {
  serverId: string;
  siteType: SiteType;
  id: string;
  postUrl: string;
  fileUrl: string;
  previewUrl: string;
  sampleUrl: string | null;
  width: number | null;
  height: number | null;
  ext: string | null;
  sizeBytes: number | null;
  rating: Rating;
  /** Site's own rating token (e.g. `q`, `questionable`, `general`). */
  rawRating: string | null;
  tags: string[];
  tagCategories: Record<string, string[]>;
  artistTags: string[];
  characterTags: string[];
  score: number | null;
  md5: string | null;
  sources: string[];
  createdAt: string | null;
  isVideo: boolean;
  isAnimated: boolean;
  parentId: string | null;
  hasChildren: boolean;
  description: string | null;
}

export interface SearchSpec {
  tags?: string;
  limit?: number;
  page?: number;
  /**
   * The shared layer decides *whether* filtering happens (server config +
   * per-search toggle); the adapter decides *how* it is spelled for its site.
   */
  ratingFilter?: {
    enabled: boolean;
    allowed: Rating[];
  };
  order?: string | null;
  /** Site-native page token (`b12345` style pagination) when supported. */
  pageToken?: string | null;
}

export interface SearchResult {
  posts: BooruPost[];
  page: number;
  limit: number;
  hasMore: boolean;
  totalCount: number | null;
  /** Tags actually sent to the site (after rating filter injection). */
  appliedTags: string;
  requestUrl: string;
  siteType: SiteType;
  serverId: string;
}

export type RouteKind = 'post' | 'search' | 'tag' | 'pool' | 'index' | 'unknown';

export interface RouteMatch {
  siteType: SiteType;
  kind: RouteKind;
  /** Present for `kind === 'post'`. */
  postId: string | null;
  /** Present for search/tag routes. */
  tags: string | null;
  page: number | null;
  /** Normalized URL the route was read from. */
  canonicalUrl: string;
  /** Saved server that owns this route, when resolution ran against a server list. */
  serverId?: string;
}

export interface DownloadRecord {
  serverId: string;
  postId: string;
  filename: string;
  url: string;
  startedAt: string;
  finishedAt: string | null;
  ok: boolean;
  bytes: number | null;
  error: string | null;
  downloadId: number | null;
}

export interface ExtensionSettings {
  /** Directory template for saved files, e.g. `booru/{siteType}`. */
  folderTemplate: string;
  /** Filename template, e.g. `{id}_{md5}.{ext}`. */
  filenameTemplate: string;
  /** Simultaneous downloads across all servers. */
  maxConcurrency: number;
  /** Minimum gap between requests to the *same* server (ms). Adapters raise this. */
  minRequestIntervalMs: number;
  /** Extra tags appended to every search (shared safe-search extras). */
  globalTagSuffix: string;
  /** How many tags `{tags}` expands to in a filename. */
  maxTagsInFilename: number;
  /** Separator used when several tags are joined in a filename. */
  tagSeparator: string;
  /** Show a floating download button on post pages. */
  enableContentScriptButton: boolean;
  /** Send `_client=<user agent>` where a site documents that fallback. */
  sendClientParam: boolean;
  /** Ask the service worker to rewrite User-Agent via declarativeNetRequest. */
  rewriteUserAgent: boolean;
  /** Only download posts whose rating is in the server allow-list. */
  enforceRatingFilterOnDownload: boolean;
  /** Retry budget for transient failures. */
  maxRetries: number;
  /** Theme for the UI pages. */
  theme: 'system' | 'light' | 'dark';
}

export const DEFAULT_SETTINGS: ExtensionSettings = {
  folderTemplate: 'booru/{siteType}',
  filenameTemplate: '{id}_{md5}',
  maxConcurrency: 2,
  minRequestIntervalMs: 1000,
  globalTagSuffix: '',
  maxTagsInFilename: 5,
  tagSeparator: ' ',
  enableContentScriptButton: true,
  sendClientParam: true,
  rewriteUserAgent: true,
  enforceRatingFilterOnDownload: true,
  maxRetries: 3,
  theme: 'system',
};

export type QueueItemStatus = 'pending' | 'running' | 'done' | 'failed' | 'skipped' | 'canceled';

export interface QueueItem {
  id: string;
  serverId: string;
  postId: string;
  label: string;
  postUrl: string;
  status: QueueItemStatus;
  attempts: number;
  error: string | null;
  errorKind: FailureKind | null;
  filename: string | null;
  bytes: number | null;
  createdAt: string;
  updatedAt: string;
}

export interface QueueSummary {
  pending: number;
  running: number;
  done: number;
  failed: number;
  skipped: number;
  canceled: number;
  total: number;
  paused: boolean;
  active: boolean;
}
