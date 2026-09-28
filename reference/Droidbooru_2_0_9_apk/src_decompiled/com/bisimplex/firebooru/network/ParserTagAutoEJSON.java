package com.bisimplex.firebooru.network;
public class ParserTagAutoEJSON extends com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON {

    public ParserTagAutoEJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p5)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        v0_1.setIdx(com.bisimplex.firebooru.network.ParserTagAutoEJSON.optInt(p5, "id", 0));
        int v1_7 = com.bisimplex.firebooru.network.ParserTagAutoEJSON.optInt(p5, "category", 0);
        if ((v1_7 == 0) || ((v1_7 == 1) || ((v1_7 == 2) || ((v1_7 == 3) || (v1_7 == 4))))) {
            v0_1.setType(v1_7);
        } else {
            if (v1_7 == 7) {
                v0_1.setType(5);
            } else {
                v0_1.setType(0);
            }
        }
        v0_1.setCount(com.bisimplex.firebooru.network.ParserTagAutoEJSON.optInt(p5, "post_count", 0));
        v0_1.setName(com.bisimplex.firebooru.network.ParserTagAutoEJSON.optString(p5, "name", ""));
        if (!android.text.TextUtils.isEmpty(v0_1.getName())) {
            this.data.add(v0_1);
            return;
        } else {
            return;
        }
    }
}
