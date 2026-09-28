package com.bisimplex.firebooru.network;
public class ParserNoteJSON extends com.bisimplex.firebooru.network.ParserNote {

    public ParserNoteJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p7)
    {
        if ((p7.has("x")) && ((p7.has("y")) && (com.bisimplex.firebooru.network.ParserNoteJSON.optBoolean(p7, "is_active", 1)))) {
            com.bisimplex.firebooru.danbooru.NoteItem v2_2 = new com.bisimplex.firebooru.danbooru.NoteItem();
            v2_2.setBody(com.bisimplex.firebooru.danbooru.Flatten.flattenHTML(com.bisimplex.firebooru.network.ParserNoteJSON.optString(p7, "body", "")));
            int v0_2 = p7.get("x").getAsInt();
            int v1_2 = p7.get("y").getAsInt();
            int v3_5 = p7.get("width").getAsInt();
            java.util.List v7_2 = p7.get("height").getAsInt();
            float v4_3 = this.params.getProportion();
            v2_2.setOriginalFrame(new android.graphics.Rect(v0_2, v1_2, v3_5, v7_2));
            v2_2.setFrame(new android.graphics.Rect(((int) (((float) v0_2) * v4_3)), ((int) (((float) v1_2) * v4_3)), ((int) (((float) v3_5) * v4_3)), ((int) (((float) v7_2) * v4_3))));
            this.data.add(v2_2);
        }
        return;
    }
}
