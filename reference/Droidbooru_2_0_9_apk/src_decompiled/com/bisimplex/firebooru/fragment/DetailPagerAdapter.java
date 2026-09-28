package com.bisimplex.firebooru.fragment;
public class DetailPagerAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private static final int BLACKLISTED_TYPE = 2;
    private static final int IMAGE_TYPE = 0;
    public static final String PageStatusChange = "PageStatusChange";
    public static final String PageStatusChangeEvent = "PageStatusChangeEvent";
    public static final String PageStatusChangeIndex = "PageStatusChangeIndex";
    public static final String PageStatusChangeSeekIndex = "PageStatusChangeSeekIndex";
    private static final int VIDEO_TYPE = 1;
    private com.bisimplex.firebooru.view.IVideoView __sharedVideoView;
    private final boolean _addToHistory;
    private final android.content.Context context;
    private final java.util.List data;
    private final com.bumptech.glide.request.RequestListener drawableRequestListener;
    private final com.bumptech.glide.request.RequestListener fileRequestListener;
    private final android.os.Handler handler;
    private final com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener listener;
    private final com.bisimplex.firebooru.network.SourcePostBasic source;
    private final com.bumptech.glide.request.RequestListener thumbDrawableRequestListener;
    private int videoControlBottomMargin;

    static bridge synthetic com.bisimplex.firebooru.view.IVideoView -$$Nest$fget__sharedVideoView(com.bisimplex.firebooru.fragment.DetailPagerAdapter p0)
    {
        return p0.__sharedVideoView;
    }

    static bridge synthetic android.content.Context -$$Nest$fgetcontext(com.bisimplex.firebooru.fragment.DetailPagerAdapter p0)
    {
        return p0.context;
    }

    static bridge synthetic com.bisimplex.firebooru.network.SourcePostBasic -$$Nest$fgetsource(com.bisimplex.firebooru.fragment.DetailPagerAdapter p0)
    {
        return p0.source;
    }

    static bridge synthetic int -$$Nest$fgetvideoControlBottomMargin(com.bisimplex.firebooru.fragment.DetailPagerAdapter p0)
    {
        return p0.videoControlBottomMargin;
    }

    public DetailPagerAdapter(android.content.Context p3, com.bisimplex.firebooru.network.SourcePostBasic p4, com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener p5)
    {
        this.drawableRequestListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$1(this);
        this.handler = new android.os.Handler(android.os.Looper.getMainLooper());
        this.fileRequestListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$2(this);
        this.thumbDrawableRequestListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$3(this);
        this.context = p3;
        this.listener = p5;
        this.source = p4;
        this._addToHistory = (p4 instanceof com.bisimplex.firebooru.network.SourcePost);
        this.data = new java.util.ArrayList(0);
        this.addData(p4.getData());
        return;
    }

    private int findPostIndex(com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        int v0 = 0;
        while (v0 < this.data.size()) {
            if (!p4.getPostId().equalsIgnoreCase(((com.bisimplex.firebooru.data.DanbooruPostPage) this.data.get(v0)).getPost().getPostId())) {
                v0++;
            } else {
                return v0;
            }
        }
        return -1;
    }

    private boolean isVideoViewAttached()
    {
        int v0_0 = this.__sharedVideoView;
        if (v0_0 != 0) {
            if ((!v0_0.isAttachedToWindow()) && (!this.__sharedVideoView.isPlaying())) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void addData(java.util.List p7)
    {
        if ((p7 != 0) && (!p7.isEmpty())) {
            int v0_2 = this.data.size();
            boolean v1_0 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isAutoLoadNotes();
            java.util.Iterator v2 = p7.iterator();
            while (v2.hasNext()) {
                this.data.add(new com.bisimplex.firebooru.data.DanbooruPostPage(((com.bisimplex.firebooru.danbooru.DanbooruPost) v2.next()), v1_0));
            }
            this.notifyItemRangeInserted(v0_2, p7.size());
        }
        return;
    }

    public void attachVideoView(com.bisimplex.firebooru.view.IVideoView p2, android.widget.FrameLayout p3, org.videolan.libvlc.MediaPlayer$EventListener p4, androidx.media3.common.Player$Listener p5, com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener p6)
    {
        if ((p2 != null) && (p3 != null)) {
            this.detachVideoView(p2);
            if (!(p2 instanceof com.bisimplex.firebooru.view.ExoVideoView)) {
                if (!(p2 instanceof com.bisimplex.firebooru.view.VLCVideoView)) {
                    if ((p2 instanceof com.bisimplex.firebooru.view.ChromeVideoView)) {
                        ((com.bisimplex.firebooru.view.ChromeVideoView) p2).addListener(p6);
                    }
                } else {
                    ((com.bisimplex.firebooru.view.VLCVideoView) p2).addListener(p4);
                }
            } else {
                ((com.bisimplex.firebooru.view.ExoVideoView) p2).addListener(p5);
            }
            p3.addView(p2.getAsLayout(), new android.widget.FrameLayout$LayoutParams(-1, -1));
        }
        return;
    }

    public void detachVideoView(com.bisimplex.firebooru.view.IVideoView p1)
    {
        if (p1 != null) {
            p1.reset();
            android.view.ViewGroup v1_2 = ((android.view.ViewGroup) p1.getParent());
            if (v1_2 != null) {
                v1_2.removeAllViews();
            }
        }
        return;
    }

    protected com.bumptech.glide.request.RequestListener getDrawableRequestListener()
    {
        if (!(this.source instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
            return 0;
        } else {
            return this.drawableRequestListener;
        }
    }

    protected com.bumptech.glide.request.RequestListener getFileRequestListener()
    {
        if (!(this.source instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
            return 0;
        } else {
            return this.fileRequestListener;
        }
    }

    public com.bisimplex.firebooru.data.DanbooruPostPage getItem(int p2)
    {
        if (p2 >= null) {
            return ((com.bisimplex.firebooru.data.DanbooruPostPage) this.data.get(p2));
        } else {
            return 0;
        }
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public int getItemViewType(int p2)
    {
        int v2_7 = ((com.bisimplex.firebooru.data.DanbooruPostPage) this.data.get(p2));
        if (!v2_7.getPost().isBlacklisted()) {
            int v2_2 = v2_7.getPost().getVisibleVersion();
            if (v2_2 != 0) {
                if (!v2_2.isVideo()) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            return 2;
        }
    }

    public com.bisimplex.firebooru.view.IVideoView getSharedVideoView()
    {
        if (this.__sharedVideoView == null) {
            com.bisimplex.firebooru.view.ExoVideoView v0_15 = com.bisimplex.firebooru.fragment.DetailPagerAdapter$4.$SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType[com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getVideoDecoder().ordinal()];
            if (v0_15 == 1) {
                if (androidx.webkit.WebViewCompat.getCurrentWebViewPackage(this.context) != null) {
                    this.__sharedVideoView = new com.bisimplex.firebooru.view.ChromeVideoView(this.context);
                } else {
                    this.__sharedVideoView = new com.bisimplex.firebooru.view.NoChromeVideoView(this.context);
                }
            } else {
                if (v0_15 == 2) {
                    this.__sharedVideoView = new com.bisimplex.firebooru.view.VLCVideoView(this.context);
                } else {
                    if (v0_15 == 3) {
                        this.__sharedVideoView = new com.bisimplex.firebooru.view.ExoVideoView(this.context);
                    }
                }
            }
        }
        return this.__sharedVideoView;
    }

    protected com.bumptech.glide.request.RequestListener getThumbDrawableRequestListener()
    {
        if (!(this.source instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
            return 0;
        } else {
            return this.thumbDrawableRequestListener;
        }
    }

    public int getVideoControlBottomMargin()
    {
        return this.videoControlBottomMargin;
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder p13, int p14)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType v0_11 = ((com.bisimplex.firebooru.data.DanbooruPostPage) this.data.get(p14));
        if (!v0_11.getPost().isBlacklisted()) {
            com.bumptech.glide.Glide.with(this.context).clear(p13.thumbImageView);
            p13.thumbImageView.setVisibility(0);
            int v1_6 = this.source.getProvider(v0_11.getPost());
            String v3_2 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPostPreview(v1_6, v0_11.getPost());
            if (v3_2 != null) {
                com.bumptech.glide.Glide.with(this.context).load(v3_2).apply(com.bisimplex.firebooru.network.Utils.getInstance().getThumbsOptions()).addListener(this.getThumbDrawableRequestListener()).into(p13.thumbImageView);
            }
            String v3_8 = v0_11.getPost().getVisibleVersion().getUrl();
            int v1_8 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPost(v1_6, v0_11.getPost());
            if (v1_8 != 0) {
                p13.loading_bottom.clearAnimation();
                p13.loading_bottom.setProgress(0);
                if (!v0_11.isReady()) {
                    p13.loading_bottom.setVisibility(0);
                } else {
                    p13.loading_bottom.setVisibility(8);
                }
                boolean v2_9 = ((com.bisimplex.firebooru.custom.DownloadTarget) com.bisimplex.firebooru.custom.MyGlideModule.find(v3_8));
                if (v2_9) {
                    if (!(p13 instanceof com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder)) {
                        ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13).imageView.setListener(this.listener);
                        ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13).imageView.setImageDrawable(0);
                        v2_9.setListener(com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder.-$$Nest$fgetdrawableTargetListener(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13)));
                        return;
                    } else {
                        ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).resetTimerLabels();
                        v2_9.setListener(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$fgetfileDownloadTargetListener(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13)));
                        return;
                    }
                } else {
                    if (!(p13 instanceof com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder)) {
                        if ((p13 instanceof com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder)) {
                            ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13).imageView.setImageDrawable(0);
                            ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13).imageView.setListener(this.listener);
                            String v14_4 = new com.bisimplex.firebooru.custom.DownloadTarget(com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder.-$$Nest$fgetdrawableTargetListener(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p13)), v3_8, v0_11.getPost(), this._addToHistory);
                            com.bisimplex.firebooru.custom.MyGlideModule.expect(v3_8, v14_4);
                            com.bumptech.glide.Glide.with(this.context).load(v1_8).apply(com.bisimplex.firebooru.network.Utils.getInstance().getImageOptions()).addListener(this.getDrawableRequestListener()).into(v14_4);
                        }
                    } else {
                        com.bisimplex.firebooru.view.IVideoView v7 = this.getSharedVideoView();
                        ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).resetTimerLabels();
                        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()) {
                            me.zhanghai.android.materialprogressbar.MaterialProgressBar v13_9 = new com.bisimplex.firebooru.custom.DownloadTarget(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$fgetfileDownloadTargetListener(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13)), v3_8, v0_11.getPost(), this._addToHistory);
                            com.bisimplex.firebooru.custom.MyGlideModule.expect(v3_8, v13_9);
                            com.bumptech.glide.Glide.with(this.context).asFile().load(v1_8).addListener(this.getFileRequestListener()).into(v13_9);
                            return;
                        } else {
                            if (this.source.getVisiblePostIndex() == p14) {
                                this.attachVideoView(v7, ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).videoViewContainer, ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).eventListener, ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).playerListener, ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder) p13).videoListener);
                                v7.setTag(2131362701, String.valueOf(p14));
                                v7.setURL(v3_8, v0_11.getPost().getPostUrl(), v0_11.getPost().getVisibleVersion().getContentType());
                                v7.start();
                            }
                            p13.loading_bottom.setVisibility(8);
                            return;
                        }
                    }
                }
            }
            return;
        } else {
            if (!android.text.TextUtils.isEmpty(v0_11.getPost().getBlacklistRule())) {
                ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$BlacklistedPagerHolder) p13).ruleTextView.setText(v0_11.getPost().getBlacklistRule());
                return;
            } else {
                ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$BlacklistedPagerHolder) p13).ruleTextView.setText(2131886984);
                return;
            }
        }
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        if (p4 != null) {
            if (p4 != 2) {
                return new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder(this, android.view.LayoutInflater.from(this.context).inflate(2131558609, p3, 0), this.listener);
            } else {
                return new com.bisimplex.firebooru.fragment.DetailPagerAdapter$BlacklistedPagerHolder(this, android.view.LayoutInflater.from(this.context).inflate(2131558607, p3, 0), this.listener);
            }
        } else {
            return new com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder(this, android.view.LayoutInflater.from(this.context).inflate(2131558608, p3, 0), this.listener);
        }
    }

    public bridge synthetic void onViewRecycled(androidx.recyclerview.widget.RecyclerView$ViewHolder p1)
    {
        this.onViewRecycled(((com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder) p1));
        return;
    }

    public void onViewRecycled(com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder p2)
    {
        super.onViewRecycled(p2);
        if ((p2 instanceof com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder)) {
            ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p2).imageView.cleanup();
            ((com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder) p2).thumbImageView.setImageDrawable(0);
        }
        return;
    }

    public void resetVideoView()
    {
        this.detachVideoView(this.__sharedVideoView);
        int v0_1 = this.__sharedVideoView;
        if (v0_1 != 0) {
            v0_1.release();
            this.__sharedVideoView = 0;
        }
        return;
    }

    public void setItem(com.bisimplex.firebooru.danbooru.DanbooruPost p4, int p5)
    {
        this.data.set(p5, new com.bisimplex.firebooru.data.DanbooruPostPage(p4, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isAutoLoadNotes()));
        return;
    }

    public void setVideoControlBottomMargin(int p1)
    {
        this.videoControlBottomMargin = p1;
        return;
    }
}
