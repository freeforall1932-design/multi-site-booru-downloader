package com.bisimplex.firebooru.view;
public final enum class GestureType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.view.GestureType[] $VALUES;
    public static final enum com.bisimplex.firebooru.view.GestureType DoubleTap;
    public static final enum com.bisimplex.firebooru.view.GestureType None;
    public static final enum com.bisimplex.firebooru.view.GestureType SwipeToDown;
    public static final enum com.bisimplex.firebooru.view.GestureType SwipeToLeft;
    public static final enum com.bisimplex.firebooru.view.GestureType SwipeToRight;
    public static final enum com.bisimplex.firebooru.view.GestureType SwipeToUp;
    private final int value;

    private static synthetic com.bisimplex.firebooru.view.GestureType[] $values()
    {
        com.bisimplex.firebooru.view.GestureType v2 = com.bisimplex.firebooru.view.GestureType.SwipeToUp;
        com.bisimplex.firebooru.view.GestureType v4 = com.bisimplex.firebooru.view.GestureType.SwipeToLeft;
        return new com.bisimplex.firebooru.view.GestureType[] {com.bisimplex.firebooru.view.GestureType.None, com.bisimplex.firebooru.view.GestureType.SwipeToRight});
    }

    static GestureType()
    {
        com.bisimplex.firebooru.view.GestureType.None = new com.bisimplex.firebooru.view.GestureType("None", 0, 0);
        com.bisimplex.firebooru.view.GestureType.DoubleTap = new com.bisimplex.firebooru.view.GestureType("DoubleTap", 1, 1);
        com.bisimplex.firebooru.view.GestureType.SwipeToUp = new com.bisimplex.firebooru.view.GestureType("SwipeToUp", 2, 2);
        com.bisimplex.firebooru.view.GestureType.SwipeToDown = new com.bisimplex.firebooru.view.GestureType("SwipeToDown", 3, 3);
        com.bisimplex.firebooru.view.GestureType.SwipeToLeft = new com.bisimplex.firebooru.view.GestureType("SwipeToLeft", 4, 4);
        com.bisimplex.firebooru.view.GestureType.SwipeToRight = new com.bisimplex.firebooru.view.GestureType("SwipeToRight", 5, 5);
        com.bisimplex.firebooru.view.GestureType.$VALUES = com.bisimplex.firebooru.view.GestureType.$values();
        return;
    }

    private GestureType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.view.GestureType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.view.GestureType.DoubleTap;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.view.GestureType.SwipeToUp;
            } else {
                if (p1 == 3) {
                    return com.bisimplex.firebooru.view.GestureType.SwipeToDown;
                } else {
                    if (p1 == 4) {
                        return com.bisimplex.firebooru.view.GestureType.SwipeToLeft;
                    } else {
                        if (p1 == 5) {
                            return com.bisimplex.firebooru.view.GestureType.SwipeToRight;
                        } else {
                            return com.bisimplex.firebooru.view.GestureType.None;
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.view.GestureType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.view.GestureType) Enum.valueOf(com.bisimplex.firebooru.view.GestureType, p1));
    }

    public static com.bisimplex.firebooru.view.GestureType[] values()
    {
        return ((com.bisimplex.firebooru.view.GestureType[]) com.bisimplex.firebooru.view.GestureType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
