package com.bisimplex.firebooru.danbooru;
public class ShimmieContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {
    private java.util.regex.Pattern rssSizeRE;
    private java.util.regex.Pattern rssURLRE;

    public ShimmieContentHandler(nl.matshofman.saxrssreader.RssFeed p3)
    {
        this.rssSizeRE = java.util.regex.Pattern.compile("[0-9]+x[0-9]+", 2);
        this.rssURLRE = java.util.regex.Pattern.compile("src=\\\"([^\"]+)\\\"", 2);
        this.ParseData(p3);
        return;
    }

    public ShimmieContentHandler(nl.matshofman.saxrssreader.RssFeed p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        this.setProvider(p3);
        this.rssSizeRE = java.util.regex.Pattern.compile("[0-9]+x[0-9]+", 2);
        this.rssURLRE = java.util.regex.Pattern.compile("src=\\\"([^\"]+)\\\"", 2);
        this.ParseData(p2);
        return;
    }

    private void ParseData(nl.matshofman.saxrssreader.RssFeed p20)
    {
        String v4_1;
        com.bisimplex.firebooru.danbooru.BooruProvider v1 = this.getProvider();
        boolean v2 = v1.getEnforceOriginalImage();
        com.bisimplex.firebooru.danbooru.DatabaseHelper v3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        int v7_1 = 1;
        if (v1.getSubpath().length() <= 1) {
            v4_1 = "";
        } else {
            if (v1.getSubpath().endsWith("/")) {
                v4_1 = v1.getSubpath();
            } else {
                v4_1 = String.format("%s/", new Object[] {v1.getSubpath()}));
            }
        }
        java.util.Iterator v8_27 = p20.getRssItems().iterator();
        while (v8_27.hasNext()) {
            int v9_9 = ((nl.matshofman.saxrssreader.RssItem) v8_27.next());
            com.bisimplex.firebooru.danbooru.DanbooruPost v10_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
            if (v9_9.getMediaContentUrl().length() == 0) {
                v9_9.setMediaContentUrl(v9_9.getEnclosure());
            }
            String v11_4 = v9_9.getMediaContentUrl().replace(" ", "%20");
            if ((v4_1.length() > v7_1) && (v11_4.startsWith(v4_1))) {
                v11_4 = v11_4.substring((v4_1.length() - v7_1));
            }
            if (!v11_4.startsWith("/")) {
                if (v11_4.startsWith("./")) {
                    v11_4 = String.format("%s%s", new Object[] {v1.getServerDescription().getUrl(), v11_4.substring(v7_1)}));
                }
            } else {
                v11_4 = String.format("%s%s", new Object[] {v1.getServerDescription().getUrl(), v11_4}));
            }
            String v12_1 = v9_9.getMediaThumbnail();
            if ((v12_1 == null) || (v12_1.isEmpty())) {
                v12_1 = this.getUrlFromShimmieDescription(v9_9.getDescription());
            }
            int v15_0 = v9_9.getSummary();
            if ((v15_0 == 0) || (v15_0.isEmpty())) {
                v15_0 = v9_9.getDescription();
            }
            int v15_1 = this.getSizeFromShimmieDescription(v15_0);
            int v16_1 = v7_1;
            int v7_0 = v15_1[0];
            int v15_2 = v15_1[v16_1];
            java.util.Iterator v17 = v8_27;
            nl.matshofman.saxrssreader.RssItem v18 = v9_9;
            int v9_0 = v16_1;
            if ((v4_1.length() > v9_0) && (v12_1.startsWith(v4_1))) {
                v12_1 = v12_1.substring((v4_1.length() - v9_0));
            }
            if (!v12_1.startsWith("/")) {
                if (v11_4.startsWith("./")) {
                    v12_1 = String.format("%s%s", new Object[] {v1.getServerDescription().getUrl(), v12_1.substring(1)}));
                }
            } else {
                v12_1 = String.format("%s%s", new Object[] {v1.getServerDescription().getUrl(), v12_1}));
            }
            java.util.Iterator v8_22;
            v10_1.setSample(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v11_4, v7_0, v15_2));
            v10_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v12_1, v7_0, v15_2));
            v10_1.setJpeg(new com.bisimplex.firebooru.danbooru.DanbooruPostImage("", v7_0, v15_2));
            v10_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v11_4, v7_0, v15_2));
            int v7_3 = v18.getTitle().split(" - ");
            if (v7_3.length < 2) {
                v8_22 = "";
            } else {
                v8_22 = v7_3[1];
            }
            v10_1.setTags(v8_22);
            v10_1.setHas_children(0);
            v10_1.setHas_comments(0);
            v10_1.setHas_notes(0);
            v10_1.setMd5(v11_4);
            v10_1.setRating("");
            v10_1.setScore(0);
            v10_1.setSource(v18.getLink());
            v10_1.setEnforceOriginalImage(v2);
            if (v7_3.length > 0) {
                v10_1.setPostIdAndUrl(v7_3[0].trim(), v1.getServerDescription().getUrl(), v1.getPostFormat());
            }
            v10_1.setVisible(1);
            v10_1.setForcedPostUrl(v18.getLink());
            if (!v1.isBlacklisted(v10_1)) {
                this.data.add(v10_1);
                v10_1.setFavorite(v3.getIsFavByPost(v10_1.getPostId(), v10_1.getMd5()));
            }
            v7_1 = 1;
            v8_27 = v17;
        }
        return;
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
}
