package com.bisimplex.firebooru.dataadapter;
 class TitleHomeItemHolder$2 implements android.widget.PopupMenu$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder this$0;

    TitleHomeItemHolder$2(com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p2)
    {
        com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder v2_1 = p2.getItemId();
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
