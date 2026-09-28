package com.bisimplex.firebooru.custom;
 class MyGlideModule$OkHttpProgressResponseBody$1 extends okio.ForwardingSource {
    final synthetic com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody this$0;
    long totalBytesRead;

    MyGlideModule$OkHttpProgressResponseBody$1(com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody p1, okio.Source p2)
    {
        this.this$0 = p1;
        super(p2);
        super.totalBytesRead = 0;
        return;
    }

    public long read(okio.Buffer p7, long p8)
    {
        long v7_1 = super.read(p7, p8);
        long v4 = com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody.-$$Nest$fgetresponseBody(this.this$0).contentLength();
        if (v7_1 != -1) {
            this.totalBytesRead = (this.totalBytesRead + v7_1);
        } else {
            this.totalBytesRead = v4;
        }
        com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody.-$$Nest$fgetprogressListener(this.this$0).update(com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody.-$$Nest$fgeturl(this.this$0), this.totalBytesRead, v4);
        return v7_1;
    }
}
