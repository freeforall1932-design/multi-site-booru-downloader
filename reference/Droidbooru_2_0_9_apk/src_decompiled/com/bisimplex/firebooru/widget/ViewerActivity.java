package com.bisimplex.firebooru.widget;
public class ViewerActivity extends com.bisimplex.firebooru.activity.MainActivity {
    public static final String EXTRA_APPWIDGET_ID = "com.bisimplex.DroidBooru.stackwidget.EXTRA_APPWIDGET_ID";
    public static final String INITIAL_INDEX = "com.bisimplex.DroidBooru.stackwidget.INITIAL_INDEX";
    private int initialIndex;
    private int widgetId;

    public ViewerActivity()
    {
        this.widgetId = -1;
        this.initialIndex = -1;
        return;
    }

    public com.bisimplex.firebooru.fragment.BaseFragment createDefaultFragment()
    {
        com.bisimplex.firebooru.widget.WidgetDetailFragment v0_1 = new com.bisimplex.firebooru.widget.WidgetDetailFragment();
        v0_1.setInitialIndex(this.initialIndex);
        return v0_1;
    }

    public void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        if (p3 != null) {
            this.initialIndex = p3.getInt("widget_initialIndex", 0);
            ((com.bisimplex.firebooru.widget.WidgetDetailFragment) this.mContent).setInitialIndex(this.initialIndex);
        }
        androidx.appcompat.app.ActionBar v3_1 = this.getSupportActionBar();
        if (v3_1 != null) {
            v3_1.setDisplayHomeAsUpEnabled(0);
            v3_1.setLogo(new com.bisimplex.firebooru.widget.ViewerActivity$1(this, this));
        }
        return;
    }

    public boolean onOptionsItemSelected(android.view.MenuItem p3)
    {
        if (p3.getItemId() == 16908332) {
            return 0;
        } else {
            return super.onOptionsItemSelected(p3);
        }
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putInt("widget_widgetId", this.widgetId);
        return;
    }
}
