package com.bisimplex.firebooru.fragment;
 class FavoriteListFragment$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.FavoriteListFragment this$0;

    FavoriteListFragment$2(com.bisimplex.firebooru.fragment.FavoriteListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteAllFavorites();
        this.this$0.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
        this.this$0.reloadData();
        return;
    }
}
