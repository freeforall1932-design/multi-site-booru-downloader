package com.bisimplex.firebooru.danbooru;
public class Danbooru2NotesJSONContentHandler extends com.bisimplex.firebooru.danbooru.DanbooruNotesXMLContentHandler {
    private boolean canLoadMore;

    public Danbooru2NotesJSONContentHandler(org.json.JSONArray p14, float p15)
    {
        this.proportion = p15;
        this.data = new java.util.ArrayList();
        int v15_1 = 0;
        int v4 = 0;
        int v5 = 0;
        try {
            while (v4 < p14.length()) {
                java.util.ArrayList v6_1 = p14.getJSONObject(v4);
                if (v6_1.getBoolean("is_active")) {
                    com.bisimplex.firebooru.danbooru.NoteItem v7_3 = new com.bisimplex.firebooru.danbooru.NoteItem();
                    v7_3.setBody(com.bisimplex.firebooru.danbooru.Flatten.flattenHTML(v6_1.getString("body")));
                    v7_3.setOriginalFrame(new android.graphics.Rect(v6_1.getInt("x"), v6_1.getInt("y"), v6_1.getInt("width"), v6_1.getInt("height")));
                    v7_3.setFrame(new android.graphics.Rect(((int) (((float) v6_1.getInt("x")) * this.proportion)), ((int) (((float) v6_1.getInt("y")) * this.proportion)), ((int) (((float) v6_1.getInt("width")) * this.proportion)), ((int) (((float) v6_1.getInt("height")) * this.proportion))));
                    this.data.add(v7_3);
                    v5++;
                } else {
                }
                v4++;
            }
        } catch (org.json.JSONException v14_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v14_1);
            return;
        }
        if (v5 > 0) {
            v15_1 = 1;
        }
        this.canLoadMore = v15_1;
        return;
    }

    public boolean isCanLoadMore()
    {
        return this.canLoadMore;
    }

    public void setCanLoadMore(boolean p1)
    {
        this.canLoadMore = p1;
        return;
    }
}
