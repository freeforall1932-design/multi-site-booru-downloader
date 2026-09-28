package com.bisimplex.firebooru.fragment;
public class ServerListDataAdapter$ItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder implements android.view.View$OnClickListener {
    com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick listener;
    android.widget.TextView textViewDescription;
    android.widget.TextView textViewUrl;

    public ServerListDataAdapter$ItemHolder(android.view.View p2, com.bisimplex.firebooru.fragment.ServerListDataAdapter$ListItemClick p3, boolean p4)
    {
        super(p2);
        super.listener = p3;
        super.textViewUrl = ((android.widget.TextView) p2.findViewById(2131362621));
        super.textViewDescription = ((android.widget.TextView) p2.findViewById(2131362620));
        p2.setOnClickListener(super);
        if (p4 != null) {
            p2.setOnCreateContextMenuListener(new com.bisimplex.firebooru.fragment.ServerListDataAdapter$ItemHolder$1(super, p3));
        }
        return;
    }

    public void onClick(android.view.View p2)
    {
        p2 = this.listener;
        if (p2 != null) {
            p2.onListItemClick(this.getBindingAdapterPosition());
        }
        return;
    }
}
