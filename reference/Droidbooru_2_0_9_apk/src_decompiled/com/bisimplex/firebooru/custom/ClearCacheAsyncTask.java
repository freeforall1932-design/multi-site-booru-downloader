package com.bisimplex.firebooru.custom;
public class ClearCacheAsyncTask extends android.os.AsyncTask {
    private com.bisimplex.firebooru.danbooru.TransactionAction callback;

    public ClearCacheAsyncTask()
    {
        return;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((Void[]) p1));
    }

    protected varargs Void doInBackground(Void[] p4)
    {
        if (!this.isCancelled()) {
            java.util.Iterator v4_3 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext();
            com.bumptech.glide.Glide.get(v4_3).clearDiskCache();
            java.util.Iterator v4_1 = v4_3.getCacheDir();
            String v1_1 = new java.util.ArrayList();
            this.listf(v4_1, v1_1);
            java.util.Iterator v4_2 = v1_1.iterator();
            while (v4_2.hasNext()) {
                if (!((java.io.File) v4_2.next()).delete()) {
                    android.util.Log.i("file", "can\'t delete file");
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    public void listf(java.io.File p5, java.util.ArrayList p6)
    {
        java.io.File[] v5_1 = p5.listFiles();
        int v0 = v5_1.length;
        int v1 = 0;
        while (v1 < v0) {
            java.io.File v2 = v5_1[v1];
            if (!v2.isFile()) {
                if (v2.isDirectory()) {
                    this.listf(v2, p6);
                }
            } else {
                p6.add(v2);
            }
            v1++;
        }
        return;
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((Void) p1));
        return;
    }

    protected void onPostExecute(Void p1)
    {
        p1 = this.callback;
        if (p1 != null) {
            p1.success();
        }
        return;
    }

    public void setCallback(com.bisimplex.firebooru.danbooru.TransactionAction p1)
    {
        this.callback = p1;
        return;
    }
}
