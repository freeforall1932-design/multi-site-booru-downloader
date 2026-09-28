package com.bisimplex.firebooru.fragment;
 class DetailFragment$3 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$3(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p1)
    {
        com.bisimplex.firebooru.activity.MainActivity v1_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.getActivity());
        if (v1_2 != null) {
            v1_2.openDrawer();
        }
        return;
    }
}
