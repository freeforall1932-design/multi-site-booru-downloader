package com.bisimplex.firebooru.services;
synthetic class UpdateMetadataService$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$services$ReloadStatusType;

    static UpdateMetadataService$1()
    {
        int[] v0_6 = new int[com.bisimplex.firebooru.services.ReloadStatusType.values().length];
        com.bisimplex.firebooru.services.UpdateMetadataService$1.$SwitchMap$com$bisimplex$firebooru$services$ReloadStatusType = v0_6;
        com.bisimplex.firebooru.services.ReloadStatusType.ServerNotFound.ordinal()[int v1_17] = 1;
        com.bisimplex.firebooru.services.ReloadStatusType.ServerNotSupported.ordinal()[int v1_1] = 2;
        com.bisimplex.firebooru.services.ReloadStatusType.Updated.ordinal()[int v1_3] = 3;
        com.bisimplex.firebooru.services.ReloadStatusType.URLMalformed.ordinal()[int v1_5] = 4;
        com.bisimplex.firebooru.services.ReloadStatusType.URLValid.ordinal()[int v1_7] = 5;
        com.bisimplex.firebooru.services.ReloadStatusType.Error.ordinal()[int v1_9] = 6;
        com.bisimplex.firebooru.services.ReloadStatusType.NotFound.ordinal()[int v1_11] = 7;
        com.bisimplex.firebooru.services.ReloadStatusType.Suppend.ordinal()[int v1_13] = 8;
        try {
            com.bisimplex.firebooru.services.ReloadStatusType.Cancel.ordinal()[int v1_16] = 9;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
