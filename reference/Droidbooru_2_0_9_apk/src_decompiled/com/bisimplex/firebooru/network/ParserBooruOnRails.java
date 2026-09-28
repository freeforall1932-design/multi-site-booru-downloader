package com.bisimplex.firebooru.network;
public class ParserBooruOnRails extends com.bisimplex.firebooru.network.ParserPosts {

    public ParserBooruOnRails(com.bisimplex.firebooru.network.ParserParams p1)
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

    protected void parse(com.google.gson.JsonElement p2)
    {
        if ((p2 != null) && (p2.isJsonObject())) {
            com.google.gson.JsonElement v2_2 = p2.getAsJsonObject().get("posts");
            if (v2_2 != null) {
                super.parse(v2_2);
            }
        }
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p15)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        if (!com.bisimplex.firebooru.network.ParserBooruOnRails.optBoolean(p15, "hidden_from_users", 0)) {
            boolean v1_10;
            v0_1.setScore(com.bisimplex.firebooru.network.ParserBooruOnRails.optInt(p15, "score", 0));
            if (com.bisimplex.firebooru.network.ParserBooruOnRails.optInt(p15, "comment_count", 0) <= 0) {
                v1_10 = 0;
            } else {
                v1_10 = 1;
            }
            v0_1.setHas_comments(v1_10);
            v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserBooruOnRails.optString(p15, "id", ""), this.baseUrl, this.provider.getPostFormat());
            boolean v1_15 = p15.getAsJsonObject("representations");
            if (v1_15) {
                StringBuilder v6_3 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_15.get("full").getAsString()), p15.get("width").getAsInt(), p15.get("height").getAsInt());
                v0_1.setFile(v6_3);
                v0_1.setJpeg(v6_3);
                v0_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_15.get("large").getAsString()), 0, 0));
                StringBuilder v5_11 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v1_15.get("thumb").getAsString()), 0, 0);
                v0_1.setPreview(v5_11);
                if (v5_11.isWebM()) {
                    v5_11.setUrlExtension("gif");
                }
            }
            v0_1.setSource(this.fixURL(com.bisimplex.firebooru.network.ParserBooruOnRails.optString(p15, "source_url", "")));
            boolean v1_27 = p15.getAsJsonArray("tags");
            StringBuilder v5_13 = new StringBuilder("");
            StringBuilder v6_7 = new StringBuilder("");
            java.util.ArrayList v7_4 = new java.util.ArrayList(0);
            StringBuilder v8_4 = new StringBuilder("");
            com.google.gson.JsonArray v4_2 = p15.getAsJsonArray("tag_ids");
            if (v4_2.size() == v1_27.size()) {
                int v9_0 = 0;
                while (v9_0 < v1_27.size()) {
                    String v10_4 = v1_27.get(v9_0).getAsString().trim();
                    if (!v10_4.isEmpty()) {
                        boolean v11_2;
                        v8_4.append(v10_4);
                        v8_4.append(" ");
                        int v12_1 = v4_2.get(v9_0).getAsInt();
                        if (!v10_4.contains("artist:")) {
                            v6_7.append(v10_4);
                            v6_7.append(" ");
                            v11_2 = 0;
                        } else {
                            v5_13.append(v10_4);
                            v5_13.append(" ");
                            v11_2 = 1;
                        }
                        com.bisimplex.firebooru.danbooru.TagItem v13_3 = new com.bisimplex.firebooru.danbooru.TagItem();
                        v13_3.setIdx(v12_1);
                        v13_3.setName(v10_4);
                        v13_3.setType(v11_2);
                        v7_4.add(v13_3);
                    }
                    v9_0++;
                }
            }
            v0_1.setTag_artist(v5_13.toString().trim());
            v0_1.setTag_general(v6_7.toString().trim());
            v0_1.setHas_notes(0);
            v0_1.setSeparateTags(v7_4);
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
            v0_1.setTags(v8_4.toString().trim());
            v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
            if (this.shouldAddToResults(v0_1)) {
                this.data.add(v0_1);
            }
        }
        return;
    }
}
