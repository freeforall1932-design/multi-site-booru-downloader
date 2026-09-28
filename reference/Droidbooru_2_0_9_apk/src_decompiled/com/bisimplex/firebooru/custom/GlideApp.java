package com.bisimplex.firebooru.custom;
public final class GlideApp {

    private GlideApp()
    {
        return;
    }

    public static void enableHardwareBitmaps()
    {
        com.bumptech.glide.Glide.enableHardwareBitmaps();
        return;
    }

    public static com.bumptech.glide.Glide get(android.content.Context p0)
    {
        return com.bumptech.glide.Glide.get(p0);
    }

    public static java.io.File getPhotoCacheDir(android.content.Context p0)
    {
        return com.bumptech.glide.Glide.getPhotoCacheDir(p0);
    }

    public static java.io.File getPhotoCacheDir(android.content.Context p0, String p1)
    {
        return com.bumptech.glide.Glide.getPhotoCacheDir(p0, p1);
    }

    public static void init(android.content.Context p0, com.bumptech.glide.GlideBuilder p1)
    {
        com.bumptech.glide.Glide.init(p0, p1);
        return;
    }

    public static void init(com.bumptech.glide.Glide p0)
    {
        com.bumptech.glide.Glide.init(p0);
        return;
    }

    public static void isInitialized()
    {
        com.bumptech.glide.Glide.isInitialized();
        return;
    }

    public static void tearDown()
    {
        com.bumptech.glide.Glide.tearDown();
        return;
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(android.app.Activity p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(android.app.Fragment p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(android.content.Context p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(android.view.View p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(androidx.fragment.app.Fragment p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }

    public static com.bisimplex.firebooru.custom.GlideRequests with(androidx.fragment.app.FragmentActivity p0)
    {
        return ((com.bisimplex.firebooru.custom.GlideRequests) com.bumptech.glide.Glide.with(p0));
    }
}
