package com.bisimplex.firebooru.fragment;
 class PostListFragment$4 implements com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;

    PostListFragment$4(com.bisimplex.firebooru.fragment.PostListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p6, com.bisimplex.firebooru.danbooru.ServerItem p7)
    {
        com.bisimplex.firebooru.network.SourcePostBasic v0_1 = this.this$0.getSource();
        if (v0_1 != null) {
            v0_1.reset();
            this.this$0.dataAdapter.clearItems();
            this.this$0.setPageLabelVisibility(0);
            this.this$0.setTitle(p6.getText());
            if (p7 != 0) {
                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(p7.getServerId());
                v0_1.setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p7));
                this.this$0.setSourceName(p7.getServerName());
            }
            v0_1.setQuery(p6);
            if (p6.getInitialPage() > 0) {
                v0_1.setPageOffset(((int) (p6.getInitialPage() - 1)));
            }
            this.this$0.mustScrollToTop = 1;
            this.this$0.clearMemoryCache();
            this.this$0.LoadData();
            return;
        } else {
            return;
        }
    }

    public void onSearchSources(com.bisimplex.firebooru.network.SourceQuery p2, java.util.List p3)
    {
        this.this$0.showMultiSearch(p2, p3);
        return;
    }
}
