package com.bisimplex.firebooru.view;
public final enum class HistoryTagMenuDialog$HistoryTagActionType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType[] $VALUES;
    public static final enum com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType Cancel;
    public static final enum com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType Copy;
    public static final enum com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType Delete;
    public static final enum com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType ToggleFav;
    private final int value;

    private static synthetic com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType[] $values()
    {
        return new com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType[] {com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.ToggleFav, com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Delete, com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Copy, com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Cancel});
    }

    static HistoryTagMenuDialog$HistoryTagActionType()
    {
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.ToggleFav = new com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType("ToggleFav", 0, 0);
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Delete = new com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType("Delete", 1, 1);
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Copy = new com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType("Copy", 2, 2);
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Cancel = new com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType("Cancel", 3, -1);
        com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.$VALUES = com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.$values();
        return;
    }

    private HistoryTagMenuDialog$HistoryTagActionType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType fromInteger(int p1)
    {
        if (p1 == -1) {
            return com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Cancel;
        } else {
            if (p1 == null) {
                return com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.ToggleFav;
            } else {
                if (p1 == 1) {
                    return com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Delete;
                } else {
                    if (p1 == 2) {
                        return com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Copy;
                    } else {
                        return com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.Cancel;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType) Enum.valueOf(com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType, p1));
    }

    public static com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType[] values()
    {
        return ((com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType[]) com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
