package com.bisimplex.firebooru.dataadapter;
public class GridPostDataAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private boolean attempFixURLs;
    private android.content.Context context;
    protected java.util.ArrayList data;
    private com.bisimplex.firebooru.custom.ThumbDisplayMode displayMode;
    protected com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener mListener;
    private final com.bumptech.glide.request.RequestListener thumbDrawableRequestListener;

    public GridPostDataAdapter(android.content.Context p2, com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener p3, com.bisimplex.firebooru.custom.ThumbDisplayMode p4)
    {
        this.thumbDrawableRequestListener = new com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$1(this);
        this.context = p2;
        this.mListener = p3;
        this.data = new java.util.ArrayList();
        this.setDisplayMode(p4);
        return;
    }

    private void recycleHolder(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder p3)
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

    public void addItems(java.util.List p6)
    {
        if ((p6 != 0) && (!p6.isEmpty())) {
            int v0_2 = this.data.size();
            int v1_1 = p6.iterator();
            while (v1_1.hasNext()) {
                this.data.add(new com.bisimplex.firebooru.model.GridPostItem(((com.bisimplex.firebooru.danbooru.DanbooruPost) v1_1.next())));
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
            com.bisimplex.firebooru.model.GridPostItem v2 = this.getItem(0);
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

    public com.bisimplex.firebooru.custom.ThumbDisplayMode getDisplayMode()
    {
        return this.displayMode;
    }

    public com.bisimplex.firebooru.model.GridPostItem getItem(int p2)
    {
        if ((p2 >= null) && (p2 < this.data.size())) {
            return ((com.bisimplex.firebooru.model.GridPostItem) this.data.get(p2));
        } else {
            return 0;
        }
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public int getItemViewType(int p1)
    {
        return this.getItem(p1).getType();
    }

    protected com.bumptech.glide.request.RequestListener getThumbDrawableRequestListener()
    {
        if (!this.attempFixURLs) {
            return 0;
        } else {
            return this.thumbDrawableRequestListener;
        }
    }

    public boolean hasSource()
    {
        if (this.data.size() != 0) {
            if (((com.bisimplex.firebooru.model.GridPostItem) this.data.get(0)).getType() != 1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder p6, int p7)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_0 = this.getItem(p7);
        if (v0_0 != null) {
            int v3 = 0;
            if (v0_0.getType() != 1) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = v0_0.getPost();
                if (v0_1 != null) {
                    if (v0_1.getPreview() != null) {
                        com.bumptech.glide.Glide.with(this.context).clear(p6.imageView);
                        p6.imageView.setTag(2131362637, Integer.valueOf(p7));
                        int v7_3 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPostPreview(0, v0_1);
                        if (v7_3 != 0) {
                            com.bumptech.glide.Glide.with(this.context).load(v7_3).apply(com.bisimplex.firebooru.network.Utils.getInstance().getThumbsOptions()).transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade()).addListener(this.getThumbDrawableRequestListener()).into(p6.imageView);
                        }
                        int v7_8 = v0_1.getVisibleVersion();
                        if (v7_8 == 0) {
                            p6.animatedImageView.setVisibility(8);
                        } else {
                            int v7_11;
                            if (!v7_8.isAnimated()) {
                                v7_11 = 8;
                            } else {
                                v7_11 = 0;
                            }
                            p6.animatedImageView.setVisibility(v7_11);
                        }
                        boolean v1_15;
                        if (!v0_1.isFavorite()) {
                            v1_15 = 8;
                        } else {
                            v1_15 = 0;
                        }
                        p6.heartImageView.setVisibility(v1_15);
                        if (p6.blacklistedTextView != null) {
                            if (!v0_1.isBlacklisted()) {
                                v3 = 8;
                            }
                            p6.blacklistedTextView.setVisibility(v3);
                        }
                        int v7_16;
                        if (!v0_1.isBlacklisted()) {
                            v7_16 = 1065353216;
                        } else {
                            v7_16 = 1045220557;
                        }
                        p6.imageView.setAlpha(v7_16);
                        return;
                    } else {
                        android.util.Log.e("idanbooru", "preview url is null");
                        return;
                    }
                }
            } else {
                p6.textView.setText(v0_0.getLabel());
                if (android.text.TextUtils.isEmpty(v0_0.getLabel())) {
                    v3 = 8;
                }
                p6.textView.setVisibility(v3);
                return;
            }
        }
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder onCreateViewHolder(android.view.ViewGroup p4, int p5)
    {
        if (p5 != 1) {
            android.view.View v4_1;
            android.view.LayoutInflater v5_16 = com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$2.$SwitchMap$com$bisimplex$firebooru$custom$ThumbDisplayMode[this.getDisplayMode().ordinal()];
            if ((v5_16 == 1) || (v5_16 == 2)) {
                v4_1 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558495, p4, 0);
            } else {
                if (v5_16 == 3) {
                    v4_1 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558451, p4, 0);
                } else {
                    if (v5_16 == 4) {
                        v4_1 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558496, p4, 0);
                    } else {
                        v4_1 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558450, p4, 0);
                    }
                }
            }
            return new com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder(v4_1, this.mListener);
        } else {
            return new com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558640, p4, 0), this.mListener);
        }
    }

    public bridge synthetic void onViewAttachedToWindow(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewAttachedToWindow(((com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder) p1));
        return;
    }

    public void onViewAttachedToWindow(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder p3)
    {
        super.onViewAttachedToWindow(p3);
        int v0 = p3.getBindingAdapterPosition();
        if (p3.mListener != null) {
            p3.mListener.attachedToWindow(v0);
        }
        return;
    }

    public bridge synthetic void onViewDetachedFromWindow(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewDetachedFromWindow(((com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder) p1));
        return;
    }

    public void onViewDetachedFromWindow(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder p1)
    {
        super.onViewDetachedFromWindow(p1);
        return;
    }

    public bridge synthetic void onViewRecycled(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewRecycled(((com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder) p1));
        return;
    }

    public void onViewRecycled(com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$PostItemHolder p1)
    {
        super.onViewRecycled(p1);
        this.recycleHolder(p1);
        return;
    }

    public void setAttempFixURLs(boolean p1)
    {
        this.attempFixURLs = p1;
        return;
    }

    public void setDisplayMode(com.bisimplex.firebooru.custom.ThumbDisplayMode p1)
    {
        this.displayMode = p1;
        return;
    }

    public void setItems(java.util.List p4)
    {
        this.data.clear();
        if ((p4 != null) && (!p4.isEmpty())) {
            java.util.Iterator v4_1 = p4.iterator();
            while (v4_1.hasNext()) {
                this.data.add(new com.bisimplex.firebooru.model.GridPostItem(((com.bisimplex.firebooru.danbooru.DanbooruPost) v4_1.next())));
            }
        }
        this.notifyDataSetChanged();
        return;
    }

    public void setSource(String p5)
    {
        if (this.data.size() != 0) {
            java.util.ArrayList v0_6 = ((com.bisimplex.firebooru.model.GridPostItem) this.data.get(0));
            if (v0_6.getType() != 1) {
                this.data.add(0, new com.bisimplex.firebooru.model.GridPostItem(p5));
                this.notifyItemInserted(0);
                return;
            } else {
                v0_6.setLabel(p5);
                this.notifyItemChanged(0);
                return;
            }
        } else {
            this.data.add(new com.bisimplex.firebooru.model.GridPostItem(p5));
            this.notifyItemInserted(0);
            return;
        }
    }
}
