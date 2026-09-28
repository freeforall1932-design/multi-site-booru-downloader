package com.bisimplex.firebooru.fragment;
public class PoolsFragment extends com.bisimplex.firebooru.fragment.ServerChangerFragment implements com.bisimplex.firebooru.network.SourceListener, com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener {
    public static final String SOURCE_KEY = "SOURCE_KEY";
    private com.bisimplex.firebooru.network.SourcePool _source;
    private String _sourceKey;
    private com.bisimplex.firebooru.dataadapter.PoolDataAdapter dataAdapter;
    private android.widget.EditText editText;
    private androidx.recyclerview.widget.RecyclerView listView;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    private android.widget.TextView noResultTextView;
    private android.os.Parcelable recyclerViewState;
    public com.bisimplex.firebooru.view.DynamicPoolSearchDialog$OnDynamicPoolSearchDialogListener searchDialogListener;
    private android.view.MenuItem searchMenuItem;
    private android.widget.TextView subtitleTextView;
    private android.widget.TextView titleTextView;

    static bridge synthetic com.bisimplex.firebooru.network.SourcePool -$$Nest$mgetSource(com.bisimplex.firebooru.fragment.PoolsFragment p0)
    {
        return p0.getSource();
    }

    static bridge synthetic void -$$Nest$msearchQuery(com.bisimplex.firebooru.fragment.PoolsFragment p0, com.bisimplex.firebooru.network.SourceQuery p1)
    {
        p0.searchQuery(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mshowSearchField(com.bisimplex.firebooru.fragment.PoolsFragment p0)
    {
        p0.showSearchField();
        return;
    }

    public PoolsFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.PoolsFragment$2(this);
        return;
    }

    private androidx.recyclerview.widget.RecyclerView$LayoutManager generateLayoutManager()
    {
        androidx.recyclerview.widget.LinearLayoutManager v0_1 = new androidx.recyclerview.widget.LinearLayoutManager(this.getContext(), 1, 0);
        this.mLayoutManager = v0_1;
        return v0_1;
    }

    private com.bisimplex.firebooru.network.SourcePool getSource()
    {
        if (this._source == null) {
            this._source = ((com.bisimplex.firebooru.network.SourcePool) com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(com.bisimplex.firebooru.network.SourceType.Pool, this.getSourceKey()));
        }
        return this._source;
    }

    private String getSourceKey()
    {
        if (android.text.TextUtils.isEmpty(this._sourceKey)) {
            String v0_7 = this.getArguments();
            if ((v0_7 == null) || (!v0_7.containsKey("SOURCE_KEY"))) {
                this._sourceKey = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Pool).getKey();
            } else {
                this._sourceKey = v0_7.getString("SOURCE_KEY");
            }
        }
        return this._sourceKey;
    }

    private void loadData()
    {
        com.bisimplex.firebooru.network.SourcePool v0_0 = this.getSource();
        if ((v0_0 != null) && ((!v0_0.isLastPage()) && (!v0_0.getIsLoading()))) {
            this.ShowLoading();
            this.getSource().loadAnotherPage(this);
        }
        return;
    }

    private void searchQuery(com.bisimplex.firebooru.network.SourceQuery p3)
    {
        this.dataAdapter.clearItems();
        com.bisimplex.firebooru.network.SourcePool v0_1 = this.getSource();
        v0_1.setQuery(p3);
        this.loadData();
        v0_1.setPageOffset(0);
        this.setTitle(p3.getText());
        return;
    }

    private void shareThis()
    {
        String v0_1 = this.getSource().getProvider().generateSearchUrl(this.getSource().getQuery().getText());
        if (v0_1 != null) {
            this.shareUrl(v0_1.toString());
        }
        return;
    }

    private void showSearchField()
    {
        androidx.fragment.app.FragmentManager v0_0 = this.getSource();
        if (v0_0 != null) {
            com.bisimplex.firebooru.view.DynamicPoolSearchDialog v1_1 = new com.bisimplex.firebooru.view.DynamicPoolSearchDialog();
            v1_1.setListener(this.searchDialogListener);
            androidx.fragment.app.FragmentManager v0_3 = v0_0.getQuery();
            String v2_0 = new android.os.Bundle(3);
            if (v0_3 != null) {
                v2_0.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v0_3));
            }
            v1_1.setArguments(v2_0);
            v1_1.show(this.getParentFragmentManager(), "DynamicPoolSearchDialog_TAG");
            return;
        } else {
            return;
        }
    }

    void askShowServers()
    {
        this.showServers(this.getSource().getProvider().getServerDescription(), com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru, this.getString(2131887148));
        return;
    }

    public void attachedToWindow(int p8)
    {
        int v0_0 = this.getSource();
        if ((v0_0 != 0) && ((!v0_0.isNewSearch()) && (!v0_0.getQuery().isDisableAutoLoad()))) {
            double v1_0 = v0_0.getIsLoading();
            boolean v2 = v0_0.isLastPage();
            if ((v1_0 == 0) && ((!v2) && (((double) p8) >= (((double) ((float) v0_0.getItemCount())) * 4604480259023595110)))) {
                this.loadData();
            }
        }
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        if ((this.getActivity() != null) && (!this.isDetached())) {
            this.HideLoading();
            this.showMessage(2131886490, com.bisimplex.firebooru.activity.MessageType.Error);
        }
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.listView;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public String getiOsFragmentName()
    {
        return "PoolsViewController";
    }

    public void itemClick(android.view.View p5, int p6)
    {
        com.bisimplex.firebooru.activity.MainActivity v5_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v5_1 != null) {
            String v6_6 = this.dataAdapter.getItem(p6);
            if (v6_6.getType() != 1) {
                String v6_1 = v6_6.getPool();
                if (v6_1 != null) {
                    String v6_4 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post, this.getSource().getProvider(), new com.bisimplex.firebooru.network.SourceQuery(this.getSource().getProvider().generatePoolQuery(v6_1.getPool_id()), v6_1.getName()));
                    com.bisimplex.firebooru.fragment.PostListFragment v0_3 = new com.bisimplex.firebooru.fragment.PostListFragment();
                    android.os.Bundle v2_5 = new android.os.Bundle(1);
                    v2_5.putString("SOURCE_KEY", v6_4.getKey());
                    v0_3.setArguments(v2_5);
                    v5_1.switchContent(v0_3);
                    return;
                }
            } else {
                this.askShowServers();
                return;
            }
        }
        return;
    }

    public void itemSecondaryClick(android.view.View p4, int p5)
    {
        com.bisimplex.firebooru.fragment.PoolsFragment$3 v0_0 = this.getContext();
        com.bisimplex.firebooru.model.Pool v1_1 = this.getSource();
        if ((v0_0 != null) && (v1_1 != null)) {
            com.bisimplex.firebooru.model.Pool v1_0 = this.dataAdapter.getItem(p5).getPool();
            if (v1_0 != null) {
                com.google.android.material.dialog.MaterialAlertDialogBuilder v2_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_0);
                v2_1.setTitle(v1_0.getName());
                if (!android.text.TextUtils.isEmpty(v1_0.getDescription())) {
                    v2_1.setMessage(v1_0.getDescription());
                } else {
                    v2_1.setMessage(2131886985);
                }
                v2_1.setPositiveButton(2131887256, new com.bisimplex.firebooru.fragment.PoolsFragment$3(this, p4, p5));
                v2_1.setNegativeButton(2131886239, new com.bisimplex.firebooru.fragment.PoolsFragment$4(this));
                v2_1.show();
            }
        }
        return;
    }

    public boolean onBackPressed()
    {
        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.getSource());
        return super.onBackPressed();
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        if (p4 != null) {
            this._sourceKey = p4.getString("SOURCE_KEY");
        }
        this.getSource().setListener(this);
        android.view.View v2_1 = p2.inflate(2131558440, p3, 0);
        this.titleTextView = ((android.widget.TextView) v2_1.findViewById(2131362651));
        this.subtitleTextView = ((android.widget.TextView) v2_1.findViewById(2131362584));
        this.listView = ((androidx.recyclerview.widget.RecyclerView) v2_1.findViewById(2131362211));
        android.widget.TextView v3_11 = new com.bisimplex.firebooru.dataadapter.PoolDataAdapter(this.getActivity(), this);
        this.dataAdapter = v3_11;
        this.listView.setAdapter(v3_11);
        this.listView.setLayoutManager(this.generateLayoutManager());
        this.noResultTextView = ((android.widget.TextView) v2_1.findViewById(2131362365));
        return v2_1;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putString("SOURCE_KEY", this.getSourceKey());
        android.os.Parcelable v0_1 = this.mLayoutManager;
        if (v0_1 != null) {
            android.os.Parcelable v0_2 = v0_1.onSaveInstanceState();
            this.recyclerViewState = v0_2;
            p3.putParcelable("recyclerViewState", v0_2);
        }
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        com.bisimplex.firebooru.network.SourceQuery v1_5 = this.getVisibleBar();
        v1_5.getMenu().clear();
        v1_5.inflateMenu(2131689491);
        v1_5.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.PoolsFragment$1(this));
        this.configureBar(v1_5);
        this.setSourceName(this.getSource().getProvider().getServerDescription().getServerName());
        if ((!this.getSource().getIsLoading()) && (!this.getSource().isNewSearch())) {
            this.rebindData();
        }
        this.setTitle(this.getString(2131887028));
        if (this.getSource().isNewSearch()) {
            this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery());
        }
        return;
    }

    public void onViewStateRestored(android.os.Bundle p2)
    {
        super.onViewStateRestored(p2);
        if (p2 != null) {
            this._sourceKey = p2.getString("SOURCE_KEY");
            if (this.mLayoutManager != null) {
                android.os.Parcelable v2_1 = p2.getParcelable("recyclerViewState");
                this.recyclerViewState = v2_1;
                this.mLayoutManager.onRestoreInstanceState(v2_1);
            }
        }
        return;
    }

    public void rebindData()
    {
        if (this.getSource() != null) {
            int v0_3;
            this.dataAdapter.addItems(this.getSource().getData());
            if (this.getSource().getItemCount() != 0) {
                v0_3 = 0;
            } else {
                v0_3 = 1;
            }
            this.showNoResults(v0_3);
            return;
        } else {
            return;
        }
    }

    public void reloadVisible()
    {
        return;
    }

    public void selectedServer(androidx.fragment.app.DialogFragment p2, com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        com.bisimplex.firebooru.network.SourceQuery v2_0 = this.getSource();
        v2_0.cancelCurrentConnection();
        v2_0.setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p3));
        this.setSourceName(p3.getServerName());
        this.searchQuery(v2_0.getQuery());
        return;
    }

    protected void setSourceName(String p1)
    {
        this.setSubtitle(p1);
        return;
    }

    public void setSubtitle(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.subtitleTextView.setText(p3);
            this.subtitleTextView.setVisibility(0);
        } else {
            this.subtitleTextView.setVisibility(8);
        }
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            this.getVisibleBar().setSubtitle(p3);
        }
        return;
    }

    public void setTitle(String p3)
    {
        super.setTitle(p3);
        androidx.appcompat.widget.Toolbar v0 = this.getVisibleBar();
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.titleTextView.setText(p3);
            v0.setTitle(p3);
            return;
        } else {
            this.titleTextView.setText(2131887028);
            v0.setTitle(2131887028);
            return;
        }
    }

    public void showNoResults(boolean p2)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 8;
        } else {
            v2_1 = 0;
        }
        this.noResultTextView.setVisibility(v2_1);
        return;
    }

    public void success(com.bisimplex.firebooru.network.Source p2, java.util.List p3)
    {
        if ((this.getActivity() != null) && (!this.isDetached())) {
            this.dataAdapter.addItems(p3);
            if ((this.listView != null) && (p2.getCurrentPage() == 1)) {
                this.listView.scrollToPosition(0);
            }
            this.HideLoading();
        }
        return;
    }
}
