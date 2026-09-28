package com.bisimplex.firebooru.view;
 class DelayedProgressDialog$1 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.DelayedProgressDialog this$0;
    final synthetic androidx.fragment.app.FragmentManager val$fm;
    final synthetic String val$tag;

    DelayedProgressDialog$1(com.bisimplex.firebooru.view.DelayedProgressDialog p1, androidx.fragment.app.FragmentManager p2, String p3)
    {
        this.this$0 = p1;
        this.val$fm = p2;
        this.val$tag = p3;
        return;
    }

    public void run()
    {
        if (com.bisimplex.firebooru.view.DelayedProgressDialog.-$$Nest$fgetmStopMillisecond(this.this$0) > System.currentTimeMillis()) {
            com.bisimplex.firebooru.view.DelayedProgressDialog.-$$Nest$mshowDialogAfterDelay(this.this$0, this.val$fm, this.val$tag);
        }
        return;
    }
}
