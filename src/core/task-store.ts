/**
 * The task list (`bsm.tasks`).
 *
 * Tasks are durable for the same reason the queue and the link list are: closing
 * the panel, reloading the page or restarting the service worker must not lose
 * the record of what has been collected, what was tried and what is still
 * missing. A task holds member *ids* only - a file's state lives in
 * `bsm.links`, so re-importing a package can never resurrect a file that is
 * already on disk.
 *
 * Mutations go through `serialize()` for the same reason the link store's do:
 * two queue workers settle two files at once, and each of them read-modify-writes
 * the whole list.
 */
import type { DownloadTask, MirrorLink, TaskRun } from '../shared/types.js';
import { STORAGE_KEYS, type StorageArea } from './storage.js';
import { MAX_TASKS, MAX_TASK_MEMBERS, MAX_SCANNED_POSTS, mergeTask, statsLabel, taskStatsFor, trimRuns } from './tasks.js';
import { uniqueBy } from '../shared/util.js';

function isTask(value: unknown): value is DownloadTask {
  const task = value as Partial<DownloadTask> | null;
  return !!task && typeof task.id === 'string' && typeof task.name === 'string';
}

/** Fill in fields a record written by an older build may lack. */
function normalizeTask(task: DownloadTask, now = new Date().toISOString()): DownloadTask {
  return {
    ...task,
    siteType: task.siteType ?? null,
    serverId: task.serverId ?? null,
    service: task.service ?? null,
    creator: task.creator ?? null,
    query: task.query ?? '',
    createdAt: task.createdAt ?? now,
    updatedAt: task.updatedAt ?? task.createdAt ?? now,
    lastScanAt: task.lastScanAt ?? null,
    memberIds: Array.isArray(task.memberIds) ? task.memberIds : [],
    scannedPosts: Array.isArray(task.scannedPosts) ? task.scannedPosts : [],
    runs: Array.isArray(task.runs) ? task.runs : [],
  };
}

export class TaskStore {
  private cache: DownloadTask[] | null = null;
  private chain: Promise<unknown> = Promise.resolve();

  constructor(private readonly area: StorageArea) {}

  /** One mutation at a time - see the module comment. */
  private serialize<T>(task: () => Promise<T>): Promise<T> {
    const next = this.chain.then(task, task);
    this.chain = next.then(
      () => undefined,
      () => undefined,
    );
    return next;
  }

  async list(): Promise<DownloadTask[]> {
    if (!this.cache) {
      const stored = await this.area.get<unknown>(STORAGE_KEYS.tasks);
      this.cache = Array.isArray(stored) ? stored.filter(isTask).map((task) => normalizeTask(task)) : [];
    }
    return this.cache.map((task) => ({ ...task }));
  }

  async get(id: string): Promise<DownloadTask | null> {
    return (await this.list()).find((task) => task.id === id) ?? null;
  }

  /**
   * Create or merge a task.
   *
   * Merging is what makes the whole feature idempotent: importing the same
   * package twice, or collecting a creator you already have, joins the existing
   * task instead of piling up duplicates. `memberIds` grow, the newest name and
   * provenance win, and the scan memory accumulates.
   */
  async upsert(task: DownloadTask): Promise<{ task: DownloadTask; created: boolean }> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const index = tasks.findIndex((entry) => entry.id === task.id);
      const now = new Date().toISOString();
      if (index < 0) {
        const created = normalizeTask({ ...task, createdAt: task.createdAt || now, updatedAt: now });
        tasks.push(created);
        await this.write(tasks);
        return { task: { ...created }, created: true };
      }
      const merged = mergeTask(tasks[index]!, { ...task, updatedAt: now });
      tasks[index] = merged;
      await this.write(tasks);
      return { task: { ...merged }, created: false };
    });
  }

  /** Attach freshly collected file ids to a task (after a scan). */
  async attachMembers(id: string, memberIds: readonly string[], scannedPosts: readonly string[] = []): Promise<DownloadTask | null> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const index = tasks.findIndex((entry) => entry.id === id);
      if (index < 0) return null;
      const current = tasks[index]!;
      const next: DownloadTask = {
        ...current,
        memberIds: uniqueBy([...current.memberIds, ...memberIds], (entry) => entry).slice(-MAX_TASK_MEMBERS),
        scannedPosts: uniqueBy([...current.scannedPosts, ...scannedPosts], (entry) => entry).slice(-MAX_SCANNED_POSTS),
        lastScanAt: new Date().toISOString(),
        updatedAt: new Date().toISOString(),
      };
      tasks[index] = next;
      await this.write(tasks);
      return { ...next };
    });
  }

  /** Append a run to the task's history (and keep the history bounded). */
  async recordRun(id: string, run: TaskRun): Promise<DownloadTask | null> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const index = tasks.findIndex((entry) => entry.id === id);
      if (index < 0) return null;
      const current = tasks[index]!;
      const next: DownloadTask = { ...current, runs: trimRuns([...current.runs, run]), updatedAt: new Date().toISOString() };
      tasks[index] = next;
      await this.write(tasks);
      return { ...next };
    });
  }

  /** Update the newest run in place (rows settling does not deserve a new run). */
  async updateLastRun(id: string, patch: Partial<TaskRun>): Promise<DownloadTask | null> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const index = tasks.findIndex((entry) => entry.id === id);
      if (index < 0) return null;
      const current = tasks[index]!;
      if (!current.runs.length) return { ...current };
      const runs = [...current.runs];
      const last = runs[runs.length - 1]!;
      runs[runs.length - 1] = { ...last, ...patch, id: last.id, startedAt: last.startedAt };
      const next: DownloadTask = { ...current, runs, updatedAt: new Date().toISOString() };
      tasks[index] = next;
      await this.write(tasks);
      return { ...next };
    });
  }

  /**
   * A link row settled: update the run record of every task that owns it.
   *
   * Called by the queue, which knows the URL but not the task. When no member of
   * the task is waiting any more the run is closed, so the task's history answers
   * "what did the last pass do?" even after the panel was closed mid-run.
   */
  async noteLinkSettled(linkId: string, links: readonly MirrorLink[]): Promise<void> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const owners = tasks.filter((task) => task.memberIds.includes(linkId));
      if (!owners.length) return;
      let changed = false;
      for (const task of owners) {
        if (!task.runs.length) continue;
        const stats = taskStatsFor(task, links);
        const last = task.runs[task.runs.length - 1]!;
        if (last.finishedAt) continue;
        const settled = stats.queued === 0;
        const runs = [...task.runs];
        runs[runs.length - 1] = {
          ...last,
          done: stats.done,
          failed: stats.failed,
          finishedAt: settled ? new Date().toISOString() : last.finishedAt,
          note: settled ? statsLabel(stats) : last.note,
        };
        const index = tasks.findIndex((entry) => entry.id === task.id);
        tasks[index] = { ...task, runs, updatedAt: new Date().toISOString() };
        changed = true;
      }
      if (changed) await this.write(tasks);
    });
  }

  /** Drop a task. Its files stay in the link list unless the caller removes them. */
  async remove(id: string): Promise<boolean> {
    return this.serialize(async () => {
      const tasks = await this.list();
      const next = tasks.filter((task) => task.id !== id);
      if (next.length === tasks.length) return false;
      await this.write(next);
      return true;
    });
  }

  /**
   * Forget the tasks that no longer own a single surviving file.
   *
   * `keepIds` is what is left in the link store: clearing the list (or removing
   * the last file of a task by hand) must not leave a card behind that describes
   * nothing.
   */
  async pruneEmpty(keepIds: readonly string[]): Promise<number> {
    return this.serialize(async () => {
      const keep = new Set(keepIds);
      const tasks = await this.list();
      const next = tasks.filter((task) => task.memberIds.some((id) => keep.has(id)));
      const removed = tasks.length - next.length;
      if (removed) await this.write(next);
      return removed;
    });
  }

  invalidate(): void {
    this.cache = null;
  }

  private async write(tasks: DownloadTask[]): Promise<void> {
    this.cache = tasks.slice(-MAX_TASKS);
    await this.area.set(STORAGE_KEYS.tasks, this.cache);
  }
}
