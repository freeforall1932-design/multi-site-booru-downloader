/** Test-only base64 decode (Buffer is not available inside the extension bundle). */
export function decodeBase64(value: string): string {
  return Buffer.from(value, 'base64').toString('utf8');
}
