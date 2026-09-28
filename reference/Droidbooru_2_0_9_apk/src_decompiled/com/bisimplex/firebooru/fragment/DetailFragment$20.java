package com.bisimplex.firebooru.fragment;
 class DetailFragment$20 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$20(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        this.this$0.getSource().forceLoadNextPage();
        return;
    }
}
