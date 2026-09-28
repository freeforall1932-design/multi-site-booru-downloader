package com.bisimplex.firebooru.network;
public class HistoryTask extends android.os.AsyncTask {
    private com.bisimplex.firebooru.network.PostDatabaseTaskListener listener;

    public HistoryTask(com.bisimplex.firebooru.network.PostDatabaseTaskListener p1)
    {
        this.listener = p1;
        return;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((Void[]) p1));
    }

    protected varargs java.util.List doInBackground(Void[] p1)
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadPostHistory();
    }

    protected bridge synthetic void onPostExecute(Object p1)
    {
        this.onPostExecute(((java.util.List) p1));
        return;
    }

    protected void onPostExecute(java.util.List p2)
    {
        if (p2 == null) {
            p2 = new java.util.ArrayList(0);
        }
        com.bisimplex.firebooru.network.PostDatabaseTaskListener v0_1 = this.listener;
        if (v0_1 != null) {
            v0_1.finishedLoading(p2);
        }
        return;
    }
}
