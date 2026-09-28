package com.bisimplex.firebooru.fragment;
 class PostListFragment$3 implements com.google.android.material.behavior.HideBottomViewOnScrollBehavior$OnScrollStateChangedListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;

    PostListFragment$3(com.bisimplex.firebooru.fragment.PostListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onStateChanged(android.view.View p5, int p6)
    {
        if (this.this$0.pageTextView != null) {
            android.animation.ObjectAnimator v5_3;
            long v0;
            this.this$0.pageTextView.clearAnimation();
            if (p6 != 2) {
                v5_3 = ((float) com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$fgetnavigationBottomMargin(this.this$0));
                v0 = 175;
            } else {
                v5_3 = 0;
                v0 = 225;
            }
            android.widget.TextView v6_2 = this.this$0.pageTextView;
            float[] v2_1 = new float[1];
            v2_1[0] = v5_3;
            android.animation.ObjectAnimator v5_5 = android.animation.ObjectAnimator.ofFloat(v6_2, "translationY", v2_1);
            v5_5.setDuration(v0);
            v5_5.start();
            return;
        } else {
            return;
        }
    }
}
