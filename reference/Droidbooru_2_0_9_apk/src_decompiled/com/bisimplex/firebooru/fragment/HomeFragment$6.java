package com.bisimplex.firebooru.fragment;
 class HomeFragment$6 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.HomeFragment this$0;
    final synthetic com.bisimplex.firebooru.data.HomeItem val$item;
    final synthetic int val$itemIndex;

    HomeFragment$6(com.bisimplex.firebooru.fragment.HomeFragment p1, com.bisimplex.firebooru.data.HomeItem p2, int p3)
    {
        this.this$0 = p1;
        this.val$item = p2;
        this.val$itemIndex = p3;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.val$item.getSource(), this.val$item.getSpecs());
        this.val$item.setScrollToIndex(-1);
        this.val$item.setSource(com.bisimplex.firebooru.network.SourceFactory.getInstance().createSourceForSpecs(this.val$item.getSpecs()));
        this.this$0.adapter.notifyItemChanged(this.val$itemIndex);
        return;
    }
}
