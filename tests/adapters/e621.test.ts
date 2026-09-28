import { describe, expect, it } from 'vitest';
import { e621Adapter, e621AuthHeader } from '../../src/adapters/e621.js';
import { createAdapterContext } from '../../src/core/registry.js';
import { DEFAULT_SETTINGS } from '../../src/shared/types.js';
import type { BooruPost, SearchSpec } from '../../src/shared/types.js';
import { BooruError } from '../../src/shared/errors.js';
import { decodeBase64 } from '../base64.js';
import { e621Server, htmlSnapshot, httpSnapshot } from '../helpers.js';

const settings = DEFAULT_SETTINGS;

function context(overrides: Record<string, unknown> = {}) {
  return createAdapterContext(e621Adapter, e621Server(overrides as never), settings);
}

const legacyPost = {
  id: 1500001,
  created_at: '2024-05-01T10:00:00.000-04:00',
  file: { width: 1200, height: 1600, ext: 'png', size: 480_000, md5: 'abc123', url: 'https://static1.e621.net/data/ab/c1/abc123.png' },
  preview: { width: 300, height: 400, url: 'https://static1.e621.net/data/preview/ab/c1/abc123.jpg' },
  sample: { has: true, width: 850, height: 1133, url: 'https://static1.e621.net/data/sample/ab/c1/abc123.jpg' },
  score: { up: 12, down: 1, total: 11 },
  rating: 'q',
  sources: ['https://artist.example/1', 'https://artist.example/2'],
  description: 'sample description',
  tags: {
    general: ['cat', 'solo'],
    artist: ['some_artist'],
    character: ['some_character'],
    copyright: ['original'],
    species: ['feline'],
    meta: [],
  },
};

describe('e621 adapter - auth', () => {
  it('builds a Basic header from username + api key', () => {
    const header = e621AuthHeader(context());
    expect(header).toBe('Basic ' + btoa('demo_e621:e621-demo-key'));
    expect(decodeBase64(header!.slice(6))).toBe('demo_e621:e621-demo-key');
  });

  it('omits the header when credentials are incomplete', () => {
    expect(e621AuthHeader(context({ username: '', apiKey: 'x' }))).toBeNull();
    expect(e621AuthHeader(context({ username: 'x', apiKey: '' }))).toBeNull();
  });

  it('encodes non-latin usernames safely', () => {
    const header = e621AuthHeader(context({ username: 'ünïcødé', apiKey: 'k' }));
    expect(decodeBase64(header!.slice(6))).toBe('ünïcødé:k');
  });
});

describe('e621 adapter - requests', () => {
  it('adds the mandatory descriptive user agent as the documented _client fallback', () => {
    const spec = e621Adapter.buildSearchRequest(context(), { tags: 'cat', limit: 10 });
    const url = new URL(spec.url);
    expect(url.pathname).toBe('/posts.json');
    expect(url.searchParams.get('tags')).toBe('cat');
    expect(url.searchParams.get('limit')).toBe('10');
    expect(url.searchParams.get('_client')).toContain('BooruServerManager/test');
    expect(spec.headers?.Authorization).toMatch(/^Basic /);
  });

  it('omits _client when the setting is off', () => {
    const ctx = createAdapterContext(e621Adapter, e621Server(), { ...settings, sendClientParam: false });
    const spec = e621Adapter.buildSearchRequest(ctx, { tags: 'cat' });
    expect(new URL(spec.url).searchParams.has('_client')).toBe(false);
  });

  it('clamps the limit to the documented maximum of 320', () => {
    const spec = e621Adapter.buildSearchRequest(context(), { tags: '', limit: 5000 });
    expect(new URL(spec.url).searchParams.get('limit')).toBe('320');
  });

  it('supports b<id> pagination tokens', () => {
    const spec = e621Adapter.buildSearchRequest(context(), { tags: 'cat', pageToken: 'b1234' });
    expect(new URL(spec.url).searchParams.get('page')).toBe('b1234');
  });

  it('keeps site-specific rating syntax inside the adapter', () => {
    const spec = e621Adapter.buildSearchRequest(context(), {
      tags: 'cat',
      ratingFilter: { enabled: true, allowed: ['safe'] },
    });
    const tags = new URL(spec.url).searchParams.get('tags') ?? '';
    expect(tags).toContain('-rating:q');
    expect(tags).toContain('-rating:e');
    expect(tags).not.toContain('-rating:s');
  });

  it('does not add rating exclusions when the filter is disabled', () => {
    const spec = e621Adapter.buildSearchRequest(context(), { tags: 'cat', ratingFilter: { enabled: false, allowed: [] } });
    expect(new URL(spec.url).searchParams.get('tags')).toBe('cat');
  });

  it('appends global tag suffix from settings', () => {
    const ctx = createAdapterContext(e621Adapter, e621Server(), { ...settings, globalTagSuffix: '-gore' });
    const spec = e621Adapter.buildSearchRequest(ctx, { tags: 'cat' });
    expect(new URL(spec.url).searchParams.get('tags')).toBe('cat -gore');
  });

  it('builds single post requests (with the _client fallback attached)', () => {
    const spec = e621Adapter.buildPostRequest(context(), '12345');
    const url = new URL(spec.url);
    expect(url.origin).toBe('https://e621.net');
    expect(url.pathname).toBe('/posts/12345.json');
    expect(url.searchParams.get('_client')).toContain('BooruServerManager');
  });
});

describe('e621 adapter - parsing', () => {
  it('normalizes a legacy post object', () => {
    const ctx = context();
    const post = e621Adapter.normalizePost(legacyPost, ctx) as BooruPost;
    expect(post.id).toBe('1500001');
    expect(post.rating).toBe('questionable');
    expect(post.rawRating).toBe('q');
    expect(post.ext).toBe('png');
    expect(post.sizeBytes).toBe(480_000);
    expect(post.artistTags).toEqual(['some_artist']);
    expect(post.characterTags).toEqual(['some_character']);
    expect(post.tags).toContain('cat');
    expect(post.tagCategories.species).toEqual(['feline']);
    expect(post.score).toBe(11);
    expect(post.isVideo).toBe(false);
    expect(post.postUrl).toBe('https://e621.net/posts/1500001');
  });

  it('normalizes a v2/extended post object', () => {
    const v2 = {
      id: 42,
      created_at: '2025-01-01T00:00:00.000Z',
      file: { width: 800, height: 600, ext: 'webm', size: 2_000_000, md5: 'f00d', url: '/data/foo.webm' },
      preview: { url: 'https://static1.e621.net/data/preview/foo.jpg' },
      score: { total: 99 },
      rating: 'e',
      sources: ['https://example.com/a'],
      tags: { general: ['video'], artist: ['someone'], meta: ['bad'], invalid: ['x'] },
    };
    const post = e621Adapter.normalizePost(v2, context()) as BooruPost;
    expect(post.ext).toBe('webm');
    expect(post.isVideo).toBe(true);
    expect(post.score).toBe(99);
    expect(post.rating).toBe('explicit');
    expect(post.fileUrl).toBe('https://e621.net/data/foo.webm');
    expect(post.tagCategories.meta).toEqual(['bad']);
  });

  it('handles a tags string plus tag_string_* fields', () => {
    const flat = { id: 7, rating: 's', tags: 'alpha beta', tag_string_artist: 'painter', file_url: 'https://x/y.jpg' };
    const post = e621Adapter.normalizePost(flat, context()) as BooruPost;
    expect(post.tags).toEqual(expect.arrayContaining(['alpha', 'beta', 'painter']));
    expect(post.artistTags).toEqual(['painter']);
  });

  it('unwraps `{ post: {...} }` on single post responses', () => {
    const snapshot = httpSnapshot({ post: legacyPost });
    const post = e621Adapter.parsePostResponse(snapshot, '1500001', context());
    expect(post.id).toBe('1500001');
  });

  it('parses search results and reports paging', () => {
    const snapshot = httpSnapshot([legacyPost, { ...legacyPost, id: 2 }]);
    const spec: SearchSpec = { tags: 'cat', limit: 2 };
    const result = e621Adapter.parseSearchResponse(snapshot, spec, context());
    expect(result.posts).toHaveLength(2);
    expect(result.hasMore).toBe(true);
    expect(result.siteType).toBe('e621');
    expect(result.appliedTags).toBe('cat');
  });

  it('rejects non-array payloads as a shape error', () => {
    const snapshot = httpSnapshot({ unexpected: true });
    expect(() => e621Adapter.parseSearchResponse(snapshot, { tags: '' }, context())).toThrowError(/JSON array/);
  });
});

describe('e621 adapter - failure classification', () => {
  it('maps 401 to auth-failure', () => {
    const failure = e621Adapter.classifyFailure!(httpSnapshot({ error: 'unauthorized' }, 401));
    expect(failure?.kind).toBe('auth-failure');
  });

  it('maps 403 to blocked (user agent policy)', () => {
    const failure = e621Adapter.classifyFailure!(httpSnapshot({ error: 'forbidden' }, 403));
    expect(failure?.kind).toBe('blocked');
    expect(failure?.hint).toMatch(/user-agent/i);
  });

  it('maps 503 to rate-limited (documented e621 behaviour)', () => {
    expect(e621Adapter.classifyFailure!(httpSnapshot({}, 503))?.kind).toBe('rate-limited');
  });

  it('maps HTML responses to endpoint-mismatch', () => {
    expect(e621Adapter.classifyFailure!(htmlSnapshot())?.kind).toBe('endpoint-mismatch');
  });

  it('surfaces 401 as a typed error when parsing', () => {
    const snapshot = httpSnapshot({ error: 'unauthorized' }, 401);
    try {
      e621Adapter.parseSearchResponse(snapshot, {}, context());
      throw new Error('expected parse to throw');
    } catch (error) {
      expect(error).toBeInstanceOf(BooruError);
      expect((error as BooruError).kind).toBe('auth-failure');
    }
  });
});

describe('e621 adapter - routes and capabilities', () => {
  it('detects post, search and index routes', () => {
    expect(e621Adapter.matchRoute('https://e621.net/posts/12345')?.kind).toBe('post');
    expect(e621Adapter.matchRoute('https://e926.net/posts?tags=cat')?.kind).toBe('search');
    expect(e621Adapter.matchRoute('https://e621.net/')?.kind).toBe('index');
    expect(e621Adapter.matchRoute('https://example.com/posts/1')).toBeNull();
  });

  it('declares the documented rate limit and UA requirement', () => {
    expect(e621Adapter.capabilities.minRequestIntervalMs).toBe(1000);
    expect(e621Adapter.capabilities.requiresUserAgent).toBe(true);
    expect(e621Adapter.capabilities.maxPostsPerRequest).toBe(320);
  });

  it('exposes credential fields for the UI without leaking values', () => {
    const fields = e621Adapter.credentialFields();
    expect(fields.map((field) => field.key)).toEqual(['username', 'apiKey', 'customUserAgent']);
    expect(fields.find((field) => field.key === 'apiKey')?.secret).toBe(true);
  });

  it('validates probes: endpoint probe is anonymous, auth probe requires a key', () => {
    const withCreds = e621Adapter.buildValidationProbes(context());
    const withoutCreds = e621Adapter.buildValidationProbes(context({ apiKey: '' }));
    expect(withCreds[0]!.request.headers?.Authorization).toBeUndefined();
    expect(withCreds.filter((probe) => probe.purpose === 'auth').every((probe) => probe.enabled)).toBe(true);
    expect(withoutCreds.filter((probe) => probe.purpose === 'auth').every((probe) => !probe.enabled)).toBe(true);
  });
});
