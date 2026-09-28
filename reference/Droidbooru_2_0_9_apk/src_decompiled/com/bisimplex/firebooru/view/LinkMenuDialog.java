package com.bisimplex.firebooru.view;
public class LinkMenuDialog extends androidx.fragment.app.DialogFragment {
    public static String URL_KEY = "URL_KEY";
    protected com.bisimplex.firebooru.view.LinkMenuDialog$LinkMenuDialogListener mListener;

    static bridge synthetic String -$$Nest$mgetURL(com.bisimplex.firebooru.view.LinkMenuDialog p0)
    {
        return p0.getURL();
    }

    static LinkMenuDialog()
    {
        return;
    }

    public LinkMenuDialog()
    {
        return;
    }

    private String getURL()
    {
        String v0_0 = this.getArguments();
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0.getString(com.bisimplex.firebooru.view.LinkMenuDialog.URL_KEY, "");
        }
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.LinkMenuDialog$LinkMenuDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v4_2.setTitle(2131886794).setItems(2130903052, new com.bisimplex.firebooru.view.LinkMenuDialog$1(this));
        return v4_2.create();
    }
}
