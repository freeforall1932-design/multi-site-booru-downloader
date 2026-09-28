package com.bisimplex.firebooru.model;
public final enum class DNSType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.DNSType[] $VALUES;
    public static final enum com.bisimplex.firebooru.model.DNSType CloudFlare;
    public static final enum com.bisimplex.firebooru.model.DNSType Google;
    public static final enum com.bisimplex.firebooru.model.DNSType System;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.DNSType[] $values()
    {
        return new com.bisimplex.firebooru.model.DNSType[] {com.bisimplex.firebooru.model.DNSType.System, com.bisimplex.firebooru.model.DNSType.Google, com.bisimplex.firebooru.model.DNSType.CloudFlare});
    }

    static DNSType()
    {
        com.bisimplex.firebooru.model.DNSType.System = new com.bisimplex.firebooru.model.DNSType("System", 0, 0);
        com.bisimplex.firebooru.model.DNSType.Google = new com.bisimplex.firebooru.model.DNSType("Google", 1, 1);
        com.bisimplex.firebooru.model.DNSType.CloudFlare = new com.bisimplex.firebooru.model.DNSType("CloudFlare", 2, 2);
        com.bisimplex.firebooru.model.DNSType.$VALUES = com.bisimplex.firebooru.model.DNSType.$values();
        return;
    }

    private DNSType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.model.DNSType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.model.DNSType.Google;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.model.DNSType.CloudFlare;
            } else {
                return com.bisimplex.firebooru.model.DNSType.System;
            }
        }
    }

    public static com.bisimplex.firebooru.model.DNSType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.DNSType) Enum.valueOf(com.bisimplex.firebooru.model.DNSType, p1));
    }

    public static com.bisimplex.firebooru.model.DNSType[] values()
    {
        return ((com.bisimplex.firebooru.model.DNSType[]) com.bisimplex.firebooru.model.DNSType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
