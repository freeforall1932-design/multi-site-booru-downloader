package com.bisimplex.firebooru.network;
synthetic class HttpClient$2 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$DNSType;

    static HttpClient$2()
    {
        int[] v0_3 = new int[com.bisimplex.firebooru.model.DNSType.values().length];
        com.bisimplex.firebooru.network.HttpClient$2.$SwitchMap$com$bisimplex$firebooru$model$DNSType = v0_3;
        com.bisimplex.firebooru.model.DNSType.Google.ordinal()[int v1_3] = 1;
        try {
            com.bisimplex.firebooru.model.DNSType.CloudFlare.ordinal()[int v1_1] = 2;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
