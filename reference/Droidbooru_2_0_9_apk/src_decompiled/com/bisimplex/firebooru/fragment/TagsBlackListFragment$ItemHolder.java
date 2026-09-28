package com.bisimplex.firebooru.fragment;
public class TagsBlackListFragment$ItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder implements android.view.View$OnClickListener, android.view.View$OnLongClickListener {
    com.mikepenz.iconics.view.IconicsImageView deleteButton;
    com.bisimplex.firebooru.fragment.TagsBlackListFragment$ListItemClick listener;
    android.widget.TextView row_title;
    android.widget.TextView site_title;

    public TagsBlackListFragment$ItemHolder(android.view.View p2, com.bisimplex.firebooru.fragment.TagsBlackListFragment$ListItemClick p3)
    {
        super(p2);
        super.listener = p3;
        super.row_title = ((android.widget.TextView) p2.findViewById(2131362479));
        super.site_title = ((android.widget.TextView) p2.findViewById(2131362533));
        com.mikepenz.iconics.view.IconicsImageView v3_4 = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131361985));
        super.deleteButton = v3_4;
        v3_4.setOnClickListener(new com.bisimplex.firebooru.fragment.TagsBlackListFragment$ItemHolder$1(super));
        p2.setOnClickListener(super);
        return;
    }

    public void onClick(android.view.View p2)
    {
        this.listener.onListItemClick(this.getBindingAdapterPosition());
        return;
    }

    public boolean onLongClick(android.view.View p2)
    {
        this.listener.onListItemLongClick(this.getBindingAdapterPosition());
        return 1;
    }
}
