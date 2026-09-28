package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$3 implements com.bumptech.glide.request.RequestListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;

    DetailPagerAdapter$3(com.bisimplex.firebooru.fragment.DetailPagerAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onLoadFailed(com.bumptech.glide.load.engine.GlideException p1, Object p2, com.bumptech.glide.request.target.Target p3, boolean p4)
    {
        return 0;
    }

    public boolean onResourceReady(android.graphics.drawable.Drawable p1, Object p2, com.bumptech.glide.request.target.Target p3, com.bumptech.glide.load.DataSource p4, boolean p5)
    {
        if ((p2 instanceof com.bumptech.glide.load.model.GlideUrl)) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper v1_5 = ((com.bumptech.glide.load.model.GlideUrl) p2).toStringUrl();
            if (!android.text.TextUtils.isEmpty(v1_5)) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v2_2 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetsource(this.this$0).getVisiblePost();
                if (v2_2 != null) {
                    if ((v2_2.getPreview().getErrorLoadCount() > 0) && (v2_2.getPreview().getUrl().equalsIgnoreCase(v1_5))) {
                        v2_2.getPreview().setErrorLoadCount(0);
                        if (v2_2.isFavorite()) {
                            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(v2_2);
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
