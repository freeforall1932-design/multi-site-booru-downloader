package com.bisimplex.firebooru.fragment;
 class TagsBlackListFragment$ItemHolder$1 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder this$0;

    TagsBlackListFragment$ItemHolder$1(com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p2)
    {
        this.this$0.listener.onListItemDeleteClick(this.this$0.getBindingAdapterPosition());
        return;
    }
}
