package com.bisimplex.firebooru.activity;
synthetic class MenuBaseActivity$41 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$activity$MessageType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuActionType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$view$HistoryTagMenuDialog$HistoryTagActionType;
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$view$TagMenuDialog$TagActionType;

    static MenuBaseActivity$41()
    {
        int[] v0_9 = new int[com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.values().length];
        com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$view$HistoryTagMenuDialog$HistoryTagActionType = v0_9;
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.ToggleFav.ordinal()[int v2_3] = 1;
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Delete.ordinal()[int v3_1] = 2;
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Copy.ordinal()[int v4_1] = 3;
        try {
            com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Cancel.ordinal()[int v5_1] = 4;
        } catch (NoSuchFieldError) {
        }
        int v4_5 = new int[com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.values().length];
        com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$view$TagMenuDialog$TagActionType = v4_5;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Search.ordinal()[int v5_3] = 1;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchPlus.ordinal()[int v5_5] = 2;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchMinus.ordinal()[int v5_7] = 3;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SaveToHistory.ordinal()[int v5_9] = 4;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Copy.ordinal()[int v6_1] = 5;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.BlacklistServer.ordinal()[int v7_1] = 6;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Pin.ordinal()[int v8_12] = 7;
        try {
            com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Cancel.ordinal()[int v9_11] = 8;
        } catch (NoSuchFieldError) {
        }
        int v8_16 = new int[com.bisimplex.firebooru.activity.MessageType.values().length];
        com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$activity$MessageType = v8_16;
        com.bisimplex.firebooru.activity.MessageType.Error.ordinal()[int v9_13] = 1;
        com.bisimplex.firebooru.activity.MessageType.Info.ordinal()[int v9_15] = 2;
        com.bisimplex.firebooru.activity.MessageType.Loading.ordinal()[int v9_17] = 3;
        com.bisimplex.firebooru.activity.MessageType.Success.ordinal()[int v9_19] = 4;
        try {
            com.bisimplex.firebooru.activity.MessageType.Minimal.ordinal()[int v9_1] = 5;
        } catch (NoSuchFieldError) {
        }
        int v8_3 = new int[com.bisimplex.firebooru.custom.SecondaryMenuType.values().length];
        com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuType = v8_3;
        com.bisimplex.firebooru.custom.SecondaryMenuType.None.ordinal()[int v9_3] = 1;
        com.bisimplex.firebooru.custom.SecondaryMenuType.InfoPanel.ordinal()[int v9_5] = 2;
        try {
            com.bisimplex.firebooru.custom.SecondaryMenuType.History.ordinal()[int v9_7] = 3;
        } catch (NoSuchFieldError) {
        }
        int v8_8 = new int[com.bisimplex.firebooru.custom.SecondaryMenuActionType.values().length];
        com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuActionType = v8_8;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.None.ordinal()[int v9_9] = 1;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowNormal.ordinal()[int v8_10] = 2;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowOriginal.ordinal()[int v1_2] = 3;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowUrl.ordinal()[int v1_4] = 4;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.SearchTag.ordinal()[int v1_6] = 5;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.MD5.ordinal()[int v1_8] = 6;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ID.ordinal()[int v1_10] = 7;
        try {
            com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowParent.ordinal()[int v1_12] = 8;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
