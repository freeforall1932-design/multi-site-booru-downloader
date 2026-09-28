package com.bisimplex.firebooru.custom;
public class LoggingInterceptor implements okhttp3.Interceptor {
    private final boolean redacted;

    public LoggingInterceptor(boolean p1)
    {
        this.redacted = p1;
        return;
    }

    public String fixURL(okhttp3.HttpUrl p3)
    {
        if (this.redacted) {
            int v0_2 = p3.query();
            String v1 = p3.toString();
            if (!android.text.TextUtils.isEmpty(v0_2)) {
                int v0_1 = v1.indexOf("?");
                if (v0_1 > 0) {
                    return v1.substring(0, v0_1);
                }
            }
        }
        return p3.toString();
    }

    public okhttp3.Response intercept(okhttp3.Interceptor$Chain p8)
    {
        com.bisimplex.firebooru.network.Utils v0_0 = p8.request();
        String v1_0 = System.nanoTime();
        com.bisimplex.firebooru.network.Utils.getInstance().logInfo(String.format("Sending request to: %s", new Object[] {this.fixURL(v0_0.url())})));
        okhttp3.Response v8_1 = p8.proceed(v0_0);
        com.bisimplex.firebooru.network.Utils.getInstance().logInfo(String.format(java.util.Locale.US, "Received response for %s in %.1fms%n%s", new Object[] {this.fixURL(v8_1.request().url()), Double.valueOf((((double) (System.nanoTime() - v1_0)) / 4696837146684686336)), v8_1.headers()})));
        return v8_1;
    }
}
