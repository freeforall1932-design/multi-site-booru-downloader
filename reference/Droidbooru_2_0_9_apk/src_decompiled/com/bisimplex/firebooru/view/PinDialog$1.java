package com.bisimplex.firebooru.view;
 class PinDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.PinDialog this$0;
    final synthetic String val$sourceID;
    final synthetic com.bisimplex.firebooru.network.SourceType val$type;

    PinDialog$1(com.bisimplex.firebooru.view.PinDialog p1, String p2, com.bisimplex.firebooru.network.SourceType p3)
    {
        this.this$0 = p1;
        this.val$sourceID = p2;
        this.val$type = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        com.bisimplex.firebooru.view.PinDialog.-$$Nest$fgetmListener(this.this$0).onDialogNewFolderClick(this.this$0, this.val$sourceID, this.val$type);
        return;
    }
}
