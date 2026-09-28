package com.bisimplex.firebooru.dataadapter;
 class GridPostDataAdapter$1 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.dataadapter.GridPostDataAdapter this$0;

    GridPostDataAdapter$1(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p9, Object p10, com.bumptech.glide.request.target.Target p11, boolean p12)
    {
        if (((p10 instanceof com.bumptech.glide.load.model.GlideUrl)) && ((p11 instanceof com.bumptech.glide.request.target.DrawableImageViewTarget))) {
            int v9_7 = ((android.widget.ImageView) ((com.bumptech.glide.request.target.DrawableImageViewTarget) p11).getView());
            try {
                com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v10_2 = ((com.bumptech.glide.load.model.GlideUrl) p10).toURL();
                int v11_3 = v10_2.getHost();
            } catch (int v9_1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v9_1);
            }
            if (v11_3.contains(".gelbooru.com")) {
                int v9_4 = ((Integer) v9_7.getTag(2131362637)).intValue();
                com.bisimplex.firebooru.danbooru.DanbooruPost v0_7 = ((com.bisimplex.firebooru.model.GridPostItem) this.this$0.data.get(v9_4)).getPost();
                if (v0_7 != null) {
                    if (v0_7.getPreview().getErrorLoadCount() < 2) {
                        String v1_3 = v0_7.getPreview().getUrl();
                        if (v1_3.equalsIgnoreCase(v10_2.toString())) {
                            com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v10_11;
                            com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v10_13;
                            com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v10_7 = v11_3.split("\\.");
                            String v2_1 = v10_7[0];
                            String v6 = "";
                            if (!v2_1.contains("img2")) {
                                if (!v2_1.contains("img3")) {
                                    if (!v2_1.equalsIgnoreCase("thumbs")) {
                                        v10_11 = "";
                                    } else {
                                        v10_11 = v1_3.replace("/thumbs.gelbooru.com/", "/img3.gelbooru.com/thumbnails/");
                                    }
                                } else {
                                    v10_7[0] = "img1";
                                    v10_13 = android.text.TextUtils.join(".", v10_7);
                                    v6 = v10_13;
                                    v10_11 = "";
                                }
                            } else {
                                v10_7[0] = "img3";
                                v10_13 = android.text.TextUtils.join(".", v10_7);
                            }
                            if (!android.text.TextUtils.isEmpty(v6)) {
                                v10_11 = v1_3.replace(v11_3, v6);
                            }
                            if (!android.text.TextUtils.isEmpty(v10_11)) {
                                v0_7.getPreview().setUrl(v10_11);
                                v0_7.getPreview().setErrorLoadCount((v0_7.getPreview().getErrorLoadCount() + 1));
                                this.this$0.notifyItemChanged(v9_4);
                                return 1;
                            }
                        }
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    public boolean onResourceReady(android.graphics.drawable.Drawable p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        if (((p2 instanceof com.bumptech.glide.load.model.GlideUrl)) && ((p3 instanceof com.bumptech.glide.request.target.DrawableImageViewTarget))) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper v1_6 = ((com.bumptech.glide.load.model.GlideUrl) p2).toStringUrl();
            if (!android.text.TextUtils.isEmpty(v1_6)) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v2_9 = ((com.bisimplex.firebooru.model.GridPostItem) this.this$0.data.get(((Integer) ((android.widget.ImageView) ((com.bumptech.glide.request.target.DrawableImageViewTarget) p3).getView()).getTag(2131362637)).intValue())).getPost();
                if (v2_9 != null) {
                    if ((v2_9.getPreview().getErrorLoadCount() > 0) && (v2_9.getPreview().getUrl().equalsIgnoreCase(v1_6))) {
                        v2_9.getPreview().setErrorLoadCount(0);
                        if (v2_9.isFavorite()) {
                            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(v2_9);
                        }
                    }
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public bridge synthetic boolean onResourceReady(Object p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        return this.onResourceReady(((android.graphics.drawable.Drawable) p1), p2, p3, p4, p5);
    }
}
