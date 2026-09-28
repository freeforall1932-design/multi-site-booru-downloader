package com.bisimplex.firebooru.view;
 class SlideshowDialog$1 implements android.widget.SeekBar$OnSeekBarChangeListener {
    final synthetic com.bisimplex.firebooru.view.SlideshowDialog this$0;

    SlideshowDialog$1(com.bisimplex.firebooru.view.SlideshowDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onProgressChanged(android.widget.SeekBar p1, int p2, boolean p3)
    {
        if (p3) {
            com.bisimplex.firebooru.view.SlideshowDialog.-$$Nest$mupdateTimeLabel(this.this$0);
        }
        return;
    }

    public void onStartTrackingTouch(android.widget.SeekBar p1)
    {
        return;
    }

    public void onStopTrackingTouch(android.widget.SeekBar p1)
    {
        return;
    }
}
