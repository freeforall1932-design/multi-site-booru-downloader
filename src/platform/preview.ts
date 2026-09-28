import { RecordingDownloader, type Downloader, type DownloadRequest, type DownloadOutcome } from '../core/downloads.js';
import type { RouterResponse, UiRequest } from '../core/messages.js';
import { WebStorageArea, type StorageArea } from '../core/storage.js';
import type { Platform } from './types.js';

/** `localStorage` namespace the preview harness resets and documents. */
export const PREVIEW_STORAGE_NAMESPACE = 'bsm-preview';

/**
 * Downloader used by the browser preview: it performs a real browser download of
 * the (mock) media URL and also records it so the preview can display a log.
 */
export class PreviewDownloader implements Downloader {
  private readonly recorder = new RecordingDownloader();
  private nextId = 1;

  get log() {
    return this.recorder.log;
  }

  async download(request: DownloadRequest): Promise<DownloadOutcome> {
    await this.recorder.download(request);
    try {
      const anchor = document.createElement('a');
      anchor.href = request.url;
      anchor.download = request.filename.split('/').pop() ?? 'download';
      anchor.target = '_blank';
      anchor.rel = 'noopener';
      document.body.append(anchor);
      anchor.click();
      anchor.remove();
    } catch {
      /* preview only */
    }
    return { downloadId: this.nextId++, viaFallback: false, bytes: null };
  }
}

export interface PreviewPlatformOptions {
  handler: (request: UiRequest) => Promise<RouterResponse>;
  /** Shared with the download queue so both record the same download log. */
  downloader?: Downloader;
  /** Shared with the service graph so the UI and the router read one area. */
  storage?: StorageArea;
}

/**
 * Platform implementation for `npm run preview`: identical services, but storage
 * is `localStorage`, downloads are browser downloads of mock media, and messages
 * are handled in-process instead of by a service worker.
 */
export class PreviewPlatform implements Platform {
  readonly environment = 'preview' as const;
  readonly storage: StorageArea;
  readonly downloader: Downloader;
  readonly permissions = {
    contains: async () => true,
    request: async () => true,
  };

  constructor(private readonly options: PreviewPlatformOptions) {
    this.downloader = options.downloader ?? new PreviewDownloader();
    this.storage = options.storage ?? new WebStorageArea(PREVIEW_STORAGE_NAMESPACE);
  }

  async send(request: UiRequest): Promise<RouterResponse> {
    return this.options.handler(request);
  }

  async syncUserAgentRules(): Promise<{ ok: boolean; applied: number; error?: string }> {
    return { ok: true, applied: 0, error: 'Preview mode: declarativeNetRequest is not available, the `_client` fallback is used instead.' };
  }

  async activeTabUrl(): Promise<string | null> {
    const params = new URLSearchParams(globalThis.location.search);
    return params.get('tab');
  }

  openOptions(hash?: string): void {
    if (!hash) return;
    globalThis.location.hash = hash.replace(/^#/, '');
  }

  async describeEnvironment(): Promise<Record<string, string>> {
    return {
      Environment: 'browser preview (no extension APIs)',
      Storage: 'localStorage namespace "bsm-preview"',
      Downloads: 'browser download of mock media',
      'User-Agent rewriting': 'disabled in preview',
    };
  }
}
