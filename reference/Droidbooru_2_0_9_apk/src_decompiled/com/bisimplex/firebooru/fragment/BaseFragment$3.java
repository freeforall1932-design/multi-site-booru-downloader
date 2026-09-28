package com.bisimplex.firebooru.fragment;
 class BaseFragment$3 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;

    BaseFragment$3(com.bisimplex.firebooru.fragment.BaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.fragment.BaseFragment v0_1 = this.this$0.getActivity();
        if (v0_1 != null) {
            v0_1.getSupportFragmentManager().popBackStack();
        }
        this.this$0.HideLoading();
        return;
    }
}
