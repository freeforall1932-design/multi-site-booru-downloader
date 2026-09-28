package com.bisimplex.firebooru.network;
public class HydrusClient {
    private static final com.bisimplex.firebooru.network.HydrusClient client;
    private final java.util.concurrent.Executor executor;

    static HydrusClient()
    {
        com.bisimplex.firebooru.network.HydrusClient.client = new com.bisimplex.firebooru.network.HydrusClient();
        return;
    }

    private HydrusClient()
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        return;
    }

    public static com.bisimplex.firebooru.network.HydrusClient getInstance()
    {
        return com.bisimplex.firebooru.network.HydrusClient.client;
    }

    static synthetic void lambda$sendToHydrus$0(com.bisimplex.firebooru.danbooru.ServerItem p6, java.util.List p7)
    {
        java.io.UnsupportedEncodingException v6_1;
        okhttp3.OkHttpClient v0 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
        String v1_2 = String.format("%s/add_urls/add_url", new Object[] {p6.getUrl()}));
        if (android.text.TextUtils.isEmpty(p6.getApiKey())) {
            v6_1 = "";
        } else {
            try {
                v6_1 = java.net.URLEncoder.encode(p6.getApiKey(), "utf-8");
            } catch (java.io.UnsupportedEncodingException v6_3) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_3);
                return;
            }
        }
        java.util.Iterator v7_2 = p7.iterator();
        while (v7_2.hasNext()) {
            Throwable v2_5 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v7_2.next());
            String v3_1 = new com.google.gson.JsonObject();
            Throwable v2_6 = v2_5.getPostUrl();
            if (!android.text.TextUtils.isEmpty(v2_6)) {
                v3_1.addProperty("url", v2_6);
                v3_1.addProperty("destination_page_name", "Anime boxes");
                try {
                    Throwable v2_14 = v0.newCall(new okhttp3.Request$Builder().url(v1_2).addHeader("Content-Type", "application/json").addHeader("Hydrus-Client-API-Access-Key", v6_1).post(okhttp3.RequestBody.create(v3_1.toString(), 0)).build()).execute();
                    try {
                        if (!v2_14.isSuccessful()) {
                            android.util.Log.e("HydrusClient", new StringBuilder().append("Error from Hydrus code ").append(v2_14.code()).toString());
                        }
                        if (v2_14 != null) {
                            v2_14.close();
                        }
                    } catch (String v3_10) {
                        if (v2_14 != null) {
                            try {
                                v2_14.close();
                            } catch (Throwable v2_15) {
                                v3_10.addSuppressed(v2_15);
                            }
                        }
                        throw v3_10;
                    }
                } catch (Throwable v2_16) {
                    v2_16.printStackTrace();
                }
            }
        }
        return;
    }

    static synthetic void lambda$sendToHydrus$1(com.bisimplex.firebooru.network.SourceQuery p6, com.bisimplex.firebooru.danbooru.ServerItem p7)
    {
        com.bisimplex.firebooru.network.Utils v7_1;
        java.util.Iterator v6_3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadFavoritesByFilter(p6, -1);
        okhttp3.OkHttpClient v0_1 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
        String v1_1 = String.format("%s/add_urls/add_url", new Object[] {p7.getUrl()}));
        if (android.text.TextUtils.isEmpty(p7.getApiKey())) {
            v7_1 = "";
        } else {
            try {
                v7_1 = java.net.URLEncoder.encode(p7.getApiKey(), "utf-8");
            } catch (java.util.Iterator v6_1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_1);
                return;
            }
        }
        java.util.Iterator v6_2 = v6_3.iterator();
        while (v6_2.hasNext()) {
            Throwable v2_6 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_2.next());
            String v3_1 = new com.google.gson.JsonObject();
            Throwable v2_7 = v2_6.getPostUrl();
            if (!android.text.TextUtils.isEmpty(v2_7)) {
                v3_1.addProperty("url", v2_7);
                v3_1.addProperty("destination_page_name", "Anime boxes");
                try {
                    Throwable v2_14 = v0_1.newCall(new okhttp3.Request$Builder().url(v1_1).addHeader("Content-Type", "application/json").addHeader("Hydrus-Client-API-Access-Key", v7_1).post(okhttp3.RequestBody.create(v3_1.toString(), 0)).build()).execute();
                    try {
                        if (!v2_14.isSuccessful()) {
                            android.util.Log.e("HydrusClient", new StringBuilder().append("Error from Hydrus code ").append(v2_14.code()).toString());
                        }
                        if (v2_14 != null) {
                            v2_14.close();
                        }
                    } catch (String v3_10) {
                        if (v2_14 != null) {
                            try {
                                v2_14.close();
                            } catch (Throwable v2_15) {
                                v3_10.addSuppressed(v2_15);
                            }
                        }
                        throw v3_10;
                    }
                } catch (Throwable v2_16) {
                    v2_16.printStackTrace();
                }
            }
        }
        return;
    }

    public void sendToHydrus(com.bisimplex.firebooru.danbooru.ServerItem p3, com.bisimplex.firebooru.network.SourceQuery p4)
    {
        if ((p3 != null) && (p4 != null)) {
            this.executor.execute(new com.bisimplex.firebooru.network.HydrusClient$$ExternalSyntheticLambda0(p4, p3));
        }
        return;
    }

    public void sendToHydrus(com.bisimplex.firebooru.danbooru.ServerItem p3, java.util.List p4)
    {
        if ((p3 != null) && ((p4 != null) && (!p4.isEmpty()))) {
            this.executor.execute(new com.bisimplex.firebooru.network.HydrusClient$$ExternalSyntheticLambda1(p3, new java.util.ArrayList(p4)));
        }
        return;
    }
}
