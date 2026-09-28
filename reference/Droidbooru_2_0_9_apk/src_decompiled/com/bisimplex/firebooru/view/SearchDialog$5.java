package com.bisimplex.firebooru.view;
 class SearchDialog$5 implements android.view.View$OnFocusChangeListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$5(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onFocusChange(android.view.View p1, boolean p2)
    {
        if (p2 == 0) {
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$msetImeVisibility(this.this$0, 0);
            return;
        } else {
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$msetImeVisibility(this.this$0, 1);
            return;
        }
    }
}
