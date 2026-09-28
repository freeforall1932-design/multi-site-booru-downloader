package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$1 implements org.videolan.libvlc.MediaPlayer$EventListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;

    DetailPagerAdapter$VideoPagerHolder$1(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onEvent(org.videolan.libvlc.MediaPlayer$Event p5)
    {
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v5_1 = p5.type;
        if (v5_1 == 266) {
            if (this.this$1.listener != null) {
                this.this$1.listener.videoError(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), "", com.bisimplex.firebooru.view.VideoErrorType.Other);
            }
        } else {
            if (v5_1 == 276) {
                this.this$1.videoViewIsPrepared = 1;
                com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v5_11 = this.this$1.this$0.getSharedVideoView();
                v5_11.setVisibility(0);
                this.this$1.thumbImageView.setVisibility(8);
                this.this$1.thumbImageView.setImageBitmap(0);
                boolean v0_9 = this.this$1.getBindingAdapterPosition();
                com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v1_6 = this.this$1.this$0.getItem(v0_9);
                if (v1_6 != null) {
                    v1_6.setDuration(v5_11.getDuration());
                }
                if (this.this$1.listener != null) {
                    this.this$1.listener.videoIsPreparedToPlay(this.this$1.getBindingAdapterPosition(), v5_11);
                    if (!v5_11.hasControls()) {
                        v5_11.setOnLongClickListener(this.this$1);
                    }
                }
                if (v0_9 == com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetsource(this.this$1.this$0).getVisiblePostIndex()) {
                    v5_11.start();
                    return;
                }
            } else {
                switch (v5_1) {
                    case 259:
                        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()) {
                            this.this$1.this$0.getSharedVideoView().setVisibility(0);
                        }
                        android.util.Log.e("VLCPlayer", "Buffering");
                        return;
                    case 260:
                        android.util.Log.e("VLCPlayer", "Playing");
                        com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$mstartHandlerTick(this.this$1);
                        if (this.this$1.listener != null) {
                            this.this$1.listener.videoStartedPlaying(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), this.this$1.videoControlLayout);
                        }
                        this.this$1.updatePlayButton(1);
                        com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$mupdateMuteButton(this.this$1, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
                        return;
                    case 261:
                        android.util.Log.e("VLCPlayer", "Paused");
                        this.this$1.updatePlayButton(0);
                        return;
                    default:
                }
            }
        }
        return;
    }

    public bridge synthetic void onEvent(org.videolan.libvlc.interfaces.AbstractVLCEvent p1)
    {
        this.onEvent(((org.videolan.libvlc.MediaPlayer$Event) p1));
        return;
    }
}
