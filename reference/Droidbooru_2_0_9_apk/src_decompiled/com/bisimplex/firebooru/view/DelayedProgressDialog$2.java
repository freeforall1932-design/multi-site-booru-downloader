package com.bisimplex.firebooru.view;
 class DelayedProgressDialog$2 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.DelayedProgressDialog this$0;

    DelayedProgressDialog$2(com.bisimplex.firebooru.view.DelayedProgressDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        this.this$0.dismissAllowingStateLoss();
        return;
    }
}
