package com.bisimplex.firebooru.network;
 class Source$2 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.network.Source this$0;
    final synthetic com.bisimplex.firebooru.data.FailureType val$failureType;

    Source$2(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        this.this$0 = p1;
        this.val$failureType = p2;
        return;
    }

    public void run()
    {
        this.this$0.notifyFailure(this.val$failureType);
        return;
    }
}
