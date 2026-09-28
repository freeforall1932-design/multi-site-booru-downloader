package com.bisimplex.firebooru.fragment;
 class DynamicFileNameFragment$1 implements com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener {
    final synthetic com.bisimplex.firebooru.fragment.DynamicFileNameFragment this$0;

    DynamicFileNameFragment$1(com.bisimplex.firebooru.fragment.DynamicFileNameFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, com.bisimplex.firebooru.dataadapter.search.ActionType p2, int p3)
    {
        if (p2 == com.bisimplex.firebooru.dataadapter.search.ActionType.Edit) {
            com.bisimplex.firebooru.fragment.DynamicFileNameFragment.-$$Nest$msave(this.this$0);
        }
        return;
    }

    public void valueChanged(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, int p2)
    {
        return;
    }
}
