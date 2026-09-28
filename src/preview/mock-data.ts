/**
 * Deterministic synthetic booru dataset for the browser preview and integration
 * tests. Media URLs are relative SVG placeholders served by the preview server,
 * so nothing here touches the real sites.
 */
import type { Rating } from '../shared/types.js';

export interface MockPostSeed {
  id: number;
  rating: Rating;
  tags: string[];
  artist: string;
  character: string;
  copyright: string;
  width: number;
  height: number;
  ext: string;
  score: number;
  daysAgo: number;
}

const GENERAL_TAGS = [
  'alternate_color',
  'blue_sky',
  'cityscape',
  'clouds',
  'digital_art',
  'forest',
  'gradient_background',
  'landscape',
  'night',
  'portrait',
  'simple_background',
  'sunset',
  'water',
  'window',
];

const ARTISTS = ['aurelia', 'birchwood', 'cobalt', 'delta_ray', 'ember', 'foxglove'];
const CHARACTERS = ['alba', 'cinder', 'harbor', 'juniper', 'kite', 'marlow'];
const COPYRIGHTS = ['original_series', 'skyline_project', 'tidewater'];

const RATING_CYCLE: Rating[] = ['general', 'general', 'safe', 'sensitive', 'questionable', 'general', 'explicit', 'safe'];

/** 24 deterministic posts - enough to exercise paging, filtering and the queue. */
export const MOCK_POSTS: MockPostSeed[] = Array.from({ length: 24 }, (_value, index) => {
  const rating = RATING_CYCLE[index % RATING_CYCLE.length] ?? 'general';
  const general = [
    GENERAL_TAGS[index % GENERAL_TAGS.length]!,
    GENERAL_TAGS[(index * 3 + 5) % GENERAL_TAGS.length]!,
    index % 4 === 0 ? 'abstract' : 'detailed',
  ];
  return {
    id: 1_500_000 + index * 37,
    rating,
    tags: [rating === 'explicit' ? 'explicit_theme' : 'safe_theme', ...general],
    artist: ARTISTS[index % ARTISTS.length]!,
    character: CHARACTERS[(index * 5) % CHARACTERS.length]!,
    copyright: COPYRIGHTS[(index * 2) % COPYRIGHTS.length]!,
    width: 900 + (index % 5) * 120,
    height: 1200 + (index % 3) * 150,
    ext: index % 7 === 0 ? 'png' : 'jpg',
    score: 12 + ((index * 17) % 180),
    daysAgo: index,
  };
});

export function mockMediaPath(id: number, ext: string): string {
  return `/mock/media/${id}.${ext === 'png' ? 'png' : 'jpg'}`;
}

export const MOCK_CREDENTIALS = {
  e621: { username: 'demo_e621', apiKey: 'e621-demo-key' },
  danbooru: { username: 'demo_danbooru', apiKey: 'danbooru-demo-key' },
  gelbooru: { userId: '4242', apiKey: 'gelbooru-demo-key' },
} as const;

/** Any key starting with `demo` is accepted, which keeps the preview easy to poke at. */
export function credentialsLookValid(siteType: 'e621' | 'danbooru' | 'gelbooru', provided: { username?: string; apiKey?: string; userId?: string }): boolean {
  const apiKey = provided.apiKey ?? '';
  if (!apiKey) return false;
  if (apiKey.startsWith('demo') || apiKey.includes('-demo-')) return true;
  const expected = MOCK_CREDENTIALS[siteType];
  if ('username' in expected && provided.username) return provided.username === expected.username && apiKey === expected.apiKey;
  if ('userId' in expected) return provided.userId === expected.userId && apiKey === expected.apiKey;
  return false;
}
