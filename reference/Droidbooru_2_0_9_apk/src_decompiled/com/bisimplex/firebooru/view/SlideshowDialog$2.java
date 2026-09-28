package com.bisimplex.firebooru.view;
 class SlideshowDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.SlideshowDialog this$0;

    SlideshowDialog$2(com.bisimplex.firebooru.view.SlideshowDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        if (com.bisimplex.firebooru.view.SlideshowDialog.-$$Nest$fgetmListener(this.this$0) != null) {
            com.bisimplex.firebooru.view.SlideshowDialog.-$$Nest$fgetmListener(this.this$0).onDialogStartSlideshow(this.this$0, (com.bisimplex.firebooru.view.SlideshowDialog.-$$Nest$fgetseekBar(this.this$0).getProgress() + com.bisimplex.firebooru.view.SlideshowDialog.-$$Nest$sfgetMIN_SECONDS()));
        }
        return;
    }
}
