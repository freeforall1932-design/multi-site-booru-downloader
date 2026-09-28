package com.bisimplex.firebooru.view;
 class GelbooruUserRequestDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.GelbooruUserRequestDialog this$0;

    GelbooruUserRequestDialog$2(com.bisimplex.firebooru.view.GelbooruUserRequestDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        if (this.this$0.mListener != null) {
            this.this$0.mListener.onUserCancel(this.this$0);
        }
        return;
    }
}
