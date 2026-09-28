package com.bisimplex.firebooru.dataadapter;
public class HomeItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder {
    ref.WeakReference listenerWeakReference;

    public HomeItemHolder(android.view.View p1, com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener p2)
    {
        super(p1);
        super.listenerWeakReference = new ref.WeakReference(p2);
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.data.ItemActionType p3)
    {
        if (this.listenerWeakReference.get() != null) {
            int v0_2 = this.getBindingAdapterPosition();
            if (v0_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener) this.listenerWeakReference.get()).actionClick(v0_2, p3);
            }
        }
        return;
    }
}
