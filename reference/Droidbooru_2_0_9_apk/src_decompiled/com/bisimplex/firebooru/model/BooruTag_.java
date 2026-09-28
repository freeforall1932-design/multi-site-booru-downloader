package com.bisimplex.firebooru.model;
public final class BooruTag_ implements io.objectbox.EntityInfo {
    public static final io.objectbox.Property[] __ALL_PROPERTIES = None;
    public static final io.objectbox.internal.CursorFactory __CURSOR_FACTORY = None;
    public static final String __DB_NAME = "BooruTag";
    public static final Class __ENTITY_CLASS = None;
    public static final int __ENTITY_ID = 4;
    public static final String __ENTITY_NAME = "BooruTag";
    static final com.bisimplex.firebooru.model.BooruTag_$BooruTagIdGetter __ID_GETTER;
    public static final io.objectbox.Property __ID_PROPERTY;
    public static final com.bisimplex.firebooru.model.BooruTag_ __INSTANCE;
    public static final io.objectbox.Property hits;
    public static final io.objectbox.Property id;
    public static final io.objectbox.Property name;
    public static final io.objectbox.Property type;

    static BooruTag_()
    {
        com.bisimplex.firebooru.model.BooruTag_.__ENTITY_CLASS = com.bisimplex.firebooru.model.BooruTag;
        com.bisimplex.firebooru.model.BooruTag_.__CURSOR_FACTORY = new com.bisimplex.firebooru.model.BooruTagCursor$Factory();
        com.bisimplex.firebooru.model.BooruTag_.__ID_GETTER = new com.bisimplex.firebooru.model.BooruTag_$BooruTagIdGetter();
        io.objectbox.Property[] v2_3 = new com.bisimplex.firebooru.model.BooruTag_();
        com.bisimplex.firebooru.model.BooruTag_.__INSTANCE = v2_3;
        io.objectbox.Property v0_1 = new io.objectbox.Property(v2_3, 0, 1, Long.TYPE, "id", 1, "id");
        com.bisimplex.firebooru.model.BooruTag_.id = v0_1;
        io.objectbox.Property v7_1 = new io.objectbox.Property(v2_3, 1, 2, Integer.TYPE, "type");
        com.bisimplex.firebooru.model.BooruTag_.type = v7_1;
        io.objectbox.Property v8_1 = new io.objectbox.Property(v2_3, 2, 3, Long.TYPE, "hits");
        com.bisimplex.firebooru.model.BooruTag_.hits = v8_1;
        io.objectbox.Property v1_7 = new io.objectbox.Property(v2_3, 3, 4, String, "name");
        com.bisimplex.firebooru.model.BooruTag_.name = v1_7;
        io.objectbox.Property[] v2_1 = new io.objectbox.Property[4];
        v2_1[0] = v0_1;
        v2_1[1] = v7_1;
        v2_1[2] = v8_1;
        v2_1[3] = v1_7;
        com.bisimplex.firebooru.model.BooruTag_.__ALL_PROPERTIES = v2_1;
        com.bisimplex.firebooru.model.BooruTag_.__ID_PROPERTY = v0_1;
        return;
    }

    public BooruTag_()
    {
        return;
    }

    public io.objectbox.Property[] getAllProperties()
    {
        return com.bisimplex.firebooru.model.BooruTag_.__ALL_PROPERTIES;
    }

    public io.objectbox.internal.CursorFactory getCursorFactory()
    {
        return com.bisimplex.firebooru.model.BooruTag_.__CURSOR_FACTORY;
    }

    public String getDbName()
    {
        return "BooruTag";
    }

    public Class getEntityClass()
    {
        return com.bisimplex.firebooru.model.BooruTag_.__ENTITY_CLASS;
    }

    public int getEntityId()
    {
        return 4;
    }

    public String getEntityName()
    {
        return "BooruTag";
    }

    public io.objectbox.internal.IdGetter getIdGetter()
    {
        return com.bisimplex.firebooru.model.BooruTag_.__ID_GETTER;
    }

    public io.objectbox.Property getIdProperty()
    {
        return com.bisimplex.firebooru.model.BooruTag_.__ID_PROPERTY;
    }
}
