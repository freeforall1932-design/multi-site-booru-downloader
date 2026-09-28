package com.bisimplex.firebooru.danbooru;
public class NoteItem {
    public static int NOTE_DRAW_PADDING = 8;
    private String body;
    private android.graphics.Rect frame;
    private android.graphics.Rect originalFrame;
    private android.graphics.Rect textFrame;
    private android.graphics.RectF touchFrame;

    static NoteItem()
    {
        return;
    }

    public NoteItem()
    {
        return;
    }

    public String getBody()
    {
        return this.body;
    }

    public android.graphics.Rect getFrame()
    {
        return this.frame;
    }

    public android.graphics.Rect getOriginalFrame()
    {
        return this.originalFrame;
    }

    public android.graphics.Rect getTextFrame()
    {
        return this.textFrame;
    }

    public android.graphics.RectF getTouchFrame()
    {
        if (this.touchFrame == null) {
            this.touchFrame = new android.graphics.RectF(0, 0, 0, 0);
        }
        return this.touchFrame;
    }

    public void setBody(String p1)
    {
        this.body = p1;
        return;
    }

    public void setFrame(android.graphics.Rect p1)
    {
        this.frame = p1;
        return;
    }

    public void setOriginalFrame(android.graphics.Rect p1)
    {
        this.originalFrame = p1;
        return;
    }

    public void setTextFrame(android.graphics.Rect p1)
    {
        this.textFrame = p1;
        return;
    }

    public void setTouchFrame(android.graphics.RectF p1)
    {
        this.touchFrame = p1;
        return;
    }

    public boolean textFitsIn(android.graphics.RectF p5)
    {
        float v0_0 = this.textFrame;
        if ((v0_0 != 0) && (p5 != 0)) {
            float v2_4 = (((float) com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING) * 1080033280);
            if (((((float) v0_0.width()) + v2_4) * (((float) this.textFrame.height()) + v2_4)) < (p5.height() * p5.width())) {
                return 1;
            }
        }
        return 0;
    }
}
