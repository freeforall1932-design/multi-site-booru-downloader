package com.bisimplex.firebooru.dataadapter.search;
 class TextItem$1 implements android.view.View$OnFocusChangeListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.TextItem this$0;

    TextItem$1(com.bisimplex.firebooru.dataadapter.search.TextItem p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onFocusChange(android.view.View p1, boolean p2)
    {
        if (p2 != null) {
            this.this$0.triggerAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Focus);
        }
        return;
    }
}
