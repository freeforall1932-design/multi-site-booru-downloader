package com.bisimplex.firebooru.view;
 class SearchDialog$9 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$9(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p6)
    {
        com.google.android.material.chip.ChipGroup v0_7 = Integer.valueOf(Integer.parseInt(((String) ((com.google.android.material.chip.Chip) p6).getTag())));
        java.util.Iterator v1_1 = com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgetselectedServers(this.this$0).iterator();
        while (v1_1.hasNext()) {
            int v2_1 = ((com.bisimplex.firebooru.danbooru.ServerItem) v1_1.next());
            if (v2_1.getServerId() == v0_7.intValue()) {
            }
            if (v2_1 != 0) {
                com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgetselectedServers(this.this$0).remove(v2_1);
            }
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgetserverChipGroup(this.this$0).removeView(((com.google.android.material.chip.Chip) p6));
            return;
        }
        v2_1 = 0;
    }
}
