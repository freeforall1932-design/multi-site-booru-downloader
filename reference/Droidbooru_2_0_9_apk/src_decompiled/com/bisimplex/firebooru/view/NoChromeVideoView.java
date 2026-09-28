package com.bisimplex.firebooru.view;
public class NoChromeVideoView extends android.widget.FrameLayout implements com.bisimplex.firebooru.view.IVideoView {

    public NoChromeVideoView(android.content.Context p1)
    {
        super(p1);
        super.initializePlayer(p1);
        return;
    }

    public NoChromeVideoView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.initializePlayer(p1);
        return;
    }

    public NoChromeVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.initializePlayer(p1);
        return;
    }

    public NoChromeVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.initializePlayer(p1);
        return;
    }

    private void initializePlayer(android.content.Context p5)
    {
        android.widget.TextView v0_1 = new android.widget.TextView(p5);
        v0_1.setTextSize(2, 1103101952);
        v0_1.setTextColor(this.getResources().getColor(2131099701, p5.getTheme()));
        v0_1.setTextAlignment(4);
        v0_1.setBackgroundColor(this.getResources().getColor(2131100467, p5.getTheme()));
        v0_1.setText(2131886988);
        android.widget.FrameLayout$LayoutParams v5_5 = new android.widget.FrameLayout$LayoutParams(-1, -2, 17);
        v5_5.setMargins(((int) android.util.TypedValue.applyDimension(1, 1098907648, this.getResources().getDisplayMetrics())), ((int) android.util.TypedValue.applyDimension(1, 1098907648, this.getResources().getDisplayMetrics())), ((int) android.util.TypedValue.applyDimension(1, 1098907648, this.getResources().getDisplayMetrics())), ((int) android.util.TypedValue.applyDimension(1, 1098907648, this.getResources().getDisplayMetrics())));
        this.addView(v0_1, v5_5);
        return;
    }

    public void cleanup()
    {
        return;
    }

    public android.widget.FrameLayout getAsLayout()
    {
        return this;
    }

    public long getCurrentPosition()
    {
        return 0;
    }

    public long getDuration()
    {
        return 0;
    }

    public boolean hasControls()
    {
        return 1;
    }

    public boolean isPlaying()
    {
        return 0;
    }

    public boolean isReleased()
    {
        return 0;
    }

    public void pause()
    {
        return;
    }

    public void release()
    {
        return;
    }

    public void removeListener()
    {
        return;
    }

    public void reset()
    {
        return;
    }

    public void resume()
    {
        return;
    }

    public void seekTo(long p1)
    {
        return;
    }

    public void setFile(java.io.File p1, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p2)
    {
        return;
    }

    public void setMuted(boolean p1)
    {
        return;
    }

    public void setURL(String p1, String p2, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p3)
    {
        return;
    }

    public void start()
    {
        return;
    }

    public void stop()
    {
        return;
    }
}
