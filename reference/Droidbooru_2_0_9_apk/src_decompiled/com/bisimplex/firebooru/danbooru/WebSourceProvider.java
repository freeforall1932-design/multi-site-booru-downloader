package com.bisimplex.firebooru.danbooru;
public class WebSourceProvider extends com.bisimplex.firebooru.danbooru.DanbooruClient {

    public WebSourceProvider(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        super(p1);
        return;
    }

    protected void errorOccured(int p2)
    {
        this.isLastPage = 0;
        this.isLoading = 0;
        if (this.callbackListener != null) {
            this.callbackListener.error(com.bisimplex.firebooru.danbooru.FailureType.fromInteger(p2));
        }
        return;
    }

    public void setFilter(String p2)
    {
        this.isLastPage = 0;
        this.isLoading = 0;
        this.currentFilter = p2;
        this.isNewSearch = 1;
        this.currentPageLoaded = (this.provider.getInitialPageNumber() - 1);
        return;
    }
}
