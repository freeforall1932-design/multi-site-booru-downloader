package com.bisimplex.firebooru.custom;
public class SecurePreferences {
    private static final String CHARSET = "UTF-8";
    private static final String KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_HASH_TRANSFORMATION = "SHA-256";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private final boolean encryptKeys;
    private final javax.crypto.Cipher keyWriter;
    private final android.content.SharedPreferences preferences;
    private final javax.crypto.Cipher reader;
    private final javax.crypto.Cipher writer;

    public SecurePreferences(android.content.Context p3, String p4, String p5, boolean p6)
    {
        try {
            this.writer = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.reader = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.keyWriter = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
            this.initCiphers(p5);
            this.preferences = p3.getSharedPreferences(p4, 0);
            this.encryptKeys = p6;
            return;
        } catch (java.io.UnsupportedEncodingException v3_3) {
            throw new com.bisimplex.firebooru.custom.SecurePreferences$SecurePreferencesException(v3_3);
        } catch (java.io.UnsupportedEncodingException v3_2) {
            throw new com.bisimplex.firebooru.custom.SecurePreferences$SecurePreferencesException(v3_2);
        }
    }

    private static byte[] convert(javax.crypto.Cipher p0, byte[] p1)
    {
        try {
            return p0.doFinal(p1);
        } catch (Exception v0_2) {
            throw new com.bisimplex.firebooru.custom.SecurePreferences$SecurePreferencesException(v0_2);
        }
    }

    private void putValue(String p2, String p3)
    {
        this.preferences.edit().putString(p2, this.encrypt(p3, this.writer)).commit();
        return;
    }

    private String toKey(String p2)
    {
        if (this.encryptKeys) {
            p2 = this.encrypt(p2, this.keyWriter);
        }
        return p2;
    }

    public void clear()
    {
        this.preferences.edit().clear().commit();
        return;
    }

    public boolean containsKey(String p2)
    {
        return this.preferences.contains(this.toKey(p2));
    }

    protected byte[] createKeyBytes(String p3)
    {
        java.security.MessageDigest v0_1 = java.security.MessageDigest.getInstance("SHA-256");
        v0_1.reset();
        return v0_1.digest(p3.getBytes("UTF-8"));
    }

    protected String decrypt(String p3)
    {
        try {
            return new String(com.bisimplex.firebooru.custom.SecurePreferences.convert(this.reader, android.util.Base64.decode(p3, 2)), "UTF-8");
        } catch (java.io.UnsupportedEncodingException v3_1) {
            throw new com.bisimplex.firebooru.custom.SecurePreferences$SecurePreferencesException(v3_1);
        }
    }

    protected String encrypt(String p2, javax.crypto.Cipher p3)
    {
        try {
            return android.util.Base64.encodeToString(com.bisimplex.firebooru.custom.SecurePreferences.convert(p3, p2.getBytes("UTF-8")), 2);
        } catch (java.io.UnsupportedEncodingException v2_4) {
            throw new com.bisimplex.firebooru.custom.SecurePreferences$SecurePreferencesException(v2_4);
        }
    }

    protected javax.crypto.spec.IvParameterSpec getIv()
    {
        byte[] v0_2 = new byte[this.writer.getBlockSize()];
        System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".getBytes(), 0, v0_2, 0, this.writer.getBlockSize());
        return new javax.crypto.spec.IvParameterSpec(v0_2);
    }

    protected javax.crypto.spec.SecretKeySpec getSecretKey(String p3)
    {
        return new javax.crypto.spec.SecretKeySpec(this.createKeyBytes(p3), "AES/CBC/PKCS5Padding");
    }

    public String getString(String p3)
    {
        if (!this.preferences.contains(this.toKey(p3))) {
            return 0;
        } else {
            return this.decrypt(this.preferences.getString(this.toKey(p3), ""));
        }
    }

    protected void initCiphers(String p5)
    {
        javax.crypto.Cipher v0_0 = this.getIv();
        javax.crypto.spec.SecretKeySpec v5_1 = this.getSecretKey(p5);
        this.writer.init(1, v5_1, v0_0);
        this.reader.init(2, v5_1, v0_0);
        this.keyWriter.init(1, v5_1);
        return;
    }

    public void put(String p1, String p2)
    {
        if (p2 != null) {
            this.putValue(this.toKey(p1), p2);
            return;
        } else {
            this.preferences.edit().remove(this.toKey(p1)).commit();
            return;
        }
    }

    public void removeValue(String p2)
    {
        this.preferences.edit().remove(this.toKey(p2)).commit();
        return;
    }
}
