package com.bisimplex.firebooru.view;
 class PageView$4 implements android.os.Handler$Callback {
    final synthetic com.bisimplex.firebooru.view.PageView this$0;

    PageView$4(com.bisimplex.firebooru.view.PageView p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean handleMessage(android.os.Message p8)
    {
        if ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgetcontentView(this.this$0) != null) && (com.bisimplex.firebooru.view.PageView.-$$Nest$fgetcontentView(this.this$0).isPlaying())) {
            if ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgetcontentView(this.this$0) instanceof com.bisimplex.firebooru.view.IVideoView)) {
                long v2_1 = ((com.bisimplex.firebooru.view.IVideoView) com.bisimplex.firebooru.view.PageView.-$$Nest$fgetcontentView(this.this$0));
                if ((com.bisimplex.firebooru.view.PageView.-$$Nest$fgetlistener(this.this$0) != null) && (com.bisimplex.firebooru.view.PageView.-$$Nest$fgetlistener(this.this$0).get() != null)) {
                    ((com.bisimplex.firebooru.view.PageViewListener) com.bisimplex.firebooru.view.PageView.-$$Nest$fgetlistener(this.this$0).get()).videoChangeTime(v2_1, v2_1.getCurrentPosition(), v2_1.getDuration());
                }
            }
            com.bisimplex.firebooru.view.PageView.-$$Nest$fgetmHandler(this.this$0).sendEmptyMessageDelayed(com.bisimplex.firebooru.view.PageView.-$$Nest$sfgetwhat(), ((long) com.bisimplex.firebooru.view.PageView.-$$Nest$sfgettickTime()));
        }
        return 1;
    }
}
