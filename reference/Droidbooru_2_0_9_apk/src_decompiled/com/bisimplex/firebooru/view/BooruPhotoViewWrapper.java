package com.bisimplex.firebooru.view;
public class BooruPhotoViewWrapper extends android.widget.FrameLayout {
    private boolean isParentInterceptionDisallowed;

    public BooruPhotoViewWrapper(android.content.Context p1)
    {
        super(p1);
        super.isParentInterceptionDisallowed = 0;
        return;
    }

    public BooruPhotoViewWrapper(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.isParentInterceptionDisallowed = 0;
        return;
    }

    public BooruPhotoViewWrapper(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.isParentInterceptionDisallowed = 0;
        return;
    }

    public BooruPhotoViewWrapper(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.isParentInterceptionDisallowed = 0;
        return;
    }

    public boolean onInterceptTouchEvent(android.view.MotionEvent p6)
    {
        android.view.ViewParent v0 = this.getParent();
        if (v0 != null) {
            boolean v2_0;
            if (this.getChildCount() <= 0) {
                v2_0 = 0;
            } else {
                boolean v2_3 = this.getChildAt(0);
                if (!(v2_3 instanceof com.bisimplex.firebooru.view.BooruPhotoView)) {
                } else {
                    v2_0 = ((com.bisimplex.firebooru.view.BooruPhotoView) v2_3).isZoomingIn();
                }
            }
            int v6_2;
            int v3_0 = 1;
            if (p6.getPointerCount() <= 1) {
                v6_2 = 0;
            } else {
                v6_2 = 1;
            }
            if ((!this.isParentInterceptionDisallowed) && ((v6_2 == 0) && (!v2_0))) {
                v3_0 = 0;
            }
            v0.requestDisallowInterceptTouchEvent(v3_0);
        }
        return 0;
    }

    public void requestDisallowInterceptTouchEvent(boolean p2)
    {
        this.isParentInterceptionDisallowed = p2;
        if ((this.getParent() != null) && (p2)) {
            this.getParent().requestDisallowInterceptTouchEvent(p2);
        }
        return;
    }
}
