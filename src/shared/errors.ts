import type { FailureKind } from './types.js';

export interface BooruErrorOptions {
  kind: FailureKind;
  status?: number | null;
  siteType?: string | null;
  serverId?: string | null;
  /** Suggested user-facing next step. Never contains credentials. */
  hint?: string | null;
  cause?: unknown;
}

/**
 * Single error type used across adapters, the HTTP layer and the queue.
 * `message` is always safe to render/log: callers must pass redacted URLs.
 */
export class BooruError extends Error {
  readonly kind: FailureKind;
  readonly status: number | null;
  readonly siteType: string | null;
  readonly serverId: string | null;
  readonly hint: string | null;

  constructor(message: string, options: BooruErrorOptions) {
    super(message);
    this.name = 'BooruError';
    this.kind = options.kind;
    this.status = options.status ?? null;
    this.siteType = options.siteType ?? null;
    this.serverId = options.serverId ?? null;
    this.hint = options.hint ?? null;
    if (options.cause !== undefined) {
      (this as { cause?: unknown }).cause = options.cause;
    }
  }

  get retryable(): boolean {
    return this.kind === 'rate-limited' || this.kind === 'network-failure' || this.kind === 'server-error';
  }
}

export function toBooruError(error: unknown, fallback: BooruErrorOptions): BooruError {
  if (error instanceof BooruError) return error;
  const message = error instanceof Error ? error.message : String(error);
  return new BooruError(message, { ...fallback, cause: error });
}

/** Human labels for the failure taxonomy - used by the UI and docs. */
export const FAILURE_LABELS: Record<FailureKind, string> = {
  'auth-failure': 'Authentication failed',
  'endpoint-mismatch': 'Endpoint mismatch',
  'network-failure': 'Network failure',
  'rate-limited': 'Rate limited',
  'unsupported-site': 'Unsupported site type',
  'incomplete-config': 'Incomplete configuration',
  'server-error': 'Server error',
  blocked: 'Request blocked',
  'parse-failure': 'Unexpected response format',
  unknown: 'Unknown error',
};

export function failureLabel(kind: FailureKind): string {
  return FAILURE_LABELS[kind] ?? FAILURE_LABELS.unknown;
}
