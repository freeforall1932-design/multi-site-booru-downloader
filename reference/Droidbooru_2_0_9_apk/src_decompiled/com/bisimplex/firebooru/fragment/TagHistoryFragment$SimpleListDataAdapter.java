package com.bisimplex.firebooru.fragment;
public class TagHistoryFragment$SimpleListDataAdapter extends android.widget.ArrayAdapter {
    final synthetic com.bisimplex.firebooru.fragment.TagHistoryFragment this$0;

    public TagHistoryFragment$SimpleListDataAdapter(com.bisimplex.firebooru.fragment.TagHistoryFragment p1, android.content.Context p2)
    {
        this.this$0 = p1;
        super(p2, 0);
        return;
    }

    public android.view.View getView(int p2, android.view.View p3, android.view.ViewGroup p4)
    {
        int v2_2 = ((com.bisimplex.firebooru.model.TagHistory) this.getItem(p2));
        if (p3 == null) {
            p3 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558498, 0);
        }
        int v2_4;
        ((android.widget.TextView) p3.findViewById(2131362479)).setText(v2_2.search);
        android.widget.ImageView v4_4 = p3.findViewById(2131362178);
        if (v2_2.isFavoritedHistoryItem != 1) {
            v2_4 = 8;
        } else {
            v2_4 = 0;
        }
        ((android.widget.ImageView) v4_4).setVisibility(v2_4);
        return p3;
    }
}
