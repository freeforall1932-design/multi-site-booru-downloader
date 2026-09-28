package com.bisimplex.firebooru.fragment;
 class SortPinFragment$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;
    final synthetic int val$position;
    final synthetic java.util.List val$specsList;

    SortPinFragment$2(com.bisimplex.firebooru.fragment.SortPinFragment p1, int p2, java.util.List p3)
    {
        this.this$0 = p1;
        this.val$position = p2;
        this.val$specsList = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$mmoveItemToFolder(this.this$0, this.val$position, this.val$specsList, p4);
        return;
    }
}
