package com.bisimplex.firebooru.view;
 class BooruPhotoView$2 implements com.github.chrisbanes.photoview.OnSingleFlingListener {
    final synthetic com.bisimplex.firebooru.view.BooruPhotoView this$0;

    BooruPhotoView$2(com.bisimplex.firebooru.view.BooruPhotoView p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onFling(android.view.MotionEvent p3, android.view.MotionEvent p4, float p5, float p6)
    {
        try {
            float v0_1 = (p4.getY() - p3.getY());
        } catch (boolean v3_15) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_15);
            return 0;
        }
        if (Math.abs((p4.getX() - p3.getX())) <= Math.abs(v0_1)) {
            if ((Math.abs(v0_1) <= 1112014848) || (Math.abs(p6) <= 1125515264)) {
                return 0;
            } else {
                if (v0_1 <= 0) {
                    return com.bisimplex.firebooru.view.BooruPhotoView.-$$Nest$fgetlistener(this.this$0).executeGesture(com.bisimplex.firebooru.view.GestureType.SwipeToUp);
                } else {
                    return com.bisimplex.firebooru.view.BooruPhotoView.-$$Nest$fgetlistener(this.this$0).executeGesture(com.bisimplex.firebooru.view.GestureType.SwipeToDown);
                }
            }
        } else {
            return 0;
        }
    }
}
