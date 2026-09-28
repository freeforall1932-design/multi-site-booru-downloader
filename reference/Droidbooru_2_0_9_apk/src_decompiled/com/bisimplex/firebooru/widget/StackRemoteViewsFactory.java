package com.bisimplex.firebooru.widget;
public class StackRemoteViewsFactory implements android.widget.RemoteViewsService$RemoteViewsFactory {
    public static final String LOADING_URL = "file://loading/data";
    public static final String NODATA_URL = "file://no/data";
    private com.bisimplex.firebooru.widget.WidgetConfigurationItem configuration;
    private int mAppWidgetId;
    private android.content.Context mContext;
    private java.util.ArrayList mWidgetItems;
    com.bisimplex.firebooru.widget.WidgetConfigurationManager manager;
    private java.util.regex.Pattern pngMatcher;

    public StackRemoteViewsFactory(android.content.Context p2, android.content.Intent p3)
    {
        this.mWidgetItems = new java.util.ArrayList();
        this.mContext = p2;
        this.mAppWidgetId = p3.getIntExtra("appWidgetId", 0);
        this.manager = new com.bisimplex.firebooru.widget.WidgetConfigurationManager(this.mContext);
        this.pngMatcher = java.util.regex.Pattern.compile(java.util.regex.Pattern.quote("*png*"), 2);
        this.configuration = this.manager.getItemById(this.mAppWidgetId);
        android.util.Log.i("GelbooruWidget", new StringBuilder("StackRemoteViewsFactory ").append(this.mAppWidgetId).toString());
        return;
    }

    public int getCount()
    {
        android.util.Log.i("GelbooruWidget", new StringBuilder("getCount ").append(this.mWidgetItems.size()).toString());
        return this.mWidgetItems.size();
    }

    public long getItemId(int p3)
    {
        return ((long) p3);
    }

    public android.widget.RemoteViews getLoadingView()
    {
        return new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558676);
    }

    public android.widget.RemoteViews getViewAt(int p4)
    {
        android.widget.RemoteViews v0_0 = this.mWidgetItems;
        if (v0_0 != null) {
            if ((v0_0.size() != 0) && (p4 < this.mWidgetItems.size())) {
                android.util.Log.i("GelbooruWidget", new StringBuilder("getViewAt ").append(p4).toString());
                Exception v4_4 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.mWidgetItems.get(p4)).getPreview().getUrl();
                if (!v4_4.equals("file://loading/data")) {
                    if (!v4_4.equals("file://no/data")) {
                        android.widget.RemoteViews v0_11 = new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558674);
                        String v1_2 = new android.os.Bundle();
                        String v2_5 = new android.content.Intent();
                        v2_5.putExtras(v1_2);
                        v0_11.setOnClickFillInIntent(2131362635, v2_5);
                        try {
                            Exception v4_5 = new java.net.URL(v4_4).openConnection();
                            v4_5.setRequestProperty("User-agent", com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent());
                            android.graphics.BitmapFactory.decodeStream(v4_5.getInputStream());
                            0.getAbsolutePath();
                            throw 0;
                        } catch (Exception v4_10) {
                            v4_10.printStackTrace();
                            return v0_11;
                        } catch (Exception v4_9) {
                            v4_9.printStackTrace();
                            return v0_11;
                        } catch (Exception v4_8) {
                            v4_8.printStackTrace();
                            return v0_11;
                        }
                    } else {
                        return new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558675);
                    }
                } else {
                    return new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558676);
                }
            } else {
                return new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558674);
            }
        } else {
            return new android.widget.RemoteViews(this.mContext.getPackageName(), 2131558674);
        }
    }

    public int getViewTypeCount()
    {
        return 1;
    }

    public boolean hasStableIds()
    {
        return 1;
    }

    public void onCreate()
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = new com.bisimplex.firebooru.danbooru.DanbooruPost();
        v0_1.setPreview(new com.bisimplex.firebooru.danbooru.DanbooruPostImage("file://loading/data", 0, 0));
        this.mWidgetItems.add(v0_1);
        return;
    }

    public void onDataSetChanged()
    {
        if (this.configuration != null) {
            String v0_1 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().ServerToServerItem(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerById(this.configuration.getServerId()));
            if (v0_1 != null) {
                android.util.Log.i("GelbooruWidget", new StringBuilder("request to url ").append(new com.bisimplex.firebooru.danbooru.BooruProvider(v0_1, 16).generateRequestUrl(new com.bisimplex.firebooru.network.SourceQuery(this.configuration.getQuery()), 0).toString()).toString());
                return;
            }
        }
        return;
    }

    public void onDestroy()
    {
        this.mWidgetItems.clear();
        return;
    }
}
