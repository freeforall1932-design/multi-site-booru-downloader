package com.bisimplex.firebooru.fragment;
 class DetailFragment$18 implements com.bisimplex.firebooru.network.FavoriteSyncListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$18(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void favoriteSyncComplete(com.bisimplex.firebooru.network.Source p1, boolean p2)
    {
        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(p1);
        return;
    }
}
