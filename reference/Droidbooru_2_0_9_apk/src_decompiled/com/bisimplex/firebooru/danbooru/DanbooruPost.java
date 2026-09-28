package com.bisimplex.firebooru.danbooru;
public class DanbooruPost {
    private String author;
    private String blacklistRule;
    private boolean blacklisted;
    private java.util.Date created_at;
    private java.util.Date dateAdded;
    private boolean disableStorage;
    private boolean enforceOriginalImage;
    private com.bisimplex.firebooru.danbooru.DanbooruPostImage file;
    private boolean has_children;
    private boolean has_comments;
    private boolean has_notes;
    private boolean imageIsVisible;
    private boolean isFavorite;
    private boolean isVisible;
    private com.bisimplex.firebooru.danbooru.DanbooruPostImage jpeg;
    private String md5;
    private String parent_id;
    private String postId;
    private String postUrl;
    private com.bisimplex.firebooru.danbooru.DanbooruPostImage preview;
    private String rating;
    private com.bisimplex.firebooru.danbooru.DanbooruPostImage sample;
    private int score;
    private java.util.List separateTags;
    private String source;
    private String tag_artist;
    private String tag_character;
    private String tag_copyright;
    private String tag_general;
    private String tag_meta;
    private String tags;

    public DanbooruPost()
    {
        return;
    }

    private String generateCurrentFileName()
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getFileNameType() != com.bisimplex.firebooru.danbooru.FileNameType.Legacy) {
            String v0_15 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getFileNameConfiguration();
            StringBuilder v1_1 = new StringBuilder();
            boolean v2_1 = new java.util.ArrayList();
            String v0_1 = v0_15.iterator();
            while (v0_1.hasNext()) {
                String v3_17 = com.bisimplex.firebooru.danbooru.DanbooruPost$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType[((com.bisimplex.firebooru.model.FileNamePartType) v0_1.next()).ordinal()];
                if (v3_17 == 1) {
                    if (!android.text.TextUtils.isEmpty(this.getMd5())) {
                        v2_1.add(this.getMd5());
                    }
                } else {
                    if (v3_17 == 2) {
                        String v3_23 = android.net.Uri.parse(this.getPostUrl()).getHost();
                        if (!android.text.TextUtils.isEmpty(v3_23)) {
                            v2_1.add(v3_23);
                        }
                    } else {
                        if (v3_17 == 3) {
                            String v3_1 = new StringBuilder();
                            if (!android.text.TextUtils.isEmpty(this.getTag_artist())) {
                                v3_1.append(this.getTag_artist());
                                v3_1.append(" ");
                            }
                            if (!android.text.TextUtils.isEmpty(this.getTag_copyright())) {
                                v3_1.append(this.getTag_copyright());
                                v3_1.append(" ");
                            }
                            if (!android.text.TextUtils.isEmpty(this.getTag_character())) {
                                v3_1.append(this.getTag_character());
                                v3_1.append(" ");
                            }
                            if (!android.text.TextUtils.isEmpty(this.getTag_general())) {
                                v3_1.append(this.getTag_general());
                                v3_1.append(" ");
                            }
                            if (!android.text.TextUtils.isEmpty(this.getTag_meta())) {
                                v3_1.append(this.getTag_meta());
                                v3_1.append(" ");
                            }
                            if (v3_1.length() == 0) {
                                v3_1.append(this.getTags());
                            }
                            int v4_17 = v3_1.length();
                            if (v4_17 != 0) {
                                if (v4_17 > 70) {
                                    String v3_2 = v3_1.substring(0, 71);
                                    int v4_19 = v3_2.lastIndexOf(" ");
                                    if (v4_19 > 0) {
                                        v2_1.add(v3_2.substring(0, v4_19).trim());
                                    }
                                } else {
                                    v2_1.add(v3_1.toString().trim());
                                }
                            }
                        } else {
                            if ((v3_17 == 4) && (!android.text.TextUtils.isEmpty(this.getPostId()))) {
                                v2_1.add(this.getPostId());
                            }
                        }
                    }
                }
            }
            String v0_3 = v2_1.iterator();
            while (v0_3.hasNext()) {
                v1_1.append(((String) v0_3.next()));
                v1_1.append(" ");
            }
            if ((!v2_1.isEmpty()) && (v1_1.length() > 0)) {
                v1_1.deleteCharAt((v1_1.length() - 1));
            }
            String v0_9 = this.getVisibleVersion();
            if (v0_9 != this.file) {
                v1_1.append(" sample");
            }
            if (v1_1.length() == 0) {
                v1_1.append(java.util.UUID.randomUUID().toString());
            }
            v1_1.append(".");
            String v0_10 = v0_9.getExtension();
            if (android.text.TextUtils.isEmpty(v0_10)) {
                v0_10 = "jpg";
            }
            v1_1.append(v0_10);
            return this.sanitizeFileNameString(v1_1.toString().trim());
        } else {
            return 0;
        }
    }

    private String generateLegacyDownloadFileName()
    {
        String v0_2;
        String v0_5 = this.postUrl.split("/");
        if (v0_5.length <= 3) {
            v0_2 = String.format("%s", new Object[] {this.postId}));
        } else {
            v0_2 = String.format("%s %s", new Object[] {v0_5[2], this.postId}));
        }
        String v1_6 = this.tags.trim().split(" ");
        int v2_1 = 0;
        while ((v0_2.length() < 90) && (v2_1 < v1_6.length)) {
            v0_2 = String.format("%s %s", new Object[] {v0_2, v1_6[v2_1]}));
            v2_1++;
        }
        return this.sanitizeFileNameString(String.format("%s.%s", new Object[] {v0_2, this.getVisibleVersion().getExtension()})));
    }

    private String generateLegacyFileName()
    {
        String v0_2;
        String v0_5 = this.postUrl.split("/");
        if (v0_5.length <= 3) {
            v0_2 = String.format("%s", new Object[] {this.postId}));
        } else {
            v0_2 = String.format("%s %s", new Object[] {v0_5[2], this.postId}));
        }
        String v1_6 = this.tags.trim().split(" ");
        String v2_1 = 0;
        while ((v0_2.length() < 100) && (v2_1 < v1_6.length)) {
            v0_2 = String.format("%s %s", new Object[] {v0_2, v1_6[v2_1]}));
            v2_1++;
        }
        String v2_3;
        String v1_7 = this.getVisibleVersion();
        if (v1_7 != this.file) {
            v2_3 = " sample";
        } else {
            v2_3 = " full";
        }
        return this.sanitizeFileNameString(String.format("%s%s.%s", new Object[] {v0_2, v2_3, v1_7.getExtension()})));
    }

    private java.util.List partTagList(String p6, int p7)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (p6 != null) {
            String[] v6_2 = p6.trim();
            if (!v6_2.isEmpty()) {
                String[] v6_1 = v6_2.split(" ");
                int v2 = 0;
                while (v2 < v6_1.length) {
                    String v3_1 = v6_1[v2];
                    com.bisimplex.firebooru.danbooru.TagItem v4_1 = new com.bisimplex.firebooru.danbooru.TagItem();
                    v4_1.setIdx(0);
                    v4_1.setName(v3_1);
                    v4_1.setType(p7);
                    v0_1.add(v4_1);
                    v2++;
                }
            }
        }
        return v0_1;
    }

    public void calculateTags()
    {
        if (this.separateTags == null) {
            java.util.List v0_2 = this.partTagList(this.getTag_artist(), 1);
            v0_2.addAll(this.partTagList(this.getTag_copyright(), 3));
            v0_2.addAll(this.partTagList(this.getTag_character(), 4));
            v0_2.addAll(this.partTagList(this.getTag_general(), 0));
            v0_2.addAll(this.partTagList(this.getTag_meta(), 5));
            if (v0_2.size() == 0) {
                v0_2.addAll(this.partTagList(this.getTags(), -1));
            }
            this.separateTags = v0_2;
        }
        return;
    }

    public String generateDownloadFileName()
    {
        String v0 = this.generateCurrentFileName();
        if (android.text.TextUtils.isEmpty(v0)) {
            v0 = this.generateLegacyDownloadFileName();
        }
        return v0;
    }

    public String generateFileName()
    {
        String v0 = this.generateCurrentFileName();
        if (android.text.TextUtils.isEmpty(v0)) {
            v0 = this.generateLegacyFileName();
        }
        return v0;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public String getBlacklistRule()
    {
        return this.blacklistRule;
    }

    public java.util.Date getCreated_at()
    {
        return this.created_at;
    }

    public java.util.Date getDateAdded()
    {
        return this.dateAdded;
    }

    public boolean getEnforceOriginalImage()
    {
        return this.enforceOriginalImage;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage getFile()
    {
        return this.file;
    }

    public boolean getHas_children()
    {
        return this.has_children;
    }

    public boolean getHas_comments()
    {
        return this.has_comments;
    }

    public boolean getHas_notes()
    {
        return this.has_notes;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage getJpeg()
    {
        return this.jpeg;
    }

    public String getMd5()
    {
        return this.md5;
    }

    public String getParent_id()
    {
        return this.parent_id;
    }

    public String getPostId()
    {
        return this.postId;
    }

    public String getPostUrl()
    {
        return this.postUrl;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage getPreview()
    {
        return this.preview;
    }

    public String getRating()
    {
        return this.rating;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage getSample()
    {
        return this.sample;
    }

    public int getScore()
    {
        return this.score;
    }

    public String getSource()
    {
        return this.source;
    }

    public java.util.List getTagList()
    {
        if (this.separateTags == null) {
            this.calculateTags();
        }
        return this.separateTags;
    }

    public String getTag_artist()
    {
        return this.tag_artist;
    }

    public String getTag_character()
    {
        return this.tag_character;
    }

    public String getTag_copyright()
    {
        return this.tag_copyright;
    }

    public String getTag_general()
    {
        return this.tag_general;
    }

    public String getTag_general_meta()
    {
        if (!android.text.TextUtils.isEmpty(this.getTag_meta())) {
            return String.format("%s %s", new Object[] {this.tag_general, this.getTag_meta()})).trim();
        } else {
            return this.tag_general;
        }
    }

    public String getTag_meta()
    {
        return this.tag_meta;
    }

    public String getTags()
    {
        return this.tags;
    }

    public float getVisibleImageProportion()
    {
        float v0_0 = this.getVisibleVersion();
        float v1_2 = ((float) this.file.getWidth());
        if (v1_2 != 0) {
            return (((float) v0_0.getWidth()) / v1_2);
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage getVisibleVersion()
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_0 = this.file;
        if (v0_0 != null) {
            if ((this.sample != null) && (!this.enforceOriginalImage)) {
                if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isDownloadOriginalImage()) {
                    return this.sample;
                } else {
                    return this.file;
                }
            } else {
                return v0_0;
            }
        } else {
            return this.sample;
        }
    }

    public boolean hasCalculatedTags()
    {
        if (this.separateTags == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean hasParent()
    {
        if ((android.text.TextUtils.isEmpty(this.getParent_id())) || (this.getParent_id().equalsIgnoreCase("0"))) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isBlacklisted()
    {
        return this.blacklisted;
    }

    public boolean isDisableStorage()
    {
        return this.disableStorage;
    }

    public boolean isFavorite()
    {
        return this.isFavorite;
    }

    public boolean isImageIsVisible()
    {
        return this.imageIsVisible;
    }

    public boolean isVisible()
    {
        return this.isVisible;
    }

    public String renderTitle()
    {
        return this.getVisibleVersion().renderResolution();
    }

    protected String sanitizeFileNameString(String p3)
    {
        return p3.replaceAll("[^0-9a-zA-Z\\+\\.\\(\\)_\\-\\s]", "");
    }

    public void setAuthor(String p1)
    {
        this.author = p1;
        return;
    }

    public void setBlacklistRule(String p1)
    {
        this.blacklistRule = p1;
        return;
    }

    public void setBlacklisted(boolean p1)
    {
        this.blacklisted = p1;
        return;
    }

    public void setCreated_at(java.util.Date p1)
    {
        this.created_at = p1;
        return;
    }

    public void setDateAdded(java.util.Date p1)
    {
        this.dateAdded = p1;
        return;
    }

    public void setDisableStorage(boolean p1)
    {
        this.disableStorage = p1;
        return;
    }

    public void setEnforceOriginalImage(boolean p1)
    {
        this.enforceOriginalImage = p1;
        return;
    }

    public void setFavorite(boolean p1)
    {
        this.isFavorite = p1;
        return;
    }

    public void setFile(com.bisimplex.firebooru.danbooru.DanbooruPostImage p1)
    {
        this.file = p1;
        return;
    }

    public void setForcedPostUrl(String p1)
    {
        this.postUrl = p1;
        return;
    }

    public void setHas_children(boolean p1)
    {
        this.has_children = p1;
        return;
    }

    public void setHas_comments(boolean p1)
    {
        this.has_comments = p1;
        return;
    }

    public void setHas_notes(boolean p1)
    {
        this.has_notes = p1;
        return;
    }

    public void setImageIsVisible(boolean p1)
    {
        this.imageIsVisible = p1;
        return;
    }

    public void setJpeg(com.bisimplex.firebooru.danbooru.DanbooruPostImage p1)
    {
        this.jpeg = p1;
        return;
    }

    public void setMd5(String p1)
    {
        this.md5 = p1;
        return;
    }

    public void setParent_id(String p1)
    {
        this.parent_id = p1;
        return;
    }

    public void setPostId(String p1)
    {
        this.postId = p1;
        return;
    }

    public void setPostIdAndUrl(String p2, String p3, String p4)
    {
        if (p2 == null) {
            this.postId = "";
        } else {
            this.postId = p2;
        }
        if (p4 == null) {
            this.postUrl = "";
            return;
        } else {
            this.postUrl = String.format(p4, new Object[] {p3, this.postId}));
            return;
        }
    }

    public void setPostUrl(String p1)
    {
        this.postUrl = p1;
        return;
    }

    public void setPreview(com.bisimplex.firebooru.danbooru.DanbooruPostImage p1)
    {
        this.preview = p1;
        return;
    }

    public void setRating(String p1)
    {
        this.rating = p1;
        return;
    }

    public void setSample(com.bisimplex.firebooru.danbooru.DanbooruPostImage p1)
    {
        this.sample = p1;
        return;
    }

    public void setScore(int p1)
    {
        this.score = p1;
        return;
    }

    public void setSeparateTags(java.util.List p1)
    {
        this.separateTags = p1;
        return;
    }

    public void setSource(String p1)
    {
        this.source = p1;
        return;
    }

    public void setTag_artist(String p1)
    {
        this.tag_artist = p1;
        return;
    }

    public void setTag_character(String p1)
    {
        this.tag_character = p1;
        return;
    }

    public void setTag_copyright(String p1)
    {
        this.tag_copyright = p1;
        return;
    }

    public void setTag_general(String p1)
    {
        this.tag_general = p1;
        return;
    }

    public void setTag_meta(String p1)
    {
        this.tag_meta = p1;
        return;
    }

    public void setTags(String p1)
    {
        this.tags = p1;
        return;
    }

    public void setVisible(boolean p1)
    {
        this.isVisible = p1;
        return;
    }

    public boolean shouldPreload()
    {
        return (this.isBlacklisted() ^ 1);
    }
}
