package com.bisimplex.firebooru.view;
 class ValidateClientDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.ValidateClientDialog this$0;
    final synthetic String val$finalServer_url;

    ValidateClientDialog$2(com.bisimplex.firebooru.view.ValidateClientDialog p1, String p2)
    {
        this.this$0 = p1;
        this.val$finalServer_url = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        com.bisimplex.firebooru.view.ValidateClientDialog.-$$Nest$mextractCookies(this.this$0, this.val$finalServer_url);
        com.bisimplex.firebooru.view.ValidateClientDialog.-$$Nest$fgetmListener(this.this$0).onDialogValidateClientSaved(this.this$0);
        return;
    }
}
