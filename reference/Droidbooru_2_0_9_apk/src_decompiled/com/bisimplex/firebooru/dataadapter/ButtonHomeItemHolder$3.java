package com.bisimplex.firebooru.dataadapter;
 class ButtonHomeItemHolder$3 implements android.widget.PopupMenu$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder this$0;

    ButtonHomeItemHolder$3(com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p2)
    {
        com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder v2_1 = p2.getItemId();
        if (v2_1 != 2131362547) {
            if (v2_1 == 2131362546) {
                this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.SortNameDesc);
            }
        } else {
            this.this$0.triggerAction(com.bisimplex.firebooru.data.ItemActionType.SortName);
        }
        return 1;
    }
}
