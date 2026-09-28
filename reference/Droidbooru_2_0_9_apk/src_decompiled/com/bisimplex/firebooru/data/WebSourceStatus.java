package com.bisimplex.firebooru.data;
public final enum class WebSourceStatus extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.data.WebSourceStatus[] $VALUES;
    public static final enum com.bisimplex.firebooru.data.WebSourceStatus Awake;
    public static final enum com.bisimplex.firebooru.data.WebSourceStatus GoingToSleep;
    public static final enum com.bisimplex.firebooru.data.WebSourceStatus Sleeping;
    private final int value;

    private static synthetic com.bisimplex.firebooru.data.WebSourceStatus[] $values()
    {
        return new com.bisimplex.firebooru.data.WebSourceStatus[] {com.bisimplex.firebooru.data.WebSourceStatus.Awake, com.bisimplex.firebooru.data.WebSourceStatus.Sleeping, com.bisimplex.firebooru.data.WebSourceStatus.GoingToSleep});
    }

    static WebSourceStatus()
    {
        com.bisimplex.firebooru.data.WebSourceStatus.Awake = new com.bisimplex.firebooru.data.WebSourceStatus("Awake", 0, 0);
        com.bisimplex.firebooru.data.WebSourceStatus.Sleeping = new com.bisimplex.firebooru.data.WebSourceStatus("Sleeping", 1, 1);
        com.bisimplex.firebooru.data.WebSourceStatus.GoingToSleep = new com.bisimplex.firebooru.data.WebSourceStatus("GoingToSleep", 2, 2);
        com.bisimplex.firebooru.data.WebSourceStatus.$VALUES = com.bisimplex.firebooru.data.WebSourceStatus.$values();
        return;
    }

    private WebSourceStatus(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.data.WebSourceStatus fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.data.WebSourceStatus.Awake;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.data.WebSourceStatus.Sleeping;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.data.WebSourceStatus.GoingToSleep;
                } else {
                    return com.bisimplex.firebooru.data.WebSourceStatus.Awake;
                }
            }
        }
    }

    public static com.bisimplex.firebooru.data.WebSourceStatus valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.data.WebSourceStatus) Enum.valueOf(com.bisimplex.firebooru.data.WebSourceStatus, p1));
    }

    public static com.bisimplex.firebooru.data.WebSourceStatus[] values()
    {
        return ((com.bisimplex.firebooru.data.WebSourceStatus[]) com.bisimplex.firebooru.data.WebSourceStatus.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
