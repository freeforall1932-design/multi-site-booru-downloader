package com.bisimplex.firebooru.fragment;
 class DetailFragment$17 implements com.bisimplex.firebooru.network.FavoriteSyncListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$currentPost;

    DetailFragment$17(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.this$0 = p1;
        this.val$currentPost = p2;
        return;
    }

    public void favoriteSyncComplete(com.bisimplex.firebooru.network.Source p2, boolean p3)
    {
        if (!this.this$0.isDetached()) {
            com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(p2);
            if (p3 == 0) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v2_2 = this.val$currentPost;
                v2_2.setFavorite((v2_2.isFavorite() ^ 1));
                return;
            } else {
                this.this$0.updateFavButton();
                return;
            }
        } else {
            return;
        }
    }
}
