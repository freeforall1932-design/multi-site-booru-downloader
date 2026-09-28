package com.bisimplex.firebooru.custom;
 class MyGlideModule$DispatchingProgressListener implements com.bisimplex.firebooru.custom.MyGlideModule$ResponseProgressListener {
    private static final java.util.WeakHashMap LISTENERS;
    private static final java.util.WeakHashMap PROGRESSES;
    private final android.os.Handler handler;

    static MyGlideModule$DispatchingProgressListener()
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.LISTENERS = new java.util.WeakHashMap();
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.PROGRESSES = new java.util.WeakHashMap();
        return;
    }

    MyGlideModule$DispatchingProgressListener()
    {
        this.handler = new android.os.Handler(android.os.Looper.getMainLooper());
        return;
    }

    static void expect(String p1, com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener p2)
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.LISTENERS.put(p1, p2);
        return;
    }

    public static com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener find(String p1)
    {
        return ((com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener) com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.LISTENERS.get(p1));
    }

    static void forget(String p1)
    {
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.LISTENERS.remove(p1);
        com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.PROGRESSES.remove(p1);
        return;
    }

    static synthetic void lambda$update$0(com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener p0, long p1, long p3)
    {
        p0.onProgress(p1, p3);
        return;
    }

    private boolean needsDispatch(String p5, long p6, long p8, float p10)
    {
        if ((p10 != 0) && ((p6 != 0) && (p8 != p6))) {
            Long v6_5 = ((long) (((((float) p6) * 1120403456) / ((float) p8)) / p10));
            java.util.WeakHashMap v8_1 = com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.PROGRESSES;
            long v9_1 = ((Long) v8_1.get(p5));
            if ((v9_1 != 0) && (v6_5 == v9_1.longValue())) {
                return 0;
            } else {
                v8_1.put(p5, Long.valueOf(v6_5));
            }
        }
        return 1;
    }

    public void update(okhttp3.HttpUrl p9, long p10, long p12)
    {
        String v1 = p9.toString();
        com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener v9_3 = ((com.bisimplex.firebooru.custom.MyGlideModule$UIonProgressListener) com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.LISTENERS.get(v1));
        if (v9_3 != null) {
            if (p12 <= p10) {
                com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener.forget(v1);
            }
            if (this.needsDispatch(v1, p10, p12, v9_3.getGranualityPercentage())) {
                this.handler.post(new com.bisimplex.firebooru.custom.MyGlideModule$DispatchingProgressListener$$ExternalSyntheticLambda0(v9_3, p10, p12));
            }
        }
        return;
    }
}
