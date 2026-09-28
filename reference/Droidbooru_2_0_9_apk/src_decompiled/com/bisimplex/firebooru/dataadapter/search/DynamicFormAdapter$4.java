package com.bisimplex.firebooru.dataadapter.search;
 class DynamicFormAdapter$4 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter this$0;

    DynamicFormAdapter$4(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p4)
    {
        int v0_4 = ((String) p4.getTag(2131362477));
        if (!android.text.TextUtils.isEmpty(v0_4)) {
            com.bisimplex.firebooru.dataadapter.search.ActionType v4_1 = ((com.bisimplex.firebooru.dataadapter.search.ActionType) p4.getTag(2131362474));
            if (v4_1 != null) {
                int v0_1 = this.this$0.findEditableItemByKey(v0_4);
                if (v0_1 != 0) {
                    com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter v1_1 = this.this$0;
                    v1_1.notifyAction(v1_1.data.indexOf(v0_1), v4_1);
                    return;
                }
            }
        }
        return;
    }
}
