package com.bisimplex.firebooru.fragment;
 class TagsBlackListFragment$1 implements com.bisimplex.firebooru.fragment.TagsBlackListFragment$ListItemClick {
    final synthetic com.bisimplex.firebooru.fragment.TagsBlackListFragment this$0;

    TagsBlackListFragment$1(com.bisimplex.firebooru.fragment.TagsBlackListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onListItemClick(int p2)
    {
        this.this$0.showItem(p2);
        return;
    }

    public void onListItemDeleteClick(int p2)
    {
        this.this$0.deleteItemAt(p2);
        return;
    }

    public void onListItemLongClick(int p1)
    {
        return;
    }
}
