package com.bisimplex.firebooru.model;
public final class UpdateEntryCursor extends io.objectbox.Cursor {
    private static final com.bisimplex.firebooru.model.UpdateEntry_$UpdateEntryIdGetter ID_GETTER;
    private static final int __ID_added_date;
    private static final int __ID_fav_id;
    private static final int __ID_message;
    private static final int __ID_status;
    private static final int __ID_update_date;

    static UpdateEntryCursor()
    {
        com.bisimplex.firebooru.model.UpdateEntryCursor.ID_GETTER = com.bisimplex.firebooru.model.UpdateEntry_.__ID_GETTER;
        com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_fav_id = com.bisimplex.firebooru.model.UpdateEntry_.fav_id.id;
        com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_status = com.bisimplex.firebooru.model.UpdateEntry_.status.id;
        com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_message = com.bisimplex.firebooru.model.UpdateEntry_.message.id;
        com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_update_date = com.bisimplex.firebooru.model.UpdateEntry_.update_date.id;
        com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_added_date = com.bisimplex.firebooru.model.UpdateEntry_.added_date.id;
        return;
    }

    public UpdateEntryCursor(io.objectbox.Transaction p7, long p8, io.objectbox.BoxStore p10)
    {
        super(p7, p8, com.bisimplex.firebooru.model.UpdateEntry_.__INSTANCE, p10);
        return;
    }

    public long getId(com.bisimplex.firebooru.model.UpdateEntry p3)
    {
        return com.bisimplex.firebooru.model.UpdateEntryCursor.ID_GETTER.getId(p3);
    }

    public bridge synthetic long getId(Object p3)
    {
        return this.getId(((com.bisimplex.firebooru.model.UpdateEntry) p3));
    }

    public long put(com.bisimplex.firebooru.model.UpdateEntry p36)
    {
        int v7;
        String v8 = p36.getMessage();
        com.bisimplex.firebooru.model.UpdateEntryCursor v0_0 = 0;
        if (v8 == null) {
            v7 = 0;
        } else {
            v7 = com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_message;
        }
        int v18;
        long v1_2 = p36.getUpdate_date();
        if (v1_2 == 0) {
            v18 = 0;
        } else {
            v18 = com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_update_date;
        }
        long v2_1 = p36.getAdded_date();
        if (v2_1 != 0) {
            v0_0 = com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_added_date;
        }
        long v19;
        int v21 = v0_0;
        long v2_2 = this.cursor;
        int v6_0 = v2_1;
        long v4_1 = p36.getId();
        long v16 = p36.getFav_id();
        int v9_0 = 0;
        if (v18 == 0) {
            v19 = 0;
        } else {
            v19 = v1_2.getTime();
        }
        if (v21 != 0) {
            v9_0 = v6_0.getTime();
        }
        long v1_1 = com.bisimplex.firebooru.model.UpdateEntryCursor.collect313311(v2_2, v4_1, 3, v7, v8, 0, 0, 0, 0, 0, 0, com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_fav_id, v16, v18, v19, v21, v9_0, com.bisimplex.firebooru.model.UpdateEntryCursor.__ID_status, p36.getStatus(), 0, 0, 0, 0, 0, 0, 0, 0);
        p36.setId(v1_1);
        return v1_1;
    }

    public bridge synthetic long put(Object p3)
    {
        return this.put(((com.bisimplex.firebooru.model.UpdateEntry) p3));
    }
}
