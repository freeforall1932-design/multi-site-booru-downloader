package com.bisimplex.firebooru.fragment;
 class BaseFragment$5 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;
    final synthetic String val$message;
    final synthetic com.bisimplex.firebooru.activity.MessageType val$type;

    BaseFragment$5(com.bisimplex.firebooru.fragment.BaseFragment p1, com.bisimplex.firebooru.activity.MessageType p2, String p3)
    {
        this.this$0 = p1;
        this.val$type = p2;
        this.val$message = p3;
        return;
    }

    public void run()
    {
        com.google.android.material.snackbar.Snackbar v0_4 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.this$0.getActivity());
        if (v0_4 != null) {
            String v1_2 = this.this$0.getSnackBarAnchorView();
            if (((this.val$type != com.bisimplex.firebooru.activity.MessageType.Success) && (this.val$type != com.bisimplex.firebooru.activity.MessageType.Minimal)) || (v1_2 == null)) {
                v0_4.ShowMessage(this.val$message, this.val$type);
                return;
            } else {
                v0_4.HideLoading();
                com.google.android.material.snackbar.Snackbar v0_3 = com.google.android.material.snackbar.Snackbar.make(v1_2, this.val$message, 0);
                v0_3.setAnchorView(v1_2);
                v0_3.show();
                return;
            }
        } else {
            return;
        }
    }
}
