package com.bisimplex.firebooru.danbooru;
public class BooruProvider {
    private static final String ALLOWED_NUMBERS = "0123456789";
    private static com.bisimplex.firebooru.danbooru.BooruProvider sharedInstance;
    private java.util.List _blackListRules;
    String baseUrl;
    String bestString;
    boolean enforceOriginalImage;
    java.util.HashMap hashKeys;
    int initialPageNumber;
    String noteBaseUrl;
    int pageSize;
    boolean parseAsHTML;
    String pingServiceUrl;
    String poolPostFormart;
    String postFormat;
    boolean requireFixPreview;
    String scheme;
    com.bisimplex.firebooru.danbooru.ServerItem serverDescription;
    boolean shouldFakeUserAgent;
    boolean shouldFixURLs;
    boolean shouldParseAsJson;
    boolean shouldParseAsRSS;
    String subdomain;
    String subpath;
    String topString;

    static BooruProvider()
    {
        return;
    }

    protected BooruProvider()
    {
        this.pageSize = 100;
        this.setServerDescription(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
        return;
    }

    public BooruProvider(com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        this.pageSize = 100;
        this.setServerDescription(p2);
        return;
    }

    public BooruProvider(com.bisimplex.firebooru.danbooru.ServerItem p1, int p2)
    {
        this.pageSize = p2;
        this.setServerDescription(p1);
        return;
    }

    public static boolean containsIgnoreCase(String p1, String p2)
    {
        if ((!android.text.TextUtils.isEmpty(p1)) && (!android.text.TextUtils.isEmpty(p2))) {
            return p2.toLowerCase(java.util.Locale.US).contains(p1.toLowerCase(java.util.Locale.US));
        } else {
            return 0;
        }
    }

    public static com.bisimplex.firebooru.danbooru.BooruProvider createSelectedInstance()
    {
        return com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
    }

    private static String fixSingleSource(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            String v3_5 = p3.trim();
            if (((com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v3_5) != null) && ((com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("pixiv.net/", v3_5)) || (com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("pximg.net/", v3_5)))) && ((!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("illust_id=", v3_5)) && (!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("/artworks/", v3_5)))) {
                java.util.Locale v0_9 = v3_5.split("/");
                if (v0_9.length > 1) {
                    String v3_3 = v0_9[(v0_9.length - 1)];
                    if (v3_3.contains("_")) {
                        v3_3 = v3_3.split("_")[0];
                    }
                    v3_5 = String.format(java.util.Locale.US, "https://www.pixiv.net/en/artworks/%s", new Object[] {v3_3}));
                }
            }
            return v3_5;
        } else {
            return 0;
        }
    }

    public static java.util.List fixSourcePost(String p5)
    {
        int v1_0 = 0;
        java.util.ArrayList v0_1 = new java.util.ArrayList(0);
        if (!android.text.TextUtils.isEmpty(p5)) {
            if (!p5.contains("|")) {
                String v5_1 = com.bisimplex.firebooru.danbooru.BooruProvider.fixSingleSource(p5);
                if (!android.text.TextUtils.isEmpty(v5_1)) {
                    v0_1.add(v5_1);
                }
            } else {
                String v5_2 = p5.split("\\|");
                int v2_1 = v5_2.length;
                while (v1_0 < v2_1) {
                    String v3_1 = com.bisimplex.firebooru.danbooru.BooruProvider.fixSingleSource(v5_2[v1_0]);
                    if (!android.text.TextUtils.isEmpty(v3_1)) {
                        v0_1.add(v3_1);
                    }
                    v1_0++;
                }
            }
        }
        return v0_1;
    }

    private java.util.List getBlackListRules()
    {
        return this._blackListRules;
    }

    public static com.bisimplex.firebooru.danbooru.BooruProvider getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.BooruProvider.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.BooruProvider.sharedInstance = new com.bisimplex.firebooru.danbooru.BooruProvider();
        }
        return com.bisimplex.firebooru.danbooru.BooruProvider.sharedInstance;
    }

    public static com.bisimplex.firebooru.danbooru.BooruProvider getInstance(com.bisimplex.firebooru.danbooru.ServerItem p1)
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_1 = new com.bisimplex.firebooru.danbooru.BooruProvider();
        v0_1.setServerDescription(p1);
        return v0_1;
    }

    public static com.bisimplex.firebooru.danbooru.BooruProvider getInstanceByUrl(String p2)
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_0 = 0;
        if ((p2 != null) && (!p2.isEmpty())) {
            com.bisimplex.firebooru.danbooru.ServerItem v2_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerByUrl(com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(p2));
            if (v2_1 != null) {
                v0_0 = new com.bisimplex.firebooru.danbooru.BooruProvider(v2_1);
            } else {
                return 0;
            }
        }
        return v0_0;
    }

    public static com.bisimplex.firebooru.danbooru.BooruProvider getInstanceByUrl(String p2, boolean p3)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            com.bisimplex.firebooru.danbooru.ServerItem v2_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerByUrl(com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(p2), p3);
            if (v2_1 != null) {
                return new com.bisimplex.firebooru.danbooru.BooruProvider(v2_1);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String randomNumberString(int p5)
    {
        if (p5 != null) {
            java.util.Random v0_1 = new java.util.Random();
            StringBuilder v1_1 = new StringBuilder(p5);
            int v2 = 0;
            while (v2 < p5) {
                v1_1.append("0123456789".charAt(v0_1.nextInt("0123456789".length())));
                v2++;
            }
            return v1_1.toString();
        } else {
            return "";
        }
    }

    public static String randomString(int p2)
    {
        if (p2 != null) {
            return java.util.UUID.randomUUID().toString().substring(0, p2);
        } else {
            return "";
        }
    }

    public static String sha1(String p4)
    {
        String v4_3 = java.security.MessageDigest.getInstance("SHA1").digest(p4.getBytes());
        StringBuffer v0_3 = new StringBuffer();
        int v1 = 0;
        while (v1 < v4_3.length) {
            v0_3.append(Integer.toString(((v4_3[v1] & 255) + 256), 16).substring(1));
            v1++;
        }
        return v0_3.toString();
    }

    public static java.net.URL stringToURL(String p1)
    {
        try {
            return new java.net.URL(p1);
        } catch (int v1_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_1);
            return 0;
        }
    }

    public boolean canFeedTagMetadata()
    {
        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621)) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean canRequestTagMetadata()
    {
        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || (!com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase("gelbooru.com", this.serverDescription.getUrl()))) {
            return 0;
        } else {
            return 1;
        }
    }

    public String encodeToUTF8(String p2)
    {
        try {
            return java.net.URLEncoder.encode(p2, "UTF-8");
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }

    public String encriptPasswordWithServiceUrl(String p3, String p4)
    {
        if (p4 != null) {
            if (!p4.isEmpty()) {
                try {
                    return com.bisimplex.firebooru.danbooru.BooruProvider.sha1(this.getHashKeyByUrl(p3).replace("your-password", p4));
                } catch (java.security.NoSuchAlgorithmException v3_2) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_2);
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void fixDanbooruPreview(com.bisimplex.firebooru.danbooru.DanbooruPostImage p4)
    {
        if (this.requireFixPreview) {
            p4.setUrl(p4.getUrl().replaceAll("/ssd/", "http://sonohara.donmai.us/"));
        }
        return;
    }

    public void fixUrlOnPost(com.bisimplex.firebooru.danbooru.DanbooruPostImage p3)
    {
        if (!p3.getUrl().startsWith("//")) {
            if (p3.getUrl().startsWith("/")) {
                p3.setUrl(String.format("%s%s", new Object[] {this.serverDescription.getUrl(), p3.getUrl()})));
            }
        } else {
            String v0_6 = this.scheme;
            if (v0_6 != null) {
                p3.setUrl(String.format("%s:%s", new Object[] {v0_6, p3.getUrl()})));
                return;
            }
        }
        return;
    }

    public void fixUrls(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.fixUrlOnPost(p2.getFile());
        this.fixUrlOnPost(p2.getJpeg());
        this.fixUrlOnPost(p2.getPreview());
        this.fixUrlOnPost(p2.getSample());
        return;
    }

    public String generateAutocompleteTagURL(String p9)
    {
        if (!android.text.TextUtils.isEmpty(p9)) {
            if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) || (!this.canRequestTagMetadata())) {
                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                                return 0;
                            } else {
                                android.net.Uri$Builder v0_35 = android.net.Uri.parse(String.format("%s/tags.json", new Object[] {this.serverDescription.getUrl()}))).buildUpon();
                                String v1_13 = this.serverDescription.getApiKey();
                                String v2_11 = this.serverDescription.getUserName();
                                String v9_23 = String.format("%s*", new Object[] {p9.trim()}));
                                v0_35.appendQueryParameter("search[name_matches]", v9_23);
                                v0_35.appendQueryParameter("search[order]", "count");
                                v0_35.appendQueryParameter("limit", "10");
                                if (!android.text.TextUtils.isEmpty(v9_23)) {
                                    if (!v9_23.endsWith("*")) {
                                        v9_23 = String.format("%s*", new Object[] {v9_23}));
                                    }
                                    v0_35.appendQueryParameter("q", v9_23);
                                }
                                if ((!android.text.TextUtils.isEmpty(v1_13)) && (!android.text.TextUtils.isEmpty(v2_11))) {
                                    v0_35.appendQueryParameter("api_key", v1_13);
                                    v0_35.appendQueryParameter("login", v2_11);
                                }
                                return v0_35.toString();
                            }
                        } else {
                            android.net.Uri$Builder v0_3 = android.net.Uri.parse(String.format("%s/api/v1/json/search/tags", new Object[] {this.serverDescription.getUrl()}))).buildUpon();
                            String v1_1 = this.serverDescription.getApiKey();
                            String v9_1 = p9.trim();
                            if (!android.text.TextUtils.isEmpty(v9_1)) {
                                if (!v9_1.endsWith("*")) {
                                    v9_1 = String.format("%s*", new Object[] {v9_1}));
                                }
                                v0_3.appendQueryParameter("q", v9_1);
                            }
                            if (!android.text.TextUtils.isEmpty(v1_1)) {
                                v0_3.appendQueryParameter("key", v1_1);
                            }
                            return v0_3.toString();
                        }
                    } else {
                        return String.format("%s/add_tags/search_tags?tag_display_type=display&search=%s&Hydrus-Client-API-Access-Key=%s", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(p9), this.encodeToUTF8(this.serverDescription.getApiKey())}));
                    }
                } else {
                    android.net.Uri$Builder v0_11 = this.serverDescription.getApiKey();
                    String v1_5 = this.serverDescription.getUserName();
                    if ((!android.text.TextUtils.isEmpty(v0_11)) && (!android.text.TextUtils.isEmpty(v1_5))) {
                        return String.format("%s/tags.json?search[order]=count&search[hide_empty]=true&limit=10&only=id,name,category,post_count&search[name_matches]=*%s*&api_key=%s&login=%s", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(p9), this.encodeToUTF8(v0_11), this.encodeToUTF8(v1_5)}));
                    } else {
                        return String.format("%s/tags.json?search[order]=count&search[hide_empty]=true&limit=10&only=id,name,category,post_count&search[name_matches]=*%s*", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(p9)}));
                    }
                }
            } else {
                android.net.Uri$Builder v0_19 = this.serverDescription.getApiKey();
                if (!android.text.TextUtils.isEmpty(v0_19)) {
                    return String.format("%s/index.php?page=autocomplete2&term=%s&type=tag_query&limit=10%s", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(p9), v0_19}));
                } else {
                    return String.format("%s/index.php?page=autocomplete2&term=%s&type=tag_query&limit=10", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(p9)}));
                }
            }
        } else {
            return 0;
        }
    }

    public String generateMetadataTagURL(String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                return 0;
            } else {
                String v1_1;
                String v0_2 = this.serverDescription.getApiKey();
                if (!this.getShouldParseAsJson()) {
                    v1_1 = "";
                } else {
                    v1_1 = "json=1&";
                }
                if ((android.text.TextUtils.isEmpty(v0_2)) && (!v0_2.startsWith("&"))) {
                    v0_2 = "";
                }
                return String.format("%s/index.php?%spage=dapi&s=tag&q=index&limit=100&names=%s%s", new Object[] {this.serverDescription.getUrl(), v1_1, this.encodeToUTF8(p5), v0_2}));
            }
        } else {
            return 0;
        }
    }

    public java.net.URL generateNoteRequestUrlForPost(String p3, int p4)
    {
        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
            return this.generateNoteRequestUrlForPostId(p3);
        } else {
            int v3_4 = new StringBuilder().append(this.noteBaseUrl).append(String.format("&search[post_id]=%s&page=%d", new Object[] {p3, Integer.valueOf(p4)}))).toString();
            android.util.Log.i("Gelbooru", String.format("notes url = %s", new Object[] {v3_4})));
            try {
                return new java.net.URL(v3_4);
            } catch (int v3_5) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_5);
                return 0;
            }
        }
    }

    public java.net.URL generateNoteRequestUrlForPostId(String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            try {
                return new java.net.URL(String.format(this.noteBaseUrl, new Object[] {this.serverDescription.getUrl(), p4})));
            } catch (java.net.MalformedURLException v4_2) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_2);
                return 0;
            }
        } else {
            return 0;
        }
    }

    public String generatePoolQuery(String p3)
    {
        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
            return String.format("pool:%s", new Object[] {p3}));
        } else {
            return String.format("gallery_id:%s", new Object[] {p3}));
        }
    }

    public java.net.URL generatePoolSearchUrl(String p4)
    {
        try {
            java.net.MalformedURLException v4_4 = java.net.URLEncoder.encode(p4, "UTF-8");
        } catch (java.net.MalformedURLException v4_5) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_5);
            return 0;
        }
        if (this.serverDescription.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
            try {
                return new java.net.URL(String.format("%s/pool?commit=Search&query=%s", new Object[] {this.serverDescription.getUrl(), v4_4})));
            } catch (java.net.MalformedURLException v4_3) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_3);
            }
        }
        return 0;
    }

    public String generatePoolUniqueUrlWithId(String p3)
    {
        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
            return String.format("%s/pool/show/%s", new Object[] {this.serverDescription.getUrl(), p3}));
        } else {
            return String.format("%s/galleries/%s", new Object[] {this.serverDescription.getUrl(), p3}));
        }
    }

    public java.net.URL generateRequestUrl(com.bisimplex.firebooru.network.SourceQuery p13, int p14)
    {
        int v1_0;
        if (p14 >= null) {
            v1_0 = p14;
        } else {
            v1_0 = 0;
        }
        String v2_62 = java.util.regex.Pattern.compile("rating", 2);
        String v4_0 = p13.getText();
        String v5_0 = "";
        if (v4_0 == null) {
            v4_0 = "";
        }
        if (this.serverDescription == null) {
            this.setServerDescription(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
        }
        if ((!v2_62.matcher(v4_0).find()) && (this.serverDescription.isRatingFilterEnabled())) {
            if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) && ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621))) {
                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch) {
                        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                            if ((this.serverDescription.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && (!this.serverDescription.isDefault())) {
                                if (!this.requireFixPreview) {
                                    v4_0 = String.format("%s rating:safe", new Object[] {v4_0}));
                                } else {
                                    v4_0 = String.format("%s rating:g", new Object[] {v4_0}));
                                }
                            }
                        } else {
                            if (!this.getShouldParseAsJson()) {
                                v4_0 = String.format("%s rating:safe", new Object[] {v4_0}));
                            } else {
                                v4_0 = String.format("%s rating:general", new Object[] {v4_0}));
                            }
                        }
                    } else {
                        v4_0 = String.format("%s rating:s", new Object[] {v4_0}));
                    }
                } else {
                    v4_0 = String.format("%s rating=s", new Object[] {v4_0}));
                }
            } else {
                v4_0 = String.format("%s rating:safe", new Object[] {v4_0}));
            }
        }
        String v2_2 = v4_0.replace("#fullhd", "width:>=1920");
        String v4_2 = new java.util.HashMap(p13.getExtraParams());
        String v13_1 = p13.getExtraTags();
        if (!android.text.TextUtils.isEmpty(v13_1)) {
            v4_2.remove("extra_tags");
            v2_2 = String.format("%s %s", new Object[] {v2_2, v13_1})).trim();
        }
        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) && ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO)))) {
            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch) {
                if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails)) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono) || ((!android.text.TextUtils.isEmpty(v2_2)) && (!"*".equalsIgnoreCase(v2_2)))) {
                            v5_0 = v2_2;
                        }
                    } else {
                        if ((!android.text.TextUtils.isEmpty(v2_2)) && (!"*".equalsIgnoreCase(v2_2))) {
                        } else {
                            v5_0 = "system:everything";
                        }
                    }
                } else {
                    if (!android.text.TextUtils.isEmpty(v2_2)) {
                    } else {
                        v5_0 = "*";
                    }
                }
            } else {
                if (!"*".equalsIgnoreCase(v2_2)) {
                } else {
                    v5_0 = "sort:-upload";
                }
            }
        } else {
            if (!"*".equalsIgnoreCase(v2_2)) {
            }
        }
        String v13_35;
        String v13_30 = java.util.regex.Pattern.compile("width:", 2);
        String v2_4 = java.util.regex.Pattern.compile("height:", 2);
        if ((!v13_30.matcher(v5_0).find()) && (!v2_4.matcher(v5_0).find())) {
            v13_35 = 0;
        } else {
            v13_35 = 1;
        }
        String v13_39;
        this.enforceOriginalImage = v13_35;
        String v13_36 = this.encodeToUTF8(v5_0);
        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) && ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621)))) {
            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch) {
                        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails)) {
                            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO) {
                                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono) {
                                        v13_39 = 0;
                                    } else {
                                        String v13_43 = android.net.Uri.parse(this.serverDescription.getUrl()).buildUpon();
                                        v13_43.path("/api/v1/posts/");
                                        if (!android.text.TextUtils.isEmpty(v5_0)) {
                                            v13_43.appendQueryParameter("q", v5_0);
                                        }
                                        v13_43.appendQueryParameter("o", String.valueOf((p14 * 50)));
                                        v13_39 = v13_43.toString();
                                    }
                                } else {
                                    if (v1_0 <= 0) {
                                        v13_39 = String.format("%s%s", new Object[] {this.baseUrl, v13_36}));
                                    } else {
                                        v13_39 = String.format("%s%s&cursor=%d", new Object[] {this.baseUrl, v13_36, Integer.valueOf((v1_0 * 50))}));
                                    }
                                }
                            } else {
                                String v13_47 = new com.google.gson.JsonArray();
                                String v14_8 = v5_0.split(",");
                                if (v14_8.length > 0) {
                                    int v1_3 = 0;
                                    while (v1_3 < v14_8.length) {
                                        String v2_41 = v14_8[v1_3].trim();
                                        if (!android.text.TextUtils.isEmpty(v2_41)) {
                                            v13_47.add(v2_41);
                                        }
                                        v1_3++;
                                    }
                                }
                                v13_39 = String.format("%s&tags=%s", new Object[] {this.baseUrl, this.encodeToUTF8(v13_47.toString())}));
                                if (v4_2.size() > 0) {
                                    String v14_12 = new StringBuilder();
                                    int v1_5 = v4_2.keySet().iterator();
                                    while (v1_5.hasNext()) {
                                        String v2_36 = ((String) v1_5.next());
                                        v14_12.append(String.format("%s=%s&", new Object[] {v2_36, this.encodeToUTF8(((String) v4_2.get(v2_36)))})));
                                    }
                                    v14_12.trimToSize();
                                    v13_39 = String.format("%s&%s", new Object[] {v13_39, v14_12.substring(0, (v14_12.length() - 1))}));
                                }
                            }
                        } else {
                            v13_39 = String.format("%s%d&q=%s", new Object[] {this.baseUrl, Integer.valueOf(v1_0), v13_36}));
                            if (v4_2.size() > 0) {
                                String v14_17 = new StringBuilder();
                                int v1_10 = v4_2.keySet().iterator();
                                while (v1_10.hasNext()) {
                                    String v2_46 = ((String) v1_10.next());
                                    v14_17.append(String.format("%s=%s&", new Object[] {v2_46, v4_2.get(v2_46)})));
                                }
                                v14_17.trimToSize();
                                v13_39 = String.format("%s&%s", new Object[] {v13_39, v14_17.substring(0, (v14_17.length() - 1))}));
                            }
                        }
                    } else {
                        v13_39 = String.format("%s%d&q=%s", new Object[] {this.baseUrl, Integer.valueOf(v1_0), v13_36}));
                    }
                } else {
                    if (!this.serverDescription.getUrl().contains("rule34.us")) {
                        String v14_23 = String.format("%s/index.php?page=post&s=list&pid=%d", new Object[] {this.serverDescription.getUrl(), Integer.valueOf((v1_0 * 20))}));
                        if (android.text.TextUtils.isEmpty(v13_36)) {
                            v13_39 = v14_23;
                        } else {
                            v13_39 = String.format("%s&tags=%s", new Object[] {v14_23, v13_36}));
                        }
                    } else {
                        String v0_17 = new StringBuilder(this.serverDescription.getUrl());
                        v0_17.append("/index.php?r=posts/index");
                        if (!android.text.TextUtils.isEmpty(v13_36)) {
                            v0_17.append("&q=");
                            v0_17.append(v13_36);
                        }
                        if (p14 > null) {
                            v0_17.append("&page=");
                            v0_17.append(p14);
                        }
                        v13_39 = v0_17.toString();
                    }
                }
            } else {
                v13_39 = String.format("%s%s/%d", new Object[] {this.baseUrl, android.net.Uri.encode(v5_0).replace("%2F", "%5Es"), Integer.valueOf(v1_0)}));
            }
        } else {
            v13_39 = String.format("%s%d&tags=%s", new Object[] {this.baseUrl, Integer.valueOf(v1_0), v13_36}));
        }
        android.util.Log.i("BooruProvider", String.format("Final request url = %s", new Object[] {v13_39})));
        try {
            return new java.net.URL(v13_39);
        } catch (String v13_61) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v13_61);
            return 0;
        }
    }

    public java.net.URL generateSearchUrl(String p6)
    {
        String v1_0 = "";
        if (p6 != null) {
            try {
                if (!p6.isEmpty()) {
                    p6 = java.net.URLEncoder.encode(p6, "UTF-8");
                } else {
                }
            } catch (Object[] v6_12) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_12);
                return 0;
            }
        } else {
            p6 = "";
        }
        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111) {
                            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch) {
                                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails) {
                                        if (this.serverDescription.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                                            v1_0 = String.format("%s/posts?tags=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                                        }
                                    } else {
                                        v1_0 = String.format("%s/search?q=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                                    }
                                } else {
                                    v1_0 = String.format("%s/search?q=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                                }
                            } else {
                                v1_0 = String.format("%s/images/?q=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                            }
                        } else {
                            v1_0 = String.format("%s/index.php?page=post&s=list&tags=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                        }
                    } else {
                        v1_0 = String.format("%s/posts?utf8=%%E2%%9C%%93&commit=Go&tags=%s", new Object[] {this.serverDescription.getUrl(), p6}));
                    }
                } else {
                    v1_0 = String.format("%s/post/list/%s", new Object[] {this.serverDescription.getUrl(), p6}));
                }
            } else {
                v1_0 = String.format("%s/post?searchDefault=Search&tags=%s", new Object[] {this.serverDescription.getUrl(), p6}));
            }
        } else {
            v1_0 = String.format("%s/index.php?page=post&s=list&tags=%s", new Object[] {this.serverDescription.getUrl(), p6}));
        }
        try {
            return new java.net.URL(v1_0);
        } catch (Object[] v6_7) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_7);
            return 0;
        }
    }

    public java.net.URL generateUrlForAddFavorite(com.bisimplex.firebooru.danbooru.DanbooruPost p5)
    {
        if (p5 != null) {
            String v1_1 = this.serverDescription;
            if (v1_1 != null) {
                try {
                    String v1_0 = java.net.URLEncoder.encode(v1_1.getApiKey(), "utf-8");
                } catch (String v1_3) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_3);
                    v1_0 = "";
                }
                if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) || (!this.serverDescription.validateIsLoggedIn())) {
                    if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) || (!this.serverDescription.validateIsLoggedIn())) {
                        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) || (!this.serverDescription.validateIsLoggedIn())) {
                            if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) || (android.text.TextUtils.isEmpty(v1_0))) {
                                if ((this.serverDescription.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) && (!android.text.TextUtils.isEmpty(v1_0))) {
                                    return com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(String.format("%s/edit_ratings/set_rating", new Object[] {this.serverDescription.getUrl()})));
                                }
                            } else {
                                return com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(String.format("%s/images/%s/fave?key=%s", new Object[] {this.serverDescription.getUrl(), p5.getPostId(), v1_0})));
                            }
                        } else {
                            java.net.URL v5_2;
                            if (!p5.isFavorite()) {
                                v5_2 = String.format("%s/favorites/%s.json?login=%s&api_key=%s", new Object[] {this.serverDescription.getUrl(), p5.getPostId(), this.serverDescription.getUserName(), v1_0}));
                            } else {
                                v5_2 = String.format("%s/favorites.json?login=%s&api_key=%s", new Object[] {this.serverDescription.getUrl(), this.serverDescription.getUserName(), v1_0}));
                            }
                            return com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v5_2);
                        }
                    } else {
                        java.net.URL v5_6;
                        if (!p5.isFavorite()) {
                            v5_6 = String.format("%s/favorites/%s?login=%s&api_key=%s", new Object[] {this.serverDescription.getUrl(), p5.getPostId(), this.serverDescription.getUserName(), v1_0}));
                        } else {
                            v5_6 = String.format("%s/favorites?login=%s&api_key=%s&post_id=%s", new Object[] {this.serverDescription.getUrl(), this.serverDescription.getUserName(), v1_0, p5.getPostId()}));
                        }
                        return com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(v5_6);
                    }
                } else {
                    return com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(String.format("%s/post/vote.json", new Object[] {this.serverDescription.getUrl()})));
                }
            }
        }
        return 0;
    }

    public java.net.URL generateUrlForPoolsByText(String p5, int p6)
    {
        String v5_2;
        String v0_8 = this.encodeToUTF8(p5.replace("*", ""));
        if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
            if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                    if (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                        v5_2 = 0;
                    } else {
                        String v5_18;
                        if (!android.text.TextUtils.isEmpty(v0_8)) {
                            v5_18 = this.encodeToUTF8(String.format("title:%s*", new Object[] {p5})));
                        } else {
                            v5_18 = this.encodeToUTF8("title:*");
                        }
                        if (!this.serverDescription.validateIsLoggedIn()) {
                            v5_2 = String.format("%s/api/v1/json/search/galleries?q=%s&page=%d", new Object[] {this.serverDescription.getUrl(), v5_18, Integer.valueOf(p6)}));
                        } else {
                            v5_2 = String.format("%s/api/v1/json/search/galleries?key=%s&q=%s&page=%d", new Object[] {this.serverDescription.getUrl(), this.encodeToUTF8(this.serverDescription.getApiKey()), v5_18, Integer.valueOf(p6)}));
                        }
                    }
                } else {
                    if (!this.serverDescription.validateIsLoggedIn()) {
                        v5_2 = String.format("%s/pools.json?search[name_matches]=%s&search[order]=name&search[is_deleted]=false&page=%d", new Object[] {this.serverDescription.getUrl(), v0_8, Integer.valueOf(p6)}));
                    } else {
                        v5_2 = String.format("%s/pools.json?search[name_matches]=%s&search[order]=name&search[is_deleted]=false&page=%d&login=%s&api_key=%s", new Object[] {this.serverDescription.getUrl(), v0_8, Integer.valueOf(p6), this.encodeToUTF8(this.serverDescription.getUserName()), this.encodeToUTF8(this.serverDescription.getApiKey())}));
                    }
                }
            } else {
                if (!this.serverDescription.validateIsLoggedIn()) {
                    v5_2 = String.format("%s/pools.json?search[name_matches]=%s&search[sort]=name&commit=Search&page=%d", new Object[] {this.serverDescription.getUrl(), v0_8, Integer.valueOf(p6)}));
                } else {
                    v5_2 = String.format("%s/pools.json?search[name_matches]=%s&search[sort]=name&commit=Search&page=%d&login=%s&api_key=%s", new Object[] {this.serverDescription.getUrl(), v0_8, Integer.valueOf(p6), this.encodeToUTF8(this.serverDescription.getUserName()), this.encodeToUTF8(this.serverDescription.getApiKey())}));
                }
            }
        } else {
            v5_2 = String.format("%s/pool/index.json?query=%s&page=%d", new Object[] {this.serverDescription.getUrl(), v0_8, Integer.valueOf(p6)}));
        }
        try {
            return new java.net.URL(v5_2);
        } catch (String v5_15) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_15);
            return 0;
        }
    }

    public String getBestString()
    {
        return this.bestString;
    }

    public boolean getEnforceOriginalImage()
    {
        return this.enforceOriginalImage;
    }

    public String getHashKeyByUrl(String p3)
    {
        if (!java.util.regex.Pattern.compile("yukkuri.shii", 2).matcher(p3).find()) {
            if (!java.util.regex.Pattern.compile("konachan", 2).matcher(p3).find()) {
                if (!java.util.regex.Pattern.compile("sakugabooru", 2).matcher(p3).find()) {
                    return "choujin-steiner--your-password--";
                } else {
                    return "er@!$rjiajd0$!dkaopc350!Y%)--your-password--";
                }
            } else {
                return "So-I-Heard-You-Like-Mupkids-?--your-password--";
            }
        } else {
            return "masaorz-r5quenm--your-password--";
        }
    }

    public int getInitialPageNumber()
    {
        return this.initialPageNumber;
    }

    public String getPingServiceUrl()
    {
        return this.pingServiceUrl;
    }

    public String getPostFormat()
    {
        return this.postFormat;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServerDescription()
    {
        return this.serverDescription;
    }

    public boolean getShouldParseAsJson()
    {
        return this.shouldParseAsJson;
    }

    public String getSubpath()
    {
        if (this.subpath == null) {
            this.subpath = "";
        }
        return this.subpath;
    }

    public String getTagSeparator()
    {
        if ((this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) && (this.serverDescription.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru)) {
            return " ";
        } else {
            return ", ";
        }
    }

    public String getTopString()
    {
        return this.topString;
    }

    public String getUserAgent()
    {
        if (this.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
            return com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent();
        } else {
            return com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getBasicUserAgent();
        }
    }

    public boolean isBlacklisted(com.bisimplex.firebooru.danbooru.DanbooruPost p7)
    {
        int v0 = 0;
        if (p7 != null) {
            String v1_1 = p7.getTags();
            if (v1_1.length() != 0) {
                String v1_0 = String.format(" %s ", new Object[] {v1_1}));
                java.util.Iterator v2_1 = this.getBlackListRules().iterator();
                while (v2_1.hasNext()) {
                    com.bisimplex.firebooru.model.BlacklistRule v3_2 = ((com.bisimplex.firebooru.model.BlacklistRule) v2_1.next());
                    if (v3_2.checkRule(v1_0, p7.getRating(), p7.getAuthor())) {
                        v0 = 1;
                        p7.setBlacklisted(1);
                        p7.setBlacklistRule(v3_2.getRule());
                        break;
                    }
                }
                return v0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean isParseAsHTML()
    {
        return this.parseAsHTML;
    }

    public boolean isShouldFixURLs()
    {
        return this.shouldFixURLs;
    }

    public String metadataURLWithArray(java.util.List p7, int p8)
    {
        if ((p7 != null) && (!p7.isEmpty())) {
            int v0_1 = p7.size();
            String v1_1 = this.getServerDescription().getApiKey();
            String v2_1 = this.getServerDescription().getUrl();
            com.google.gson.JsonArray v3_1 = new com.google.gson.JsonArray(this.pageSize);
            int v4_1 = 0;
            while (v4_1 < this.pageSize) {
                Number v5_1 = (p8 + v4_1);
                if (v5_1 >= v0_1) {
                    break;
                }
                v3_1.add(((Number) p7.get(v5_1)));
                v4_1++;
            }
            return String.format("%s/get_files/file_metadata?detailed_url_information=true&Hydrus-Client-API-Access-Key=%s&file_ids=%s", new Object[] {v2_1, v1_1, this.encodeToUTF8(v3_1.toString())}));
        } else {
            return "";
        }
    }

    public void setBestString(String p1)
    {
        this.bestString = p1;
        return;
    }

    public void setEnforceOriginalImage(boolean p1)
    {
        this.enforceOriginalImage = p1;
        return;
    }

    public void setPingServiceUrl(String p1)
    {
        this.pingServiceUrl = p1;
        return;
    }

    public void setPostFormat(String p1)
    {
        this.postFormat = p1;
        return;
    }

    public void setServerDescription(com.bisimplex.firebooru.danbooru.ServerItem p13)
    {
        this.serverDescription = p13;
        if (p13 != 0) {
            this.shouldParseAsJson = 0;
            this.shouldParseAsRSS = 0;
            this.shouldFixURLs = 0;
            int v13_61 = p13.getRealURL();
            this.subdomain = v13_61.getHost();
            this.scheme = v13_61.getProtocol();
            this.subpath = v13_61.getPath();
            int v13_10 = this.subdomain;
            if (v13_10 != 0) {
                int v13_17 = v13_10.split(".");
                if (v13_17.length > 0) {
                    this.subdomain = v13_17[0];
                }
            }
            int v13_26 = this.serverDescription.getUrl();
            if (v13_26.endsWith("/")) {
                v13_26 = v13_26.substring(0, (v13_26.length() - 1));
            }
            switch (com.bisimplex.firebooru.danbooru.BooruProvider$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[this.serverDescription.getType().ordinal()]) {
                case 1:
                    this.shouldParseAsJson = 1;
                    if (!this.serverDescription.validateIsLoggedIn()) {
                        this.baseUrl = String.format("%s/post/index.%s?limit=%d&page=", new Object[] {v13_26, "json", Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/post/index.%s", new Object[] {v13_26, "json"}));
                    } else {
                        this.baseUrl = String.format("%s/post/index.%s?login=%s&password_hash=%s&limit=%d&page=", new Object[] {v13_26, "json", this.serverDescription.getUserName(), this.serverDescription.getPasswordKey(), Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/post/index.%s?login=%s&password_hash=%s", new Object[] {v13_26, "json", this.serverDescription.getUserName(), this.serverDescription.getPasswordKey()}));
                    }
                    this.requireFixPreview = v13_26.toLowerCase().contains("donmai.");
                    this.postFormat = "%s/post/show/%s/";
                    this.noteBaseUrl = "%s/note/index.json?post_id=%s";
                    this.topString = "order:score";
                    int v13_52 = java.util.Calendar.getInstance();
                    v13_52.add(5, -1);
                    this.bestString = String.format("date:>%s order:votes", new Object[] {new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(v13_52.getTime())}));
                    this.initialPageNumber = 1;
                    break;
                case 2:
                    String v1_51 = this.serverDescription.getApiKey();
                    if (v1_51 == null) {
                        v1_51 = "";
                    }
                    if ((!v13_26.toLowerCase().contains("gelbooru.com")) && ((!v13_26.toLowerCase().contains("realbooru.com")) && ((!v13_26.toLowerCase().contains("safebooru.org")) && (!v13_26.toLowerCase().contains("rule34.xxx"))))) {
                        String v3_15 = 0;
                    } else {
                        v3_15 = 1;
                    }
                    String v3_16;
                    this.shouldParseAsJson = v3_15;
                    if (v3_15 == null) {
                        v3_16 = "";
                    } else {
                        v3_16 = "json=1&";
                    }
                    this.baseUrl = String.format(java.util.Locale.US, "%s/index.php?%spage=dapi&s=post&q=index&limit=%d%s&pid=", new Object[] {v13_26, v3_16, Integer.valueOf(this.pageSize), v1_51}));
                    this.postFormat = "%s/index.php?page=post&s=view&id=%s";
                    this.noteBaseUrl = String.format(java.util.Locale.US, "%%s/index.php?page=dapi&s=note&q=index&post_id=%%s%s", new Object[] {v1_51}));
                    this.topString = "sort:score";
                    this.bestString = "sort:date score:>=5";
                    this.initialPageNumber = 0;
                    this.pingServiceUrl = String.format(java.util.Locale.US, "%s/index.php?%spage=dapi&s=post&q=index&limit=1%s", new Object[] {v13_26, v3_16, v1_51}));
                    this.shouldFixURLs = (this.shouldParseAsJson ^ 1);
                    break;
                case 3:
                    this.postFormat = "%s/post/view/%s";
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 1;
                    this.parseAsHTML = 1;
                    this.baseUrl = String.format("%s/post/list/", new Object[] {v13_26}));
                    this.pingServiceUrl = String.format("%s/post/list/1", new Object[] {v13_26}));
                    break;
                case 4:
                default:
                    break;
                case 5:
                    try {
                        java.util.Locale v0_37 = java.net.URLEncoder.encode(this.serverDescription.getApiKey(), "utf-8");
                    } catch (java.util.Locale v0_38) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_38);
                        v0_37 = "";
                    }
                    this.initialPageNumber = 1;
                    this.postFormat = "%s/posts/%s/";
                    this.topString = "order:score";
                    this.bestString = "";
                    this.shouldParseAsJson = 1;
                    this.poolPostFormart = "%s/pools/%s";
                    if (!this.serverDescription.validateIsLoggedIn()) {
                        this.baseUrl = String.format("%s/posts.json?limit=%d&page=", new Object[] {v13_26, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/posts.json?limit=1", new Object[] {v13_26}));
                        this.noteBaseUrl = String.format("%%s/notes.json?group_by=note&search[post_id]=%%s&page=%d", new Object[] {Integer.valueOf(this.initialPageNumber)}));
                    } else {
                        this.baseUrl = String.format("%s/posts.json?login=%s&api_key=%s&limit=%d&page=", new Object[] {v13_26, this.serverDescription.getUserName(), v0_37, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/posts.json?login=%s&api_key=%s&limit=1", new Object[] {v13_26, this.serverDescription.getUserName(), v0_37}));
                        this.noteBaseUrl = String.format("%%s/notes.json?group_by=note&search[post_id]=%%s&page=%d&login=%s&api_key=%s", new Object[] {Integer.valueOf(this.initialPageNumber), this.serverDescription.getUserName(), v0_37}));
                    }
                    break;
                case 6:
                    this.baseUrl = v13_26;
                    this.postFormat = "%s/index.php?page=post&s=view&id=%s";
                    if (this.serverDescription.getUrl().contains("rule.us")) {
                        this.postFormat = "%s/index.php?r=posts/index&q=";
                    }
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 0;
                    this.shouldParseAsJson = 1;
                    this.pingServiceUrl = new String(v13_26);
                    break;
                case 7:
                    this.shouldParseAsJson = 1;
                    java.util.Locale v0_27 = this.serverDescription.getApiKey();
                    if (android.text.TextUtils.isEmpty(v0_27)) {
                        this.baseUrl = String.format("%s/api/v1/images.json?limit=%d&page=", new Object[] {v13_26, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/api/v1/images.json?limit=1", new Object[] {v13_26}));
                    } else {
                        this.baseUrl = String.format("%s/api/v1/images.json?key=%s&limit=%d&page=", new Object[] {v13_26, v0_27, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/api/v1/images.json?key=%s&limit=1", new Object[] {v13_26, v0_27}));
                    }
                    this.postFormat = "%s/images/%s#image";
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 1;
                    this.shouldParseAsJson = 1;
                    break;
                case 8:
                    try {
                        java.util.Locale v0_21 = java.net.URLEncoder.encode(this.serverDescription.getApiKey(), "utf-8");
                    } catch (java.util.Locale v0_22) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_22);
                        v0_21 = "";
                    }
                    this.shouldParseAsJson = 1;
                    this.baseUrl = String.format("%s/api/v1/json/search/images?key=%s&per_page=50&page=", new Object[] {v13_26, v0_21}));
                    this.pingServiceUrl = String.format("%s/api/v1/json/images/featured", new Object[] {v13_26}));
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 1;
                    this.postFormat = "%s/%s";
                    break;
                case 9:
                    try {
                        java.util.Locale v0_14 = java.net.URLEncoder.encode(this.serverDescription.getApiKey(), "utf-8");
                    } catch (java.util.Locale v0_15) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_15);
                        v0_14 = "";
                    }
                    this.shouldParseAsJson = 1;
                    this.baseUrl = String.format("%s/api/v3/search/posts?key=%s&per_page=50&page=", new Object[] {v13_26, v0_14}));
                    this.pingServiceUrl = String.format("%s/api/v3/posts/featured", new Object[] {v13_26}));
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 1;
                    this.postFormat = "%s/%s";
                    break;
                case 10:
                    this.shouldParseAsJson = 1;
                    if (!this.serverDescription.validateIsLoggedIn()) {
                        this.baseUrl = String.format("%s/posts.json?v1=true&mode=extended&limit=%d&page=", new Object[] {v13_26, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/posts.json?v2=true&limit=1", new Object[] {v13_26}));
                        this.noteBaseUrl = String.format("%%s/notes.json?group_by=note&search[post_id]=%%s&page=%d", new Object[] {Integer.valueOf(this.initialPageNumber)}));
                    } else {
                        try {
                            java.util.Locale v0_9 = java.net.URLEncoder.encode(this.serverDescription.getApiKey(), "utf-8");
                        } catch (java.util.Locale v0_10) {
                            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_10);
                            v0_9 = "";
                        }
                        this.baseUrl = String.format("%s/posts.json?v1=true&mode=extended&login=%s&api_key=%s&limit=%d&page=", new Object[] {v13_26, this.serverDescription.getUserName(), v0_9, Integer.valueOf(this.pageSize)}));
                        this.pingServiceUrl = String.format("%s/posts.json?v2=true&login=%s&api_key=%s&limit=1", new Object[] {v13_26, this.serverDescription.getUserName(), v0_9}));
                        this.noteBaseUrl = String.format("%%s/notes.json?group_by=note&search[post_id]=%%s&page=%d&login=%s&api_key=%s", new Object[] {Integer.valueOf(this.initialPageNumber), this.serverDescription.getUserName(), v0_9}));
                    }
                    this.topString = "order:score";
                    this.bestString = "";
                    this.initialPageNumber = 1;
                    this.postFormat = "%s/posts/%s/";
                    this.poolPostFormart = "%s/pools/%s";
                    break;
                case 11:
                    try {
                        String v1_1 = java.net.URLEncoder.encode(this.serverDescription.getApiKey(), "utf-8");
                    } catch (String v1_0) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_0);
                        v1_1 = "";
                    }
                    this.shouldParseAsJson = 1;
                    this.baseUrl = String.format("%s/get_files/search_files?Hydrus-Client-API-Access-Key=%s", new Object[] {v13_26, v1_1}));
                    this.pingServiceUrl = String.format("%s/verify_access_key?Hydrus-Client-API-Access-Key=%s", new Object[] {v13_26, v1_1}));
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 0;
                    this.postFormat = "";
                    this.poolPostFormart = "";
                    break;
                case 12:
                    this.shouldParseAsJson = 1;
                    this.baseUrl = String.format("%s/api/legacy/query/entity?query=", new Object[] {v13_26}));
                    this.pingServiceUrl = String.format("%s/api/legacy/query/entity", new Object[] {v13_26}));
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 0;
                    this.postFormat = "%s/p/%s";
                    this.poolPostFormart = "";
                    break;
                case 13:
                    this.shouldParseAsJson = 1;
                    this.baseUrl = String.format("%s/api/", new Object[] {v13_26}));
                    this.pingServiceUrl = String.format("%s/api/v1/posts", new Object[] {v13_26}));
                    this.noteBaseUrl = "";
                    this.topString = "";
                    this.bestString = "";
                    this.initialPageNumber = 0;
                    this.postFormat = "";
                    this.poolPostFormart = "";
                    break;
            }
            this.updateBannedTags();
            if (this.serverDescription.isDefault()) {
                this.topString = "";
                this.bestString = "";
            }
        }
        return;
    }

    public void setTopString(String p1)
    {
        this.topString = p1;
        return;
    }

    public boolean shouldAvoidPolish()
    {
        return this.getServerDescription().getUrl().contains("rule34.xxx");
    }

    public void updateBannedTags()
    {
        this._blackListRules = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getBlacklistRules(this.serverDescription);
        return;
    }
}
