package com.bisimplex.firebooru.view;
public class GroupSpecsDialog extends androidx.fragment.app.DialogFragment {
    public static final String SOURCE_SPECS_CHILD_ID = "SOURCE_SPECS_CHILD_ID";
    public static final String SOURCE_SPECS_PARENT_ID = "SOURCE_SPECS_PARENT_ID";
    android.widget.ArrayAdapter adapter;
    com.google.android.material.textfield.TextInputLayout inputLayout;
    private com.bisimplex.firebooru.view.GroupSpecsDialog$GroupSpecsDialogListener mListener;
    private int selectedGroupIndex;

    static bridge synthetic com.bisimplex.firebooru.view.GroupSpecsDialog$GroupSpecsDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.GroupSpecsDialog p0)
    {
        return p0.mListener;
    }

    static bridge synthetic int -$$Nest$fgetselectedGroupIndex(com.bisimplex.firebooru.view.GroupSpecsDialog p0)
    {
        return p0.selectedGroupIndex;
    }

    static bridge synthetic void -$$Nest$fputselectedGroupIndex(com.bisimplex.firebooru.view.GroupSpecsDialog p0, int p1)
    {
        p0.selectedGroupIndex = p1;
        return;
    }

    public GroupSpecsDialog()
    {
        this.selectedGroupIndex = -1;
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.GroupSpecsDialog$GroupSpecsDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p6)
    {
        androidx.appcompat.app.AlertDialog v6_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getGroupSourceSpecs();
        com.bisimplex.firebooru.view.GroupSpecsDialog$2 v0_0 = this.getArguments();
        if (v0_0 != null) {
            com.bisimplex.firebooru.model.SourceSpecs v2_0 = v0_0.getString("SOURCE_SPECS_PARENT_ID", 0);
            if (!android.text.TextUtils.isEmpty(v2_0)) {
                com.bisimplex.firebooru.model.SourceSpecs v2_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().findSourceSpecsByID(v2_0);
                if (v2_1 != null) {
                    v6_1.remove(v2_1);
                    com.bisimplex.firebooru.model.SourceSpecs v2_3 = new com.bisimplex.firebooru.model.SourceSpecs();
                    v2_3.setQuery(new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131886662)));
                    v6_1.add(0, v2_3);
                }
            }
        }
        this.adapter = new android.widget.ArrayAdapter(this.requireContext(), 2131558641, v6_1);
        com.bisimplex.firebooru.model.SourceSpecs v2_7 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        int v3_8 = this.getActivity().getLayoutInflater();
        v2_7.setTitle(2131886899);
        int v1_0 = v3_8.inflate(2131558472, 0);
        int v3_11 = ((com.google.android.material.textfield.TextInputLayout) v1_0.findViewById(2131362186));
        this.inputLayout = v3_11;
        int v3_13 = ((android.widget.AutoCompleteTextView) v3_11.getEditText());
        v3_13.setAdapter(this.adapter);
        v2_7.setView(v1_0);
        v3_13.setOnItemClickListener(new com.bisimplex.firebooru.view.GroupSpecsDialog$1(this));
        v2_7.setPositiveButton(2131886894, new com.bisimplex.firebooru.view.GroupSpecsDialog$3(this, v0_0, v6_1, v3_13)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.GroupSpecsDialog$2(this));
        return v2_7.create();
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putInt("selectedGroupIndex", this.selectedGroupIndex);
        return;
    }
}
