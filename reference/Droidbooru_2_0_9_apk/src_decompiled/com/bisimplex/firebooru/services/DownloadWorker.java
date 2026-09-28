package com.bisimplex.firebooru.services;
public class DownloadWorker extends androidx.work.Worker {
    public static final String ANDROID_CHANNEL_ID = "com.bisimplex.firebooru.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "Anime boxes";
    public static final String DOWNLOAD_ENTRY_ID = "DownloadWorker_DOWNLOAD_ENTRY_ID";
    public static final String DOWNLOAD_STATUS_CHANGE = "DownloadWorker_WORK_STATUS_CHANGE";
    public static final String DOWNLOAD_WORK_ID = "DownloadWorker_DOWNLOAD_WORK_ID";
    static final int FINISHED_NOTIFICATION_ID = 1378;
    static final int NOTIFICATION_ID = 1372;
    private android.content.Context context;
    private android.app.NotificationManager mManager;
    private android.content.SharedPreferences preferences;

    public DownloadWorker(android.content.Context p2, androidx.work.WorkerParameters p3)
    {
        super(p2, p3);
        super.context = p2;
        super.preferences = p2.getSharedPreferences("UserConfiguration", 0);
        return;
    }

    public static void cancelAll(android.content.Context p1)
    {
        androidx.work.WorkManager.getInstance(p1).cancelUniqueWork("DownloadWorker_DOWNLOAD_WORK_ID");
        return;
    }

    private void changeStatus(com.bisimplex.firebooru.model.DownloadEntry p3, io.objectbox.Box p4, int p5, String p6)
    {
        p3.setStatus(p5);
        if (!android.text.TextUtils.isEmpty(p6)) {
            p3.setError_message(p6);
        }
        p4.put(p3);
        android.content.Intent v4_2 = new android.content.Intent("DownloadWorker_WORK_STATUS_CHANGE");
        v4_2.putExtra("DownloadWorker_DOWNLOAD_ENTRY_ID", p3.getId());
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(v4_2);
        return;
    }

    public static void checkIfMustLaunch(android.content.Context p5, io.objectbox.Box p6)
    {
        if (p6.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build().count() > 0) {
            com.bisimplex.firebooru.services.DownloadWorker.enqueueWork(p5);
        }
        return;
    }

    public static void checkIfMustLaunch(android.content.Context p5, boolean p6)
    {
        io.objectbox.Property v0_2 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        if ((p6 != null) && (!com.bisimplex.firebooru.services.DownloadWorker.isRunning(p5))) {
            java.util.List v6_4 = v0_2.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 1).build().find();
            java.util.Iterator v1_2 = v6_4.iterator();
            while (v1_2.hasNext()) {
                ((com.bisimplex.firebooru.model.DownloadEntry) v1_2.next()).setStatus(0);
            }
            v0_2.put(v6_4);
        }
        com.bisimplex.firebooru.services.DownloadWorker.clearFinished(v0_2);
        if (v0_2.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build().count() > 0) {
            com.bisimplex.firebooru.services.DownloadWorker.enqueueWork(p5);
        }
        return;
    }

    private static void clearFinished(io.objectbox.Box p3)
    {
        p3.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 2).or().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 4).build().remove();
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

    private static void enqueueWork(android.content.Context p3)
    {
        androidx.work.WorkManager.getInstance(p3).enqueueUniqueWork("DownloadWorker_DOWNLOAD_WORK_ID", androidx.work.ExistingWorkPolicy.KEEP, ((androidx.work.OneTimeWorkRequest) new androidx.work.OneTimeWorkRequest$Builder(com.bisimplex.firebooru.services.DownloadWorker).build()));
        return;
    }

    public static void enqueueWork(android.content.Context p7, java.util.List p8, com.bisimplex.firebooru.services.DownloadOptions p9, com.bisimplex.firebooru.network.SourceQuery p10)
    {
        if ((p8) && (!p8.isEmpty())) {
            io.objectbox.Box v0_2 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
            com.bisimplex.firebooru.services.DownloadWorker.clearFinished(v0_2);
            java.util.Date v1_1 = new java.util.Date();
            java.util.ArrayList v2_1 = new java.util.ArrayList();
            boolean v8_1 = p8.iterator();
            while (v8_1.hasNext()) {
                int v3_3 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v8_1.next());
                boolean v4 = v3_3.getEnforceOriginalImage();
                v3_3.setEnforceOriginalImage(p9.isDownloadOriginal());
                if ((!p9.isExcludeAnimated()) || (!v3_3.getVisibleVersion().isAnimated())) {
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
                    v5_5.setQuery(p10.getText());
                    v5_5.setDate_added(v1_1);
                    v5_5.setAvoid_duplicate(p9.isAvoidDuplicates());
                    v5_5.setExclude_animated(p9.isExcludeAnimated());
                    v5_5.setTarget_folder(p9.getTarget_folder());
                    v3_3.setEnforceOriginalImage(v4);
                    v5_5.setStatus(0);
                    v2_1.add(v5_5);
                }
            }
            if (!v2_1.isEmpty()) {
                v0_2.put(v2_1);
                com.bisimplex.firebooru.services.DownloadWorker.enqueueWork(p7);
            }
        }
        return;
    }

    private android.app.NotificationManager getManager()
    {
        if (this.mManager == null) {
            this.mManager = ((android.app.NotificationManager) this.context.getSystemService("notification"));
        }
        return this.mManager;
    }

    private androidx.documentfile.provider.DocumentFile getTargetFolder(String p3)
    {
        if (android.text.TextUtils.isEmpty(p3)) {
            p3 = this.preferences.getString("sdPath", "");
        }
        if (!android.text.TextUtils.isEmpty(p3)) {
            try {
                androidx.documentfile.provider.DocumentFile v3_1 = android.net.Uri.parse(p3);
            } catch (androidx.documentfile.provider.DocumentFile v3_3) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_3);
                return 0;
            }
            if (v3_1 != null) {
                return androidx.documentfile.provider.DocumentFile.fromTreeUri(this.context, v3_1);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static boolean isRunning(android.content.Context p3)
    {
        try {
            int v3_2 = ((java.util.List) androidx.work.WorkManager.getInstance(p3).getWorkInfosForUniqueWork("DownloadWorker_DOWNLOAD_WORK_ID").get()).iterator();
        } catch (int v3_3) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_3);
            return 0;
        }
        while (v3_2.hasNext()) {
            if (((androidx.work.WorkInfo) v3_2.next()).getState() == androidx.work.WorkInfo$State.RUNNING) {
                return 1;
            }
        }
        return 0;
    }

    private void postNotification(long p15, long p17, long p19, long p21)
    {
        android.app.Notification v0_5;
        int v7 = 1372;
        if (p15 != 0) {
            int v8_1 = (((p15 + p17) + p19) + p21);
            String v2_3 = this.context.getString(2131886594, new Object[] {Long.valueOf(p15), Long.valueOf(p21), Long.valueOf(p17), Long.valueOf(p19)}));
            int v8_2 = ((int) v8_1);
            v0_5 = new androidx.core.app.NotificationCompat$Builder(this.context, "com.bisimplex.firebooru.ANDROID").setSmallIcon(2131231039).setContentTitle(this.context.getString(2131886170)).setContentText(v2_3).setProgress(v8_2, (v8_2 - ((int) p15)), 0).setAutoCancel(1).setStyle(new androidx.core.app.NotificationCompat$BigTextStyle().bigText(v2_3));
        } else {
            android.app.Notification v0_7 = this.context.getString(2131886172);
            v0_5 = new androidx.core.app.NotificationCompat$Builder(this.context, "com.bisimplex.firebooru.ANDROID").setSmallIcon(2131231039).setContentTitle(this.context.getString(2131886170)).setContentText(v0_7).setAutoCancel(1).setStyle(new androidx.core.app.NotificationCompat$BigTextStyle().bigText(v0_7));
            this.getManager().cancel(1372);
            v7 = 1378;
        }
        this.getManager().notify(v7, v0_5.build());
        return;
    }

    private static void requestNotificationPermission(android.content.Context p0)
    {
        return;
    }

    public androidx.work.ListenableWorker$Result doWork()
    {
        void v1_0 = this;
        io.objectbox.Box v10 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        android.content.ContentResolver v11_1 = 0;
        com.bisimplex.firebooru.model.DownloadEntry v13 = v10.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 0).build();
        io.objectbox.query.Query v14 = v10.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 3).build();
        io.objectbox.query.Query v15 = v10.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 2).build();
        io.objectbox.query.Query v16 = v10.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 4).build();
        long v2_15 = this.context.getContentResolver();
        String v3_23 = 0;
        int v17 = 0;
        do {
            com.bisimplex.firebooru.model.DownloadEntry v18;
            android.content.ContentResolver v23;
            android.content.ContentResolver v11_0;
            int v22_1;
            int v4_25 = ((com.bisimplex.firebooru.model.DownloadEntry) v10.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, v11_1).order(com.bisimplex.firebooru.model.DownloadEntry_.id).build().findFirst());
            if (v4_25 != 0) {
                long v6_3 = 0;
                java.io.FileInputStream v7_10 = v2_15;
                int v8_21 = v3_23;
                try {
                    long v2_18 = v13.count();
                    int v9_1 = v4_25;
                    try {
                        android.content.ContentResolver v19 = v7_10;
                        try {
                            v22_1 = v8_21;
                            com.bisimplex.firebooru.model.DownloadEntry v21 = v9_1;
                            try {
                                v23 = v11_1;
                                v18 = v13;
                                v11_0 = v19;
                                int v12_0 = 0;
                                v13 = v21;
                                try {
                                    String v5_2;
                                    v1_0 = v1_0.postNotification(v2_18, v14.count(), v16.count(), v15.count()).changeStatus(v13, v10, 1, 0);
                                    long v2_1 = v1_0.getTargetFolder(v13.getTarget_folder());
                                    String v3_0 = v13.getFile_name();
                                    int v4_1 = com.bisimplex.firebooru.danbooru.DanbooruPostContentType.fromExtension(v13.getExtension());
                                } catch (String v0_1) {
                                    v6_3 = v12_0;
                                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
                                    v13.setStatus(3);
                                    long v2_12 = v0_1.getCause();
                                    if (!(v2_12 instanceof com.bumptech.glide.load.engine.GlideException)) {
                                        String v3_21 = 0;
                                        v13.setError_message(v0_1.getLocalizedMessage());
                                    } else {
                                        long v2_14 = ((com.bumptech.glide.load.engine.GlideException) v2_12).getRootCauses();
                                        if (v2_14.isEmpty()) {
                                            v3_21 = 0;
                                            v13.setError_message(v0_1.getLocalizedMessage());
                                        } else {
                                            v3_21 = 0;
                                            v13.setError_message(((Throwable) v2_14.get(0)).getLocalizedMessage());
                                        }
                                    }
                                    v1_0 = v1_0.deleteFailedIfNeeded(v11_0, v6_3, v12_0);
                                    v10.put(v13);
                                    v22_1 = v3_21;
                                    v1_0.postNotification(v18.count(), v14.count(), v16.count(), v15.count());
                                }
                                if (v4_1 == com.bisimplex.firebooru.danbooru.DanbooruPostContentType.WebM) {
                                    v5_2 = 1;
                                } else {
                                    if (v4_1 != com.bisimplex.firebooru.danbooru.DanbooruPostContentType.MP4) {
                                        v5_2 = v22_1;
                                    } else {
                                    }
                                }
                                long v6_0 = v13.getExtension();
                                if (android.text.TextUtils.isEmpty(v6_0)) {
                                    v6_0 = "*";
                                }
                                int v8_0;
                                int v4_4;
                                int v4_3 = com.bisimplex.firebooru.services.DownloadWorker$1.$SwitchMap$com$bisimplex$firebooru$danbooru$DanbooruPostContentType[v4_1.ordinal()];
                                if (v4_3 == 1) {
                                    v8_0 = 3;
                                    v4_4 = "video/mp4";
                                } else {
                                    if (v4_3 == 2) {
                                        v8_0 = 3;
                                        v4_4 = "video/webm";
                                    } else {
                                        v8_0 = 3;
                                        if (v4_3 == 3) {
                                            v4_4 = "image/gif";
                                        } else {
                                            v4_4 = String.format("image/%s", new Object[] {v6_0}));
                                        }
                                    }
                                }
                                try {
                                    long v2_2;
                                    int v4_9;
                                    if (v2_1 == 0) {
                                        long v2_5 = new android.content.ContentValues();
                                        v2_5.put("title", v3_0);
                                        v2_5.put("_display_name", v3_0);
                                        v2_5.put("mime_type", v4_4);
                                        v4_9 = v2_5;
                                        v2_2 = 0;
                                        long v6_4;
                                        if (v2_2 == 0) {
                                            int v4_10;
                                            if (v5_2 == null) {
                                                v4_10 = v11_0.insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v4_9);
                                            } else {
                                                v4_10 = v11_0.insert(android.provider.MediaStore$Video$Media.EXTERNAL_CONTENT_URI, v4_9);
                                            }
                                            try {
                                                if ((v13.isAvoid_duplicate()) && (v4_10 != 0)) {
                                                    int v8_6 = new String[1];
                                                    v8_6[v22_1] = "_display_name";
                                                    int v8_7 = v11_0.query(v4_10, v8_6, 0, 0);
                                                    if ((v8_7 != 0) && (v8_7.moveToFirst())) {
                                                        java.io.FileInputStream v7_5 = v8_7.getString(v8_7.getColumnIndex("_display_name"));
                                                        v8_7.close();
                                                        if ((!android.text.TextUtils.isEmpty(v7_5)) && ((!v3_0.equalsIgnoreCase(v7_5)) && (v5_2 == null))) {
                                                            if (v11_0.delete(v4_10, 0, 0) > 0) {
                                                                android.util.Log.e("SaveFile", "deleted register");
                                                            }
                                                            v1_0 = v1_0.changeStatus(v13, v10, 4, v12_0).deleteFailedIfNeeded(v11_0, v12_0, v4_10);
                                                        }
                                                    }
                                                }
                                                v6_4 = v4_10;
                                            } catch (String v0_1) {
                                                v6_3 = v2_2;
                                                v12_0 = v4_10;
                                            }
                                        } else {
                                            v6_4 = v2_2.getUri();
                                        }
                                        try {
                                            if (v6_4 != 0) {
                                                String v3_5 = v11_0.openOutputStream(v6_4);
                                                if (v3_5 != null) {
                                                    int v4_12 = com.bisimplex.firebooru.network.Utils.getInstance().urlForEntry(0, v13);
                                                    if (v4_12 != 0) {
                                                        int v4_14 = com.bumptech.glide.Glide.with(v1_0.getApplicationContext()).asFile().load(v4_12).submit();
                                                        if (!v1_0.isStopped()) {
                                                            String v5_8 = ((java.io.File) v4_14.get());
                                                            if (v5_8 == null) {
                                                                v1_0 = v1_0.changeStatus(v13, v10, 3, v1_0.context.getString(2131887227)).deleteFailedIfNeeded(v11_0, v2_2, v6_4);
                                                            } else {
                                                                if (v5_8.exists()) {
                                                                    java.io.FileInputStream v7_8 = new java.io.FileInputStream(v5_8);
                                                                    String v5_10 = new byte[1024];
                                                                    int v8_9 = v23;
                                                                    while(true) {
                                                                        String v0_12 = v7_8.read(v5_10);
                                                                        if (v0_12 <= null) {
                                                                            break;
                                                                        }
                                                                        v3_5.write(v5_10, 0, v0_12);
                                                                        v8_9 += ((long) v0_12);
                                                                    }
                                                                    long v25_0 = v8_9;
                                                                    v7_8.close();
                                                                    v3_5.close();
                                                                    if (v25_0 != v23) {
                                                                        v13.setStatus(2);
                                                                        v13.setDownload_date(new java.util.Date());
                                                                        v10.put(v13);
                                                                        v1_0 = v1_0.changeStatus(v13, v10, 2, 0);
                                                                        String v0_15 = v1_0.getApplicationContext();
                                                                        String v3_10 = new String[1];
                                                                        v3_10[0] = v6_4.getPath();
                                                                        android.media.MediaScannerConnection.scanFile(v0_15, v3_10, 0, 0);
                                                                        com.bumptech.glide.Glide.with(v1_0.getApplicationContext()).clear(v4_14);
                                                                    } else {
                                                                        v1_0 = v1_0.changeStatus(v13, v10, 3, v1_0.context.getString(2131886585, new Object[] {v13.getFile_url()}))).deleteFailedIfNeeded(v11_0, v2_2, v6_4);
                                                                    }
                                                                } else {
                                                                }
                                                            }
                                                            v1_0.postNotification(v18.count(), v14.count(), v16.count(), v15.count());
                                                            v22_1 = 0;
                                                        } else {
                                                            v1_0.changeStatus(v13, v10, v22_1, v1_0.context.getString(2131886438, new Object[] {v13.getFile_url()}))).deleteFailedIfNeeded(v11_0, v2_2, v6_4).postNotification(v18.count(), v14.count(), v16.count(), v15.count());
                                                            return androidx.work.ListenableWorker$Result.success();
                                                        }
                                                    } else {
                                                        v1_0 = v1_0.changeStatus(v13, v10, 3, v1_0.context.getString(2131886218, new Object[] {v13.getFile_url()}))).deleteFailedIfNeeded(v11_0, v2_2, v6_4);
                                                    }
                                                } else {
                                                    v1_0 = v1_0.changeStatus(v13, v10, 3, v1_0.context.getString(2131886216)).deleteFailedIfNeeded(v11_0, v2_2, v6_4);
                                                }
                                            } else {
                                                v1_0 = v1_0.changeStatus(v13, v10, 3, v1_0.context.getString(2131886215));
                                            }
                                        } catch (String v0_1) {
                                            v12_0 = v6_4;
                                        }
                                    } else {
                                        if (v2_1.canWrite()) {
                                            v2_2 = v2_1.createFile(v4_4, v3_0);
                                            if (v2_2 != 0) {
                                                if ((v3_0.equalsIgnoreCase(v2_2.getName())) || ((v5_2 != null) || (!v13.isAvoid_duplicate()))) {
                                                    v4_9 = 0;
                                                } else {
                                                    v2_2.delete();
                                                    v1_0 = v1_0.changeStatus(v13, v10, 4, 0);
                                                }
                                            } else {
                                                v1_0 = v1_0.changeStatus(v13, v10, v8_0, v1_0.context.getString(2131886215));
                                            }
                                        } else {
                                        }
                                    }
                                    v6_3 = v2_2;
                                } catch (String v0_1) {
                                }
                            } catch (String v0_1) {
                                v23 = v11_0;
                                v18 = v13;
                                v11_0 = v19;
                                v12_0 = 0;
                                v13 = v21;
                            }
                        } catch (String v0_1) {
                            v23 = v11_1;
                            v18 = v13;
                            v11_0 = v19;
                            v12_0 = 0;
                            v13 = v9_1;
                        }
                    } catch (String v0_1) {
                        v23 = v11_1;
                        v18 = v13;
                        v12_0 = 0;
                        v11_0 = v7_10;
                        v13 = v9_1;
                    }
                } catch (String v0_1) {
                    v23 = v11_1;
                    v18 = v13;
                    v13 = v4_25;
                    v12_0 = 0;
                    v11_0 = v7_10;
                } catch (String v0_0) {
                    v18 = v13;
                    this.postNotification(v18.count(), v14.count(), v16.count(), v15.count());
                    throw v0_0;
                } catch (String v0_0) {
                }
            } else {
                v17 = 1;
                v22_1 = v3_23;
                v23 = v11_1;
                v18 = v13;
                v11_0 = v2_15;
            }
            return androidx.work.ListenableWorker$Result.success();
        } while((v17 == 0) && (!this.isStopped()));
        v1_0 = this;
        v2_15 = v11_0;
        v13 = v18;
        v3_23 = v22_1;
        v11_1 = v23;
    }

    public void onStopped()
    {
        super.onStopped();
        this.getManager().cancel(1372);
        return;
    }
}
