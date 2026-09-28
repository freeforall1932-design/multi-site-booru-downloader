package com.bisimplex.firebooru.fragment;
public class TagsBlackListFragment$TagsDataAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private java.util.List data;
    private com.bisimplex.firebooru.fragment.TagsBlackListFragment$ListItemClick listener;
    final synthetic com.bisimplex.firebooru.fragment.TagsBlackListFragment this$0;

    public TagsBlackListFragment$TagsDataAdapter(com.bisimplex.firebooru.fragment.TagsBlackListFragment p1, java.util.List p2, com.bisimplex.firebooru.fragment.TagsBlackListFragment$ListItemClick p3)
    {
        this.this$0 = p1;
        this.data = p2;
        this.listener = p3;
        return;
    }

    public com.bisimplex.firebooru.model.BlacklistRule getItem(int p2)
    {
        return ((com.bisimplex.firebooru.model.BlacklistRule) this.data.get(p2));
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder p4, int p5)
    {
        String v5_2;
        String v5_6 = ((com.bisimplex.firebooru.model.BlacklistRule) this.data.get(p5));
        p4.row_title.setText(v5_6.getTagString());
        android.widget.TextView v4_1 = p4.site_title;
        String v1_1 = this.this$0.getString(2131886159);
        if (!android.text.TextUtils.isEmpty(v5_6.getSiteString())) {
            v5_2 = v5_6.getSiteString();
        } else {
            v5_2 = this.this$0.getString(2131886145);
        }
        v4_1.setText(String.format(java.util.Locale.US, "%s: %s", new Object[] {v1_1, v5_2})));
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder onCreateViewHolder(android.view.ViewGroup p4, int p5)
    {
        return new com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder(this.this$0.getLayoutInflater().inflate(2131558660, p4, 0), this.listener);
    }

    public void remove(int p2)
    {
        this.data.remove(p2);
        return;
    }

    public void setData(java.util.List p1)
    {
        this.data = p1;
        return;
    }
}
