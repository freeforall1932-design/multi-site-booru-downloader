package com.bisimplex.firebooru.view;
 class SourceSpecsDialog$1 implements android.widget.AdapterView$OnItemSelectedListener {
    final synthetic com.bisimplex.firebooru.view.SourceSpecsDialog this$0;
    final synthetic android.widget.ArrayAdapter val$serverAdapter;

    SourceSpecsDialog$1(com.bisimplex.firebooru.view.SourceSpecsDialog p1, android.widget.ArrayAdapter p2)
    {
        this.this$0 = p1;
        this.val$serverAdapter = p2;
        return;
    }

    public void onItemSelected(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        if (p3 != null) {
            com.bisimplex.firebooru.danbooru.ServerItem v2_9 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.val$serverAdapter.getItem(0));
            if (v2_9 != null) {
                if (v2_9.getServerId() != 0) {
                    com.bisimplex.firebooru.danbooru.ServerItem v2_1 = new com.bisimplex.firebooru.danbooru.ServerItem();
                    v2_1.setServerName(this.this$0.getString(2131886145));
                    this.val$serverAdapter.insert(v2_1, 0);
                }
            } else {
                return;
            }
        } else {
            com.bisimplex.firebooru.danbooru.ServerItem v2_5 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.val$serverAdapter.getItem(0));
            if (v2_5 != null) {
                if (v2_5.getServerId() == 0) {
                    this.val$serverAdapter.remove(v2_5);
                }
            }
        }
        this.this$0.serverSpinner.setSelection(0, 0);
        return;
    }

    public void onNothingSelected(android.widget.AdapterView p1)
    {
        return;
    }
}
