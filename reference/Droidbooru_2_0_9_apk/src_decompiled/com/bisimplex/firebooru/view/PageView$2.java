package com.bisimplex.firebooru.view;
 class PageView$2 implements com.bisimplex.firebooru.custom.DownloadTargetListener {
    final synthetic com.bisimplex.firebooru.view.PageView this$0;

    PageView$2(com.bisimplex.firebooru.view.PageView p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onLoadFailed(String p2)
    {
        this.this$0.reportLoadFailed(p2);
        return;
    }

    public void onProgress(long p2, long p4)
    {
        this.this$0.reportProgress(p2, p4);
        return;
    }

    public void onResourceReady(java.io.File p5, com.bumptech.glide.request.transition.Transition p6, String p7, com.bisimplex.firebooru.danbooru.DanbooruPost p8, boolean p9)
    {
        if ((this.this$0.getContext() != null) && ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgetthumbImageView(this.this$0) != null) && ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0) != null) && ((p8 != null) && ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0) == p8) || (com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0).getPostId().equalsIgnoreCase(p8.getPostId()))))))) {
            com.bisimplex.firebooru.view.PageView.-$$Nest$fgetthumbImageView(this.this$0).setVisibility(8);
            com.bisimplex.firebooru.view.PageView.-$$Nest$fputisLoading(this.this$0, 0);
            if ((p5.exists()) && (p5.length() != 0)) {
                this.this$0.finishedLoading(p8, p5);
            } else {
                if (p5.delete()) {
                    android.util.Log.i("PageView", "Empty file deleted");
                }
                this.this$0.reportLoadFailed(p7);
            }
            com.bisimplex.firebooru.view.PageView v5_4 = this.this$0;
            com.bisimplex.firebooru.view.PageView.-$$Nest$mapplyFadeOutAnimation(v5_4, com.bisimplex.firebooru.view.PageView.-$$Nest$fgetloading_bottom(v5_4));
        }
        return;
    }

    public bridge synthetic void onResourceReady(Object p1, com.bumptech.glide.request.transition.Transition p2, String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
        return;
    }
}
