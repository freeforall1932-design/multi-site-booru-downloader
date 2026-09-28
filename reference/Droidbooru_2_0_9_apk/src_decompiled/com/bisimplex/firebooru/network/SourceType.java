package com.bisimplex.firebooru.network;
public final enum class SourceType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.network.SourceType[] $VALUES;
    public static final enum com.bisimplex.firebooru.network.SourceType Blacklist;
    public static final enum com.bisimplex.firebooru.network.SourceType BooruTag;
    public static final enum com.bisimplex.firebooru.network.SourceType Comment;
    public static final enum com.bisimplex.firebooru.network.SourceType Dmail;
    public static final enum com.bisimplex.firebooru.network.SourceType Favorites;
    public static final enum com.bisimplex.firebooru.network.SourceType FullTag;
    public static final enum com.bisimplex.firebooru.network.SourceType History;
    public static final enum com.bisimplex.firebooru.network.SourceType MultiPost;
    public static final enum com.bisimplex.firebooru.network.SourceType Notes;
    public static final enum com.bisimplex.firebooru.network.SourceType Permanent;
    public static final enum com.bisimplex.firebooru.network.SourceType Pool;
    public static final enum com.bisimplex.firebooru.network.SourceType Post;
    public static final enum com.bisimplex.firebooru.network.SourceType Ranking;
    public static final enum com.bisimplex.firebooru.network.SourceType Tag;
    public static final enum com.bisimplex.firebooru.network.SourceType User;
    private final int value;

    private static synthetic com.bisimplex.firebooru.network.SourceType[] $values()
    {
        com.bisimplex.firebooru.network.SourceType v2 = com.bisimplex.firebooru.network.SourceType.Comment;
        com.bisimplex.firebooru.network.SourceType v4 = com.bisimplex.firebooru.network.SourceType.Tag;
        com.bisimplex.firebooru.network.SourceType v6 = com.bisimplex.firebooru.network.SourceType.Ranking;
        com.bisimplex.firebooru.network.SourceType v8 = com.bisimplex.firebooru.network.SourceType.User;
        com.bisimplex.firebooru.network.SourceType v10 = com.bisimplex.firebooru.network.SourceType.History;
        com.bisimplex.firebooru.network.SourceType v12 = com.bisimplex.firebooru.network.SourceType.Blacklist;
        return new com.bisimplex.firebooru.network.SourceType[] {com.bisimplex.firebooru.network.SourceType.Post, com.bisimplex.firebooru.network.SourceType.MultiPost});
    }

    static SourceType()
    {
        com.bisimplex.firebooru.network.SourceType.Post = new com.bisimplex.firebooru.network.SourceType("Post", 0, 0);
        com.bisimplex.firebooru.network.SourceType.Notes = new com.bisimplex.firebooru.network.SourceType("Notes", 1, 1);
        com.bisimplex.firebooru.network.SourceType.Comment = new com.bisimplex.firebooru.network.SourceType("Comment", 2, 2);
        com.bisimplex.firebooru.network.SourceType.Pool = new com.bisimplex.firebooru.network.SourceType("Pool", 3, 3);
        com.bisimplex.firebooru.network.SourceType.Tag = new com.bisimplex.firebooru.network.SourceType("Tag", 4, 4);
        com.bisimplex.firebooru.network.SourceType.FullTag = new com.bisimplex.firebooru.network.SourceType("FullTag", 5, 5);
        com.bisimplex.firebooru.network.SourceType.Ranking = new com.bisimplex.firebooru.network.SourceType("Ranking", 6, 6);
        com.bisimplex.firebooru.network.SourceType.Dmail = new com.bisimplex.firebooru.network.SourceType("Dmail", 7, 7);
        com.bisimplex.firebooru.network.SourceType.User = new com.bisimplex.firebooru.network.SourceType("User", 8, 8);
        com.bisimplex.firebooru.network.SourceType.Favorites = new com.bisimplex.firebooru.network.SourceType("Favorites", 9, 9);
        com.bisimplex.firebooru.network.SourceType.History = new com.bisimplex.firebooru.network.SourceType("History", 10, 10);
        com.bisimplex.firebooru.network.SourceType.Permanent = new com.bisimplex.firebooru.network.SourceType("Permanent", 11, 11);
        com.bisimplex.firebooru.network.SourceType.Blacklist = new com.bisimplex.firebooru.network.SourceType("Blacklist", 12, 12);
        com.bisimplex.firebooru.network.SourceType.BooruTag = new com.bisimplex.firebooru.network.SourceType("BooruTag", 13, 13);
        com.bisimplex.firebooru.network.SourceType.MultiPost = new com.bisimplex.firebooru.network.SourceType("MultiPost", 14, 14);
        com.bisimplex.firebooru.network.SourceType.$VALUES = com.bisimplex.firebooru.network.SourceType.$values();
        return;
    }

    private SourceType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.network.SourceType fromInteger(int p0)
    {
        switch (p0) {
            case 0:
                return com.bisimplex.firebooru.network.SourceType.Post;
            case 1:
                return com.bisimplex.firebooru.network.SourceType.Notes;
            case 2:
                return com.bisimplex.firebooru.network.SourceType.Comment;
            case 3:
                return com.bisimplex.firebooru.network.SourceType.Pool;
            case 4:
                return com.bisimplex.firebooru.network.SourceType.Tag;
            case 5:
                return com.bisimplex.firebooru.network.SourceType.FullTag;
            case 6:
                return com.bisimplex.firebooru.network.SourceType.Ranking;
            case 7:
                return com.bisimplex.firebooru.network.SourceType.Dmail;
            case 8:
                return com.bisimplex.firebooru.network.SourceType.User;
            case 9:
                return com.bisimplex.firebooru.network.SourceType.Favorites;
            case 10:
                return com.bisimplex.firebooru.network.SourceType.History;
            case 11:
                return com.bisimplex.firebooru.network.SourceType.Permanent;
            case 12:
                return com.bisimplex.firebooru.network.SourceType.Blacklist;
            case 13:
                return com.bisimplex.firebooru.network.SourceType.BooruTag;
            case 14:
                return com.bisimplex.firebooru.network.SourceType.MultiPost;
            default:
                return com.bisimplex.firebooru.network.SourceType.Post;
        }
    }

    public static com.bisimplex.firebooru.network.SourceType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.network.SourceType) Enum.valueOf(com.bisimplex.firebooru.network.SourceType, p1));
    }

    public static com.bisimplex.firebooru.network.SourceType[] values()
    {
        return ((com.bisimplex.firebooru.network.SourceType[]) com.bisimplex.firebooru.network.SourceType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
