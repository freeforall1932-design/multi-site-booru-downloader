package com.bisimplex.firebooru.activity;
 class MainActivity$2 extends android.content.BroadcastReceiver {
    final synthetic com.bisimplex.firebooru.activity.MainActivity this$0;

    MainActivity$2(com.bisimplex.firebooru.activity.MainActivity p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v2_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.this$0);
        v2_1.setTitle(2131887231);
        v2_1.setMessage(2131886436);
        v2_1.setPositiveButton(2131887271, new com.bisimplex.firebooru.activity.MainActivity$2$1(this));
        v2_1.setNegativeButton(2131886982, new com.bisimplex.firebooru.activity.MainActivity$2$2(this));
        v2_1.show();
        return;
    }
}
