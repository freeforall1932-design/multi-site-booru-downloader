package com.bisimplex.firebooru.danbooru;
public class DanbooruJSONContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {

    public DanbooruJSONContentHandler(org.json.JSONArray p1)
    {
        this.ParseData(p1);
        return;
    }

    public DanbooruJSONContentHandler(org.json.JSONArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        this.setProvider(p2);
        this.ParseData(p1);
        return;
    }

    protected void ParseData(org.json.JSONArray p17)
    {
        if ((p17 != null) && (p17.length() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v2_1 = this.getProvider();
            int v3 = p17.length();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v5_1 = v2_1.getServerDescription().getUrl();
            int v6_23 = 0;
            int v7 = 0;
            while (v7 < v3) {
                try {
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v12_2;
                    boolean v8_3 = p17.getJSONObject(v7);
                    com.bisimplex.firebooru.danbooru.DanbooruPost v9_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                    v9_1.setHas_children(v8_3.optBoolean("has_children", v6_23));
                    v9_1.setMd5(v8_3.optString("md5", ""));
                    int v10_11 = v8_3.optBoolean("has_large", v6_23);
                    String v11_3 = v8_3.optString("file_ext", "");
                } catch (org.json.JSONException v0_1) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
                    break;
                }
                if (!v11_3.toLowerCase().endsWith("png")) {
                    v12_2 = v11_3;
                } else {
                    v12_2 = "jpg";
                }
                String v14_1 = v8_3.optString("file_url", "");
                if (v14_1.isEmpty()) {
                    v14_1 = String.format("%s/data/%s.%s", new Object[] {v5_1, v9_1.getMd5(), v11_3}));
                }
                String v11_5;
                if (v10_11 == 0) {
                    v11_5 = v14_1;
                } else {
                    v11_5 = v8_3.optString("large_file_url", "");
                    if (v11_5.isEmpty()) {
                        v11_5 = String.format("%s/data/sample/sample-%s.%s", new Object[] {v5_1, v9_1.getMd5(), v12_2}));
                    }
                }
                v9_1.setPostIdAndUrl(v8_3.optString("id", ""), v5_1, v2_1.getPostFormat());
                v9_1.setParent_id(v8_3.optString("parent_id", ""));
                com.bisimplex.firebooru.danbooru.DanbooruPostImage v12_9 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v14_1, v8_3.getInt("image_width"), v8_3.getInt("image_height"));
                v9_1.setFile(v12_9);
                v9_1.setJpeg(v12_9);
                if (v10_11 == 0) {
                    v9_1.setSample(v12_9);
                } else {
                    v9_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v11_5, 0, 0));
                }
                int v6_1 = v8_3.optString("preview_file_url", "");
                if (v6_1.isEmpty()) {
                    v6_1 = String.format("%s/ssd/data/preview/%s.%s", new Object[] {v5_1, v9_1.getMd5(), "jpg"}));
                }
                v9_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v6_1, 0, 0));
                v9_1.setRating(v8_3.optString("rating", ""));
                v9_1.setTags(v8_3.optString("tag_string", ""));
                v9_1.setSource(v8_3.optString("source", ""));
                v9_1.setScore(v8_3.optInt("score", 0));
                v9_1.setHas_notes((android.text.TextUtils.isEmpty(v8_3.optString("last_noted_at", "")) ^ 1));
                v9_1.setTag_artist(v8_3.optString("tag_string_artist", ""));
                v9_1.setTag_character(v8_3.optString("tag_string_character", ""));
                v9_1.setTag_copyright(v8_3.optString("tag_string_copyright", ""));
                v9_1.setTag_general(v8_3.optString("tag_string_general", ""));
                v2_1.fixUrls(v9_1);
                v9_1.setEnforceOriginalImage(v2_1.getEnforceOriginalImage());
                if (!v2_1.isBlacklisted(v9_1)) {
                    try {
                        this.data.add(v9_1);
                        v9_1.setFavorite(v4.getIsFavByPost(v9_1.getPostId(), v9_1.getMd5()));
                    } catch (org.json.JSONException v0_1) {
                    }
                }
                v7++;
                v6_23 = 0;
            }
        }
        return;
    }
}
