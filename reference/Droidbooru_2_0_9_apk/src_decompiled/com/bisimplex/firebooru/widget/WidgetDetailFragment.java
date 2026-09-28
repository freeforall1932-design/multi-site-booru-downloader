package com.bisimplex.firebooru.widget;
public class WidgetDetailFragment extends com.bisimplex.firebooru.fragment.DetailFragment {
    private int initialIndex;

    public WidgetDetailFragment()
    {
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        android.view.View v1_1 = super.onCreateView(p1, p2, p3);
        this.pager.setCurrentItem(this.initialIndex);
        return v1_1;
    }

    public void setInitialIndex(int p1)
    {
        this.initialIndex = p1;
        return;
    }
}
