package com.bisimplex.firebooru.network;
public class ParserTagAutoDanbooruJSON extends com.bisimplex.firebooru.network.ParserTag {

    public ParserTagAutoDanbooruJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p5)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        v0_1.setIdx(com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON.optInt(p5, "id", 0));
        v0_1.setType(com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON.optInt(p5, "category", 0));
        v0_1.setCount(com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON.optInt(p5, "post_count", 0));
        v0_1.setName(com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON.optString(p5, "name", ""));
        if ((!android.text.TextUtils.isEmpty(v0_1.getName())) && (!com.bisimplex.firebooru.network.ParserTagAutoDanbooruJSON.optBoolean(p5, "is_deprecated", 0))) {
            v0_1.setAmbiguous(0);
            this.data.add(v0_1);
        }
        return;
    }
}
