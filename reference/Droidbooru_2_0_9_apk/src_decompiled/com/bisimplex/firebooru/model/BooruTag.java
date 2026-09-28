package com.bisimplex.firebooru.model;
public class BooruTag {
    private long hits;
    private long id;
    private String name;
    private int type;

    public BooruTag()
    {
        return;
    }

    public BooruTag(long p1, int p3, long p4, String p6)
    {
        this.setId(p1);
        this.setType(p3);
        this.setHits(p4);
        this.setName(p6);
        return;
    }

    public long getHits()
    {
        return this.hits;
    }

    public long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getType()
    {
        return this.type;
    }

    public void setHits(long p1)
    {
        this.hits = p1;
        return;
    }

    public void setId(long p1)
    {
        this.id = p1;
        return;
    }

    public void setName(String p1)
    {
        this.name = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }
}
