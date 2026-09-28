package com.bisimplex.firebooru.fragment;
 class FavoriteListFragment$4 implements com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog$OnDynamicFavoriteSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.FavoriteListFragment this$0;

    FavoriteListFragment$4(com.bisimplex.firebooru.fragment.FavoriteListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p4, com.bisimplex.firebooru.danbooru.ServerItem p5)
    {
        com.bisimplex.firebooru.network.SourcePostBasic v0_1 = this.this$0.getSource();
        if (v0_1 != null) {
            v0_1.reset();
            this.this$0.dataAdapter.clearItems();
            this.this$0.setTitle(p4.getText());
            if (p5 == null) {
                com.bisimplex.firebooru.fragment.FavoriteListFragment.-$$Nest$mupdateSourceName(this.this$0, 0);
            } else {
                com.bisimplex.firebooru.fragment.FavoriteListFragment.-$$Nest$mupdateSourceName(this.this$0, p5.getServerName());
            }
            v0_1.setQuery(p4);
            this.this$0.reloadData();
            return;
        } else {
            return;
        }
    }
}
