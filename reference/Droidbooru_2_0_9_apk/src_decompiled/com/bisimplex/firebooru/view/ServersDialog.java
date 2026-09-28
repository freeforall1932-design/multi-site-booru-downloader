package com.bisimplex.firebooru.view;
public class ServersDialog extends androidx.fragment.app.DialogFragment {
    public static final String SERVER_FILTER_CHANGE_ON_SELECT = "SERVER_FILTER_CHANGE_ON_SELECT";
    public static final String SERVER_FILTER_TITLE = "SERVER_FILTER_TITLE";
    public static final String SERVER_FILTER_TYPE_ID = "SERVER_FILTER_TYPE_ID";
    public static final String SERVER_SELECTED_ID = "SERVER_SELECTED_ID";
    com.bisimplex.firebooru.dataadapter.ServersDataAdapter adapter;
    com.bisimplex.firebooru.view.ServersDialog$ServersDialogListener mListener;
    java.util.List servers;

    public ServersDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.ServersDialog$ServersDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        com.bisimplex.firebooru.dataadapter.ServersDataAdapter v2_2 = new com.bisimplex.firebooru.dataadapter.ServersDataAdapter(this.getActivity());
        this.adapter = v2_2;
        v2_2.setEnableDelete(0);
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p7)
    {
        android.widget.ListView v1_0;
        androidx.appcompat.app.AlertDialog v7_1;
        androidx.appcompat.app.AlertDialog v7_0 = this.getArguments();
        if (v7_0 == null) {
            this.servers = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
            v1_0 = "";
            v7_1 = 0;
        } else {
            this.adapter.setSelectedServerID(v7_0.getInt("SERVER_SELECTED_ID", -1));
            this.servers = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServersByType(com.bisimplex.firebooru.danbooru.ServerItemType.fromInteger(v7_0.getInt("SERVER_FILTER_TYPE_ID", com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone.getValue())));
            v1_0 = v7_0.getString("SERVER_FILTER_TITLE");
            v7_1 = v7_0.getBoolean("SERVER_FILTER_CHANGE_ON_SELECT", 1);
        }
        this.adapter.addAll(this.servers);
        com.google.android.material.dialog.MaterialAlertDialogBuilder v2_7 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        int v3_5 = this.getActivity().getLayoutInflater();
        v2_7.setTitle(v1_0);
        android.widget.ListView v1_10 = ((android.widget.ListView) v3_5.inflate(2131558473, 0));
        v1_10.setPadding(v1_10.getPaddingLeft(), 0, v1_10.getPaddingRight(), v1_10.getListPaddingBottom());
        v1_10.setAdapter(this.adapter);
        v1_10.setOnItemClickListener(new com.bisimplex.firebooru.view.ServersDialog$1(this, v7_1));
        v2_7.setView(v1_10);
        v2_7.setNegativeButton(2131886205, new com.bisimplex.firebooru.view.ServersDialog$2(this));
        return v2_7.create();
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
