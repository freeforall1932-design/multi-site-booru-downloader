package com.bisimplex.firebooru.backup;
public final enum class BackupServerItemType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.backup.BackupServerItemType[] $VALUES;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeBIO;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeBooruOnRails;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeDanbooru;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeDanbooru2;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeDerpibooru;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeE621;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeGelbooru;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeGelbooru111;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeHydrus;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeIBSearch;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeKemono;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeNone;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeRSS;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeShimmie;
    public static final enum com.bisimplex.firebooru.backup.BackupServerItemType ServerItemTypeZeroChan;
    private final int value;

    private static synthetic com.bisimplex.firebooru.backup.BackupServerItemType[] $values()
    {
        com.bisimplex.firebooru.backup.BackupServerItemType v2 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru;
        com.bisimplex.firebooru.backup.BackupServerItemType v4 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru2;
        com.bisimplex.firebooru.backup.BackupServerItemType v6 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeZeroChan;
        com.bisimplex.firebooru.backup.BackupServerItemType v8 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeIBSearch;
        com.bisimplex.firebooru.backup.BackupServerItemType v10 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeE621;
        com.bisimplex.firebooru.backup.BackupServerItemType v12 = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBIO;
        return new com.bisimplex.firebooru.backup.BackupServerItemType[] {com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone, com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeKemono});
    }

    static BackupServerItemType()
    {
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeNone", 0, -1);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeDanbooru", 1, 0);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeGelbooru", 2, 1);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeShimmie = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeShimmie", 3, 2);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru2 = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeDanbooru2", 4, 3);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru111 = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeGelbooru111", 5, 4);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeZeroChan = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeZeroChan", 6, 5);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeRSS = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeRSS", 7, 6);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeIBSearch = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeIBSearch", 8, 7);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDerpibooru = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeDerpibooru", 9, 8);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeE621 = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeE621", 10, 9);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeHydrus = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeHydrus", 11, 10);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBIO = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeBIO", 12, 11);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBooruOnRails = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeBooruOnRails", 13, 12);
        com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeKemono = new com.bisimplex.firebooru.backup.BackupServerItemType("ServerItemTypeKemono", 14, 13);
        com.bisimplex.firebooru.backup.BackupServerItemType.$VALUES = com.bisimplex.firebooru.backup.BackupServerItemType.$values();
        return;
    }

    private BackupServerItemType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.backup.BackupServerItemType fromInteger(int p0)
    {
        switch (p0) {
            case 0:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru;
            case 1:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru;
            case 2:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeShimmie;
            case 3:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru2;
            case 4:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru111;
            case 5:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeZeroChan;
            case 6:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeRSS;
            case 7:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeIBSearch;
            case 8:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDerpibooru;
            case 9:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeE621;
            case 10:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeHydrus;
            case 11:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBIO;
            case 12:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBooruOnRails;
            case 13:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeKemono;
            default:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone;
        }
    }

    public static com.bisimplex.firebooru.backup.BackupServerItemType fromServerItemType(com.bisimplex.firebooru.danbooru.ServerItemType p1)
    {
        switch (com.bisimplex.firebooru.backup.BackupServerItemType$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p1.ordinal()]) {
            case 1:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone;
            case 2:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru;
            case 3:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru;
            case 4:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeShimmie;
            case 5:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDanbooru2;
            case 6:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru111;
            case 7:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeZeroChan;
            case 8:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeRSS;
            case 9:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeIBSearch;
            case 10:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeDerpibooru;
            case 11:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeE621;
            case 12:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeHydrus;
            case 13:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBIO;
            case 14:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeBooruOnRails;
            case 15:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeKemono;
            default:
                return com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeNone;
        }
    }

    public static com.bisimplex.firebooru.danbooru.ServerItemType toServerItemType(com.bisimplex.firebooru.backup.BackupServerItemType p1)
    {
        switch (com.bisimplex.firebooru.backup.BackupServerItemType$1.$SwitchMap$com$bisimplex$firebooru$backup$BackupServerItemType[p1.ordinal()]) {
            case 1:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
            case 2:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru;
            case 3:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru;
            case 4:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie;
            case 5:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2;
            case 6:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111;
            case 7:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeZeroChan;
            case 8:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeRSS;
            case 9:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch;
            case 10:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru;
            case 11:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621;
            case 12:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus;
            case 13:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO;
            case 14:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails;
            case 15:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono;
            default:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
        }
    }

    public static com.bisimplex.firebooru.backup.BackupServerItemType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.backup.BackupServerItemType) Enum.valueOf(com.bisimplex.firebooru.backup.BackupServerItemType, p1));
    }

    public static com.bisimplex.firebooru.backup.BackupServerItemType[] values()
    {
        return ((com.bisimplex.firebooru.backup.BackupServerItemType[]) com.bisimplex.firebooru.backup.BackupServerItemType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
