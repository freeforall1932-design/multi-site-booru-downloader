/**
 * The panel's list of rows - the vertical, tickable queue used by both the
 * Browse tab (review before downloading) and the Queue tab (progress).
 *
 * Row anatomy, borrowed from the sister side panels:
 *
 *   [x] [thumb]  #1500000  [video] [saved]        completed    [×]
 *                e621 · 4.2 MB · bsm/folder/file.jpg
 *                [====== progress ======]
 *
 * Everything is drawn from the core queue: a row *is* a persisted `QueueItem`,
 * so closing the panel never loses work and a service-worker restart resumes it.
 */
import type { QueueItem, QueueSummary, Rating, ServerConfigView } from '../shared/types.js';
import { formatBytes, relativeTime } from '../shared/util.js';
import { button, h } from './dom.js';
import { kindLabel, ratingChip } from './format.js';

export interface QueueRowView {
  item: QueueItem;
  server: ServerConfigView | null;
  /** Row is ticked for the next download run. */
  selected: boolean;
  /** Post was downloaded before this run (found in the history notebook). */
  savedFilename: string | null;
  /** Preview image ('' when thumbnails are off or unknown). */
  thumbnail: string;
  media: 'video' | 'image';
  /** Canonical rating of the post (shown as a chip so the list stays rating-aware). */
  rating: Rating | null;
}

export interface QueueListState {
  summary: QueueSummary;
  rows: QueueRowView[];
  /** Rows hidden by the row limit, for the "n more" footer. */
  hiddenRows: number;
  /** Rows hidden by the media filter, for the footer too. */
  filteredRows: number;
  showThumbnails: boolean;
  busy: boolean;
}

export interface QueueListCallbacks {
  onToggleRow(itemId: string): void;
  onSelectAll(select: boolean): void;
  onInvert(): void;
  onRemoveSelected(): void;
  onRemoveRow(itemId: string): void;
  onCancelRow(itemId: string): void;
  onRetryFailed(): void;
  onClearFinished(): void;
  onResetHistory(): void;
  onClearList(): void;
  onOpenPost(item: QueueItem): void;
}

export function renderQueueList(container: HTMLElement, state: QueueListState, callbacks: QueueListCallbacks): void {
  container.replaceChildren();
  container.className = 'psCard psQueueCard';
  const { summary } = state;

  // --------------------------------------------------------------- summary
  container.appendChild(
    h(
      'div',
      { class: 'psSummary' },
      summaryTile(summary.total - summary.done - summary.failed - summary.skipped - summary.canceled, 'listed'),
      summaryTile(state.rows.filter((row) => row.selected).length, 'selected'),
      summaryTile(summary.done, 'completed'),
    ),
  );

  // --------------------------------------------------------------- toolbars
  const selectedCount = state.rows.filter((row) => row.selected).length;
  container.appendChild(
    h(
      'div',
      { class: 'psToolbar' },
      h(
        'label',
        { class: 'psCheck' },
        h('input', {
          type: 'checkbox',
          checked: state.rows.length > 0 && selectedCount === state.rows.length,
          on: {
            change: (event) => callbacks.onSelectAll((event.target as HTMLInputElement).checked),
          },
        }),
        h('span', { text: 'Select all' }),
      ),
      h('button', { class: 'psTextButton', type: 'button', text: 'Invert', title: 'Invert the selection of the rows shown', onClick: () => callbacks.onInvert() }),
      h('button', {
        class: 'psTextButton',
        type: 'button',
        text: `Remove selected (${selectedCount})`,
        disabled: selectedCount === 0,
        title: 'Delete the ticked rows from this list. Nothing is deleted from disk.',
        onClick: () => callbacks.onRemoveSelected(),
      }),
    ),
  );

  container.appendChild(
    h(
      'div',
      { class: 'psToolbar psToolbarSecondary' },
      h('button', {
        class: 'psTextButton',
        type: 'button',
        text: `Retry failed (${summary.failed + summary.skipped})`,
        disabled: summary.failed + summary.skipped === 0,
        onClick: () => callbacks.onRetryFailed(),
      }),
      h('button', {
        class: 'psTextButton',
        type: 'button',
        text: 'Clear finished',
        title: 'Remove completed and failed rows, keep the rest.',
        onClick: () => callbacks.onClearFinished(),
      }),
      h('button', {
        class: 'psTextButton',
        type: 'button',
        text: 'Reset history',
        title: 'Forget which posts were already downloaded, so they can be listed again.',
        onClick: () => callbacks.onResetHistory(),
      }),
      h('button', { class: 'psTextButton danger', type: 'button', text: 'Clear list', onClick: () => callbacks.onClearList() }),
    ),
  );

  // -------------------------------------------------------------- empty state
  if (!state.rows.length) {
    container.appendChild(
      h(
        'div',
        { class: 'psEmptyState' },
        h('div', { class: 'psEmptyIcon', text: '↓' }),
        h('h2', { text: state.filteredRows ? 'Nothing matches this filter' : 'Nothing listed yet' }),
        h('p', {
          class: 'psMuted',
          text: state.filteredRows
            ? 'Switch the media filter back to "Pics + videos" to see the rows that are hidden right now.'
            : 'Open a booru page and press List this page, or type tags in the search box above, then press Fetch selected pages.',
        }),
      ),
    );
    return;
  }

  // -------------------------------------------------------------------- rows
  const list = h('div', { class: 'psQueue' });
  for (const row of state.rows) list.appendChild(queueRow(row, state, callbacks));
  container.appendChild(list);

  if (state.hiddenRows > 0 || state.filteredRows > 0) {
    const bits: string[] = [];
    if (state.filteredRows) bits.push(`${state.filteredRows} hidden by the media filter`);
    if (state.hiddenRows) bits.push(`${state.hiddenRows} more row(s) not drawn (Settings → Queue rows shown)`);
    container.appendChild(h('div', { class: 'psQueueMore', text: bits.join(' · ') }));
  }
}

function summaryTile(value: number, label: string): HTMLElement {
  return h('div', {}, h('strong', { text: String(Math.max(0, value)) }), h('span', { text: label }));
}

function queueRow(row: QueueRowView, state: QueueListState, callbacks: QueueListCallbacks): HTMLElement {
  const { item } = row;
  const finished = item.status === 'done';
  const element = h('div', {
    class: `psQueueItem psStatus-${item.status}${row.selected ? ' psSelected' : ''}`,
    dataset: { itemId: item.id },
  });

  element.appendChild(
    h('input', {
      type: 'checkbox',
      class: 'psRowCheck',
      checked: row.selected,
      disabled: finished || item.status === 'running' || item.status === 'canceled',
      title: finished ? 'Already downloaded' : 'Include this post in the next download run',
      on: { change: () => callbacks.onToggleRow(item.id) },
    }),
  );

  if (state.showThumbnails) {
    element.appendChild(
      row.thumbnail
        ? h('img', {
            class: 'psThumb',
            src: row.thumbnail,
            alt: '',
            loading: 'lazy',
            on: { error: (event) => (event.target as HTMLImageElement).classList.add('psBroken') },
          })
        : h('span', { class: 'psThumb psThumbEmpty', text: row.media === 'video' ? '▶' : '▣', ariaLabel: 'no preview' }),
    );
  }

  const info = h('div', { class: 'psItemInfo' });
  const title = h('div', { class: 'psItemTitle' });
  title.appendChild(
    item.postUrl
      ? h('a', {
          href: item.postUrl,
          target: '_blank',
          rel: 'noreferrer',
          text: `#${item.postId}`,
          title: 'Open the post on the site',
          onClick: (event) => {
            event.preventDefault();
            callbacks.onOpenPost(item);
          },
        })
      : h('span', { text: `#${item.postId}` }),
  );
  title.appendChild(h('span', { class: `psTypeBadge${row.media === 'video' ? ' video' : ''}`, text: row.media === 'video' ? 'video' : 'pic' }));
  if (row.rating) title.appendChild(ratingChip(row.rating));
  if (row.server) title.appendChild(h('span', { class: 'psTypeBadge server', text: row.server.siteType }));
  if (row.savedFilename) {
    title.appendChild(h('span', { class: 'psTypeBadge saved', text: 'saved', title: `Already downloaded as ${row.savedFilename}` }));
  }
  info.appendChild(title);

  const meta = h('div', { class: 'psItemMeta' });
  const file = item.filename ?? row.savedFilename;
  if (file) meta.appendChild(h('span', { class: 'psFile', text: file, title: file }));
  if (item.bytes !== null) meta.appendChild(h('span', { text: formatBytes(item.bytes) }));
  if (item.attempts > 1) meta.appendChild(h('span', { text: `attempt ${item.attempts}` }));
  if (item.status === 'done' || item.status === 'failed') meta.appendChild(h('span', { text: relativeTime(item.updatedAt) }));
  info.appendChild(meta);

  if (item.error) {
    info.appendChild(
      h('div', { class: 'psItemError', title: item.error }, h('span', { text: kindLabel(item.errorKind ?? 'unknown') }), h('span', { class: 'psTruncate', text: item.error })),
    );
  }
  element.appendChild(info);

  const right = h('div', { class: 'psItemRight' });
  right.appendChild(h('span', { class: `psItemStatus ${item.status}`, text: statusLabel(item.status) }));
  if (item.status === 'running' || item.status === 'pending') {
    right.appendChild(h('div', { class: 'psItemProgress' }, h('i', { style: `width: ${item.status === 'running' ? '45%' : '0%'}` })));
  }
  const actions = h('div', { class: 'psItemActions' });
  if (item.status === 'pending' || item.status === 'running') {
    actions.appendChild(
      h('button', {
        class: 'psRowButton',
        type: 'button',
        text: '✕',
        title: 'Cancel this download',
        ariaLabel: 'Cancel this download',
        onClick: () => callbacks.onCancelRow(item.id),
      }),
    );
  } else {
    actions.appendChild(
      h('button', {
        class: 'psRowButton',
        type: 'button',
        text: '✕',
        title: 'Remove this row from the list (the file on disk is not touched)',
        ariaLabel: 'Remove this row',
        onClick: () => callbacks.onRemoveRow(item.id),
      }),
    );
  }
  right.appendChild(actions);
  element.appendChild(right);

  return element;
}

function statusLabel(status: QueueItem['status']): string {
  const labels: Record<QueueItem['status'], string> = {
    pending: 'queued',
    running: 'downloading',
    done: 'completed',
    failed: 'failed',
    skipped: 'skipped',
    canceled: 'canceled',
  };
  return labels[status];
}

/** Dock-level controls: concurrency, quality, run/stop. */
export interface DockState {
  concurrency: number;
  filePreference: 'original' | 'sample';
  selectedCount: number;
  pendingCount: number;
  running: boolean;
  paused: boolean;
  notice: { text: string; tone: 'plain' | 'ok' | 'error' } | null;
  busy: boolean;
}

export interface DockCallbacks {
  onConcurrencyChange(value: number): void;
  onFilePreferenceChange(value: 'original' | 'sample'): void;
  onDownloadSelected(): void;
  onStart(): void;
  onPause(): void;
  onResume(): void;
  onStop(): void;
}

export function renderDock(container: HTMLElement, state: DockState, callbacks: DockCallbacks): void {
  container.replaceChildren();
  container.className = 'psDock';

  const controls = h('div', { class: 'psDockControls' });

  const concurrency = h('div', { class: 'psConcurrency', title: 'Maximum active downloads. The remaining selected posts stay safely queued.' });
  concurrency.appendChild(h('span', { text: 'Downloads at once' }));
  const segmented = h('div', { class: 'psSegmented', attrs: { role: 'group', 'aria-label': 'Maximum concurrent downloads' } });
  for (const value of [1, 2, 3, 5]) {
    segmented.appendChild(
      h('button', {
        type: 'button',
        text: String(value),
        class: value === state.concurrency ? 'selected' : '',
        title: value === 3 ? 'Recommended default' : `${value} parallel download(s)`,
        onClick: () => callbacks.onConcurrencyChange(value),
      }),
    );
  }
  concurrency.appendChild(segmented);
  controls.appendChild(concurrency);

  const quality = h('label', { class: 'psConcurrency' });
  quality.appendChild(h('span', { text: 'Quality' }));
  const select = h('select', {
    class: 'psSelect psCompact',
    title: 'Original files are what the site hosts; the sample is a smaller, faster version when the site offers one.',
    on: { change: (event) => callbacks.onFilePreferenceChange((event.target as HTMLSelectElement).value as 'original' | 'sample') },
  });
  select.appendChild(h('option', { value: 'original', text: 'Original', selected: state.filePreference === 'original' }));
  select.appendChild(h('option', { value: 'sample', text: 'Sample (smaller)', selected: state.filePreference === 'sample' }));
  quality.appendChild(select);
  controls.appendChild(quality);
  container.appendChild(controls);

  const actions = h('div', { class: 'psDockActions' });
  actions.appendChild(
    button(state.running ? 'Running…' : `Download selected (${state.selectedCount})`, {
      variant: 'primary',
      disabled: state.busy || state.running || state.selectedCount === 0,
      title: 'Start only the ticked rows. Everything else stays in the list.',
      onClick: () => callbacks.onDownloadSelected(),
    }),
  );
  if (state.running) {
    actions.appendChild(
      button(state.paused ? 'Resume' : 'Pause', {
        variant: 'subtle',
        onClick: () => (state.paused ? callbacks.onResume() : callbacks.onPause()),
      }),
    );
    actions.appendChild(button('Stop', { variant: 'danger', onClick: () => callbacks.onStop() }));
  } else if (state.pendingCount > 0) {
    actions.appendChild(
      button(`Start queued (${state.pendingCount})`, {
        variant: 'subtle',
        disabled: state.busy,
        title: 'Run every queued row, not just the ticked ones',
        onClick: () => callbacks.onStart(),
      }),
    );
  }
  container.appendChild(actions);

  container.appendChild(
    h('p', {
      class: `psNotice${state.notice ? ` ${state.notice.tone}` : ''}`,
      text:
        state.notice?.text ??
        'Tick rows (or Select all), then download. Downloads keep running while you browse other pages.',
    }),
  );
}
