package com.bisimplex.firebooru.danbooru;
public class HistoryClient extends com.bisimplex.firebooru.danbooru.SourceProvider {
    private static com.bisimplex.firebooru.danbooru.HistoryClient sharedInstance;

    static HistoryClient()
    {
        return;
    }

    protected HistoryClient()
    {
        this.posts = new java.util.ArrayList();
        this.isLastPage = 1;
        this.isLoading = 0;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.HistoryClient getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.HistoryClient.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.HistoryClient.sharedInstance = new com.bisimplex.firebooru.danbooru.HistoryClient();
        }
        return com.bisimplex.firebooru.danbooru.HistoryClient.sharedInstance;
    }

    public void loadAnOtherPage(com.bisimplex.firebooru.danbooru.TransactionAction p2)
    {
        super.loadAnOtherPage(p2);
        this.loadPosts(this.getFilter());
        p2.success();
        return;
    }

    public java.util.List loadPosts(String p1)
    {
        this.posts = ((java.util.ArrayList) com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadPostHistory());
        return this.posts;
    }
}
