package com.bisimplex.firebooru.fragment;
public class PoolServersFragment extends com.bisimplex.firebooru.fragment.ServersFragment {

    public PoolServersFragment()
    {
        return;
    }

    public void addNewItem()
    {
        return;
    }

    protected com.bisimplex.firebooru.fragment.ServerListDataAdapter configureAdapter()
    {
        com.bisimplex.firebooru.fragment.ServerListDataAdapter v0 = super.configureAdapter();
        v0.setEnableDelete(0);
        v0.setHighlightSelected(0);
        return v0;
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p2)
    {
        super.configureBar(p2);
        p2.setTitle(2131887148);
        p2.getMenu().clear();
        return;
    }

    protected void deleteServerAt(int p1)
    {
        return;
    }

    protected void editServerAt(int p1)
    {
        return;
    }

    protected java.util.List loadData()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServersWithPools();
    }

    public boolean onContextItemSelected(android.view.MenuItem p1)
    {
        return 0;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.addButton.setVisibility(8);
        return;
    }

    protected void selectServerAt(int p3)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(this.adapter.getItem(p3).getServerId());
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.PoolsFragment());
            return;
        } else {
            return;
        }
    }
}
