import { defineConfig } from 'vitest/config';

export default defineConfig({
  test: {
    include: ['tests/**/*.test.ts'],
    environment: 'node',
    globals: true,
    reporters: ['default'],
    coverage: {
      include: ['src/core/**', 'src/adapters/**', 'src/shared/**'],
    },
  },
});
