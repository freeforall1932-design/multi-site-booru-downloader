package com.bisimplex.firebooru.view;
 class RenameGroupSpecsDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.RenameGroupSpecsDialog this$0;
    final synthetic android.os.Bundle val$args;
    final synthetic android.widget.EditText val$editText;

    RenameGroupSpecsDialog$2(com.bisimplex.firebooru.view.RenameGroupSpecsDialog p1, android.os.Bundle p2, android.widget.EditText p3)
    {
        this.this$0 = p1;
        this.val$args = p2;
        this.val$editText = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.model.SourceSpecs v2_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().findSourceSpecsByID(this.val$args.getString("SOURCE_SPECS_ID"));
        if (v2_2 != null) {
            com.bisimplex.firebooru.view.RenameGroupSpecsDialog$RenameGroupSpecsDialogListener v3_2 = this.val$editText.getText().toString();
            if (android.text.TextUtils.isEmpty(v3_2)) {
                v3_2 = this.this$0.getContext().getString(2131886979);
            }
            v2_2.getQuery().setTitle(v3_2);
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().storeSpecs(v2_2);
            com.bisimplex.firebooru.view.RenameGroupSpecsDialog.-$$Nest$fgetmListener(this.this$0).onDialogGroupSpecsSaved(this.this$0, v2_2);
            return;
        } else {
            return;
        }
    }
}
