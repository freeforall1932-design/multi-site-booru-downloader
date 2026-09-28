package com.bisimplex.firebooru.custom;
 class GlideImageLoader$2 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.custom.GlideImageLoader this$0;
    final synthetic String val$url;

    GlideImageLoader$2(com.bisimplex.firebooru.custom.GlideImageLoader p1, String p2)
    {
        this.this$0 = p1;
        this.val$url = p2;
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        com.bisimplex.firebooru.custom.MyGlideModule.forget(this.val$url);
        com.bisimplex.firebooru.custom.GlideImageLoader.-$$Nest$monFinished(this.this$0);
        return 0;
    }

    public boolean onResourceReady(android.graphics.drawable.Drawable p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        com.bisimplex.firebooru.custom.MyGlideModule.forget(this.val$url);
        com.bisimplex.firebooru.custom.GlideImageLoader.-$$Nest$monFinished(this.this$0);
        return 0;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((android.graphics.drawable.Drawable) p1), p2, p3, p4, p5);
    }
}
