package com.bisimplex.firebooru.custom;
 class Progress$1 implements com.bisimplex.firebooru.custom.Progress$ProgressListener {
    boolean firstUpdate;
    final synthetic com.bisimplex.firebooru.custom.Progress this$0;

    Progress$1(com.bisimplex.firebooru.custom.Progress p1)
    {
        this.this$0 = p1;
        this.firstUpdate = 1;
        return;
    }

    public void update(long p5, long p7, boolean p9)
    {
        if (p9 == null) {
            if (this.firstUpdate) {
                this.firstUpdate = 0;
                if (p7 != -1) {
                    System.out.format("content-length: %d\n", new Object[] {Long.valueOf(p7)}));
                } else {
                    System.out.println("content-length: unknown");
                }
            }
            System.out.println(p5);
            if (p7 != -1) {
                System.out.format("%d%% done\n", new Object[] {Long.valueOf(((p5 * 100) / p7))}));
            }
            return;
        } else {
            System.out.println("completed");
            return;
        }
    }
}
