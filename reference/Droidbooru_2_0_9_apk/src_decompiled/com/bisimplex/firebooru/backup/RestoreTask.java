package com.bisimplex.firebooru.backup;
public class RestoreTask {
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private final com.bisimplex.firebooru.backup.LiveDB liveDB;
    private ref.WeakReference mListener;
    private boolean working;

    public static synthetic void $r8$lambda$iyFdFxA9U3yX6zhyNurV47h76a4(com.bisimplex.firebooru.backup.RestoreTask p0, Exception p1)
    {
        p0.lambda$doWork$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$n5oLIY4OWrzZjGhijMpYFL8E30A(com.bisimplex.firebooru.backup.RestoreTask p0)
    {
        p0.doWork();
        return;
    }

    public static synthetic void $r8$lambda$ovZy0lWPN4E5dKjodzjrcMc9cgE(com.bisimplex.firebooru.backup.RestoreTask p0)
    {
        p0.lambda$doWork$0();
        return;
    }

    public RestoreTask(ref.WeakReference p2, com.bisimplex.firebooru.backup.LiveDB p3)
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.mListener = p2;
        this.liveDB = p3;
        return;
    }

    private void doWork()
    {
        if (this.liveDB != null) {
            this.working = 1;
            try {
                java.util.List v2_3 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
                java.util.ArrayList v3_0 = this.liveDB.getBannedTags().iterator();
            } catch (com.bisimplex.firebooru.danbooru.UserConfiguration v0_2) {
                this.enqueueHandler.post(new com.bisimplex.firebooru.backup.RestoreTask$$ExternalSyntheticLambda1(this, v0_2));
                this.working = 0;
                return;
            } catch (com.bisimplex.firebooru.danbooru.UserConfiguration v0_3) {
                this.working = 0;
                throw v0_3;
            }
            while (v3_0.hasNext()) {
                v2_3.addBannedTag(((com.bisimplex.firebooru.model.BannedTag) v3_0.next()).tagText);
            }
            java.util.ArrayList v3_6 = new java.util.ArrayList(0);
            com.bisimplex.firebooru.model.SourceSpecs v4_6 = v2_3.loadServers();
            boolean v5_3 = this.liveDB.getServers();
            java.util.Date v6_5 = new java.util.ArrayList();
            boolean v5_4 = v5_3.iterator();
            while (v5_4.hasNext()) {
                v6_5.add(((com.bisimplex.firebooru.backup.BackupServerItem) v5_4.next()).asServerItem());
            }
            if (!v6_5.isEmpty()) {
                java.util.Collections.reverse(v6_5);
            }
            boolean v5_6 = v6_5.iterator();
            while (v5_6.hasNext()) {
                java.util.Date v6_3 = ((com.bisimplex.firebooru.danbooru.ServerItem) v5_6.next());
                if (!v6_3.isDefault()) {
                    v6_3.setServerId(0);
                    v6_3.setSelected(0);
                    long v7_2 = v4_6.iterator();
                    boolean v8_0 = 0;
                    while (v7_2.hasNext()) {
                        String v9_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) v7_2.next());
                        if (!v9_2.isDefault()) {
                            if (v9_2.getUrl().equalsIgnoreCase(v6_3.getUrl())) {
                                if ((!android.text.TextUtils.isEmpty(v9_2.getUserName())) || (!android.text.TextUtils.isEmpty(v9_2.getPassword()))) {
                                    if ((android.text.TextUtils.isEmpty(v9_2.getUserName())) || ((android.text.TextUtils.isEmpty(v6_3.getUserName())) || (!v6_3.getUserName().equalsIgnoreCase(v9_2.getUserName())))) {
                                        v8_0 = 0;
                                        if (v8_0) {
                                            break;
                                        }
                                    } else {
                                    }
                                }
                                v8_0 = 1;
                            }
                        } else {
                        }
                    }
                    if (!v8_0) {
                        if ((v6_3.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru) && ((!android.text.TextUtils.isEmpty(v6_3.getUserName())) && (!android.text.TextUtils.isEmpty(v6_3.getPassword())))) {
                            v3_6.add(v6_3);
                        }
                        long v7_8 = v2_3.addServer(v6_3);
                        if (v3_6.contains(v6_3)) {
                            v6_3.setServerId(v7_8.longValue());
                        }
                    } else {
                    }
                } else {
                }
            }
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_5 = this.liveDB.getFavorites();
            if ((v0_5 != null) && (!v0_5.isEmpty())) {
                java.util.Collections.reverse(v0_5);
                com.bisimplex.firebooru.danbooru.UserConfiguration v0_6 = v0_5.iterator();
                while (v0_6.hasNext()) {
                    v2_3.addFavoriteItemNoCheck(((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_6.next()));
                }
            }
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_8 = this.liveDB.getRawFavorites();
            if ((v0_8 != null) && (!v0_8.isEmpty())) {
                java.util.Collections.reverse(v0_8);
                com.bisimplex.firebooru.danbooru.UserConfiguration v0_9 = v0_8.iterator();
                while (v0_9.hasNext()) {
                    ((com.bisimplex.firebooru.model.Favorite) v0_9.next()).save();
                }
            }
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_11 = new java.util.Date();
            java.util.ArrayList v3_16 = this.liveDB.getSearchHistory().iterator();
            while (v3_16.hasNext()) {
                java.util.Date v6_1;
                com.bisimplex.firebooru.model.SourceSpecs v4_3 = ((com.bisimplex.firebooru.backup.BackupTagHistory) v3_16.next());
                if (v4_3.searchDate != null) {
                    v6_1 = v4_3.searchDate;
                } else {
                    v6_1 = v0_11;
                }
                v2_3.addHistoryItem(v4_3.searchText, v6_1, v4_3.starred);
            }
            com.bisimplex.firebooru.danbooru.UserConfiguration v0_13 = this.liveDB.getHomePins();
            if ((v0_13 != null) && (!v0_13.isEmpty())) {
                java.util.List v2_6 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSourceSpecs();
                java.util.ArrayList v3_18 = new java.util.ArrayList();
                com.bisimplex.firebooru.danbooru.UserConfiguration v0_14 = v0_13.iterator();
                while (v0_14.hasNext()) {
                    com.bisimplex.firebooru.model.SourceSpecs v4_1 = ((com.bisimplex.firebooru.model.SourceSpecs) v0_14.next());
                    if (!this.specsExistInList(v4_1, v2_6)) {
                        v3_18.add(v4_1);
                    }
                }
                if (!v3_18.isEmpty()) {
                    v2_6.addAll(v3_18);
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setSourceSpecs(v2_6);
                }
            }
            this.enqueueHandler.post(new com.bisimplex.firebooru.backup.RestoreTask$$ExternalSyntheticLambda0(this));
            this.working = 0;
            return;
        } else {
            return;
        }
    }

    private synthetic void lambda$doWork$0()
    {
        com.bisimplex.firebooru.backup.RestoreTask$RestoreTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.RestoreTask$RestoreTaskListener) this.mListener.get()).restoreDone(com.bisimplex.firebooru.backup.RestoreStatusType.Success, 0);
        }
        return;
    }

    private synthetic void lambda$doWork$1(Exception p3)
    {
        com.bisimplex.firebooru.backup.RestoreTask$RestoreTaskListener v0_0 = this.mListener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.backup.RestoreTask$RestoreTaskListener) this.mListener.get()).restoreDone(com.bisimplex.firebooru.backup.RestoreStatusType.InvalidFile, p3.getLocalizedMessage());
        }
        return;
    }

    private boolean specsExistInList(com.bisimplex.firebooru.model.SourceSpecs p2, java.util.List p3)
    {
        java.util.Iterator v3_1 = p3.iterator();
        while (v3_1.hasNext()) {
            if (p2.isEqualTo(((com.bisimplex.firebooru.model.SourceSpecs) v3_1.next()))) {
                return 1;
            }
        }
        return 0;
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
        this.executor.execute(new com.bisimplex.firebooru.backup.RestoreTask$$ExternalSyntheticLambda2(this));
        return;
    }
}
