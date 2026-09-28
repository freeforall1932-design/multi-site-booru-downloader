package com.bisimplex.firebooru.model;
public final enum class VideoDecoderType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.VideoDecoderType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.VideoDecoderType Base;
    public static final enum com.bisimplex.firebooru.model.VideoDecoderType EXO;
    public static final enum com.bisimplex.firebooru.model.VideoDecoderType VLC;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.VideoDecoderType[] $values()
    {
        return new com.bisimplex.firebooru.model.VideoDecoderType[] {com.bisimplex.firebooru.model.VideoDecoderType.Base, com.bisimplex.firebooru.model.VideoDecoderType.VLC, com.bisimplex.firebooru.model.VideoDecoderType.EXO});
    }

    static VideoDecoderType()
    {
        com.bisimplex.firebooru.model.VideoDecoderType.Base = new com.bisimplex.firebooru.model.VideoDecoderType("Base", 0, 0);
        com.bisimplex.firebooru.model.VideoDecoderType.VLC = new com.bisimplex.firebooru.model.VideoDecoderType("VLC", 1, 1);
        com.bisimplex.firebooru.model.VideoDecoderType.EXO = new com.bisimplex.firebooru.model.VideoDecoderType("EXO", 2, 2);
        com.bisimplex.firebooru.model.VideoDecoderType.$VALUES = com.bisimplex.firebooru.model.VideoDecoderType.$values();
        return;
    }

    private VideoDecoderType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.model.VideoDecoderType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.model.VideoDecoderType.VLC;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.model.VideoDecoderType.EXO;
            } else {
                return com.bisimplex.firebooru.model.VideoDecoderType.Base;
            }
        }
    }

    public static com.bisimplex.firebooru.model.VideoDecoderType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.VideoDecoderType) Enum.valueOf(com.bisimplex.firebooru.model.VideoDecoderType, p1));
    }

    public static com.bisimplex.firebooru.model.VideoDecoderType[] values()
    {
        return ((com.bisimplex.firebooru.model.VideoDecoderType[]) com.bisimplex.firebooru.model.VideoDecoderType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
