package com.bisimplex.firebooru.view;
public class HistoryTagMenuDialog extends androidx.fragment.app.DialogFragment {
    public static String FAVORITE_KEY = "FAVORITE_KEY";
    public static String POSITION_KEY = "POSITION_KEY";
    public static String TITLE_KEY = "TITLE_KEY";
    protected com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagMenuDialogListener mListener;

    static HistoryTagMenuDialog()
    {
        return;
    }

    public HistoryTagMenuDialog()
    {
        return;
    }

    protected int getPosition()
    {
        int v0_0 = this.getArguments();
        if (v0_0 == 0) {
            return -1;
        } else {
            return v0_0.getInt(com.bisimplex.firebooru.view.HistoryTagMenuDialog.POSITION_KEY, -1);
        }
    }

    protected String getTitle()
    {
        String v0_0 = this.getArguments();
        if (v0_0 == null) {
            return "";
        } else {
            return v0_0.getString(com.bisimplex.firebooru.view.HistoryTagMenuDialog.TITLE_KEY, "");
        }
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagMenuDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_0 = this.getTitle();
        int v0_1 = this.getArguments();
        com.google.android.material.dialog.MaterialAlertDialogBuilder v1_2 = 0;
        if (v0_1 != 0) {
            v1_2 = v0_1.getBoolean(com.bisimplex.firebooru.view.HistoryTagMenuDialog.FAVORITE_KEY, 0);
        }
        int v0_0;
        if (v1_2 == null) {
            v0_0 = 2130903050;
        } else {
            v0_0 = 2130903049;
        }
        com.google.android.material.dialog.MaterialAlertDialogBuilder v1_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v1_1.setTitle(v4_0).setItems(v0_0, new com.bisimplex.firebooru.view.HistoryTagMenuDialog$1(this));
        return v1_1.create();
    }
}
