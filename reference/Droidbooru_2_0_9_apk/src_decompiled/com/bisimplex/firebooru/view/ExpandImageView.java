package com.bisimplex.firebooru.view;
public class ExpandImageView extends androidx.appcompat.widget.AppCompatImageView {
    private float scale;

    public ExpandImageView(android.content.Context p1)
    {
        super(p1);
        super.updateScale();
        return;
    }

    public ExpandImageView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.updateScale();
        return;
    }

    public ExpandImageView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.updateScale();
        return;
    }

    private void updateScale()
    {
        this.scale = this.getResources().getDisplayMetrics().density;
        return;
    }

    protected void onMeasure(int p3, int p4)
    {
        float v0_0 = this.getDrawable();
        if (!(v0_0 instanceof android.graphics.drawable.BitmapDrawable)) {
            super.onMeasure(p3, p4);
            return;
        } else {
            float v0_2 = ((android.graphics.drawable.BitmapDrawable) v0_0);
            this.setMeasuredDimension(((int) (((float) v0_2.getIntrinsicWidth()) * this.scale)), ((int) (((float) v0_2.getIntrinsicHeight()) * this.scale)));
            return;
        }
    }
}
