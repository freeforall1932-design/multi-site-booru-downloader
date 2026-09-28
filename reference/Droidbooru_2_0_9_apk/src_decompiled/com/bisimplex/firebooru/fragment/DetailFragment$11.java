package com.bisimplex.firebooru.fragment;
 class DetailFragment$11 implements okhttp3.Callback {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$11(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onFailure(okhttp3.Call p2, java.io.IOException p3)
    {
        if (this.this$0.getActivity() != null) {
            this.this$0.showMessage(p3.getLocalizedMessage(), com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        } else {
            return;
        }
    }

    public void onResponse(okhttp3.Call p2, okhttp3.Response p3)
    {
        if (this.this$0.getActivity() != null) {
            if (!p3.isSuccessful()) {
                com.bisimplex.firebooru.activity.MessageType v0_0;
                com.bisimplex.firebooru.fragment.DetailFragment v2_5 = p3.body();
                if (v2_5 == null) {
                    v0_0 = "";
                } else {
                    v0_0 = v2_5.string();
                    v2_5.close();
                }
                com.bisimplex.firebooru.fragment.DetailFragment v2_1 = this.this$0;
                v2_1.showMessage(v2_1.getString(2131886504, new Object[] {String.valueOf(p3.code()), v0_0})), com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            } else {
                com.bisimplex.firebooru.fragment.DetailFragment v2_3 = this.this$0;
                v2_3.showMessage(v2_3.getString(2131887195), com.bisimplex.firebooru.activity.MessageType.Success);
                return;
            }
        } else {
            return;
        }
    }
}
