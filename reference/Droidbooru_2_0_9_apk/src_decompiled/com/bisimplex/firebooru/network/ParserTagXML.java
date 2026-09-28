package com.bisimplex.firebooru.network;
public class ParserTagXML extends com.bisimplex.firebooru.network.ParserTag {

    public ParserTagXML(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    private void parseXML(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            org.jsoup.select.Elements v3_1 = org.jsoup.Jsoup.parse(p3, "", org.jsoup.parser.Parser.xmlParser()).select("tag");
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

    protected void parseElement(org.jsoup.nodes.Element p4)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        int v1_2 = this.tryParseInt(p4.attr("type"));
        v0_1.setName(p4.attr("name"));
        v0_1.setType(v1_2);
        this.data.add(v0_1);
        return;
    }
}
