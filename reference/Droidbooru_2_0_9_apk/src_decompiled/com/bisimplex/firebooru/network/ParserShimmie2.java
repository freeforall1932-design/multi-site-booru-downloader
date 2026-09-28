package com.bisimplex.firebooru.network;
public class ParserShimmie2 extends com.bisimplex.firebooru.network.ParserPosts {
    private java.util.regex.Pattern rssSizeRE;
    private java.util.regex.Pattern rssURLRE;
    private String subpath;

    public ParserShimmie2(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super(p2);
        super.rssSizeRE = java.util.regex.Pattern.compile("[0-9]+x[0-9]+", 2);
        super.rssURLRE = java.util.regex.Pattern.compile("src=\\\"([^\"]+)\\\"", 2);
        super.subpath = "";
        if (super.provider.getSubpath().length() > 1) {
            if (super.provider.getSubpath().endsWith("/")) {
                super.subpath = super.provider.getSubpath();
            } else {
                super.subpath = String.format("%s/", new Object[] {super.provider.getSubpath()}));
                return;
            }
        }
        return;
    }

    private void parseXML(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            org.jsoup.select.Elements v3_2 = org.jsoup.Jsoup.parse(p3).select("item");
            int v0_1 = 0;
            while (v0_1 < v3_2.size()) {
                this.parseElement(((org.jsoup.nodes.Element) v3_2.get(v0_1)));
                v0_1++;
            }
        }
        return;
    }

    private String valueForAttrOnChildren(org.jsoup.nodes.Element p1, String p2, String p3)
    {
        String v1_2;
        String v1_1 = p1.getElementsByTag(p2);
        if (v1_1.size() <= 0) {
            v1_2 = 0;
        } else {
            v1_2 = ((org.jsoup.nodes.Element) v1_1.get(0)).attr(p3);
        }
        if (v1_2 == null) {
            v1_2 = "";
        }
        return v1_2;
    }

    private String valueForChildren(org.jsoup.nodes.Element p2, String p3)
    {
        String v2_3;
        String v2_1 = p2.getElementsByTag(p3);
        if (v2_1.size() <= 0) {
            v2_3 = "";
        } else {
            v2_3 = ((org.jsoup.nodes.Element) v2_1.get(0)).text();
        }
        if (v2_3 != null) {
            return v2_3;
        } else {
            return "";
        }
    }

    public int[] getSizeFromShimmieDescription(String p4)
    {
        int[] v4_4 = this.rssSizeRE.matcher(p4);
        if (!v4_4.find()) {
            return new int[] {0, 0});
        } else {
            int[] v4_3 = v4_4.group().toLowerCase().split("x");
            if (v4_3.length == 2) {
                return new int[] {Integer.parseInt(v4_3[0]), Integer.parseInt(v4_3[1])});
            } else {
                return new int[] {0, 0});
            }
        }
    }

    public String getUrlFromShimmieDescription(String p3)
    {
        String v3_2 = this.rssURLRE.matcher(p3);
        if (!v3_2.find()) {
            return "";
        } else {
            String v3_1 = v3_2.group().toLowerCase();
            if (v3_1.length() != 0) {
                return v3_1.substring("src=\"".length(), (v3_1.length() - 1));
            } else {
                return "";
            }
        }
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

    protected void parseElement(org.jsoup.nodes.Element p13)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        String v1_3 = this.valueForChildren(p13, "title");
        String v2_0 = this.valueForChildren(p13, "link");
        String v3_0 = this.valueForChildren(p13, "guid");
        if ((android.text.TextUtils.isEmpty(v2_0)) && (!android.text.TextUtils.isEmpty(v3_0))) {
            v2_0 = v3_0;
        }
        String v3_7 = this.valueForChildren(p13, "description");
        String v4_1 = this.valueForAttrOnChildren(p13, "media:thumbnail", "url");
        java.util.List v13_10 = this.valueForAttrOnChildren(p13, "media:content", "url").replace(" ", "%20");
        if ((this.subpath.length() > 1) && (v13_10.startsWith(this.subpath))) {
            v13_10 = v13_10.substring((this.subpath.length() - 1));
        }
        if (!v13_10.startsWith("/")) {
            if (v13_10.startsWith("./")) {
                v13_10 = String.format("%s%s", new Object[] {this.baseUrl, v13_10.substring(1)}));
            }
        } else {
            v13_10 = String.format("%s%s", new Object[] {this.baseUrl, v13_10}));
        }
        String v3_8 = this.getSizeFromShimmieDescription(v3_7);
        int v10 = v3_8[0];
        String v3_9 = v3_8[1];
        if ((this.subpath.length() > 1) && (v4_1.startsWith(this.subpath))) {
            v4_1 = v4_1.substring((this.subpath.length() - 1));
        }
        if (!v4_1.startsWith("/")) {
            if (v13_10.startsWith("./")) {
                v4_1 = String.format("%s%s", new Object[] {this.provider.getServerDescription().getUrl(), v4_1.substring(1)}));
            }
        } else {
            v4_1 = String.format("%s%s", new Object[] {this.provider.getServerDescription().getUrl(), v4_1}));
        }
        String v3_3;
        v0_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v13_10, v10, v3_9));
        v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v4_1, v10, v3_9));
        v0_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage("", v10, v3_9));
        v0_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v13_10, v10, v3_9));
        String v1_0 = v1_3.split(" - ");
        if (v1_0.length < 2) {
            v3_3 = "";
        } else {
            v3_3 = v1_0[1];
        }
        v0_1.setTags(v3_3);
        v0_1.setHas_children(0);
        v0_1.setHas_comments(0);
        v0_1.setHas_notes(0);
        v0_1.setMd5(com.bisimplex.firebooru.network.Utils.md5(v13_10));
        v0_1.setRating("");
        v0_1.setScore(0);
        v0_1.setSource(v2_0);
        v0_1.setEnforceOriginalImage(this.provider.getEnforceOriginalImage());
        if (v1_0.length > 0) {
            v0_1.setPostIdAndUrl(v1_0[0].trim(), this.baseUrl, this.provider.getPostFormat());
        }
        v0_1.setVisible(1);
        v0_1.setForcedPostUrl(v2_0);
        if (this.shouldAddToResults(v0_1)) {
            this.data.add(v0_1);
        }
        return;
    }
}
