# Booru Server Manager

One Manifest V3 browser extension that manages **many saved booru server/account
profiles** (e621, Danbooru, Gelbooru and their close forks) and downloads from all
of them through a single, unified UI.

Everything runs inside the browser extension: there is **no remote backend**, no
analytics and no third-party service. The extension talks only to the booru hosts
you configure.

```
┌───────────────────────────────────────────────────────────────────┐
│  Server list           Browse                  Queue              │
│  ───────────           ──────                  ─────              │
│  e621  ★ default       [tag search]            ✔ 12 done          │
│  Danbooru              [post grid]             ⏳ 3 running        │
│  Gelbooru              [Add to queue]          ✖ 1 failed         │
│  e926 (safe mirror)                                               │
└───────────────────────────────────────────────────────────────────┘
```

## Features

**Server manager (first-class screen)**

- Add / edit / delete / duplicate server profiles, set exactly one default.
- Per-server credentials with per-site field sets (e621: username + API key +
  descriptive User-Agent; Danbooru: username + API key; Gelbooru: API key +
  numeric user id, optional username for display).
- Validate a draft *before* saving or a stored profile at any time. Validation
  distinguishes, with separate messages:
  `auth-failure` · `endpoint-mismatch` · `rate-limited` · `network-failure` ·
  `blocked` · `server-error` · `unsupported-site` · `incomplete-config` ·
  `parse-failure`.
- The **last validation result and timestamp are persisted** per profile, and the
  server list shows site type, base URL, rating-filter state, login state and the
  default marker at a glance.
- Export / import the whole server list as JSON (secrets excluded unless you
  explicitly opt in).

**Downloading**

- Post-page download button (content script, shadow DOM, can be switched off).
- Popup: acts on the current tab — detects the site/post route, shows the matching
  server, and downloads or queues the post.
- Tag/search browsing per server with rating-filter awareness.
- Batch queue with per-server rate limiting, pause/resume, retry, cancel, clear,
  live progress and persisted state (survives a service-worker restart).
- Configurable download folder and filename templates with a documented token
  set, path sanitisation, traversal protection and length caps.

**Quality**

- One shared adapter contract; the UI never branches on site type.
- 223 automated tests (adapters, shared services, message router, HTTP layer) and
  a strict TypeScript build.
- An offline **browser preview harness** (`npm run preview`) that runs the real UI
  against mock booru APIs, so the extension can be demoed without installing it
  and without any live requests.

## Supported sites

| `siteType` | Site | Auth style | Rating filter | API notes |
| --- | --- | --- | --- | --- |
| `e621` | e621.net, e926.net | HTTP Basic (`username:api_key`) or `login`/`api_key` query params | `-rating:s/q/e` | Descriptive User-Agent required by policy; `_client` query fallback for extensions; 320 posts/request; hard 2 req/s |
| `danbooru` | danbooru.donmai.us, `*.donmai.us` | HTTP Basic or `login`/`api_key` query params | `-rating:general/sensitive/questionable/explicit` | `/profile.json` returns the account; prefer `id:` batching; 200 posts/request; 10 req/s global |
| `gelbooru` | gelbooru.com, `www.gelbooru.com`, `*.gelbooru.com` | `api_key` + `user_id` query params | `rating:`/`-rating:` tokens | DAPI (`page=dapi&s=post&q=index&json=1`); `{"@attributes":…,"post":[…]}` envelope; 100 posts/request |

Additional booru-like sites are added by implementing the adapter contract — see
[docs/ADAPTER_CONTRACT.md](docs/ADAPTER_CONTRACT.md). No UI or service code changes
are required.

## Install (unpacked, for development)

```bash
node --version          # requires Node 20+ (built with Node 22)
npm run build           # type-checks, compiles src/ → dist/, generates icons
```

Then in Chrome/Edge/Brave:

1. Open `chrome://extensions`, enable **Developer mode**.
2. **Load unpacked** → select the generated `dist/` folder.
3. Click the extension icon → **Manage servers** (options page opens on `#servers`).

`npm run verify` runs type checks, a full build, the test suite and the built-bundle
smoke test in one go.

## Quick start

1. **Add a server** — *Options → Servers → Add server*.
2. Pick a **site type**, check the pre-filled base URL, paste your credentials.
3. Press **Validate now**. A green *“Endpoint and credentials OK — signed in as …”*
   means you are done; a red chip tells you exactly which layer failed and what to
   fix (see [Manual tests](docs/MANUAL_TESTS.md) for the full failure matrix).
4. **Save**. The first saved profile becomes the default automatically; later use
   *Set default* on any other row.
5. **Browse** the server (tag search + rating filter), then **Queue → Run**, or use
   the toolbar popup / the on-page button on a post page.

### Getting credentials

| Site | Where |
| --- | --- |
| e621 | Account → *Manage API Access* (key). Put your e621 username in the username field: e621 policy requires a descriptive User-Agent that identifies you, e.g. `BooruServerManager/0.1.0 (by your_name on e621)`. |
| Danbooru | Profile → *API Key*. Danbooru asks for a unique descriptive User-Agent too; the extension generates one containing your account id when credentials are present. |
| Gelbooru | Account → *Options → API Access Credentials*: copy **API key** and **user id** (both are required together). |

Credentials are stored only in `chrome.storage.local` on your machine, are never
rendered in full (masked previews only), and are never written to console output or
diagnostics — see [docs/SECURITY.md](docs/SECURITY.md).

## What the UI looks like

| Surface | Route / entry | Purpose |
| --- | --- | --- |
| Server list | `options.html#servers` | Add/edit/duplicate/delete, set default, validate, clear credentials, export/import |
| Server editor | `options.html#editor` | One form for every site type; fields come from the adapter's `credentialFields()` |
| Browse | `options.html#browse` | Tag search, rating-filter toggle, post grid, single or bulk download |
| Queue | `options.html#queue` | Running/pending/failed items, retry, cancel, clear, pause/resume |
| Settings | `options.html#settings` | Templates, concurrency, request interval, rating enforcement, User-Agent behaviour, theme |
| Diagnostics | `options.html#diagnostics` | Adapter catalog, environment, per-server validation traces (credential-free), copy-to-clipboard report |
| Popup | toolbar icon | Current tab detection, quick search, one-click download, queue footer |
| Content script | any supported post page | Floating download button linked to the matching server |

## Download naming

Default folder template `booru/{siteType}`, default filename template `{id}_{md5}`
(the file extension is always appended from the post metadata).

Available tokens:
`{id}` `{siteType}` `{server}` `{serverLabel}` `{rating}` `{score}` `{md5}` `{ext}`
`{filename}` `{date}` `{year}` `{month}` `{artist}` `{artists}` `{character}`
`{copyright}` `{tags}` `{tagCount}` `{width}` `{height}` `{source}` `{isVideo}`
`{tag1}` … `{tag9}`

Templates are sanitised token-by-token: illegal characters are replaced, path
traversal and reserved Windows names are neutralised, each path segment is capped
at 120 characters and the filename at 180 characters total. When a value had to be
changed, the download result carries a warning explaining it.

## Rating filters

Each profile has a rating filter toggle plus an allow-list of canonical ratings
(`safe`, `general`, `sensitive`, `questionable`, `explicit`). The shared layer
decides *whether* filtering happens; the adapter decides *how* it is spelled for
its site (all three targets support the portable exclusion form, e.g.
`-rating:explicit`). Filters apply to browsing, to queueing and — when
*Settings → Enforce rating filter on download* is on — to single-post downloads.

## Scripts

| Command | What it does |
| --- | --- |
| `npm run build` | Removes `dist/`, compiles `src/` with `tsc`, copies HTML/CSS/manifest, renders the 4 PNG icons, writes `dist/build-info.json` |
| `npm run typecheck` | Strict type check of `src/` and of `src/` + `tests/` |
| `npm test` | Vitest suite (223 tests) |
| `npm run smoke` | Drives the **built** `dist/` bundle end-to-end against the mock booru (30 checks: manifest, masking, validation kinds, browse, download naming, queue draining, settings, diagnostics) |
| `npm run test:watch` | Vitest in watch mode |
| `npm run verify` | `typecheck` → `build` → `test` |
| `npm run preview` | Builds, then serves the **offline preview harness** on <http://localhost:4173> |
| `npm run icons` | Re-renders the PNG icons into `dist/icons` |

### Browser preview harness

`npm run preview` serves `dist/` plus `dev/preview/index.html` on port 4173. The
harness embeds the real options page and popup in iframes, backed by an in-page
mock of all three booru APIs (`force:401`, `force:429`, `force:503`, `force:html`
directives let you reproduce failures), a mock media server, and the same service
graph used by the extension. Demo credentials and the reset button are documented
on the page itself. Nothing in the harness touches the network.

## Project layout

```
src/
  shared/      domain types, error taxonomy, HTTP client + rate limiter, helpers
  core/        adapter contract + registry, storage, settings, servers, validation,
               client, queue, naming, downloads, User-Agent rules, message router
  adapters/    e621, danbooru, gelbooru (+ factory for Gelbooru-family forks)
  platform/    Chrome MV3 platform, preview platform, lazy façade
  ui/          DOM helpers and view builders (server list, form, browse, queue,
               settings, diagnostics, toasts)
  options/     options page shell and routing (#servers/#editor/#browse/…)
  popup/       toolbar popup (current tab + quick search + queue footer)
  content/     post-page download button (shadow DOM)
  background/  MV3 service worker: message router host, storage-change handling
  preview/     offline mock booru + preview bootstrap (development only)
tests/
  adapters/    per-site adapter tests + cross-adapter conformance tests
  core/        servers, naming, validation, queue, HTTP/rate-limit, router tests
scripts/       build, icon generator, preview server
dev/preview/   preview harness page
```

## Documentation

| Document | Contents |
| --- | --- |
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | Layers, data flow, storage schema, message protocol, queue lifecycle |
| [docs/ADAPTER_CONTRACT.md](docs/ADAPTER_CONTRACT.md) | The full adapter contract + how to add a new booru in ~200 lines |
| [docs/SECURITY.md](docs/SECURITY.md) | Credential storage, redaction rules, what never leaves the browser |
| [docs/MANUAL_TESTS.md](docs/MANUAL_TESTS.md) | Step-by-step manual test plan with expected results |
| [docs/WORKLIST.md](docs/WORKLIST.md) | Implemented scope, known gaps and next steps |

## Status

Version 0.1.0 — the three target sites are implemented against their official
APIs, with an adapter conformance suite that any new site must pass. See
[docs/WORKLIST.md](docs/WORKLIST.md) for what is done and what is deliberately
left for later (e.g. per-site uploads, tag autocomplete).

## Licence / site terms

Personal, non-commercial use. Respect each site's Terms of Service and API rules:
the extension keeps to the documented request rates (1 request/second per server
by default), identifies itself with a descriptive User-Agent, and never
circumvents authentication or paywalls.
