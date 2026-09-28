/**
 * Page-range parsing for the listing card.
 *
 * The side panel offers the same two gestures as the Rule 34 side panel: a
 * simple **From … To** row and an **advanced** box that takes exact pages
 * (`2,4,6-10`). Everything is parsed here, without touching the DOM, so the
 * behaviour is testable and identical everywhere.
 *
 * Grammar (`advanced` box):
 *
 * ```
 * all          every page from the first one until the listing runs out
 * 7            a single page
 * 2,4,6-10     a list, with ranges
 * 1-99         bounded range
 * 50-          open range: from page 50 until the listing runs out
 * ```
 *
 * A fetch is capped by `maxPages` (Settings → "Pages per fetch") exactly like the
 * sister extensions limit one crawl, so a typo cannot queue 10 000 pages.
 */

export type PageSelection =
  /** Explicit, ascending, de-duplicated page numbers. */
  | { kind: 'list'; pages: number[] }
  /** Walk forward from `start` until a page comes back empty or the cap is hit. */
  | { kind: 'open'; start: number }
  /** Walk from page 1 until a page comes back empty or the cap is hit. */
  | { kind: 'all' };

export interface PageRangeOptions {
  /** Hard cap on how many pages one selection may cover. */
  maxPages: number;
  /** Highest page number the site could have (sanity bound for open ranges). */
  maxPage?: number;
}

export type PageRangeResult = { ok: true; selection: PageSelection; label: string; pages: number } | { ok: false; message: string };

const DEFAULT_MAX_PAGE = 5000;

export function parsePageRange(input: string, options: PageRangeOptions): PageRangeResult {
  const maxPages = Math.max(1, Math.floor(options.maxPages));
  const maxPage = Math.max(1, Math.floor(options.maxPage ?? DEFAULT_MAX_PAGE));
  const text = (input ?? '').trim().toLowerCase();

  if (!text || text === 'all') {
    return { ok: true, selection: { kind: 'all' }, label: 'all pages from the first one', pages: maxPages };
  }

  const open = /^(\d+)\s*-\s*$/.exec(text);
  if (open) {
    const start = Number(open[1]);
    if (!Number.isFinite(start) || start < 1) return { ok: false, message: `"${input}" is not a page number.` };
    if (start > maxPage) return { ok: false, message: `Page ${start} is beyond the ${maxPage}-page sanity limit.` };
    return { ok: true, selection: { kind: 'open', start }, label: `pages ${start} to the end`, pages: maxPages };
  }

  const requested = new Set<number>();
  let openEnded = false;
  for (const chunk of text.split(',').map((part) => part.trim()).filter(Boolean)) {
    const range = /^(\d+)\s*-\s*(\d+)$/.exec(chunk);
    if (range) {
      const from = Number(range[1]);
      const to = Number(range[2]);
      if (from < 1 || to < 1) return { ok: false, message: `Page numbers start at 1 ("${chunk}").` };
      if (to < from) return { ok: false, message: `"${chunk}" counts backwards - write it as ${to}-${from}.` };
      if (to - from + 1 > maxPages) return { ok: false, message: `"${chunk}" covers ${to - from + 1} pages; the limit is ${maxPages}.` };
      for (let page = from; page <= to; page += 1) requested.add(page);
      continue;
    }
    if (/^\d+$/.test(chunk)) {
      const page = Number(chunk);
      if (page < 1) return { ok: false, message: `Page numbers start at 1 ("${chunk}").` };
      requested.add(page);
      continue;
    }
    if (chunk === 'all') {
      openEnded = true;
      continue;
    }
    return { ok: false, message: `"${chunk}" is not a page or page range (try 7, 2-5 or 10-).` };
  }

  const pages = [...requested].sort((a, b) => a - b).filter((page) => page <= maxPage);
  if (!pages.length) {
    return openEnded
      ? { ok: true, selection: { kind: 'all' }, label: 'all pages from the first one', pages: maxPages }
      : { ok: false, message: 'No pages selected.' };
  }
  if (pages.length > maxPages) {
    return { ok: false, message: `That is ${pages.length} pages; one fetch is limited to ${maxPages} (Settings → Pages per fetch).` };
  }
  return {
    ok: true,
    selection: { kind: 'list', pages },
    label: pages.length === 1 ? `page ${pages[0]}` : `${pages.length} pages (${pages[0]}–${pages[pages.length - 1]})`,
    pages: pages.length,
  };
}

/** Build the advanced syntax from the simple From/To row. */
export function fromToRange(from: string, to: string): string {
  const start = (from ?? '').trim();
  const end = (to ?? '').trim();
  if (!start && !end) return '';
  if (!start) return `1-${end}`;
  if (!end) return `${start}-`;
  return `${start}-${end}`;
}

/** Fill a reviewable batch: the first `count` pages starting at `from`. */
export function batchRange(from: string, count: number): string {
  const start = Math.max(1, Number.parseInt((from ?? '').trim() || '1', 10) || 1);
  const size = Math.max(1, Math.floor(count));
  return `${start}-${start + size - 1}`;
}
