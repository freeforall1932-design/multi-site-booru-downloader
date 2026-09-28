package com.bisimplex.firebooru.model;
public final class DownloadEntryCursor extends io.objectbox.Cursor {
    private static final com.bisimplex.firebooru.model.DownloadEntry_$DownloadEntryIdGetter ID_GETTER;
    private static final int __ID_avoid_duplicate;
    private static final int __ID_date_added;
    private static final int __ID_download_date;
    private static final int __ID_error_code;
    private static final int __ID_error_message;
    private static final int __ID_exclude_animated;
    private static final int __ID_extension;
    private static final int __ID_file_name;
    private static final int __ID_file_url;
    private static final int __ID_md5;
    private static final int __ID_post_id;
    private static final int __ID_post_url;
    private static final int __ID_preview_url;
    private static final int __ID_query;
    private static final int __ID_rating;
    private static final int __ID_sample_url;
    private static final int __ID_source;
    private static final int __ID_status;
    private static final int __ID_tag_artist;
    private static final int __ID_tag_character;
    private static final int __ID_tag_copyright;
    private static final int __ID_tag_general;
    private static final int __ID_tags;
    private static final int __ID_target_folder;

    static DownloadEntryCursor()
    {
        com.bisimplex.firebooru.model.DownloadEntryCursor.ID_GETTER = com.bisimplex.firebooru.model.DownloadEntry_.__ID_GETTER;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_preview_url = com.bisimplex.firebooru.model.DownloadEntry_.preview_url.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_sample_url = com.bisimplex.firebooru.model.DownloadEntry_.sample_url.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_file_url = com.bisimplex.firebooru.model.DownloadEntry_.file_url.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_post_id = com.bisimplex.firebooru.model.DownloadEntry_.post_id.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tags = com.bisimplex.firebooru.model.DownloadEntry_.tags.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_post_url = com.bisimplex.firebooru.model.DownloadEntry_.post_url.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_rating = com.bisimplex.firebooru.model.DownloadEntry_.rating.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_md5 = com.bisimplex.firebooru.model.DownloadEntry_.md5.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_source = com.bisimplex.firebooru.model.DownloadEntry_.source.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_general = com.bisimplex.firebooru.model.DownloadEntry_.tag_general.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_copyright = com.bisimplex.firebooru.model.DownloadEntry_.tag_copyright.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_character = com.bisimplex.firebooru.model.DownloadEntry_.tag_character.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_artist = com.bisimplex.firebooru.model.DownloadEntry_.tag_artist.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_query = com.bisimplex.firebooru.model.DownloadEntry_.query.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_date_added = com.bisimplex.firebooru.model.DownloadEntry_.date_added.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_download_date = com.bisimplex.firebooru.model.DownloadEntry_.download_date.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_file_name = com.bisimplex.firebooru.model.DownloadEntry_.file_name.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_status = com.bisimplex.firebooru.model.DownloadEntry_.status.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_avoid_duplicate = com.bisimplex.firebooru.model.DownloadEntry_.avoid_duplicate.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_exclude_animated = com.bisimplex.firebooru.model.DownloadEntry_.exclude_animated.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_error_message = com.bisimplex.firebooru.model.DownloadEntry_.error_message.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_error_code = com.bisimplex.firebooru.model.DownloadEntry_.error_code.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_extension = com.bisimplex.firebooru.model.DownloadEntry_.extension.id;
        com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_target_folder = com.bisimplex.firebooru.model.DownloadEntry_.target_folder.id;
        return;
    }

    public DownloadEntryCursor(io.objectbox.Transaction p7, long p8, io.objectbox.BoxStore p10)
    {
        super(p7, p8, com.bisimplex.firebooru.model.DownloadEntry_.__INSTANCE, p10);
        return;
    }

    public long getId(com.bisimplex.firebooru.model.DownloadEntry p3)
    {
        return com.bisimplex.firebooru.model.DownloadEntryCursor.ID_GETTER.getId(p3);
    }

    public bridge synthetic long getId(Object p3)
    {
        return this.getId(((com.bisimplex.firebooru.model.DownloadEntry) p3));
    }

    public long put(com.bisimplex.firebooru.model.DownloadEntry p62)
    {
        int v6;
        long v7_1 = p62.getPreview_url();
        int v14 = 0;
        if (v7_1 == 0) {
            v6 = 0;
        } else {
            v6 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_preview_url;
        }
        int v8;
        String v9 = p62.getSample_url();
        if (v9 == null) {
            v8 = 0;
        } else {
            v8 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_sample_url;
        }
        int v10;
        String v11 = p62.getFile_url();
        if (v11 == null) {
            v10 = 0;
        } else {
            v10 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_file_url;
        }
        int v12;
        String v13 = p62.getPost_id();
        if (v13 == null) {
            v12 = 0;
        } else {
            v12 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_post_id;
        }
        int v20_0;
        com.bisimplex.firebooru.model.DownloadEntryCursor.collect400000(this.cursor, 0, 1, v6, v7_1, v8, v9, v10, v11, v12, v13);
        String v21_0 = p62.getTags();
        if (v21_0 == null) {
            v20_0 = 0;
        } else {
            v20_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tags;
        }
        int v22_0;
        String v23_0 = p62.getPost_url();
        if (v23_0 == null) {
            v22_0 = 0;
        } else {
            v22_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_post_url;
        }
        int v24_1;
        String v25_1 = p62.getRating();
        if (v25_1 == null) {
            v24_1 = 0;
        } else {
            v24_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_rating;
        }
        int v26_1;
        String v27_1 = p62.getMd5();
        if (v27_1 == null) {
            v26_1 = 0;
        } else {
            v26_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_md5;
        }
        int v33_1;
        com.bisimplex.firebooru.model.DownloadEntryCursor.collect400000(this.cursor, 0, 0, v20_0, v21_0, v22_0, v23_0, v24_1, v25_1, v26_1, v27_1);
        String v34_1 = p62.getSource();
        if (v34_1 == null) {
            v33_1 = 0;
        } else {
            v33_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_source;
        }
        int v35_1;
        String v36_1 = p62.getTag_general();
        if (v36_1 == null) {
            v35_1 = 0;
        } else {
            v35_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_general;
        }
        int v37_1;
        int v38_1 = p62.getTag_copyright();
        if (v38_1 == 0) {
            v37_1 = 0;
        } else {
            v37_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_copyright;
        }
        int v39_1;
        int v40_1 = p62.getTag_character();
        if (v40_1 == 0) {
            v39_1 = 0;
        } else {
            v39_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_character;
        }
        int v20_1;
        com.bisimplex.firebooru.model.DownloadEntryCursor.collect400000(this.cursor, 0, 0, v33_1, v34_1, v35_1, v36_1, v37_1, v38_1, v39_1, v40_1);
        String v21_1 = p62.getTag_artist();
        if (v21_1 == null) {
            v20_1 = 0;
        } else {
            v20_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_tag_artist;
        }
        int v22_1;
        String v23_1 = p62.getQuery();
        if (v23_1 == null) {
            v22_1 = 0;
        } else {
            v22_1 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_query;
        }
        int v24_0;
        String v25_0 = p62.getFile_name();
        if (v25_0 == null) {
            v24_0 = 0;
        } else {
            v24_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_file_name;
        }
        int v26_0;
        String v27_0 = p62.getError_message();
        if (v27_0 == null) {
            v26_0 = 0;
        } else {
            v26_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_error_message;
        }
        int v33_0;
        com.bisimplex.firebooru.model.DownloadEntryCursor.collect400000(this.cursor, 0, 0, v20_1, v21_1, v22_1, v23_1, v24_0, v25_0, v26_0, v27_0);
        String v34_0 = p62.getExtension();
        if (v34_0 == null) {
            v33_0 = 0;
        } else {
            v33_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_extension;
        }
        int v35_0;
        String v36_0 = p62.getTarget_folder();
        if (v36_0 == null) {
            v35_0 = 0;
        } else {
            v35_0 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_target_folder;
        }
        int v41;
        long v1_6 = p62.getDate_added();
        if (v1_6 == 0) {
            v41 = 0;
        } else {
            v41 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_date_added;
        }
        java.util.Date v2_1 = p62.getDownload_date();
        if (v2_1 != null) {
            v14 = com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_download_date;
        }
        long v42;
        int v44 = v14;
        com.bisimplex.firebooru.model.DownloadEntry v3_0 = this.cursor;
        long v30_0 = p62.getId();
        long v5_0 = 0;
        if (v41 == 0) {
            v42 = 0;
        } else {
            v42 = v1_6.getTime();
        }
        if (v44 != 0) {
            v5_0 = v2_1.getTime();
        }
        long v1_9 = com.bisimplex.firebooru.model.DownloadEntryCursor.collect313311(v3_0, v30_0, 2, v33_0, v34_0, v35_0, v36_0, 0, 0, 0, 0, v41, v42, v44, v5_0, com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_status, ((long) p62.getStatus()), com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_error_code, p62.getError_code(), com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_avoid_duplicate, p62.isAvoid_duplicate(), com.bisimplex.firebooru.model.DownloadEntryCursor.__ID_exclude_animated, p62.isExclude_animated(), 0, 0, 0, 0);
        p62.setId(v1_9);
        return v1_9;
    }

    public bridge synthetic long put(Object p3)
    {
        return this.put(((com.bisimplex.firebooru.model.DownloadEntry) p3));
    }
}
