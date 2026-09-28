import { h } from './dom.js';

export type ToastKind = 'info' | 'success' | 'error' | 'warning';

let host: HTMLElement | null = null;

function ensureHost(): HTMLElement {
  if (host && host.isConnected) return host;
  host = h('div', { class: 'toast-host', attrs: { role: 'status', 'aria-live': 'polite' } });
  document.body.appendChild(host);
  return host;
}

export function toast(message: string, kind: ToastKind = 'info', timeoutMs = 4200): void {
  const node = h('div', { class: `toast toast-${kind}`, text: message });
  ensureHost().appendChild(node);
  setTimeout(() => {
    node.classList.add('toast-out');
    setTimeout(() => node.remove(), 250);
  }, timeoutMs);
}

export function showError(error: { message: string; kind?: string; hint?: string | null } | string): void {
  const text = typeof error === 'string' ? error : `${error.message}${error.hint ? ` - ${error.hint}` : ''}`;
  toast(text, 'error', 6500);
}
