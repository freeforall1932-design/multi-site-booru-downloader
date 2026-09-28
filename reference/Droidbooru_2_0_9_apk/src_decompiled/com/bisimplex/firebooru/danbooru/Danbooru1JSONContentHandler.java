package com.bisimplex.firebooru.danbooru;
public class Danbooru1JSONContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {

    public Danbooru1JSONContentHandler(org.json.JSONArray p1)
    {
        this.ParseData(p1);
        return;
    }

    public Danbooru1JSONContentHandler(org.json.JSONArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        this.setProvider(p2);
        this.ParseData(p1);
        return;
    }

    protected void ParseData(org.json.JSONArray p14)
    {
        if ((p14 != null) && (p14.length() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v1_1 = this.getProvider();
            int v2 = p14.length();
            String v3_1 = v1_1.getServerDescription().getUrl();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            int v6 = 0;
            while (v6 < v2) {
                try {
                    boolean v7_3 = p14.getJSONObject(v6);
                    com.bisimplex.firebooru.danbooru.DanbooruPost v8_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                    v8_1.setTags(v7_3.optString("tags", ""));
                    v8_1.setRating(v7_3.optString("rating", ""));
                } catch (org.json.JSONException v14_1) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v14_1);
                    break;
                }
                if (!v1_1.isBlacklisted(v8_1)) {
                    this.data.add(v8_1);
                    v8_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_3.optString("sample_url", ""), v7_3.optInt("sample_width", 0), v7_3.optInt("sample_height", 0)));
                    v8_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_3.optString("preview_url", ""), v7_3.optInt("preview_width", 0), v7_3.optInt("preview_height", 0)));
                    v1_1.fixDanbooruPreview(v8_1.getPreview());
                    v8_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_3.optString("jpeg_url", ""), v7_3.optInt("jpeg_width", 0), v7_3.optInt("jpeg_height", 0)));
                    v8_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_3.optString("file_url", ""), v7_3.optInt("width", 0), v7_3.optInt("height", 0)));
                    if (v8_1.getSample().getUrl().isEmpty()) {
                        v8_1.setSample(v8_1.getFile());
                    }
                    v8_1.setHas_children(v7_3.optBoolean("has_children", 0));
                    v8_1.setHas_comments(v7_3.optBoolean("has_comments", 0));
                    v8_1.setHas_notes(1);
                    v8_1.setMd5(v7_3.optString("md5", ""));
                    v8_1.setParent_id(v7_3.optString("parent_id", ""));
                    v8_1.setScore(v7_3.optInt("score", 0));
                    v8_1.setSource(v7_3.optString("source", ""));
                    v8_1.setEnforceOriginalImage(v1_1.getEnforceOriginalImage());
                    v8_1.setPostIdAndUrl(v7_3.optString("id", ""), v3_1, v1_1.getPostFormat());
                    v8_1.setImageIsVisible(1);
                    v8_1.setFavorite(v4.getIsFavByPost(v8_1.getPostId(), v8_1.getMd5()));
                    v1_1.fixUrls(v8_1);
                } else {
                }
                v6++;
            }
        }
        return;
    }
}
