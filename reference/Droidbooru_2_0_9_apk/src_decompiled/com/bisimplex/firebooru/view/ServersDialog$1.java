package com.bisimplex.firebooru.view;
 class ServersDialog$1 implements android.widget.AdapterView$OnItemClickListener {
    final synthetic com.bisimplex.firebooru.view.ServersDialog this$0;
    final synthetic boolean val$change;

    ServersDialog$1(com.bisimplex.firebooru.view.ServersDialog p1, boolean p2)
    {
        this.this$0 = p1;
        this.val$change = p2;
        return;
    }

    public void onItemClick(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        com.bisimplex.firebooru.view.ServersDialog v1_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.this$0.servers.get(p3));
        if (this.val$change) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(v1_4.getServerId());
            com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().setServerDescription(v1_4);
        }
        this.this$0.mListener.onDialogServerSelected(this.this$0, v1_4);
        this.this$0.dismiss();
        return;
    }
}
