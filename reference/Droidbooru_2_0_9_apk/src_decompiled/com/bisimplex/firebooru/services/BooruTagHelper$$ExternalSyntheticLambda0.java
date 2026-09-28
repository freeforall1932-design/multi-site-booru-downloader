package com.bisimplex.firebooru.services;
public final synthetic class BooruTagHelper$$ExternalSyntheticLambda0 implements java.lang.Runnable {
    public final synthetic ref.WeakReference f$0;
    public final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost f$1;

    public synthetic BooruTagHelper$$ExternalSyntheticLambda0(ref.WeakReference p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.f$0 = p1;
        this.f$1 = p2;
        return;
    }

    public final void run()
    {
        com.bisimplex.firebooru.services.BooruTagHelper.lambda$processTagsAsync$2(this.f$0, this.f$1);
        return;
    }
}
