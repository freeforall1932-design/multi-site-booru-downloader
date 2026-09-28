package com.bisimplex.firebooru.network;
public class ParserGelbooruXML extends com.bisimplex.firebooru.network.ParserPosts {

    public ParserGelbooruXML(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    public static int optAttInt(org.jsoup.nodes.Element p1, String p2, int p3)
    {
        if ((p1 != 0) && ((!android.text.TextUtils.isEmpty(p2)) && (p1.hasAttr(p2)))) {
            int v1_2 = p1.attr(p2);
            if (!android.text.TextUtils.isEmpty(v1_2)) {
                return Integer.parseInt(v1_2);
            }
        }
        return p3;
    }

    private void parseXML(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            org.jsoup.select.Elements v3_1 = org.jsoup.Jsoup.parse(p3, "", org.jsoup.parser.Parser.xmlParser()).select("post");
            int v0_1 = 0;
            while (v0_1 < v3_1.size()) {
                this.parseElement(((org.jsoup.nodes.Element) v3_1.get(v0_1)));
                v0_1++;
            }
        }
        return;
    }

    protected void fixDanbooruPostURLs(com.bisimplex.firebooru.danbooru.DanbooruPost p5)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_0 = p5.getMd5();
        if ((!android.text.TextUtils.isEmpty(v0_0)) && (v0_0.length() >= 4)) {
            String v1_2 = String.format("/%s/%s/", new Object[] {v0_0.substring(0, 2), v0_0.substring(2, 4)}));
            if (this.fixImageURL(p5.getFile(), v0_0, v1_2)) {
                p5.setSample(p5.getFile());
            }
            this.fixImageURL(p5.getPreview(), v0_0, v1_2);
        }
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_1 = p5.getTags();
        if (((!v0_1.contains("webm")) && ((!v0_1.contains("video ")) && (!v0_1.contains(" video")))) || (p5.getFile().isWebM())) {
            if ((v0_1.contains("mp4")) && (!p5.getFile().isMP4())) {
                p5.getFile().setUrlExtension("mp4");
                p5.setSample(p5.getFile());
            }
            return;
        } else {
            p5.getFile().setUrlExtension("webm");
            p5.setSample(p5.getFile());
            return;
        }
    }

    protected boolean fixImageURL(com.bisimplex.firebooru.danbooru.DanbooruPostImage p7, String p8, String p9)
    {
        if (!android.text.TextUtils.isEmpty(p7.getUrl())) {
            if (p7.getUrl().contains(p9)) {
                return 0;
            } else {
                String v2_2;
                int v3_1;
                String v9_2 = android.net.Uri.parse(p7.getUrl());
                android.net.Uri$Builder v0_2 = new android.net.Uri$Builder();
                if (!p7.getUrl().contains("thumbnail")) {
                    v3_1 = "images";
                    v2_2 = p8;
                } else {
                    v2_2 = String.format("thumbnail_%s", new Object[] {p8}));
                    v3_1 = "thumbnails";
                }
                v0_2.scheme(v9_2.getScheme()).authority(v9_2.getAuthority()).appendPath(v3_1).appendPath(p8.substring(0, 2)).appendPath(p8.substring(2, 4)).appendPath(String.format("%s.%s", new Object[] {v2_2, p7.getExtension()}))).query(v9_2.getQuery());
                p7.setUrl(v0_2.build().toString());
                return 1;
            }
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPostImage generateDanbooruPostFromAtts(org.jsoup.nodes.Element p2, String p3, String p4, String p5)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage();
        int v3_2 = p2.attr(p3);
        String v4_1 = p2.attr(p4);
        int v2_2 = p2.attr(p5);
        v0_1.setUrl(v3_2);
        v0_1.setWidth(this.tryParseInt(v4_1));
        v0_1.setHeight(this.tryParseInt(v2_2));
        return v0_1;
    }

    public void parse()
    {
        this.data.clear();
        this.parseXML(this.params.getResponseBody());
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p1)
    {
        return;
    }

    protected void parseElement(org.jsoup.nodes.Element p8)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setSample(this.generateDanbooruPostFromAtts(p8, "sample_url", "sample_width", "sample_height"));
        v0_1.setPreview(this.generateDanbooruPostFromAtts(p8, "preview_url", "preview_width", "preview_height"));
        v0_1.setJpeg(this.generateDanbooruPostFromAtts(p8, "jpeg_url", "jpeg_width", "jpeg_height"));
        v0_1.setFile(this.generateDanbooruPostFromAtts(p8, "file_url", "width", "height"));
        v0_1.setMd5(p8.attr("md5"));
        v0_1.setTags(p8.attr("tags"));
        v0_1.setCreated_at(com.bisimplex.firebooru.network.ParserGelbooruXML.parseDateIfValid(p8.attr("created_at"), "E MMM d HH:mm:ss Z yyyy"));
        if (this.provider.isShouldFixURLs()) {
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_83 = v0_1.getPreview().getExtension();
            if ((v1_83 != null) && ((v1_83.equalsIgnoreCase("png")) || ((v1_83.equalsIgnoreCase("jpeg")) || (v1_83.equalsIgnoreCase("gif"))))) {
                v0_1.getPreview().setUrlExtension("jpg");
            }
            if ((v0_1.getSample().getWidth() != v0_1.getFile().getWidth()) || (v0_1.getSample().getHeight() != v0_1.getFile().getHeight())) {
                if ((!v0_1.getSample().getExtension().equalsIgnoreCase("png")) || (!v0_1.getFile().getExtension().equalsIgnoreCase("png"))) {
                    if ((!v0_1.getSample().getExtension().equalsIgnoreCase("jpeg")) || (!v0_1.getFile().getExtension().equalsIgnoreCase("jpeg"))) {
                        if ((v0_1.getSample().getExtension().equalsIgnoreCase("gif")) && (v0_1.getFile().getExtension().equalsIgnoreCase("gif"))) {
                            v0_1.getSample().setUrlExtension("jpg");
                        }
                    } else {
                        v0_1.getSample().setUrlExtension("jpg");
                    }
                } else {
                    v0_1.getSample().setUrlExtension("jpg");
                }
            }
            this.provider.getServerDescription().getServerName();
            if ((v0_1.getSample().getWidth() == v0_1.getFile().getWidth()) && ((v0_1.getSample().getHeight() == v0_1.getFile().getHeight()) && (v0_1.getSample().getExtension().equalsIgnoreCase(v0_1.getFile().getExtension())))) {
                v0_1.setSample(v0_1.getFile());
            }
        }
        if ((v0_1.getSample() != v0_1.getFile()) && ((v0_1.getSample().getWidth() == v0_1.getFile().getWidth()) && ((v0_1.getSample().getHeight() == v0_1.getFile().getHeight()) && (v0_1.getSample().getUrl().equalsIgnoreCase(v0_1.getFile().getUrl()))))) {
            v0_1.setSample(v0_1.getFile());
        }
        if ((v0_1.getFile().isAnimated()) && (!v0_1.getSample().isAnimated())) {
            v0_1.setSample(v0_1.getFile());
        }
        v0_1.setHas_children(Boolean.parseBoolean(p8.attr("has_children")));
        v0_1.setHas_comments(Boolean.parseBoolean(p8.attr("has_comments")));
        if (this.provider.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
            if (this.provider.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) {
                v0_1.setHas_notes(0);
            } else {
                v0_1.setHas_notes(Boolean.parseBoolean(p8.attr("has_notes")));
            }
        } else {
            v0_1.setHas_notes(1);
        }
        v0_1.setParent_id(p8.attr("parent_id"));
        v0_1.setRating(p8.attr("rating"));
        v0_1.setScore(com.bisimplex.firebooru.network.ParserGelbooruXML.optAttInt(p8, "score", 0));
        v0_1.setSource(p8.attr("source"));
        v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
        v0_1.setPostIdAndUrl(p8.attr("id"), this.provider.getServerDescription().getUrl(), this.provider.getPostFormat());
        v0_1.setImageIsVisible(1);
        if ((this.provider.isBlacklisted(v0_1)) && (!this.isIcludeBlacklisted())) {
            return;
        } else {
            this.provider.fixUrls(v0_1);
            this.data.add(v0_1);
            return;
        }
    }
}
