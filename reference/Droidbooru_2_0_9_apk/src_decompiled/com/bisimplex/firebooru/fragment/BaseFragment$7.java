package com.bisimplex.firebooru.fragment;
 class BaseFragment$7 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;

    BaseFragment$7(com.bisimplex.firebooru.fragment.BaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p1)
    {
        com.bisimplex.firebooru.activity.MainActivity v1_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.getActivity());
        if (v1_2 != null) {
            v1_2.openDrawer();
            return;
        } else {
            return;
        }
    }
}
