package com.bisimplex.firebooru.custom;
public class CustomPersistentCookieJar extends com.franmontiel.persistentcookiejar.PersistentCookieJar {
    private final com.franmontiel.persistentcookiejar.cache.CookieCache myCache;
    private final com.franmontiel.persistentcookiejar.persistence.CookiePersistor myPersistor;

    public CustomPersistentCookieJar(com.franmontiel.persistentcookiejar.cache.CookieCache p1, com.franmontiel.persistentcookiejar.persistence.CookiePersistor p2)
    {
        super(p1, p2);
        super.myCache = p1;
        super.myPersistor = p2;
        return;
    }

    private static boolean myIsCookieExpired(okhttp3.Cookie p4)
    {
        if (p4.expiresAt() >= System.currentTimeMillis()) {
            return 0;
        } else {
            return 1;
        }
    }

    public declared_synchronized java.util.List loadForRequest(okhttp3.HttpUrl p7)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v2_1 = this.myCache.iterator();
        while (v2_1.hasNext()) {
            okhttp3.Cookie v3_1 = ((okhttp3.Cookie) v2_1.next());
            if (!com.bisimplex.firebooru.custom.CustomPersistentCookieJar.myIsCookieExpired(v3_1)) {
                if (!v3_1.matches(p7)) {
                    if (p7.host().contains(v3_1.domain())) {
                        v1_1.add(v3_1);
                    }
                } else {
                    v1_1.add(v3_1);
                }
            } else {
                v0_1.add(v3_1);
                v2_1.remove();
            }
        }
        this.myPersistor.removeAll(v0_1);
        return v1_1;
    }

    public declared_synchronized void saveFromResponse(okhttp3.HttpUrl p1, java.util.List p2)
    {
        this.myCache.addAll(p2);
        this.myPersistor.saveAll(p2);
        return;
    }
}
