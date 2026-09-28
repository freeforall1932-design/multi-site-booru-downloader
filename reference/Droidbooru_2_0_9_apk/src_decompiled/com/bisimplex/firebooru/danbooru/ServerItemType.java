package com.bisimplex.firebooru.danbooru;
public final enum class ServerItemType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.ServerItemType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeBIO;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeBooruOnRails;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeDanbooru;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeDanbooru2;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeDerpibooru;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeE621;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeGelbooru;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeGelbooru111;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeHydrus;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeIBSearch;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeKemono;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeNone;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeRSS;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeShimmie;
    public static final enum com.bisimplex.firebooru.danbooru.ServerItemType ServerItemTypeZeroChan;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.ServerItemType[] $values()
    {
        com.bisimplex.firebooru.danbooru.ServerItemType v2 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru;
        com.bisimplex.firebooru.danbooru.ServerItemType v4 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2;
        com.bisimplex.firebooru.danbooru.ServerItemType v6 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeZeroChan;
        com.bisimplex.firebooru.danbooru.ServerItemType v8 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch;
        com.bisimplex.firebooru.danbooru.ServerItemType v10 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621;
        com.bisimplex.firebooru.danbooru.ServerItemType v12 = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO;
        return new com.bisimplex.firebooru.danbooru.ServerItemType[] {com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone, com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono});
    }

    static ServerItemType()
    {
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeNone", 0, -1);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeDanbooru", 1, 0);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeGelbooru", 2, 1);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeShimmie", 3, 4);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2 = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeDanbooru2", 4, 2);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111 = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeGelbooru111", 5, 3);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeZeroChan = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeZeroChan", 6, 5);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeRSS = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeRSS", 7, 6);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeIBSearch", 8, 7);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeDerpibooru", 9, 8);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621 = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeE621", 10, 9);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeHydrus", 11, 10);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeBIO", 12, 11);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeBooruOnRails", 13, 12);
        com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono = new com.bisimplex.firebooru.danbooru.ServerItemType("ServerItemTypeKemono", 14, 13);
        com.bisimplex.firebooru.danbooru.ServerItemType.$VALUES = com.bisimplex.firebooru.danbooru.ServerItemType.$values();
        return;
    }

    private ServerItemType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.ServerItemType fromInteger(int p0)
    {
        switch (p0) {
            case 0:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru;
            case 1:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru;
            case 2:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2;
            case 3:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru111;
            case 4:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie;
            case 5:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeZeroChan;
            case 6:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeRSS;
            case 7:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeIBSearch;
            case 8:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru;
            case 9:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621;
            case 10:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus;
            case 11:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO;
            case 12:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBooruOnRails;
            case 13:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeKemono;
            default:
                return com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeNone;
        }
    }

    public static String publicName(com.bisimplex.firebooru.danbooru.ServerItemType p1)
    {
        switch (com.bisimplex.firebooru.danbooru.ServerItemType$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[p1.ordinal()]) {
            case 1:
                return "Danbooru";
            case 2:
                return "Gelbooru";
            case 3:
                return "ZeroChan";
            case 4:
                return "Shimmie 2";
            case 5:
                return "RSS";
            case 6:
                return "Danbooru 2";
            case 7:
                return "Gelbooru 0.1.11";
            case 8:
                return "IBSearch";
            case 9:
                return "Philomena (Derpibooru)";
            case 10:
                return "e621";
            case 11:
                return "Hydrus";
            case 12:
                return "BooruIO";
            case 13:
                return "booru-on-rails";
            case 14:
                return "Kemono";
            default:
                return "Gelbooru";
        }
    }

    public static com.bisimplex.firebooru.danbooru.ServerItemType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.ServerItemType) Enum.valueOf(com.bisimplex.firebooru.danbooru.ServerItemType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.ServerItemType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.ServerItemType[]) com.bisimplex.firebooru.danbooru.ServerItemType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
