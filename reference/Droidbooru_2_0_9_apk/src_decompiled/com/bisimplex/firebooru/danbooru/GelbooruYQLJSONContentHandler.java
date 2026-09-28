package com.bisimplex.firebooru.danbooru;
public class GelbooruYQLJSONContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {

    public GelbooruYQLJSONContentHandler(org.json.JSONObject p1)
    {
        this.ParseData(p1);
        return;
    }

    public GelbooruYQLJSONContentHandler(org.json.JSONObject p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        this.setProvider(p2);
        this.ParseData(p1);
        return;
    }

    private void ParseData(org.json.JSONObject p17)
    {
        if ((p17 != null) && (p17.length() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v5_0 = this.getProvider();
            java.util.regex.Pattern v6_1 = java.util.regex.Pattern.compile("score:[0-9]+", 2);
            java.util.regex.Pattern v8_1 = java.util.regex.Pattern.compile("rating:[a-z]+", 2);
            com.bisimplex.firebooru.danbooru.DatabaseHelper v9 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v10_1 = v5_0.getServerDescription().getUrl();
            try {
                org.json.JSONException v0_20 = p17.getJSONObject("query");
            } catch (org.json.JSONException v0_19) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_19);
            }
            if (v0_20 != null) {
                if (v0_20.getInt("count") != 0) {
                    if (v0_20.has("results")) {
                        org.json.JSONException v0_21 = v0_20.getJSONObject("results");
                        if (v0_21.has("a")) {
                            org.json.JSONArray v2_1 = v0_21.getJSONArray("a");
                            int v3_5 = 0;
                            int v11_0 = 0;
                            while (v11_0 < v2_1.length()) {
                                org.json.JSONException v0_23 = v2_1.getJSONObject(v11_0);
                                com.bisimplex.firebooru.network.Utils v12_4 = v0_23.getJSONObject("img");
                                com.bisimplex.firebooru.danbooru.DanbooruPost v13_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                                v13_1.setPostIdAndUrl(v0_23.getString("id").substring(1), v10_1, v5_0.getPostFormat());
                                v13_1.setHas_children(v3_5);
                                v13_1.setMd5("");
                                v13_1.setParent_id("");
                                v13_1.setRating("");
                                v13_1.setTags(v12_4.getString("title"));
                                v13_1.setSource("");
                                v13_1.setScore(v3_5);
                                v13_1.setHas_notes(v3_5);
                                org.json.JSONException v0_29 = v12_4.getString("src");
                                v13_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v0_29, v3_5, v3_5));
                                com.bisimplex.firebooru.network.Utils v12_11 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v0_29.replaceAll("thumbs", "img").replaceAll("/thumbnails//", "//images/").replaceAll("thumbnail_", ""), v3_5, v3_5);
                                v13_1.setFile(v12_11);
                                v13_1.setSample(v12_11);
                                v13_1.setJpeg(v12_11);
                                org.json.JSONException v0_34 = v6_1.matcher(v13_1.getTags());
                                if (v0_34.find()) {
                                    com.bisimplex.firebooru.network.Utils v12_0 = v0_34.group();
                                    org.json.JSONException v0_1 = v12_0.split(":");
                                    if (v0_1.length == 2) {
                                        try {
                                            org.json.JSONException v0_3 = Integer.parseInt(v0_1[1]);
                                        } catch (org.json.JSONException v0_4) {
                                            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_4);
                                            v0_3 = 0;
                                        }
                                        v13_1.setScore(v0_3);
                                    }
                                    v13_1.setTags(v13_1.getTags().replace(v12_0, ""));
                                }
                                org.json.JSONException v0_8 = v8_1.matcher(v13_1.getTags());
                                if (v0_8.find()) {
                                    int v3_3 = v0_8.group();
                                    org.json.JSONException v0_9 = v3_3.split(":");
                                    if (v0_9.length == 2) {
                                        try {
                                            org.json.JSONException v0_11 = Integer.parseInt(v0_9[1]);
                                        } catch (org.json.JSONException v0_12) {
                                            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_12);
                                            v0_11 = 0;
                                        }
                                        v13_1.setScore(v0_11);
                                    }
                                    v13_1.setTags(v13_1.getTags().replace(v3_3, ""));
                                }
                                if (!v5_0.isBlacklisted(v13_1)) {
                                    try {
                                        this.data.add(v13_1);
                                        v13_1.setFavorite(v9.getIsFavByPost(v13_1.getPostId(), v13_1.getMd5()));
                                    } catch (org.json.JSONException v0_19) {
                                    }
                                }
                                v11_0++;
                                v3_5 = 0;
                            }
                        } else {
                        }
                    } else {
                    }
                } else {
                }
            } else {
            }
        }
        return;
    }
}
