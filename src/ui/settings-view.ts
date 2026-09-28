import type { ExtensionSettings } from '../shared/types.js';
import { button, field, h } from './dom.js';
import { toast, showError } from './toast.js';

export interface SettingsState {
  settings: ExtensionSettings;
  saving: boolean;
  /** Host origin the options page can ask extra permissions for. */
  hostAccess: { origin: string; granted: boolean } | null;
}

export interface SettingsCallbacks {
  onChange(patch: Partial<ExtensionSettings>): void;
  onSave(): void;
  onReset(): void;
  onRequestHostAccess(): void;
  onExport(includeSecrets: boolean): void;
  onImport(json: string, includeSecrets: boolean): void;
  onClearAllCredentials(): void;
  onSyncUserAgent(): void;
}

/** Settings screen: download naming, throttling, privacy/credential tools. */
export function renderSettings(container: HTMLElement, state: SettingsState, callbacks: SettingsCallbacks): void {
  container.replaceChildren();
  const { settings } = state;

  container.appendChild(
    h(
      'div',
      { class: 'view-header' },
      h('div', {}, h('h1', { text: 'Settings' }), h('p', { class: 'muted', text: 'Applies to every server unless the adapter requires stricter limits.' })),
      h('div', { class: 'header-actions' }, button(state.saving ? 'Saving…' : 'Save settings', { variant: 'primary', iconName: 'check', onClick: () => callbacks.onSave() })),
    ),
  );

  const form = h('form', {
    class: 'card form-grid',
    on: {
      submit: (event) => {
        event.preventDefault();
        callbacks.onSave();
      },
    },
  });

  form.appendChild(h('h2', { class: 'section-title', text: 'Downloads' }));
  form.appendChild(
    field(
      'Folder template',
      h('input', {
        class: 'input mono',
        value: settings.folderTemplate,
        on: { input: (event) => callbacks.onChange({ folderTemplate: (event.target as HTMLInputElement).value }) },
      }),
      { help: 'Tokens: {siteType} {serverLabel} {rating} {date} {artist}. Slashes create sub-folders.' },
    ),
  );
  form.appendChild(
    field(
      'Filename template',
      h('input', {
        class: 'input mono',
        value: settings.filenameTemplate,
        on: { input: (event) => callbacks.onChange({ filenameTemplate: (event.target as HTMLInputElement).value }) },
      }),
      { help: 'Tokens: {id} {md5} {ext} {rating} {score} {artist} {character} {copyright} {tags} {tag1}…{tag9} {date}. Extension is added automatically.' },
    ),
  );
  form.appendChild(
    field(
      'Tags used by {tags}, {artists} …',
      h('input', {
        class: 'input',
        type: 'number',
        min: 1,
        max: 30,
        value: String(settings.maxTagsInFilename),
        on: { input: (event) => callbacks.onChange({ maxTagsInFilename: Number((event.target as HTMLInputElement).value) }) },
      }),
      { help: 'Keeps filenames short enough for the filesystem.' },
    ),
  );
  form.appendChild(
    field(
      'Tag separator',
      h('input', {
        class: 'input',
        value: settings.tagSeparator,
        maxlength: 3,
        on: { input: (event) => callbacks.onChange({ tagSeparator: (event.target as HTMLInputElement).value }) },
      }),
      { help: 'Used when a template expands several tags, e.g. "_" for tag_tag.' },
    ),
  );
  form.appendChild(
    field(
      'Parallel downloads',
      h('input', {
        class: 'input',
        type: 'number',
        min: 1,
        max: 8,
        value: String(settings.maxConcurrency),
        on: { input: (event) => callbacks.onChange({ maxConcurrency: Number((event.target as HTMLInputElement).value) }) },
      }),
      { help: 'Per-server request spacing is always respected.' },
    ),
  );

  form.appendChild(h('h2', { class: 'section-title', text: 'Requests' }));
  form.appendChild(
    field(
      'Minimum request interval (ms)',
      h('input', {
        class: 'input',
        type: 'number',
        min: 0,
        max: 30000,
        step: 100,
        value: String(settings.minRequestIntervalMs),
        on: { input: (event) => callbacks.onChange({ minRequestIntervalMs: Number((event.target as HTMLInputElement).value) }) },
      }),
      { help: 'Adapters enforce their own minimum (e621: 1000ms, Danbooru/Gelbooru: 1000ms recommended).' },
    ),
  );
  form.appendChild(
    field(
      'Retry attempts',
      h('input', {
        class: 'input',
        type: 'number',
        min: 1,
        max: 8,
        value: String(settings.maxRetries),
        on: { input: (event) => callbacks.onChange({ maxRetries: Number((event.target as HTMLInputElement).value) }) },
      }),
      { help: 'Applies to rate-limit and transient network failures.' },
    ),
  );
  form.appendChild(
    field(
      'Global tag suffix',
      h('input', {
        class: 'input',
        value: settings.globalTagSuffix,
        placeholder: 'e.g. -scat -guro',
        on: { input: (event) => callbacks.onChange({ globalTagSuffix: (event.target as HTMLInputElement).value }) },
      }),
      { help: 'Appended to every search you run, on top of the per-server rating filter.' },
    ),
  );
  form.appendChild(toggle(settings.enforceRatingFilterOnDownload, 'Enforce rating filter when downloading', (value) => callbacks.onChange({ enforceRatingFilterOnDownload: value })));

  form.appendChild(h('h2', { class: 'section-title', text: 'Client identity' }));
  form.appendChild(toggle(settings.rewriteUserAgent, 'Rewrite User-Agent for saved hosts', (value) => callbacks.onChange({ rewriteUserAgent: value }), 'Uses declarativeNetRequest so sites that require a descriptive User-Agent see the configured value.'));
  form.appendChild(toggle(settings.sendClientParam, 'Send `_client` fallback where documented', (value) => callbacks.onChange({ sendClientParam: value }), 'e621 documents this query parameter for extensions that cannot set a User-Agent header.'));
  form.appendChild(toggle(settings.enableContentScriptButton, 'Show the download button on post pages', (value) => callbacks.onChange({ enableContentScriptButton: value })));

  form.appendChild(
    h('div', { class: 'form-actions' }, button('Save settings', { variant: 'primary', iconName: 'check', onClick: () => callbacks.onSave() }), button('Reset to defaults', { variant: 'ghost', onClick: () => callbacks.onReset() })),
  );
  container.appendChild(form);

  // --------------------------------------------------------------- security
  container.appendChild(
    h(
      'section',
      { class: 'card' },
      h('h2', { class: 'section-title', text: 'Credentials & data' }),
      h('p', {
        class: 'muted small',
        text: 'Server profiles (including API keys) live in chrome.storage.local only. Keys are never sent anywhere except the server you configured, and never rendered in full.',
      }),
      h(
        'div',
        { class: 'header-actions' },
        button('Export servers (no keys)', { iconName: 'download', onClick: () => callbacks.onExport(false) }),
        button('Export with keys', { variant: 'danger', iconName: 'download', onClick: () => callbacks.onExport(true) }),
        labelForFile((json, includeSecrets) => callbacks.onImport(json, includeSecrets)),
        button('Delete all stored keys', { variant: 'danger', iconName: 'trash', onClick: () => callbacks.onClearAllCredentials() }),
      ),
      state.hostAccess
        ? h(
            'p',
            { class: 'muted small' },
            `Host access for ${state.hostAccess.origin}: ${state.hostAccess.granted ? 'granted' : 'not granted'}. `,
            state.hostAccess.granted ? null : button('Grant access', { variant: 'subtle', onClick: () => callbacks.onRequestHostAccess() }),
          )
        : null,
    ),
  );

  // ------------------------------------------------------------- maintenance
  container.appendChild(
    h(
      'section',
      { class: 'card' },
      h('h2', { class: 'section-title', text: 'Maintenance' }),
      h('p', { class: 'muted small', text: 'Re-apply User-Agent rules after changing servers outside this page.' }),
      h('div', { class: 'header-actions' }, button('Re-sync User-Agent rules', { iconName: 'refresh', onClick: () => callbacks.onSyncUserAgent() })),
    ),
  );
}

function toggle(checked: boolean, label: string, onChange: (value: boolean) => void, help?: string): HTMLElement {
  const id = `toggle-${label.replace(/\W+/g, '-').toLowerCase()}`;
  return h(
    'div',
    { class: 'toggle-row' },
    h('input', { type: 'checkbox', id, checked, on: { change: (event) => onChange((event.target as HTMLInputElement).checked) } }),
    h('label', { attrs: { for: id }, text: label }),
    help ? h('span', { class: 'muted small', text: help }) : null,
  );
}

/** Hidden file input that imports a previously exported JSON file. */
function labelForFile(onFile: (json: string, includeSecrets: boolean) => void): HTMLElement {
  const input = h('input', {
    type: 'file',
    attrs: { accept: 'application/json', style: 'display:none' },
    on: {
      change: async (event) => {
        const file = (event.target as HTMLInputElement).files?.[0];
        if (!file) return;
        try {
          const text = await file.text();
          const includesSecrets = /"apiKey"\s*:\s*"(?!\s*")/.test(text);
          onFile(text, includesSecrets);
        } catch (error) {
          showError(error instanceof Error ? error.message : String(error));
        }
      },
    },
  });
  const trigger = button('Import servers', { iconName: 'copy', onClick: () => input.click() });
  const wrapper = h('span', { class: 'file-import' }, trigger, input);
  return wrapper;
}

export function notifySaved(): void {
  toast('Settings saved', 'success');
}
