package com.bisimplex.firebooru.model;
public final class DownloadEntry_ implements io.objectbox.EntityInfo {
    public static final io.objectbox.Property[] __ALL_PROPERTIES = None;
    public static final io.objectbox.internal.CursorFactory __CURSOR_FACTORY = None;
    public static final String __DB_NAME = "DownloadEntry";
    public static final Class __ENTITY_CLASS = None;
    public static final int __ENTITY_ID = 2;
    public static final String __ENTITY_NAME = "DownloadEntry";
    static final com.bisimplex.firebooru.model.DownloadEntry_$DownloadEntryIdGetter __ID_GETTER;
    public static final io.objectbox.Property __ID_PROPERTY;
    public static final com.bisimplex.firebooru.model.DownloadEntry_ __INSTANCE;
    public static final io.objectbox.Property avoid_duplicate;
    public static final io.objectbox.Property date_added;
    public static final io.objectbox.Property download_date;
    public static final io.objectbox.Property error_code;
    public static final io.objectbox.Property error_message;
    public static final io.objectbox.Property exclude_animated;
    public static final io.objectbox.Property extension;
    public static final io.objectbox.Property file_name;
    public static final io.objectbox.Property file_url;
    public static final io.objectbox.Property id;
    public static final io.objectbox.Property md5;
    public static final io.objectbox.Property post_id;
    public static final io.objectbox.Property post_url;
    public static final io.objectbox.Property preview_url;
    public static final io.objectbox.Property query;
    public static final io.objectbox.Property rating;
    public static final io.objectbox.Property sample_url;
    public static final io.objectbox.Property source;
    public static final io.objectbox.Property status;
    public static final io.objectbox.Property tag_artist;
    public static final io.objectbox.Property tag_character;
    public static final io.objectbox.Property tag_copyright;
    public static final io.objectbox.Property tag_general;
    public static final io.objectbox.Property tags;
    public static final io.objectbox.Property target_folder;

    static DownloadEntry_()
    {
        com.bisimplex.firebooru.model.DownloadEntry_.__ENTITY_CLASS = com.bisimplex.firebooru.model.DownloadEntry;
        com.bisimplex.firebooru.model.DownloadEntry_.__CURSOR_FACTORY = new com.bisimplex.firebooru.model.DownloadEntryCursor$Factory();
        com.bisimplex.firebooru.model.DownloadEntry_.__ID_GETTER = new com.bisimplex.firebooru.model.DownloadEntry_$DownloadEntryIdGetter();
        io.objectbox.Property[] v2_3 = new com.bisimplex.firebooru.model.DownloadEntry_();
        com.bisimplex.firebooru.model.DownloadEntry_.__INSTANCE = v2_3;
        io.objectbox.Property v0_1 = new io.objectbox.Property(v2_3, 0, 1, Long.TYPE, "id", 1, "id");
        com.bisimplex.firebooru.model.DownloadEntry_.id = v0_1;
        io.objectbox.Property v7_1 = new io.objectbox.Property(v2_3, 1, 2, String, "preview_url");
        com.bisimplex.firebooru.model.DownloadEntry_.preview_url = v7_1;
        io.objectbox.Property v8_1 = new io.objectbox.Property(v2_3, 2, 3, String, "sample_url");
        com.bisimplex.firebooru.model.DownloadEntry_.sample_url = v8_1;
        io.objectbox.Property v9 = new io.objectbox.Property(v2_3, 3, 4, String, "file_url");
        com.bisimplex.firebooru.model.DownloadEntry_.file_url = v9;
        io.objectbox.Property v10 = new io.objectbox.Property(v2_3, 4, 5, String, "post_id");
        com.bisimplex.firebooru.model.DownloadEntry_.post_id = v10;
        io.objectbox.Property v11 = new io.objectbox.Property(v2_3, 5, 6, String, "tags");
        com.bisimplex.firebooru.model.DownloadEntry_.tags = v11;
        io.objectbox.Property v12 = new io.objectbox.Property(v2_3, 6, 7, String, "post_url");
        com.bisimplex.firebooru.model.DownloadEntry_.post_url = v12;
        io.objectbox.Property v13 = new io.objectbox.Property(v2_3, 7, 8, String, "rating");
        com.bisimplex.firebooru.model.DownloadEntry_.rating = v13;
        io.objectbox.Property v14 = new io.objectbox.Property(v2_3, 8, 9, String, "md5");
        com.bisimplex.firebooru.model.DownloadEntry_.md5 = v14;
        io.objectbox.Property v15 = new io.objectbox.Property(v2_3, 9, 10, String, "source");
        com.bisimplex.firebooru.model.DownloadEntry_.source = v15;
        io.objectbox.Property v16 = new io.objectbox.Property(v2_3, 10, 11, String, "tag_general");
        com.bisimplex.firebooru.model.DownloadEntry_.tag_general = v16;
        io.objectbox.Property v17 = new io.objectbox.Property(v2_3, 11, 12, String, "tag_copyright");
        com.bisimplex.firebooru.model.DownloadEntry_.tag_copyright = v17;
        io.objectbox.Property v18 = new io.objectbox.Property(v2_3, 12, 13, String, "tag_character");
        com.bisimplex.firebooru.model.DownloadEntry_.tag_character = v18;
        io.objectbox.Property v19 = new io.objectbox.Property(v2_3, 13, 14, String, "tag_artist");
        com.bisimplex.firebooru.model.DownloadEntry_.tag_artist = v19;
        io.objectbox.Property v20 = new io.objectbox.Property(v2_3, 14, 15, String, "query");
        com.bisimplex.firebooru.model.DownloadEntry_.query = v20;
        io.objectbox.Property v21 = new io.objectbox.Property(v2_3, 15, 16, java.util.Date, "date_added");
        com.bisimplex.firebooru.model.DownloadEntry_.date_added = v21;
        io.objectbox.Property v22 = new io.objectbox.Property(v2_3, 16, 24, java.util.Date, "download_date");
        com.bisimplex.firebooru.model.DownloadEntry_.download_date = v22;
        io.objectbox.Property v23 = new io.objectbox.Property(v2_3, 17, 17, String, "file_name");
        com.bisimplex.firebooru.model.DownloadEntry_.file_name = v23;
        io.objectbox.Property v24 = new io.objectbox.Property(v2_3, 18, 18, Integer.TYPE, "status");
        com.bisimplex.firebooru.model.DownloadEntry_.status = v24;
        io.objectbox.Property v25 = new io.objectbox.Property(v2_3, 19, 19, Boolean.TYPE, "avoid_duplicate");
        com.bisimplex.firebooru.model.DownloadEntry_.avoid_duplicate = v25;
        io.objectbox.Property v26 = new io.objectbox.Property(v2_3, 20, 20, Boolean.TYPE, "exclude_animated");
        com.bisimplex.firebooru.model.DownloadEntry_.exclude_animated = v26;
        io.objectbox.Property v27 = new io.objectbox.Property(v2_3, 21, 21, String, "error_message");
        com.bisimplex.firebooru.model.DownloadEntry_.error_message = v27;
        io.objectbox.Property v28 = new io.objectbox.Property(v2_3, 22, 22, Integer.TYPE, "error_code");
        com.bisimplex.firebooru.model.DownloadEntry_.error_code = v28;
        io.objectbox.Property v29 = new io.objectbox.Property(v2_3, 23, 23, String, "extension");
        com.bisimplex.firebooru.model.DownloadEntry_.extension = v29;
        io.objectbox.Property v1_30 = new io.objectbox.Property(v2_3, 24, 25, String, "target_folder");
        com.bisimplex.firebooru.model.DownloadEntry_.target_folder = v1_30;
        io.objectbox.Property[] v2_1 = new io.objectbox.Property[25];
        v2_1[0] = v0_1;
        v2_1[1] = v7_1;
        v2_1[2] = v8_1;
        v2_1[3] = v9;
        v2_1[4] = v10;
        v2_1[5] = v11;
        v2_1[6] = v12;
        v2_1[7] = v13;
        v2_1[8] = v14;
        v2_1[9] = v15;
        v2_1[10] = v16;
        v2_1[11] = v17;
        v2_1[12] = v18;
        v2_1[13] = v19;
        v2_1[14] = v20;
        v2_1[15] = v21;
        v2_1[16] = v22;
        v2_1[17] = v23;
        v2_1[18] = v24;
        v2_1[19] = v25;
        v2_1[20] = v26;
        v2_1[21] = v27;
        v2_1[22] = v28;
        v2_1[23] = v29;
        v2_1[24] = v1_30;
        com.bisimplex.firebooru.model.DownloadEntry_.__ALL_PROPERTIES = v2_1;
        com.bisimplex.firebooru.model.DownloadEntry_.__ID_PROPERTY = v0_1;
        return;
    }

    public DownloadEntry_()
    {
        return;
    }

    public io.objectbox.Property[] getAllProperties()
    {
        return com.bisimplex.firebooru.model.DownloadEntry_.__ALL_PROPERTIES;
    }

    public io.objectbox.internal.CursorFactory getCursorFactory()
    {
        return com.bisimplex.firebooru.model.DownloadEntry_.__CURSOR_FACTORY;
    }

    public String getDbName()
    {
        return "DownloadEntry";
    }

    public Class getEntityClass()
    {
        return com.bisimplex.firebooru.model.DownloadEntry_.__ENTITY_CLASS;
    }

    public int getEntityId()
    {
        return 2;
    }

    public String getEntityName()
    {
        return "DownloadEntry";
    }

    public io.objectbox.internal.IdGetter getIdGetter()
    {
        return com.bisimplex.firebooru.model.DownloadEntry_.__ID_GETTER;
    }

    public io.objectbox.Property getIdProperty()
    {
        return com.bisimplex.firebooru.model.DownloadEntry_.__ID_PROPERTY;
    }
}
