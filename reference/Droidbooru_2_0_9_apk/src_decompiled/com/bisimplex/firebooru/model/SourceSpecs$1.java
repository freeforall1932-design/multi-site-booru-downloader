package com.bisimplex.firebooru.model;
synthetic class SourceSpecs$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$network$SourceType;

    static SourceSpecs$1()
    {
        int[] v0_4 = new int[com.bisimplex.firebooru.network.SourceType.values().length];
        com.bisimplex.firebooru.model.SourceSpecs$1.$SwitchMap$com$bisimplex$firebooru$network$SourceType = v0_4;
        com.bisimplex.firebooru.network.SourceType.Favorites.ordinal()[int v1_5] = 1;
        com.bisimplex.firebooru.network.SourceType.History.ordinal()[int v1_1] = 2;
        try {
            com.bisimplex.firebooru.network.SourceType.Post.ordinal()[int v1_3] = 3;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
