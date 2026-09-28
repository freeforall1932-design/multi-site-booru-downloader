package com.bisimplex.firebooru.network;
public class Utils {
    public static String LOG_TAG = "AnimeBoxes";
    private static final com.bisimplex.firebooru.network.Utils ourInstance;
    private com.bumptech.glide.request.RequestOptions imageOptions;
    private com.bumptech.glide.request.RequestOptions thumbsOptions;

    static Utils()
    {
        com.bisimplex.firebooru.network.Utils.ourInstance = new com.bisimplex.firebooru.network.Utils();
        return;
    }

    private Utils()
    {
        this.thumbsOptions = ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) new com.bumptech.glide.request.RequestOptions().placeholder(2131230881)).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA)).dontAnimate()).format(com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565)).disallowHardwareConfig());
        this.resetImageOptions();
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getQualityRender() != com.bisimplex.firebooru.danbooru.QualityRenderType.Normal) {
            this.imageOptions = ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) this.imageOptions.format(com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565)).disallowHardwareConfig());
        }
        return;
    }

    public static String generateEmptyKey()
    {
        return java.util.UUID.randomUUID().toString();
    }

    public static com.bisimplex.firebooru.network.Utils getInstance()
    {
        return com.bisimplex.firebooru.network.Utils.ourInstance;
    }

    public static String md5(String p5)
    {
        try {
            if (!android.text.TextUtils.isEmpty(p5)) {
                StringBuilder v1_5 = java.security.MessageDigest.getInstance("md5");
                v1_5.update(p5.getBytes());
                String v5_1 = v1_5.digest();
                StringBuilder v1_1 = new StringBuilder();
                int v2 = 0;
                while (v2 < v5_1.length) {
                    v1_1.append(String.format("%02X", new Object[] {Byte.valueOf(v5_1[v2])})));
                    v2++;
                }
                return v1_1.toString().toLowerCase();
            } else {
                return "";
            }
        } catch (String v5_4) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v5_4);
            return "";
        }
    }

    private String refererForPost(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if (p3 != null) {
            if (!android.text.TextUtils.isEmpty(p3.getPostUrl())) {
                String v3_1 = android.net.Uri.parse(p3.getPostUrl());
                return String.format(java.util.Locale.US, "%s://%s/", new Object[] {v3_1.getScheme(), v3_1.getHost()}));
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private String refererForPostURL(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            String v3_4 = android.net.Uri.parse(p3);
            return String.format(java.util.Locale.US, "%s://%s/", new Object[] {v3_4.getScheme(), v3_4.getHost()}));
        } else {
            return "";
        }
    }

    private void resetImageOptions()
    {
        this.imageOptions = ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) ((com.bumptech.glide.request.RequestOptions) new com.bumptech.glide.request.RequestOptions().placeholder(2131230881)).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA)).dontTransform()).downsample(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.CENTER_INSIDE)).override(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getDeviceMaxImageSize()));
        return;
    }

    public com.bumptech.glide.request.RequestOptions getImageOptions()
    {
        return this.imageOptions;
    }

    public com.bumptech.glide.request.RequestOptions getThumbsOptions()
    {
        return this.thumbsOptions;
    }

    public void logError(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            android.util.Log.e(com.bisimplex.firebooru.network.Utils.LOG_TAG, p2);
            return;
        } else {
            return;
        }
    }

    public void logException(Exception p2)
    {
        if (!(p2 instanceof com.bumptech.glide.load.engine.GlideException)) {
            android.util.Log.e(com.bisimplex.firebooru.network.Utils.LOG_TAG, p2.toString());
            return;
        } else {
            ((com.bumptech.glide.load.engine.GlideException) p2).logRootCauses(com.bisimplex.firebooru.network.Utils.LOG_TAG);
            return;
        }
    }

    public void logInfo(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            android.util.Log.i(com.bisimplex.firebooru.network.Utils.LOG_TAG, p2);
            return;
        } else {
            return;
        }
    }

    public com.bumptech.glide.load.model.GlideUrl urlForEntry(com.bisimplex.firebooru.danbooru.BooruProvider p5, com.bisimplex.firebooru.model.DownloadEntry p6)
    {
        if (p6 != null) {
            com.bumptech.glide.load.model.LazyHeaders v5_4;
            if (p5 != null) {
                v5_4 = p5.getUserAgent();
            } else {
                v5_4 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent();
            }
            String v1 = p6.getFile_url();
            if (!android.text.TextUtils.isEmpty(v1)) {
                String v6_1 = p6.getPost_url();
                if (android.text.TextUtils.isEmpty(v6_1)) {
                    v6_1 = v1;
                }
                return new com.bumptech.glide.load.model.GlideUrl(v1, new com.bumptech.glide.load.model.LazyHeaders$Builder().setHeader("Referer", this.refererForPostURL(v6_1)).setHeader("User-Agent", v5_4).setHeader("Connection", "keep-alive").build());
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public com.bumptech.glide.load.model.GlideUrl urlForPost(com.bisimplex.firebooru.danbooru.BooruProvider p5, com.bisimplex.firebooru.danbooru.DanbooruPost p6)
    {
        com.bumptech.glide.load.model.GlideUrl v0_0 = 0;
        if ((p6 != null) && (p6.getVisibleVersion() != null)) {
            com.bumptech.glide.load.model.LazyHeaders v5_1;
            if (p5 != null) {
                v5_1 = p5.getUserAgent();
            } else {
                v5_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent();
            }
            if (!android.text.TextUtils.isEmpty(p6.getVisibleVersion().getUrl())) {
                v0_0 = new com.bumptech.glide.load.model.GlideUrl(p6.getVisibleVersion().getUrl(), new com.bumptech.glide.load.model.LazyHeaders$Builder().setHeader("Referer", this.refererForPost(p6)).setHeader("User-Agent", v5_1).setHeader("Connection", "keep-alive").build());
            } else {
                return 0;
            }
        }
        return v0_0;
    }

    public com.bumptech.glide.load.model.GlideUrl urlForPostPreview(com.bisimplex.firebooru.danbooru.BooruProvider p5, com.bisimplex.firebooru.danbooru.DanbooruPost p6)
    {
        com.bumptech.glide.load.model.GlideUrl v0_0 = 0;
        if ((p6 != null) && (p6.getPreview() != null)) {
            com.bumptech.glide.load.model.LazyHeaders v5_1;
            if (p5 != null) {
                v5_1 = p5.getUserAgent();
            } else {
                v5_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent();
            }
            if (!android.text.TextUtils.isEmpty(p6.getPreview().getUrl())) {
                v0_0 = new com.bumptech.glide.load.model.GlideUrl(p6.getPreview().getUrl(), new com.bumptech.glide.load.model.LazyHeaders$Builder().setHeader("Referer", this.refererForPost(p6)).setHeader("User-Agent", v5_1).setHeader("Connection", "keep-alive").build());
            } else {
                return 0;
            }
        }
        return v0_0;
    }

    public com.bumptech.glide.load.model.GlideUrl urlForPreview(com.bisimplex.firebooru.danbooru.BooruProvider p4, String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            com.bumptech.glide.load.model.LazyHeaders v4_5;
            if (p4 != null) {
                v4_5 = p4.getUserAgent();
            } else {
                v4_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent();
            }
            return new com.bumptech.glide.load.model.GlideUrl(p5, new com.bumptech.glide.load.model.LazyHeaders$Builder().setHeader("User-Agent", v4_5).setHeader("Connection", "keep-alive").build());
        } else {
            return 0;
        }
    }
}
