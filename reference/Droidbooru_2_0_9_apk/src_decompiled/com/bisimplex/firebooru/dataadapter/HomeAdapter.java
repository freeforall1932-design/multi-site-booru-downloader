package com.bisimplex.firebooru.dataadapter;
public class HomeAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter implements com.bisimplex.firebooru.network.SourceListener {
    private static final String SOURCE_POSITION = "SOURCE_POSITION";
    private boolean allowEdition;
    private android.content.Context context;
    private java.util.List data;
    private final android.os.Handler handler;
    private ref.WeakReference itemListenerWeakReference;

    static bridge synthetic ref.WeakReference -$$Nest$fgetitemListenerWeakReference(com.bisimplex.firebooru.dataadapter.HomeAdapter p0)
    {
        return p0.itemListenerWeakReference;
    }

    static bridge synthetic void -$$Nest$mpostAndNotifyAdapterAtPosition(com.bisimplex.firebooru.dataadapter.HomeAdapter p0, int p1)
    {
        p0.postAndNotifyAdapterAtPosition(p1);
        return;
    }

    public HomeAdapter(android.content.Context p2, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p3)
    {
        this.allowEdition = 1;
        this.handler = new android.os.Handler();
        this.data = new java.util.ArrayList();
        this.context = p2;
        this.itemListenerWeakReference = new ref.WeakReference(p3);
        return;
    }

    private void postAndNotifyAdapterAtPosition(int p3)
    {
        this.handler.post(new com.bisimplex.firebooru.dataadapter.HomeAdapter$1(this, p3));
        return;
    }

    public void addItem(com.bisimplex.firebooru.data.HomeItem p3)
    {
        if (p3 != null) {
            int v0_1 = this.data.size();
            this.data.add(p3);
            this.notifyItemInserted(v0_1);
            return;
        } else {
            return;
        }
    }

    public void addItems(java.util.List p3)
    {
        if ((p3 != 0) && (!p3.isEmpty())) {
            int v0_2 = this.data.size();
            this.data.addAll(p3);
            this.notifyItemRangeInserted(v0_2, p3.size());
        }
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p2, com.bisimplex.firebooru.data.FailureType p3)
    {
        int v3_4 = ((String) p2.getQuery().getExtraParams().get("SOURCE_POSITION"));
        if (!android.text.TextUtils.isEmpty(v3_4)) {
            int v3_1 = Integer.parseInt(v3_4);
            p2.block();
            this.postAndNotifyAdapterAtPosition(v3_1);
            return;
        } else {
            return;
        }
    }

    public com.bisimplex.firebooru.data.HomeItem getItem(int p2)
    {
        return ((com.bisimplex.firebooru.data.HomeItem) this.data.get(p2));
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public int getItemViewType(int p2)
    {
        return ((com.bisimplex.firebooru.data.HomeItem) this.data.get(p2)).getType();
    }

    public boolean isAllowEdition()
    {
        return this.allowEdition;
    }

    public void move(int p3, int p4)
    {
        this.data.add(p4, ((com.bisimplex.firebooru.data.HomeItem) this.data.remove(p3)));
        this.notifyItemMoved(p3, p4);
        return;
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.HomeItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.HomeItemHolder p18, int p19)
    {
        android.widget.ImageView v1_20 = ((com.bisimplex.firebooru.data.HomeItem) this.data.get(p19));
        String v15_1 = "";
        if (v1_20.getType() != 1) {
            if (v1_20.getType() != 4) {
                if (v1_20.getType() != 2) {
                    if (v1_20.getType() == 3) {
                        ((com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder) p18).titleTextView.setText(v1_20.getTitle());
                        ((com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder) p18).menuButton.setVisibility(8);
                    }
                    return;
                } else {
                    android.widget.ImageView v1_14 = v1_20.getSpecs();
                    ((com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder) p18).titleTextView.setText(v1_14.getQuery().getTitle());
                    android.widget.ImageView v1_21 = v1_14.getChildCount();
                    if (v1_21 != null) {
                        if (v1_21 != 1) {
                            ((com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder) p18).descriptionTextView.setText(this.context.getString(2131886277, new Object[] {Integer.valueOf(v1_21)})));
                        } else {
                            ((com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder) p18).descriptionTextView.setText(2131886278);
                        }
                    } else {
                        ((com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder) p18).descriptionTextView.setText(2131886730);
                    }
                    ((com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder) p18).menuButton.setVisibility(8);
                    return;
                }
            } else {
                String v3_4;
                android.widget.ImageView v1_28 = v1_20.getSpecs();
                if (v1_28.getType() != 3) {
                    if (v1_28.getType() != 1) {
                        if (v1_28.getType() != 2) {
                            if (v1_28.getQuery() != null) {
                                v3_4 = v1_28.getQuery().getText();
                            } else {
                                v3_4 = this.context.getString(2131886984);
                            }
                        } else {
                            if (v1_28.getQuery() != null) {
                                if (!android.text.TextUtils.isEmpty(v1_28.getQuery().getText())) {
                                    v3_4 = this.context.getString(2131886713, new Object[] {v1_28.getQuery().getText()}));
                                } else {
                                    v3_4 = this.context.getString(2131886856);
                                }
                            } else {
                                v3_4 = this.context.getString(2131886984);
                            }
                        }
                    } else {
                        if (v1_28.getQuery() != null) {
                            if (!android.text.TextUtils.isEmpty(v1_28.getQuery().getText())) {
                                v3_4 = this.context.getString(2131886712, new Object[] {v1_28.getQuery().getText()}));
                            } else {
                                v3_4 = this.context.getString(2131886853);
                            }
                            v15_1 = ((String) v1_28.getQuery().getExtraParams().get("FILTER_SERVER_URL"));
                            if (android.text.TextUtils.isEmpty(v15_1)) {
                                v15_1 = this.context.getString(2131886145);
                            }
                        } else {
                            v15_1 = this.context.getString(2131886984);
                            v3_4 = v15_1;
                        }
                    }
                } else {
                    if (v1_28.getQuery() != null) {
                        if ((!android.text.TextUtils.isEmpty(v1_28.getQuery().getText())) && (!"*".equalsIgnoreCase(v1_28.getQuery().getText()))) {
                            v3_4 = v1_28.getQuery().getText();
                        } else {
                            v3_4 = this.context.getString(2131886143);
                        }
                    } else {
                        v3_4 = this.context.getString(2131886984);
                    }
                    if (v1_28.getProvider() != null) {
                        if (!v1_28.getProvider().getServerDescription().isDefault()) {
                            v15_1 = v1_28.getProvider().getServerDescription().getUrl();
                        } else {
                            v15_1 = this.context.getString(2131886382);
                        }
                    } else {
                        v15_1 = this.context.getString(2131886984);
                    }
                }
                ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).nameTextView.setText(v15_1);
                ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).titleTextView.setText(v3_4);
                if ((v1_28.getQuery() != null) && (v1_28.getProvider() != null)) {
                    ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).searchButton.setVisibility(0);
                    ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).expandButton.setVisibility(0);
                    return;
                } else {
                    ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).searchButton.setVisibility(8);
                    ((com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder) p18).expandButton.setVisibility(8);
                    return;
                }
            }
        } else {
            android.widget.ImageView v4_23;
            com.bisimplex.firebooru.model.SourceSpecs v16 = v1_20.getSpecs();
            if (v16.getType() != 3) {
                if (v16.getType() != 1) {
                    if (v16.getType() != 2) {
                        v4_23 = v16.getQuery().getText();
                    } else {
                        if (!android.text.TextUtils.isEmpty(v16.getQuery().getText())) {
                            v4_23 = this.context.getString(2131886713, new Object[] {v16.getQuery().getText()}));
                        } else {
                            v4_23 = this.context.getString(2131886856);
                        }
                    }
                } else {
                    if (!android.text.TextUtils.isEmpty(v16.getQuery().getText())) {
                        v4_23 = this.context.getString(2131886712, new Object[] {v16.getQuery().getText()}));
                    } else {
                        v4_23 = this.context.getString(2131886853);
                    }
                }
            } else {
                if ((!android.text.TextUtils.isEmpty(v16.getQuery().getText())) && (!"*".equalsIgnoreCase(v16.getQuery().getText()))) {
                    v4_23 = v16.getQuery().getText();
                } else {
                    v4_23 = this.context.getString(2131886143);
                }
            }
            ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).titleTextView.setText(v4_23);
            android.widget.ImageView v1_6 = v1_20.getSource();
            ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setVisibility(0);
            ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).reloadButton.setVisibility(0);
            if (v1_6 == null) {
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).nameTextView.setText("");
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).menuButton.setVisibility(8);
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setVisibility(8);
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).progressBar.setVisibility(8);
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).sourceKey = "";
                return;
            } else {
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).sourceKey = v1_6.getKey();
                if (v1_6.getType() != com.bisimplex.firebooru.network.SourceType.Post) {
                    if (v1_6.getType() != com.bisimplex.firebooru.network.SourceType.Favorites) {
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).nameTextView.setText("");
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setVisibility(8);
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).menuButton.setVisibility(8);
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).reloadButton.setVisibility(8);
                    } else {
                        android.widget.ImageView v4_54 = ((String) v1_6.getQuery().getExtraParams().get("FILTER_SERVER_URL"));
                        if (android.text.TextUtils.isEmpty(v4_54)) {
                            v4_54 = this.context.getString(2131886145);
                        }
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).nameTextView.setText(v4_54);
                    }
                } else {
                    if (!v1_6.getProvider().getServerDescription().isDefault()) {
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).nameTextView.setText(v1_6.getProvider().getServerDescription().getUrl());
                    } else {
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).nameTextView.setText(this.context.getString(2131886382));
                    }
                }
                v1_6.getQuery().getExtraParams().put("SOURCE_POSITION", String.valueOf(p19));
                v1_6.setListener(this);
                int v2_7 = ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).getAdapter();
                if (v2_7 != 0) {
                    v2_7.clearItems();
                    v2_7.addItems(v1_6.getData());
                    v2_7.setAttempFixURLs((v1_6 instanceof com.bisimplex.firebooru.network.SourceFavorites));
                }
                if (!v1_6.isNewSearch()) {
                    int v2_9 = v1_6.getVisiblePostIndex();
                    if (v2_9 > 0) {
                        ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).recyclerView.scrollToPosition(v2_9);
                    }
                } else {
                    v1_6.loadAnotherPage();
                }
                android.widget.ImageView v4_67;
                if (!v1_6.getIsLoading()) {
                    v4_67 = 8;
                } else {
                    v4_67 = 0;
                }
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).progressBar.setVisibility(v4_67);
                android.widget.ImageView v1_15 = v1_6.getIsLoading();
                int v2_11 = (v1_15 ^ 1);
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).reloadButton.setEnabled(v2_11);
                ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setEnabled(v2_11);
                if (v1_15 != null) {
                    ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).reloadButton.setAlpha(1050253722);
                    ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setAlpha(1050253722);
                    return;
                } else {
                    ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).reloadButton.setAlpha(1065353216);
                    ((com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder) p18).searchButton.setAlpha(1065353216);
                    return;
                }
            }
        }
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.HomeItemHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        if (p4 != 1) {
            if (p4 != 4) {
                if (p4 != 2) {
                    if (p4 != 3) {
                        return new com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(2131558505, p3, 0), ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.itemListenerWeakReference.get()));
                    } else {
                        return new com.bisimplex.firebooru.dataadapter.TitleHomeItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(2131558513, p3, 0), ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.itemListenerWeakReference.get()));
                    }
                } else {
                    return new com.bisimplex.firebooru.dataadapter.GroupHomeItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(2131558512, p3, 0), ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.itemListenerWeakReference.get()));
                }
            } else {
                return new com.bisimplex.firebooru.dataadapter.SublistCollapsedHomeItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(2131558511, p3, 0), ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.itemListenerWeakReference.get()));
            }
        } else {
            com.bisimplex.firebooru.dataadapter.ButtonHomeItemHolder v4_20;
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThumbDisplayMode().isLarge()) {
                v4_20 = 2131558509;
            } else {
                v4_20 = 2131558510;
            }
            return new com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder(android.view.LayoutInflater.from(p3.getContext()).inflate(v4_20, p3, 0), ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.itemListenerWeakReference.get()), this.allowEdition);
        }
    }

    public void reloadVisible()
    {
        return;
    }

    public void removeItem(int p2)
    {
        this.data.remove(p2);
        this.notifyItemRemoved(p2);
        return;
    }

    public void removeItem(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            int v0_1 = 0;
            while (v0_1 < this.data.size()) {
                boolean v1_2 = ((com.bisimplex.firebooru.data.HomeItem) this.data.get(v0_1)).getSpecs();
                if ((!v1_2) || (!v1_2.getKey().equalsIgnoreCase(p3))) {
                    v0_1++;
                }
                if (v0_1 >= 0) {
                    this.removeItem(v0_1);
                }
            }
            v0_1 = -1;
        }
        return;
    }

    public void setAllowEdition(boolean p1)
    {
        this.allowEdition = p1;
        return;
    }

    public void setItems(java.util.List p3)
    {
        java.util.List v0_2 = this.data.size();
        this.data.clear();
        this.notifyItemRangeRemoved(0, v0_2);
        if ((p3 != 0) && (!p3.isEmpty())) {
            this.data.addAll(p3);
        }
        this.notifyItemRangeInserted(0, this.data.size());
        return;
    }

    public void success(com.bisimplex.firebooru.network.Source p1, java.util.List p2)
    {
        int v1_5 = ((String) p1.getQuery().getExtraParams().get("SOURCE_POSITION"));
        if (!android.text.TextUtils.isEmpty(v1_5)) {
            this.postAndNotifyAdapterAtPosition(Integer.parseInt(v1_5));
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

    public void updateItem(com.bisimplex.firebooru.model.SourceSpecs p5)
    {
        if ((p5 != null) && (!android.text.TextUtils.isEmpty(p5.getKey()))) {
            com.bisimplex.firebooru.data.HomeItem v0_3 = new com.bisimplex.firebooru.data.HomeItem(p5);
            int v1 = 0;
            while (v1 < this.data.size()) {
                boolean v2_3 = ((com.bisimplex.firebooru.data.HomeItem) this.data.get(v1));
                if ((v2_3.getSpecs() == null) || (!v2_3.getSpecs().getKey().equalsIgnoreCase(p5.getKey()))) {
                    v1++;
                }
                if (v1 >= 0) {
                    this.data.set(v1, v0_3);
                    this.notifyItemChanged(v1);
                }
            }
            v1 = -1;
        }
        return;
    }
}
