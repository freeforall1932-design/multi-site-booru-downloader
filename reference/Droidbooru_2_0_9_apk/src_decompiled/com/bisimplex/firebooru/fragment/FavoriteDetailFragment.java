package com.bisimplex.firebooru.fragment;
public class FavoriteDetailFragment extends com.bisimplex.firebooru.fragment.DetailFragment {

    public FavoriteDetailFragment()
    {
        return;
    }

    protected boolean getShouldHideDefaultStuff()
    {
        return 1;
    }

    public boolean shouldAddViewItemsToHistory()
    {
        return 0;
    }
}
