package com.bisimplex.firebooru.fragment;
 class TagBlackListFormFragment$6 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.TagBlackListFormFragment this$0;
    final synthetic boolean[] val$selection;

    TagBlackListFormFragment$6(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p1, boolean[] p2)
    {
        this.this$0 = p1;
        this.val$selection = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.fragment.TagBlackListFormFragment v2_0 = 0;
        while (v2_0 < this.val$selection.length) {
            ((com.bisimplex.firebooru.danbooru.ServerItem) com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$fgetserverItems(this.this$0).get(v2_0)).setSelected(this.val$selection[v2_0]);
            v2_0++;
        }
        com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$mupdateButton(this.this$0);
        return;
    }
}
