package com.bisimplex.firebooru.fragment;
 class DetailPagerAdapter$VideoPagerHolder$4 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder this$1;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter val$this$0;

    DetailPagerAdapter$VideoPagerHolder$4(com.bisimplex.firebooru.fragment.DetailPagerAdapter$VideoPagerHolder p1, com.bisimplex.firebooru.fragment.DetailPagerAdapter p2)
    {
        this.this$1 = p1;
        this.val$this$0 = p2;
        return;
    }

    public void onClick(android.view.View p2)
    {
        boolean v2_3 = this.this$1.this$0.getSharedVideoView();
        if (!v2_3.isPlaying()) {
            v2_3.resume();
        } else {
            v2_3.pause();
        }
        this.this$1.updatePlayButton(v2_3.isPlaying());
        return;
    }
}
