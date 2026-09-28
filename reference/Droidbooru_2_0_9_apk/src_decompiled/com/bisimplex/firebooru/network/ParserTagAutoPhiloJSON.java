package com.bisimplex.firebooru.network;
public class ParserTagAutoPhiloJSON extends com.bisimplex.firebooru.network.ParserTagAutoHydrusJSON {

    public ParserTagAutoPhiloJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p5)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        v0_1.setIdx(com.bisimplex.firebooru.network.ParserTagAutoPhiloJSON.optInt(p5, "id", 0));
        v0_1.setName(com.bisimplex.firebooru.network.ParserTagAutoPhiloJSON.optString(p5, "name", ""));
        v0_1.setCount(com.bisimplex.firebooru.network.ParserTagAutoPhiloJSON.optInt(p5, "images", 0));
        if (!android.text.TextUtils.isEmpty(v0_1.getName())) {
            int v5_1 = com.bisimplex.firebooru.network.ParserTagAutoPhiloJSON.optString(p5, "category", 0);
            if (!android.text.TextUtils.isEmpty(v5_1)) {
                if ((!v5_1.equalsIgnoreCase("character")) && ((!v5_1.equalsIgnoreCase("oc")) && (!v5_1.equalsIgnoreCase("species")))) {
                    if ((v5_1.equalsIgnoreCase("content-fanmade")) || (v5_1.equalsIgnoreCase("content-official"))) {
                        v0_1.setType(3);
                    }
                } else {
                    v0_1.setType(4);
                }
            }
            if (com.bisimplex.firebooru.danbooru.BooruProvider.containsIgnoreCase(v0_1.getName(), "artist:")) {
                v0_1.setType(1);
            }
            v0_1.setAmbiguous(0);
            this.data.add(v0_1);
            return;
        } else {
            return;
        }
    }
}
