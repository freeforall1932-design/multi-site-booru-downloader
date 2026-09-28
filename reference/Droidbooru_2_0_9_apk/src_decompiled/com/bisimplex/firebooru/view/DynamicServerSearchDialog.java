package com.bisimplex.firebooru.view;
public class DynamicServerSearchDialog extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    public static final String QUERY_JSON = "QUERY_JSON";
    public static final String TAG = "DynamicServerSearchDialog_TAG";
    com.bisimplex.firebooru.dataadapter.search.ServerSearchDialogAdapter adapter;
    private final com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener adapterListener;
    protected com.bisimplex.firebooru.view.DynamicServerSearchDialog$OnDynamicServerSearchDialogListener listener;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    com.bisimplex.firebooru.network.SourceQuery sourceQuery;

    static bridge synthetic void -$$Nest$mreset(com.bisimplex.firebooru.view.DynamicServerSearchDialog p0)
    {
        p0.reset();
        return;
    }

    public DynamicServerSearchDialog()
    {
        this.adapterListener = new com.bisimplex.firebooru.view.DynamicServerSearchDialog$1(this);
        return;
    }

    private void reset()
    {
        if (this.listener != null) {
            this.listener.onSearch(new com.bisimplex.firebooru.network.SourceQuery());
            return;
        } else {
            return;
        }
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

    public void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) p2.findViewById(2131362449));
        this.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext()));
        if (p3 == null) {
            com.bisimplex.firebooru.network.SourceQuery v2_1 = this.getArguments();
            if (v2_1 != null) {
                com.bisimplex.firebooru.network.SourceQuery v2_4 = ((com.bisimplex.firebooru.network.SourceQuery) new com.google.gson.Gson().fromJson(v2_1.getString("QUERY_JSON", ""), com.bisimplex.firebooru.network.SourceQuery));
                this.sourceQuery = v2_4;
                if (v2_4 == null) {
                    this.sourceQuery = new com.bisimplex.firebooru.network.SourceQuery();
                }
            }
        }
        com.bisimplex.firebooru.network.SourceQuery v2_9 = new com.bisimplex.firebooru.dataadapter.search.ServerSearchDialogAdapter(this.requireContext(), this.sourceQuery);
        this.adapter = v2_9;
        v2_9.setListener(this.adapterListener);
        this.recyclerView.setNestedScrollingEnabled(1);
        this.recyclerView.setAdapter(this.adapter);
        return;
    }

    public void search()
    {
        if (this.listener != null) {
            this.listener.onSearch(this.adapter.getResultQuery());
            return;
        } else {
            return;
        }
    }

    public void setListener(com.bisimplex.firebooru.view.DynamicServerSearchDialog$OnDynamicServerSearchDialogListener p1)
    {
        this.listener = p1;
        return;
    }
}
