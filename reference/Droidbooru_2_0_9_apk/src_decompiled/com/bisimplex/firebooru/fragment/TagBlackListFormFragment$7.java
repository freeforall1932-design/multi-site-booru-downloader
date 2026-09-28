package com.bisimplex.firebooru.fragment;
 class TagBlackListFormFragment$7 implements android.content.DialogInterface$OnMultiChoiceClickListener {
    final synthetic com.bisimplex.firebooru.fragment.TagBlackListFormFragment this$0;
    final synthetic boolean[] val$selection;

    TagBlackListFormFragment$7(com.bisimplex.firebooru.fragment.TagBlackListFormFragment p1, boolean[] p2)
    {
        this.this$0 = p1;
        this.val$selection = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2, boolean p3)
    {
        this.val$selection[p2] = p3;
        return;
    }
}
