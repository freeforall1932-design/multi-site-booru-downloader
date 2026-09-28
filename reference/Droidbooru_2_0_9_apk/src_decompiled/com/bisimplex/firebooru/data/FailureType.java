package com.bisimplex.firebooru.data;
public final enum class FailureType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.data.FailureType[] $VALUES;
    public static final enum com.bisimplex.firebooru.data.FailureType AccessDenied;
    public static final enum com.bisimplex.firebooru.data.FailureType AlreadyExist;
    public static final enum com.bisimplex.firebooru.data.FailureType Connection;
    public static final enum com.bisimplex.firebooru.data.FailureType ContentCannotBeParsed;
    public static final enum com.bisimplex.firebooru.data.FailureType Forbidden;
    public static final enum com.bisimplex.firebooru.data.FailureType InvalidAppKey;
    public static final enum com.bisimplex.firebooru.data.FailureType InvalidCert;
    public static final enum com.bisimplex.firebooru.data.FailureType InvalidParameters;
    public static final enum com.bisimplex.firebooru.data.FailureType InvalidRecord;
    public static final enum com.bisimplex.firebooru.data.FailureType Locked;
    public static final enum com.bisimplex.firebooru.data.FailureType NotFound;
    public static final enum com.bisimplex.firebooru.data.FailureType ServerError;
    public static final enum com.bisimplex.firebooru.data.FailureType ServiceUnavailable;
    public static final enum com.bisimplex.firebooru.data.FailureType TooManyRequest;
    public static final enum com.bisimplex.firebooru.data.FailureType Unauthorized;
    public static final enum com.bisimplex.firebooru.data.FailureType UserThrottled;
    private final int value;

    private static synthetic com.bisimplex.firebooru.data.FailureType[] $values()
    {
        com.bisimplex.firebooru.data.FailureType v3 = com.bisimplex.firebooru.data.FailureType.InvalidAppKey;
        com.bisimplex.firebooru.data.FailureType v5 = com.bisimplex.firebooru.data.FailureType.Connection;
        com.bisimplex.firebooru.data.FailureType v7 = com.bisimplex.firebooru.data.FailureType.Forbidden;
        com.bisimplex.firebooru.data.FailureType v9 = com.bisimplex.firebooru.data.FailureType.InvalidRecord;
        com.bisimplex.firebooru.data.FailureType v11 = com.bisimplex.firebooru.data.FailureType.Locked;
        com.bisimplex.firebooru.data.FailureType v13 = com.bisimplex.firebooru.data.FailureType.InvalidParameters;
        com.bisimplex.firebooru.data.FailureType v15 = com.bisimplex.firebooru.data.FailureType.ServerError;
        return new com.bisimplex.firebooru.data.FailureType[] {com.bisimplex.firebooru.data.FailureType.InvalidCert, com.bisimplex.firebooru.data.FailureType.ServiceUnavailable});
    }

    static FailureType()
    {
        com.bisimplex.firebooru.data.FailureType.InvalidCert = new com.bisimplex.firebooru.data.FailureType("InvalidCert", 0, -501);
        com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed = new com.bisimplex.firebooru.data.FailureType("ContentCannotBeParsed", 1, -500);
        com.bisimplex.firebooru.data.FailureType.InvalidAppKey = new com.bisimplex.firebooru.data.FailureType("InvalidAppKey", 2, -499);
        com.bisimplex.firebooru.data.FailureType.AccessDenied = new com.bisimplex.firebooru.data.FailureType("AccessDenied", 3, -403);
        com.bisimplex.firebooru.data.FailureType.Connection = new com.bisimplex.firebooru.data.FailureType("Connection", 4, 0);
        com.bisimplex.firebooru.data.FailureType.Unauthorized = new com.bisimplex.firebooru.data.FailureType("Unauthorized", 5, 401);
        com.bisimplex.firebooru.data.FailureType.Forbidden = new com.bisimplex.firebooru.data.FailureType("Forbidden", 6, 403);
        com.bisimplex.firebooru.data.FailureType.NotFound = new com.bisimplex.firebooru.data.FailureType("NotFound", 7, 404);
        com.bisimplex.firebooru.data.FailureType.InvalidRecord = new com.bisimplex.firebooru.data.FailureType("InvalidRecord", 8, 420);
        com.bisimplex.firebooru.data.FailureType.UserThrottled = new com.bisimplex.firebooru.data.FailureType("UserThrottled", 9, 421);
        com.bisimplex.firebooru.data.FailureType.Locked = new com.bisimplex.firebooru.data.FailureType("Locked", 10, 422);
        com.bisimplex.firebooru.data.FailureType.AlreadyExist = new com.bisimplex.firebooru.data.FailureType("AlreadyExist", 11, 423);
        com.bisimplex.firebooru.data.FailureType.InvalidParameters = new com.bisimplex.firebooru.data.FailureType("InvalidParameters", 12, 424);
        com.bisimplex.firebooru.data.FailureType.TooManyRequest = new com.bisimplex.firebooru.data.FailureType("TooManyRequest", 13, 429);
        com.bisimplex.firebooru.data.FailureType.ServerError = new com.bisimplex.firebooru.data.FailureType("ServerError", 14, 500);
        com.bisimplex.firebooru.data.FailureType.ServiceUnavailable = new com.bisimplex.firebooru.data.FailureType("ServiceUnavailable", 15, 503);
        com.bisimplex.firebooru.data.FailureType.$VALUES = com.bisimplex.firebooru.data.FailureType.$values();
        return;
    }

    private FailureType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.data.FailureType fromInteger(int p1)
    {
        if (p1 == -403) {
            return com.bisimplex.firebooru.data.FailureType.AccessDenied;
        } else {
            if (p1 == null) {
                return com.bisimplex.firebooru.data.FailureType.Connection;
            } else {
                if (p1 == 401) {
                    return com.bisimplex.firebooru.data.FailureType.Unauthorized;
                } else {
                    if (p1 == 429) {
                        return com.bisimplex.firebooru.data.FailureType.TooManyRequest;
                    } else {
                        if (p1 == 500) {
                            return com.bisimplex.firebooru.data.FailureType.ServerError;
                        } else {
                            if (p1 == 503) {
                                return com.bisimplex.firebooru.data.FailureType.ServiceUnavailable;
                            } else {
                                if (p1 == 403) {
                                    return com.bisimplex.firebooru.data.FailureType.Forbidden;
                                } else {
                                    if (p1 == 404) {
                                        return com.bisimplex.firebooru.data.FailureType.NotFound;
                                    } else {
                                        switch (p1) {
                                            case -501:
                                                return com.bisimplex.firebooru.data.FailureType.InvalidCert;
                                            case -500:
                                                return com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed;
                                            case -499:
                                                return com.bisimplex.firebooru.data.FailureType.InvalidAppKey;
                                            default:
                                                switch (p1) {
                                                    case 420:
                                                        return com.bisimplex.firebooru.data.FailureType.InvalidRecord;
                                                    case 421:
                                                        return com.bisimplex.firebooru.data.FailureType.UserThrottled;
                                                    case 422:
                                                        return com.bisimplex.firebooru.data.FailureType.Locked;
                                                    case 423:
                                                        return com.bisimplex.firebooru.data.FailureType.AlreadyExist;
                                                    case 424:
                                                        return com.bisimplex.firebooru.data.FailureType.InvalidParameters;
                                                    default:
                                                        return com.bisimplex.firebooru.data.FailureType.Connection;
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.data.FailureType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.data.FailureType) Enum.valueOf(com.bisimplex.firebooru.data.FailureType, p1));
    }

    public static com.bisimplex.firebooru.data.FailureType[] values()
    {
        return ((com.bisimplex.firebooru.data.FailureType[]) com.bisimplex.firebooru.data.FailureType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
