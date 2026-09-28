package com.bisimplex.firebooru.fragment;
 class DetailFragment$22 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;
    final synthetic com.bisimplex.firebooru.model.SourceSpecs val$specs;

    DetailFragment$22(com.bisimplex.firebooru.fragment.DetailFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2)
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
