package com.bisimplex.firebooru.activity;
 class MainActivity$5 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.activity.MainActivity this$0;

    MainActivity$5(com.bisimplex.firebooru.activity.MainActivity p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        io.objectbox.Box v0_1 = com.bisimplex.firebooru.activity.MainActivity.-$$Nest$fgetdownloadEntryBox(this.this$0).query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 1).build();
        java.util.List v1_0 = v0_1.find();
        v0_1.close();
        io.objectbox.Box v0_2 = v1_0.iterator();
        while (v0_2.hasNext()) {
            ((com.bisimplex.firebooru.model.DownloadEntry) v0_2.next()).setStatus(0);
        }
        com.bisimplex.firebooru.activity.MainActivity.-$$Nest$fgetdownloadEntryBox(this.this$0).put(v1_0);
        return;
    }
}
