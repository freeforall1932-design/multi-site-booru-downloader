package com.bisimplex.firebooru.services;
public class CacheCleanUpWorker extends androidx.work.Worker {
    private static final int MAX_DELETE_COUNT = 1200;
    public static final String WORKER_NAME = "CacheCleanUp";

    public CacheCleanUpWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    private int clearDirectory(java.io.File p7, int p8, long p9)
    {
        if ((p7 != null) && ((p7.exists()) && (p7.isDirectory()))) {
            java.io.File[] v7_1 = p7.listFiles();
            if (v7_1 != null) {
                int v0_0 = v7_1.length;
                int v1 = 0;
                while (v1 < v0_0) {
                    int v2_0 = v7_1[v1];
                    if ((v2_0.isFile()) && ((v2_0.canRead()) && (v2_0.lastModified() < p9))) {
                        android.util.Log.i("CacheCleanUpWorker", new StringBuilder("deleting file ").append(v2_0.getName()).toString());
                        if (!v2_0.delete()) {
                            android.util.Log.i("CacheCleanUpWorker", new StringBuilder("can\'t delete file: ").append(v2_0.getName()).toString());
                        }
                        p8++;
                        if (p8 == 1200) {
                            return p8;
                        }
                    }
                    v1++;
                }
            }
        }
        return p8;
    }

    private void deleteCache()
    {
        long v0_0 = java.util.Calendar.getInstance();
        v0_0.add(6, -2);
        long v0_1 = v0_0.getTimeInMillis();
        java.io.File v2_2 = this.getApplicationContext();
        this.clearDirectory(com.bumptech.glide.Glide.getPhotoCacheDir(v2_2), 0, v0_1);
        this.clearDirectory(com.bumptech.glide.Glide.getPhotoCacheDir(v2_2, "image_cache"), 0, v0_1);
        return;
    }

    public androidx.work.ListenableWorker$Result doWork()
    {
        try {
            this.deleteCache();
            return androidx.work.ListenableWorker$Result.success();
        } catch (androidx.work.ListenableWorker$Result v0_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
            return androidx.work.ListenableWorker$Result.failure();
        }
    }
}
