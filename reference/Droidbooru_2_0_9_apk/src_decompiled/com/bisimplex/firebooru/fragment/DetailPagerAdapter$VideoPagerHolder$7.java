package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$7 implements com.bisimplex.firebooru.custom.DownloadTargetListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;

    DetailPagerAdapter$VideoPagerHolder$7(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1)
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

    public void onResourceReady(java.io.File p5, com.bumptech.glide.request.transition.Transition p6, String p7, com.bisimplex.firebooru.danbooru.DanbooruPost p8, boolean p9)
    {
        me.zhanghai.android.materialprogressbar.MaterialProgressBar v6_7 = this.this$1.this$0.getItem(this.this$1.getBindingAdapterPosition());
        if ((v6_7 != null) && ((v6_7.getPost() == p8) || (v6_7.getPost().getPostId().equalsIgnoreCase(p8.getPostId())))) {
            this.this$1.thumbImageView.setVisibility(8);
            v6_7.setLoading(0);
            v6_7.setReady(1);
            if ((p5.exists()) && (p5.length() != 0)) {
                this.this$1.finishedLoading(p8, p5);
            } else {
                if (p5.delete()) {
                    android.util.Log.i("PageView", "Empty file deleted");
                }
                this.this$1.reportLoadFailed(p7);
            }
            if (this.this$1.loading_bottom.getVisibility() == 0) {
                com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v5_7 = this.this$1;
                v5_7.applyFadeOutAnimation(v5_7.loading_bottom);
            }
        }
        return;
    }

    public bridge synthetic void onResourceReady(Object p1, com.bumptech.glide.request.transition.Transition p2, String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
        return;
    }
}
