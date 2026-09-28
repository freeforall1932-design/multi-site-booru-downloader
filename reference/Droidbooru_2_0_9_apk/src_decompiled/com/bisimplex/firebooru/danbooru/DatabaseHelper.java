package com.bisimplex.firebooru.danbooru;
public class DatabaseHelper {
    private static final int FAVORITE_PAGE_SIZE = 100;
    private static com.bisimplex.firebooru.danbooru.DatabaseHelper sharedInstance;

    static DatabaseHelper()
    {
        return;
    }

    protected DatabaseHelper()
    {
        return;
    }

    private String buildTagText(String p4, java.util.List p5)
    {
        if ((p5 != null) && (!p5.isEmpty())) {
            java.util.Locale v0_6 = new StringBuilder();
            String v5_3 = p5.iterator();
            while (v5_3.hasNext()) {
                String v1_3 = ((com.bisimplex.firebooru.danbooru.ServerItem) v5_3.next());
                if ((!v1_3.isDefault()) || (v1_3.getServerId() > 0)) {
                    v0_6.append(v1_3.getUrl());
                    v0_6.append(",");
                }
            }
            String v5_1 = v0_6.toString();
            if (!android.text.TextUtils.isEmpty(v5_1)) {
                v5_1 = v5_1.substring(0, (v5_1.length() - 1));
            }
            p4 = String.format(java.util.Locale.US, "%s %s%s", new Object[] {p4, "site:", v5_1}));
        }
        return p4;
    }

    private void copyPostToFavorite(com.bisimplex.firebooru.danbooru.DanbooruPost p3, com.bisimplex.firebooru.model.Favorite p4)
    {
        if ((p3 != null) && (p4 != null)) {
            p4.has_children = p3.getHas_children();
            p4.has_comments = p3.getHas_comments();
            p4.has_notes = p3.getHas_notes();
            java.util.Date v0_0 = p3.getFile();
            if (v0_0 != null) {
                p4.file_url = v0_0.getUrl();
                p4.height = v0_0.getHeight();
                p4.width = v0_0.getWidth();
            }
            java.util.Date v0_2 = p3.getJpeg();
            if (v0_2 != null) {
                p4.jpeg_url = v0_2.getUrl();
                p4.jpeg_height = v0_2.getHeight();
                p4.jpeg_width = v0_2.getWidth();
            }
            java.util.Date v0_5 = p3.getSample();
            if (v0_5 != null) {
                p4.sample_url = v0_5.getUrl();
                p4.sample_height = v0_5.getHeight();
                p4.sample_width = v0_5.getWidth();
            }
            java.util.Date v0_7 = p3.getPreview();
            if (v0_7 != null) {
                p4.preview_url = v0_7.getUrl();
                p4.preview_height = v0_7.getHeight();
                p4.preview_width = v0_7.getWidth();
            }
            p4.md5 = p3.getMd5();
            p4.parent_id = p3.getParent_id();
            p4.postid = p3.getPostId();
            p4.posturl = p3.getPostUrl();
            p4.rating = p3.getRating();
            p4.score = p3.getScore();
            p4.source = p3.getSource();
            p4.tags = p3.getTags();
            p4.tag_string_artist = p3.getTag_artist();
            p4.tag_string_character = p3.getTag_character();
            p4.tag_string_copyright = p3.getTag_copyright();
            p4.tag_string_general = p3.getTag_general_meta();
            if (p3.getDateAdded() != null) {
                p4.dateAdded = p3.getDateAdded();
            }
        }
        return;
    }

    private void deleteOldHistoryItems()
    {
        com.activeandroid.query.From v0_12 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory);
        com.activeandroid.query.From v1_8 = Integer.valueOf(0);
        if (v0_12.where("isFavoritedHistoryItem = ?", new Object[] {v1_8})).count() > 1000) {
            new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.TagHistory).where("id = ?", new Object[] {((com.bisimplex.firebooru.model.TagHistory) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("isFavoritedHistoryItem = ?", new Object[] {v1_8})).orderBy("searchDate ASC").limit(1).executeSingle()).getId()})).execute();
        }
        return;
    }

    public static com.bisimplex.firebooru.danbooru.DatabaseHelper getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.DatabaseHelper.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.sharedInstance = new com.bisimplex.firebooru.danbooru.DatabaseHelper();
        }
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.sharedInstance;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost FavoriteToPost(com.bisimplex.firebooru.model.Favorite p7)
    {
        if (p7 != null) {
            String v0_16;
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            v1_1.setTags(p7.tags);
            int v2_1 = 1;
            v1_1.setImageIsVisible(1);
            v1_1.setPostIdAndUrl(p7.postid, 0, 0);
            v1_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.jpeg_url, p7.jpeg_width, p7.jpeg_height));
            v1_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.sample_url, p7.sample_width, p7.sample_height));
            v1_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.file_url, p7.width, p7.height));
            v1_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.preview_url, p7.preview_width, p7.preview_height));
            v1_1.setFavorite(1);
            v1_1.setForcedPostUrl(p7.posturl);
            v1_1.setParent_id(p7.parent_id);
            v1_1.setScore(p7.score);
            v1_1.setRating(p7.rating);
            v1_1.setMd5(p7.md5);
            v1_1.setSource(p7.source);
            if (p7.has_notes != 1) {
                v0_16 = 0;
            } else {
                v0_16 = 1;
            }
            String v0_18;
            v1_1.setHas_notes(v0_16);
            if (p7.has_comments != 1) {
                v0_18 = 0;
            } else {
                v0_18 = 1;
            }
            v1_1.setHas_comments(v0_18);
            if (p7.has_children != 1) {
                v2_1 = 0;
            }
            v1_1.setHas_children(v2_1);
            v1_1.setTag_artist(p7.tag_string_artist);
            v1_1.setTag_general(p7.tag_string_general);
            v1_1.setTag_copyright(p7.tag_string_copyright);
            v1_1.setTag_character(p7.tag_string_character);
            v1_1.setDateAdded(p7.dateAdded);
            return v1_1;
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost PostHistoryToPost(com.bisimplex.firebooru.model.Post p7)
    {
        if (p7 != null) {
            String v0_18;
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            v1_1.setTags(p7.tags);
            int v2_1 = 1;
            v1_1.setImageIsVisible(1);
            v1_1.setPostIdAndUrl(p7.postid, 0, 0);
            v1_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.jpeg_url, p7.jpeg_width, p7.jpeg_height));
            v1_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.sample_url, p7.sample_width, p7.sample_height));
            v1_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.file_url, p7.width, p7.height));
            v1_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(p7.preview_url, p7.preview_width, p7.preview_height));
            v1_1.setFavorite(this.getIsFavByPost(p7.postid, p7.md5));
            v1_1.setForcedPostUrl(p7.posturl);
            v1_1.setParent_id(p7.parent_id);
            v1_1.setScore(p7.score);
            v1_1.setRating(p7.rating);
            v1_1.setMd5(p7.md5);
            v1_1.setSource(p7.source);
            if (p7.has_notes != 1) {
                v0_18 = 0;
            } else {
                v0_18 = 1;
            }
            String v0_20;
            v1_1.setHas_notes(v0_18);
            if (p7.has_comments != 1) {
                v0_20 = 0;
            } else {
                v0_20 = 1;
            }
            v1_1.setHas_comments(v0_20);
            if (p7.has_children != 1) {
                v2_1 = 0;
            }
            v1_1.setHas_children(v2_1);
            v1_1.setTag_artist(p7.tag_string_artist);
            v1_1.setTag_general(p7.tag_string_general);
            v1_1.setTag_copyright(p7.tag_string_copyright);
            v1_1.setTag_character(p7.tag_string_character);
            return v1_1;
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem ServerToServerItem(com.bisimplex.firebooru.model.Server p7)
    {
        if (p7 != null) {
            com.bisimplex.firebooru.danbooru.ServerItemType v1_0;
            com.bisimplex.firebooru.danbooru.ServerItem v0_1 = new com.bisimplex.firebooru.danbooru.ServerItem();
            v0_1.setApiKey(p7.apikey);
            int v3 = 1;
            if (p7.isDefault != 1) {
                v1_0 = 0;
            } else {
                v1_0 = 1;
            }
            com.bisimplex.firebooru.danbooru.ServerItemType v1_4;
            v0_1.setDefault(v1_0);
            v0_1.setPassword(p7.password);
            v0_1.setPasswordKey(p7.passwordKey);
            if (p7.ratingFilterEnabled != 1) {
                v1_4 = 0;
            } else {
                v1_4 = 1;
            }
            com.bisimplex.firebooru.danbooru.ServerItemType v1_6;
            v0_1.setRatingFilterEnabled(v1_4);
            if (p7.isSelected != 1) {
                v1_6 = 0;
            } else {
                v1_6 = 1;
            }
            v0_1.setSelected(v1_6);
            v0_1.setServerId(p7.getId().longValue());
            v0_1.setServerName(p7.serverName);
            v0_1.setType(com.bisimplex.firebooru.danbooru.ServerItemType.fromInteger(p7.isGelbooru));
            v0_1.setUrl(p7.url);
            if (p7.useNativeAutocomplete != 1) {
                v3 = 0;
            }
            v0_1.setUseNativeAutocomplete(v3);
            v0_1.setUserName(p7.userName);
            if (v0_1.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                v0_1.setRatingFilterEnabled(0);
            }
            return v0_1;
        } else {
            return 0;
        }
    }

    public void addBannedTag(String p1, java.util.List p2)
    {
        this.addBannedTag(this.buildTagText(p1, p2));
        return;
    }

    public boolean addBannedTag(String p5)
    {
        if (p5 != 0) {
            int v5_2 = p5.trim();
            if (v5_2.length() != 0) {
                if (((com.bisimplex.firebooru.model.BannedTag) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.BannedTag).where("tagText = ?", new Object[] {v5_2})).executeSingle()) == null) {
                    new com.bisimplex.firebooru.model.BannedTag(v5_2).save();
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void addFavoriteItem(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if ((p3 != null) && (!p3.isDisableStorage())) {
            if (this.getFavoriteItemFromPost(p3) == null) {
                com.bisimplex.firebooru.model.Favorite v0_3 = new com.bisimplex.firebooru.model.Favorite();
                this.copyPostToFavorite(p3, v0_3);
                p3.setFavorite(1);
                v0_3.save();
                return;
            } else {
                p3.setFavorite(1);
                return;
            }
        } else {
            return;
        }
    }

    public void addFavoriteItemNoCheck(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if ((p2 != null) && (!p2.isDisableStorage())) {
            p2.setFavorite(1);
            com.bisimplex.firebooru.model.Favorite v0_3 = new com.bisimplex.firebooru.model.Favorite();
            this.copyPostToFavorite(p2, v0_3);
            v0_3.save();
        }
        return;
    }

    public void addHistoryItem(String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            com.bisimplex.firebooru.model.TagHistory v0_3 = ((com.bisimplex.firebooru.model.TagHistory) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("search = ?", new Object[] {p4})).executeSingle());
            if (v0_3 == null) {
                com.bisimplex.firebooru.model.TagHistory v0_5 = new com.bisimplex.firebooru.model.TagHistory();
                v0_5.search = p4;
                v0_5.lowercaseSearch = p4.toLowerCase(java.util.Locale.ENGLISH);
                v0_5.save();
                this.deleteOldHistoryItems();
                return;
            } else {
                v0_3.searchDate = new java.util.Date();
                v0_3.save();
                return;
            }
        } else {
            return;
        }
    }

    public void addHistoryItem(String p4, java.util.Date p5, boolean p6)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            com.bisimplex.firebooru.model.TagHistory v0_3 = ((com.bisimplex.firebooru.model.TagHistory) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("search = ?", new Object[] {p4})).executeSingle());
            if (v0_3 == null) {
                com.bisimplex.firebooru.model.TagHistory v0_5 = new com.bisimplex.firebooru.model.TagHistory();
                v0_5.search = p4;
                v0_5.lowercaseSearch = p4.toLowerCase(java.util.Locale.ENGLISH);
                v0_5.searchDate = p5;
                v0_5.isFavoritedHistoryItem = p6;
                v0_5.save();
                if (!p6) {
                    this.deleteOldHistoryItems();
                }
            } else {
                v0_3.isFavoritedHistoryItem = p6;
                v0_3.save();
                return;
            }
        }
        return;
    }

    public void addPoolFavorite(com.bisimplex.firebooru.model.PoolFavorite p4)
    {
        if (p4 != null) {
            if (p4.poolUrl != null) {
                p4.poolUrl = p4.poolUrl.toLowerCase(java.util.Locale.ENGLISH);
            }
            if (((com.bisimplex.firebooru.model.PoolFavorite) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.PoolFavorite).where("poolUrl = ?", new Object[] {p4.poolUrl})).executeSingle()) == null) {
                p4.server = this.getServerById(this.getSelectedServer().getServerId());
                p4.save();
                return;
            }
        }
        return;
    }

    public void addPostHistoryItem(com.bisimplex.firebooru.danbooru.DanbooruPost p6)
    {
        if ((p6 != null) && (!p6.isDisableStorage())) {
            com.bisimplex.firebooru.model.Post v0_14 = p6.getPostId();
            int v1_1 = p6.getMd5();
            if ((!android.text.TextUtils.isEmpty(v0_14)) && (!android.text.TextUtils.isEmpty(v1_1))) {
                if (((com.bisimplex.firebooru.model.Post) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Post).where("postid=? and md5=?", new Object[] {v0_14, v1_1})).executeSingle()) == null) {
                    String v2_21 = new String[1];
                    v2_21[0] = "*";
                    if (new com.activeandroid.query.Select(v2_21).from(com.bisimplex.firebooru.model.Post).count() > 100) {
                        com.bisimplex.firebooru.model.Post v0_18 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Post).orderBy("Id desc").limit(String.valueOf(100)).offset(String.valueOf(100)).execute().iterator();
                        while (v0_18.hasNext()) {
                            ((com.bisimplex.firebooru.model.Post) v0_18.next()).delete();
                        }
                    }
                    com.bisimplex.firebooru.model.Post v0_20 = new com.bisimplex.firebooru.model.Post();
                    v0_20.has_children = p6.getHas_children();
                    v0_20.has_comments = p6.getHas_comments();
                    v0_20.has_notes = p6.getHas_notes();
                    String v2_29 = p6.getFile();
                    if (v2_29 != null) {
                        v0_20.file_url = v2_29.getUrl();
                        v0_20.height = v2_29.getHeight();
                        v0_20.width = v2_29.getWidth();
                    }
                    String v2_31 = p6.getJpeg();
                    if (v2_31 != null) {
                        v0_20.jpeg_url = v2_31.getUrl();
                        v0_20.jpeg_height = v2_31.getHeight();
                        v0_20.jpeg_width = v2_31.getWidth();
                    }
                    String v2_0 = p6.getSample();
                    if (v2_0 != null) {
                        v0_20.sample_url = v2_0.getUrl();
                        v0_20.sample_height = v2_0.getHeight();
                        v0_20.sample_width = v2_0.getWidth();
                    }
                    String v2_2 = p6.getPreview();
                    if (v2_2 != null) {
                        v0_20.preview_url = v2_2.getUrl();
                        v0_20.preview_height = v2_2.getHeight();
                        v0_20.preview_width = v2_2.getWidth();
                    }
                    v0_20.md5 = p6.getMd5();
                    v0_20.parent_id = p6.getParent_id();
                    v0_20.postid = p6.getPostId();
                    v0_20.posturl = p6.getPostUrl();
                    v0_20.rating = p6.getRating();
                    v0_20.score = p6.getScore();
                    v0_20.source = p6.getSource();
                    v0_20.tags = p6.getTags();
                    v0_20.tag_string_artist = p6.getTag_artist();
                    v0_20.tag_string_character = p6.getTag_character();
                    v0_20.tag_string_copyright = p6.getTag_copyright();
                    v0_20.tag_string_general = p6.getTag_general_meta();
                    v0_20.isHistory = 1;
                    v0_20.save();
                } else {
                    android.util.Log.i("Gelbooru", "Avoid addPostHistoryItem duplicated");
                    return;
                }
            }
        }
        return;
    }

    public Long addServer(com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        com.bisimplex.firebooru.model.Server v0_3;
        if (p4.getServerId() <= 0) {
            v0_3 = new com.bisimplex.firebooru.model.Server();
        } else {
            v0_3 = ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("Id = ?", new Object[] {Integer.valueOf(p4.getServerId())})).executeSingle());
        }
        v0_3.apikey = p4.getApiKey();
        v0_3.isDefault = 0;
        v0_3.isGelbooru = p4.getType().getValue();
        v0_3.isSelected = p4.isSelected();
        v0_3.isDefault = p4.isDefault();
        v0_3.password = p4.getPassword();
        v0_3.passwordKey = p4.getPasswordKey();
        v0_3.ratingFilterEnabled = p4.isRatingFilterEnabled();
        v0_3.serverName = p4.getServerName();
        v0_3.url = p4.getUrl();
        v0_3.useNativeAutocomplete = p4.isUseNativeAutocomplete();
        v0_3.userName = p4.getUserName();
        return v0_3.save();
    }

    public void deleteAllFavorites()
    {
        new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.Favorite).execute();
        return;
    }

    public void deleteAllHistory()
    {
        new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.TagHistory).execute();
        return;
    }

    public void deleteAllPostHistory()
    {
        new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.Post).execute();
        return;
    }

    public void deleteBannedTagById(long p2)
    {
        com.bisimplex.firebooru.model.BannedTag.delete(com.bisimplex.firebooru.model.BannedTag, p2);
        return;
    }

    public void deleteHistoryItemById(Long p3)
    {
        new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.TagHistory).where("Id = ?", new Object[] {p3})).execute();
        return;
    }

    public void deleteNonStarredHistory()
    {
        new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.TagHistory).where("isFavoritedHistoryItem = ?", new Object[] {Integer.valueOf(0)})).execute();
        return;
    }

    public void deletePoolFavoriteById(int p4)
    {
        com.bisimplex.firebooru.model.PoolFavorite.delete(com.bisimplex.firebooru.model.PoolFavorite, ((long) p4));
        return;
    }

    public boolean deleteServerById(int p5)
    {
        Object[] v1_0 = 0;
        if (this.getDefaultServer().getServerId() != p5) {
            if (this.getSelectedServer().getServerId() == p5) {
                v1_0 = 1;
            }
            com.activeandroid.ActiveAndroid.beginTransaction();
            try {
                new com.activeandroid.query.Delete().from(com.bisimplex.firebooru.model.Server).where("Id = ?", new Object[] {Integer.valueOf(p5)})).execute();
            } catch (com.bisimplex.firebooru.model.Server v5_10) {
                com.activeandroid.ActiveAndroid.endTransaction();
                throw v5_10;
            }
            if (v1_0 != null) {
                com.bisimplex.firebooru.model.Server v5_9 = ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isDefault = ?", new Object[] {Integer.valueOf(1)})).executeSingle());
                v5_9.isSelected = 1;
                v5_9.save();
            }
            com.activeandroid.ActiveAndroid.setTransactionSuccessful();
            com.activeandroid.ActiveAndroid.endTransaction();
            return 1;
        } else {
            return 0;
        }
    }

    public void dropPoolFavorite()
    {
        return;
    }

    public String generateBannedString()
    {
        String v0_0 = this.loadBannedTags();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        String v0_11 = v0_0.iterator();
        while (v0_11.hasNext()) {
            v1_1.add(((com.bisimplex.firebooru.model.BannedTag) v0_11.next()).tagText);
        }
        String v0_1 = v1_1.size();
        if (v0_1 != null) {
            if (v0_1 != 1) {
                return String.format("\\b%s\\b", new Object[] {android.text.TextUtils.join("\\b|\\b", v1_1)}));
            } else {
                return String.format("\\b%s\\b", new Object[] {v1_1.get(0)}));
            }
        } else {
            return "";
        }
    }

    public java.util.List getBlacklistRules()
    {
        java.util.Iterator v0_0 = this.loadBannedTags();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v0_1 = v0_0.iterator();
        while (v0_1.hasNext()) {
            Long v2_1 = ((com.bisimplex.firebooru.model.BannedTag) v0_1.next());
            v1_1.add(new com.bisimplex.firebooru.model.BlacklistRule(v2_1.tagText, v2_1.getId().longValue()));
        }
        return v1_1;
    }

    public java.util.List getBlacklistRules(com.bisimplex.firebooru.danbooru.ServerItem p8)
    {
        java.util.Iterator v0_0 = this.loadBannedTags();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v0_1 = v0_0.iterator();
        while (v0_1.hasNext()) {
            Long v2_1 = ((com.bisimplex.firebooru.model.BannedTag) v0_1.next());
            String v3_0 = v2_1.tagText;
            if (v3_0 != null) {
                String v3_1 = v3_0.trim();
                if (!android.text.TextUtils.isEmpty(v3_1)) {
                    if (!v3_1.contains("site:")) {
                        v1_1.add(new com.bisimplex.firebooru.model.BlacklistRule(v3_1, ((long) v2_1.bannedTagid)));
                    } else {
                        if (v3_1.contains(p8.getUrl())) {
                            v1_1.add(new com.bisimplex.firebooru.model.BlacklistRule(v3_1, v2_1.getId().longValue()));
                        }
                    }
                }
            }
        }
        return v1_1;
    }

    public java.util.List getBlacklistRules(com.bisimplex.firebooru.network.SourceQuery p7)
    {
        if (p7 != null) {
            java.util.Iterator v7_1 = this.loadBannedTags(p7);
            java.util.ArrayList v0_1 = new java.util.ArrayList();
            java.util.Iterator v7_3 = v7_1.iterator();
            while (v7_3.hasNext()) {
                Long v1_1 = ((com.bisimplex.firebooru.model.BannedTag) v7_3.next());
                v0_1.add(new com.bisimplex.firebooru.model.BlacklistRule(v1_1.tagText, v1_1.getId().longValue()));
            }
            return v0_1;
        } else {
            return this.getBlacklistRules();
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getDefaultServer()
    {
        com.bisimplex.firebooru.danbooru.ServerItem v0_4 = ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isDefault = ?", new Object[] {Integer.valueOf(1)})).executeSingle());
        com.bisimplex.firebooru.danbooru.ServerItem v2_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getDefaultServerSettings();
        if (v0_4 != null) {
            if ((v0_4.isGelbooru != v2_1.getType().getValue()) || (!v0_4.url.equalsIgnoreCase(v2_1.getUrl()))) {
                v0_4.serverName = v2_1.getServerName();
                v0_4.url = v2_1.getUrl();
                v0_4.isGelbooru = v2_1.getType().getValue();
                v0_4.save();
            }
        } else {
            v0_4 = new com.bisimplex.firebooru.model.Server();
            v0_4.apikey = "";
            v0_4.isDefault = 1;
            v0_4.isGelbooru = v2_1.getType().getValue();
            v0_4.isSelected = 1;
            v0_4.password = "";
            v0_4.passwordKey = "";
            v0_4.ratingFilterEnabled = 1;
            v0_4.serverName = v2_1.getServerName();
            v0_4.url = v2_1.getUrl();
            v0_4.useNativeAutocomplete = 0;
            v0_4.userName = "";
            v0_4.save();
        }
        return this.ServerToServerItem(v0_4);
    }

    public com.bisimplex.firebooru.model.Favorite getFavoriteByID(long p3)
    {
        return ((com.bisimplex.firebooru.model.Favorite) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite).where("Id = ?", new Object[] {Long.valueOf(p3)})).executeSingle());
    }

    public com.bisimplex.firebooru.model.Favorite getFavoriteItemFromPost(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if (p3 != null) {
            if (!android.text.TextUtils.isEmpty(p3.getPostId())) {
                com.activeandroid.query.From v0_1 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite);
                if (!android.text.TextUtils.isEmpty(p3.getMd5())) {
                    return ((com.bisimplex.firebooru.model.Favorite) v0_1.where("postid=? and md5=?", new Object[] {p3.getPostId(), p3.getMd5()})).executeSingle());
                } else {
                    return ((com.bisimplex.firebooru.model.Favorite) v0_1.where("postid=?", new Object[] {p3.getPostId()})).executeSingle());
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean getIsFavByPost(String p6, String p7)
    {
        if ((!android.text.TextUtils.isEmpty(p6)) && (!android.text.TextUtils.isEmpty(p7))) {
            long v3_3 = new String[1];
            v3_3[0] = "Id";
            long v6_5 = ((com.bisimplex.firebooru.model.Favorite) new com.activeandroid.query.Select(v3_3).from(com.bisimplex.firebooru.model.Favorite).where("postid = ? and md5 = ?", new Object[] {p6, p7})).limit("1").executeSingle());
            if ((v6_5 != 0) && (v6_5.getId().longValue() != 0)) {
                return 1;
            }
        }
        return 0;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getSelectedServer()
    {
        com.bisimplex.firebooru.danbooru.ServerItem v0_2 = ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isSelected = 1").executeSingle());
        if (v0_2 != null) {
            if (v0_2.isDefault != 1) {
                return this.ServerToServerItem(v0_2);
            } else {
                return this.getDefaultServer();
            }
        } else {
            return this.getDefaultServer();
        }
    }

    public com.bisimplex.firebooru.model.Server getServerById(int p3)
    {
        return ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("Id = ?", new Object[] {Integer.valueOf(p3)})).executeSingle());
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServerByNamePart(String p4, String p5, boolean p6)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            com.bisimplex.firebooru.danbooru.ServerItem v4_2 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("url like ?", new Object[] {String.format("%%%s%%", new Object[] {p4}))}));
            if (p6 != null) {
                v4_2 = v4_2.and("isDefault != 1");
            }
            if (!android.text.TextUtils.isEmpty(p5)) {
                v4_2 = v4_2.and("userName = ?", new Object[] {p5}));
            }
            com.bisimplex.firebooru.danbooru.ServerItem v4_4 = ((com.bisimplex.firebooru.model.Server) v4_2.executeSingle());
            if (v4_4 == null) {
                return 0;
            } else {
                return this.ServerToServerItem(v4_4);
            }
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServerByUrl(java.net.URL p4)
    {
        if (p4 != null) {
            com.bisimplex.firebooru.danbooru.ServerItem v4_5 = ((com.bisimplex.firebooru.model.Server) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("url like ?", new Object[] {String.format("%%%s%%", new Object[] {p4.getHost()}))})).orderBy("isDefault asc").executeSingle());
            if (v4_5 == null) {
                return 0;
            } else {
                return this.ServerToServerItem(v4_5);
            }
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServerByUrl(java.net.URL p4, boolean p5)
    {
        if (p4 != null) {
            com.bisimplex.firebooru.danbooru.ServerItem v4_2 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("url like ?", new Object[] {String.format("%%%s%%", new Object[] {p4.getHost()}))}));
            if (p5 != null) {
                v4_2 = v4_2.and("isDefault != 1");
            }
            com.bisimplex.firebooru.danbooru.ServerItem v4_4 = ((com.bisimplex.firebooru.model.Server) v4_2.executeSingle());
            if (v4_4 == null) {
                return 0;
            } else {
                return this.ServerToServerItem(v4_4);
            }
        } else {
            return 0;
        }
    }

    public int getServerCount()
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).execute().size();
    }

    public int getTagBlackListCount()
    {
        return this.loadBannedTags().size();
    }

    public boolean hasHydrusServers()
    {
        if (new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isGelbooru = ?", new Object[] {Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus.getValue())})).count() <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isMaxFreeServerReached()
    {
        return 0;
    }

    public boolean isMaxFreeTagReached()
    {
        return 0;
    }

    public com.bisimplex.firebooru.model.BannedTag loadBannedTag(long p3)
    {
        return ((com.bisimplex.firebooru.model.BannedTag) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.BannedTag).where("id = ?", new Object[] {Long.valueOf(p3)})).executeSingle());
    }

    public java.util.List loadBannedTags()
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.BannedTag).execute();
    }

    public java.util.List loadBannedTags(com.bisimplex.firebooru.network.SourceQuery p10)
    {
        if (p10 != null) {
            com.activeandroid.query.From v0_2 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.BannedTag);
            java.util.ArrayList v1_2 = new java.util.ArrayList(2);
            if (!android.text.TextUtils.isEmpty(p10.getText())) {
                int v2_3 = p10.getText().split(" ");
                String v5_1 = v2_3.length;
                int v6 = 0;
                while (v6 < v5_1) {
                    String v7_0 = v2_3[v6];
                    if (!android.text.TextUtils.isEmpty(v7_0)) {
                        v1_2.add(String.format("%%%s%%", new Object[] {v7_0})));
                    }
                    v6++;
                }
            }
            if (p10.getExtraParams().containsKey("SERVER_URL")) {
                java.util.List v10_3 = ((String) p10.getExtraParams().get("SERVER_URL"));
                if (!android.text.TextUtils.isEmpty(v10_3)) {
                    v1_2.add(String.format("%%%s%%", new Object[] {v10_3})));
                }
            }
            java.util.List v10_6 = v1_2.size();
            if (v10_6 != null) {
                v0_2.where("tagText like ?", new Object[] {v1_2.get(0)}));
                int v2_9 = 1;
                while (v2_9 < v10_6) {
                    v0_2.and("tagText like ?", new Object[] {v1_2.get(v2_9)}));
                    v2_9++;
                }
                return v0_2.execute();
            } else {
                return this.loadBannedTags();
            }
        } else {
            return this.loadBannedTags();
        }
    }

    public java.util.List loadFavorites()
    {
        java.util.Iterator v0_5 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite).orderBy("Id desc").execute();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v0_2 = v0_5.iterator();
        while (v0_2.hasNext()) {
            v1_1.add(this.FavoriteToPost(((com.bisimplex.firebooru.model.Favorite) v0_2.next())));
        }
        return v1_1;
    }

    public java.util.List loadFavorites(int p3)
    {
        java.util.Iterator v3_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite).orderBy("Id desc").limit(100).offset((p3 * 100)).execute();
        java.util.ArrayList v0_4 = new java.util.ArrayList();
        java.util.Iterator v3_4 = v3_3.iterator();
        while (v3_4.hasNext()) {
            v0_4.add(this.FavoriteToPost(((com.bisimplex.firebooru.model.Favorite) v3_4.next())));
        }
        return v0_4;
    }

    public java.util.List loadFavoritesByFilter(com.bisimplex.firebooru.network.SourceQuery p2, int p3)
    {
        java.util.Iterator v2_1 = this.loadRawFavoritesByFilter(p2, p3);
        java.util.ArrayList v3_2 = new java.util.ArrayList();
        java.util.Iterator v2_2 = v2_1.iterator();
        while (v2_2.hasNext()) {
            v3_2.add(this.FavoriteToPost(((com.bisimplex.firebooru.model.Favorite) v2_2.next())));
        }
        return v3_2;
    }

    public java.util.List loadHistory()
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).orderBy("isFavoritedHistoryItem DESC, searchDate DESC").execute();
    }

    public java.util.List loadHistory(String p3)
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("search like ?", new Object[] {String.format("%%%s%%", new Object[] {p3}))})).orderBy("isFavoritedHistoryItem DESC, searchDate DESC").execute();
    }

    public java.util.List loadHistoryStarred(boolean p3)
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("isFavoritedHistoryItem = ?", new Object[] {Integer.valueOf(p3)})).orderBy("searchDate DESC").execute();
    }

    public java.util.List loadPoolsFavorite()
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.PoolFavorite).execute();
    }

    public java.util.List loadPostHistory()
    {
        java.util.Iterator v0_5 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Post).orderBy("Id desc").execute();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v0_2 = v0_5.iterator();
        while (v0_2.hasNext()) {
            v1_1.add(this.PostHistoryToPost(((com.bisimplex.firebooru.model.Post) v0_2.next())));
        }
        return v1_1;
    }

    public java.util.List loadRawFavorites()
    {
        return new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite).orderBy("Id desc").execute();
    }

    public java.util.List loadRawFavoritesByFilter(com.bisimplex.firebooru.network.SourceQuery p16, int p17)
    {
        String v0_0 = p16.getExtraParams();
        if (v0_0 == null) {
            v0_0 = new java.util.HashMap(0);
        }
        int v3_0;
        int v2_4 = p16.getText();
        if (!v0_0.containsKey("FILTER_SERVER_URL")) {
            v3_0 = "";
        } else {
            v3_0 = ((String) v0_0.get("FILTER_SERVER_URL"));
        }
        com.bisimplex.firebooru.danbooru.FavoriteSortType v4_1 = com.bisimplex.firebooru.danbooru.FavoriteSortType.Date;
        if (v0_0.containsKey("FILTER_SORT_ID")) {
            v4_1 = com.bisimplex.firebooru.danbooru.FavoriteSortType.fromInteger(Integer.parseInt(((String) v0_0.get("FILTER_SORT_ID"))));
        }
        int v2_1;
        Object[] v5_7 = ((String) v0_0.get("FILTER_EXT"));
        String v6_3 = ((String) v0_0.get("FILTER_RATING"));
        String v7_2 = ((String) v0_0.get("FILTER_SOURCE"));
        if (!android.text.TextUtils.isEmpty(v2_4)) {
            v2_1 = v2_4.trim().split(" ");
        } else {
            v2_1 = new String[0];
        }
        com.activeandroid.query.From v8_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Favorite);
        if (v2_1.length > 0) {
            Object[] v10_6 = v2_1.length;
            String v12_1 = 0;
            while (v12_1 < v10_6) {
                v8_3.where("tags like ?", new Object[] {String.format("%%%s%%", new Object[] {v2_1[v12_1]}))}));
                v12_1++;
            }
        }
        String v0_24;
        String v0_22 = ((String) v0_0.get("FILTER_EXCLUDE_TAGS"));
        if (!android.text.TextUtils.isEmpty(v0_22)) {
            v0_24 = v0_22.trim().split(" ");
        } else {
            v0_24 = new String[0];
        }
        if (v0_24.length > 0) {
            int v2_6 = v0_24.length;
            String v9_1 = 0;
            while (v9_1 < v2_6) {
                v8_3.where("tags not like ?", new Object[] {String.format("%%%s%%", new Object[] {v0_24[v9_1]}))}));
                v9_1++;
            }
        }
        if (!android.text.TextUtils.isEmpty(v3_0)) {
            v8_3.where("posturl like ?", new Object[] {String.format("%%%s%%", new Object[] {v3_0}))}));
        }
        if (!android.text.TextUtils.isEmpty(v5_7)) {
            String v0_31 = v5_7.split(",");
            int v2_8 = v0_31.length;
            int v3_2 = 0;
            while (v3_2 < v2_8) {
                v8_3.where("file_url like ?", new Object[] {String.format("%%%s%%", new Object[] {v0_31[v3_2]}))}));
                v3_2++;
            }
        }
        if (!android.text.TextUtils.isEmpty(v6_3)) {
            v8_3.where("rating like ?", new Object[] {String.format("%s%%", new Object[] {Character.valueOf(v6_3.charAt(0))}))}));
        }
        if (!android.text.TextUtils.isEmpty(v7_2)) {
            v8_3.where("source like ?", new Object[] {String.format("%%%s%%", new Object[] {v7_2}))}));
        }
        String v0_11 = com.bisimplex.firebooru.danbooru.DatabaseHelper$1.$SwitchMap$com$bisimplex$firebooru$danbooru$FavoriteSortType[v4_1.ordinal()];
        if (v0_11 == 1) {
            v8_3.orderBy("Id desc");
        } else {
            if (v0_11 == 2) {
                v8_3.orderBy("Id asc");
            } else {
                if (v0_11 == 3) {
                    v8_3.orderBy("score desc");
                } else {
                    if (v0_11 == 4) {
                        v8_3.orderBy("score asc");
                    } else {
                        if (v0_11 == 5) {
                            v8_3.orderBy("RANDOM()");
                        }
                    }
                }
            }
        }
        if (p17 >= 0) {
            v8_3.limit(100).offset((p17 * 100));
        }
        return v8_3.execute();
    }

    public java.util.List loadServers()
    {
        return this.loadServersByType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone);
    }

    public java.util.List loadServers(com.bisimplex.firebooru.network.SourceQuery p4)
    {
        if (p4 != null) {
            java.util.ArrayList v0_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server);
            if (!android.text.TextUtils.isEmpty(p4.getText())) {
                v0_3.where("url like ?", new Object[] {String.format("%%%s%%", new Object[] {p4.getText()}))}));
            }
            if (p4.getExtraParams().containsKey("serve_type")) {
                Object[] v4_3 = ((String) p4.getExtraParams().get("serve_type"));
                if (!android.text.TextUtils.isEmpty(v4_3)) {
                    v0_3.where("isGelbooru = ?", new Object[] {Integer.valueOf(Integer.parseInt(v4_3))}));
                }
            }
            Object[] v4_9 = v0_3.orderBy("id desc, isDefault desc").execute();
            java.util.ArrayList v0_4 = new java.util.ArrayList();
            Object[] v4_10 = v4_9.iterator();
            while (v4_10.hasNext()) {
                v0_4.add(this.ServerToServerItem(((com.bisimplex.firebooru.model.Server) v4_10.next())));
            }
            return v0_4;
        } else {
            return this.loadServers();
        }
    }

    public java.util.List loadServersByType(com.bisimplex.firebooru.danbooru.ServerItemType p5)
    {
        java.util.Iterator v5_3;
        java.util.ArrayList v0_5 = com.bisimplex.firebooru.danbooru.DatabaseHelper$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p5.ordinal()];
        if (v0_5 == 1) {
            v5_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).orderBy("id desc, isDefault desc").execute();
        } else {
            if (v0_5 == 2) {
                v5_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isGelbooru = ? or isGelbooru = ? or isGelbooru = ?", new Object[] {Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru.getValue()), Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2.getValue()), Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621.getValue())})).orderBy("id desc, isDefault desc").execute();
            } else {
                v5_3 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isGelbooru = ?", new Object[] {Integer.valueOf(p5.getValue())})).orderBy("id desc, isDefault desc").execute();
            }
        }
        java.util.ArrayList v0_11 = new java.util.ArrayList();
        java.util.Iterator v5_14 = v5_3.iterator();
        while (v5_14.hasNext()) {
            v0_11.add(this.ServerToServerItem(((com.bisimplex.firebooru.model.Server) v5_14.next())));
        }
        return v0_11;
    }

    public java.util.List loadServersWithPools()
    {
        java.util.Iterator v0_4 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).where("isGelbooru = ? or isGelbooru = ? or isGelbooru = ? or isGelbooru = ?", new Object[] {Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru.getValue()), Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2.getValue()), Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621.getValue()), Integer.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru.getValue())})).orderBy("id desc, isDefault desc").execute();
        java.util.ArrayList v1_4 = new java.util.ArrayList();
        java.util.Iterator v0_5 = v0_4.iterator();
        while (v0_5.hasNext()) {
            v1_4.add(this.ServerToServerItem(((com.bisimplex.firebooru.model.Server) v0_5.next())));
        }
        return v1_4;
    }

    public void removeFavoriteItem(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if (p2 != null) {
            int v0_0 = this.getFavoriteItemFromPost(p2);
            if (v0_0 != 0) {
                v0_0.delete();
            }
            p2.setFavorite(0);
            return;
        } else {
            return;
        }
    }

    public void setSelectedServer(int p7)
    {
        if (this.getSelectedServer().getServerId() != p7) {
            java.util.Iterator v0_1 = new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.Server).execute();
            com.activeandroid.ActiveAndroid.beginTransaction();
            try {
                java.util.Iterator v0_2 = v0_1.iterator();
            } catch (Throwable v7_1) {
                com.activeandroid.ActiveAndroid.endTransaction();
                throw v7_1;
            }
            while (v0_2.hasNext()) {
                int v2_3;
                com.bisimplex.firebooru.model.Server v1_2 = ((com.bisimplex.firebooru.model.Server) v0_2.next());
                if (v1_2.getId().longValue() != ((long) p7)) {
                    v2_3 = 0;
                } else {
                    v2_3 = 1;
                }
                v1_2.isSelected = v2_3;
                v1_2.save();
            }
            com.activeandroid.ActiveAndroid.setTransactionSuccessful();
            com.activeandroid.ActiveAndroid.endTransaction();
            return;
        } else {
            return;
        }
    }

    public void setServerType(int p1, com.bisimplex.firebooru.danbooru.ServerItemType p2)
    {
        com.bisimplex.firebooru.model.Server v1_1 = this.getServerById(p1);
        if (v1_1 != null) {
            v1_1.isGelbooru = p2.getValue();
            v1_1.save();
        }
        return;
    }

    public void starHistoryItem(long p3, boolean p5)
    {
        com.bisimplex.firebooru.model.TagHistory v3_3 = ((com.bisimplex.firebooru.model.TagHistory) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.TagHistory).where("Id = ?", new Object[] {Long.valueOf(p3)})).executeSingle());
        if (v3_3 != null) {
            v3_3.isFavoritedHistoryItem = p5;
            v3_3.save();
            return;
        } else {
            return;
        }
    }

    public void toogleFavoriteItem(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if ((p2 != null) && (!p2.isDisableStorage())) {
            if (!p2.isFavorite()) {
                this.addFavoriteItem(p2);
                return;
            } else {
                this.removeFavoriteItem(p2);
                return;
            }
        } else {
            return;
        }
    }

    public void updateBannedTag(String p3, java.util.List p4, long p5)
    {
        String v3_2 = this.buildTagText(p3, p4).trim();
        if ((v3_2.length() != 0) && (((com.bisimplex.firebooru.model.BannedTag) new com.activeandroid.query.Select().from(com.bisimplex.firebooru.model.BannedTag).where("tagText = ?", new Object[] {v3_2})).executeSingle()) == null)) {
            com.bisimplex.firebooru.model.BannedTag v4_5 = this.loadBannedTag(p5);
            if (v4_5 != null) {
                v4_5.tagText = v3_2;
                v4_5.save();
                return;
            }
        }
        return;
    }

    public void updateFavoriteItem(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if ((p2 != null) && ((!p2.isDisableStorage()) && (p2.isFavorite()))) {
            com.bisimplex.firebooru.model.Favorite v0_2 = this.getFavoriteItemFromPost(p2);
            if (v0_2 != null) {
                this.copyPostToFavorite(p2, v0_2);
                v0_2.save();
                return;
            }
        }
        return;
    }

    public void updateFavoriteItem(com.bisimplex.firebooru.model.Favorite p1)
    {
        if (p1 != null) {
            p1.save();
        }
        return;
    }

    public void updateMD5For(com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if ((p2 != null) && ((p3 != null) && ((!android.text.TextUtils.isEmpty(p3.getMd5())) && (p2.isFavorite())))) {
            com.bisimplex.firebooru.model.Favorite v2_1 = this.getFavoriteItemFromPost(p2);
            if (v2_1 != null) {
                v2_1.md5 = p3.getMd5();
                v2_1.save();
            }
        }
        return;
    }

    public void updateMD5For(Long p5, String p6)
    {
        if ((p5.longValue() != 0) && (!android.text.TextUtils.isEmpty(p6))) {
            com.bisimplex.firebooru.model.Favorite v5_1 = this.getFavoriteByID(p5.longValue());
            v5_1.md5 = p6;
            v5_1.save();
        }
        return;
    }
}
