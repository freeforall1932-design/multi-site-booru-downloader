package com.bisimplex.firebooru.view;
public class DynamicSearchDialog extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    public static final String ALLOW_SERVER_LIST_MODIFICATION = "ALLOW_SERVER_LIST_MODIFICATION";
    public static final String MULTI_SERVER_SELECTED_IDS = "MULTI_SERVER_SELECTED_IDS";
    public static final String QUERY_JSON = "QUERY_JSON";
    public static final String TAG = "DynamicSearchDialog_TAG";
    com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter adapter;
    private final com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener adapterListener;
    protected com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener listener;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    com.bisimplex.firebooru.network.SourceQuery sourceQuery;

    public DynamicSearchDialog()
    {
        this.adapterListener = new com.bisimplex.firebooru.view.DynamicSearchDialog$1(this);
        return;
    }

    public void onAttach(android.content.Context p2)
    {
        super.onAttach(p2);
        if ((this.listener == null) && ((p2 instanceof com.bisimplex.firebooru.activity.MainActivity))) {
            this.listener = ((com.bisimplex.firebooru.activity.MainActivity) p2);
        }
        return;
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p3)
    {
        android.app.Dialog v3_1 = super.onCreateDialog(p3);
        if ((v3_1 instanceof com.google.android.material.bottomsheet.BottomSheetDialog)) {
            com.google.android.material.bottomsheet.BottomSheetBehavior v0_3 = ((com.google.android.material.bottomsheet.BottomSheetDialog) v3_1).getBehavior();
            v0_3.setSaveFlags(-1);
            v0_3.setState(3);
            v0_3.setHideable(0);
        }
        return v3_1;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558482, p3, 0);
    }

    public void onViewCreated(android.view.View p5, android.os.Bundle p6)
    {
        java.util.List v0_2;
        boolean v6_2;
        super.onViewCreated(p5, p6);
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) p5.findViewById(2131362449));
        this.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext()));
        if (p6) {
            v0_2 = 0;
            v6_2 = 1;
        } else {
            boolean v6_1 = this.getArguments();
            if (!v6_1) {
            } else {
                java.util.List v0_6 = ((com.bisimplex.firebooru.network.SourceQuery) new com.google.gson.Gson().fromJson(v6_1.getString("QUERY_JSON", ""), com.bisimplex.firebooru.network.SourceQuery));
                this.sourceQuery = v0_6;
                if (v0_6 == null) {
                    this.sourceQuery = new com.bisimplex.firebooru.network.SourceQuery();
                }
                v0_2 = v6_1.getIntegerArrayList("MULTI_SERVER_SELECTED_IDS");
                if (v0_2 == null) {
                    v0_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getMultiSearchSelectedServerIds();
                }
                v6_2 = v6_1.getBoolean("ALLOW_SERVER_LIST_MODIFICATION", 1);
            }
        }
        String v1_5 = new com.bisimplex.firebooru.dataadapter.search.SearchDialogAdapter(this.requireContext(), this.sourceQuery, v0_2, v6_2);
        this.adapter = v1_5;
        v1_5.setListener(this.adapterListener);
        this.recyclerView.setNestedScrollingEnabled(1);
        this.recyclerView.setAdapter(this.adapter);
        return;
    }

    public void search()
    {
        if (this.listener != null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_4 = this.adapter.getResultQuery();
            com.bisimplex.firebooru.danbooru.UserConfiguration v1_6 = this.adapter.fetchSelectedServers();
            if ((v1_6 != null) && (!v1_6.isEmpty())) {
                if (v1_6.size() != 1) {
                    this.listener.onSearchSources(v0_4, v1_6);
                    com.bisimplex.firebooru.danbooru.UserConfiguration v0_2 = new java.util.ArrayList();
                    com.bisimplex.firebooru.danbooru.UserConfiguration v1_0 = v1_6.iterator();
                    while (v1_0.hasNext()) {
                        Integer v2_5 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_0.next());
                        if (v2_5.getServerId() >= 0) {
                            v0_2.add(Integer.valueOf(v2_5.getServerId()));
                        }
                    }
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setMultiSearchSelectedServerIds(v0_2);
                } else {
                    Integer v2_9 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
                    com.bisimplex.firebooru.danbooru.UserConfiguration v1_3 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_6.get(0));
                    if ((v2_9 == null) || (v2_9.getServerId() == v1_3.getServerId())) {
                        this.listener.onSearch(v0_4, 0);
                    } else {
                        this.listener.onSearch(v0_4, v1_3);
                    }
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setMultiSearchSelectedServerIds(0);
                    return;
                }
            }
        }
        return;
    }

    public void setListener(com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener p1)
    {
        this.listener = p1;
        return;
    }
}
