package com.bisimplex.firebooru.fragment;
 class ServerChangerFragment$1 implements android.view.MenuItem$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.ServerChangerFragment this$0;

    ServerChangerFragment$1(com.bisimplex.firebooru.fragment.ServerChangerFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p1)
    {
        this.this$0.askShowServers();
        return 1;
    }
}
