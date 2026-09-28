package com.bisimplex.firebooru.view;
 class ChromeVideoView$2 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.ChromeVideoView this$0;
    final synthetic long val$progress;

    ChromeVideoView$2(com.bisimplex.firebooru.view.ChromeVideoView p1, long p2)
    {
        this.this$0 = p1;
        this.val$progress = p2;
        return;
    }

    public void run()
    {
        if ((android.os.Looper.getMainLooper() == android.os.Looper.myLooper()) && (com.bisimplex.firebooru.view.ChromeVideoView.-$$Nest$fgetwebView(this.this$0) != null)) {
            com.bisimplex.firebooru.view.ChromeVideoView.-$$Nest$fgetwebView(this.this$0).evaluateJavascript(String.format(java.util.Locale.US, "seekVideo(%.1f);", new Object[] {Double.valueOf((((double) this.val$progress) / 4652007308841189376))})), 0);
        }
        return;
    }
}
