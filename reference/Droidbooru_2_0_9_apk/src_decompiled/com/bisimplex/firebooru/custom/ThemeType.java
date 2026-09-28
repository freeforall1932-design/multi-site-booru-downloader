package com.bisimplex.firebooru.custom;
public final enum class ThemeType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.custom.ThemeType[] $VALUES;
    public static final enum com.bisimplex.firebooru.custom.ThemeType Dark;
    public static final enum com.bisimplex.firebooru.custom.ThemeType Gray;
    public static final enum com.bisimplex.firebooru.custom.ThemeType Light;
    public static final enum com.bisimplex.firebooru.custom.ThemeType Purple;
    private final int value;

    private static synthetic com.bisimplex.firebooru.custom.ThemeType[] $values()
    {
        return new com.bisimplex.firebooru.custom.ThemeType[] {com.bisimplex.firebooru.custom.ThemeType.Dark, com.bisimplex.firebooru.custom.ThemeType.Light, com.bisimplex.firebooru.custom.ThemeType.Gray, com.bisimplex.firebooru.custom.ThemeType.Purple});
    }

    static ThemeType()
    {
        com.bisimplex.firebooru.custom.ThemeType.Dark = new com.bisimplex.firebooru.custom.ThemeType("Dark", 0, 0);
        com.bisimplex.firebooru.custom.ThemeType.Light = new com.bisimplex.firebooru.custom.ThemeType("Light", 1, 1);
        com.bisimplex.firebooru.custom.ThemeType.Gray = new com.bisimplex.firebooru.custom.ThemeType("Gray", 2, 2);
        com.bisimplex.firebooru.custom.ThemeType.Purple = new com.bisimplex.firebooru.custom.ThemeType("Purple", 3, 3);
        com.bisimplex.firebooru.custom.ThemeType.$VALUES = com.bisimplex.firebooru.custom.ThemeType.$values();
        return;
    }

    private ThemeType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.custom.ThemeType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.custom.ThemeType.Dark;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.custom.ThemeType.Light;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.custom.ThemeType.Gray;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.custom.ThemeType.Purple;
                    } else {
                        return com.bisimplex.firebooru.custom.ThemeType.Dark;
                    }
                }
            }
        }
    }

    public static int styleFromInt(int p1)
    {
        if (p1 == 1) {
            return 2131951953;
        } else {
            if (p1 == 2) {
                return 2131951627;
            } else {
                if (p1 == 3) {
                    return 2131951994;
                } else {
                    return 2131951913;
                }
            }
        }
    }

    public static int styleFromType(com.bisimplex.firebooru.custom.ThemeType p0)
    {
        return com.bisimplex.firebooru.custom.ThemeType.styleFromInt(p0.getValue());
    }

    public static com.bisimplex.firebooru.custom.ThemeType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.custom.ThemeType) Enum.valueOf(com.bisimplex.firebooru.custom.ThemeType, p1));
    }

    public static com.bisimplex.firebooru.custom.ThemeType[] values()
    {
        return ((com.bisimplex.firebooru.custom.ThemeType[]) com.bisimplex.firebooru.custom.ThemeType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
