package com.bisimplex.firebooru.fragment;
public class ServersFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    protected com.bisimplex.firebooru.fragment.ServerListDataAdapter adapter;
    protected com.google.android.material.floatingactionbutton.FloatingActionButton addButton;
    protected com.bisimplex.firebooru.network.SourceQuery query;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private final com.bisimplex.firebooru.view.DynamicServerSearchDialog$OnDynamicServerSearchDialogListener searchDialogListener;

    static bridge synthetic void -$$Nest$mshowSearch(com.bisimplex.firebooru.fragment.ServersFragment p0)
    {
        p0.showSearch();
        return;
    }

    public ServersFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.ServersFragment$1(this);
        return;
    }

    private void showSearch()
    {
        com.bisimplex.firebooru.view.DynamicServerSearchDialog v0_1 = new com.bisimplex.firebooru.view.DynamicServerSearchDialog();
        v0_1.setListener(this.searchDialogListener);
        androidx.fragment.app.FragmentManager v1_3 = new android.os.Bundle(3);
        if (this.query != null) {
            v1_3.putString("QUERY_JSON", new com.google.gson.Gson().toJson(this.query));
        }
        v0_1.setArguments(v1_3);
        v0_1.show(this.getParentFragmentManager(), "DynamicServerSearchDialog_TAG");
        return;
    }

    public void addNewItem()
    {
        this.switchFragment(new com.bisimplex.firebooru.fragment.DynamicServerFormFragment());
        return;
    }

    protected com.bisimplex.firebooru.fragment.ServerListDataAdapter configureAdapter()
    {
        return new com.bisimplex.firebooru.fragment.ServerListDataAdapter(this.getActivity(), new com.bisimplex.firebooru.fragment.ServersFragment$3(this));
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p2)
    {
        super.configureBar(p2);
        p2.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.ServersFragment$4(this));
        return;
    }

    protected void configureInsets(androidx.core.graphics.Insets p3, androidx.core.graphics.Insets p4)
    {
        super.configureInsets(p3, p4);
        android.view.ViewGroup$MarginLayoutParams v3_3 = ((android.view.ViewGroup$MarginLayoutParams) this.addButton.getLayoutParams());
        v3_3.bottomMargin = (p4.bottom + this.getResources().getDimensionPixelSize(2131165370));
        this.addButton.setLayoutParams(v3_3);
        return;
    }

    protected void deleteServerAt(int p2)
    {
        int v2_2 = this.adapter.getItem(p2);
        if (!v2_2.isDefault()) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteServerById(v2_2.getServerId());
            this.reloadData();
            return;
        } else {
            this.showMessage(2131886500, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    protected void editServerAt(int p4)
    {
        String v4_3 = this.adapter.getItem(p4);
        if (!v4_3.isDefault()) {
            com.bisimplex.firebooru.fragment.DynamicServerFormFragment v0_4 = new com.bisimplex.firebooru.fragment.DynamicServerFormFragment();
            android.os.Bundle v1_1 = new android.os.Bundle();
            v1_1.putString("ServerFormFragment_Data", new com.google.gson.Gson().toJson(v4_3));
            v0_4.setArguments(v1_1);
            this.switchFragment(v0_4);
            return;
        } else {
            this.showMessage(2131886501, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    protected android.view.View getSnackBarAnchorView()
    {
        return this.addButton;
    }

    protected java.util.List loadData()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServers(this.query);
    }

    public boolean onContextItemSelected(android.view.MenuItem p4)
    {
        android.widget.AdapterView$AdapterContextMenuInfo v0_1 = ((android.widget.AdapterView$AdapterContextMenuInfo) p4.getMenuInfo());
        if (p4.getItemId() != 2131362513) {
            if (p4.getItemId() != 2131361987) {
                if (p4.getItemId() != 2131362029) {
                    return super.onContextItemSelected(p4);
                } else {
                    this.editServerAt(v0_1.position);
                }
            } else {
                this.deleteServerAt(v0_1.position);
            }
        } else {
            this.selectServerAt(v0_1.position);
        }
        return 1;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558443, 0);
        if (p4 != null) {
            com.bisimplex.firebooru.network.SourceQuery v3_19 = p4.getString("query", 0);
            if (!android.text.TextUtils.isEmpty(v3_19)) {
                this.query = ((com.bisimplex.firebooru.network.SourceQuery) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v3_19, com.bisimplex.firebooru.network.SourceQuery));
            }
        }
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getDefaultServer();
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) v2_1.findViewById(2131362449));
        com.bisimplex.firebooru.network.SourceQuery v3_8 = new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext());
        this.recyclerView.setLayoutManager(v3_8);
        this.recyclerView.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this.recyclerView.getContext(), v3_8.getOrientation()));
        this.adapter = this.configureAdapter();
        this.adapter.setData(this.loadData());
        this.recyclerView.setAdapter(this.adapter);
        com.bisimplex.firebooru.network.SourceQuery v3_17 = ((com.google.android.material.floatingactionbutton.FloatingActionButton) v2_1.findViewById(2131361869));
        this.addButton = v3_17;
        v3_17.setOnClickListener(new com.bisimplex.firebooru.fragment.ServersFragment$2(this));
        this.fixFloatButtonLayout(this.addButton);
        return v2_1;
    }

    public void onDeleteServer(com.bisimplex.firebooru.dataadapter.ServersDataAdapter p1, int p2)
    {
        this.deleteServerAt(p2);
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        if (this.query != null) {
            p3.putString("query", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.query));
        }
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        return;
    }

    protected void reloadData()
    {
        this.adapter.setData(this.loadData());
        this.adapter.notifyDataSetChanged();
        return;
    }

    protected void selectServerAt(int p3)
    {
        com.bisimplex.firebooru.activity.MainActivity v3_3 = this.adapter.getItem(p3);
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(v3_3.getServerId());
        this.showMessage(2131887144, com.bisimplex.firebooru.activity.MessageType.Success);
        com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().setServerDescription(v3_3);
        this.reloadData();
        com.bisimplex.firebooru.activity.MainActivity v3_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v3_2 != null) {
            v3_2.reloadMenuOptions();
        }
        return;
    }
}
