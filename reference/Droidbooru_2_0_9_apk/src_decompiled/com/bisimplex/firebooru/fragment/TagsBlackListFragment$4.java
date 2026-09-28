package com.bisimplex.firebooru.fragment;
 class TagsBlackListFragment$4 implements com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog$OnDynamicBlacklistSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.TagsBlackListFragment this$0;

    TagsBlackListFragment$4(com.bisimplex.firebooru.fragment.TagsBlackListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p2)
    {
        com.bisimplex.firebooru.fragment.TagsBlackListFragment.-$$Nest$fputquery(this.this$0, p2);
        this.this$0.reloadData();
        return;
    }
}
