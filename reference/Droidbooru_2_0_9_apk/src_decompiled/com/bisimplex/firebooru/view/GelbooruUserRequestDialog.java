package com.bisimplex.firebooru.view;
public class GelbooruUserRequestDialog extends androidx.fragment.app.DialogFragment {
    com.bisimplex.firebooru.view.GelbooruUserRequestDialog$GelbooruUserRequestDialogListener mListener;
    private android.widget.EditText passwordEditText;
    private android.widget.EditText userNameEditText;

    static bridge synthetic android.widget.EditText -$$Nest$fgetpasswordEditText(com.bisimplex.firebooru.view.GelbooruUserRequestDialog p0)
    {
        return p0.passwordEditText;
    }

    static bridge synthetic android.widget.EditText -$$Nest$fgetuserNameEditText(com.bisimplex.firebooru.view.GelbooruUserRequestDialog p0)
    {
        return p0.userNameEditText;
    }

    public GelbooruUserRequestDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.GelbooruUserRequestDialog$GelbooruUserRequestDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p5)
    {
        androidx.appcompat.app.AlertDialog v5_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        com.bisimplex.firebooru.view.GelbooruUserRequestDialog$2 v0_7 = this.getActivity().getLayoutInflater();
        v5_1.setTitle(2131886288);
        com.bisimplex.firebooru.view.GelbooruUserRequestDialog$2 v0_0 = v0_7.inflate(2131558666, 0);
        this.passwordEditText = ((android.widget.EditText) v0_0.findViewById(2131362412));
        this.userNameEditText = ((android.widget.EditText) v0_0.findViewById(2131362688));
        int v1_8 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
        if (v1_8 != 0) {
            this.userNameEditText.setText(v1_8.getUserName());
            this.passwordEditText.setText(v1_8.getPassword());
        }
        v5_1.setView(v0_0);
        v5_1.setPositiveButton(2131886997, new com.bisimplex.firebooru.view.GelbooruUserRequestDialog$1(this));
        v5_1.setNegativeButton(2131886205, new com.bisimplex.firebooru.view.GelbooruUserRequestDialog$2(this));
        return v5_1.create();
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
