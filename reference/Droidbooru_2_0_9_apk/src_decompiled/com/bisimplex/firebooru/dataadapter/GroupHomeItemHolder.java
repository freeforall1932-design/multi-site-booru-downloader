package com.bisimplex.firebooru.dataadapter;
public class GroupHomeItemHolder extends com.bisimplex.firebooru.dataadapter.HomeItemHolder {
    androidx.constraintlayout.widget.ConstraintLayout containerLayout;
    android.widget.TextView descriptionTextView;
    android.widget.ImageView menuButton;
    android.widget.TextView titleTextView;

    static bridge synthetic void -$$Nest$mshowMenu(com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder p0, android.view.View p1, int p2)
    {
        p0.showMenu(p1, p2);
        return;
    }

    public GroupHomeItemHolder(android.view.View p1, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p2)
    {
        super(p1, p2);
        super.titleTextView = ((android.widget.TextView) p1.findViewById(2131362651));
        super.descriptionTextView = ((android.widget.TextView) p1.findViewById(2131361992));
        super.menuButton = ((android.widget.ImageView) p1.findViewById(2131362297));
        android.widget.ImageView v1_2 = ((androidx.constraintlayout.widget.ConstraintLayout) p1.findViewById(2131361961));
        super.containerLayout = v1_2;
        v1_2.setOnClickListener(new com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder$1(super));
        super.menuButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder$2(super));
        return;
    }

    private void showMenu(android.view.View p3, int p4)
    {
        android.widget.PopupMenu v0_1 = new android.widget.PopupMenu(p3.getContext(), p3);
        v0_1.getMenuInflater().inflate(p4, v0_1.getMenu());
        v0_1.setOnMenuItemClickListener(new com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder$3(this));
        v0_1.setOnDismissListener(new com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder$4(this));
        v0_1.show();
        return;
    }
}
