package com.bisimplex.firebooru.view;
public class CustomImageVIew extends androidx.appcompat.widget.AppCompatImageView implements android.view.View$OnTouchListener {
    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    private int mBitmapHeight;
    private android.graphics.Point mBitmapMiddlePoint;
    private int mBitmapWidth;
    private boolean mDraggable;
    private android.graphics.PointF mMiddlePoint;
    private android.graphics.PointF mStartPoint;
    private int mViewHeight;
    private int mViewWidth;
    private android.graphics.Matrix matrix;
    private float[] matrixValues;
    private int mode;
    private float oldDist;
    private float oldEventX;
    private float oldEventY;
    private float oldStartPointX;
    private float oldStartPointY;
    private android.graphics.Matrix savedMatrix;
    private float scale;

    public CustomImageVIew(android.content.Context p3)
    {
        this(p3, 0, 0);
        return;
    }

    public CustomImageVIew(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public CustomImageVIew(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.matrix = new android.graphics.Matrix();
        super.savedMatrix = new android.graphics.Matrix();
        super.mode = 0;
        super.mStartPoint = new android.graphics.PointF();
        super.mMiddlePoint = new android.graphics.PointF();
        super.mBitmapMiddlePoint = new android.graphics.Point();
        super.oldDist = 1065353216;
        int v2_8 = new float[9];
        v2_8 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        super.matrixValues = v2_8;
        super.oldEventX = 0;
        super.oldEventY = 0;
        super.oldStartPointX = 0;
        super.oldStartPointY = 0;
        super.mViewWidth = -1;
        super.mViewHeight = -1;
        super.mBitmapWidth = -1;
        super.mBitmapHeight = -1;
        super.mDraggable = 0;
        super.setOnTouchListener(super);
        return;
    }

    private void midPoint(android.graphics.PointF p5, android.view.MotionEvent p6)
    {
        p5.set(((p6.getX(0) + p6.getX(1)) / 1073741824), ((p6.getY(0) + p6.getY(1)) / 1073741824));
        return;
    }

    private float spacing(android.view.MotionEvent p5)
    {
        return ((float) Math.sqrt(((double) (((p5.getX(0) - p5.getX(1)) * (p5.getX(0) - p5.getX(1))) + ((p5.getY(0) - p5.getY(1)) * (p5.getY(0) - p5.getY(1)))))));
    }

    public void drag(android.view.MotionEvent p12)
    {
        float v9_1;
        this.matrix.getValues(this.matrixValues);
        int v0_4 = this.matrixValues;
        android.graphics.Matrix v1_3 = v0_4[2];
        int v2_5 = v0_4[5];
        float v3_1 = 0;
        int v0_10 = v0_4[0];
        float v4_4 = (((((float) this.mBitmapHeight) * v0_10) + v2_5) - ((float) this.mViewHeight));
        int v0_3 = (((v0_10 * ((float) this.mBitmapWidth)) + v1_3) - ((float) this.mViewWidth));
        float v5_6 = p12.getX();
        float v12_3 = p12.getY();
        float v6_2 = (v5_6 - this.mStartPoint.x);
        float v7_2 = (v12_3 - this.mStartPoint.y);
        float v8 = 0;
        if (v1_3 >= 0) {
            v9_1 = (v6_2 * -1082130432);
        } else {
            v9_1 = v6_2;
        }
        float v10_1;
        float v9_2 = (v9_1 + v1_3);
        if (v2_5 >= 0) {
            v10_1 = (-1082130432 * v7_2);
        } else {
            v10_1 = v7_2;
        }
        int v0_9;
        float v10_2 = (v10_1 + v2_5);
        float v7_3 = (v7_2 + v4_4);
        int v0_6 = (v6_2 + v0_3) cmp 0;
        if ((v0_6 >= 0) && (v9_2 <= 0)) {
            v0_9 = 1;
        } else {
            if ((v0_6 >= 0) || (v9_2 <= 0)) {
                v5_6 = this.oldEventX;
                this.mStartPoint.x = this.oldStartPointX;
            } else {
                v0_9 = 0;
            }
        }
        int v2_3 = v7_3 cmp 0;
        if ((v2_3 >= 0) && (v10_2 <= 0)) {
            v3_1 = 1;
        } else {
            if ((v2_3 >= 0) || (v10_2 <= 0)) {
                v12_3 = this.oldEventY;
                this.mStartPoint.y = this.oldStartPointY;
            }
        }
        if (this.mDraggable) {
            int v2_8;
            this.matrix.set(this.savedMatrix);
            if (v0_9 == 0) {
                v2_8 = 0;
            } else {
                v2_8 = (v5_6 - this.mStartPoint.x);
            }
            if (v3_1 != 0) {
                v8 = (v12_3 - this.mStartPoint.y);
            }
            this.matrix.postTranslate(v2_8, v8);
            this.setImageMatrix(this.matrix);
            if (v0_9 != 0) {
                this.oldEventX = v5_6;
            }
            if (v3_1 != 0) {
                this.oldEventY = v12_3;
            }
            if (v0_9 != 0) {
                this.oldStartPointX = this.mStartPoint.x;
            }
            if (v3_1 != 0) {
                this.oldStartPointY = this.mStartPoint.y;
            }
        }
        return;
    }

    public void onSizeChanged(int p1, int p2, int p3, int p4)
    {
        super.onSizeChanged(p1, p2, p3, p4);
        this.mViewWidth = p1;
        this.mViewHeight = p2;
        return;
    }

    public boolean onTouch(android.view.View p4, android.view.MotionEvent p5)
    {
        android.graphics.PointF v4_2 = (p5.getAction() & 255);
        if (v4_2 == null) {
            this.savedMatrix.set(this.matrix);
            this.mStartPoint.set(p5.getX(), p5.getY());
            this.mode = 1;
        } else {
            if (v4_2 != 1) {
                if (v4_2 == 2) {
                    android.graphics.PointF v4_1 = this.mode;
                    if (v4_1 != 1) {
                        if (v4_1 != 2) {
                            return 1;
                        } else {
                            this.zoom(p5);
                            return 1;
                        }
                    } else {
                        this.drag(p5);
                        return 1;
                    }
                } else {
                    if (v4_2 == 5) {
                        android.graphics.PointF v4_3 = this.spacing(p5);
                        this.oldDist = v4_3;
                        if (v4_3 <= 1092616192) {
                            return 1;
                        } else {
                            this.savedMatrix.set(this.matrix);
                            this.midPoint(this.mMiddlePoint, p5);
                            this.mode = 2;
                            return 1;
                        }
                    } else {
                        if (v4_2 != 6) {
                            return 1;
                        }
                    }
                }
            }
            this.mode = 0;
        }
        return 1;
    }

    public void setBitmap(android.graphics.Bitmap p3)
    {
        if (p3 != null) {
            this.setImageBitmap(p3);
            this.mBitmapWidth = p3.getWidth();
            this.mBitmapHeight = p3.getHeight();
            this.mBitmapMiddlePoint.x = ((this.mViewWidth / 2) - (this.mBitmapWidth / 2));
            this.mBitmapMiddlePoint.y = ((this.mViewHeight / 2) - (this.mBitmapHeight / 2));
            this.matrix.postTranslate(((float) this.mBitmapMiddlePoint.x), ((float) this.mBitmapMiddlePoint.y));
            this.setImageMatrix(this.matrix);
        }
        return;
    }

    public void zoom(android.view.MotionEvent p7)
    {
        this.matrix.getValues(this.matrixValues);
        android.graphics.Matrix v7_1 = this.spacing(p7);
        float v0_7 = this.matrixValues[0];
        float v2_0 = (((float) this.mBitmapWidth) * v0_7);
        float v3_2 = (((float) this.mBitmapHeight) * v0_7);
        if ((v7_1 > this.oldDist) || (v0_7 >= 1065353216)) {
            float v0_2 = this.mViewWidth;
            if ((v2_0 <= ((float) v0_2)) && (v3_2 <= ((float) this.mViewHeight))) {
                this.mDraggable = 0;
            } else {
                this.mDraggable = 1;
            }
            float v0_4 = ((float) (v0_2 / 2));
            float v1_4 = ((float) (this.mViewHeight / 2));
            this.matrix.set(this.savedMatrix);
            android.graphics.Matrix v7_2 = (v7_1 / this.oldDist);
            this.scale = v7_2;
            if (v2_0 > ((float) this.mViewWidth)) {
                v0_4 = this.mMiddlePoint.x;
            }
            if (v3_2 > ((float) this.mViewHeight)) {
                v1_4 = this.mMiddlePoint.y;
            }
            this.matrix.postScale(v7_2, v7_2, v0_4, v1_4);
            this.setImageMatrix(this.matrix);
            return;
        } else {
            return;
        }
    }
}
