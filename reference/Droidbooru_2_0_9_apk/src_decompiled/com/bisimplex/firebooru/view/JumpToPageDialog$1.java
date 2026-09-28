package com.bisimplex.firebooru.view;
 class JumpToPageDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.JumpToPageDialog this$0;

    JumpToPageDialog$1(com.bisimplex.firebooru.view.JumpToPageDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        try {
            this.this$0.mListener.onDialogJumpToPageSelected(this.this$0, (Integer.parseInt(com.bisimplex.firebooru.view.JumpToPageDialog.-$$Nest$fgetnumberEditText(this.this$0).getText().toString()) - 1));
            return;
        } catch (com.bisimplex.firebooru.view.JumpToPageDialog$JumpToPageDialogListener v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            this.this$0.mListener.onDialogJumpToPageSelected(this.this$0, 0);
            return;
        }
    }
}
