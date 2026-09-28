# Architecture

A Manifest V3 extension with strictly layered code. The rule that shapes
everything else: **the UI and the shared services never branch on site type** —
site-specific knowledge lives inside adapters, and every surface talks to the
shared booru services through one message protocol.

```
┌──────────────────────────── UI surfaces ────────────────────────────┐
│ options page (#servers #editor #browse #queue #settings #diagnostics)│
│ toolbar popup          ·  content script button (post pages)         │
└───────────────────────────────┬─────────────────────────────────────┘
                                │ UiRequest / RouterResponse  (message protocol)
┌───────────────────────────────▼─────────────────────────────────────┐
│                        core/router.ts                               │
│  one closure: translate UI intent → services; sanitise every reply  │
└──┬──────────┬───────────┬───────────┬───────────┬───────────┬───────┘
   │          │           │           │           │           │
┌──▼───┐ ┌────▼────┐ ┌────▼────┐ ┌────▼────┐ ┌────▼─────┐ ┌───▼──────┐
│server│ │settings │ │ client  │ │  queue  │ │validation│ │ naming / │
│store │ │ store   │ │(resolve │ │(download│ │ service  │ │downloads │
│      │ │         │ │ posts)  │ │ + rate) │ │          │ │          │
└──┬───┘ └────┬────┘ └────┬────┘ └────┬────┘ └────┬─────┘ └───┬──────┘
   │          │           │           │           │           │
┌──▼──────────▼───────────▼───────────▼───────────▼───────────▼───────┐
│  shared: http client (rate limiter, retries, redaction), errors,     │
│          domain types, storage abstraction, registry                 │
└───────────────────────────────┬─────────────────────────────────────┘
                                │ BooruAdapter contract
┌───────────────────────────────▼─────────────────────────────────────┐
│              adapters: e621 · danbooru · gelbooru (+ forks)          │
└─────────────────────────────────────────────────────────────────────┘
```

## Layers

### 1. `src/shared/` — no knowledge of any concrete site

| File | Responsibility |
| --- | --- |
| `types.ts` | Domain model: `ServerConfig`, `ServerConfigView`, `BooruPost`, `SearchSpec/Result`, `Rating`, `QueueItem`, `ExtensionSettings`, `FailureKind`, `ValidationResult`, `ProbeTraceEntry` |
| `errors.ts` | `BooruError` (kind + status + hint + retryable) and `FAILURE_LABELS` |
| `http.ts` | `HttpClient` (timeouts, retry with backoff, per-key `RateLimiter`), `HttpRequestSpec`, `HttpResponseSnapshot`, `parseJsonBody`, `describeRequest` |
| `util.ts` | ids, deep clone/deep merge, `sleep`, `redactUrl`, `redactHeaders`, host/path helpers, amount formatting |

`HttpClient` is the only place that performs network I/O, and it is the only place
that can leak a credential: every snapshot it produces carries a `redactedUrl`, and
its event stream is redacted before it is emitted.

### 2. `src/core/` — shared services

| File | Responsibility |
| --- | --- |
| `adapter.ts` | The `BooruAdapter` contract and shared adapter helpers (`suggestUserAgent`) |
| `registry.ts` | Site-type → adapter registry, `detectRoute`, `detectAdapterForUrl`, `effectiveUserAgent`, `buildDefaultRatings`, `createAdapterContext` |
| `registry` + `adapters/index.ts` | `registerBuiltinAdapters()` is the single registration point |
| `storage.ts` | `StorageArea` interface + Chrome/Memory/Web implementations + `STORAGE_KEYS`, schema version, `migrateStorage` |
| `servers.ts` | `ServerStore`: CRUD, default handling, duplicate (secrets copied, validation reset), credential clearing, masking (`toServerView`), JSON export/import, validation bookkeeping |
| `settings.ts` | `SettingsStore`: defaults, partial saves, reset, migration-safe merge |
| `validation.ts` | `ValidationService`: runs an adapter's ordered probes, classifies results, produces a `ValidationResult` with a redacted trace |
| `client.ts` | `BooruClient`: server/adapter resolution, search, post lookup, URL → post resolution, rating-filter decisions |
| `queue.ts` | `DownloadQueue`: persistence, de-duplication, concurrency, pause/resume/retry/cancel/clear, per-item failure recording |
| `naming.ts` | Template tokens, `renderTemplate`, `sanitizePathSegment`, `sanitizeFolderPath`, `buildDownloadPath` (traversal + length safety, warnings) |
| `downloads.ts` | `Downloader` interface, `ChromeDownloader`, `RecordingDownloader` |
| `userAgent.ts` | Builds and syncs `declarativeNetRequest` dynamic User-Agent rules from the saved profiles |
| `router.ts`, `messages.ts` | The message protocol and the single request handler |

### 3. `src/adapters/` — one file per site

`base.ts` holds helpers shared by adapters (status classification, HTML detection,
auth-body word detection, UTF-8-safe base64, tag splitting, probe interpreters).
Each adapter implements the contract from
[docs/ADAPTER_CONTRACT.md](ADAPTER_CONTRACT.md): capabilities, host patterns,
route matching, request building, response parsing, rating mapping, validation
probes, credential fields and User-Agent suggestion.

`gelbooru.ts` additionally exports `createGelbooruLikeAdapter()` so Gelbooru-family
forks (rule34-style DAPI instances) can be registered with a few lines and no
changes to any other layer.

### 4. `src/platform/` — environment abstraction

`Platform` gathers everything that differs between the real extension and the
browser preview: storage area, downloader, message transport, User-Agent rule sync,
active tab URL, options opening, host-permission checks and an environment
description for the diagnostics tab.

- `ExtensionPlatform` — `chrome.storage.local`, `chrome.downloads`,
  `chrome.runtime.sendMessage`, `declarativeNetRequest`, `chrome.tabs`,
  `chrome.permissions`.
- `PreviewPlatform` — shared `localStorage` namespace, mock-media downloads,
  in-process router, no-op User-Agent sync.
- `index.ts` — lazy façade plus `isExtensionEnvironment()` and a headless fallback.

The UI captures `getPlatform()` at module scope; the façade delegates per call, so
the preview can install its platform *after* the UI modules were evaluated.

### 5. UI, popup, content script

`src/ui/` contains pure view builders (`server-list`, `server-form`, `browse`,
`queue-view`, `settings-view`, `diagnostics-view`) plus `dom.ts` (element helper,
no `innerHTML` for dynamic content), `format.ts` and `toast.ts`. The options shell
owns application state and hash routing; the popup reuses the same views for its
compact layout; the content script is a shadow-DOM button that sends one
`posts/download` request.

## Message protocol

Everything the UI can ask for is a `UiRequest` (`src/core/messages.ts`); the answer
is always `{ ok: true, data }` or `{ ok: false, error: { message, kind, hint } }`.

```
servers/  list · save · remove · duplicate · setDefault · validate · validateDraft
          clearCredentials · export · import
browse/   search
posts/    get · resolveUrl · download
routes/   detect
queue/    list · enqueue · enqueuePosts · run · pause · resume · retryFailed
          clear · cancel
settings/ get · save · reset
diagnostics/info · userAgent/sync
```

Rules enforced by the router:

- No response containing a secret: servers cross the boundary as
  `ServerConfigView` (`apiKeyMask` + `hasApiKey` only).
- `queue/run` and `queue/resume` are fire-and-forget; the UI polls
  `queue/list` (1.5 s in the options page) so a long download never blocks a
  message channel.
- Failures are converted into the shared taxonomy (`FailureKind`) plus a
  next-step `hint`, so the UI can separate *authentication failure* from
  *endpoint mismatch* from *rate limiting* from *network failure*.
- `downloads/post` re-checks the rating filter before saving a file.

## Request flows

**Validation** (`servers/validate`, `servers/validateDraft`)

```
UI → router → ValidationService
        ├─ preflight: adapter registered? base URL valid? credential pair complete?
        ├─ probe 1: anonymous endpoint (does this host speak the API?)
        │            HTML body → endpoint-mismatch, 404 → endpoint-mismatch, …
        ├─ probe 2..n: authenticated request(s) (only if credentials are present)
        │            401 → auth-failure, 403 → blocked, 429/503 → rate-limited
        └─ result: kind, message, warnings, account, httpStatus, durationMs, trace
               ↓
        ServerStore records validationStatus / validationMessage / lastValidatedAt
        / lastValidationTrace (redacted; visible in the diagnostics tab)
```

**Search**

```
UI → router.browse/search → BooruClient.search
     ├─ resolve server + adapter (serverId, or the default profile)
     ├─ decide the rating filter (settings + server config + per-search toggle)
     ├─ adapter.buildSearchRequest(ctx, spec)   ← site-specific URL/params
     ├─ HttpClient.request (rate limited per server, retried, redacted)
     └─ adapter.parseSearchResponse(...)        → SearchResult of BooruPost
```

**Single-post download**

```
posts/download { url | postId, serverId? }
  ├─ url  → adapter.matchRoute → resolve saved server → adapter.buildPostRequest
  ├─ enforce rating filter (if enabled in settings)
  ├─ buildDownloadPath (templates → sanitised folder + filename)
  └─ Downloader.download → chrome.downloads (conflictAction: uniquify)
```

**Batch download**

```
posts/download many → queue/enqueuePosts → DownloadQueue.enqueue
   ├─ filter by rating, drop duplicates (`serverId:postId`)
   └─ persist

queue/run → worker loop (maxConcurrency)
   per item: resolve server → adapter.buildPostRequest → parse → naming
             → Downloader.download → status done/failed/skipped + error kind
```

## Storage schema

`chrome.storage.local` (never `sync`, because it holds API keys):

| Key | Contents |
| --- | --- |
| `bsm.servers` | `ServerConfig[]` — credentials + validation state + rating allow-list |
| `bsm.settings` | `ExtensionSettings` — templates, concurrency, intervals, toggles |
| `bsm.queue` | `{ items: QueueItem[], paused: boolean }` |
| `bsm.meta` | `{ schemaVersion, firstRunAt }` |

`migrateStorage()` runs on install/update and is the single place to react to a
schema bump. In-flight queue items are reset from `running` to `pending` on worker
start, so a browser restart never loses work or leaves stuck rows.

## Service worker lifetime

MV3 workers are killed aggressively, so the worker owns no durable state:

- on `onInstalled`: `migrateStorage()` + User-Agent rule sync;
- on `storage.onChanged` for servers/settings: caches invalidated,
  User-Agent rules re-synced;
- on `runtime.onMessage`: one router call per request;
- the queue reads its state from storage on first use, persists after every
  transition, and is re-runnable at any time.

The UI never talks to an adapter or the network directly; it only sends messages.
In the preview harness the same router runs in the page, which is why the preview
behaves identically to the extension.

## User-Agent strategy

`fetch` cannot set `User-Agent` in a browser, but e621 policy (and Danbooru's
guidance) require a descriptive, non-browser UA:

1. `effectiveUserAgent(adapter, server)` prefers the profile's custom UA, then the
   adapter's generated hint (`BooruServerManager/<version> (by <user> on <site>)`).
2. `syncUserAgentRules()` mirrors those strings into `declarativeNetRequest`
   dynamic rules — one `modifyHeaders` rule per saved server host, applied to
   `xmlhttprequest` only.
3. e621 additionally documents a `_client=<user agent>` query fallback, which the
   adapter appends when the probe/tests or the user's settings call for it
   (`sendClientParam`).

Both paths are optional and visible in *Settings → User-Agent handling*.

## Testing strategy

| Suite | Covers |
| --- | --- |
| `tests/adapters/{e621,danbooru,gelbooru}.test.ts` | Request building, response parsing, rating mapping, failure classification, routes, credential fields, UA suggestions |
| `tests/adapters/contract.test.ts` | Cross-adapter conformance: every registered adapter must satisfy the contract invariants |
| `tests/core/servers.test.ts` | CRUD, default rules, duplicate, credential clearing, masking, export/import |
| `tests/core/naming.test.ts` | Sanitisation, traversal, reserved names, token rendering, length caps |
| `tests/core/validation.test.ts` | Preflight, endpoint mismatch, auth vs blocked, rate limiting, network failure, trace redaction |
| `tests/core/queue.test.ts` | De-duplication, rating enforcement, failure recording, pause/retry/clear, persistence, concurrency |
| `tests/core/http.test.ts` | Rate limiter spacing, retries, abort, redaction, JSON/HTML/empty-body handling, taxonomy |
| `tests/core/router.test.ts` | The whole message protocol against the mock booru APIs, including secret masking in every response |

All suites run offline: `tests/helpers.ts` wires the real service graph against
`MemoryStorageArea`, a recording downloader and the mock booru fetch.
