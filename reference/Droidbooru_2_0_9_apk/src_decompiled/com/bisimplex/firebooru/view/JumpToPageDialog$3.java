package com.bisimplex.firebooru.view;
 class JumpToPageDialog$3 implements android.view.View$OnFocusChangeListener {
    final synthetic com.bisimplex.firebooru.view.JumpToPageDialog this$0;

    JumpToPageDialog$3(com.bisimplex.firebooru.view.JumpToPageDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onFocusChange(android.view.View p1, boolean p2)
    {
        com.bisimplex.firebooru.view.JumpToPageDialog.-$$Nest$fgetnumberEditText(this.this$0).post(new com.bisimplex.firebooru.view.JumpToPageDialog$3$1(this));
        return;
    }
}
