package com.bisimplex.firebooru.fragment;
 class DownloadsFragment$10 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DownloadsFragment this$0;
    final synthetic int val$position;

    DownloadsFragment$10(com.bisimplex.firebooru.fragment.DownloadsFragment p1, int p2)
    {
        this.this$0 = p1;
        this.val$position = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$mexecuteAction(this.this$0, com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.Copy, this.val$position);
        return;
    }
}
