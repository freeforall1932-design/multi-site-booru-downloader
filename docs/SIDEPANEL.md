# The side panel

The side panel (`src/panel/`) is the daily driver of the extension: a docked,
resizable surface that lists results, reviews them, and downloads them without
leaving the booru page you are on.

It is deliberately built the way the sister side-panel downloaders are built —
one dock, one list, one settings screen — while staying a *booru* tool:

| Where the idea comes from | What was taken | How it was adapted here |
| --- | --- | --- |
| **twitter-batch-download** (`X Media Downloader`) | Header with an eyebrow + live status pill, a "what page am I on?" card with a refresh button, a summary strip, toolbars, a row list with per-row remove, a fixed download dock with a segmented concurrency control, output settings in a collapsible `<details>` | The "active tab" card resolves the page against your **saved booru servers** instead of one hard-coded site; rows are booru posts (rating, tags, md5, video/picture) |
| **rule34video** (`Downloader for Rule 34`) | Listing card with `List this page` / `Download page`, `From … To` page range plus an **advanced** box (`2,4,6-10`, `1-99`, `50-`, `all`), a media filter, "skip downloaded", a page-limit cap on one crawl, progress bar, review-then-download promise, dock with concurrency **and quality** selects | Pages are the sites' own listing pages (e621/Danbooru/Gelbooru paginate differently, the adapters handle that); the media filter is picture/video; quality is *original vs sample* file rendition |
| **nh-dw-2.0** (`NHentai Downloader`) | Settings as **sections with a heading and a plain-language hint per option**, a name-template editor made of checkboxes with a custom-template fallback and a live preview, download history with "skip downloaded", per-server credentials stored in `storage.local` only, `uiMode` (panel vs popup) | Sections cover booru concerns: rating filters, per-server credentials, request spacing, tag blacklist, template tokens that exist here (`{md5}`, `{rating}`, `{artist}` …) |
| **Anime Boxes** (Gelbooru/Danbooru client) | Multi-server profile list, tag search with remembered searches, tag blacklist, picture/video split, "you already have this" awareness | Search history + blacklist live in Settings and feed the panel search box; `skipDownloaded` greys out/omits posts found in the download history |

## Layout

```
┌───────────────────────────────────────────────────────────┐
│ BOORU SERVER MANAGER                        3 downloading │  header + status pill
│ Download panel                                            │
├───────────────────────────────────────────────────────────┤
│  Browse │ Queue ③ │ Servers │ Settings                    │  tab strip (deep links:
├───────────────────────────────────────────────────────────┤  #browse #queue #servers
│ ●  e621 post #1500000 — default profile             [Use…]│  #settings)
│ [thumb] #1500000 safe  [video]                            │  "this post" card
│         [Download this post] [Add to list]                │
├───────────────────────────────────────────────────────────┤
│ [ e621 (demo account) ▾ ]                                 │
│ [ tags, e.g. cat solo rating:safe              ] [×]      │  search + suggestions
│ This fetch: +1 always-on tag(s) · −2 blacklisted          │
│ [ List this page ] [ Download page ]                      │
│ From [1]  To [last]                              advanced │  page range
│ [ Pics + videos ▾ ]  ☑ Skip downloaded                    │
│ ☑ apply this profile's rating filter (safe)               │
│ [ Fetch selected pages ]                          [ Stop ]│
│  ▓▓▓▓▓▓░░░░  Listed page 3 · 120 post(s) so far           │
├───────────────────────────────────────────────────────────┤
│   18 listed        3 selected        2 completed          │  summary
│ ☑ Select all   Invert   Remove selected (3)               │  toolbars
│ Retry failed   Clear finished   Reset history   Clear list│
│ [☑][thumb] #1500000 [pic][safe][e621][saved]  completed ✕ │  rows (a row *is* a
│ [☑][thumb] #1500001 [video][general][e621]  downloading ✕ │  persisted queue item)
├───────────────────────────────────────────────────────────┤
│ Downloads at once [1][2][3][5]        Quality [Original ▾]│  dock (fixed)
│ [ Download selected (3) ]                        [ Pause ]│
│ Tick rows (or Select all), then download. …                │
└───────────────────────────────────────────────────────────┘
```

## Tabs

| Tab | What it does | Backing messages |
| --- | --- | --- |
| **Browse** | Active-tab card, "this post" card, fetch card, the list of rows | `routes/detect`, `posts/get`, `browse/search`, `queue/enqueuePosts`, `history/list` |
| **Queue** | The same rows, focused on progress: retry failed, clear finished, reset history, clear list | `queue/list`, `queue/retryFailed`, `queue/clear`, `history/clear` |
|  | Rows carry the media badge (`pic`/`video`), the canonical **rating chip**, the profile's site type and a `saved` badge | `queue/list` (rating is stored on the row at enqueue time) |
| **Servers** | Full server manager (list, add/edit/validate, duplicate, delete, set default) plus diagnostics | `servers/*`, `diagnostics/info`, `userAgent/sync` |
| **Settings** | The sectioned settings screen (identical to the options page's) | `settings/get`, `settings/save`, `servers/export|import`, `history/*`, `searches/*` |

Everything the panel shows is fetched through the background worker. The panel
never holds a credential, never issues a network request and never writes a file
itself — the same rule the options page and popup follow.

## The list is the queue

A row **is** a persisted `QueueItem` (`src/core/queue.ts`). That gives the panel,
for free:

- state that survives closing the panel, reloading the page and restarting the
  service worker (`running` rows are reset to `pending` on worker start);
- de-duplication (`serverId:postId`) that spans every surface, so the popup and
  the content-script button cannot queue the same post twice;
- one place where the rating filter is enforced.

"Fetching only adds rows" is a promise the code keeps: `queue/enqueuePosts` is the
only thing a fetch calls, and a run happens only when you press **Download
selected**. Tick marks select *which pending rows* a run may touch:

```
queue/run                     → every pending row
queue/run { itemIds: [...] }  → only the ticked rows (the dock button)
```

Newly listed rows arrive ticked; a row you untick stays unticked across refreshes
(the selection is reconciled, not reset).

## Settings reference

All of these live in `chrome.storage.local` and are shared by the panel, the
options page and the popup. Defaults in brackets.

### 1. Global behaviour

| Setting | What it does |
| --- | --- |
| **Toolbar click opens** [`sidepanel`] | `sidepanel` = docked panel (`openPanelOnActionClick`), `popup` = the classic popup page. The worker re-applies the stored choice every time it starts, so a `popup` profile keeps its popup. |
| **Panel opens on** [`browse`] | Which tab the panel shows when it opens (`browse`, `queue`, `servers`, `settings`). |
| **Theme** [`system`] | Dark-first design; `light` exists for daytime reading. |

### 2. Multiple download

| Setting | What it does |
| --- | --- |
| **Downloads at once** [2] | Parallel download slots. The dock's segmented control writes the same value. |
| **Wait between requests to one server** [1 s] | Per-server spacing for API calls; adapters raise it when the site documents a hard limit (e621: 2 req/s), and the stricter value wins. |
| **Retries per download** [3] | Retries for transient failures (network, 5xx, "slow down"). Auth and configuration errors are never retried. |
| **If the file already exists** [`uniquify`] | Keep both (add a number) or overwrite. Passed straight to `chrome.downloads`. |

### 3. Multiple download → file rendition

| Setting | What it does |
| --- | --- |
| **Quality** (`filePreference`) [`original`] | `original` downloads the file the site hosts; `sample` prefers the site's smaller sample rendition when it has one (`sampleUrl`), and silently falls back to the original when it does not. |

### 4. List results

| Setting | What it does |
| --- | --- |
| **Default media filter** [`all`] | `all` / `video` / `image` — what a fetch may add to the list. Still switchable per fetch in the listing card. |
| **Pages per fetch** [150] | Hard cap on one crawl (`all` or `50-` selections stop there). |
| **Queue rows shown** [60] | How many rows are drawn; the rest is summarised as "n more row(s) not drawn". Changes the view, never the run. |
| **Skip posts already downloaded** [on] | Posts found in the download history are not listed again. With it off, rows that are already saved show a `saved` badge. |

### 5. Search behaviour

| Setting | What it does |
| --- | --- |
| **Always append these tags** [`''`] | Global tag suffix, appended by the adapters to every search (de-duplicated case-insensitively, so typing a tag that is also in the suffix sends it once). |
| **Never show these tags** [`''`] | Tag blacklist. Each token becomes `-tag` (the portable spelling on all three sites) and is removed from the positive side of your own queries. |
| **Remember my searches** [on] | Recent queries appear under the search box and re-run with one click. |
| **Searches remembered per server** [12] | Trim limit per server; `None` also stops new ones being stored. |

### 6. Name template

| Setting | What it does |
| --- | --- |
| **Folder** [`booru/{siteType}`] | Directory template. Slashes nest; every segment is sanitised and traversal-proof. |
| **File name is built from** [`{id}_{md5}`] | One checkbox per token (`{id}`, `{md5}`, `{siteType}`, `{serverLabel}`, `{rating}`, `{score}`, `{artist}`, `{artists}`, `{character}`, `{copyright}`, `{tags}`, `{tagCount}`, `{date}`, `{year}`, `{month}`, `{width}`, `{height}`, `{ext}`, `{filename}`, `{isVideo}`, `{source}`). A template the checkboxes cannot express keeps its raw value in an editable box (custom templates are never silently rewritten). |
| **Example** | Live preview of `Downloads/<folder>/<name>` for a sample post, including the warnings the naming layer would raise. |
| **Tags in the filename** [5] | How many tags `{tags}`/`{artists}`/`{tagN}` expand to. |
| **Tag separator** [space] | Used when several tags are joined. |

### 7. Interface

| Setting | What it does |
| --- | --- |
| **Post thumbnails in the queue** [on] | Show a preview image per row (one preview request per row). |
| **Start downloading immediately** [on] | On: *Download selected* runs the queue. Off: it only adds rows — press Start on the dock. |
| **Download button on post pages** [on] | The floating content-script button. |

### 8. Advanced behaviour

| Setting | What it does |
| --- | --- |
| **Identify as this extension** [on] | Descriptive User-Agent via `declarativeNetRequest` (required by e621/Danbooru policy). |
| **Also send the `_client` parameter** [on] | Fallback for proxies that strip headers. |
| **Enforce the rating filter on download** [on] | Single-post downloads obey the same allow-list as listings. |
| **Host access** | Whether the browser granted the profile's origin, with a Grant button. |

### 9–10. Server credentials & download history

Export (with or without keys), import, "delete all keys", the download-history
counter with **Reset download history**, **Clear search history** and **Reset
every setting** (servers and keys are never touched by a reset).

## History notebooks

Two small stores (`src/core/history.ts`), both in `chrome.storage.local`:

- `bsm.history` — successful downloads keyed `serverId:postId` (label, filename,
  bytes, timestamp). Written by the queue on success, read by the listing card for
  "skip downloaded"/the `saved` badge. Capped at 5000 entries.
- `bsm.searches` — recent queries per server (capped by the setting, 500 overall).

Neither contains credentials. The only thing "Reset download history" changes is
what the *listing* shows; nothing is deleted from disk.

## Using the panel

1. Load the unpacked `dist/` folder, then click the toolbar icon (or *Open side
   panel* in the options rail). The panel opens next to the page.
2. On a booru page the context card names the matching profile; on a post page the
   "this post" card offers **Download this post** / **Add to list**.
3. Type tags, pick a page range, press **List this page** (or **Fetch selected
   pages** for a range). Rows appear ticked.
4. Untick what you do not want, then press **Download selected** on the dock. The
   queue keeps running while you browse — the status pill and the Queue badge stay
   live.
5. Deep links work: `panel.html#queue`, `#servers`, `#settings`.

### Preview harness

`npm run preview` serves the harness on <http://localhost:4173/>, which embeds
the real panel next to the options page and popup, backed by the offline mock
boorus. The buttons at the top switch the panel's simulated active tab
(`https://e621.net/posts/1500000` vs. a listing URL), which is exactly what the
panel reads in preview mode (`?tab=`).
