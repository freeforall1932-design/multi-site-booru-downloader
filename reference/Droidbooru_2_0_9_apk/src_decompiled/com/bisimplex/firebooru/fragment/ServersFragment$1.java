package com.bisimplex.firebooru.fragment;
 class ServersFragment$1 implements com.bisimplex.firebooru.view.DynamicServerSearchDialog$OnDynamicServerSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.ServersFragment this$0;

    ServersFragment$1(com.bisimplex.firebooru.fragment.ServersFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p2)
    {
        this.this$0.query = p2;
        this.this$0.reloadData();
        return;
    }
}
