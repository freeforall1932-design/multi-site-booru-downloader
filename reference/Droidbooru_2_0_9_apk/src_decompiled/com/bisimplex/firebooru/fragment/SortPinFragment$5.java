package com.bisimplex.firebooru.fragment;
 class SortPinFragment$5 extends androidx.recyclerview.widget.ItemTouchHelper$SimpleCallback {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;

    SortPinFragment$5(com.bisimplex.firebooru.fragment.SortPinFragment p1, int p2, int p3)
    {
        this.this$0 = p1;
        super(p2, p3);
        return;
    }

    public boolean canDropOver(androidx.recyclerview.widget.RecyclerView p1, androidx.recyclerview.widget.RecyclerView$ViewHolder p2, androidx.recyclerview.widget.RecyclerView$ViewHolder p3)
    {
        return super.canDropOver(p1, p2, p3);
    }

    public boolean isLongPressDragEnabled()
    {
        return 0;
    }

    public boolean onMove(androidx.recyclerview.widget.RecyclerView p1, androidx.recyclerview.widget.RecyclerView$ViewHolder p2, androidx.recyclerview.widget.RecyclerView$ViewHolder p3)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetadapter(this.this$0).swap(p2.getBindingAdapterPosition(), p3.getBindingAdapterPosition());
        this.this$0.setEdited(1);
        return 1;
    }

    public void onSwiped(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        return;
    }
}
