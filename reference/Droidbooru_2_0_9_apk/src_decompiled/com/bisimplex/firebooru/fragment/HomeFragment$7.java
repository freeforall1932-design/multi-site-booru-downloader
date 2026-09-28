package com.bisimplex.firebooru.fragment;
 class HomeFragment$7 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.HomeFragment this$0;
    final synthetic com.bisimplex.firebooru.data.HomeItem val$item;
    final synthetic int val$itemIndex;

    HomeFragment$7(com.bisimplex.firebooru.fragment.HomeFragment p1, int p2, com.bisimplex.firebooru.data.HomeItem p3)
    {
        this.this$0 = p1;
        this.val$itemIndex = p2;
        this.val$item = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.this$0.adapter.removeItem(this.val$itemIndex);
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().deleteSourceSpecs(this.val$item.getSpecs(), this.this$0.selectedGroupID);
        if (this.val$item.getType() == 1) {
            com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.val$item.getSource(), this.val$item.getSpecs());
        }
        return;
    }
}
