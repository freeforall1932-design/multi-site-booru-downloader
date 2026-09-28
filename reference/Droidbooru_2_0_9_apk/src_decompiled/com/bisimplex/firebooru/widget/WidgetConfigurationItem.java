package com.bisimplex.firebooru.widget;
public class WidgetConfigurationItem {
    private int mAppWidgetId;
    private String query;
    private int serverId;

    public WidgetConfigurationItem()
    {
        return;
    }

    public String getQuery()
    {
        return this.query;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServer()
    {
        return com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().ServerToServerItem(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerById(this.getServerId()));
    }

    public int getServerId()
    {
        return this.serverId;
    }

    public int getmAppWidgetId()
    {
        return this.mAppWidgetId;
    }

    public void setQuery(String p1)
    {
        this.query = p1;
        return;
    }

    public void setServerId(int p1)
    {
        this.serverId = p1;
        return;
    }

    public void setmAppWidgetId(int p1)
    {
        this.mAppWidgetId = p1;
        return;
    }
}
