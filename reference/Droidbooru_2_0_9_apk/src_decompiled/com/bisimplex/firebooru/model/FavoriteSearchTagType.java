package com.bisimplex.firebooru.model;
public final enum class FavoriteSearchTagType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.FavoriteSearchTagType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchTagType AllTags;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchTagType AnyTags;
    public static final enum com.bisimplex.firebooru.model.FavoriteSearchTagType NotAllTags;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.FavoriteSearchTagType[] $values()
    {
        return new com.bisimplex.firebooru.model.FavoriteSearchTagType[] {com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags, com.bisimplex.firebooru.model.FavoriteSearchTagType.AnyTags, com.bisimplex.firebooru.model.FavoriteSearchTagType.NotAllTags});
    }

    static FavoriteSearchTagType()
    {
        com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags = new com.bisimplex.firebooru.model.FavoriteSearchTagType("AllTags", 0, 0);
        com.bisimplex.firebooru.model.FavoriteSearchTagType.AnyTags = new com.bisimplex.firebooru.model.FavoriteSearchTagType("AnyTags", 1, 1);
        com.bisimplex.firebooru.model.FavoriteSearchTagType.NotAllTags = new com.bisimplex.firebooru.model.FavoriteSearchTagType("NotAllTags", 2, 2);
        com.bisimplex.firebooru.model.FavoriteSearchTagType.$VALUES = com.bisimplex.firebooru.model.FavoriteSearchTagType.$values();
        return;
    }

    private FavoriteSearchTagType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static java.util.List all()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(3);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchTagType.NotAllTags);
        v0_1.add(com.bisimplex.firebooru.model.FavoriteSearchTagType.AnyTags);
        return v0_1;
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchTagType fromInt(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.model.FavoriteSearchTagType.AnyTags;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.model.FavoriteSearchTagType.NotAllTags;
            } else {
                return com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags;
            }
        }
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchTagType fromString(String p1)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            return com.bisimplex.firebooru.model.FavoriteSearchTagType.fromInt(Integer.parseInt(p1));
        } else {
            return com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags;
        }
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchTagType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.FavoriteSearchTagType) Enum.valueOf(com.bisimplex.firebooru.model.FavoriteSearchTagType, p1));
    }

    public static com.bisimplex.firebooru.model.FavoriteSearchTagType[] values()
    {
        return ((com.bisimplex.firebooru.model.FavoriteSearchTagType[]) com.bisimplex.firebooru.model.FavoriteSearchTagType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
