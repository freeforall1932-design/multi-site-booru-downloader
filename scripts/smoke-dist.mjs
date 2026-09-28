/**
 * Smoke-tests the *built* extension bundle (dist/) outside a browser.
 *
 * It boots the real preview platform - which uses the exact service graph the
 * MV3 service worker uses - against the in-page mock booru APIs, then drives the
 * public message protocol. This catches packaging mistakes that a unit test
 * cannot: missing files, broken imports, module-order/chrome-API assumptions.
 *
 * Usage: node scripts/build.mjs && node scripts/smoke-dist.mjs
 */
import { readFile } from 'node:fs/promises';
import path from 'node:path';

const root = process.cwd();
const checks = [];
let failures = 0;

function check(name, condition, detail = '') {
  const ok = Boolean(condition);
  checks.push({ name, ok, detail });
  if (!ok) failures += 1;
  console.log(`${ok ? '✓' : '✗'} ${name}${ok || !detail ? '' : `  → ${detail}`}`);
}

/** Minimal browser globals the preview platform touches. */
function installBrowserStubs() {
  const store = new Map();
  globalThis.localStorage = {
    get length() {
      return store.size;
    },
    key: (index) => Array.from(store.keys())[index] ?? null,
    getItem: (key) => (store.has(key) ? store.get(key) : null),
    setItem: (key, value) => store.set(key, String(value)),
    removeItem: (key) => store.delete(key),
    clear: () => store.clear(),
  };
  globalThis.location = { hash: '', search: '', origin: 'http://localhost:4173' };
  const elements = [];
  globalThis.document = {
    createElement: () => {
      const element = {
        clicked: 0,
        click() {
          this.clicked += 1;
        },
        remove() {},
      };
      elements.push(element);
      return element;
    },
    body: { append() {} },
  };
  return { store, elements };
}

async function main() {
  const manifest = JSON.parse(await readFile(path.join(root, 'dist/manifest.json'), 'utf8'));
  check('manifest is MV3', manifest.manifest_version === 3);
  check('manifest points at the built worker', manifest.background?.service_worker === 'background/service-worker.js');
  check('service worker file exists', await exists('dist/background/service-worker.js'));
  check('popup + options files exist', (await exists('dist/popup/popup.html')) && (await exists('dist/options/options.html')));
  check('icons exist', (await exists('dist/icons/icon16.png')) && (await exists('dist/icons/icon128.png')));

  const { elements, store } = installBrowserStubs();

  const { startPreview } = await import(path.join(root, 'dist/preview/preview.js'));
  const { getPlatform } = await import(path.join(root, 'dist/platform/index.js'));

  await startPreview();
  const platform = getPlatform();
  check('preview platform installed', platform.environment === 'preview');

  const send = (request) => platform.send(request);
  const expectOk = async (request) => {
    const response = await send(request);
    if (!response.ok) throw new Error(`${request.type} failed: ${JSON.stringify(response.error)}`);
    return response.data;
  };

  // ------------------------------------------------------------------ servers
  const servers = await expectOk({ type: 'servers/list' });
  check('seeds demo servers', Array.isArray(servers) && servers.length >= 4, JSON.stringify(servers).slice(0, 120));
  check('exactly one default', servers.filter((server) => server.isDefault).length === 1);
  check(
    'responses never contain a raw api key',
    !JSON.stringify(servers).includes('e621-demo-key') &&
      servers.every((server) => !('apiKey' in server) && server.hasApiKey === (server.apiKeyMask.length > 0)),
  );

  const target = servers.find((server) => server.isDefault);

  // --------------------------------------------------------------- validation
  const validation = await expectOk({ type: 'servers/validate', payload: { id: target.id } });
  check('validation succeeds with demo credentials', validation.ok === true, validation.message);
  check('validation reports the signed-in account', /demo_e621/.test(validation.message ?? ''), validation.message);
  check('validation trace is credential-free', !JSON.stringify(validation).includes('e621-demo-key'));

  await send({ type: 'servers/save', payload: { id: target.id, siteType: target.siteType, baseUrl: target.baseUrl, label: target.label, apiKey: 'definitely-wrong' } });
  const badAuth = await expectOk({ type: 'servers/validate', payload: { id: target.id } });
  check('wrong key is classified as auth-failure (not endpoint mismatch)', badAuth.kind === 'auth-failure', badAuth.kind);
  await send({ type: 'servers/save', payload: { id: target.id, siteType: target.siteType, baseUrl: target.baseUrl, label: target.label, apiKey: 'e621-demo-key' } });

  // ------------------------------------------------------------------- browse
  const search = await expectOk({ type: 'browse/search', payload: { serverId: target.id, spec: { tags: 'cityscape', limit: 6, page: 1 } } });
  check('search returns normalized posts', search.posts.length > 0 && search.posts.length <= 6, `got ${search.posts.length}`);
  check('every post carries a file url and a canonical rating', search.posts.every((post) => post.fileUrl.startsWith('http') && post.rating));
  check('rating filter is applied', search.posts.every((post) => post.rating === 'safe' || post.rating === 'unknown'), search.posts.map((post) => post.rating).join(','));

  const rateLimited = await send({ type: 'browse/search', payload: { serverId: target.id, spec: { tags: 'force:429', limit: 3, page: 1 } } });
  check('throttling is classified as rate-limited', rateLimited.ok === false && rateLimited.error.kind === 'rate-limited', JSON.stringify(rateLimited));

  // --------------------------------------------------------------------- posts
  const postId = search.posts[0].id;
  const post = await expectOk({ type: 'posts/get', payload: { serverId: target.id, postId } });
  check('single post lookup matches the listing id', String(post.id) === String(postId));

  const download = await expectOk({ type: 'posts/download', payload: { serverId: target.id, postId } });
  check('download path uses the naming templates', /^booru\/e621\//.test(download.filename), download.filename);
  check('preview downloader received the file', platform.downloader.log.length === 1);
  check('download anchor was rendered for the browser', elements.some((element) => element.clicked === 1));

  // --------------------------------------------------------------------- queue
  await expectOk({
    type: 'queue/enqueuePosts',
    payload: { serverId: target.id, posts: search.posts.slice(0, 3).map((candidate) => ({ id: candidate.id, rating: candidate.rating })) },
  });
  const queued = await expectOk({ type: 'queue/list' });
  check('queue accepted the listing posts', queued.summary.pending === 3, JSON.stringify(queued.summary));
  await expectOk({ type: 'queue/run' });
  let listed = queued;
  // Each queued post costs one request, and the per-server limit is the
  // documented 1 request/second, so give the run enough wall-clock time.
  for (let attempt = 0; attempt < 400 && listed.summary.pending + listed.summary.running > 0; attempt += 1) {
    await new Promise((resolve) => setTimeout(resolve, 50));
    listed = await expectOk({ type: 'queue/list' });
  }
  check('queue drained through the router', listed.summary.done === 3, JSON.stringify(listed.summary));

  // ----------------------------------------------------------------- settings
  const settings = await expectOk({ type: 'settings/save', payload: { folderTemplate: 'smoke/{siteType}' } });
  check('settings persist', settings.folderTemplate === 'smoke/{siteType}');
  const afterReload = await expectOk({ type: 'settings/get' });
  check('settings survive a service-graph restart', afterReload.folderTemplate === 'smoke/{siteType}');
  check('settings are written to the shared preview namespace', store.has('bsm-preview:bsm.settings'));

  // -------------------------------------------------------------- diagnostics
  const info = await expectOk({ type: 'diagnostics/info' });
  check('diagnostics list all three adapters', info.adapters.map((entry) => entry.siteType).sort().join(',') === 'danbooru,e621,gelbooru');
  check('diagnostics contain no secrets', !JSON.stringify(info).includes('e621-demo-key') && !JSON.stringify(info).includes('Authorization'));

  // --------------------------------------------------------------- route detect
  const route = await expectOk({ type: 'routes/detect', payload: { url: `https://gelbooru.com/index.php?page=post&s=view&id=${postId}` } });
  check('route detection works on a post URL', route?.siteType === 'gelbooru' && route.postId === String(postId));
  check('route detection resolves the saved server', route.server?.siteType === 'gelbooru');

  console.log(`\n${checks.length - failures}/${checks.length} checks passed`);
  if (failures > 0) process.exitCode = 1;
}

async function exists(relative) {
  try {
    await readFile(path.join(root, relative));
    return true;
  } catch {
    return false;
  }
}

await main();
