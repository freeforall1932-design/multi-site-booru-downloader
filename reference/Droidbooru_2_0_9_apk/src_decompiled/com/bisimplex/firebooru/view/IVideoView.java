package com.bisimplex.firebooru.view;
public interface IVideoView implements com.bisimplex.firebooru.view.IPageContentView {

    public abstract android.widget.FrameLayout getAsLayout();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract android.view.ViewParent getParent();

    public abstract boolean hasControls();

    public abstract boolean isAttachedToWindow();

    public abstract boolean isReleased();

    public abstract void release();

    public abstract void removeListener();

    public abstract void reset();

    public abstract void seekTo(long p0);

    public abstract void setMuted(boolean p0);

    public abstract void setOnLongClickListener(android.view.View$OnLongClickListener p0);

    public abstract void setTag(int p0, Object p1);

    public abstract void setURL(String p0, String p1, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p2);

    public abstract void setVisibility(int p0);
}
