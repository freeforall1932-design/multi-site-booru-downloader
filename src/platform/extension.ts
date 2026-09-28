import { ChromeDownloader } from '../core/downloads.js';
import type { RouterResponse, UiRequest } from '../core/messages.js';
import { ChromeStorageArea } from '../core/storage.js';
import { syncUserAgentRules } from '../core/userAgent.js';
import type { Platform } from './types.js';

function runtimeAvailable(): boolean {
  return typeof globalThis.chrome !== 'undefined' && !!globalThis.chrome?.runtime?.id;
}

/** Chrome MV3 implementation. All cross-origin traffic happens in the worker. */
export class ExtensionPlatform implements Platform {
  readonly environment = 'extension' as const;
  readonly storage = new ChromeStorageArea();
  readonly downloader = new ChromeDownloader();
  readonly permissions = {
    contains: async (origin: string): Promise<boolean> => {
      if (!globalThis.chrome?.permissions) return false;
      try {
        return await globalThis.chrome.permissions.contains({ origins: [`${origin}/*`] });
      } catch {
        return false;
      }
    },
    request: async (origin: string): Promise<boolean> => {
      if (!globalThis.chrome?.permissions) return false;
      try {
        return await globalThis.chrome.permissions.request({ origins: [`${origin}/*`] });
      } catch {
        return false;
      }
    },
  };

  async send(request: UiRequest): Promise<RouterResponse> {
    if (!runtimeAvailable()) {
      return { ok: false, error: { message: 'Extension runtime unavailable', kind: 'unknown', hint: null } };
    }
    try {
      const response = (await globalThis.chrome.runtime.sendMessage(request)) as RouterResponse | undefined;
      if (!response) {
        return { ok: false, error: { message: 'The background worker returned no response', kind: 'unknown', hint: null } };
      }
      return response;
    } catch (error) {
      const message = error instanceof Error ? error.message : String(error);
      // A sleeping MV3 worker rejects the first message; one retry wakes it.
      if (/Receiving end does not exist|message port closed/i.test(message)) {
        globalThis.chrome.runtime.sendMessage?.({ type: 'settings/get' }).catch(() => undefined);
        try {
          const retry = (await globalThis.chrome.runtime.sendMessage(request)) as RouterResponse | undefined;
          if (retry) return retry;
        } catch {
          /* fall through to the error below */
        }
      }
      return { ok: false, error: { message: `Background worker error: ${message}`, kind: 'unknown', hint: null } };
    }
  }

  async syncUserAgentRules(servers: Parameters<typeof syncUserAgentRules>[0], settings: Parameters<typeof syncUserAgentRules>[1]) {
    return syncUserAgentRules(servers, settings);
  }

  async activeTabUrl(): Promise<string | null> {
    if (!globalThis.chrome?.tabs) return null;
    try {
      const [tab] = await globalThis.chrome.tabs.query({ active: true, lastFocusedWindow: true });
      return tab?.url ?? null;
    } catch {
      return null;
    }
  }

  openOptions(hash?: string): void {
    const suffix = hash ? `#${hash.replace(/^#/, '')}` : '';
    if (globalThis.chrome?.runtime?.openOptionsPage) {
      void globalThis.chrome.runtime.openOptionsPage();
      if (suffix) {
        // Best effort: also switch the tab that is already open.
        void globalThis.chrome.tabs?.query({ url: globalThis.chrome.runtime.getURL('options/options.html') }, (tabs) => {
          tabs.forEach((tab) => {
            if (tab.id !== undefined) void globalThis.chrome.tabs.update(tab.id, { active: true, url: `${tab.url}${suffix}` });
          });
        });
      }
      return;
    }
    if (suffix) globalThis.location.hash = suffix;
  }

  /**
   * Open the docked side panel. Chrome only allows this from a user gesture
   * (toolbar click, popup button, options-page link); the toolbar path is handled
   * by `openPanelOnActionClick` in the worker instead.
   */
  async openPanel(): Promise<boolean> {
    const sidePanel = globalThis.chrome?.sidePanel;
    if (!sidePanel?.open) return false;
    try {
      const [tab] = await globalThis.chrome.tabs.query({ active: true, lastFocusedWindow: true });
      if (tab?.windowId !== undefined) {
        await sidePanel.open({ windowId: tab.windowId });
        return true;
      }
      if (tab?.id !== undefined) {
        await sidePanel.open({ tabId: tab.id });
        return true;
      }
      return false;
    } catch {
      return false;
    }
  }

  async describeEnvironment(): Promise<Record<string, string>> {
    return {
      'Manifest version': String(globalThis.chrome?.runtime?.getManifest?.().manifest_version ?? 'unknown'),
      'Extension version': String(globalThis.chrome?.runtime?.getManifest?.().version ?? 'unknown'),
      'Docked side panel': globalThis.chrome?.sidePanel ? 'available' : 'unavailable',
      'User-Agent rewriting': globalThis.chrome?.declarativeNetRequest ? 'available' : 'unavailable',
      'Native downloads': globalThis.chrome?.downloads ? 'available' : 'unavailable',
    };
  }
}
