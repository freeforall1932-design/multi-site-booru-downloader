package com.bisimplex.firebooru.fragment;
public class SearchFragment$TagClientAdapter extends android.widget.ArrayAdapter {
    private int[] colors;
    private java.util.List data;
    final synthetic com.bisimplex.firebooru.fragment.SearchFragment this$0;

    public SearchFragment$TagClientAdapter(com.bisimplex.firebooru.fragment.SearchFragment p4, android.content.Context p5)
    {
        this.this$0 = p4;
        int v0 = 0;
        super(p5, 0);
        super.data = new java.util.ArrayList();
        com.bisimplex.firebooru.danbooru.TagItem v5_6 = new int[10];
        super.colors = v5_6;
        android.content.res.Resources v4_1 = p4.getResources();
        com.bisimplex.firebooru.danbooru.TagItem v5_2 = new com.bisimplex.firebooru.danbooru.TagItem();
        while (v0 < 8) {
            v5_2.setType(v0);
            super.colors[v0] = v4_1.getColor(v5_2.getTypeColorId());
            v0++;
        }
        return;
    }

    public int getCount()
    {
        int v0_0 = this.data;
        if (v0_0 != 0) {
            return v0_0.size();
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.TagItem getItem(int p2)
    {
        return ((com.bisimplex.firebooru.danbooru.TagItem) this.data.get(p2));
    }

    public bridge synthetic Object getItem(int p1)
    {
        return this.getItem(p1);
    }

    public long getItemId(int p3)
    {
        return ((long) p3);
    }

    public android.view.View getView(int p5, android.view.View p6, android.view.ViewGroup p7)
    {
        int[] v7_2;
        int v5_1 = this.getItem(p5);
        if (p6 != null) {
            v7_2 = ((com.bisimplex.firebooru.fragment.SearchFragment$TagClientHolder) p6.getTag());
        } else {
            p6 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558616, 0);
            v7_2 = new com.bisimplex.firebooru.fragment.SearchFragment$TagClientHolder(this.this$0);
            v7_2.autocompleteTextView = ((android.widget.TextView) p6.findViewById(2131361898));
            v7_2.iconImageView = ((android.widget.ImageView) p6.findViewById(2131362169));
            p6.setTag(v7_2);
        }
        android.widget.TextView v0_8 = v7_2.autocompleteTextView;
        v0_8.setText(v5_1.getName());
        int v5_2 = v5_1.getType();
        if (v5_2 < 7) {
            v7_2.iconImageView.setImageDrawable(this.this$0.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        } else {
            v7_2.iconImageView.setImageDrawable(this.this$0.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
            v5_2 = 7;
        }
        v0_8.setTextColor(this.colors[v5_2]);
        return p6;
    }

    public String getiOsFragmentName()
    {
        return "SearchViewController";
    }

    public void setData(java.util.List p1)
    {
        if (p1 != null) {
            this.data = p1;
            return;
        } else {
            this.data = new java.util.ArrayList();
            return;
        }
    }
}
