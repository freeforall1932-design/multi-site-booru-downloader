package com.bisimplex.firebooru.parser;
public class Danbooru2NotesParser extends com.bisimplex.firebooru.parser.GelbooruNotesParser {

    public Danbooru2NotesParser(com.google.gson.JsonArray p1, float p2)
    {
        super(p2);
        super.parse(p1);
        return;
    }

    protected void parse(com.google.gson.JsonArray p14)
    {
        int v5 = 0;
        try {
            while (v5 < p14.size()) {
                java.util.ArrayList v6_1 = p14.get(v5).getAsJsonObject();
                if (this.optBoolean(v6_1, "is_active", 1)) {
                    com.bisimplex.firebooru.danbooru.NoteItem v7_3 = new com.bisimplex.firebooru.danbooru.NoteItem();
                    v7_3.setBody(com.bisimplex.firebooru.danbooru.Flatten.flattenHTML(v6_1.get("body").getAsString()));
                    v7_3.setOriginalFrame(new android.graphics.Rect(v6_1.get("x").getAsInt(), v6_1.get("y").getAsInt(), v6_1.get("width").getAsInt(), v6_1.get("height").getAsInt()));
                    v7_3.setFrame(new android.graphics.Rect(((int) (((float) v6_1.get("x").getAsInt()) * this.proportion)), ((int) (((float) v6_1.get("y").getAsInt()) * this.proportion)), ((int) (((float) v6_1.get("width").getAsInt()) * this.proportion)), ((int) (((float) v6_1.get("height").getAsInt()) * this.proportion))));
                    this.data.add(v7_3);
                } else {
                }
                v5++;
            }
        } catch (Exception v14_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v14_1);
            return;
        }
        this.canLoadMore = 0;
        return;
    }
}
