package com.bisimplex.firebooru.fragment;
public class ServerListDataAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    android.content.Context context;
    java.util.List data;
    protected boolean enableDelete;
    protected boolean highlightSelected;
    protected com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick listener;
    private int selectedServerID;

    public ServerListDataAdapter(android.content.Context p2, com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick p3)
    {
        this.selectedServerID = -1;
        this.enableDelete = 1;
        this.highlightSelected = 1;
        this.listener = p3;
        this.context = p2;
        return;
    }

    private int positionById(int p3)
    {
        int v0 = 0;
        while (v0 < this.data.size()) {
            if (((com.bisimplex.firebooru.danbooru.ServerItem) this.data.get(v0)).getServerId() != p3) {
                v0++;
            } else {
                return v0;
            }
        }
        return -1;
    }

    protected void deleteServerAt(int p2)
    {
        com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick v0 = this.listener;
        if (v0 != null) {
            v0.onDeleteServer(p2);
        }
        return;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getItem(int p2)
    {
        return ((com.bisimplex.firebooru.danbooru.ServerItem) this.data.get(p2));
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getSelectedServer()
    {
        int v0_1 = this.positionById(this.selectedServerID);
        if (v0_1 < 0) {
            return 0;
        } else {
            return this.getItem(v0_1);
        }
    }

    public int getSelectedServerID()
    {
        return this.selectedServerID;
    }

    public int getSelectedServerPosition()
    {
        return this.positionById(this.selectedServerID);
    }

    public boolean isHighlightSelected()
    {
        return this.highlightSelected;
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder p3, int p4)
    {
        int v4_1;
        int v4_4 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.data.get(p4));
        p3.textViewUrl.setText(v4_4.getServerName());
        p3.textViewDescription.setText(v4_4.getExtraInfo());
        if (!this.highlightSelected) {
            v4_1 = 0;
        } else {
            if (this.selectedServerID <= 0) {
                v4_1 = v4_4.isSelected();
            } else {
                if (v4_4.getServerId() != this.selectedServerID) {
                } else {
                    v4_1 = 1;
                }
            }
        }
        if (v4_1 == 0) {
            p3.itemView.setBackgroundColor(0);
            return;
        } else {
            p3.itemView.setBackgroundColor(androidx.core.content.ContextCompat.getColor(this.context, 2131099701));
            return;
        }
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder onCreateViewHolder(android.view.ViewGroup p4, int p5)
    {
        return new com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder(android.view.LayoutInflater.from(this.context).inflate(2131558635, p4, 0), this.listener, this.enableDelete);
    }

    public void setData(java.util.List p1)
    {
        this.data = p1;
        return;
    }

    public void setEnableDelete(boolean p1)
    {
        this.enableDelete = p1;
        return;
    }

    public void setHighlightSelected(boolean p1)
    {
        this.highlightSelected = p1;
        return;
    }

    public void setSelectedServerID(int p1)
    {
        this.selectedServerID = p1;
        return;
    }
}
