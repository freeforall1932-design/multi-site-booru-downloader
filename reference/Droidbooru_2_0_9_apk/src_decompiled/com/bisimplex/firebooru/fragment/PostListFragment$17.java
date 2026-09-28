package com.bisimplex.firebooru.fragment;
 class PostListFragment$17 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic androidx.fragment.app.DialogFragment val$dialog;
    final synthetic com.bisimplex.firebooru.model.SourceSpecs val$parent;
    final synthetic com.bisimplex.firebooru.model.SourceSpecs val$specs;

    PostListFragment$17(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2, com.bisimplex.firebooru.model.SourceSpecs p3, androidx.fragment.app.DialogFragment p4)
    {
        this.this$0 = p1;
        this.val$specs = p2;
        this.val$parent = p3;
        this.val$dialog = p4;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(this.val$specs, this.val$parent.getKey());
        this.this$0.onDialogSpecsSaved(this.val$dialog, this.val$parent, 1);
        return;
    }
}
