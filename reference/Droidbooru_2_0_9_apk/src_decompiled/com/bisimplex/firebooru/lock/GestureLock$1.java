package com.bisimplex.firebooru.lock;
 class GestureLock$1 implements android.view.ViewTreeObserver$OnGlobalLayoutListener {
    final synthetic com.bisimplex.firebooru.lock.GestureLock this$0;

    GestureLock$1(com.bisimplex.firebooru.lock.GestureLock p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onGlobalLayout()
    {
        this.this$0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0) == null) {
            int v0_1 = this.this$0.getMeasuredWidth();
            int v1_2 = this.this$0.getMeasuredHeight();
            if (v0_1 > v1_2) {
                v0_1 = v1_2;
            }
            int v1_4 = this.this$0;
            com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fputblockWidth(v1_4, ((v0_1 - (com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockGap(v1_4) * 2)) / 3));
            int v0_5 = this.this$0;
            com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fputgestureWidth(v0_5, ((com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockWidth(v0_5) * 3) + (com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockGap(this.this$0) * 2)));
            int v1_9 = new com.bisimplex.firebooru.lock.GestureLockView[9];
            com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fputlockers(this.this$0, v1_9);
            int v1_3 = 0;
            while (v1_3 < com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0).length) {
                com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[v1_3] = new com.bisimplex.firebooru.lock.GestureLockView(this.this$0.getContext());
                int v5_2 = (v1_3 + 1);
                com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[v1_3].setId(v5_2);
                com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[v1_3].setCOLOR_ERROR(com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetcolorError(this.this$0));
                com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[v1_3].setCOLOR_NORMAL(com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetcolorLine(this.this$0));
                int v4_21 = new android.widget.RelativeLayout$LayoutParams(com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockWidth(this.this$0), com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockWidth(this.this$0));
                if ((v1_3 % 3) != 0) {
                    v4_21.addRule(1, com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[(v1_3 - 1)].getId());
                }
                if (v1_3 > 2) {
                    v4_21.addRule(3, com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[(v1_3 - 3)].getId());
                }
                com.bisimplex.firebooru.lock.GestureLock v6_4;
                if ((v5_2 % 3) == 0) {
                    v6_4 = 0;
                } else {
                    v6_4 = com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockGap(this.this$0);
                }
                com.bisimplex.firebooru.lock.GestureLockView v7_2;
                if (v1_3 >= 6) {
                    v7_2 = 0;
                } else {
                    v7_2 = com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetblockGap(this.this$0);
                }
                v4_21.setMargins(0, 0, v6_4, v7_2);
                com.bisimplex.firebooru.lock.GestureLock v6_6 = this.this$0;
                v6_6.addView(com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(v6_6)[v1_3], v4_21);
                com.bisimplex.firebooru.lock.GestureLock.-$$Nest$fgetlockers(this.this$0)[v1_3].setMode(256);
                v1_3 = v5_2;
            }
        }
        return;
    }
}
