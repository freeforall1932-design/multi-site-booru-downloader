/**
 * Well-known Gelbooru-compatible forks, registered through the same factory
 * that builds gelbooru.com itself. Each entry is *only* metadata - the wire
 * format is the DAPI (`/index.php?page=dapi&s=post&q=index&json=1`).
 *
 * Most of these run the older "Gelbooru 0.1.11" code base, which answers with a
 * bare post array and `hash` instead of `md5`; `createGelbooruLikeAdapter`
 * accepts both shapes.
 *
 * The list mirrors what Droidbooru ships as `ServerItemTypeGelbooru111`
 * (see reference/README.md) minus dead hosts.
 */
import type { BooruAdapter } from '../core/adapter.js';
import { createGelbooruLikeAdapter } from './gelbooru.js';

export const rule34Adapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'rule34',
  displayName: 'Rule34.xxx',
  baseUrl: 'https://rule34.xxx',
  label: 'Rule34.xxx',
  hostPatterns: ['rule34.xxx', 'www.rule34.xxx', 'api.rule34.xxx'],
  apiDocsUrl: 'https://api.rule34.xxx/',
  requiresCredentials: true,
  apiKeyPlaceholder: 'from My Account > Options > API Access Credentials',
  notes: [
    'Since 2024 rule34.xxx requires api_key + user_id on every API request; anonymous reads answer "Missing authentication".',
    'The API is served from api.rule34.xxx; the site redirects there automatically.',
  ],
});

export const safebooruOrgAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'safebooru-org',
  displayName: 'Safebooru.org',
  baseUrl: 'https://safebooru.org',
  label: 'Safebooru.org',
  hostPatterns: ['safebooru.org', 'www.safebooru.org'],
  apiDocsUrl: 'https://safebooru.org/index.php?page=help&topic=dapi',
  notes: ['Gelbooru 0.1.11 fork: bare-array JSON, `hash` field instead of `md5`. Not the same site as safebooru.donmai.us.'],
});

export const xbooruAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'xbooru',
  displayName: 'Xbooru',
  baseUrl: 'https://xbooru.com',
  label: 'Xbooru',
  hostPatterns: ['xbooru.com', 'www.xbooru.com'],
  apiDocsUrl: 'https://xbooru.com/index.php?page=help&topic=dapi',
  notes: ['Gelbooru 0.1.11 fork.'],
});

export const tbibAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'tbib',
  displayName: 'The Big ImageBoard (TBIB)',
  baseUrl: 'https://tbib.org',
  label: 'TBIB',
  hostPatterns: ['tbib.org', 'www.tbib.org'],
  apiDocsUrl: 'https://tbib.org/index.php?page=help&topic=dapi',
  notes: ['Gelbooru 0.1.11 fork; aggregates several other boorus.'],
});

export const hypnohubAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'hypnohub',
  displayName: 'Hypnohub',
  baseUrl: 'https://hypnohub.net',
  label: 'Hypnohub',
  hostPatterns: ['hypnohub.net', 'www.hypnohub.net'],
  apiDocsUrl: 'https://hypnohub.net/index.php?page=help&topic=dapi',
  notes: ['Gelbooru 0.1.11 fork.'],
});

export const realbooruAdapter: BooruAdapter = createGelbooruLikeAdapter({
  siteType: 'realbooru',
  displayName: 'Realbooru',
  baseUrl: 'https://realbooru.com',
  label: 'Realbooru',
  hostPatterns: ['realbooru.com', 'www.realbooru.com'],
  apiDocsUrl: 'https://realbooru.com/index.php?page=help&topic=dapi',
  notes: ['Gelbooru 0.1.11 fork.'],
});

export const GELBOORU_FORK_ADAPTERS: BooruAdapter[] = [
  rule34Adapter,
  safebooruOrgAdapter,
  xbooruAdapter,
  tbibAdapter,
  hypnohubAdapter,
  realbooruAdapter,
];
