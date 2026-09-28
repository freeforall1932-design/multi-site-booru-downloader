package com.bisimplex.firebooru.danbooru;
synthetic class DatabaseHelper$1 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$danbooru$FavoriteSortType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType;

    static DatabaseHelper$1()
    {
        int[] v0_3 = new int[com.bisimplex.firebooru.danbooru.ServerItemType.values().length];
        com.bisimplex.firebooru.danbooru.DatabaseHelper$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType = v0_3;
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone.ordinal()[int v2_10] = 1;
        try {
            com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru.ordinal()[int v3_1] = 2;
        } catch (NoSuchFieldError) {
        }
        int v2_3 = new int[com.bisimplex.firebooru.danbooru.FavoriteSortType.values().length];
        com.bisimplex.firebooru.danbooru.DatabaseHelper$1.$SwitchMap$com$bisimplex$firebooru$danbooru$FavoriteSortType = v2_3;
        com.bisimplex.firebooru.danbooru.FavoriteSortType.Date.ordinal()[int v3_3] = 1;
        com.bisimplex.firebooru.danbooru.FavoriteSortType.DateAsc.ordinal()[int v2_5] = 2;
        com.bisimplex.firebooru.danbooru.FavoriteSortType.Score.ordinal()[int v1_2] = 3;
        com.bisimplex.firebooru.danbooru.FavoriteSortType.ScoreAsc.ordinal()[int v1_4] = 4;
        try {
            com.bisimplex.firebooru.danbooru.FavoriteSortType.Random.ordinal()[int v1_6] = 5;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
