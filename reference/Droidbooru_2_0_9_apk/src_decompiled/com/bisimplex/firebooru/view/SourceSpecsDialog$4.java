package com.bisimplex.firebooru.view;
 class SourceSpecsDialog$4 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.SourceSpecsDialog this$0;
    final synthetic String val$parentGroupID;

    SourceSpecsDialog$4(com.bisimplex.firebooru.view.SourceSpecsDialog p1, String p2)
    {
        this.this$0 = p1;
        this.val$parentGroupID = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p6, int p7)
    {
        com.bisimplex.firebooru.view.SourceSpecsDialog v7_32;
        com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener v6_1 = new com.bisimplex.firebooru.model.SourceSpecs();
        if (this.this$0.typeSpinner.getSelectedItemPosition() != 0) {
            v7_32 = 1;
        } else {
            v7_32 = 3;
        }
        v6_1.setType(v7_32);
        com.bisimplex.firebooru.view.SourceSpecsDialog v7_11 = new com.bisimplex.firebooru.network.SourceQuery(this.this$0.queryEditText.getText().toString());
        v6_1.setQuery(v7_11);
        com.bisimplex.firebooru.view.SourceSpecsDialog v1_8 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.this$0.serverSpinner.getSelectedItem());
        if (v1_8.getServerId() > 0) {
            v6_1.setServer(v1_8);
            v6_1.setUrl(v1_8.getUrl());
            if (v6_1.getType() == 1) {
                v7_11.getExtraParams().put("FILTER_SERVER_URL", v6_1.getUrl());
            }
            v6_1.setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(v1_8));
        }
        v6_1.setKey(java.util.UUID.randomUUID().toString());
        if (this.this$0.groupInputLayout.getVisibility() != 0) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v6_1, this.val$parentGroupID);
            com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v6_1, 0);
            return;
        } else {
            if (com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetgroupSelectedPosition(this.this$0) < 0) {
                com.bisimplex.firebooru.view.SourceSpecsDialog v7_31 = this.this$0.groupEditText.getText().toString().trim();
                if (!android.text.TextUtils.isEmpty(v7_31)) {
                    com.bisimplex.firebooru.model.SourceSpecs v2_11 = new com.bisimplex.firebooru.model.SourceSpecs();
                    v2_11.setType(4);
                    v2_11.setQuery(new com.bisimplex.firebooru.network.SourceQuery(v7_31));
                    v2_11.setKey(java.util.UUID.randomUUID().toString());
                    com.bisimplex.firebooru.view.SourceSpecsDialog v7_36 = new java.util.ArrayList(1);
                    v2_11.setChilds(v7_36);
                    v7_36.add(v6_1);
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v2_11, 0);
                    com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v2_11, 0);
                    return;
                } else {
                    com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v6_1, 0);
                    com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v6_1, 0);
                    return;
                }
            } else {
                com.bisimplex.firebooru.view.SourceSpecsDialog v7_8 = ((com.bisimplex.firebooru.model.SourceSpecs) this.this$0.groupAdapter.getItem(com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetgroupSelectedPosition(this.this$0)));
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v6_1, v7_8.getKey());
                if (!android.text.TextUtils.isEmpty(v7_8.getKey())) {
                    com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v7_8, 1);
                    return;
                } else {
                    com.bisimplex.firebooru.view.SourceSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v6_1, 0);
                    return;
                }
            }
        }
    }
}
