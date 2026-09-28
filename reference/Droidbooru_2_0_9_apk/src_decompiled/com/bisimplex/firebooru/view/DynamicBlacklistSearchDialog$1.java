package com.bisimplex.firebooru.view;
 class DynamicBlacklistSearchDialog$1 implements com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener {
    final synthetic com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog this$0;

    DynamicBlacklistSearchDialog$1(com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, com.bisimplex.firebooru.dataadapter.search.ActionType p2, int p3)
    {
        if (p2 != com.bisimplex.firebooru.dataadapter.search.ActionType.Search) {
            if (p2 == com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
                com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog.-$$Nest$mreset(this.this$0);
                this.this$0.dismiss();
            }
            return;
        } else {
            this.this$0.search();
            this.this$0.dismiss();
            return;
        }
    }

    public void valueChanged(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, int p2)
    {
        return;
    }
}
