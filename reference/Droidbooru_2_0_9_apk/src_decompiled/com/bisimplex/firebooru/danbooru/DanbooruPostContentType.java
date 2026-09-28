package com.bisimplex.firebooru.danbooru;
public final enum class DanbooruPostContentType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.DanbooruPostContentType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.DanbooruPostContentType Gif;
    public static final enum com.bisimplex.firebooru.danbooru.DanbooruPostContentType MP4;
    public static final enum com.bisimplex.firebooru.danbooru.DanbooruPostContentType Static;
    public static final enum com.bisimplex.firebooru.danbooru.DanbooruPostContentType WebM;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.DanbooruPostContentType[] $values()
    {
        return new com.bisimplex.firebooru.danbooru.DanbooruPostContentType[] {com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static, com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4, com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM, com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Gif});
    }

    static DanbooruPostContentType()
    {
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static = new com.bisimplex.firebooru.danbooru.DanbooruPostContentType("Static", 0, 0);
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4 = new com.bisimplex.firebooru.danbooru.DanbooruPostContentType("MP4", 1, 3);
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM = new com.bisimplex.firebooru.danbooru.DanbooruPostContentType("WebM", 2, 2);
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Gif = new com.bisimplex.firebooru.danbooru.DanbooruPostContentType("Gif", 3, 1);
        com.bisimplex.firebooru.danbooru.DanbooruPostContentType.$VALUES = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.$values();
        return;
    }

    private DanbooruPostContentType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruPostContentType fromExtension(String p2)
    {
        if (p2 != null) {
            p2.hashCode();
            int v1 = -1;
            switch (p2.hashCode()) {
                case 102340:
                    if (p2.equals("gif")) {
                        v1 = 0;
                    } else {
                    }
                    break;
                case 108273:
                    if (p2.equals("mp4")) {
                        v1 = 1;
                    } else {
                    }
                    break;
                case 3645337:
                    if (p2.equals("webm")) {
                        v1 = 2;
                    } else {
                    }
                    break;
                default:
            }
            switch (v1) {
                case 0:
                    return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Gif;
                case 1:
                    return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4;
                case 2:
                    return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM;
                default:
                    return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static;
            }
        } else {
            return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static;
        }
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruPostContentType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Gif;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4;
                    } else {
                        return com.bisimplex.firebooru.danbooru.DanbooruPostContentType.Static;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruPostContentType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.DanbooruPostContentType) Enum.valueOf(com.bisimplex.firebooru.danbooru.DanbooruPostContentType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruPostContentType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.DanbooruPostContentType[]) com.bisimplex.firebooru.danbooru.DanbooruPostContentType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
