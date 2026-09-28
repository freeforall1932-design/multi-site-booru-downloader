package com.bisimplex.firebooru.view;
 class SearchDialog$7 implements android.view.View$OnKeyListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$7(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onKey(android.view.View p1, int p2, android.view.KeyEvent p3)
    {
        if ((p2 != 66) || (p3.getAction() != 1)) {
            return 0;
        } else {
            this.this$0.search();
            return 1;
        }
    }
}
