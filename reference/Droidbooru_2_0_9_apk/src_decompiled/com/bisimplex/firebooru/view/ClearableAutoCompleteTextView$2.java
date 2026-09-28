package com.bisimplex.firebooru.view;
 class ClearableAutoCompleteTextView$2 implements android.view.View$OnTouchListener {
    final synthetic com.bisimplex.firebooru.view.ClearableAutoCompleteTextView this$0;

    ClearableAutoCompleteTextView$2(com.bisimplex.firebooru.view.ClearableAutoCompleteTextView p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onTouch(android.view.View p4, android.view.MotionEvent p5)
    {
        com.bisimplex.firebooru.view.ClearableAutoCompleteTextView v4_0 = this.this$0;
        if (v4_0.getCompoundDrawables()[2] != null) {
            if (p5.getAction() == 1) {
                if (p5.getX() > ((float) ((v4_0.getWidth() - v4_0.getPaddingRight()) - this.this$0.imgClearButton.getIntrinsicWidth()))) {
                    com.bisimplex.firebooru.view.ClearableAutoCompleteTextView.-$$Nest$fgetonClearListener(this.this$0).onClear();
                    this.this$0.justCleared = 1;
                }
                return 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
