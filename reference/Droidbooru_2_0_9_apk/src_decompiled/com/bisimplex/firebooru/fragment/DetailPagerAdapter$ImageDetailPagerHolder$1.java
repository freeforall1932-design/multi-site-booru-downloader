package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$ImageDetailPagerHolder$1 implements com.bisimplex.firebooru.custom.DownloadTargetListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder this$1;

    DetailPagerAdapter$ImageDetailPagerHolder$1(com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onLoadFailed(String p2)
    {
        this.this$1.reportLoadFailed(p2);
        return;
    }

    public void onProgress(long p2, long p4)
    {
        this.this$1.reportProgress(p2, p4);
        return;
    }

    public void onResourceReady(android.graphics.drawable.Drawable p4, com.bumptech.glide.request.transition.Transition p5, String p6, com.bisimplex.firebooru.danbooru.DanbooruPost p7, boolean p8)
    {
        float v5_2 = this.this$1.getBindingAdapterPosition();
        com.bisimplex.firebooru.data.DanbooruPostPage v6_2 = this.this$1.this$0.getItem(v5_2);
        if ((v6_2 != null) && ((v6_2.getPost() != null) && ((!android.text.TextUtils.isEmpty(v6_2.getPost().getPostId())) && (v6_2.getPost().getPostId().equalsIgnoreCase(p7.getPostId()))))) {
            v6_2.setLoading(0);
            v6_2.setReady(1);
            int v1_0 = p7.getVisibleVersion();
            if ((v1_0.getWidth() == 0) || (v1_0.getHeight() == 0)) {
                v1_0.setWidth(p4.getIntrinsicWidth());
                v1_0.setHeight(p4.getIntrinsicHeight());
            }
            this.this$1.finishedLoading(p7, 0);
            if (v5_2 != com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetsource(this.this$1.this$0).getVisiblePostIndex()) {
                this.this$1.imageView.setImageDrawable(0);
                this.this$1.loading_bottom.setVisibility(8);
            } else {
                this.this$1.imageView.setImageDrawable(p4);
                if ((p4 instanceof android.graphics.drawable.Animatable)) {
                    ((android.graphics.drawable.Animatable) p4).start();
                }
                if (this.this$1.loading_bottom.getVisibility() == 0) {
                    com.bisimplex.firebooru.model.ViewerCommandType v4_10 = this.this$1;
                    v4_10.applyFadeOutAnimation(v4_10.loading_bottom);
                }
                this.this$1.thumbImageView.setVisibility(8);
            }
            com.bisimplex.firebooru.model.ViewerCommandType v4_14 = com.bisimplex.firebooru.fragment.DetailPagerAdapter$4.$SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType[v6_2.getPendingCommand().ordinal()];
            if (v4_14 == 1) {
                this.this$1.imageView.setScale(this.this$1.imageView.getMaximumScale(), 0);
            } else {
                if (v4_14 == 2) {
                    this.this$1.imageView.setScale(this.this$1.imageView.getMinimumScale(), 0);
                }
            }
            v6_2.setPendingCommand(com.bisimplex.firebooru.model.ViewerCommandType.None);
        }
        return;
    }

    public bridge synthetic void onResourceReady(Object p1, com.bumptech.glide.request.transition.Transition p2, String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        this.onResourceReady(((android.graphics.drawable.Drawable) p1), p2, p3, p4, p5);
        return;
    }
}
