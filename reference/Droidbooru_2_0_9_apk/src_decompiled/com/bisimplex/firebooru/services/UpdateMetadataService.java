package com.bisimplex.firebooru.services;
public class UpdateMetadataService {
    static final int FINISHED_NOTIFICATION_ID = 1048;
    static final int NOTIFICATION_ID = 1047;
    private static final com.bisimplex.firebooru.services.UpdateMetadataService ourInstance = None;
    private static final int small_wait_time = 1000;
    private static final int wait_time = 4000;
    private boolean downloadOriginal;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private ref.WeakReference listener;
    private android.app.NotificationManager mManager;
    private android.content.SharedPreferences preferences;
    private boolean running;
    private boolean stopped;

    public static synthetic void $r8$lambda$67xzrF6GaiBnpfiILFicbjO744g(com.bisimplex.firebooru.services.UpdateMetadataService p0)
    {
        p0.lambda$doWork$0();
        return;
    }

    public static synthetic void $r8$lambda$97674fkOk4_7WtpNv62WpRu_D8A(com.bisimplex.firebooru.services.UpdateMetadataService p0, android.content.Context p1)
    {
        p0.lambda$doWork$1(p1);
        return;
    }

    static UpdateMetadataService()
    {
        com.bisimplex.firebooru.services.UpdateMetadataService.ourInstance = new com.bisimplex.firebooru.services.UpdateMetadataService();
        return;
    }

    private UpdateMetadataService()
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        return;
    }

    private void doWork(android.content.Context p3)
    {
        if ((!this.isRunning()) && (!this.isStopped())) {
            this.start();
            this.executor.execute(new com.bisimplex.firebooru.services.UpdateMetadataService$$ExternalSyntheticLambda0(this, p3));
        }
        return;
    }

    public static com.bisimplex.firebooru.services.UpdateMetadataService getInstance()
    {
        return com.bisimplex.firebooru.services.UpdateMetadataService.ourInstance;
    }

    private android.app.NotificationManager getManager()
    {
        return this.mManager;
    }

    private synthetic void lambda$doWork$0()
    {
        com.bisimplex.firebooru.services.UpdateMetadataService$UpdateMetadataServiceListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.services.UpdateMetadataService$UpdateMetadataServiceListener) this.listener.get()).updateFinished();
        }
        return;
    }

    private synthetic void lambda$doWork$1(android.content.Context p15)
    {
        com.bisimplex.firebooru.services.UpdateMetadataService$$ExternalSyntheticLambda1 v0_7 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.UpdateEntry);
        String v1_42 = 0;
        int v2 = 0;
        int v3 = 0;
        try {
            do {
                com.bisimplex.firebooru.network.Utils v8_13 = v0_7.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).order(com.bisimplex.firebooru.model.UpdateEntry_.id).build();
            } while((v1_42 != null) && (!this.isStopped()));
            this.resetFlags();
            com.bisimplex.firebooru.services.UpdateMetadataService$$ExternalSyntheticLambda1 v0_3 = v0_7.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build();
            try {
                if (v0_3.count() == 0) {
                    v2 = 1;
                }
            } catch (android.os.Handler v15_1) {
                if (v0_3 != null) {
                    try {
                        v0_3.close();
                    } catch (com.bisimplex.firebooru.services.UpdateMetadataService$$ExternalSyntheticLambda1 v0_4) {
                        v15_1.addSuppressed(v0_4);
                    }
                }
                throw v15_1;
            }
            this.postNotification(v2, p15.getString(2131886595), p15);
            if (v0_3 != null) {
                v0_3.close();
            }
            android.os.Handler v15_2 = this.listener;
            if ((v15_2 == null) || (v15_2.get() == null)) {
                return;
            } else {
                this.enqueueHandler.post(new com.bisimplex.firebooru.services.UpdateMetadataService$$ExternalSyntheticLambda1(this));
                return;
            }
        } catch (com.bisimplex.firebooru.network.Utils v8_19) {
            Throwable v9_3 = v1_42;
            String v1_41 = v8_19;
            if (v9_3 != null) {
                v9_3.setStatus(3);
                v9_3.setMessage(v1_41.getLocalizedMessage());
                v0_7.put(v9_3);
            }
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_41);
            v1_42 = v9_3;
            if ((v1_42 != null) || (this.isStopped())) {
            }
        }
        v9_3 = ((com.bisimplex.firebooru.model.UpdateEntry) v8_13.findFirst());
        if (v8_13 != null) {
            v8_13.close();
        }
        if (v9_3 != null) {
            String v1_48 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getFavoriteByID(v9_3.getFav_id());
            if (v1_48 != null) {
                switch (com.bisimplex.firebooru.services.UpdateMetadataService$1.$SwitchMap$com$bisimplex$firebooru$services$ReloadStatusType[this.reloadIt(v1_48).ordinal()]) {
                    case 1:
                        String v1_38 = p15.getString(2131887232, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_38);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_38, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_38})));
                    case 2:
                        String v1_33 = p15.getString(2131887233, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_33);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_33, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_33})));
                        break;
                    case 3:
                        String v1_28 = p15.getString(2131887234, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(2);
                        v9_3.setMessage(v1_28);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_28, p15);
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 4:
                        String v1_23 = p15.getString(2131886871, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_23);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_23, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_23})));
                        break;
                    case 5:
                        String v1_17 = p15.getString(2131886872, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(2);
                        v9_3.setMessage(v1_17);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_17, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logInfo(String.format("UpdateMetadataWorker Info: %s", new Object[] {v1_17})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(1000);
                        } else {
                        }
                        break;
                    case 6:
                        String v1_11 = p15.getString(2131886869, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_11);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_11, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_11})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 7:
                        String v1_5 = p15.getString(2131886870, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_5);
                        v0_7.put(v9_3);
                        this.postNotification(0, v1_5, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_5})));
                        if (!this.isStopped()) {
                            android.os.SystemClock.sleep(4000);
                        } else {
                        }
                        break;
                    case 8:
                        v3++;
                        if (v3 >= 3) {
                            String v1_56 = p15.getString(2131886874, new Object[] {v1_48.posturl}));
                            v9_3.setStatus(3);
                            v9_3.setMessage(v1_56);
                            v0_7.put(v9_3);
                            this.postNotification(1, v1_56, p15);
                            com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_56})));
                            return;
                        } else {
                            String v1_61 = p15.getString(2131886873, new Object[] {v1_48.posturl}));
                            v9_3.setStatus(3);
                            v9_3.setMessage(v1_61);
                            v0_7.put(v9_3);
                            this.postNotification(0, v1_61, p15);
                            com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_61})));
                            if (!this.isStopped()) {
                                android.os.SystemClock.sleep(4000);
                            } else {
                            }
                        }
                    case 9:
                        String v1_51 = p15.getString(2131886868, new Object[] {v1_48.posturl}));
                        v9_3.setStatus(3);
                        v9_3.setMessage(v1_51);
                        v0_7.put(v9_3);
                        this.postNotification(1, v1_51, p15);
                        com.bisimplex.firebooru.network.Utils.getInstance().logError(String.format("UpdateMetadataWorker Error: %s", new Object[] {v1_51})));
                        return;
                    default:
                }
            }
        } else {
        }
    }

    private void postNotification(boolean p1, String p2, android.content.Context p3)
    {
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

    public void cancelAll(android.content.Context p1)
    {
        if (this.isRunning()) {
            this.stop();
        }
        return;
    }

    public void checkIfMustLaunch(android.content.Context p7)
    {
        Throwable v0_2 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.UpdateEntry).query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build();
        try {
            if (v0_2.count() <= 0) {
                android.app.NotificationManager v7_1 = this.getManager();
                if (v7_1 != null) {
                    v7_1.cancelAll();
                }
            } else {
                this.enqueueWork(p7);
            }
        } catch (android.app.NotificationManager v7_2) {
            if (v0_2 != null) {
                try {
                    v0_2.close();
                } catch (Throwable v0_3) {
                    v7_2.addSuppressed(v0_3);
                }
            }
            throw v7_2;
        }
        if (v0_2 != null) {
            v0_2.close();
        }
        return;
    }

    public void enqueueWork(android.content.Context p2)
    {
        this.init(p2);
        if (!this.isRunning()) {
            this.doWork(p2);
            return;
        } else {
            return;
        }
    }

    public void init(android.content.Context p3)
    {
        if (this.preferences == null) {
            boolean v3_5 = p3.getApplicationContext();
            this.preferences = v3_5.getSharedPreferences("UserConfiguration", 0);
            this.mManager = ((android.app.NotificationManager) v3_5.getSystemService("notification"));
            this.downloadOriginal = this.preferences.getBoolean("downloadOriginalImage", 0);
        }
        return;
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public boolean isStopped()
    {
        return this.stopped;
    }

    public void setListener(com.bisimplex.firebooru.services.UpdateMetadataService$UpdateMetadataServiceListener p2)
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
