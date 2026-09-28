package com.bisimplex.firebooru.view;
public final enum class VideoErrorType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.view.VideoErrorType[] $VALUES;
    public static final enum com.bisimplex.firebooru.view.VideoErrorType Other;
    public static final enum com.bisimplex.firebooru.view.VideoErrorType Remote;
    public static final enum com.bisimplex.firebooru.view.VideoErrorType Renderer;
    public static final enum com.bisimplex.firebooru.view.VideoErrorType Source;
    private final int value;

    private static synthetic com.bisimplex.firebooru.view.VideoErrorType[] $values()
    {
        return new com.bisimplex.firebooru.view.VideoErrorType[] {com.bisimplex.firebooru.view.VideoErrorType.Source, com.bisimplex.firebooru.view.VideoErrorType.Remote, com.bisimplex.firebooru.view.VideoErrorType.Renderer, com.bisimplex.firebooru.view.VideoErrorType.Other});
    }

    static VideoErrorType()
    {
        com.bisimplex.firebooru.view.VideoErrorType.Source = new com.bisimplex.firebooru.view.VideoErrorType("Source", 0, 0);
        com.bisimplex.firebooru.view.VideoErrorType.Remote = new com.bisimplex.firebooru.view.VideoErrorType("Remote", 1, 1);
        com.bisimplex.firebooru.view.VideoErrorType.Renderer = new com.bisimplex.firebooru.view.VideoErrorType("Renderer", 2, 2);
        com.bisimplex.firebooru.view.VideoErrorType.Other = new com.bisimplex.firebooru.view.VideoErrorType("Other", 3, 3);
        com.bisimplex.firebooru.view.VideoErrorType.$VALUES = com.bisimplex.firebooru.view.VideoErrorType.$values();
        return;
    }

    private VideoErrorType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.view.VideoErrorType fromExoInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.view.VideoErrorType.Source;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.view.VideoErrorType.Renderer;
            } else {
                if (p1 == 3) {
                    return com.bisimplex.firebooru.view.VideoErrorType.Remote;
                } else {
                    return com.bisimplex.firebooru.view.VideoErrorType.Other;
                }
            }
        }
    }

    public static com.bisimplex.firebooru.view.VideoErrorType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.view.VideoErrorType) Enum.valueOf(com.bisimplex.firebooru.view.VideoErrorType, p1));
    }

    public static com.bisimplex.firebooru.view.VideoErrorType[] values()
    {
        return ((com.bisimplex.firebooru.view.VideoErrorType[]) com.bisimplex.firebooru.view.VideoErrorType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
