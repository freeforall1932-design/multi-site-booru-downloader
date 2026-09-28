package com.bisimplex.firebooru.services;
public class DownloadService {
    public static final String ANDROID_CHANNEL_ID = "com.bisimplex.firebooru.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "Anime boxes";
    static final int FINISHED_NOTIFICATION_ID = 1378;
    static final int NOTIFICATION_ID = 1372;
    private static final com.bisimplex.firebooru.services.DownloadService ourInstance;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private ref.WeakReference listener;
    private android.app.NotificationManager mManager;
    private android.content.SharedPreferences preferences;
    private boolean running;
    private boolean stopped;

    public static synthetic void $r8$lambda$Gr_MfsBCsZmnCLda98ikZ-w-Df4(com.bisimplex.firebooru.services.DownloadService p0)
    {
        p0.lambda$doWork$0();
        return;
    }

    public static synthetic void $r8$lambda$Vwz7a9JPsroyHOXeww9XshbQe-0(com.bisimplex.firebooru.services.DownloadService p0, com.bisimplex.firebooru.model.DownloadEntry p1)
    {
        p0.lambda$changeStatus$2(p1);
        return;
    }

    public static synthetic void $r8$lambda$i_RyPGJaB-PsFEogBbBu7l5w_dQ(com.bisimplex.firebooru.services.DownloadService p0, android.content.Context p1)
    {
        p0.lambda$doWork$1(p1);
        return;
    }

    static DownloadService()
    {
        com.bisimplex.firebooru.services.DownloadService.ourInstance = new com.bisimplex.firebooru.services.DownloadService();
        return;
    }

    private DownloadService()
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        return;
    }

    private void changeStatus(com.bisimplex.firebooru.model.DownloadEntry p1, io.objectbox.Box p2, int p3, String p4)
    {
        p1.setStatus(p3);
        if (!android.text.TextUtils.isEmpty(p4)) {
            p1.setError_message(p4);
        }
        p2.put(p1);
        android.os.Handler v2_2 = this.listener;
        if ((v2_2 != null) && (v2_2.get() != null)) {
            this.enqueueHandler.post(new com.bisimplex.firebooru.services.DownloadService$$ExternalSyntheticLambda1(this, p1));
        }
        return;
    }

    private void clearFinished(io.objectbox.Box p4)
    {
        Throwable v4_3 = p4.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 2).or().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 4).build();
        try {
            v4_3.remove();
        } catch (Throwable v0_0) {
            if (v4_3 != null) {
                try {
                    v4_3.close();
                } catch (Throwable v4_4) {
                    v0_0.addSuppressed(v4_4);
                }
            }
            throw v0_0;
        }
        if (v4_3 != null) {
            v4_3.close();
        }
        return;
    }

    private void deleteFailedIfNeeded(android.content.ContentResolver p1, androidx.documentfile.provider.DocumentFile p2, android.net.Uri p3)
    {
        if (p2 != null) {
            try {
                p2.delete();
                return;
            } catch (Exception v1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(p1);
            }
        }
        return;
    }

    private void doWork(android.content.Context p3)
    {
        if ((!this.isRunning()) && (!this.isStopped())) {
            this.start();
            this.executor.execute(new com.bisimplex.firebooru.services.DownloadService$$ExternalSyntheticLambda0(this, p3));
        }
        return;
    }

    private void enqueueWork(android.content.Context p2)
    {
        if (!this.isRunning()) {
            this.doWork(p2);
            return;
        } else {
            return;
        }
    }

    public static com.bisimplex.firebooru.services.DownloadService getInstance()
    {
        return com.bisimplex.firebooru.services.DownloadService.ourInstance;
    }

    private android.app.NotificationManager getManager()
    {
        return this.mManager;
    }

    private androidx.documentfile.provider.DocumentFile getTargetFolder(android.content.Context p3, String p4)
    {
        if (android.text.TextUtils.isEmpty(p4)) {
            p4 = this.preferences.getString("sdPath", "");
        }
        if (!android.text.TextUtils.isEmpty(p4)) {
            try {
                com.bisimplex.firebooru.network.Utils v4_1 = android.net.Uri.parse(p4);
            } catch (androidx.documentfile.provider.DocumentFile v3_2) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_2);
                return 0;
            }
            if (v4_1 != null) {
                return androidx.documentfile.provider.DocumentFile.fromTreeUri(p3, v4_1);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private boolean isStopped()
    {
        return this.stopped;
    }

    private synthetic void lambda$changeStatus$2(com.bisimplex.firebooru.model.DownloadEntry p2)
    {
        com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener) this.listener.get()).changedEntryStatus(p2);
        }
        return;
    }

    private synthetic void lambda$doWork$0()
    {
        com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener) this.listener.get()).downloadsFinished();
        }
        return;
    }

    private synthetic void lambda$doWork$1(android.content.Context p27)
    {
        io.objectbox.Box v11 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        com.bisimplex.firebooru.services.DownloadService v12_1 = 0;
        com.bisimplex.firebooru.model.DownloadEntry v14 = v11.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build();
        io.objectbox.query.Query v15 = v11.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 3).build();
        io.objectbox.query.Query v16 = v11.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 2).build();
        io.objectbox.query.Query v17 = v11.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 4).build();
        void v1_16 = p27.getContentResolver();
        android.content.Context v2_12 = 0;
        int v18 = 0;
        do {
            android.content.Context v3_26 = v11.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, v12_1).order(com.bisimplex.firebooru.model.DownloadEntry_.id).build();
            int v4_14 = ((com.bisimplex.firebooru.model.DownloadEntry) v3_26.findFirst());
            if (v3_26 != null) {
                v3_26.close();
            }
            void v22;
            void v1_5;
            int v13_3;
            com.bisimplex.firebooru.model.DownloadEntry v20_0;
            com.bisimplex.firebooru.services.DownloadService v12_0;
            if (v4_14 != 0) {
                com.bumptech.glide.request.FutureTarget v6_7 = v4_14;
                try {
                    android.content.Context v3_28 = v14.count();
                    java.io.FileInputStream v8_9 = v6_7;
                    try {
                        int v10_2 = v8_9;
                        try {
                            try {
                                v20_0 = v14;
                                v14 = v10_2;
                                int v19_1 = v2_12;
                                v22 = v12_1;
                                int v13_0 = 0;
                                android.content.Context v2_0 = p27;
                                v12_0 = v1_16;
                                try {
                                    com.bumptech.glide.request.FutureTarget v6_2;
                                    void v1_1 = this.postNotification(p27, v3_28, v15.count(), v17.count(), v16.count()).changeStatus(v14, v11, 1, 0);
                                    android.content.Context v3_1 = v1_1.getTargetFolder(p27, v14.getTarget_folder());
                                    int v4_2 = v14.getFile_name();
                                    long v5_1 = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromExtension(v14.getExtension());
                                    try {
                                        if ((v5_1 != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM) && (v5_1 != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4)) {
                                            v6_2 = v19_1;
                                        } else {
                                            v6_2 = 1;
                                        }
                                        long v7_0 = v14.getExtension();
                                        if (android.text.TextUtils.isEmpty(v7_0)) {
                                            v7_0 = "*";
                                        }
                                        long v9_0;
                                        long v5_5;
                                        long v5_4 = com.bisimplex.firebooru.services.DownloadService$1.$SwitchMap$com$bisimplex$firebooru$danbooru$DanbooruPostContentType[v5_1.ordinal()];
                                        try {
                                            if (v5_4 == 1) {
                                                v9_0 = 3;
                                                v5_5 = "video/mp4";
                                            } else {
                                                if (v5_4 == 2) {
                                                    v9_0 = 3;
                                                    v5_5 = "video/webm";
                                                } else {
                                                    v9_0 = 3;
                                                    if (v5_4 == 3) {
                                                        v5_5 = "image/gif";
                                                    } else {
                                                        v5_5 = String.format("image/%s", new Object[] {v7_0}));
                                                    }
                                                }
                                            }
                                            try {
                                                android.content.Context v2_6;
                                                long v5_2;
                                                if (v3_1 == null) {
                                                    v2_6 = new android.content.ContentValues();
                                                    v2_6.put("title", v4_2);
                                                    v2_6.put("_display_name", v4_2);
                                                    v2_6.put("mime_type", v5_5);
                                                    v5_2 = 0;
                                                    try {
                                                        android.content.Context v2_1;
                                                        if (v5_2 == 0) {
                                                            if (v6_2 == null) {
                                                                try {
                                                                    v2_1 = v12_0.insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v2_6);
                                                                } catch (com.bumptech.glide.RequestManager v0_3) {
                                                                    v2_1 = 0;
                                                                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_3);
                                                                    v14.setStatus(v9_0);
                                                                    android.content.Context v3_19 = v0_3.getCause();
                                                                    if (!(v3_19 instanceof com.bumptech.glide.load.engine.GlideException)) {
                                                                        v13_3 = 0;
                                                                        v14.setError_message(v0_3.getLocalizedMessage());
                                                                    } else {
                                                                        android.content.Context v3_21 = ((com.bumptech.glide.load.engine.GlideException) v3_19).getRootCauses();
                                                                        if (v3_21.isEmpty()) {
                                                                            v13_3 = 0;
                                                                            v14.setError_message(v0_3.getLocalizedMessage());
                                                                        } else {
                                                                            v13_3 = 0;
                                                                            v14.setError_message(((Throwable) v3_21.get(0)).getLocalizedMessage());
                                                                        }
                                                                    }
                                                                    void v1_21 = v1_1.deleteFailedIfNeeded(v12_0, v5_2, v2_1);
                                                                    v11.put(v14);
                                                                    v1_5 = v1_21.postNotification(p27, v20_0.count(), v15.count(), v17.count(), v16.count());
                                                                }
                                                            } else {
                                                                v2_1 = v12_0.insert(android.provider.MediaStore$Video$Media.EXTERNAL_CONTENT_URI, v2_6);
                                                            }
                                                            if ((!v14.isAvoid_duplicate()) || (v2_1 == null)) {
                                                                if (v2_1 != null) {
                                                                    int v4_4 = v12_0.openOutputStream(v2_1);
                                                                    if (v4_4 != 0) {
                                                                        com.bumptech.glide.request.FutureTarget v6_4 = com.bisimplex.firebooru.network.Utils.getInstance().urlForEntry(0, v14);
                                                                        if (v6_4 != null) {
                                                                            com.bumptech.glide.request.FutureTarget v6_6 = com.bumptech.glide.Glide.with(p27).asFile().load(v6_4).submit();
                                                                            try {
                                                                                if (!v1_1.isStopped()) {
                                                                                    long v7_8 = ((java.io.File) v6_6.get());
                                                                                    if (v7_8 == 0) {
                                                                                        v1_5 = this.changeStatus(v14, v11, v9_0, p27.getString(2131887227)).deleteFailedIfNeeded(v12_0, v5_2, v2_1).postNotification(p27, v20_0.count(), v15.count(), v17.count(), v16.count());
                                                                                    } else {
                                                                                        if (v7_8.exists()) {
                                                                                            java.io.FileInputStream v8_8 = new java.io.FileInputStream(v7_8);
                                                                                            long v7_10 = new byte[1024];
                                                                                            long v24_2 = v22;
                                                                                            while(true) {
                                                                                                int v10_1 = v8_8.read(v7_10);
                                                                                                if (v10_1 <= 0) {
                                                                                                    break;
                                                                                                }
                                                                                                v4_4.write(v7_10, 0, v10_1);
                                                                                                v24_2 += ((long) v10_1);
                                                                                                int v13 = 0;
                                                                                            }
                                                                                            v8_8.close();
                                                                                            v4_4.close();
                                                                                            if (v24_2 != v22) {
                                                                                                v14.setStatus(2);
                                                                                                v14.setDownload_date(new java.util.Date());
                                                                                                v11.put(v14);
                                                                                                v1_1 = this.changeStatus(v14, v11, 2, 0);
                                                                                                com.bumptech.glide.RequestManager v0_14 = new String[1];
                                                                                                v0_14[0] = v2_1.getPath();
                                                                                                android.media.MediaScannerConnection.scanFile(p27, v0_14, 0, 0);
                                                                                                com.bumptech.glide.Glide.with(p27).clear(v6_6);
                                                                                            } else {
                                                                                                v1_1 = this.changeStatus(v14, v11, v9_0, p27.getString(2131886585, new Object[] {v14.getFile_url()}))).deleteFailedIfNeeded(v12_0, v5_2, v2_1);
                                                                                            }
                                                                                            v1_5 = v1_1.postNotification(p27, v20_0.count(), v15.count(), v17.count(), v16.count());
                                                                                        } else {
                                                                                        }
                                                                                    }
                                                                                    v13_3 = 0;
                                                                                } else {
                                                                                    void v1_18 = v1_1.changeStatus(v14, v11, v19_1, p27.getString(2131886438, new Object[] {v14.getFile_url()}))).deleteFailedIfNeeded(v12_0, v5_2, v2_1);
                                                                                    android.content.Context v3_16 = v20_0.count();
                                                                                    v5_2 = v15.count();
                                                                                    long v7_14 = v17.count();
                                                                                    v9_0 = v16.count();
                                                                                    v2_1 = p27;
                                                                                    v1_18.postNotification(p27, v3_16, v5_2, v7_14, v9_0);
                                                                                    v1_5 = this;
                                                                                    void v1_22 = v1_5.resetFlags();
                                                                                    com.bumptech.glide.RequestManager v0_41 = v1_22.listener;
                                                                                    if ((v0_41 != null) && (v0_41.get() != null)) {
                                                                                        v1_22.enqueueHandler.post(new com.bisimplex.firebooru.services.DownloadService$$ExternalSyntheticLambda2(v1_22));
                                                                                    }
                                                                                    return;
                                                                                }
                                                                            } catch (com.bumptech.glide.RequestManager v0_3) {
                                                                                v1_1 = this;
                                                                            } catch (com.bumptech.glide.RequestManager v0_2) {
                                                                                v1_1 = this;
                                                                                v1_1.postNotification(p27, v20_0.count(), v15.count(), v17.count(), v16.count());
                                                                                throw v0_2;
                                                                            }
                                                                        } else {
                                                                            v1_1 = v1_1.changeStatus(v14, v11, v9_0, p27.getString(2131886218, new Object[] {v14.getFile_url()}))).deleteFailedIfNeeded(v12_0, v5_2, v2_1);
                                                                        }
                                                                    } else {
                                                                        v1_1 = v1_1.changeStatus(v14, v11, v9_0, p27.getString(2131886216)).deleteFailedIfNeeded(v12_0, v5_2, v2_1);
                                                                    }
                                                                } else {
                                                                    v1_1 = v1_1.changeStatus(v14, v11, v9_0, p27.getString(2131886215));
                                                                }
                                                            } else {
                                                                try {
                                                                    android.content.Context v3_8 = new String[1];
                                                                    v3_8[v19_1] = "_display_name";
                                                                    android.content.Context v3_9 = v12_0.query(v2_1, v3_8, 0, 0);
                                                                } catch (com.bumptech.glide.RequestManager v0_3) {
                                                                } catch (com.bumptech.glide.RequestManager v0_2) {
                                                                }
                                                                if ((v3_9 == null) || (!v3_9.moveToFirst())) {
                                                                } else {
                                                                    java.io.FileInputStream v8_5 = v3_9.getString(v3_9.getColumnIndex("_display_name"));
                                                                    v3_9.close();
                                                                    if ((android.text.TextUtils.isEmpty(v8_5)) || ((v4_2.equalsIgnoreCase(v8_5)) || (v6_2 != null))) {
                                                                    } else {
                                                                        if (v12_0.delete(v2_1, 0, 0) > 0) {
                                                                            android.util.Log.e("SaveFile", "deleted register");
                                                                        }
                                                                        v1_1 = v1_1.changeStatus(v14, v11, 4, v13_0).deleteFailedIfNeeded(v12_0, v13_0, v2_1);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            v2_1 = v5_2.getUri();
                                                        }
                                                        v1_5 = v1_1.postNotification(p27, v20_0.count(), v15.count(), v17.count(), v16.count());
                                                    } catch (com.bumptech.glide.RequestManager v0_3) {
                                                    }
                                                } else {
                                                    if (v3_1.canWrite()) {
                                                        long v7_3;
                                                        android.content.Context v3_2;
                                                        v5_2 = v3_1.createFile(v5_5, v4_2);
                                                        if (v5_2 != 0) {
                                                            if ((v4_2.equalsIgnoreCase(v5_2.getName())) || ((v6_2 != null) || (!v14.isAvoid_duplicate()))) {
                                                                v2_6 = 0;
                                                            } else {
                                                                v5_2.delete();
                                                                v1_1 = v1_1.changeStatus(v14, v11, 4, 0);
                                                                v3_2 = v20_0.count();
                                                                v5_2 = v15.count();
                                                                v7_3 = v17.count();
                                                                v9_0 = v16.count();
                                                                v2_0 = p27;
                                                            }
                                                        } else {
                                                            v1_1 = v1_1.changeStatus(v14, v11, v9_0, p27.getString(2131886215));
                                                            v3_2 = v20_0.count();
                                                            v5_2 = v15.count();
                                                            v7_3 = v17.count();
                                                            v9_0 = v16.count();
                                                        }
                                                        v1_5 = v1_1.postNotification(v2_0, v3_2, v5_2, v7_3, v9_0);
                                                    } else {
                                                    }
                                                }
                                                v13_3 = v19_1;
                                            } catch (com.bumptech.glide.RequestManager v0_3) {
                                                v2_1 = 0;
                                            }
                                        } catch (com.bumptech.glide.RequestManager v0_3) {
                                            int v4_1 = 0;
                                            v2_1 = v4_1;
                                            v5_2 = v2_1;
                                        } catch (com.bumptech.glide.RequestManager v0_3) {
                                            v2_1 = 0;
                                        }
                                    } catch (com.bumptech.glide.RequestManager v0_3) {
                                        v2_1 = 0;
                                        v5_2 = 0;
                                        v9_0 = 3;
                                    }
                                } catch (com.bumptech.glide.RequestManager v0_3) {
                                    v4_1 = 0;
                                    v9_0 = 3;
                                }
                            } catch (com.bumptech.glide.RequestManager v0_3) {
                                int v4_0 = v20_0;
                                v20_0 = v14;
                                v14 = v4_0;
                                v22 = v12_0;
                                v9_0 = v19_1;
                                v4_1 = 0;
                                v12_0 = this;
                                v1_1 = this;
                            }
                        } catch (com.bumptech.glide.RequestManager v0_3) {
                            v4_1 = 0;
                            v22 = v12_1;
                            v20_0 = v14;
                            v9_0 = 3;
                            v12_0 = v1_16;
                            v14 = v10_2;
                        }
                    } catch (com.bumptech.glide.RequestManager v0_3) {
                        v4_1 = 0;
                        v22 = v12_1;
                        v20_0 = v14;
                        v12_0 = v1_16;
                        v9_0 = 3;
                        v14 = v8_9;
                    }
                } catch (com.bumptech.glide.RequestManager v0_3) {
                    v4_1 = 0;
                    v9_0 = 3;
                    v22 = v12_1;
                    v20_0 = v14;
                    v12_0 = v1_16;
                    v14 = v6_7;
                } catch (com.bumptech.glide.RequestManager v0_2) {
                    v1_1 = this;
                    v20_0 = v14;
                } catch (com.bumptech.glide.RequestManager v0_2) {
                }
            } else {
                v18 = 1;
                v22 = v12_1;
                v20_0 = v14;
                v12_0 = v1_16;
                v13_3 = v2_12;
                v1_5 = this;
            }
        } while((v18 == 0) && (!v1_5.isStopped()));
        v1_16 = v12_0;
        v2_12 = v13_3;
        v14 = v20_0;
        v12_1 = v22;
    }

    private void postNotification(android.content.Context p17, long p18, long p20, long p22, long p24)
    {
        android.app.NotificationManager v3 = this.getManager();
        if (v3 != null) {
            android.app.Notification v0_8;
            int v10 = 1372;
            if (p18 != 0) {
                int v11_2 = (((p18 + p20) + p22) + p24);
                String v4_2 = p17.getString(2131886594, new Object[] {Long.valueOf(p18), Long.valueOf(p24), Long.valueOf(p20), Long.valueOf(p22)}));
                int v11_3 = ((int) v11_2);
                v0_8 = new androidx.core.app.NotificationCompat$Builder(p17, "com.bisimplex.firebooru.ANDROID").setSmallIcon(2131231039).setContentTitle(p17.getString(2131886170)).setContentText(v4_2).setProgress(v11_3, (v11_3 - ((int) p18)), 0).setLocalOnly(1).setAutoCancel(1).setCategory("progress").setStyle(new androidx.core.app.NotificationCompat$BigTextStyle().bigText(v4_2));
            } else {
                androidx.core.app.NotificationCompat$BigTextStyle v1_7 = p17.getString(2131886172);
                v0_8 = new androidx.core.app.NotificationCompat$Builder(p17, "com.bisimplex.firebooru.ANDROID").setSmallIcon(2131231039).setContentTitle(p17.getString(2131886170)).setContentText(v1_7).setLocalOnly(int v9).setAutoCancel(1).setCategory("progress").setStyle(new androidx.core.app.NotificationCompat$BigTextStyle().bigText(v1_7));
                v3.cancel(1372);
                v10 = 1378;
            }
            v3.notify(v10, v0_8.build());
            return;
        } else {
            return;
        }
    }

    private declared_synchronized void resetFlags()
    {
        this.stopped = 0;
        this.running = 0;
        return;
    }

    private declared_synchronized void start()
    {
        this.running = 1;
        this.stopped = 0;
        return;
    }

    private declared_synchronized void stop()
    {
        this.stopped = 1;
        return;
    }

    public void cancelAll()
    {
        if (this.isRunning()) {
            this.stop();
        }
        return;
    }

    public void checkIfMustLaunch(android.content.Context p6, io.objectbox.Box p7)
    {
        this.init(p6);
        Throwable v7_4 = p7.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build();
        try {
            if (v7_4.count() > 0) {
                this.enqueueWork(p6);
            }
        } catch (Throwable v6_1) {
            if (v7_4 != null) {
                try {
                    v7_4.close();
                } catch (Throwable v7_1) {
                    v6_1.addSuppressed(v7_1);
                }
            }
            throw v6_1;
        }
        if (v7_4 != null) {
            v7_4.close();
        }
        return;
    }

    public void checkIfMustLaunch(android.content.Context p6, boolean p7)
    {
        this.init(p6);
        long v0_3 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        if ((p7 != null) && (!this.isRunning())) {
            Throwable v7_3 = v0_3.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 1).build();
            try {
                java.util.List v1_1 = v7_3.find();
                java.util.Iterator v2_1 = v1_1.iterator();
            } catch (Throwable v6_2) {
                if (v7_3 != null) {
                    try {
                        v7_3.close();
                    } catch (Throwable v7_8) {
                        v6_2.addSuppressed(v7_8);
                    }
                }
                throw v6_2;
            }
            while (v2_1.hasNext()) {
                ((com.bisimplex.firebooru.model.DownloadEntry) v2_1.next()).setStatus(0);
            }
            v0_3.put(v1_1);
            if (v7_3 != null) {
                v7_3.close();
            }
        }
        this.clearFinished(v0_3);
        Throwable v7_6 = v0_3.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build();
        try {
            if (v7_6.count() > 0) {
                this.enqueueWork(p6);
            }
        } catch (Throwable v6_1) {
            if (v7_6 != null) {
                try {
                    v7_6.close();
                } catch (Throwable v7_7) {
                    v6_1.addSuppressed(v7_7);
                }
            }
            throw v6_1;
        }
        if (v7_6 != null) {
            v7_6.close();
        }
        return;
    }

    public void enqueueWork(android.content.Context p8, java.util.List p9, com.bisimplex.firebooru.services.DownloadOptions p10, com.bisimplex.firebooru.network.SourceQuery p11)
    {
        this.init(p8);
        if ((p9) && (!p9.isEmpty())) {
            io.objectbox.Box v0_2 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
            this.clearFinished(v0_2);
            java.util.Date v1_1 = new java.util.Date();
            java.util.ArrayList v2_1 = new java.util.ArrayList();
            boolean v9_1 = p9.iterator();
            while (v9_1.hasNext()) {
                int v3_3 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v9_1.next());
                boolean v4 = v3_3.getEnforceOriginalImage();
                v3_3.setEnforceOriginalImage(p10.isDownloadOriginal());
                if ((!p10.isExcludeAnimated()) || (!v3_3.getVisibleVersion().isAnimated())) {
                    com.bisimplex.firebooru.model.DownloadEntry v5_5 = new com.bisimplex.firebooru.model.DownloadEntry();
                    v5_5.setFile_url(v3_3.getVisibleVersion().getUrl());
                    v5_5.setPreview_url(v3_3.getPreview().getUrl());
                    v5_5.setSample_url(v3_3.getSample().getUrl());
                    v5_5.setSource(v3_3.getSource());
                    v5_5.setRating(v3_3.getRating());
                    v5_5.setMd5(v3_3.getMd5());
                    v5_5.setPost_id(v3_3.getPostId());
                    v5_5.setPost_url(v3_3.getPostUrl());
                    v5_5.setTags(v3_3.getTags());
                    v5_5.setTag_artist(v3_3.getTag_artist());
                    v5_5.setTag_character(v3_3.getTag_character());
                    v5_5.setTag_copyright(v3_3.getTag_copyright());
                    v5_5.setTag_general(v3_3.getTag_general());
                    v5_5.setFile_name(v3_3.generateDownloadFileName());
                    v5_5.setExtension(v3_3.getVisibleVersion().getExtension());
                    v5_5.setQuery(p11.getText());
                    v5_5.setDate_added(v1_1);
                    v5_5.setAvoid_duplicate(p10.isAvoidDuplicates());
                    v5_5.setExclude_animated(p10.isExcludeAnimated());
                    v5_5.setTarget_folder(p10.getTarget_folder());
                    v3_3.setEnforceOriginalImage(v4);
                    v5_5.setStatus(0);
                    v2_1.add(v5_5);
                }
            }
            if (!v2_1.isEmpty()) {
                v0_2.put(v2_1);
                this.enqueueWork(p8);
            }
        }
        return;
    }

    public void init(android.content.Context p4)
    {
        if (this.preferences == null) {
            this.preferences = p4.getApplicationContext().getSharedPreferences("UserConfiguration", 0);
            if (this.mManager == null) {
                this.mManager = ((android.app.NotificationManager) p4.getSystemService("notification"));
            }
        }
        return;
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public void pauseIfRunning()
    {
        return;
    }

    public void setListener(com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener p2)
    {
        if (p2 != 0) {
            this.listener = new ref.WeakReference(p2);
            return;
        } else {
            this.listener = 0;
            return;
        }
    }
}
