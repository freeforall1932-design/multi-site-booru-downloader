package com.bisimplex.firebooru.custom;
public final enum class SecondaryMenuType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.custom.SecondaryMenuType[] $VALUES;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuType History;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuType InfoPanel;
    public static final enum com.bisimplex.firebooru.custom.SecondaryMenuType None;
    private final int value;

    private static synthetic com.bisimplex.firebooru.custom.SecondaryMenuType[] $values()
    {
        return new com.bisimplex.firebooru.custom.SecondaryMenuType[] {com.bisimplex.firebooru.custom.SecondaryMenuType.None, com.bisimplex.firebooru.custom.SecondaryMenuType.InfoPanel, com.bisimplex.firebooru.custom.SecondaryMenuType.History});
    }

    static SecondaryMenuType()
    {
        com.bisimplex.firebooru.custom.SecondaryMenuType.None = new com.bisimplex.firebooru.custom.SecondaryMenuType("None", 0, -1);
        com.bisimplex.firebooru.custom.SecondaryMenuType.InfoPanel = new com.bisimplex.firebooru.custom.SecondaryMenuType("InfoPanel", 1, 0);
        com.bisimplex.firebooru.custom.SecondaryMenuType.History = new com.bisimplex.firebooru.custom.SecondaryMenuType("History", 2, 1);
        com.bisimplex.firebooru.custom.SecondaryMenuType.$VALUES = com.bisimplex.firebooru.custom.SecondaryMenuType.$values();
        return;
    }

    private SecondaryMenuType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.custom.SecondaryMenuType.InfoPanel;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.custom.SecondaryMenuType.History;
            } else {
                return com.bisimplex.firebooru.custom.SecondaryMenuType.None;
            }
        }
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.custom.SecondaryMenuType) Enum.valueOf(com.bisimplex.firebooru.custom.SecondaryMenuType, p1));
    }

    public static com.bisimplex.firebooru.custom.SecondaryMenuType[] values()
    {
        return ((com.bisimplex.firebooru.custom.SecondaryMenuType[]) com.bisimplex.firebooru.custom.SecondaryMenuType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
