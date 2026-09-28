package com.bisimplex.firebooru.network;
public class ParserParams {
    private int currentPage;
    private boolean disableVisibilityChecks;
    private float proportion;
    private com.bisimplex.firebooru.danbooru.BooruProvider provider;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private String responseBody;
    private com.bisimplex.firebooru.network.SourceType type;

    public ParserParams(String p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3, com.bisimplex.firebooru.danbooru.BooruProvider p4, com.bisimplex.firebooru.network.SourceType p5)
    {
        this.responseBody = p1;
        this.currentPage = p2;
        this.query = p3;
        this.provider = p4;
        this.type = p5;
        return;
    }

    public int getCurrentPage()
    {
        return this.currentPage;
    }

    public float getProportion()
    {
        return this.proportion;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        return this.provider;
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        return this.query;
    }

    public String getResponseBody()
    {
        return this.responseBody;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return this.type;
    }

    public boolean isDisableVisibilityChecks()
    {
        return this.disableVisibilityChecks;
    }

    public void setDisableVisibilityChecks(boolean p1)
    {
        this.disableVisibilityChecks = p1;
        return;
    }

    public void setProportion(float p1)
    {
        this.proportion = p1;
        return;
    }
}
