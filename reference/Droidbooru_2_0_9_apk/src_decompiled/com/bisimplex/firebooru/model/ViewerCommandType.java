package com.bisimplex.firebooru.model;
public final enum class ViewerCommandType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.ViewerCommandType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType Back;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType None;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType Save;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType Share;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ShowInfo;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ToggleFavorite;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ToggleNotes;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ViewNormal;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ViewOriginal;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ZoomIn;
    public static final enum com.bisimplex.firebooru.model.ViewerCommandType ZoomOut;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.ViewerCommandType[] $values()
    {
        com.bisimplex.firebooru.model.ViewerCommandType v2 = com.bisimplex.firebooru.model.ViewerCommandType.Save;
        com.bisimplex.firebooru.model.ViewerCommandType v4 = com.bisimplex.firebooru.model.ViewerCommandType.ViewNormal;
        com.bisimplex.firebooru.model.ViewerCommandType v6 = com.bisimplex.firebooru.model.ViewerCommandType.Share;
        com.bisimplex.firebooru.model.ViewerCommandType v8 = com.bisimplex.firebooru.model.ViewerCommandType.Back;
        return new com.bisimplex.firebooru.model.ViewerCommandType[] {com.bisimplex.firebooru.model.ViewerCommandType.None, com.bisimplex.firebooru.model.ViewerCommandType.ZoomOut});
    }

    static ViewerCommandType()
    {
        com.bisimplex.firebooru.model.ViewerCommandType.None = new com.bisimplex.firebooru.model.ViewerCommandType("None", 0, 0);
        com.bisimplex.firebooru.model.ViewerCommandType.ShowInfo = new com.bisimplex.firebooru.model.ViewerCommandType("ShowInfo", 1, 1);
        com.bisimplex.firebooru.model.ViewerCommandType.Save = new com.bisimplex.firebooru.model.ViewerCommandType("Save", 2, 2);
        com.bisimplex.firebooru.model.ViewerCommandType.ToggleFavorite = new com.bisimplex.firebooru.model.ViewerCommandType("ToggleFavorite", 3, 3);
        com.bisimplex.firebooru.model.ViewerCommandType.ViewNormal = new com.bisimplex.firebooru.model.ViewerCommandType("ViewNormal", 4, 4);
        com.bisimplex.firebooru.model.ViewerCommandType.ViewOriginal = new com.bisimplex.firebooru.model.ViewerCommandType("ViewOriginal", 5, 5);
        com.bisimplex.firebooru.model.ViewerCommandType.Share = new com.bisimplex.firebooru.model.ViewerCommandType("Share", 6, 6);
        com.bisimplex.firebooru.model.ViewerCommandType.ToggleNotes = new com.bisimplex.firebooru.model.ViewerCommandType("ToggleNotes", 7, 7);
        com.bisimplex.firebooru.model.ViewerCommandType.Back = new com.bisimplex.firebooru.model.ViewerCommandType("Back", 8, 8);
        com.bisimplex.firebooru.model.ViewerCommandType.ZoomIn = new com.bisimplex.firebooru.model.ViewerCommandType("ZoomIn", 9, 9);
        com.bisimplex.firebooru.model.ViewerCommandType.ZoomOut = new com.bisimplex.firebooru.model.ViewerCommandType("ZoomOut", 10, 10);
        com.bisimplex.firebooru.model.ViewerCommandType.$VALUES = com.bisimplex.firebooru.model.ViewerCommandType.$values();
        return;
    }

    private ViewerCommandType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.model.ViewerCommandType fromInteger(int p0)
    {
        switch (p0) {
            case 1:
                return com.bisimplex.firebooru.model.ViewerCommandType.ShowInfo;
            case 2:
                return com.bisimplex.firebooru.model.ViewerCommandType.Save;
            case 3:
                return com.bisimplex.firebooru.model.ViewerCommandType.ToggleFavorite;
            case 4:
                return com.bisimplex.firebooru.model.ViewerCommandType.ViewNormal;
            case 5:
                return com.bisimplex.firebooru.model.ViewerCommandType.ViewOriginal;
            case 6:
                return com.bisimplex.firebooru.model.ViewerCommandType.Share;
            case 7:
                return com.bisimplex.firebooru.model.ViewerCommandType.ToggleNotes;
            case 8:
                return com.bisimplex.firebooru.model.ViewerCommandType.Back;
            case 9:
                return com.bisimplex.firebooru.model.ViewerCommandType.ZoomIn;
            case 10:
                return com.bisimplex.firebooru.model.ViewerCommandType.ZoomOut;
            default:
                return com.bisimplex.firebooru.model.ViewerCommandType.None;
        }
    }

    public static com.bisimplex.firebooru.model.ViewerCommandType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.ViewerCommandType) Enum.valueOf(com.bisimplex.firebooru.model.ViewerCommandType, p1));
    }

    public static com.bisimplex.firebooru.model.ViewerCommandType[] values()
    {
        return ((com.bisimplex.firebooru.model.ViewerCommandType[]) com.bisimplex.firebooru.model.ViewerCommandType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
