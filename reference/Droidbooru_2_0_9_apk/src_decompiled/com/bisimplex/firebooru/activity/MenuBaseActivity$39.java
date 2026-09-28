package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$39 implements android.view.animation.Animation$AnimationListener {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic android.widget.ProgressBar val$progressBar;

    MenuBaseActivity$39(com.bisimplex.firebooru.activity.MenuBaseActivity p1, android.widget.ProgressBar p2)
    {
        this.this$0 = p1;
        this.val$progressBar = p2;
        return;
    }

    public void onAnimationEnd(android.view.animation.Animation p2)
    {
        this.val$progressBar.setVisibility(8);
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
