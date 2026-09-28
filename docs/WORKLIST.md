# Worklist / status

Status of the product scope, what is verified, and what is deliberately left for
later. Last updated for `0.1.0`.

## Delivered

| Deliverable | Status | Where |
| --- | --- | --- |
| Working extension skeleton (MV3, TypeScript, no bundler) | ✅ | `src/manifest.json`, `scripts/build.mjs` → `dist/` |
| Multi-server / multi-account manager with mobile-style server list | ✅ | `src/ui/server-list.ts`, `src/options/options.ts` (`#servers`) |
| Add/edit screen with per-site fields, validate-client, save | ✅ | `src/ui/server-form.ts`, `src/core/validation.ts` |
| **e621 adapter** (first target) | ✅ | `src/adapters/e621.ts` |
| Danbooru adapter | ✅ | `src/adapters/danbooru.ts` |
| Gelbooru adapter (+ factory for DAPI forks) | ✅ | `src/adapters/gelbooru.ts` |
| Documented adapter contract | ✅ | `docs/ADAPTER_CONTRACT.md`, `src/core/adapter.ts`, conformance tests |
| Shared services (servers, settings, validation, client, queue, naming, downloads, User-Agent rules, router) | ✅ | `src/core/*` |
| Browsing, single-post download, batch queue | ✅ | `src/ui/browse.ts`, `src/ui/queue-view.ts`, `src/core/queue.ts` |
| Popup + content-script download button | ✅ | `src/popup/popup.ts`, `src/content/post-button.ts` |
| Diagnostics + credential-free traces | ✅ | `src/ui/diagnostics-view.ts`, `src/core/router.ts` |
| Offline preview harness + mock booru | ✅ | `dev/preview/`, `src/preview/`, `scripts/preview-server.mjs` |
| Tests for adapters and shared logic | ✅ | `tests/` — 223 tests, all passing |
| Docs | ✅ | `README.md`, `docs/{ARCHITECTURE,ADAPTER_CONTRACT,SECURITY,MANUAL_TESTS,WORKLIST}.md` |

### Verification snapshot

```
npm run typecheck   → clean (src + tests, strict TS, noUncheckedIndexedAccess)
npm test            → 223 passed (10 files)
npm run build       → dist/ with manifest, 4 icons, build-info.json
npm run smoke       → 30/30 checks against the built dist/ bundle
npm run preview     → harness + options/popup served on :4173
```

| Suite | Tests | Focus |
| --- | --- | --- |
| `tests/adapters/e621.test.ts` | 26 | auth, requests, parsing, failure classification, routes, UA |
| `tests/adapters/danbooru.test.ts` | 17 | same, plus `/profile.json` account parsing |
| `tests/adapters/gelbooru.test.ts` | 24 | DAPI envelope, query-with-userid auth, fork factory |
| `tests/adapters/contract.test.ts` | 27 | cross-adapter invariants, automatically covers new adapters |
| `tests/core/servers.test.ts` | 19 | CRUD, default rules, duplicate, masking, export/import |
| `tests/core/naming.test.ts` | 17 | sanitisation, traversal, reserved names, tokens, length caps |
| `tests/core/validation.test.ts` | 19 | preflight, endpoint/auth/rate-limit/network classification, trace redaction |
| `tests/core/queue.test.ts` | 13 | dedupe, rating enforcement, failures, pause/retry/clear, persistence |
| `tests/core/http.test.ts` | 26 | rate limiter, retries, abort, redaction, JSON/HTML/empty bodies |
| `tests/core/router.test.ts` | 35 | entire message protocol end-to-end against the mock APIs |

All tests run offline against `src/preview/mock-booru.ts`; no live site is contacted
by the suite. Live behaviour is covered by `docs/MANUAL_TESTS.md`.

## Behaviour notes worth knowing

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
| Preview modules ship inside `dist/` | `dist/preview/*.js` is inert in the extension (the UI only imports it when `chrome.runtime.id` is missing); excluding it from the build would shave a few KB. |
| No release packaging | `dist/` is loadable unpacked; a zipped CRX/XPI and a CI workflow are not set up yet. |

## Next steps (suggested order)

1. **Zip packaging + CI**: build artifact upload, `npm run verify` as a workflow,
   and a version-bump script.
2. **Exclude the preview modules from release builds** (build flag) and ship a
   `dist-preview` variant for the harness.
3. **Pool/favourites browsing** for e621/Danbooru (adapters already recognise the
   routes; the UI needs a second listing mode).
4. **Download integrity**: compare the saved file's size/md5 against the post when
   the site reports it; surface mismatches in the queue.
5. **Tag autocomplete** using the sites' tag endpoints, behind the existing
   capability flag.
6. **More adapters**: register common DAPI forks via `createGelbooruLikeAdapter`
   and add a first-class adapter for a Danbooru-style instance to validate that the
   contract scales beyond the three targets.
7. **Firefox/Manifest parity pass** if a second browser target is required.
