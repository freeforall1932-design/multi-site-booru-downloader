package com.bisimplex.firebooru.backup;
synthetic class LoadLiveDBTask$3 {
    static final synthetic int[] $SwitchMap$com$bisimplex$firebooru$backup$BackupCSVRowType;

    static LoadLiveDBTask$3()
    {
        int[] v0_6 = new int[com.bisimplex.firebooru.backup.BackupCSVRowType.values().length];
        com.bisimplex.firebooru.backup.LoadLiveDBTask$3.$SwitchMap$com$bisimplex$firebooru$backup$BackupCSVRowType = v0_6;
        com.bisimplex.firebooru.backup.BackupCSVRowType.None.ordinal()[int v1_13] = 1;
        com.bisimplex.firebooru.backup.BackupCSVRowType.Server.ordinal()[int v1_1] = 2;
        com.bisimplex.firebooru.backup.BackupCSVRowType.History.ordinal()[int v1_3] = 3;
        com.bisimplex.firebooru.backup.BackupCSVRowType.Favorite.ordinal()[int v1_5] = 4;
        com.bisimplex.firebooru.backup.BackupCSVRowType.Blacklist.ordinal()[int v1_7] = 5;
        com.bisimplex.firebooru.backup.BackupCSVRowType.HomePin.ordinal()[int v1_9] = 6;
        try {
            com.bisimplex.firebooru.backup.BackupCSVRowType.Meta.ordinal()[int v1_11] = 7;
        } catch (NoSuchFieldError) {
        }
        return;
    }
}
