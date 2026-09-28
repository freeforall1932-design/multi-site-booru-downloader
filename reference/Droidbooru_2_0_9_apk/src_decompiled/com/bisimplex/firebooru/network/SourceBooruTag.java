package com.bisimplex.firebooru.network;
public class SourceBooruTag extends com.bisimplex.firebooru.network.SourceTag {

    public SourceBooruTag(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        return;
    }

    protected String generateURLForCurrentPage()
    {
        return this.provider.generateMetadataTagURL(this.getQuery().getText());
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.BooruTag;
    }
}
