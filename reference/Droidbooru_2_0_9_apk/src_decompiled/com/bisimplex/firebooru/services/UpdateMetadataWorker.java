package com.bisimplex.firebooru.services;
public class UpdateMetadataWorker extends androidx.work.Worker {
    static final int FINISHED_NOTIFICATION_ID = 1048;
    static final int NOTIFICATION_ID = 1047;
    static final String UPDATE_ID = "UpdateMetadataWorker_UPDATE_ID";
    private static final int small_wait_time = 1000;
    private static final int wait_time = 4000;
    private final android.content.Context context;
    private final boolean downloadOriginal;
    private android.app.NotificationManager mManager;
    final io.objectbox.Box updateEntryBox;

    public UpdateMetadataWorker(android.content.Context p2, androidx.work.WorkerParameters p3)
    {
        super(p2, p3);
        super.context = p2;
        super.downloadOriginal = p2.getSharedPreferences("UserConfiguration", 0).getBoolean("downloadOriginalImage", 0);
        super.updateEntryBox = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.UpdateEntry);
        return;
    }

    public static void cancelAll(android.content.Context p1)
    {
        androidx.work.WorkManager.getInstance(p1).cancelUniqueWork("UpdateMetadataWorker_UPDATE_ID");
        Throwable v1_3 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.UpdateEntry).query().build();
        try {
            v1_3.remove();
        } catch (Throwable v0_0) {
            if (v1_3 != null) {
                try {
                    v1_3.close();
                } catch (Throwable v1_4) {
                    v0_0.addSuppressed(v1_4);
                }
            }
            throw v0_0;
        }
        if (v1_3 != null) {
            v1_3.close();
        }
        return;
    }

    private boolean checkFileExistAtURL(String p3, com.bisimplex.firebooru.danbooru.BooruProvider p4)
    {
        Throwable v3_5 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().newCall(new okhttp3.Request$Builder().url(p3).header("User-Agent", p4.getUserAgent()).head().build()).execute();
        try {
            Throwable v4_1 = v3_5.isSuccessful();
        } catch (Throwable v4_2) {
            if (v3_5 != null) {
                try {
                    v3_5.close();
                } catch (Throwable v3_6) {
                    v4_2.addSuppressed(v3_6);
                }
            }
            throw v4_2;
        }
        if (v3_5 != null) {
            v3_5.close();
        }
        return v4_1;
    }

    private String fixGelbooruURL(String p4)
    {
        String v0_1;
        if (!p4.contains("simg")) {
            v0_1 = p4;
        } else {
            v0_1 = p4.replace("/simg", "/img");
        }
        if (v0_1.contains("img3")) {
            v0_1 = v0_1.replace("img3", "img4");
        }
        if (v0_1.contains("/thumbs.gelbooru.com/")) {
            v0_1 = v0_1.replace("/thumbs.gelbooru.com/", "/img3.gelbooru.com/thumbnails/");
        }
        if (!v0_1.equalsIgnoreCase(p4)) {
            return v0_1;
        } else {
            return "";
        }
    }

    private android.app.NotificationManager getManager()
    {
        if (this.mManager == null) {
            this.mManager = ((android.app.NotificationManager) this.context.getSystemService("notification"));
        }
        return this.mManager;
    }

    public static boolean isRunning(android.content.Context p2)
    {
        try {
            int v2_7 = ((java.util.List) androidx.work.WorkManager.getInstance(p2).getWorkInfosForUniqueWork("UpdateMetadataWorker_UPDATE_ID").get());
        } catch (int v2_4) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_4);
            return 0;
        }
        if (v2_7 != 0) {
            int v2_2 = v2_7.iterator();
            while (v2_2.hasNext()) {
                if (!((androidx.work.WorkInfo) v2_2.next()).getState().isFinished()) {
                    return 1;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    private void postNotification(boolean p5, String p6)
    {
        android.app.NotificationManager v0 = this.getManager();
        androidx.core.app.NotificationCompat$Builder v6_2 = new androidx.core.app.NotificationCompat$Builder(this.context, "com.bisimplex.firebooru.ANDROID").setSmallIcon(2131231039).setContentTitle(this.context.getString(2131887073)).setContentText(p6).setAutoCancel(1).setStyle(new androidx.core.app.NotificationCompat$BigTextStyle().bigText(p6));
        int v1_4 = 1047;
        if (p5 == null) {
            v6_2 = v6_2.setProgress(0, 0, 1);
        } else {
            v0.cancel(1047);
            v1_4 = 1048;
        }
        v0.notify(v1_4, v6_2.build());
        return;
    }

    private com.bisimplex.firebooru.services.ReloadStatusType reloadIt(com.bisimplex.firebooru.model.Favorite p4)
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstanceByUrl(p4.posturl);
        if (v0_1 != null) {
            com.bisimplex.firebooru.danbooru.ServerItemType v1_1 = v0_1.getServerDescription().getType();
            if ((v1_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) && ((v1_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) && ((v1_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && ((v1_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && (v1_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie))))) {
                return com.bisimplex.firebooru.services.ReloadStatusType.ServerNotSupported;
            } else {
                okhttp3.OkHttpClient v2_5;
                if (!this.downloadOriginal) {
                    v2_5 = p4.sample_url;
                } else {
                    v2_5 = p4.file_url;
                }
                if (!android.text.TextUtils.isEmpty(v2_5)) {
                    return this.reloadMetadata(p4, v0_1, v1_1, com.bisimplex.firebooru.network.HttpClient.getOkHttpClient());
                } else {
                    return com.bisimplex.firebooru.services.ReloadStatusType.URLMalformed;
                }
            }
        } else {
            return com.bisimplex.firebooru.services.ReloadStatusType.ServerNotFound;
        }
    }

    private com.bisimplex.firebooru.services.ReloadStatusType reloadMetadata(com.bisimplex.firebooru.model.Favorite p7, com.bisimplex.firebooru.danbooru.BooruProvider p8, com.bisimplex.firebooru.danbooru.ServerItemType p9, okhttp3.OkHttpClient p10)
    {
        okhttp3.Response v9_4;
        if (p9 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
            v9_4 = "id:%s";
        } else {
            v9_4 = "id=%s";
        }
        com.bisimplex.firebooru.network.SourceQuery v3_0 = new com.bisimplex.firebooru.network.SourceQuery(String.format(java.util.Locale.US, v9_4, new Object[] {p7.postid})));
        okhttp3.Response v9_3 = p8.generateRequestUrl(v3_0, p8.getInitialPageNumber());
        if (v9_3 != null) {
            okhttp3.Response v9_10 = p10.newCall(new okhttp3.Request$Builder().url(v9_3.toString()).header("User-Agent", p8.getUserAgent()).build()).execute();
            try {
                if (!v9_10.isSuccessful()) {
                    if (v9_10.code() != 403) {
                        Long v7_11 = v9_10.body();
                        com.bisimplex.firebooru.danbooru.DatabaseHelper v8_19 = "Empty";
                        if (v7_11 != null) {
                            Long v7_12 = v7_11.string();
                            if (!android.text.TextUtils.isEmpty(v7_12)) {
                                v8_19 = v7_12;
                            } else {
                            }
                        }
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format(java.util.Locale.US, "UpdateMetadataWorker Error: %d, Body: %s", new Object[] {Integer.valueOf(v9_10.code()), v8_19})));
                        if (v9_10 != null) {
                            v9_10.close();
                        }
                        return com.bisimplex.firebooru.services.ReloadStatusType.Error;
                    } else {
                        if (v9_10 != null) {
                            v9_10.close();
                        }
                        return com.bisimplex.firebooru.services.ReloadStatusType.Suppend;
                    }
                } else {
                    com.bisimplex.firebooru.danbooru.DanbooruPost v10_9;
                    com.bisimplex.firebooru.danbooru.DanbooruPost v10_8 = v9_10.body();
                    if (v10_8 == null) {
                        v10_9 = 0;
                    } else {
                        v10_9 = v10_8.string();
                    }
                    Integer v1_4 = v10_9;
                    if (android.text.TextUtils.isEmpty(v1_4)) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logError("UpdateMetadataWorker Error: 200, Body: Empty");
                        if (v9_10 != null) {
                            v9_10.close();
                        }
                        return com.bisimplex.firebooru.services.ReloadStatusType.NotFound;
                    } else {
                        String v0_14 = new com.bisimplex.firebooru.network.ParserParams(v1_4, p8.getInitialPageNumber(), v3_0, p8, com.bisimplex.firebooru.network.SourceType.Post);
                        v0_14.setDisableVisibilityChecks(1);
                        com.bisimplex.firebooru.danbooru.DanbooruPost v10_1 = ((com.bisimplex.firebooru.network.ParserPosts) com.bisimplex.firebooru.network.Parser.fromProvider(v0_14));
                        if (v10_1 == null) {
                            if (v9_10 != null) {
                                v9_10.close();
                            }
                            return com.bisimplex.firebooru.services.ReloadStatusType.NotFound;
                        } else {
                            v10_1.parse();
                            if (!v10_1.getData().isEmpty()) {
                                com.bisimplex.firebooru.danbooru.DanbooruPost v10_4 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v10_1.getData().get(0));
                                v10_4.setFavorite(1);
                                if ((v10_4.getPostId() == null) || (!v10_4.getPostId().equalsIgnoreCase(p7.postid))) {
                                    if ((v10_4.getPreview() != null) || (v10_4.getFile() == null)) {
                                        if (v9_10 != null) {
                                            v9_10.close();
                                        }
                                        return com.bisimplex.firebooru.services.ReloadStatusType.ServerNotSupported;
                                    } else {
                                        p7.file_url = v10_4.getFile().getUrl();
                                        p7.sample_url = v10_4.getFile().getUrl();
                                        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(p7);
                                        if (v9_10 != null) {
                                            v9_10.close();
                                        }
                                        return com.bisimplex.firebooru.services.ReloadStatusType.Updated;
                                    }
                                } else {
                                    v10_4.setEnforceOriginalImage(this.downloadOriginal);
                                    if ((android.text.TextUtils.isEmpty(p7.md5)) && (!android.text.TextUtils.isEmpty(v10_4.getMd5()))) {
                                        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateMD5For(p7.getId(), v10_4.getMd5());
                                    }
                                    com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(v10_4);
                                    if (v9_10 != null) {
                                        v9_10.close();
                                    }
                                    return com.bisimplex.firebooru.services.ReloadStatusType.Updated;
                                }
                            } else {
                                if (v9_10 != null) {
                                    v9_10.close();
                                }
                                return com.bisimplex.firebooru.services.ReloadStatusType.NotFound;
                            }
                        }
                    }
                }
            } catch (String v0_7) {
                Long v7_5 = v0_7;
                if (v9_10 != null) {
                    try {
                        v9_10.close();
                    } catch (String v0_8) {
                        v7_5.addSuppressed(v0_8);
                    }
                }
                throw v7_5;
            }
        } else {
            return com.bisimplex.firebooru.services.ReloadStatusType.URLMalformed;
        }
    }

    public static androidx.work.WorkInfo$State workerState(android.content.Context p2)
    {
        try {
            androidx.work.WorkInfo$State v2_8 = ((java.util.List) androidx.work.WorkManager.getInstance(p2).getWorkInfosForUniqueWork("UpdateMetadataWorker_UPDATE_ID").get());
        } catch (androidx.work.WorkInfo$State v2_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_2);
            return 0;
        }
        if ((v2_8 == null) || (v2_8.isEmpty())) {
            return 0;
        } else {
            return ((androidx.work.WorkInfo) v2_8.get(0)).getState();
        }
    }

    public androidx.work.ListenableWorker$Result doWork()
    {
        Throwable v0_0 = 0;
        int v1_0 = 0;
        String v2_0 = 0;
        try {
            do {
                com.bisimplex.firebooru.network.Utils v7_10 = this.updateEntryBox.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).order(com.bisimplex.firebooru.model.UpdateEntry_.id).build();
            } while((v0_0 != null) && (!this.isStopped()));
            Throwable v0_46 = this.updateEntryBox.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build();
            try {
                if (v0_46.count() == 0) {
                    v1_0 = 1;
                }
            } catch (int v1_1) {
                if (v0_46 != null) {
                    try {
                        v0_46.close();
                    } catch (Throwable v0_47) {
                        v1_1.addSuppressed(v0_47);
                    }
                }
                throw v1_1;
            }
            this.postNotification(v1_0, this.context.getString(2131886172));
            if (v0_46 != null) {
                v0_46.close();
            }
            return androidx.work.ListenableWorker$Result.success();
        } catch (com.bisimplex.firebooru.network.Utils v7_26) {
            Throwable v8_2 = v0_0;
            Throwable v0_42 = v7_26;
            if (v8_2 != null) {
                v8_2.setStatus(3);
                v8_2.setMessage(v0_42.getLocalizedMessage());
                this.updateEntryBox.put(v8_2);
            }
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_42);
            v0_0 = v8_2;
            if ((v0_0 != null) || (this.isStopped())) {
            }
        }
        v8_2 = ((com.bisimplex.firebooru.model.UpdateEntry) v7_10.findFirst());
        if (v7_10 != null) {
            v7_10.close();
        }
        if (v8_2 != null) {
            Throwable v0_50 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getFavoriteByID(v8_2.getFav_id());
            if (v0_50 != null) {
                switch (com.bisimplex.firebooru.services.UpdateMetadataWorker$1.$SwitchMap$com$bisimplex$firebooru$services$ReloadStatusType[this.reloadIt(v0_50).ordinal()]) {
                    case 1:
                        Throwable v0_39 = this.context.getString(2131887232, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_39);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_39);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_39})));
                    case 2:
                        Throwable v0_34 = this.context.getString(2131887233, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_34);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_34);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_34})));
                        break;
                    case 3:
                        Throwable v0_30 = this.context.getString(2131887234, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(2);
                        v8_2.setMessage(v0_30);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_30);
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 4:
                        Throwable v0_25 = this.context.getString(2131886871, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_25);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_25);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_25})));
                        break;
                    case 5:
                        Throwable v0_19 = this.context.getString(2131886872, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(2);
                        v8_2.setMessage(v0_19);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_19);
                        com.bisimplex.firebooru.network.Utils.getInstance().logInfo(String.format("UpdateMetadataWorker Info: %s", new Object[] {v0_19})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(1000);
                        } else {
                        }
                        break;
                    case 6:
                        Throwable v0_13 = this.context.getString(2131886869, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_13);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_13);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_13})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 7:
                        Throwable v0_7 = this.context.getString(2131886870, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_7);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(0, v0_7);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_7})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 8:
                        v2_0++;
                        if (v2_0 >= 3) {
                            Throwable v0_59 = this.context.getString(2131886874, new Object[] {v0_50.posturl}));
                            v8_2.setStatus(3);
                            v8_2.setMessage(v0_59);
                            this.updateEntryBox.put(v8_2);
                            this.postNotification(1, v0_59);
                            com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_59})));
                            return androidx.work.ListenableWorker$Result.retry();
                        } else {
                            Throwable v0_1 = this.context.getString(2131886873, new Object[] {v0_50.posturl}));
                            v8_2.setStatus(3);
                            v8_2.setMessage(v0_1);
                            this.updateEntryBox.put(v8_2);
                            this.postNotification(0, v0_1);
                            com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_1})));
                            if (!this.isStopped()) {
                                android.os.SystemClock.sleep(4000);
                            } else {
                            }
                        }
                    case 9:
                        Throwable v0_53 = this.context.getString(2131886868, new Object[] {v0_50.posturl}));
                        v8_2.setStatus(3);
                        v8_2.setMessage(v0_53);
                        this.updateEntryBox.put(v8_2);
                        this.postNotification(1, v0_53);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v0_53})));
                        return androidx.work.ListenableWorker$Result.failure();
                    default:
                }
            }
        } else {
        }
    }

    public void onStopped()
    {
        super.onStopped();
        this.getManager().cancel(1047);
        return;
    }
}
