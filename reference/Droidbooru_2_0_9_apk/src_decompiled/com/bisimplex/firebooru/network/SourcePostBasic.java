package com.bisimplex.firebooru.network;
public class SourcePostBasic extends com.bisimplex.firebooru.network.Source {
    private boolean disableVisiblityChecks;
    private java.util.List file_ids;
    private int lastPageRemovedCount;
    private String rating_service_key;
    private String serverMessage;
    private int visiblePostIndex;

    public SourcePostBasic(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.disableVisiblityChecks = 0;
        return;
    }

    public void configureParserParams(com.bisimplex.firebooru.network.ParserParams p2)
    {
        super.configureParserParams(p2);
        p2.setDisableVisibilityChecks(this.disableVisiblityChecks);
        return;
    }

    protected void endParsing(com.bisimplex.firebooru.network.Parser p5)
    {
        this.serverMessage = 0;
        com.bisimplex.firebooru.services.BooruTagHelper v0_1 = (p5 instanceof com.bisimplex.firebooru.network.ParserPosts);
        if (v0_1 != null) {
            this.lastPageRemovedCount = ((com.bisimplex.firebooru.network.ParserPosts) p5).getRemovedCount();
            this.serverMessage = ((com.bisimplex.firebooru.network.ParserPosts) p5).getServerMessage();
        }
        if (!(p5 instanceof com.bisimplex.firebooru.network.ParserHydrusJSON)) {
            super.endParsing(p5);
            if ((!p5.getData().isEmpty()) && ((this.getProvider().canFeedTagMetadata()) && (v0_1 != null))) {
                com.bisimplex.firebooru.services.BooruTagHelper.getInstance().feedWithSourceAsync(((com.bisimplex.firebooru.network.ParserPosts) p5).getData());
            }
        } else {
            java.util.List v1_6 = this.file_ids;
            if ((v1_6 != null) && (!v1_6.isEmpty())) {
                java.util.List v1_9 = this.getItemCount();
                ((com.bisimplex.firebooru.network.ParserHydrusJSON) p5).sortWithFileIds(this.file_ids.subList(v1_9, (((com.bisimplex.firebooru.network.ParserHydrusJSON) p5).getData().size() + v1_9)));
                this.rating_service_key = ((com.bisimplex.firebooru.network.ParserHydrusJSON) p5).getRating_service_key();
                super.endParsing(p5);
                if (this.file_ids.size() == this.getItemCount()) {
                    this.isLastPage = 1;
                    return;
                }
            } else {
                com.bisimplex.firebooru.services.BooruTagHelper v0_7 = ((com.bisimplex.firebooru.network.ParserHydrusJSON) p5).getFile_ids();
                this.file_ids = v0_7;
                if (!v0_7.isEmpty()) {
                    this.loadAnotherPage();
                    return;
                } else {
                    super.endParsing(p5);
                    return;
                }
            }
        }
        return;
    }

    public void forceLoadNextPage()
    {
        if ((this.lastPageRemovedCount > 0) && (this.isLastPage)) {
            this.isLastPage = 0;
            this.currentPage = (this.currentPage + 1);
            this.loadAnotherPage();
        }
        return;
    }

    protected String generateURLForCurrentPage()
    {
        if ((this.getProvider() != null) && (this.getQuery() != null)) {
            if (this.getProvider().getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                String v0_2 = this.file_ids;
                if ((v0_2 != null) && (!v0_2.isEmpty())) {
                    return this.getProvider().metadataURLWithArray(this.file_ids, this.getItemCount());
                }
            }
            String v0_5 = this.getProvider().generateRequestUrl(this.getQuery(), this.getCurrentPage());
            if (v0_5 != null) {
                return v0_5.toString();
            }
        }
        return 0;
    }

    public int getLastPageRemovedCount()
    {
        return this.lastPageRemovedCount;
    }

    public String getRating_service_key()
    {
        return this.rating_service_key;
    }

    public String getServerMessage()
    {
        return this.serverMessage;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.Post;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getVisiblePost()
    {
        return ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.getItemAt(this.getVisiblePostIndex()));
    }

    public int getVisiblePostIndex()
    {
        return this.visiblePostIndex;
    }

    public void reset()
    {
        super.reset();
        java.util.List v0 = this.file_ids;
        if (v0 != null) {
            v0.clear();
        }
        return;
    }

    public void setDisableVisiblityChecks(boolean p1)
    {
        this.disableVisiblityChecks = p1;
        return;
    }

    public void setRating_service_key(String p1)
    {
        this.rating_service_key = p1;
        return;
    }

    public void setServerMessage(String p1)
    {
        this.serverMessage = p1;
        return;
    }

    public void setVisiblePostIndex(int p1)
    {
        this.visiblePostIndex = p1;
        return;
    }
}
