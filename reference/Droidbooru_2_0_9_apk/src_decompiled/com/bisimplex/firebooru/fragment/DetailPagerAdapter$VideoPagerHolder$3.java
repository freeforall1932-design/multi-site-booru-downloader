package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$3 implements androidx.media3.common.Player$Listener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;

    DetailPagerAdapter$VideoPagerHolder$3(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onIsPlayingChanged(boolean p5)
    {
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener v0_0;
        if (!p5) {
            v0_0 = "false";
        } else {
            v0_0 = "true";
        }
        android.util.Log.e("ExoPlayer", String.format("onIsPlayingChanged %s", new Object[] {v0_0})));
        if (p5) {
            com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$mstartHandlerTick(this.this$1);
            if (this.this$1.listener != null) {
                this.this$1.listener.videoStartedPlaying(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), this.this$1.videoControlLayout);
            }
        }
        this.this$1.updatePlayButton(p5);
        return;
    }

    public void onMetadata(androidx.media3.common.Metadata p1)
    {
        return;
    }

    public void onPlaybackStateChanged(int p5)
    {
        if (p5 == 1) {
            android.util.Log.e("ExoPlayer", "STATE_IDLE");
            return;
        } else {
            if (p5 == 2) {
                if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()) {
                    this.this$1.this$0.getSharedVideoView().setVisibility(0);
                }
                android.util.Log.e("ExoPlayer", "STATE_BUFFERING");
                return;
            } else {
                if (p5 == 3) {
                    android.util.Log.e("ExoPlayer", "STATE_READY");
                    this.this$1.videoViewIsPrepared = 1;
                    com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v5_12 = this.this$1.this$0.getSharedVideoView();
                    v5_12.setVisibility(0);
                    this.this$1.thumbImageView.setVisibility(8);
                    this.this$1.thumbImageView.setImageBitmap(0);
                    boolean v0_6 = this.this$1.getBindingAdapterPosition();
                    int v1_5 = this.this$1.this$0.getItem(v0_6);
                    if (v1_5 != 0) {
                        v1_5.setDuration(v5_12.getDuration());
                    }
                    if (this.this$1.listener != null) {
                        this.this$1.listener.videoIsPreparedToPlay(this.this$1.getBindingAdapterPosition(), v5_12);
                        v5_12.setOnLongClickListener(this.this$1);
                    }
                    if (v0_6 == com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fgetsource(this.this$1.this$0).getVisiblePostIndex()) {
                        v5_12.start();
                    }
                    com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$mupdateMuteButton(this.this$1, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
                    return;
                } else {
                    if (p5 == 4) {
                        android.util.Log.e("ExoPlayer", "STATE_ENDED");
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void onPlayerError(androidx.media3.common.PlaybackException p5)
    {
        if ((this.this$1.listener != null) && ((p5 instanceof androidx.media3.exoplayer.ExoPlaybackException))) {
            this.this$1.listener.videoError(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), p5.getLocalizedMessage(), com.bisimplex.firebooru.view.VideoErrorType.fromExoInteger(((androidx.media3.exoplayer.ExoPlaybackException) p5).type));
        }
        return;
    }

    public void onSurfaceSizeChanged(int p1, int p2)
    {
        return;
    }

    public void onVideoSizeChanged(androidx.media3.common.VideoSize p1)
    {
        return;
    }
}
