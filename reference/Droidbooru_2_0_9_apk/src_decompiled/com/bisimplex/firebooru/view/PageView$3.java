package com.bisimplex.firebooru.view;
 class PageView$3 implements com.bisimplex.firebooru.custom.DownloadTargetListener {
    final synthetic com.bisimplex.firebooru.view.PageView this$0;

    PageView$3(com.bisimplex.firebooru.view.PageView p1)
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

    public void onResourceReady(android.graphics.drawable.Drawable p1, com.bumptech.glide.request.transition.Transition p2, String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        if ((this.this$0.getContext() != null) && ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0) != null) && ((p4 != null) && ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0) == p4) || (com.bisimplex.firebooru.view.PageView.-$$Nest$fgettargetPost(this.this$0).getPostId().equalsIgnoreCase(p4.getPostId())))))) {
            com.bisimplex.firebooru.view.PageView.-$$Nest$fputisLoading(this.this$0, 0);
            androidx.appcompat.widget.AppCompatImageView v2_8 = p4.getVisibleVersion();
            if ((v2_8.getWidth() == 0) || (v2_8.getHeight() == 0)) {
                v2_8.setWidth(p1.getIntrinsicWidth());
                v2_8.setHeight(p1.getIntrinsicHeight());
            }
            this.this$0.finishedLoading(p4, 0);
            androidx.appcompat.widget.AppCompatImageView v2_11 = this.this$0;
            com.bisimplex.firebooru.view.PageView.-$$Nest$mapplyFadeOutAnimation(v2_11, com.bisimplex.firebooru.view.PageView.-$$Nest$fgetloading_bottom(v2_11));
            androidx.appcompat.widget.AppCompatImageView v2_13 = this.this$0.getImageView();
            if (v2_13 != null) {
                v2_13.setImageDrawable(p1);
            }
            if ((p1 instanceof android.graphics.drawable.Animatable)) {
                ((android.graphics.drawable.Animatable) p1).start();
            }
            com.bisimplex.firebooru.view.PageView v1_2 = this.this$0;
            com.bisimplex.firebooru.view.PageView.-$$Nest$mapplyFadeOutAnimation(v1_2, com.bisimplex.firebooru.view.PageView.-$$Nest$fgetthumbImageView(v1_2));
        }
        return;
    }

    public bridge synthetic void onResourceReady(Object p1, com.bumptech.glide.request.transition.Transition p2, String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        this.onResourceReady(((android.graphics.drawable.Drawable) p1), p2, p3, p4, p5);
        return;
    }
}
