package com.bisimplex.firebooru.custom;
public class DownloadTarget extends com.bumptech.glide.request.target.CustomTarget implements com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener {
    private boolean addToHistory;
    private ref.WeakReference listener;
    private com.bisimplex.firebooru.danbooru.DanbooruPost post;
    private String url;

    public DownloadTarget(com.bisimplex.firebooru.custom.DownloadTargetListener p1, String p2, com.bisimplex.firebooru.danbooru.DanbooruPost p3, boolean p4)
    {
        this.setListener(p1);
        this.url = p2;
        this.post = p3;
        this.addToHistory = p4;
        return;
    }

    public float getGranualityPercentage()
    {
        return 1065353216;
    }

    public void onLoadCleared(android.graphics.drawable.Drawable p1)
    {
        return;
    }

    public void onLoadFailed(android.graphics.drawable.Drawable p2)
    {
        com.bisimplex.firebooru.custom.DownloadTargetListener v2_0 = this.listener;
        if ((v2_0 != null) && (v2_0.get() != null)) {
            ((com.bisimplex.firebooru.custom.DownloadTargetListener) this.listener.get()).onLoadFailed(this.url);
        }
        com.bisimplex.firebooru.custom.MyGlideModule.forget(this.url);
        this.listener = 0;
        return;
    }

    public void onProgress(long p2, long p4)
    {
        com.bisimplex.firebooru.custom.DownloadTargetListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.custom.DownloadTargetListener) this.listener.get()).onProgress(p2, p4);
        }
        return;
    }

    public void onResourceReady(Object p8, com.bumptech.glide.request.transition.Transition p9)
    {
        Object v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.custom.DownloadTargetListener) this.listener.get()).onResourceReady(p8, p9, this.url, this.post, this.addToHistory);
        }
        com.bisimplex.firebooru.custom.MyGlideModule.forget(this.url);
        this.listener = 0;
        return;
    }

    public void setListener(com.bisimplex.firebooru.custom.DownloadTargetListener p2)
    {
        this.listener = new ref.WeakReference(p2);
        return;
    }
}
