package com.bisimplex.firebooru.view;
 class JumpToPageDialog$4 implements android.widget.TextView$OnEditorActionListener {
    final synthetic com.bisimplex.firebooru.view.JumpToPageDialog this$0;

    JumpToPageDialog$4(com.bisimplex.firebooru.view.JumpToPageDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onEditorAction(android.widget.TextView p2, int p3, android.view.KeyEvent p4)
    {
        if ((p3 == null) && (p4.getAction() == 0)) {
            try {
                this.this$0.mListener.onDialogJumpToPageSelected(this.this$0, (Integer.parseInt(com.bisimplex.firebooru.view.JumpToPageDialog.-$$Nest$fgetnumberEditText(this.this$0).getText().toString()) - 1));
            } catch (com.bisimplex.firebooru.view.JumpToPageDialog v3_4) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_4);
                this.this$0.mListener.onDialogJumpToPageSelected(this.this$0, 0);
            } catch (Throwable v2_1) {
                this.this$0.dismiss();
                throw v2_1;
            }
            this.this$0.dismiss();
        }
        return 1;
    }
}
