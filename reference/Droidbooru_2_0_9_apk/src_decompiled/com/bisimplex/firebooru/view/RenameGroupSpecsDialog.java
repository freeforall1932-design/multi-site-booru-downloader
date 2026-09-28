package com.bisimplex.firebooru.view;
public class RenameGroupSpecsDialog extends androidx.fragment.app.DialogFragment {
    public static final String SOURCE_SPECS_ID = "SOURCE_SPECS_ID";
    com.google.android.material.textfield.TextInputLayout inputLayout;
    private com.bisimplex.firebooru.view.RenameGroupSpecsDialog$RenameGroupSpecsDialogListener mListener;

    static bridge synthetic com.bisimplex.firebooru.view.RenameGroupSpecsDialog$RenameGroupSpecsDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.RenameGroupSpecsDialog p0)
    {
        return p0.mListener;
    }

    public RenameGroupSpecsDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.RenameGroupSpecsDialog$RenameGroupSpecsDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p5)
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getGroupSourceSpecs();
        androidx.appcompat.app.AlertDialog v5_4 = this.getArguments();
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        com.bisimplex.firebooru.view.RenameGroupSpecsDialog$1 v1_1 = this.getActivity().getLayoutInflater();
        v0_1.setTitle(2131887080);
        com.bisimplex.firebooru.view.RenameGroupSpecsDialog$1 v1_2 = v1_1.inflate(2131558471, 0);
        int v2_4 = ((com.google.android.material.textfield.TextInputLayout) v1_2.findViewById(2131362186));
        this.inputLayout = v2_4;
        int v2_5 = v2_4.getEditText();
        v0_1.setView(v1_2);
        v0_1.setPositiveButton(2131887079, new com.bisimplex.firebooru.view.RenameGroupSpecsDialog$2(this, v5_4, v2_5)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.RenameGroupSpecsDialog$1(this));
        return v0_1.create();
    }
}
