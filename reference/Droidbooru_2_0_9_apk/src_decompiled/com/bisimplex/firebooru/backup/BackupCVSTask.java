package com.bisimplex.firebooru.backup;
public class BackupCVSTask {
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private ref.WeakReference listener;
    private final com.bisimplex.firebooru.backup.BackupCVSOptions options;

    public static synthetic void $r8$lambda$NV4GVgkYK0hJEWaZ5OOBJAc7B2s(com.bisimplex.firebooru.backup.BackupCVSTask p0, Exception p1)
    {
        p0.lambda$doWork$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$fhOOWHSAAOp-kODmwYmeWeqFN_Q(com.bisimplex.firebooru.backup.BackupCVSTask p0)
    {
        p0.doWork();
        return;
    }

    public static synthetic void $r8$lambda$qesUZM7Q1NOewl6Xu2AyuOpqZKc(com.bisimplex.firebooru.backup.BackupCVSTask p0, android.net.Uri p1)
    {
        p0.lambda$doWork$0(p1);
        return;
    }

    public BackupCVSTask(com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener p2, com.bisimplex.firebooru.backup.BackupCVSOptions p3)
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.setListener(p2);
        this.options = p3;
        return;
    }

    private void doWork()
    {
        Throwable v0_0 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        new java.util.Date();
        try {
            Throwable v0_5;
            Throwable v1_7 = this.options;
        } catch (Throwable v0_3) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_3);
            this.enqueueHandler.post(new com.bisimplex.firebooru.backup.BackupCVSTask$$ExternalSyntheticLambda2(this, v0_3));
            return;
        }
        if (v1_7 == null) {
            v0_5 = 0;
        } else {
            Throwable v1_2 = v1_7.getPosts();
            if (v1_2 == null) {
                v1_2 = v0_0.loadFavoritesByFilter(this.options.getQuery(), -1);
            }
            Throwable v0_4 = androidx.documentfile.provider.DocumentFile.fromTreeUri(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), this.options.getUri());
            if ((v0_4 == null) || (!v0_4.canWrite())) {
            } else {
                android.os.ParcelFileDescriptor v2_9 = new StringBuilder();
                if (!android.text.TextUtils.isEmpty(this.options.getFileNamePrefix())) {
                    v2_9.append(this.options.getFileNamePrefix());
                    v2_9.append(" ");
                }
                de.siegmar.fastcsv.writer.CsvWriter v3_8 = this.options.getQuery().getText();
                if ((android.text.TextUtils.isEmpty(v3_8)) || ("*".equalsIgnoreCase(v3_8))) {
                    v3_8 = "";
                }
                if (!android.text.TextUtils.isEmpty(v3_8)) {
                    v2_9.append("[");
                    v2_9.append(this.options.getQuery().getText());
                    v2_9.append("] ");
                }
                v2_9.append(java.util.UUID.randomUUID().toString());
                v2_9.append(".csv");
                Throwable v0_6 = v0_4.createFile("text/csv", v2_9.toString());
                if (v0_6 != null) {
                    android.os.ParcelFileDescriptor v2_12 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver();
                    v0_5 = v0_6.getUri();
                    android.os.ParcelFileDescriptor v2_13 = v2_12.openFileDescriptor(v0_5, "rwt");
                    java.io.FileWriter v4_4 = new java.io.FileWriter(v2_13.getFileDescriptor());
                    de.siegmar.fastcsv.writer.CsvWriter v3_22 = de.siegmar.fastcsv.writer.CsvWriter.builder().build(v4_4);
                    try {
                        Throwable v1_9 = v1_2.iterator();
                    } catch (Throwable v0_1) {
                        if (v3_22 != null) {
                            try {
                                v3_22.close();
                            } catch (Throwable v1_3) {
                                v0_1.addSuppressed(v1_3);
                            }
                        }
                        throw v0_1;
                    }
                    while (v1_9.hasNext()) {
                        boolean v5_2 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v1_9.next());
                        java.util.ArrayList v6_1 = new java.util.ArrayList(2);
                        if (this.options.isFileURL()) {
                            boolean v7_4 = v5_2.getFile().getUrl();
                            if (!android.text.TextUtils.isEmpty(v7_4)) {
                                v6_1.add(v7_4);
                            }
                        }
                        if (this.options.isPostURL()) {
                            boolean v7_7 = v5_2.getPostUrl();
                            if (!android.text.TextUtils.isEmpty(v7_7)) {
                                v6_1.add(v7_7);
                            }
                        }
                        if (this.options.isMD5()) {
                            boolean v5_3 = v5_2.getMd5();
                            if (!android.text.TextUtils.isEmpty(v5_3)) {
                                v6_1.add(v5_3);
                            }
                        }
                        if (!v6_1.isEmpty()) {
                            v3_22.writeRecord(v6_1);
                        } else {
                        }
                    }
                    if (v3_22 != null) {
                        v3_22.close();
                    }
                    v4_4.close();
                    v2_13.close();
                } else {
                    return;
                }
            }
        }
        this.enqueueHandler.post(new com.bisimplex.firebooru.backup.BackupCVSTask$$ExternalSyntheticLambda1(this, v0_5));
        return;
    }

    private synthetic void lambda$doWork$0(android.net.Uri p2)
    {
        com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener) this.listener.get()).workDone(p2);
        }
        return;
    }

    private synthetic void lambda$doWork$1(Exception p2)
    {
        com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener) this.listener.get()).workFiled(p2.getLocalizedMessage());
        }
        return;
    }

    public void setListener(com.bisimplex.firebooru.backup.BackupCVSTask$BackupCVSListener p2)
    {
        if (p2 == 0) {
            this.listener = 0;
            return;
        } else {
            this.listener = new ref.WeakReference(p2);
            return;
        }
    }

    public void start()
    {
        if (this.options != null) {
            this.executor.execute(new com.bisimplex.firebooru.backup.BackupCVSTask$$ExternalSyntheticLambda0(this));
            return;
        } else {
            return;
        }
    }
}
