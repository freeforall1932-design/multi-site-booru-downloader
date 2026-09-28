package com.bisimplex.firebooru.dataadapter;
public class TagClientAdapter extends android.widget.ArrayAdapter {
    private int[] colors;
    private java.util.List data;

    public TagClientAdapter(android.content.Context p3)
    {
        int v0 = 0;
        super(p3, 0);
        super.data = new java.util.ArrayList();
        int[] v3_5 = new int[10];
        super.colors = v3_5;
        while (v0 < 8) {
            super.colors[v0] = com.bisimplex.firebooru.danbooru.TagItem.getColorIdByType(v0);
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

    public android.view.View getView(int p6, android.view.View p7, android.view.ViewGroup p8)
    {
        int v8_2;
        int v6_1 = this.getItem(p6);
        if (p7 != null) {
            v8_2 = ((com.bisimplex.firebooru.dataadapter.TagClientHolder) p7.getTag());
        } else {
            p7 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558616, 0);
            v8_2 = new com.bisimplex.firebooru.dataadapter.TagClientHolder();
            v8_2.autocompleteTextView = ((android.widget.TextView) p7.findViewById(2131361898));
            v8_2.iconImageView = ((android.widget.ImageView) p7.findViewById(2131362169));
            p7.setTag(v8_2);
        }
        int v6_3;
        android.widget.TextView v0_7 = v8_2.autocompleteTextView;
        v0_7.setText(v6_1.getName());
        int v6_2 = v6_1.getType();
        com.mikepenz.iconics.IconicsDrawable v1_1 = 7;
        if (v6_2 < 7) {
            v1_1 = v6_2;
            v6_3 = com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.getContext(), com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_tag, this.colors[v6_2]);
        } else {
            v6_3 = com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.getContext(), com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_hourglass, this.colors[7]);
        }
        int v2_4 = this.getContext().getResources().getDimensionPixelSize(2131166098);
        v6_3.setSizeYPx(v2_4);
        v6_3.setSizeXPx(v2_4);
        v8_2.iconImageView.setImageDrawable(v6_3);
        v0_7.setTextColor(androidx.core.content.ContextCompat.getColor(this.getContext(), this.colors[v1_1]));
        return p7;
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
