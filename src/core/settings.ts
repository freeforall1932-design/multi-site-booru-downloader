import { DEFAULT_SETTINGS, type ExtensionSettings } from '../shared/types.js';
import { clamp, toInt } from '../shared/util.js';
import { STORAGE_KEYS, type StorageArea } from './storage.js';

export type SettingsListener = (settings: ExtensionSettings) => void;

const MAX_TEMPLATE_LENGTH = 300;

/** Coerce whatever is in storage into a valid settings object. */
export function normalizeSettings(raw: unknown): ExtensionSettings {
  const input = (raw ?? {}) as Partial<ExtensionSettings>;
  const theme = input.theme === 'light' || input.theme === 'dark' ? input.theme : 'system';
  return {
    folderTemplate: sanitizeTemplate(input.folderTemplate, DEFAULT_SETTINGS.folderTemplate),
    filenameTemplate: sanitizeTemplate(input.filenameTemplate, DEFAULT_SETTINGS.filenameTemplate),
    maxConcurrency: clamp(toInt(input.maxConcurrency, DEFAULT_SETTINGS.maxConcurrency), 1, 8),
    minRequestIntervalMs: clamp(toInt(input.minRequestIntervalMs, DEFAULT_SETTINGS.minRequestIntervalMs), 0, 30_000),
    globalTagSuffix: typeof input.globalTagSuffix === 'string' ? input.globalTagSuffix.trim().slice(0, 300) : '',
    maxTagsInFilename: clamp(toInt(input.maxTagsInFilename, DEFAULT_SETTINGS.maxTagsInFilename), 1, 30),
    tagSeparator: typeof input.tagSeparator === 'string' ? input.tagSeparator.slice(0, 3) : DEFAULT_SETTINGS.tagSeparator,
    enableContentScriptButton: input.enableContentScriptButton ?? DEFAULT_SETTINGS.enableContentScriptButton,
    sendClientParam: input.sendClientParam ?? DEFAULT_SETTINGS.sendClientParam,
    rewriteUserAgent: input.rewriteUserAgent ?? DEFAULT_SETTINGS.rewriteUserAgent,
    enforceRatingFilterOnDownload: input.enforceRatingFilterOnDownload ?? DEFAULT_SETTINGS.enforceRatingFilterOnDownload,
    maxRetries: clamp(toInt(input.maxRetries, DEFAULT_SETTINGS.maxRetries), 1, 8),
    theme,
  };
}

function sanitizeTemplate(value: unknown, fallback: string): string {
  if (typeof value !== 'string') return fallback;
  const trimmed = value.trim().slice(0, MAX_TEMPLATE_LENGTH);
  return trimmed || fallback;
}

export class SettingsStore {
  private cache: ExtensionSettings | null = null;
  private readonly listeners = new Set<SettingsListener>();

  constructor(private readonly area: StorageArea) {}

  async get(): Promise<ExtensionSettings> {
    if (this.cache) return { ...this.cache };
    const stored = await this.area.get<unknown>(STORAGE_KEYS.settings);
    this.cache = normalizeSettings(stored ?? DEFAULT_SETTINGS);
    return { ...this.cache };
  }

  async save(patch: Partial<ExtensionSettings>): Promise<ExtensionSettings> {
    const current = await this.get();
    const next = normalizeSettings({ ...current, ...patch });
    this.cache = next;
    await this.area.set(STORAGE_KEYS.settings, next);
    this.listeners.forEach((listener) => listener({ ...next }));
    return { ...next };
  }

  async reset(): Promise<ExtensionSettings> {
    return this.save(DEFAULT_SETTINGS);
  }

  onChange(listener: SettingsListener): () => void {
    this.listeners.add(listener);
    return () => this.listeners.delete(listener);
  }

  /** Storage writes from other contexts (options page vs service worker) invalidate the cache. */
  invalidate(): void {
    this.cache = null;
  }
}
