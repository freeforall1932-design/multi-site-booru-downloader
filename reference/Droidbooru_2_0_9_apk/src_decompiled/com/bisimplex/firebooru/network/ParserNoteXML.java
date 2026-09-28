package com.bisimplex.firebooru.network;
public class ParserNoteXML extends com.bisimplex.firebooru.network.ParserNote {

    public ParserNoteXML(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    private void parseXML(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            org.jsoup.select.Elements v3_1 = org.jsoup.Jsoup.parse(p3, "", org.jsoup.parser.Parser.xmlParser()).select("note");
            int v0_1 = 0;
            while (v0_1 < v3_1.size()) {
                this.parseElement(((org.jsoup.nodes.Element) v3_1.get(v0_1)));
                v0_1++;
            }
        }
        return;
    }

    public void parse()
    {
        String v0_1 = this.params.getResponseBody();
        this.data.clear();
        this.parseXML(v0_1);
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p1)
    {
        return;
    }

    protected void parseElement(org.jsoup.nodes.Element p8)
    {
        com.bisimplex.firebooru.danbooru.NoteItem v0_1 = new com.bisimplex.firebooru.danbooru.NoteItem();
        String v1_6 = this.tryParseInt(p8.attr("x"));
        int v2_0 = this.tryParseInt(p8.attr("y"));
        int v3_2 = this.tryParseInt(p8.attr("width"));
        int v4_2 = this.tryParseInt(p8.attr("height"));
        float v5_1 = this.params.getProportion();
        v0_1.setOriginalFrame(new android.graphics.Rect(v1_6, v2_0, v3_2, v4_2));
        v0_1.setFrame(new android.graphics.Rect(((int) (((float) v1_6) * v5_1)), ((int) (((float) v2_0) * v5_1)), ((int) (((float) v3_2) * v5_1)), ((int) (((float) v4_2) * v5_1))));
        v0_1.setBody(com.bisimplex.firebooru.danbooru.Flatten.flattenHTML(p8.attr("body")));
        this.data.add(v0_1);
        return;
    }
}
