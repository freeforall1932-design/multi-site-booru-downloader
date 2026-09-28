package com.bisimplex.firebooru.dataadapter;
public class PoolDataAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private android.content.Context context;
    protected java.util.ArrayList data;
    private com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener listener;

    public PoolDataAdapter(android.content.Context p1, com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemListener p2)
    {
        this.context = p1;
        this.listener = p2;
        this.data = new java.util.ArrayList();
        return;
    }

    public void addItems(java.util.List p6)
    {
        if ((p6 != 0) && (!p6.isEmpty())) {
            int v0_2 = this.data.size();
            int v1_1 = p6.iterator();
            while (v1_1.hasNext()) {
                this.data.add(new com.bisimplex.firebooru.model.PoolItem(((com.bisimplex.firebooru.model.Pool) v1_1.next())));
            }
            if (v0_2 != 0) {
                if (p6.size() > 0) {
                    this.notifyItemRangeInserted(v0_2, p6.size());
                }
            } else {
                this.notifyDataSetChanged();
                return;
            }
        }
        return;
    }

    public void clearItems()
    {
        int v0_2 = this.data.size();
        if (v0_2 > 0) {
            com.bisimplex.firebooru.model.PoolItem v2 = this.getItem(0);
            this.data.clear();
            if (v2.getType() != 1) {
                this.notifyItemRangeRemoved(0, v0_2);
            } else {
                this.data.add(v2);
                this.notifyItemRangeRemoved(1, (v0_2 - int v4));
                return;
            }
        }
        return;
    }

    public android.content.Context getContext()
    {
        return this.context;
    }

    public com.bisimplex.firebooru.model.PoolItem getItem(int p2)
    {
        return ((com.bisimplex.firebooru.model.PoolItem) this.data.get(p2));
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public int getItemViewType(int p1)
    {
        return this.getItem(p1).getType();
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder p3, int p4)
    {
        String v4_1 = this.getItem(p4);
        if (v4_1 != null) {
            if (v4_1.getType() != 1) {
                String v4_4 = v4_1.getPool();
                if (v4_4 != null) {
                    p3.textView.setText(v4_4.getName());
                    return;
                }
            } else {
                p3.textView.setText(v4_1.getLabel());
                return;
            }
        }
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        if (p4 != 1) {
            return new com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558612, p3, 0), this.listener);
        } else {
            return new com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558640, p3, 0), this.listener);
        }
    }

    public bridge synthetic void onViewAttachedToWindow(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewAttachedToWindow(((com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder) p1));
        return;
    }

    public void onViewAttachedToWindow(com.bisimplex.firebooru.dataadapter.PoolDataAdapter$PoolItemHolder p3)
    {
        super.onViewAttachedToWindow(p3);
        int v0 = p3.getAdapterPosition();
        if (p3.itemListener != null) {
            p3.itemListener.attachedToWindow(v0);
        }
        return;
    }

    public void setSource(String p5)
    {
        if (this.data.size() != 0) {
            java.util.ArrayList v0_6 = ((com.bisimplex.firebooru.model.PoolItem) this.data.get(0));
            if (v0_6.getType() != 1) {
                this.data.add(0, new com.bisimplex.firebooru.model.PoolItem(p5));
                this.notifyItemInserted(0);
                return;
            } else {
                v0_6.setLabel(p5);
                this.notifyItemChanged(0);
                return;
            }
        } else {
            this.data.add(new com.bisimplex.firebooru.model.PoolItem(p5));
            this.notifyItemInserted(0);
            return;
        }
    }
}
