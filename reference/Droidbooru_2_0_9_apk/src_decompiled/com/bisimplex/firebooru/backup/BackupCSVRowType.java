package com.bisimplex.firebooru.backup;
public final enum class BackupCSVRowType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.backup.BackupCSVRowType[] $VALUES;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType Blacklist;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType Favorite;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType History;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType HomePin;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType Meta;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType None;
    public static final enum com.bisimplex.firebooru.backup.BackupCSVRowType Server;
    private final int value;

    private static synthetic com.bisimplex.firebooru.backup.BackupCSVRowType[] $values()
    {
        com.bisimplex.firebooru.backup.BackupCSVRowType v2 = com.bisimplex.firebooru.backup.BackupCSVRowType.History;
        com.bisimplex.firebooru.backup.BackupCSVRowType v4 = com.bisimplex.firebooru.backup.BackupCSVRowType.Blacklist;
        return new com.bisimplex.firebooru.backup.BackupCSVRowType[] {com.bisimplex.firebooru.backup.BackupCSVRowType.None, com.bisimplex.firebooru.backup.BackupCSVRowType.Meta});
    }

    static BackupCSVRowType()
    {
        com.bisimplex.firebooru.backup.BackupCSVRowType.None = new com.bisimplex.firebooru.backup.BackupCSVRowType("None", 0, -1);
        com.bisimplex.firebooru.backup.BackupCSVRowType.Server = new com.bisimplex.firebooru.backup.BackupCSVRowType("Server", 1, 0);
        com.bisimplex.firebooru.backup.BackupCSVRowType.History = new com.bisimplex.firebooru.backup.BackupCSVRowType("History", 2, 1);
        com.bisimplex.firebooru.backup.BackupCSVRowType.Favorite = new com.bisimplex.firebooru.backup.BackupCSVRowType("Favorite", 3, 2);
        com.bisimplex.firebooru.backup.BackupCSVRowType.Blacklist = new com.bisimplex.firebooru.backup.BackupCSVRowType("Blacklist", 4, 3);
        com.bisimplex.firebooru.backup.BackupCSVRowType.HomePin = new com.bisimplex.firebooru.backup.BackupCSVRowType("HomePin", 5, 4);
        com.bisimplex.firebooru.backup.BackupCSVRowType.Meta = new com.bisimplex.firebooru.backup.BackupCSVRowType("Meta", 6, 5);
        com.bisimplex.firebooru.backup.BackupCSVRowType.$VALUES = com.bisimplex.firebooru.backup.BackupCSVRowType.$values();
        return;
    }

    private BackupCSVRowType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.backup.BackupCSVRowType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.backup.BackupCSVRowType.Server;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.backup.BackupCSVRowType.History;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.backup.BackupCSVRowType.Favorite;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.backup.BackupCSVRowType.Blacklist;
                    } else {
                        if (p1 == 4) {
                            return com.bisimplex.firebooru.backup.BackupCSVRowType.HomePin;
                        } else {
                            if (p1 == 5) {
                                return com.bisimplex.firebooru.backup.BackupCSVRowType.Meta;
                            } else {
                                return com.bisimplex.firebooru.backup.BackupCSVRowType.None;
                            }
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.backup.BackupCSVRowType fromValueString(String p1)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            try {
                return com.bisimplex.firebooru.backup.BackupCSVRowType.fromInteger(Integer.parseInt(p1));
            } catch (NumberFormatException) {
                return com.bisimplex.firebooru.backup.BackupCSVRowType.None;
            }
        } else {
            return com.bisimplex.firebooru.backup.BackupCSVRowType.None;
        }
    }

    public static com.bisimplex.firebooru.backup.BackupCSVRowType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.backup.BackupCSVRowType) Enum.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType, p1));
    }

    public static com.bisimplex.firebooru.backup.BackupCSVRowType[] values()
    {
        return ((com.bisimplex.firebooru.backup.BackupCSVRowType[]) com.bisimplex.firebooru.backup.BackupCSVRowType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
