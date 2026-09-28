package com.bisimplex.firebooru.dataadapter;
public class PoolDataAdapter$PoolItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder implements android.view.View$OnClickListener {
    public android.view.View container;
    public com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener itemListener;
    public com.mikepenz.iconics.view.IconicsImageView moreButton;
    public android.widget.TextView textView;

    public PoolDataAdapter$PoolItemHolder(android.view.View p2, com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener p3)
    {
        super(p2);
        super.container = p2;
        super.moreButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362312));
        super.textView = ((android.widget.TextView) p2.findViewById(2131362617));
        android.view.View v2_2 = super.moreButton;
        if (v2_2 != null) {
            v2_2.setOnClickListener(super);
        }
        android.view.View v2_3 = super.container;
        if (v2_3 != null) {
            v2_3.setOnClickListener(super);
        }
        super.itemListener = p3;
        return;
    }

    public void onClick(android.view.View p3)
    {
        com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener v0 = this.itemListener;
        if (v0 != null) {
            if (p3 != this.moreButton) {
                v0.itemClick(p3, this.getAdapterPosition());
            } else {
                v0.itemSecondaryClick(p3, this.getAdapterPosition());
                return;
            }
        }
        return;
    }
}
