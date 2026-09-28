package com.bisimplex.firebooru.network;
public class ParserDanbooru1JSON extends com.bisimplex.firebooru.network.ParserPosts {

    public ParserDanbooru1JSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p8)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setTags(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "tags", ""));
        v0_1.setRating(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "rating", ""));
        if ((!this.provider.isBlacklisted(v0_1)) || (this.isIcludeBlacklisted())) {
            v0_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "sample_url", ""), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "sample_width", 0), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "sample_height", 0)));
            v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "preview_url", ""), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "preview_width", 0), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "preview_height", 0)));
            this.provider.fixDanbooruPreview(v0_1.getPreview());
            v0_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "jpeg_url", ""), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "jpeg_width", 0), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "jpeg_height", 0)));
            v0_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "file_url", ""), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "width", 0), com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "height", 0)));
            if (v0_1.getSample().getUrl().isEmpty()) {
                v0_1.setSample(v0_1.getFile());
            }
            if ((v0_1.getFile().isAnimated()) && (!v0_1.getSample().isAnimated())) {
                v0_1.setSample(v0_1.getFile());
            }
            if ((v0_1.getSample().getUrl().equalsIgnoreCase(v0_1.getFile().getUrl())) && ((v0_1.getSample().getWidth() == v0_1.getFile().getWidth()) && (v0_1.getSample().getHeight() == v0_1.getFile().getHeight()))) {
                v0_1.setSample(v0_1.getFile());
            }
            v0_1.setHas_children(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optBoolean(p8, "has_children", 0));
            v0_1.setHas_comments(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optBoolean(p8, "has_comments", 0));
            v0_1.setHas_notes(1);
            v0_1.setMd5(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "md5", ""));
            v0_1.setParent_id(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "parent_id", ""));
            v0_1.setAuthor(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "author", ""));
            v0_1.setScore(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optInt(p8, "score", 0));
            v0_1.setSource(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "source", ""));
            v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
            v0_1.setCreated_at(com.bisimplex.firebooru.network.ParserDanbooru1JSON.getDateIfExist(p8, "created_at", 0));
            v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserDanbooru1JSON.optString(p8, "id", ""), this.baseUrl, this.provider.getPostFormat());
            v0_1.setImageIsVisible(1);
            this.provider.fixUrls(v0_1);
            this.data.add(v0_1);
            return;
        } else {
            return;
        }
    }
}
