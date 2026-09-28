package com.bisimplex.firebooru.parser;
public class IBSearchParser extends com.bisimplex.firebooru.parser.DanbooruParser {

    public IBSearchParser(com.google.gson.JsonArray p1)
    {
        super(p1);
        return;
    }

    public IBSearchParser(com.google.gson.JsonArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        super(p1, p2);
        return;
    }

    protected void ParseData(com.google.gson.JsonArray p17)
    {
        com.google.gson.JsonArray v0_0 = p17;
        if ((p17 != null) && (p17.size() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v3_1 = this.getProvider();
            int v4 = p17.size();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v5 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v6_1 = v3_1.getServerDescription().getUrl();
            java.net.URL v7 = com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v6_1);
            if (v7 != null) {
                int v9 = 0;
                while (v9 < v4) {
                    try {
                        com.google.gson.JsonObject v10_1 = v0_0.get(v9).getAsJsonObject();
                        com.bisimplex.firebooru.danbooru.DanbooruPost v11_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                        v11_1.setMd5(this.optString(v10_1, "md5", ""));
                        v11_1.setPostIdAndUrl(this.optString(v10_1, "id", ""), v6_1, v3_1.getPostFormat());
                        v11_1.setParent_id(this.optString(v10_1, "parent_id", ""));
                        int v12_7 = this.optString(v10_1, "path", "");
                        int v13_2 = this.optString(v10_1, "server", "");
                        String v8_3 = String.format("%s://%s.%s/%s", new Object[] {v7.getProtocol(), v13_2, v7.getHost(), v12_7}));
                        com.google.gson.JsonArray v0_7 = String.format("%s://%s.%s/t%s", new Object[] {v7.getProtocol(), v13_2, v7.getHost(), v12_7}));
                        int v12_9 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v8_3, v10_1.get("width").getAsInt(), v10_1.get("height").getAsInt());
                        v11_1.setFile(v12_9);
                        v11_1.setJpeg(v12_9);
                        v11_1.setSample(v12_9);
                        v11_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v0_7, 0, 0));
                        v11_1.setRating(this.optString(v10_1, "rating", ""));
                        v11_1.setTags(this.optString(v10_1, "tags", ""));
                        v11_1.setHas_notes(0);
                        v11_1.setEnforceOriginalImage(v3_1.getEnforceOriginalImage());
                    } catch (com.google.gson.JsonArray v0_4) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_4);
                        break;
                    }
                    if (!v3_1.isBlacklisted(v11_1)) {
                        this.data.add(v11_1);
                        v11_1.setFavorite(v5.getIsFavByPost(v11_1.getPostId(), v11_1.getMd5()));
                    }
                    v9++;
                    v0_0 = p17;
                }
            }
        }
        return;
    }
}
