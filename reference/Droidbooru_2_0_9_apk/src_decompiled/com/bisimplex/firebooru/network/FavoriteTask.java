package com.bisimplex.firebooru.network;
public class FavoriteTask extends android.os.AsyncTask {
    private com.bisimplex.firebooru.network.PostDatabaseTaskListener listener;
    private int page;

    public FavoriteTask(int p1, com.bisimplex.firebooru.network.PostDatabaseTaskListener p2)
    {
        this.listener = p2;
        this.page = p1;
        return;
    }

    protected bridge synthetic Object doInBackground(Object[] p1)
    {
        return this.doInBackground(((com.bisimplex.firebooru.network.SourceQuery[]) p1));
    }

    protected varargs java.util.List doInBackground(com.bisimplex.firebooru.network.SourceQuery[] p4)
    {
        if (p4.length > 0) {
            int v4_5 = p4[0].getExtraParams();
            if (v4_5 == 0) {
                v4_5 = new java.util.HashMap(0);
            }
            if (v4_5.containsKey("FILTER_SERVER_URL")) {
                v4_5.get("FILTER_SERVER_URL");
            }
            if (v4_5.containsKey("FILTER_SORT_ID")) {
                com.bisimplex.firebooru.danbooru.FavoriteSortType.fromInteger(Integer.parseInt(((String) v4_5.get("FILTER_SORT_ID"))));
            }
            v4_5.get("FILTER_EXT");
            v4_5.get("FILTER_RATING");
            v4_5.get("FILTER_SOURCE");
            String v0 = com.bisimplex.firebooru.model.FavoriteSearchTagType.AllTags;
            if (v4_5.containsKey("FILTER_SEARCH_TYPE")) {
                com.bisimplex.firebooru.model.FavoriteSearchTagType.fromInt(Integer.parseInt(((String) v4_5.get("FILTER_SEARCH_TYPE"))));
            }
        }
        return 0;
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
