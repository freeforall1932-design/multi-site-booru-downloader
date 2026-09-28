package com.bisimplex.firebooru.fragment;
 class BaseFragment$4 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;
    final synthetic int val$code;
    final synthetic com.bisimplex.firebooru.data.FailureType val$failureType;

    BaseFragment$4(com.bisimplex.firebooru.fragment.BaseFragment p1, com.bisimplex.firebooru.data.FailureType p2, int p3)
    {
        this.this$0 = p1;
        this.val$failureType = p2;
        this.val$code = p3;
        return;
    }

    public void run()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.this$0.requireActivity());
        v0_1.setMessage(this.this$0.messageForFailure(this.val$failureType, this.val$code));
        v0_1.setTitle(2131887211);
        v0_1.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.BaseFragment$4$1(this));
        v0_1.setNeutralButton(2131887244, new com.bisimplex.firebooru.fragment.BaseFragment$4$2(this));
        v0_1.show();
        return;
    }
}
