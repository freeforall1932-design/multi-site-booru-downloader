package com.bisimplex.firebooru.dataadapter;
public class GridPostDataAdapter$PostItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder implements android.view.View$OnClickListener, android.view.View$OnLongClickListener {
    public com.mikepenz.iconics.view.IconicsImageView animatedImageView;
    public android.widget.TextView blacklistedTextView;
    public android.view.View container;
    public com.mikepenz.iconics.view.IconicsImageView heartImageView;
    public android.widget.ImageView imageView;
    public com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener mListener;
    public android.widget.TextView textView;

    public GridPostDataAdapter$PostItemHolder(android.view.View p2, com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener p3)
    {
        super(p2);
        super.container = p2;
        super.imageView = ((android.widget.ImageView) p2.findViewById(2131362176));
        super.heartImageView = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362151));
        super.animatedImageView = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131361884));
        super.mListener = p3;
        super.container.setOnClickListener(super);
        super.container.setOnLongClickListener(super);
        int v3_5 = ((android.widget.TextView) p2.findViewById(2131362617));
        super.textView = v3_5;
        if (v3_5 != 0) {
            v3_5.setPaintFlags((v3_5.getPaintFlags() | 8));
        }
        super.blacklistedTextView = ((android.widget.TextView) p2.findViewById(2131361912));
        return;
    }

    public void onClick(android.view.View p3)
    {
        com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener v0 = this.mListener;
        if (v0 != null) {
            v0.itemClick(p3, this.getBindingAdapterPosition());
        }
        return;
    }

    public boolean onLongClick(android.view.View p3)
    {
        com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener v0 = this.mListener;
        if (v0 == null) {
            return 0;
        } else {
            return v0.onLongClick(p3, this.getBindingAdapterPosition());
        }
    }
}
