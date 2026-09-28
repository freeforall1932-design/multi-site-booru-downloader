package com.bisimplex.firebooru.fragment;
synthetic class DetailFragment$23 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$view$GestureType;

    static DetailFragment$23()
    {
        int[] v0_3 = new int[com.bisimplex.firebooru.view.GestureType.values().length];
        com.bisimplex.firebooru.fragment.DetailFragment$23.$SwitchMap$com$bisimplex$firebooru$view$GestureType = v0_3;
        com.bisimplex.firebooru.view.GestureType.DoubleTap.ordinal()[int v2_9] = 1;
        com.bisimplex.firebooru.view.GestureType.SwipeToUp.ordinal()[int v3_1] = 2;
        try {
            com.bisimplex.firebooru.view.GestureType.SwipeToDown.ordinal()[int v4_1] = 3;
        } catch (NoSuchFieldError) {
        }
        int v3_5 = new int[com.bisimplex.firebooru.model.ViewerCommandType.values().length];
        com.bisimplex.firebooru.fragment.DetailFragment$23.$SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType = v3_5;
        com.bisimplex.firebooru.model.ViewerCommandType.None.ordinal()[int v4_3] = 1;
        com.bisimplex.firebooru.model.ViewerCommandType.ShowInfo.ordinal()[int v3_7] = 2;
        com.bisimplex.firebooru.model.ViewerCommandType.Save.ordinal()[int v1_2] = 3;
        com.bisimplex.firebooru.model.ViewerCommandType.ToggleFavorite.ordinal()[int v1_4] = 4;
        com.bisimplex.firebooru.model.ViewerCommandType.ViewNormal.ordinal()[int v1_7] = 5;
        com.bisimplex.firebooru.model.ViewerCommandType.ViewOriginal.ordinal()[int v1_9] = 6;
        com.bisimplex.firebooru.model.ViewerCommandType.Share.ordinal()[int v1_11] = 7;
        com.bisimplex.firebooru.model.ViewerCommandType.ToggleNotes.ordinal()[int v1_13] = 8;
        try {
            com.bisimplex.firebooru.model.ViewerCommandType.Back.ordinal()[int v1_15] = 9;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
