package com.bisimplex.firebooru.fragment;
public class TagHistoryFragment extends com.bisimplex.firebooru.fragment.ListBaseFragment {
    private com.bisimplex.firebooru.fragment.TagHistoryFragment$SimpleListDataAdapter adapter;

    public TagHistoryFragment()
    {
        return;
    }

    private void copyItem(com.bisimplex.firebooru.model.TagHistory p2)
    {
        if (p2 != 0) {
            this.copyToClipboard(p2.search);
            this.showMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
            return;
        } else {
            return;
        }
    }

    private void deleteItem(com.bisimplex.firebooru.model.TagHistory p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteHistoryItemById(p3.getId());
        this.adapter.remove(p3);
        this.reloadList();
        this.showMessage(2131886396, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }

    private void favoriteItem(com.bisimplex.firebooru.model.TagHistory p5)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().starHistoryItem(p5.getId().longValue(), 1);
        p5.isFavoritedHistoryItem = 1;
        this.reloadList();
        return;
    }

    private void reloadList()
    {
        this.adapter.notifyDataSetChanged();
        this.getListView().invalidateViews();
        return;
    }

    private void removeFavoriteItem(com.bisimplex.firebooru.model.TagHistory p5)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().starHistoryItem(p5.getId().longValue(), 0);
        p5.isFavoritedHistoryItem = 0;
        this.reloadList();
        return;
    }

    public void copyToClipboard(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            android.content.ClipboardManager v0_1 = this.getActivity();
            if (v0_1 != null) {
                ((android.content.ClipboardManager) v0_1.getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newPlainText("Copied Text", p3));
                return;
            }
        }
        return;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public void onActivityCreated(android.os.Bundle p2)
    {
        super.onActivityCreated(p2);
        this.adapter = new com.bisimplex.firebooru.fragment.TagHistoryFragment$SimpleListDataAdapter(this, this.getActivity());
        this.adapter.addAll(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadHistory());
        this.setListAdapter(this.adapter);
        this.registerForContextMenu(this.getListView());
        return;
    }

    public boolean onContextItemSelected(android.view.MenuItem p4)
    {
        if (((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()) != null) {
            com.bisimplex.firebooru.model.TagHistory v0_2 = ((com.bisimplex.firebooru.model.TagHistory) this.adapter.getItem(((android.widget.AdapterView$AdapterContextMenuInfo) p4.getMenuInfo()).position));
            switch (p4.getItemId()) {
                case 2131361970:
                    this.copyItem(v0_2);
                    return 1;
                case 2131361987:
                    this.deleteItem(v0_2);
                    return 1;
                case 2131362105:
                    this.favoriteItem(v0_2);
                    return 1;
                case 2131362457:
                    this.removeFavoriteItem(v0_2);
                    return 1;
                default:
                    return super.onContextItemSelected(p4);
            }
        } else {
            return 0;
        }
    }

    public void onCreateContextMenu(android.view.ContextMenu p4, android.view.View p5, android.view.ContextMenu$ContextMenuInfo p6)
    {
        super.onCreateContextMenu(p4, p5, p6);
        int v6_1 = ((com.bisimplex.firebooru.model.TagHistory) this.adapter.getItem(((android.widget.AdapterView$AdapterContextMenuInfo) p6).position));
        this.getActivity().getMenuInflater().inflate(2131689478, p4);
        if (v6_1.isFavoritedHistoryItem != 0) {
            p4.findItem(2131362105).setVisible(0);
            p4.findItem(2131362457).setVisible(1);
            return;
        } else {
            p4.findItem(2131362105).setVisible(1);
            p4.findItem(2131362457).setVisible(0);
            return;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        return p1.inflate(2131558446, 0);
    }

    public void onListItemClick(android.widget.ListView p1, android.view.View p2, int p3, long p4)
    {
        com.bisimplex.firebooru.activity.MainActivity v2_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        v2_1.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(((com.bisimplex.firebooru.model.TagHistory) this.adapter.getItem(p3)).search), 0);
        v2_1.closeDrawer();
        return;
    }
}
