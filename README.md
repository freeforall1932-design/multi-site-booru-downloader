# Booru Server Manager

One Manifest V3 browser extension that manages **many saved booru server/account
profiles** (e621, Danbooru, Gelbooru and their close forks) and downloads from all
of them through a single docked **side panel**.

The panel is built the way the good side-panel downloaders are built: one dock next
to the page, one list that *is* the queue, one settings screen with a heading and a
plain-language hint per option. It stays a booru tool - multi-server profiles,
rating allow-lists, tag search, per-site credentials - and it is an extension, not
an app.

Everything runs inside the browser extension: there is **no remote backend**, no
analytics and no third-party service. The extension talks only to the booru hosts
you configure.

```
┌───────────────────────────────────────────────────────────────────┐
│  BOORU SERVER MANAGER                          3 downloading      │
│  Download panel                                                   │
│  Browse │ Queue ③ │ Servers │ Settings                            │
│  ● e621 post #1500000 — default profile                    ⟳      │
│  [thumb] #1500000 safe [video]                                    │
│          [Download this post] [Add to list]                       │
│  [ e621 (demo account) ▾ ]                                        │
│  [ tags, e.g. cat solo rating:safe ]                              │
│  [ List this page ] [ Download page ]                             │
│  From [1] To [last]   advanced    ☑ Skip downloaded               │
│  ▓▓▓▓▓▓░░░░  Listed page 3 · 120 post(s) so far                   │
│  ☑ #1500000 [pic] saved      completed ✕                          │
│  ☑ #1500001 [video]      downloading ✕                            │
│  Downloads at once [1][2][3][5]      Quality [Original ▾]         │
│  [ Download selected (2) ]                             [ Pause ]  │
└───────────────────────────────────────────────────────────────────┘
```

The design and every setting are documented in
[docs/SIDEPANEL.md](docs/SIDEPANEL.md).

## Features

**Side panel (the toolbar surface)**

- **Browse** — reads the active tab, names the saved profile it belongs to, and
  offers *Download this post* / *Add to list* on post pages. Tag search with
  remembered suggestions, a `From … To` page range plus an advanced box
  (`2,4,6-10`, `1-99`, `50-`, `all`), a picture/video filter, "skip downloaded",
  a review-then-download flow and a page-limit cap on one crawl.
- **Queue** — the row list with live status, per-row cancel/remove, retry failed,
  clear finished, reset history, clear list, and a fixed dock with a concurrency
  segmented control, an original/sample quality select and *Download selected*.
- **Servers** — the full multi-account manager plus diagnostics in one scroll.
- **Settings** — sectioned settings (global behaviour, download behaviour, list
  results, search behaviour, name template, interface, advanced behaviour,
  credentials, history) with a live file-name preview.
- Deep links (`#browse`, `#queue`, `#servers`, `#settings`) and a `uiMode` switch
  that puts the classic popup back on the toolbar if you prefer it.

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
- Tag/search browsing per server with rating-filter awareness, a shared tag
  blacklist and a global "always append these tags" suffix.
- Batch queue with per-server rate limiting, pause/resume, retry, cancel, clear,
  live progress and persisted state (survives a service-worker restart).
- Configurable download folder and filename templates with a documented token
  set, a checkbox token editor with live preview, path sanitisation, traversal
  protection and length caps.
- **Download history** — successful downloads are remembered per server, so
  re-running a search only lists what you do not have yet; reset it any time.

**Quality**

- One shared adapter contract; the UI never branches on site type.
- 270 automated tests (adapters, shared services, message router, HTTP layer, the
  panel's page/query/template/history logic) and a strict TypeScript build.
- An offline **browser preview harness** (`npm run preview`) that runs the real
  UI - side panel included - against mock booru APIs, so the extension can be
  demoed without installing it and without any live requests.

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

Then in Chrome/Edge/Brave (116+ for the side panel):

1. Open `chrome://extensions`, enable **Developer mode**.
2. **Load unpacked** → select the generated `dist/` folder.
3. Click the toolbar icon → the **side panel** opens (Settings → *Toolbar click
   opens* switches back to the classic popup). The full manager is also in the
   options page: right-click the icon → *Options*, or the panel's Servers tab.

`npm run verify` runs type checks, a full build, the test suite and the built-bundle
smoke test in one go.

## Quick start

1. **Add a server** — panel → *Servers → Add server* (or *Options → Servers*).
2. Pick a **site type**, check the pre-filled base URL, paste your credentials.
3. Press **Validate now**. A green *“Endpoint and credentials OK — signed in as …”*
   means you are done; a red chip tells you exactly which layer failed and what to
   fix (see [Manual tests](docs/MANUAL_TESTS.md) for the full failure matrix).
4. **Save**. The first saved profile becomes the default automatically; later use
   *Set default* on any other row.
5. **Browse** in the panel: type tags, press *List this page* or *Fetch selected
   pages*, untick what you do not want, then *Download selected* on the dock. The
   popup and the on-page button feed the same queue.

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
| **Side panel** | toolbar icon (or options → *Open side panel*) | Browse · Queue · Servers · Settings — the primary surface, with a fixed download dock |
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
| `npm test` | Vitest suite (270 tests) |
| `npm run smoke` | Drives the **built** `dist/` bundle end-to-end against the mock booru (76 checks: manifest, service worker wiring, side-panel page, masking, validation kinds, browse, download naming, queue draining, history notebooks, selective runs, settings, diagnostics) and then boots the built **panel bundle** and the **options bundle** on a DOM mock, drives a real query through the panel and walks all four panel tabs |
| `npm run test:watch` | Vitest in watch mode |
| `npm run verify` | `typecheck` → `build` → `test` |
| `npm run preview` | Builds, then serves the **offline preview harness** on <http://localhost:4173> |
| `npm run icons` | Re-renders the PNG icons into `dist/icons` |

### Browser preview harness

`npm run preview` serves `dist/` plus `dev/preview/index.html` on port 4173. The
harness embeds the real **side panel** (at a realistic 380 px), the options page
and the popup, backed by an in-page mock of all three booru APIs (`force:401`,
`force:429`, `force:503`, `force:html` directives let you reproduce failures), a
mock media server, and the same service graph used by the extension. Buttons at the
top switch the panel's simulated active tab, so the post card and the listing card
can both be exercised. Demo credentials and the reset button are documented on the
page itself. Nothing in the harness touches the network.

## Project layout

```
src/
  shared/      domain types, error taxonomy, HTTP client + rate limiter, helpers
  core/        adapter contract + registry, storage, settings, servers, validation,
               client, queue, naming, downloads, history notebooks, page ranges,
               search composition, template editor, User-Agent rules, router
  adapters/    e621, danbooru, gelbooru (+ factory for Gelbooru-family forks)
  platform/    Chrome MV3 platform, preview platform, lazy façade
  ui/          DOM helpers and view builders (server list, form, browse, queue,
               settings sections, diagnostics, panel cards/lists/dock, toasts)
  panel/       side panel shell: layout, tab routing, controller, dock  ← primary
  options/     options page shell and routing (#servers/#editor/#browse/…)
  popup/       toolbar popup (current tab + quick search + queue footer)
  content/     post-page download button (shadow DOM)
  background/  MV3 service worker: message router host, toolbar mode, UA rules
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
| [docs/SIDEPANEL.md](docs/SIDEPANEL.md) | The side panel: layout, tabs, row/queue model, complete settings reference, history notebooks, preview harness |
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | Layers, data flow, storage schema, message protocol, queue lifecycle |
| [docs/ADAPTER_CONTRACT.md](docs/ADAPTER_CONTRACT.md) | The full adapter contract + how to add a new booru in ~200 lines |
| [docs/SECURITY.md](docs/SECURITY.md) | Credential storage, redaction rules, what never leaves the browser |
| [docs/MANUAL_TESTS.md](docs/MANUAL_TESTS.md) | Step-by-step manual test plan with expected results |
| [docs/WORKLIST.md](docs/WORKLIST.md) | Implemented scope, known gaps and next steps |

## Status

Version 0.2.0 — the three target sites are implemented against their official
APIs, the side panel is the primary surface, and an adapter conformance suite
guards any new site. See [docs/WORKLIST.md](docs/WORKLIST.md) for what is done and
what is deliberately left for later (e.g. per-site uploads, tag autocomplete,
archive/PDF picture packing).

## Licence / site terms

Personal, non-commercial use. Respect each site's Terms of Service and API rules:
the extension keeps to the documented request rates (1 request/second per server
by default), identifies itself with a descriptive User-Agent, and never
circumvents authentication or paywalls.
