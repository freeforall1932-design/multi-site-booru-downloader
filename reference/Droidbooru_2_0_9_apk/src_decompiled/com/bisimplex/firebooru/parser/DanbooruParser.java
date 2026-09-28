package com.bisimplex.firebooru.parser;
public class DanbooruParser extends com.bisimplex.firebooru.parser.Parser {

    public DanbooruParser(com.google.gson.JsonArray p1)
    {
        this.ParseData(p1);
        return;
    }

    public DanbooruParser(com.google.gson.JsonArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        this.setProvider(p2);
        this.ParseData(p1);
        return;
    }

    protected void ParseData(com.google.gson.JsonArray p14)
    {
        if ((p14 != null) && (p14.size() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v1_1 = this.getProvider();
            int v2 = p14.size();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v4_1 = v1_1.getServerDescription().getUrl();
            int v6 = 0;
            while (v6 < v2) {
                try {
                    java.util.ArrayList v7_5 = p14.get(v6).getAsJsonObject();
                    com.bisimplex.firebooru.danbooru.DanbooruPost v8_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                    v8_1.setTags(this.optString(v7_5, "tags", ""));
                    v8_1.setRating(this.optString(v7_5, "rating", ""));
                } catch (Exception v14_1) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v14_1);
                    break;
                }
                if (!v1_1.isBlacklisted(v8_1)) {
                    v8_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.optString(v7_5, "sample_url", ""), this.optInt(v7_5, "sample_width", 0), this.optInt(v7_5, "sample_height", 0)));
                    v8_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.optString(v7_5, "preview_url", ""), this.optInt(v7_5, "preview_width", 0), this.optInt(v7_5, "preview_height", 0)));
                    v1_1.fixDanbooruPreview(v8_1.getPreview());
                    v8_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.optString(v7_5, "jpeg_url", ""), this.optInt(v7_5, "jpeg_width", 0), this.optInt(v7_5, "jpeg_height", 0)));
                    v8_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.optString(v7_5, "file_url", ""), this.optInt(v7_5, "width", 0), this.optInt(v7_5, "height", 0)));
                    if (v8_1.getSample().getUrl().isEmpty()) {
                        v8_1.setSample(v8_1.getFile());
                    }
                    if ((v8_1.getSample().getUrl().equalsIgnoreCase(v8_1.getFile().getUrl())) && ((v8_1.getSample().getWidth() == v8_1.getFile().getWidth()) && (v8_1.getSample().getHeight() == v8_1.getFile().getHeight()))) {
                        v8_1.setSample(v8_1.getFile());
                    }
                    v8_1.setHas_children(this.optBoolean(v7_5, "has_children", 0));
                    v8_1.setHas_comments(this.optBoolean(v7_5, "has_comments", 0));
                    v8_1.setHas_notes(1);
                    v8_1.setMd5(this.optString(v7_5, "md5", ""));
                    v8_1.setParent_id(this.optString(v7_5, "parent_id", ""));
                    v8_1.setScore(this.optInt(v7_5, "score", 0));
                    v8_1.setSource(this.optString(v7_5, "source", ""));
                    v8_1.setEnforceOriginalImage(v1_1.getEnforceOriginalImage());
                    v8_1.setPostIdAndUrl(this.optString(v7_5, "id", ""), v4_1, v1_1.getPostFormat());
                    v8_1.setImageIsVisible(1);
                    v8_1.setFavorite(v3.getIsFavByPost(v8_1.getPostId(), v8_1.getMd5()));
                    v1_1.fixUrls(v8_1);
                    this.data.add(v8_1);
                } else {
                }
                v6++;
            }
        }
        return;
    }
}
