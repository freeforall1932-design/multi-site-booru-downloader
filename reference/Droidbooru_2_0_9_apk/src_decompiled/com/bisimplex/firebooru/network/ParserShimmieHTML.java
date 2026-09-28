package com.bisimplex.firebooru.network;
public class ParserShimmieHTML extends com.bisimplex.firebooru.network.ParserPosts {
    private String postFormat;

    public ParserShimmieHTML(com.bisimplex.firebooru.network.ParserParams p3)
    {
        super(p3);
        if (p3 == null) {
            super.postFormat = "";
        } else {
            super.postFormat = super.provider.getPostFormat();
        }
        String v3_2 = android.net.Uri.parse(super.baseUrl).getPath();
        if (v3_2.length() > 1) {
            super.baseUrl = super.baseUrl.substring(0, super.baseUrl.indexOf(v3_2));
        }
        return;
    }

    private void parseHTML(String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.List v4_6 = org.jsoup.Jsoup.parse(p4);
            com.bisimplex.firebooru.danbooru.DanbooruPost v0_12 = v4_6.select(".thumb");
            int v2 = 0;
            if (v0_12.size() <= 0) {
                java.util.List v4_1 = v4_6.select("#main_image");
                if (v4_1.size() <= 0) {
                    return;
                } else {
                    java.util.List v4_4 = ((org.jsoup.nodes.Element) v4_1.get(0)).attr("src");
                    if (!android.text.TextUtils.isEmpty(v4_4)) {
                        if (v4_4.startsWith("/")) {
                            v4_4 = String.format("%s%s", new Object[] {this.baseUrl, v4_4}));
                        }
                        com.bisimplex.firebooru.danbooru.DanbooruPost v0_10 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                        v0_10.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v4_4, 0, 0));
                        this.data.add(v0_10);
                        return;
                    }
                }
            }
            while (v2 < v0_12.size()) {
                this.parseElement(((org.jsoup.nodes.Element) v0_12.get(v2)));
                v2++;
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

    protected void parseElement(org.jsoup.nodes.Element p15)
    {
        String v4_1;
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setHas_children(0);
        v0_1.setParent_id("");
        v0_1.setRating("");
        v0_1.setSource("");
        v0_1.setScore(0);
        v0_1.setHas_notes(0);
        v0_1.setEnforceOriginalImage(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isDownloadOriginalImage());
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_2 = p15.dataset();
        if (!v3_2.containsKey("tags")) {
            v4_1 = "";
        } else {
            v4_1 = ((String) v3_2.get("tags"));
        }
        String v5_3;
        v0_1.setTags(v4_1);
        if (!v3_2.containsKey("post-id")) {
            v5_3 = "";
        } else {
            v5_3 = ((String) v3_2.get("post-id"));
        }
        com.bisimplex.firebooru.danbooru.DanbooruPostImage v3_5;
        if (!v3_2.containsKey("ext")) {
            if (!v3_2.containsKey("fileext")) {
                v3_5 = "";
            } else {
                v3_5 = ((String) v3_2.get("fileext"));
            }
        } else {
            v3_5 = ((String) v3_2.get("ext"));
        }
        java.util.Iterator v7_4 = p15.getElementsByTag("a").iterator();
        while (v7_4.hasNext()) {
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_1 = ((org.jsoup.nodes.Element) v7_4.next());
            int v11_1 = v8_1.attr("href");
            if ((!android.text.TextUtils.isEmpty(v11_1)) && ((!v11_1.equalsIgnoreCase("#")) && (!v8_1.hasAttr("onclick")))) {
                if ((!v8_1.hasClass("shm-thumb-link")) && (!v11_1.contains("/post/view"))) {
                    if (v0_1.getFile() == null) {
                        v0_1.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v11_1, 0, 0));
                        v0_1.setSample(v0_1.getFile());
                        v0_1.setJpeg(v0_1.getFile());
                        if ((!android.text.TextUtils.isEmpty(v3_5)) && (this.isSupportedExtension(v3_5))) {
                            com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_14;
                            v0_1.getFile().setExt(v3_5);
                            if (v3_5.startsWith(".")) {
                                v8_14 = v3_5;
                            } else {
                                v8_14 = String.format(".%s", new Object[] {v3_5}));
                            }
                            if (!v11_1.endsWith(v8_14)) {
                                boolean v9_3 = android.net.Uri.parse(v11_1).buildUpon();
                                v9_3.appendQueryParameter("ext", v8_14);
                                v0_1.getFile().setUrl(v9_3.toString());
                            }
                        }
                    }
                } else {
                    if ((android.text.TextUtils.isEmpty(v5_3)) && (v11_1.contains("/post/view"))) {
                        com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_19 = v11_1.split("/");
                        if (v8_19.length > 1) {
                            v5_3 = v8_19[(v8_19.length - 1)];
                            if (v5_3.contains("?")) {
                                v5_3 = v5_3.split("\\?")[0];
                            }
                        }
                    }
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_24 = p15.getElementsByTag("img");
                    if ((v8_24 != null) && (v8_24.size() > 0)) {
                        com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_26 = ((org.jsoup.nodes.Element) v8_24.get(0));
                        int v11_5 = v8_26.attr("src");
                        if (android.text.TextUtils.isEmpty(v11_5)) {
                            v11_5 = "";
                        }
                        if (v11_5.startsWith("/")) {
                            v11_5 = String.format("%s%s", new Object[] {this.baseUrl, v11_5}));
                        }
                        v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v11_5, 0, 0));
                        boolean v9_7 = v11_5.split("/");
                        if (v9_7.length > 2) {
                            v0_1.setMd5(v9_7[(v9_7.length - 2)]);
                        }
                        com.bisimplex.firebooru.danbooru.DanbooruPostImage v8_28 = v8_26.attr("title").split("//");
                        if ((v8_28.length > 1) && (android.text.TextUtils.isEmpty(v4_1))) {
                            v4_1 = v8_28[0].trim();
                        }
                        if ((v8_28.length > 2) && (android.text.TextUtils.isEmpty(v3_5))) {
                            v3_5 = v8_28[(v8_28.length - 1)];
                        }
                    }
                }
            }
        }
        v0_1.setPostIdAndUrl(v5_3, this.baseUrl, this.postFormat);
        v0_1.setTags(v4_1);
        if ((v0_1.getFile() == null) && (v0_1.getPreview() != null)) {
            if ((android.text.TextUtils.isEmpty(v3_5)) || (!" jpg jpeg png gif mp4 webm webp ".contains(v3_5))) {
                v3_5 = "jpg";
            }
            boolean v15_18 = v0_1.getPreview().getUrl().replace("thumbs", "images");
            if (!android.text.TextUtils.isEmpty(v0_1.getPostId())) {
                v15_18 = v15_18.replace("thumb", v0_1.getPostId());
            }
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v2_7 = new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v15_18, 0, 0);
            if (!v15_18.endsWith(v3_5)) {
                v2_7.setUrlExtension(v3_5);
            }
            v0_1.setFile(v2_7);
            v0_1.setSample(v2_7);
            v0_1.setJpeg(v2_7);
        }
        if (v0_1.getFile() != null) {
            if (v0_1.getFile().getUrl().startsWith("/")) {
                v0_1.getFile().setUrl(String.format("%s%s", new Object[] {this.baseUrl, v0_1.getFile().getUrl()})));
            }
            if (this.shouldAddToResults(v0_1)) {
                this.data.add(v0_1);
            }
        }
        return;
    }
}
