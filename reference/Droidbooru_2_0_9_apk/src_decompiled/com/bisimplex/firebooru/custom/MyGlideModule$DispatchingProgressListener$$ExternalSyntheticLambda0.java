package com.bisimplex.firebooru.custom;
public final synthetic class MyGlideModule$DispatchingProgressListener$$ExternalSyntheticLambda0 implements java.lang.Runnable {
    public final synthetic com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener f$0;
    public final synthetic long f$1;
    public final synthetic long f$2;

    public synthetic MyGlideModule$DispatchingProgressListener$$ExternalSyntheticLambda0(com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener p1, long p2, long p4)
    {
        this.f$0 = p1;
        this.f$1 = p2;
        this.f$2 = p4;
        return;
    }

    public final void run()
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.lambda$update$0(this.f$0, this.f$1, this.f$2);
        return;
    }
}
