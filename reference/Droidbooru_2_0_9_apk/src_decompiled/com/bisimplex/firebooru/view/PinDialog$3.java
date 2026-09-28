package com.bisimplex.firebooru.view;
 class PinDialog$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.PinDialog this$0;
    final synthetic java.util.List val$groups;
    final synthetic com.bisimplex.firebooru.network.SourcePostBasic val$source;

    PinDialog$3(com.bisimplex.firebooru.view.PinDialog p1, com.bisimplex.firebooru.network.SourcePostBasic p2, java.util.List p3)
    {
        this.this$0 = p1;
        this.val$source = p2;
        this.val$groups = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p4, int p5)
    {
        com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener v4_3 = com.bisimplex.firebooru.model.SourceSpecs.fromSource(this.val$source);
        com.bisimplex.firebooru.model.SourceSpecs v5_2 = ((com.bisimplex.firebooru.model.SourceSpecs) this.val$groups.get(p5));
        com.bisimplex.firebooru.view.PinDialog v0_0 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().similarSpecsExit(v4_3);
        if (v0_0 == null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().addSourceSpecs(v4_3, v5_2.getKey());
            com.bisimplex.firebooru.view.PinDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsSaved(this.this$0, v5_2, 1);
            return;
        } else {
            com.bisimplex.firebooru.view.PinDialog.-$$Nest$fgetmListener(this.this$0).onDialogSpecsDuplicated(this.this$0, v4_3, v5_2, v0_0);
            return;
        }
    }
}
