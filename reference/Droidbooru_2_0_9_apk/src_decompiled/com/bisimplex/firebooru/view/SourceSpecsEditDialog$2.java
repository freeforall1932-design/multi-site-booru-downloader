package com.bisimplex.firebooru.view;
 class SourceSpecsEditDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.SourceSpecsEditDialog this$0;
    final synthetic String val$editID;
    final synthetic String val$parentGroupID;

    SourceSpecsEditDialog$2(com.bisimplex.firebooru.view.SourceSpecsEditDialog p1, String p2, String p3)
    {
        this.this$0 = p1;
        this.val$editID = p2;
        this.val$parentGroupID = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.model.SourceSpecs v2_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().findSourceSpecsByID(this.val$editID, this.val$parentGroupID);
        if (v2_1 != null) {
            com.bisimplex.firebooru.view.SourceSpecsEditDialog$SourceSpecsEditDialogListener v3_0 = new com.bisimplex.firebooru.network.SourceQuery(v2_1.getQuery());
            v3_0.setText(this.this$0.queryEditText.getText().toString());
            v2_1.setQuery(v3_0);
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v2_1, this.val$parentGroupID);
            com.bisimplex.firebooru.view.SourceSpecsEditDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsEdited(this.this$0, v2_1);
        }
        return;
    }
}
