package com.bisimplex.firebooru.network;
synthetic class SourceFactory$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$network$SourceType;

    static SourceFactory$1()
    {
        int[] v0_6 = new int[com.bisimplex.firebooru.network.SourceType.values().length];
        com.bisimplex.firebooru.network.SourceFactory$1.$SwitchMap$com$bisimplex$firebooru$network$SourceType = v0_6;
        com.bisimplex.firebooru.network.SourceType.Post.ordinal()[int v1_15] = 1;
        com.bisimplex.firebooru.network.SourceType.Notes.ordinal()[int v1_1] = 2;
        com.bisimplex.firebooru.network.SourceType.Pool.ordinal()[int v1_3] = 3;
        com.bisimplex.firebooru.network.SourceType.Tag.ordinal()[int v1_5] = 4;
        com.bisimplex.firebooru.network.SourceType.BooruTag.ordinal()[int v1_7] = 5;
        com.bisimplex.firebooru.network.SourceType.Favorites.ordinal()[int v1_9] = 6;
        com.bisimplex.firebooru.network.SourceType.History.ordinal()[int v1_11] = 7;
        try {
            com.bisimplex.firebooru.network.SourceType.MultiPost.ordinal()[int v1_13] = 8;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
