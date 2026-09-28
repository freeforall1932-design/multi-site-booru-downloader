package com.bisimplex.firebooru.fragment;
 class DynamicServerFormFragment$3 implements okhttp3.Callback {
    final synthetic com.bisimplex.firebooru.fragment.DynamicServerFormFragment this$0;

    public static synthetic void $r8$lambda$KQKygdJGmk0VlApKL7DmyQW0ZX4(com.bisimplex.firebooru.fragment.DynamicServerFormFragment$3 p0)
    {
        p0.lambda$onResponse$0();
        return;
    }

    DynamicServerFormFragment$3(com.bisimplex.firebooru.fragment.DynamicServerFormFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    private synthetic void lambda$onResponse$0()
    {
        this.this$0.HideLoading();
        this.this$0.adapter.setControlsEnabled(1);
        return;
    }

    public void onFailure(okhttp3.Call p3, java.io.IOException p4)
    {
        if ((!this.this$0.isDetached()) && (this.this$0.getActivity() != null)) {
            this.this$0.showMessage(p4.getLocalizedMessage(), com.bisimplex.firebooru.activity.MessageType.Error);
            com.bisimplex.firebooru.network.Utils.getInstance().logException(p4);
        }
        return;
    }

    public void onResponse(okhttp3.Call p5, okhttp3.Response p6)
    {
        if ((!this.this$0.isDetached()) && (this.this$0.getActivity() != null)) {
            int v0_0;
            com.bisimplex.firebooru.fragment.DynamicServerFormFragment v5_25 = p6.body();
            if (v5_25 == null) {
                v0_0 = 0;
            } else {
                v0_0 = v5_25.string();
                v5_25.close();
            }
            String v1 = "";
            if (!p6.isSuccessful()) {
                if ((p6.code() != 401) || ((com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$fgetdata(this.this$0) == null) || (com.bisimplex.firebooru.fragment.DynamicServerFormFragment.-$$Nest$fgetdata(this.this$0).getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2))) {
                    com.bisimplex.firebooru.fragment.DynamicServerFormFragment v5_9 = p6.code();
                    this.this$0.showMessage(com.bisimplex.firebooru.data.FailureType.fromInteger(v5_9), v5_9);
                    if (this.this$0.recyclerView != null) {
                        this.this$0.recyclerView.post(new com.bisimplex.firebooru.fragment.DynamicServerFormFragment$3$$ExternalSyntheticLambda0(this));
                    }
                    com.bisimplex.firebooru.fragment.DynamicServerFormFragment v5_15 = com.bisimplex.firebooru.network.Utils.getInstance();
                    com.bisimplex.firebooru.fragment.DynamicServerFormFragment v2_5 = this.this$0;
                    String v6_2 = Integer.valueOf(p6.code());
                    if (android.text.TextUtils.isEmpty(v0_0)) {
                        v0_0 = "";
                    }
                    v5_15.logError(v2_5.getString(2131886491, new Object[] {v6_2, v0_0})));
                } else {
                    this.this$0.showMessage(2131886293, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            } else {
                if (!android.text.TextUtils.isEmpty(v0_0)) {
                    if (java.util.regex.Pattern.compile("!DOCTYPE html", 2).matcher(v0_0).find()) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logInfo("Server returned HTML body, using mime text/html.");
                        v1 = "text/html";
                    }
                    this.this$0.confirmSaveServerWithMIME(v1);
                    return;
                } else {
                    com.bisimplex.firebooru.network.Utils.getInstance().logInfo("Server returned empty body. No mime detected.");
                    this.this$0.confirmSaveServerWithMIME("");
                    return;
                }
            }
        }
        return;
    }
}
