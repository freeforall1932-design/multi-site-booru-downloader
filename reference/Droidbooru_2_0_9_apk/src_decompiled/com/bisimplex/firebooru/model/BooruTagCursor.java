package com.bisimplex.firebooru.model;
public final class BooruTagCursor extends io.objectbox.Cursor {
    private static final com.bisimplex.firebooru.model.BooruTag_$BooruTagIdGetter ID_GETTER;
    private static final int __ID_hits;
    private static final int __ID_name;
    private static final int __ID_type;

    static BooruTagCursor()
    {
        com.bisimplex.firebooru.model.BooruTagCursor.ID_GETTER = com.bisimplex.firebooru.model.BooruTag_.__ID_GETTER;
        com.bisimplex.firebooru.model.BooruTagCursor.__ID_type = com.bisimplex.firebooru.model.BooruTag_.type.id;
        com.bisimplex.firebooru.model.BooruTagCursor.__ID_hits = com.bisimplex.firebooru.model.BooruTag_.hits.id;
        com.bisimplex.firebooru.model.BooruTagCursor.__ID_name = com.bisimplex.firebooru.model.BooruTag_.name.id;
        return;
    }

    public BooruTagCursor(io.objectbox.Transaction p7, long p8, io.objectbox.BoxStore p10)
    {
        super(p7, p8, com.bisimplex.firebooru.model.BooruTag_.__INSTANCE, p10);
        return;
    }

    public long getId(com.bisimplex.firebooru.model.BooruTag p3)
    {
        return com.bisimplex.firebooru.model.BooruTagCursor.ID_GETTER.getId(p3);
    }

    public bridge synthetic long getId(Object p3)
    {
        return this.getId(((com.bisimplex.firebooru.model.BooruTag) p3));
    }

    public long put(com.bisimplex.firebooru.model.BooruTag p36)
    {
        com.bisimplex.firebooru.model.BooruTagCursor v0_0;
        String v8 = p36.getName();
        if (v8 == null) {
            v0_0 = 0;
        } else {
            v0_0 = com.bisimplex.firebooru.model.BooruTagCursor.__ID_name;
        }
        long v1_1 = com.bisimplex.firebooru.model.BooruTagCursor.collect313311(this.cursor, p36.getId(), 3, v0_0, v8, 0, 0, 0, 0, 0, 0, com.bisimplex.firebooru.model.BooruTagCursor.__ID_hits, p36.getHits(), com.bisimplex.firebooru.model.BooruTagCursor.__ID_type, ((long) p36.getType()), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        p36.setId(v1_1);
        return v1_1;
    }

    public bridge synthetic long put(Object p3)
    {
        return this.put(((com.bisimplex.firebooru.model.BooruTag) p3));
    }
}
