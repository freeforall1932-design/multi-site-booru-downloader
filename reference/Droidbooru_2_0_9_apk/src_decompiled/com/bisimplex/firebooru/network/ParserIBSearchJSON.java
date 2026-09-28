package com.bisimplex.firebooru.network;
public class ParserIBSearchJSON extends com.bisimplex.firebooru.network.ParserPosts {
    private java.net.URL url;

    public ParserIBSearchJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        super.url = com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(super.baseUrl);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p8)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setMd5(com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "md5", ""));
        v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "id", ""), this.baseUrl, this.provider.getPostFormat());
        v0_1.setParent_id(com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "parent_id", ""));
        String v1_4 = com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "path", "");
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_2 = com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "server", "");
        int v4_5 = String.format("%s://%s.%s/%s", new Object[] {this.url.getProtocol(), v3_2, this.url.getHost(), v1_4}));
        String v1_6 = String.format("%s://%s.%s/t%s", new Object[] {this.url.getProtocol(), v3_2, this.url.getHost(), v1_4}));
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_5 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v4_5, p8.get("width").getAsInt(), p8.get("height").getAsInt());
        v0_1.setFile(v3_5);
        v0_1.setJpeg(v3_5);
        v0_1.setSample(v3_5);
        v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v1_6, 0, 0));
        v0_1.setRating(com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "rating", ""));
        v0_1.setTags(com.bisimplex.firebooru.network.ParserIBSearchJSON.optString(p8, "tags", ""));
        v0_1.setHas_notes(0);
        v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
        if (this.shouldAddToResults(v0_1)) {
            this.data.add(v0_1);
        }
        return;
    }
}
