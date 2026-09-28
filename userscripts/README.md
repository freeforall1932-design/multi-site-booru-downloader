# Userscripts

Standalone userscripts that do one job on one site, kept here because they are
part of the same toolbox as the extension.

| Script | Site | What it does |
| --- | --- | --- |
| [`pawchive-link-collector.user.js`](pawchive-link-collector.user.js) | `pawchive.pw` | Crawls **every post of the creator page you are on**, harvests the off-site download links (Google Drive, Mega, MediaFire, Pixeldrain, …) and exports them as a `.txt`. |

## Pawchive Link Collector

Install it from Greasy Fork
(<https://greasyfork.org/en/scripts/596246-pawchive-link-collector>) or drop the
file into Tampermonkey/Violentmonkey by hand — `@grant none`, no build step, no
network requests of its own.

**How to use it**

1. Open a creator page, e.g. `https://pawchive.pw/fanbox/user/1245946`.
2. Click **Collect Downloads**. The panel walks the creator's pages, then opens
   each post and collects every link that looks like a download.
3. Press **Export .TXT** — the file lists the creator, the timestamp and one URL
   per line.

The gear button opens the filter settings: which **file formats** count (33 by
default), which **providers** count (21 by default), plus custom entries for
both, request delay, concurrency and retry budget. Settings and the panel
position are stored in `localStorage` on `pawchive.pw` only.

**Note on the copy in this repository**

The paste that added this file arrived with one mangled regular expression in
`looksLikeDownload()` (`\vert{}` where a `|` alternation belongs, an artifact of
sharing code through chat). It is restored here, so the `/download/…` and
`/attachment/…` path rule actually matches. Everything else is byte-for-byte the
published script.

## How this relates to the extension

The same job is built into Booru Server Manager's **Links** tab, where it:

- reads the creator through the site's own JSON API instead of the HTML pages of
  the creator/post views (no scraping, no breakage when a theme changes);
- keeps the collected links in `chrome.storage.local`, so they survive a reload;
- can push the links it finds straight into the download queue, with the
  extension's concurrency and request-spacing controls;
- imports a `.txt` back in (the same format it exports) to rebuild a queue;
- imports a **task package** — a `.json` manifest plus the `.txt` — which comes
  back as one task per creator with a file list, a completion summary and a run
  history, so a list can be handed to someone else with the same extension.

The userscript remains the zero-install option — it needs nothing but the site
open in a browser that runs userscripts.
