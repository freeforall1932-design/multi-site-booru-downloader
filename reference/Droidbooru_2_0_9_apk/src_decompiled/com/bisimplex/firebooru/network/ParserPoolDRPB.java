package com.bisimplex.firebooru.network;
public class ParserPoolDRPB extends com.bisimplex.firebooru.network.Parser {

    public ParserPoolDRPB(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parse(com.google.gson.JsonElement p2)
    {
        if ((p2 != null) && (p2.isJsonObject())) {
            java.util.Iterator v2_3 = p2.getAsJsonObject().getAsJsonArray("galleries");
            if (v2_3.isJsonArray()) {
                java.util.Iterator v2_1 = v2_3.iterator();
                while (v2_1.hasNext()) {
                    this.parseElement(((com.google.gson.JsonElement) v2_1.next()).getAsJsonObject());
                }
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p7)
    {
        com.bisimplex.firebooru.model.Pool v0_1 = new com.bisimplex.firebooru.model.Pool();
        v0_1.setPool_id(com.bisimplex.firebooru.network.Parser.optString(p7, "id", ""));
        v0_1.setName(com.bisimplex.firebooru.network.Parser.optString(p7, "title", ""));
        String v1_1 = new StringBuilder();
        String v3_1 = com.bisimplex.firebooru.network.Parser.optString(p7, "description", "");
        if (!android.text.TextUtils.isEmpty(v3_1)) {
            v1_1.append(v3_1);
            v1_1.append("\n");
        }
        String v3_3 = com.bisimplex.firebooru.network.Parser.optString(p7, "spoiler_warning", "");
        if (!android.text.TextUtils.isEmpty(v3_3)) {
            v1_1.append("[Spoiler] ");
            v1_1.append(v3_3);
            v1_1.append("\n");
        }
        java.util.List v7_1 = com.bisimplex.firebooru.network.Parser.optString(p7, "user", "");
        if (!android.text.TextUtils.isEmpty(v7_1)) {
            v1_1.append("By ");
            v1_1.append(v7_1);
            v1_1.append("\n");
        }
        v0_1.setDescription(v1_1.toString().trim());
        v0_1.setUrl(this.provider.generatePoolUniqueUrlWithId(v0_1.getPool_id()));
        this.data.add(v0_1);
        return;
    }
}
