package com.bisimplex.firebooru.parser;
public class DerpibooruParser extends com.bisimplex.firebooru.parser.Parser {

    public DerpibooruParser(com.google.gson.JsonArray p1, com.bisimplex.firebooru.danbooru.BooruProvider p2)
    {
        this.setProvider(p2);
        this.ParseData(p1);
        return;
    }

    private String fixURL(String p2)
    {
        if ((!android.text.TextUtils.isEmpty(p2)) && (p2.startsWith("//"))) {
            p2 = String.format("https:%s", new Object[] {p2}));
        }
        return p2;
    }

    protected void ParseData(com.google.gson.JsonArray p24)
    {
        com.google.gson.JsonArray v0_0 = p24;
        if ((p24 != null) && (p24.size() != 0)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v4_1 = this.getProvider();
            int v5_0 = p24.size();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v6 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
            String v7_3 = v4_1.getServerDescription().getUrl();
            if (com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v7_3) != null) {
                int v8_4 = 0;
                com.bisimplex.firebooru.danbooru.TagItem v9_0 = 0;
                while (v9_0 < v5_0) {
                    try {
                        String[] v12_5;
                        com.google.gson.JsonObject v10_1 = v0_0.get(v9_0).getAsJsonObject();
                        com.bisimplex.firebooru.danbooru.DanbooruPost v11_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                        v11_1.setScore(this.optInt(v10_1, "score", v8_4));
                    } catch (com.google.gson.JsonArray v0_19) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_19);
                        break;
                    }
                    if (this.optInt(v10_1, "comment_count", v8_4) <= 0) {
                        v12_5 = v8_4;
                    } else {
                        v12_5 = 1;
                    }
                    int v16;
                    v11_1.setHas_comments(v12_5);
                    v11_1.setPostIdAndUrl(this.optString(v10_1, "id", ""), v7_3, v4_1.getPostFormat());
                    String[] v12_9 = v10_1.getAsJsonObject("representations");
                    if (v12_9 == null) {
                        v16 = 1;
                    } else {
                        v16 = 1;
                        java.util.ArrayList v15_3 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v12_9.get("full").getAsString()), v10_1.get("width").getAsInt(), v10_1.get("height").getAsInt());
                        v11_1.setFile(v15_3);
                        v11_1.setJpeg(v15_3);
                        v11_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v12_9.get("large").getAsString()), 0, 0));
                        v11_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(this.fixURL(v12_9.get("thumb").getAsString()), 0, 0));
                    }
                    v11_1.setSource(this.fixURL(v10_1.get("source_url").getAsString()));
                    int v8_3 = this.optString(v10_1, "tags", "");
                    String[] v12_0 = v8_3.split(",");
                    StringBuilder v13_1 = new StringBuilder("");
                    int v14_1 = new StringBuilder("");
                    java.util.ArrayList v15_1 = new java.util.ArrayList(0);
                    com.google.gson.JsonArray v0_3 = v10_1.getAsJsonArray("tag_ids");
                    int v17 = v5_0;
                    String v18 = v7_3;
                    if (v0_3.size() == v12_0.length) {
                        int v5_2 = 0;
                        while (v5_2 < v12_0.length) {
                            int v19_2;
                            com.google.gson.JsonArray v20;
                            boolean v21_2;
                            String v7_5 = v12_0[v5_2].trim();
                            if (!v7_5.isEmpty()) {
                                int v5_7;
                                v20 = v0_3;
                                com.google.gson.JsonArray v0_18 = v0_3.get(v5_2).getAsInt();
                                v19_2 = v5_2;
                                if (!v7_5.contains("artist:")) {
                                    v21_2 = v9_0;
                                    v14_1.append(v7_5);
                                    v14_1.append(" ");
                                    v5_7 = 0;
                                } else {
                                    v21_2 = v9_0;
                                    String v7_6 = v7_5.split(":");
                                    if (v7_6.length == 2) {
                                        v7_5 = v7_6[v16];
                                        v13_1.append(v7_5);
                                        v13_1.append(" ");
                                        v5_7 = v16;
                                    } else {
                                        v5_2 = (v19_2 + 1);
                                        v0_3 = v20;
                                        v9_0 = v21_2;
                                    }
                                }
                                com.bisimplex.firebooru.danbooru.TagItem v9_4 = new com.bisimplex.firebooru.danbooru.TagItem();
                                v9_4.setIdx(v0_18);
                                v9_4.setName(v7_5);
                                v9_4.setType(v5_7);
                                v15_1.add(v9_4);
                            } else {
                                v20 = v0_3;
                                v19_2 = v5_2;
                                v21_2 = v9_0;
                            }
                        }
                    }
                    boolean v21_0 = v9_0;
                    v11_1.setTag_artist(v13_1.toString().trim());
                    v11_1.setTag_general(v14_1.toString().trim());
                    v11_1.setHas_notes(0);
                    v11_1.setSeparateTags(v15_1);
                    v11_1.setMd5(v10_1.get("orig_sha512_hash").getAsString());
                    v11_1.setDisableStorage(v16);
                    v11_1.setTags(v8_3.replace(",", ""));
                    v11_1.setEnforceOriginalImage(v4_1.getEnforceOriginalImage());
                    if (!v4_1.isBlacklisted(v11_1)) {
                        this.data.add(v11_1);
                        v11_1.setFavorite(v6.getIsFavByPost(v11_1.getPostId(), v11_1.getMd5()));
                    }
                    v9_0 = (v21_0 + 1);
                    v0_0 = p24;
                    v8_4 = 0;
                    v5_0 = v17;
                    v7_3 = v18;
                }
            }
        }
        return;
    }
}
