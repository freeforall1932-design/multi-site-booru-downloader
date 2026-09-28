package com.bisimplex.firebooru.view;
 class ChromeVideoView$ChromeVideoInterface {
    private String backgroundColor;
    private com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener currentPlayerListener;
    private long currentTime;
    private long duration;
    private int loopCount;
    private double originalDuration;
    private boolean playing;
    private boolean seeking;
    private boolean started;
    final synthetic com.bisimplex.firebooru.view.ChromeVideoView this$0;
    private String type;
    private String videoUrl;

    static bridge synthetic com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener -$$Nest$fgetcurrentPlayerListener(com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface p0)
    {
        return p0.currentPlayerListener;
    }

    static bridge synthetic void -$$Nest$fputbackgroundColor(com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface p0, String p1)
    {
        p0.backgroundColor = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputcurrentPlayerListener(com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoInterface p0, com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener p1)
    {
        p0.currentPlayerListener = p1;
        return;
    }

    public ChromeVideoView$ChromeVideoInterface(com.bisimplex.firebooru.view.ChromeVideoView p1)
    {
        this.this$0 = p1;
        return;
    }

    public void cleanUp()
    {
        this.setVideoUrl("", com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static);
        return;
    }

    public String getBackgroundColor()
    {
        return this.backgroundColor;
    }

    public long getCurrentTime()
    {
        return this.currentTime;
    }

    public long getDuration()
    {
        return this.duration;
    }

    public boolean getMuted()
    {
        return com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted();
    }

    public String getType()
    {
        if (!android.text.TextUtils.isEmpty(this.type)) {
            return this.type;
        } else {
            return "video/mp4";
        }
    }

    public String getVideoUrl()
    {
        return this.videoUrl;
    }

    public boolean isPlaying()
    {
        return this.playing;
    }

    public void onAbort()
    {
        this.playing = 0;
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener v0_1 = this.currentPlayerListener;
        if (v0_1 != null) {
            v0_1.onError(0);
        }
        return;
    }

    public void onEnded()
    {
        android.util.Log.e("ChromeVideo", "onEnded");
        this.playing = 0;
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener v0_2 = this.currentPlayerListener;
        if (v0_2 != null) {
            v0_2.onPlayingChange(this.isPlaying(), this.getDuration());
        }
        return;
    }

    public void onError()
    {
        android.util.Log.e("ChromeVideo", "onError");
        this.playing = 0;
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener v0_2 = this.currentPlayerListener;
        if (v0_2 != null) {
            v0_2.onError(0);
        }
        return;
    }

    public void onPause()
    {
        this.playing = 0;
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener v0_1 = this.currentPlayerListener;
        if (v0_1 != null) {
            v0_1.onPlayingChange(this.isPlaying(), this.getDuration());
        }
        return;
    }

    public void onPlay(double p3)
    {
        android.util.Log.e("ChromeVideo", String.format("duration %f", new Object[] {Double.valueOf(p3)})));
        this.originalDuration = p3;
        this.duration = ((long) (p3 * 4652007308841189376));
        this.playing = 1;
        boolean v4_0 = this.currentPlayerListener;
        if (v4_0) {
            if (!this.started) {
                v4_0.onStart(this.getDuration());
                this.started = 1;
            }
            this.currentPlayerListener.onPlayingChange(this.isPlaying(), this.getDuration());
        }
        return;
    }

    public void onSeekingChange(boolean p2)
    {
        this.seeking = p2;
        com.bisimplex.firebooru.view.ChromeVideoView v0_0 = this.currentPlayerListener;
        if (v0_0 != null) {
            v0_0.onSeekingChange(p2);
        }
        this.this$0.requestDisallowInterceptTouchEvent(p2);
        return;
    }

    public void onTimeUpdate(double p5)
    {
        long v0_2 = ((long) (4652007308841189376 * p5));
        this.currentTime = v0_2;
        if (p5 == 0) {
            this.loopCount = (this.loopCount + 1);
        }
        com.bisimplex.firebooru.view.ChromeVideoView$ChromeVideoListener v5_1 = this.currentPlayerListener;
        if (v5_1 != null) {
            v5_1.onTimeUpdate(v0_2, this.duration);
        }
        return;
    }

    public void onVolumeChange(boolean p2)
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setVideoMuted(p2);
        return;
    }

    public void setVideoUrl(String p4, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p5)
    {
        this.playing = 0;
        this.videoUrl = p4;
        this.duration = 0;
        this.currentTime = 0;
        this.loopCount = -1;
        this.originalDuration = 0;
        this.started = 0;
        String v4_2 = com.bisimplex.firebooru.view.ChromeVideoView$3.$SwitchMap$com$bisimplex$firebooru$danbooru$DanbooruPostContentType[p5.ordinal()];
        if (v4_2 == 1) {
            this.type = "video/mp4";
            return;
        } else {
            if (v4_2 == 2) {
                this.type = "video/webm";
                return;
            } else {
                this.type = 0;
                return;
            }
        }
    }
}
