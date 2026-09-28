/**
 * Filename / folder template helpers.
 *
 * The download engine consumes a placeholder string such as `{id}_{md5}`. Typing
 * those by hand is slow and error-prone, so the settings screen offers one
 * checkbox per token (the same idea as the NHentai Downloader's name-template
 * checkboxes) and rebuilds the string from what is ticked. The stored format is
 * unchanged, so old templates keep working.
 *
 * A template that the checkboxes cannot represent (literal words, unusual
 * ordering) is treated as *custom*: the editor then shows the raw input with the
 * current value so nothing is silently lost.
 */
import type { BooruPost, ExtensionSettings, ServerConfig } from '../shared/types.js';
import { buildDownloadPath, type DownloadPath } from './naming.js';

export interface TemplateTokenInfo {
  token: string;
  label: string;
  /** Short hint shown as the checkbox title. */
  hint: string;
  /** Tokens that expand to several values (`{tags}`) are off by default. */
  bulky?: boolean;
}

/** Token catalog, in the order the checkbox list shows them. */
export const TEMPLATE_TOKENS: TemplateTokenInfo[] = [
  { token: 'id', label: 'Post id', hint: 'The site post number, e.g. 1500000 - the safest unique part.' },
  { token: 'md5', label: 'MD5 / hash', hint: 'Content hash when the site reports one; empty otherwise.' },
  { token: 'siteType', label: 'Site type', hint: 'e621 / danbooru / gelbooru - useful when servers are mixed.' },
  { token: 'serverLabel', label: 'Server label', hint: 'The name you gave this server profile.' },
  { token: 'server', label: 'Server id', hint: 'Internal profile id (stable across renames).' },
  { token: 'rating', label: 'Rating', hint: 'Canonical rating: safe, general, sensitive, questionable, explicit.' },
  { token: 'score', label: 'Score', hint: 'Favourites score when the site reports it.' },
  { token: 'artist', label: 'First artist', hint: 'First artist tag.' },
  { token: 'artists', label: 'All artists', hint: 'Every artist tag joined by the tag separator.' },
  { token: 'character', label: 'First character', hint: 'First character tag.' },
  { token: 'copyright', label: 'Copyright', hint: 'First copyright / series tag.' },
  { token: 'tags', label: 'Tag list', hint: 'General tags, capped by "Tags in filename".', bulky: true },
  { token: 'tagCount', label: 'Tag count', hint: 'How many tags the post carries.' },
  { token: 'date', label: 'Date', hint: 'Post date (YYYY-MM-DD), falls back to today.' },
  { token: 'year', label: 'Year', hint: 'Four-digit year of the post date.' },
  { token: 'month', label: 'Month', hint: 'Two-digit month of the post date.' },
  { token: 'width', label: 'Width', hint: 'Pixel width when known.' },
  { token: 'height', label: 'Height', hint: 'Pixel height when known.' },
  { token: 'ext', label: 'Extension', hint: 'File extension without the dot (jpg, png, webp, mp4...).' },
  { token: 'filename', label: 'Original filename', hint: 'The site\'s own file name when it exposes one.' },
  { token: 'isVideo', label: 'Video marker', hint: 'Adds "video" for animated/video posts, nothing otherwise.' },
  { token: 'source', label: 'First source', hint: 'First source URL reported by the site (sanitised).' },
];

export const TEMPLATE_TOKEN_NAMES = TEMPLATE_TOKENS.map((entry) => entry.token);

/** Separator the checkbox editor uses when it composes a template. */
export const TEMPLATE_SEPARATOR = '_';

/** Which tokens the stored template uses (order-insensitive detection). */
export function templateTokensInUse(template: string): Record<string, boolean> {
  const text = String(template ?? '');
  const result: Record<string, boolean> = {};
  for (const token of TEMPLATE_TOKEN_NAMES) {
    result[token] = text.includes(`{${token}}`);
  }
  return result;
}

/** True when nothing but known tokens and simple separators are present. */
export function isTokenOnlyTemplate(template: string): boolean {
  const stripped = String(template ?? '').replace(/\{[a-zA-Z0-9]+\}/g, '');
  return /^[\s\-_,.()[\]]*$/.test(stripped);
}

/** Rebuild a template from the checked tokens, in catalog order. */
export function buildTemplate(tokens: Record<string, boolean>, separator = TEMPLATE_SEPARATOR): string {
  return TEMPLATE_TOKEN_NAMES.filter((token) => tokens[token])
    .map((token) => `{${token}}`)
    .join(separator);
}

/**
 * True when the stored template is exactly what the checkboxes would produce
 * for the tokens it contains: canonical order *and* canonical separator. An
 * empty template counts.
 */
export function isCanonicalTemplate(template: string, separator = TEMPLATE_SEPARATOR): string | null {
  const text = String(template ?? '').trim();
  if (!text) return '';
  if (!isTokenOnlyTemplate(text)) return null;
  const inUse = templateTokensInUse(text);
  return buildTemplate(inUse, separator) === text ? text : null;
}

/** Sample post used for the live template preview (no network, no real site). */
export function samplePost(overrides: Partial<BooruPost> = {}): BooruPost {
  return {
    serverId: 'sample-server',
    siteType: 'e621',
    id: '1500000',
    postUrl: 'https://e621.net/posts/1500000',
    fileUrl: 'https://static1.e621.net/data/4f/2a/sample_image.jpg',
    previewUrl: 'https://static1.e621.net/data/preview/4f/2a/sample_image.jpg',
    sampleUrl: 'https://static1.e621.net/data/sample/4f/2a/sample_image.jpg',
    width: 1280,
    height: 1920,
    ext: 'jpg',
    sizeBytes: 524288,
    rating: 'safe',
    rawRating: 's',
    tags: ['cat', 'solo', 'artist_name', 'character_name', 'copyright_name'],
    tagCategories: {
      general: ['cat', 'solo'],
      artist: ['artist_name'],
      character: ['character_name'],
      copyright: ['copyright_name'],
    },
    artistTags: ['artist_name'],
    characterTags: ['character_name'],
    score: 42,
    md5: '4f2a9c1d0b3e5f7a8c9d0e1f2a3b4c5d',
    sources: ['https://example.org/art/1234'],
    createdAt: '2024-05-06T07:08:09.000Z',
    isVideo: false,
    isAnimated: false,
    parentId: null,
    hasChildren: false,
    description: null,
    ...overrides,
  };
}

export interface PreviewNamingInput {
  settings: Pick<ExtensionSettings, 'folderTemplate' | 'filenameTemplate' | 'maxTagsInFilename' | 'tagSeparator'>;
  post?: BooruPost;
  server?: Pick<ServerConfig, 'id' | 'label' | 'siteType'>;
}

/** Render the current templates against the sample post (or a real one). */
export function previewNaming(input: PreviewNamingInput): DownloadPath {
  return buildDownloadPath({
    post: input.post ?? samplePost(),
    server: input.server ?? { id: 'sample-server', label: 'e621 (demo account)', siteType: 'e621' },
    settings: input.settings,
  });
}
