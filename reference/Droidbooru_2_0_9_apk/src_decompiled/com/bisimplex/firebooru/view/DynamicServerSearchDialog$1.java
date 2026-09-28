package com.bisimplex.firebooru.view;
 class DynamicServerSearchDialog$1 implements com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener {
    final synthetic com.bisimplex.firebooru.view.DynamicServerSearchDialog this$0;

    DynamicServerSearchDialog$1(com.bisimplex.firebooru.view.DynamicServerSearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, com.bisimplex.firebooru.dataadapter.search.ActionType p2, int p3)
    {
        if (p2 != com.bisimplex.firebooru.dataadapter.search.ActionType.Search) {
            if (p2 == com.bisimplex.firebooru.dataadapter.search.ActionType.Reset) {
                com.bisimplex.firebooru.view.DynamicServerSearchDialog.-$$Nest$mreset(this.this$0);
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
