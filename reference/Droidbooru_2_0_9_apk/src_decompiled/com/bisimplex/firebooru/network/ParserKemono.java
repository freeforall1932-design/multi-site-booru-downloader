package com.bisimplex.firebooru.network;
public class ParserKemono extends com.bisimplex.firebooru.network.ParserPosts {
    private android.net.Uri baseUri;
    private final java.util.List paths;

    public ParserKemono(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super(p2);
        super.paths = new java.util.ArrayList(0);
        return;
    }

    private void checkIsUnique(java.util.List p3, String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.Iterator v0_1 = p3.iterator();
            while (v0_1.hasNext()) {
                if (((String) v0_1.next()).equalsIgnoreCase(p4)) {
                }
            }
            p3.add(p4);
            return;
        }
        return;
    }

    private android.net.Uri getBaseUri()
    {
        if (this.baseUri == null) {
            this.baseUri = android.net.Uri.parse(this.params.getProvider().getServerDescription().getUrl());
        }
        return this.baseUri;
    }

    private com.bisimplex.firebooru.danbooru.DanbooruPost initPost(String p5, String p6, java.util.Date p7, String p8, String p9)
    {
        String v5_3 = this.getBaseUri().buildUpon().path(p5).build();
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v5_3.toString(), 0, 0);
        if (this.isSupportedExtension(v0_1.getExtension())) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_4 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            v1_4.setTags("");
            v1_4.setPostId(p6);
            v1_4.setCreated_at(p7);
            if (!android.text.TextUtils.isEmpty(p9)) {
                v1_4.setSource(p9);
            }
            int v6_5 = this.getBaseUri().buildUpon().appendPath("thumbnail").appendPath("data");
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v7_4 = v5_3.getPathSegments().iterator();
            while (v7_4.hasNext()) {
                v6_5.appendPath(((String) v7_4.next()));
            }
            v1_4.setPostUrl(p8);
            v1_4.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v6_5.toString(), 0, 0));
            if (v0_1.isVideo()) {
                v1_4.getPreview().setExt("");
            }
            v1_4.setFile(v0_1);
            v1_4.setSample(v0_1);
            String v5_2 = v5_3.getLastPathSegment();
            int v6_10 = v5_2.lastIndexOf(46);
            if (v6_10 > 0) {
                v5_2 = v5_2.substring(0, v6_10);
            }
            v1_4.setMd5(v5_2);
            return v1_4;
        } else {
            return 0;
        }
    }

    private void parseFile(com.google.gson.JsonElement p9, String p10, java.util.Date p11, String p12, String p13)
    {
        if ((p9 != 0) && ((!p9.isJsonNull()) && (p9.isJsonObject()))) {
            String v3 = com.bisimplex.firebooru.network.ParserKemono.optString(p9.getAsJsonObject(), "path", "");
            if ((!android.text.TextUtils.isEmpty(v3)) && (this.pathIsUnique(v3))) {
                int v9_3 = this.initPost(v3, p10, p11, p12, p13);
                if (v9_3 != 0) {
                    this.paths.add(v3);
                    this.data.add(v9_3);
                    return;
                } else {
                    this.removedCount = (this.removedCount + 1);
                    return;
                }
            }
        }
        return;
    }

    private boolean pathIsUnique(String p3)
    {
        java.util.Iterator v0_1 = this.paths.iterator();
        while (v0_1.hasNext()) {
            if (((String) v0_1.next()).equalsIgnoreCase(p3)) {
                return 0;
            }
        }
        return 1;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if ((p3 != null) && ((!p3.isJsonNull()) && (p3.isJsonObject()))) {
            java.util.Iterator v3_4 = p3.getAsJsonObject();
            if (v3_4.has("posts")) {
                java.util.Iterator v3_1 = v3_4.get("posts");
                if ((v3_1 != null) && (v3_1.isJsonArray())) {
                    java.util.Iterator v3_3 = v3_1.getAsJsonArray().iterator();
                    while (v3_3.hasNext()) {
                        com.google.gson.JsonObject v0_4 = ((com.google.gson.JsonElement) v3_3.next());
                        if (v0_4.isJsonObject()) {
                            this.parseElement(v0_4.getAsJsonObject());
                        }
                    }
                }
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p9)
    {
        String v1_0 = "";
        String v4 = com.bisimplex.firebooru.network.ParserKemono.optString(p9, "id", "");
        java.util.Date v5 = com.bisimplex.firebooru.network.ParserKemono.getDateIfExist(p9, "published", 0);
        void v2_0 = com.bisimplex.firebooru.network.ParserKemono.optString(p9, "user", "");
        com.google.gson.JsonElement v3_1 = com.bisimplex.firebooru.network.ParserKemono.optString(p9, "service", "");
        String v7 = com.bisimplex.firebooru.network.ParserKemono.optString(p9, "title", "");
        if ((!android.text.TextUtils.isEmpty(v2_0)) && ((!android.text.TextUtils.isEmpty(v4)) && (!android.text.TextUtils.isEmpty(v3_1)))) {
            v1_0 = this.getBaseUri().buildUpon().appendPath(v3_1).appendPath("user").appendPath(v2_0).appendPath("post").appendPath(v4).toString();
        }
        boolean v6_4 = v1_0;
        this.paths.clear();
        if (p9.has("file")) {
            this.parseFile(p9.get("file"), v4, v5, v6_4, v7);
        }
        if (p9.has("attachments")) {
            java.util.Iterator v9_1 = p9.get("attachments");
            if (v9_1.isJsonArray()) {
                java.util.Iterator v9_4 = v9_1.getAsJsonArray().asList().iterator();
                while (v9_4.hasNext()) {
                    this.parseFile(((com.google.gson.JsonElement) v9_4.next()), v4, v5, v6_4, v7);
                }
            }
        }
        return;
    }
}
