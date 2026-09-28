package com.bisimplex.firebooru.view;
 class HistoryTagMenuDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.HistoryTagMenuDialog this$0;

    HistoryTagMenuDialog$1(com.bisimplex.firebooru.view.HistoryTagMenuDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p4, int p5)
    {
        if (this.this$0.mListener != null) {
            com.bisimplex.firebooru.view.HistoryTagMenuDialog v0 = this.this$0;
            this.this$0.mListener.onHistoryTagDialogClick(v0, v0.getTitle(), this.this$0.getPosition(), com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.fromInteger(p5));
        }
        return;
    }
}
