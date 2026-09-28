package com.bisimplex.firebooru.parser;
public class GelbooruXPATHParser extends com.bisimplex.firebooru.parser.GelbooruParser {

    public GelbooruXPATHParser(String p20, com.bisimplex.firebooru.danbooru.BooruProvider p21)
    {
        Exception v0_0 = "img";
        com.bisimplex.firebooru.parser.GelbooruXPATHParser v19_1 = ;
if (!android.text.TextUtils.isEmpty(p20)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v2_1;
            if (p21 != null) {
                v2_1 = p21;
            } else {
                v2_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
            }
            com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_2 = org.jsoup.Jsoup.parse(p20);
            String v4_1 = v2_1.getServerDescription().getUrl();
            String v5 = v2_1.getPostFormat();
            java.util.regex.Pattern v6_1 = java.util.regex.Pattern.compile("score:[0-9]+", 2);
            java.util.regex.Pattern v8_1 = java.util.regex.Pattern.compile("rating:[a-z]+", 2);
            java.util.regex.Pattern v9_1 = java.util.regex.Pattern.compile("animated_gif", 2);
            java.util.regex.Pattern v11 = java.util.regex.Pattern.compile("webm", 2);
            try {
                com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_18 = v3_2.select("span.thumb a");
                int v13 = 0;
            } catch (Exception v0_18) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_18);
                return;
            }
            while (v13 < v3_18.size()) {
                int v14_4 = ((org.jsoup.nodes.Element) v3_18.get(v13));
                int v15_4 = v14_4.attr("id");
                if ((!android.text.TextUtils.isEmpty(v15_4)) && (v15_4.length() > 1)) {
                    v15_4 = v15_4.substring(1);
                }
                int v18;
                com.bisimplex.firebooru.parser.GelbooruXPATHParser v17;
                com.bisimplex.firebooru.danbooru.DanbooruPost v12_3 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
                v12_3.setPostIdAndUrl(v15_4, v4_1, v5);
                v12_3.setHas_children(0);
                v12_3.setMd5("");
                v12_3.setParent_id("");
                v12_3.setRating("");
                v12_3.setSource("");
                v12_3.setScore(0);
                v12_3.setHas_notes(0);
                int v14_5 = v14_4.getElementsByTag(v0_0);
                if ((v14_5 == 0) || (v14_5.size() <= 0)) {
                    v18 = v0_0;
                    v17 = v3_18;
                } else {
                    int v14_7 = ((org.jsoup.nodes.Element) v14_5.get(0));
                    int v15_7 = v14_7.attr("src");
                    if (android.text.TextUtils.isEmpty(v15_7)) {
                        v15_7 = "";
                    }
                    v17 = v3_18;
                    v12_3.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v15_7, 0, 0));
                    v12_3.setFile(new com.bisimplex.firebooru.danbooru.DanbooruPostImage(v15_7.replace("thumbs", v0_0).replace("/thumbnails/", "//images/").replace("thumbnail_", "").replace("//aimg.", "//img."), 0, 0));
                    v12_3.setSample(v12_3.getFile());
                    v12_3.setJpeg(v12_3.getFile());
                    v12_3.setEnforceOriginalImage(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isDownloadOriginalImage());
                    com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_7 = v14_7.attr("title");
                    if (android.text.TextUtils.isEmpty(v3_7)) {
                        v3_7 = "";
                    }
                    com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_10;
                    v12_3.setTags(v3_7);
                    com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_9 = v6_1.matcher(v12_3.getTags());
                    if (!v3_9.find()) {
                        v3_10 = "";
                    } else {
                        v3_10 = v3_9.group();
                    }
                    if (android.text.TextUtils.isEmpty(v3_10)) {
                        v18 = v0_0;
                    } else {
                        int v7_5 = v3_10.split(":");
                        v18 = v0_0;
                        if (v7_5.length == 2) {
                            v12_3.setScore(Integer.parseInt(v7_5[1]));
                        }
                        v12_3.setTags(v12_3.getTags().replace(v3_10, ""));
                    }
                    Exception v0_8;
                    Exception v0_7 = v8_1.matcher(v12_3.getTags());
                    if (!v0_7.find()) {
                        v0_8 = "";
                    } else {
                        v0_8 = v0_7.group();
                    }
                    if (android.text.TextUtils.isEmpty(v0_8)) {
                    } else {
                        com.bisimplex.firebooru.parser.GelbooruXPATHParser v3_13 = v0_8.split(":");
                        if (v3_13.length == 2) {
                            v12_3.setRating(v3_13[1]);
                        }
                        v12_3.setTags(v12_3.getTags().replace(v0_8, ""));
                    }
                    if (!v9_1.matcher(v12_3.getTags()).find()) {
                        if (v11.matcher(v12_3.getTags()).find()) {
                            v12_3.getFile().setUrlExtension("webm");
                        }
                    } else {
                        v12_3.getFile().setUrlExtension("gif");
                    }
                    if (!v2_1.isBlacklisted(v12_3)) {
                        try {
                            v19_1.data.add(v12_3);
                        } catch (Exception v0_18) {
                            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_18);
                            return;
                        }
                    }
                }
                v13++;
                v3_18 = v17;
                v0_0 = v18;
            }
        }
        return;
    }
}
