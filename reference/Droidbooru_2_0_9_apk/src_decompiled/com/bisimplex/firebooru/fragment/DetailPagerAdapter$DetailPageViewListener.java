package com.bisimplex.firebooru.fragment;
public interface DetailPagerAdapter$DetailPageViewListener implements android.view.View$OnLongClickListener, com.bisimplex.firebooru.view.BooruImageViewTouchListener {

    public abstract void loadFailed(int p0, String p1);

    public abstract void pageViewLoaded(int p0, com.bisimplex.firebooru.view.BooruPhotoView p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2, long p3);

    public abstract void postFinishedDownload(int p0, java.io.File p1);

    public abstract void seekingChange(boolean p0);

    public abstract void showBlacklisted(int p0);

    public abstract void togglePlayVideo(int p0, boolean p1);

    public abstract void videoChangeTime(int p0, com.bisimplex.firebooru.view.IVideoView p1, long p2, long p3);

    public abstract void videoError(int p0, com.bisimplex.firebooru.view.IVideoView p1, String p2, com.bisimplex.firebooru.view.VideoErrorType p3);

    public abstract void videoIsPreparedToPlay(int p0, com.bisimplex.firebooru.view.IVideoView p1);

    public abstract void videoStartedPlaying(int p0, com.bisimplex.firebooru.view.IVideoView p1, android.view.ViewGroup p2);
}
