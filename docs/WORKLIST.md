# Worklist / status

Status of the product scope, what is verified, and what is deliberately left for
later. Last updated for `0.2.0` (side panel release) plus the mirror-link
collector (`0.2.x`).

## Delivered

| Deliverable | Status | Where |
| --- | --- | --- |
| Working extension skeleton (MV3, TypeScript, no bundler) | ✅ | `src/manifest.json`, `scripts/build.mjs` → `dist/` |
| **Side panel as the primary surface** (Browse · Queue · Links · Servers · Settings, deep links, dock) | ✅ | `src/panel/`, `src/ui/panel-*.ts`, [docs/SIDEPANEL.md](SIDEPANEL.md) |
| Toolbar mode switch (side panel vs popup) applied from settings by the worker | ✅ | `src/background/service-worker.ts` (`applyUiMode`), `settings.uiMode` |
| Active-tab detection + "this post" card in the panel | ✅ | `src/ui/panel-context.ts`, `routes/detect`, `posts/get` |
| Listing card: tag search with suggestions, page ranges (`2,4,6-10`, `50-`, `all`), crawl cap, progress, media filter | ✅ | `src/ui/panel-listing.ts`, `src/core/pages.ts` |
| Review-then-download rows: a row *is* a persisted queue item, ticked selection, selective runs | ✅ | `src/ui/panel-queue.ts`, `queue/run { itemIds }`, `queue/remove` |
| Settings in the NHentai-Downloader section style (heading + hint per option, template checkbox editor, live preview) | ✅ | `src/ui/settings-sections.ts`, `src/core/template.ts` |
| Download history ("skip downloaded", `saved` badges) + remembered searches | ✅ | `src/core/history.ts`, `bsm.history`, `bsm.searches` |
| Shared tag blacklist and "always append these tags" applied to every search | ✅ | `src/core/search.ts`, router `browse/search`, adapter `composeTags` |
| Quality (original vs sample rendition) and duplicate behaviour on the dock/settings | ✅ | `settings.filePreference`, `settings.duplicateBehaviour`, queue + router download paths |
| Multi-server / multi-account manager with mobile-style server list | ✅ | `src/ui/server-list.ts`, `src/options/options.ts` (`#servers`) |
| Add/edit screen with per-site fields, validate-client, save | ✅ | `src/ui/server-form.ts`, `src/core/validation.ts` |
| **e621 adapter** (first target) | ✅ | `src/adapters/e621.ts` |
| Danbooru adapter | ✅ | `src/adapters/danbooru.ts` |
| Gelbooru adapter (+ factory for DAPI forks; accepts 0.2 envelope and 0.1.11 bare arrays) | ✅ | `src/adapters/gelbooru.ts` |
| Gelbooru 0.1.11 forks: rule34.xxx (auth required), safebooru.org, xbooru, tbib, hypnohub, realbooru | ✅ | `src/adapters/gelbooru-forks.ts` |
| Moebooru family: yande.re, konachan (`/post.json`, optional `login`+`password_hash`) | ✅ | `src/adapters/moebooru.ts` |
| Philomena family: derpibooru, furbooru, ponybooru (comma query syntax, ratings as tags, filter pinning) | ✅ | `src/adapters/philomena.ts` |
| Hydrus Client API (local http, access key, two-step listing → metadata) | ✅ | `src/adapters/hydrus.ts` |
| Kemono / Coomer / Pawchive creator archives (attachment rows with composite ids, per-node file hosts) | ✅ | `src/adapters/kemono.ts` |
| **Mirror-link collector (Links tab)**: walks a creator's posts, harvests the off-site download links in their bodies, keeps them in a durable list, groups/ticks/queues them, exports and re-imports the list as `.txt` | ✅ | `src/core/links.ts`, `src/core/link-store.ts`, `src/ui/panel-links.ts`, `src/panel/panel.ts`, [docs/SIDEPANEL.md#links-tab-mirror-links](SIDEPANEL.md#links-tab-mirror-links) |
| `link` queue rows: off-site URLs download without an adapter, a post lookup or a rating, keyed by URL so the same file is never queued twice | ✅ | `src/core/queue.ts` (`enqueueLinks`, `processLinkItem`, `syncLinkRow`) |
| Standalone **Pawchive Link Collector** userscript shipped next to the extension (same rules, no extension needed) | ✅ | `userscripts/pawchive-link-collector.user.js`, `userscripts/README.md` |
| Fixture tests for every added family from live-captured response shapes | ✅ | `tests/adapters/families.test.ts` |
| Documented adapter contract | ✅ | `docs/ADAPTER_CONTRACT.md`, `src/core/adapter.ts`, conformance tests |
| Shared services (servers, settings, validation, client, queue, naming, downloads, User-Agent rules, router) | ✅ | `src/core/*` |
| Browsing, single-post download, batch queue | ✅ | `src/ui/browse.ts`, `src/ui/queue-view.ts`, `src/core/queue.ts` |
| Popup + content-script download button | ✅ | `src/popup/popup.ts`, `src/content/post-button.ts` |
| Diagnostics + credential-free traces | ✅ | `src/ui/diagnostics-view.ts`, `src/core/router.ts` |
| Offline preview harness + mock booru | ✅ | `dev/preview/`, `src/preview/`, `scripts/preview-server.mjs` |
| Tests for adapters and shared logic | ✅ | `tests/` — 455 tests, all passing (incl. the mirror-link rules, export/import round-trip and panel logic) |
| Docs | ✅ | `README.md`, `docs/{SIDEPANEL,ARCHITECTURE,ADAPTER_CONTRACT,SECURITY,MANUAL_TESTS,WORKLIST}.md`, `userscripts/README.md` |

### Verification snapshot

```
npm run typecheck   → clean (src + tests, strict TS, noUncheckedIndexedAccess)
npm test            → 455 passed (14 files)
npm run build       → dist/ with manifest, 4 icons, build-info.json
npm run smoke       → 86/86 checks against the built dist/ bundle (incl. booting
                      the built panel bundle, driving a query and a full
                      mirror-link collection through it, walking all five panel
                      tabs incl. Links, and booting the options bundle)
npm run preview     → harness + side panel/options/popup served on :4173, with a
                      demo creator whose posts carry mirror links
```

| Suite | Tests | Focus |
| --- | --- | --- |
| `tests/adapters/contract.test.ts` | 147 | cross-adapter invariants, automatically covers new adapters (incl. Pawchive) |
| `tests/adapters/families.test.ts` | 35 | Moebooru, Philomena, Hydrus, Gelbooru forks and the Kemono family fixtures |
| `tests/adapters/e621.test.ts` | 26 | auth, requests, parsing, failure classification, routes, UA |
| `tests/adapters/danbooru.test.ts` | 17 | same, plus `/profile.json` account parsing |
| `tests/adapters/gelbooru.test.ts` | 24 | DAPI envelope, query-with-userid auth, fork factory |
| `tests/core/router.test.ts` | 35 | entire message protocol end-to-end against the mock APIs |
| `tests/core/links.test.ts` | 30 | link rules, payload extraction, export/import round-trip, store merge rules, `link` rows, the `links/*` flow |
| `tests/core/panel-core.test.ts` | 35 | page ranges, query composition, template editor, history notebooks, panel settings keys |
| `tests/core/panel-router.test.ts` | 12 | selective runs, row removal, history/search messages, blacklist + suffix, sample/overwrite preferences |
| `tests/core/http.test.ts` | 26 | rate limiter, retries, abort, redaction, JSON/HTML/empty bodies |
| `tests/core/queue.test.ts` | 13 | dedupe, rating enforcement, failures, pause/retry/clear, persistence |
| `tests/core/servers.test.ts` | 19 | CRUD, default rules, duplicate, masking, export/import |
| `tests/core/validation.test.ts` | 19 | preflight, endpoint/auth/rate-limit/network classification, trace redaction |
| `tests/core/naming.test.ts` | 17 | sanitisation, traversal, reserved names, tokens, length caps |

All tests run offline against `src/preview/mock-booru.ts`; no live site is contacted
by the suite. Live behaviour is covered by `docs/MANUAL_TESTS.md`.

## Behaviour notes worth knowing

- **The panel's row list *is* `bsm.queue`.** A row is a persisted `QueueItem`, so
  closing the panel (or restarting the worker) never loses work; ticking rows only
  decides *which pending items a run may touch* (`queue/run { itemIds }`).
- **Fetching never downloads.** A fetch calls `queue/enqueuePosts` only; the queue
  starts when the dock button (or the content-script button on a post page) asks
  for a run. `settings.autoStartQueue` only controls whether "Download selected"
  also starts the run.
- **Newly listed rows arrive ticked**, and a row you untick stays unticked across
  refreshes (selection is reconciled against a snapshot of pending ids).
- **The tag blacklist is applied in the router**, once, for every surface; the
  *global suffix* is applied by the adapters (which also de-duplicate it against
  your own tags), so neither is ever added twice.
- **`filePreference: 'sample'`** falls back to the original file whenever the site
  reports no sample, so a download never fails because of a quality preference.
- **A mirror link is a second kind of queue row.** It is keyed by its URL
  (`link:<url>`), skips the adapter, the post lookup and the rating filter, and is
  saved through the URL-shaped naming tokens (`mirrors/{host}` by default) - which
  is what makes an imported `.txt` work on a profile that has no server at all.
  Hosting pages that need a login cannot be fetched by a browser download; the row
  keeps the error and the exported `.txt` is the fallback for those.
- **Re-collecting a creator never duplicates work.** The link store merges by URL
  id and keeps an existing `done`/`failed` outcome, so a second scan of the same
  creator only refreshes the post context and reports duplicates.

- **Exactly one default** profile always exists once at least one server is saved
  (first save wins, deleting the default promotes the next row).
- **Rating filter is three-layered**: profile allow-list (stored), shared decision
  layer (`BooruClient.isRatingAllowed` / `ratingFilterFor`), site spelling in the
  adapter. Queueing filters, and single downloads filter when
  *Settings → Enforce rating filter on download* is on.
- **Validating a draft reuses stored secrets** when the key field is left blank, so
  “edit → validate” does not require re-typing a key.
- **The queue is stateful across restarts**: `running` items are reset to `pending`
  when the worker starts; de-duplication applies to pending/running items only.
- **e621 User-Agent**: custom UA → `declarativeNetRequest` header rewrite →
  `_client` query parameter fallback (`sendClientParam` setting).
- **Rate limiting** is per saved server, defaulting to 1 request/second plus the
  adapter's documented minimum; the HTTP layer also retries 429/5xx with backoff
  and honours `Retry-After`.

## Known gaps / deliberate non-goals (0.1.0)

| Gap | Notes / plan |
| --- | --- |
| Read-only product | No uploads, favourites, pool edits or tag edits. The adapters expose only read endpoints plus the vote/favourite helpers that are not yet surfaced in the UI. |
| No tag autocomplete | Would need a per-site tag endpoint; `supportsTagSearch` capability is already declared. |
| Pools/forks browsing | `matchRoute` recognises pool URLs, but no pool listing UI yet. |
| No integrity check after download | The worker records the filename/size the browser reports; verifying `md5` against the site is future work. |
| No bulk “download whole search” | Queueing is per page; “queue all loaded” covers the current listing. |
| Live network tests are not automated | By design (no credentials in CI, no hammering the sites); covered manually. `npm run smoke` exercises the built bundle against the offline mock instead. |
| Chrome/Edge/Brave only | Built on `chrome.*` MV3 APIs. Firefox would need a `browser.*` shim and manifest tweaks. |
| No i18n | All strings are English literals in the view layer. |
| Mirror links are not resolved | The collector stores and downloads the URL a post links to; it does not follow provider redirects to a direct file (Mega/Drive landing pages stay pages). A resolver per provider would be a plugin of its own. |
| Preview modules ship inside `dist/` | `dist/preview/*.js` is inert in the extension (the UI only imports it when `chrome.runtime.id` is missing); excluding it from the build would shave a few KB. |
| No release packaging | `dist/` is loadable unpacked; a zipped CRX/XPI and a CI workflow are not set up yet. |

## Next steps (suggested order)

1. **Download-integrity pass**: stream `chrome.downloads.onChanged` byte counts
   into `bsm.queue` so rows show real progress, then compare size/md5 when the site
   reports a hash and flag mismatches in the row.
2. **Archive picture packing** (ZIP/CBZ/PDF per post, offscreen document) as an
   optional output mode in the panel's settings, mirroring the Rule 34 picture
   modes.
3. **Tag autocomplete + favourites/history browsing** using the sites' tag and
   favourites endpoints, behind the existing capability flags (Anime Boxes parity).
4. **Zip packaging + CI**: build artifact upload, `npm run verify` as a workflow,
   a version-bump script.
5. **Exclude the preview modules from release builds** (build flag) and ship a
   `dist-preview` variant for the harness.
6. **Pool/favourites browsing** for e621/Danbooru (adapters already recognise the
   routes; the UI needs a second listing mode).
7. **More adapters**: register common DAPI forks via `createGelbooruLikeAdapter`
   and add a first-class adapter for a Danbooru-style instance to validate that the
   contract scales beyond the three targets.
8. **Firefox/Manifest parity pass** if a second browser target is required
   (the side panel has no Firefox equivalent, so the popup fallback earns its keep).
