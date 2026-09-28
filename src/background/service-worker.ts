/**
 * MV3 background service worker.
 *
 * Responsibilities:
 *  - own every network request (the extension has host permissions, pages do not)
 *  - own stored credentials (the UI only ever receives masked views)
 *  - answer UI/content-script messages through the shared router
 *  - keep declarativeNetRequest User-Agent rules in sync with saved servers
 *
 * No credential is ever logged: only counts, ids and redacted URLs appear below.
 */
import { registerBuiltinAdapters } from '../adapters/index.js';
import { BooruClient } from '../core/client.js';
import { ChromeDownloader } from '../core/downloads.js';
import type { UiRequest } from '../core/messages.js';
import { DownloadQueue } from '../core/queue.js';
import { createRouter } from '../core/router.js';
import { ServerStore } from '../core/servers.js';
import { SettingsStore } from '../core/settings.js';
import { ChromeStorageArea, migrateStorage, STORAGE_KEYS } from '../core/storage.js';
import { syncUserAgentRules } from '../core/userAgent.js';
import { ValidationService } from '../core/validation.js';
import { HttpClient } from '../shared/http.js';

registerBuiltinAdapters();

const VERSION = globalThis.chrome?.runtime?.getManifest?.().version ?? '0.1.0';
const area = new ChromeStorageArea();
const settings = new SettingsStore(area);
const servers = new ServerStore(area);
const http = new HttpClient({ maxRetries: 3 });
const validation = new ValidationService(http);
const client = new BooruClient({ servers, settings, http, validation });
const downloader = new ChromeDownloader();
const queue = new DownloadQueue({ storage: area, client, servers, settings, downloader });

const handle = createRouter({
  client,
  queue,
  servers,
  settings,
  downloader,
  syncUserAgentRules,
  environment: 'extension',
  version: VERSION,
});

async function refreshUserAgentRules(reason: string): Promise<void> {
  try {
    const [currentSettings, currentServers] = await Promise.all([settings.get(), servers.list()]);
    const result = await syncUserAgentRules(currentServers, currentSettings);
    console.info(
      `[booru] User-Agent rules ${result.ok ? 'synced' : 'failed'} (${result.applied} rule(s), reason: ${reason})${
        result.error ? `: ${result.error}` : ''
      }`,
    );
  } catch (error) {
    console.warn('[booru] User-Agent rule sync threw:', error instanceof Error ? error.message : String(error));
  }
}

globalThis.chrome?.runtime?.onInstalled?.addListener((details) => {
  void (async () => {
    await migrateStorage(area);
    await refreshUserAgentRules(`install:${details.reason}`);
    const count = await servers.count();
    console.info(`[booru] Booru Server Manager ${VERSION} ready - ${count} saved server profile(s)`);
  })();
});

globalThis.chrome?.runtime?.onStartup?.addListener(() => {
  void (async () => {
    await refreshUserAgentRules('startup');
  })();
});

// Another context wrote settings/servers: drop caches so the worker re-reads them.
globalThis.chrome?.storage?.onChanged?.addListener((changes, areaName) => {
  if (areaName !== 'local') return;
  if (STORAGE_KEYS.servers in changes) servers.invalidate();
  if (STORAGE_KEYS.settings in changes) settings.invalidate();
});

globalThis.chrome?.runtime?.onMessage?.addListener((message, _sender, sendResponse) => {
  const request = message as UiRequest;
  if (!request || typeof request !== 'object' || typeof (request as { type?: unknown }).type !== 'string') {
    return false;
  }
  void handle(request).then(sendResponse);
  return true; // keep the message channel open for the async response
});

// Re-sync rules whenever the saved server list changes (worker-side writes).
servers.onChange((list) => {
  void (async () => {
    const currentSettings = await settings.get();
    await syncUserAgentRules(list, currentSettings);
  })();
});
