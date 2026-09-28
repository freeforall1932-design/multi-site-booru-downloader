package com.bisimplex.firebooru.fragment;
 class PoolsFragment$2 implements com.bisimplex.firebooru.view.DynamicPoolSearchDialog$OnDynamicPoolSearchDialogListener {
    final synthetic com.bisimplex.firebooru.fragment.PoolsFragment this$0;

    PoolsFragment$2(com.bisimplex.firebooru.fragment.PoolsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p3, com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper v0_2 = com.bisimplex.firebooru.fragment.PoolsFragment.-$$Nest$mgetSource(this.this$0);
        if (v0_2 != null) {
            v0_2.reset();
            if (p4 != null) {
                v0_2.setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p4));
                this.this$0.setSourceName(p4.getServerName());
                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(p4.getServerId());
            }
            com.bisimplex.firebooru.fragment.PoolsFragment.-$$Nest$msearchQuery(this.this$0, p3);
            return;
        } else {
            return;
        }
    }
}
