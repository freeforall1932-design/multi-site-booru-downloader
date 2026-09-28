package com.bisimplex.firebooru.dataadapter;
synthetic class SettingsDataAdapter$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$FileNamePartType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType;

    static SettingsDataAdapter$1()
    {
        int[] v0_3 = new int[com.bisimplex.firebooru.model.FileNamePartType.values().length];
        com.bisimplex.firebooru.dataadapter.SettingsDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType = v0_3;
        com.bisimplex.firebooru.model.FileNamePartType.MD5.ordinal()[int v2_3] = 1;
        com.bisimplex.firebooru.model.FileNamePartType.Domain.ordinal()[int v3_1] = 2;
        com.bisimplex.firebooru.model.FileNamePartType.Tags.ordinal()[int v4_1] = 3;
        try {
            com.bisimplex.firebooru.model.FileNamePartType.ID.ordinal()[int v4_3] = 4;
        } catch (NoSuchFieldError) {
        }
        int v3_6 = new int[com.bisimplex.firebooru.model.VideoDecoderType.values().length];
        com.bisimplex.firebooru.dataadapter.SettingsDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType = v3_6;
        com.bisimplex.firebooru.model.VideoDecoderType.Base.ordinal()[int v4_5] = 1;
        com.bisimplex.firebooru.model.VideoDecoderType.VLC.ordinal()[int v3_8] = 2;
        try {
            com.bisimplex.firebooru.model.VideoDecoderType.EXO.ordinal()[int v1_2] = 3;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
