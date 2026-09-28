package com.bisimplex.firebooru.view;
public interface ChromeVideoView$ChromeVideoListener {

    public abstract void onError(String p0);

    public abstract void onPlayingChange(boolean p0, long p1);

    public abstract void onSeekingChange(boolean p0);

    public abstract void onStart(long p0);

    public abstract void onTimeUpdate(long p0, long p1);
}
