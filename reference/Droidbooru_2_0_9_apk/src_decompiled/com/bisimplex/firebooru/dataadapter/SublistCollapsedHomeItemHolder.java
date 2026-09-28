package com.bisimplex.firebooru.dataadapter;
public class SublistCollapsedHomeItemHolder extends com.bisimplex.firebooru.dataadapter.HomeItemHolder {
    android.widget.ImageView expandButton;
    android.widget.TextView nameTextView;
    android.widget.ImageView searchButton;
    android.widget.TextView titleTextView;

    public SublistCollapsedHomeItemHolder(android.view.View p1, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p2)
    {
        super(p1, p2);
        super.titleTextView = ((android.widget.TextView) p1.findViewById(2131362651));
        super.nameTextView = ((android.widget.TextView) p1.findViewById(2131362345));
        super.expandButton = ((android.widget.ImageView) p1.findViewById(2131362097));
        super.searchButton = ((android.widget.ImageView) p1.findViewById(2131362497));
        super.expandButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder$1(super));
        super.searchButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder$2(super));
        return;
    }
}
