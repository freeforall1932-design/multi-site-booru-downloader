package com.bisimplex.firebooru.danbooru;
public class GelbooruXMLContentHandler extends org.xml.sax.helpers.DefaultHandler {
    protected java.util.ArrayList data;
    private com.bisimplex.firebooru.danbooru.BooruProvider provider;

    public GelbooruXMLContentHandler()
    {
        this.data = new java.util.ArrayList();
        return;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage generateDanbooruPostFromAtts(org.xml.sax.Attributes p2, String p3, String p4, String p5)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage();
        int v3_2 = p2.getValue(p3);
        String v4_1 = p2.getValue(p4);
        int v2_2 = p2.getValue(p5);
        v0_1.setUrl(v3_2);
        v0_1.setWidth(this.tryParseInt(v4_1));
        v0_1.setHeight(this.tryParseInt(v2_2));
        return v0_1;
    }

    public int getPageNumber()
    {
        return 0;
    }

    public java.util.ArrayList getParsedData()
    {
        return this.data;
    }

    protected com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        if (this.provider == null) {
            this.provider = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
        }
        return this.provider;
    }

    public void setProvider(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        this.provider = p1;
        return;
    }

    public void startDocument()
    {
        this.data.clear();
        return;
    }

    public void startElement(String p3, String p4, String p5, org.xml.sax.Attributes p6)
    {
        if (p5.equalsIgnoreCase("post")) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v3_3 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            boolean v4_3 = this.getProvider();
            v3_3.setSample(this.generateDanbooruPostFromAtts(p6, "sample_url", "sample_width", "sample_height"));
            v3_3.setPreview(this.generateDanbooruPostFromAtts(p6, "preview_url", "preview_width", "preview_height"));
            v3_3.setJpeg(this.generateDanbooruPostFromAtts(p6, "jpeg_url", "jpeg_width", "jpeg_height"));
            v3_3.setFile(this.generateDanbooruPostFromAtts(p6, "file_url", "width", "height"));
            v4_3.fixDanbooruPreview(v3_3.getPreview());
            v3_3.setTags(p6.getValue("tags"));
            v3_3.setHas_children(Boolean.parseBoolean(p6.getValue("has_children")));
            v3_3.setHas_comments(Boolean.parseBoolean(p6.getValue("has_comments")));
            if (v4_3.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
                if (v4_3.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                    v3_3.setHas_notes(0);
                } else {
                    v3_3.setHas_notes(Boolean.parseBoolean(p6.getValue("has_notes")));
                }
            } else {
                v3_3.setHas_notes(1);
            }
            v3_3.setMd5(p6.getValue("md5"));
            v3_3.setParent_id(p6.getValue("parent_id"));
            v3_3.setRating(p6.getValue("rating"));
            v3_3.setScore(Integer.parseInt(p6.getValue("score")));
            v3_3.setSource(p6.getValue("source"));
            v3_3.setEnforceOriginalImage(v4_3.getEnforceOriginalImage());
            v3_3.setPostIdAndUrl(p6.getValue("id"), v4_3.serverDescription.getUrl(), v4_3.getPostFormat());
            v3_3.setImageIsVisible(1);
            if (!v4_3.isBlacklisted(v3_3)) {
                v4_3.fixUrls(v3_3);
                this.data.add(v3_3);
                v3_3.setFavorite(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getIsFavByPost(v3_3.getPostId(), v3_3.getMd5()));
            }
        }
        return;
    }

    protected int tryParseInt(String p2)
    {
        try {
            return Integer.parseInt(p2);
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }
}
