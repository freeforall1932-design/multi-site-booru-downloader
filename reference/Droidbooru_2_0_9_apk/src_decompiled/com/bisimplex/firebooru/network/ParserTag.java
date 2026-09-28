package com.bisimplex.firebooru.network;
public class ParserTag extends com.bisimplex.firebooru.network.Parser {

    public ParserTag(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        return;
    }

    public void parse()
    {
        super.parse();
        com.bisimplex.firebooru.services.BooruTagHelper.getInstance().feedWithSource(this.data);
        java.util.List v0_11 = this.params.getQuery().getText();
        com.bisimplex.firebooru.danbooru.TagItem v1_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadHistory(v0_11);
        java.util.HashMap v2_1 = new java.util.HashMap();
        int v3_1 = com.bisimplex.firebooru.services.BooruTagHelper.getInstance().tagsForAutocomplete(v0_11);
        java.util.ArrayList v4_1 = new java.util.ArrayList();
        com.bisimplex.firebooru.danbooru.TagItem v1_2 = v1_1.iterator();
        while (v1_2.hasNext()) {
            boolean v5_7 = ((com.bisimplex.firebooru.model.TagHistory) v1_2.next()).search.split(" ");
            int v6_1 = v5_7.length;
            int v8 = 0;
            while (v8 < v6_1) {
                Integer v9_2 = v5_7[v8];
                if (com.bisimplex.firebooru.network.Parser.containsIgnoreCase(v9_2, v0_11)) {
                    String v10_2 = v9_2.toLowerCase(java.util.Locale.US);
                    if (!v2_1.containsKey(v10_2)) {
                        com.bisimplex.firebooru.danbooru.TagItem v11_2 = new com.bisimplex.firebooru.danbooru.TagItem();
                        v11_2.setIdx(0);
                        v11_2.setType(7);
                        v11_2.setCount(0);
                        v11_2.setName(v9_2);
                        v11_2.setAmbiguous(0);
                        v2_1.put(v10_2, Integer.valueOf(v4_1.size()));
                        v4_1.add(v11_2);
                    }
                }
                v8++;
            }
        }
        java.util.List v0_2 = new java.util.ArrayList(this.data);
        if (v3_1.size() > 0) {
            v0_2.addAll(v3_1);
        }
        com.bisimplex.firebooru.danbooru.TagItem v1_7 = new java.util.HashMap();
        java.util.List v0_3 = v0_2.iterator();
        while (v0_3.hasNext()) {
            int v3_9 = ((com.bisimplex.firebooru.danbooru.TagItem) v0_3.next());
            v1_7.put(v3_9.getName(), v3_9);
        }
        java.util.List v0_6 = new java.util.ArrayList(v1_7.values()).iterator();
        while (v0_6.hasNext()) {
            com.bisimplex.firebooru.danbooru.TagItem v1_11 = ((com.bisimplex.firebooru.danbooru.TagItem) v0_6.next());
            int v3_4 = v1_11.getName().toLowerCase(java.util.Locale.US);
            if (!v2_1.containsKey(v3_4)) {
                v4_1.add(v1_11);
            } else {
                int v3_6 = ((Integer) v2_1.get(v3_4));
                if (v3_6 == 0) {
                    v4_1.add(v1_11);
                } else {
                    v4_1.set(v3_6.intValue(), v1_11);
                }
            }
        }
        this.data.clear();
        this.data.addAll(v4_1);
        return;
    }

    protected void parseElement(com.google.gson.JsonObject p4)
    {
        com.bisimplex.firebooru.danbooru.TagItem v0_1 = new com.bisimplex.firebooru.danbooru.TagItem();
        if (!p4.has("Name")) {
            v0_1.setIdx(p4.get("id").getAsInt());
            v0_1.setType(p4.get("type").getAsInt());
            v0_1.setCount(p4.get("count").getAsInt());
            v0_1.setName(p4.get("name").getAsString());
        } else {
            v0_1.setIdx(p4.get("Id").getAsInt());
            v0_1.setType(p4.get("Type").getAsInt());
            v0_1.setCount(p4.get("Count").getAsInt());
            v0_1.setName(p4.get("Name").getAsString());
        }
        v0_1.setAmbiguous(0);
        this.data.add(v0_1);
        return;
    }
}
