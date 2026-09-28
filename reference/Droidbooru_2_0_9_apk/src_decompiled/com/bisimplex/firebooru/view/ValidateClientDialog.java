package com.bisimplex.firebooru.view;
public class ValidateClientDialog extends androidx.fragment.app.DialogFragment {
    public static final String SERVER_URL = "SERVER_URL";
    com.google.android.material.textfield.TextInputLayout inputLayout;
    private com.bisimplex.firebooru.view.ValidateClientDialog$ValidateClientDialogListener mListener;
    android.webkit.WebView webView;

    static bridge synthetic com.bisimplex.firebooru.view.ValidateClientDialog$ValidateClientDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.ValidateClientDialog p0)
    {
        return p0.mListener;
    }

    static bridge synthetic void -$$Nest$mextractCookies(com.bisimplex.firebooru.view.ValidateClientDialog p0, String p1)
    {
        p0.extractCookies(p1);
        return;
    }

    public ValidateClientDialog()
    {
        return;
    }

    private void extractCookies(String p6)
    {
        okhttp3.HttpUrl v0 = okhttp3.HttpUrl.parse(p6);
        if (v0 != null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addServerWithCookies(p6);
            com.bisimplex.firebooru.network.SourceFactory v6_6 = android.webkit.CookieManager.getInstance().getCookie(p6);
            if (!android.text.TextUtils.isEmpty(v6_6)) {
                com.bisimplex.firebooru.network.SourceFactory v6_1 = v6_6.split(";");
                java.util.ArrayList v1_3 = new java.util.ArrayList();
                int v2 = v6_1.length;
                int v3 = 0;
                while (v3 < v2) {
                    okhttp3.Cookie v4_1 = okhttp3.Cookie.parse(v0, v6_1[v3]);
                    if (v4_1 != null) {
                        v1_3.add(v4_1);
                    }
                    v3++;
                }
                if (!v1_3.isEmpty()) {
                    com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().cookieJar().saveFromResponse(v0, v1_3);
                }
                com.bisimplex.firebooru.network.SourceFactory.getInstance().cookiesUpdatedFor(v0);
                return;
            }
        }
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.ValidateClientDialog$ValidateClientDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement ValidateClientDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p6)
    {
        androidx.appcompat.app.AlertDialog v6_0 = this.getArguments();
        com.bisimplex.firebooru.view.ValidateClientDialog$1 v0_1 = this.requireActivity();
        com.google.android.material.dialog.MaterialAlertDialogBuilder v1_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_1);
        com.bisimplex.firebooru.view.ValidateClientDialog$1 v0_5 = v0_1.getLayoutInflater();
        v1_1.setTitle(2131887244);
        String v3 = 0;
        com.bisimplex.firebooru.view.ValidateClientDialog$1 v0_0 = v0_5.inflate(2131558478, 0);
        int v2_3 = ((android.webkit.WebView) v0_0.findViewById(2131362713));
        this.webView = v2_3;
        v2_3.setWebViewClient(new android.webkit.WebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(1);
        if (v6_0 != null) {
            v3 = v6_0.getString("SERVER_URL", 0);
        }
        if (android.text.TextUtils.isEmpty(v3)) {
            v3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer().getUrl();
        }
        this.webView.loadUrl(v3);
        v1_1.setView(v0_0);
        v1_1.setPositiveButton(2131886239, new com.bisimplex.firebooru.view.ValidateClientDialog$2(this, v3)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.ValidateClientDialog$1(this));
        v1_1.setCancelable(0);
        return v1_1.create();
    }
}
