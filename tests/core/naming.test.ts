import { describe, expect, it } from 'vitest';
import { buildDownloadPath, buildTokens, renderTemplate, sanitizeFolderPath, sanitizePathSegment } from '../../src/core/naming.js';
import type { BooruPost } from '../../src/shared/types.js';
import { DEFAULT_SETTINGS } from '../../src/shared/types.js';

function post(overrides: Partial<BooruPost> = {}): BooruPost {
  return {
    serverId: 'srv_1',
    siteType: 'e621',
    id: '1500000',
    postUrl: 'https://e621.net/posts/1500000',
    fileUrl: 'https://static1.e621.net/data/ab/cd/abcdef.png',
    previewUrl: 'https://static1.e621.net/data/preview/ab/cd/abcdef.jpg',
    sampleUrl: null,
    width: 1200,
    height: 1600,
    ext: 'png',
    sizeBytes: 480_000,
    rating: 'safe',
    rawRating: 's',
    tags: ['cat', 'solo', 'blue_sky', 'window', 'sunset', 'grass'],
    tagCategories: {
      general: ['cat', 'solo', 'blue_sky', 'window', 'sunset', 'grass'],
      artist: ['some_artist'],
      character: ['some_character'],
      copyright: ['original_series'],
    },
    artistTags: ['some_artist'],
    characterTags: ['some_character'],
    score: 42,
    md5: 'abcdef0123456789abcdef0123456789',
    sources: ['https://artist.example/post/1'],
    createdAt: '2024-03-05T10:00:00.000Z',
    isVideo: false,
    isAnimated: false,
    parentId: null,
    hasChildren: false,
    description: null,
    ...overrides,
  };
}

const server = { id: 'srv_1', label: 'e621 main', siteType: 'e621' };

function settings(overrides: Partial<typeof DEFAULT_SETTINGS> = {}) {
  return {
    folderTemplate: DEFAULT_SETTINGS.folderTemplate,
    filenameTemplate: DEFAULT_SETTINGS.filenameTemplate,
    maxTagsInFilename: DEFAULT_SETTINGS.maxTagsInFilename,
    tagSeparator: DEFAULT_SETTINGS.tagSeparator,
    ...overrides,
  };
}

describe('sanitization', () => {
  it('replaces characters that are illegal on Windows and Unix', () => {
    expect(sanitizePathSegment('a<b>c:d"e/f\\g|h?i*j')).toBe('a_b_c_d_e_f_g_h_i_j');
  });

  it('strips control characters and trailing dots/spaces', () => {
    expect(sanitizePathSegment('name\u0000with\u001fctrl...  ')).toBe('name_with_ctrl');
  });

  it('protects against path traversal segments', () => {
    expect(sanitizePathSegment('..')).toBe('unknown');
    expect(sanitizePathSegment('.')).toBe('unknown');
    expect(sanitizePathSegment('../etc/passwd')).toBe('_etc_passwd');
    expect(sanitizeFolderPath('../../etc/{siteType}')).toBe('etc/{siteType}');
    expect(sanitizeFolderPath('booru/../secrets')).toBe('booru/secrets');
    expect(sanitizeFolderPath('..\u002e/evil')).not.toContain('..');
  });

  it('escapes Windows reserved device names', () => {
    expect(sanitizePathSegment('con')).toBe('_con');
    expect(sanitizePathSegment('LPT1.txt')).toBe('_LPT1.txt');
  });

  it('collapses whitespace and limits length', () => {
    expect(sanitizePathSegment('  many    spaces  ')).toBe('many spaces');
    expect(sanitizePathSegment('x'.repeat(400)).length).toBeLessThanOrEqual(120);
  });

  it('falls back when a segment is empty', () => {
    expect(sanitizePathSegment('', 'fallback')).toBe('fallback');
    expect(sanitizeFolderPath('///')).toBe('');
  });
});

describe('templates', () => {
  it('exposes the documented token set', () => {
    const tokens = buildTokens({ post: post(), server, settings: settings() }, new Date('2024-05-05T00:00:00.000Z'));
    expect(tokens.id).toBe('1500000');
    expect(tokens.siteType).toBe('e621');
    expect(tokens.serverLabel).toBe('e621 main');
    expect(tokens.md5).toBe('abcdef0123456789abcdef0123456789');
    expect(tokens.ext).toBe('png');
    expect(tokens.artist).toBe('some_artist');
    expect(tokens.character).toBe('some_character');
    expect(tokens.date).toBe('2024-03-05');
    expect(tokens.year).toBe('2024');
    expect(tokens.source).toBe('artist.example');
    expect(tokens.tag1).toBe('cat');
    expect(tokens.tags).toBe('cat solo blue_sky window sunset');
    expect(tokens.tagCount).toBe('6');
  });

  it('supplies fallbacks for missing metadata', () => {
    const tokens = buildTokens(
      { post: post({ artistTags: [], characterTags: [], tagCategories: {}, tags: [], md5: null, sources: [] }), server, settings: settings() },
      new Date('2024-05-05T00:00:00.000Z'),
    );
    expect(tokens.artist).toBe('unknown_artist');
    expect(tokens.character).toBe('unknown_character');
    expect(tokens.md5).toBe('nomd5');
    expect(tokens.source).toBe('no_source');
    expect(tokens.tags).toBe('untagged');
  });

  it('renders templates and drops unknown tokens', () => {
    const tokens = { id: '7', ext: 'jpg' };
    expect(renderTemplate('{id}-{nope}.{ext}', tokens)).toBe('7-.jpg');
  });

  it('respects the tag separator', () => {
    const tokens = buildTokens({ post: post(), server, settings: settings({ tagSeparator: '_' }) });
    expect(tokens.tags).toBe('cat_solo_blue_sky_window_sunset');
  });
});

describe('buildDownloadPath', () => {
  it('builds the default path with the adapter-independent default template', () => {
    const path = buildDownloadPath({ post: post(), server, settings: settings() });
    expect(path.folder).toBe('booru/e621');
    expect(path.filename).toBe('1500000_abcdef0123456789abcdef0123456789.png');
    expect(path.fullPath).toBe('booru/e621/1500000_abcdef0123456789abcdef0123456789.png');
    expect(path.warnings).toEqual([]);
  });

  it('does not duplicate the extension when the template already adds it', () => {
    const path = buildDownloadPath({ post: post(), server, settings: settings({ filenameTemplate: '{id}.{ext}' }) });
    expect(path.filename).toBe('1500000.png');
  });

  it('appends the extension when the template omits it', () => {
    const path = buildDownloadPath({ post: post(), server, settings: settings({ filenameTemplate: '{artist}-{id}' }) });
    expect(path.filename).toBe('some_artist-1500000.png');
  });

  it('sanitizes template output into a safe relative path', () => {
    const path = buildDownloadPath({
      post: post(),
      server: { ...server, label: '../../evil' },
      settings: settings({ folderTemplate: '{serverLabel}/{siteType}', filenameTemplate: '{id}_{tags}' }),
    });
    expect(path.fullPath.startsWith('evil/e621/')).toBe(true);
    expect(path.fullPath).not.toContain('..');
    expect(path.fullPath).not.toContain('//');
  });

  it('truncates long filenames but keeps the extension', () => {
    const path = buildDownloadPath({
      post: post({
        tags: Array.from({ length: 40 }, (_v, index) => `very_long_tag_${index}`),
        tagCategories: { general: Array.from({ length: 40 }, (_v, index) => `very_long_tag_${index}`) },
      }),
      server,
      settings: settings({ filenameTemplate: '{id}_{tags}', maxTagsInFilename: 30, tagSeparator: '-' }),
    });
    expect(path.filename.length).toBeLessThanOrEqual(180);
    expect(path.filename.endsWith('.png')).toBe(true);
    expect(path.warnings.some((warning) => warning.toLowerCase().includes('shortened'))).toBe(true);
  });

  it('warns when the post has no extension information', () => {
    const path = buildDownloadPath({
      post: post({ ext: null, fileUrl: 'https://static1.e621.net/data/ab/cd/abcdef' }),
      server,
      settings: settings(),
    });
    expect(path.warnings.length).toBeGreaterThan(0);
    expect(path.filename).toBe('1500000_abcdef0123456789abcdef0123456789');
  });

  it('produces identical names for the same post across runs (dedupe friendly)', () => {
    const a = buildDownloadPath({ post: post(), server, settings: settings() });
    const b = buildDownloadPath({ post: post(), server, settings: settings() });
    expect(a.fullPath).toBe(b.fullPath);
  });
});
