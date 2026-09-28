package com.bisimplex.firebooru.danbooru;
public class GelbooruXPATHContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {

    public GelbooruXPATHContentHandler(String p19)
    {
        Exception v0_0 = "img";
        com.bisimplex.firebooru.danbooru.GelbooruXPATHContentHandler v18_1 = ;
if (!android.text.TextUtils.isEmpty(p19)) {
            org.jsoup.select.Elements v2_2 = org.jsoup.Jsoup.parse(p19);
            String v3_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getServerDescription().getUrl();
            String v4_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getPostFormat();
            java.util.regex.Pattern v5_1 = java.util.regex.Pattern.compile("score:[0-9]+", 2);
            java.util.regex.Pattern v7_1 = java.util.regex.Pattern.compile("rating:[a-z]+", 2);
            java.util.regex.Pattern v8_1 = java.util.regex.Pattern.compile("animated_gif", 2);
            java.util.regex.Pattern v10 = java.util.regex.Pattern.compile("webm", 2);
            try {
                org.jsoup.select.Elements v2_0 = v2_2.select("span.thumb a");
                int v11_7 = 0;
                int v12 = 0;
            } catch (Exception v0_18) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_18);
                return;
            }
            while (v12 < v2_0.size()) {
                int v13_4 = ((org.jsoup.nodes.Element) v2_0.get(v12));
                int v14_3 = v13_4.attr("id");
                if ((!android.text.TextUtils.isEmpty(v14_3)) && (v14_3.length() > 1)) {
                    v14_3 = v14_3.substring(1);
                }
                int v17;
                com.bisimplex.firebooru.danbooru.DanbooruPost v15_3 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                v15_3.setPostIdAndUrl(v14_3, v3_1, v4_1);
                v15_3.setHas_children(v11_7);
                v15_3.setMd5("");
                v15_3.setParent_id("");
                v15_3.setRating("");
                v15_3.setSource("");
                v15_3.setScore(v11_7);
                v15_3.setHas_notes(v11_7);
                int v13_5 = v13_4.getElementsByTag(v0_0);
                if ((v13_5 == 0) || (v13_5.size() <= 0)) {
                    v17 = v0_0;
                } else {
                    int v13_7 = ((org.jsoup.nodes.Element) v13_5.get(v11_7));
                    int v14_6 = v13_7.attr("src");
                    if (android.text.TextUtils.isEmpty(v14_6)) {
                        v14_6 = "";
                    }
                    v15_3.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v14_6, v11_7, v11_7));
                    v15_3.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v14_6.replace("thumbs", v0_0).replace("/thumbnails/", "//images/").replace("thumbnail_", "").replace("//aimg.", "//img."), 0, 0));
                    v15_3.setSample(v15_3.getFile());
                    v15_3.setJpeg(v15_3.getFile());
                    v15_3.setEnforceOriginalImage(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isDownloadOriginalImage());
                    com.bisimplex.firebooru.danbooru.GelbooruXPATHContentHandler v6_6 = v13_7.attr("title");
                    if (android.text.TextUtils.isEmpty(v6_6)) {
                        v6_6 = "";
                    }
                    com.bisimplex.firebooru.danbooru.GelbooruXPATHContentHandler v6_9;
                    v15_3.setTags(v6_6);
                    com.bisimplex.firebooru.danbooru.GelbooruXPATHContentHandler v6_8 = v5_1.matcher(v15_3.getTags());
                    if (!v6_8.find()) {
                        v6_9 = "";
                    } else {
                        v6_9 = v6_8.group();
                    }
                    if (android.text.TextUtils.isEmpty(v6_9)) {
                        v17 = v0_0;
                    } else {
                        int v11_5 = v6_9.split(":");
                        v17 = v0_0;
                        if (v11_5.length == 2) {
                            v15_3.setScore(Integer.parseInt(v11_5[1]));
                        }
                        v15_3.setTags(v15_3.getTags().replace(v6_9, ""));
                    }
                    Exception v0_8;
                    Exception v0_7 = v7_1.matcher(v15_3.getTags());
                    if (!v0_7.find()) {
                        v0_8 = "";
                    } else {
                        v0_8 = v0_7.group();
                    }
                    if (android.text.TextUtils.isEmpty(v0_8)) {
                    } else {
                        com.bisimplex.firebooru.danbooru.GelbooruXPATHContentHandler v6_12 = v0_8.split(":");
                        if (v6_12.length == 2) {
                            v15_3.setRating(v6_12[1]);
                        }
                        v15_3.setTags(v15_3.getTags().replace(v0_8, ""));
                    }
                    if (!v8_1.matcher(v15_3.getTags()).find()) {
                        if (v10.matcher(v15_3.getTags()).find()) {
                            v15_3.getFile().setUrlExtension("webm");
                        }
                    } else {
                        v15_3.getFile().setUrlExtension("gif");
                    }
                    if (!com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().isBlacklisted(v15_3)) {
                        try {
                            v18_1.data.add(v15_3);
                        } catch (Exception v0_18) {
                            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_18);
                            return;
                        }
                    }
                }
                v12++;
                v0_0 = v17;
                v11_7 = 0;
            }
        }
        return;
    }
}
