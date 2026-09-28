package com.bisimplex.firebooru.dataadapter;
public class ButtonHomeItemHolder extends com.bisimplex.firebooru.dataadapter.HomeItemHolder {
    android.widget.ImageButton imageButton;
    android.widget.ImageView menuButton;

    static bridge synthetic void -$$Nest$mshowMenu(com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder p0, android.view.View p1, int p2)
    {
        p0.showMenu(p1, p2);
        return;
    }

    public ButtonHomeItemHolder(android.view.View p2, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p3)
    {
        super(p2, p3);
        com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder$2 v3_5 = ((android.widget.ImageButton) p2.findViewById(2131362177));
        super.imageButton = v3_5;
        v3_5.setOnClickListener(new com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder$1(super));
        android.widget.ImageView v2_2 = ((android.widget.ImageView) p2.findViewById(2131362297));
        super.menuButton = v2_2;
        v2_2.setOnClickListener(new com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder$2(super));
        return;
    }

    private void showMenu(android.view.View p3, int p4)
    {
        android.widget.PopupMenu v0_1 = new android.widget.PopupMenu(p3.getContext(), p3);
        v0_1.getMenuInflater().inflate(p4, v0_1.getMenu());
        v0_1.setOnMenuItemClickListener(new com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder$3(this));
        v0_1.setOnDismissListener(new com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder$4(this));
        v0_1.show();
        return;
    }
}
