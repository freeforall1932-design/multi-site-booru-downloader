package com.bisimplex.firebooru.network;
public class ParserE621JSON extends com.bisimplex.firebooru.network.ParserPosts {
    private boolean allowFavSync;
    private com.bisimplex.firebooru.danbooru.DatabaseHelper helper;

    public ParserE621JSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        super.helper = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        super.allowFavSync = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isFavSyncDanbooru2();
        return;
    }

    private String concatStringJSONArray(com.google.gson.JsonArray p5)
    {
        StringBuilder v0_1 = new StringBuilder();
        if ((p5 != null) && ((!p5.isJsonNull()) && (p5.size() > 0))) {
            int v1_0 = p5.size();
            int v2 = 0;
            while (v2 < v1_0) {
                v0_1.append(p5.get(v2).getAsString());
                v0_1.append(" ");
                v2++;
            }
        }
        return v0_1.toString().trim();
    }

    private String concatStringJSONArray(com.google.gson.JsonObject p2, String p3)
    {
        if ((p2 != null) && ((!android.text.TextUtils.isEmpty(p3)) && (p2.has(p3)))) {
            String v2_4 = p2.get(p3);
            if ((v2_4 != null) && (v2_4.isJsonArray())) {
                return this.concatStringJSONArray(v2_4.getAsJsonArray());
            }
        }
        return "";
    }

    private String md5FromFile(com.google.gson.JsonObject p3, String p4)
    {
        if ((p3 != null) && ((!android.text.TextUtils.isEmpty(p4)) && (p3.has(p4)))) {
            String v3_4 = p3.get(p4);
            if (!v3_4.isJsonNull()) {
                if (v3_4.isJsonObject()) {
                    return v3_4.getAsJsonObject().get("md5").getAsString();
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    private com.bisimplex.firebooru.danbooru.DanbooruPostImage parseFile(com.google.gson.JsonObject p4, String p5)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_0 = 0;
        if ((p4 != 0) && ((!android.text.TextUtils.isEmpty(p5)) && (p4.has(p5)))) {
            int v4_4 = p4.get(p5);
            if (!v4_4.isJsonNull()) {
                if (v4_4.isJsonObject()) {
                    int v4_1 = v4_4.getAsJsonObject();
                    if (v4_1.has("has")) {
                        String v5_4 = v4_1.get("has");
                        if ((v5_4.isJsonNull()) || (!v5_4.getAsBoolean())) {
                            return 0;
                        }
                    }
                    String v5_6 = "url";
                    if (!v4_1.has("url")) {
                        v5_6 = "webp";
                        if (!v4_1.has("webp")) {
                            v5_6 = "jpg";
                            if (!v4_1.has("jpg")) {
                                v5_6 = 0;
                            }
                        }
                    }
                    if (!android.text.TextUtils.isEmpty(v5_6)) {
                        String v5_7 = v4_1.get(v5_6);
                        if (!v5_7.isJsonNull()) {
                            v0_0 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v5_7.getAsString(), v4_1.get("width").getAsInt(), v4_1.get("height").getAsInt());
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        }
        return v0_0;
    }

    protected void checkPostsFavorited()
    {
        java.util.Iterator v0_1 = this.data.iterator();
        while (v0_1.hasNext()) {
            int v4_1;
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_2 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_1.next());
            com.bisimplex.firebooru.danbooru.DatabaseHelper v2_0 = v1_2.isFavorite();
            boolean v3_1 = this.helper.getIsFavByPost(v1_2.getPostId(), v1_2.getMd5());
            if ((v2_0 == null) && (!v3_1)) {
                v4_1 = 0;
            } else {
                v4_1 = 1;
            }
            v1_2.setFavorite(v4_1);
            if (v2_0 != null) {
                if (!v3_1) {
                    this.helper.addFavoriteItem(v1_2);
                } else {
                    this.helper.updateFavoriteItem(v1_2);
                }
            }
        }
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if (p3 != null) {
            if (!p3.isJsonObject()) {
                if (p3.isJsonArray()) {
                    super.parse(p3.getAsJsonArray());
                    return;
                }
            } else {
                com.google.gson.JsonArray v3_1 = p3.getAsJsonObject();
                if (v3_1.has("posts")) {
                    super.parse(v3_1.get("posts").getAsJsonArray());
                    return;
                }
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p12)
    {
        if ((!p12.has("flags")) || (!p12.get("flags").getAsJsonObject().get("deleted").getAsBoolean())) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v0_4 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            StringBuilder v1_6 = com.bisimplex.firebooru.network.ParserE621JSON.optString(p12, "id", "");
            com.google.gson.JsonObject v3 = 0;
            v0_4.setParent_id(0);
            if (p12.has("relationships")) {
                StringBuilder v4_4 = p12.get("relationships");
                if ((!v4_4.isJsonNull()) && (v4_4.isJsonObject())) {
                    StringBuilder v4_7 = v4_4.getAsJsonObject();
                    if (!v4_7.get("parent_id").isJsonNull()) {
                        v0_4.setParent_id(v4_7.get("parent_id").getAsString());
                    }
                    v0_4.setHas_children(com.bisimplex.firebooru.network.ParserE621JSON.optBoolean(v4_7, "has_children", 0));
                }
            }
            v0_4.setHas_notes(0);
            if (p12.has("has")) {
                StringBuilder v4_10 = p12.get("has");
                if ((!v4_10.isJsonNull()) && (v4_10.isJsonObject())) {
                    StringBuilder v4_11 = v4_10.getAsJsonObject();
                    v0_4.setHas_children(com.bisimplex.firebooru.network.ParserE621JSON.optBoolean(v4_11, "active_children", 0));
                    v0_4.setHas_notes(com.bisimplex.firebooru.network.ParserE621JSON.optBoolean(v4_11, "notes", 0));
                }
            }
            v0_4.setRating(com.bisimplex.firebooru.network.ParserE621JSON.optString(p12, "rating", ""));
            v0_4.setPostIdAndUrl(v1_6, this.baseUrl, this.provider.getPostFormat());
            v0_4.setCreated_at(com.bisimplex.firebooru.network.ParserE621JSON.getDateIfExist(p12, "created_at", 0));
            v0_4.setAuthor(com.bisimplex.firebooru.network.ParserE621JSON.optString(p12, "uploader_name", ""));
            if (android.text.TextUtils.isEmpty(v0_4.getAuthor())) {
                v0_4.setAuthor(com.bisimplex.firebooru.network.ParserE621JSON.optString(p12, "uploader_id", ""));
            }
            StringBuilder v1_2;
            if (!p12.has("files")) {
                v1_2 = p12;
            } else {
                StringBuilder v1_1 = p12.get("files");
                if (!v1_1.isJsonObject()) {
                } else {
                    v1_2 = v1_1.getAsJsonObject();
                }
            }
            int v7_0 = "file";
            if (!v1_2.has("meta")) {
                if (v1_2.has("file")) {
                    String v5_2 = v1_2.get("file");
                    if (v5_2.isJsonObject()) {
                        v3 = v5_2.getAsJsonObject();
                    }
                }
            } else {
                String v5_3 = v1_2.get("meta");
                if (v5_3.isJsonObject()) {
                    v3 = v5_3.getAsJsonObject();
                }
            }
            if ((v3 != null) && (v3.isJsonObject())) {
                v0_4.setMd5(com.bisimplex.firebooru.network.ParserE621JSON.optString(v3, "md5", ""));
            }
            if (android.text.TextUtils.isEmpty(v0_4.getMd5())) {
                v0_4.setMd5(com.bisimplex.firebooru.network.Utils.md5(v0_4.getPostUrl()));
            }
            if (v1_2.has("original")) {
                v7_0 = "original";
            }
            boolean v2_6 = this.parseFile(v1_2, v7_0);
            if ((v2_6) && (!v2_6.getExtension().equalsIgnoreCase("swf"))) {
                v0_4.setFile(v2_6);
                v0_4.setJpeg(v2_6);
                String v5_10 = this.parseFile(v1_2, "sample");
                if (v5_10 == null) {
                    v5_10 = v2_6;
                }
                v0_4.setSample(v5_10);
                if ((v5_10 != v2_6) && ((v2_6.isAnimated()) && (!v5_10.isAnimated()))) {
                    v0_4.setSample(v0_4.getFile());
                }
                v0_4.setPreview(this.parseFile(v1_2, "preview"));
                String v5_11 = 1;
                if (p12.has("sources")) {
                    StringBuilder v1_7 = p12.get("sources");
                    if ((!v1_7.isJsonNull()) && (v1_7.isJsonArray())) {
                        StringBuilder v1_9 = v1_7.getAsJsonArray();
                        if (!v1_9.isEmpty()) {
                            boolean v2_17 = new StringBuilder();
                            int v7_2 = 0;
                            while (v7_2 < v1_9.size()) {
                                String v8_7 = v1_9.get(v7_2).getAsString();
                                if (!android.text.TextUtils.isEmpty(v8_7)) {
                                    v2_17.append(v8_7);
                                    if (v7_2 < (v1_9.size() - 1)) {
                                        v2_17.append(" | ");
                                    }
                                }
                                v7_2++;
                            }
                            v0_4.setSource(v2_17.toString());
                        }
                    }
                }
                StringBuilder v1_13;
                if (!p12.has("stats")) {
                    v1_13 = p12;
                } else {
                    StringBuilder v1_12 = p12.get("stats");
                    if (!v1_12.isJsonObject()) {
                    } else {
                        v1_13 = v1_12.getAsJsonObject();
                    }
                }
                if (v1_13.has("score")) {
                    v0_4.setScore(com.bisimplex.firebooru.network.ParserE621JSON.optInt(v1_13.get("score").getAsJsonObject(), "total", 0));
                }
                v0_4.setFavorite(com.bisimplex.firebooru.network.ParserE621JSON.optBoolean(v1_13, "is_favorited", 0));
                if (com.bisimplex.firebooru.network.ParserE621JSON.optInt(v1_13, "comment_count", 0) <= 0) {
                    v5_11 = 0;
                }
                v0_4.setHas_comments(v5_11);
                java.util.List v12_2 = p12.get("tags").getAsJsonObject();
                StringBuilder v1_17 = this.concatStringJSONArray(v12_2, "general");
                boolean v2_28 = this.concatStringJSONArray(v12_2, "species");
                String v5_14 = this.concatStringJSONArray(v12_2, "character");
                int v7_6 = this.concatStringJSONArray(v12_2, "copyright");
                String v8_4 = this.concatStringJSONArray(v12_2, "artist");
                String v9_1 = this.concatStringJSONArray(v12_2, "invalid");
                String v10_1 = this.concatStringJSONArray(v12_2, "lore");
                java.util.List v12_3 = this.concatStringJSONArray(v12_2, "meta");
                v0_4.setTag_artist(v8_4);
                v0_4.setTag_character(v5_14);
                v0_4.setTag_copyright(v7_6);
                StringBuilder v4_6 = new StringBuilder(v1_17);
                if (!android.text.TextUtils.isEmpty(v2_28)) {
                    v4_6.insert(0, " ");
                    v4_6.insert(0, v2_28);
                }
                if (!android.text.TextUtils.isEmpty(v10_1)) {
                    v4_6.insert(0, " ");
                    v4_6.insert(0, v10_1);
                }
                if (!android.text.TextUtils.isEmpty(v9_1)) {
                    v4_6.insert(0, " ");
                    v4_6.insert(0, v9_1);
                }
                if (!android.text.TextUtils.isEmpty(v12_3)) {
                    v0_4.setTag_meta(v12_3);
                }
                v0_4.setTag_general(v4_6.toString().trim());
                StringBuilder v1_25 = new StringBuilder(v0_4.getTag_general());
                if (!android.text.TextUtils.isEmpty(v0_4.getTag_artist())) {
                    v1_25.append(" ");
                    v1_25.append(v0_4.getTag_artist());
                }
                if (!android.text.TextUtils.isEmpty(v5_14)) {
                    v1_25.append(" ");
                    v1_25.append(v5_14);
                }
                if (!android.text.TextUtils.isEmpty(v7_6)) {
                    v1_25.append(" ");
                    v1_25.append(v7_6);
                }
                if (!android.text.TextUtils.isEmpty(v12_3)) {
                    v1_25.append(" ");
                    v1_25.append(v3);
                }
                v0_4.setTags(v1_25.toString().trim());
                v0_4.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
                if (this.shouldAddToResults(v0_4)) {
                    this.data.add(v0_4);
                    v0_4.calculateTags();
                }
            }
        }
        return;
    }
}
