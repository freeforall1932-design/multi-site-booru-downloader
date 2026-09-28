package com.bisimplex.firebooru.network;
public class ParserBooruIO extends com.bisimplex.firebooru.network.ParserPosts {
    private com.bisimplex.firebooru.danbooru.DatabaseHelper helper;
    private com.bisimplex.firebooru.services.BooruTagSorter sorter;

    public ParserBooruIO(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        super.sorter = new com.bisimplex.firebooru.services.BooruTagSorter();
        super.helper = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if ((p3 != null) && (p3.isJsonObject())) {
            com.google.gson.JsonArray v3_3 = p3.getAsJsonObject();
            if (v3_3.has("data")) {
                super.parse(v3_3.get("data").getAsJsonArray());
                return;
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p20)
    {
        int v5_1;
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_6;
        com.bisimplex.firebooru.danbooru.DanbooruPost v2_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v2_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserBooruIO.optString(p20, "key", ""), this.baseUrl, this.provider.getPostFormat());
        if (!p20.has("attributes")) {
            v3_6 = 0;
            v5_1 = 0;
        } else {
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_5 = p20.getAsJsonObject("attributes");
            if (v3_5 == null) {
            } else {
                v5_1 = com.bisimplex.firebooru.network.ParserBooruIO.optInt(v3_5, "width", 0);
                v3_6 = com.bisimplex.firebooru.network.ParserBooruIO.optInt(v3_5, "height", 0);
            }
        }
        java.util.HashMap v7_4 = new java.util.ArrayList();
        if (p20.has("tags")) {
            int v8_18 = p20.getAsJsonObject("tags");
            if (v8_18 != 0) {
                int v8_19 = v8_18.keySet();
                Integer v9_9 = new StringBuilder();
                int v11_2 = new StringBuilder();
                StringBuilder v12_1 = new StringBuilder();
                StringBuilder v13_1 = new StringBuilder();
                StringBuilder v14_1 = new StringBuilder();
                int v8_20 = v8_19.iterator();
                while (v8_20.hasNext()) {
                    String v15_1 = ((String) v8_20.next());
                    if (!v15_1.isEmpty()) {
                        String v6_13;
                        if (!v15_1.contains("#")) {
                            v6_13 = 0;
                        } else {
                            String v6_11 = v15_1.substring(v15_1.lastIndexOf("#"));
                            if (!v6_11.equalsIgnoreCase("#artist")) {
                                if (!v6_11.equalsIgnoreCase("#character")) {
                                    if (!v6_11.equalsIgnoreCase("#origin")) {
                                    } else {
                                        v6_13 = 3;
                                    }
                                } else {
                                    v6_13 = 4;
                                }
                            } else {
                                v6_13 = 1;
                            }
                        }
                        if (v6_13 == 1) {
                            v11_2.append(v15_1);
                        } else {
                            if (v6_13 == 3) {
                                v12_1.append(v15_1);
                            } else {
                                if (v6_13 == 4) {
                                    v13_1.append(v15_1);
                                } else {
                                    v14_1.append(v15_1);
                                }
                            }
                        }
                        com.bisimplex.firebooru.danbooru.TagItem v10_20 = new com.bisimplex.firebooru.danbooru.TagItem();
                        v10_20.setName(v15_1);
                        v10_20.setType(v6_13);
                        v7_4.add(v10_20);
                        v9_9.append(v15_1);
                        v9_9.append(" ");
                    }
                }
                v2_1.setTags(v9_9.toString().trim());
                v2_1.setTag_general(v14_1.toString().trim());
                v2_1.setTag_artist(v11_2.toString().trim());
                v2_1.setTag_character(v13_1.toString().trim());
                v2_1.setTag_copyright(v12_1.toString().trim());
            }
        }
        v2_1.setSeparateTags(this.sorter.sortTags(v7_4));
        v2_1.setMd5(com.bisimplex.firebooru.network.Utils.md5(v2_1.getPostUrl()));
        v2_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
        v2_1.setRating("");
        v2_1.setVisible(1);
        if (p20.has("transforms")) {
            java.util.List v1_2 = p20.get("transforms").getAsJsonObject();
            String v6_0 = v1_2.keySet();
            java.util.HashMap v7_1 = new java.util.HashMap();
            String v6_1 = v6_0.iterator();
            while (v6_1.hasNext()) {
                int v8_14 = v1_2.get(((String) v6_1.next())).getAsString().trim();
                if (!android.text.TextUtils.isEmpty(v8_14)) {
                    com.bisimplex.firebooru.danbooru.TagItem v10_6 = v8_14.split("/");
                    if (v10_6.length >= 2) {
                        Integer v9_4 = v10_6[(v10_6.length - 1)].replaceAll("[^0-9]", "");
                        if (!android.text.TextUtils.isEmpty(v9_4)) {
                            v7_1.put(Integer.valueOf(Integer.parseInt(v9_4)), String.format("%s/api/legacy/data/%s", new Object[] {this.baseUrl, v8_14})));
                        }
                    }
                }
            }
            java.util.List v1_3 = v7_1.size();
            if (v1_3 >= 2) {
                java.util.ArrayList v4_1 = new java.util.ArrayList(v7_1.keySet());
                java.util.Collections.sort(v4_1);
                v2_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(((String) v7_1.get(v4_1.get(0))), 0, 0));
                String v6_7 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(((String) v7_1.get(v4_1.get((v1_3 - 1)))), v5_1, v3_6);
                v2_1.setFile(v6_7);
                v2_1.setJpeg(v6_7);
                if (v1_3 <= 2) {
                    v2_1.setSample(v6_7);
                } else {
                    java.util.List v1_7 = ((String) v7_1.get(v4_1.get(((int) Math.floor((((double) v1_3) / 4611686018427387904))))));
                    if (!v6_7.getUrl().equalsIgnoreCase(v1_7)) {
                        v2_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v1_7, 0, 0));
                    } else {
                        v2_1.setSample(v6_7);
                    }
                }
                if ((v2_1.getFile() != null) && (this.shouldAddToResults(v2_1))) {
                    this.data.add(v2_1);
                }
            }
        }
        return;
    }
}
