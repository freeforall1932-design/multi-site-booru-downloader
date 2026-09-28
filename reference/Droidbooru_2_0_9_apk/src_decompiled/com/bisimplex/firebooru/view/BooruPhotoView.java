package com.bisimplex.firebooru.view;
public class BooruPhotoView extends com.github.chrisbanes.photoview.PhotoView implements com.bisimplex.firebooru.view.IPageContentView, com.bisimplex.firebooru.network.SourceListener, com.github.chrisbanes.photoview.OnPhotoTapListener {
    static final int SWIPE_MAX_OFF_PATH = 150;
    static final int SWIPE_MIN_DISTANCE = 80;
    private static final int SWIPE_THRESHOLD = 50;
    static final int SWIPE_THRESHOLD_VELOCITY = 50;
    private static final int SWIPE_VELOCITY_THRESHOLD = 150;
    private com.bisimplex.firebooru.view.BooruImageViewTouchListener listener;
    protected final float[] mMatrixValues;
    private boolean notesVisible;
    private android.graphics.Paint paint;
    private com.bisimplex.firebooru.danbooru.DanbooruPost relatedPost;
    private com.bisimplex.firebooru.network.SourceNote sourceNote;
    private android.text.TextPaint textPaint;

    static bridge synthetic com.bisimplex.firebooru.view.BooruImageViewTouchListener -$$Nest$fgetlistener(com.bisimplex.firebooru.view.BooruPhotoView p0)
    {
        return p0.listener;
    }

    public BooruPhotoView(android.content.Context p2)
    {
        this(p2, 0);
        return;
    }

    public BooruPhotoView(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public BooruPhotoView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        com.github.chrisbanes.photoview.PhotoViewAttacher v1_3 = new float[9];
        super.mMatrixValues = v1_3;
        super.setScaleType(android.widget.ImageView$ScaleType.FIT_CENTER);
        super.getAttacher().setOnPhotoTapListener(super);
        com.github.chrisbanes.photoview.PhotoViewAttacher v1_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance();
        if (v1_2.getDoubleTapCommand() != com.bisimplex.firebooru.model.ViewerCommandType.None) {
            super.getAttacher().setOnDoubleTapListener(new com.bisimplex.firebooru.view.BooruPhotoView$1(super));
        }
        com.bisimplex.firebooru.view.BooruPhotoView$2 v2_3 = v1_2.getSwipeUpCommand();
        com.github.chrisbanes.photoview.PhotoViewAttacher v1_4 = v1_2.getSwipeDownCommand();
        if ((v2_3 == com.bisimplex.firebooru.model.ViewerCommandType.None) && (v1_4 == com.bisimplex.firebooru.model.ViewerCommandType.None)) {
            return;
        } else {
            super.getAttacher().setOnSingleFlingListener(new com.bisimplex.firebooru.view.BooruPhotoView$2(super));
            return;
        }
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
        if (this.sourceNote != null) {
            com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.sourceNote);
            this.sourceNote = 0;
        }
        this.setListener(0);
        this.paint = 0;
        this.textPaint = 0;
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

    public long getDuration()
    {
        return 0;
    }

    protected float getScale(android.graphics.Matrix p2)
    {
        return this.getValue(p2, 0);
    }

    protected float getValue(android.graphics.Matrix p2, int p3)
    {
        p2.getValues(this.mMatrixValues);
        return this.mMatrixValues[p3];
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

    public boolean isZoomingIn()
    {
        int v0_0 = this.getAttacher();
        float v1 = v0_0.getScale();
        int v0_4 = v0_0.getMinimumScale();
        android.util.Log.i("BooruPhotoView", String.format("scale = %.2f min = %.2f", new Object[] {Float.valueOf(v1), Float.valueOf(v0_4)})));
        if (v1 <= v0_4) {
            return 0;
        } else {
            return 1;
        }
    }

    public void onDraw(android.graphics.Canvas p19)
    {
        super.onDraw(p19);
        if (this.notesVisible) {
            java.util.List v2_5 = this.sourceNote;
            if ((v2_5 != null) && ((v2_5.getOwner() != null) && (this.sourceNote.notesAreFromPost(this.relatedPost)))) {
                java.util.List v2_3 = this.sourceNote.getData();
                if ((v2_3 != null) && (!v2_3.isEmpty())) {
                    int v3_3 = this.sourceNote.getOwner();
                    android.graphics.RectF v4_1 = this.getAttacher().getDisplayRect();
                    if (v4_1 != null) {
                        int v3_5;
                        float v5_0 = v3_3.getVisibleVersion();
                        int v6 = 0;
                        if (v5_0 != v3_3.getFile()) {
                            v3_5 = 0;
                        } else {
                            v3_5 = 1;
                        }
                        float v5_3 = Math.max((v4_1.width() / ((float) v5_0.getWidth())), (v4_1.height() / ((float) v5_0.getHeight())));
                        while (v6 < v2_3.size()) {
                            android.graphics.RectF v8_4;
                            com.bisimplex.firebooru.danbooru.NoteItem v7_4 = ((com.bisimplex.firebooru.danbooru.NoteItem) v2_3.get(v6));
                            if (v3_5 == 0) {
                                v8_4 = v7_4.getFrame();
                            } else {
                                v8_4 = v7_4.getOriginalFrame();
                            }
                            android.graphics.RectF v8_5 = this.fixRect(v8_4, v4_1, v5_3);
                            p19.drawRect(v8_5, this.paint);
                            if (v7_4.textFitsIn(v8_5)) {
                                android.text.StaticLayout v10_1 = new android.text.StaticLayout(v7_4.getBody(), this.textPaint, (((int) v8_5.width()) - com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING), android.text.Layout$Alignment.ALIGN_NORMAL, 1065353216, 0, 0);
                                p19.save();
                                p19.translate((v8_5.left + ((float) com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING)), (v8_5.top + ((float) com.bisimplex.firebooru.danbooru.NoteItem.NOTE_DRAW_PADDING)));
                                v10_1.draw(p19);
                                p19.restore();
                            }
                            v7_4.setTouchFrame(v8_5);
                            v6++;
                        }
                    }
                }
            }
        }
        return;
    }

    public void onPhotoTap(android.widget.ImageView p3, float p4, float p5)
    {
        if ((this.listener != null) && ((this.notesVisible) && (this.sourceNote != null))) {
            com.bisimplex.firebooru.view.BooruImageViewTouchListener v3_9 = this.getAttacher().getDisplayRect();
            float v4_2 = ((p4 * v3_9.width()) + v3_9.left);
            float v5_2 = ((p5 * v3_9.height()) + v3_9.top);
            com.bisimplex.firebooru.view.BooruImageViewTouchListener v3_5 = this.sourceNote.getData().iterator();
            while (v3_5.hasNext()) {
                com.bisimplex.firebooru.danbooru.NoteItem v0_5 = ((com.bisimplex.firebooru.danbooru.NoteItem) v3_5.next());
                if (v0_5.getTouchFrame().contains(v4_2, v5_2)) {
                    this.listener.tapOnNote(v0_5);
                    break;
                }
            }
        }
        return;
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
                    v1_3.setTextFrame(com.bisimplex.firebooru.view.BooruPhotoView.calcultateTextSize(this.textPaint, v1_3.getBody()));
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

    public void showNotes(com.bisimplex.firebooru.danbooru.DanbooruPost p4, com.bisimplex.firebooru.danbooru.BooruProvider p5)
    {
        if (this.sourceNote == null) {
            com.bisimplex.firebooru.network.SourceNote v0_16 = ((com.bisimplex.firebooru.network.SourceNote) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Notes));
            this.sourceNote = v0_16;
            v0_16.setListener(this);
        }
        if (this.paint == null) {
            com.bisimplex.firebooru.network.SourceNote v0_3 = new android.graphics.Paint();
            this.paint = v0_3;
            v0_3.setStyle(android.graphics.Paint$Style.FILL);
            this.paint.setColor(-16777216);
            this.paint.setAlpha(130);
        }
        if (this.textPaint == null) {
            com.bisimplex.firebooru.network.SourceNote v0_8 = new android.text.TextPaint();
            this.textPaint = v0_8;
            v0_8.setColor(-1);
            this.textPaint.setTextSize(((float) this.getResources().getDimensionPixelSize(2131166071)));
        }
        this.notesVisible = 1;
        this.relatedPost = p4;
        if (!this.sourceNote.notesAreFromPost(p4)) {
            this.sourceNote.loadNotesFromPost(p4, p5);
            return;
        } else {
            this.renderNotes();
            return;
        }
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

    public void toggleNotes(com.bisimplex.firebooru.danbooru.DanbooruPost p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        if (!this.notesVisible) {
            this.showNotes(p2, p3);
            return;
        } else {
            this.hideNotes();
            return;
        }
    }
}
