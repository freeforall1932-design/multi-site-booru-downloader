package com.bisimplex.firebooru.dataadapter;
 class SublistHomeItemHolder$2 implements android.widget.PopupMenu$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder this$0;

    SublistHomeItemHolder$2(com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p2)
    {
        com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder v2_1 = p2.getItemId();
        if (v2_1 != 2131362498) {
            if (v2_1 != 2131362453) {
                if (v2_1 != 2131362319) {
                    if (v2_1 != 2131362316) {
                        if (v2_1 != 2131362318) {
                            if (v2_1 != 2131362314) {
                                if (v2_1 != 2131361987) {
                                    if (v2_1 != 2131361871) {
                                        if (v2_1 == 2131362029) {
                                            this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Edit);
                                        }
                                    } else {
                                        this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.AddToGroup);
                                    }
                                } else {
                                    this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Delete);
                                }
                            } else {
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
                this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Reload);
            }
        } else {
            this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.Search);
        }
        return 1;
    }
}
