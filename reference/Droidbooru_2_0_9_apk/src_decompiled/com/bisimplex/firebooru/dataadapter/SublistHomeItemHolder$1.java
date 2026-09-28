package com.bisimplex.firebooru.dataadapter;
 class SublistHomeItemHolder$1 implements com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder this$0;

    SublistHomeItemHolder$1(com.bisimplex.firebooru.dataadapter.SublistHomeItemHolder p1)
    {
        this.this$0 = p1;
        return;
    }

    public void attachedToWindow(int p1)
    {
        return;
    }

    public void itemClick(android.view.View p2, int p3)
    {
        if (this.this$0.listenerWeakReference.get() != null) {
            ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.this$0.listenerWeakReference.get()).sublistClick(this.this$0.getAdapterPosition(), p3);
        }
        return;
    }

    public boolean onLongClick(android.view.View p2, int p3)
    {
        if (this.this$0.listenerWeakReference.get() == null) {
            return 0;
        } else {
            return ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.this$0.listenerWeakReference.get()).sublistLongClick(this.this$0.getAdapterPosition(), p3);
        }
    }
}
