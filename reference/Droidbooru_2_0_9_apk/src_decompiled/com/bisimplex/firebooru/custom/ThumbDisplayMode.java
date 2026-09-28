package com.bisimplex.firebooru.custom;
public final enum class ThumbDisplayMode extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.custom.ThumbDisplayMode[] $VALUES;
    public static final enum com.bisimplex.firebooru.custom.ThumbDisplayMode Big;
    public static final enum com.bisimplex.firebooru.custom.ThumbDisplayMode BigNoCrop;
    public static final enum com.bisimplex.firebooru.custom.ThumbDisplayMode Classic;
    public static final enum com.bisimplex.firebooru.custom.ThumbDisplayMode NoCrop;
    public static final enum com.bisimplex.firebooru.custom.ThumbDisplayMode Staggered;
    private final int value;

    private static synthetic com.bisimplex.firebooru.custom.ThumbDisplayMode[] $values()
    {
        return new com.bisimplex.firebooru.custom.ThumbDisplayMode[] {com.bisimplex.firebooru.custom.ThumbDisplayMode.Classic, com.bisimplex.firebooru.custom.ThumbDisplayMode.NoCrop, com.bisimplex.firebooru.custom.ThumbDisplayMode.Staggered, com.bisimplex.firebooru.custom.ThumbDisplayMode.Big, com.bisimplex.firebooru.custom.ThumbDisplayMode.BigNoCrop});
    }

    static ThumbDisplayMode()
    {
        com.bisimplex.firebooru.custom.ThumbDisplayMode.Classic = new com.bisimplex.firebooru.custom.ThumbDisplayMode("Classic", 0, 0);
        com.bisimplex.firebooru.custom.ThumbDisplayMode.NoCrop = new com.bisimplex.firebooru.custom.ThumbDisplayMode("NoCrop", 1, 1);
        com.bisimplex.firebooru.custom.ThumbDisplayMode.Staggered = new com.bisimplex.firebooru.custom.ThumbDisplayMode("Staggered", 2, -2);
        com.bisimplex.firebooru.custom.ThumbDisplayMode.Big = new com.bisimplex.firebooru.custom.ThumbDisplayMode("Big", 3, 2);
        com.bisimplex.firebooru.custom.ThumbDisplayMode.BigNoCrop = new com.bisimplex.firebooru.custom.ThumbDisplayMode("BigNoCrop", 4, 3);
        com.bisimplex.firebooru.custom.ThumbDisplayMode.$VALUES = com.bisimplex.firebooru.custom.ThumbDisplayMode.$values();
        return;
    }

    private ThumbDisplayMode(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.custom.ThumbDisplayMode fromInteger(int p1)
    {
        if (p1 == -2) {
            return com.bisimplex.firebooru.custom.ThumbDisplayMode.Staggered;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.custom.ThumbDisplayMode.NoCrop;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.custom.ThumbDisplayMode.Big;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.custom.ThumbDisplayMode.BigNoCrop;
                    } else {
                        return com.bisimplex.firebooru.custom.ThumbDisplayMode.Classic;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.custom.ThumbDisplayMode valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.custom.ThumbDisplayMode) Enum.valueOf(com.bisimplex.firebooru.custom.ThumbDisplayMode, p1));
    }

    public static com.bisimplex.firebooru.custom.ThumbDisplayMode[] values()
    {
        return ((com.bisimplex.firebooru.custom.ThumbDisplayMode[]) com.bisimplex.firebooru.custom.ThumbDisplayMode.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }

    public boolean isLarge()
    {
        if ((this != com.bisimplex.firebooru.custom.ThumbDisplayMode.Big) && (this != com.bisimplex.firebooru.custom.ThumbDisplayMode.BigNoCrop)) {
            return 0;
        } else {
            return 1;
        }
    }
}
