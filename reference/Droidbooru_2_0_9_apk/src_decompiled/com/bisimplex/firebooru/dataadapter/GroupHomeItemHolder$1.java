package com.bisimplex.firebooru.dataadapter;
 class GroupHomeItemHolder$1 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder this$0;

    GroupHomeItemHolder$1(com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p2)
    {
        this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.ShowGroup);
        return;
    }
}
