package com.bisimplex.firebooru.dataadapter.search;
public class SearchDialogResult {
    private com.bisimplex.firebooru.network.SourceQuery query;
    private java.util.List servers;

    public SearchDialogResult(com.bisimplex.firebooru.network.SourceQuery p1, java.util.List p2)
    {
        this.servers = p2;
        this.query = p1;
        return;
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        return this.query;
    }

    public java.util.List getServers()
    {
        return this.servers;
    }
}
