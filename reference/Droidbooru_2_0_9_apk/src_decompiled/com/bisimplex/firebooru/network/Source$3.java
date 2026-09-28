package com.bisimplex.firebooru.network;
 class Source$3 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.network.Source this$0;
    final synthetic com.bisimplex.firebooru.network.Parser val$parser;

    Source$3(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.network.Parser p2)
    {
        this.this$0 = p1;
        this.val$parser = p2;
        return;
    }

    public void run()
    {
        this.this$0.endParsing(this.val$parser);
        return;
    }
}
