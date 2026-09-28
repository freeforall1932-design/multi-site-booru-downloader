package com.bisimplex.firebooru.network;
public class ParserHydrusJSON extends com.bisimplex.firebooru.network.ParserPosts {
    private String apikey;
    private String baseURL;
    private java.util.HashMap dic;
    private java.util.List file_ids;
    private String rating_service_key;

    public ParserHydrusJSON(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super(p2);
        super.baseURL = super.provider.getServerDescription().getUrl();
        super.apikey = super.provider.getServerDescription().getApiKey();
        super.file_ids = new java.util.ArrayList(0);
        super.dic = new java.util.HashMap();
        return;
    }

    private com.google.gson.JsonArray getTagList(com.google.gson.JsonObject p8)
    {
        if (!p8.has("service_names_to_statuses_to_tags")) {
            if (p8.has("tags")) {
                com.google.gson.JsonArray v8_3 = p8.get("tags").getAsJsonObject();
                if (!v8_3.isJsonNull()) {
                    boolean v0_3 = v8_3.keySet().iterator();
                    while (v0_3.hasNext()) {
                        com.google.gson.JsonObject v1_5 = v8_3.get(((String) v0_3.next())).getAsJsonObject();
                        if ((!v1_5.isJsonNull()) && ((v1_5.has("display_tags")) && (v1_5.has("type")))) {
                            int v5_2 = com.bisimplex.firebooru.network.ParserHydrusJSON.optInt(v1_5, "type", 0);
                            com.google.gson.JsonObject v1_7 = v1_5.get("display_tags").getAsJsonObject();
                            if ((v5_2 == 10) && ((!v1_7.isJsonNull()) && (v1_7.has("0")))) {
                                return v1_7.get("0").getAsJsonArray();
                            }
                        }
                    }
                } else {
                    return 0;
                }
            }
        } else {
            com.google.gson.JsonArray v8_7 = p8.get("service_names_to_statuses_to_tags").getAsJsonObject();
            if (!v8_7.isJsonNull()) {
                boolean v0_6 = "all known tags";
                if (!v8_7.has("all known tags")) {
                    v0_6 = "my tags";
                    if (!v8_7.has("my tags")) {
                        v0_6 = "my tag repository";
                        if (!v8_7.has("my tag repository")) {
                            v0_6 = "";
                        }
                    }
                }
                if (!android.text.TextUtils.isEmpty(v0_6)) {
                    com.google.gson.JsonArray v8_9 = v8_7.get(v0_6).getAsJsonObject();
                    if ((!v8_9.isJsonNull()) && (v8_9.has("0"))) {
                        return v8_9.get("0").getAsJsonArray();
                    }
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    private void parseFileIds(com.google.gson.JsonArray p4)
    {
        this.file_ids.clear();
        int v0_1 = 0;
        while (v0_1 < p4.size()) {
            this.file_ids.add(Integer.valueOf(p4.get(v0_1).getAsInt()));
            v0_1++;
        }
        return;
    }

    private void parseMetadata(com.google.gson.JsonArray p11)
    {
        this.dic.clear();
        int v1 = 0;
        while (v1 < p11.size()) {
            java.util.HashMap v2_0 = p11.get(v1).getAsJsonObject();
            com.bisimplex.firebooru.danbooru.DanbooruPost v3_3 = this.postWithFileId(v2_0.get("file_id").getAsString());
            v3_3.setMd5(v2_0.get("hash").getAsString());
            String v4_15 = v3_3.getFile();
            v4_15.setExt(v2_0.get("ext").getAsString());
            boolean v5_7 = v4_15.getExtension();
            if ((!android.text.TextUtils.isEmpty(v5_7)) && (this.isSupportedExtension(v5_7))) {
                if (v2_0.has("ratings")) {
                    boolean v5_10 = v2_0.get("ratings");
                    if ((v5_10) && (v5_10.isJsonObject())) {
                        boolean v5_11 = v5_10.getAsJsonObject();
                        if ((!android.text.TextUtils.isEmpty(this.rating_service_key)) && (v5_11.has(this.rating_service_key))) {
                            boolean v5_12 = v5_11.get(this.rating_service_key);
                            if (v5_12.isJsonPrimitive()) {
                                v3_3.setFavorite(v5_12.getAsBoolean());
                            }
                        }
                    }
                }
                v4_15.setWidth(com.bisimplex.firebooru.network.Parser.optInt(v2_0, "width", 0));
                v4_15.setHeight(com.bisimplex.firebooru.network.Parser.optInt(v2_0, "height", 0));
                if (!v2_0.has("detailed_known_urls")) {
                    String v4_19 = v2_0.get("known_urls").getAsJsonArray();
                    if ((v4_19.isJsonArray()) && (v4_19.size() > 0)) {
                        v3_3.setSource(v4_19.get(0).getAsString());
                    }
                } else {
                    String v4_0 = v2_0.get("detailed_known_urls").getAsJsonArray();
                    boolean v5_1 = new java.util.ArrayList(2);
                    if ((v4_0.isJsonArray()) && (v4_0.size() > 0)) {
                        int v7_2 = 0;
                        while (v7_2 < v4_0.size()) {
                            String v8_2 = v4_0.get(v7_2).getAsJsonObject();
                            if (v8_2.get("url_type").getAsInt() == 0) {
                                v5_1.add(v8_2.get("normalised_url").getAsString());
                            }
                            v7_2++;
                        }
                    }
                    if (v5_1.size() >= 1) {
                        v3_3.setPostUrl(((String) v5_1.get(0)));
                    }
                    if (v5_1.size() >= 2) {
                        v3_3.setSource(((String) v5_1.get(1)));
                    }
                }
                if (v2_0.has("time_modified_details")) {
                    v3_3.setCreated_at(com.bisimplex.firebooru.network.ParserHydrusJSON.getDateIfExist(v2_0.getAsJsonObject("time_modified_details"), "local", 0));
                }
                java.util.HashMap v2_1 = this.getTagList(v2_0);
                if ((v2_1 != null) && (!v2_1.isJsonNull())) {
                    this.parseTagForPost(v3_3, v2_1);
                }
                this.data.add(v3_3);
                this.dic.put(v3_3.getPostId(), v3_3);
            } else {
                v4_15.setUrl("");
            }
            v1++;
        }
        return;
    }

    private void parseTagForPost(com.bisimplex.firebooru.danbooru.DanbooruPost p12, com.google.gson.JsonArray p13)
    {
        StringBuilder v0_1 = new StringBuilder("");
        StringBuilder v2_1 = new StringBuilder("");
        StringBuilder v3_1 = new StringBuilder("");
        StringBuilder v4_1 = new StringBuilder("");
        java.util.ArrayList v5_0 = new java.util.ArrayList(0);
        StringBuilder v7_1 = new StringBuilder("");
        int v1_1 = 0;
        while (v1_1 < p13.size()) {
            String v8_3 = p13.get(v1_1).getAsString().trim();
            if (!v8_3.isEmpty()) {
                int v9_2;
                v7_1.append(v8_3);
                v7_1.append(" ");
                if (!v8_3.contains("creator:")) {
                    if ((!v8_3.contains("character:")) && (!v8_3.contains("person:"))) {
                        if ((!v8_3.contains("series:")) && (!v8_3.contains("title:"))) {
                            v2_1.append(v8_3);
                            v2_1.append(" ");
                            v9_2 = 0;
                        } else {
                            v4_1.append(v8_3);
                            v4_1.append(" ");
                            v9_2 = 3;
                        }
                    } else {
                        v3_1.append(v8_3);
                        v3_1.append(" ");
                        v9_2 = 4;
                    }
                } else {
                    v0_1.append(v8_3);
                    v0_1.append(" ");
                    v9_2 = 1;
                }
                boolean v10_11 = new com.bisimplex.firebooru.danbooru.TagItem();
                v10_11.setIdx(v1_1);
                v10_11.setName(v8_3);
                v10_11.setType(v9_2);
                v5_0.add(v10_11);
            }
            v1_1++;
        }
        p12.setTag_artist(v0_1.toString().trim());
        p12.setTag_general(v2_1.toString().trim());
        p12.setTag_copyright(v4_1.toString().trim());
        p12.setTag_character(v3_1.toString().trim());
        p12.setSeparateTags(v5_0);
        return;
    }

    private com.bisimplex.firebooru.danbooru.DanbooruPost postWithFileId(String p5)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setPostId(p5);
        v0_1.setDisableStorage(1);
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_4 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage();
        v1_4.setUrl(String.format("%s/get_files/file?Hydrus-Client-API-Access-Key=%s&file_id=%s", new Object[] {this.baseURL, this.apikey, p5})));
        v0_1.setFile(v1_4);
        v0_1.setJpeg(v1_4);
        v0_1.setSample(v1_4);
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_1 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage();
        v1_1.setUrl(String.format("%s/get_files/thumbnail?Hydrus-Client-API-Access-Key=%s&file_id=%s", new Object[] {this.baseURL, this.apikey, p5})));
        v0_1.setPreview(v1_1);
        v0_1.setTags("");
        v0_1.setTag_artist("");
        v0_1.setTag_character("");
        v0_1.setTag_copyright("");
        v0_1.setTag_general("");
        v0_1.setPostUrl("");
        return v0_1;
    }

    private void setFile_ids(java.util.List p1)
    {
        this.file_ids = p1;
        return;
    }

    public java.util.List getFile_ids()
    {
        return this.file_ids;
    }

    public String getRating_service_key()
    {
        return this.rating_service_key;
    }

    protected void parse(com.google.gson.JsonElement p7)
    {
        if ((p7 != null) && (p7.isJsonObject())) {
            com.google.gson.JsonObject v0_3 = p7.getAsJsonObject();
            if (!v0_3.has("file_ids")) {
                if (v0_3.has("metadata")) {
                    if (v0_3.has("services")) {
                        com.google.gson.JsonObject v0_0 = v0_3.get("services");
                        if ((v0_0 != null) && (v0_0.isJsonObject())) {
                            com.google.gson.JsonObject v0_2 = v0_0.getAsJsonObject();
                            java.util.Iterator v2_4 = v0_2.keySet().iterator();
                            while (v2_4.hasNext()) {
                                String v3_3 = ((String) v2_4.next());
                                int v4_0 = v0_2.get(v3_3);
                                if ((v4_0.isJsonObject()) && (v4_0.getAsJsonObject().get("type").getAsInt() == 7)) {
                                    this.rating_service_key = v3_3;
                                    break;
                                }
                            }
                        }
                    }
                    this.parseMetadata(p7.getAsJsonObject().get("metadata").getAsJsonArray());
                }
            } else {
                this.parseFileIds(p7.getAsJsonObject().get("file_ids").getAsJsonArray());
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p1)
    {
        return;
    }

    protected void parseFinished()
    {
        return;
    }

    public void setRating_service_key(String p1)
    {
        this.rating_service_key = p1;
        return;
    }

    public void sortWithFileIds(java.util.List p4)
    {
        if ((!this.dic.isEmpty()) && (!p4.isEmpty())) {
            java.util.ArrayList v0_1 = new java.util.ArrayList(this.data.size());
            java.util.Iterator v4_1 = p4.iterator();
            while (v4_1.hasNext()) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v1_6 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.dic.get(String.valueOf(((Integer) v4_1.next()))));
                if (v1_6 != null) {
                    v0_1.add(v1_6);
                }
            }
            this.data = v0_1;
        }
        return;
    }
}
