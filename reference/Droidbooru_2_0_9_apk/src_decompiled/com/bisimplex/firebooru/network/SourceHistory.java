package com.bisimplex.firebooru.network;
public class SourceHistory extends com.bisimplex.firebooru.network.SourcePostBasic {
    protected final android.os.Handler enqueueHandler;
    protected final java.util.concurrent.Executor executor;

    public static synthetic void $r8$lambda$We6piy3rSR57A32cH-HaOZZNMRs(com.bisimplex.firebooru.network.SourceHistory p0)
    {
        p0.lambda$loadAnotherPage$1();
        return;
    }

    public static synthetic void $r8$lambda$jxvy6fqbVz2cPWva_Wz_2FG8OrQ(com.bisimplex.firebooru.network.SourceHistory p0, java.util.List p1)
    {
        p0.lambda$loadAnotherPage$0(p1);
        return;
    }

    public SourceHistory(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        super.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        return;
    }

    private synthetic void lambda$loadAnotherPage$0(java.util.List p2)
    {
        this.lastPageCount = p2.size();
        if (this.lastPageCount > 0) {
            this.data.addAll(p2);
        }
        this.notifySuccess(this.data);
        return;
    }

    private synthetic void lambda$loadAnotherPage$1()
    {
        this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourceHistory$$ExternalSyntheticLambda0(this, com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadPostHistory()));
        return;
    }

    public void cancelCurrentConnection()
    {
        return;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.History;
    }

    public void loadAnotherPage()
    {
        this.cancelCurrentConnection();
        this.isLoading = 1;
        this.executor.execute(new com.bisimplex.firebooru.network.SourceHistory$$ExternalSyntheticLambda1(this));
        return;
    }

    protected void notifySuccess(java.util.List p2)
    {
        this.isNewSearch = 0;
        this.isLoading = 0;
        this.isLastPage = 1;
        this.currentPage = 1;
        com.bisimplex.firebooru.network.SourceListener v0_2 = this.getListener();
        if (v0_2 != null) {
            v0_2.success(this, p2);
        }
        return;
    }
}
