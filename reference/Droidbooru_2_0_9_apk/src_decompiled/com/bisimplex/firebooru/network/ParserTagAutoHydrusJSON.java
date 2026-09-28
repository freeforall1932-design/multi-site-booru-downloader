package com.bisimplex.firebooru.network;
public class ParserTagAutoHydrusJSON extends com.bisimplex.firebooru.network.ParserTagJSON {

    public ParserTagAutoHydrusJSON(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    public void parse()
    {
        this.data.clear();
        if (!android.text.TextUtils.isEmpty(this.params.getResponseBody())) {
            try {
                this.parse(com.google.gson.JsonParser.parseString(this.params.getResponseBody()));
                return;
            } catch (com.google.gson.JsonSyntaxException v0_3) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_3);
            }
        }
        return;
    }

    protected void parse(com.google.gson.JsonElement p3)
    {
        if ((p3 != null) && (p3.isJsonObject())) {
            java.util.Iterator v3_4 = p3.getAsJsonObject();
            if (v3_4.has("tags")) {
                java.util.Iterator v3_3 = v3_4.get("tags").getAsJsonArray().iterator();
                while (v3_3.hasNext()) {
                    com.google.gson.JsonObject v0_3 = ((com.google.gson.JsonElement) v3_3.next());
                    if (v0_3.isJsonObject()) {
                        this.parseElement(v0_3.getAsJsonObject());
                    }
                }
                return;
            }
        }
        this.parseFinished();
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p3)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        v0_1.setCount(p3.get("count").getAsInt());
        v0_1.setName(p3.get("value").getAsString());
        int v3_3 = v0_1.getName();
        if (!v3_3.contains("creator:")) {
            if ((!v3_3.contains("character:")) && (!v3_3.contains("person:"))) {
                if ((v3_3.contains("series:")) || (v3_3.contains("title:"))) {
                    v0_1.setType(3);
                }
            } else {
                v0_1.setType(4);
            }
        } else {
            v0_1.setType(1);
        }
        v0_1.setAmbiguous(0);
        this.data.add(v0_1);
        return;
    }
}
