package com.bisimplex.firebooru.view;
 class TagMenuDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.TagMenuDialog this$0;

    TagMenuDialog$1(com.bisimplex.firebooru.view.TagMenuDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        if (this.this$0.mListener != null) {
            com.bisimplex.firebooru.view.TagMenuDialog v0 = this.this$0;
            this.this$0.mListener.onTagDialogClick(v0, v0.getTitle(), com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.fromInteger(p4));
        }
        return;
    }
}
