package com.bisimplex.firebooru.data;
public final enum class SourceType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.data.SourceType[] $VALUES;
    public static final enum com.bisimplex.firebooru.data.SourceType ChildPosts;
    public static final enum com.bisimplex.firebooru.data.SourceType NormalPosts;
    public static final enum com.bisimplex.firebooru.data.SourceType ParentPost;
    public static final enum com.bisimplex.firebooru.data.SourceType RecommendedPosts;
    public static final enum com.bisimplex.firebooru.data.SourceType RecommendedPostsForUser;
    public static final enum com.bisimplex.firebooru.data.SourceType RowNormalPosts;
    private final int value;

    private static synthetic com.bisimplex.firebooru.data.SourceType[] $values()
    {
        com.bisimplex.firebooru.data.SourceType v2 = com.bisimplex.firebooru.data.SourceType.RecommendedPostsForUser;
        com.bisimplex.firebooru.data.SourceType v4 = com.bisimplex.firebooru.data.SourceType.ChildPosts;
        return new com.bisimplex.firebooru.data.SourceType[] {com.bisimplex.firebooru.data.SourceType.NormalPosts, com.bisimplex.firebooru.data.SourceType.RowNormalPosts});
    }

    static SourceType()
    {
        com.bisimplex.firebooru.data.SourceType.NormalPosts = new com.bisimplex.firebooru.data.SourceType("NormalPosts", 0, 0);
        com.bisimplex.firebooru.data.SourceType.RecommendedPosts = new com.bisimplex.firebooru.data.SourceType("RecommendedPosts", 1, 1);
        com.bisimplex.firebooru.data.SourceType.RecommendedPostsForUser = new com.bisimplex.firebooru.data.SourceType("RecommendedPostsForUser", 2, 2);
        com.bisimplex.firebooru.data.SourceType.ParentPost = new com.bisimplex.firebooru.data.SourceType("ParentPost", 3, 3);
        com.bisimplex.firebooru.data.SourceType.ChildPosts = new com.bisimplex.firebooru.data.SourceType("ChildPosts", 4, 4);
        com.bisimplex.firebooru.data.SourceType.RowNormalPosts = new com.bisimplex.firebooru.data.SourceType("RowNormalPosts", 5, 5);
        com.bisimplex.firebooru.data.SourceType.$VALUES = com.bisimplex.firebooru.data.SourceType.$values();
        return;
    }

    private SourceType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.data.SourceType fromInteger(int p1)
    {
        if (p1 == null) {
            return com.bisimplex.firebooru.data.SourceType.NormalPosts;
        } else {
            if (p1 == 1) {
                return com.bisimplex.firebooru.data.SourceType.RecommendedPosts;
            } else {
                if (p1 == 2) {
                    return com.bisimplex.firebooru.data.SourceType.RecommendedPostsForUser;
                } else {
                    if (p1 == 3) {
                        return com.bisimplex.firebooru.data.SourceType.ParentPost;
                    } else {
                        if (p1 == 4) {
                            return com.bisimplex.firebooru.data.SourceType.ChildPosts;
                        } else {
                            if (p1 == 5) {
                                return com.bisimplex.firebooru.data.SourceType.RowNormalPosts;
                            } else {
                                return com.bisimplex.firebooru.data.SourceType.NormalPosts;
                            }
                        }
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.data.SourceType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.data.SourceType) Enum.valueOf(com.bisimplex.firebooru.data.SourceType, p1));
    }

    public static com.bisimplex.firebooru.data.SourceType[] values()
    {
        return ((com.bisimplex.firebooru.data.SourceType[]) com.bisimplex.firebooru.data.SourceType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
