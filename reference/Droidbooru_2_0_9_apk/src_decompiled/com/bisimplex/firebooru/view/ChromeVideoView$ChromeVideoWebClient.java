package com.bisimplex.firebooru.view;
 class ChromeVideoView$ChromeVideoWebClient extends androidx.webkit.WebViewClientCompat {
    final synthetic com.bisimplex.firebooru.view.ChromeVideoView this$0;

    private ChromeVideoView$ChromeVideoWebClient(com.bisimplex.firebooru.view.ChromeVideoView p1)
    {
        this.this$0 = p1;
        return;
    }

    synthetic ChromeVideoView$ChromeVideoWebClient(com.bisimplex.firebooru.view.ChromeVideoView p1, com.bisimplex.firebooru.view.ChromeVideoView-IA p2)
    {
        this(p1);
        return;
    }

    public void onPageFinished(android.webkit.WebView p2, String p3)
    {
        super.onPageFinished(p2, p3);
        if (!p3.equalsIgnoreCase("about:blank")) {
            p2.setVisibility(0);
            return;
        } else {
            p2.setVisibility(4);
            return;
        }
    }

    public boolean onRenderProcessGone(android.webkit.WebView p1, android.webkit.RenderProcessGoneDetail p2)
    {
        com.bisimplex.firebooru.view.ChromeVideoView.-$$Nest$mrecoverFromVideoPlayerError(this.this$0, p2.didCrash());
        return 1;
    }

    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView p1, android.webkit.WebResourceRequest p2)
    {
        if (com.bisimplex.firebooru.view.ChromeVideoView.-$$Nest$fgetassetLoader(this.this$0) == null) {
            return 0;
        } else {
            return com.bisimplex.firebooru.view.ChromeVideoView.-$$Nest$fgetassetLoader(this.this$0).shouldInterceptRequest(p2.getUrl());
        }
    }
}
