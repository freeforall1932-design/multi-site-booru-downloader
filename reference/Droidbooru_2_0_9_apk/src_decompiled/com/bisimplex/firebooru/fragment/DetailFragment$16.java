package com.bisimplex.firebooru.fragment;
 class DetailFragment$16 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic String val$filename;
    final synthetic boolean val$isVideo;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;
    final synthetic androidx.documentfile.provider.DocumentFile val$targetDocument;
    final synthetic android.content.ContentValues val$targetValues;

    public static synthetic void $r8$lambda$rj2BeZnCUXV84t2tx3fwWCLgsKY(com.bisimplex.firebooru.fragment.DetailFragment$16 p0, android.net.Uri p1)
    {
        p0.lambda$onResourceReady$0(p1);
        return;
    }

    DetailFragment$16(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2, androidx.documentfile.provider.DocumentFile p3, android.content.ContentValues p4, String p5, boolean p6)
    {
        this.this$0 = p1;
        this.val$post = p2;
        this.val$targetDocument = p3;
        this.val$targetValues = p4;
        this.val$filename = p5;
        this.val$isVideo = p6;
        return;
    }

    private synthetic void lambda$onResourceReady$0(android.net.Uri p4)
    {
        android.content.Context v0_1 = this.this$0.getContext();
        String[] v1_1 = new String[1];
        v1_1[0] = p4.getPath();
        android.media.MediaScannerConnection.scanFile(v0_1, v1_1, 0, 0);
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        if (p1 != 0) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(p1);
        }
        this.this$0.showMessageOnMain(2131887108, com.bisimplex.firebooru.activity.MessageType.Error);
        return 1;
    }

    public boolean onResourceReady(java.io.File p6, Object p7, com.bumptech.glide.request.target.Target p8, com.bumptech.glide.load.DataSource p9, boolean p10)
    {
        com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mwriteExif(this.this$0, p6.getPath(), this.val$post);
        try {
            if ((this.val$targetDocument != null) || (this.val$targetValues != null)) {
                String v7_11;
                com.bisimplex.firebooru.fragment.DetailFragment$16$$ExternalSyntheticLambda0 v9_3 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver();
                int v10_0 = this.val$targetDocument;
                if (v10_0 == 0) {
                    int v10_3;
                    if (!this.val$isVideo) {
                        v10_3 = v9_3.insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, this.val$targetValues);
                    } else {
                        v10_3 = v9_3.insert(android.provider.MediaStore$Video$Media.EXTERNAL_CONTENT_URI, this.val$targetValues);
                    }
                    if ((v10_3 != 0) && (!this.val$isVideo)) {
                        String v2_3 = new String[1];
                        v2_3[0] = "_display_name";
                        String v2_4 = v9_3.query(v10_3, v2_3, 0, 0);
                        if ((v2_4 != null) && (v2_4.moveToFirst())) {
                            String v7_9 = v2_4.getString(v2_4.getColumnIndex("_display_name"));
                            v2_4.close();
                            if ((!android.text.TextUtils.isEmpty(v7_9)) && (!this.val$filename.equalsIgnoreCase(v7_9))) {
                                this.this$0.showMessage(2131886584, com.bisimplex.firebooru.activity.MessageType.Minimal);
                                if (v9_3.delete(v10_3, 0, 0) > 0) {
                                    android.util.Log.e("SaveFile", "deleted register");
                                }
                                return 1;
                            }
                        }
                    }
                    v7_11 = v10_3;
                } else {
                    if (this.val$filename.equalsIgnoreCase(v10_0.getName())) {
                        v7_11 = this.val$targetDocument.getUri();
                    } else {
                        this.this$0.showMessage(2131886584, com.bisimplex.firebooru.activity.MessageType.Minimal);
                        this.val$targetDocument.delete();
                        return 1;
                    }
                }
                if (v7_11 != null) {
                    com.bisimplex.firebooru.fragment.DetailFragment$16$$ExternalSyntheticLambda0 v9_6 = v9_3.openOutputStream(v7_11);
                    if (v9_6 != null) {
                        int v10_9 = new java.io.FileInputStream(p6);
                        String v6_12 = new byte[1024];
                        while(true) {
                            int v1_1 = v10_9.read(v6_12);
                            if (v1_1 <= 0) {
                                break;
                            }
                            v9_6.write(v6_12, 0, v1_1);
                        }
                        v10_9.close();
                        v9_6.close();
                        this.this$0.showMessage(2131887103, com.bisimplex.firebooru.activity.MessageType.Success);
                        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$16$$ExternalSyntheticLambda0(this, v7_11));
                    } else {
                        this.this$0.showMessage(2131887109, com.bisimplex.firebooru.activity.MessageType.Error);
                        return 1;
                    }
                } else {
                    this.this$0.showMessage(2131887109, com.bisimplex.firebooru.activity.MessageType.Error);
                    return 1;
                }
            }
        } catch (String v6_6) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_6);
            this.this$0.showMessage(2131887108, com.bisimplex.firebooru.activity.MessageType.Error);
        } catch (String v6_4) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_4);
            this.this$0.showMessage(v6_4.getLocalizedMessage(), com.bisimplex.firebooru.activity.MessageType.Error);
        }
        return 1;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((java.io.File) p1), p2, p3, p4, p5);
    }
}
