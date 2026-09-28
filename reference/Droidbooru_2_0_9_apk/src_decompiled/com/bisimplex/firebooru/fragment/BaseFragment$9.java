package com.bisimplex.firebooru.fragment;
synthetic class BaseFragment$9 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$data$FailureType;

    static BaseFragment$9()
    {
        int[] v0_6 = new int[com.bisimplex.firebooru.data.FailureType.values().length];
        com.bisimplex.firebooru.fragment.BaseFragment$9.$SwitchMap$com$bisimplex$firebooru$data$FailureType = v0_6;
        com.bisimplex.firebooru.data.FailureType.InvalidCert.ordinal()[int v1_19] = 1;
        com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed.ordinal()[int v1_1] = 2;
        com.bisimplex.firebooru.data.FailureType.Connection.ordinal()[int v1_3] = 3;
        com.bisimplex.firebooru.data.FailureType.Unauthorized.ordinal()[int v1_5] = 4;
        com.bisimplex.firebooru.data.FailureType.Forbidden.ordinal()[int v1_7] = 5;
        com.bisimplex.firebooru.data.FailureType.NotFound.ordinal()[int v1_9] = 6;
        com.bisimplex.firebooru.data.FailureType.InvalidRecord.ordinal()[int v1_11] = 7;
        com.bisimplex.firebooru.data.FailureType.TooManyRequest.ordinal()[int v1_13] = 8;
        com.bisimplex.firebooru.data.FailureType.ServerError.ordinal()[int v1_16] = 9;
        try {
            com.bisimplex.firebooru.data.FailureType.ServiceUnavailable.ordinal()[int v1_18] = 10;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
