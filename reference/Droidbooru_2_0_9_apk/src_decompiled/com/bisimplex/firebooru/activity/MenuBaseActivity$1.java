package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$1 implements androidx.core.view.OnApplyWindowInsetsListener {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;

    MenuBaseActivity$1(com.bisimplex.firebooru.activity.MenuBaseActivity p1)
    {
        this.this$0 = p1;
        return;
    }

    public androidx.core.view.WindowInsetsCompat onApplyWindowInsets(android.view.View p7, androidx.core.view.WindowInsetsCompat p8)
    {
        int v7_2 = p8.getInsets(androidx.core.view.WindowInsetsCompat$Type.displayCutout());
        int v0_2 = p8.getInsets(androidx.core.view.WindowInsetsCompat$Type.systemBars());
        this.this$0.menuDrawer.getRecyclerView().setPadding(v7_2.left, v0_2.top, v7_2.right, v0_2.bottom);
        this.this$0.secondaryDrawer.getRecyclerView().setPadding(v7_2.left, v0_2.top, v7_2.right, v0_2.bottom);
        return p8;
    }
}
