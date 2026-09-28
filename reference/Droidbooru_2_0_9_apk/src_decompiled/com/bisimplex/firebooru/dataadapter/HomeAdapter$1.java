package com.bisimplex.firebooru.dataadapter;
 class HomeAdapter$1 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.dataadapter.HomeAdapter this$0;
    final synthetic int val$position;

    HomeAdapter$1(com.bisimplex.firebooru.dataadapter.HomeAdapter p1, int p2)
    {
        this.this$0 = p1;
        this.val$position = p2;
        return;
    }

    public void run()
    {
        if (com.bisimplex.firebooru.dataadapter.HomeAdapter.-$$Nest$fgetitemListenerWeakReference(this.this$0).get() != null) {
            if (!((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) com.bisimplex.firebooru.dataadapter.HomeAdapter.-$$Nest$fgetitemListenerWeakReference(this.this$0).get()).isComputingLayout()) {
                this.this$0.notifyItemChanged(this.val$position);
            } else {
                com.bisimplex.firebooru.dataadapter.HomeAdapter.-$$Nest$mpostAndNotifyAdapterAtPosition(this.this$0, this.val$position);
                return;
            }
        }
        return;
    }
}
