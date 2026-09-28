/**
 * Storage abstraction. All persisted state goes through one of these areas so
 * tests and the browser preview can run the exact same service code without a
 * Chrome runtime.
 *
 * Everything lives in `chrome.storage.local` (never `sync`) because it holds
 * API keys; see docs/SECURITY.md.
 */
export interface StorageArea {
  get<T>(key: string): Promise<T | null>;
  set(key: string, value: unknown): Promise<void>;
  remove(key: string): Promise<void>;
  clear(): Promise<void>;
  keys(): Promise<string[]>;
}

export const STORAGE_KEYS = {
  servers: 'bsm.servers',
  settings: 'bsm.settings',
  queue: 'bsm.queue',
  meta: 'bsm.meta',
  /** Successful downloads, so listings can skip what is already saved. */
  history: 'bsm.history',
  /** Remembered searches per server (side-panel search box). */
  searches: 'bsm.searches',
} as const;

/** Bump when a migration is needed; `migrateStorage` is the single place to react. */
export const SCHEMA_VERSION = 1;

export class MemoryStorageArea implements StorageArea {
  private readonly data = new Map<string, unknown>();

  constructor(seed: Record<string, unknown> = {}) {
    for (const [key, value] of Object.entries(seed)) this.data.set(key, value);
  }

  async get<T>(key: string): Promise<T | null> {
    return this.data.has(key) ? (this.data.get(key) as T) : null;
  }

  async set(key: string, value: unknown): Promise<void> {
    this.data.set(key, value);
  }

  async remove(key: string): Promise<void> {
    this.data.delete(key);
  }

  async clear(): Promise<void> {
    this.data.clear();
  }

  async keys(): Promise<string[]> {
    return Array.from(this.data.keys());
  }
}

/** Browser (preview) storage backed by `localStorage`. */
export class WebStorageArea implements StorageArea {
  constructor(private readonly namespace = 'bsm') {}

  private key(key: string): string {
    return `${this.namespace}:${key}`;
  }

  async get<T>(key: string): Promise<T | null> {
    const raw = globalThis.localStorage?.getItem(this.key(key));
    if (raw === null || raw === undefined) return null;
    try {
      return JSON.parse(raw) as T;
    } catch {
      return null;
    }
  }

  async set(key: string, value: unknown): Promise<void> {
    globalThis.localStorage?.setItem(this.key(key), JSON.stringify(value));
  }

  async remove(key: string): Promise<void> {
    globalThis.localStorage?.removeItem(this.key(key));
  }

  async clear(): Promise<void> {
    const storage = globalThis.localStorage;
    if (!storage) return;
    const toRemove: string[] = [];
    for (let index = 0; index < storage.length; index += 1) {
      const name = storage.key(index);
      if (name?.startsWith(`${this.namespace}:`)) toRemove.push(name);
    }
    toRemove.forEach((name) => storage.removeItem(name));
  }

  async keys(): Promise<string[]> {
    const storage = globalThis.localStorage;
    if (!storage) return [];
    const out: string[] = [];
    for (let index = 0; index < storage.length; index += 1) {
      const name = storage.key(index);
      if (name?.startsWith(`${this.namespace}:`)) out.push(name.slice(this.namespace.length + 1));
    }
    return out;
  }
}

/** `chrome.storage.local` implementation used by the extension. */
export class ChromeStorageArea implements StorageArea {
  private area(): chrome.storage.LocalStorageArea {
    const storage = globalThis.chrome?.storage?.local;
    if (!storage) throw new Error('chrome.storage.local is unavailable');
    return storage;
  }

  async get<T>(key: string): Promise<T | null> {
    const result = await this.area().get(key);
    return (key in result ? (result[key] as T) : null) ?? null;
  }

  async set(key: string, value: unknown): Promise<void> {
    await this.area().set({ [key]: value });
  }

  async remove(key: string): Promise<void> {
    await this.area().remove(key);
  }

  async clear(): Promise<void> {
    await this.area().clear();
  }

  async keys(): Promise<string[]> {
    const all = await this.area().get(null);
    return Object.keys(all ?? {});
  }
}

export function hasChromeStorage(): boolean {
  return typeof globalThis.chrome !== 'undefined' && !!globalThis.chrome?.storage?.local;
}

/** Pick the best available area for the current environment. */
export function createDefaultStorageArea(): StorageArea {
  if (hasChromeStorage()) return new ChromeStorageArea();
  if (typeof globalThis.localStorage !== 'undefined') return new WebStorageArea();
  return new MemoryStorageArea();
}

export interface StorageMeta {
  schemaVersion: number;
  firstRunAt: string | null;
}

/** Placeholder migration hook - keeps future schema bumps in one place. */
export async function migrateStorage(area: StorageArea): Promise<StorageMeta> {
  const meta = await area.get<StorageMeta>(STORAGE_KEYS.meta);
  if (!meta) {
    const fresh: StorageMeta = { schemaVersion: SCHEMA_VERSION, firstRunAt: new Date().toISOString() };
    await area.set(STORAGE_KEYS.meta, fresh);
    return fresh;
  }
  if (meta.schemaVersion !== SCHEMA_VERSION) {
    const upgraded: StorageMeta = { ...meta, schemaVersion: SCHEMA_VERSION };
    await area.set(STORAGE_KEYS.meta, upgraded);
    return upgraded;
  }
  return meta;
}
