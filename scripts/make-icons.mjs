/**
 * Generates the extension icons (16/32/48/128 px) with a tiny dependency-free
 * PNG encoder, so the repo has no image build step and no binary blobs checked in.
 */
import { mkdir, writeFile } from 'node:fs/promises';
import path from 'node:path';
import zlib from 'node:zlib';

const SIZES = [16, 32, 48, 128];

function crc32(buffer) {
  let crc = ~0;
  for (const byte of buffer) {
    crc ^= byte;
    for (let bit = 0; bit < 8; bit += 1) {
      crc = (crc >>> 1) ^ (0xedb88320 & -(crc & 1));
    }
  }
  return ~crc >>> 0;
}

function chunk(type, data) {
  const length = Buffer.alloc(4);
  length.writeUInt32BE(data.length, 0);
  const typeBuffer = Buffer.from(type, 'ascii');
  const crc = Buffer.alloc(4);
  crc.writeUInt32BE(crc32(Buffer.concat([typeBuffer, data])), 0);
  return Buffer.concat([length, typeBuffer, data, crc]);
}

function encodePng(width, height, rgba) {
  const raw = Buffer.alloc((width * 4 + 1) * height);
  for (let y = 0; y < height; y += 1) {
    raw[y * (width * 4 + 1)] = 0; // filter: none
    rgba.copy(raw, y * (width * 4 + 1) + 1, y * width * 4, (y + 1) * width * 4);
  }
  const ihdr = Buffer.alloc(13);
  ihdr.writeUInt32BE(width, 0);
  ihdr.writeUInt32BE(height, 4);
  ihdr[8] = 8; // bit depth
  ihdr[9] = 6; // RGBA
  return Buffer.concat([
    Buffer.from([0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a]),
    chunk('IHDR', ihdr),
    chunk('IDAT', zlib.deflateSync(raw, { level: 9 })),
    chunk('IEND', Buffer.alloc(0)),
  ]);
}

/** Signed distance style helpers for anti-aliased shapes. */
function roundedRectCoverage(x, y, size, radius, inset) {
  const min = inset;
  const max = size - inset;
  const cx = Math.min(Math.max(x, min + radius), max - radius);
  const cy = Math.min(Math.max(y, min + radius), max - radius);
  const distance = Math.hypot(x - cx, y - cy) - radius;
  return Math.min(Math.max(0.5 - distance, 0), 1);
}

function arrowCoverage(x, y, size) {
  const cx = size / 2;
  const stroke = Math.max(1.6, size * 0.11);
  const top = size * 0.22;
  const headTop = size * 0.46;
  const bottom = size * 0.72;

  // Vertical shaft.
  if (x >= cx - stroke / 2 && x <= cx + stroke / 2 && y >= top && y <= headTop + stroke * 0.2) return 1;
  // Arrow head (triangle).
  if (y >= headTop && y <= bottom) {
    const halfWidth = ((y - headTop) / (bottom - headTop)) * (size * 0.26);
    if (Math.abs(x - cx) <= halfWidth) return 1;
  }
  // Baseline.
  const baseY = size * 0.82;
  if (y >= baseY - stroke / 2 && y <= baseY + stroke / 2 && x >= size * 0.24 && x <= size * 0.76) return 1;
  return 0;
}

function renderIcon(size) {
  const rgba = Buffer.alloc(size * size * 4);
  const supersample = size <= 32 ? 4 : 3;
  const step = 1 / supersample;

  for (let y = 0; y < size; y += 1) {
    for (let x = 0; x < size; x += 1) {
      let bgAlpha = 0;
      let fgAlpha = 0;
      let samples = 0;
      for (let sy = 0; sy < supersample; sy += 1) {
        for (let sx = 0; sx < supersample; sx += 1) {
          const px = x + (sx + 0.5) * step;
          const py = y + (sy + 0.5) * step;
          samples += 1;
          bgAlpha += roundedRectCoverage(px, py, size, size * 0.24, size * 0.03);
          fgAlpha += arrowCoverage(px, py, size);
        }
      }
      bgAlpha /= samples;
      fgAlpha = Math.min(fgAlpha / samples, bgAlpha);

      const t = (x + y) / (2 * size);
      const start = [0x4b, 0x6b, 0xff];
      const end = [0x7b, 0x4b, 0xff];
      const base = start.map((channel, index) => Math.round(channel + (end[index] - channel) * t));
      const color = base.map((channel, index) => Math.round(channel * (1 - fgAlpha) + 255 * fgAlpha));
      const alpha = Math.round(bgAlpha * 255);

      const offset = (y * size + x) * 4;
      rgba[offset] = color[0];
      rgba[offset + 1] = color[1];
      rgba[offset + 2] = color[2];
      rgba[offset + 3] = alpha;
    }
  }
  return encodePng(size, size, rgba);
}

export async function generateIcons(outDir) {
  await mkdir(outDir, { recursive: true });
  const written = [];
  for (const size of SIZES) {
    const file = path.join(outDir, `icon${size}.png`);
    await writeFile(file, renderIcon(size));
    written.push(file);
  }
  return written;
}

if (import.meta.url === `file://${process.argv[1]}`) {
  const target = process.argv[2] ?? 'dist/icons';
  const files = await generateIcons(path.resolve(process.cwd(), target));
  console.log(`Generated ${files.length} icons in ${target}`);
}
