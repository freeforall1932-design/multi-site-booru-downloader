package com.bisimplex.firebooru.activity;
public final enum class MessageType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.activity.MessageType[] $VALUES;
    public static final enum com.bisimplex.firebooru.activity.MessageType Error;
    public static final enum com.bisimplex.firebooru.activity.MessageType Info;
    public static final enum com.bisimplex.firebooru.activity.MessageType Loading;
    public static final enum com.bisimplex.firebooru.activity.MessageType Minimal;
    public static final enum com.bisimplex.firebooru.activity.MessageType None;
    public static final enum com.bisimplex.firebooru.activity.MessageType Success;

    private static synthetic com.bisimplex.firebooru.activity.MessageType[] $values()
    {
        com.bisimplex.firebooru.activity.MessageType v2 = com.bisimplex.firebooru.activity.MessageType.Success;
        com.bisimplex.firebooru.activity.MessageType v4 = com.bisimplex.firebooru.activity.MessageType.Loading;
        return new com.bisimplex.firebooru.activity.MessageType[] {com.bisimplex.firebooru.activity.MessageType.None, com.bisimplex.firebooru.activity.MessageType.Minimal});
    }

    static MessageType()
    {
        com.bisimplex.firebooru.activity.MessageType.None = new com.bisimplex.firebooru.activity.MessageType("None", 0);
        com.bisimplex.firebooru.activity.MessageType.Info = new com.bisimplex.firebooru.activity.MessageType("Info", 1);
        com.bisimplex.firebooru.activity.MessageType.Success = new com.bisimplex.firebooru.activity.MessageType("Success", 2);
        com.bisimplex.firebooru.activity.MessageType.Error = new com.bisimplex.firebooru.activity.MessageType("Error", 3);
        com.bisimplex.firebooru.activity.MessageType.Loading = new com.bisimplex.firebooru.activity.MessageType("Loading", 4);
        com.bisimplex.firebooru.activity.MessageType.Minimal = new com.bisimplex.firebooru.activity.MessageType("Minimal", 5);
        com.bisimplex.firebooru.activity.MessageType.$VALUES = com.bisimplex.firebooru.activity.MessageType.$values();
        return;
    }

    private MessageType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static int TypeToInt(com.bisimplex.firebooru.activity.MessageType p2)
    {
        int v2_3 = com.bisimplex.firebooru.activity.MessageType$1.$SwitchMap$com$bisimplex$firebooru$activity$MessageType[p2.ordinal()];
        if (v2_3 == 2) {
            return 1;
        } else {
            if (v2_3 == 3) {
                return 2;
            } else {
                if (v2_3 == 4) {
                    return 3;
                } else {
                    if (v2_3 == 5) {
                        return 4;
                    } else {
                        if (v2_3 == 6) {
                            return 5;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.activity.MessageType intToType(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.activity.MessageType.None;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.activity.MessageType.Info;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.activity.MessageType.Success;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.activity.MessageType.Error;
                    } else {
                        if (p1 == 4) {
                            return com.bisimplex.firebooru.activity.MessageType.Loading;
                        } else {
                            if (p1 == 5) {
                                return com.bisimplex.firebooru.activity.MessageType.Minimal;
                            } else {
                                return com.bisimplex.firebooru.activity.MessageType.None;
                            }
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.activity.MessageType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.activity.MessageType) Enum.valueOf(com.bisimplex.firebooru.activity.MessageType, p1));
    }

    public static com.bisimplex.firebooru.activity.MessageType[] values()
    {
        return ((com.bisimplex.firebooru.activity.MessageType[]) com.bisimplex.firebooru.activity.MessageType.$VALUES.clone());
    }
}
