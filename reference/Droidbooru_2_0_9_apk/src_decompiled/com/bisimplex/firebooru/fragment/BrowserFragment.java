package com.bisimplex.firebooru.fragment;
public class BrowserFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    String initialUrl;
    private boolean loadingFinished;
    private boolean shouldResetStack;
    private android.webkit.WebView web;

    static bridge synthetic void -$$Nest$fputloadingFinished(com.bisimplex.firebooru.fragment.BrowserFragment p0, boolean p1)
    {
        p0.loadingFinished = p1;
        return;
    }

    public BrowserFragment()
    {
        this.loadingFinished = 1;
        this.shouldResetStack = 1;
        return;
    }

    public com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion()
    {
        return 0;
    }

    public boolean getShouldResetStack()
    {
        return this.shouldResetStack;
    }

    public String getiOsFragmentName()
    {
        return "WebControllerView";
    }

    public void goBack()
    {
        if (this.web.canGoBack()) {
            this.web.goBack();
        }
        return;
    }

    public void goNext()
    {
        if (this.web.canGoForward()) {
            this.web.goForward();
        }
        return;
    }

    public void loadUrl(String p2)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            android.webkit.WebView v0_1 = this.web;
            if (v0_1 != null) {
                v0_1.loadUrl(p2);
            }
        }
        return;
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        this.setHasOptionsMenu(1);
        return;
    }

    public void onCreateOptionsMenu(android.view.Menu p2, android.view.MenuInflater p3)
    {
        p3.inflate(2131689475, p2);
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p3, android.view.ViewGroup p4, android.os.Bundle p5)
    {
        android.view.View v3_1 = p3.inflate(2131558449, p4, 0);
        if (this.web == null) {
            String v4_9 = ((android.webkit.WebView) v3_1.findViewById(2131362713));
            this.web = v4_9;
            String v4_1 = v4_9.getSettings();
            v4_1.setJavaScriptEnabled(1);
            v4_1.setSupportZoom(1);
            v4_1.setLoadWithOverviewMode(1);
            v4_1.setUseWideViewPort(1);
            this.web.setWebViewClient(new com.bisimplex.firebooru.fragment.BrowserFragment$1(this));
            this.setTitle(2131886190);
        }
        if (p5 == null) {
            this.loadUrl(this.initialUrl);
            return v3_1;
        } else {
            this.web.restoreState(p5);
            return v3_1;
        }
    }

    public void onSaveInstanceState(android.os.Bundle p2)
    {
        super.onSaveInstanceState(p2);
        this.web.saveState(p2);
        return;
    }

    public void reload()
    {
        this.web.reload();
        return;
    }

    public void setShouldResetStack(boolean p1)
    {
        this.shouldResetStack = p1;
        return;
    }

    public void shareIt()
    {
        String v0_0 = this.web;
        if (v0_0 != null) {
            this.shareUrl(v0_0.getUrl());
        }
        return;
    }
}
