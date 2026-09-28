package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$2 extends androidx.drawerlayout.widget.DrawerLayout$SimpleDrawerListener {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;

    MenuBaseActivity$2(com.bisimplex.firebooru.activity.MenuBaseActivity p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onDrawerClosed(android.view.View p2)
    {
        super.onDrawerClosed(p2);
        this.this$0.drawerClosed(p2);
        return;
    }

    public void onDrawerOpened(android.view.View p2)
    {
        super.onDrawerOpened(p2);
        this.this$0.drawerOpened(p2);
        return;
    }

    public void onDrawerSlide(android.view.View p1, float p2)
    {
        super.onDrawerSlide(p1, p2);
        return;
    }

    public void onDrawerStateChanged(int p1)
    {
        super.onDrawerStateChanged(p1);
        return;
    }
}
