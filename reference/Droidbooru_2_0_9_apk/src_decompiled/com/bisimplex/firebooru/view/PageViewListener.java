package com.bisimplex.firebooru.view;
public interface PageViewListener implements android.view.View$OnLongClickListener, com.bisimplex.firebooru.view.BooruImageViewTouchListener {

    public abstract void loadFailed(com.bisimplex.firebooru.view.PageView p0, String p1);

    public abstract void pageViewLoaded(com.bisimplex.firebooru.danbooru.DanbooruPost p0, long p1);

    public abstract void postFinishedDownload(com.bisimplex.firebooru.view.PageView p0, java.io.File p1);

    public abstract void togglePlayVideo();

    public abstract void videoChangeTime(com.bisimplex.firebooru.view.IVideoView p0, long p1, long p2);

    public abstract void videoError(com.bisimplex.firebooru.view.PageView p0, com.bisimplex.firebooru.view.IVideoView p1, String p2, com.bisimplex.firebooru.view.VideoErrorType p3);

    public abstract void videoIsPreparedToPlay(com.bisimplex.firebooru.view.IVideoView p0);

    public abstract void videoStartedPlaying(com.bisimplex.firebooru.view.IVideoView p0);
}
