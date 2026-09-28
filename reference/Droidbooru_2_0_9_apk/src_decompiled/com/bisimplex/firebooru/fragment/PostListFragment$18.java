package com.bisimplex.firebooru.fragment;
 class PostListFragment$18 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic com.bisimplex.firebooru.model.SourceSpecs val$specs;

    PostListFragment$18(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        this.this$0 = p1;
        this.val$specs = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        this.this$0.askAddGroup(this.val$specs, this.this$0);
        return;
    }
}
