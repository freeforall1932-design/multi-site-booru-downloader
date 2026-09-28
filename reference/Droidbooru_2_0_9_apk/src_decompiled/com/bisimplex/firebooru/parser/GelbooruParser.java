package com.bisimplex.firebooru.parser;
public class GelbooruParser extends com.bisimplex.firebooru.parser.Parser {

    public GelbooruParser()
    {
        return;
    }

    public GelbooruParser(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        this.setProvider(p1);
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

    public void startDocument()
    {
        this.data.clear();
        return;
    }

    public void startElement(String p6, String p7, String p8, org.xml.sax.Attributes p9)
    {
        if (p8.equalsIgnoreCase("post")) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v6_3 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            com.bisimplex.firebooru.danbooru.DatabaseHelper v7_2 = this.getProvider();
            v6_3.setSample(this.generateDanbooruPostFromAtts(p9, "sample_url", "sample_width", "sample_height"));
            v6_3.setPreview(this.generateDanbooruPostFromAtts(p9, "preview_url", "preview_width", "preview_height"));
            v6_3.setJpeg(this.generateDanbooruPostFromAtts(p9, "jpeg_url", "jpeg_width", "jpeg_height"));
            v6_3.setFile(this.generateDanbooruPostFromAtts(p9, "file_url", "width", "height"));
            if (v7_2.isShouldFixURLs()) {
                com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_60 = v6_3.getPreview().getExtension();
                if ((v8_60 != null) && ((v8_60.equalsIgnoreCase("png")) || ((v8_60.equalsIgnoreCase("jpeg")) || (v8_60.equalsIgnoreCase("gif"))))) {
                    v6_3.getPreview().setUrlExtension("jpg");
                }
                v7_2.fixDanbooruPreview(v6_3.getPreview());
                if ((v6_3.getSample().getWidth() != v6_3.getFile().getWidth()) || (v6_3.getSample().getHeight() != v6_3.getFile().getHeight())) {
                    if ((!v6_3.getSample().getExtension().equalsIgnoreCase("png")) || (!v6_3.getFile().getExtension().equalsIgnoreCase("png"))) {
                        if ((!v6_3.getSample().getExtension().equalsIgnoreCase("jpeg")) || (!v6_3.getFile().getExtension().equalsIgnoreCase("jpeg"))) {
                            if ((v6_3.getSample().getExtension().equalsIgnoreCase("gif")) && (v6_3.getFile().getExtension().equalsIgnoreCase("gif"))) {
                                v6_3.getSample().setUrlExtension("jpg");
                            }
                        } else {
                            v6_3.getSample().setUrlExtension("jpg");
                        }
                    } else {
                        v6_3.getSample().setUrlExtension("jpg");
                    }
                }
            }
            if ((v6_3.getFile().isGif()) && (!v6_3.getSample().isGif())) {
                v6_3.setSample(v6_3.getFile());
            }
            v6_3.setTags(p9.getValue("tags"));
            v6_3.setHas_children(Boolean.parseBoolean(p9.getValue("has_children")));
            v6_3.setHas_comments(Boolean.parseBoolean(p9.getValue("has_comments")));
            if (v7_2.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
                if (v7_2.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                    v6_3.setHas_notes(0);
                } else {
                    v6_3.setHas_notes(Boolean.parseBoolean(p9.getValue("has_notes")));
                }
            } else {
                v6_3.setHas_notes(1);
            }
            v6_3.setMd5(p9.getValue("md5"));
            v6_3.setParent_id(p9.getValue("parent_id"));
            v6_3.setRating(p9.getValue("rating"));
            v6_3.setScore(Integer.parseInt(p9.getValue("score")));
            v6_3.setSource(p9.getValue("source"));
            v6_3.setEnforceOriginalImage(v7_2.getEnforceOriginalImage());
            v6_3.setPostIdAndUrl(p9.getValue("id"), v7_2.getServerDescription().getUrl(), v7_2.getPostFormat());
            v6_3.setImageIsVisible(1);
            if (!v7_2.isBlacklisted(v6_3)) {
                v7_2.fixUrls(v6_3);
                this.data.add(v6_3);
                com.bisimplex.firebooru.danbooru.DatabaseHelper v7_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
                v6_3.setFavorite(v7_1.getIsFavByPost(v6_3.getPostId(), v6_3.getMd5()));
                if (v6_3.isFavorite()) {
                    v7_1.updateFavoriteItem(v6_3);
                }
            }
        }
        return;
    }
}
