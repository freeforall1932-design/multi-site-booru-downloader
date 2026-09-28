package com.bisimplex.firebooru.danbooru;
public class IBSearchJSONContentHandler extends com.bisimplex.firebooru.danbooru.DanbooruJSONContentHandler {

    public IBSearchJSONContentHandler(org.json.JSONArray p1)
    {
        super(p1);
        return;
    }

    public IBSearchJSONContentHandler(org.json.JSONArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        super(p1, p2);
        return;
    }

    protected void ParseData(org.json.JSONArray p17)
    {
        if ((p17 != null) && (p17.length() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v2_1 = this.getProvider();
            int v3 = p17.length();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v5_1 = v2_1.getServerDescription().getUrl();
            java.net.URL v6 = com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v5_1);
            if (v6 != null) {
                int v8 = 0;
                while (v8 < v3) {
                    try {
                        boolean v9_2 = p17.getJSONObject(v8);
                        com.bisimplex.firebooru.danbooru.DanbooruPost v10_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                        v10_1.setMd5(v9_2.optString("md5", ""));
                        v10_1.setPostIdAndUrl(v9_2.optString("id", ""), v5_1, v2_1.getPostFormat());
                        v10_1.setParent_id(v9_2.optString("parent_id", ""));
                        String v11_8 = v9_2.optString("path");
                        int v12_2 = v9_2.optString("server");
                        String v13_1 = String.format("%s://%s.%s/%s", new Object[] {v6.getProtocol(), v12_2, v6.getHost(), v11_8}));
                        com.bisimplex.firebooru.danbooru.IBSearchJSONContentHandler v7_2 = String.format("%s://%s.%s/t%s", new Object[] {v6.getProtocol(), v12_2, v6.getHost(), v11_8}));
                        String v11_10 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v13_1, v9_2.getInt("width"), v9_2.getInt("height"));
                        v10_1.setFile(v11_10);
                        v10_1.setJpeg(v11_10);
                        v10_1.setSample(v11_10);
                        v10_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_2, 0, 0));
                        v10_1.setRating(v9_2.optString("rating", ""));
                        v10_1.setTags(v9_2.optString("tags", ""));
                        v10_1.setHas_notes(0);
                        v10_1.setEnforceOriginalImage(v2_1.getEnforceOriginalImage());
                    } catch (org.json.JSONException v0_1) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
                        break;
                    }
                    if (!v2_1.isBlacklisted(v10_1)) {
                        try {
                            this.data.add(v10_1);
                            v10_1.setFavorite(v4.getIsFavByPost(v10_1.getPostId(), v10_1.getMd5()));
                        } catch (org.json.JSONException v0_1) {
                        }
                    }
                    v8++;
                }
            }
        }
        return;
    }
}
