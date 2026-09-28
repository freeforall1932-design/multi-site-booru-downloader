import { DEFAULT_MIRROR_FOLDER, type BooruPost, type ExtensionSettings, type ServerConfig } from '../shared/types.js';
import { fileExtensionFromUrl, safeUrl } from '../shared/util.js';
import { extensionFromUrl, fileNameFromUrl } from './links.js';

const MAX_SEGMENT_LENGTH = 120;
const MAX_FILENAME_LENGTH = 180;
const ILLEGAL_CHARS = /[<>:"/\\|?*\u0000-\u001f]/g;
const WINDOWS_RESERVED = /^(con|prn|aux|nul|com[1-9]|lpt[1-9])(\..*)?$/i;

export type TemplateTokens = Record<string, string>;

export interface NamingInput {
  post: BooruPost;
  server: Pick<ServerConfig, 'id' | 'label' | 'siteType'>;
  settings: Pick<ExtensionSettings, 'folderTemplate' | 'filenameTemplate' | 'maxTagsInFilename' | 'tagSeparator'>;
}

export interface DownloadPath {
  /** Directory relative to the browser's download root (may be ''). */
  folder: string;
  /** Filename including extension. */
  filename: string;
  /** folder + '/' + filename, ready for chrome.downloads. */
  fullPath: string;
  warnings: string[];
}

/** Replace characters that are illegal in file names on any supported OS. */
export function sanitizePathSegment(segment: string, fallback = 'unknown'): string {
  let value = (segment ?? '')
    .replace(ILLEGAL_CHARS, '_')
    .replace(/\s+/g, ' ')
    .trim()
    // Windows refuses trailing dots/spaces.
    .replace(/[. ]+$/, '')
    .replace(/^\.+/, '');
  if (!value || value === '.' || value === '..') return fallback;
  if (WINDOWS_RESERVED.test(value)) value = `_${value}`;
  if (value.length > MAX_SEGMENT_LENGTH) value = value.slice(0, MAX_SEGMENT_LENGTH).trim();
  return value || fallback;
}

/** Sanitize a whole relative folder template (`booru/{siteType}/…`). */
export function sanitizeFolderPath(folder: string): string {
  return folder
    .split('/')
    .map((segment) => segment.trim())
    .filter((segment) => segment && segment !== '.' && segment !== '..')
    .map((segment) => sanitizePathSegment(segment, ''))
    .filter(Boolean)
    .join('/');
}

export function buildTokens(input: NamingInput, now = new Date()): TemplateTokens {
  const { post, server, settings } = input;
  const ext = post.ext ?? fileExtensionFromUrl(post.fileUrl) ?? 'jpg';
  const createdAt = post.createdAt ? new Date(post.createdAt) : null;
  const date = createdAt && !Number.isNaN(createdAt.getTime()) ? createdAt.toISOString().slice(0, 10) : now.toISOString().slice(0, 10);
  const separator = settings.tagSeparator ?? ' ';
  const maxTags = Math.max(1, settings.maxTagsInFilename ?? 5);
  const generalTags = (post.tagCategories.general ?? post.tags).slice(0, maxTags);
  const firstSource = post.sources[0];
  const sourceHost = firstSource ? safeUrl(firstSource)?.host ?? '' : '';

  const tokens: TemplateTokens = {
    id: post.id || 'unknown',
    siteType: post.siteType,
    server: server.id,
    serverLabel: server.label,
    rating: post.rating === 'unknown' ? 'unrated' : post.rating,
    score: post.score !== null ? String(post.score) : '0',
    md5: post.md5 ?? 'nomd5',
    ext,
    filename: post.fileUrl ? (safeUrl(post.fileUrl)?.pathname.split('/').pop() ?? '') : '',
    date,
    year: date.slice(0, 4),
    month: date.slice(5, 7),
    artist: post.artistTags[0] ?? 'unknown_artist',
    artists: post.artistTags.slice(0, maxTags).join(separator) || 'unknown_artist',
    character: post.characterTags[0] ?? 'unknown_character',
    copyright: (post.tagCategories.copyright ?? [])[0] ?? 'unknown_series',
    tags: generalTags.join(separator) || 'untagged',
    tagCount: String(post.tags.length),
    width: post.width !== null ? String(post.width) : '0',
    height: post.height !== null ? String(post.height) : '0',
    source: sourceHost || 'no_source',
    isVideo: post.isVideo ? 'video' : 'image',
  };
  for (let index = 1; index <= 9; index += 1) {
    tokens[`tag${index}`] = generalTags[index - 1] ?? '';
  }
  return tokens;
}

/** Expand `{token}` placeholders. Unknown tokens collapse to an empty string. */
export function renderTemplate(template: string, tokens: TemplateTokens): string {
  return template.replace(/\{([a-zA-Z0-9_]+)\}/g, (_match, token: string) => tokens[token] ?? '');
}

/** Does the rendered filename already end with the post's extension? */
function hasExtension(filename: string, ext: string): boolean {
  return new RegExp(`\\.${ext.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')}$`, 'i').test(filename);
}

export function buildDownloadPath(input: NamingInput, now = new Date()): DownloadPath {
  const { post, settings } = input;
  const tokens = buildTokens(input, now);
  const warnings: string[] = [];

  const folder = sanitizeFolderPath(renderTemplate(settings.folderTemplate ?? 'booru/{siteType}', tokens));
  const renderedName = renderTemplate(settings.filenameTemplate ?? '{id}_{md5}', tokens);
  let filename = sanitizePathSegment(renderedName, post.id || 'download');

  const ext = (post.ext ?? fileExtensionFromUrl(post.fileUrl) ?? '').toLowerCase();
  if (ext && !hasExtension(filename, ext)) filename = `${filename}.${ext}`;
  if (!ext) warnings.push('The post did not report a file extension; filename may need adjusting.');

  const renderedLength = renderedName.length;
  if (renderedLength > MAX_FILENAME_LENGTH) {
    const keep = MAX_FILENAME_LENGTH - (ext ? ext.length + 1 : 0);
    filename = `${filename.slice(0, Math.max(1, keep)).trim()}.${ext}`;
  }
  if (renderedLength > filename.length || renderedName !== filename.replace(new RegExp(`\\.${ext}$`), '')) {
    warnings.push('Filename was shortened (illegal characters or length) to keep the download path portable.');
  }

  return {
    folder,
    filename,
    fullPath: folder ? `${folder}/${filename}` : filename,
    warnings,
  };
}

// ------------------------------------------------------------- mirror links

export interface MirrorPathInput {
  url: string;
  /** Provider label resolved by the link rules (`Mega`, `Google Drive`, …). */
  provider?: string | null;
  /** Profile the link was collected with (provenance only). */
  siteType?: string | null;
  settings: Pick<ExtensionSettings, 'mirrorFolderTemplate'>;
  /** Existing filename recorded on the link row, when one was already used. */
  filename?: string | null;
  now?: Date;
}

/**
 * Where a collected mirror link is saved.
 *
 * Mirror files are not booru posts: there is no post metadata, no md5 and often
 * no filename at all (a Drive *folder* link, for instance). The template only
 * gets tokens that exist for a URL - `{host}`, `{provider}`, `{siteType}`,
 * `{filename}`, `{ext}`, `{date}` - and the URL's own last path segment is used
 * as the filename, falling back to the provider name. Everything goes through
 * the same sanitiser as post filenames, so traversal and reserved names stay
 * impossible.
 */
export function buildMirrorPath(input: MirrorPathInput): DownloadPath {
  const { url, settings } = input;
  const now = input.now ?? new Date();
  const parsed = safeUrl(url);
  const host = parsed?.hostname ?? 'unknown-host';
  const ext = extensionFromUrl(url);
  const baseName = input.filename?.trim() || fileNameFromUrl(url) || `${input.provider ?? host}`;
  const withoutExt = ext ? baseName.replace(new RegExp(`\\.${ext}$`, 'i'), '') : baseName;
  const warnings: string[] = [];

  const tokens: TemplateTokens = {
    host,
    provider: input.provider ?? host,
    siteType: input.siteType ?? 'link',
    filename: baseName,
    ext: ext ?? '',
    date: now.toISOString().slice(0, 10),
  };

  const template = settings.mirrorFolderTemplate || DEFAULT_MIRROR_FOLDER;
  const folder = sanitizeFolderPath(renderTemplate(template, tokens));
  let filename = sanitizePathSegment(withoutExt, 'download');
  if (ext && !hasExtension(filename, ext)) filename = `${filename}.${ext}`;
  if (!ext) warnings.push('The link did not show a file extension; the saved name may need adjusting.');

  if (baseName.length > MAX_FILENAME_LENGTH) {
    const keep = MAX_FILENAME_LENGTH - (ext ? ext.length + 1 : 0);
    filename = `${sanitizePathSegment(withoutExt.slice(0, Math.max(1, keep)), 'download')}${ext ? `.${ext}` : ''}`;
    warnings.push('Filename was shortened (illegal characters or length) to keep the download path portable.');
  }

  return {
    folder,
    filename,
    fullPath: folder ? `${folder}/${filename}` : filename,
    warnings,
  };
}
