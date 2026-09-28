package com.bisimplex.firebooru.view;
 class LinkMenuDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.LinkMenuDialog this$0;

    LinkMenuDialog$1(com.bisimplex.firebooru.view.LinkMenuDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        if (this.this$0.mListener != null) {
            com.bisimplex.firebooru.view.LinkMenuDialog v0 = this.this$0;
            this.this$0.mListener.onLinkDialogClick(v0, com.bisimplex.firebooru.view.LinkMenuDialog.-$$Nest$mgetURL(v0), com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.fromInteger(p4));
        }
        return;
    }
}
