package com.bisimplex.firebooru.view;
public class ImageViewTouchViewPager extends androidx.viewpager.widget.ViewPager {
    private static final String TAG = "ImageViewTouchViewPager";
    public static final String VIEW_PAGER_OBJECT_TAG = "image#";
    java.util.List childs;
    private com.bisimplex.firebooru.view.ImageViewTouchViewPager$OnPageSelectedListener onPageSelectedListener;
    androidx.viewpager.widget.ViewPager$SimpleOnPageChangeListener pcl;
    private int previousPosition;

    static bridge synthetic com.bisimplex.firebooru.view.ImageViewTouchViewPager$OnPageSelectedListener -$$Nest$fgetonPageSelectedListener(com.bisimplex.firebooru.view.ImageViewTouchViewPager p0)
    {
        return p0.onPageSelectedListener;
    }

    static bridge synthetic int -$$Nest$fgetpreviousPosition(com.bisimplex.firebooru.view.ImageViewTouchViewPager p0)
    {
        return p0.previousPosition;
    }

    static bridge synthetic void -$$Nest$fputpreviousPosition(com.bisimplex.firebooru.view.ImageViewTouchViewPager p0, int p1)
    {
        p0.previousPosition = p1;
        return;
    }

    public ImageViewTouchViewPager(android.content.Context p1)
    {
        super(p1);
        super.init();
        return;
    }

    public ImageViewTouchViewPager(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.init();
        return;
    }

    private void init()
    {
        this.childs = new java.util.ArrayList(3);
        this.previousPosition = this.getCurrentItem();
        com.bisimplex.firebooru.view.ImageViewTouchViewPager$1 v0_4 = new com.bisimplex.firebooru.view.ImageViewTouchViewPager$1(this);
        this.pcl = v0_4;
        this.addOnPageChangeListener(v0_4);
        return;
    }

    protected boolean canScroll(android.view.View p7, boolean p8, int p9, int p10, int p11)
    {
        if (!(p7 instanceof com.github.chrisbanes.photoview.PhotoView)) {
            return super.canScroll(p7, p8, p9, p10, p11);
        } else {
            return ((com.github.chrisbanes.photoview.PhotoView) p7).canScrollHorizontally(p9);
        }
    }

    public android.view.View findPageByTag(Object p5)
    {
        if (p5 != null) {
            java.util.Iterator v1_1 = this.childs.iterator();
            while (v1_1.hasNext()) {
                android.view.View v2_1 = ((android.view.View) v1_1.next());
                if (v2_1.getTag() == p5) {
                    return v2_1;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    public java.util.List getAllChilds()
    {
        return new java.util.ArrayList(this.childs);
    }

    public androidx.viewpager.widget.ViewPager$SimpleOnPageChangeListener getOnPageChangeListener()
    {
        return this.pcl;
    }

    public boolean onInterceptTouchEvent(android.view.MotionEvent p2)
    {
        try {
            return super.onInterceptTouchEvent(p2);
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }

    public boolean onTouchEvent(android.view.MotionEvent p2)
    {
        try {
            return super.onTouchEvent(p2);
        } catch (int v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
    }

    public void onViewAdded(android.view.View p2)
    {
        super.onViewAdded(p2);
        this.childs.add(p2);
        return;
    }

    public void onViewRemoved(android.view.View p2)
    {
        super.onViewRemoved(p2);
        this.childs.remove(p2);
        return;
    }

    public void setOnPageSelectedListener(com.bisimplex.firebooru.view.ImageViewTouchViewPager$OnPageSelectedListener p1)
    {
        this.onPageSelectedListener = p1;
        return;
    }
}
