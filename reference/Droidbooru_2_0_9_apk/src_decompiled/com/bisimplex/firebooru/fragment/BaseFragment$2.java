package com.bisimplex.firebooru.fragment;
 class BaseFragment$2 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;

    BaseFragment$2(com.bisimplex.firebooru.fragment.BaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.this$0.getActivity());
        if (v0_2 != null) {
            v0_2.HideLoading();
        }
        return;
    }
}
