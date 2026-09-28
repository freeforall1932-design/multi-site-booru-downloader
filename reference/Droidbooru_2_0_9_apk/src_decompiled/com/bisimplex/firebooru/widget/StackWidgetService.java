package com.bisimplex.firebooru.widget;
public class StackWidgetService extends android.widget.RemoteViewsService {

    public StackWidgetService()
    {
        return;
    }

    public android.widget.RemoteViewsService$RemoteViewsFactory onGetViewFactory(android.content.Intent p3)
    {
        android.util.Log.i("GelbooruWidget", new StringBuilder("onGetViewFactory ").append(p3.getAction()).toString());
        return new com.bisimplex.firebooru.widget.StackRemoteViewsFactory(this.getApplicationContext(), p3);
    }
}
