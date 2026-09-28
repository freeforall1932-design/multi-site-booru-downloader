package com.bisimplex.firebooru.view;
public class BooruImageViewTouch extends it.sephiroth.android.library.imagezoom.ImageViewTouch implements com.bisimplex.firebooru.view.IPageContentView, com.bisimplex.firebooru.network.SourceListener {
    static final float SCROLL_DELTA_THRESHOLD = 16256;
    com.bisimplex.firebooru.view.BooruImageViewTouchListener listener;
    private boolean notesVisible;
    private android.graphics.Paint paint;
    private com.bisimplex.firebooru.danbooru.DanbooruPost relatedPost;
    private com.bisimplex.firebooru.network.SourceNote sourceNote;
    private android.text.TextPaint textPaint;

    public BooruImageViewTouch(android.content.Context p1)
    {
        super(p1);
        super.setDisplayType(it.sephiroth.android.library.imagezoom.ImageViewTouchBase$DisplayType.FIT_TO_SCREEN);
        return;
    }

    public BooruImageViewTouch(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.setDisplayType(it.sephiroth.android.library.imagezoom.ImageViewTouchBase$DisplayType.FIT_TO_SCREEN);
        return;
    }

    public BooruImageViewTouch(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.setDisplayType(it.sephiroth.android.library.imagezoom.ImageViewTouchBase$DisplayType.FIT_TO_SCREEN);
        return;
    }

    private static android.graphics.Rect calcultateTextSize(android.text.TextPaint p3, String p4)
    {
        android.graphics.Rect v0_1 = new android.graphics.Rect();
        p3.getTextBounds(p4, 0, p4.length(), v0_1);
        return v0_1;
    }

    public void cleanup()
    {
        this.setImageDrawable(0);
        this.setImageBitmap(0);
        if (this.sourceNote != null) {
            com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.sourceNote);
            this.sourceNote = 0;
        }
        return;
    }

    public void dealloc()
    {
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        return;
    }

    protected android.graphics.RectF fixRect(android.graphics.Rect p5, android.graphics.RectF p6, float p7)
    {
        float v0_1 = new android.graphics.RectF((((float) p5.left) * p7), (((float) p5.top) * p7), (((float) p5.right) * p7), (((float) p5.bottom) * p7));
        float v5_5 = (v0_1.left + p6.left);
        float v7_3 = (v0_1.top + p6.top);
        return new android.graphics.RectF(v5_5, v7_3, (v0_1.right + v5_5), (v0_1.bottom + v7_3));
    }

    protected void hideNotes()
    {
        this.notesVisible = 0;
        this.invalidate();
        return;
    }

    public boolean isNotesVisible()
    {
        return this.notesVisible;
    }

    public boolean isPlaying()
    {
        return 1;
    }

    public void onDraw(android.graphics.Canvas p19)
    {
        super.onDraw(p19);
        if (this.notesVisible) {
            java.util.List v2_5 = this.sourceNote;
            if ((v2_5 != null) && ((v2_5.getOwner() != null) && (this.sourceNote.notesAreFromPost(this.relatedPost)))) {
                java.util.List v2_3 = this.sourceNote.getData();
                if ((v2_3 != null) && (!v2_3.isEmpty())) {
                    float v3_3 = this.sourceNote.getOwner();
                    android.graphics.RectF v4 = this.getBitmapRect();
                    if (v4 != null) {
                        int v5_3;
                        float v6_1 = (this.getScale() * this.getScale(this.mBaseMatrix));
                        int v8 = 0;
                        if (v3_3.getVisibleVersion() != v3_3.getFile()) {
                            v5_3 = 0;
                        } else {
                            v5_3 = 1;
                        }
                        android.graphics.RectF v7_1 = v3_3.getVisibleVersion();
                        if ((v7_1 == v3_3.getFile()) && ((v3_3.getEnforceOriginalImage()) && ((v7_1.getHeight() > 0) && (v7_1.getWidth() > 0)))) {
                            v6_1 = Math.min((v4.width() / ((float) v7_1.getWidth())), (v4.height() / ((float) v7_1.getHeight())));
                        }
                        while (v8 < v2_3.size()) {
                            android.graphics.RectF v7_4;
                            float v3_11 = ((com.bisimplex.firebooru.danbooru.NoteItem) v2_3.get(v8));
                            if (v5_3 == 0) {
                                v7_4 = v3_11.getFrame();
                            } else {
                                v7_4 = v3_11.getOriginalFrame();
                            }
                            android.graphics.RectF v7_5 = this.fixRect(v7_4, v4, v6_1);
                            p19.drawRect(v7_5, this.paint);
                            if (v3_11.textFitsIn(v7_5)) {
                                android.text.StaticLayout v10_0 = new android.text.StaticLayout(v3_11.getBody(), this.textPaint, (((int) v7_5.width()) - com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING), android.text.Layout$Alignment.ALIGN_NORMAL, 1065353216, 0, 0);
                                p19.save();
                                p19.translate((v7_5.left + ((float) com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING)), (v7_5.top + ((float) com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING)));
                                v10_0.draw(p19);
                                p19.restore();
                            }
                            v3_11.setTouchFrame(v7_5);
                            v8++;
                        }
                    }
                }
            }
        }
        return;
    }

    public boolean onSingleTapConfirmed(android.view.MotionEvent p6)
    {
        if ((this.listener != null) && (this.notesVisible)) {
            com.bisimplex.firebooru.view.BooruImageViewTouchListener v0_3 = this.sourceNote;
            if (v0_3 != null) {
                com.bisimplex.firebooru.view.BooruImageViewTouchListener v0_5 = v0_3.getData().iterator();
                while (v0_5.hasNext()) {
                    com.bisimplex.firebooru.danbooru.NoteItem v1_2 = ((com.bisimplex.firebooru.danbooru.NoteItem) v0_5.next());
                    if (v1_2.getTouchFrame().contains(p6.getX(), p6.getY())) {
                        this.listener.tapOnNote(v1_2);
                        break;
                    }
                }
            }
        }
        return super.onSingleTapConfirmed(p6);
    }

    public void pause()
    {
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    protected void renderNotes()
    {
        java.util.Iterator v0_0 = this.sourceNote;
        if ((v0_0 != null) && (this.textPaint != null)) {
            com.bisimplex.firebooru.danbooru.NoteItem v1_4 = this.relatedPost;
            if ((v1_4 != null) && (v0_0.notesAreFromPost(v1_4))) {
                java.util.Iterator v0_2 = this.sourceNote.getData().iterator();
                while (v0_2.hasNext()) {
                    com.bisimplex.firebooru.danbooru.NoteItem v1_3 = ((com.bisimplex.firebooru.danbooru.NoteItem) v0_2.next());
                    v1_3.setTextFrame(com.bisimplex.firebooru.view.BooruImageViewTouch.calcultateTextSize(this.textPaint, v1_3.getBody()));
                }
                this.invalidate();
            }
        }
        return;
    }

    public void resume()
    {
        return;
    }

    public void setFile(java.io.File p1, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p2)
    {
        return;
    }

    public void setListener(com.bisimplex.firebooru.view.BooruImageViewTouchListener p1)
    {
        this.listener = p1;
        return;
    }

    public void setNotesVisible(boolean p1)
    {
        this.notesVisible = p1;
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

    public void success(com.bisimplex.firebooru.network.Source p1, java.util.List p2)
    {
        if (p1 == this.sourceNote) {
            this.renderNotes();
        }
        return;
    }
}
