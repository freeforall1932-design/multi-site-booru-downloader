package com.bisimplex.firebooru.fragment;
 class RestoreFragment$4 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.RestoreFragment this$0;

    RestoreFragment$4(com.bisimplex.firebooru.fragment.RestoreFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        com.bisimplex.firebooru.activity.MainActivity v1_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.getActivity());
        if (v1_2 != null) {
            v1_2.showHome();
        }
        return;
    }
}
