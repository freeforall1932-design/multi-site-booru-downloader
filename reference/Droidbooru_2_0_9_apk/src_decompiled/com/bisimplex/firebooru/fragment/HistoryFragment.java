package com.bisimplex.firebooru.fragment;
public class HistoryFragment extends com.bisimplex.firebooru.fragment.PostListFragment {

    public HistoryFragment()
    {
        return;
    }

    void askShowServers()
    {
        return;
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p3)
    {
        super.configureBar(p3);
        this.setIconToMenuItem(p3.getMenu(), 2131361987, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_trash);
        return;
    }

    public void deleteItems()
    {
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_2.setMessage(2131886275).setTitle(2131886395);
        v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.HistoryFragment$1(this));
        v0_2.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.HistoryFragment$2(this));
        v0_2.create().show();
        return;
    }

    protected int getMenuID()
    {
        return 2131689488;
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    protected com.bisimplex.firebooru.network.SourceType getSourceType()
    {
        return com.bisimplex.firebooru.network.SourceType.History;
    }

    public String getiOsFragmentName()
    {
        return "PostHistoryViewController";
    }

    protected void onMenuClick(android.view.MenuItem p2)
    {
        if (p2.getItemId() == 2131361987) {
            this.deleteItems();
        }
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.setTitle(2131886856);
        return;
    }

    protected void setPageLabelVisibility(boolean p2)
    {
        this.pageTextView.setVisibility(8);
        return;
    }

    protected void setSourceName(String p1)
    {
        super.setSourceName("");
        return;
    }

    public void setTitle(String p1)
    {
        super.setTitle(this.getString(2131886856));
        return;
    }
}
