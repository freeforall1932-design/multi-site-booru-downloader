package com.bisimplex.firebooru.fragment;
 class BaseFragment$6 implements android.view.MenuItem$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;

    BaseFragment$6(com.bisimplex.firebooru.fragment.BaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p1)
    {
        this.this$0.openDrawer();
        return 1;
    }
}
