package com.bisimplex.firebooru.custom;
public class MyGlideModule extends com.bumptech.glide.module.AppGlideModule {

    public MyGlideModule()
    {
        return;
    }

    public static void expect(String p0, com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener p1)
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.expect(p0, p1);
        return;
    }

    public static com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener find(String p0)
    {
        return com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.find(p0);
    }

    public static void forget(String p0)
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.forget(p0);
        return;
    }

    static synthetic okhttp3.Response lambda$registerComponents$0(okhttp3.Interceptor$Chain p4)
    {
        okhttp3.HttpUrl v0_0 = p4.request();
        okhttp3.Response v4_4 = p4.proceed(v0_0);
        return v4_4.newBuilder().body(new com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody(v0_0.url(), v4_4.body(), new com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener())).build();
    }

    public void applyOptions(android.content.Context p5, com.bumptech.glide.GlideBuilder p6)
    {
        p6.setDiskCache(new com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory(p5, "image_cache", ((long) 1048576000)));
        return;
    }

    public void registerComponents(android.content.Context p3, com.bumptech.glide.Glide p4, com.bumptech.glide.Registry p5)
    {
        super.registerComponents(p3, p4, p5);
        okhttp3.OkHttpClient v3_8 = new okhttp3.OkHttpClient$Builder().connectTimeout(5, java.util.concurrent.TimeUnit.MINUTES).writeTimeout(10, java.util.concurrent.TimeUnit.MINUTES).readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).callTimeout(10, java.util.concurrent.TimeUnit.MINUTES).addInterceptor(new com.bisimplex.firebooru.custom.LoggingInterceptor(0)).cookieJar(com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().cookieJar()).addNetworkInterceptor(new com.bisimplex.firebooru.custom.MyGlideModule$$ExternalSyntheticLambda0());
        if (!com.bisimplex.firebooru.network.HttpClient.proxySettingsAreValid()) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setProxyConfiguration(0);
        } else {
            com.bisimplex.firebooru.network.HttpClient.configureProxy(v3_8);
        }
        p5.replace(com.bumptech.glide.load.model.GlideUrl, java.io.InputStream, new com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader$Factory(com.bisimplex.firebooru.network.HttpClient.applyDNSSettings(v3_8.build())));
        return;
    }
}
