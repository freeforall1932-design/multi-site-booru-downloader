package com.bisimplex.firebooru.dataadapter;
public class SortPinAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private android.content.Context context;
    private com.bisimplex.firebooru.model.SourceSpecs currentFolder;
    private java.util.List data;
    private com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener sortPinItemListener;

    public SortPinAdapter(android.content.Context p2, com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener p3)
    {
        this.context = p2;
        this.data = new java.util.ArrayList(0);
        this.sortPinItemListener = p3;
        return;
    }

    public void addItem(com.bisimplex.firebooru.model.SourceSpecs p3)
    {
        int v0_1 = this.data.size();
        this.data.add(p3);
        this.notifyItemInserted(v0_1);
        return;
    }

    public void deleteItem(int p2)
    {
        this.data.remove(p2);
        this.notifyItemRemoved(p2);
        return;
    }

    public java.util.List getData()
    {
        return this.data;
    }

    public java.util.List getFolders()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        java.util.Iterator v1_1 = this.data.iterator();
        while (v1_1.hasNext()) {
            com.bisimplex.firebooru.model.SourceSpecs v2_1 = ((com.bisimplex.firebooru.model.SourceSpecs) v1_1.next());
            if (v2_1.getType() == 4) {
                v0_1.add(v2_1);
            }
        }
        return v0_1;
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public void move(int p3, int p4)
    {
        this.data.add(p4, ((com.bisimplex.firebooru.model.SourceSpecs) this.data.remove(p3)));
        this.notifyItemMoved(p3, p4);
        return;
    }

    public void moveBottom(int p3)
    {
        int v0_1 = this.data.size();
        if (v0_1 > 1) {
            int v0_2 = (v0_1 - 1);
            if (p3 != v0_2) {
                this.move(p3, v0_2);
                return;
            }
        }
        return;
    }

    public void moveTop(int p2)
    {
        if (p2 != 0) {
            this.move(p2, 0);
            return;
        } else {
            return;
        }
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.SortPinItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p7, int p8)
    {
        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_14 = ((com.bisimplex.firebooru.model.SourceSpecs) this.data.get(p8));
        if (v8_14.getQuery() != null) {
            if (!android.text.TextUtils.isEmpty(v8_14.getQuery().getText())) {
                p7.titleTextView.setText(v8_14.getQuery().getText());
            } else {
                p7.titleTextView.setText(this.context.getString(2131886143));
            }
        } else {
            p7.titleTextView.setText(2131886984);
        }
        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_7;
        p7.folderButton.setVisibility(8);
        p7.moveButton.setVisibility(0);
        String v5 = "";
        if (v8_14.getType() != 3) {
            if (v8_14.getType() != 1) {
                if (v8_14.getType() != 2) {
                    if (v8_14.getType() != 4) {
                        v8_7 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_thumbtack;
                        p7.folderButton.setVisibility(8);
                        p7.moveButton.setVisibility(8);
                    } else {
                        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_18;
                        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_15 = v8_14.getChildCount();
                        if (v8_15 != null) {
                            if (v8_15 != 1) {
                                v8_18 = this.context.getString(2131886965, new Object[] {Integer.valueOf(v8_15)}));
                            } else {
                                v8_18 = this.context.getString(2131887000);
                            }
                        } else {
                            v8_18 = this.context.getString(2131886730);
                        }
                        v5 = v8_18;
                        v8_7 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_folder;
                        p7.folderButton.setVisibility(0);
                        p7.moveButton.setVisibility(8);
                    }
                } else {
                    v5 = this.context.getString(2131886856);
                    v8_7 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history;
                }
            } else {
                if ((v8_14.getQuery() != null) && (v8_14.getQuery().getExtraParams() != null)) {
                    com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_2 = ((String) v8_14.getQuery().getExtraParams().get("FILTER_SERVER_URL"));
                    if (!android.text.TextUtils.isEmpty(v8_2)) {
                        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v8_4 = this.context.getString(2131886581, new Object[] {v8_2}));
                    } else {
                        v8_4 = this.context.getString(2131886853);
                    }
                } else {
                    v8_4 = this.context.getString(2131886984);
                }
                v5 = v8_4;
                v8_7 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_thumbtack;
            }
        } else {
            if (v8_14.getServer() != null) {
                v5 = v8_14.getServer().getServerName();
            }
            if (android.text.TextUtils.isEmpty(v5)) {
                v5 = this.context.getString(2131886984);
            }
            v8_7 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_thumbtack;
        }
        p7.descriptionTextView.setText(v5);
        p7.iconImageView.setIcon(com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.context, v8_7, 2131099701));
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.SortPinItemHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        return new com.bisimplex.firebooru.dataadapter.SortPinItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(2131558508, p3, 0), this.sortPinItemListener);
    }

    public void removeAndReload(int p2, com.bisimplex.firebooru.model.SourceSpecs p3)
    {
        this.data.remove(p2);
        this.notifyItemRemoved(p2);
        int v2_2 = this.data.indexOf(p3);
        if (v2_2 >= 0) {
            this.notifyItemChanged(v2_2);
        }
        return;
    }

    public void setData(java.util.List p1)
    {
        this.data = p1;
        this.notifyDataSetChanged();
        return;
    }

    public void sortAlphabetically(boolean p3)
    {
        int v0_0 = this.getData();
        if (!v0_0.isEmpty()) {
            java.util.Collections.sort(v0_0, new com.bisimplex.firebooru.dataadapter.SortPinAdapter$1(this));
            if (p3 != 0) {
                java.util.Collections.reverse(v0_0);
            }
            this.notifyItemRangeChanged(0, v0_0.size());
            return;
        } else {
            return;
        }
    }

    public void swap(int p2, int p3)
    {
        java.util.Collections.swap(this.data, p2, p3);
        this.notifyItemMoved(p2, p3);
        return;
    }
}
