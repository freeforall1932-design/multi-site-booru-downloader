package com.bisimplex.firebooru.custom;
public class BottomPaddingDecoration extends androidx.recyclerview.widget.RecyclerView$ItemDecoration {
    private final int bottomPadding;

    public BottomPaddingDecoration(int p1)
    {
        this.bottomPadding = p1;
        return;
    }

    public int getBottomPadding()
    {
        return this.bottomPadding;
    }

    public void getItemOffsets(android.graphics.Rect p1, android.view.View p2, androidx.recyclerview.widget.RecyclerView p3, androidx.recyclerview.widget.RecyclerView$State p4)
    {
        int v2_4 = ((androidx.recyclerview.widget.RecyclerView$LayoutParams) p2.getLayoutParams()).getViewLayoutPosition();
        int v3_3 = p3.getAdapter();
        if (v3_3 != 0) {
            if (v2_4 == (v3_3.getItemCount() - 1)) {
                p1.set(0, 0, 0, this.getBottomPadding());
            }
            return;
        } else {
            p1.set(0, 0, 0, 0);
            return;
        }
    }
}
