package com.bisimplex.firebooru.dataadapter.search;
public class Item {
    private String key;
    private String label;
    private com.bisimplex.firebooru.dataadapter.search.ItemType type;

    public Item(com.bisimplex.firebooru.dataadapter.search.ItemType p1, String p2, String p3)
    {
        this.setType(p1);
        this.setKey(p2);
        this.setLabel(p3);
        return;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getLabel()
    {
        return this.label;
    }

    public com.bisimplex.firebooru.dataadapter.search.ItemType getType()
    {
        return this.type;
    }

    public void setKey(String p1)
    {
        this.key = p1;
        return;
    }

    public void setLabel(String p1)
    {
        this.label = p1;
        return;
    }

    public void setType(com.bisimplex.firebooru.dataadapter.search.ItemType p1)
    {
        this.type = p1;
        return;
    }
}
