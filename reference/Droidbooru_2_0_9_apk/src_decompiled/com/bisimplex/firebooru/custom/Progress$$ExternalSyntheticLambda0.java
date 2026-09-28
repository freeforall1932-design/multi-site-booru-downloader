package com.bisimplex.firebooru.custom;
public final synthetic class Progress$$ExternalSyntheticLambda0 implements okhttp3.Interceptor {
    public final synthetic com.bisimplex.firebooru.custom.Progress$ProgressListener f$0;

    public synthetic Progress$$ExternalSyntheticLambda0(com.bisimplex.firebooru.custom.Progress$ProgressListener p1)
    {
        this.f$0 = p1;
        return;
    }

    public final okhttp3.Response intercept(okhttp3.Interceptor$Chain p2)
    {
        return com.bisimplex.firebooru.custom.Progress.lambda$run$0(this.f$0, p2);
    }
}
