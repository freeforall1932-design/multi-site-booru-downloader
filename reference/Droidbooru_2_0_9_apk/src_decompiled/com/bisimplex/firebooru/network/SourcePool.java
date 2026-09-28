package com.bisimplex.firebooru.network;
public class SourcePool extends com.bisimplex.firebooru.network.Source {

    public SourcePool(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        return;
    }

    protected String generateURLForCurrentPage()
    {
        if ((this.getProvider() != null) && (this.getQuery() != null)) {
            String v0_1 = this.getProvider().generateUrlForPoolsByText(this.getQuery().getText(), this.getCurrentPage());
            if (v0_1 != null) {
                return v0_1.toString();
            }
        }
        return 0;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.Pool;
    }
}
