package com.bisimplex.firebooru.fragment;
 class DynamicSettingsFragment$7 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DynamicSettingsFragment this$0;

    DynamicSettingsFragment$7(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteAllPostHistory();
        this.this$0.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }
}
