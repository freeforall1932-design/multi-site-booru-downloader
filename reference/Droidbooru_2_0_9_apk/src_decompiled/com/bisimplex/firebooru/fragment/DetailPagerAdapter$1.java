package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$1 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;

    DetailPagerAdapter$1(com.bisimplex.firebooru.fragment.DetailPagerAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        if (p1 != 0) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(p1);
        }
        return 0;
    }

    public boolean onResourceReady(android.graphics.drawable.Drawable p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return 0;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((android.graphics.drawable.Drawable) p1), p2, p3, p4, p5);
    }
}
