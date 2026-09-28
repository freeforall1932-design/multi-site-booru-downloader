package com.bisimplex.firebooru.fragment;
 class ServerListDataAdapter$ItemHolder$1$1 implements android.view.MenuItem$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder$1 this$1;

    ServerListDataAdapter$ItemHolder$1$1(com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder$1 p1)
    {
        this.this$1 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p2)
    {
        if (this.this$1.val$l != null) {
            this.this$1.val$l.onEditServer(this.this$1.this$0.getBindingAdapterPosition());
        }
        return 1;
    }
}
