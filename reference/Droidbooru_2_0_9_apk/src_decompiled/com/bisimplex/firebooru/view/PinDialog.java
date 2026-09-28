package com.bisimplex.firebooru.view;
public class PinDialog extends androidx.fragment.app.DialogFragment {
    public static final String SOURCE_ID = "SOURCE_ID";
    public static final String SOURCE_TYPE = "SOURCE_TYPE";
    public static final String TAG = "PinDialog_TAG";
    private com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener mListener;

    static bridge synthetic com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener -$$Nest$fgetmListener(com.bisimplex.firebooru.view.PinDialog p0)
    {
        return p0.mListener;
    }

    public PinDialog()
    {
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

    public android.app.Dialog onCreateDialog(android.os.Bundle p9)
    {
        int v0_1;
        androidx.appcompat.app.AlertDialog v9_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getGroupSourceSpecs();
        int v0_4 = new com.bisimplex.firebooru.model.SourceSpecs();
        v0_4.setQuery(new com.bisimplex.firebooru.network.SourceQuery("", this.getString(2131886662)));
        v9_1.add(0, v0_4);
        int v0_0 = this.getArguments();
        String v2_0 = 0;
        if (v0_0 == 0) {
            v0_1 = com.bisimplex.firebooru.network.SourceType.Post;
        } else {
            v2_0 = v0_0.getString("SOURCE_ID", 0);
            v0_1 = com.bisimplex.firebooru.network.SourceType.fromInteger(v0_0.getInt("SOURCE_TYPE", com.bisimplex.firebooru.network.SourceType.Post.getValue()));
        }
        int v3_6 = ((com.bisimplex.firebooru.network.SourcePostBasic) com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(v0_1, v2_0));
        com.google.android.material.dialog.MaterialAlertDialogBuilder v4_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v4_2.setTitle(2131887022);
        com.bisimplex.firebooru.view.PinDialog$3 v5_3 = new java.util.ArrayList();
        java.util.Iterator v6 = v9_1.iterator();
        while (v6.hasNext()) {
            v5_3.add(((com.bisimplex.firebooru.model.SourceSpecs) v6.next()).getTitle());
        }
        com.bisimplex.firebooru.view.PinDialog$1 v1_2 = new String[0];
        v4_2.setItems(((String[]) v5_3.toArray(v1_2)), new com.bisimplex.firebooru.view.PinDialog$3(this, v3_6, v9_1)).setNegativeButton(2131886205, new com.bisimplex.firebooru.view.PinDialog$2(this)).setNeutralButton(2131886979, new com.bisimplex.firebooru.view.PinDialog$1(this, v2_0, v0_1));
        return v4_2.create();
    }
}
