package com.bisimplex.firebooru.dataadapter;
 class GroupHomeItemHolder$3 implements android.widget.PopupMenu$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder this$0;

    GroupHomeItemHolder$3(com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p2)
    {
        com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder v2_1 = p2.getItemId();
        if (v2_1 != 2131361987) {
            if (v2_1 != 2131362458) {
                if (v2_1 != 2131362319) {
                    if (v2_1 != 2131362316) {
                        if (v2_1 != 2131362318) {
                            if (v2_1 == 2131362314) {
                                this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.MoveLast);
                            }
                        } else {
                            this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.MoveFirst);
                        }
                    } else {
                        this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.MoveDown);
                    }
                } else {
                    this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.MoveUp);
                }
            } else {
                this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Rename);
            }
        } else {
            this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Delete);
        }
        return 1;
    }
}
