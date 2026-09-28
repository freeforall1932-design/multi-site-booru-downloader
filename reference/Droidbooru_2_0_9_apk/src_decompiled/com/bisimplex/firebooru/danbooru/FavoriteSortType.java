package com.bisimplex.firebooru.danbooru;
public final enum class FavoriteSortType extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.danbooru.FavoriteSortType[] $VALUES;
    public static final enum com.bisimplex.firebooru.danbooru.FavoriteSortType Date;
    public static final enum com.bisimplex.firebooru.danbooru.FavoriteSortType DateAsc;
    public static final enum com.bisimplex.firebooru.danbooru.FavoriteSortType Random;
    public static final enum com.bisimplex.firebooru.danbooru.FavoriteSortType Score;
    public static final enum com.bisimplex.firebooru.danbooru.FavoriteSortType ScoreAsc;
    private final int value;

    private static synthetic com.bisimplex.firebooru.danbooru.FavoriteSortType[] $values()
    {
        return new com.bisimplex.firebooru.danbooru.FavoriteSortType[] {com.bisimplex.firebooru.danbooru.FavoriteSortType.Date, com.bisimplex.firebooru.danbooru.FavoriteSortType.DateAsc, com.bisimplex.firebooru.danbooru.FavoriteSortType.Score, com.bisimplex.firebooru.danbooru.FavoriteSortType.ScoreAsc, com.bisimplex.firebooru.danbooru.FavoriteSortType.Random});
    }

    static FavoriteSortType()
    {
        com.bisimplex.firebooru.danbooru.FavoriteSortType.Date = new com.bisimplex.firebooru.danbooru.FavoriteSortType("Date", 0, 0);
        com.bisimplex.firebooru.danbooru.FavoriteSortType.DateAsc = new com.bisimplex.firebooru.danbooru.FavoriteSortType("DateAsc", 1, 1);
        com.bisimplex.firebooru.danbooru.FavoriteSortType.Score = new com.bisimplex.firebooru.danbooru.FavoriteSortType("Score", 2, 2);
        com.bisimplex.firebooru.danbooru.FavoriteSortType.ScoreAsc = new com.bisimplex.firebooru.danbooru.FavoriteSortType("ScoreAsc", 3, 3);
        com.bisimplex.firebooru.danbooru.FavoriteSortType.Random = new com.bisimplex.firebooru.danbooru.FavoriteSortType("Random", 4, 4);
        com.bisimplex.firebooru.danbooru.FavoriteSortType.$VALUES = com.bisimplex.firebooru.danbooru.FavoriteSortType.$values();
        return;
    }

    private FavoriteSortType(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.value = p3;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.FavoriteSortType fromInteger(int p1)
    {
        if (p1 == 1) {
            return com.bisimplex.firebooru.danbooru.FavoriteSortType.DateAsc;
        } else {
            if (p1 == 2) {
                return com.bisimplex.firebooru.danbooru.FavoriteSortType.Score;
            } else {
                if (p1 == 3) {
                    return com.bisimplex.firebooru.danbooru.FavoriteSortType.ScoreAsc;
                } else {
                    if (p1 == 4) {
                        return com.bisimplex.firebooru.danbooru.FavoriteSortType.Random;
                    } else {
                        return com.bisimplex.firebooru.danbooru.FavoriteSortType.Date;
                    }
                }
            }
        }
    }

    public static com.bisimplex.firebooru.danbooru.FavoriteSortType valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.danbooru.FavoriteSortType) Enum.valueOf(com.bisimplex.firebooru.danbooru.FavoriteSortType, p1));
    }

    public static com.bisimplex.firebooru.danbooru.FavoriteSortType[] values()
    {
        return ((com.bisimplex.firebooru.danbooru.FavoriteSortType[]) com.bisimplex.firebooru.danbooru.FavoriteSortType.$VALUES.clone());
    }

    public int getValue()
    {
        return this.value;
    }
}
