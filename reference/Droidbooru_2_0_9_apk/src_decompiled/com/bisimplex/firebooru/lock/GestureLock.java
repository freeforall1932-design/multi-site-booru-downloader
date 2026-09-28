package com.bisimplex.firebooru.lock;
public class GestureLock extends android.widget.RelativeLayout {
    public static final int MODE_EDIT = 1;
    public static final int MODE_NORMAL = 0;
    private static final int depth = 3;
    private static final int unmatchedBoundary = 5;
    private int blockGap;
    private int blockWidth;
    private int colorError;
    private int colorLine;
    private int[] defaultGestures;
    private int gestureCursor;
    private android.graphics.Path gesturePath;
    private int gestureWidth;
    private int[] gesturesContainer;
    private int lastPathX;
    private int lastPathY;
    private int lastX;
    private int lastY;
    private com.bisimplex.firebooru.lock.GestureLockView[] lockers;
    private int mode;
    private int[] negativeGestures;
    private com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener onGestureEventListener;
    private android.graphics.Paint paint;
    private boolean touchable;
    private int unmatchedCount;

    static bridge synthetic int -$$Nest$fgetblockGap(com.bisimplex.firebooru.lock.GestureLock p0)
    {
        return p0.blockGap;
    }

    static bridge synthetic int -$$Nest$fgetblockWidth(com.bisimplex.firebooru.lock.GestureLock p0)
    {
        return p0.blockWidth;
    }

    static bridge synthetic int -$$Nest$fgetcolorError(com.bisimplex.firebooru.lock.GestureLock p0)
    {
        return p0.colorError;
    }

    static bridge synthetic int -$$Nest$fgetcolorLine(com.bisimplex.firebooru.lock.GestureLock p0)
    {
        return p0.colorLine;
    }

    static bridge synthetic com.bisimplex.firebooru.lock.GestureLockView[] -$$Nest$fgetlockers(com.bisimplex.firebooru.lock.GestureLock p0)
    {
        return p0.lockers;
    }

    static bridge synthetic void -$$Nest$fputblockWidth(com.bisimplex.firebooru.lock.GestureLock p0, int p1)
    {
        p0.blockWidth = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputgestureWidth(com.bisimplex.firebooru.lock.GestureLock p0, int p1)
    {
        p0.gestureWidth = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputlockers(com.bisimplex.firebooru.lock.GestureLock p0, com.bisimplex.firebooru.lock.GestureLockView[] p1)
    {
        p0.lockers = p1;
        return;
    }

    public GestureLock(android.content.Context p2)
    {
        this(p2, 0);
        return;
    }

    public GestureLock(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public GestureLock(android.content.Context p3, android.util.AttributeSet p4, int p5)
    {
        super(p3, p4, p5);
        super.mode = 0;
        super.defaultGestures = new int[] {0, 1, 2, 4, 6});
        super.gestureCursor = 0;
        super.blockWidth = 190;
        super.blockGap = 70;
        com.bisimplex.firebooru.lock.GestureLock$1 v4_4 = new int[9];
        super.negativeGestures = v4_4;
        com.bisimplex.firebooru.lock.GestureLock$1 v4_5 = 0;
        while(true) {
            android.graphics.Paint$Join v5_1 = super.negativeGestures;
            if (v4_5 >= v5_1.length) {
                break;
            }
            v5_1[v4_5] = -1;
            v4_5++;
        }
        super.gesturesContainer = ((int[]) v5_1.clone());
        com.bisimplex.firebooru.lock.GestureLock$1 v4_9 = new android.graphics.Paint(1);
        super.paint = v4_9;
        v4_9.setStyle(android.graphics.Paint$Style.STROKE);
        super.paint.setStrokeWidth(1101004800);
        super.paint.setStrokeCap(android.graphics.Paint$Cap.ROUND);
        super.paint.setStrokeJoin(android.graphics.Paint$Join.ROUND);
        super.unmatchedCount = 0;
        super.touchable = 1;
        super.getViewTreeObserver().addOnGlobalLayoutListener(new com.bisimplex.firebooru.lock.GestureLock$1(super));
        return;
    }

    private int calculateChildIdByCoords(int p3, int p4)
    {
        if (p3 >= 0) {
            float v0_0 = this.gestureWidth;
            if ((p3 <= v0_0) && ((p4 >= 0) && (p4 <= v0_0))) {
                return (((int) ((((float) p3) / ((float) v0_0)) * 1077936128)) + (((int) ((((float) p4) / ((float) v0_0)) * 1077936128)) * 3));
            }
        }
        return -1;
    }

    private boolean checkChildInCoords(int p4, int p5, android.view.View p6)
    {
        if (p6 != null) {
            int v4_2;
            int v0_0 = ((p6.getLeft() + (p6.getWidth() / 2)) - p4);
            int v1_1 = ((p6.getTop() + (p6.getHeight() / 2)) - p5);
            if (p6.getWidth() <= p6.getHeight()) {
                v4_2 = p6.getWidth();
            } else {
                v4_2 = p6.getHeight();
            }
            if (((v0_0 * v0_0) + (v1_1 * v1_1)) < ((v4_2 / 2) * (v4_2 / 2))) {
                return 1;
            }
        }
        return 0;
    }

    public void dispatchDraw(android.graphics.Canvas p8)
    {
        super.dispatchDraw(p8);
        int v0_0 = this.gesturePath;
        if (v0_0 != 0) {
            p8.drawPath(v0_0, this.paint);
        }
        if (this.gesturesContainer[0] != -1) {
            p8.drawLine(((float) this.lastPathX), ((float) this.lastPathY), ((float) this.lastX), ((float) this.lastY), this.paint);
        }
        return;
    }

    public boolean onTouchEvent(android.view.MotionEvent p9)
    {
        if (this.touchable) {
            com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_17 = p9.getActionMasked();
            int v2_0 = 0;
            if (v0_17 != null) {
                if (v0_17 != 1) {
                    if (v0_17 == 2) {
                        this.lastX = ((int) p9.getX());
                        com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_25 = ((int) p9.getY());
                        this.lastY = v9_25;
                        com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_26 = this.calculateChildIdByCoords(this.lastX, v9_25);
                        com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_20 = this.findViewById((v9_26 + 1));
                        int v4_3 = this.gesturesContainer;
                        int v6_2 = 0;
                        while (v6_2 < v4_3.length) {
                            if (v4_3[v6_2] != v9_26) {
                                v6_2++;
                            } else {
                                v2_0 = 1;
                                break;
                            }
                        }
                        if ((v0_20 != null) && (((v0_20 instanceof com.bisimplex.firebooru.lock.GestureLockView)) && (this.checkChildInCoords(this.lastX, this.lastY, v0_20)))) {
                            ((com.bisimplex.firebooru.lock.GestureLockView) v0_20).setMode(512);
                            if (v2_0 == 0) {
                                int v2_2 = (v0_20.getLeft() + (v0_20.getWidth() / 2));
                                int v4_12 = (v0_20.getTop() + (v0_20.getHeight() / 2));
                                com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_24 = this.gesturePath;
                                if (v0_24 != null) {
                                    v0_24.lineTo(((float) v2_2), ((float) v4_12));
                                } else {
                                    com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_26 = new android.graphics.Path();
                                    this.gesturePath = v0_26;
                                    v0_26.moveTo(((float) v2_2), ((float) v4_12));
                                }
                                int v3_11 = this.gestureCursor;
                                this.gesturesContainer[v3_11] = v9_26;
                                this.gestureCursor = (v3_11 + 1);
                                this.lastPathX = v2_2;
                                this.lastPathY = v4_12;
                                com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_2 = this.onGestureEventListener;
                                if (v0_2 != null) {
                                    v0_2.onBlockSelected(v9_26);
                                }
                            }
                        }
                        this.invalidate();
                        return 1;
                    } else {
                        if (v0_17 != 3) {
                            return 1;
                        }
                    }
                }
                if (this.gesturesContainer[0] != -1) {
                    com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_4 = 0;
                    com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_4 = 0;
                    while(true) {
                        int v3_0 = this.gesturesContainer;
                        if (v9_4 >= v3_0.length) {
                            break;
                        }
                        if (v3_0[v9_4] >= 0) {
                            v0_4++;
                        }
                        v9_4++;
                    }
                    com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_5;
                    if (v0_4 != this.defaultGestures.length) {
                        v0_5 = 0;
                    } else {
                        com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_7 = 0;
                        v0_5 = 0;
                        while(true) {
                            int v3_1 = this.defaultGestures;
                            if (v9_7 < v3_1.length) {
                                if (this.gesturesContainer[v9_7] != v3_1[v9_7]) {
                                    break;
                                }
                                v9_7++;
                                v0_5 = 1;
                            }
                        }
                    }
                    if ((v0_5 != null) || (this.mode == 1)) {
                        this.unmatchedCount = 0;
                    } else {
                        this.unmatchedCount = (this.unmatchedCount + 1);
                        this.paint.setColor(this.colorError);
                        com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_12 = this.gesturesContainer;
                        int v3_4 = v9_12.length;
                        int v4_2 = 0;
                        while (v4_2 < v3_4) {
                            com.bisimplex.firebooru.lock.GestureLockView v5_2 = this.findViewById((v9_12[v4_2] + 1));
                            if ((v5_2 != null) && ((v5_2 instanceof com.bisimplex.firebooru.lock.GestureLockView))) {
                                ((com.bisimplex.firebooru.lock.GestureLockView) v5_2).setMode(1024);
                            }
                            v4_2++;
                        }
                    }
                    com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_13 = this.onGestureEventListener;
                    if (v9_13 != null) {
                        v9_13.onGestureEvent(v0_5);
                        if (this.unmatchedCount >= 5) {
                            this.onGestureEventListener.onUnmatchedExceedBoundary();
                            this.unmatchedCount = 0;
                        }
                    }
                }
                this.gestureCursor = 0;
                this.gesturesContainer = ((int[]) this.negativeGestures.clone());
                this.lastX = this.lastPathX;
                this.lastY = this.lastPathY;
                this.invalidate();
                return 1;
            }
            while (v2_0 < this.getChildCount()) {
                com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v0_13 = this.getChildAt(v2_0);
                if ((v0_13 instanceof com.bisimplex.firebooru.lock.GestureLockView)) {
                    ((com.bisimplex.firebooru.lock.GestureLockView) v0_13).setMode(256);
                }
                v2_0++;
            }
            this.gesturePath = 0;
            this.lastX = ((int) p9.getX());
            com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener v9_22 = ((int) p9.getY());
            this.lastY = v9_22;
            this.lastPathX = this.lastX;
            this.lastPathY = v9_22;
            this.paint.setColor(this.colorLine);
        }
        return 1;
    }

    public void resetPath()
    {
        this.gesturePath = 0;
        int v0_1 = 0;
        while (v0_1 < this.getChildCount()) {
            com.bisimplex.firebooru.lock.GestureLockView v1_2 = this.getChildAt(v0_1);
            if ((v1_2 instanceof com.bisimplex.firebooru.lock.GestureLockView)) {
                ((com.bisimplex.firebooru.lock.GestureLockView) v1_2).setMode(256);
            }
            v0_1++;
        }
        this.invalidate();
        return;
    }

    public void rewindUnmatchedCount()
    {
        this.unmatchedCount = 0;
        return;
    }

    public void setColorError(int p1)
    {
        this.colorError = p1;
        return;
    }

    public void setColorLine(int p1)
    {
        this.colorLine = p1;
        return;
    }

    public void setCorrectGesture(int[] p1)
    {
        this.defaultGestures = p1;
        return;
    }

    public void setMode(int p1)
    {
        this.mode = p1;
        return;
    }

    public void setOnGestureEventListener(com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener p1)
    {
        this.onGestureEventListener = p1;
        return;
    }

    public void setTouchable(boolean p1)
    {
        this.touchable = p1;
        return;
    }
}
