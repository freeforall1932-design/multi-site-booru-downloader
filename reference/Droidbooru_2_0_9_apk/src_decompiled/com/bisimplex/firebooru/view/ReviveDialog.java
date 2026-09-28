package com.bisimplex.firebooru.view;
public class ReviveDialog extends androidx.appcompat.app.AppCompatDialogFragment {
    public static final String TARGET_URL = "TARGET_URL";
    private com.bisimplex.firebooru.view.ReviveDialog$ReviveDialogListener mListener;
    private final int style;
    private final int theme;

    public ReviveDialog()
    {
        this.style = 1;
        this.theme = 0;
        return;
    }

    private void closeDialog()
    {
        this.dismiss();
        return;
    }

    private void launchURL(String p2)
    {
        com.bisimplex.firebooru.view.ReviveDialog$ReviveDialogListener v0 = this.mListener;
        if (v0 != null) {
            v0.launchURL(p2);
        }
        return;
    }

    public void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.setStyle(1, 0);
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p3, android.view.ViewGroup p4, android.os.Bundle p5)
    {
        android.view.View v3_1 = p3.inflate(2131558670, p4, 0);
        android.webkit.WebView v4_4 = this.getArguments();
        String v5_1 = "";
        if (v4_4 != null) {
            v5_1 = v4_4.getString("TARGET_URL", "");
        }
        android.webkit.WebView v4_3 = ((android.webkit.WebView) v3_1.findViewById(2131362713));
        v4_3.getSettings().setJavaScriptEnabled(1);
        v4_3.loadUrl(v5_1);
        return v3_1;
    }

    public void onDismiss(android.content.DialogInterface p1)
    {
        super.onDismiss(p1);
        com.bisimplex.firebooru.view.ReviveDialog$ReviveDialogListener v1_1 = this.mListener;
        if (v1_1 != null) {
            v1_1.onReviveDialogClosed();
        }
        return;
    }

    public void onStart()
    {
        super.onStart();
        android.view.Window v0_0 = this.getDialog();
        if (v0_0 != null) {
            android.view.Window v0_1 = v0_0.getWindow();
            if (v0_1 != null) {
                v0_1.setLayout(-1, -1);
            }
        }
        return;
    }
}
