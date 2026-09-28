package com.bisimplex.firebooru.view;
public class SourceSpecsEditDialog extends androidx.fragment.app.DialogFragment {
    public static final String EDIT_ID = "EDIT_ID";
    public static final String PARENT_GROUP_ID = "PARENT_GROUP_ID";
    private com.bisimplex.firebooru.view.SourceSpecsEditDialog$SourceSpecsEditDialogListener mListener;
    android.widget.EditText queryEditText;

    static bridge synthetic com.bisimplex.firebooru.view.SourceSpecsEditDialog$SourceSpecsEditDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.SourceSpecsEditDialog p0)
    {
        return p0.mListener;
    }

    public SourceSpecsEditDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.SourceSpecsEditDialog$SourceSpecsEditDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p5)
    {
        com.bisimplex.firebooru.view.SourceSpecsEditDialog$1 v1_4;
        androidx.appcompat.app.AlertDialog v5_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        String v0_12 = this.getActivity().getLayoutInflater();
        v5_1.setTitle(2131886472);
        int v2_0 = 0;
        String v0_0 = v0_12.inflate(2131558476, 0);
        this.queryEditText = ((android.widget.EditText) v0_0.findViewById(2131362442));
        v5_1.setView(v0_0);
        String v0_2 = this.getArguments();
        if (v0_2 == null) {
            v1_4 = 0;
        } else {
            v1_4 = v0_2.getString("PARENT_GROUP_ID", 0);
            v2_0 = v0_2.getString("EDIT_ID", 0);
        }
        if (!android.text.TextUtils.isEmpty(v2_0)) {
            String v0_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().findSourceSpecsByID(v2_0, v1_4);
            if (v0_5 != null) {
                this.queryEditText.setText(v0_5.getQuery().getText());
            }
        }
        v5_1.setPositiveButton(2131887099, new com.bisimplex.firebooru.view.SourceSpecsEditDialog$2(this, v2_0, v1_4)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.SourceSpecsEditDialog$1(this));
        return v5_1.create();
    }
}
