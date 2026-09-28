package com.bisimplex.firebooru.view;
public class FlagDialog extends androidx.fragment.app.DialogFragment {
    private static String POSTURL_KEY = "POSTURL_KEY";

    static FlagDialog()
    {
        return;
    }

    public FlagDialog()
    {
        return;
    }

    public static com.bisimplex.firebooru.view.FlagDialog newInstance(String p3)
    {
        com.bisimplex.firebooru.view.FlagDialog v0_1 = new com.bisimplex.firebooru.view.FlagDialog();
        android.os.Bundle v1_1 = new android.os.Bundle();
        v1_1.putString(com.bisimplex.firebooru.view.FlagDialog.POSTURL_KEY, p3);
        v0_1.setArguments(v1_1);
        return v0_1;
    }

    public void flagItem()
    {
        this.getArguments().getString(com.bisimplex.firebooru.view.FlagDialog.POSTURL_KEY);
        this.dismiss();
        com.bisimplex.firebooru.activity.MenuBaseActivity v0_2 = ((com.bisimplex.firebooru.activity.MenuBaseActivity) this.getActivity());
        if (v0_2 != null) {
            v0_2.ShowMessage(2131887204, com.bisimplex.firebooru.activity.MessageType.Success);
        }
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v4_3.setMessage(2131886596);
        v4_3.setTitle(2131886597);
        androidx.appcompat.app.AlertDialog v4_2 = v4_3.setPositiveButton(2131886997, new com.bisimplex.firebooru.view.FlagDialog$1(this)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.FlagDialog$2(this));
        com.mikepenz.iconics.IconicsDrawable v0_4 = new com.mikepenz.iconics.IconicsDrawable(this.getContext(), com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_flag);
        int v1_4 = this.getResources().getDimensionPixelSize(2131165379);
        v0_4.setSizeXPx(v1_4);
        v0_4.setSizeYPx(v1_4);
        return v4_2.setIcon(v0_4).create();
    }

    public void onDestroyView()
    {
        android.app.Dialog v0 = this.getDialog();
        if ((v0 != null) && (this.getRetainInstance())) {
            v0.setDismissMessage(0);
        }
        super.onDestroyView();
        return;
    }
}
