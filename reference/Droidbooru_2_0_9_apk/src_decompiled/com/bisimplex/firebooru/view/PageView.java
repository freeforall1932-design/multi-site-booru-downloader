package com.bisimplex.firebooru.view;
public class PageView extends android.widget.FrameLayout {
    private static int tickTime = 1000;
    private static int what;
    private boolean _isReady;
    private com.bisimplex.firebooru.view.IPageContentView contentView;
    private final com.bisimplex.firebooru.custom.DownloadTargetListener drawableTargetListener;
    private final com.bisimplex.firebooru.custom.DownloadTargetListener fileDownloadTargetListener;
    private boolean isLoading;
    private ref.WeakReference listener;
    private me.zhanghai.android.materialprogressbar.MaterialProgressBar loading_bottom;
    private android.os.Handler mHandler;
    private com.bisimplex.firebooru.danbooru.DanbooruPost targetPost;
    private androidx.appcompat.widget.AppCompatImageView thumbImageView;
    private boolean videoViewIsPrepared;

    static bridge synthetic com.bisimplex.firebooru.view.IPageContentView -$$Nest$fgetcontentView(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.contentView;
    }

    static bridge synthetic ref.WeakReference -$$Nest$fgetlistener(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.listener;
    }

    static bridge synthetic me.zhanghai.android.materialprogressbar.MaterialProgressBar -$$Nest$fgetloading_bottom(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.loading_bottom;
    }

    static bridge synthetic android.os.Handler -$$Nest$fgetmHandler(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.mHandler;
    }

    static bridge synthetic com.bisimplex.firebooru.danbooru.DanbooruPost -$$Nest$fgettargetPost(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.targetPost;
    }

    static bridge synthetic androidx.appcompat.widget.AppCompatImageView -$$Nest$fgetthumbImageView(com.bisimplex.firebooru.view.PageView p0)
    {
        return p0.thumbImageView;
    }

    static bridge synthetic void -$$Nest$fputisLoading(com.bisimplex.firebooru.view.PageView p0, boolean p1)
    {
        p0.isLoading = p1;
        return;
    }

    static bridge synthetic void -$$Nest$mapplyFadeOutAnimation(com.bisimplex.firebooru.view.PageView p0, android.view.View p1)
    {
        p0.applyFadeOutAnimation(p1);
        return;
    }

    static bridge synthetic int -$$Nest$sfgettickTime()
    {
        return com.bisimplex.firebooru.view.PageView.tickTime;
    }

    static bridge synthetic int -$$Nest$sfgetwhat()
    {
        return com.bisimplex.firebooru.view.PageView.what;
    }

    static PageView()
    {
        return;
    }

    public PageView(android.content.Context p2)
    {
        super(p2);
        super.fileDownloadTargetListener = new com.bisimplex.firebooru.view.PageView$2(super);
        super.drawableTargetListener = new com.bisimplex.firebooru.view.PageView$3(super);
        super.mHandler = new android.os.Handler(new com.bisimplex.firebooru.view.PageView$4(super));
        return;
    }

    public PageView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.fileDownloadTargetListener = new com.bisimplex.firebooru.view.PageView$2(super);
        super.drawableTargetListener = new com.bisimplex.firebooru.view.PageView$3(super);
        super.mHandler = new android.os.Handler(new com.bisimplex.firebooru.view.PageView$4(super));
        return;
    }

    public PageView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.fileDownloadTargetListener = new com.bisimplex.firebooru.view.PageView$2(super);
        super.drawableTargetListener = new com.bisimplex.firebooru.view.PageView$3(super);
        super.mHandler = new android.os.Handler(new com.bisimplex.firebooru.view.PageView$4(super));
        return;
    }

    public PageView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.fileDownloadTargetListener = new com.bisimplex.firebooru.view.PageView$2(super);
        super.drawableTargetListener = new com.bisimplex.firebooru.view.PageView$3(super);
        super.mHandler = new android.os.Handler(new com.bisimplex.firebooru.view.PageView$4(super));
        return;
    }

    private void applyFadeOutAnimation(android.view.View p3)
    {
        android.view.animation.Animation v0_0 = this.getContext();
        if ((p3 != null) && (v0_0 != null)) {
            p3.clearAnimation();
            android.view.animation.Animation v0_2 = android.view.animation.AnimationUtils.loadAnimation(this.getContext(), 2130771997);
            v0_2.setAnimationListener(new com.bisimplex.firebooru.view.PageView$1(this, p3));
            p3.startAnimation(v0_2);
        }
        return;
    }

    private androidx.appcompat.widget.AppCompatImageView createThumb()
    {
        androidx.appcompat.widget.AppCompatImageView v0_1 = new androidx.appcompat.widget.AppCompatImageView(this.getContext());
        v0_1.setLayoutParams(new android.widget.FrameLayout$LayoutParams(-1, -1, 17));
        v0_1.setScaleType(android.widget.ImageView$ScaleType.FIT_CENTER);
        this.addView(v0_1, 0);
        return v0_1;
    }

    private void startHandlerTick()
    {
        this.mHandler.removeMessages(com.bisimplex.firebooru.view.PageView.what);
        this.mHandler.sendEmptyMessageDelayed(com.bisimplex.firebooru.view.PageView.what, ((long) com.bisimplex.firebooru.view.PageView.tickTime));
        return;
    }

    public void cleanup()
    {
        androidx.appcompat.widget.AppCompatImageView v0_0 = this.contentView;
        if (v0_0 != null) {
            v0_0.cleanup();
        }
        androidx.appcompat.widget.AppCompatImageView v0_1 = this.thumbImageView;
        if (v0_1 != null) {
            v0_1.setImageDrawable(0);
        }
        return;
    }

    public void destroy()
    {
        this.recycleContentView();
        return;
    }

    public void finishedLoading(com.bisimplex.firebooru.danbooru.DanbooruPost p3, java.io.File p4)
    {
        long v0_0 = this.contentView;
        if ((v0_0 != 0) && (p4 != null)) {
            try {
                v0_0.setFile(p4, p3.getVisibleVersion().getContentType());
            } catch (long v0_7) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_7);
            }
        }
        long v0_1 = this.listener;
        if ((v0_1 != 0) && (v0_1.get() != null)) {
            ((com.bisimplex.firebooru.view.PageViewListener) this.listener.get()).postFinishedDownload(this, p4);
            if ((this.contentView instanceof com.bisimplex.firebooru.view.BooruPhotoView)) {
                ((com.bisimplex.firebooru.view.PageViewListener) this.listener.get()).pageViewLoaded(p3, 0);
            }
        }
        this._isReady = 1;
        return;
    }

    public android.view.View getContentView()
    {
        return ((android.view.View) this.contentView);
    }

    public com.bisimplex.firebooru.custom.DownloadTargetListener getDrawableDownloadTargetListener(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.targetPost = p1;
        return this.drawableTargetListener;
    }

    public long getDuration()
    {
        long v0_0 = this.contentView;
        if (!(v0_0 instanceof com.bisimplex.firebooru.view.IVideoView)) {
            if (!(v0_0 instanceof com.bisimplex.firebooru.view.PageGifImageView)) {
                return 0;
            } else {
                return ((com.bisimplex.firebooru.view.PageGifImageView) v0_0).getDuration();
            }
        } else {
            return ((com.bisimplex.firebooru.view.IVideoView) v0_0).getDuration();
        }
    }

    public com.bisimplex.firebooru.custom.DownloadTargetListener getFileDownloadTargetListener(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.targetPost = p1;
        return this.fileDownloadTargetListener;
    }

    public com.bisimplex.firebooru.view.BooruPhotoView getImageView()
    {
        int v0_0 = this.contentView;
        if (!(v0_0 instanceof com.bisimplex.firebooru.view.BooruPhotoView)) {
            return 0;
        } else {
            return ((com.bisimplex.firebooru.view.BooruPhotoView) v0_0);
        }
    }

    public boolean getIsLoading()
    {
        return this.isLoading;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getTargetPost()
    {
        return this.targetPost;
    }

    public androidx.appcompat.widget.AppCompatImageView getThumbImageView()
    {
        return this.thumbImageView;
    }

    public boolean isClean()
    {
        boolean v0_0 = this.contentView;
        if (v0_0) {
            if (!(v0_0 instanceof com.bisimplex.firebooru.view.BooruPhotoView)) {
                if (!(v0_0 instanceof com.bisimplex.firebooru.view.IVideoView)) {
                    return 0;
                } else {
                    return ((com.bisimplex.firebooru.view.IVideoView) v0_0).isReleased();
                }
            } else {
                if (((com.bisimplex.firebooru.view.BooruPhotoView) v0_0).getDrawable() != null) {
                    return 0;
                } else {
                    return 1;
                }
            }
        } else {
            return 1;
        }
    }

    public boolean isPlaying()
    {
        int v0_0 = this.contentView;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0.isPlaying();
        }
    }

    public boolean isReady()
    {
        return this._isReady;
    }

    public boolean isVideoView()
    {
        boolean v0_0 = this.contentView;
        if (v0_0) {
            return (v0_0 instanceof com.bisimplex.firebooru.view.IVideoView);
        } else {
            return 0;
        }
    }

    public void pauseContent()
    {
        com.bisimplex.firebooru.view.IPageContentView v0_0 = this.contentView;
        if ((v0_0 != null) && (v0_0.isPlaying())) {
            this.contentView.pause();
        }
        return;
    }

    public void prepareBeginLoad()
    {
        if (this.loading_bottom == null) {
            if (this.getContext() != null) {
                android.widget.FrameLayout$LayoutParams v0_2 = ((android.view.LayoutInflater) this.getContext().getSystemService("layout_inflater"));
                if (v0_2 != null) {
                    android.widget.FrameLayout$LayoutParams v0_4 = ((me.zhanghai.android.materialprogressbar.MaterialProgressBar) v0_2.inflate(2131558668, 0));
                    this.loading_bottom = v0_4;
                    this.addView(v0_4);
                    this.loading_bottom.setLayoutParams(new android.widget.FrameLayout$LayoutParams(-1, -2, 80));
                    this.isLoading = 1;
                    this.loading_bottom.clearAnimation();
                    this.loading_bottom.setProgress(0);
                    this.loading_bottom.setVisibility(0);
                    this._isReady = 0;
                    this.targetPost = 0;
                    return;
                }
            }
            return;
        }
        this.isLoading = 1;
        this.loading_bottom.clearAnimation();
        this.loading_bottom.setProgress(0);
        this.loading_bottom.setVisibility(0);
        this._isReady = 0;
        this.targetPost = 0;
        return;
    }

    protected void recycleContentView()
    {
        me.zhanghai.android.materialprogressbar.MaterialProgressBar v0_0 = this.contentView;
        if (v0_0 != null) {
            v0_0.cleanup();
            this.contentView = 0;
            this.videoViewIsPrepared = 0;
            this._isReady = 0;
        }
        me.zhanghai.android.materialprogressbar.MaterialProgressBar v0_3 = this.thumbImageView;
        if (v0_3 != null) {
            v0_3.setImageDrawable(0);
            this.thumbImageView = 0;
        }
        if (this.loading_bottom != null) {
            this.loading_bottom = 0;
        }
        this.removeAllViews();
        return;
    }

    public void reportLoadFailed(String p2)
    {
        this.isLoading = 0;
        com.bisimplex.firebooru.view.PageViewListener v0_2 = this.listener;
        if ((v0_2 != null) && (v0_2.get() != null)) {
            ((com.bisimplex.firebooru.view.PageViewListener) this.listener.get()).loadFailed(this, p2);
        }
        return;
    }

    public void reportProgress(long p4, long p6)
    {
        me.zhanghai.android.materialprogressbar.MaterialProgressBar v0 = this.loading_bottom;
        if ((v0 != null) && (p6 > 0)) {
            v0.setProgress(((int) ((p4 * 100) / p6)));
        }
        return;
    }

    public void resumeContent()
    {
        com.bisimplex.firebooru.view.IPageContentView v0_0 = this.contentView;
        if ((v0_0 != null) && (!v0_0.isPlaying())) {
            this.contentView.start();
            this.startHandlerTick();
        }
        return;
    }

    public void setContentProgressTo(int p4)
    {
        com.bisimplex.firebooru.view.IVideoView v0_0 = this.contentView;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.view.IVideoView))) {
            ((com.bisimplex.firebooru.view.IVideoView) v0_0).seekTo(((long) p4));
        }
        return;
    }

    public void setListener(com.bisimplex.firebooru.view.PageViewListener p2)
    {
        this.listener = new ref.WeakReference(p2);
        return;
    }

    public void setMuted(boolean p2)
    {
        if (this.isVideoView()) {
            ((com.bisimplex.firebooru.view.IVideoView) this.contentView).setMuted(p2);
            return;
        } else {
            return;
        }
    }

    public void setVideoURL(String p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        this.isLoading = 0;
        this.targetPost = p4;
        this.loading_bottom.clearAnimation();
        this.loading_bottom.setProgress(0);
        this.loading_bottom.setVisibility(4);
        this._isReady = 1;
        com.bisimplex.firebooru.view.IVideoView v0_2 = this.getContentView();
        if ((v0_2 instanceof com.bisimplex.firebooru.view.IVideoView)) {
            ((com.bisimplex.firebooru.view.IVideoView) v0_2).setURL(p3, p4.getPostUrl(), p4.getVisibleVersion().getContentType());
        }
        return;
    }

    public void showNotes(com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        com.bisimplex.firebooru.view.BooruPhotoView v0 = this.getImageView();
        if (v0 != null) {
            v0.showNotes(p2, p3);
        }
        return;
    }

    public void stop()
    {
        int v0_0 = this.contentView;
        if (v0_0 != 0) {
            v0_0.stop();
            this.targetPost = 0;
        }
        return;
    }

    public void toggleNotes(com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        com.bisimplex.firebooru.view.BooruPhotoView v0 = this.getImageView();
        if (v0 != null) {
            v0.toggleNotes(p2, p3);
        }
        return;
    }
}
