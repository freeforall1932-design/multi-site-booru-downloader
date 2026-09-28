package com.bisimplex.firebooru.fragment;
 class DetailFragment$13 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    public static synthetic void $r8$lambda$TJx3Rt0nyogxRfAiZaRTzQuYmjc(com.bisimplex.firebooru.fragment.DetailFragment$13 p0, com.bisimplex.firebooru.danbooru.DanbooruPost p1, java.io.File p2)
    {
        p0.lambda$onResourceReady$1(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$tqVc-5Vk7-BqtKDyMixpRUD7Owc(com.bisimplex.firebooru.fragment.DetailFragment$13 p0, com.bumptech.glide.load.engine.GlideException p1)
    {
        p0.lambda$onLoadFailed$0(p1);
        return;
    }

    DetailFragment$13(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.this$0 = p1;
        this.val$post = p2;
        return;
    }

    private synthetic void lambda$onLoadFailed$0(com.bumptech.glide.load.engine.GlideException p3)
    {
        if (p3 != null) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(p3);
        }
        this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
        return;
    }

    private synthetic void lambda$onResourceReady$1(com.bisimplex.firebooru.danbooru.DanbooruPost p5, java.io.File p6)
    {
        try {
            int v0_0 = p5.getPostId();
        } catch (android.content.Intent v5_12) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_12);
            this.this$0.showMessage(2131886221, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
        if (!android.text.TextUtils.isEmpty(p5.getMd5())) {
            v0_0 = p5.getMd5();
        }
        java.io.File v1_2 = new java.io.File(new StringBuilder().append(p6.getParent()).append(java.io.File.separator).append(v0_0).append(".").append(p5.getVisibleVersion().getExtension()).toString());
        if (!this.this$0.copy(p6, v1_2, 0)) {
            this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        } else {
            if (v1_2.exists()) {
                android.content.Intent v5_11 = android.app.WallpaperManager.getInstance(this.this$0.getContext()).getCropAndSetWallpaperIntent(com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mfileToSharingUri(this.this$0, v1_2));
                v5_11.addFlags(1);
                this.this$0.startActivity(v5_11);
                return;
            } else {
                return;
            }
        }
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$13$$ExternalSyntheticLambda0(this, p1));
        return 1;
    }

    public boolean onResourceReady(java.io.File p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$13$$ExternalSyntheticLambda1(this, this.val$post, p1));
        return 1;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
    }
}
