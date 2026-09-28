package com.bisimplex.firebooru.danbooru;
public final enum class FileNameType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.FileNameType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.FileNameType Legacy;
    public static final enum com.bisimplex.firebooru.danbooru.FileNameType MD5;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.FileNameType[] $values()
    {
        return new com.bisimplex.firebooru.danbooru.FileNameType[] {com.bisimplex.firebooru.danbooru.FileNameType.MD5, com.bisimplex.firebooru.danbooru.FileNameType.Legacy});
    }

    static FileNameType()
    {
        com.bisimplex.firebooru.danbooru.FileNameType.MD5 = new com.bisimplex.firebooru.danbooru.FileNameType("MD5", 0, 0);
        com.bisimplex.firebooru.danbooru.FileNameType.Legacy = new com.bisimplex.firebooru.danbooru.FileNameType("Legacy", 1, 1);
        com.bisimplex.firebooru.danbooru.FileNameType.$VALUES = com.bisimplex.firebooru.danbooru.FileNameType.$values();
        return;
    }

    private FileNameType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.FileNameType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.danbooru.FileNameType.Legacy;
        } else {
            return com.bisimplex.firebooru.danbooru.FileNameType.MD5;
        }
    }

    public static com.bisimplex.firebooru.danbooru.FileNameType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.FileNameType) Enum.valueOf(com.bisimplex.firebooru.danbooru.FileNameType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.FileNameType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.FileNameType[]) com.bisimplex.firebooru.danbooru.FileNameType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
