package com.bisimplex.firebooru.custom;
 class GlideImageLoader$1 implements com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener {
    final synthetic com.bisimplex.firebooru.custom.GlideImageLoader this$0;

    GlideImageLoader$1(com.bisimplex.firebooru.custom.GlideImageLoader p1)
    {
        this.this$0 = p1;
        return;
    }

    public float getGranualityPercentage()
    {
        return 1065353216;
    }

    public void onProgress(long p4, long p6)
    {
        if (com.bisimplex.firebooru.custom.GlideImageLoader.-$$Nest$fgetmProgressBar(this.this$0) != null) {
            com.bisimplex.firebooru.custom.GlideImageLoader.-$$Nest$fgetmProgressBar(this.this$0).setProgress(((int) ((p4 * 100) / p6)));
        }
        return;
    }
}
