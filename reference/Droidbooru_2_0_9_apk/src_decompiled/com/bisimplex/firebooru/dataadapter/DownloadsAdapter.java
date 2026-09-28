package com.bisimplex.firebooru.dataadapter;
public class DownloadsAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private android.content.Context context;
    private io.objectbox.query.LazyList entryList;
    private com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener listener;

    public DownloadsAdapter(android.content.Context p1, com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener p2)
    {
        this.context = p1;
        this.listener = p2;
        return;
    }

    private void recycleHolder(com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder p3)
    {
        if ((p3 != null) && (p3.imageView != null)) {
            p3.imageView.setImageBitmap(0);
            p3.imageView.setImageDrawable(0);
            com.bumptech.glide.RequestManager v0_4 = this.context;
            if (v0_4 != null) {
                com.bumptech.glide.Glide.with(v0_4).clear(p3.imageView);
            }
        }
        return;
    }

    public void deleteItemAt(int p2)
    {
        this.entryList.remove(p2);
        this.notifyItemRemoved(p2);
        return;
    }

    public android.content.Context getContext()
    {
        return this.context;
    }

    public com.bisimplex.firebooru.model.DownloadEntry getItem(int p2)
    {
        if ((p2 >= null) && (p2 < this.entryList.size())) {
            return ((com.bisimplex.firebooru.model.DownloadEntry) this.entryList.get(p2));
        } else {
            return 0;
        }
    }

    public int getItemCount()
    {
        return this.entryList.size();
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder p5, int p6)
    {
        com.bumptech.glide.RequestBuilder v6_1 = this.getItem(p6);
        if (v6_1 != null) {
            String v0_0;
            int v1_2;
            String v0_4 = v6_1.getStatus();
            p5.messageTextView.setText("");
            if (v0_4 == null) {
                v0_0 = this.context.getString(2131887263);
                v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099712);
            } else {
                if (v0_4 == 1) {
                    v0_0 = this.context.getString(2131886441);
                    v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099705);
                } else {
                    if (v0_4 == 2) {
                        v0_0 = this.context.getString(2131886440);
                        v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099704);
                    } else {
                        if (v0_4 == 3) {
                            v0_0 = this.context.getString(2131886578);
                            v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099707);
                            p5.messageTextView.setText(v6_1.getError_message());
                        } else {
                            if (v0_4 == 4) {
                                v0_0 = this.context.getString(2131886445);
                                v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099706);
                            } else {
                                v0_0 = this.context.getString(2131887228);
                                v1_2 = androidx.core.content.ContextCompat.getColor(this.context, 2131099706);
                            }
                        }
                    }
                }
            }
            p5.statusTextView.setText(v0_0);
            p5.statusTextView.setTextColor(v1_2);
            p5.messageTextView.setTextColor(v1_2);
            p5.urlTextView.setText(v6_1.getPost_url());
            com.bumptech.glide.RequestBuilder v6_4 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPreview(0, v6_1.getPreview_url());
            if (v6_4 != null) {
                com.bumptech.glide.Glide.with(this.context).load(v6_4).apply(com.bisimplex.firebooru.network.Utils.getInstance().getThumbsOptions()).transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade()).into(p5.imageView);
            }
        }
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        return new com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558480, p3, 0), this.listener);
    }

    public bridge synthetic void onViewAttachedToWindow(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewAttachedToWindow(((com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder) p1));
        return;
    }

    public void onViewAttachedToWindow(com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder p3)
    {
        super.onViewAttachedToWindow(p3);
        int v0 = p3.getAdapterPosition();
        if (p3.mListener != null) {
            p3.mListener.attachedToWindow(v0);
        }
        return;
    }

    public bridge synthetic void onViewRecycled(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewRecycled(((com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder) p1));
        return;
    }

    public void onViewRecycled(com.bisimplex.firebooru.dataadapter.DownloadsAdapter$ItemHolder p1)
    {
        super.onViewRecycled(p1);
        this.recycleHolder(p1);
        return;
    }

    public void reloadItems(io.objectbox.query.LazyList p1)
    {
        this.entryList = p1;
        this.notifyDataSetChanged();
        return;
    }

    public void updateItemAt(int p1)
    {
        this.notifyItemChanged(p1);
        return;
    }

    public boolean updateItems(io.objectbox.query.LazyList p4)
    {
        int v0_1;
        int v0_0 = this.entryList;
        int v1 = 0;
        if (v0_0 == 0) {
            v0_1 = 0;
        } else {
            v0_1 = v0_0.size();
        }
        if (p4.size() > v0_1) {
            v1 = 1;
        }
        this.entryList = p4;
        this.notifyDataSetChanged();
        return v1;
    }
}
