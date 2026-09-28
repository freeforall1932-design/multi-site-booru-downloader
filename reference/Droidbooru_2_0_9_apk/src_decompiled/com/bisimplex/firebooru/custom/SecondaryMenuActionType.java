package com.bisimplex.firebooru.custom;
public final enum class SecondaryMenuActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.custom.SecondaryMenuActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType Author;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ID;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType MD5;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType None;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType SearchTag;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ShowChild;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ShowNormal;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ShowOriginal;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ShowParent;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuActionType ShowUrl;
    private final int value;

    private static synthetic com.bisimplex.firebooru.custom.SecondaryMenuActionType[] $values()
    {
        com.bisimplex.firebooru.custom.SecondaryMenuActionType v2 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowOriginal;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType v4 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.SearchTag;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType v6 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowParent;
        com.bisimplex.firebooru.custom.SecondaryMenuActionType v8 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.ID;
        return new com.bisimplex.firebooru.custom.SecondaryMenuActionType[] {com.bisimplex.firebooru.custom.SecondaryMenuActionType.None, com.bisimplex.firebooru.custom.SecondaryMenuActionType.Author});
    }

    static SecondaryMenuActionType()
    {
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.None = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("None", 0, -1);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowNormal = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ShowNormal", 1, 0);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowOriginal = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ShowOriginal", 2, 1);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowUrl = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ShowUrl", 3, 2);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.SearchTag = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("SearchTag", 4, 3);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.MD5 = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("MD5", 5, 4);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowParent = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ShowParent", 6, 5);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowChild = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ShowChild", 7, 6);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.ID = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("ID", 8, 7);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.Author = new com.bisimplex.firebooru.custom.SecondaryMenuActionType("Author", 9, 8);
        com.bisimplex.firebooru.custom.SecondaryMenuActionType.$VALUES = com.bisimplex.firebooru.custom.SecondaryMenuActionType.$values();
        return;
    }

    private SecondaryMenuActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuActionType fromInteger(int p0)
    {
        switch (p0) {
            case 0:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowNormal;
            case 1:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowOriginal;
            case 2:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowUrl;
            case 3:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.SearchTag;
            case 4:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.MD5;
            case 5:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowParent;
            case 6:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowChild;
            case 7:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.ID;
            case 8:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.Author;
            default:
                return com.bisimplex.firebooru.custom.SecondaryMenuActionType.None;
        }
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.custom.SecondaryMenuActionType) Enum.valueOf(com.bisimplex.firebooru.custom.SecondaryMenuActionType, p1));
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuActionType[] values()
    {
        return ((com.bisimplex.firebooru.custom.SecondaryMenuActionType[]) com.bisimplex.firebooru.custom.SecondaryMenuActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
