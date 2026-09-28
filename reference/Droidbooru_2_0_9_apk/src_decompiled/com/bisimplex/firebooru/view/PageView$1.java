package com.bisimplex.firebooru.view;
 class PageView$1 implements android.view.animation.Animation$AnimationListener {
    final synthetic com.bisimplex.firebooru.view.PageView this$0;
    final synthetic android.view.View val$view;

    PageView$1(com.bisimplex.firebooru.view.PageView p1, android.view.View p2)
    {
        this.this$0 = p1;
        this.val$view = p2;
        return;
    }

    public void onAnimationEnd(android.view.animation.Animation p2)
    {
        if (this.this$0.getContext() != null) {
            this.val$view.setVisibility(4);
            if (this.val$view == com.bisimplex.firebooru.view.PageView.-$$Nest$fgetthumbImageView(this.this$0)) {
                com.bisimplex.firebooru.view.PageView.-$$Nest$fgetthumbImageView(this.this$0).setImageDrawable(0);
            }
        }
        return;
    }

    public void onAnimationRepeat(android.view.animation.Animation p1)
    {
        return;
    }

    public void onAnimationStart(android.view.animation.Animation p1)
    {
        return;
    }
}
