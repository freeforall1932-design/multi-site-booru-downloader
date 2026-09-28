package com.bisimplex.firebooru.parser;
public class ParsePack {
    com.bisimplex.firebooru.data.Query currentFilter;
    com.google.gson.JsonArray data;
    private String html;
    int pageNumber;
    com.bisimplex.firebooru.danbooru.BooruProvider provider;
    private nl.matshofman.saxrssreader.RssFeed rssFeed;

    public ParsePack(com.google.gson.JsonArray p1, int p2, com.bisimplex.firebooru.data.Query p3, com.bisimplex.firebooru.danbooru.BooruProvider p4)
    {
        this.data = p1;
        this.pageNumber = p2;
        this.currentFilter = p3;
        this.provider = p4;
        return;
    }

    public String getHtml()
    {
        return this.html;
    }

    public nl.matshofman.saxrssreader.RssFeed getRssFeed()
    {
        return this.rssFeed;
    }

    public void setHtml(String p1)
    {
        this.html = p1;
        return;
    }

    public void setRssFeed(nl.matshofman.saxrssreader.RssFeed p1)
    {
        this.rssFeed = p1;
        return;
    }
}
