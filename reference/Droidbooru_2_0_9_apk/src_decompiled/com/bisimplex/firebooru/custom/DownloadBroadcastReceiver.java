package com.bisimplex.firebooru.custom;
public class DownloadBroadcastReceiver extends android.content.BroadcastReceiver {

    public DownloadBroadcastReceiver()
    {
        return;
    }

    private boolean validDownload(long p6)
    {
        android.app.DownloadManager v0_2 = ((android.app.DownloadManager) com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getSystemService("download"));
        android.database.Cursor v1_4 = new android.app.DownloadManager$Query();
        int v2_5 = new long[1];
        v2_5[0] = p6;
        android.database.Cursor v1_1 = v0_2.query(v1_4.setFilterById(v2_5));
        if (v1_1.moveToFirst()) {
            if (v1_1.getInt(v1_1.getColumnIndex("status")) != 8) {
                v1_1.getInt(v1_1.getColumnIndex("reason"));
            } else {
                this.saveIt(v0_2.getUriForDownloadedFile(p6), v1_1.getString(v1_1.getColumnIndex("description")));
            }
        }
        return 0;
    }

    public boolean copy(java.io.File p5, java.io.File p6)
    {
        if (p5 != 0) {
            java.io.FileInputStream v1_1 = new java.io.FileInputStream(p5);
            int v5_5 = new java.io.FileOutputStream(p6, 0);
            byte[] v2_1 = new byte[1024];
            while(true) {
                int v3 = v1_1.read(v2_1);
                if (v3 <= 0) {
                    break;
                }
                v5_5.write(v2_1, 0, v3);
            }
            v1_1.close();
            v5_5.close();
            int v5_2 = new android.content.Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            v5_2.setData(android.net.Uri.fromFile(p6));
            com.bisimplex.firebooru.DroidBooruApplication.getAppContext().sendBroadcast(v5_2);
            return 1;
        } else {
            return 0;
        }
    }

    public void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(p3.getAction())) {
            this.validDownload(p3.getExtras().getLong("extra_download_id"));
        }
        return;
    }

    public void saveIt(android.net.Uri p4, String p5)
    {
        byte[] v0_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSDRootDirectory();
        int v1_2 = new java.io.File(p4.getPath());
        if (v0_5 != null) {
            try {
                this.copy(v1_2, new java.io.File(v0_5, p4.getPath()));
            } catch (java.io.IOException) {
            }
            return;
        } else {
            byte[] v0_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getRootDocumentFile();
            if ((v0_2 != null) && (v0_2.canWrite())) {
                try {
                    com.bisimplex.firebooru.network.Utils v5_4 = com.bisimplex.firebooru.DroidBooruApplication.getAppContext().getContentResolver().openOutputStream(v0_2.createFile("image", p5).getUri());
                    androidx.documentfile.provider.DocumentFile.fromTreeUri(com.bisimplex.firebooru.DroidBooruApplication.getAppContext(), p4);
                } catch (java.io.FileInputStream v4_7) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_7);
                } catch (java.io.FileInputStream v4_6) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v4_6);
                    return;
                }
                if ((v1_2.exists()) && ((v1_2.canRead()) && (v5_4 != null))) {
                    java.io.FileInputStream v4_5 = new java.io.FileInputStream(v1_2);
                    byte[] v0_8 = new byte[1024];
                    while(true) {
                        int v1_1 = v4_5.read(v0_8);
                        if (v1_1 <= 0) {
                            break;
                        }
                        v5_4.write(v0_8, 0, v1_1);
                    }
                    v4_5.close();
                    v5_4.close();
                    return;
                }
            }
            return;
        }
    }
}
