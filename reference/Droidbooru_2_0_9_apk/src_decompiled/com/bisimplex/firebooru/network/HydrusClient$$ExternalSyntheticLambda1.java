package com.bisimplex.firebooru.network;
public final synthetic class HydrusClient$$ExternalSyntheticLambda1 implements java.lang.Runnable {
    public final synthetic com.bisimplex.firebooru.danbooru.ServerItem f$0;
    public final synthetic java.util.List f$1;

    public synthetic HydrusClient$$ExternalSyntheticLambda1(com.bisimplex.firebooru.danbooru.ServerItem p1, java.util.List p2)
    {
        this.f$0 = p1;
        this.f$1 = p2;
        return;
    }

    public final void run()
    {
        com.bisimplex.firebooru.network.HydrusClient.lambda$sendToHydrus$0(this.f$0, this.f$1);
        return;
    }
}
