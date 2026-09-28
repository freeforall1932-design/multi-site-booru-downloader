package com.bisimplex.firebooru.network;
public class ParserPoolDanbooru2JSON extends com.bisimplex.firebooru.network.Parser {

    public ParserPoolDanbooru2JSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p5)
    {
        com.bisimplex.firebooru.model.Pool v0_1 = new com.bisimplex.firebooru.model.Pool();
        v0_1.setPool_id(com.bisimplex.firebooru.network.Parser.optString(p5, "id", ""));
        v0_1.setName(com.bisimplex.firebooru.network.Parser.optString(p5, "name", ""));
        v0_1.setPost_count(com.bisimplex.firebooru.network.Parser.optInt(p5, "post_count", 0));
        v0_1.setDescription(com.bisimplex.firebooru.network.Parser.optString(p5, "description", ""));
        v0_1.setUrl(this.provider.generatePoolUniqueUrlWithId(v0_1.getPool_id()));
        this.data.add(v0_1);
        return;
    }
}
