package com.bisimplex.firebooru.fragment;
 class PostListFragment$16 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic androidx.recyclerview.widget.GridLayoutManager val$manager;
    final synthetic int val$visibleIndex;

    PostListFragment$16(com.bisimplex.firebooru.fragment.PostListFragment p1, androidx.recyclerview.widget.GridLayoutManager p2, int p3)
    {
        this.this$0 = p1;
        this.val$manager = p2;
        this.val$visibleIndex = p3;
        return;
    }

    public void run()
    {
        if ((!this.this$0.isDetached()) && (this.this$0.getActivity() != null)) {
            this.val$manager.scrollToPosition(this.val$visibleIndex);
        }
        return;
    }
}
