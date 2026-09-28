package com.bisimplex.firebooru.dataadapter.search;
public final enum class ActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.dataadapter.search.ActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Add;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Delete;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Edit;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Focus;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Next;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType None;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Reset;
    public static final enum com.bisimplex.firebooru.dataadapter.search.ActionType Search;
    private final int value;

    private static synthetic com.bisimplex.firebooru.dataadapter.search.ActionType[] $values()
    {
        com.bisimplex.firebooru.dataadapter.search.ActionType v2 = com.bisimplex.firebooru.dataadapter.search.ActionType.Add;
        com.bisimplex.firebooru.dataadapter.search.ActionType v4 = com.bisimplex.firebooru.dataadapter.search.ActionType.Edit;
        com.bisimplex.firebooru.dataadapter.search.ActionType v6 = com.bisimplex.firebooru.dataadapter.search.ActionType.Next;
        return new com.bisimplex.firebooru.dataadapter.search.ActionType[] {com.bisimplex.firebooru.dataadapter.search.ActionType.None, com.bisimplex.firebooru.dataadapter.search.ActionType.Focus});
    }

    static ActionType()
    {
        com.bisimplex.firebooru.dataadapter.search.ActionType.None = new com.bisimplex.firebooru.dataadapter.search.ActionType("None", 0, 0);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Search = new com.bisimplex.firebooru.dataadapter.search.ActionType("Search", 1, 1);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Add = new com.bisimplex.firebooru.dataadapter.search.ActionType("Add", 2, 2);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Delete = new com.bisimplex.firebooru.dataadapter.search.ActionType("Delete", 3, 3);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Edit = new com.bisimplex.firebooru.dataadapter.search.ActionType("Edit", 4, 4);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Reset = new com.bisimplex.firebooru.dataadapter.search.ActionType("Reset", 5, 5);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Next = new com.bisimplex.firebooru.dataadapter.search.ActionType("Next", 6, 6);
        com.bisimplex.firebooru.dataadapter.search.ActionType.Focus = new com.bisimplex.firebooru.dataadapter.search.ActionType("Focus", 7, 7);
        com.bisimplex.firebooru.dataadapter.search.ActionType.$VALUES = com.bisimplex.firebooru.dataadapter.search.ActionType.$values();
        return;
    }

    private ActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.dataadapter.search.ActionType fromInteger(int p0)
    {
        switch (p0) {
            case 1:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Search;
            case 2:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Add;
            case 3:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Delete;
            case 4:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Edit;
            case 5:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Reset;
            case 6:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Next;
            case 7:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.Focus;
            default:
                return com.bisimplex.firebooru.dataadapter.search.ActionType.None;
        }
    }

    public static com.bisimplex.firebooru.dataadapter.search.ActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.dataadapter.search.ActionType) Enum.valueOf(com.bisimplex.firebooru.dataadapter.search.ActionType, p1));
    }

    public static com.bisimplex.firebooru.dataadapter.search.ActionType[] values()
    {
        return ((com.bisimplex.firebooru.dataadapter.search.ActionType[]) com.bisimplex.firebooru.dataadapter.search.ActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
