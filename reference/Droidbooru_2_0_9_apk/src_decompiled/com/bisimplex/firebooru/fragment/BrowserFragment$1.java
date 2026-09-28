package com.bisimplex.firebooru.fragment;
 class BrowserFragment$1 extends android.webkit.WebViewClient {
    final synthetic com.bisimplex.firebooru.fragment.BrowserFragment this$0;

    BrowserFragment$1(com.bisimplex.firebooru.fragment.BrowserFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onPageFinished(android.webkit.WebView p2, String p3)
    {
        com.bisimplex.firebooru.fragment.BrowserFragment.-$$Nest$fputloadingFinished(this.this$0, 1);
        if ((this.this$0.getActivity() != null) && ((!this.this$0.isDetached()) && (this.this$0.isVisible()))) {
            this.this$0.setTitle(p2.getTitle());
        }
        return;
    }

    public void onPageStarted(android.webkit.WebView p1, String p2, android.graphics.Bitmap p3)
    {
        com.bisimplex.firebooru.fragment.BrowserFragment.-$$Nest$fputloadingFinished(this.this$0, 0);
        if ((this.this$0.getActivity() != null) && ((!this.this$0.isDetached()) && (this.this$0.isVisible()))) {
            com.bisimplex.firebooru.fragment.BrowserFragment v1_3 = this.this$0;
            v1_3.setTitle(v1_3.getString(2131887213));
        }
        return;
    }
}
