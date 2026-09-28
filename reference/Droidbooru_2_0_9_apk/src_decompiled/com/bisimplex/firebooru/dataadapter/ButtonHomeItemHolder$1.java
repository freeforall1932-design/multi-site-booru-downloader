package com.bisimplex.firebooru.dataadapter;
 class ButtonHomeItemHolder$1 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder this$0;

    ButtonHomeItemHolder$1(com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p2)
    {
        this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Browse);
        return;
    }
}
