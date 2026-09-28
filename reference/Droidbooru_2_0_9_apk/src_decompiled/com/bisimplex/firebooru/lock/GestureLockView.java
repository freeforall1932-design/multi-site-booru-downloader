package com.bisimplex.firebooru.lock;
public class GestureLockView extends android.view.View {
    public static final int ARROW_BOTTOM = 16;
    public static final int ARROW_BOTTOM_LEFT = 32;
    public static final int ARROW_LEFT = 64;
    public static final int ARROW_LEFT_TOP = 128;
    public static final int ARROW_RIGHT = 4;
    public static final int ARROW_RIGHT_BOTTOM = 8;
    public static final int ARROW_TOP = 1;
    public static final int ARROW_TOP_RIGHT = 2;
    public static final int MODE_ERROR = 1024;
    public static final int MODE_NORMAL = 256;
    public static final int MODE_SELECTED = 512;
    private int COLOR_ERROR;
    private int COLOR_NORMAL;
    private android.graphics.Path arrow;
    private int arrowDistance;
    private float arrowDistanceRate;
    private float arrowRate;
    private int centerX;
    private int centerY;
    private int height;
    private float innerRate;
    private int mode;
    private float outerRate;
    private float outerWidthRate;
    private android.graphics.Paint paint;
    private int radius;
    private int width;

    public GestureLockView(android.content.Context p2)
    {
        this(p2, 0);
        return;
    }

    public GestureLockView(android.content.Context p2, android.util.AttributeSet p3)
    {
        this(p2, p3, 0);
        return;
    }

    public GestureLockView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.mode = 256;
        super.COLOR_NORMAL = -1;
        super.COLOR_ERROR = -65536;
        super.innerRate = 1045220557;
        super.outerWidthRate = 1041865114;
        super.outerRate = 1063843267;
        super.arrowRate = 1048576000;
        super.arrowDistanceRate = 0;
        super.paint = new android.graphics.Paint(1);
        return;
    }

    public int getMode()
    {
        return this.mode;
    }

    public void onDraw(android.graphics.Canvas p6)
    {
        float v0_1 = (this.mode & 3840);
        if (v0_1 == 256) {
            this.paint.setStyle(android.graphics.Paint$Style.FILL);
            this.paint.setColor(this.COLOR_NORMAL);
            p6.drawCircle(((float) this.centerX), ((float) this.centerY), (((float) this.radius) * this.innerRate), this.paint);
        } else {
            if (v0_1 == 512) {
                this.paint.setStyle(android.graphics.Paint$Style.STROKE);
                this.paint.setColor(this.COLOR_NORMAL);
                this.paint.setStrokeWidth((((float) this.radius) * this.outerWidthRate));
                p6.drawCircle(((float) this.centerX), ((float) this.centerY), (((float) this.radius) * this.outerRate), this.paint);
                this.paint.setStrokeWidth(1073741824);
                p6.drawCircle(((float) this.centerX), ((float) this.centerY), (((float) this.radius) * this.innerRate), this.paint);
            } else {
                if (v0_1 == 1024) {
                    this.paint.setStyle(android.graphics.Paint$Style.STROKE);
                    this.paint.setColor(this.COLOR_ERROR);
                    this.paint.setStrokeWidth((((float) this.radius) * this.outerWidthRate));
                    p6.drawCircle(((float) this.centerX), ((float) this.centerY), (((float) this.radius) * this.outerRate), this.paint);
                    this.paint.setStrokeWidth(1073741824);
                    p6.drawCircle(((float) this.centerX), ((float) this.centerY), (((float) this.radius) * this.innerRate), this.paint);
                }
            }
        }
        if ((this.mode & 255) > 0) {
            this.paint.setStyle(android.graphics.Paint$Style.FILL);
            p6.save();
            float v0_4 = (this.mode & 255);
            if (v0_4 == 2) {
                p6.rotate(1110704128, ((float) this.centerX), ((float) this.centerY));
            } else {
                if (v0_4 == 4) {
                    p6.rotate(1119092736, ((float) this.centerX), ((float) this.centerY));
                } else {
                    if (v0_4 == 8) {
                        p6.rotate(1124532224, ((float) this.centerX), ((float) this.centerY));
                    } else {
                        if (v0_4 == 16) {
                            p6.rotate(1127481344, ((float) this.centerX), ((float) this.centerY));
                        } else {
                            if (v0_4 == 32) {
                                p6.rotate(-1022951424, ((float) this.centerX), ((float) this.centerY));
                            } else {
                                if (v0_4 == 64) {
                                    p6.rotate(-1028390912, ((float) this.centerX), ((float) this.centerY));
                                } else {
                                    if (v0_4 == 128) {
                                        p6.rotate(-1036779520, ((float) this.centerX), ((float) this.centerY));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            p6.drawPath(this.arrow, this.paint);
            p6.restore();
        }
        return;
    }

    protected void onMeasure(int p4, int p5)
    {
        super.onMeasure(p4, p5);
        this.width = android.view.View$MeasureSpec.getSize(p4);
        android.graphics.Path v4_1 = android.view.View$MeasureSpec.getSize(p5);
        this.height = v4_1;
        android.graphics.Path v5_10 = this.width;
        this.centerX = (v5_10 / 2);
        this.centerY = (v4_1 / 2);
        if (v5_10 <= v4_1) {
            v4_1 = v5_10;
        }
        this.radius = v4_1;
        android.graphics.Path v4_2 = (v4_1 / 2);
        this.radius = v4_2;
        if (this.arrow == null) {
            this.arrowDistance = ((int) (((float) v4_2) * this.arrowDistanceRate));
            android.graphics.Path v4_6 = ((int) (((float) v4_2) * this.arrowRate));
            android.graphics.Path v5_7 = new android.graphics.Path();
            this.arrow = v5_7;
            v5_7.moveTo(((float) ((- v4_6) + this.centerX)), ((float) ((this.centerY + v4_6) - this.arrowDistance)));
            this.arrow.lineTo(((float) this.centerX), ((float) (this.centerY - this.arrowDistance)));
            this.arrow.lineTo(((float) (this.centerX + v4_6)), ((float) ((v4_6 + this.centerY) - this.arrowDistance)));
            this.arrow.close();
        }
        return;
    }

    public void setCOLOR_ERROR(int p1)
    {
        this.COLOR_ERROR = p1;
        return;
    }

    public void setCOLOR_NORMAL(int p1)
    {
        this.COLOR_NORMAL = p1;
        return;
    }

    public void setMode(int p1)
    {
        this.mode = p1;
        this.invalidate();
        return;
    }
}
