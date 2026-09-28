package com.bisimplex.firebooru.model;
public final class UpdateEntry_ implements io.objectbox.EntityInfo {
    public static final io.objectbox.Property[] __ALL_PROPERTIES = None;
    public static final io.objectbox.internal.CursorFactory __CURSOR_FACTORY = None;
    public static final String __DB_NAME = "UpdateEntry";
    public static final Class __ENTITY_CLASS = None;
    public static final int __ENTITY_ID = 3;
    public static final String __ENTITY_NAME = "UpdateEntry";
    static final com.bisimplex.firebooru.model.UpdateEntry_$UpdateEntryIdGetter __ID_GETTER;
    public static final io.objectbox.Property __ID_PROPERTY;
    public static final com.bisimplex.firebooru.model.UpdateEntry_ __INSTANCE;
    public static final io.objectbox.Property added_date;
    public static final io.objectbox.Property fav_id;
    public static final io.objectbox.Property id;
    public static final io.objectbox.Property message;
    public static final io.objectbox.Property status;
    public static final io.objectbox.Property update_date;

    static UpdateEntry_()
    {
        com.bisimplex.firebooru.model.UpdateEntry_.__ENTITY_CLASS = com.bisimplex.firebooru.model.UpdateEntry;
        com.bisimplex.firebooru.model.UpdateEntry_.__CURSOR_FACTORY = new com.bisimplex.firebooru.model.UpdateEntryCursor$Factory();
        com.bisimplex.firebooru.model.UpdateEntry_.__ID_GETTER = new com.bisimplex.firebooru.model.UpdateEntry_$UpdateEntryIdGetter();
        io.objectbox.Property[] v2_3 = new com.bisimplex.firebooru.model.UpdateEntry_();
        com.bisimplex.firebooru.model.UpdateEntry_.__INSTANCE = v2_3;
        io.objectbox.Property v0_1 = new io.objectbox.Property(v2_3, 0, 1, Long.TYPE, "id", 1, "id");
        com.bisimplex.firebooru.model.UpdateEntry_.id = v0_1;
        io.objectbox.Property v7_1 = new io.objectbox.Property(v2_3, 1, 2, Long.TYPE, "fav_id");
        com.bisimplex.firebooru.model.UpdateEntry_.fav_id = v7_1;
        io.objectbox.Property v8_1 = new io.objectbox.Property(v2_3, 2, 3, Integer.TYPE, "status");
        com.bisimplex.firebooru.model.UpdateEntry_.status = v8_1;
        io.objectbox.Property v9 = new io.objectbox.Property(v2_3, 3, 4, String, "message");
        com.bisimplex.firebooru.model.UpdateEntry_.message = v9;
        io.objectbox.Property v10 = new io.objectbox.Property(v2_3, 4, 5, java.util.Date, "update_date");
        com.bisimplex.firebooru.model.UpdateEntry_.update_date = v10;
        io.objectbox.Property v1_11 = new io.objectbox.Property(v2_3, 5, 6, java.util.Date, "added_date");
        com.bisimplex.firebooru.model.UpdateEntry_.added_date = v1_11;
        io.objectbox.Property[] v2_1 = new io.objectbox.Property[6];
        v2_1[0] = v0_1;
        v2_1[1] = v7_1;
        v2_1[2] = v8_1;
        v2_1[3] = v9;
        v2_1[4] = v10;
        v2_1[5] = v1_11;
        com.bisimplex.firebooru.model.UpdateEntry_.__ALL_PROPERTIES = v2_1;
        com.bisimplex.firebooru.model.UpdateEntry_.__ID_PROPERTY = v0_1;
        return;
    }

    public UpdateEntry_()
    {
        return;
    }

    public io.objectbox.Property[] getAllProperties()
    {
        return com.bisimplex.firebooru.model.UpdateEntry_.__ALL_PROPERTIES;
    }

    public io.objectbox.internal.CursorFactory getCursorFactory()
    {
        return com.bisimplex.firebooru.model.UpdateEntry_.__CURSOR_FACTORY;
    }

    public String getDbName()
    {
        return "UpdateEntry";
    }

    public Class getEntityClass()
    {
        return com.bisimplex.firebooru.model.UpdateEntry_.__ENTITY_CLASS;
    }

    public int getEntityId()
    {
        return 3;
    }

    public String getEntityName()
    {
        return "UpdateEntry";
    }

    public io.objectbox.internal.IdGetter getIdGetter()
    {
        return com.bisimplex.firebooru.model.UpdateEntry_.__ID_GETTER;
    }

    public io.objectbox.Property getIdProperty()
    {
        return com.bisimplex.firebooru.model.UpdateEntry_.__ID_PROPERTY;
    }
}
