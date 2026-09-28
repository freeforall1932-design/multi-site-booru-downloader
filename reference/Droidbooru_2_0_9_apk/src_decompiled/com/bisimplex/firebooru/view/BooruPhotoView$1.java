package com.bisimplex.firebooru.view;
 class BooruPhotoView$1 implements android.view.GestureDetector$OnDoubleTapListener {
    final synthetic com.bisimplex.firebooru.view.BooruPhotoView this$0;

    BooruPhotoView$1(com.bisimplex.firebooru.view.BooruPhotoView p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onDoubleTap(android.view.MotionEvent p2)
    {
        if (com.bisimplex.firebooru.view.BooruPhotoView.-$$Nest$fgetlistener(this.this$0) == null) {
            return 0;
        } else {
            return com.bisimplex.firebooru.view.BooruPhotoView.-$$Nest$fgetlistener(this.this$0).executeGesture(com.bisimplex.firebooru.view.GestureType.DoubleTap);
        }
    }

    public boolean onDoubleTapEvent(android.view.MotionEvent p1)
    {
        return 0;
    }

    public boolean onSingleTapConfirmed(android.view.MotionEvent p4)
    {
        com.bisimplex.firebooru.view.BooruPhotoView v0_1 = this.this$0.getDisplayRect();
        float v1_2 = p4.getX();
        int v4_5 = p4.getY();
        if ((v0_1 == null) || (!v0_1.contains(v1_2, v4_5))) {
            return 0;
        } else {
            this.this$0.onPhotoTap(this.this$0, ((v1_2 - v0_1.left) / v0_1.width()), ((v4_5 - v0_1.top) / v0_1.height()));
            return 1;
        }
    }
}
