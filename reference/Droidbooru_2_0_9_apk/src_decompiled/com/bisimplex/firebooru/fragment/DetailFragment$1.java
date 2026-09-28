package com.bisimplex.firebooru.fragment;
 class DetailFragment$1 extends androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    public static synthetic void $r8$lambda$ArOaRd6J9xKQHUKLyggyVJ3GuLQ(com.bisimplex.firebooru.fragment.DetailFragment$1 p0, int p1)
    {
        p0.lambda$onPageSelected$0(p1);
        return;
    }

    public static synthetic void $r8$lambda$CoSCBo_uC0Fx9L2KMCqgDoLmOnQ(com.bisimplex.firebooru.fragment.DetailFragment$1 p0)
    {
        p0.lambda$onPageScrollStateChanged$1();
        return;
    }

    DetailFragment$1(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    private synthetic void lambda$onPageScrollStateChanged$1()
    {
        if ((this.this$0.isAdded()) && (this.this$0.adapter != null)) {
            this.this$0.adapter.notifyItemChanged(com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetreloadPosition(this.this$0));
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputreloadPosition(this.this$0, -1);
        }
        return;
    }

    private synthetic void lambda$onPageSelected$0(int p2)
    {
        com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mpreloadPostIfNeeded(this.this$0, p2);
        return;
    }

    public void onPageScrollStateChanged(int p4)
    {
        super.onPageScrollStateChanged(p4);
        if ((p4 == null) && (com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetreloadPosition(this.this$0) >= 0)) {
            this.this$0.pager.postDelayed(new com.bisimplex.firebooru.fragment.DetailFragment$1$$ExternalSyntheticLambda0(this), 50);
        }
        return;
    }

    public void onPageScrolled(int p1, float p2, int p3)
    {
        super.onPageScrolled(p1, p2, p3);
        return;
    }

    public void onPageSelected(int p7)
    {
        super.onPageSelected(p7);
        int v0_1 = this.this$0.getSource();
        com.bisimplex.firebooru.activity.MessageType v1_12 = v0_1.getVisiblePostIndex();
        v0_1.setVisiblePostIndex(p7);
        com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mupdateTitle(this.this$0, p7);
        com.bisimplex.firebooru.danbooru.DanbooruPost v2_0 = v0_1.getVisiblePost();
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addPostHistoryItem(v2_0);
        this.this$0.updateFavButton();
        this.this$0.updateNotesButton();
        if (v1_12 != p7) {
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputreloadPosition(this.this$0, p7);
        }
        if ((com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowEnabled(this.this$0)) && ((com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowHandler(this.this$0) != null) && (com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowRunnable(this.this$0) != null))) {
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowHandler(this.this$0).removeCallbacks(com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowRunnable(this.this$0));
        }
        com.bisimplex.firebooru.activity.MessageType v1_11 = this.this$0.adapter.getItem(p7);
        if ((v1_11.isReady()) && ((com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowEnabled(this.this$0)) && (!v2_0.getVisibleVersion().isVideo()))) {
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mtriggerSlideshowTick(this.this$0, v1_11.getDuration());
        }
        com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetexecutor(this.this$0).execute(new com.bisimplex.firebooru.fragment.DetailFragment$1$$ExternalSyntheticLambda1(this, p7));
        if ((v0_1.isLastItem(v2_0)) && ((!v0_1.isLastPage()) && (!v0_1.getIsLoading()))) {
            v0_1.loadAnotherPage();
            this.this$0.showMessage(2131886802, com.bisimplex.firebooru.activity.MessageType.Minimal);
        }
        return;
    }
}
