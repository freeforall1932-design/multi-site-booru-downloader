package com.bisimplex.firebooru.custom;
public final class Progress {

    public Progress()
    {
        return;
    }

    static synthetic okhttp3.Response lambda$run$0(com.bisimplex.firebooru.custom.Progress$ProgressListener p2, okhttp3.Interceptor$Chain p3)
    {
        okhttp3.ResponseBody v3_1 = p3.proceed(p3.request());
        return v3_1.newBuilder().body(new com.bisimplex.firebooru.custom.Progress$ProgressResponseBody(v3_1.body(), p2)).build();
    }

    public static varargs void main(String[] p0)
    {
        new com.bisimplex.firebooru.custom.Progress().run();
        return;
    }

    public void run()
    {
        Throwable v1_3 = new okhttp3.OkHttpClient$Builder().addNetworkInterceptor(new com.bisimplex.firebooru.custom.Progress$$ExternalSyntheticLambda0(new com.bisimplex.firebooru.custom.Progress$1(this))).build().newCall(new okhttp3.Request$Builder().url("https://publicobject.com/helloworld.txt").build()).execute();
        try {
            if (!v1_3.isSuccessful()) {
                throw new java.io.IOException(new StringBuilder("Unexpected code ").append(v1_3).toString());
            } else {
                System.out.println(v1_3.body().string());
                if (v1_3 != null) {
                    v1_3.close();
                }
                return;
            }
        } catch (String v0_3) {
            if (v1_3 != null) {
                try {
                    v1_3.close();
                } catch (Throwable v1_4) {
                    v0_3.addSuppressed(v1_4);
                }
            }
            throw v0_3;
        }
    }
}
