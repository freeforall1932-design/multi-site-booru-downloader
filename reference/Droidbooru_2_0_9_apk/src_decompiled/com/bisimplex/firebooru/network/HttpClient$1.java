package com.bisimplex.firebooru.network;
 class HttpClient$1 implements okhttp3.Authenticator {
    final synthetic com.bisimplex.firebooru.model.ProxyConfiguration val$configuration;

    HttpClient$1(com.bisimplex.firebooru.model.ProxyConfiguration p1)
    {
        this.val$configuration = p1;
        return;
    }

    public okhttp3.Request authenticate(okhttp3.Route p2, okhttp3.Response p3)
    {
        return p3.request().newBuilder().header("Proxy-Authorization", okhttp3.Credentials.basic(this.val$configuration.getUser(), this.val$configuration.getPass())).build();
    }
}
