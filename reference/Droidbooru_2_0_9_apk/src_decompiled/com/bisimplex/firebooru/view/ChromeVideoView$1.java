package com.bisimplex.firebooru.view;
 class ChromeVideoView$1 extends android.webkit.WebChromeClient {
    final synthetic com.bisimplex.firebooru.view.ChromeVideoView this$0;

    ChromeVideoView$1(com.bisimplex.firebooru.view.ChromeVideoView p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onConsoleMessage(android.webkit.ConsoleMessage p2)
    {
        android.util.Log.e("ChromeVideo", p2.message());
        return 0;
    }
}
