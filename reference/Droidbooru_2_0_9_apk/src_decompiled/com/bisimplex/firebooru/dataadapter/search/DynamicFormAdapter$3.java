package com.bisimplex.firebooru.dataadapter.search;
 class DynamicFormAdapter$3 implements android.widget.CompoundButton$OnCheckedChangeListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter this$0;

    DynamicFormAdapter$3(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onCheckedChanged(android.widget.CompoundButton p2, boolean p3)
    {
        com.bisimplex.firebooru.dataadapter.search.CheckboxItem v2_3 = ((String) p2.getTag(2131362477));
        if (!android.text.TextUtils.isEmpty(v2_3)) {
            com.bisimplex.firebooru.dataadapter.search.CheckboxItem v2_1 = ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) this.this$0.findEditableItemByKey(v2_3));
            if (v2_1 != null) {
                v2_1.setValue(Boolean.valueOf(p3));
                return;
            }
        }
        return;
    }
}
