package com.bisimplex.firebooru.model;
public final enum class FileNamePartType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.model.FileNamePartType[] $VALUES = None;
    public static final enum com.bisimplex.firebooru.model.FileNamePartType Domain = None;
    public static final enum com.bisimplex.firebooru.model.FileNamePartType ID = None;
    public static final enum com.bisimplex.firebooru.model.FileNamePartType MD5 = None;
    private static final String Separator = ",";
    public static final enum com.bisimplex.firebooru.model.FileNamePartType Tags;
    private final int value;

    private static synthetic com.bisimplex.firebooru.model.FileNamePartType[] $values()
    {
        return new com.bisimplex.firebooru.model.FileNamePartType[] {com.bisimplex.firebooru.model.FileNamePartType.MD5, com.bisimplex.firebooru.model.FileNamePartType.Domain, com.bisimplex.firebooru.model.FileNamePartType.Tags, com.bisimplex.firebooru.model.FileNamePartType.ID});
    }

    static FileNamePartType()
    {
        com.bisimplex.firebooru.model.FileNamePartType.MD5 = new com.bisimplex.firebooru.model.FileNamePartType("MD5", 0, 0);
        com.bisimplex.firebooru.model.FileNamePartType.Domain = new com.bisimplex.firebooru.model.FileNamePartType("Domain", 1, 1);
        com.bisimplex.firebooru.model.FileNamePartType.Tags = new com.bisimplex.firebooru.model.FileNamePartType("Tags", 2, 2);
        com.bisimplex.firebooru.model.FileNamePartType.ID = new com.bisimplex.firebooru.model.FileNamePartType("ID", 3, 3);
        com.bisimplex.firebooru.model.FileNamePartType.$VALUES = com.bisimplex.firebooru.model.FileNamePartType.$values();
        return;
    }

    private FileNamePartType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static java.util.List all()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(4);
        v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.MD5);
        v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.Domain);
        v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.ID);
        v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.Tags);
        return v0_1;
    }

    public static java.util.List fromCSV(String p5)
    {
        int v1 = 0;
        java.util.ArrayList v0_1 = new java.util.ArrayList(0);
        if (!android.text.TextUtils.isEmpty(p5)) {
            String[] v5_1 = p5.split(",");
            int v2_2 = v5_1.length;
            while (v1 < v2_2) {
                com.bisimplex.firebooru.model.FileNamePartType v3_0 = v5_1[v1];
                if ((!android.text.TextUtils.isEmpty(v3_0)) && (android.text.TextUtils.isDigitsOnly(v3_0))) {
                    v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.fromInteger(Integer.parseInt(v3_0)));
                }
                v1++;
            }
        }
        return v0_1;
    }

    public static java.util.List fromFileNameType(com.bisimplex.firebooru.danbooru.FileNameType p2)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList(0);
        com.bisimplex.firebooru.model.FileNamePartType v2_4 = com.bisimplex.firebooru.model.FileNamePartType$1.$SwitchMap$com$bisimplex$firebooru$danbooru$FileNameType[p2.ordinal()];
        if (v2_4 == 1) {
            v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.MD5);
            return v0_1;
        } else {
            if (v2_4 == 2) {
                v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.Domain);
                v0_1.add(com.bisimplex.firebooru.model.FileNamePartType.Tags);
                return v0_1;
            } else {
                return v0_1;
            }
        }
    }

    public static com.bisimplex.firebooru.model.FileNamePartType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.model.FileNamePartType.Domain;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.model.FileNamePartType.Tags;
            } else {
                if (p1 == 3) {
                    return com.bisimplex.firebooru.model.FileNamePartType.ID;
                } else {
                    return com.bisimplex.firebooru.model.FileNamePartType.MD5;
                }
            }
        }
    }

    public static String toCSV(java.util.List p2)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            StringBuilder v0_2 = new StringBuilder();
            String v2_5 = p2.iterator();
            while (v2_5.hasNext()) {
                v0_2.append(((com.bisimplex.firebooru.model.FileNamePartType) v2_5.next()).getValue());
                v0_2.append(",");
            }
            return v0_2.substring(0, (v0_2.length() - 1));
        } else {
            return "";
        }
    }

    public static com.bisimplex.firebooru.model.FileNamePartType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.model.FileNamePartType) Enum.valueOf(com.bisimplex.firebooru.model.FileNamePartType, p1));
    }

    public static com.bisimplex.firebooru.model.FileNamePartType[] values()
    {
        return ((com.bisimplex.firebooru.model.FileNamePartType[]) com.bisimplex.firebooru.model.FileNamePartType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
