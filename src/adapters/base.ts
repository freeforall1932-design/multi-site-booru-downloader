import { BooruError } from '../shared/errors.js';
import { parseJsonBody, type HttpResponseSnapshot } from '../shared/http.js';
import type { AdapterFailure } from '../core/adapter.js';
import type { AccountInfo, Rating } from '../shared/types.js';
import { isRecord, splitTags, unique } from '../shared/util.js';

const BASE64_ALPHABET = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';

/**
 * UTF-8 safe base64. `btoa` only handles Latin-1 and `Buffer` is not available
 * in the extension bundle, so both platforms use this implementation.
 */
export function encodeBase64(value: string): string {
  const bytes = new TextEncoder().encode(value);
  let output = '';
  for (let index = 0; index < bytes.length; index += 3) {
    const byte1 = bytes[index] ?? 0;
    const byte2 = bytes[index + 1];
    const byte3 = bytes[index + 2];
    const chunk = (byte1 << 16) | ((byte2 ?? 0) << 8) | (byte3 ?? 0);
    output += BASE64_ALPHABET[(chunk >> 18) & 63];
    output += BASE64_ALPHABET[(chunk >> 12) & 63];
    output += byte2 === undefined ? '=' : BASE64_ALPHABET[(chunk >> 6) & 63];
    output += byte3 === undefined ? '=' : BASE64_ALPHABET[chunk & 63];
  }
  return output;
}

/** Raw JSON object helper. */
export function asRecord(value: unknown): Record<string, unknown> | null {
  return isRecord(value) ? value : null;
}

export function pickString(source: Record<string, unknown> | null, ...keys: string[]): string | null {
  if (!source) return null;
  for (const key of keys) {
    const value = source[key];
    if (typeof value === 'string' && value.trim()) return value.trim();
    if (typeof value === 'number' && Number.isFinite(value)) return String(value);
  }
  return null;
}

export function pickNumber(source: Record<string, unknown> | null, ...keys: string[]): number | null {
  if (!source) return null;
  for (const key of keys) {
    const value = source[key];
    if (typeof value === 'number' && Number.isFinite(value)) return value;
    if (typeof value === 'string' && value.trim() && Number.isFinite(Number(value))) return Number(value);
  }
  return null;
}

export function pickBoolean(source: Record<string, unknown> | null, ...keys: string[]): boolean | null {
  if (!source) return null;
  for (const key of keys) {
    const value = source[key];
    if (typeof value === 'boolean') return value;
    if (value === 'true') return true;
    if (value === 'false') return false;
  }
  return null;
}

/** Read a nested object key (`file`, `preview`, `sample` on e621). */
export function pickGroup(source: Record<string, unknown> | null, key: string): Record<string, unknown> | null {
  const value = source?.[key];
  return isRecord(value) ? value : null;
}

/** Make a possibly-relative site URL absolute against the base URL. */
export function absolutize(baseUrl: string, raw: string | null): string | null {
  if (!raw) return null;
  if (/^https?:\/\//i.test(raw)) return raw;
  if (raw.startsWith('//')) {
    const scheme = baseUrl.startsWith('http://') ? 'http:' : 'https:';
    return `${scheme}${raw}`;
  }
  if (raw.startsWith('/')) return `${baseUrl.replace(/\/+$/, '')}${raw}`;
  return `${baseUrl.replace(/\/+$/, '')}/${raw}`;
}

export function isHtmlResponse(snapshot: HttpResponseSnapshot): boolean {
  if (snapshot.contentType.includes('html')) return true;
  const head = snapshot.bodyText.trimStart().slice(0, 200).toLowerCase();
  return head.startsWith('<!doctype html') || head.startsWith('<html');
}

/** JSON parse with a site-aware failure kind. */
export function readJson<T = unknown>(snapshot: HttpResponseSnapshot, context: string): T {
  return parseJsonBody<T>(snapshot, context);
}

/**
 * Generic HTTP status classification shared by adapters.
 * Adapters call this first, then add site-specific payload checks.
 */
export function classifyStatus(snapshot: HttpResponseSnapshot, siteLabel: string): AdapterFailure | null {
  if (isHtmlResponse(snapshot)) {
    return {
      kind: 'endpoint-mismatch',
      message: `${siteLabel} returned an HTML page instead of API JSON`,
      hint: 'Check that the base URL is the site root (no /posts or /index.php suffix) and that the server is a ' + siteLabel + ' instance.',
    };
  }
  const status = snapshot.status;
  if (status === 401) {
    return {
      kind: 'auth-failure',
      message: `${siteLabel} rejected the credentials (HTTP 401)`,
      hint: 'Re-check the API key (and username/user id) on the site account page. Keys are case sensitive.',
    };
  }
  if (status === 403) {
    return {
      kind: 'blocked',
      message: `${siteLabel} denied the request (HTTP 403)`,
      hint: 'Usually a missing/blocked User-Agent, disabled API access on the account, or a Cloudflare challenge.',
    };
  }
  if (status === 404) {
    return {
      kind: 'endpoint-mismatch',
      message: `${siteLabel} has no such endpoint (HTTP 404)`,
      hint: 'The base URL probably points at something that is not this booru, or the instance is a fork with a different API.',
    };
  }
  if (status === 405) {
    return {
      kind: 'endpoint-mismatch',
      message: `${siteLabel} does not accept this method at that endpoint (HTTP 405)`,
    };
  }
  if (status === 429) {
    return {
      kind: 'rate-limited',
      message: `${siteLabel} rate-limited the request (HTTP 429)`,
      hint: 'Lower the request interval in Settings and try again in a minute.',
    };
  }
  if (status === 503 || status === 522 || status === 524) {
    return {
      kind: 'rate-limited',
      message: `${siteLabel} is unavailable or rate-limiting (HTTP ${status})`,
      hint: 'e621 returns 503 when the two-requests-per-second limit is exceeded. Wait a moment and retry.',
    };
  }
  if (status >= 500) {
    return { kind: 'server-error', message: `${siteLabel} returned a server error (HTTP ${status})` };
  }
  return null;
}

/** Look for credential error strings in a plain-text/JSON body (Gelbooru style). */
export function detectAuthWordsInBody(body: string): AdapterFailure | null {
  const text = body.toLowerCase();
  if (!text) return null;
  if (/invalid api key|incorrect api key|api key (?:is )?(?:invalid|not valid|missing)/.test(text)) {
    return {
      kind: 'auth-failure',
      message: 'The site reports an invalid API key',
      hint: 'Copy the API key again from the site account page - it must match exactly.',
    };
  }
  if (/user not found|no such user|invalid user|user_id (?:is )?(?:invalid|missing)/.test(text)) {
    return {
      kind: 'auth-failure',
      message: 'The site reports an unknown user id',
      hint: 'Gelbooru and derivatives authenticate with api_key + user_id together.',
    };
  }
  if (/banned|blacklisted|blocked for abuse/.test(text)) {
    return { kind: 'blocked', message: 'The site reports this client/account as blocked' };
  }
  if (/too many requests|rate limit|slow down/.test(text)) {
    return { kind: 'rate-limited', message: 'The site asked the client to slow down' };
  }
  return null;
}

/**
 * Build `-rating:` exclusions for the site's own rating tokens.
 * Exclusions are used (instead of allow-lists) because every target site
 * supports them and OR-syntax for rating metatags differs between sites.
 */
export function ratingExclusions(
  siteRatingTokens: readonly string[],
  canonicalRatingFor: (token: string) => Rating | null,
  allowed: Rating[],
): { tags: string[]; warnings: string[] } {
  const allowedSet = new Set(allowed);
  const tags: string[] = [];
  const warnings: string[] = [];
  const unmapped: string[] = [];
  for (const token of siteRatingTokens) {
    const canonical = canonicalRatingFor(token);
    if (!canonical) {
      unmapped.push(token);
      continue;
    }
    if (!allowedSet.has(canonical)) tags.push(`-rating:${token}`);
  }
  if (unmapped.length) {
    warnings.push(`Rating tokens without a shared equivalent were left unfiltered: ${unmapped.join(', ')}.`);
  }
  if (tags.length === 0 && allowed.length > 0) {
    warnings.push('The rating filter allows every rating this site has - no exclusion tags were added.');
  }
  return { tags: unique(tags), warnings };
}

/** Merge search tags with adapter-provided rating filters. */
export function composeTags(baseTags: string | undefined, extra: string[], globalSuffix: string): string {
  return [...splitTags(baseTags), ...extra, ...splitTags(globalSuffix)].join(' ').trim();
}

/** Normalize account info extracted from a profile-ish endpoint. */
export function accountInfo(name: string | null, id: string | null, level: string | null): AccountInfo {
  return { username: name, userId: id, level };
}

/** Throw a properly typed BooruError for shape problems. */
export function shapeError(context: string, detail: string): BooruError {
  return new BooruError(`${context}: ${detail}`, {
    kind: 'parse-failure',
    hint: 'The server answered, but not with the JSON shape this adapter expects.',
  });
}
