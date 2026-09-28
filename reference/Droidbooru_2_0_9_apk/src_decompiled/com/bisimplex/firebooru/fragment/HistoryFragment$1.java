package com.bisimplex.firebooru.fragment;
 class HistoryFragment$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.HistoryFragment this$0;

    HistoryFragment$1(com.bisimplex.firebooru.fragment.HistoryFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteAllPostHistory();
        this.this$0.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
        this.this$0.reloadData();
        return;
    }
}
