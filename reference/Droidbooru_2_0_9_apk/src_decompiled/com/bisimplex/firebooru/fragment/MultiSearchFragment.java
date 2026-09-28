package com.bisimplex.firebooru.fragment;
public class MultiSearchFragment extends com.bisimplex.firebooru.fragment.HomeFragment implements com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver {
    public static final String SPECS = "SPECS";
    public final com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener searchDialogListener;

    static bridge synthetic void -$$Nest$mdestroySources(com.bisimplex.firebooru.fragment.MultiSearchFragment p0)
    {
        p0.destroySources();
        return;
    }

    public MultiSearchFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.MultiSearchFragment$2(this);
        return;
    }

    private void destroySources()
    {
        if (this.adapter != null) {
            int v0_1 = 0;
            while (v0_1 < this.adapter.getItemCount()) {
                com.bisimplex.firebooru.model.SourceSpecs v1_4 = this.adapter.getItem(v0_1);
                com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(v1_4.getSource(), v1_4.getSpecs());
                v0_1++;
            }
        }
        return;
    }

    protected void bindDataAdapter(com.bisimplex.firebooru.dataadapter.HomeAdapter p5)
    {
        java.util.Iterator v0_0 = this.getArguments();
        p5.setAllowEdition(0);
        if (v0_0 != null) {
            java.util.Iterator v0_4 = v0_0.getString("SPECS", "");
            if (!android.text.TextUtils.isEmpty(v0_4)) {
                java.util.Iterator v0_2 = ((java.util.List) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_4, new com.bisimplex.firebooru.fragment.MultiSearchFragment$1(this).getType()));
                java.util.ArrayList v1_6 = new java.util.ArrayList();
                java.util.Iterator v0_3 = v0_2.iterator();
                while (v0_3.hasNext()) {
                    v1_6.add(new com.bisimplex.firebooru.data.HomeItem(((com.bisimplex.firebooru.model.SourceSpecs) v0_3.next())));
                }
                p5.addItems(v1_6);
            }
        }
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    protected int getLayoutID()
    {
        return 2131558439;
    }

    public com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener getSearchDialogListener()
    {
        return this.searchDialogListener;
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    public boolean onBackPressed()
    {
        this.destroySources();
        return super.onBackPressed();
    }

    public void onCreateOptionsMenu(android.view.Menu p2, android.view.MenuInflater p3)
    {
        super.onCreateOptionsMenu(p2, p3);
        p2.findItem(2131362157).setVisible(0);
        p2.findItem(2131362519).setVisible(0);
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.setTitle(2131887111);
        return;
    }

    public void showSearchScreen()
    {
        com.bisimplex.firebooru.view.DynamicSearchDialog v0_1 = new com.bisimplex.firebooru.view.DynamicSearchDialog();
        v0_1.setListener(this.searchDialogListener);
        androidx.fragment.app.FragmentManager v1_3 = new android.os.Bundle(1);
        if (this.adapter != null) {
            String v2_0 = new java.util.ArrayList();
            String v3_0 = 0;
            String v4_0 = 0;
            while (v4_0 < this.adapter.getItemCount()) {
                com.bisimplex.firebooru.network.SourceQuery v5_3 = this.adapter.getItem(v4_0);
                v2_0.add(Integer.valueOf(v5_3.getSpecs().getServer().getServerId()));
                if (v3_0 == null) {
                    v3_0 = new com.bisimplex.firebooru.network.SourceQuery(v5_3.getSource().getQuery());
                }
                v4_0++;
            }
            if (v2_0.size() > 0) {
                v1_3.putIntegerArrayList("MULTI_SERVER_SELECTED_IDS", v2_0);
            }
            if (v3_0 != null) {
                v1_3.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v3_0));
            }
        }
        v0_1.setArguments(v1_3);
        v0_1.show(this.getParentFragmentManager(), "DynamicSearchDialog_TAG");
        return;
    }
}
