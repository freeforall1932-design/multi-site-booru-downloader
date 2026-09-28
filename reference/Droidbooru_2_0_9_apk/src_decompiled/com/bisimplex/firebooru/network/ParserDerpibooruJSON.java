package com.bisimplex.firebooru.network;
public class ParserDerpibooruJSON extends com.bisimplex.firebooru.network.ParserPosts {
    public static final String FAVED_INTERACTION_TYPE = "faved";

    public ParserDerpibooruJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    private String fixURL(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            if (!p2.startsWith("//")) {
                if (p2.startsWith("/")) {
                    p2 = String.format("%s%s", new Object[] {this.baseUrl, p2}));
                }
            } else {
                return String.format("https:%s", new Object[] {p2}));
            }
        }
        return p2;
    }

    protected void parse(com.google.gson.JsonElement p5)
    {
        if ((p5 != null) && (p5.isJsonObject())) {
            java.util.Iterator v5_8 = p5.getAsJsonObject();
            String v0_10 = v5_8.get("images");
            if (v0_10 != null) {
                super.parse(v0_10);
            }
            java.util.Iterator v5_1 = v5_8.get("interactions");
            if ((v5_1 != null) && ((!v5_1.isJsonNull()) && (v5_1.isJsonArray()))) {
                String v0_5 = new java.util.ArrayList();
                java.util.Iterator v5_3 = v5_1.getAsJsonArray().iterator();
                while (v5_3.hasNext()) {
                    java.util.Iterator v1_4 = ((com.google.gson.JsonElement) v5_3.next());
                    if (!v1_4.isJsonNull()) {
                        java.util.Iterator v1_5 = v1_4.getAsJsonObject();
                        if ("faved".equalsIgnoreCase(v1_5.get("interaction_type").getAsString())) {
                            v0_5.add(v1_5.get("image_id").getAsString());
                        }
                    }
                }
                if ((v0_5.size() > 0) && (this.data.size() > 0)) {
                    java.util.Iterator v5_7 = v0_5.iterator();
                    while (v5_7.hasNext()) {
                        String v0_8 = ((String) v5_7.next());
                        java.util.Iterator v1_2 = this.data.iterator();
                        while (v1_2.hasNext()) {
                            com.bisimplex.firebooru.danbooru.DanbooruPost v2_2 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v1_2.next());
                            if (v0_8.equalsIgnoreCase(v2_2.getPostId())) {
                                v2_2.setFavorite(1);
                            }
                        }
                    }
                }
            }
        }
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p15)
    {
        boolean v1_3;
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setScore(com.bisimplex.firebooru.network.ParserDerpibooruJSON.optInt(p15, "score", 0));
        if (com.bisimplex.firebooru.network.ParserDerpibooruJSON.optInt(p15, "comment_count", 0) <= 0) {
            v1_3 = 0;
        } else {
            v1_3 = 1;
        }
        v0_1.setHas_comments(v1_3);
        v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserDerpibooruJSON.optString(p15, "id", ""), this.baseUrl, this.provider.getPostFormat());
        boolean v1_13 = p15.getAsJsonObject("representations");
        if (v1_13) {
            java.util.ArrayList v6_3 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_13.get("full").getAsString()), p15.get("width").getAsInt(), p15.get("height").getAsInt());
            v0_1.setFile(v6_3);
            v0_1.setJpeg(v6_3);
            v0_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_13.get("large").getAsString()), 0, 0));
            StringBuilder v5_11 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_13.get("thumb").getAsString()), 0, 0);
            v0_1.setPreview(v5_11);
            if (v5_11.isWebM()) {
                v5_11.setUrlExtension("gif");
            }
        }
        v0_1.setSource(this.fixURL(com.bisimplex.firebooru.network.ParserDerpibooruJSON.optString(p15, "source_url", "")));
        v0_1.setCreated_at(com.bisimplex.firebooru.network.ParserDerpibooruJSON.getDateIfExist(p15, "created_at", 0));
        v0_1.setAuthor(com.bisimplex.firebooru.network.ParserDerpibooruJSON.optString(p15, "uploader", ""));
        boolean v1_30 = p15.getAsJsonArray("tags");
        StringBuilder v4_2 = new StringBuilder();
        StringBuilder v5_14 = new StringBuilder();
        java.util.ArrayList v6_7 = new java.util.ArrayList(0);
        StringBuilder v7_4 = new StringBuilder();
        com.google.gson.JsonArray v8_1 = p15.getAsJsonArray("tag_ids");
        if (v8_1.size() == v1_30.size()) {
            int v9_1 = 0;
            while (v9_1 < v1_30.size()) {
                String v10_4 = v1_30.get(v9_1).getAsString().trim();
                if (!v10_4.isEmpty()) {
                    boolean v11_2;
                    v7_4.append(v10_4);
                    v7_4.append(" ");
                    int v12_1 = v8_1.get(v9_1).getAsInt();
                    if (!v10_4.contains("artist:")) {
                        v5_14.append(v10_4);
                        v5_14.append(" ");
                        v11_2 = 0;
                    } else {
                        v4_2.append(v10_4);
                        v4_2.append(" ");
                        v11_2 = 1;
                    }
                    com.bisimplex.firebooru.danbooru.TagItem v13_3 = new com.bisimplex.firebooru.danbooru.TagItem();
                    v13_3.setIdx(v12_1);
                    v13_3.setName(v10_4);
                    v13_3.setType(v11_2);
                    v6_7.add(v13_3);
                }
                v9_1++;
            }
        }
        v0_1.setTag_artist(v4_2.toString().trim());
        v0_1.setTag_general(v5_14.toString().trim());
        v0_1.setHas_notes(0);
        v0_1.setSeparateTags(v6_7);
        boolean v1_6 = p15.get("orig_sha512_hash");
        if (!v1_6.isJsonNull()) {
            v0_1.setMd5(v1_6.getAsString());
        } else {
            java.util.List v15_2 = p15.get("sha512_hash");
            if (!v15_2.isJsonNull()) {
                v0_1.setMd5(v15_2.getAsString());
            } else {
                v0_1.setMd5(com.bisimplex.firebooru.network.Utils.md5(v0_1.getPostUrl()));
            }
        }
        v0_1.setDisableStorage(1);
        v0_1.setTags(v7_4.toString().trim());
        v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
        if (this.shouldAddToResults(v0_1)) {
            this.data.add(v0_1);
        }
        return;
    }

    protected void parseFinished()
    {
        return;
    }
}
