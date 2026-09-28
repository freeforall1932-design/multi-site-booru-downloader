package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$8 implements android.os.Handler$Callback {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;

    DetailPagerAdapter$VideoPagerHolder$8(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1)
    {
        this.this$1 = p1;
        return;
    }

    public boolean handleMessage(android.os.Message p9)
    {
        if (com.bisimplex.firebooru.fragment.DetailPagerAdapter.-$$Nest$fget__sharedVideoView(this.this$1.this$0) != null) {
            com.bisimplex.firebooru.view.IVideoView v3 = this.this$1.this$0.getSharedVideoView();
            if (v3.isPlaying()) {
                if (!this.this$1.videoSeekbarIsBeingDragged) {
                    long v4 = v3.getCurrentPosition();
                    this.this$1.timeTextView.setText(com.bisimplex.firebooru.fragment.DetailFragment.formatElapsedTime(v4));
                    long v6 = v3.getDuration();
                    if (((long) this.this$1.timeSeekBar.getMax()) != v6) {
                        this.this$1.timeSeekBar.setMax(((int) v6));
                        this.this$1.totalTimeTextView.setText(com.bisimplex.firebooru.fragment.DetailFragment.formatElapsedTime(v6));
                    }
                    this.this$1.timeSeekBar.setProgress(((int) v4));
                    if (this.this$1.listener != null) {
                        this.this$1.listener.videoChangeTime(this.this$1.getBindingAdapterPosition(), v3, v4, v6);
                    }
                    com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder.-$$Nest$fgetmHandler(this.this$1).sendEmptyMessageDelayed(0, 1000);
                    return 1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
