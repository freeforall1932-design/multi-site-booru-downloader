package com.bisimplex.firebooru.view;
 class GroupSpecsDialog$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.GroupSpecsDialog this$0;
    final synthetic android.os.Bundle val$args;
    final synthetic android.widget.AutoCompleteTextView val$autoCompleteTextView;
    final synthetic java.util.List val$groups;

    GroupSpecsDialog$3(com.bisimplex.firebooru.view.GroupSpecsDialog p1, android.os.Bundle p2, java.util.List p3, android.widget.AutoCompleteTextView p4)
    {
        this.this$0 = p1;
        this.val$args = p2;
        this.val$groups = p3;
        this.val$autoCompleteTextView = p4;
        return;
    }

    public void onClick(android.content.DialogInterface p5, int p6)
    {
        String v5_1 = this.val$args.getString("SOURCE_SPECS_CHILD_ID");
        com.bisimplex.firebooru.view.GroupSpecsDialog$GroupSpecsDialogListener v6_9 = this.val$args.getString("SOURCE_SPECS_PARENT_ID", 0);
        if (com.bisimplex.firebooru.view.GroupSpecsDialog.-$$Nest$fgetselectedGroupIndex(this.this$0) < 0) {
            com.bisimplex.firebooru.model.SourceSpecs v0_2 = new com.bisimplex.firebooru.model.SourceSpecs();
            v0_2.setType(4);
            com.bisimplex.firebooru.view.GroupSpecsDialog v2_3 = this.val$autoCompleteTextView.getText().toString();
            if (android.text.TextUtils.isEmpty(v2_3)) {
                v2_3 = this.this$0.getContext().getString(2131886979);
            }
            v0_2.setQuery(new com.bisimplex.firebooru.network.SourceQuery(v2_3));
            v0_2.setKey(java.util.UUID.randomUUID().toString());
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addGroupSpecs(v0_2, v6_9, v5_1);
            if (!android.text.TextUtils.isEmpty(v6_9)) {
                com.bisimplex.firebooru.view.GroupSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogGroupSpecsSaved(this.this$0, v0_2, v5_1, 1);
                return;
            } else {
                com.bisimplex.firebooru.view.GroupSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogGroupSpecsSaved(this.this$0, v0_2, v5_1, 0);
                return;
            }
        } else {
            com.bisimplex.firebooru.model.SourceSpecs v0_6 = ((com.bisimplex.firebooru.model.SourceSpecs) this.val$groups.get(com.bisimplex.firebooru.view.GroupSpecsDialog.-$$Nest$fgetselectedGroupIndex(this.this$0)));
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().moveToGroupSpecs(v0_6, v6_9, v5_1);
            com.bisimplex.firebooru.view.GroupSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogGroupSpecsSaved(this.this$0, v0_6, v5_1, 1);
            return;
        }
    }
}
