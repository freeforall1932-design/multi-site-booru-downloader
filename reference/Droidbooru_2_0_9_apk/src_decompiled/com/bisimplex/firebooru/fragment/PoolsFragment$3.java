package com.bisimplex.firebooru.fragment;
 class PoolsFragment$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PoolsFragment this$0;
    final synthetic android.view.View val$caller;
    final synthetic int val$position;

    PoolsFragment$3(com.bisimplex.firebooru.fragment.PoolsFragment p1, android.view.View p2, int p3)
    {
        this.this$0 = p1;
        this.val$caller = p2;
        this.val$position = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.this$0.itemClick(this.val$caller, this.val$position);
        return;
    }
}
