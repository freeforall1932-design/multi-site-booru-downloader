package com.bisimplex.firebooru.fragment;
 class MixedSearchFragment$1 implements com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.MixedSearchFragment this$0;

    MixedSearchFragment$1(com.bisimplex.firebooru.fragment.MixedSearchFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p4, com.bisimplex.firebooru.danbooru.ServerItem p5)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.getActivity());
        if (v0_2 != null) {
            int v5_1;
            if (p5 == 0) {
                v5_1 = 0;
            } else {
                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(p5.getServerId());
                v5_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p5);
            }
            v0_2.searchQuery(p4, v5_1, 1);
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
