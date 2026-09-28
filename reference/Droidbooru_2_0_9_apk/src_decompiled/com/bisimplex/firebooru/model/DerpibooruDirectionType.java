package com.bisimplex.firebooru.model;
public final enum class DerpibooruDirectionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.DerpibooruDirectionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.DerpibooruDirectionType asc;
    public static final enum com.bisimplex.firebooru.model.DerpibooruDirectionType desc;
    private final String value;

    private static synthetic com.bisimplex.firebooru.model.DerpibooruDirectionType[] $values()
    {
        return new com.bisimplex.firebooru.model.DerpibooruDirectionType[] {com.bisimplex.firebooru.model.DerpibooruDirectionType.desc, com.bisimplex.firebooru.model.DerpibooruDirectionType.asc});
    }

    static DerpibooruDirectionType()
    {
        com.bisimplex.firebooru.model.DerpibooruDirectionType.desc = new com.bisimplex.firebooru.model.DerpibooruDirectionType("desc", 0, "desc");
        com.bisimplex.firebooru.model.DerpibooruDirectionType.asc = new com.bisimplex.firebooru.model.DerpibooruDirectionType("asc", 1, "asc");
        com.bisimplex.firebooru.model.DerpibooruDirectionType.$VALUES = com.bisimplex.firebooru.model.DerpibooruDirectionType.$values();
        return;
    }

    private DerpibooruDirectionType(String p1, int p2, String p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static java.util.List all()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(2);
        v0_1.add(com.bisimplex.firebooru.model.DerpibooruDirectionType.desc);
        v0_1.add(com.bisimplex.firebooru.model.DerpibooruDirectionType.asc);
        return v0_1;
    }

    public static com.bisimplex.firebooru.model.DerpibooruDirectionType fromString(String p1)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            if (!p1.equalsIgnoreCase("asc")) {
                return com.bisimplex.firebooru.model.DerpibooruDirectionType.desc;
            } else {
                return com.bisimplex.firebooru.model.DerpibooruDirectionType.asc;
            }
        } else {
            return com.bisimplex.firebooru.model.DerpibooruDirectionType.desc;
        }
    }

    public static com.bisimplex.firebooru.model.DerpibooruDirectionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.DerpibooruDirectionType) Enum.valueOf(com.bisimplex.firebooru.model.DerpibooruDirectionType, p1));
    }

    public static com.bisimplex.firebooru.model.DerpibooruDirectionType[] values()
    {
        return ((com.bisimplex.firebooru.model.DerpibooruDirectionType[]) com.bisimplex.firebooru.model.DerpibooruDirectionType.$VALUES.clone());
    }

    public String getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return this.value;
    }
}
