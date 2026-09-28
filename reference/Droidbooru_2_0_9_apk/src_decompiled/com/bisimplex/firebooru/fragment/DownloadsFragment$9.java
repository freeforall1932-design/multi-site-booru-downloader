package com.bisimplex.firebooru.fragment;
 class DownloadsFragment$9 implements com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener {
    final synthetic com.bisimplex.firebooru.fragment.DownloadsFragment this$0;

    DownloadsFragment$9(com.bisimplex.firebooru.fragment.DownloadsFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void changedEntryStatus(com.bisimplex.firebooru.model.DownloadEntry p1)
    {
        if ((!this.this$0.isDetached()) && (this.this$0.getActivity() != null)) {
            com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$mreloadAllData(this.this$0);
        }
        return;
    }

    public void downloadsFinished()
    {
        com.bisimplex.firebooru.fragment.DownloadsFragment.-$$Nest$mupdateMenuButtons(this.this$0);
        return;
    }
}
