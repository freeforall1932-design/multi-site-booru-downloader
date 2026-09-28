package com.bisimplex.firebooru.custom;
public class ExternalStorage {
    public static final String EXTERNAL_SD_CARD = "externalSdCard";
    public static final String SD_CARD = "sdCard";

    public ExternalStorage()
    {
        return;
    }

    public static java.util.Map getAllStorageLocations()
    {
        java.util.HashMap v0_1 = new java.util.HashMap(10);
        v0_1.put("sdCard", android.os.Environment.getExternalStorageDirectory());
        v0_1.put("externalSdCard", android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_PICTURES));
        return v0_1;
    }

    public static String getSdCardPath()
    {
        return new StringBuilder().append(android.os.Environment.getExternalStorageDirectory().getPath()).append("/").toString();
    }

    public static boolean isAvailable()
    {
        int v0_0 = android.os.Environment.getExternalStorageState();
        if ((!"mounted".equals(v0_0)) && (!"mounted_ro".equals(v0_0))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isWritable()
    {
        if (!"mounted".equals(android.os.Environment.getExternalStorageState())) {
            return 0;
        } else {
            return 1;
        }
    }
}
