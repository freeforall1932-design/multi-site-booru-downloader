package com.bisimplex.firebooru.fragment;
public class EmptyFragment extends com.bisimplex.firebooru.fragment.BaseFragment {

    public EmptyFragment()
    {
        return;
    }

    protected int getLayoutID()
    {
        return 2131558431;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(this.getLayoutID(), p3, 0);
    }
}
