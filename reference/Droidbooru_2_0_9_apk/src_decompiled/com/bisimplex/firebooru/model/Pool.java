package com.bisimplex.firebooru.model;
public class Pool {
    private String description;
    private String name;
    private String pool_id;
    private int post_count;
    private String url;

    public Pool()
    {
        this.pool_id = "";
        this.name = "";
        return;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPool_id()
    {
        return this.pool_id;
    }

    public int getPost_count()
    {
        return this.post_count;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setDescription(String p1)
    {
        this.description = p1;
        return;
    }

    public void setName(String p1)
    {
        this.name = p1;
        return;
    }

    public void setPool_id(String p1)
    {
        this.pool_id = p1;
        return;
    }

    public void setPost_count(int p1)
    {
        this.post_count = p1;
        return;
    }

    public void setUrl(String p1)
    {
        this.url = p1;
        return;
    }
}
