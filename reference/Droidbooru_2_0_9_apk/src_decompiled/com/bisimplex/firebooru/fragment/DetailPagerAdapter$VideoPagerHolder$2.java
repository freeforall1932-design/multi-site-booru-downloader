package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$2 implements com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;

    DetailPagerAdapter$VideoPagerHolder$2(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onError(String p5)
    {
        if (this.this$1.listener != null) {
            this.this$1.listener.videoError(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), p5, com.bisimplex.firebooru.view.VideoErrorType.Other);
        }
        return;
    }

    public void onPlayingChange(boolean p2, long p3)
    {
        if ((this.this$1.listener != null) && (p2 != null)) {
            this.this$1.listener.videoStartedPlaying(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), this.this$1.videoControlLayout);
        }
        return;
    }

    public void onSeekingChange(boolean p2)
    {
        if (this.this$1.listener != null) {
            this.this$1.listener.seekingChange(p2);
        }
        return;
    }

    public void onStart(long p2)
    {
        if (this.this$1.listener != null) {
            this.this$1.listener.videoIsPreparedToPlay(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView());
            this.this$1.this$0.getSharedVideoView().setOnLongClickListener(this.this$1);
        }
        return;
    }

    public void onTimeUpdate(long p9, long p11)
    {
        if (this.this$1.listener != null) {
            this.this$1.listener.videoChangeTime(this.this$1.getBindingAdapterPosition(), this.this$1.this$0.getSharedVideoView(), p9, p11);
        }
        return;
    }
}
