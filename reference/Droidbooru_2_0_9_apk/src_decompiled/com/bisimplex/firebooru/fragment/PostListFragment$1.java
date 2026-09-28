package com.bisimplex.firebooru.fragment;
 class PostListFragment$1 extends androidx.recyclerview.widget.RecyclerView$OnScrollListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;

    PostListFragment$1(com.bisimplex.firebooru.fragment.PostListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onScrollStateChanged(androidx.recyclerview.widget.RecyclerView p1, int p2)
    {
        super.onScrollStateChanged(p1, p2);
        if ((p2 == 2) && (this.this$0.pageTextView.getVisibility() == 0)) {
            this.this$0.updatePageLabel();
        }
        return;
    }
}
