package com.bisimplex.firebooru.view;
 class ImageViewTouchViewPager$1 extends androidx.viewpager.widget.ViewPager$SimpleOnPageChangeListener {
    final synthetic com.bisimplex.firebooru.view.ImageViewTouchViewPager this$0;

    ImageViewTouchViewPager$1(com.bisimplex.firebooru.view.ImageViewTouchViewPager p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onPageScrollStateChanged(int p4)
    {
        if ((p4 == 2) && (com.bisimplex.firebooru.view.ImageViewTouchViewPager.-$$Nest$fgetpreviousPosition(this.this$0) != this.this$0.getCurrentItem())) {
            try {
                com.bisimplex.firebooru.view.ImageViewTouchViewPager v4_3 = ((it.sephiroth.android.library.imagezoom.ImageViewTouch) this.this$0.findViewWithTag(new StringBuilder("image#").append(this.this$0.getCurrentItem()).toString()));
            } catch (com.bisimplex.firebooru.view.ImageViewTouchViewPager v4_4) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_4);
                android.util.Log.e("ImageViewTouchViewPager", "This view pager should have only ImageViewTouch as a children.", v4_4);
            }
            if (v4_3 != null) {
                v4_3.zoomTo(1065353216, 300);
            }
            com.bisimplex.firebooru.view.ImageViewTouchViewPager v4_6 = this.this$0;
            com.bisimplex.firebooru.view.ImageViewTouchViewPager.-$$Nest$fputpreviousPosition(v4_6, v4_6.getCurrentItem());
            return;
        }
        return;
    }

    public void onPageSelected(int p2)
    {
        if (com.bisimplex.firebooru.view.ImageViewTouchViewPager.-$$Nest$fgetonPageSelectedListener(this.this$0) != null) {
            com.bisimplex.firebooru.view.ImageViewTouchViewPager.-$$Nest$fgetonPageSelectedListener(this.this$0).onPageSelected(p2);
        }
        return;
    }
}
