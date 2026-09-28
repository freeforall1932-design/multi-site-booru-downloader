package com.bisimplex.firebooru.fragment;
public class TagsBlackListFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    protected com.bisimplex.firebooru.fragment.TagsBlackListFragment$TagsDataAdapter adapter;
    private com.google.android.material.floatingactionbutton.FloatingActionButton addButton;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private final com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog$OnDynamicBlacklistSearchDialogListener searchDialogListener;

    static bridge synthetic void -$$Nest$fputquery(com.bisimplex.firebooru.fragment.TagsBlackListFragment p0, com.bisimplex.firebooru.network.SourceQuery p1)
    {
        p0.query = p1;
        return;
    }

    static bridge synthetic void -$$Nest$mshowSearch(com.bisimplex.firebooru.fragment.TagsBlackListFragment p0)
    {
        p0.showSearch();
        return;
    }

    public TagsBlackListFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.TagsBlackListFragment$4(this);
        return;
    }

    private void showSearch()
    {
        com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog v0_1 = new com.bisimplex.firebooru.view.DynamicBlacklistSearchDialog();
        v0_1.setListener(this.searchDialogListener);
        androidx.fragment.app.FragmentManager v1_3 = new android.os.Bundle(1);
        if (this.query != null) {
            v1_3.putString("QUERY_JSON", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.query));
        }
        v0_1.setArguments(v1_3);
        v0_1.show(this.getParentFragmentManager(), "DynamicServerSearchDialog_TAG");
        return;
    }

    public void addNewItem()
    {
        this.switchFragment(new com.bisimplex.firebooru.fragment.TagBlackListFormFragment());
        return;
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p2)
    {
        super.configureBar(p2);
        p2.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.TagsBlackListFragment$3(this));
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

    protected void deleteItemAt(int p5)
    {
        com.bisimplex.firebooru.fragment.TagsBlackListFragment$TagsDataAdapter v0_4 = this.adapter.getItem(p5);
        if (v0_4 != null) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteBannedTagById(v0_4.getId());
            com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().updateBannedTags();
            this.adapter.remove(p5);
            this.adapter.notifyItemRemoved(p5);
            return;
        } else {
            return;
        }
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public String getiOsFragmentName()
    {
        return "BannedTagViewController";
    }

    public android.view.View onCreateView(android.view.LayoutInflater p3, android.view.ViewGroup p4, android.os.Bundle p5)
    {
        android.view.View v3_1 = p3.inflate(2131558443, 0);
        if (p5 != null) {
            com.bisimplex.firebooru.network.SourceQuery v4_15 = p5.getString("query");
            if (!android.text.TextUtils.isEmpty(v4_15)) {
                this.query = ((com.bisimplex.firebooru.network.SourceQuery) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v4_15, com.bisimplex.firebooru.network.SourceQuery));
            }
        }
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) v3_1.findViewById(2131362449));
        com.bisimplex.firebooru.network.SourceQuery v4_7 = new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext());
        this.recyclerView.setLayoutManager(v4_7);
        com.google.gson.Gson v5_5 = new com.bisimplex.firebooru.fragment.TagsBlackListFragment$TagsDataAdapter(this, com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getBlacklistRules(this.query), new com.bisimplex.firebooru.fragment.TagsBlackListFragment$1(this));
        this.adapter = v5_5;
        this.recyclerView.setAdapter(v5_5);
        this.recyclerView.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this.recyclerView.getContext(), v4_7.getOrientation()));
        com.bisimplex.firebooru.network.SourceQuery v4_13 = ((com.google.android.material.floatingactionbutton.FloatingActionButton) v3_1.findViewById(2131361869));
        this.addButton = v4_13;
        v4_13.setOnClickListener(new com.bisimplex.firebooru.fragment.TagsBlackListFragment$2(this));
        this.fixFloatButtonLayout(this.addButton);
        return v3_1;
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
        this.getVisibleBar().setTitle(2131886863);
        return;
    }

    protected void reloadData()
    {
        this.adapter.setData(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getBlacklistRules(this.query));
        this.adapter.notifyDataSetChanged();
        return;
    }

    public void showItem(int p7)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            com.bisimplex.firebooru.fragment.TagBlackListFormFragment v1_1 = new com.bisimplex.firebooru.fragment.TagBlackListFormFragment();
            android.os.Bundle v2_1 = new android.os.Bundle();
            v2_1.putLong("RULE_ID", this.adapter.getItem(p7).getId());
            v1_1.setArguments(v2_1);
            v0_1.switchContent(v1_1);
            return;
        } else {
            return;
        }
    }
}
