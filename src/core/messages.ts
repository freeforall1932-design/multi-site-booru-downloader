import type { EnqueueCandidate, EnqueueInput } from './queue.js';
import type { SaveServerInput } from './servers.js';
import type {
  BooruPost,
  ExtensionSettings,
  FailureKind,
  QueueItem,
  QueueSummary,
  Rating,
  RouteMatch,
  SearchResult,
  SearchSpec,
  ServerConfig,
  ServerConfigView,
  SearchHistoryEntry,
  DownloadHistoryEntry,
  ValidationResult,
} from '../shared/types.js';

/** Minimal shape the UI can hand to the queue without holding a full post. */
export type { EnqueueCandidate };

export type UiRequest =
  | { type: 'servers/list' }
  | { type: 'servers/save'; payload: SaveServerInput }
  | { type: 'servers/remove'; payload: { id: string } }
  | { type: 'servers/duplicate'; payload: { id: string } }
  | { type: 'servers/setDefault'; payload: { id: string } }
  | { type: 'servers/validate'; payload: { id: string } }
  | { type: 'servers/validateDraft'; payload: { server: SaveServerInput } }
  | { type: 'servers/clearCredentials'; payload: { id: string } }
  | { type: 'servers/export'; payload?: { includeSecrets?: boolean } }
  | { type: 'servers/import'; payload: { json: string; includeSecrets?: boolean } }
  | { type: 'browse/search'; payload: { serverId: string | null; spec: SearchSpec } }
  | { type: 'posts/get'; payload: { serverId: string | null; postId: string } }
  | { type: 'posts/resolveUrl'; payload: { url: string } }
  | { type: 'posts/download'; payload: { serverId?: string | null; postId?: string; url?: string } }
  | { type: 'routes/detect'; payload: { url: string } }
  | { type: 'queue/list' }
  | { type: 'queue/enqueue'; payload: { items: EnqueueInput[] } }
  | { type: 'queue/enqueuePosts'; payload: { serverId: string; posts: EnqueueCandidate[] } }
  | { type: 'queue/run'; payload?: { itemIds?: string[] } }
  | { type: 'queue/pause' }
  | { type: 'queue/resume' }
  | { type: 'queue/retryFailed' }
  | { type: 'queue/clear'; payload: { scope: 'completed' | 'failed' | 'all' } }
  | { type: 'queue/cancel'; payload: { itemId: string } }
  | { type: 'queue/remove'; payload: { itemIds: string[] } }
  | { type: 'history/list' }
  | { type: 'history/remove'; payload: { serverId: string; postId: string } }
  | { type: 'history/clear' }
  | { type: 'searches/list'; payload?: { serverId?: string | null } }
  | { type: 'searches/add'; payload: { serverId: string; query: string } }
  | { type: 'searches/remove'; payload: { serverId: string; query: string } }
  | { type: 'searches/clear'; payload?: { serverId?: string | null } }
  | { type: 'settings/get' }
  | { type: 'settings/save'; payload: Partial<ExtensionSettings> }
  | { type: 'settings/reset' }
  | { type: 'diagnostics/info' }
  | { type: 'userAgent/sync' };

export interface RouterErrorPayload {
  message: string;
  kind: FailureKind;
  hint: string | null;
}

export type RouterResponse<T = unknown> = { ok: true; data: T } | { ok: false; error: RouterErrorPayload };

/** Response payload map - keeps the UI and the router in lockstep. */
export interface ResponseMap {
  'servers/list': ServerConfigView[];
  'servers/save': ServerConfigView;
  'servers/remove': ServerConfigView[];
  'servers/duplicate': ServerConfigView;
  'servers/setDefault': ServerConfigView[];
  'servers/validate': ValidationResult;
  'servers/validateDraft': ValidationResult;
  'servers/clearCredentials': ServerConfigView | null;
  'servers/export': { json: string; includesSecrets: boolean };
  'servers/import': { imported: number; skipped: number; errors: string[] };
  'browse/search': SearchResult;
  'posts/get': BooruPost;
  'posts/resolveUrl': { post: BooruPost; server: ServerConfigView; route: RouteMatch };
  'posts/download': { filename: string; post: BooruPost; viaFallback: boolean; downloadId: number | null };
  'routes/detect': (RouteMatch & { server: ServerConfigView | null }) | null;
  'queue/list': { summary: QueueSummary; items: QueueItem[]; maxConcurrency: number };
  'queue/enqueue': { added: number; skipped: number; summary: QueueSummary };
  'queue/enqueuePosts': { added: number; skipped: number; summary: QueueSummary };
  'queue/run': { started: boolean; summary: QueueSummary };
  'queue/pause': { summary: QueueSummary };
  'queue/resume': { started: boolean; summary: QueueSummary };
  'queue/retryFailed': { requeued: number; summary: QueueSummary };
  'queue/clear': { removed: number; summary: QueueSummary };
  'queue/cancel': { summary: QueueSummary };
  'queue/remove': { removed: number; summary: QueueSummary };
  'history/list': { entries: DownloadHistoryEntry[]; total: number };
  'history/remove': { removed: number; total: number };
  'history/clear': { removed: number };
  'searches/list': { entries: SearchHistoryEntry[] };
  'searches/add': { entries: SearchHistoryEntry[] };
  'searches/remove': { entries: SearchHistoryEntry[] };
  'searches/clear': { removed: number };
  'settings/get': ExtensionSettings;
  'settings/save': ExtensionSettings;
  'settings/reset': ExtensionSettings;
  'diagnostics/info': DiagnosticsInfo;
  'userAgent/sync': { ok: boolean; applied: number; error?: string };
}

export interface AdapterCatalogEntry {
  siteType: string;
  displayName: string;
  capabilitySummary: string;
  baseUrl: string;
  authStyle: string;
  requiresUserAgent: boolean;
  maxPostsPerRequest: number;
  ratingTokens: string[];
  defaultRatings: Rating[];
  supportsRatingFilter: boolean;
  supportsPostLookup: boolean;
  apiDocsUrl: string;
  notes: string[];
  credentialFields: Array<{ key: string; label: string; required: boolean; secret: boolean; help?: string }>;
}

export interface DiagnosticsInfo {
  version: string;
  environment: 'extension' | 'preview';
  storageKeys: string[];
  settings: ExtensionSettings;
  adapters: AdapterCatalogEntry[];
  servers: Array<{
    id: string;
    label: string;
    siteType: string;
    baseUrl: string;
    ratingFilterEnabled: boolean;
    hasApiKey: boolean;
    validationStatus: string;
    lastValidatedAt: string | null;
    trace: ServerConfig['lastValidationTrace'];
  }>;
  userAgentRules: { ok: boolean; applied: number; error?: string };
}
