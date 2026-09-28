package com.bisimplex.firebooru.fragment;
 class TagBlackListFormFragment$4 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.TagBlackListFormFragment this$0;

    TagBlackListFormFragment$4(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        if (com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$fgetserverItems(this.this$0) != null) {
            p3 = 0;
            while (p3 < com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$fgetserverItems(this.this$0).size()) {
                ((com.bisimplex.firebooru.danbooru.ServerItem) com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$fgetserverItems(this.this$0).get(p3)).setSelected(0);
                p3++;
            }
            com.bisimplex.firebooru.fragment.TagBlackListFormFragment.-$$Nest$mupdateButton(this.this$0);
            return;
        } else {
            return;
        }
    }
}
