package com.bisimplex.firebooru.dataadapter;
public class TagsDataAdapter extends android.widget.ArrayAdapter {

    public TagsDataAdapter(android.content.Context p2)
    {
        super(p2, 0);
        return;
    }

    public android.view.View getView(int p2, android.view.View p3, android.view.ViewGroup p4)
    {
        String v2_3 = ((com.bisimplex.firebooru.model.BannedTag) this.getItem(p2));
        if (p3 == null) {
            p3 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558660, 0);
        }
        ((android.widget.TextView) p3.findViewById(2131362621)).setText(v2_3.tagText);
        return p3;
    }
}
