package com.bisimplex.firebooru.fragment;
 class DynamicServerFormFragment$1 implements com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener {
    final synthetic com.bisimplex.firebooru.fragment.DynamicServerFormFragment this$0;

    DynamicServerFormFragment$1(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, com.bisimplex.firebooru.dataadapter.search.ActionType p2, int p3)
    {
        if (p2 != com.bisimplex.firebooru.dataadapter.search.ActionType.Add) {
            if (p2 != com.bisimplex.firebooru.dataadapter.search.ActionType.Search) {
                if (p2 != com.bisimplex.firebooru.dataadapter.search.ActionType.Focus) {
                    return;
                } else {
                    if ((this.this$0.recyclerView != null) && (com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$fgetbottomPaddingDecoration(this.this$0) != null)) {
                        androidx.recyclerview.widget.LinearLayoutManager v1_6 = ((androidx.recyclerview.widget.LinearLayoutManager) this.this$0.recyclerView.getLayoutManager());
                        if (v1_6 != null) {
                            v1_6.scrollToPositionWithOffset(p3, com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$fgetbottomPaddingDecoration(this.this$0).getBottomPadding());
                        }
                    }
                    return;
                }
            } else {
                com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$maskValidateClient(this.this$0);
                return;
            }
        } else {
            com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$msave(this.this$0);
            return;
        }
    }

    public void valueChanged(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1, int p2)
    {
        return;
    }
}
