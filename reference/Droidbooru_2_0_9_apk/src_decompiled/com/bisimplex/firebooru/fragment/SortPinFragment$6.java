package com.bisimplex.firebooru.fragment;
 class SortPinFragment$6 implements com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;

    SortPinFragment$6(com.bisimplex.firebooru.fragment.SortPinFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void groupCreatd(com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        if (com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetadapter(this.this$0) != null) {
            com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetadapter(this.this$0).addItem(p2);
            this.this$0.setEdited(1);
            return;
        } else {
            return;
        }
    }
}
