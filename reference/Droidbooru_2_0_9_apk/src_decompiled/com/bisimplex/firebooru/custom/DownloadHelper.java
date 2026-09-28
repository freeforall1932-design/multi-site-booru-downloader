package com.bisimplex.firebooru.custom;
public class DownloadHelper {
    private static com.bisimplex.firebooru.custom.DownloadHelper sharedInstance;
    private final android.os.FileObserver fileObserver;

    static DownloadHelper()
    {
        return;
    }

    private DownloadHelper()
    {
        com.bisimplex.firebooru.custom.DownloadHelper$1 v0_1 = new com.bisimplex.firebooru.custom.DownloadHelper$1(this, com.bisimplex.firebooru.danbooru.UserConfiguration.getTempDownloadPath());
        this.fileObserver = v0_1;
        v0_1.startWatching();
        return;
    }

    private void addPostToDownload(com.bisimplex.firebooru.danbooru.DanbooruPost p4, android.app.DownloadManager p5)
    {
        if ((p4 != null) && (p4.getFile() != null)) {
            Exception v4_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.requestForPost(p4, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getUserAgent(), this.refererForPost(p4), 0);
            if (v4_1 != null) {
                try {
                    p5.enqueue(v4_1);
                    return;
                } catch (Exception v4_2) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_2);
                }
            }
        }
        return;
    }

    public static com.bisimplex.firebooru.custom.DownloadHelper getInstance()
    {
        if (com.bisimplex.firebooru.custom.DownloadHelper.sharedInstance == null) {
            com.bisimplex.firebooru.custom.DownloadHelper.sharedInstance = new com.bisimplex.firebooru.custom.DownloadHelper();
        }
        return com.bisimplex.firebooru.custom.DownloadHelper.sharedInstance;
    }

    private String refererForPost(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if (p3 != null) {
            String v3_5 = android.net.Uri.parse(p3.getPostUrl());
            return String.format(java.util.Locale.US, "%s://%s/", new Object[] {v3_5.getScheme(), v3_5.getHost()}));
        } else {
            return "";
        }
    }

    public void addPostsToDownload(android.content.Context p2, java.util.List p3)
    {
        if ((p3 != null) && (!p3.isEmpty())) {
            android.app.DownloadManager v2_2 = ((android.app.DownloadManager) p2.getSystemService("download"));
            java.util.Iterator v3_1 = p3.iterator();
            while (v3_1.hasNext()) {
                this.addPostToDownload(((com.bisimplex.firebooru.danbooru.DanbooruPost) v3_1.next()), v2_2);
            }
        }
        return;
    }
}
