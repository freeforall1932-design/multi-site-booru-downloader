package com.bisimplex.firebooru.view;
public class SourceSpecsDialog extends androidx.fragment.app.DialogFragment {
    public static final String PARENT_GROUP_ID = "PARENT_GROUP_ID";
    com.bisimplex.firebooru.dataadapter.ServersDataAdapter adapter;
    android.widget.ArrayAdapter groupAdapter;
    android.widget.AutoCompleteTextView groupEditText;
    com.google.android.material.textfield.TextInputLayout groupInputLayout;
    private int groupSelectedPosition;
    private com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener mListener;
    android.widget.EditText queryEditText;
    android.widget.Spinner serverSpinner;
    java.util.List servers;
    android.widget.Spinner typeSpinner;

    static bridge synthetic int -$$Nest$fgetgroupSelectedPosition(com.bisimplex.firebooru.view.SourceSpecsDialog p0)
    {
        return p0.groupSelectedPosition;
    }

    static bridge synthetic com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.SourceSpecsDialog p0)
    {
        return p0.mListener;
    }

    static bridge synthetic void -$$Nest$fputgroupSelectedPosition(com.bisimplex.firebooru.view.SourceSpecsDialog p0, int p1)
    {
        p0.groupSelectedPosition = p1;
        return;
    }

    public SourceSpecsDialog()
    {
        this.groupSelectedPosition = -1;
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        if (p5 != null) {
            this.groupSelectedPosition = p5.getInt("groupSelectedPosition", -1);
        }
        java.util.List v5_1 = new com.bisimplex.firebooru.dataadapter.ServersDataAdapter(this.getActivity());
        this.adapter = v5_1;
        v5_1.setEnableDelete(0);
        java.util.List v5_3 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getGroupSourceSpecs();
        android.content.Context v1_1 = new com.bisimplex.firebooru.model.SourceSpecs();
        v1_1.setQuery(new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131886662)));
        v5_3.add(0, v1_1);
        this.groupAdapter = new android.widget.ArrayAdapter(this.requireContext(), 2131558641, v5_3);
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p10)
    {
        androidx.appcompat.app.AlertDialog v10_3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
        this.servers = v10_3;
        this.adapter.addAll(v10_3);
        androidx.appcompat.app.AlertDialog v10_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_2 = this.getActivity().getLayoutInflater();
        v10_1.setTitle(2131887022);
        int v2_1 = 0;
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3 = v0_2.inflate(2131558475, 0);
        this.serverSpinner = ((android.widget.Spinner) v0_3.findViewById(2131362520));
        this.typeSpinner = ((android.widget.Spinner) v0_3.findViewById(2131362674));
        this.queryEditText = ((android.widget.EditText) v0_3.findViewById(2131362442));
        this.groupInputLayout = ((com.google.android.material.textfield.TextInputLayout) v0_3.findViewById(2131362145));
        com.bisimplex.firebooru.view.SourceSpecsDialog$3 v1_19 = ((android.widget.AutoCompleteTextView) v0_3.findViewById(2131362144));
        this.groupEditText = v1_19;
        v1_19.setAdapter(this.groupAdapter);
        com.bisimplex.firebooru.view.SourceSpecsDialog$3 v1_21 = new android.widget.ArrayAdapter(this.getActivity(), 2131558650, 2131362196, this.servers);
        v1_21.setDropDownViewResource(2131558642);
        this.serverSpinner.setAdapter(v1_21);
        android.widget.ArrayAdapter v5_3 = new android.widget.ArrayAdapter(this.getActivity(), 2131558655, 2131362196, java.util.Arrays.asList(this.getResources().getStringArray(2130903060)));
        v5_3.setDropDownViewResource(2131558642);
        this.typeSpinner.setAdapter(v5_3);
        this.typeSpinner.setOnItemSelectedListener(new com.bisimplex.firebooru.view.SourceSpecsDialog$1(this, v1_21));
        this.groupEditText.setOnItemClickListener(new com.bisimplex.firebooru.view.SourceSpecsDialog$2(this));
        v10_1.setView(v0_3);
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_5 = this.getArguments();
        if (v0_5 != null) {
            v2_1 = v0_5.getString("PARENT_GROUP_ID", 0);
        }
        if (android.text.TextUtils.isEmpty(v2_1)) {
            this.groupInputLayout.setVisibility(0);
        } else {
            this.groupInputLayout.setVisibility(8);
        }
        v10_1.setPositiveButton(2131886133, new com.bisimplex.firebooru.view.SourceSpecsDialog$4(this, v2_1)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.SourceSpecsDialog$3(this));
        return v10_1.create();
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putInt("groupSelectedPosition", this.groupSelectedPosition);
        return;
    }
}
