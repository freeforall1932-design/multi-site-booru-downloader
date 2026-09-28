package com.bisimplex.firebooru.view;
 class GelbooruUserRequestDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.GelbooruUserRequestDialog this$0;

    GelbooruUserRequestDialog$1(com.bisimplex.firebooru.view.GelbooruUserRequestDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        if ((com.bisimplex.firebooru.view.GelbooruUserRequestDialog.-$$Nest$fgetuserNameEditText(this.this$0) != null) && ((com.bisimplex.firebooru.view.GelbooruUserRequestDialog.-$$Nest$fgetpasswordEditText(this.this$0) != null) && (this.this$0.mListener != null))) {
            p4 = this.this$0;
            this.this$0.mListener.onUserSaved(p4, com.bisimplex.firebooru.view.GelbooruUserRequestDialog.-$$Nest$fgetuserNameEditText(p4).getText().toString(), com.bisimplex.firebooru.view.GelbooruUserRequestDialog.-$$Nest$fgetpasswordEditText(this.this$0).getText().toString());
        }
        return;
    }
}
