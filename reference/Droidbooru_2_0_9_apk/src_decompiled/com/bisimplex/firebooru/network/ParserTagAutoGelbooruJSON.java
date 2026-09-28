package com.bisimplex.firebooru.network;
public class ParserTagAutoGelbooruJSON extends com.bisimplex.firebooru.network.ParserTag {

    public ParserTagAutoGelbooruJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p6)
    {
        if (com.bisimplex.firebooru.network.ParserTagAutoGelbooruJSON.optString(p6, "type", "").equalsIgnoreCase("tag")) {
            com.bisimplex.firebooru.danbooru.TagItem v0_4 = new com.bisimplex.firebooru.danbooru.TagItem();
            v0_4.setCount(com.bisimplex.firebooru.network.ParserTagAutoGelbooruJSON.optInt(p6, "post_count", 0));
            v0_4.setName(com.bisimplex.firebooru.network.ParserTagAutoGelbooruJSON.optString(p6, "value", ""));
            if (!android.text.TextUtils.isEmpty(v0_4.getName())) {
                int v6_1 = com.bisimplex.firebooru.network.ParserTagAutoGelbooruJSON.optString(p6, "category", "tag");
                if (!v6_1.equalsIgnoreCase("tag")) {
                    if (!v6_1.equalsIgnoreCase("character")) {
                        if (!v6_1.equalsIgnoreCase("copyright")) {
                            if (v6_1.equalsIgnoreCase("artist")) {
                                v0_4.setType(1);
                            }
                        } else {
                            v0_4.setType(3);
                        }
                    } else {
                        v0_4.setType(4);
                    }
                } else {
                    v0_4.setType(0);
                }
                v0_4.setAmbiguous(0);
                this.data.add(v0_4);
            }
        }
        return;
    }
}
