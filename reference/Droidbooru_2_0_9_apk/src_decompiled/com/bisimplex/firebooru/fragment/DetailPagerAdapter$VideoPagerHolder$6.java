package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$6 implements android.widget.SeekBar$OnSeekBarChangeListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter val$this$0;

    DetailPagerAdapter$VideoPagerHolder$6(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1, com.bisimplex.firebooru.fragment.DetailPagerAdapter p2)
    {
        this.this$1 = p1;
        this.val$this$0 = p2;
        return;
    }

    public void onProgressChanged(android.widget.SeekBar p1, int p2, boolean p3)
    {
        return;
    }

    public void onStartTrackingTouch(android.widget.SeekBar p2)
    {
        this.this$1.videoSeekbarIsBeingDragged = 1;
        return;
    }

    public void onStopTrackingTouch(android.widget.SeekBar p9)
    {
        this.this$1.videoSeekbarIsBeingDragged = 0;
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder v9_5 = p9.getProgress();
        android.util.Log.e("SeekBar", String.format("progress %d", new Object[] {Integer.valueOf(v9_5)})));
        com.bisimplex.firebooru.view.IVideoView v3 = this.this$1.this$0.getSharedVideoView();
        long v4 = ((long) v9_5);
        v3.seekTo(v4);
        if (this.this$1.listener != null) {
            this.this$1.listener.videoChangeTime(this.this$1.getBindingAdapterPosition(), v3, v4, v3.getDuration());
        }
        return;
    }
}
