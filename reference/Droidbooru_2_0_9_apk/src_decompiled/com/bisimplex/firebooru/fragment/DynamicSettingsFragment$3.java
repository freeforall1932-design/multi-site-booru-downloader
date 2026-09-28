package com.bisimplex.firebooru.fragment;
 class DynamicSettingsFragment$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DynamicSettingsFragment this$0;

    DynamicSettingsFragment$3(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteAllFavorites();
        com.bisimplex.firebooru.fragment.DynamicSettingsFragment.-$$Nest$mclearCache(this.this$0);
        this.this$0.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }
}
