package com.bisimplex.firebooru.dataadapter;
public class TitleHomeItemHolder extends com.bisimplex.firebooru.dataadapter.HomeItemHolder {
    android.widget.ImageView menuButton;
    android.widget.TextView titleTextView;

    static bridge synthetic void -$$Nest$mshowMenu(com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder p0, android.view.View p1, int p2)
    {
        p0.showMenu(p1, p2);
        return;
    }

    public TitleHomeItemHolder(android.view.View p1, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p2)
    {
        super(p1, p2);
        super.titleTextView = ((android.widget.TextView) p1.findViewById(2131362651));
        android.widget.ImageView v1_2 = ((android.widget.ImageView) p1.findViewById(2131362297));
        super.menuButton = v1_2;
        v1_2.setOnClickListener(new com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder$1(super));
        return;
    }

    private void showMenu(android.view.View p3, int p4)
    {
        android.widget.PopupMenu v0_1 = new android.widget.PopupMenu(p3.getContext(), p3);
        v0_1.getMenuInflater().inflate(p4, v0_1.getMenu());
        v0_1.setOnMenuItemClickListener(new com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder$2(this));
        v0_1.setOnDismissListener(new com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder$3(this));
        v0_1.show();
        return;
    }
}
