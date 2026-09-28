package com.bisimplex.firebooru.network;
public class SourcePost extends com.bisimplex.firebooru.network.SourcePostBasic {
    private boolean disableHistory;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;

    public static synthetic void $r8$lambda$CMhUDbbSOrY5K4JigVbBvfrD9TI(com.bisimplex.firebooru.network.SourcePost p0, java.util.List p1)
    {
        p0.lambda$checkParsedPostsAreFavoritesAsync$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$H-brq_ha1nuKhD49yIyXUKce5c8(com.bisimplex.firebooru.network.SourcePost p0)
    {
        p0.lambda$checkParsedPostsAreFavoritesAsync$0();
        return;
    }

    public SourcePost(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        super.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        return;
    }

    private synthetic void lambda$checkParsedPostsAreFavoritesAsync$0()
    {
        if (this.getListener() != null) {
            this.getListener().reloadVisible();
        }
        return;
    }

    private synthetic void lambda$checkParsedPostsAreFavoritesAsync$1(java.util.List p6)
    {
        com.bisimplex.firebooru.network.SourcePost$$ExternalSyntheticLambda1 v0_0 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        android.os.Handler v6_1 = p6.iterator();
        while (v6_1.hasNext()) {
            int v4_1;
            com.bisimplex.firebooru.danbooru.DanbooruPost v1_1 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_1.next());
            boolean v2 = v1_1.isFavorite();
            boolean v3_1 = v0_0.getIsFavByPost(v1_1.getPostId(), v1_1.getMd5());
            if ((!v2) && (!v3_1)) {
                v4_1 = 0;
            } else {
                v4_1 = 1;
            }
            v1_1.setFavorite(v4_1);
            if (!v2) {
                if (v3_1) {
                    v0_0.updateFavoriteItem(v1_1);
                }
            } else {
                if (!v3_1) {
                    v0_0.addFavoriteItem(v1_1);
                } else {
                    v0_0.updateFavoriteItem(v1_1);
                }
            }
        }
        this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourcePost$$ExternalSyntheticLambda1(this));
        return;
    }

    protected void addHistoryItem()
    {
        if (!this.isDisableHistory()) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addHistoryItem(this.getQuery().getText());
            return;
        } else {
            return;
        }
    }

    protected void checkParsedPostsAreFavoritesAsync(java.util.List p3)
    {
        this.executor.execute(new com.bisimplex.firebooru.network.SourcePost$$ExternalSyntheticLambda0(this, p3));
        return;
    }

    protected void endParsing(com.bisimplex.firebooru.network.Parser p2)
    {
        super.endParsing(p2);
        if ((p2 != null) && ((!p2.getData().isEmpty()) && ((p2 instanceof com.bisimplex.firebooru.network.ParserPosts)))) {
            this.checkParsedPostsAreFavoritesAsync(((com.bisimplex.firebooru.network.ParserPosts) p2).getData());
        }
        return;
    }

    public boolean isDisableHistory()
    {
        return this.disableHistory;
    }

    public void loadAnotherPage()
    {
        super.loadAnotherPage();
        this.addHistoryItem();
        return;
    }

    public void setDisableHistory(boolean p1)
    {
        this.disableHistory = p1;
        return;
    }
}
