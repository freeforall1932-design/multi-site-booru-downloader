package com.bisimplex.firebooru.view;
public class TagMenuDialog extends androidx.fragment.app.DialogFragment {
    public static String TITLE_KEY = "TITLE_KEY";
    protected com.bisimplex.firebooru.view.TagMenuDialog$TagMenuDialogListener mListener;

    static TagMenuDialog()
    {
        return;
    }

    public TagMenuDialog()
    {
        return;
    }

    protected String getTitle()
    {
        String v0_0 = this.getArguments();
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0.getString(com.bisimplex.firebooru.view.TagMenuDialog.TITLE_KEY, "");
        }
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.TagMenuDialog$TagMenuDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_0 = this.getTitle();
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_1.setTitle(v4_0).setItems(2130903061, new com.bisimplex.firebooru.view.TagMenuDialog$1(this));
        return v0_1.create();
    }
}
