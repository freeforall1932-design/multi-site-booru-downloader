package com.bisimplex.firebooru.services;
public final enum class ReloadStatusType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.services.ReloadStatusType[] $VALUES;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType Cancel;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType Error;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType NotFound;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType ServerNotFound;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType ServerNotSupported;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType Suppend;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType URLMalformed;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType URLValid;
    public static final enum com.bisimplex.firebooru.services.ReloadStatusType Updated;

    private static synthetic com.bisimplex.firebooru.services.ReloadStatusType[] $values()
    {
        com.bisimplex.firebooru.services.ReloadStatusType v2 = com.bisimplex.firebooru.services.ReloadStatusType.Updated;
        com.bisimplex.firebooru.services.ReloadStatusType v4 = com.bisimplex.firebooru.services.ReloadStatusType.URLValid;
        com.bisimplex.firebooru.services.ReloadStatusType v6 = com.bisimplex.firebooru.services.ReloadStatusType.Suppend;
        return new com.bisimplex.firebooru.services.ReloadStatusType[] {com.bisimplex.firebooru.services.ReloadStatusType.ServerNotFound, com.bisimplex.firebooru.services.ReloadStatusType.Error});
    }

    static ReloadStatusType()
    {
        com.bisimplex.firebooru.services.ReloadStatusType.ServerNotFound = new com.bisimplex.firebooru.services.ReloadStatusType("ServerNotFound", 0);
        com.bisimplex.firebooru.services.ReloadStatusType.ServerNotSupported = new com.bisimplex.firebooru.services.ReloadStatusType("ServerNotSupported", 1);
        com.bisimplex.firebooru.services.ReloadStatusType.Updated = new com.bisimplex.firebooru.services.ReloadStatusType("Updated", 2);
        com.bisimplex.firebooru.services.ReloadStatusType.URLMalformed = new com.bisimplex.firebooru.services.ReloadStatusType("URLMalformed", 3);
        com.bisimplex.firebooru.services.ReloadStatusType.URLValid = new com.bisimplex.firebooru.services.ReloadStatusType("URLValid", 4);
        com.bisimplex.firebooru.services.ReloadStatusType.NotFound = new com.bisimplex.firebooru.services.ReloadStatusType("NotFound", 5);
        com.bisimplex.firebooru.services.ReloadStatusType.Suppend = new com.bisimplex.firebooru.services.ReloadStatusType("Suppend", 6);
        com.bisimplex.firebooru.services.ReloadStatusType.Cancel = new com.bisimplex.firebooru.services.ReloadStatusType("Cancel", 7);
        com.bisimplex.firebooru.services.ReloadStatusType.Error = new com.bisimplex.firebooru.services.ReloadStatusType("Error", 8);
        com.bisimplex.firebooru.services.ReloadStatusType.$VALUES = com.bisimplex.firebooru.services.ReloadStatusType.$values();
        return;
    }

    private ReloadStatusType(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static com.bisimplex.firebooru.services.ReloadStatusType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.services.ReloadStatusType) Enum.valueOf(com.bisimplex.firebooru.services.ReloadStatusType, p1));
    }

    public static com.bisimplex.firebooru.services.ReloadStatusType[] values()
    {
        return ((com.bisimplex.firebooru.services.ReloadStatusType[]) com.bisimplex.firebooru.services.ReloadStatusType.$VALUES.clone());
    }
}
