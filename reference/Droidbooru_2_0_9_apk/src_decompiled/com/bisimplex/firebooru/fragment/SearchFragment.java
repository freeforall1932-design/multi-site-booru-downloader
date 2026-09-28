package com.bisimplex.firebooru.fragment;
public class SearchFragment extends com.bisimplex.firebooru.fragment.ServerChangerFragment implements android.view.View$OnClickListener, com.bisimplex.firebooru.danbooru.TransactionAction {
    public static final String SEARCH_TERM = "com.bisimplex.firebooru.SEARCH_TERM";
    private com.bisimplex.firebooru.fragment.SearchFragment$TagClientAdapter adapter;
    private android.widget.AutoCompleteTextView autoCompleteTextView;
    private String currentText;

    static bridge synthetic com.bisimplex.firebooru.fragment.SearchFragment$TagClientAdapter -$$Nest$fgetadapter(com.bisimplex.firebooru.fragment.SearchFragment p0)
    {
        return p0.adapter;
    }

    static bridge synthetic android.widget.AutoCompleteTextView -$$Nest$fgetautoCompleteTextView(com.bisimplex.firebooru.fragment.SearchFragment p0)
    {
        return p0.autoCompleteTextView;
    }

    static bridge synthetic String -$$Nest$fgetcurrentText(com.bisimplex.firebooru.fragment.SearchFragment p0)
    {
        return p0.currentText;
    }

    static bridge synthetic void -$$Nest$fputcurrentText(com.bisimplex.firebooru.fragment.SearchFragment p0, String p1)
    {
        p0.currentText = p1;
        return;
    }

    public SearchFragment()
    {
        return;
    }

    void askShowServers()
    {
        this.showServers(0, 0, 0);
        return;
    }

    public void error(com.bisimplex.firebooru.danbooru.FailureType p1)
    {
        return;
    }

    public void onClick(android.view.View p1)
    {
        this.search(p1);
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558437, p3, 0);
        ((android.widget.ImageButton) v2_1.findViewById(2131362503)).setOnClickListener(this);
        this.setTitle(2131886860);
        this.adapter = new com.bisimplex.firebooru.fragment.SearchFragment$TagClientAdapter(this, this.getActivity());
        android.widget.AutoCompleteTextView v3_5 = ((android.widget.AutoCompleteTextView) v2_1.findViewById(2131362031));
        this.autoCompleteTextView = v3_5;
        v3_5.setAdapter(this.adapter);
        this.autoCompleteTextView.setOnKeyListener(new com.bisimplex.firebooru.fragment.SearchFragment$1(this));
        this.autoCompleteTextView.addTextChangedListener(new com.bisimplex.firebooru.fragment.SearchFragment$2(this));
        this.autoCompleteTextView.setOnItemClickListener(new com.bisimplex.firebooru.fragment.SearchFragment$3(this));
        this.autoCompleteTextView.setOnClickListener(new com.bisimplex.firebooru.fragment.SearchFragment$4(this));
        this.autoCompleteTextView.setOnItemSelectedListener(new com.bisimplex.firebooru.fragment.SearchFragment$5(this));
        return v2_1;
    }

    public void search(android.view.View p2)
    {
        if (this.getView() != null) {
            this.searchText(((android.widget.EditText) this.getView().findViewById(2131362031)).getText().toString());
        }
        return;
    }

    public void searchTags(String p2)
    {
        if (p2 != null) {
            android.widget.AutoCompleteTextView v2_2 = p2.split(" ");
            if (v2_2.length != 0) {
                v2_2[(v2_2.length - 1)].length();
                this.autoCompleteTextView.getThreshold();
                return;
            }
        }
        return;
    }

    public void searchText(String p3)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(p3), 0);
        }
        return;
    }

    public void success()
    {
        this.adapter.clear();
        android.widget.AutoCompleteTextView v0_7 = new java.util.ArrayList();
        this.adapter.setData(v0_7);
        this.adapter.getCount();
        android.util.Log.i("Gelbooru", String.format("TagCount = %d", new Object[] {Integer.valueOf(v0_7.size())})));
        this.adapter.notifyDataSetChanged();
        this.autoCompleteTextView.invalidate();
        return;
    }
}
