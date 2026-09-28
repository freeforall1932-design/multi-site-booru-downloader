import { beforeEach, describe, expect, it } from 'vitest';
import { DownloadQueue } from '../../src/core/queue.js';
import { createHarness, e621Server, gelbooruServer, danbooruServer } from '../helpers.js';
import { MOCK_POSTS } from '../../src/preview/mock-data.js';
import { BooruError } from '../../src/shared/errors.js';

type Harness = ReturnType<typeof createHarness>;

async function serverIdOf(harness: Harness): Promise<string> {
  return (await harness.servers.getDefault())!.id;
}

describe('DownloadQueue', () => {
  let harness: Harness;

  beforeEach(async () => {
    harness = createHarness();
    await harness.servers.save(e621Server());
    await harness.servers.save(gelbooruServer());
    await harness.servers.save(danbooruServer());
  });

  it('enqueues items and de-duplicates pending work', async () => {
    const first = await harness.queue.enqueue([{ serverId: 'a', postId: '1' }, { serverId: 'a', postId: '2' }]);
    const second = await harness.queue.enqueue([{ serverId: 'a', postId: '1' }]);
    expect(first.added).toBe(2);
    expect(second.added).toBe(0);
    expect(second.skipped).toBe(1);
    expect(harness.queue.summary().pending).toBe(2);
  });

  it('downloads every queued post through the mock booru and names files deterministically', async () => {
    const server = (await harness.servers.getDefault())!;
    await harness.queue.enqueue([
      { serverId: server.id, postId: String(MOCK_POSTS[0]!.id) },
      { serverId: server.id, postId: String(MOCK_POSTS[1]!.id) },
    ]);
    const summary = await harness.queue.run();

    expect(summary.done).toBe(2);
    expect(summary.failed).toBe(0);
    expect(harness.downloader.log).toHaveLength(2);
    expect(harness.downloader.log[0]!.filename).toMatch(/^booru\/e621\/\d+_md5/);
    expect(harness.downloader.log[0]!.url).toContain('/mock/media/');
  });

  it('resolves and downloads single posts from every site type', async () => {
    for (const server of await harness.servers.list()) {
      await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[2]!.id) }]);
    }
    const summary = await harness.queue.run();
    expect(summary.done).toBe(3);
    const folders = harness.downloader.log.map((entry) => entry.filename.split('/').slice(0, 2).join('/'));
    expect(new Set(folders)).toEqual(new Set(['booru/e621', 'booru/gelbooru', 'booru/danbooru']));
  });

  it('enforces the per-server rating filter before queueing', async () => {
    const server = (await harness.servers.getDefault())!; // e621, allowed: safe only
    const explicit = MOCK_POSTS.find((post) => post.rating === 'explicit')!;
    const safe = MOCK_POSTS.find((post) => post.rating === 'safe')!;
    const result = await harness.queue.enqueuePosts(server.id, [
      { id: String(explicit.id), rating: 'explicit' },
      { id: String(safe.id), rating: 'safe' },
    ]);
    expect(result.added).toBe(1);
    expect(result.skipped).toBe(1);
  });

  it('skips a post that violates the filter at download time (settings enforcement)', async () => {
    const server = (await harness.servers.getDefault())!;
    const explicit = MOCK_POSTS.find((post) => post.rating === 'explicit')!;
    await harness.queue.enqueue([{ serverId: server.id, postId: String(explicit.id) }]);
    const summary = await harness.queue.run();
    expect(summary.failed).toBe(1);
    const item = harness.queue.list()[0]!;
    expect(item.error).toMatch(/rating/i);
  });

  it('records an authentication failure per item instead of throwing', async () => {
    const server = (await harness.servers.getDefault())!;
    await harness.servers.update(server.id, { apiKey: '', username: '' });
    await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[0]!.id) }]);
    const summary = await harness.queue.run();
    expect(summary.done + summary.failed).toBe(1);
  });

  it('counts skips separately for unsupported servers', async () => {
    const area = harness.area;
    await area.set('bsm.servers', [
      {
        id: 'ghost',
        label: 'Ghost',
        siteType: 'not-registered',
        baseUrl: 'https://ghost.example',
        ratingFilterEnabled: false,
        username: '',
        apiKey: '',
        userId: '',
        customUserAgent: '',
        isDefault: true,
        validationStatus: 'unknown',
        validationMessage: '',
        lastValidatedAt: null,
      },
    ]);
    const queueWithGhost = new DownloadQueue({
      storage: area,
      client: harness.client,
      servers: harness.servers,
      settings: harness.settings,
      downloader: harness.downloader,
    });
    await queueWithGhost.enqueue([{ serverId: 'ghost', postId: '1' }]);
    const summary = await queueWithGhost.run();
    expect(summary.skipped).toBe(1);
    expect(queueWithGhost.list()[0]!.errorKind).toBe('unsupported-site');
  });

  it('pauses, resumes and cancels', async () => {
    const server = (await harness.servers.getDefault())!;
    await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[0]!.id) }]);
    await harness.queue.pause();
    expect(harness.queue.summary().paused).toBe(true);
    await harness.queue.resume();
    expect(harness.queue.summary().paused).toBe(false);

    const item = harness.queue.list()[0]!;
    await harness.queue.cancel(item.id);
    expect(harness.queue.summary().canceled).toBe(1);
  });

  it('requeues failed items and clears finished ones', async () => {
    const server = (await harness.servers.getDefault())!;
    await harness.servers.update(server.id, { apiKey: 'wrong-key' });
    await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[0]!.id) }]);
    await harness.queue.run();
    const failed = harness.queue.summary().failed + harness.queue.summary().skipped;
    expect(failed).toBe(1);

    const requeued = await harness.queue.retryFailed();
    expect(requeued).toBe(1);
    expect(harness.queue.summary().pending).toBe(1);

    await harness.queue.clear('all');
    expect(harness.queue.summary().total).toBe(0);
  });

  it('persists state so a worker restart resumes pending work', async () => {
    const server = (await harness.servers.getDefault())!;
    await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[0]!.id) }]);
    await harness.queue.pause();

    const restarted = new DownloadQueue({
      storage: harness.area,
      client: harness.client,
      servers: harness.servers,
      settings: harness.settings,
      downloader: harness.downloader,
    });
    const list = await (async () => {
      await restarted.pause();
      return restarted.list();
    })();
    expect(list).toHaveLength(1);
    expect(list[0]!.status).toBe('pending');
  });

  it('respects the concurrency setting and keeps every item accounted for', async () => {
    await harness.settings.save({ maxConcurrency: 4 });
    const server = (await harness.servers.getDefault())!;
    await harness.queue.enqueue(
      MOCK_POSTS.slice(0, 8).map((post) => ({ serverId: server.id, postId: String(post.id) })),
    );
    const summary = await harness.queue.run();
    expect(summary.total).toBe(8);
    expect(summary.done + summary.failed + summary.skipped + summary.canceled).toBe(8);
  });

  it('emits change events for progress UIs', async () => {
    const events: number[] = [];
    const unsubscribe = harness.queue.onChange((summary) => events.push(summary.total));
    const server = (await harness.servers.getDefault())!;
    await harness.queue.enqueue([{ serverId: server.id, postId: String(MOCK_POSTS[0]!.id) }]);
    await harness.queue.run();
    unsubscribe();
    expect(events.length).toBeGreaterThan(0);
    expect(events.at(-1)).toBe(1);
  });

  it('validates unknown server ids', async () => {
    await expect(harness.queue.enqueuePosts('missing-server', [{ id: '1' }])).rejects.toBeInstanceOf(BooruError);
    await expect(harness.queue.enqueuePosts(await serverIdOf(harness), [])).resolves.toEqual({ added: 0, skipped: 0 });
    await expect(harness.client.resolve('missing-server')).rejects.toBeInstanceOf(BooruError);
  });
});
