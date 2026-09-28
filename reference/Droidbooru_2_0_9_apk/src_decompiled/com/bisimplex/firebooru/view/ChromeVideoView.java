package com.bisimplex.firebooru.view;
public class ChromeVideoView extends android.widget.FrameLayout implements com.bisimplex.firebooru.view.IVideoView {
    private androidx.webkit.WebViewAssetLoader assetLoader;
    private com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface videoInterface;
    private android.webkit.WebView webView;

    static bridge synthetic androidx.webkit.WebViewAssetLoader -$$Nest$fgetassetLoader(com.bisimplex.firebooru.view.ChromeVideoView p0)
    {
        return p0.assetLoader;
    }

    static bridge synthetic android.webkit.WebView -$$Nest$fgetwebView(com.bisimplex.firebooru.view.ChromeVideoView p0)
    {
        return p0.webView;
    }

    static bridge synthetic void -$$Nest$mrecoverFromVideoPlayerError(com.bisimplex.firebooru.view.ChromeVideoView p0, boolean p1)
    {
        p0.recoverFromVideoPlayerError(p1);
        return;
    }

    public ChromeVideoView(android.content.Context p1)
    {
        super(p1);
        super.initializePlayer(p1);
        return;
    }

    public ChromeVideoView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.initializePlayer(p1);
        return;
    }

    public ChromeVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.initializePlayer(p1);
        return;
    }

    public ChromeVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.initializePlayer(p1);
        return;
    }

    private void initializePlayer(android.content.Context p3)
    {
        if (p3 != null) {
            if (this.webView == null) {
                String v0_11 = new android.webkit.WebView(p3);
                this.webView = v0_11;
                com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v3_15 = v0_11.getSettings();
                v3_15.setJavaScriptEnabled(1);
                String v0_1 = (1 ^ com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo());
                v3_15.setAllowFileAccess(v0_1);
                v3_15.setAllowFileAccessFromFileURLs(v0_1);
                v3_15.setAllowContentAccess(v0_1);
                v3_15.setAllowUniversalAccessFromFileURLs(v0_1);
                v3_15.setMediaPlaybackRequiresUserGesture(0);
                this.webView.setWebChromeClient(new com.bisimplex.firebooru.view.ChromeVideoView$1(this));
                this.webView.setWebViewClient(new com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoWebClient(this, 0));
                this.webView.setVisibility(4);
                this.addView(this.webView, new android.widget.FrameLayout$LayoutParams(-1, -1));
            }
            if (this.videoInterface == null) {
                com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v3_8 = new com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface(this);
                this.videoInterface = v3_8;
                this.webView.addJavascriptInterface(v3_8, "Android");
                com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v3_10 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThemeSelected();
                if (v3_10 != com.bisimplex.firebooru.custom.ThemeType.Purple) {
                    if (v3_10 != com.bisimplex.firebooru.custom.ThemeType.Gray) {
                        if (v3_10 != com.bisimplex.firebooru.custom.ThemeType.Light) {
                            com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputbackgroundColor(this.videoInterface, "black");
                        } else {
                            com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputbackgroundColor(this.videoInterface, "white");
                            return;
                        }
                    } else {
                        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputbackgroundColor(this.videoInterface, "#222222");
                        return;
                    }
                } else {
                    com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputbackgroundColor(this.videoInterface, "#0A0414");
                    return;
                }
            }
        }
        return;
    }

    private void recoverFromVideoPlayerError(boolean p3)
    {
        String v0_0 = this.videoInterface;
        if ((v0_0 != null) && (com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fgetcurrentPlayerListener(v0_0) != null)) {
            if (p3 == null) {
                com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fgetcurrentPlayerListener(this.videoInterface).onError(this.getContext().getString(2131886508));
            } else {
                com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fgetcurrentPlayerListener(this.videoInterface).onError(this.getContext().getString(2131886507));
            }
        }
        this.removeAllViews();
        this.webView.destroy();
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputcurrentPlayerListener(this.videoInterface, 0);
        this.webView = 0;
        this.videoInterface = 0;
        this.initializePlayer(this.getContext());
        return;
    }

    public void addListener(com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener p2)
    {
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputcurrentPlayerListener(this.videoInterface, p2);
        return;
    }

    public void cleanup()
    {
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v0_0 = this.webView;
        if (v0_0 != null) {
            v0_0.setVisibility(4);
            this.webView.loadUrl("about:blank");
        }
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v0_2 = this.videoInterface;
        if (v0_2 != null) {
            v0_2.cleanUp();
        }
        return;
    }

    public android.widget.FrameLayout getAsLayout()
    {
        return this;
    }

    public long getCurrentPosition()
    {
        long v0_0 = this.videoInterface;
        if (v0_0 != 0) {
            return v0_0.getCurrentTime();
        } else {
            return 0;
        }
    }

    public long getDuration()
    {
        return this.videoInterface.getDuration();
    }

    public boolean hasControls()
    {
        return 1;
    }

    public boolean isPlaying()
    {
        return this.videoInterface.isPlaying();
    }

    public boolean isReleased()
    {
        if (this.webView != null) {
            return 0;
        } else {
            return 1;
        }
    }

    public void pause()
    {
        return;
    }

    public void release()
    {
        this.webView.loadUrl("about:blank");
        this.removeAllViews();
        this.webView.destroy();
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputcurrentPlayerListener(this.videoInterface, 0);
        this.webView = 0;
        this.videoInterface = 0;
        return;
    }

    public void removeListener()
    {
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface.-$$Nest$fputcurrentPlayerListener(this.videoInterface, 0);
        return;
    }

    public void reset()
    {
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v0_0 = this.webView;
        if (v0_0 != null) {
            v0_0.setVisibility(4);
            this.webView.loadUrl("about:blank");
        }
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface v0_2 = this.videoInterface;
        if (v0_2 != null) {
            v0_2.cleanUp();
        }
        return;
    }

    public void resume()
    {
        return;
    }

    public void seekTo(long p2)
    {
        if (this.webView != null) {
            this.post(new com.bisimplex.firebooru.view.ChromeVideoView$2(this, p2));
            return;
        } else {
            return;
        }
    }

    public void setFile(java.io.File p2, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p3)
    {
        if ((this.webView != null) && (p2 != null)) {
            this.videoInterface.setVideoUrl(android.net.Uri.fromFile(p2).toString(), p3);
            this.webView.loadUrl("file:///android_asset/video.html");
        }
        return;
    }

    public void setMuted(boolean p1)
    {
        return;
    }

    public void setOnLongClickListener(android.view.View$OnLongClickListener p3)
    {
        int v1;
        if (p3 == null) {
            v1 = 0;
        } else {
            v1 = 1;
        }
        this.webView.setLongClickable(v1);
        this.webView.setOnLongClickListener(p3);
        return;
    }

    public void setURL(String p4, String p5, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p6)
    {
        if ((this.webView != null) && (!android.text.TextUtils.isEmpty(p4))) {
            String v5_8 = android.net.Uri.parse(p4);
            this.assetLoader = new androidx.webkit.WebViewAssetLoader$Builder().setDomain(v5_8.getHost()).addPathHandler("/ab_assets/", new androidx.webkit.WebViewAssetLoader$AssetsPathHandler(this.getContext())).build();
            String v5_6 = v5_8.buildUpon().path("ab_assets").appendPath("video.html").clearQuery().build();
            this.videoInterface.setVideoUrl(p4, p6);
            this.webView.loadUrl(v5_6.toString());
        }
        return;
    }

    public void start()
    {
        return;
    }

    public void stop()
    {
        this.pause();
        return;
    }
}
