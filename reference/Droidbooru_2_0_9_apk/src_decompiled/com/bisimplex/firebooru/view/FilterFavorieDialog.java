package com.bisimplex.firebooru.view;
public class FilterFavorieDialog extends androidx.fragment.app.DialogFragment {
    public static final String SERVER_SELECTED_ID = "SERVER_SELECTED_ID";
    public static final String SORT_ID = "SORT_ID";
    com.bisimplex.firebooru.view.FilterFavorieDialog$FilterFavoriteDialogListener mListener;

    public FilterFavorieDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.FilterFavorieDialog$FilterFavoriteDialogListener) p3);
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

    public android.app.Dialog onCreateDialog(android.os.Bundle p13)
    {
        androidx.appcompat.app.AlertDialog v13_3;
        androidx.appcompat.app.AlertDialog v13_0 = this.getArguments();
        int v0_3 = new com.bisimplex.firebooru.danbooru.ServerItem();
        v0_3.setServerName(this.getString(2131886145));
        v0_3.setServerId(-1);
        String v1_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers();
        v1_1.add(v0_3);
        String[] v2_1 = new android.widget.ArrayAdapter(this.getActivity(), 2131558650, 2131362196, v1_1);
        android.view.View v3_2 = (v1_1.size() - 1);
        if (v13_0 == null) {
            v13_3 = 0;
        } else {
            int v0_4 = v13_0.getInt("SERVER_SELECTED_ID", v0_3.getServerId());
            int v6_1 = 0;
            while (v6_1 < v1_1.size()) {
                if (((com.bisimplex.firebooru.danbooru.ServerItem) v1_1.get(v6_1)).getServerId() != v0_4) {
                    v6_1++;
                } else {
                    v3_2 = v6_1;
                    break;
                }
            }
            v13_3 = v13_0.getInt("SORT_ID", com.bisimplex.firebooru.danbooru.FavoriteSortType.Date.getValue());
        }
        v2_1.setDropDownViewResource(2131558642);
        String v1_5 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        int v6_4 = this.getActivity().getLayoutInflater();
        v1_5.setTitle(2131886591);
        int v6_5 = v6_4.inflate(2131558470, 0);
        android.widget.Spinner v8_3 = ((android.widget.Spinner) v6_5.findViewById(2131362520));
        android.widget.Spinner v9_3 = ((android.widget.Spinner) v6_5.findViewById(2131362548));
        v8_3.setAdapter(v2_1);
        if (v3_2 > null) {
            v8_3.setSelection(v3_2, 0);
        }
        android.view.View v3_5 = new android.widget.ArrayAdapter(this.getActivity(), 2131558651, 2131362196, this.getResources().getStringArray(2130903059));
        v3_5.setDropDownViewResource(2131558642);
        v9_3.setAdapter(v3_5);
        if (v13_3 > null) {
            v9_3.setSelection(v13_3, 0);
        }
        v1_5.setView(v6_5);
        v1_5.setPositiveButton(2131886591, new com.bisimplex.firebooru.view.FilterFavorieDialog$1(this, v8_3, v9_3));
        v1_5.setNegativeButton(2131886205, new com.bisimplex.firebooru.view.FilterFavorieDialog$2(this));
        v1_5.setNeutralButton(2131887086, new com.bisimplex.firebooru.view.FilterFavorieDialog$3(this));
        return v1_5.create();
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
