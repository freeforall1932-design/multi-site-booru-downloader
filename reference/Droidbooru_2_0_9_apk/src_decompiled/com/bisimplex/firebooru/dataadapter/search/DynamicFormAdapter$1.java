package com.bisimplex.firebooru.dataadapter.search;
 class DynamicFormAdapter$1 implements com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter this$0;

    DynamicFormAdapter$1(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.Item p3, com.bisimplex.firebooru.dataadapter.search.ActionType p4)
    {
        com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter v0 = this.this$0;
        v0.notifyAction(v0.data.indexOf(p3), p4);
        return;
    }

    public void valueChanged(com.bisimplex.firebooru.dataadapter.search.Item p2)
    {
        this.this$0.notifyValueChanged(p2);
        return;
    }
}
