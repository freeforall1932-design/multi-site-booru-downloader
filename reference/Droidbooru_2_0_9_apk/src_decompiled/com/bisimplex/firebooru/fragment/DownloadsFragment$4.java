package com.bisimplex.firebooru.fragment;
 class DownloadsFragment$4 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.DownloadsFragment this$0;

    DownloadsFragment$4(com.bisimplex.firebooru.fragment.DownloadsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.fragment.DownloadsFragment v0_1 = com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$fgetdownloadEntryBox(this.this$0).query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 3).build();
        java.util.List v1_0 = v0_1.find();
        v0_1.close();
        com.bisimplex.firebooru.fragment.DownloadsFragment v0_2 = v1_0.iterator();
        while (v0_2.hasNext()) {
            com.bisimplex.firebooru.model.DownloadEntry v2_2 = ((com.bisimplex.firebooru.model.DownloadEntry) v0_2.next());
            v2_2.setStatus(0);
            v2_2.setError_message("");
        }
        com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$fgetdownloadEntryBox(this.this$0).put(v1_0);
        com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$mreloadAllData(this.this$0);
        return;
    }
}
