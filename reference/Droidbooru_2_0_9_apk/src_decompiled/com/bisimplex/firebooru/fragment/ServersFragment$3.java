package com.bisimplex.firebooru.fragment;
 class ServersFragment$3 implements com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick {
    final synthetic com.bisimplex.firebooru.fragment.ServersFragment this$0;

    ServersFragment$3(com.bisimplex.firebooru.fragment.ServersFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onDeleteServer(int p2)
    {
        this.this$0.deleteServerAt(p2);
        return;
    }

    public void onEditServer(int p2)
    {
        this.this$0.editServerAt(p2);
        return;
    }

    public void onListItemClick(int p2)
    {
        this.this$0.selectServerAt(p2);
        return;
    }
}
