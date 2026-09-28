package com.bisimplex.firebooru.fragment;
public abstract class ServerChangerFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.fragment.IServerChanger {

    public ServerChangerFragment()
    {
        return;
    }

    abstract void askShowServers();

    public void onCreateOptionsMenu(android.view.Menu p2, android.view.MenuInflater p3)
    {
        super.onCreateOptionsMenu(p2, p3);
        android.view.MenuItem v2_1 = p2.add(2131886861);
        v2_1.setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_server, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        v2_1.setShowAsAction(1);
        v2_1.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.ServerChangerFragment$1(this));
        return;
    }

    public void reloadData()
    {
        return;
    }

    public void selectedServer(androidx.fragment.app.DialogFragment p1, com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        return;
    }

    public void selectedServer(androidx.fragment.app.DialogFragment p1, com.bisimplex.firebooru.danbooru.ServerItem p2, com.bisimplex.firebooru.danbooru.FavoriteSortType p3)
    {
        return;
    }

    public void showServers(com.bisimplex.firebooru.danbooru.ServerItem p4, com.bisimplex.firebooru.danbooru.ServerItemType p5, String p6)
    {
        com.bisimplex.firebooru.view.ServersDialog v0_1 = new com.bisimplex.firebooru.view.ServersDialog();
        android.os.Bundle v1_1 = new android.os.Bundle(3);
        if (p4 != null) {
            v1_1.putInt("SERVER_SELECTED_ID", p4.getServerId());
        }
        if (p5 != null) {
            v1_1.putInt("SERVER_FILTER_TYPE_ID", p5.getValue());
        }
        if (!android.text.TextUtils.isEmpty(p6)) {
            v1_1.putString("SERVER_FILTER_TITLE", p6);
        } else {
            v1_1.putString("SERVER_FILTER_TITLE", this.getString(2131886861));
        }
        v0_1.setArguments(v1_1);
        v0_1.show(this.getActivity().getSupportFragmentManager(), "showServers");
        return;
    }
}
