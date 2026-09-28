package com.bisimplex.firebooru.fragment;
 class SortPinFragment$7 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;

    SortPinFragment$7(com.bisimplex.firebooru.fragment.SortPinFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        this.this$0.setEdited(0);
        this.this$0.goBackInStack();
        return;
    }
}
