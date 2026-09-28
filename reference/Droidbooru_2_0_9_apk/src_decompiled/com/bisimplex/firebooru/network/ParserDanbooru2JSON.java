package com.bisimplex.firebooru.network;
public class ParserDanbooru2JSON extends com.bisimplex.firebooru.network.ParserPosts {
    private boolean addCredentialsToFilesURLs;
    private boolean allowFavSync;
    private com.bisimplex.firebooru.danbooru.DatabaseHelper helper;

    public ParserDanbooru2JSON(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super(p2);
        super.allowFavSync = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isFavSyncDanbooru2();
        super.helper = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        super.addCredentialsToFilesURLs = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().serverHasCookies(super.provider.getServerDescription().getUrl());
        return;
    }

    private void addCredentialsIfNeeded(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if ((this.addCredentialsToFilesURLs) && (this.provider.getServerDescription().validateIsLoggedIn())) {
            this.addCredentials(p3.getFile());
            this.addCredentials(p3.getPreview());
            if (p3.getSample() != p3.getFile()) {
                this.addCredentials(p3.getSample());
            }
        }
        return;
    }

    protected void addCredentials(com.bisimplex.firebooru.danbooru.DanbooruPostImage p5)
    {
        if (p5 != null) {
            String v0_6 = android.net.Uri.parse(p5.getUrl());
            android.net.Uri$Builder v1 = v0_6.buildUpon();
            if ((android.text.TextUtils.isEmpty(v0_6.getQueryParameter("login"))) && (!android.text.TextUtils.isEmpty(this.provider.getServerDescription().getUserName()))) {
                v1.appendQueryParameter("login", this.provider.getServerDescription().getUserName());
            }
            if (android.text.TextUtils.isEmpty(v0_6.getQueryParameter("api_key"))) {
                v1.appendQueryParameter("api_key", this.provider.getServerDescription().getApiKey());
            }
            p5.setUrl(v1.build().toString());
            return;
        } else {
            return;
        }
    }

    protected void checkPostsFavorited()
    {
        java.util.Iterator v0_1 = this.data.iterator();
        while (v0_1.hasNext()) {
            int v4_1;
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_2 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_1.next());
            com.bisimplex.firebooru.danbooru.DatabaseHelper v2_0 = v1_2.isFavorite();
            boolean v3_1 = this.helper.getIsFavByPost(v1_2.getPostId(), v1_2.getMd5());
            if ((v2_0 == null) && (!v3_1)) {
                v4_1 = 0;
            } else {
                v4_1 = 1;
            }
            v1_2.setFavorite(v4_1);
            if (v2_0 != null) {
                if (!v3_1) {
                    this.helper.addFavoriteItem(v1_2);
                } else {
                    this.helper.updateFavoriteItem(v1_2);
                }
            }
        }
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p11)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setHas_children(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optBoolean(p11, "has_children", 0));
        v0_1.setMd5(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "md5", ""));
        if (!android.text.TextUtils.isEmpty(v0_1.getMd5())) {
            String v5_9;
            int v1_38 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optBoolean(p11, "has_large", 0);
            String v4_11 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "file_ext", "");
            if (!v4_11.toLowerCase().endsWith("png")) {
                v5_9 = v4_11;
            } else {
                v5_9 = "jpg";
            }
            String v7_1 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "file_url", "");
            if (v7_1.isEmpty()) {
                v7_1 = String.format("%s/data/%s.%s", new Object[] {this.baseUrl, v0_1.getMd5(), v4_11}));
            }
            String v4_20;
            if (v1_38 == 0) {
                v4_20 = v7_1;
            } else {
                v4_20 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "large_file_url", "");
                if (v4_20.isEmpty()) {
                    v4_20 = String.format("%s/data/sample/sample-%s.%s", new Object[] {this.baseUrl, v0_1.getMd5(), v5_9}));
                }
            }
            v0_1.setPostIdAndUrl(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "id", ""), this.baseUrl, this.provider.getPostFormat());
            v0_1.setParent_id(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "parent_id", ""));
            String v5_16 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v7_1, com.bisimplex.firebooru.network.ParserDanbooru2JSON.optInt(p11, "image_width", 0), com.bisimplex.firebooru.network.ParserDanbooru2JSON.optInt(p11, "image_height", 0));
            v0_1.setFile(v5_16);
            v0_1.setJpeg(v5_16);
            if (v1_38 == 0) {
                v0_1.setSample(v5_16);
            } else {
                int v1_59 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v4_20, 0, 0);
                v0_1.setSample(v1_59);
                if (("zip".equalsIgnoreCase(v5_16.getExtension())) && (v1_59.isVideo())) {
                    v0_1.setFile(v0_1.getSample());
                }
            }
            int v1_2 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "preview_file_url", "");
            if (v1_2.isEmpty()) {
                v1_2 = String.format("%s/ssd/data/preview/%s.%s", new Object[] {this.baseUrl, v0_1.getMd5(), "jpg"}));
            }
            int v1_18;
            v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v1_2, 0, 0));
            v0_1.setRating(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "rating", ""));
            v0_1.setTags(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string", ""));
            v0_1.setSource(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "source", ""));
            v0_1.setAuthor(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "uploader_id", ""));
            v0_1.setScore(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optInt(p11, "score", 0));
            if (com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "last_noted_at", 0) == null) {
                v1_18 = 0;
            } else {
                v1_18 = 1;
            }
            v0_1.setHas_notes(v1_18);
            v0_1.setCreated_at(com.bisimplex.firebooru.network.ParserDanbooru2JSON.getDateIfExist(p11, "created_at", 0));
            v0_1.setTag_artist(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_artist", ""));
            v0_1.setTag_character(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_character", ""));
            v0_1.setTag_copyright(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_copyright", ""));
            v0_1.setTag_general(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_general", ""));
            int v1_31 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_model", "");
            if ((!android.text.TextUtils.isEmpty(v1_31)) && (!android.text.TextUtils.isEmpty(v0_1.getTag_general()))) {
                v0_1.setTag_general(String.format("%s %s", new Object[] {v0_1.getTag_general(), v1_31})).trim());
            }
            int v1_37 = com.bisimplex.firebooru.network.ParserDanbooru2JSON.optString(p11, "tag_string_meta", "");
            if (!android.text.TextUtils.isEmpty(v1_37)) {
                v0_1.setTag_meta(v1_37);
            }
            v0_1.setFavorite(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optBoolean(p11, "is_favorited", 0));
            this.provider.fixUrls(v0_1);
            this.addCredentialsIfNeeded(v0_1);
            v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
            if (p11.has("media_asset")) {
                java.util.Iterator v11_2 = p11.get("media_asset").getAsJsonObject();
                if ((v11_2 != null) && ((!v11_2.isJsonNull()) && (v11_2.has("variants")))) {
                    java.util.Iterator v11_3 = v11_2.get("variants");
                    if ((v11_3 != null) && ((!v11_3.isJsonNull()) && (v11_3.isJsonArray()))) {
                        java.util.Iterator v11_5 = v11_3.getAsJsonArray().iterator();
                        while (v11_5.hasNext()) {
                            int v1_52 = ((com.google.gson.JsonElement) v11_5.next());
                            if (v1_52.isJsonObject()) {
                                int v1_53 = v1_52.getAsJsonObject();
                                if ((v1_53.has("type")) && (v1_53.has("url"))) {
                                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_6 = v1_53.get("type").getAsString();
                                    String v4_15 = v1_53.get("url").getAsString();
                                    if ((!android.text.TextUtils.isEmpty(v3_6)) && (!android.text.TextUtils.isEmpty(v4_15))) {
                                        if (!v3_6.startsWith("360")) {
                                            if (v3_6.equalsIgnoreCase("sample")) {
                                                com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_8 = v0_1.getSample();
                                                v3_8.setUrl(v4_15);
                                                v3_8.setWidth(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optInt(v1_53, "width", 0));
                                                v3_8.setHeight(com.bisimplex.firebooru.network.ParserDanbooru2JSON.optInt(v1_53, "height", 0));
                                            }
                                        } else {
                                            v0_1.getPreview().setUrl(v4_15);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.shouldAddToResults(v0_1)) {
                this.data.add(v0_1);
                v0_1.calculateTags();
            }
        }
        return;
    }
}
