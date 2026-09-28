package com.bisimplex.firebooru.danbooru;
 class UserConfiguration$1 implements okhttp3.Callback {
    final synthetic com.bisimplex.firebooru.danbooru.UserConfiguration this$0;

    UserConfiguration$1(com.bisimplex.firebooru.danbooru.UserConfiguration p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onFailure(okhttp3.Call p1, java.io.IOException p2)
    {
        return;
    }

    public void onResponse(okhttp3.Call p3, okhttp3.Response p4)
    {
        if (p4.isSuccessful()) {
            androidx.localbroadcastmanager.content.LocalBroadcastManager v4_10 = p4.body();
            if (v4_10 != null) {
                int v1_6 = v4_10.string();
                v4_10.close();
                if (!android.text.TextUtils.isEmpty(v1_6)) {
                    try {
                        androidx.localbroadcastmanager.content.LocalBroadcastManager v4_2 = com.google.gson.JsonParser.parseString(v1_6);
                    } catch (android.content.Intent v3_1) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v3_1);
                    }
                    if (v4_2.isJsonObject()) {
                        androidx.localbroadcastmanager.content.LocalBroadcastManager v4_3 = v4_2.getAsJsonObject();
                        if (v4_3.has("version")) {
                            androidx.localbroadcastmanager.content.LocalBroadcastManager v4_5 = v4_3.get("version").getAsInt();
                            int vtmp13 = com.bisimplex.firebooru.danbooru.UserConfiguration.-$$Nest$fgetpreferences(this.this$0).getInt("lastVersionChecked", 0);
                            if ((v4_5 > com.bisimplex.firebooru.danbooru.UserConfiguration.-$$Nest$fgetcurrentAppVersionCode(this.this$0)) && (vtmp13 != v4_5)) {
                                android.content.SharedPreferences$Editor v0_6 = com.bisimplex.firebooru.danbooru.UserConfiguration.-$$Nest$fgetpreferences(this.this$0).edit();
                                v0_6.putInt("lastVersionChecked", v4_5);
                                v0_6.apply();
                                androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).sendBroadcast(new android.content.Intent(com.bisimplex.firebooru.danbooru.UserConfiguration.NEWVERSIONAPP));
                                return;
                            }
                        }
                    }
                }
            }
        }
        return;
    }
}
