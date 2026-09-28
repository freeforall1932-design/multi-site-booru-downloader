package com.bisimplex.firebooru.fragment;
 class DetailFragment$15 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPostImage val$visibleVersion;

    public static synthetic void $r8$lambda$I-Bmxcs1TkAlC4DfBUPUwYZiuKA(com.bisimplex.firebooru.fragment.DetailFragment$15 p0, com.bisimplex.firebooru.danbooru.DanbooruPost p1, java.io.File p2, com.bisimplex.firebooru.danbooru.DanbooruPostImage p3)
    {
        p0.lambda$onResourceReady$1(p1, p2, p3);
        return;
    }

    public static synthetic void $r8$lambda$vBqucZTCdAK2LklNm7mNtPZR7is(com.bisimplex.firebooru.fragment.DetailFragment$15 p0, com.bumptech.glide.load.engine.GlideException p1)
    {
        p0.lambda$onLoadFailed$0(p1);
        return;
    }

    DetailFragment$15(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.DanbooruPostImage p3)
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
        this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
        return;
    }

    private synthetic void lambda$onResourceReady$1(com.bisimplex.firebooru.danbooru.DanbooruPost p6, java.io.File p7, com.bisimplex.firebooru.danbooru.DanbooruPostImage p8)
    {
        int v2_0 = p6.getPostId();
        if (!android.text.TextUtils.isEmpty(p6.getMd5())) {
            v2_0 = p6.getMd5();
        }
        com.bisimplex.firebooru.fragment.DetailFragment v6_2 = new java.io.File(new StringBuilder().append(p7.getParent()).append(java.io.File.separator).append(v2_0).append(".").append(p8.getExtension()).toString());
        try {
            if (!this.this$0.copy(p7, v6_2, 0)) {
                this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            } else {
                if (v6_2.exists()) {
                    com.bisimplex.firebooru.fragment.DetailFragment v6_4 = com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mfileToSharingUri(this.this$0, v6_2);
                    android.content.Intent v7_6 = new android.content.Intent();
                    v7_6.setAction("android.intent.action.SEND");
                    v7_6.setClipData(android.content.ClipData.newRawUri(0, v6_4));
                    v7_6.putExtra("android.intent.extra.STREAM", v6_4);
                    if (!p8.isVideo()) {
                        v7_6.setType(new StringBuilder("image/").append(p8.getExtension()).toString());
                    } else {
                        v7_6.setType(new StringBuilder("video/").append(p8.getExtension()).toString());
                    }
                    v7_6.addFlags(1);
                    com.bisimplex.firebooru.fragment.DetailFragment v6_15 = this.this$0;
                    v6_15.startActivity(android.content.Intent.createChooser(v7_6, v6_15.getResources().getText(2131887134)));
                    return;
                } else {
                    return;
                }
            }
        } catch (com.bisimplex.firebooru.fragment.DetailFragment v6_16) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_16);
            this.this$0.showMessage(2131886222, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$15$$ExternalSyntheticLambda1(this, p1));
        return 1;
    }

    public boolean onResourceReady(java.io.File p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$15$$ExternalSyntheticLambda0(this, this.val$post, p1, this.val$visibleVersion));
        return 1;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
    }
}
