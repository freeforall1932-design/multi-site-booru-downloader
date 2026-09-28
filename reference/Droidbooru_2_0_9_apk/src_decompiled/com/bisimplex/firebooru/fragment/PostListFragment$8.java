package com.bisimplex.firebooru.fragment;
 class PostListFragment$8 implements android.content.DialogInterface$OnMultiChoiceClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic com.bisimplex.firebooru.backup.BackupCVSOptions val$options;

    PostListFragment$8(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.backup.BackupCVSOptions p2)
    {
        this.this$0 = p1;
        this.val$options = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2, boolean p3)
    {
        if (p2 != 0) {
            if (p2 != 1) {
                if (p2 == 2) {
                    this.val$options.setMD5(p3);
                }
                return;
            } else {
                this.val$options.setPostURL(p3);
                return;
            }
        } else {
            this.val$options.setFileURL(p3);
            return;
        }
    }
}
