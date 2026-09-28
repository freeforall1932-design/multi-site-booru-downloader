package com.bisimplex.firebooru.model;
synthetic class FileNamePartType$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$danbooru$FileNameType;

    static FileNamePartType$1()
    {
        int[] v0_3 = new int[com.bisimplex.firebooru.danbooru.FileNameType.values().length];
        com.bisimplex.firebooru.model.FileNamePartType$1.$SwitchMap$com$bisimplex$firebooru$danbooru$FileNameType = v0_3;
        com.bisimplex.firebooru.danbooru.FileNameType.MD5.ordinal()[int v1_3] = 1;
        try {
            com.bisimplex.firebooru.danbooru.FileNameType.Legacy.ordinal()[int v1_1] = 2;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
