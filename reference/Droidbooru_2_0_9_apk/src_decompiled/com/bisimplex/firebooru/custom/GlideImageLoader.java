package com.bisimplex.firebooru.custom;
public class GlideImageLoader {
    private android.widget.ImageView mImageView;
    private android.widget.ProgressBar mProgressBar;

    static bridge synthetic android.widget.ProgressBar -$$Nest$fgetmProgressBar(com.bisimplex.firebooru.custom.GlideImageLoader p0)
    {
        return p0.mProgressBar;
    }

    static bridge synthetic void -$$Nest$monFinished(com.bisimplex.firebooru.custom.GlideImageLoader p0)
    {
        p0.onFinished();
        return;
    }

    public GlideImageLoader(android.widget.ImageView p1, android.widget.ProgressBar p2)
    {
        this.mImageView = p1;
        this.mProgressBar = p2;
        return;
    }

    private void onConnecting()
    {
        android.widget.ProgressBar v0 = this.mProgressBar;
        if (v0 != null) {
            v0.setVisibility(0);
        }
        return;
    }

    private void onFinished()
    {
        android.widget.ImageView v0_0 = this.mProgressBar;
        if ((v0_0 != null) && (this.mImageView != null)) {
            v0_0.setVisibility(8);
            this.mImageView.setVisibility(0);
        }
        return;
    }

    public void load(String p3, com.bumptech.glide.request.RequestOptions p4)
    {
        if ((p3 != null) && (p4 != null)) {
            this.onConnecting();
            com.bisimplex.firebooru.custom.MyGlideModule.expect(p3, new com.bisimplex.firebooru.custom.GlideImageLoader$1(this));
            com.bumptech.glide.Glide.with(this.mImageView.getContext()).load(p3).transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade()).apply(p4.skipMemoryCache(1)).listener(new com.bisimplex.firebooru.custom.GlideImageLoader$2(this, p3)).into(this.mImageView);
        }
        return;
    }
}
