package com.bisimplex.firebooru.fragment;
public class MainMenuFragment extends androidx.fragment.app.ListFragment {

    public MainMenuFragment()
    {
        return;
    }

    private void switchFragment(androidx.fragment.app.Fragment p2)
    {
        if (this.getActivity() != null) {
            ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()).switchContent(p2);
            return;
        } else {
            return;
        }
    }

    public void launchBrowser(String p2, boolean p3)
    {
        if (!this.isDetached()) {
            com.bisimplex.firebooru.activity.MainActivity v0_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v0_2 != null) {
                v0_2.launchBrowser(p2, p3);
                return;
            }
        }
        return;
    }

    public void loadMenuOptions()
    {
        return;
    }

    public void onActivityCreated(android.os.Bundle p1)
    {
        super.onActivityCreated(p1);
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        android.view.View v1_1 = p1.inflate(2131558438, 0);
        this.loadMenuOptions();
        return v1_1;
    }

    public void onListItemClick(android.widget.ListView p1, android.view.View p2, int p3, long p4)
    {
        com.bisimplex.firebooru.network.SourceQuery v1_2;
        com.bisimplex.firebooru.activity.MainActivity v2_2 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        switch (Integer.parseInt(p2.getTag().toString())) {
            case 0:
                v1_2 = new com.bisimplex.firebooru.fragment.HomeFragment();
                break;
            case 1:
                if (v2_2 == null) {
                    v1_2 = 0;
                } else {
                    com.bisimplex.firebooru.network.SourceQuery v1_21 = new com.bisimplex.firebooru.network.SourceQuery();
                    v1_21.setDisableAutoLoad(1);
                    v2_2.searchQuery(v1_21, com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                }
                break;
            case 2:
                if (v2_2 == null) {
                } else {
                    v2_2.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131886144)), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                }
                break;
            case 3:
                v1_2 = new com.bisimplex.firebooru.fragment.FavoriteListFragment();
                break;
            case 4:
                v1_2 = new com.bisimplex.firebooru.fragment.ServersFragment();
                break;
            case 5:
                v1_2 = new com.bisimplex.firebooru.fragment.TagsBlackListFragment();
                break;
            case 6:
            default:
                break;
            case 7:
                this.launchBrowser(this.getString(2131886265), 1);
                break;
            case 8:
                this.launchBrowser(this.getString(2131887223), 1);
                break;
            case 9:
                this.launchBrowser(this.getString(2131886651), 1);
                break;
            case 10:
                if (v2_2 == null) {
                } else {
                    v2_2.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getTopString()), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                }
                break;
            case 11:
                if (v2_2 == null) {
                } else {
                    v2_2.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getBestString()), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                }
                break;
            case 12:
                v1_2 = new com.bisimplex.firebooru.fragment.HistoryFragment();
                break;
        }
        if (v1_2 != null) {
            this.switchFragment(v1_2);
        }
        return;
    }
}
