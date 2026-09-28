package com.bisimplex.firebooru.fragment;
 class HomeFragment$2 implements com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.HomeFragment this$0;

    HomeFragment$2(com.bisimplex.firebooru.fragment.HomeFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p2, com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        this.this$0.showSearch(p2, p3);
        return;
    }

    public void onSearchSources(com.bisimplex.firebooru.network.SourceQuery p2, java.util.List p3)
    {
        this.this$0.showMultiSearch(p2, p3);
        return;
    }
}
