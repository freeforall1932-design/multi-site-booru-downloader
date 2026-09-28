# The booru adapter contract

Every supported site is one implementation of `BooruAdapter`
(`src/core/adapter.ts`). The rest of the extension — UI, server manager,
validation, queue, naming, downloads — is site-agnostic: it asks the registry for
an adapter by `siteType` and only ever calls contract methods.

Consequences of that rule:

- Adding a booru-like site = **one new file + one registration line**.
- No `if (site === 'e621')` outside `src/adapters/`.
- A new adapter must pass `tests/adapters/contract.test.ts`, which is
  automatically parametrised over every registered adapter.

---

## 1. Types an adapter works with

```ts
interface AdapterContext {          // built by createAdapterContext()
  server: ServerConfig;             // full profile incl. credentials
  settings: ExtensionSettings;
  userAgent: string;                // effective UA (custom or generated)
  baseUrl: string;                  // normalized, no trailing slash
}

interface AdapterCapabilities {     // what the site can do — drives the UI
  supportsAnonymousAccess: boolean;
  supportsRatingFilter: boolean;
  requiresUsername: boolean;
  requiresUserId: boolean;
  requiresApiKey: boolean;
  requiresUserAgent: boolean;
  authStyle: 'basic' | 'query' | 'basic-or-query' | 'query-with-userid';
  supportsBasicAuthHeader: boolean;
  maxPostsPerRequest: number;       // e621 320, Danbooru 200, Gelbooru 100
  maxPage: number | null;           // e621 listing stops at page 750
  minRequestIntervalMs: number;     // site-documented minimum gap
  supportsPostLookup: boolean;
  supportsTagSearch: boolean;
  supportsIdPagination: boolean;    // e621 `b<id>` style paging
  apiDocsUrl: string;               // shown in Diagnostics
  notes: string[];                  // short, user-visible caveats
}

interface AdapterDefaults {
  baseUrl: string;
  label: string;
  ratings: Rating[];                // pre-selected allow-list when filtering is on
  userAgentHint: string;            // fallback descriptive UA
}

interface CredentialField {
  key: 'username' | 'apiKey' | 'userId' | 'customUserAgent';
  label: string;
  type: 'text' | 'password' | 'textarea';
  required: boolean;                // profile is unusable without it
  requiredForAuth: boolean;         // needed only when signing in
  secret: boolean;                  // never rendered/logged in the clear
  placeholder?: string;
  help?: string;
}
```

## 2. The contract

```ts
interface BooruAdapter {
  readonly siteType: SiteType;
  readonly displayName: string;
  readonly capabilities: AdapterCapabilities;
  readonly hostPatterns: string[];          // e.g. ['*.gelbooru.com']
  readonly defaults: AdapterDefaults;

  normalizeBaseUrl?(raw: string): string;   // optional clean-up of user input
  matchesHost(host: string): boolean;

  matchRoute(url: string): RouteMatch | null;          // pure; no server needed
  postUrl(baseUrl: string, postId: string): string;

  buildSearchRequest(ctx: AdapterContext, spec: SearchSpec): HttpRequestSpec;
  buildPostRequest(ctx: AdapterContext, postId: string): HttpRequestSpec;
  buildValidationProbes(ctx: AdapterContext): ValidationProbe[];

  parseSearchResponse(snapshot, spec, ctx): SearchResult;
  parsePostResponse(snapshot, postId, ctx): BooruPost;
  normalizePost(raw: unknown, ctx: AdapterContext): BooruPost;

  normalizeRating(raw: unknown): Rating;
  readonly siteRatingTokens: readonly string[];
  canonicalRatingFor(siteToken: string): Rating | null;
  ratingQueryTags(allowed: Rating[]): { tags: string[]; warnings: string[] };

  classifyFailure?(snapshot: HttpResponseSnapshot): AdapterFailure | null;
  credentialFields(server?: Partial<ServerConfig>): CredentialField[];
  suggestUserAgent(ctx: UserAgentHintContext): string;
  decorateRequest?(ctx: AdapterContext, spec: HttpRequestSpec): HttpRequestSpec;
}
```

### Invariants the conformance suite enforces

| Invariant | Why |
| --- | --- |
| `capabilities.authStyle` is one of the four documented values | The server form and diagnostics render it verbatim |
| `credentialFields().key` ⊆ `{username, apiKey, userId, customUserAgent}` | The profile schema is fixed by product policy |
| At least one probe has `purpose: 'endpoint'` | Endpoint mismatch must be diagnosable before credentials |
| Every auth probe is `enabled: false` when no credentials are saved | Anonymous read-only validation must work |
| Every entry of `siteRatingTokens` maps through `canonicalRatingFor` | Rating filters must be exact |
| `normalizeRating(unknownValue)` returns `'unknown'`, never throws | Parsers must survive new site tokens |
| `ratingQueryTags([])` excludes every canonical rating | Empty allow-list must never mean “everything” |
| `suggestUserAgent()` output is never browser-like (`Mozilla/…`) | Site policies forbid impersonating a browser |
| `matchRoute()` is pure and returns `null` for foreign URLs | The popup asks before any server is saved |
| Register/unregister of an adapter needs no shared-code change | The whole point of the contract |

## 3. Writing an adapter

```ts
// src/adapters/example.ts
import { classifyStatus, readJson, ratingExclusions, composeTags } from './base.js';
import type { BooruAdapter } from '../core/adapter.js';

export const exampleAdapter: BooruAdapter = {
  siteType: 'example',
  displayName: 'Example Booru',
  capabilities: {
    supportsAnonymousAccess: true,
    supportsRatingFilter: true,
    requiresUsername: false,
    requiresUserId: false,
    requiresApiKey: true,
    requiresUserAgent: true,
    authStyle: 'query',
    supportsBasicAuthHeader: false,
    maxPostsPerRequest: 100,
    maxPage: null,
    minRequestIntervalMs: 1000,
    supportsPostLookup: true,
    supportsTagSearch: true,
    supportsIdPagination: false,
    apiDocsUrl: 'https://example.invalid/api',
    notes: ['Reads are public; writes need an API key.'],
  },
  hostPatterns: ['example.invalid', '*.example.invalid'],
  defaults: {
    baseUrl: 'https://example.invalid',
    label: 'Example Booru',
    ratings: ['safe', 'general'],
    userAgentHint: 'BooruServerManager/0.1.0 (by example_user on example.invalid)',
  },

  matchesHost(host) {
    return host === 'example.invalid' || host.endsWith('.example.invalid');
  },

  matchRoute(url) {
    const parsed = new URL(url);
    const post = /^\/post\/(\d+)$/.exec(parsed.pathname);
    if (post) return { siteType: 'example', kind: 'post', postId: post[1]!, tags: null, page: null, canonicalUrl: parsed.toString() };
    return null;
  },

  postUrl(baseUrl, postId) {
    return `${baseUrl}/post/${postId}`;
  },

  buildSearchRequest(ctx, spec) {
    const params = new URLSearchParams({ limit: String(Math.min(spec.limit ?? 50, 100)) });
    const filter = spec.ratingFilter?.enabled ? ratingExclusions(this, spec.ratingFilter.allowed) : { tags: [], warnings: [] };
    params.set('tags', composeTags(spec.tags, filter.tags, ctx.settings.globalTagSuffix));
    if (ctx.server.apiKey) params.set('api_key', ctx.server.apiKey);
    return { url: `${ctx.baseUrl}/api/posts?${params}`, tag: 'example', label: 'Listing' };
  },

  buildPostRequest(ctx, postId) {
    return { url: `${ctx.baseUrl}/api/posts/${encodeURIComponent(postId)}`, tag: 'example', label: 'Post lookup' };
  },

  buildValidationProbes(ctx) {
    const hasCredentials = Boolean(ctx.server.apiKey);
    return [
      {
        id: 'example-endpoint',
        label: 'Listing endpoint (anonymous)',
        purpose: 'endpoint',
        request: { url: `${ctx.baseUrl}/api/posts?limit=1`, tag: 'example', label: 'Endpoint check' },
        interpret: (snapshot) => {
          const failure = classifyStatus(snapshot, 'Example Booru');
          if (failure) return { ok: false, kind: failure.kind, message: failure.message, warnings: failure.hint ? [failure.hint] : [] };
          return { ok: true, kind: 'ok', message: 'Listing endpoint responds with JSON' };
        },
      },
      {
        id: 'example-auth',
        label: 'Authenticated request',
        purpose: 'auth',
        enabled: hasCredentials,
        request: { url: `${ctx.baseUrl}/api/profile?api_key=${encodeURIComponent(ctx.server.apiKey)}`, tag: 'example', label: 'Credential check' },
        interpret: (snapshot) => { /* map 401 / 403 / payload → ProbeInterpretation */ },
      },
    ];
  },

  parseSearchResponse(snapshot, spec, ctx) { /* readJson → normalizePost[] → SearchResult */ },
  parsePostResponse(snapshot, postId, ctx) { /* readJson → normalizePost */ },
  normalizePost(raw, ctx) { /* → BooruPost, every field explicit */ },
  normalizeRating(raw) { /* site token → Rating | 'unknown' */ },
  siteRatingTokens: ['safe', 'questionable', 'explicit'],
  canonicalRatingFor(token) { /* → Rating | null */ },
  ratingQueryTags(allowed) { const { tags, warnings } = ratingExclusions(this, allowed); return { tags, warnings }; },

  credentialFields() {
    return [{ key: 'apiKey', label: 'API key', type: 'password', required: false, requiredForAuth: true, secret: true }];
  },

  suggestUserAgent(ctx) {
    return `BooruServerManager/0.1.0 (by ${ctx.server.username || 'anonymous'} on example.invalid)`;
  },
};
```

Then register it — the only change outside the new file:

```ts
// src/adapters/index.ts
export function registerBuiltinAdapters(): void {
  for (const adapter of [e621Adapter, danbooruAdapter, gelbooruAdapter, exampleAdapter]) {
    if (!getAdapter(adapter.siteType)) registerAdapter(adapter);
  }
}
```

Run `npm test` — the conformance suite picks the new adapter up automatically, and
the server editor renders its credential fields with no UI change.

## 4. Validation probes

Validation is a *pipeline*, not a single request:

1. **Preflight (no network)** — adapter registered? base URL parseable?
   credential pair complete (`apiKey` + `userId` for query-with-userid, etc.)?
   Produces `unsupported-site` / `incomplete-config`.
2. **Endpoint probe(s)** — proves the host speaks the expected API. An HTML body,
   a 404 or an unrelated JSON shape is an **endpoint mismatch**, never a credential
   error. Reads must work without credentials where the site allows it.
3. **Auth probe(s)** — only when credentials are stored, only `purpose: 'auth'`.
   `401`, or a site-specific “invalid API key / unknown user” payload, is
   `auth-failure`; `403` is `blocked` (UA policy, disabled API access, Cloudflare);
   `429`/`500`-style responses are `rate-limited`/`server-error`.
4. **Interpretation** — `interpret(snapshot)` returns
   `{ ok, kind, message, warnings?, account? }`, so the message the user sees is
   written by the adapter that knows the site.
5. **Result** — `ValidationResult { ok, kind, endpointOk, authOk, message,
   warnings, account, checkedAt, httpStatus, durationMs, trace }` is stored on the
   profile (`valid` / `invalid` / `unreachable` / `partial`) and displayed in the
   diagnostics tab.

Use `optional: true` for probe endpoints that only exist on some instances
(a 404/405 then means “not available here”, and validation moves to the next
candidate). The e621 adapter demonstrates this: it probes `/dmail.json` first and
falls back to `/favorites.json`.

`ValidationService` is the only caller; adapters never decide *when* to validate.

## 5. Failure taxonomy

`FailureKind` (`src/shared/types.ts`) is shared by the HTTP layer, adapters,
validation, the queue and the UI:

| Kind | Meaning | Typical cause |
| --- | --- | --- |
| `auth-failure` | Credentials rejected | Wrong/expired key, key for another account |
| `endpoint-mismatch` | Reached something that is not this API | Base URL wrong, HTML page returned, fork with a different API |
| `blocked` | Request denied without being an auth error | Missing/blocked User-Agent, disabled API access, Cloudflare |
| `rate-limited` | Throttled | 429, e621 503 burst limit |
| `server-error` | 5xx that is not throttling | Site outage |
| `network-failure` | No usable response | Offline, DNS, TLS, timeout |
| `unsupported-site` | No adapter for the configured site type | Typo in a profile, unregistered fork |
| `incomplete-config` | Missing fields / policy refusal | Gelbooru without user id, rating enforcement |
| `parse-failure` | Response was not the documented shape | Fork drifted from the documented JSON |

Adapters should implement `classifyFailure(snapshot)` only for *extra*
site-specific phrasing (for example Gelbooru returning a credential error with
HTTP 200); generic HTTP mapping is available through `classifyStatus()` and
`detectAuthWordsInBody()` from `base.ts`.

## 6. Ratings

Canonical ratings: `safe`, `general`, `sensitive`, `questionable`, `explicit`,
plus `unknown` for anything a site reports that we do not recognise.

- `normalizeRating(raw)` maps a site token to a canonical rating (`unknown` if the
  site invents a new one — never throw).
- `canonicalRatingFor(token)` is the inverse for the site's own token table.
- `ratingQueryTags(allowed)` returns the **exclusion form** (`-rating:explicit`),
  which all three target sites understand and which stays correct when a site's
  OR-syntax differs. Emit a warning when a site cannot express a filter exactly.

The allow-list lives on the profile (`ServerConfig.allowedRatings`); the shared
layer decides *whether* to filter, the adapter decides *how*.

## 7. User-Agent

- `capabilities.requiresUserAgent` marks policies that mandate a descriptive UA.
- `suggestUserAgent(ctx)` builds `BooruServerManager/<version> (by <account> on
  <host>)` from *non-secret* context (`username`, `userId`, `baseUrl`) — never from
  an API key.
- `decorateRequest(ctx, spec)` is for browser limitations: e621 documents
  `_client=<ua>` for clients that cannot set the header, which the extension uses as
  a fallback beside `declarativeNetRequest` rewriting.

## 8. Gelbooru-family forks

`src/adapters/gelbooru.ts` exports `createGelbooruLikeAdapter({ siteType,
displayName, baseUrl, hostPatterns, apiDocsUrl, notes? })` for DAPI-compatible
instances. Register it the same way as a first-class adapter; it inherits
Gelbooru's query-with-userid auth, `@attributes` envelope parsing and credential
error detection.

## 9. Checklist for a new adapter

- [ ] Capabilities match the site's documented limits (rate, page size, pagination).
- [ ] `matchRoute` handles post, tag/search and index URLs, and returns `null` for
      anything else (including look-alike hosts).
- [ ] `parseSearchResponse` / `parsePostResponse` never throw on unexpected fields;
      they degrade to `null`/`unknown` and rely on `shapeError()` only when the
      response cannot be the documented shape at all.
- [ ] `normalizePost` fills **every** `BooruPost` field, using the canonical rating.
- [ ] At least one endpoint probe and one auth probe, with `enabled` flags.
- [ ] `credentialFields` marks secrets and explains what is required when.
- [ ] `suggestUserAgent` never returns a browser-looking string.
- [ ] Tests: response fixtures for happy path, empty result, HTML body, 401, 429,
      malformed JSON, plus the conformance suite.
- [ ] `docs/ARCHITECTURE.md` supported-site notes and README table updated.
