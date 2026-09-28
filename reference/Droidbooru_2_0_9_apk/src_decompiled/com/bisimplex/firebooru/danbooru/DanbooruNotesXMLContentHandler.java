package com.bisimplex.firebooru.danbooru;
public class DanbooruNotesXMLContentHandler extends com.bisimplex.firebooru.danbooru.GelbooruXMLContentHandler {
    protected java.util.ArrayList data;
    protected float proportion;

    public DanbooruNotesXMLContentHandler()
    {
        this.data = new java.util.ArrayList();
        return;
    }

    public java.util.ArrayList getParsedNotes()
    {
        return this.data;
    }

    public float getProportion()
    {
        return this.proportion;
    }

    public void setProportion(float p1)
    {
        this.proportion = p1;
        return;
    }

    public void startElement(String p8, String p9, String p10, org.xml.sax.Attributes p11)
    {
        if (p10.equalsIgnoreCase("note")) {
            com.bisimplex.firebooru.danbooru.NoteItem v8_3 = new com.bisimplex.firebooru.danbooru.NoteItem();
            v8_3.setOriginalFrame(new android.graphics.Rect(this.tryParseInt(p11.getValue("x")), this.tryParseInt(p11.getValue("y")), this.tryParseInt(p11.getValue("width")), this.tryParseInt(p11.getValue("height"))));
            v8_3.setFrame(new android.graphics.Rect(((int) (((float) this.tryParseInt(p11.getValue("x"))) * this.proportion)), ((int) (((float) this.tryParseInt(p11.getValue("y"))) * this.proportion)), ((int) (((float) this.tryParseInt(p11.getValue("width"))) * this.proportion)), ((int) (((float) this.tryParseInt(p11.getValue("height"))) * this.proportion))));
            v8_3.setBody(com.bisimplex.firebooru.danbooru.Flatten.flattenHTML(p11.getValue("body")));
            this.data.add(v8_3);
            return;
        } else {
            return;
        }
    }
}
