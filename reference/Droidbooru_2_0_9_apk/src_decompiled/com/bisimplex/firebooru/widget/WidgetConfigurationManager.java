package com.bisimplex.firebooru.widget;
public class WidgetConfigurationManager {
    public static final String CONFIGURATION_KEY = "DROIDBOORU_WIDGET_CONFIGURATIONS";
    public static final String DATA_KEY = "DATA_KEY_WIDGET_";
    private java.util.ArrayList configurations;
    private android.content.Context mContext;
    private android.content.SharedPreferences preferences;

    public WidgetConfigurationManager(android.content.Context p3)
    {
        this.mContext = p3;
        this.preferences = p3.getSharedPreferences("DroidBooruWidgetConfiguration", 0);
        this.configurations = this.loadConfigurations();
        return;
    }

    private java.util.ArrayList loadConfigurations()
    {
        java.util.ArrayList v0_3 = this.preferences.getString("DROIDBOORU_WIDGET_CONFIGURATIONS", 0);
        android.util.Log.i("GelbooruWidget", String.format("loadConfigurations json: %s", new Object[] {v0_3})));
        reflect.Type v1_2 = new java.util.ArrayList();
        com.google.gson.Gson v3_1 = new com.google.gson.Gson();
        if (v0_3 == null) {
            return v1_2;
        } else {
            android.util.Log.i("GelbooruWidget", new StringBuilder("class: ").append(v1_2.getClass().toString()).toString());
            return ((java.util.ArrayList) v3_1.fromJson(v0_3, new com.bisimplex.firebooru.widget.WidgetConfigurationManager$1(this).getType()));
        }
    }

    public void cleanConfigurations()
    {
        this.preferences.edit().clear().commit();
        return;
    }

    public void commitConfigurations()
    {
        String v0_0 = this.configurations;
        if ((v0_0 != null) && (v0_0.size() != 0)) {
            String v0_10 = new com.google.gson.Gson().toJson(this.configurations);
            android.content.SharedPreferences$Editor v1_1 = this.preferences.edit();
            v1_1.putString("DROIDBOORU_WIDGET_CONFIGURATIONS", v0_10);
            android.util.Log.i("GelbooruWidget", String.format("commitConfigurations json: %s", new Object[] {v0_10})));
            android.util.Log.i("GelbooruWidget", new StringBuilder("class: ").append(this.configurations.getClass().toString()).toString());
            v1_1.commit();
            return;
        } else {
            this.cleanConfigurations();
            return;
        }
    }

    public java.util.ArrayList getConfigurations()
    {
        return this.configurations;
    }

    public java.util.ArrayList getDataForWidget(int p4)
    {
        java.util.ArrayList v4_1 = this.preferences.getString(new StringBuilder("DATA_KEY_WIDGET_").append(p4).toString(), 0);
        android.util.Log.i("GelbooruWidget", String.format("getDataForWidget json: %s", new Object[] {v4_1})));
        reflect.Type v0_4 = new java.util.ArrayList();
        com.google.gson.Gson v1_4 = new com.google.gson.Gson();
        if (v4_1 == null) {
            return v0_4;
        } else {
            return ((java.util.ArrayList) v1_4.fromJson(v4_1, new com.bisimplex.firebooru.widget.WidgetConfigurationManager$2(this).getType()));
        }
    }

    public com.bisimplex.firebooru.widget.WidgetConfigurationItem getItemById(int p7)
    {
        int v0_0 = this.configurations;
        if (v0_0 != 0) {
            if (!v0_0.isEmpty()) {
                int v0_2 = 0;
                while (v0_2 < this.configurations.size()) {
                    android.util.Log.i("GelbooruWidget", new StringBuilder("class: ").append(this.configurations.getClass().toString()).toString());
                    android.util.Log.i("GelbooruWidget", new StringBuilder("class object: ").append(this.configurations.get(v0_2).getClass().toString()).toString());
                    com.bisimplex.firebooru.widget.WidgetConfigurationItem v2_12 = ((com.bisimplex.firebooru.widget.WidgetConfigurationItem) this.configurations.get(v0_2));
                    if (v2_12.getmAppWidgetId() != p7) {
                        v0_2++;
                    } else {
                        return v2_12;
                    }
                }
                return 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void removeConfiguration(int p4)
    {
        android.content.SharedPreferences$Editor v0_0 = this.getItemById(p4);
        if (v0_0 != null) {
            this.configurations.remove(v0_0);
            this.commitConfigurations();
        }
        this.preferences.edit().remove(new StringBuilder("DATA_KEY_WIDGET_").append(p4).toString()).commit();
        return;
    }

    public void setDataForWidget(int p4, java.util.ArrayList p5)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        if ((p5 != null) && (p5.size() != 0)) {
            v0_1.putString(new StringBuilder("DATA_KEY_WIDGET_").append(p4).toString(), new com.google.gson.Gson().toJson(p5));
        } else {
            v0_1.remove(new StringBuilder("DATA_KEY_WIDGET_").append(p4).toString());
        }
        v0_1.commit();
        return;
    }
}
