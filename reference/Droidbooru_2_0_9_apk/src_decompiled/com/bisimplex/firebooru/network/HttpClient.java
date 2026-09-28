package com.bisimplex.firebooru.network;
public class HttpClient {
    private static com.franmontiel.persistentcookiejar.PersistentCookieJar cookieJar;
    private static com.google.gson.Gson gson;
    private static okhttp3.OkHttpClient okHttpClient;

    public HttpClient()
    {
        return;
    }

    public static okhttp3.OkHttpClient applyDNSSettings(okhttp3.OkHttpClient p5)
    {
        okhttp3.OkHttpClient$Builder v0_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getDNSType();
        try {
            if (v0_5 != com.bisimplex.firebooru.model.DNSType.System) {
                okhttp3.OkHttpClient$Builder v0_7;
                okhttp3.OkHttpClient$Builder v0_17 = com.bisimplex.firebooru.network.HttpClient$2.$SwitchMap$com$bisimplex$firebooru$model$DNSType[v0_5.ordinal()];
                if (v0_17 == 1) {
                    okhttp3.OkHttpClient$Builder v0_4 = new okhttp3.dnsoverhttps.DnsOverHttps$Builder().client(p5).url(okhttp3.HttpUrl.get("https://dns.google/dns-query"));
                    java.net.InetAddress[] v2_1 = new java.net.InetAddress[2];
                    v2_1[0] = java.net.InetAddress.getByName("8.8.4.4");
                    v2_1[1] = java.net.InetAddress.getByName("8.8.8.8");
                    v0_7 = v0_4.bootstrapDnsHosts(v2_1).build();
                } else {
                    if (v0_17 == 2) {
                        okhttp3.OkHttpClient$Builder v0_11 = new okhttp3.dnsoverhttps.DnsOverHttps$Builder().client(p5).url(okhttp3.HttpUrl.get("https://1.1.1.1/dns-query"));
                        java.net.InetAddress[] v2_2 = new java.net.InetAddress[2];
                        v2_2[0] = java.net.InetAddress.getByName("1.1.1.1");
                        v2_2[1] = java.net.InetAddress.getByName("1.0.0.1");
                        v0_7 = v0_11.bootstrapDnsHosts(v2_2).includeIPv6(0).build();
                    } else {
                    }
                }
                return p5.newBuilder().dns(v0_7).build();
            }
        } catch (okhttp3.OkHttpClient$Builder v0_15) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_15);
            return p5;
        }
        return p5;
    }

    public static void configureProxy(okhttp3.OkHttpClient$Builder p1)
    {
        com.bisimplex.firebooru.network.HttpClient.configureProxy(p1, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getProxyConfiguration());
        return;
    }

    public static void configureProxy(okhttp3.OkHttpClient$Builder p4, com.bisimplex.firebooru.model.ProxyConfiguration p5)
    {
        if ((p5 != null) && (p5.isValid())) {
            com.bisimplex.firebooru.network.HttpClient$1 v0_2 = new com.bisimplex.firebooru.network.HttpClient$1(p5);
            java.net.Proxy v1_2 = p5.getType();
            java.net.Proxy$Type v2 = java.net.Proxy$Type.DIRECT;
            if (v1_2 != 1) {
                if (v1_2 == 2) {
                    v2 = java.net.Proxy$Type.SOCKS;
                }
            } else {
                v2 = java.net.Proxy$Type.HTTP;
            }
            p4.proxy(new java.net.Proxy(v2, java.net.InetSocketAddress.createUnresolved(p5.getHost(), p5.getPort()))).proxyAuthenticator(v0_2);
        }
        return;
    }

    private static okhttp3.OkHttpClient$Builder getBasicBuilder()
    {
        return new okhttp3.OkHttpClient$Builder().connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS).writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS).readTimeout(120, java.util.concurrent.TimeUnit.SECONDS).cookieJar(com.bisimplex.firebooru.network.HttpClient.cookieJar);
    }

    public static declared_synchronized com.google.gson.Gson getGson()
    {
        if (com.bisimplex.firebooru.network.HttpClient.gson == null) {
            com.bisimplex.firebooru.network.HttpClient.gson = new com.google.gson.Gson();
        }
        return com.bisimplex.firebooru.network.HttpClient.gson;
    }

    public static declared_synchronized okhttp3.OkHttpClient getOkHttpClient()
    {
        if (com.bisimplex.firebooru.network.HttpClient.cookieJar == null) {
            com.bisimplex.firebooru.network.HttpClient.cookieJar = new com.bisimplex.firebooru.custom.CustomPersistentCookieJar(new com.franmontiel.persistentcookiejar.cache.SetCookieCache(), new com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()));
        }
        if (com.bisimplex.firebooru.network.HttpClient.okHttpClient == null) {
            okhttp3.OkHttpClient v1_10 = new okhttp3.OkHttpClient$Builder().addInterceptor(new com.bisimplex.firebooru.custom.LoggingInterceptor(1)).connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS).writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS).readTimeout(120, java.util.concurrent.TimeUnit.SECONDS).cookieJar(com.bisimplex.firebooru.network.HttpClient.cookieJar);
            if (!com.bisimplex.firebooru.network.HttpClient.proxySettingsAreValid()) {
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setProxyConfiguration(0);
            } else {
                com.bisimplex.firebooru.network.HttpClient.configureProxy(v1_10);
            }
            com.bisimplex.firebooru.network.HttpClient.okHttpClient = com.bisimplex.firebooru.network.HttpClient.applyDNSSettings(v1_10.build());
        }
        return com.bisimplex.firebooru.network.HttpClient.okHttpClient;
    }

    public static boolean proxySettingsAreValid()
    {
        boolean v0_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getProxyConfiguration();
        if (v0_2) {
            if (v0_2.getType() != 0) {
                return com.bisimplex.firebooru.network.HttpClient.proxySettingsAreValid(v0_2);
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public static boolean proxySettingsAreValid(com.bisimplex.firebooru.model.ProxyConfiguration p4)
    {
        try {
            com.bisimplex.firebooru.network.Utils v0_3 = new okhttp3.OkHttpClient$Builder().connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS).writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS).readTimeout(120, java.util.concurrent.TimeUnit.SECONDS).cookieJar(com.bisimplex.firebooru.network.HttpClient.cookieJar);
            com.bisimplex.firebooru.network.HttpClient.configureProxy(v0_3, p4);
            v0_3.build();
            return 1;
        } catch (int v4_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_2);
            return 0;
        }
    }
}
