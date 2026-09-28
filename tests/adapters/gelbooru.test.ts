import { describe, expect, it } from 'vitest';
import { createGelbooruLikeAdapter, gelbooruAdapter, gelbooruSearchUrl, unwrapGelbooruList } from '../../src/adapters/gelbooru.js';
import { registerAdapter, createAdapterContext, unregisterAdapter } from '../../src/core/registry.js';
import { DEFAULT_SETTINGS, type BooruPost } from '../../src/shared/types.js';
import { gelbooruServer, htmlSnapshot, httpSnapshot } from '../helpers.js';

const settings = DEFAULT_SETTINGS;

function context(overrides: Record<string, unknown> = {}) {
  return createAdapterContext(gelbooruAdapter, gelbooruServer(overrides as never), settings);
}

const gelbooruPost = {
  id: 9_000_001,
  created_at: 'Mon Jun 03 10:00:00 -0500 2024',
  score: 7,
  width: 1000,
  height: 1500,
  md5: 'cafebabe',
  directory: 'ab/cd',
  image: '9000001.jpg',
  rating: 'questionable',
  source: 'https://artist.example/x',
  change: 1234,
  owner: 'demo_gelbooru',
  creator_id: 4242,
  parent_id: '',
  sample: 1,
  preview_height: 300,
  preview_width: 200,
  tags: 'cat solo window',
  title: 'A synthetic gelbooru post',
  file_url: 'https://img3.gelbooru.com/images/ab/cd/9000001.jpg',
  preview_url: 'https://img3.gelbooru.com/thumbnails/ab/cd/thumbnail_cafebabe.jpg',
  sample_url: 'https://img3.gelbooru.com/samples/ab/cd/sample_cafebabe.jpg',
  status: 'active',
  post_locked: 0,
};

describe('Gelbooru adapter - requests', () => {
  it('builds the documented DAPI request with json=1', () => {
    const spec = gelbooruAdapter.buildSearchRequest(context(), { tags: 'cat', limit: 40, page: 2 });
    const url = new URL(spec.url);
    expect(url.pathname).toBe('/index.php');
    expect(url.searchParams.get('page')).toBe('dapi');
    expect(url.searchParams.get('s')).toBe('post');
    expect(url.searchParams.get('q')).toBe('index');
    expect(url.searchParams.get('json')).toBe('1');
    expect(url.searchParams.get('tags')).toBe('cat');
    expect(url.searchParams.get('limit')).toBe('40');
    expect(url.searchParams.get('pid')).toBe('2');
  });

  it('sends credentials as query parameters (documented method)', () => {
    const url = new URL(gelbooruAdapter.buildSearchRequest(context(), { tags: 'cat' }).url);
    expect(url.searchParams.get('api_key')).toBe('gelbooru-demo-key');
    expect(url.searchParams.get('user_id')).toBe('4242');
  });

  it('omits credentials when the profile has none', () => {
    const url = new URL(gelbooruAdapter.buildSearchRequest(context({ apiKey: '', userId: '' }), { tags: 'cat' }).url);
    expect(url.searchParams.has('api_key')).toBe(false);
    expect(url.searchParams.has('user_id')).toBe(false);
  });

  it('clamps the limit to 100', () => {
    const url = new URL(gelbooruAdapter.buildSearchRequest(context(), { limit: 1000 }).url);
    expect(url.searchParams.get('limit')).toBe('100');
  });

  it('uses Gelbooru rating words for exclusions', () => {
    const url = new URL(
      gelbooruAdapter.buildSearchRequest(context(), { ratingFilter: { enabled: true, allowed: ['general', 'sensitive'] } }).url,
    );
    const tags = url.searchParams.get('tags') ?? '';
    expect(tags).toContain('-rating:questionable');
    expect(tags).toContain('-rating:explicit');
    expect(tags).not.toContain('-rating:general');
  });

  it('fetches a single post by id through the same endpoint', () => {
    const url = new URL(gelbooruAdapter.buildPostRequest(context(), '9').url);
    expect(url.searchParams.get('id')).toBe('9');
    expect(url.searchParams.get('limit')).toBe('1');
  });

  it('normalizes a pasted API URL back to the site root', () => {
    expect(gelbooruAdapter.normalizeBaseUrl!('https://gelbooru.com/index.php?page=dapi&s=post&q=index')).toBe('https://gelbooru.com');
    expect(gelbooruAdapter.normalizeBaseUrl!('https://gelbooru.com/')).toBe('https://gelbooru.com');
  });
});

describe('Gelbooru adapter - parsing', () => {
  it('normalizes a DAPI post (flat tag string, no categories)', () => {
    const normalized = gelbooruAdapter.normalizePost(gelbooruPost, context()) as BooruPost;
    expect(normalized.rating).toBe('questionable');
    expect(normalized.tags).toEqual(['cat', 'solo', 'window']);
    expect(normalized.artistTags).toEqual([]);
    expect(normalized.postUrl).toBe('https://gelbooru.com/index.php?page=post&s=view&id=9000001');
    expect(normalized.description).toContain('synthetic');
  });

  it('rebuilds a file URL from directory + image when file_url is absent', () => {
    const normalized = gelbooruAdapter.normalizePost({ ...gelbooruPost, file_url: undefined }, context()) as BooruPost;
    expect(normalized.fileUrl).toBe('https://gelbooru.com/images/ab/cd/9000001.jpg');
  });

  it('unwraps the envelope, including the single-element object quirk', () => {
    expect(unwrapGelbooruList({ '@attributes': { count: 1 }, post: [gelbooruPost] })).toHaveLength(1);
    expect(unwrapGelbooruList({ '@attributes': { count: 1 }, post: gelbooruPost })).toHaveLength(1);
    expect(unwrapGelbooruList({ '@attributes': { count: 0 } })).toHaveLength(0);
  });

  it('parses an empty result set as an empty list (not an error)', () => {
    const snapshot = httpSnapshot({ '@attributes': { limit: 100, offset: 0, count: 0 } });
    const result = gelbooruAdapter.parseSearchResponse(snapshot, { limit: 100, page: 0 }, context());
    expect(result.posts).toEqual([]);
    expect(result.hasMore).toBe(false);
    expect(result.totalCount).toBe(0);
  });

  it('computes paging from the envelope attributes', () => {
    const body = { '@attributes': { limit: 2, offset: 0, count: 24 }, post: [gelbooruPost, { ...gelbooruPost, id: 2 }] };
    const result = gelbooruAdapter.parseSearchResponse(httpSnapshot(body), { limit: 2, page: 0 }, context());
    expect(result.hasMore).toBe(true);
    expect(result.totalCount).toBe(24);
  });

  it('throws a typed error for a missing post', () => {
    const snapshot = httpSnapshot({ '@attributes': { limit: 1, offset: 0, count: 0 } });
    expect(() => gelbooruAdapter.parsePostResponse(snapshot, '123', context())).toThrowError(/no post 123/);
  });
});

describe('Gelbooru adapter - failure classification', () => {
  it('maps 401 to auth failure and hints at api_key + user_id', () => {
    const failure = gelbooruAdapter.classifyFailure!(httpSnapshot({ error: 'Invalid API key' }, 401));
    expect(failure?.kind).toBe('auth-failure');
  });

  it('detects credential words in a 200 body (DAPI quirk)', () => {
    const failure = gelbooruAdapter.classifyFailure!(httpSnapshot('Invalid API key provided', 200, 'text/plain'));
    expect(failure?.kind).toBe('auth-failure');
  });

  it('detects HTML responses as endpoint mismatch', () => {
    expect(gelbooruAdapter.classifyFailure!(htmlSnapshot())?.kind).toBe('endpoint-mismatch');
  });

  it('rejects JSON without the DAPI envelope', () => {
    const snapshot = httpSnapshot({ posts: [] });
    expect(() => gelbooruAdapter.parseSearchResponse(snapshot, {}, context())).toThrowError(/envelope/);
  });

  it('reports plain-text error bodies as an unexpected format', () => {
    const failure = gelbooruAdapter.classifyFailure!(httpSnapshot('You are not allowed to do that', 200, 'text/plain'));
    expect(failure?.kind).toBe('parse-failure');
  });
});

describe('Gelbooru adapter - routes and validation', () => {
  it('detects view and list routes', () => {
    expect(gelbooruAdapter.matchRoute('https://gelbooru.com/index.php?page=post&s=view&id=42')?.kind).toBe('post');
    expect(gelbooruAdapter.matchRoute('https://gelbooru.com/index.php?page=post&s=list&tags=cat&pid=2')).toMatchObject({
      kind: 'search',
      tags: 'cat',
      page: 2,
    });
    expect(gelbooruAdapter.matchRoute('https://img3.gelbooru.com/images/ab/cd/x.jpg')).toBeNull();
  });

  it('declares api_key + user_id as required credential fields', () => {
    const fields = gelbooruAdapter.credentialFields();
    const required = fields.filter((field) => field.requiredForAuth).map((field) => field.key);
    expect(required).toEqual(['apiKey', 'userId']);
    expect(fields.find((field) => field.key === 'apiKey')?.secret).toBe(true);
  });

  it('probes anonymously for the endpoint and with credentials when present', () => {
    const withCreds = gelbooruAdapter.buildValidationProbes(context());
    const withoutCreds = gelbooruAdapter.buildValidationProbes(context({ apiKey: '', userId: '' }));
    expect(withCreds[0]!.request.url).not.toContain('api_key');
    expect(withCreds[1]!.enabled).toBe(true);
    expect(withoutCreds[1]!.enabled).toBe(false);
  });

  it('accepts the DAPI envelope during endpoint validation', () => {
    const probe = gelbooruAdapter.buildValidationProbes(context())[0]!;
    expect(probe.interpret(httpSnapshot({ '@attributes': { count: 1 }, post: [gelbooruPost] })).ok).toBe(true);
    expect(probe.interpret(httpSnapshot({ posts: [] })).ok).toBe(false);
  });
});

describe('Gelbooru-compatible forks', () => {
  it('can be registered for another instance without touching shared code', () => {
    const rule34 = createGelbooruLikeAdapter({
      siteType: 'rule34-test',
      displayName: 'Rule34 (test)',
      baseUrl: 'https://rule34.example',
      label: 'Rule34 test',
      hostPatterns: ['rule34.example'],
      apiDocsUrl: 'https://rule34.example/wiki',
    });
    registerAdapter(rule34, { override: true });
    try {
      const url = new URL(gelbooruSearchUrl('https://rule34.example', { tags: 'cat', limit: 5 }));
      expect(url.origin).toBe('https://rule34.example');
      expect(rule34.matchRoute('https://rule34.example/index.php?page=post&s=view&id=5')?.siteType).toBe('rule34-test');
      expect(rule34.matchRoute('https://gelbooru.com/index.php?page=post&s=view&id=5')).toBeNull();
    } finally {
      unregisterAdapter('rule34-test');
    }
  });

  it('keeps site type on normalized posts (so the queue knows the origin)', () => {
    const fork = createGelbooruLikeAdapter({
      siteType: 'fork-test',
      displayName: 'Fork',
      baseUrl: 'https://fork.example',
      label: 'Fork',
      hostPatterns: ['fork.example'],
      apiDocsUrl: 'https://fork.example/wiki',
    });
    registerAdapter(fork, { override: true });
    try {
      const ctx = createAdapterContext(fork, gelbooruServer({ siteType: 'fork-test', baseUrl: 'https://fork.example' }), settings);
      const post = fork.normalizePost(gelbooruPost, ctx);
      expect(post.siteType).toBe('fork-test');
      expect(post.serverId).toBe(ctx.server.id);
    } finally {
      unregisterAdapter('fork-test');
    }
  });
});
