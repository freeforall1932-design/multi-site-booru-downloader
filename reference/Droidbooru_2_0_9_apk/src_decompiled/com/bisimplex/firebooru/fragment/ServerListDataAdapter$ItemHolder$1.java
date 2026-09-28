package com.bisimplex.firebooru.fragment;
 class ServerListDataAdapter$ItemHolder$1 implements android.view.View$OnCreateContextMenuListener {
    final synthetic com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder this$0;
    final synthetic com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick val$l;

    ServerListDataAdapter$ItemHolder$1(com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder p1, com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick p2)
    {
        this.this$0 = p1;
        this.val$l = p2;
        return;
    }

    public void onCreateContextMenu(android.view.ContextMenu p1, android.view.View p2, android.view.ContextMenu$ContextMenuInfo p3)
    {
        p1.clear();
        p1.add(2131886468).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder$1$1(this));
        p1.add(2131886392).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder$1$2(this));
        return;
    }
}
