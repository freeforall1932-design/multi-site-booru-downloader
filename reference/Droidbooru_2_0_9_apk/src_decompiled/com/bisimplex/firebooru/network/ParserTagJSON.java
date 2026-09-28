package com.bisimplex.firebooru.network;
public class ParserTagJSON extends com.bisimplex.firebooru.network.ParserTag {

    public ParserTagJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if ((p3 != null) && (p3.isJsonObject())) {
            com.google.gson.JsonArray v3_3 = p3.getAsJsonObject();
            if (v3_3.has("tag")) {
                super.parse(v3_3.get("tag").getAsJsonArray());
                return;
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p4)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        v0_1.setIdx(com.bisimplex.firebooru.network.ParserTagJSON.optInt(p4, "id", 0));
        v0_1.setType(com.bisimplex.firebooru.network.ParserTagJSON.optInt(p4, "type", 0));
        v0_1.setCount(com.bisimplex.firebooru.network.ParserTagJSON.optInt(p4, "count", 0));
        v0_1.setName(com.bisimplex.firebooru.network.ParserTagJSON.optString(p4, "name", ""));
        if (!v0_1.getName().isEmpty()) {
            if (v0_1.getName().contains("#")) {
                v0_1.setName(android.text.Html.fromHtml(v0_1.getName(), 63).toString());
            }
            this.data.add(v0_1);
            return;
        } else {
            return;
        }
    }
}
