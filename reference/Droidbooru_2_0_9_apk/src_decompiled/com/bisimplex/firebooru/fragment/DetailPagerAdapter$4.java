package com.bisimplex.firebooru.fragment;
synthetic class DetailPagerAdapter$4 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType;

    static DetailPagerAdapter$4()
    {
        int v0_3 = new int[com.bisimplex.firebooru.model.VideoDecoderType.values().length];
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$4.$SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType = v0_3;
        com.bisimplex.firebooru.model.VideoDecoderType.Base.ordinal()[int v2_8] = 1;
        com.bisimplex.firebooru.model.VideoDecoderType.VLC.ordinal()[int v3_1] = 2;
        try {
            com.bisimplex.firebooru.model.VideoDecoderType.EXO.ordinal()[int v3_3] = 3;
        } catch (NoSuchFieldError) {
        }
        int v2_4 = new int[com.bisimplex.firebooru.model.ViewerCommandType.values().length];
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$4.$SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType = v2_4;
        com.bisimplex.firebooru.model.ViewerCommandType.ZoomIn.ordinal()[int v3_5] = 1;
        try {
            com.bisimplex.firebooru.model.ViewerCommandType.ZoomOut.ordinal()[int v2_6] = 2;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
