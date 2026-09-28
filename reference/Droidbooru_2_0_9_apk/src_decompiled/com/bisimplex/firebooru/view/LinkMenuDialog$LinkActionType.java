package com.bisimplex.firebooru.view;
public final enum class LinkMenuDialog$LinkActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType Copy;
    public static final enum com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType Send;
    private final int value;

    private static synthetic com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType[] $values()
    {
        return new com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType[] {com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Send, com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Copy});
    }

    static LinkMenuDialog$LinkActionType()
    {
        com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Send = new com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType("Send", 0, 0);
        com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Copy = new com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType("Copy", 1, 1);
        com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.$VALUES = com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.$values();
        return;
    }

    private LinkMenuDialog$LinkActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Send;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Copy;
            } else {
                return com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.Send;
            }
        }
    }

    public static com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType) Enum.valueOf(com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType, p1));
    }

    public static com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType[] values()
    {
        return ((com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType[]) com.bisimplex.firebooru.view.LinkMenuDialog$LinkActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
