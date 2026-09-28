package com.bisimplex.firebooru.fragment;
 class PostListFragment$5 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;

    PostListFragment$5(com.bisimplex.firebooru.fragment.PostListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setThumbDisplayMode(com.bisimplex.firebooru.custom.ThumbDisplayMode.fromInteger(p2));
        com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$mrebindData(this.this$0);
        return;
    }
}
