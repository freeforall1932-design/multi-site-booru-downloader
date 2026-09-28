package com.bisimplex.firebooru.parser;
public class Parser extends org.xml.sax.helpers.DefaultHandler {
    protected java.util.ArrayList data;
    private int pageNumber;
    private com.bisimplex.firebooru.danbooru.BooruProvider provider;

    public Parser()
    {
        this.data = new java.util.ArrayList();
        return;
    }

    public int getPageNumber()
    {
        return this.pageNumber;
    }

    public java.util.ArrayList getParsedData()
    {
        return this.data;
    }

    protected com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        if (this.provider == null) {
            this.provider = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
        }
        return this.provider;
    }

    protected boolean optBoolean(com.google.gson.JsonObject p2, String p3, boolean p4)
    {
        if ((!p2) || (!p2.has(p3))) {
            return p4;
        } else {
            return p2.get(p3).getAsBoolean();
        }
    }

    protected int optInt(com.google.gson.JsonObject p2, String p3, int p4)
    {
        if ((p2 != 0) && (p2.has(p3))) {
            int v2_2 = p2.get(p3);
            if (!v2_2.isJsonNull()) {
                return v2_2.getAsInt();
            }
        }
        return p4;
    }

    protected String optString(com.google.gson.JsonObject p2, String p3, String p4)
    {
        if ((p2 != null) && (p2.has(p3))) {
            String v2_2 = p2.get(p3);
            if (!v2_2.isJsonNull()) {
                return v2_2.getAsString();
            }
        }
        return p4;
    }

    public void setPageNumber(int p1)
    {
        this.pageNumber = p1;
        return;
    }

    public void setProvider(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        this.provider = p1;
        return;
    }

    protected int tryParseInt(String p2)
    {
        try {
            return Integer.parseInt(p2);
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }
}
