package com.bisimplex.firebooru.fragment;
 class PostListFragment$7 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic com.bisimplex.firebooru.backup.BackupCVSOptions val$options;

    PostListFragment$7(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.backup.BackupCVSOptions p2)
    {
        this.this$0 = p1;
        this.val$options = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        if ((!this.val$options.isPostURL()) && ((!this.val$options.isFileURL()) && (!this.val$options.isMD5()))) {
            this.this$0.showMessage(2131886503, com.bisimplex.firebooru.activity.MessageType.Error);
            com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$fputbackupCVSOptions(this.this$0, 0);
            return;
        } else {
            com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$mtriggerStorageAccessFramework(this.this$0, 2);
            return;
        }
    }
}
