package com.bisimplex.firebooru.fragment;
 class PoolsFragment$1 implements androidx.appcompat.widget.Toolbar$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PoolsFragment this$0;

    PoolsFragment$1(com.bisimplex.firebooru.fragment.PoolsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131362519) {
            if (p3.getItemId() == 2131362498) {
                com.bisimplex.firebooru.fragment.PoolsFragment.-$$Nest$mshowSearchField(this.this$0);
            }
        } else {
            this.this$0.askShowServers();
        }
        return 1;
    }
}
