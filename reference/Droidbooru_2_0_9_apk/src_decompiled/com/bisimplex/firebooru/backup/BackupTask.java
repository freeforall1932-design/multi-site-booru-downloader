package com.bisimplex.firebooru.backup;
public class BackupTask {
    private final com.bisimplex.firebooru.backup.BackupConfiguration configuration;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private ref.WeakReference mListener;
    private final com.google.gson.Gson serializer;
    private final android.net.Uri uri;
    private boolean working;

    public static synthetic void $r8$lambda$5wA-kU1EMRdiLmFo7DUOC1EQO6s(com.bisimplex.firebooru.backup.BackupTask p0)
    {
        p0.lambda$doJSONWork$0();
        return;
    }

    public static synthetic void $r8$lambda$H6IJp6_yOVY-89QzsN9PlYDPANE(com.bisimplex.firebooru.backup.BackupTask p0, Exception p1)
    {
        p0.lambda$doWork$2(p1);
        return;
    }

    public static synthetic void $r8$lambda$dsudlDpfrxwkSdlMYiY5qIjSaTg(com.bisimplex.firebooru.backup.BackupTask p0, android.net.Uri p1)
    {
        p0.lambda$doWork$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$jhm7UfMEKuoiGCJZb85wRm0gz3E(com.bisimplex.firebooru.backup.BackupTask p0)
    {
        p0.doWork();
        return;
    }

    public BackupTask(ref.WeakReference p4, android.net.Uri p5, com.bisimplex.firebooru.backup.BackupConfiguration p6)
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.serializer = new com.google.gson.GsonBuilder().setDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ").registerTypeAdapter(com.bisimplex.firebooru.backup.BackupServerItemType, new com.bisimplex.firebooru.backup.BackupServerItemTypeAdapter()).registerTypeAdapter(com.bisimplex.firebooru.danbooru.DanbooruPostContentType, new com.bisimplex.firebooru.backup.DanbooruContentTypeAdapter()).excludeFieldsWithModifiers(new int[] {1176})).create();
        this.mListener = p4;
        this.uri = p5;
        this.configuration = ((com.bisimplex.firebooru.backup.BackupConfiguration) com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticBackport0.m(p6, new com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticLambda3()));
        return;
    }

    private void addColumn(java.util.List p1, int p2)
    {
        p1.add(String.valueOf(p2));
        return;
    }

    private void addColumn(java.util.List p2, String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            p2.add(p3.trim());
            return;
        } else {
            p2.add("");
            return;
        }
    }

    private void addColumn(java.util.List p1, java.net.URL p2)
    {
        if (p2 != null) {
            p1.add(p2.toString());
            return;
        } else {
            p1.add("");
            return;
        }
    }

    private void addColumn(java.util.List p1, java.util.Date p2, java.text.DateFormat p3)
    {
        if (p2 != null) {
            p1.add(p3.format(p2));
            return;
        } else {
            p1.add("");
            return;
        }
    }

    private void addColumn(java.util.List p1, boolean p2)
    {
        p1.add(String.valueOf(p2));
        return;
    }

    private void addSourceSpecs(java.util.List p8, com.bisimplex.firebooru.model.SourceSpecs p9, de.siegmar.fastcsv.writer.CsvWriter p10, String p11, String p12)
    {
        p8.clear();
        p8.add(p11);
        p8.add(p12);
        this.addColumn(p8, p9.getKey());
        this.addColumn(p8, p9.getUrl());
        this.addColumn(p8, p9.getType());
        this.addColumn(p8, this.serializer.toJson(p9.getQuery()));
        this.addColumn(p8, "");
        this.addColumn(p8, "");
        this.addColumn(p8, "");
        this.addColumn(p8, "");
        this.addColumn(p8, "");
        p10.writeRecord(p8);
        if (p9.getChilds() != null) {
            java.util.Iterator v12_7 = p9.getChilds().iterator();
            while (v12_7.hasNext()) {
                this.addSourceSpecs(p8, ((com.bisimplex.firebooru.model.SourceSpecs) v12_7.next()), p10, p11, p9.getKey());
            }
        }
        return;
    }

    private android.net.Uri doCSVWork(com.bisimplex.firebooru.backup.LiveDB p13)
    {
        if (this.uri == null) {
            throw new java.io.IOException("No file location defined");
        } else {
            android.net.Uri v1_1 = androidx.documentfile.provider.DocumentFile.fromTreeUri(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), this.uri);
            if ((v1_1 == null) || (!v1_1.canWrite())) {
                return 0;
            } else {
                String v3_13 = java.text.SimpleDateFormat.getDateTimeInstance(1, 1);
                java.util.Iterator v4_3 = new java.text.SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ", java.util.Locale.US);
                android.net.Uri v1_2 = v1_1.createFile("text/csv", String.format("%s", new Object[] {v3_13.format(p13.getBackupTime())})));
                if (v1_2 != null) {
                    android.os.ParcelFileDescriptor v2_2 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver();
                    android.net.Uri v1_3 = v1_2.getUri();
                    android.os.ParcelFileDescriptor v2_3 = v2_2.openFileDescriptor(v1_3, "rwt");
                    java.io.FileWriter v5_5 = new java.io.FileWriter(v2_3.getFileDescriptor());
                    de.siegmar.fastcsv.writer.CsvWriter v9 = de.siegmar.fastcsv.writer.CsvWriter.builder().build(v5_5);
                    try {
                        java.util.ArrayList v7_1 = new java.util.ArrayList(10);
                        v9.writeComment("Meta");
                        String v3_24 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.Meta.getValue());
                        v7_1.clear();
                        v7_1.add(v3_24);
                        this.addColumn(v7_1, p13.getBackupVersion());
                        this.addColumn(v7_1, p13.getCreatorName());
                        this.addColumn(v7_1, p13.getCreatorVersion());
                        this.addColumn(v7_1, p13.getBackupTime(), v4_3);
                        v7_1.add("");
                        v7_1.add("");
                        v7_1.add("");
                        v7_1.add("");
                        v7_1.add("");
                        v9.writeRecord(v7_1);
                        v9.writeComment("Servers");
                        String v3_32 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.Server.getValue());
                        void v6_11 = p13.getServers().iterator();
                    } catch (Object v0_6) {
                        java.util.Iterator v13_4 = v0_6;
                        if (v9 != null) {
                            try {
                                v9.close();
                            } catch (Object v0_7) {
                                v13_4.addSuppressed(v0_7);
                            }
                        }
                        throw v13_4;
                    }
                    while (v6_11.hasNext()) {
                        com.bisimplex.firebooru.model.SourceSpecs v8_12 = ((com.bisimplex.firebooru.backup.BackupServerItem) v6_11.next());
                        v7_1.clear();
                        v7_1.add(v3_32);
                        this.addColumn(v7_1, v8_12.getServerId());
                        this.addColumn(v7_1, v8_12.getUrl());
                        this.addColumn(v7_1, v8_12.getServerName());
                        this.addColumn(v7_1, v8_12.isUseNativeAutocomplete());
                        this.addColumn(v7_1, v8_12.isRatingFilterEnabled());
                        this.addColumn(v7_1, v8_12.isSelected());
                        this.addColumn(v7_1, v8_12.isDefault());
                        this.addColumn(v7_1, v8_12.getType().getValue());
                        this.addColumn(v7_1, v8_12.getUserName());
                        this.addColumn(v7_1, v8_12.getPassword());
                        this.addColumn(v7_1, v8_12.getRealURL());
                        this.addColumn(v7_1, v8_12.getApikey());
                        this.addColumn(v7_1, v8_12.getApiKey());
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        v9.writeRecord(v7_1);
                    }
                    v9.writeComment("History");
                    String v3_2 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.History.getValue());
                    void v6_1 = p13.getSearchHistory().iterator();
                    while (v6_1.hasNext()) {
                        com.bisimplex.firebooru.model.SourceSpecs v8_9 = ((com.bisimplex.firebooru.backup.BackupTagHistory) v6_1.next());
                        v7_1.clear();
                        v7_1.add(v3_2);
                        this.addColumn(v7_1, v8_9.itemId);
                        this.addColumn(v7_1, v8_9.searchText);
                        this.addColumn(v7_1, v8_9.searchDate, v4_3);
                        this.addColumn(v7_1, v8_9.starred);
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        v9.writeRecord(v7_1);
                    }
                    v9.writeComment("Favorites");
                    String v3_6 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.Favorite.getValue());
                    void v6_3 = p13.getRawFavorites().iterator();
                    while (v6_3.hasNext()) {
                        com.bisimplex.firebooru.model.SourceSpecs v8_6 = ((com.bisimplex.firebooru.model.Favorite) v6_3.next());
                        v7_1.clear();
                        v7_1.add(v3_6);
                        this.addColumn(v7_1, v8_6.postid);
                        this.addColumn(v7_1, v8_6.posturl);
                        this.addColumn(v7_1, v8_6.sample_width);
                        this.addColumn(v7_1, v8_6.sample_height);
                        this.addColumn(v7_1, v8_6.sample_url);
                        this.addColumn(v7_1, v8_6.preview_width);
                        this.addColumn(v7_1, v8_6.preview_height);
                        this.addColumn(v7_1, v8_6.preview_url);
                        this.addColumn(v7_1, v8_6.width);
                        this.addColumn(v7_1, v8_6.height);
                        this.addColumn(v7_1, v8_6.file_url);
                        this.addColumn(v7_1, v8_6.jpeg_width);
                        this.addColumn(v7_1, v8_6.jpeg_height);
                        this.addColumn(v7_1, v8_6.jpeg_url);
                        this.addColumn(v7_1, v8_6.tags);
                        this.addColumn(v7_1, v8_6.tag_string_general);
                        this.addColumn(v7_1, v8_6.tag_string_artist);
                        this.addColumn(v7_1, v8_6.tag_string_character);
                        this.addColumn(v7_1, v8_6.tag_string_copyright);
                        this.addColumn(v7_1, v8_6.md5);
                        this.addColumn(v7_1, v8_6.source);
                        this.addColumn(v7_1, v8_6.parent_id);
                        this.addColumn(v7_1, v8_6.score);
                        this.addColumn(v7_1, v8_6.rating);
                        this.addColumn(v7_1, v8_6.has_notes);
                        this.addColumn(v7_1, v8_6.has_comments);
                        this.addColumn(v7_1, v8_6.has_children);
                        this.addColumn(v7_1, v8_6.dateAdded, v4_3);
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        v9.writeRecord(v7_1);
                    }
                    v9.writeComment("Tag Blacklist");
                    String v3_10 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.Blacklist.getValue());
                    java.util.Iterator v4_1 = p13.getBannedTags().iterator();
                    while (v4_1.hasNext()) {
                        void v6_7 = ((com.bisimplex.firebooru.model.BannedTag) v4_1.next());
                        v7_1.clear();
                        v7_1.add(v3_10);
                        this.addColumn(v7_1, v6_7.tagText);
                        this.addColumn(v7_1, v6_7.bannedTagid);
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        this.addColumn(v7_1, "");
                        v9.writeRecord(v7_1);
                    }
                    v9.writeComment("Home Pins");
                    String v10_0 = String.valueOf(com.bisimplex.firebooru.backup.BackupCSVRowType.HomePin.getValue());
                    java.util.Iterator v13_2 = p13.getHomePins().iterator();
                    while (v13_2.hasNext()) {
                        this.addSourceSpecs(v7_1, ((com.bisimplex.firebooru.model.SourceSpecs) v13_2.next()), v9, v10_0, "");
                    }
                    if (v9 != null) {
                        v9.close();
                    }
                    v5_5.close();
                    v2_3.close();
                    return v1_3;
                } else {
                    return 0;
                }
            }
        }
    }

    private android.net.Uri doJSONWork(com.bisimplex.firebooru.backup.LiveDB p5)
    {
        android.net.Uri v0_1 = new java.util.Date();
        if (this.uri != null) {
            android.os.ParcelFileDescriptor v1_5 = androidx.documentfile.provider.DocumentFile.fromTreeUri(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), this.uri);
            if ((v1_5 != null) && (v1_5.canWrite())) {
                android.net.Uri v0_5 = v1_5.createFile("text/json", String.format("%s.abbj", new Object[] {java.text.SimpleDateFormat.getDateTimeInstance(1, 1).format(v0_1)})));
                if (v0_5 != null) {
                    android.os.ParcelFileDescriptor v1_2 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver();
                    android.net.Uri v0_6 = v0_5.getUri();
                    android.os.ParcelFileDescriptor v1_3 = v1_2.openFileDescriptor(v0_6, "rwt");
                    java.io.FileWriter v3_6 = new java.io.FileWriter(v1_3.getFileDescriptor());
                    this.serializer.toJson(p5, v3_6);
                    v3_6.close();
                    v1_3.close();
                    return v0_6;
                } else {
                    this.enqueueHandler.post(new com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticLambda5(this));
                    return 0;
                }
            }
        }
        return 0;
    }

    private void doWork()
    {
        try {
            android.os.Handler v1_2 = System.nanoTime();
            this.working = 1;
            android.net.Uri v3_4 = new com.bisimplex.firebooru.backup.LiveDB();
            String v4_10 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        } catch (android.os.Handler v1_0) {
            this.enqueueHandler.post(new com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticLambda2(this, v1_0));
            this.working = 0;
            return;
        } catch (android.os.Handler v1_1) {
            this.working = 0;
            throw v1_1;
        }
        if (this.configuration.isFavorites()) {
            if (!this.configuration.isToCSV()) {
                v3_4.setFavorites(v4_10.loadFavorites());
            } else {
                v3_4.setRawFavorites(v4_10.loadRawFavorites());
            }
        }
        if (this.configuration.isServers()) {
            StringBuilder v5_7 = v4_10.loadServers();
            long v6_3 = new java.util.ArrayList();
            StringBuilder v5_8 = v5_7.iterator();
            while (v5_8.hasNext()) {
                v6_3.add(new com.bisimplex.firebooru.backup.BackupServerItem(((com.bisimplex.firebooru.danbooru.ServerItem) v5_8.next())));
            }
            v3_4.setServers(v6_3);
        }
        if (this.configuration.isBannedTags()) {
            v3_4.setBannedTags(v4_10.loadBannedTags());
        }
        if (this.configuration.isHistory()) {
            String v4_0 = v4_10.loadHistory();
            StringBuilder v5_15 = new java.util.ArrayList(v4_0.size());
            String v4_1 = v4_0.iterator();
            while (v4_1.hasNext()) {
                v5_15.add(new com.bisimplex.firebooru.backup.BackupTagHistory(((com.bisimplex.firebooru.model.TagHistory) v4_1.next())));
            }
            v3_4.setSearchHistory(v5_15);
        }
        if (this.configuration.isHomeSources()) {
            v3_4.setHomePins(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSourceSpecs());
        }
        android.net.Uri v3_5;
        v3_4.setBackupTime(new java.util.Date());
        if (!this.configuration.isToCSV()) {
            v3_5 = this.doJSONWork(v3_4);
        } else {
            v3_5 = this.doCSVWork(v3_4);
        }
        android.util.Log.e("Measure", new StringBuilder().append("Backup Took: ").append(((System.nanoTime() - v1_2) / 1000000)).append("mS\n").toString());
        this.enqueueHandler.post(new com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticLambda1(this, v3_5));
        this.working = 0;
        return;
    }

    private synthetic void lambda$doJSONWork$0()
    {
        com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener) this.mListener.get()).workDone(0, "Target document is Null");
        }
        return;
    }

    private synthetic void lambda$doWork$1(android.net.Uri p3)
    {
        com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener) this.mListener.get()).workDone(p3, 0);
        }
        return;
    }

    private synthetic void lambda$doWork$2(Exception p3)
    {
        com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener) this.mListener.get()).workDone(0, p3.getLocalizedMessage());
        }
        return;
    }

    public boolean isWorking()
    {
        return this.working;
    }

    public void setListener(ref.WeakReference p1)
    {
        this.mListener = p1;
        return;
    }

    public void start()
    {
        this.executor.execute(new com.bisimplex.firebooru.backup.BackupTask$$ExternalSyntheticLambda4(this));
        return;
    }
}
