package com.bisimplex.firebooru.danbooru;
public final enum class FailureType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.FailureType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType AlreadyExist;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType Connection;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType ContentCannotBeParsed;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType Forbidden;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType InvalidCert;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType InvalidGelbooruCredentials;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType InvalidParameters;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType InvalidRecord;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType Locked;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType NotFound;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType ServerError;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType ServiceUnavailable;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType TooManyRequest;
    public static final enum com.bisimplex.firebooru.danbooru.FailureType UserThrottled;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.FailureType[] $values()
    {
        com.bisimplex.firebooru.danbooru.FailureType v2 = com.bisimplex.firebooru.danbooru.FailureType.Connection;
        com.bisimplex.firebooru.danbooru.FailureType v4 = com.bisimplex.firebooru.danbooru.FailureType.NotFound;
        com.bisimplex.firebooru.danbooru.FailureType v6 = com.bisimplex.firebooru.danbooru.FailureType.UserThrottled;
        com.bisimplex.firebooru.danbooru.FailureType v8 = com.bisimplex.firebooru.danbooru.FailureType.AlreadyExist;
        com.bisimplex.firebooru.danbooru.FailureType v10 = com.bisimplex.firebooru.danbooru.FailureType.TooManyRequest;
        com.bisimplex.firebooru.danbooru.FailureType v12 = com.bisimplex.firebooru.danbooru.FailureType.ServiceUnavailable;
        return new com.bisimplex.firebooru.danbooru.FailureType[] {com.bisimplex.firebooru.danbooru.FailureType.InvalidCert, com.bisimplex.firebooru.danbooru.FailureType.InvalidGelbooruCredentials});
    }

    static FailureType()
    {
        com.bisimplex.firebooru.danbooru.FailureType.InvalidCert = new com.bisimplex.firebooru.danbooru.FailureType("InvalidCert", 0, -501);
        com.bisimplex.firebooru.danbooru.FailureType.ContentCannotBeParsed = new com.bisimplex.firebooru.danbooru.FailureType("ContentCannotBeParsed", 1, -500);
        com.bisimplex.firebooru.danbooru.FailureType.Connection = new com.bisimplex.firebooru.danbooru.FailureType("Connection", 2, 0);
        com.bisimplex.firebooru.danbooru.FailureType.Forbidden = new com.bisimplex.firebooru.danbooru.FailureType("Forbidden", 3, 403);
        com.bisimplex.firebooru.danbooru.FailureType.NotFound = new com.bisimplex.firebooru.danbooru.FailureType("NotFound", 4, 404);
        com.bisimplex.firebooru.danbooru.FailureType.InvalidRecord = new com.bisimplex.firebooru.danbooru.FailureType("InvalidRecord", 5, 420);
        com.bisimplex.firebooru.danbooru.FailureType.UserThrottled = new com.bisimplex.firebooru.danbooru.FailureType("UserThrottled", 6, 421);
        com.bisimplex.firebooru.danbooru.FailureType.Locked = new com.bisimplex.firebooru.danbooru.FailureType("Locked", 7, 422);
        com.bisimplex.firebooru.danbooru.FailureType.AlreadyExist = new com.bisimplex.firebooru.danbooru.FailureType("AlreadyExist", 8, 423);
        com.bisimplex.firebooru.danbooru.FailureType.InvalidParameters = new com.bisimplex.firebooru.danbooru.FailureType("InvalidParameters", 9, 424);
        com.bisimplex.firebooru.danbooru.FailureType.TooManyRequest = new com.bisimplex.firebooru.danbooru.FailureType("TooManyRequest", 10, 429);
        com.bisimplex.firebooru.danbooru.FailureType.ServerError = new com.bisimplex.firebooru.danbooru.FailureType("ServerError", 11, 500);
        com.bisimplex.firebooru.danbooru.FailureType.ServiceUnavailable = new com.bisimplex.firebooru.danbooru.FailureType("ServiceUnavailable", 12, 503);
        com.bisimplex.firebooru.danbooru.FailureType.InvalidGelbooruCredentials = new com.bisimplex.firebooru.danbooru.FailureType("InvalidGelbooruCredentials", 13, -502);
        com.bisimplex.firebooru.danbooru.FailureType.$VALUES = com.bisimplex.firebooru.danbooru.FailureType.$values();
        return;
    }

    private FailureType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.FailureType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.danbooru.FailureType.Connection;
        } else {
            if (p1 == 429) {
                return com.bisimplex.firebooru.danbooru.FailureType.TooManyRequest;
            } else {
                if (p1 == 500) {
                    return com.bisimplex.firebooru.danbooru.FailureType.ServerError;
                } else {
                    if (p1 == 503) {
                        return com.bisimplex.firebooru.danbooru.FailureType.ServiceUnavailable;
                    } else {
                        if (p1 == 403) {
                            return com.bisimplex.firebooru.danbooru.FailureType.Forbidden;
                        } else {
                            if (p1 == 404) {
                                return com.bisimplex.firebooru.danbooru.FailureType.NotFound;
                            } else {
                                switch (p1) {
                                    case -502:
                                        return com.bisimplex.firebooru.danbooru.FailureType.InvalidGelbooruCredentials;
                                    case -501:
                                        return com.bisimplex.firebooru.danbooru.FailureType.InvalidCert;
                                    case -500:
                                        return com.bisimplex.firebooru.danbooru.FailureType.ContentCannotBeParsed;
                                    default:
                                        switch (p1) {
                                            case 420:
                                                return com.bisimplex.firebooru.danbooru.FailureType.InvalidRecord;
                                            case 421:
                                                return com.bisimplex.firebooru.danbooru.FailureType.UserThrottled;
                                            case 422:
                                                return com.bisimplex.firebooru.danbooru.FailureType.Locked;
                                            case 423:
                                                return com.bisimplex.firebooru.danbooru.FailureType.AlreadyExist;
                                            case 424:
                                                return com.bisimplex.firebooru.danbooru.FailureType.InvalidParameters;
                                            default:
                                                return com.bisimplex.firebooru.danbooru.FailureType.Connection;
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.danbooru.FailureType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.FailureType) Enum.valueOf(com.bisimplex.firebooru.danbooru.FailureType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.FailureType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.FailureType[]) com.bisimplex.firebooru.danbooru.FailureType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
