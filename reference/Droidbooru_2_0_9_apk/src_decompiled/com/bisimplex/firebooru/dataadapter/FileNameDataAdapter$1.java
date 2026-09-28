package com.bisimplex.firebooru.dataadapter;
synthetic class FileNameDataAdapter$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$FileNamePartType;

    static FileNameDataAdapter$1()
    {
        int[] v0_5 = new int[com.bisimplex.firebooru.model.FileNamePartType.values().length];
        com.bisimplex.firebooru.dataadapter.FileNameDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType = v0_5;
        com.bisimplex.firebooru.model.FileNamePartType.MD5.ordinal()[int v1_7] = 1;
        com.bisimplex.firebooru.model.FileNamePartType.Domain.ordinal()[int v1_1] = 2;
        com.bisimplex.firebooru.model.FileNamePartType.Tags.ordinal()[int v1_3] = 3;
        try {
            com.bisimplex.firebooru.model.FileNamePartType.ID.ordinal()[int v1_5] = 4;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
