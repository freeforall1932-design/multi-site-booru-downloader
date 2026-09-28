/**
 * Build script: compile TypeScript to ESM with the TypeScript compiler and copy
 * the static extension assets next to it. No bundler - MV3 service workers and
 * extension pages natively support ES modules, which keeps the build inspectable.
 */
import { execFileSync } from 'node:child_process';
import { mkdir, readdir, copyFile, rm, stat, writeFile, readFile } from 'node:fs/promises';
import path from 'node:path';
import { generateIcons } from './make-icons.mjs';

const root = process.cwd();
const srcDir = path.join(root, 'src');
const distDir = path.join(root, 'dist');

const COPY_EXTENSIONS = new Set(['.html', '.css', '.json', '.svg', '.png']);
const SKIP_DIRECTORIES = new Set(['preview']);

async function walk(directory) {
  const entries = await readdir(directory, { withFileTypes: true });
  const files = [];
  for (const entry of entries) {
    const full = path.join(directory, entry.name);
    if (entry.isDirectory()) {
      files.push(...(await walk(full)));
    } else {
      files.push(full);
    }
  }
  return files;
}

async function exists(target) {
  try {
    await stat(target);
    return true;
  } catch {
    return false;
  }
}

async function copyStaticAssets() {
  const files = await walk(srcDir);
  let copied = 0;
  for (const file of files) {
    const relative = path.relative(srcDir, file);
    const parts = relative.split(path.sep);
    if (parts.some((part) => SKIP_DIRECTORIES.has(part))) continue;
    const extension = path.extname(file);
    if (!COPY_EXTENSIONS.has(extension)) continue;
    const destination = path.join(distDir, relative);
    await mkdir(path.dirname(destination), { recursive: true });
    await copyFile(file, destination);
    copied += 1;
  }
  return copied;
}

async function writeBuildInfo() {
  const manifest = JSON.parse(await readFile(path.join(root, 'src', 'manifest.json'), 'utf8'));
  const info = {
    name: manifest.name,
    version: manifest.version,
    builtAt: new Date().toISOString(),
  };
  await writeFile(path.join(distDir, 'build-info.json'), `${JSON.stringify(info, null, 2)}\n`);
}

async function main() {
  await rm(distDir, { recursive: true, force: true });
  await mkdir(distDir, { recursive: true });

  console.log('• type-checking and compiling TypeScript…');
  execFileSync(path.join(root, 'node_modules', '.bin', 'tsc'), ['-p', 'tsconfig.json'], {
    cwd: root,
    stdio: 'inherit',
  });

  console.log('• copying static assets…');
  const copied = await copyStaticAssets();

  console.log('• generating icons…');
  const icons = await generateIcons(path.join(distDir, 'icons'));

  await writeBuildInfo();

  console.log(`✓ Built ${path.relative(root, distDir)} (${copied} static file(s), ${icons.length} icon(s))`);
  if (!(await exists(path.join(distDir, 'manifest.json')))) {
    console.warn('! dist/manifest.json is missing - the extension will not load');
    process.exitCode = 1;
  }
}

await main();
