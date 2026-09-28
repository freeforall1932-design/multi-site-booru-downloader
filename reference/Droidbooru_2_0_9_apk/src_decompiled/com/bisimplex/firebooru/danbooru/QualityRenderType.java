package com.bisimplex.firebooru.danbooru;
public final enum class QualityRenderType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.QualityRenderType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.QualityRenderType ForcedLow;
    public static final enum com.bisimplex.firebooru.danbooru.QualityRenderType Low;
    public static final enum com.bisimplex.firebooru.danbooru.QualityRenderType None;
    public static final enum com.bisimplex.firebooru.danbooru.QualityRenderType Normal;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.QualityRenderType[] $values()
    {
        return new com.bisimplex.firebooru.danbooru.QualityRenderType[] {com.bisimplex.firebooru.danbooru.QualityRenderType.None, com.bisimplex.firebooru.danbooru.QualityRenderType.ForcedLow, com.bisimplex.firebooru.danbooru.QualityRenderType.Low, com.bisimplex.firebooru.danbooru.QualityRenderType.Normal});
    }

    static QualityRenderType()
    {
        com.bisimplex.firebooru.danbooru.QualityRenderType.None = new com.bisimplex.firebooru.danbooru.QualityRenderType("None", 0, 0);
        com.bisimplex.firebooru.danbooru.QualityRenderType.ForcedLow = new com.bisimplex.firebooru.danbooru.QualityRenderType("ForcedLow", 1, 1);
        com.bisimplex.firebooru.danbooru.QualityRenderType.Low = new com.bisimplex.firebooru.danbooru.QualityRenderType("Low", 2, 2);
        com.bisimplex.firebooru.danbooru.QualityRenderType.Normal = new com.bisimplex.firebooru.danbooru.QualityRenderType("Normal", 3, 3);
        com.bisimplex.firebooru.danbooru.QualityRenderType.$VALUES = com.bisimplex.firebooru.danbooru.QualityRenderType.$values();
        return;
    }

    private QualityRenderType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.QualityRenderType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.danbooru.QualityRenderType.None;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.danbooru.QualityRenderType.ForcedLow;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.danbooru.QualityRenderType.Low;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.danbooru.QualityRenderType.Normal;
                    } else {
                        return com.bisimplex.firebooru.danbooru.QualityRenderType.Low;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.danbooru.QualityRenderType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.QualityRenderType) Enum.valueOf(com.bisimplex.firebooru.danbooru.QualityRenderType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.QualityRenderType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.QualityRenderType[]) com.bisimplex.firebooru.danbooru.QualityRenderType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
