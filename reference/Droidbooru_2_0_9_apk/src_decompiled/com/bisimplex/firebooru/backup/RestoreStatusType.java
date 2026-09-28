package com.bisimplex.firebooru.backup;
public final enum class RestoreStatusType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.backup.RestoreStatusType[] $VALUES;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType CannotBeParsed;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType CannotOpenFile;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType CannotReadLine;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType DoesntExist;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType Error;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType InvalidFile;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType InvalidParameters;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType None;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType NotFound;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType PermissionDenied;
    public static final enum com.bisimplex.firebooru.backup.RestoreStatusType Success;
    private final int value;

    private static synthetic com.bisimplex.firebooru.backup.RestoreStatusType[] $values()
    {
        com.bisimplex.firebooru.backup.RestoreStatusType v2 = com.bisimplex.firebooru.backup.RestoreStatusType.PermissionDenied;
        com.bisimplex.firebooru.backup.RestoreStatusType v4 = com.bisimplex.firebooru.backup.RestoreStatusType.None;
        com.bisimplex.firebooru.backup.RestoreStatusType v6 = com.bisimplex.firebooru.backup.RestoreStatusType.NotFound;
        com.bisimplex.firebooru.backup.RestoreStatusType v8 = com.bisimplex.firebooru.backup.RestoreStatusType.CannotReadLine;
        return new com.bisimplex.firebooru.backup.RestoreStatusType[] {com.bisimplex.firebooru.backup.RestoreStatusType.Success, com.bisimplex.firebooru.backup.RestoreStatusType.InvalidParameters});
    }

    static RestoreStatusType()
    {
        com.bisimplex.firebooru.backup.RestoreStatusType.Success = new com.bisimplex.firebooru.backup.RestoreStatusType("Success", 0, 0);
        com.bisimplex.firebooru.backup.RestoreStatusType.InvalidFile = new com.bisimplex.firebooru.backup.RestoreStatusType("InvalidFile", 1, 1);
        com.bisimplex.firebooru.backup.RestoreStatusType.PermissionDenied = new com.bisimplex.firebooru.backup.RestoreStatusType("PermissionDenied", 2, 2);
        com.bisimplex.firebooru.backup.RestoreStatusType.Error = new com.bisimplex.firebooru.backup.RestoreStatusType("Error", 3, 3);
        com.bisimplex.firebooru.backup.RestoreStatusType.None = new com.bisimplex.firebooru.backup.RestoreStatusType("None", 4, 4);
        com.bisimplex.firebooru.backup.RestoreStatusType.CannotOpenFile = new com.bisimplex.firebooru.backup.RestoreStatusType("CannotOpenFile", 5, 5);
        com.bisimplex.firebooru.backup.RestoreStatusType.NotFound = new com.bisimplex.firebooru.backup.RestoreStatusType("NotFound", 6, 6);
        com.bisimplex.firebooru.backup.RestoreStatusType.DoesntExist = new com.bisimplex.firebooru.backup.RestoreStatusType("DoesntExist", 7, 7);
        com.bisimplex.firebooru.backup.RestoreStatusType.CannotReadLine = new com.bisimplex.firebooru.backup.RestoreStatusType("CannotReadLine", 8, 8);
        com.bisimplex.firebooru.backup.RestoreStatusType.CannotBeParsed = new com.bisimplex.firebooru.backup.RestoreStatusType("CannotBeParsed", 9, 9);
        com.bisimplex.firebooru.backup.RestoreStatusType.InvalidParameters = new com.bisimplex.firebooru.backup.RestoreStatusType("InvalidParameters", 10, 10);
        com.bisimplex.firebooru.backup.RestoreStatusType.$VALUES = com.bisimplex.firebooru.backup.RestoreStatusType.$values();
        return;
    }

    private RestoreStatusType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.backup.RestoreStatusType fromInteger(int p0)
    {
        switch (p0) {
            case 0:
                return com.bisimplex.firebooru.backup.RestoreStatusType.Success;
            case 1:
                return com.bisimplex.firebooru.backup.RestoreStatusType.InvalidFile;
            case 2:
                return com.bisimplex.firebooru.backup.RestoreStatusType.PermissionDenied;
            case 3:
                return com.bisimplex.firebooru.backup.RestoreStatusType.Error;
            case 4:
            default:
                return com.bisimplex.firebooru.backup.RestoreStatusType.None;
            case 5:
                return com.bisimplex.firebooru.backup.RestoreStatusType.CannotOpenFile;
            case 6:
                return com.bisimplex.firebooru.backup.RestoreStatusType.NotFound;
            case 7:
                return com.bisimplex.firebooru.backup.RestoreStatusType.DoesntExist;
            case 8:
                return com.bisimplex.firebooru.backup.RestoreStatusType.CannotReadLine;
            case 9:
                return com.bisimplex.firebooru.backup.RestoreStatusType.CannotBeParsed;
            case 10:
                return com.bisimplex.firebooru.backup.RestoreStatusType.InvalidParameters;
        }
    }

    public static com.bisimplex.firebooru.backup.RestoreStatusType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.backup.RestoreStatusType) Enum.valueOf(com.bisimplex.firebooru.backup.RestoreStatusType, p1));
    }

    public static com.bisimplex.firebooru.backup.RestoreStatusType[] values()
    {
        return ((com.bisimplex.firebooru.backup.RestoreStatusType[]) com.bisimplex.firebooru.backup.RestoreStatusType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
