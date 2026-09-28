package com.bisimplex.firebooru.network;
public class ParserGelbooruHTML extends com.bisimplex.firebooru.network.ParserPosts {
    private java.util.regex.Pattern animatedGifExpression;
    private String postFormat;
    private java.util.regex.Pattern ratingExpression;
    private java.util.regex.Pattern scoreExpression;
    private java.util.regex.Pattern webmExpression;

    public ParserGelbooruHTML(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super(p2);
        super.scoreExpression = java.util.regex.Pattern.compile("score:[0-9]+", 2);
        super.ratingExpression = java.util.regex.Pattern.compile("rating:[a-z]+", 2);
        super.animatedGifExpression = java.util.regex.Pattern.compile("animated", 2);
        super.webmExpression = java.util.regex.Pattern.compile("webm|video", 2);
        super.postFormat = super.provider.getPostFormat();
        return;
    }

    private void parseHTML(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            java.util.Iterator v3_2 = org.jsoup.Jsoup.parse(p3);
            org.jsoup.nodes.Element v0_2 = v3_2.select("span.thumb");
            if (v0_2.size() == 0) {
                v0_2 = v3_2.select(".thumbail-container a");
            }
            java.util.Iterator v3_1 = v0_2.iterator();
            while (v3_1.hasNext()) {
                this.parseElement(((org.jsoup.nodes.Element) v3_1.next()));
            }
        }
        return;
    }

    public void parse()
    {
        this.data.clear();
        this.parseHTML(this.params.getResponseBody());
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p1)
    {
        return;
    }

    protected void parseElement(org.jsoup.nodes.Element p12)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_1;
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_4 = p12.getElementsByTag("a");
        if ((v1_4 == null) || (v1_4.size() <= 0)) {
            v1_1 = p12.attr("id");
        } else {
            v1_1 = v1_4.first().attr("id");
        }
        if ((!android.text.TextUtils.isEmpty(v1_1)) && ((v1_1.length() > 1) && (!android.text.TextUtils.isDigitsOnly(v1_1)))) {
            v1_1 = v1_1.substring(1);
        }
        boolean v2_4 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v2_4.setPostIdAndUrl(v1_1, this.baseUrl, this.postFormat);
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_3 = 0;
        v2_4.setHas_children(0);
        v2_4.setMd5(com.bisimplex.firebooru.network.Utils.md5(v2_4.getPostUrl()));
        v2_4.setParent_id("");
        v2_4.setRating("");
        v2_4.setSource("");
        v2_4.setScore(0);
        v2_4.setHas_notes(0);
        int v6_4 = p12.getElementsByTag("img");
        if ((v6_4 != 0) && (!v6_4.isEmpty())) {
            int v6_5 = v6_4.iterator();
            int v7_1 = 0;
            String v8_2 = 0;
            while (v6_5.hasNext()) {
                String v9_2;
                String v8_1 = ((org.jsoup.nodes.Element) v6_5.next());
                if (!v8_1.hasAttr("data-src")) {
                    v9_2 = v8_1.attr("src");
                } else {
                    v9_2 = v8_1.attr("data-src");
                }
                if (!android.text.TextUtils.isEmpty(v9_2)) {
                    v7_1 = v8_1;
                }
                v8_2 = v9_2;
            }
            if ((v7_1 != 0) && (!android.text.TextUtils.isEmpty(v8_2))) {
                if (!v8_2.startsWith("http")) {
                    if (v8_2.startsWith("/")) {
                        v8_2 = String.format("%s%s", new Object[] {this.baseUrl, v8_2}));
                    } else {
                        v8_2 = String.format("%s/%s", new Object[] {this.baseUrl, v8_2}));
                    }
                }
                v2_4.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v8_2, 0, 0));
                v2_4.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v8_2.replace("thumbs", "img").replace("/thumbnails/", "//images/").replace("thumbnail_", "").replace("//aimg.", "//img."), 0, 0));
                v2_4.setSample(v2_4.getFile());
                v2_4.setJpeg(v2_4.getFile());
                v2_4.setEnforceOriginalImage(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isDownloadOriginalImage());
                int v5_5 = v7_1.attr("title");
                if (android.text.TextUtils.isEmpty(v5_5)) {
                    v5_5 = "";
                }
                v2_4.setTags(v5_5);
                if (v7_1.hasClass("lazyload")) {
                    if ((p12.is("a")) && (p12.hasAttr("href"))) {
                        java.util.List v12_1 = p12.attr("href");
                        if (!android.text.TextUtils.isEmpty(v12_1)) {
                            v2_4.setPostUrl(v12_1);
                        }
                    }
                    v1_3 = 1;
                }
                java.util.List v12_4;
                java.util.List v12_3 = this.scoreExpression.matcher(v2_4.getTags());
                if (!v12_3.find()) {
                    v12_4 = "";
                } else {
                    v12_4 = v12_3.group();
                }
                if (!android.text.TextUtils.isEmpty(v12_4)) {
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_7 = v12_4.split(":");
                    if (v0_7.length == 2) {
                        v2_4.setScore(Integer.parseInt(v0_7[1]));
                    }
                    v2_4.setTags(v2_4.getTags().replace(v12_4, ""));
                }
                java.util.List v12_8;
                java.util.List v12_7 = this.ratingExpression.matcher(v2_4.getTags());
                if (!v12_7.find()) {
                    v12_8 = "";
                } else {
                    v12_8 = v12_7.group();
                }
                if (!android.text.TextUtils.isEmpty(v12_8)) {
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_14 = v12_8.split(":");
                    if (v0_14.length == 2) {
                        v2_4.setRating(v0_14[1]);
                    }
                    v2_4.setTags(v2_4.getTags().replace(v12_8, ""));
                }
                if (!this.webmExpression.matcher(v2_4.getTags()).find()) {
                    if (this.animatedGifExpression.matcher(v2_4.getTags()).find()) {
                        v2_4.getFile().setUrlExtension("gif");
                    }
                } else {
                    if (v1_3 == null) {
                        com.bisimplex.firebooru.danbooru.DanbooruPostImage v0_21 = v2_4.getTags().indexOf("video");
                        int vtmp73 = v2_4.getTags().indexOf("video_game");
                        if ((v0_21 >= null) && (v0_21 != vtmp73)) {
                            v2_4.getFile().setUrlExtension("webm");
                        }
                    } else {
                        com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_9 = v2_4.getTags().indexOf("video");
                        int vtmp69 = v2_4.getTags().indexOf("video game");
                        if ((v1_9 >= null) && (v1_9 != vtmp69)) {
                            com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_12 = android.net.Uri.parse(v2_4.getFile().getUrl());
                            String v3_4 = v1_12.getAuthority();
                            if (!v3_4.startsWith("video")) {
                                v2_4.getFile().setUrl(v1_12.buildUpon().authority(String.format("video%s", new Object[] {v3_4.substring(v3_4.indexOf("."))}))).build().toString());
                            }
                            v2_4.getFile().setUrlExtension("webm");
                        }
                    }
                }
                if (this.shouldAddToResults(v2_4)) {
                    this.data.add(v2_4);
                }
            }
        }
        return;
    }
}
