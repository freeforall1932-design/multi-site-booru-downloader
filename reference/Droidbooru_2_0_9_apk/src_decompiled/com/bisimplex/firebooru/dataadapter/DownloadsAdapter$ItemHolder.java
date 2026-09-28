package com.bisimplex.firebooru.dataadapter;
public class DownloadsAdapter$ItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder implements android.view.View$OnClickListener, android.view.View$OnLongClickListener {
    public android.view.View container;
    public android.widget.ImageView imageView;
    public com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener mListener;
    public android.widget.TextView messageTextView;
    public android.widget.TextView statusTextView;
    public android.widget.TextView urlTextView;

    public DownloadsAdapter$ItemHolder(android.view.View p2, com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener p3)
    {
        super(p2);
        super.container = p2;
        super.imageView = ((android.widget.ImageView) p2.findViewById(2131362178));
        super.mListener = p3;
        super.container.setOnClickListener(super);
        super.statusTextView = ((android.widget.TextView) p2.findViewById(2131362576));
        super.urlTextView = ((android.widget.TextView) p2.findViewById(2131362684));
        super.messageTextView = ((android.widget.TextView) p2.findViewById(2131362302));
        return;
    }

    public void onClick(android.view.View p3)
    {
        com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener v0 = this.mListener;
        if (v0 != null) {
            v0.itemClick(p3, this.getAdapterPosition());
        }
        return;
    }

    public boolean onLongClick(android.view.View p3)
    {
        com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener v0 = this.mListener;
        if (v0 == null) {
            return 0;
        } else {
            return v0.onLongClick(p3, this.getAdapterPosition());
        }
    }
}
