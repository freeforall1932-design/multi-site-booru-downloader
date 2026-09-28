package com.bisimplex.firebooru.fragment;
 class BaseFragment$8 implements com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;
    final synthetic com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener val$dialogListener;
    final synthetic com.bisimplex.firebooru.model.SourceSpecs val$specs;

    BaseFragment$8(com.bisimplex.firebooru.fragment.BaseFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener p3)
    {
        this.this$0 = p1;
        this.val$specs = p2;
        this.val$dialogListener = p3;
        return;
    }

    public void groupCreatd(com.bisimplex.firebooru.model.SourceSpecs p4)
    {
        p4.getChilds().add(this.val$specs);
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(p4, 0);
        this.val$dialogListener.onDialogSpecsSaved(0, p4, 0);
        return;
    }
}
