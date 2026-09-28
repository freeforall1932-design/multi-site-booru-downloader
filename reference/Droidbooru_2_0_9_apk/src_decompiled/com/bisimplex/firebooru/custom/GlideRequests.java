package com.bisimplex.firebooru.custom;
public class GlideRequests extends com.bumptech.glide.RequestManager {

    public GlideRequests(com.bumptech.glide.Glide p1, com.bumptech.glide.manager.Lifecycle p2, com.bumptech.glide.manager.RequestManagerTreeNode p3, android.content.Context p4)
    {
        super(p1, p2, p3, p4);
        return;
    }

    public com.bisimplex.firebooru.custom.GlideRequests addDefaultRequestListener(com.bumptech.glide.request.RequestListener p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) super.addDefaultRequestListener(p1));
    }

    public bridge synthetic com.bumptech.glide.RequestManager addDefaultRequestListener(com.bumptech.glide.request.RequestListener p1)
    {
        return this.addDefaultRequestListener(p1);
    }

    public declared_synchronized com.bisimplex.firebooru.custom.GlideRequests applyDefaultRequestOptions(com.bumptech.glide.request.RequestOptions p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) super.applyDefaultRequestOptions(p1));
    }

    public bridge synthetic com.bumptech.glide.RequestManager applyDefaultRequestOptions(com.bumptech.glide.request.RequestOptions p1)
    {
        return this.applyDefaultRequestOptions(p1);
    }

    public com.bisimplex.firebooru.custom.GlideRequest as(Class p4)
    {
        return new com.bisimplex.firebooru.custom.GlideRequest(this.glide, this, p4, this.context);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder as(Class p1)
    {
        return this.as(p1);
    }

    public com.bisimplex.firebooru.custom.GlideRequest asBitmap()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.asBitmap());
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder asBitmap()
    {
        return this.asBitmap();
    }

    public com.bisimplex.firebooru.custom.GlideRequest asDrawable()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.asDrawable());
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder asDrawable()
    {
        return this.asDrawable();
    }

    public com.bisimplex.firebooru.custom.GlideRequest asFile()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.asFile());
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder asFile()
    {
        return this.asFile();
    }

    public com.bisimplex.firebooru.custom.GlideRequest asGif()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.asGif());
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder asGif()
    {
        return this.asGif();
    }

    public declared_synchronized com.bisimplex.firebooru.custom.GlideRequests clearOnStop()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) super.clearOnStop());
    }

    public bridge synthetic com.bumptech.glide.RequestManager clearOnStop()
    {
        return this.clearOnStop();
    }

    public com.bisimplex.firebooru.custom.GlideRequest download(Object p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.download(p1));
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder download(Object p1)
    {
        return this.download(p1);
    }

    public com.bisimplex.firebooru.custom.GlideRequest downloadOnly()
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.downloadOnly());
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder downloadOnly()
    {
        return this.downloadOnly();
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(android.graphics.Bitmap p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(android.graphics.drawable.Drawable p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(android.net.Uri p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(java.io.File p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(Integer p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(Object p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(String p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(java.net.URL p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public com.bisimplex.firebooru.custom.GlideRequest load(byte[] p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequest) super.load(p1));
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(android.graphics.Bitmap p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(android.graphics.drawable.Drawable p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(android.net.Uri p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(java.io.File p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(Integer p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(Object p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(String p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(java.net.URL p1)
    {
        return this.load(p1);
    }

    public bridge synthetic com.bumptech.glide.RequestBuilder load(byte[] p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(android.graphics.Bitmap p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(android.graphics.drawable.Drawable p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(android.net.Uri p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(java.io.File p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(Integer p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(Object p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(String p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(java.net.URL p1)
    {
        return this.load(p1);
    }

    public bridge synthetic Object load(byte[] p1)
    {
        return this.load(p1);
    }

    public declared_synchronized com.bisimplex.firebooru.custom.GlideRequests setDefaultRequestOptions(com.bumptech.glide.request.RequestOptions p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) super.setDefaultRequestOptions(p1));
    }

    public bridge synthetic com.bumptech.glide.RequestManager setDefaultRequestOptions(com.bumptech.glide.request.RequestOptions p1)
    {
        return this.setDefaultRequestOptions(p1);
    }

    protected void setRequestOptions(com.bumptech.glide.request.RequestOptions p2)
    {
        if (!(p2 instanceof com.bisimplex.firebooru.custom.GlideOptions)) {
            super.setRequestOptions(new com.bisimplex.firebooru.custom.GlideOptions().apply(p2));
            return;
        } else {
            super.setRequestOptions(p2);
            return;
        }
    }
}
