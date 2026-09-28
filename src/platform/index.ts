import type { ExtensionSettings, ServerConfig } from '../shared/types.js';
import type { RouterResponse, UiRequest } from '../core/messages.js';
import { MemoryStorageArea, type StorageArea } from '../core/storage.js';
import { RecordingDownloader, type Downloader } from '../core/downloads.js';
import { ExtensionPlatform } from './extension.js';
import { PreviewPlatform } from './preview.js';
import type { Platform } from './types.js';

export type { Platform } from './types.js';

let installed: Platform | null = null;

export function isExtensionEnvironment(): boolean {
  return typeof globalThis.chrome !== 'undefined' && !!globalThis.chrome?.runtime?.id;
}

/**
 * Stable façade. UI modules may grab the platform at module scope: the façade
 * always forwards to whatever platform is installed *when the call happens*,
 * which is what lets the preview bootstrap swap implementations after the UI
 * module has been evaluated.
 */
const facade: Platform = {
  get environment() {
    return resolve().environment;
  },
  get storage() {
    return resolve().storage;
  },
  get downloader() {
    return resolve().downloader;
  },
  get permissions() {
    return resolve().permissions;
  },
  send: (request: UiRequest): Promise<RouterResponse> => resolve().send(request),
  syncUserAgentRules: (servers: ServerConfig[], settings: ExtensionSettings) => resolve().syncUserAgentRules(servers, settings),
  activeTabUrl: (): Promise<string | null> => resolve().activeTabUrl(),
  openOptions: (hash?: string): void => resolve().openOptions(hash),
  describeEnvironment: (): Promise<Record<string, string>> => resolve().describeEnvironment(),
};

/** Fallback for hosts without the extension API (unit tests, stray pages). */
class HeadlessPlatform implements Platform {
  readonly environment = 'preview' as const;
  readonly storage: StorageArea = new MemoryStorageArea();
  readonly downloader: Downloader = new RecordingDownloader();
  readonly permissions = { contains: async () => true, request: async () => false };

  async send(): Promise<RouterResponse> {
    return { ok: false, error: { message: 'No message handler is installed', kind: 'unknown', hint: null } };
  }

  async syncUserAgentRules() {
    return { ok: true, applied: 0 };
  }

  async activeTabUrl(): Promise<string | null> {
    return null;
  }

  openOptions(): void {
    /* no-op */
  }

  async describeEnvironment(): Promise<Record<string, string>> {
    return { Environment: 'headless' };
  }
}

function resolve(): Platform {
  if (!installed) {
    installed = isExtensionEnvironment()
      ? new ExtensionPlatform()
      : new PreviewPlatform({
          handler: async () => ({
            ok: false,
            error: {
              message: 'The preview backend is not running in this page',
              kind: 'unknown',
              hint: 'Open the preview harness (npm run preview) to exercise the UI without the extension.',
            },
          }),
        });
  }
  return installed;
}

export function installPlatform(next: Platform): void {
  installed = next;
}

export function getPlatform(): Platform {
  return facade;
}

/** Alias kept for readability in the preview bootstrap. */
export const platformFacade = facade;

/** Convenience used by tests: same services, no browser APIs. */
export function createHeadlessPlatform(): Platform {
  return new HeadlessPlatform();
}

export type { UiRequest, RouterResponse };
