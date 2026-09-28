package com.bisimplex.firebooru.fragment;
 class DetailFragment$12 implements com.bisimplex.firebooru.network.SourceListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$12(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p3, com.bisimplex.firebooru.data.FailureType p4)
    {
        this.this$0.showMessage(p4, p3.getLastErrorCode());
        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(p3);
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    public void success(com.bisimplex.firebooru.network.Source p7, java.util.List p8)
    {
        if (this.this$0.getActivity() != null) {
            int v0_7 = this.this$0.getSource();
            if (v0_7 != 0) {
                com.bisimplex.firebooru.activity.MessageType v1_2 = v0_7.getVisiblePostIndex();
                int v2_2 = Integer.parseInt(p7.getQuery().getTitle());
                com.bisimplex.firebooru.danbooru.DatabaseHelper v3_1 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_7.getItemAt(v2_2));
                if (v3_1 != null) {
                    if (p8.size() <= 0) {
                        this.this$0.showMessage(2131886866, com.bisimplex.firebooru.activity.MessageType.Error);
                    } else {
                        com.bisimplex.firebooru.fragment.DetailFragment v8_3 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) p8.get(0));
                        com.bisimplex.firebooru.danbooru.DanbooruPost v4_3 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_7.getItemAt(v2_2));
                        if ((v8_3.getPostId() == null) || (!v8_3.getPostId().equalsIgnoreCase(v3_1.getPostId()))) {
                            if ((v8_3.getPreview() == null) && (v8_3.getFile() != null)) {
                                v4_3.getFile().setUrl(v8_3.getFile().getUrl());
                                v8_3 = v4_3;
                            }
                        } else {
                            if ((v4_3.isFavorite()) && ((android.text.TextUtils.isEmpty(v4_3.getMd5())) && ((!android.text.TextUtils.isEmpty(v8_3.getMd5())) && (!v8_3.isFavorite())))) {
                                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateMD5For(v4_3, v8_3);
                            }
                            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(v8_3);
                            v0_7.replaceItemAt(v2_2, v8_3);
                        }
                        if (v1_2 == v2_2) {
                            this.this$0.adapter.setItem(v8_3, v1_2);
                            this.this$0.reloadSelectedImage();
                            this.this$0.showMessage(2131886867, com.bisimplex.firebooru.activity.MessageType.Minimal);
                        }
                    }
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(p7);
                    return;
                }
            }
        }
        return;
    }
}
