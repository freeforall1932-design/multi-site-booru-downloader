package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$DetailPagerHolder$1 implements android.view.animation.Animation$AnimationListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder this$1;
    final synthetic android.view.View val$view;

    DetailPagerAdapter$DetailPagerHolder$1(com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder p1, android.view.View p2)
    {
        this.this$1 = p1;
        this.val$view = p2;
        return;
    }

    public void onAnimationEnd(android.view.animation.Animation p2)
    {
        this.val$view.setVisibility(4);
        if (this.val$view == this.this$1.thumbImageView) {
            this.this$1.thumbImageView.setImageDrawable(0);
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
