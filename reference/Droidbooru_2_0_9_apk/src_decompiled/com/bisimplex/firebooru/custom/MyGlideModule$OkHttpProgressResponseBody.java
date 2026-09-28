package com.bisimplex.firebooru.custom;
 class MyGlideModule$OkHttpProgressResponseBody extends okhttp3.ResponseBody {
    private okio.BufferedSource bufferedSource;
    private final com.bisimplex.firebooru.custom.MyGlideModule$ResponseProgressListener progressListener;
    private final okhttp3.ResponseBody responseBody;
    private final okhttp3.HttpUrl url;

    static bridge synthetic com.bisimplex.firebooru.custom.MyGlideModule$ResponseProgressListener -$$Nest$fgetprogressListener(com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody p0)
    {
        return p0.progressListener;
    }

    static bridge synthetic okhttp3.ResponseBody -$$Nest$fgetresponseBody(com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody p0)
    {
        return p0.responseBody;
    }

    static bridge synthetic okhttp3.HttpUrl -$$Nest$fgeturl(com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody p0)
    {
        return p0.url;
    }

    MyGlideModule$OkHttpProgressResponseBody(okhttp3.HttpUrl p1, okhttp3.ResponseBody p2, com.bisimplex.firebooru.custom.MyGlideModule$ResponseProgressListener p3)
    {
        this.url = p1;
        this.responseBody = p2;
        this.progressListener = p3;
        return;
    }

    private okio.Source source(okio.Source p2)
    {
        return new com.bisimplex.firebooru.custom.MyGlideModule$OkHttpProgressResponseBody$1(this, p2);
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
