package com.bisimplex.firebooru.dataadapter;
public class SublistHomeItemHolder extends com.bisimplex.firebooru.dataadapter.HomeItemHolder {
    private com.bisimplex.firebooru.dataadapter.GridPostDataAdapter adapter;
    boolean allowEdition;
    android.widget.ImageView menuButton;
    android.widget.TextView nameTextView;
    private com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener postItemClickListener;
    android.widget.ProgressBar progressBar;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    android.widget.ImageView reloadButton;
    android.widget.ImageView searchButton;
    String sourceKey;
    android.widget.TextView titleTextView;

    static bridge synthetic void -$$Nest$mshowMenu(com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder p0, android.view.View p1, int p2)
    {
        p0.showMenu(p1, p2);
        return;
    }

    public SublistHomeItemHolder(android.view.View p3, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p4, boolean p5)
    {
        super(p3, p4);
        super.postItemClickListener = new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$1(super);
        super.titleTextView = ((android.widget.TextView) p3.findViewById(2131362651));
        super.nameTextView = ((android.widget.TextView) p3.findViewById(2131362345));
        super.recyclerView = ((androidx.recyclerview.widget.RecyclerView) p3.findViewById(2131362449));
        super.searchButton = ((android.widget.ImageView) p3.findViewById(2131362497));
        super.reloadButton = ((android.widget.ImageView) p3.findViewById(2131362450));
        super.progressBar = ((android.widget.ProgressBar) p3.findViewById(2131362435));
        com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v4_19 = ((android.widget.ImageView) p3.findViewById(2131362297));
        super.menuButton = v4_19;
        super.allowEdition = p5;
        v4_19.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$4(super));
        super.reloadButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$5(super));
        super.searchButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$6(super));
        super.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(p3.getContext(), 0, 0));
        com.bisimplex.firebooru.dataadapter.GridPostDataAdapter v4_26 = new com.bisimplex.firebooru.dataadapter.GridPostDataAdapter(p3.getContext(), super.postItemClickListener, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThumbDisplayMode());
        super.adapter = v4_26;
        super.recyclerView.setAdapter(v4_26);
        return;
    }

    private void showMenu(android.view.View p3, int p4)
    {
        android.widget.PopupMenu v0_1 = new android.widget.PopupMenu(p3.getContext(), p3);
        v0_1.getMenuInflater().inflate(p4, v0_1.getMenu());
        if (!this.allowEdition) {
            com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$3 v3_1 = v0_1.getMenu();
            v3_1.findItem(2131361987).setVisible(0);
            v3_1.findItem(2131362453).setVisible(0);
            v3_1.findItem(2131362317).setVisible(0);
            v3_1.findItem(2131361871).setVisible(0);
            v3_1.findItem(2131362029).setVisible(0);
        }
        v0_1.setOnMenuItemClickListener(new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$2(this));
        v0_1.setOnDismissListener(new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder$3(this));
        v0_1.show();
        return;
    }

    public com.bisimplex.firebooru.dataadapter.GridPostDataAdapter getAdapter()
    {
        return this.adapter;
    }
}
