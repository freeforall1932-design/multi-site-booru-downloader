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
│  Browse │ Queue ③ │ Links │ Servers │ Settings            │  tab strip (deep links:
├───────────────────────────────────────────────────────────┤  #browse #queue #links
│ ●  e621 post #1500000 — default profile             [Use…]│  #servers #settings)
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
| **Links** | The mirror-link collector: pick a creator-archive profile, type the creator, collect the off-site download links from every post, group/tick/queue them, export or import the list as `.txt` | `links/list`, `links/posts`, `links/scanPost`, `links/queue`, `links/remove`, `links/clear`, `links/export`, `links/import`, `queue/enqueueLinks` |
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

A mirror link is a queue row too, with two deliberate differences: it is keyed by
its **URL** (`link:<url>`) instead of `serverId:postId`, and it skips the adapter,
the post lookup and the rating filter - the file is off-site, so there is nothing
to look up. That is what lets an imported `.txt` download on a fresh profile with
no server configured at all, and why the same URL can never be queued twice.

## Settings reference

All of these live in `chrome.storage.local` and are shared by the panel, the
options page and the popup. Defaults in brackets.

### 1. Global behaviour

| Setting | What it does |
| --- | --- |
| **Toolbar click opens** [`sidepanel`] | `sidepanel` = docked panel (`openPanelOnActionClick`), `popup` = the classic popup page. The worker re-applies the stored choice every time it starts, so a `popup` profile keeps its popup. |
| **Panel opens on** [`browse`] | Which tab the panel shows when it opens (`browse`, `queue`, `links`, `servers`, `settings`). |
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

### 2b. Mirror links

| Setting | What it does |
| --- | --- |
| **Only download-looking links** [`downloads`] | What the collector keeps. `downloads` = known file hosts plus URLs that end in a file extension, look like a download (`/download/…`, `?file=…`) or carry a long hash; `any` = every external link, for posts that link somewhere unusual. Links on the site itself are never collected - those are ordinary post downloads. |
| **Extra provider hosts** [`''`] | Which hosts count as providers, space or comma separated. A subdomain counts too (`mega.nz` matches `www.mega.nz`). |
| **Mirror folder template** [`mirrors/{host}`] | Where link rows are saved. Its tokens describe a URL, not a post: `{host}`, `{provider}`, `{siteType}`, `{filename}`, `{ext}`, `{date}`. |

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

## Links tab (mirror links)

Creator archives (Kemono, Coomer, Pawchive) are the family where the real file is
usually *not* on the site: the post body says "the pack is on Mega" and links
there. Those URLs are not booru posts, so the ordinary listing cannot see them -
the Links tab is built for exactly that gap.

```
Links
[ Profile: Pawchive (demo creator) ▾ ]   archive API
[ fanbox/1245946                                    ]
[ Collect links ]  [ Stop ]
▓▓▓▓▓▓▓░░░░  Scanned 41/58 · 63 found · 12 already known
  96 collected   61 to download   35 downloaded   3 failed
☑ Select all   Invert   Remove selected (12)
[ Add to download queue (12) ]  [ Export .txt ]  [ Import .txt… ]
Export as [ Grouped by post ▾ ]        filter: downloads only
─ Post: September art pack (v2) — …/post/12674481     3 link(s)
  ☑ [Mega] September art pack (v2)   https://mega.nz/file/…
  ☑ [Google Drive] …                 https://drive.google.com/file/d/…
─ Post: Sketch dump — week 35                2 link(s)
  …
```

**Collecting.** Pick a profile whose site type is a creator archive, type a
creator (`service/creatorId`, or paste the creator page's URL - `tag:name` and
plain text work too), then press *Collect links*. The panel asks the worker for
one listing page (`links/posts`) and then walks the posts one at a time
(`links/scanPost`), showing progress as it goes; *Stop* finishes the post in
flight and keeps everything found so far. Every URL found in a post body
(`content` HTML, attachments, `source` fields - the scan is payload-wide, not
site-specific) is tested against the rules above and stored in `bsm.links`.
Collecting the same creator again is safe: a URL is merged by id, so a re-scan
never duplicates a row and never hides a file you already downloaded.

**Queuing.** Ticked rows go to the queue with *Add to download queue* - one `link`
row per URL, downloaded with the same concurrency and spacing as every other row.
Hosting pages that need a login or run a script (Drive folders, Mega landing
pages) fail honestly: the row keeps the error and the exported file stays the
reliable path for those.

**Exchanging the list.** *Export .txt* writes the whole collected list - grouped
by post (default), grouped by provider, or as a plain URL list - to
`<creator>_download_links.txt`:

```
# Booru Server Manager — collected mirror links
# Creator: fanbox/1245946
# Server: Pawchive (demo creator) (https://pawchive.pw)
# Exported: 2026-09-29T10:00:00.000Z
# Total: 6 link(s), grouped by post
#
# One URL per line. Feed this file back in with "Import .txt" to rebuild the list.

# Post: September art pack (v2) — https://pawchive.pw/fanbox/user/1245946/post/12674481
- https://mega.nz/file/AbCdEf12#9hIjKlMnOpQrStUvWxYz0123456789
- https://drive.google.com/file/d/1AbCdEfGhIjKlMnOpQrStUvWxYz/view?usp=sharing
```

*Import .txt…* opens a file picker and reads the same shape back - its own
export, a hand-written list or one the
[Pawchive Link Collector userscript](../userscripts/README.md) wrote (its
`Creator:`/`Collected:` preamble is ignored, bullets are stripped, `#` lines are
comments). Imported URLs are added with no provider filter and no own-host rule -
a file you hand over *is* the instruction - and the post title from each section
header is kept, so the imported rows still group by post. Use the `queue: true`
path (the panel's *Import .txt…* and *Add to download queue*) to go straight from
the file to downloads.

**Storing.** `bsm.links` holds the list (`src/core/link-store.ts`, capped at
20,000 rows). Like the queue, it is a real `chrome.storage.local` list: closing
the panel, reloading the page or restarting the worker keeps it, and a row
remembers its download outcome (`new` → `queued` → `done`/`failed`, the saved
filename and the error) so re-importing the same file does not re-download what
you already have. *Clear downloaded* and *Clear list* are the two ways to trim it;
removing a link also drops its pending queue rows (a running download is never
killed).

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
