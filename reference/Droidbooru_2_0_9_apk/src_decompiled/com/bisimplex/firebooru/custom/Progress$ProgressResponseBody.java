package com.bisimplex.firebooru.custom;
 class Progress$ProgressResponseBody extends okhttp3.ResponseBody {
    private okio.BufferedSource bufferedSource;
    private final com.bisimplex.firebooru.custom.Progress$ProgressListener progressListener;
    private final okhttp3.ResponseBody responseBody;

    static bridge synthetic com.bisimplex.firebooru.custom.Progress$ProgressListener -$$Nest$fgetprogressListener(com.bisimplex.firebooru.custom.Progress$ProgressResponseBody p0)
    {
        return p0.progressListener;
    }

    static bridge synthetic okhttp3.ResponseBody -$$Nest$fgetresponseBody(com.bisimplex.firebooru.custom.Progress$ProgressResponseBody p0)
    {
        return p0.responseBody;
    }

    Progress$ProgressResponseBody(okhttp3.ResponseBody p1, com.bisimplex.firebooru.custom.Progress$ProgressListener p2)
    {
        this.responseBody = p1;
        this.progressListener = p2;
        return;
    }

    private okio.Source source(okio.Source p2)
    {
        return new com.bisimplex.firebooru.custom.Progress$ProgressResponseBody$1(this, p2);
    }

    public long contentLength()
    {
        return this.responseBody.contentLength();
    }

    public okhttp3.MediaType contentType()
    {
        return this.responseBody.contentType();
    }

    public okio.BufferedSource source()
    {
        if (this.bufferedSource == null) {
            this.bufferedSource = okio.Okio.buffer(this.source(this.responseBody.source()));
        }
        return this.bufferedSource;
    }
}
