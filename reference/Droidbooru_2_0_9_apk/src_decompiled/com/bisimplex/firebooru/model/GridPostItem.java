package com.bisimplex.firebooru.model;
public class GridPostItem {
    public static final int TYPE_POST = 0;
    public static final int TYPE_SOURCE = 1;
    private String label;
    private com.bisimplex.firebooru.danbooru.DanbooruPost post;
    private int type;

    public GridPostItem(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.setPost(p1);
        this.setType(0);
        return;
    }

    public GridPostItem(String p1)
    {
        this.setLabel(p1);
        this.setType(1);
        return;
    }

    public String getLabel()
    {
        return this.label;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getPost()
    {
        return this.post;
    }

    public int getType()
    {
        return this.type;
    }

    public void setLabel(String p1)
    {
        this.label = p1;
        return;
    }

    public void setPost(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.post = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }
}
