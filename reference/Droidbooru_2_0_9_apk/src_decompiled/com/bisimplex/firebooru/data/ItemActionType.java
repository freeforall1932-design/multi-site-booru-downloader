package com.bisimplex.firebooru.data;
public final enum class ItemActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.data.ItemActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.data.ItemActionType AddToGroup;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Browse;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Delete;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Edit;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Expand;
    public static final enum com.bisimplex.firebooru.data.ItemActionType LoadFailure;
    public static final enum com.bisimplex.firebooru.data.ItemActionType LoadPage;
    public static final enum com.bisimplex.firebooru.data.ItemActionType LoadSuccess;
    public static final enum com.bisimplex.firebooru.data.ItemActionType MoveDown;
    public static final enum com.bisimplex.firebooru.data.ItemActionType MoveFirst;
    public static final enum com.bisimplex.firebooru.data.ItemActionType MoveLast;
    public static final enum com.bisimplex.firebooru.data.ItemActionType MoveUp;
    public static final enum com.bisimplex.firebooru.data.ItemActionType None;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Reload;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Rename;
    public static final enum com.bisimplex.firebooru.data.ItemActionType Search;
    public static final enum com.bisimplex.firebooru.data.ItemActionType ShowGroup;
    public static final enum com.bisimplex.firebooru.data.ItemActionType SortName;
    public static final enum com.bisimplex.firebooru.data.ItemActionType SortNameDesc;
    private final int value;

    private static synthetic com.bisimplex.firebooru.data.ItemActionType[] $values()
    {
        com.bisimplex.firebooru.data.ItemActionType v3 = com.bisimplex.firebooru.data.ItemActionType.Browse;
        com.bisimplex.firebooru.data.ItemActionType v5 = com.bisimplex.firebooru.data.ItemActionType.Reload;
        com.bisimplex.firebooru.data.ItemActionType v7 = com.bisimplex.firebooru.data.ItemActionType.LoadSuccess;
        com.bisimplex.firebooru.data.ItemActionType v9 = com.bisimplex.firebooru.data.ItemActionType.MoveUp;
        com.bisimplex.firebooru.data.ItemActionType v11 = com.bisimplex.firebooru.data.ItemActionType.MoveFirst;
        com.bisimplex.firebooru.data.ItemActionType v13 = com.bisimplex.firebooru.data.ItemActionType.AddToGroup;
        com.bisimplex.firebooru.data.ItemActionType v15 = com.bisimplex.firebooru.data.ItemActionType.Rename;
        com.bisimplex.firebooru.data.ItemActionType v17 = com.bisimplex.firebooru.data.ItemActionType.SortNameDesc;
        return new com.bisimplex.firebooru.data.ItemActionType[] {com.bisimplex.firebooru.data.ItemActionType.None, com.bisimplex.firebooru.data.ItemActionType.Expand});
    }

    static ItemActionType()
    {
        com.bisimplex.firebooru.data.ItemActionType.None = new com.bisimplex.firebooru.data.ItemActionType("None", 0, 0);
        com.bisimplex.firebooru.data.ItemActionType.Search = new com.bisimplex.firebooru.data.ItemActionType("Search", 1, 1);
        com.bisimplex.firebooru.data.ItemActionType.Browse = new com.bisimplex.firebooru.data.ItemActionType("Browse", 2, 2);
        com.bisimplex.firebooru.data.ItemActionType.Delete = new com.bisimplex.firebooru.data.ItemActionType("Delete", 3, 3);
        com.bisimplex.firebooru.data.ItemActionType.Reload = new com.bisimplex.firebooru.data.ItemActionType("Reload", 4, 4);
        com.bisimplex.firebooru.data.ItemActionType.LoadPage = new com.bisimplex.firebooru.data.ItemActionType("LoadPage", 5, 5);
        com.bisimplex.firebooru.data.ItemActionType.LoadSuccess = new com.bisimplex.firebooru.data.ItemActionType("LoadSuccess", 6, 6);
        com.bisimplex.firebooru.data.ItemActionType.LoadFailure = new com.bisimplex.firebooru.data.ItemActionType("LoadFailure", 7, 7);
        com.bisimplex.firebooru.data.ItemActionType.MoveUp = new com.bisimplex.firebooru.data.ItemActionType("MoveUp", 8, 8);
        com.bisimplex.firebooru.data.ItemActionType.MoveDown = new com.bisimplex.firebooru.data.ItemActionType("MoveDown", 9, 9);
        com.bisimplex.firebooru.data.ItemActionType.MoveFirst = new com.bisimplex.firebooru.data.ItemActionType("MoveFirst", 10, 10);
        com.bisimplex.firebooru.data.ItemActionType.MoveLast = new com.bisimplex.firebooru.data.ItemActionType("MoveLast", 11, 11);
        com.bisimplex.firebooru.data.ItemActionType.AddToGroup = new com.bisimplex.firebooru.data.ItemActionType("AddToGroup", 12, 12);
        com.bisimplex.firebooru.data.ItemActionType.ShowGroup = new com.bisimplex.firebooru.data.ItemActionType("ShowGroup", 13, 13);
        com.bisimplex.firebooru.data.ItemActionType.Rename = new com.bisimplex.firebooru.data.ItemActionType("Rename", 14, 14);
        com.bisimplex.firebooru.data.ItemActionType.SortName = new com.bisimplex.firebooru.data.ItemActionType("SortName", 15, 15);
        com.bisimplex.firebooru.data.ItemActionType.SortNameDesc = new com.bisimplex.firebooru.data.ItemActionType("SortNameDesc", 16, 16);
        com.bisimplex.firebooru.data.ItemActionType.Edit = new com.bisimplex.firebooru.data.ItemActionType("Edit", 17, 17);
        com.bisimplex.firebooru.data.ItemActionType.Expand = new com.bisimplex.firebooru.data.ItemActionType("Expand", 18, 18);
        com.bisimplex.firebooru.data.ItemActionType.$VALUES = com.bisimplex.firebooru.data.ItemActionType.$values();
        return;
    }

    private ItemActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.data.ItemActionType fromInteger(int p0)
    {
        switch (p0) {
            case 1:
                return com.bisimplex.firebooru.data.ItemActionType.Search;
            case 2:
                return com.bisimplex.firebooru.data.ItemActionType.Browse;
            case 3:
                return com.bisimplex.firebooru.data.ItemActionType.Delete;
            case 4:
                return com.bisimplex.firebooru.data.ItemActionType.Reload;
            case 5:
                return com.bisimplex.firebooru.data.ItemActionType.LoadPage;
            case 6:
                return com.bisimplex.firebooru.data.ItemActionType.LoadSuccess;
            case 7:
                return com.bisimplex.firebooru.data.ItemActionType.LoadFailure;
            case 8:
                return com.bisimplex.firebooru.data.ItemActionType.MoveUp;
            case 9:
                return com.bisimplex.firebooru.data.ItemActionType.MoveDown;
            case 10:
                return com.bisimplex.firebooru.data.ItemActionType.MoveFirst;
            case 11:
                return com.bisimplex.firebooru.data.ItemActionType.MoveLast;
            case 12:
                return com.bisimplex.firebooru.data.ItemActionType.AddToGroup;
            case 13:
                return com.bisimplex.firebooru.data.ItemActionType.ShowGroup;
            case 14:
                return com.bisimplex.firebooru.data.ItemActionType.Rename;
            case 15:
                return com.bisimplex.firebooru.data.ItemActionType.SortName;
            case 16:
                return com.bisimplex.firebooru.data.ItemActionType.SortNameDesc;
            case 17:
                return com.bisimplex.firebooru.data.ItemActionType.Edit;
            case 18:
                return com.bisimplex.firebooru.data.ItemActionType.Expand;
            default:
                return com.bisimplex.firebooru.data.ItemActionType.None;
        }
    }

    public static com.bisimplex.firebooru.data.ItemActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.data.ItemActionType) Enum.valueOf(com.bisimplex.firebooru.data.ItemActionType, p1));
    }

    public static com.bisimplex.firebooru.data.ItemActionType[] values()
    {
        return ((com.bisimplex.firebooru.data.ItemActionType[]) com.bisimplex.firebooru.data.ItemActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
