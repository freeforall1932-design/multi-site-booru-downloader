package com.bisimplex.firebooru.fragment;
public class MiniServerListDataAdapter extends com.bisimplex.firebooru.fragment.ServerListDataAdapter {

    public MiniServerListDataAdapter(android.content.Context p1, com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick p2)
    {
        super(p1, p2);
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder onCreateViewHolder(android.view.ViewGroup p4, int p5)
    {
        return new com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder(android.view.LayoutInflater.from(this.context).inflate(2131558636, p4, 0), this.listener, this.enableDelete);
    }
}
