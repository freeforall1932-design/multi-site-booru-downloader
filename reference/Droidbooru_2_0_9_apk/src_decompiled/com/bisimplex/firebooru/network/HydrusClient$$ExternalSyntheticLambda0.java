package com.bisimplex.firebooru.network;
public final synthetic class HydrusClient$$ExternalSyntheticLambda0 implements java.lang.Runnable {
    public final synthetic com.bisimplex.firebooru.network.SourceQuery f$0;
    public final synthetic com.bisimplex.firebooru.danbooru.ServerItem f$1;

    public synthetic HydrusClient$$ExternalSyntheticLambda0(com.bisimplex.firebooru.network.SourceQuery p1, com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        this.f$0 = p1;
        this.f$1 = p2;
        return;
    }

    public final void run()
    {
        com.bisimplex.firebooru.network.HydrusClient.lambda$sendToHydrus$1(this.f$0, this.f$1);
        return;
    }
}
