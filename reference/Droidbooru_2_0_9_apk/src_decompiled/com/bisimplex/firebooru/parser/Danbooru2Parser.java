package com.bisimplex.firebooru.parser;
public class Danbooru2Parser extends com.bisimplex.firebooru.parser.DanbooruParser {

    public Danbooru2Parser(com.google.gson.JsonArray p1)
    {
        super(p1);
        return;
    }

    public Danbooru2Parser(com.google.gson.JsonArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        super(p1, p2);
        return;
    }

    protected void ParseData(com.google.gson.JsonArray p19)
    {
        com.google.gson.JsonArray v0_0 = p19;
        if ((p19 != null) && (p19.size() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v3_1 = this.getProvider();
            boolean v4_0 = p19.size();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v5 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v6_1 = v3_1.getServerDescription().getUrl();
            boolean v7_8 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isFavSyncDanbooru2();
            int v8_2 = 0;
            int v9 = 0;
            while (v9 < v4_0) {
                try {
                    int v14_2;
                    String v10_2 = v0_0.get(v9).getAsJsonObject();
                    com.bisimplex.firebooru.danbooru.DanbooruPost v11_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                    v11_1.setHas_children(this.optBoolean(v10_2, "has_children", v8_2));
                    v11_1.setMd5(this.optString(v10_2, "md5", ""));
                    boolean v12_5 = this.optBoolean(v10_2, "has_large", v8_2);
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v13_2 = this.optString(v10_2, "file_ext", "");
                } catch (com.google.gson.JsonArray v0_35) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_35);
                    break;
                }
                if (!v13_2.toLowerCase().endsWith("png")) {
                    v14_2 = v13_2;
                } else {
                    v14_2 = "jpg";
                }
                int v8_8 = this.optString(v10_2, "file_url", "");
                if (v8_8.isEmpty()) {
                    v8_8 = String.format("%s/data/%s.%s", new Object[] {v6_1, v11_1.getMd5(), v13_2}));
                }
                com.google.gson.JsonArray v0_38;
                if (!v12_5) {
                    v0_38 = v8_8;
                } else {
                    v0_38 = this.optString(v10_2, "large_file_url", "");
                    if (v0_38.isEmpty()) {
                        v0_38 = String.format("%s/data/sample/sample-%s.%s", new Object[] {v6_1, v11_1.getMd5(), v14_2}));
                    }
                }
                v11_1.setPostIdAndUrl(this.optString(v10_2, "id", ""), v6_1, v3_1.getPostFormat());
                v11_1.setParent_id(this.optString(v10_2, "parent_id", ""));
                int v16_1 = v4_0;
                boolean v17 = v7_8;
                com.bisimplex.firebooru.danbooru.DanbooruPostImage v13_0 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v8_8, this.optInt(v10_2, "image_width", 0), this.optInt(v10_2, "image_height", 0));
                v11_1.setFile(v13_0);
                v11_1.setJpeg(v13_0);
                if (!v12_5) {
                    v11_1.setSample(v13_0);
                } else {
                    boolean v7_2 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v0_38, 0, 0);
                    v11_1.setSample(v7_2);
                    if (("zip".equalsIgnoreCase(v13_0.getExtension())) && (v7_2.isVideo())) {
                        v11_1.setFile(v11_1.getSample());
                    }
                }
                com.google.gson.JsonArray v0_6 = this.optString(v10_2, "preview_file_url", "");
                if (v0_6.isEmpty()) {
                    v0_6 = String.format("%s/ssd/data/preview/%s.%s", new Object[] {v6_1, v11_1.getMd5(), "jpg"}));
                }
                com.google.gson.JsonArray v0_18;
                v11_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v0_6, 0, 0));
                v11_1.setRating(this.optString(v10_2, "rating", ""));
                v11_1.setTags(this.optString(v10_2, "tag_string", ""));
                v11_1.setSource(this.optString(v10_2, "source", ""));
                v11_1.setScore(this.optInt(v10_2, "score", 0));
                boolean v4_9 = 1;
                if (this.optString(v10_2, "last_noted_at", 0) == null) {
                    v0_18 = 0;
                } else {
                    v0_18 = 1;
                }
                v11_1.setHas_notes(v0_18);
                v11_1.setTag_artist(this.optString(v10_2, "tag_string_artist", ""));
                v11_1.setTag_character(this.optString(v10_2, "tag_string_character", ""));
                v11_1.setTag_copyright(this.optString(v10_2, "tag_string_copyright", ""));
                v11_1.setTag_general(this.optString(v10_2, "tag_string_general", ""));
                com.google.gson.JsonArray v0_28 = this.optString(v10_2, "tag_string_meta", "");
                if (!android.text.TextUtils.isEmpty(v0_28)) {
                    v11_1.setTag_general(String.format("%s %s", new Object[] {v11_1.getTag_general(), v0_28})).trim());
                }
                boolean v7_7;
                com.google.gson.JsonArray v0_34;
                if (!v17) {
                    v7_7 = 0;
                    v0_34 = v7_7;
                } else {
                    v7_7 = 0;
                    if (!this.optBoolean(v10_2, "is_favorited", 0)) {
                    } else {
                        v0_34 = 1;
                    }
                }
                v3_1.fixUrls(v11_1);
                v11_1.setEnforceOriginalImage(v3_1.getEnforceOriginalImage());
                if (!v3_1.isBlacklisted(v11_1)) {
                    this.data.add(v11_1);
                    int v8_6 = v5.getIsFavByPost(v11_1.getPostId(), v11_1.getMd5());
                    if (v0_34 == null) {
                        if (v8_6 == 0) {
                            v4_9 = v7_7;
                        } else {
                        }
                    }
                    v11_1.setFavorite(v4_9);
                    if (v0_34 != null) {
                        if (v8_6 == 0) {
                            v5.addFavoriteItem(v11_1);
                        } else {
                            v5.updateFavoriteItem(v11_1);
                        }
                    }
                }
                v9++;
                v0_0 = p19;
                v8_2 = v7_7;
                v4_0 = v16_1;
                v7_8 = v17;
            }
        }
        return;
    }
}
