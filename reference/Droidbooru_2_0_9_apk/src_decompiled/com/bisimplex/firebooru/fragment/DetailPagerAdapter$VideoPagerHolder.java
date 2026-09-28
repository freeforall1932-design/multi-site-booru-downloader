package com.bisimplex.firebooru.fragment;
public class DetailPagerAdapter$VideoPagerHolder extends com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder implements android.view.View$OnLongClickListener {
    private static final int tickTime = 1000;
    private static final int what;
    org.videolan.libvlc.MediaPlayer$EventListener eventListener;
    private final com.bisimplex.firebooru.custom.DownloadTargetListener fileDownloadTargetListener;
    private final android.os.Handler mHandler;
    android.widget.ImageButton muteButton;
    android.widget.ImageButton playButton;
    androidx.media3.common.Player$Listener playerListener;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;
    android.widget.SeekBar timeSeekBar;
    android.widget.TextView timeTextView;
    android.widget.TextView totalTimeTextView;
    android.view.ViewGroup videoControlLayout;
    com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener videoListener;
    boolean videoSeekbarIsBeingDragged;
    android.widget.FrameLayout videoViewContainer;
    boolean videoViewIsPrepared;

    static bridge synthetic com.bisimplex.firebooru.custom.DownloadTargetListener -$$Nest$fgetfileDownloadTargetListener(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p0)
    {
        return p0.fileDownloadTargetListener;
    }

    static bridge synthetic android.os.Handler -$$Nest$fgetmHandler(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p0)
    {
        return p0.mHandler;
    }

    static bridge synthetic void -$$Nest$mstartHandlerTick(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p0)
    {
        p0.startHandlerTick();
        return;
    }

    static bridge synthetic void -$$Nest$mtoggleMute(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p0)
    {
        p0.toggleMute();
        return;
    }

    static bridge synthetic void -$$Nest$mupdateMuteButton(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p0, boolean p1)
    {
        p0.updateMuteButton(p1);
        return;
    }

    public DetailPagerAdapter$VideoPagerHolder(com.bisimplex.firebooru.fragment.DetailPagerAdapter p3, android.view.View p4, com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener p5)
    {
        this.this$0 = p3;
        super(p3, p4, p5);
        super.eventListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$1(super);
        super.videoListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$2(super);
        super.playerListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$3(super);
        super.fileDownloadTargetListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$7(super);
        super.mHandler = new android.os.Handler(new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$8(super));
        super.timeSeekBar = ((android.widget.SeekBar) p4.findViewById(2131362639));
        super.timeTextView = ((android.widget.TextView) p4.findViewById(2131362640));
        super.totalTimeTextView = ((android.widget.TextView) p4.findViewById(2131362660));
        super.playButton = ((android.widget.ImageButton) p4.findViewById(2131362423));
        super.muteButton = ((android.widget.ImageButton) p4.findViewById(2131362344));
        super.videoControlLayout = ((android.view.ViewGroup) p4.findViewById(2131362697));
        super.videoViewContainer = ((android.widget.FrameLayout) p4.findViewById(2131362699));
        super.updateMuteButton(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
        super.updatePlayButton(0);
        super.videoControlLayout.setVisibility(8);
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            android.widget.SeekBar v4_2 = ((androidx.constraintlayout.widget.Guideline) p4.findViewById(2131362149));
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams v0_7 = ((androidx.constraintlayout.widget.ConstraintLayout$LayoutParams) v4_2.getLayoutParams());
            v0_7.guideEnd = 0;
            v4_2.setLayoutParams(v0_7);
        }
        android.widget.SeekBar v4_5 = ((android.view.ViewGroup$MarginLayoutParams) super.videoControlLayout.getLayoutParams());
        v4_5.bottomMargin = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetvideoControlBottomMargin(p3);
        super.videoControlLayout.setLayoutParams(v4_5);
        super.playButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$4(super, p3));
        super.muteButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$5(super, p3));
        super.timeSeekBar.setOnSeekBarChangeListener(new com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder$6(super, p3));
        return;
    }

    private void startHandlerTick()
    {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 1000);
        return;
    }

    private void toggleMute()
    {
        int v0_2 = (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted() ^ 1);
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setVideoMuted(v0_2);
        this.updateMuteButton(v0_2);
        this.this$0.getSharedVideoView().setMuted(v0_2);
        return;
    }

    private void updateMuteButton(boolean p4)
    {
        com.mikepenz.iconics.IconicsDrawable v4_2;
        android.widget.ImageButton v0_5 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0);
        if (p4 == null) {
            v4_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_volume_up;
        } else {
            v4_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_volume_mute;
        }
        com.mikepenz.iconics.IconicsDrawable v4_1 = com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(v0_5, v4_2, com.bisimplex.firebooru.skin.SkinManager.getInstance().getTextColorRes());
        android.widget.ImageButton v0_4 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0).getResources().getDimensionPixelSize(2131165379);
        int v1_4 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0).getResources().getDimensionPixelSize(2131165378);
        v4_1.setSizeXPx(v0_4);
        v4_1.setSizeYPx(v0_4);
        v4_1.setPaddingPx(v1_4);
        this.muteButton.setImageDrawable(v4_1);
        return;
    }

    protected void finishedLoading(com.bisimplex.firebooru.danbooru.DanbooruPost p9, java.io.File p10)
    {
        super.finishedLoading(p9, p10);
        String v0_1 = this.getBindingAdapterPosition();
        if (v0_1 == com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetsource(this.this$0).getVisiblePostIndex()) {
            com.bisimplex.firebooru.view.IVideoView v3 = this.this$0.getSharedVideoView();
            v3.setTag(2131362701, String.valueOf(v0_1));
            this.this$0.attachVideoView(v3, this.videoViewContainer, this.eventListener, this.playerListener, this.videoListener);
            v3.setFile(p10, p9.getVisibleVersion().getContentType());
            v3.start();
            if (!v3.hasControls()) {
                this.videoControlLayout.setVisibility(0);
            } else {
                this.videoControlLayout.setVisibility(8);
                return;
            }
        }
        return;
    }

    public boolean onLongClick(android.view.View p4)
    {
        if (!this.this$0.getSharedVideoView().hasControls()) {
            if (this.videoControlLayout.getVisibility() != 0) {
                this.videoControlLayout.setVisibility(0);
            } else {
                this.videoControlLayout.setVisibility(8);
            }
        }
        if (this.listener == null) {
            return 0;
        } else {
            return this.listener.onLongClick(p4);
        }
    }

    public void pause()
    {
        this.this$0.getSharedVideoView().pause();
        return;
    }

    public void resetTimerLabels()
    {
        this.timeSeekBar.setProgress(0);
        this.timeTextView.setText("0:00");
        this.totalTimeTextView.setText("0:00");
        return;
    }

    public void stop()
    {
        this.this$0.getSharedVideoView().stop();
        return;
    }

    public void updatePlayButton(boolean p4)
    {
        com.mikepenz.iconics.IconicsDrawable v4_2;
        android.widget.ImageButton v0_5 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0);
        if (p4 == null) {
            v4_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_play;
        } else {
            v4_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_pause;
        }
        com.mikepenz.iconics.IconicsDrawable v4_1 = com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(v0_5, v4_2, com.bisimplex.firebooru.skin.SkinManager.getInstance().getTextColorRes());
        android.widget.ImageButton v0_4 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0).getResources().getDimensionPixelSize(2131165379);
        int v1_4 = com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetcontext(this.this$0).getResources().getDimensionPixelSize(2131165378);
        v4_1.setSizeYPx(v0_4);
        v4_1.setSizeXPx(v0_4);
        v4_1.setPaddingPx(v1_4);
        this.playButton.setImageDrawable(v4_1);
        return;
    }
}
