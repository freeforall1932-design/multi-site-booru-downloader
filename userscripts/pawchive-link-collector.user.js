// ==UserScript==
// @name         Pawchive Link Collector
// @namespace    https://greasyfork.org/en/scripts/596246-pawchive-link-collector
// @version      1.0.0
// @description  Collect download links from every post of a Pawchive creator and export them as TXT. Supports toggleable file formats, providers, and custom entries.
// @match        https://pawchive.pw/*
// @run-at       document-idle
// @grant        none
// @license MIT
// ==/UserScript==

(() => {
    'use strict';

    // ── Configuration ─────────────────────────────────────────────────────────
    const SITE_ORIGIN          = 'https://pawchive.pw';
    const POSTS_PER_PAGE       = 50;
    const REQUEST_TIMEOUT_MS   = 30000;
    const DEFAULT_CONCURRENT   = 1;
    const DEFAULT_DELAY        = 1000;
    const DEFAULT_RETRIES      = 10;
    const DEFAULT_MAX_PAGES    = 500;

    const STORAGE_KEY_FORMATS          = 'pawchive-enabled-formats';
    const STORAGE_KEY_PROVIDERS        = 'pawchive-enabled-providers';
    const STORAGE_KEY_CUSTOM_FORMATS   = 'pawchive-custom-formats';
    const STORAGE_KEY_CUSTOM_PROVIDERS = 'pawchive-custom-providers';
    const STORAGE_KEY_POSITION         = 'pawchive-position';
    const STORAGE_KEY_CONCURRENT       = 'pawchive-max-concurrent';
    const STORAGE_KEY_DELAY            = 'pawchive-request-delay';
    const STORAGE_KEY_RETRIES          = 'pawchive-max-retries';
    const STORAGE_KEY_PAGES            = 'pawchive-max-pages';


    // ── Default file formats ──────────────────────────────────────────────────
    const DEFAULT_FORMATS = [
        { ext: 'zip',          label: 'ZIP' }, { ext: 'rar',          label: 'RAR' },
        { ext: '7z',           label: '7Z' },  { ext: '7zip',         label: '7ZIP' },
        { ext: 'tar',          label: 'TAR' }, { ext: 'gz',           label: 'GZ' },
        { ext: 'png',          label: 'PNG' }, { ext: 'jpg',          label: 'JPG' },
        { ext: 'jpeg',         label: 'JPEG' },{ ext: 'gif',          label: 'GIF' },
        { ext: 'webp',         label: 'WEBP' },{ ext: 'psd',          label: 'PSD' },
        { ext: 'pdf',          label: 'PDF' }, { ext: 'epub',         label: 'EPUB' },
        { ext: 'cbz',          label: 'CBZ' }, { ext: 'cbr',          label: 'CBR' },
        { ext: 'mp4',          label: 'MP4' }, { ext: 'mkv',          label: 'MKV' },
        { ext: 'webm',         label: 'WEBM' },{ ext: 'mov',          label: 'MOV' },
        { ext: 'avi',          label: 'AVI' }, { ext: 'mp3',          label: 'MP3' },
        { ext: 'wav',          label: 'WAV' }, { ext: 'flac',         label: 'FLAC' },
        { ext: 'blend',        label: 'Blend'},{ ext: 'obj',          label: 'OBJ' },
        { ext: 'fbx',          label: 'FBX' }, { ext: 'stl',          label: 'STL' },
        { ext: 'unitypackage', label: 'Unity'},{ ext: 'apk',          label: 'APK' },
        { ext: 'exe',          label: 'EXE' }, { ext: 'msi',          label: 'MSI' },
        { ext: 'iso',          label: 'ISO' }
    ];

    // ── Default download providers ────────────────────────────────────────────
    const DEFAULT_PROVIDERS = [
        { host: 'file.pawchive.pw', label: 'Pawchive' },
        { host: 'drive.google.com',  label: 'Google Drive' },
        { host: 'docs.google.com',   label: 'Google Docs' },
        { host: 'mega.nz',           label: 'Mega' },
        { host: 'drive.proton.me',  label: 'Proton Drive' },
        { host: 'mediafire.com',     label: 'MediaFire' },
        { host: 'dropbox.com',       label: 'Dropbox' },
        { host: 'pixeldrain.com',    label: 'Pixeldrain' },
        { host: 'gofile.io',         label: 'GoFile' },
        { host: 'workupload.com',    label: 'WorkUpload' },
        { host: 'catbox.moe',        label: 'Catbox' },
        { host: 'krakenfiles.com',   label: 'KrakenFiles' },
        { host: '1fichier.com',      label: '1fichier' },
        { host: 'pcloud.com',        label: 'pCloud' },
        { host: 'terabox.com',       label: 'TeraBox' },
        { host: 'mixdrop.co',        label: 'MixDrop' },
        { host: 'rapidgator.net',    label: 'RapidGator' },
        { host: 'wetransfer.com',    label: 'WeTransfer' },
        { host: '4shared.com',       label: '4shared' },
        { host: 'sendspace.com',     label: 'SendSpace' },
        { host: '1drv.ms',           label: 'OneDrive' },
    ];

    // ── State helpers ─────────────────────────────────────────────────────────
    function loadSet(key, defaults) {
        try { return new Set(JSON.parse(localStorage.getItem(key)) || defaults); }
        catch { return new Set(defaults); }
    }
    function saveSet(key, set) {
        try { localStorage.setItem(key, JSON.stringify([...set])); } catch {}
    }
    function loadArr(key) {
        try { return JSON.parse(localStorage.getItem(key)) || []; }
        catch { return []; }
    }
    function saveArr(key, arr) {
        try { localStorage.setItem(key, JSON.stringify(arr)); } catch {}
    }
    function loadPosition() {
        try { return JSON.parse(localStorage.getItem(STORAGE_KEY_POSITION)); }
        catch { return null; }
    }
    function savePosition(pos) {
        try { localStorage.setItem(STORAGE_KEY_POSITION, JSON.stringify(pos)); } catch {}
    }
    function loadNum(key, defaultVal) {
        try {
            const raw = localStorage.getItem(key);
            return (raw !== null && !isNaN(raw)) ? Number(raw) : defaultVal;
        } catch { return defaultVal; }
    }
    function saveNum(key, val) {
        try { localStorage.setItem(key, val); } catch {}
    }

    const enabledFormats   = loadSet(STORAGE_KEY_FORMATS,   DEFAULT_FORMATS.map(f => f.ext));
    const enabledProviders = loadSet(STORAGE_KEY_PROVIDERS, DEFAULT_PROVIDERS.map(p => p.host));
    const customFormats    = loadArr(STORAGE_KEY_CUSTOM_FORMATS);
    const customProviders  = loadArr(STORAGE_KEY_CUSTOM_PROVIDERS);

    let MAX_CONCURRENT_POSTS = loadNum(STORAGE_KEY_CONCURRENT, DEFAULT_CONCURRENT);
    let REQUEST_DELAY_MS     = loadNum(STORAGE_KEY_DELAY, DEFAULT_DELAY);
    let MAX_CREATOR_RETRIES  = loadNum(STORAGE_KEY_RETRIES, DEFAULT_RETRIES);
    let MAX_PAGES            = loadNum(STORAGE_KEY_PAGES, DEFAULT_MAX_PAGES);

    // ── Cached regex / set builders ───────────────────────────────────────────
    let cachedFileExtRe, cachedDisabledExtRe, cachedHostSet, cachedKnownProviderHosts;

    function invalidateFilterCaches() {
        cachedFileExtRe = cachedDisabledExtRe = cachedHostSet = cachedKnownProviderHosts = undefined;
    }

    function buildFileExtRe() {
        if (cachedFileExtRe !== undefined) return cachedFileExtRe;
        const allExts = [
            ...DEFAULT_FORMATS.filter(f => enabledFormats.has(f.ext)).map(f => f.ext),
            ...customFormats.filter(f => enabledFormats.has(f.ext)).map(f => f.ext),
        ];
        if (allExts.length === 0) return (cachedFileExtRe = null);
        const joined = allExts.map(e => e.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')).join('|');
        return (cachedFileExtRe = new RegExp(`\\.(${joined})(?:$|[?#])`, 'i'));
    }

    function buildDisabledExtRe() {
        if (cachedDisabledExtRe !== undefined) return cachedDisabledExtRe;
        const disabled = [...DEFAULT_FORMATS, ...customFormats]
            .map(f => f.ext).filter(ext => !enabledFormats.has(ext));
        if (disabled.length === 0) return (cachedDisabledExtRe = null);
        const joined = disabled.map(e => e.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')).join('|');
        return (cachedDisabledExtRe = new RegExp(`\\.(${joined})(?![a-z0-9])`, 'i'));
    }

    function buildHostSet() {
        if (cachedHostSet !== undefined) return cachedHostSet;
        const set = new Set();
        for (const p of [...DEFAULT_PROVIDERS, ...customProviders]) {
            if (enabledProviders.has(p.host)) set.add(p.host);
        }
        return (cachedHostSet = set);
    }

    function getKnownProviderHosts() {
        if (cachedKnownProviderHosts !== undefined) return cachedKnownProviderHosts;
        return (cachedKnownProviderHosts = [...DEFAULT_PROVIDERS, ...customProviders]
            .map(p => p.host.toLowerCase()));
    }

    function isKnownProviderHost(hostname) {
        const h = hostname.toLowerCase();
        return getKnownProviderHosts().some(p => h === p || h.endsWith('.' + p));
    }

    // ── Core state ────────────────────────────────────────────────────────────
    const URL_RE = /https?:\/\/[^\s<>"'`)\]]+/gi;
    const collectedLinks  = new Map();
    const failedPosts     = new Set();
    let isRunning         = false;
    let abortCollection   = false;
    let shadowRoot        = null;

    function getUI(selector) {
        return shadowRoot ? shadowRoot.querySelector(selector) : null;
    }

    // ── Utilities ─────────────────────────────────────────────────────────────
    const sleep = ms => new Promise(res => setTimeout(res, ms));

    function absoluteUrl(rawUrl, baseUrl) {
        try { return new URL(rawUrl, baseUrl).href; } catch { return null; }
    }

    function cleanUrl(url) {
        if (!url) return null;
        const cleaned = url.trim().replace(/&amp;/g, '&').replace(/[.,;!]+$/, '');
        try { return new URL(cleaned).href; } catch { return null; }
    }

    function hostnameMatchesDownloadService(hostname) {
        const hostSet = buildHostSet();
        const h = hostname.toLowerCase();
        if (hostSet.has(h)) return true;
        for (const pattern of hostSet) {
            if (h.endsWith('.' + pattern)) return true;
        }
        return false;
    }

    function looksLikeDownload(url) {
        try {
            const u = new URL(url);
            const host = u.hostname.toLowerCase();
            if (host === location.hostname) return false;

            const disabledRe = buildDisabledExtRe();
            if (disabledRe && disabledRe.test(u.pathname + u.search + u.hash)) return false;

            if (isKnownProviderHost(host)) return hostnameMatchesDownloadService(host);

            const re = buildFileExtRe();
            if (re && re.test(u.pathname)) return true;

            return /[?&](?:download|attachment|file|filename|dl)=/i.test(u.search) ||
                   /(?:\/download(?:\/|$)|\/attachment(?:\/|$))/i.test(u.pathname);
        } catch { return false; }
    }

    function looksLikePawchivePost(url) {
        try {
            const u = new URL(url);
            if (u.origin !== SITE_ORIGIN) return false;
            return /\/patreon\/user\/\d+\/post\/\d+\/?$/.test(u.pathname) ||
                   /\/fanbox\/user\/\d+\/post\/\d+\/?$/.test(u.pathname) ||
                   /\/discord\/(?:server|user)\/[^/]+\/post\/\d+\/?$/.test(u.pathname);
        } catch { return false; }
    }

    function looksLikeCreatorPage() {
        const p = location.pathname;
        return /^\/patreon\/user\/\d+\/?$/.test(p) ||
               /^\/fanbox\/user\/\d+\/?$/.test(p) ||
               /^\/discord\/(?:server|user)\/[^/]+\/?$/.test(p);
    }

    function getCreatorName() {
        const heading = document.querySelector('h1');
        return (heading && heading.textContent.trim()) || 'Pawchive Creator';
    }

    // ── Link extraction ───────────────────────────────────────────────────────
    function extractLinksFromScope(scope, baseUrl) {
        const results = new Set();

        for (const a of scope.querySelectorAll('a[href]')) {
            const href = absoluteUrl(a.getAttribute('href'), baseUrl);
            const cleaned = href ? cleanUrl(href) : null;
            if (cleaned && looksLikeDownload(cleaned)) results.add(cleaned);
        }

        const ownerDoc = scope.ownerDocument || document;
        const walker   = ownerDoc.createTreeWalker(scope, NodeFilter.SHOW_TEXT);
        let node;
        while ((node = walker.nextNode())) {
            const matches = node.nodeValue.match(URL_RE);
            if (!matches) continue;
            for (const raw of matches) {
                const cleaned = cleanUrl(raw);
                if (cleaned && looksLikeDownload(cleaned)) results.add(cleaned);
            }
        }
        return results;
    }

    function extractPostLinks(doc, postUrl) {
        const results = new Set();
        const headings = [...doc.querySelectorAll('h1, h2, h3, h4')]
            .filter(h => /\b(content|downloads?|files?)\b/i.test(h.textContent.trim()));

        for (const heading of headings) {
            const container = heading.closest('article, section, main, .post, .post-content, .post__content, .content') || heading.parentElement;
            if (!container) continue;
            for (const link of extractLinksFromScope(container, postUrl)) results.add(link);
        }

        if (results.size === 0) {
            for (const link of extractLinksFromScope(doc, postUrl)) results.add(link);
        }
        return [...results];
    }

    function getPostLinksFromCreatorDocument(doc, baseUrl) {
        const posts = new Set();
        for (const a of doc.querySelectorAll('a[href]')) {
            const href = absoluteUrl(a.getAttribute('href'), baseUrl);
            if (href && looksLikePawchivePost(href)) posts.add(href);
        }
        return [...posts];
    }

    function getTotalPostsFromDocument(doc) {
        const text  = doc.body?.textContent || '';
        const match = text.match(/Showing\s+\d+\s*-\s*\d+\s+of\s+([\d,]+)/i);
        return match ? Number(match[1].replace(/,/g, '')) : null;
    }

    async function fetchDocument(url) {
        const controller = new AbortController();
        const timer = setTimeout(() => controller.abort(), REQUEST_TIMEOUT_MS);
        try {
            const response = await fetch(url, {
                method: 'GET', credentials: 'include', cache: 'no-store', signal: controller.signal,
            });
            if (!response.ok) throw new Error(`HTTP ${response.status}`);
            const html = await response.text();
            return new DOMParser().parseFromString(html, 'text/html');
        } finally {
            clearTimeout(timer);
        }
    }

    // ── Creator page crawl ────────────────────────────────────────────────────
    async function collectCreatorPostUrls() {
        const creatorUrl = new URL(location.href);
        creatorUrl.search = '';

        const firstDoc   = await fetchDocument(creatorUrl.href);
        const totalPosts = getTotalPostsFromDocument(firstDoc);
        const postSet    = new Set(getPostLinksFromCreatorDocument(firstDoc, creatorUrl.href));

        let totalPages = totalPosts ? Math.ceil(totalPosts / POSTS_PER_PAGE) : MAX_PAGES;
        totalPages = Math.min(totalPages, MAX_PAGES);

        updateStatus(totalPosts ? `Found ${totalPosts.toLocaleString()} creator posts.` : 'Reading creator pages...');

        const failedPages = [];

        for (let page = 1; page < totalPages && !abortCollection; page++) {
            const pageUrl = new URL(creatorUrl.href);
            pageUrl.searchParams.set('o', page * POSTS_PER_PAGE);
            try {
                const doc = await fetchDocument(pageUrl.href);
                const posts = getPostLinksFromCreatorDocument(doc, pageUrl.href);
                const before = postSet.size;

                for (const post of posts) postSet.add(post);

                updateStatus(`Reading creator page ${page + 1}/${totalPages} — ${postSet.size.toLocaleString()} posts found`);

                if (posts.length === 0 || (postSet.size === before && page > 2)) break;
                await sleep(REQUEST_DELAY_MS);
            } catch (error) {
                console.warn('[Pawchive] Failed creator page:', page, error);
                failedPages.push({ page, url: pageUrl.href });
            }
        }

        for (let round = 1; round <= MAX_CREATOR_RETRIES && failedPages.length > 0 && !abortCollection; round++) {
            updateStatus(`Retrying ${failedPages.length} creator page(s) (round ${round}/${MAX_CREATOR_RETRIES})…`);
            const stillFailed = [];
            for (const { page, url } of failedPages) {
                if (abortCollection) break;
                try {
                    const doc = await fetchDocument(url);
                    const posts = getPostLinksFromCreatorDocument(doc, url);
                    for (const post of posts) postSet.add(post);
                    await sleep(REQUEST_DELAY_MS);
                } catch (error) {
                    stillFailed.push({ page, url });
                }
            }
            failedPages.length = 0;
            failedPages.push(...stillFailed);
        }
        return [...postSet];
    }

    async function processPost(postUrl) {
        try {
            const doc = await fetchDocument(postUrl);
            const title = doc.querySelector('h1')?.textContent.trim() || postUrl;

            for (const link of extractPostLinks(doc, postUrl)) {
                if (!collectedLinks.has(link)) {
                    collectedLinks.set(link, { url: link, postTitle: title, postUrl });
                }
            }
            failedPosts.delete(postUrl);
            return true;
        } catch (error) {
            console.warn('[Pawchive] Failed post:', postUrl, error);
            failedPosts.add(postUrl);
            return false;
        }
    }

    async function processPosts(postUrls) {
        failedPosts.clear();
        const totalPosts = postUrls.length;
        let processedCount = 0;
        const workers = [];

        for (let i = 0; i < Math.min(MAX_CONCURRENT_POSTS, postUrls.length); i++) {
            workers.push((async () => {
                try {
                    for (let j = i; j < postUrls.length; j += MAX_CONCURRENT_POSTS) {
                        if (abortCollection) break;
                        await processPost(postUrls[j]);
                        processedCount++;
                        updateStatus(`Scanning posts: ${processedCount}/${totalPosts} — ${collectedLinks.size} links`);
                        await sleep(REQUEST_DELAY_MS);
                    }
                } catch (err) {
                    console.error('[Pawchive] Worker loop failure:', err);
                }
            })());
        }

        await Promise.all(workers);

        let retryRound = 1;
        while (failedPosts.size > 0 && !abortCollection) {
            const toRetry = [...failedPosts];
            updateStatus(`Retry round ${retryRound} (${toRetry.length} posts): ${collectedLinks.size} links`);

            for (const postUrl of toRetry) {
                if (abortCollection) break;
                await processPost(postUrl);
                updateStatus(`Retry round ${retryRound}: retrying — ${collectedLinks.size} links`);
                await sleep(REQUEST_DELAY_MS * 2);
            }

            if (toRetry.filter(u => failedPosts.has(u)).length === toRetry.length) break;
            retryRound++;
        }
    }

    // ── Export ────────────────────────────────────────────────────────────────
    function downloadTextFile() {
        if (collectedLinks.size === 0) { alert('No links collected yet.'); return; }

        const items = [...collectedLinks.values()].sort((a, b) => a.url.localeCompare(b.url));
        const text = [
            `Creator: ${getCreatorName()}`,
            `Collected: ${new Date().toLocaleString()}`,
            `Total unique links: ${items.length}`,
            '',
            ...items.map(item => item.url),
        ].join('\n');

        const blob = new Blob([text], { type: 'text/plain;charset=utf-8' });
        const blobUrl = URL.createObjectURL(blob);
        const safeCreator = getCreatorName().replace(/[<>:"/\\|?*]+/g, '_').replace(/\s+/g, '_').slice(0, 80);

        const a = document.createElement('a');
        a.href = blobUrl;
        a.download = `${safeCreator || 'pawchive'}_download_links.txt`;
        document.body.appendChild(a);
        a.click();
        a.remove();
        setTimeout(() => URL.revokeObjectURL(blobUrl), 1000);
    }

    // ── Dragging & position persistence ───────────────────────────────────────
    let dragAbortController = null;

    function initDragging(root, shadow) {
        if (dragAbortController) dragAbortController.abort();
        dragAbortController = new AbortController();
        const { signal } = dragAbortController;

        const collapsed = shadow.querySelector('#pawchive-collapsed');
        let dragging = false, hasDragged = false;
        let ox = 0, oy = 0, startX = 0, startY = 0;

        const savedPos = loadPosition();
        if (savedPos) {
            const width = root.offsetWidth || 320;
            const height = root.offsetHeight || 500;
            const maxLeft = Math.max(0, window.innerWidth - width);
            const maxTop  = Math.max(0, window.innerHeight - height);

            root.style.right = 'auto';
            root.style.left  = Math.min(Math.max(0, savedPos.left ?? 0), maxLeft) + 'px';
            root.style.top   = Math.min(Math.max(0, savedPos.top ?? 0), maxTop) + 'px';
            syncCollapsedPos(root, collapsed);
        }

        function savePos() {
            const rect = root.getBoundingClientRect();
            savePosition({ left: rect.left, top: rect.top });
        }

        function beginDrag(e, targetEl) {
            if (e.button !== 0) return;
            dragging = true; hasDragged = false;
            startX = e.clientX; startY = e.clientY;
            const rect = targetEl.getBoundingClientRect();
            ox = e.clientX - rect.left; oy = e.clientY - rect.top;
            e.preventDefault();
        }

        shadow.querySelector('.plc-header')?.addEventListener('mousedown', e => beginDrag(e, root));
        shadow.querySelector('#pawchive-collapsed button')?.addEventListener('mousedown', e => beginDrag(e, collapsed));

        document.addEventListener('mousemove', e => {
            if (!dragging) return;
            if (!hasDragged && (Math.abs(e.clientX - startX) > 4 || Math.abs(e.clientY - startY) > 4)) hasDragged = true;

            const isCollapsed = collapsed.style.display !== 'none';
            const target = isCollapsed ? collapsed : root;
            const w = isCollapsed ? 48 : (root.offsetWidth || 320);
            const h = isCollapsed ? 48 : (root.offsetHeight || 500);

            const x = Math.max(0, Math.min(e.clientX - ox, window.innerWidth - w));
            const y = Math.max(0, Math.min(e.clientY - oy, window.innerHeight - h));

            target.style.right = 'auto';
            target.style.left  = x + 'px';
            target.style.top   = y + 'px';

            if (isCollapsed) {
                const panelWidth = root.offsetWidth || 320;
                root.style.left = Math.max(0, Math.min(x + w - panelWidth, window.innerWidth - panelWidth)) + 'px';
            }
        }, { signal });

        document.addEventListener('mouseup', () => {
            if (dragging) savePos();
            dragging = false;
        }, { signal });

        shadow.querySelector('#pawchive-restore').addEventListener('click', () => {
            if (hasDragged) { hasDragged = false; return; }
            shadow.querySelector('.plc-panel').style.display = '';
            collapsed.style.display = 'none';
        });
    }

    function syncCollapsedPos(panelRoot, pill) {
        const rect = panelRoot.getBoundingClientRect();
        pill.style.right = 'auto';
        pill.style.left  = (rect.right - (pill.offsetWidth || 48)) + 'px';
        pill.style.top   = rect.top + 'px';
    }

    // ── UI helpers ────────────────────────────────────────────────────────────
    function updateCounts() {
        const countEl = getUI('#pawchive-link-count');
        if (countEl) countEl.textContent = `${collectedLinks.size} links`;
        const exportBtn = getUI('#pawchive-export');
        if (exportBtn) exportBtn.disabled = collectedLinks.size === 0;
    }

    function updateStatus(message) {
        const status = getUI('#pawchive-status-text');
        if (status) status.textContent = message;
        updateCounts();
    }

    function makeSliderSetting(label, value, min, max, step, onChange) {
        const wrap = document.createElement('div');
        wrap.className = 'plc-slider-setting';

        const header = document.createElement('div');
        header.className = 'plc-slider-header';

        const valDisplay = document.createElement('span');
        valDisplay.className = 'plc-slider-value';
        valDisplay.textContent = value;

        const text = document.createElement('span');
        text.className = 'plc-slider-label';
        text.textContent = label;

        header.append(text, valDisplay);

        const input = document.createElement('input');
        input.type = 'range'; input.min = min; input.max = max;
        input.step = step; input.value = value; input.className = 'plc-slider-input';

        input.addEventListener('input', () => {
            let val = Number(input.value);
            valDisplay.textContent = val;
            onChange(val);
        });

        wrap.append(header, input);
        return wrap;
    }

    // ── Settings panel ────────────────────────────────────────────────────────
    function getAllFormats()   { return [...DEFAULT_FORMATS,   ...customFormats]; }
    function getAllProviders() { return [...DEFAULT_PROVIDERS, ...customProviders]; }

    function mkMiniBtn(label, onClick) {
        const btn = document.createElement('button');
        btn.className = 'plc-mini-btn'; btn.textContent = label;
        btn.addEventListener('click', onClick);
        return btn;
    }

    function makeToggle(label, isOn, onChange) {
        const wrap = document.createElement('label');
        wrap.className = 'plc-toggle-item';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox'; checkbox.checked = isOn;
        checkbox.addEventListener('change', () => onChange(checkbox.checked));

        const track = document.createElement('span'); track.className = 'plc-track';
        const text = document.createElement('span'); text.className = 'plc-toggle-label';
        text.textContent = label;

        wrap.append(checkbox, track, text);
        return wrap;
    }

    function makeCustomAdder(placeholder, onAdd) {
        const wrap = document.createElement('div'); wrap.className = 'plc-custom-adder';
        const input = document.createElement('input');
        input.type = 'text'; input.placeholder = placeholder; input.className = 'plc-custom-input';

        const btn = document.createElement('button');
        btn.textContent = '+'; btn.className = 'plc-add-btn';

        btn.addEventListener('click', () => {
            if (input.value.trim()) { onAdd(input.value.trim()); input.value = ''; }
        });
        input.addEventListener('keydown', e => {
            if (e.key === 'Enter') { e.preventDefault(); btn.click(); }
        });

        wrap.append(input, btn);
        return wrap;
    }

    function makeCustomItem(label, onRemove) {
        const item = document.createElement('div'); item.className = 'plc-custom-item';
        const text = document.createElement('span'); text.textContent = label;
        const btn = document.createElement('button');
        btn.textContent = '×'; btn.className = 'plc-remove-btn';
        btn.addEventListener('click', onRemove);
        item.append(text, btn);
        return item;
    }

    function buildSettingsPanel() {
        const container = getUI('#pawchive-settings-body');
        if (!container) return;
        container.innerHTML = '';

        // 1. Network
        const netHeader = document.createElement('div'); netHeader.className = 'plc-settings-section-header';
        const netTitle = document.createElement('div'); netTitle.className = 'plc-settings-section-title'; netTitle.textContent = 'Network & Scraping';

        const netBulk = document.createElement('div'); netBulk.className = 'plc-bulk-btns';
        netBulk.append(
            mkMiniBtn('Reset', () => {
                MAX_PAGES = DEFAULT_MAX_PAGES; saveNum(STORAGE_KEY_PAGES, DEFAULT_MAX_PAGES);
                MAX_CONCURRENT_POSTS = DEFAULT_CONCURRENT; saveNum(STORAGE_KEY_CONCURRENT, DEFAULT_CONCURRENT);
                REQUEST_DELAY_MS = DEFAULT_DELAY; saveNum(STORAGE_KEY_DELAY, DEFAULT_DELAY);
                MAX_CREATOR_RETRIES = DEFAULT_RETRIES; saveNum(STORAGE_KEY_RETRIES, DEFAULT_RETRIES);
                buildSettingsPanel();
            })
        );
        netHeader.append(netTitle, netBulk); container.appendChild(netHeader);

        container.appendChild(makeSliderSetting('Max Pages to Scan', MAX_PAGES, 1, 5000, 1, val => {
            MAX_PAGES = val; saveNum(STORAGE_KEY_PAGES, val);
        }));
        container.appendChild(makeSliderSetting('Max Concurrent Posts', MAX_CONCURRENT_POSTS, 1, 50, 1, val => {
            MAX_CONCURRENT_POSTS = val; saveNum(STORAGE_KEY_CONCURRENT, val);
        }));
        container.appendChild(makeSliderSetting('Request Delay (ms)', REQUEST_DELAY_MS, 0, 5000, 50, val => {
            REQUEST_DELAY_MS = val; saveNum(STORAGE_KEY_DELAY, val);
        }));
        container.appendChild(makeSliderSetting('Max Creator Retries', MAX_CREATOR_RETRIES, 0, 20, 1, val => {
            MAX_CREATOR_RETRIES = val; saveNum(STORAGE_KEY_RETRIES, val);
        }));

        const divider1 = document.createElement('div'); divider1.className = 'plc-divider'; container.appendChild(divider1);

        // 2. Formats
        const fmtHeader = document.createElement('div'); fmtHeader.className = 'plc-settings-section-header';
        const fmtTitle = document.createElement('div'); fmtTitle.className = 'plc-settings-section-title'; fmtTitle.textContent = 'File Formats';

        const fmtBulk = document.createElement('div'); fmtBulk.className = 'plc-bulk-btns';
        fmtBulk.append(
            mkMiniBtn('All', () => { getAllFormats().forEach(f => enabledFormats.add(f.ext)); saveSet(STORAGE_KEY_FORMATS, enabledFormats); invalidateFilterCaches(); buildSettingsPanel(); }),
            mkMiniBtn('None', () => { enabledFormats.clear(); saveSet(STORAGE_KEY_FORMATS, enabledFormats); invalidateFilterCaches(); buildSettingsPanel(); })
        );
        fmtHeader.append(fmtTitle, fmtBulk); container.appendChild(fmtHeader);

        const fmtGrid = document.createElement('div'); fmtGrid.className = 'plc-toggle-grid';
        getAllFormats().forEach(f => fmtGrid.appendChild(makeToggle(f.label || f.ext, enabledFormats.has(f.ext), on => {
            on ? enabledFormats.add(f.ext) : enabledFormats.delete(f.ext);
            saveSet(STORAGE_KEY_FORMATS, enabledFormats); invalidateFilterCaches();
        })));
        container.appendChild(fmtGrid);

        container.appendChild(makeCustomAdder('Add format (e.g. psd)', val => {
            const ext = val.replace(/^\./, '').toLowerCase().trim();
            if (!ext || getAllFormats().some(f => f.ext === ext)) return;
            customFormats.push({ ext, label: ext.toUpperCase() });
            enabledFormats.add(ext);
            saveArr(STORAGE_KEY_CUSTOM_FORMATS, customFormats); saveSet(STORAGE_KEY_FORMATS, enabledFormats);
            invalidateFilterCaches(); buildSettingsPanel();
        }));

        if (customFormats.length > 0) {
            const customList = document.createElement('div'); customList.className = 'plc-custom-list';
            customFormats.forEach(f => customList.appendChild(makeCustomItem(f.label || f.ext, () => {
                const idx = customFormats.indexOf(f); if (idx >= 0) customFormats.splice(idx, 1);
                enabledFormats.delete(f.ext);
                saveArr(STORAGE_KEY_CUSTOM_FORMATS, customFormats); saveSet(STORAGE_KEY_FORMATS, enabledFormats);
                invalidateFilterCaches(); buildSettingsPanel();
            })));
            container.appendChild(customList);
        }

        const divider2 = document.createElement('div'); divider2.className = 'plc-divider'; container.appendChild(divider2);

        // 3. Providers
        const pvdHeader = document.createElement('div'); pvdHeader.className = 'plc-settings-section-header';
        const pvdTitle = document.createElement('div'); pvdTitle.className = 'plc-settings-section-title'; pvdTitle.textContent = 'Providers';

        const pvdBulk = document.createElement('div'); pvdBulk.className = 'plc-bulk-btns';
        pvdBulk.append(
            mkMiniBtn('All', () => { getAllProviders().forEach(p => enabledProviders.add(p.host)); saveSet(STORAGE_KEY_PROVIDERS, enabledProviders); invalidateFilterCaches(); buildSettingsPanel(); }),
            mkMiniBtn('None', () => { enabledProviders.clear(); saveSet(STORAGE_KEY_PROVIDERS, enabledProviders); invalidateFilterCaches(); buildSettingsPanel(); })
        );
        pvdHeader.append(pvdTitle, pvdBulk); container.appendChild(pvdHeader);

        const pvdGrid = document.createElement('div'); pvdGrid.className = 'plc-toggle-grid';
        getAllProviders().forEach(p => pvdGrid.appendChild(makeToggle(p.label || p.host, enabledProviders.has(p.host), on => {
            on ? enabledProviders.add(p.host) : enabledProviders.delete(p.host);
            saveSet(STORAGE_KEY_PROVIDERS, enabledProviders); invalidateFilterCaches();
        })));
        container.appendChild(pvdGrid);

        container.appendChild(makeCustomAdder('Add host (e.g. site.com)', val => {
            const host = val.replace(/^https?:\/\//i, '').replace(/\/.*$/, '').toLowerCase().trim();
            if (!host || getAllProviders().some(p => p.host === host)) return;
            customProviders.push({ host, label: host }); enabledProviders.add(host);
            saveArr(STORAGE_KEY_CUSTOM_PROVIDERS, customProviders); saveSet(STORAGE_KEY_PROVIDERS, enabledProviders);
            invalidateFilterCaches(); buildSettingsPanel();
        }));

        if (customProviders.length > 0) {
            const customList = document.createElement('div'); customList.className = 'plc-custom-list';
            customProviders.forEach(p => customList.appendChild(makeCustomItem(p.label || p.host, () => {
                const idx = customProviders.indexOf(p); if (idx >= 0) customProviders.splice(idx, 1);
                enabledProviders.delete(p.host);
                saveArr(STORAGE_KEY_CUSTOM_PROVIDERS, customProviders); saveSet(STORAGE_KEY_PROVIDERS, enabledProviders);
                invalidateFilterCaches(); buildSettingsPanel();
            })));
            container.appendChild(customList);
        }
    }

    // ── CSS Styles ────────────────────────────────────────────────────────────
    const CSS_TEXT = `
        :host {
            position: fixed; right: 18px; top: 18px; width: 320px; z-index: 2147483647;
            font-family: Inter, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif; color: #fff;
        }
        * { box-sizing: border-box; }
        .plc-panel {
            background: rgba(20, 20, 26, 0.97); border: 1px solid rgba(255,255,255,.11);
            border-radius: 14px; box-shadow: 0 14px 40px rgba(0,0,0,.55); overflow: hidden; backdrop-filter: blur(14px);
        }
        .plc-header {
            display: flex; align-items: center; justify-content: space-between; padding: 11px 13px;
            background: rgba(255,255,255,.04); border-bottom: 1px solid rgba(255,255,255,.07); cursor: grab; user-select: none;
        }
        .plc-header:active { cursor: grabbing; }
        .plc-title { font-size: 13px; font-weight: 700; letter-spacing: .25px; color: rgba(255,255,255,.92); }
        .plc-header-actions { display: flex; align-items: center; gap: 4px; }
        .plc-icon-btn {
            width: 28px; height: 28px; border: 0; border-radius: 7px; background: rgba(255,255,255,.07);
            color: rgba(255,255,255,.75); cursor: pointer; font-size: 14px; line-height: 1;
            display: flex; align-items: center; justify-content: center; transition: background .15s;
        }
        .plc-icon-btn:hover { background: rgba(255,255,255,.14); color: #fff; }
        .plc-icon-btn.active { background: rgba(47,125,246,.3); color: #7eb3ff; }
        .plc-body { padding: 12px 13px; }
        .plc-status {
            min-height: 38px; margin-bottom: 10px; padding: 9px 10px; border-radius: 9px;
            background: rgba(255,255,255,.05); color: rgba(255,255,255,.78); font-size: 12px; line-height: 1.45;
        }
        .plc-count { margin-top: 4px; color: rgba(255,255,255,.45); }
        .plc-buttons { display: grid; grid-template-columns: 1fr 1fr; gap: 7px; }
        .plc-button {
            border: 0; border-radius: 9px; padding: 10px 9px; font-weight: 700; font-size: 12px;
            cursor: pointer; color: #fff; background: rgba(255,255,255,.08); transition: background .15s;
        }
        .plc-button:hover:not(:disabled) { background: rgba(255,255,255,.14); }
        .plc-button.primary { grid-column: 1 / -1; background: #2f7df6; }
        .plc-button.primary:hover:not(:disabled) { background: #246fe0; }
        .plc-button.danger { background: rgba(220,60,60,.18); }
        .plc-button.danger:hover { background: rgba(220,60,60,.28); }
        .plc-button:disabled { opacity: .4; cursor: not-allowed; }
        .plc-settings-wrap { overflow: hidden; max-height: 0; transition: max-height .28s ease; }
        .plc-settings-wrap.open { max-height: 700px; }
        .plc-settings-inner {
            border-top: 1px solid rgba(255,255,255,.07); padding: 12px 13px; max-height: 380px;
            overflow-y: auto; scrollbar-width: thin; scrollbar-color: rgba(255,255,255,.15) transparent;
        }
        .plc-settings-inner::-webkit-scrollbar { width: 4px; }
        .plc-settings-inner::-webkit-scrollbar-thumb { background: rgba(255,255,255,.15); border-radius: 4px; }
        .plc-settings-section-header { display: flex; align-items: center; justify-content: space-between; margin-bottom: 8px; }
        .plc-settings-section-title { font-size: 11px; font-weight: 700; color: rgba(255,255,255,.5); text-transform: uppercase; letter-spacing: .6px; }
        .plc-bulk-btns { display: flex; gap: 4px; }
        .plc-mini-btn {
            padding: 2px 8px; border: 1px solid rgba(255,255,255,.12); border-radius: 5px;
            background: transparent; color: rgba(255,255,255,.55); font-size: 10px; cursor: pointer; transition: background .12s, color .12s;
        }
        .plc-mini-btn:hover { background: rgba(255,255,255,.08); color: #fff; }
        .plc-toggle-grid { display: flex; flex-wrap: wrap; gap: 5px; margin-bottom: 10px; }
        .plc-toggle-item {
            position: relative; display: flex; align-items: center; gap: 5px; padding: 4px 8px 4px 6px;
            border-radius: 7px; background: rgba(255,255,255,.05); border: 1px solid rgba(255,255,255,.08);
            cursor: pointer; transition: background .12s, border-color .12s; user-select: none;
        }
        .plc-toggle-item:hover { background: rgba(255,255,255,.09); }
        .plc-toggle-item input[type="checkbox"] {
            position: absolute; inset: 0; width: 100%; height: 100%; margin: 0; padding: 0;
            opacity: 0; cursor: pointer; z-index: 1; appearance: none; -webkit-appearance: none;
        }
        .plc-track, .plc-toggle-label { pointer-events: none; }
        .plc-track {
            width: 26px; height: 14px; border-radius: 7px; background: rgba(255,255,255,.12);
            position: relative; flex-shrink: 0; transition: background .15s;
        }
        .plc-track::after {
            content: ''; position: absolute; top: 2px; left: 2px; width: 10px; height: 10px;
            border-radius: 50%; background: rgba(255,255,255,.5); transition: transform .15s, background .15s;
        }
        .plc-toggle-item input:checked ~ .plc-track { background: #2f7df6; }
        .plc-toggle-item input:checked ~ .plc-track::after { transform: translateX(12px); background: #fff; }
        .plc-toggle-label { font-size: 11px; color: rgba(255,255,255,.75); font-weight: 600; }
        .plc-divider { border: none; border-top: 1px solid rgba(255,255,255,.07); margin: 10px 0; }
        .plc-custom-adder { display: flex; gap: 5px; margin-bottom: 7px; }
        .plc-custom-input {
            flex: 1; border: 1px solid rgba(255,255,255,.12); border-radius: 7px; background: rgba(255,255,255,.05);
            color: #fff; font-size: 11px; padding: 5px 8px; outline: none; transition: border-color .15s;
        }
        .plc-custom-input::placeholder { color: rgba(255,255,255,.3); }
        .plc-custom-input:focus { border-color: rgba(47,125,246,.6); }
        .plc-add-btn {
            width: 28px; height: 28px; border: 1px solid rgba(47,125,246,.35); border-radius: 7px;
            background: rgba(47,125,246,.12); color: #7eb3ff; font-size: 16px; cursor: pointer;
            display: flex; align-items: center; justify-content: center; transition: background .12s; flex-shrink: 0;
        }
        .plc-add-btn:hover { background: rgba(47,125,246,.22); }
        .plc-custom-list { display: flex; flex-wrap: wrap; gap: 5px; margin-bottom: 6px; }
        .plc-custom-item {
            display: flex; align-items: center; gap: 4px; padding: 3px 5px 3px 8px;
            border-radius: 6px; background: rgba(47,125,246,.1); border: 1px solid rgba(47,125,246,.25);
            font-size: 11px; color: #7eb3ff;
        }
        .plc-remove-btn { border: 0; background: none; color: rgba(255,100,100,.7); cursor: pointer; font-size: 14px; line-height: 1; padding: 0 1px; }
        .plc-remove-btn:hover { color: rgba(255,100,100,1); }
        .plc-slider-setting { margin-bottom: 12px; }
        .plc-slider-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px; }
        .plc-slider-label { font-size: 11px; font-weight: 600; color: rgba(255,255,255,.75); }
        .plc-slider-value {
            font-size: 11px; font-weight: 700; color: #7eb3ff; background: rgba(47,125,246,.12);
            padding: 2px 6px; border-radius: 4px; min-width: 32px; text-align: center;
        }
        .plc-slider-input { width: 100%; appearance: none; -webkit-appearance: none; height: 4px; background: rgba(255,255,255,.12); border-radius: 2px; outline: none; margin: 0; }
        .plc-slider-input::-webkit-slider-thumb {
            -webkit-appearance: none; appearance: none; width: 14px; height: 14px;
            border-radius: 50%; background: #2f7df6; cursor: pointer; transition: transform .1s, background .1s;
        }
        .plc-slider-input::-webkit-slider-thumb:hover { transform: scale(1.15); background: #4a8df8; }
        .plc-slider-input::-moz-range-thumb { width: 14px; height: 14px; border: none; border-radius: 50%; background: #2f7df6; cursor: pointer; transition: transform .1s, background .1s; }
        .plc-slider-input::-moz-range-thumb:hover { transform: scale(1.15); background: #4a8df8; }
        #pawchive-collapsed { display: none; position: fixed; right: 18px; top: 18px; z-index: 2147483647; width: 48px; height: 48px; }
        #pawchive-collapsed button {
            width: 100%; height: 100%; border: 1px solid rgba(255,255,255,.12); border-radius: 14px;
            background: rgba(20,20,26,.97); color: #fff; box-shadow: 0 10px 25px rgba(0,0,0,.35); cursor: grab; font-size: 19px;
        }
        #pawchive-collapsed button:active { cursor: grabbing; }
        @media (max-width: 600px) {
            :host { right: 10px; top: 10px; width: min(320px, calc(100vw - 20px)); }
            #pawchive-collapsed { right: 10px !important; top: 10px !important; }
        }
    `;

    // ── UI Construction (Shadow DOM) ──────────────────────────────────────────
    function createUI() {
        let root = document.querySelector('#pawchive-link-collector');
        if (!root) {
            root = document.createElement('div');
            root.id = 'pawchive-link-collector';
            // Mount to documentElement so aggressive SPA frameworks don't tear it down with the body.
            document.documentElement.appendChild(root);
        }

        if (root.shadowRoot) {
            shadowRoot = root.shadowRoot;
            return;
        }

        shadowRoot = root.attachShadow({ mode: 'open' });
        const styleEl = document.createElement('style');
        styleEl.textContent = CSS_TEXT;
        shadowRoot.appendChild(styleEl);

        const container = document.createElement('div');
        container.innerHTML = `
            <div class="plc-panel">
                <div class="plc-header">
                    <div class="plc-title">🐾 Link Collector</div>
                    <div class="plc-header-actions">
                        <button class="plc-icon-btn" id="pawchive-settings-toggle" title="Filter settings">⚙</button>
                        <button class="plc-icon-btn" id="pawchive-minimize" title="Minimize">−</button>
                    </div>
                </div>
                <div class="plc-body">
                    <div class="plc-status">
                        <span id="pawchive-status-text">Ready.</span>
                        <div class="plc-count" id="pawchive-link-count">0 links</div>
                    </div>
                    <div class="plc-buttons">
                        <button class="plc-button primary" id="pawchive-collect">Collect Downloads</button>
                        <button class="plc-button" id="pawchive-export" disabled>Export .TXT</button>
                        <button class="plc-button danger" id="pawchive-clear">Clear</button>
                    </div>
                </div>
                <div class="plc-settings-wrap" id="pawchive-settings-wrap">
                    <div class="plc-settings-inner" id="pawchive-settings-body"></div>
                </div>
            </div>
            <div id="pawchive-collapsed"><button id="pawchive-restore" title="Open Pawchive Link Collector">🐾</button></div>
        `;
        shadowRoot.appendChild(container);

        const panel = shadowRoot.querySelector('.plc-panel');
        const collapsed = shadowRoot.querySelector('#pawchive-collapsed');
        const settingsWrap = shadowRoot.querySelector('#pawchive-settings-wrap');

        collapsed.style.display = 'none';
        initDragging(root, shadowRoot);

        shadowRoot.querySelector('#pawchive-settings-toggle').addEventListener('click', function () {
            const isOpen = settingsWrap.classList.toggle('open');
            this.classList.toggle('active', isOpen);
            if (isOpen) buildSettingsPanel();
        });

        shadowRoot.querySelector('#pawchive-minimize').addEventListener('click', () => {
            syncCollapsedPos(root, collapsed);
            panel.style.display = 'none';
            collapsed.style.display = 'block';
        });

        shadowRoot.querySelector('#pawchive-export').addEventListener('click', downloadTextFile);
        shadowRoot.querySelector('#pawchive-clear').addEventListener('click', () => {
            collectedLinks.clear(); updateStatus('Collection cleared.');
        });

        shadowRoot.querySelector('#pawchive-collect').addEventListener('click', function () {
            if (isRunning) {
                abortCollection = true; this.textContent = 'Stopping…'; this.disabled = true;
            } else startCollection();
        });
    }

    // ── Collection entry point ────────────────────────────────────────────────
    async function startCollection() {
        if (isRunning) return;
        if (!looksLikeCreatorPage()) {
            updateStatus('Open a Pawchive creator page first. The collector works on creator pages so it can scan all posts.');
            return;
        }

        isRunning = true; abortCollection = false;
        const button = getUI('#pawchive-collect');
        if (button) button.textContent = 'Stop Collection';

        try {
            collectedLinks.clear(); updateStatus('Finding all creator posts…');
            const postUrls = await collectCreatorPostUrls();

            if (postUrls.length === 0) { updateStatus('No posts were found.'); return; }

            updateStatus(`Found ${postUrls.length.toLocaleString()} posts. Scanning for downloads…`);
            await processPosts(postUrls);

            if (abortCollection) {
                updateStatus(`Collection stopped. Found ${collectedLinks.size.toLocaleString()} links.`);
            } else if (failedPosts.size === 0) {
                updateStatus(`✓ Done. ${collectedLinks.size.toLocaleString()} unique links. All posts scanned!`);
            } else {
                updateStatus(`⚠ Done with issues. ${collectedLinks.size.toLocaleString()} links, ${failedPosts.size} posts unreachable.`);
            }
        } catch (error) {
            console.error('[Pawchive] Collection failed:', error);
            updateStatus(`Error: ${error.message || 'Unknown error'}`);
        } finally {
            isRunning = false;
            const collectBtn = getUI('#pawchive-collect');
            if (collectBtn) { collectBtn.disabled = false; collectBtn.textContent = 'Collect Downloads'; }
            updateCounts();
        }
    }

    // ── SPA Navigation Listener ───────────────────────────────────────────────
    function setupSpaNavigation() {
        let lastUrl = location.href;

        function handleUrlChange() {
            if (location.href === lastUrl) return;
            lastUrl = location.href;

            if (!document.querySelector('#pawchive-link-collector')) createUI();

            if (!isRunning) {
                looksLikeCreatorPage() ? updateStatus(`Ready to scan ${getCreatorName()}.`) : updateStatus('Open a creator page to collect links.');
            }
        }

        const originalPushState = history.pushState;
        history.pushState = function (...args) { originalPushState.apply(this, args); handleUrlChange(); };
        const originalReplaceState = history.replaceState;
        history.replaceState = function (...args) { originalReplaceState.apply(this, args); handleUrlChange(); };

        window.addEventListener('popstate', handleUrlChange);

        setInterval(() => {
            if (!document.querySelector('#pawchive-link-collector')) createUI();
            handleUrlChange();
        }, 1000);
    }

    // ── Init ──────────────────────────────────────────────────────────────────
    function init() {
        createUI();
        setupSpaNavigation();
        looksLikeCreatorPage() ? updateStatus(`Ready to scan ${getCreatorName()}.`) : updateStatus('Open a creator page to collect links.');
    }

    init();
})();
