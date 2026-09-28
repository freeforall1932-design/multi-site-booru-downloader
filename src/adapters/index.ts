/**
 * Adapter registration point.
 *
 * Adding a booru-like site is a single line here plus a new adapter module.
 * Nothing else in the codebase branches on site type - see
 * docs/ADAPTER_CONTRACT.md.
 */
import { registerAdapter } from '../core/registry.js';
import { danbooruAdapter } from './danbooru.js';
import { e621Adapter } from './e621.js';
import { createGelbooruLikeAdapter, gelbooruAdapter } from './gelbooru.js';

let registered = false;

/** Idempotent: safe to call from the service worker, popup, options page and tests. */
export function registerBuiltinAdapters(): void {
  if (registered) return;
  registerAdapter(e621Adapter);
  registerAdapter(danbooruAdapter);
  registerAdapter(gelbooruAdapter);
  registered = true;
}

/**
 * Example: attaching a Gelbooru-compatible fork takes a few lines and no changes
 * to shared code -
 *
 *   registerAdapter(createGelbooruLikeAdapter({
 *     siteType: 'rule34',
 *     displayName: 'Rule34',
 *     baseUrl: 'https://rule34.xxx',
 *     label: 'Rule34',
 *     hostPatterns: ['rule34.xxx', 'www.rule34.xxx'],
 *     apiDocsUrl: 'https://rule34.xxx/index.php?page=wiki&s=view&id=18780',
 *   }));
 */
export { e621Adapter, danbooruAdapter, gelbooruAdapter, createGelbooruLikeAdapter };
