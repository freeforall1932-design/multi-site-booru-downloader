package com.bisimplex.firebooru.view;
public class PageGifImageView extends pl.droidsonroids.gif.GifImageView implements com.bisimplex.firebooru.view.IPageContentView {

    public PageGifImageView(android.content.Context p1)
    {
        super(p1);
        return;
    }

    public PageGifImageView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public PageGifImageView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        return;
    }

    public PageGifImageView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        return;
    }

    public void cleanup()
    {
        int v0_0 = this.getDrawable();
        if ((v0_0 instanceof pl.droidsonroids.gif.GifDrawable)) {
            int v0_1 = ((pl.droidsonroids.gif.GifDrawable) v0_0);
            if (!v0_1.isRecycled()) {
                v0_1.recycle();
            }
        }
        this.setImageDrawable(0);
        return;
    }

    public long getDuration()
    {
        long v0_1 = ((pl.droidsonroids.gif.GifDrawable) this.getDrawable());
        if (v0_1 == 0) {
            return 0;
        } else {
            return ((long) v0_1.getDuration());
        }
    }

    public boolean isPlaying()
    {
        return 1;
    }

    public void pause()
    {
        return;
    }

    public void resume()
    {
        return;
    }

    public void setFile(java.io.File p1, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p2)
    {
        if ((p1 != null) && (p1.exists())) {
            try {
                this.setImageDrawable(new pl.droidsonroids.gif.GifDrawable(p1));
                return;
            } catch (java.io.IOException v1_1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_1);
            }
        }
        return;
    }

    public void start()
    {
        this.setVisibility(0);
        return;
    }

    public void stop()
    {
        return;
    }
}
