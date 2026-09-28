import type { ExtensionSettings, ServerConfig } from '../shared/types.js';
import type { UiRequest, RouterResponse } from '../core/messages.js';
import type { Downloader } from '../core/downloads.js';
import type { StorageArea } from '../core/storage.js';

export interface UserAgentSyncOutcome {
  ok: boolean;
  applied: number;
  error?: string;
}

/**
 * Everything the UI needs from its host environment. Keeping this small is what
 * lets the options page, the popup and the content script share one codebase -
 * and lets `npm run preview` drive the real UI in a plain web page.
 */
export interface Platform {
  readonly environment: 'extension' | 'preview';
  readonly storage: StorageArea;
  readonly downloader: Downloader;
  send(request: UiRequest): Promise<RouterResponse>;
  syncUserAgentRules(servers: ServerConfig[], settings: ExtensionSettings): Promise<UserAgentSyncOutcome>;
  activeTabUrl(): Promise<string | null>;
  openOptions(hash?: string): void;
  permissions: {
    contains(origin: string): Promise<boolean>;
    request(origin: string): Promise<boolean>;
  };
  /** Optional capability probe surfaced in Diagnostics. */
  describeEnvironment(): Promise<Record<string, string>>;
}
