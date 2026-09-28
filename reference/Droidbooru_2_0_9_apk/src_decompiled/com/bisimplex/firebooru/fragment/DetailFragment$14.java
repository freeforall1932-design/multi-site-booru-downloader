package com.bisimplex.firebooru.fragment;
 class DetailFragment$14 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPostImage val$visibleVersion;

    public static synthetic void $r8$lambda$2TAJmxOutcR-R0XgkMB84bzNGK8(com.bisimplex.firebooru.fragment.DetailFragment$14 p0, com.bisimplex.firebooru.danbooru.DanbooruPost p1, java.io.File p2, com.bisimplex.firebooru.danbooru.DanbooruPostImage p3)
    {
        p0.lambda$onResourceReady$1(p1, p2, p3);
        return;
    }

    public static synthetic void $r8$lambda$EQXuZccOC4g7jMIT2q82SFeZ94s(com.bisimplex.firebooru.fragment.DetailFragment$14 p0, com.bumptech.glide.load.engine.GlideException p1)
    {
        p0.lambda$onLoadFailed$0(p1);
        return;
    }

    DetailFragment$14(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.DanbooruPostImage p3)
    {
        this.this$0 = p1;
        this.val$post = p2;
        this.val$visibleVersion = p3;
        return;
    }

    private synthetic void lambda$onLoadFailed$0(com.bumptech.glide.load.engine.GlideException p3)
    {
        if (p3 != null) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(p3);
        }
        this.this$0.showMessage(2131886213, com.bisimplex.firebooru.activity.MessageType.Error);
        return;
    }

    private synthetic void lambda$onResourceReady$1(com.bisimplex.firebooru.danbooru.DanbooruPost p5, java.io.File p6, com.bisimplex.firebooru.danbooru.DanbooruPostImage p7)
    {
        android.content.ContentResolver v0_0 = p5.getPostId();
        if (!android.text.TextUtils.isEmpty(p5.getMd5())) {
            v0_0 = p5.getMd5();
        }
        com.bisimplex.firebooru.fragment.DetailFragment v5_1 = this.this$0.getContext();
        try {
            if (v5_1 != null) {
                int v1_2 = new java.io.File(new StringBuilder().append(p6.getParent()).append(java.io.File.separator).append(v0_0).append(".").append(p7.getExtension()).toString());
                if (!this.this$0.copy(p6, v1_2, 0)) {
                    this.this$0.showMessage(2131886213, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                } else {
                    if (v1_2.exists()) {
                        ((android.content.ClipboardManager) v5_1.getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newUri(v5_1.getApplicationContext().getContentResolver(), v5_1.getString(2131886708), com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mfileToSharingUri(this.this$0, v1_2)));
                        this.this$0.showMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                        return;
                    }
                }
            }
        } catch (com.bisimplex.firebooru.fragment.DetailFragment v5_6) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_6);
            this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$14$$ExternalSyntheticLambda0(this, p1));
        return 1;
    }

    public boolean onResourceReady(java.io.File p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$14$$ExternalSyntheticLambda1(this, this.val$post, p1, this.val$visibleVersion));
        return 1;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
    }
}
