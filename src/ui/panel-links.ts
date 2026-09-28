/**
 * The Links tab - the collector for off-site (mirror) download links.
 *
 * Creator archives such as Pawchive, Kemono and Coomer put their real downloads
 * on Google Drive, Mega, MediaFire and friends; the post body carries those URLs
 * and nothing else. This card walks a creator through the site's own API, keeps
 * every link it finds in `bsm.links`, and offers the three things a link list is
 * for:
 *
 *   1. **Export** it as a grouped `.txt` (by post or by provider) for an
 *      external download manager, or as a plain URL list someone can read.
 *   2. **Import** that same `.txt` back - the file round-trips - and go straight
 *      to the download queue with it.
 *   3. **Queue** selected links so the extension downloads them itself, slowly,
 *      with the concurrency and request-spacing controls from Settings.
 *
 * The card keeps the panel's house style: one status strip, the toolbar row,
 * then the rows. It never talks to a site and never touches storage: everything
 * goes through `UiRequest`s handled by the background worker.
 */
import type { MirrorLink, MirrorLinkFilter, MirrorLinkStats, ServerConfigView } from '../shared/types.js';
import { formatBytes, relativeTime } from '../shared/util.js';
import { button, h } from './dom.js';

export type LinkExportGrouping = 'post' | 'provider' | 'plain';

export interface MirrorLinkRow {
  link: MirrorLink;
  /** Row context resolved by the panel (the link only stores ids). */
  serverLabel: string | null;
  selected: boolean;
}

export interface MirrorLinkGroup {
  key: string;
  title: string;
  postUrl: string | null;
  rows: MirrorLinkRow[];
}

export interface LinksState {
  servers: ServerConfigView[];
  serverId: string | null;
  query: string;
  /** True when the selected profile is a creator archive (Kemono family). */
  canCollect: boolean;
  busy: boolean;
  progress: { done: number; total: number; label: string } | null;
  error: { message: string; kind: string; hint: string | null } | null;
  stats: MirrorLinkStats;
  groups: MirrorLinkGroup[];
  selectedCount: number;
  /** Rows not drawn because of the row limit. */
  hiddenRows: number;
  exportGrouping: LinkExportGrouping;
  filterMode: MirrorLinkFilter;
  /** Duplicate URLs already in the queue, for the notice line. */
  queuePending: number;
}

export interface LinksCallbacks {
  onServerChange(serverId: string): void;
  onQueryChange(query: string): void;
  onCollect(): void;
  onStop(): void;
  onToggleRow(id: string): void;
  onSelectAll(select: boolean): void;
  onInvert(): void;
  onAddToQueue(ids: string[]): void;
  onExport(grouping: LinkExportGrouping): void;
  onImport(text: string, filename: string): void;
  onRemoveSelected(ids: string[]): void;
  onRemoveRow(id: string): void;
  onClear(scope: 'all' | 'done' | 'pending'): void;
  onOpenLink(url: string): void;
  onOpenPost(url: string): void;
  onOpenQueue(): void;
  onOpenSettings(): void;
}

const STATUS_LABELS: Record<MirrorLink['status'], string> = {
  new: 'not downloaded',
  queued: 'in the queue',
  done: 'downloaded',
  failed: 'failed',
};

export function renderLinksCard(container: HTMLElement, state: LinksState, callbacks: LinksCallbacks): void {
  container.replaceChildren();
  container.className = 'psCard psLinksCard';

  const server = state.servers.find((entry) => entry.id === state.serverId) ?? null;
  // Kept so the query box can enable/disable *Collect links* as you type, without
  // re-rendering the card (which would steal the caret).
  let collectButton: HTMLButtonElement | null = null;

  // ------------------------------------------------------------------ header
  container.appendChild(
    h(
      'div',
      { class: 'psLinksHead' },
      h('h2', { text: 'Mirror links' }),
      h('span', {
        class: 'psMuted psSmall',
        text: 'Collects the off-site download links inside a creator’s posts (Drive, Mega, MediaFire, …) and keeps them until you export or queue them.',
      }),
    ),
  );

  // ------------------------------------------------------------------ source
  const serverRow = h('div', { class: 'psServerRow' });
  serverRow.appendChild(h('span', { class: 'psMuted psSmall', text: 'Profile' }));
  const select = h('select', {
    class: 'psSelect',
    title: 'Which saved profile the links are collected with. Creator archives only - the mirror links live in their post bodies.',
    on: {
      change: (event) => callbacks.onServerChange((event.target as HTMLSelectElement).value),
    },
  });
  if (!state.servers.length) select.appendChild(h('option', { value: '', text: 'No profile saved yet' }));
  for (const entry of state.servers) {
    select.appendChild(
      h('option', {
        value: entry.id,
        text: `${entry.label} · ${entry.siteType}`,
        selected: entry.id === state.serverId,
      }),
    );
  }
  serverRow.appendChild(select);
  serverRow.appendChild(
    h('span', {
      class: 'psMuted psSmall',
      text: state.canCollect ? 'archive API' : 'pick a Kemono/Pawchive/Coomer profile',
    }),
  );
  container.appendChild(serverRow);

  const searchRow = h('div', { class: 'psSearchRow' });
  searchRow.appendChild(
    h('input', {
      class: 'psInput',
      id: 'psLinksQuery',
      type: 'text',
      placeholder: 'creator: fanbox/1245946, a creator URL, tag:name or a title',
      value: state.query,
      autocomplete: 'off',
      spellcheck: 'false',
      on: {
        input: (event) => {
          const value = (event.target as HTMLInputElement).value;
          callbacks.onQueryChange(value);
          if (collectButton) collectButton.disabled = !value.trim() || !state.canCollect || !state.serverId || state.busy;
        },
        keydown: (event) => {
          if ((event as KeyboardEvent).key === 'Enter' && state.canCollect && !state.busy) {
            event.preventDefault();
            callbacks.onCollect();
          }
        },
      },
    }),
  );
  container.appendChild(searchRow);
  container.appendChild(
    h('p', {
      class: 'psHint',
      text: 'Tip: open the creator page in the tab you came from and paste its address here, or type `service/creatorId` as the site shows it.',
    }),
  );

  // --------------------------------------------------------------- action row
  collectButton = button(state.busy ? 'Collecting…' : 'Collect links', {
    variant: 'primary',
    disabled: state.busy || !state.canCollect || !state.serverId || !state.query.trim(),
    onClick: () => callbacks.onCollect(),
  }) as HTMLButtonElement;
  container.appendChild(
    h(
      'div',
      { class: 'psActionRow' },
      collectButton,
      button('Stop', {
        variant: 'danger',
        disabled: !state.busy,
        title: 'Stop after the post being scanned right now. Everything collected so far is kept.',
        onClick: () => callbacks.onStop(),
      }),
    ),
  );

  if (state.progress) {
    const percent = state.progress.total ? Math.round((state.progress.done / state.progress.total) * 100) : 0;
    container.appendChild(
      h(
        'div',
        { class: 'psProgress' },
        h('div', { class: 'psBar' }, h('i', { style: `width: ${percent}%` })),
        h('span', { class: 'psMuted psSmall', text: state.progress.label }),
      ),
    );
  }

  if (state.error) {
    container.appendChild(
      h(
        'div',
        { class: 'psError' },
        h('strong', { text: state.error.kind }),
        h('p', { text: state.error.message }),
        state.error.hint ? h('small', { class: 'psHint', text: state.error.hint }) : null,
      ),
    );
  }

  // ------------------------------------------------------------------ summary
  container.appendChild(
    h(
      'div',
      { class: 'psSummary' },
      summaryTile(state.stats.total, 'collected'),
      summaryTile(state.stats.new, 'to download'),
      summaryTile(state.stats.done, 'downloaded'),
      summaryTile(state.stats.failed, 'failed'),
    ),
  );

  // ------------------------------------------------------------------ toolbar
  const allSelected = state.groups.length > 0 && state.groups.every((group) => group.rows.every((row) => row.selected));
  container.appendChild(
    h(
      'div',
      { class: 'psToolbar' },
      h(
        'label',
        { class: 'psCheck' },
        h('input', {
          type: 'checkbox',
          checked: allSelected,
          on: { change: (event) => callbacks.onSelectAll((event.target as HTMLInputElement).checked) },
        }),
        h('span', { text: 'Select all' }),
      ),
      h('button', { class: 'psTextButton', type: 'button', text: 'Invert', onClick: () => callbacks.onInvert() }),
      h('button', {
        class: 'psTextButton',
        type: 'button',
        text: `Remove selected (${state.selectedCount})`,
        disabled: state.selectedCount === 0,
        title: 'Drop these links from the collected list (and their pending queue rows).',
        onClick: () => callbacks.onRemoveSelected(selectedIds(state)),
      }),
    ),
  );

  const selectedNow = selectedIds(state);
  container.appendChild(
    h(
      'div',
      { class: 'psToolbar psToolbarSecondary' },
      button(`Add to download queue (${state.selectedCount})`, {
        variant: 'subtle',
        disabled: state.selectedCount === 0,
        title: 'Queue the ticked links. They download one at a time at the request spacing from Settings.',
        onClick: () => callbacks.onAddToQueue(selectedNow),
      }),
      button('Export .txt', {
        title: 'Write the whole collected list to a text file (grouped by post by default).',
        disabled: state.stats.total === 0,
        onClick: () => callbacks.onExport(state.exportGrouping),
      }),
      button('Import .txt…', {
        title: 'Read a list back in - the file this tab exports, or one the userscript wrote.',
        onClick: () => pickFile((text, name) => callbacks.onImport(text, name)),
      }),
    ),
  );

  const groupingRow = h('div', { class: 'psRangeRow' });
  groupingRow.appendChild(h('label', { class: 'psRangeLabel', text: 'Export as' }));
  const grouping = h('select', {
    class: 'psSelect',
    title: 'How the exported file groups its lines.',
    on: { change: (event) => callbacks.onExport((event.target as HTMLSelectElement).value as LinkExportGrouping) },
  });
  for (const option of [
    { value: 'post', label: 'Grouped by post' },
    { value: 'provider', label: 'Grouped by provider' },
    { value: 'plain', label: 'Plain URL list' },
  ] as const) {
    grouping.appendChild(h('option', { value: option.value, text: option.label, selected: option.value === state.exportGrouping }));
  }
  groupingRow.appendChild(grouping);
  groupingRow.appendChild(
    h('button', {
      class: 'psTextButton',
      type: 'button',
      text: `filter: ${state.filterMode === 'any' ? 'every external link' : 'downloads only'}`,
      title: 'Which links the collector keeps. Change it under Settings → Mirror links.',
      onClick: () => callbacks.onOpenSettings(),
    }),
  );
  container.appendChild(groupingRow);

  if (state.stats.total) {
    container.appendChild(
      h(
        'div',
        { class: 'psToolbar psToolbarSecondary' },
        h('button', {
          class: 'psTextButton',
          type: 'button',
          text: `Clear downloaded (${state.stats.done})`,
          disabled: state.stats.done === 0,
          onClick: () => callbacks.onClear('done'),
        }),
        h('button', {
          class: 'psTextButton danger',
          type: 'button',
          text: 'Clear list',
          title: 'Forget every collected link. Queued rows are dropped from the download queue as well.',
          onClick: () => callbacks.onClear('all'),
        }),
      ),
    );
  }

  if (state.queuePending) {
    container.appendChild(
      h(
        'p',
        { class: 'psHint' },
        h('span', { text: `${state.queuePending} link(s) are waiting in the download queue. ` }),
        h('button', { class: 'psTextButton', type: 'button', text: 'Open the queue', onClick: () => callbacks.onOpenQueue() }),
      ),
    );
  }

  // -------------------------------------------------------------------- rows
  if (!state.groups.length) {
    container.appendChild(
      h(
        'div',
        { class: 'psEmptyState' },
        h('div', { class: 'psEmptyIcon', text: '⛓' }),
        h('h2', { text: 'No links collected yet' }),
        h('p', {
          class: 'psMuted',
          text: state.busy
            ? 'Scanning… links appear here as they are found.'
            : 'Pick a Kemono/Coomer/Pawchive profile, paste the creator, then press Collect links. You can also import a .txt written by this tab or by the Pawchive Link Collector userscript.',
        }),
      ),
    );
    return;
  }

  const list = h('div', { class: 'psQueue psLinkGroups' });
  for (const group of state.groups) {
    const header = h('div', { class: 'psLinkGroup' });
    header.appendChild(
      h('span', {
        class: 'psLinkGroupTitle',
        text: group.title,
        title: group.title,
      }),
    );
    if (group.postUrl) {
      header.appendChild(
        h('a', {
          class: 'psMuted psSmall',
          href: group.postUrl,
          target: '_blank',
          rel: 'noreferrer',
          text: 'open post',
          onClick: (event) => {
            event.preventDefault();
            callbacks.onOpenPost(group.postUrl!);
          },
        }),
      );
    }
    header.appendChild(h('span', { class: 'psMuted psSmall', text: `${group.rows.length} link(s)` }));
    list.appendChild(header);
    for (const row of group.rows) list.appendChild(linkRow(row, callbacks));
  }
  container.appendChild(list);

  if (state.hiddenRows > 0) {
    container.appendChild(
      h('div', { class: 'psQueueMore', text: `${state.hiddenRows} more link(s) not drawn (Settings → Queue rows shown)` }),
    );
  }

  container.appendChild(
    h('p', {
      class: 'psHint',
      text: 'Hosting pages that need a login or a script (Drive folders, Mega landing pages) cannot be fetched by a browser download: the row will say so, and the exported .txt is the reliable path for those.',
    }),
  );
}

function selectedIds(state: LinksState): string[] {
  return state.groups.flatMap((group) => group.rows.filter((row) => row.selected).map((row) => row.link.id));
}

function summaryTile(value: number, label: string): HTMLElement {
  return h('div', {}, h('strong', { text: String(Math.max(0, value)) }), h('span', { text: label }));
}

function linkRow(row: MirrorLinkRow, callbacks: LinksCallbacks): HTMLElement {
  const { link } = row;
  const element = h('div', {
    class: `psQueueItem psStatus-${link.status === 'done' ? 'done' : link.status === 'failed' ? 'failed' : link.status === 'queued' ? 'running' : 'pending'}${
      row.selected ? ' psSelected' : ''
    }`,
    dataset: { linkId: link.id },
  });

  element.appendChild(
    h('input', {
      type: 'checkbox',
      class: 'psRowCheck',
      checked: row.selected,
      title: 'Include this link in the next "Add to download queue"',
      on: { change: () => callbacks.onToggleRow(link.id) },
    }),
  );

  const info = h('div', { class: 'psItemInfo' });
  const title = h('div', { class: 'psItemTitle' });
  title.appendChild(h('span', { class: 'psTypeBadge server', text: link.provider }));
  if (link.postTitle) title.appendChild(h('span', { class: 'psMuted psSmall psTruncate', text: link.postTitle, title: link.postTitle }));
  if (link.status === 'done' && link.filename) {
    title.appendChild(h('span', { class: 'psTypeBadge saved', text: 'saved', title: `Saved as ${link.filename}` }));
  }
  info.appendChild(title);

  info.appendChild(
    h(
      'div',
      { class: 'psItemMeta' },
      h('a', {
        class: 'psFile',
        href: link.url,
        target: '_blank',
        rel: 'noreferrer',
        text: shortenUrl(link.url, 88),
        title: link.url,
        onClick: (event) => {
          event.preventDefault();
          callbacks.onOpenLink(link.url);
        },
      }),
    ),
  );

  const meta = h('div', { class: 'psItemMeta' });
  if (link.host) meta.appendChild(h('span', { class: 'psMuted psSmall', text: link.host }));
  if (link.bytes !== null) meta.appendChild(h('span', { class: 'psMuted psSmall', text: formatBytes(link.bytes) }));
  if (link.attempts > 0) meta.appendChild(h('span', { class: 'psMuted psSmall', text: `attempt ${link.attempts}` }));
  if (link.status !== 'new') meta.appendChild(h('span', { class: 'psMuted psSmall', text: relativeTime(link.updatedAt) }));
  if (row.serverLabel) meta.appendChild(h('span', { class: 'psMuted psSmall', text: row.serverLabel }));
  info.appendChild(meta);

  if (link.error) {
    info.appendChild(h('div', { class: 'psItemError', title: link.error }, h('span', { class: 'psTruncate', text: link.error })));
  }
  element.appendChild(info);

  const right = h('div', { class: 'psItemRight' });
  right.appendChild(h('span', { class: `psItemStatus ${link.status === 'queued' ? 'running' : link.status}`, text: STATUS_LABELS[link.status] }));
  const actions = h('div', { class: 'psItemActions' });
  actions.appendChild(
    h('button', {
      class: 'psRowButton',
      type: 'button',
      text: '✕',
      title: 'Remove this link from the collected list (the file on disk is not touched)',
      ariaLabel: 'Remove this link',
      onClick: () => callbacks.onRemoveRow(link.id),
    }),
  );
  right.appendChild(actions);
  element.appendChild(right);
  return element;
}

function shortenUrl(url: string, max: number): string {
  if (url.length <= max) return url;
  const head = url.slice(0, Math.floor(max / 2) - 1);
  const tail = url.slice(-Math.floor(max / 2));
  return `${head}…${tail}`;
}

/**
 * Open a file picker and hand the text to the caller.
 *
 * Same pattern as the settings screen's server import: a hidden `<input
 * type="file">`, `file.text()`, no network and no form submission.
 */
export function pickFile(onText: (text: string, filename: string) => void): void {
  const input = h('input', {
    type: 'file',
    accept: '.txt,text/plain',
    style: 'display:none',
  });
  input.addEventListener('change', () => {
    const file = input.files?.[0];
    if (!file) return;
    void file
      .text()
      .then((text) => onText(text, file.name))
      .catch(() => onText('', file.name))
      .finally(() => input.remove());
  });
  document.body.appendChild(input);
  input.click();
}
