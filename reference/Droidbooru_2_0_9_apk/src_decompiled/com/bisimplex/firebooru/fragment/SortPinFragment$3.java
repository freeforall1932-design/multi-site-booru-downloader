package com.bisimplex.firebooru.fragment;
 class SortPinFragment$3 implements com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog$OnDynamicFavoriteSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;

    SortPinFragment$3(com.bisimplex.firebooru.fragment.SortPinFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p2, com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        this.this$0.updateEditingSource(p2, p3);
        return;
    }
}
