package com.bisimplex.firebooru.view;
public class CustomDialog extends androidx.fragment.app.DialogFragment {
    public static String MESSAGE_KEY = "MESSAGE_KEY";
    public static String TYPE_KEY = "TYPE_KEY";
    private String message;
    private com.bisimplex.firebooru.activity.MessageType type;

    static CustomDialog()
    {
        return;
    }

    public CustomDialog()
    {
        this.type = com.bisimplex.firebooru.activity.MessageType.None;
        return;
    }

    public String getMessage()
    {
        return this.message;
    }

    public com.bisimplex.firebooru.activity.MessageType getType()
    {
        return this.type;
    }

    public com.mikepenz.iconics.IconicsDrawable iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon p3, int p4)
    {
        com.mikepenz.iconics.IconicsDrawable v0_1 = new com.mikepenz.iconics.IconicsDrawable(this.getContext(), p3);
        v0_1.setColorList(android.content.res.ColorStateList.valueOf(androidx.core.content.ContextCompat.getColor(this.getContext(), p4)));
        int v3_2 = this.getResources().getDimensionPixelSize(2131165379);
        v0_1.setSizeXPx(v3_2);
        v0_1.setSizeYPx(v3_2);
        return v0_1;
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p6)
    {
        androidx.appcompat.app.AlertDialog v6_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        this.getActivity().getLayoutInflater();
        com.bisimplex.firebooru.view.CustomDialog$1 v0_10 = this.getArguments();
        com.mikepenz.iconics.IconicsDrawable v1_3 = 0;
        if (v0_10 != null) {
            this.message = v0_10.getString(com.bisimplex.firebooru.view.CustomDialog.MESSAGE_KEY);
            this.type = com.bisimplex.firebooru.activity.MessageType.intToType(v0_10.getInt(com.bisimplex.firebooru.view.CustomDialog.TYPE_KEY, 0));
        }
        com.bisimplex.firebooru.view.CustomDialog$1 v0_3 = this.message;
        if (v0_3 != null) {
            v6_1 = v6_1.setMessage(v0_3);
        }
        if ((this.type != com.bisimplex.firebooru.activity.MessageType.Error) && (this.type != com.bisimplex.firebooru.activity.MessageType.Info)) {
            com.bisimplex.firebooru.view.CustomDialog$1 v0_6 = 0;
        } else {
            v0_6 = 1;
        }
        int v2_10;
        com.mikepenz.iconics.IconicsDrawable v1_1;
        int v2_7;
        if (this.type != com.bisimplex.firebooru.activity.MessageType.Error) {
            if (this.type != com.bisimplex.firebooru.activity.MessageType.Info) {
                v2_7 = 0;
            } else {
                v1_1 = this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_info, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes());
                v2_10 = 2131887212;
                v2_7 = v1_1;
                v1_3 = v2_10;
            }
        } else {
            v1_1 = this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_times, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes());
            v2_10 = 2131887211;
        }
        if (v0_6 != null) {
            v6_1 = v6_1.setPositiveButton(2131886997, new com.bisimplex.firebooru.view.CustomDialog$1(this));
        }
        if (v1_3 > null) {
            v6_1 = v6_1.setTitle(v1_3);
        }
        if (v2_7 != 0) {
            v6_1 = v6_1.setIcon(v2_7);
        }
        return v6_1.create();
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
