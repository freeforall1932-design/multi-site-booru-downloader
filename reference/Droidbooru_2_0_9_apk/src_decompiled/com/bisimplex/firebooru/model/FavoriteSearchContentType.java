package com.bisimplex.firebooru.model;
public final enum class FavoriteSearchContentType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.FavoriteSearchContentType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchContentType All;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchContentType Gif;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchContentType MP4;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchContentType Static;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchContentType WebM;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.FavoriteSearchContentType[] $values()
    {
        return new com.bisimplex.firebooru.model.FavoriteSearchContentType[] {com.bisimplex.firebooru.model.FavoriteSearchContentType.All, com.bisimplex.firebooru.model.FavoriteSearchContentType.Static, com.bisimplex.firebooru.model.FavoriteSearchContentType.Gif, com.bisimplex.firebooru.model.FavoriteSearchContentType.WebM, com.bisimplex.firebooru.model.FavoriteSearchContentType.MP4});
    }

    static FavoriteSearchContentType()
    {
        com.bisimplex.firebooru.model.FavoriteSearchContentType.All = new com.bisimplex.firebooru.model.FavoriteSearchContentType("All", 0, 0);
        com.bisimplex.firebooru.model.FavoriteSearchContentType.Static = new com.bisimplex.firebooru.model.FavoriteSearchContentType("Static", 1, 1);
        com.bisimplex.firebooru.model.FavoriteSearchContentType.Gif = new com.bisimplex.firebooru.model.FavoriteSearchContentType("Gif", 2, 2);
        com.bisimplex.firebooru.model.FavoriteSearchContentType.WebM = new com.bisimplex.firebooru.model.FavoriteSearchContentType("WebM", 3, 3);
        com.bisimplex.firebooru.model.FavoriteSearchContentType.MP4 = new com.bisimplex.firebooru.model.FavoriteSearchContentType("MP4", 4, 4);
        com.bisimplex.firebooru.model.FavoriteSearchContentType.$VALUES = com.bisimplex.firebooru.model.FavoriteSearchContentType.$values();
        return;
    }

    private FavoriteSearchContentType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static java.util.List all()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(3);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchContentType.All);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchContentType.Static);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchContentType.Gif);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchContentType.WebM);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchContentType.MP4);
        return v0_1;
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchContentType fromInt(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.model.FavoriteSearchContentType.Static;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.model.FavoriteSearchContentType.Gif;
            } else {
                if (p1 == 3) {
                    return com.bisimplex.firebooru.model.FavoriteSearchContentType.WebM;
                } else {
                    if (p1 == 4) {
                        return com.bisimplex.firebooru.model.FavoriteSearchContentType.MP4;
                    } else {
                        return com.bisimplex.firebooru.model.FavoriteSearchContentType.All;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchContentType fromString(String p1)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            return com.bisimplex.firebooru.model.FavoriteSearchContentType.fromInt(Integer.parseInt(p1));
        } else {
            return com.bisimplex.firebooru.model.FavoriteSearchContentType.All;
        }
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchContentType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.FavoriteSearchContentType) Enum.valueOf(com.bisimplex.firebooru.model.FavoriteSearchContentType, p1));
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchContentType[] values()
    {
        return ((com.bisimplex.firebooru.model.FavoriteSearchContentType[]) com.bisimplex.firebooru.model.FavoriteSearchContentType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
