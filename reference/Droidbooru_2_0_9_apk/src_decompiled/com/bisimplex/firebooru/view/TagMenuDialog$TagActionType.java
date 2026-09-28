package com.bisimplex.firebooru.view;
public final enum class TagMenuDialog$TagActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.view.TagMenuDialog$TagActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType BlacklistServer;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType Cancel;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType Copy;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType Pin;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType SaveToHistory;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType Search;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType SearchMinus;
    public static final enum com.bisimplex.firebooru.view.TagMenuDialog$TagActionType SearchPlus;
    private final int value;

    private static synthetic com.bisimplex.firebooru.view.TagMenuDialog$TagActionType[] $values()
    {
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType v2 = com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchMinus;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType v4 = com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SaveToHistory;
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType v6 = com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Pin;
        return new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType[] {com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Search, com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Cancel});
    }

    static TagMenuDialog$TagActionType()
    {
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Search = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("Search", 0, 0);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchPlus = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("SearchPlus", 1, 1);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchMinus = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("SearchMinus", 2, 2);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Copy = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("Copy", 3, 3);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SaveToHistory = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("SaveToHistory", 4, 4);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.BlacklistServer = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("BlacklistServer", 5, 5);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Pin = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("Pin", 6, 6);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Cancel = new com.bisimplex.firebooru.view.TagMenuDialog$TagActionType("Cancel", 7, -1);
        com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.$VALUES = com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.$values();
        return;
    }

    private TagMenuDialog$TagActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.view.TagMenuDialog$TagActionType fromInteger(int p0)
    {
        switch (p0) {
            case -1:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Cancel;
            case 0:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Search;
            case 1:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchPlus;
            case 2:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SearchMinus;
            case 3:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Copy;
            case 4:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.SaveToHistory;
            case 5:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.BlacklistServer;
            case 6:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Pin;
            default:
                return com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.Search;
        }
    }

    public static com.bisimplex.firebooru.view.TagMenuDialog$TagActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.view.TagMenuDialog$TagActionType) Enum.valueOf(com.bisimplex.firebooru.view.TagMenuDialog$TagActionType, p1));
    }

    public static com.bisimplex.firebooru.view.TagMenuDialog$TagActionType[] values()
    {
        return ((com.bisimplex.firebooru.view.TagMenuDialog$TagActionType[]) com.bisimplex.firebooru.view.TagMenuDialog$TagActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
