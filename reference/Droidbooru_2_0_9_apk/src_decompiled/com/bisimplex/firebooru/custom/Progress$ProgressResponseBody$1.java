package com.bisimplex.firebooru.custom;
 class Progress$ProgressResponseBody$1 extends okio.ForwardingSource {
    final synthetic com.bisimplex.firebooru.custom.Progress$ProgressResponseBody this$0;
    long totalBytesRead;

    Progress$ProgressResponseBody$1(com.bisimplex.firebooru.custom.Progress$ProgressResponseBody p1, okio.Source p2)
    {
        this.this$0 = p1;
        super(p2);
        super.totalBytesRead = 0;
        return;
    }

    public long read(okio.Buffer p8, long p9)
    {
        long v2_2;
        long v8_1 = super.read(p8, p9);
        int v10_1 = v8_1 cmp -1;
        if (v10_1 == 0) {
            v2_2 = 0;
        } else {
            v2_2 = v8_1;
        }
        int v10_0;
        this.totalBytesRead = (this.totalBytesRead + v2_2);
        com.bisimplex.firebooru.custom.Progress$ProgressListener v1 = com.bisimplex.firebooru.custom.Progress$ProgressResponseBody.-$$Nest$fgetprogressListener(this.this$0);
        long v2_0 = this.totalBytesRead;
        long vtmp4 = com.bisimplex.firebooru.custom.Progress$ProgressResponseBody.-$$Nest$fgetresponseBody(this.this$0).contentLength();
        if (v10_1 != 0) {
            v10_0 = 0;
        } else {
            v10_0 = 1;
        }
        v1.update(v2_0, vtmp4, v10_0);
        return v8_1;
    }
}
