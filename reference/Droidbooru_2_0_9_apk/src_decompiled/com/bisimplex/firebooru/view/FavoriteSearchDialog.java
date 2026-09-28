package com.bisimplex.firebooru.view;
public class FavoriteSearchDialog extends com.bisimplex.firebooru.view.SearchDialog {
    public static final String SORT_ID = "SORT_ID";
    private android.widget.Spinner sortSpinner;

    static bridge synthetic void -$$Nest$mreset(com.bisimplex.firebooru.view.FavoriteSearchDialog p0)
    {
        p0.reset();
        return;
    }

    public FavoriteSearchDialog()
    {
        return;
    }

    private void reset()
    {
        if (this.listener != null) {
            this.listener.onSearch(new com.bisimplex.firebooru.network.SourceQuery(), ((com.bisimplex.firebooru.danbooru.ServerItem) this.serverSpinner.getItemAtPosition((this.serverSpinner.getCount() - 1))));
        }
        this.dismiss();
        return;
    }

    protected int getDefaultServerID()
    {
        return -1;
    }

    protected java.util.List getServers(com.bisimplex.firebooru.danbooru.ServerItemType p4)
    {
        java.util.List v4_1 = super.getServers(p4);
        com.bisimplex.firebooru.danbooru.ServerItem v0_1 = new com.bisimplex.firebooru.danbooru.ServerItem();
        v0_1.setServerName(this.getString(2131886145));
        v0_1.setServerId(-1);
        v4_1.add(v0_1);
        return v4_1;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558493, p3, 0);
    }

    public void onViewCreated(android.view.View p6, android.os.Bundle p7)
    {
        com.bisimplex.firebooru.view.FavoriteSearchDialog$1 v7_2;
        super.onViewCreated(p6, p7);
        this.sortSpinner = ((android.widget.Spinner) p6.findViewById(2131362548));
        android.widget.Spinner v1_1 = new android.widget.ArrayAdapter(this.getActivity(), 2131558651, 2131362196, this.getResources().getStringArray(2130903059));
        v1_1.setDropDownViewResource(2131558642);
        this.sortSpinner.setAdapter(v1_1);
        if (p7 != null) {
            v7_2 = 0;
        } else {
            com.bisimplex.firebooru.view.FavoriteSearchDialog$1 v7_1 = this.getArguments();
            if (v7_1 == null) {
            } else {
                v7_2 = v7_1.getInt("SORT_ID", com.bisimplex.firebooru.danbooru.FavoriteSortType.Date.getValue());
            }
        }
        if (v7_2 > null) {
            this.sortSpinner.setSelection(v7_2, 0);
        }
        ((android.widget.Button) p6.findViewById(2131362460)).setOnClickListener(new com.bisimplex.firebooru.view.FavoriteSearchDialog$1(this));
        return;
    }

    protected void search()
    {
        if (this.listener != null) {
            com.bisimplex.firebooru.network.SourceQuery v0_2 = new com.bisimplex.firebooru.network.SourceQuery(this.autoCompleteTextView.getText().toString());
            com.bisimplex.firebooru.view.SearchDialog$OnSearchDialogListener v1_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
            int v2_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.serverSpinner.getSelectedItem());
            if ((v2_2 != 0) && (v2_2.getServerId() < 0)) {
                v2_2 = 0;
            }
            int v4_1 = v0_2.getExtraParams();
            String v5_2 = com.bisimplex.firebooru.danbooru.FavoriteSortType.fromInteger(this.sortSpinner.getSelectedItemPosition());
            if ((v2_2 != 0) && (!android.text.TextUtils.isEmpty(v2_2.getUrl()))) {
                v4_1.put("FILTER_SERVER_URL", v2_2.getUrl());
            }
            v4_1.put("FILTER_SORT_ID", String.valueOf(v5_2.getValue()));
            if ((v1_1 == null) || ((v2_2 == 0) || (v1_1.getServerId() == v2_2.getServerId()))) {
                this.listener.onSearch(v0_2, 0);
            } else {
                this.listener.onSearch(v0_2, v2_2);
            }
        }
        this.dismiss();
        return;
    }
}
