import { BooruError } from '../shared/errors.js';
import { safeUrl } from '../shared/util.js';

export interface DownloadRequest {
  url: string;
  /** Path relative to the browser's download directory. */
  filename: string;
  /** Avoid clobbering an existing file (default: uniquify). */
  conflictAction?: 'uniquify' | 'overwrite' | 'prompt';
  /** Abort signal used by queue cancellation. */
  signal?: AbortSignal;
}

export interface DownloadOutcome {
  downloadId: number | null;
  /** True when the direct download failed and the bytes were fetched then saved. */
  viaFallback: boolean;
  bytes: number | null;
}

export interface Downloader {
  download(request: DownloadRequest): Promise<DownloadOutcome>;
}

/** Records downloads instead of touching the filesystem (tests + browser preview). */
export class RecordingDownloader implements Downloader {
  readonly log: Array<{ url: string; filename: string; at: string }> = [];
  /** When set, the next `download()` call throws it and the field resets. */
  failNext: Error | null = null;
  /** URLs that always fail (a hosting page that refuses, a dead mirror). */
  failUrls = new Set<string>();
  /** Failures the caller asked for, by URL, without consuming `failNext`. */
  failMessage = 'HTTP 403 - the hosting page refused the request';
  private nextId = 1;

  async download(request: DownloadRequest): Promise<DownloadOutcome> {
    if (!safeUrl(request.url)) {
      throw new BooruError(`Not a valid download URL: ${request.url}`, { kind: 'parse-failure' });
    }
    const failure = this.failNext;
    if (failure) {
      this.failNext = null;
      throw failure;
    }
    if (this.failUrls.has(request.url)) throw new Error(this.failMessage);
    this.log.push({ url: request.url, filename: request.filename, at: new Date().toISOString() });
    return { downloadId: this.nextId++, viaFallback: false, bytes: null };
  }

  reset(): void {
    this.log.length = 0;
    this.failNext = null;
    this.failUrls.clear();
    this.nextId = 1;
  }
}

export interface ChromeDownloaderOptions {
  /** Fetch implementation used for the blob fallback (extension host permissions apply). */
  fetchImpl?: (input: string, init?: RequestInit) => Promise<Response>;
  /** Blob URL factory override for tests. */
  createObjectUrl?: (blob: Blob) => string;
  revokeObjectUrl?: (url: string) => void;
}

/**
 * Real downloads through `chrome.downloads`.
 *
 * The direct API call is preferred (browser handles retries/resume). If Chrome
 * rejects the URL - typically a media host the extension has no host permission
 * for - the file is fetched in the worker and saved from a blob URL instead.
 */
export class ChromeDownloader implements Downloader {
  private readonly fetchImpl: (input: string, init?: RequestInit) => Promise<Response>;
  private readonly createObjectUrl: (blob: Blob) => string;
  private readonly revokeObjectUrl: (url: string) => void;

  constructor(options: ChromeDownloaderOptions = {}) {
    this.fetchImpl = options.fetchImpl ?? ((input, init) => fetch(input, init));
    this.createObjectUrl = options.createObjectUrl ?? ((blob) => URL.createObjectURL(blob));
    this.revokeObjectUrl = options.revokeObjectUrl ?? ((url) => URL.revokeObjectURL(url));
  }

  async download(request: DownloadRequest): Promise<DownloadOutcome> {
    try {
      const downloadId = await this.direct(request.url, request.filename, request.conflictAction ?? 'uniquify');
      return { downloadId, viaFallback: false, bytes: null };
    } catch (error) {
      const booruError = error instanceof BooruError ? error : null;
      if (booruError?.kind !== 'blocked' && booruError?.kind !== 'unknown') throw error;
      return this.viaBlob(request);
    }
  }

  private async direct(url: string, filename: string, conflictAction: 'uniquify' | 'overwrite' | 'prompt'): Promise<number> {
    const downloads = globalThis.chrome?.downloads;
    if (!downloads) {
      throw new BooruError('chrome.downloads is unavailable', { kind: 'unknown' });
    }
    try {
      return await downloads.download({ url, filename, conflictAction, saveAs: false });
    } catch (error) {
      const message = error instanceof Error ? error.message : String(error);
      const blocked = /permission|denied|not allowed|invalid url|forbidden/i.test(message);
      throw new BooruError(`chrome.downloads rejected the file: ${message}`, {
        kind: blocked ? 'blocked' : 'unknown',
        hint: blocked ? 'Grant access to this media host (Settings > Host access) and try again.' : null,
      });
    }
  }

  private async viaBlob(request: DownloadRequest): Promise<DownloadOutcome> {
    const response = await this.fetchImpl(request.url, { signal: request.signal, credentials: 'omit' });
    if (!response.ok) {
      throw new BooruError(`Media host refused the download (HTTP ${response.status})`, {
        kind: response.status === 401 || response.status === 403 ? 'blocked' : 'network-failure',
        status: response.status,
      });
    }
    const blob = await response.blob();
    const objectUrl = this.createObjectUrl(blob);
    try {
      const downloadId = await this.direct(objectUrl, request.filename, request.conflictAction ?? 'uniquify');
      return { downloadId, viaFallback: true, bytes: blob.size };
    } finally {
      setTimeout(() => this.revokeObjectUrl(objectUrl), 60_000);
    }
  }
}
