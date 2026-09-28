package com.bisimplex.firebooru.custom;
public final class GlideOptions extends com.bumptech.glide.request.RequestOptions implements java.lang.Cloneable {
    private static com.bisimplex.firebooru.custom.GlideOptions centerCropTransform2;
    private static com.bisimplex.firebooru.custom.GlideOptions centerInsideTransform1;
    private static com.bisimplex.firebooru.custom.GlideOptions circleCropTransform3;
    private static com.bisimplex.firebooru.custom.GlideOptions fitCenterTransform0;
    private static com.bisimplex.firebooru.custom.GlideOptions noAnimation5;
    private static com.bisimplex.firebooru.custom.GlideOptions noTransformation4;

    public GlideOptions()
    {
        return;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions bitmapTransform(com.bumptech.glide.load.Transformation p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().transform(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions centerCropTransform()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.centerCropTransform2 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.centerCropTransform2 = new com.bisimplex.firebooru.custom.GlideOptions().centerCrop().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.centerCropTransform2;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions centerInsideTransform()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.centerInsideTransform1 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.centerInsideTransform1 = new com.bisimplex.firebooru.custom.GlideOptions().centerInside().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.centerInsideTransform1;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions circleCropTransform()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.circleCropTransform3 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.circleCropTransform3 = new com.bisimplex.firebooru.custom.GlideOptions().circleCrop().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.circleCropTransform3;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions decodeTypeOf(Class p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().decode(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions diskCacheStrategyOf(com.bumptech.glide.load.engine.DiskCacheStrategy p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().diskCacheStrategy(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions downsampleOf(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().downsample(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions encodeFormatOf(android.graphics.Bitmap$CompressFormat p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().encodeFormat(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions encodeQualityOf(int p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().encodeQuality(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions errorOf(int p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().error(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions errorOf(android.graphics.drawable.Drawable p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().error(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions fitCenterTransform()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.fitCenterTransform0 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.fitCenterTransform0 = new com.bisimplex.firebooru.custom.GlideOptions().fitCenter().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.fitCenterTransform0;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions formatOf(com.bumptech.glide.load.DecodeFormat p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().format(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions frameOf(long p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().frame(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions noAnimation()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.noAnimation5 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.noAnimation5 = new com.bisimplex.firebooru.custom.GlideOptions().dontAnimate().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.noAnimation5;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions noTransformation()
    {
        if (com.bisimplex.firebooru.custom.GlideOptions.noTransformation4 == null) {
            com.bisimplex.firebooru.custom.GlideOptions.noTransformation4 = new com.bisimplex.firebooru.custom.GlideOptions().dontTransform().autoClone();
        }
        return com.bisimplex.firebooru.custom.GlideOptions.noTransformation4;
    }

    public static com.bisimplex.firebooru.custom.GlideOptions option(com.bumptech.glide.load.Option p1, Object p2)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().set(p1, p2);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions overrideOf(int p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().override(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions overrideOf(int p1, int p2)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().override(p1, p2);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions placeholderOf(int p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().placeholder(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions placeholderOf(android.graphics.drawable.Drawable p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().placeholder(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions priorityOf(com.bumptech.glide.Priority p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().priority(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions signatureOf(com.bumptech.glide.load.Key p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().signature(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions sizeMultiplierOf(float p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().sizeMultiplier(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions skipMemoryCacheOf(boolean p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().skipMemoryCache(p1);
    }

    public static com.bisimplex.firebooru.custom.GlideOptions timeoutOf(int p1)
    {
        return new com.bisimplex.firebooru.custom.GlideOptions().timeout(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions apply(com.bumptech.glide.request.BaseRequestOptions p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.apply(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions apply(com.bumptech.glide.request.BaseRequestOptions p1)
    {
        return this.apply(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions autoClone()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.autoClone());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions autoClone()
    {
        return this.autoClone();
    }

    public com.bisimplex.firebooru.custom.GlideOptions centerCrop()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.centerCrop());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions centerCrop()
    {
        return this.centerCrop();
    }

    public com.bisimplex.firebooru.custom.GlideOptions centerInside()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.centerInside());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions centerInside()
    {
        return this.centerInside();
    }

    public com.bisimplex.firebooru.custom.GlideOptions circleCrop()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.circleCrop());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions circleCrop()
    {
        return this.circleCrop();
    }

    public com.bisimplex.firebooru.custom.GlideOptions clone()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.clone());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions clone()
    {
        return this.clone();
    }

    public bridge synthetic Object clone()
    {
        return this.clone();
    }

    public com.bisimplex.firebooru.custom.GlideOptions decode(Class p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.decode(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions decode(Class p1)
    {
        return this.decode(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions disallowHardwareConfig()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.disallowHardwareConfig());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions disallowHardwareConfig()
    {
        return this.disallowHardwareConfig();
    }

    public com.bisimplex.firebooru.custom.GlideOptions diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.diskCacheStrategy(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy p1)
    {
        return this.diskCacheStrategy(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions dontAnimate()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.dontAnimate());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions dontAnimate()
    {
        return this.dontAnimate();
    }

    public com.bisimplex.firebooru.custom.GlideOptions dontTransform()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.dontTransform());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions dontTransform()
    {
        return this.dontTransform();
    }

    public com.bisimplex.firebooru.custom.GlideOptions downsample(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.downsample(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions downsample(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy p1)
    {
        return this.downsample(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions encodeFormat(android.graphics.Bitmap$CompressFormat p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.encodeFormat(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions encodeFormat(android.graphics.Bitmap$CompressFormat p1)
    {
        return this.encodeFormat(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions encodeQuality(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.encodeQuality(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions encodeQuality(int p1)
    {
        return this.encodeQuality(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions error(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.error(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions error(android.graphics.drawable.Drawable p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.error(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions error(int p1)
    {
        return this.error(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions error(android.graphics.drawable.Drawable p1)
    {
        return this.error(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions fallback(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.fallback(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions fallback(android.graphics.drawable.Drawable p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.fallback(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions fallback(int p1)
    {
        return this.fallback(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions fallback(android.graphics.drawable.Drawable p1)
    {
        return this.fallback(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions fitCenter()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.fitCenter());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions fitCenter()
    {
        return this.fitCenter();
    }

    public com.bisimplex.firebooru.custom.GlideOptions format(com.bumptech.glide.load.DecodeFormat p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.format(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions format(com.bumptech.glide.load.DecodeFormat p1)
    {
        return this.format(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions frame(long p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.frame(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions frame(long p1)
    {
        return this.frame(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions lock()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.lock());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions lock()
    {
        return this.lock();
    }

    public com.bisimplex.firebooru.custom.GlideOptions onlyRetrieveFromCache(boolean p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.onlyRetrieveFromCache(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions onlyRetrieveFromCache(boolean p1)
    {
        return this.onlyRetrieveFromCache(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalCenterCrop()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalCenterCrop());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalCenterCrop()
    {
        return this.optionalCenterCrop();
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalCenterInside()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalCenterInside());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalCenterInside()
    {
        return this.optionalCenterInside();
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalCircleCrop()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalCircleCrop());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalCircleCrop()
    {
        return this.optionalCircleCrop();
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalFitCenter()
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalFitCenter());
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalFitCenter()
    {
        return this.optionalFitCenter();
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalTransform(com.bumptech.glide.load.Transformation p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalTransform(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions optionalTransform(Class p1, com.bumptech.glide.load.Transformation p2)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.optionalTransform(p1, p2));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalTransform(com.bumptech.glide.load.Transformation p1)
    {
        return this.optionalTransform(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions optionalTransform(Class p1, com.bumptech.glide.load.Transformation p2)
    {
        return this.optionalTransform(p1, p2);
    }

    public com.bisimplex.firebooru.custom.GlideOptions override(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.override(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions override(int p1, int p2)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.override(p1, p2));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions override(int p1)
    {
        return this.override(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions override(int p1, int p2)
    {
        return this.override(p1, p2);
    }

    public com.bisimplex.firebooru.custom.GlideOptions placeholder(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.placeholder(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions placeholder(android.graphics.drawable.Drawable p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.placeholder(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions placeholder(int p1)
    {
        return this.placeholder(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions placeholder(android.graphics.drawable.Drawable p1)
    {
        return this.placeholder(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions priority(com.bumptech.glide.Priority p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.priority(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions priority(com.bumptech.glide.Priority p1)
    {
        return this.priority(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions set(com.bumptech.glide.load.Option p1, Object p2)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.set(p1, p2));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions set(com.bumptech.glide.load.Option p1, Object p2)
    {
        return this.set(p1, p2);
    }

    public com.bisimplex.firebooru.custom.GlideOptions signature(com.bumptech.glide.load.Key p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.signature(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions signature(com.bumptech.glide.load.Key p1)
    {
        return this.signature(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions sizeMultiplier(float p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.sizeMultiplier(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions sizeMultiplier(float p1)
    {
        return this.sizeMultiplier(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions skipMemoryCache(boolean p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.skipMemoryCache(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions skipMemoryCache(boolean p1)
    {
        return this.skipMemoryCache(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions theme(android.content.res.Resources$Theme p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.theme(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions theme(android.content.res.Resources$Theme p1)
    {
        return this.theme(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions timeout(int p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.timeout(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions timeout(int p1)
    {
        return this.timeout(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions transform(com.bumptech.glide.load.Transformation p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.transform(p1));
    }

    public com.bisimplex.firebooru.custom.GlideOptions transform(Class p1, com.bumptech.glide.load.Transformation p2)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.transform(p1, p2));
    }

    public final varargs com.bisimplex.firebooru.custom.GlideOptions transform(com.bumptech.glide.load.Transformation[] p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.transform(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions transform(com.bumptech.glide.load.Transformation p1)
    {
        return this.transform(p1);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions transform(Class p1, com.bumptech.glide.load.Transformation p2)
    {
        return this.transform(p1, p2);
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions transform(com.bumptech.glide.load.Transformation[] p1)
    {
        return this.transform(p1);
    }

    public final varargs com.bisimplex.firebooru.custom.GlideOptions transforms(com.bumptech.glide.load.Transformation[] p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.transforms(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions transforms(com.bumptech.glide.load.Transformation[] p1)
    {
        return this.transforms(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions useAnimationPool(boolean p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.useAnimationPool(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions useAnimationPool(boolean p1)
    {
        return this.useAnimationPool(p1);
    }

    public com.bisimplex.firebooru.custom.GlideOptions useUnlimitedSourceGeneratorsPool(boolean p1)
    {
        return ((com.bisimplex.firebooru.custom.GlideOptions) super.useUnlimitedSourceGeneratorsPool(p1));
    }

    public bridge synthetic com.bumptech.glide.request.BaseRequestOptions useUnlimitedSourceGeneratorsPool(boolean p1)
    {
        return this.useUnlimitedSourceGeneratorsPool(p1);
    }
}
