package com.bisimplex.firebooru.danbooru;
public class UserConfiguration {
    public static final String ASK_CRASH_REPORTS = "ASK_CRASH_REPORTS";
    public static final String AUTOCOMPLETE_FROM_SERVER = "AUTOCOMPLETE_FROM_SERVER";
    public static final String COMBINE_MULTI_SEARCH = "CombineMultiSearch";
    public static final String FAV_SYNC_DANBOORU_2 = "FavSyncDanbooru2";
    public static final String LAST_VERSION_CHECKED = "lastVersionChecked";
    private static String LOCK_KEY = "LOCK_KEY";
    public static final String MATERIAL_THEME_TYPE = "MaterialThemeType";
    public static String NEWVERSIONAPP = "NEWVERSIONAPP";
    public static int PRELOAD_TYPE_ALWAYS = 1;
    public static int PRELOAD_TYPE_DISABLED = 1;
    public static int PRELOAD_TYPE_WIFI = 0;
    public static final String SD_PATH = "sdPath";
    public static final String SHOW_VIEWER_NAVIGATION_BUTTONS = "ic_ShowViewerNavigationButtons";
    public static final String UC_AUTO_LOAD_NOTES = "uc_AUTO_LOAD_NOTES";
    public static final String UC_AUTO_LOAD_PINS = "uc_auto_load_pins";
    public static final String UC_DNS_TYPE = "uc_DNS_type";
    public static final String UC_DOUBLE_TAP_CMD = "uc_doubleTap_cmd";
    public static final String UC_FILE_NAME_CONFIGURATION = "uc_fileNameConfiguration";
    public static final String UC_FILE_NAME_TYPE = "uc_file_name_type";
    public static final String UC_FIX_GELBOORU_VIDEO_LINKS = "uc_fix_gelbooru_video_links";
    public static final String UC_HIDE_FAVORITES = "uc_hide_favorites";
    public static final String UC_INCLUDE_BLACKLISTED = "uc_include_blacklisted";
    public static final String UC_LIMIT_IMAGE_SIZE = "uc_limit_image_size";
    public static final String UC_LONG_TAP_TO_DOWNLOAD = "uc_longTapToDownload";
    public static final String UC_MULTI_SEARCH_IDS = "uc_multi_search_ids";
    public static final String UC_PROXY = "uc_Proxy";
    public static final String UC_SAVE_SPECS = "uc_specs_list";
    public static final String UC_SCREEN_ON_VIEWER = "uc_screen_on_viewer";
    public static final String UC_SECURE_SCREEN = "uc_secure_screen";
    public static final String UC_SERVER_WITH_COOKIES = "uc_server_with_cookies";
    public static final String UC_STATUS_BAR_VISIBLE = "uc_status_bar_visible";
    public static final String UC_STREAM_VIDEO = "uc_streamVideo";
    public static final String UC_SWIPE_DOWN_CMD = "uc_swipeDown_cmd";
    public static final String UC_SWIPE_UP_CMD = "uc_swipeUp_cmd";
    public static final String UC_THUMB_DISPLAY_MODE = "uc_ThumbDisplayMode";
    public static final String UC_USER_HIDE_NAV_BARS = "uc_user_hide_nav_bars";
    public static final String UC_USER_TOP_BAR = "UC_user_top_bar";
    public static final String UC_VIDEO_DECODER = "uc_VideoDecoder_1";
    public static final String UC_VOLUME_NAVIGATION = "uc_VOLUME_NAVIGATION";
    private static com.bisimplex.firebooru.danbooru.UserConfiguration sharedInstance;
    private boolean __showLock;
    private boolean allowFlagOnDefaultServer;
    private boolean allowPurchase;
    private String basicUserAgent;
    private boolean checkedAppVersion;
    private android.content.Context context;
    private int currentAppVersionCode;
    private com.bisimplex.firebooru.danbooru.ServerItem defaultServerSettings;
    private java.util.List localSourceSpecs;
    private String marketUrl;
    private int maxImageSize;
    private boolean needGPUImageRefresh;
    private boolean needRestartProxyChange;
    private android.content.SharedPreferences preferences;
    private com.bisimplex.firebooru.danbooru.QualityRenderType qualityRender;
    private com.bisimplex.firebooru.custom.SecurePreferences securePreferences;
    private String userAgent;

    static bridge synthetic int -$$Nest$fgetcurrentAppVersionCode(com.bisimplex.firebooru.danbooru.UserConfiguration p0)
    {
        return p0.currentAppVersionCode;
    }

    static bridge synthetic android.content.SharedPreferences -$$Nest$fgetpreferences(com.bisimplex.firebooru.danbooru.UserConfiguration p0)
    {
        return p0.preferences;
    }

    static UserConfiguration()
    {
        return;
    }

    protected UserConfiguration()
    {
        this.needGPUImageRefresh = 1;
        this.__showLock = 1;
        this.setContext(com.bisimplex.firebooru.DroidBooruApplication.getAppContext());
        try {
            this.allowPurchase = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData.getBoolean("PURCHASE_ENABLED", 0);
            return;
        } catch (Exception v0_4) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_4);
            return;
        }
    }

    private com.bisimplex.firebooru.model.SourceSpecs findSimilarSpecs(com.bisimplex.firebooru.model.SourceSpecs p5, java.util.List p6)
    {
        if ((p5 != 0) && (p6 != null)) {
            java.util.Iterator v6_1 = p6.iterator();
            while (v6_1.hasNext()) {
                com.bisimplex.firebooru.model.SourceSpecs v1_0 = ((com.bisimplex.firebooru.model.SourceSpecs) v6_1.next());
                if (v1_0.getType() != 4) {
                    if ((v1_0.getType() != 0) && (p5.isEqualTo(v1_0))) {
                    }
                } else {
                    boolean v2_4 = this.findSimilarSpecs(p5, v1_0.getChilds());
                    if (v2_4) {
                        if (v2_4.getType() == 4) {
                            return v2_4;
                        }
                    }
                }
                return v1_0;
            }
        }
        return 0;
    }

    private int findSourceSpecsInList(java.util.List p3, String p4)
    {
        if ((p3 != 0) && (!p3.isEmpty())) {
            int v0_0 = 0;
            while (v0_0 < p3.size()) {
                if (!((com.bisimplex.firebooru.model.SourceSpecs) p3.get(v0_0)).getKey().equalsIgnoreCase(p4)) {
                    v0_0++;
                } else {
                    return v0_0;
                }
            }
        }
        return -1;
    }

    private void firstLaunchInVersion()
    {
        try {
            java.net.MalformedURLException v0_2 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerByUrl(new java.net.URL("https://e621.net"));
        } catch (java.net.MalformedURLException v0_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
            return;
        }
        if (v0_2 != null) {
            if (v0_2.getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                v0_2.setType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621);
                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addServer(v0_2);
                if (v0_2.isSelected()) {
                    com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().setServerDescription(v0_2);
                }
            }
        } else {
        }
        return;
    }

    private boolean getAlwaysPreload()
    {
        return this.preferences.getBoolean("AlwaysPreload", 0);
    }

    public static android.net.Uri getDownloadURI(com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        java.io.File v0_1 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSDRootDirectory();
        if (v0_1 == null) {
            androidx.documentfile.provider.DocumentFile v1_4 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getRootDocumentFile();
            if ((v1_4 != null) && (v1_4.canWrite())) {
                String v2_1 = p4.generateFileName();
                String v3_0 = v1_4.findFile(v2_1);
                if (v3_0 == null) {
                    androidx.documentfile.provider.DocumentFile v1_0 = v1_4.createFile("image", v2_1);
                    if (v1_0 != null) {
                        return v1_0.getUri();
                    }
                } else {
                    return v3_0.getUri();
                }
            } else {
                return 0;
            }
        }
        return android.net.Uri.fromFile(new java.io.File(v0_1, p4.generateFileName()));
    }

    private long getFolderSize(java.io.File p7)
    {
        long v1 = 0;
        if (p7.exists()) {
            java.io.File[] v7_1 = p7.listFiles();
            if (v7_1 != null) {
                int v0_1 = 0;
                while (v0_1 < v7_1.length) {
                    if (!v7_1[v0_1].isDirectory()) {
                        v1 += v7_1[v0_1].length();
                        android.util.Log.i("Cache", String.format("File %s is [%d]", new Object[] {v7_1[v0_1].getAbsolutePath(), Long.valueOf(v7_1[v0_1].length())})));
                    } else {
                        v1 += this.getFolderSize(v7_1[v0_1]);
                    }
                    v0_1++;
                }
            } else {
                return 0;
            }
        }
        return v1;
    }

    public static com.bisimplex.firebooru.danbooru.UserConfiguration getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.sharedInstance = new com.bisimplex.firebooru.danbooru.UserConfiguration();
        }
        return com.bisimplex.firebooru.danbooru.UserConfiguration.sharedInstance;
    }

    private String getRootExternalSDCard()
    {
        String[] v0_1 = System.getenv("SECONDARY_STORAGE");
        if (!android.text.TextUtils.isEmpty(v0_1)) {
            String[] v0_2 = v0_1.split(java.io.File.pathSeparator);
            int v3 = 0;
            while (v3 < v0_2.length) {
                String v4 = v0_2[v3];
                if (!android.text.TextUtils.isEmpty(v4)) {
                    return v4;
                } else {
                    v3++;
                }
            }
            return "";
        } else {
            return "";
        }
    }

    public static String getTempDownloadPath()
    {
        return new java.io.File(android.os.Environment.getDownloadCacheDirectory(), "TempDownload").getPath();
    }

    public static android.app.DownloadManager$Request requestForPost(com.bisimplex.firebooru.danbooru.DanbooruPost p3, String p4, String p5, String p6)
    {
        if (p3 != null) {
            String v0_1 = p3.getFile().getUrl();
            android.app.DownloadManager$Request v1_1 = new android.app.DownloadManager$Request(android.net.Uri.parse(v0_1));
            v1_1.addRequestHeader("Referer", p5);
            v1_1.addRequestHeader("User-Agent", p4);
            if (android.text.TextUtils.isEmpty(p3.getPostUrl())) {
                v1_1.setDescription(v0_1);
            } else {
                v1_1.setDescription(p3.getPostUrl());
            }
            String v4_4 = p3.getMd5();
            if (android.text.TextUtils.isEmpty(v4_4)) {
                if (!android.text.TextUtils.isEmpty(p3.getPostId())) {
                    v1_1.setTitle(p3.getPostId());
                }
            } else {
                v1_1.setTitle(v4_4);
            }
            String v4_12;
            v1_1.allowScanningByMediaScanner();
            v1_1.setNotificationVisibility(0);
            v1_1.setVisibleInDownloadsUi(1);
            String v3_1 = p3.generateDownloadFileName();
            if (!android.text.TextUtils.isEmpty(p6)) {
                v4_12 = String.format(java.util.Locale.US, "%s/%s/", new Object[] {android.os.Environment.DIRECTORY_PICTURES, p6}));
            } else {
                v4_12 = android.os.Environment.DIRECTORY_PICTURES;
            }
            v1_1.setDestinationInExternalPublicDir(v4_12, v3_1);
            return v1_1;
        } else {
            return 0;
        }
    }

    public static String sanitizeFolderName(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            String v2_3 = p2.trim().replace("*", "");
            if (!android.text.TextUtils.isEmpty(v2_3)) {
                return v2_3.replaceAll("[^a-zA-Z0-9\\._]+", "_");
            } else {
                return v2_3;
            }
        } else {
            return p2;
        }
    }

    private void setAlwaysPreload(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("AlwaysPreload", p3);
        v0_1.apply();
        return;
    }

    private void setIncludeBlacklisted(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_include_blacklisted", p3).apply();
        return;
    }

    private void versionCheck()
    {
        if (this.preferences.getInt("uc_version_check", 0) < this.currentAppVersionCode) {
            this.firstLaunchInVersion();
            this.preferences.edit().putInt("uc_version_check", this.currentAppVersionCode).apply();
        }
        return;
    }

    public void addGroupSpecs(com.bisimplex.firebooru.model.SourceSpecs p4, String p5, String p6)
    {
        java.util.List v0 = this.getSourceSpecs();
        if (!android.text.TextUtils.isEmpty(p5)) {
            com.bisimplex.firebooru.model.SourceSpecs v5_7 = this.findSourceSpecsByID(p5);
            if (v5_7 != null) {
                java.util.ArrayList v6_1 = this.findSourceSpecsInList(v5_7.getChilds(), p6);
                if (v6_1 >= null) {
                    com.bisimplex.firebooru.model.SourceSpecs v5_3 = ((com.bisimplex.firebooru.model.SourceSpecs) v5_7.getChilds().remove(v6_1));
                    java.util.ArrayList v6_3 = new java.util.ArrayList(1);
                    v6_3.add(v5_3);
                    p4.setChilds(v6_3);
                    v0.add(p4);
                    this.setSourceSpecs(v0);
                }
            }
        } else {
            com.bisimplex.firebooru.model.SourceSpecs v5_4 = this.findSourceSpecsInList(v0, p6);
            if (v5_4 >= null) {
                com.bisimplex.firebooru.model.SourceSpecs v5_6 = ((com.bisimplex.firebooru.model.SourceSpecs) v0.remove(v5_4));
                java.util.ArrayList v6_5 = new java.util.ArrayList(1);
                v6_5.add(v5_6);
                p4.setChilds(v6_5);
                v0.add(p4);
                this.setSourceSpecs(v0);
                return;
            }
        }
        return;
    }

    public void addServerWithCookies(String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            String v0_5 = this.preferences.getString("uc_server_with_cookies", "");
            if (!v0_5.contains(p4)) {
                StringBuilder v1_1 = new StringBuilder(v0_5);
                if (!android.text.TextUtils.isEmpty(v0_5)) {
                    v1_1.append(",");
                }
                v1_1.append(p4);
                this.preferences.edit().putString("uc_server_with_cookies", v1_1.toString()).apply();
                return;
            }
        }
        return;
    }

    public void addSourceSpecs(com.bisimplex.firebooru.model.SourceSpecs p3)
    {
        if (p3 != null) {
            java.util.List v0 = this.getSourceSpecs();
            int v1_1 = this.findSourceSpecsInList(v0, p3.getKey());
            if (v1_1 >= 0) {
                v0.set(v1_1, p3);
            } else {
                v0.add(p3);
            }
            this.setSourceSpecs(v0);
            return;
        } else {
            return;
        }
    }

    public void addSourceSpecs(com.bisimplex.firebooru.model.SourceSpecs p3, String p4)
    {
        if (p3 != null) {
            if (!android.text.TextUtils.isEmpty(p4)) {
                int v4_3 = this.findSourceSpecsByID(p4);
                java.util.ArrayList v0_0 = v4_3.getChilds();
                if (v0_0 == null) {
                    v0_0 = new java.util.ArrayList(1);
                    v4_3.setChilds(v0_0);
                }
                int v4_2 = this.findSourceSpecsInList(v0_0, p3.getKey());
                if (v4_2 >= 0) {
                    v0_0.set(v4_2, p3);
                } else {
                    v0_0.add(p3);
                }
            } else {
                this.addSourceSpecs(p3);
            }
            this.setSourceSpecs(this.getSourceSpecs());
            return;
        } else {
            return;
        }
    }

    public boolean appUpdateAvailable()
    {
        if (this.preferences.getInt("lastVersionChecked", 0) <= this.currentAppVersionCode) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean askForCrashReport()
    {
        return this.preferences.getBoolean("ASK_CRASH_REPORTS", 1);
    }

    public void checkAppVersion()
    {
        if (!this.checkedAppVersion) {
            this.checkedAppVersion = 1;
            com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().newCall(new okhttp3.Request$Builder().url("https://www.animebox.es/def.json").build()).enqueue(new com.bisimplex.firebooru.danbooru.UserConfiguration$1(this));
            return;
        } else {
            return;
        }
    }

    public void checkUserAgent()
    {
        try {
            this.userAgent = new android.webkit.WebView(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).getSettings().getUserAgentString();
            Exception v0_1 = this.preferences.edit();
            v0_1.putString("default_ua", this.userAgent);
            v0_1.apply();
            return;
        } catch (Exception v0_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_2);
            return;
        }
    }

    public void cleanLastVersionCheck()
    {
        android.content.SharedPreferences$Editor v0_0 = this.preferences;
        if (v0_0 != null) {
            v0_0.edit().remove("lastVersionChecked").apply();
        }
        return;
    }

    public void deleteServiceProgress(String p2)
    {
        if (this.preferences.contains(p2)) {
            this.preferences.edit().remove(p2).apply();
            return;
        } else {
            return;
        }
    }

    public void deleteSourceSpecs(com.bisimplex.firebooru.model.SourceSpecs p4, String p5)
    {
        java.util.List v5_2;
        if (!android.text.TextUtils.isEmpty(p5)) {
            v5_2 = this.findSourceSpecsByID(p5).getChilds();
            if (v5_2 != null) {
                int v0_1 = 0;
                while (v0_1 < v5_2.size()) {
                    if (!((com.bisimplex.firebooru.model.SourceSpecs) v5_2.get(v0_1)).getKey().equalsIgnoreCase(p4.getKey())) {
                        v0_1++;
                    }
                    if (v0_1 >= 0) {
                        v5_2.remove(v0_1);
                        this.setSourceSpecs(this.getSourceSpecs());
                    }
                }
                v0_1 = -1;
            }
        } else {
            v5_2 = this.getSourceSpecs();
        }
        return;
    }

    public String dirSize(java.util.List p9)
    {
        String v9_1 = p9.iterator();
        String v0_0 = 0;
        while (v9_1.hasNext()) {
            String v2_9 = ((java.io.File) v9_1.next());
            if (v2_9.exists()) {
                v0_0 += this.getFolderSize(v2_9);
            }
        }
        if (v0_0 >= 1024) {
            String v0_4 = ((double) v0_0);
            String v9_2 = ((int) (Math.log(v0_4) / Math.log(4652218415073722368)));
            return String.format(java.util.Locale.US, "%.2f %sB", new Object[] {Double.valueOf((v0_4 / Math.pow(4652218415073722368, ((double) v9_2)))), new StringBuilder().append("KMGTPE".charAt((v9_2 - 1))).append("").toString()}));
        } else {
            return new StringBuilder().append(v0_0).append(" B").toString();
        }
    }

    public boolean existLock()
    {
        return this.securePreferences.containsKey(com.bisimplex.firebooru.danbooru.UserConfiguration.LOCK_KEY);
    }

    public com.bisimplex.firebooru.model.SourceSpecs findSourceSpecsByID(String p4)
    {
        java.util.List v0 = this.getSourceSpecs();
        int v1 = 0;
        while (v1 < v0.size()) {
            if (!((com.bisimplex.firebooru.model.SourceSpecs) v0.get(v1)).getKey().equalsIgnoreCase(p4)) {
                v1++;
            }
            if (v1 < 0) {
                return 0;
            } else {
                return ((com.bisimplex.firebooru.model.SourceSpecs) v0.get(v1));
            }
        }
        v1 = -1;
    }

    public com.bisimplex.firebooru.model.SourceSpecs findSourceSpecsByID(String p3, String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.List v4_2 = this.findSourceSpecsByID(p4);
            if (v4_2 != null) {
                com.bisimplex.firebooru.model.SourceSpecs v3_1 = this.findSourceSpecsInList(v4_2.getChilds(), p3);
                if (v3_1 >= null) {
                    return ((com.bisimplex.firebooru.model.SourceSpecs) v4_2.getChilds().get(v3_1));
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return this.findSourceSpecsByID(p3);
        }
    }

    public boolean fixGelbooruVideoLinks()
    {
        return this.preferences.getBoolean("uc_fix_gelbooru_video_links", 1);
    }

    public boolean getAllowPreload(boolean p4)
    {
        int v0 = this.getPreloadType();
        if (v0 != com.bisimplex.firebooru.danbooru.UserConfiguration.PRELOAD_TYPE_ALWAYS) {
            if ((v0 != com.bisimplex.firebooru.danbooru.UserConfiguration.PRELOAD_TYPE_WIFI) || (p4 == 0)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public boolean getAllowPurchase()
    {
        return this.allowPurchase;
    }

    public String getBasicUserAgent()
    {
        if (android.text.TextUtils.isEmpty(this.basicUserAgent)) {
            this.basicUserAgent = String.format("Anime boxes/%s (by Bisimplex)", new Object[] {"2.0.9"}));
        }
        return this.basicUserAgent;
    }

    public com.bisimplex.firebooru.model.DNSType getDNSType()
    {
        return com.bisimplex.firebooru.model.DNSType.fromInteger(this.preferences.getInt("uc_DNS_type", com.bisimplex.firebooru.model.DNSType.System.getValue()));
    }

    public String getDatabaseName()
    {
        try {
            return this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData.getString("AA_DB_NAME");
        } catch (int v0_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_2);
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getDefaultServerSettings()
    {
        if (this.defaultServerSettings == null) {
            try {
                Exception v0_1 = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
                this.allowFlagOnDefaultServer = v0_1.metaData.getBoolean("ALLOW_FLAG", 0);
                com.bisimplex.firebooru.network.Utils v1_3 = new com.bisimplex.firebooru.danbooru.ServerItem();
                v1_3.setUrl(v0_1.metaData.getString("DEFAULT_SERVER_URL"));
                v1_3.setServerName(v0_1.metaData.getString("DEFAULT_SERVER_NAME"));
                v1_3.setType(com.bisimplex.firebooru.danbooru.ServerItemType.fromInteger(v0_1.metaData.getInt("DEFAULT_SERVER_TYPE")));
                this.defaultServerSettings = v1_3;
            } catch (Exception v0_6) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_6);
            }
        }
        return this.defaultServerSettings;
    }

    public int getDeviceMaxImageSize()
    {
        if (this.maxImageSize == 0) {
            if (this.context.getResources().getDisplayMetrics().density <= 1073741824) {
                this.maxImageSize = 2048;
            } else {
                this.maxImageSize = 4096;
            }
        }
        return this.maxImageSize;
    }

    public com.bisimplex.firebooru.model.ViewerCommandType getDoubleTapCommand()
    {
        return com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(this.preferences.getInt("uc_doubleTap_cmd", com.bisimplex.firebooru.model.ViewerCommandType.None.getValue()));
    }

    public String getExternalSDPath()
    {
        if (!this.isExternalSDCardAvailable()) {
            return "";
        } else {
            return new StringBuilder().append(this.getRootExternalSDCard()).append(java.io.File.separator).append(this.getFolderName()).toString();
        }
    }

    public java.util.List getFileNameConfiguration()
    {
        java.util.List v0_3 = this.preferences.getString("uc_fileNameConfiguration", "");
        if (!android.text.TextUtils.isEmpty(v0_3)) {
            return com.bisimplex.firebooru.model.FileNamePartType.fromCSV(v0_3);
        } else {
            return com.bisimplex.firebooru.model.FileNamePartType.fromFileNameType(this.getFileNameType());
        }
    }

    public com.bisimplex.firebooru.danbooru.FileNameType getFileNameType()
    {
        return com.bisimplex.firebooru.danbooru.FileNameType.fromInteger(this.preferences.getInt("uc_file_name_type", com.bisimplex.firebooru.danbooru.FileNameType.MD5.getValue()));
    }

    public String getFolderName()
    {
        return "anime boxes";
    }

    public java.util.List getGroupSourceSpecs()
    {
        java.util.Iterator v0_0 = this.getSourceSpecs();
        java.util.ArrayList v1_1 = new java.util.ArrayList();
        java.util.Iterator v0_1 = v0_0.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.model.SourceSpecs v2_1 = ((com.bisimplex.firebooru.model.SourceSpecs) v0_1.next());
            if (v2_1.getType() == 4) {
                v1_1.add(v2_1);
            }
        }
        return v1_1;
    }

    public int[] getLockInfo()
    {
        return this.toIntArray(((java.util.List) new com.google.gson.Gson().fromJson(this.securePreferences.getString(com.bisimplex.firebooru.danbooru.UserConfiguration.LOCK_KEY), new java.util.ArrayList(0).getClass())));
    }

    public String getMarketUrlFormat()
    {
        if (this.marketUrl == null) {
            try {
                this.marketUrl = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData.getString("MARKET_FORMAT_URL");
            } catch (Exception v0_4) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_4);
            }
        }
        return this.marketUrl;
    }

    public java.util.List getMultiSearchSelectedServerIds()
    {
        String[] v0_3 = this.preferences.getString("uc_multi_search_ids", "");
        if (!android.text.TextUtils.isEmpty(v0_3)) {
            String[] v0_1 = v0_3.split(",");
            java.util.ArrayList v1_2 = new java.util.ArrayList();
            int v2_0 = v0_1.length;
            int v3 = 0;
            while (v3 < v2_0) {
                Integer v4_0 = v0_1[v3];
                if (!android.text.TextUtils.isEmpty(v4_0)) {
                    v1_2.add(Integer.valueOf(Integer.parseInt(v4_0)));
                }
                v3++;
            }
            return v1_2;
        } else {
            return 0;
        }
    }

    public String getOtherAppPackageName()
    {
        try {
            return this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128).metaData.getString("OTHER_ANIMEBOXES_PACKAGE_NAME");
        } catch (int v0_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_2);
            return 0;
        }
    }

    public int getPreloadCount()
    {
        return this.preferences.getInt("uc_preload_count", 1);
    }

    public int getPreloadType()
    {
        return this.preferences.getInt("PreloadType", com.bisimplex.firebooru.danbooru.UserConfiguration.PRELOAD_TYPE_WIFI);
    }

    public com.bisimplex.firebooru.model.ProxyConfiguration getProxyConfiguration()
    {
        com.bisimplex.firebooru.model.ProxyConfiguration v0_4 = this.securePreferences.getString("uc_Proxy");
        if (!android.text.TextUtils.isEmpty(v0_4)) {
            return ((com.bisimplex.firebooru.model.ProxyConfiguration) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_4, com.bisimplex.firebooru.model.ProxyConfiguration));
        } else {
            return 0;
        }
    }

    public com.bisimplex.firebooru.danbooru.QualityRenderType getQualityRender()
    {
        if (this.qualityRender == null) {
            this.qualityRender = com.bisimplex.firebooru.danbooru.QualityRenderType.Low;
        }
        return this.qualityRender;
    }

    public androidx.documentfile.provider.DocumentFile getRootDocumentFile()
    {
        IllegalArgumentException v0_2 = android.net.Uri.parse(this.getSDPath());
        if (v0_2 != null) {
            try {
                return androidx.documentfile.provider.DocumentFile.fromTreeUri(this.context, v0_2);
            } catch (IllegalArgumentException v0_1) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_1);
                return 0;
            }
        } else {
            return 0;
        }
    }

    public String getSDPath()
    {
        return this.preferences.getString("sdPath", "");
    }

    public java.io.File getSDRootDirectory()
    {
        int v0_2 = new java.io.File(this.getSDPath());
        if ((!v0_2.exists()) && (!v0_2.mkdirs())) {
            android.util.Log.e("tasks", "Cannot create save directory");
            v0_2 = 0;
        }
        return v0_2;
    }

    public com.bisimplex.firebooru.model.DownloadServiceProgress getServiceProgress(String p3)
    {
        com.google.gson.Gson v0_0 = this.preferences;
        if (v0_0 != null) {
            com.bisimplex.firebooru.model.DownloadServiceProgress v3_7 = v0_0.getString(p3, "");
            if (!android.text.TextUtils.isEmpty(v3_7)) {
                return ((com.bisimplex.firebooru.model.DownloadServiceProgress) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v3_7, com.bisimplex.firebooru.model.DownloadServiceProgress));
            } else {
                return new com.bisimplex.firebooru.model.DownloadServiceProgress();
            }
        } else {
            return new com.bisimplex.firebooru.model.DownloadServiceProgress();
        }
    }

    public java.util.List getSourceSpecs()
    {
        if (this.localSourceSpecs == null) {
            this.localSourceSpecs = this.loadSourceSpecs();
        }
        return this.localSourceSpecs;
    }

    public java.util.List getSourceSpecsByGroupID(String p4)
    {
        java.util.List v0 = this.getSourceSpecs();
        if (!android.text.TextUtils.isEmpty(p4)) {
            int v1_0 = 0;
            while (v1_0 < v0.size()) {
                if (!((com.bisimplex.firebooru.model.SourceSpecs) v0.get(v1_0)).getKey().equalsIgnoreCase(p4)) {
                    v1_0++;
                }
                if (v1_0 < 0) {
                    return new java.util.ArrayList();
                } else {
                    java.util.ArrayList v4_5 = ((com.bisimplex.firebooru.model.SourceSpecs) v0.get(v1_0)).getChilds();
                    if (v4_5 == null) {
                        v4_5 = new java.util.ArrayList();
                    }
                    return v4_5;
                }
            }
            v1_0 = -1;
        } else {
            return v0;
        }
    }

    public com.bisimplex.firebooru.model.ViewerCommandType getSwipeDownCommand()
    {
        return com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(this.preferences.getInt("uc_swipeDown_cmd", com.bisimplex.firebooru.model.ViewerCommandType.None.getValue()));
    }

    public com.bisimplex.firebooru.model.ViewerCommandType getSwipeUpCommand()
    {
        return com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(this.preferences.getInt("uc_swipeUp_cmd", com.bisimplex.firebooru.model.ViewerCommandType.None.getValue()));
    }

    public com.bisimplex.firebooru.custom.ThemeType getThemeSelected()
    {
        return com.bisimplex.firebooru.custom.ThemeType.fromInteger(this.preferences.getInt("MaterialThemeType", com.bisimplex.firebooru.custom.ThemeType.Dark.getValue()));
    }

    public com.bisimplex.firebooru.custom.ThumbDisplayMode getThumbDisplayMode()
    {
        return com.bisimplex.firebooru.custom.ThumbDisplayMode.fromInteger(this.preferences.getInt("uc_ThumbDisplayMode", com.bisimplex.firebooru.custom.ThumbDisplayMode.Classic.getValue()));
    }

    public String getUserAgent()
    {
        if (android.text.TextUtils.isEmpty(this.userAgent)) {
            String v0_5 = this.preferences.getString("default_ua", "");
            this.userAgent = v0_5;
            if (android.text.TextUtils.isEmpty(v0_5)) {
                this.checkUserAgent();
            }
        }
        return this.userAgent;
    }

    public com.bisimplex.firebooru.model.VideoDecoderType getVideoDecoder()
    {
        return com.bisimplex.firebooru.model.VideoDecoderType.fromInteger(this.preferences.getInt("uc_VideoDecoder_1", com.bisimplex.firebooru.model.VideoDecoderType.Base.getValue()));
    }

    public boolean getYumekui()
    {
        return this.preferences.getBoolean("yumekui", 0);
    }

    public boolean hideFavorites()
    {
        return this.preferences.getBoolean("uc_hide_favorites", 0);
    }

    public boolean hideNavBars()
    {
        return this.preferences.getBoolean("uc_user_hide_nav_bars", 1);
    }

    public boolean includeBlacklisted()
    {
        return this.preferences.getBoolean("uc_include_blacklisted", 0);
    }

    public boolean isAllowFlagOnDefaultServer()
    {
        return this.allowFlagOnDefaultServer;
    }

    public boolean isAllowInvalidCertificates()
    {
        return 1;
    }

    public boolean isAutoLoadNotes()
    {
        return this.preferences.getBoolean("uc_AUTO_LOAD_NOTES", 1);
    }

    public boolean isAutoLoadPins()
    {
        return this.preferences.getBoolean("uc_auto_load_pins", 1);
    }

    public boolean isCombineMultiSearch()
    {
        return this.preferences.getBoolean("CombineMultiSearch", 0);
    }

    public boolean isDownloadOriginalImage()
    {
        return this.preferences.getBoolean("downloadOriginalImage", 0);
    }

    public boolean isEnableAnonymousDiagnostics()
    {
        return 1;
    }

    public boolean isEnableDiskCache()
    {
        return 1;
    }

    public boolean isExternalSDCardAvailable()
    {
        int v0_0 = this.getRootExternalSDCard();
        if (!android.text.TextUtils.isEmpty(v0_0)) {
            java.io.File v1_2 = new java.io.File(v0_0);
            if ((!v1_2.exists()) || ((!v1_2.isDirectory()) || ((!v1_2.canWrite()) || (v1_2.getUsableSpace() <= 0)))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public boolean isFavSyncDanbooru2()
    {
        return this.preferences.getBoolean("FavSyncDanbooru2", 1);
    }

    public boolean isGifFullScreen()
    {
        return 1;
    }

    public boolean isLongTapToDownload()
    {
        return this.preferences.getBoolean("uc_longTapToDownload", 1);
    }

    public boolean isNeedGPUImageRefresh()
    {
        return this.needGPUImageRefresh;
    }

    public boolean isNeedRestartProxyChange()
    {
        return this.needRestartProxyChange;
    }

    public boolean isReduceImageSize()
    {
        return this.preferences.getBoolean("isReduceImageSize", 1);
    }

    public boolean isSDCardAvailable()
    {
        return com.bisimplex.firebooru.custom.ExternalStorage.isWritable();
    }

    public boolean isSecureScreen()
    {
        return this.preferences.getBoolean("uc_secure_screen", 0);
    }

    public boolean isShareAsImage()
    {
        return 1;
    }

    public boolean isShowExtensionLabel()
    {
        return 0;
    }

    public boolean isShowThumbnail()
    {
        return 1;
    }

    public boolean isShowViewerNavigationButtons()
    {
        return this.preferences.getBoolean("ic_ShowViewerNavigationButtons", 0);
    }

    public boolean isStatusBarVisible()
    {
        return this.preferences.getBoolean("uc_status_bar_visible", 0);
    }

    public boolean isThumbFullScreen()
    {
        return 1;
    }

    public boolean isUseInternalBrowser()
    {
        return 0;
    }

    public boolean isUsingStorageAcccessFramework()
    {
        return (android.text.TextUtils.isEmpty(this.getSDPath()) ^ 1);
    }

    public boolean isVideoMuted()
    {
        boolean v0_0 = this.preferences;
        if (v0_0) {
            return v0_0.getBoolean("uc_video_muted", 0);
        } else {
            return 0;
        }
    }

    public boolean isVolumeNavigation()
    {
        return this.preferences.getBoolean("uc_VOLUME_NAVIGATION", 0);
    }

    public boolean keepScreenOn()
    {
        return this.preferences.getBoolean("uc_screen_on_viewer", 1);
    }

    public boolean limitImageSize()
    {
        return this.preferences.getBoolean("uc_limit_image_size", 1);
    }

    public java.util.List loadSourceSpecs()
    {
        java.util.List v0_0 = this.preferences;
        if (v0_0 != null) {
            java.util.List v0_7 = v0_0.getString("uc_specs_list", "");
            if (!android.text.TextUtils.isEmpty(v0_7)) {
                return ((java.util.List) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_7, new com.bisimplex.firebooru.danbooru.UserConfiguration$2(this).getType()));
            } else {
                return new java.util.ArrayList();
            }
        } else {
            return new java.util.ArrayList();
        }
    }

    public void moveSourceSpecs(int p2, int p3, String p4)
    {
        java.util.List v4_1;
        if (!android.text.TextUtils.isEmpty(p4)) {
            v4_1 = this.findSourceSpecsByID(p4).getChilds();
            if (v4_1 == null) {
                return;
            }
        } else {
            v4_1 = this.getSourceSpecs();
        }
        v4_1.add(p3, ((com.bisimplex.firebooru.model.SourceSpecs) v4_1.remove(p2)));
        this.setSourceSpecs(this.getSourceSpecs());
        return;
    }

    public void moveToGroupSpecs(com.bisimplex.firebooru.model.SourceSpecs p3, String p4, String p5)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.ArrayList v0_1 = this.getSourceSpecs();
            com.bisimplex.firebooru.model.SourceSpecs v4_5 = this.findSourceSpecsByID(p4);
            if (v4_5 != null) {
                java.util.ArrayList v5_1 = this.findSourceSpecsInList(v4_5.getChilds(), p5);
                if (v5_1 >= null) {
                    com.bisimplex.firebooru.model.SourceSpecs v4_3 = ((com.bisimplex.firebooru.model.SourceSpecs) v4_5.getChilds().remove(v5_1));
                    if (!android.text.TextUtils.isEmpty(p3.getKey())) {
                        java.util.ArrayList v5_4 = p3.getChilds();
                        if (v5_4 == null) {
                            v5_4 = new java.util.ArrayList();
                            p3.setChilds(v5_4);
                        }
                        v5_4.add(v4_3);
                    } else {
                        v0_1.add(v4_3);
                    }
                }
                this.setSourceSpecs(v0_1);
                return;
            } else {
                return;
            }
        } else {
            com.bisimplex.firebooru.model.SourceSpecs v4_4 = this.getSourceSpecs();
            java.util.ArrayList v5_6 = this.findSourceSpecsInList(v4_4, p5);
            if (v5_6 >= null) {
                java.util.ArrayList v5_8 = ((com.bisimplex.firebooru.model.SourceSpecs) v4_4.remove(v5_6));
                java.util.ArrayList v0_2 = p3.getChilds();
                if (v0_2 == null) {
                    v0_2 = new java.util.ArrayList(1);
                    p3.setChilds(v0_2);
                }
                v0_2.add(v5_8);
            }
            this.setSourceSpecs(v4_4);
            return;
        }
    }

    public boolean mustShowFullScreenTutorial()
    {
        boolean v0_1 = this.preferences.getBoolean("mustShowFullScreenTutorial", 1);
        android.content.SharedPreferences$Editor v1_2 = this.preferences.edit();
        v1_2.putBoolean("mustShowFullScreenTutorial", 0);
        v1_2.apply();
        return v0_1;
    }

    public void passedLock()
    {
        this.__showLock = 0;
        return;
    }

    public void removeAllServerWithCookies()
    {
        this.preferences.edit().remove("uc_server_with_cookies").apply();
        return;
    }

    public boolean serverHasCookies(String p4)
    {
        return this.preferences.getString("uc_server_with_cookies", "").contains(p4);
    }

    public void setAllowInvalidCertificates(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("AllowInvalidCertificates", p3);
        v0_1.commit();
        return;
    }

    public void setAskForCrashReport(boolean p3)
    {
        this.preferences.edit().putBoolean("ASK_CRASH_REPORTS", p3).apply();
        return;
    }

    public void setAutoLoadNotes(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_AUTO_LOAD_NOTES", p3).apply();
        return;
    }

    public void setAutoLoadPins(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_auto_load_pins", p3).apply();
        return;
    }

    public void setCombineMultiSearch(boolean p3)
    {
        this.preferences.edit().putBoolean("CombineMultiSearch", p3).apply();
        return;
    }

    public void setContext(android.content.Context p6)
    {
        this.context = p6;
        if (p6 != null) {
            this.preferences = p6.getSharedPreferences("UserConfiguration", 0);
            this.securePreferences = new com.bisimplex.firebooru.custom.SecurePreferences(this.context, "uc_sec", "e45WNnwSAy4gZ688Edq2", 1);
            this.__showLock = this.existLock();
            this.currentAppVersionCode = 118;
            if ((this.preferences.contains("AlwaysPreload")) && (!this.preferences.contains("PreloadType"))) {
                android.content.SharedPreferences$Editor v0_9;
                if (!this.getAlwaysPreload()) {
                    v0_9 = com.bisimplex.firebooru.danbooru.UserConfiguration.PRELOAD_TYPE_WIFI;
                } else {
                    v0_9 = com.bisimplex.firebooru.danbooru.UserConfiguration.PRELOAD_TYPE_ALWAYS;
                }
                this.preferences.edit().putInt("PreloadType", v0_9).apply();
            }
            if (!this.preferences.contains("uc_quality_render")) {
                com.bisimplex.firebooru.danbooru.QualityRenderType v6_2 = ((android.app.ActivityManager) p6.getSystemService("activity"));
                if (v6_2 != null) {
                    android.content.SharedPreferences$Editor v0_16 = new android.app.ActivityManager$MemoryInfo();
                    v6_2.getMemoryInfo(v0_16);
                    if (v0_16.totalMem < 2147483648) {
                        this.setQualityRender(com.bisimplex.firebooru.danbooru.QualityRenderType.ForcedLow);
                    } else {
                        this.setQualityRender(com.bisimplex.firebooru.danbooru.QualityRenderType.Normal);
                    }
                }
            } else {
                this.qualityRender = com.bisimplex.firebooru.danbooru.QualityRenderType.fromInteger(this.preferences.getInt("uc_quality_render", com.bisimplex.firebooru.danbooru.QualityRenderType.Low.getValue()));
            }
            this.qualityRender = com.bisimplex.firebooru.danbooru.QualityRenderType.Low;
            this.versionCheck();
            this.localSourceSpecs = this.loadSourceSpecs();
        }
        return;
    }

    public void setDNSType(com.bisimplex.firebooru.model.DNSType p3)
    {
        this.preferences.edit().putInt("uc_DNS_type", p3.getValue()).apply();
        return;
    }

    public void setDeviceMaxImageSize(int p3)
    {
        this.maxImageSize = p3;
        this.preferences.edit().putInt("uc_DeviceMaxImageSize", p3).apply();
        this.needGPUImageRefresh = 0;
        return;
    }

    public void setDoubleTapCommand(com.bisimplex.firebooru.model.ViewerCommandType p3)
    {
        this.preferences.edit().putInt("uc_doubleTap_cmd", p3.getValue()).apply();
        return;
    }

    public void setDownloadOriginalImage(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("downloadOriginalImage", p3);
        v0_1.apply();
        return;
    }

    public void setEnableAnonymousDiagnostics(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("enableAnonymousDiagnostics", p3);
        v0_1.commit();
        return;
    }

    public void setEnableDiskCache(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("enableDiskCache", p3);
        v0_1.commit();
        return;
    }

    public void setFavSyncDanbooru2(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("FavSyncDanbooru2", p3);
        v0_1.apply();
        return;
    }

    public void setFileNameConfiguration(java.util.List p3)
    {
        if ((p3 != null) && (!p3.isEmpty())) {
            this.preferences.edit().putString("uc_fileNameConfiguration", com.bisimplex.firebooru.model.FileNamePartType.toCSV(p3)).apply();
            return;
        } else {
            this.preferences.edit().putString("uc_fileNameConfiguration", String.valueOf(com.bisimplex.firebooru.model.FileNamePartType.MD5.getValue())).apply();
            return;
        }
    }

    public void setFileNameType(com.bisimplex.firebooru.danbooru.FileNameType p3)
    {
        this.preferences.edit().putInt("uc_file_name_type", p3.getValue()).apply();
        return;
    }

    public void setFixGelbooruVideoLinks(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_fix_gelbooru_video_links", p3).apply();
        return;
    }

    public void setGifFullScreen(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("GifFullScreen", p3);
        v0_1.apply();
        return;
    }

    public void setHideFavorites(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_hide_favorites", p3).apply();
        return;
    }

    public void setHideNavBars(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_user_hide_nav_bars", p3).apply();
        return;
    }

    public void setKeepScreenOn(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_screen_on_viewer", p3).apply();
        return;
    }

    public void setLimitImageSize(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_limit_image_size", p3).apply();
        this.maxImageSize = 0;
        return;
    }

    public void setLockInfo(java.util.List p3)
    {
        if ((p3 != null) && (p3.size() != 0)) {
            this.securePreferences.put(com.bisimplex.firebooru.danbooru.UserConfiguration.LOCK_KEY, new com.google.gson.Gson().toJson(p3));
            return;
        } else {
            this.securePreferences.removeValue(com.bisimplex.firebooru.danbooru.UserConfiguration.LOCK_KEY);
            return;
        }
    }

    public void setLongTapToDownload(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_longTapToDownload", p3).apply();
        return;
    }

    public void setMultiSearchSelectedServerIds(java.util.List p4)
    {
        if ((p4 != null) && (!p4.isEmpty())) {
            android.content.SharedPreferences$Editor v1_5 = new StringBuilder();
            android.content.SharedPreferences$Editor v4_9 = p4.iterator();
            while (v4_9.hasNext()) {
                v1_5.append(((Integer) v4_9.next()));
                v1_5.append(",");
            }
            android.content.SharedPreferences$Editor v4_1 = v1_5.toString();
            if (!android.text.TextUtils.isEmpty(v4_1)) {
                this.preferences.edit().putString("uc_multi_search_ids", v4_1).apply();
                return;
            } else {
                this.preferences.edit().remove("uc_multi_search_ids").apply();
                return;
            }
        } else {
            this.preferences.edit().remove("uc_multi_search_ids").apply();
            return;
        }
    }

    public void setPreloadCount(int p3)
    {
        this.preferences.edit().putInt("uc_preload_count", p3).apply();
        return;
    }

    public void setPreloadType(int p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putInt("PreloadType", p3);
        v0_1.apply();
        return;
    }

    public void setProxyConfiguration(com.bisimplex.firebooru.model.ProxyConfiguration p3)
    {
        int v3_1;
        if (p3 == 0) {
            v3_1 = "";
        } else {
            v3_1 = com.bisimplex.firebooru.network.HttpClient.getGson().toJson(p3);
        }
        if (!android.text.TextUtils.isEmpty(v3_1)) {
            this.securePreferences.put("uc_Proxy", v3_1);
        } else {
            this.securePreferences.removeValue("uc_Proxy");
        }
        this.needRestartProxyChange = 1;
        return;
    }

    public void setQualityRender(com.bisimplex.firebooru.danbooru.QualityRenderType p3)
    {
        if (p3 == null) {
            p3 = com.bisimplex.firebooru.danbooru.QualityRenderType.Low;
        }
        this.qualityRender = p3;
        this.preferences.edit().putInt("uc_quality_render", p3.getValue()).apply();
        return;
    }

    public void setReduceImageSize(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("isReduceImageSize", p3);
        v0_1.apply();
        return;
    }

    public void setSDPath(String p4)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        if ((p4 != null) && (!p4.isEmpty())) {
            v0_1.putString("sdPath", p4);
        } else {
            v0_1.remove("sdPath");
        }
        v0_1.apply();
        return;
    }

    public void setSecureScreen(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_secure_screen", p3).apply();
        return;
    }

    public void setServiceProgress(String p3, com.bisimplex.firebooru.model.DownloadServiceProgress p4)
    {
        this.preferences.edit().putString(p3, com.bisimplex.firebooru.network.HttpClient.getGson().toJson(p4)).apply();
        return;
    }

    public void setShareAsImage(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("shareAsImage", p3);
        v0_1.apply();
        return;
    }

    public void setShowExtensionLabel(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("showExtensionLabel", p3);
        v0_1.commit();
        return;
    }

    public void setShowThumbnail(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("showThumbnail", p3);
        v0_1.apply();
        return;
    }

    public void setShowViewerNavigationButtons(boolean p3)
    {
        this.preferences.edit().putBoolean("ic_ShowViewerNavigationButtons", p3).apply();
        return;
    }

    public void setSourceSpecs(java.util.List p4)
    {
        if ((p4 == null) || (p4.isEmpty())) {
            android.content.SharedPreferences$Editor v4_5 = 0;
        } else {
            v4_5 = com.bisimplex.firebooru.network.HttpClient.getGson().toJson(p4);
        }
        if (!android.text.TextUtils.isEmpty(v4_5)) {
            this.preferences.edit().putString("uc_specs_list", v4_5).apply();
        } else {
            this.preferences.edit().remove("uc_specs_list").apply();
        }
        this.localSourceSpecs = 0;
        return;
    }

    public void setStatusBarVisible(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_status_bar_visible", p3).apply();
        return;
    }

    public void setStreamVideo(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("uc_streamVideo", p3);
        v0_1.apply();
        return;
    }

    public void setSwipeDownCommand(com.bisimplex.firebooru.model.ViewerCommandType p3)
    {
        this.preferences.edit().putInt("uc_swipeDown_cmd", p3.getValue()).apply();
        return;
    }

    public void setSwipeUpCommand(com.bisimplex.firebooru.model.ViewerCommandType p3)
    {
        this.preferences.edit().putInt("uc_swipeUp_cmd", p3.getValue()).apply();
        return;
    }

    public void setThemeSelected(com.bisimplex.firebooru.custom.ThemeType p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putInt("MaterialThemeType", p3.getValue());
        v0_1.apply();
        return;
    }

    public void setThumbDisplayMode(com.bisimplex.firebooru.custom.ThumbDisplayMode p3)
    {
        this.preferences.edit().putInt("uc_ThumbDisplayMode", p3.getValue()).apply();
        return;
    }

    public void setThumbFullScreen(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("ThumbFullScreen", p3);
        v0_1.commit();
        return;
    }

    public void setUseAutocompleteFromServer(boolean p3)
    {
        this.preferences.edit().putBoolean("AUTOCOMPLETE_FROM_SERVER", p3).apply();
        return;
    }

    public void setUseInternalBrowser(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("useInternalBrowser", p3);
        v0_1.commit();
        return;
    }

    public void setUseTopBar(boolean p3)
    {
        this.preferences.edit().putBoolean("UC_user_top_bar", p3).apply();
        return;
    }

    public void setVideoDecoderType(com.bisimplex.firebooru.model.VideoDecoderType p3)
    {
        this.preferences.edit().putInt("uc_VideoDecoder_1", p3.getValue()).apply();
        return;
    }

    public void setVideoMuted(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_0 = this.preferences;
        if (v0_0 != null) {
            v0_0.edit().putBoolean("uc_video_muted", p3).apply();
            return;
        } else {
            return;
        }
    }

    public void setVolumeNavigation(boolean p3)
    {
        this.preferences.edit().putBoolean("uc_VOLUME_NAVIGATION", p3).apply();
        return;
    }

    public void setYumekui(boolean p3)
    {
        android.content.SharedPreferences$Editor v0_1 = this.preferences.edit();
        v0_1.putBoolean("yumekui", p3);
        v0_1.apply();
        return;
    }

    public boolean showLock()
    {
        return this.__showLock;
    }

    public com.bisimplex.firebooru.model.SourceSpecs similarSpecsExit(com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        return this.findSimilarSpecs(p2, this.getSourceSpecs());
    }

    public boolean sortSourceSpecs(String p4, boolean p5)
    {
        java.util.List v4_1;
        java.util.List v0 = this.getSourceSpecs();
        if (!android.text.TextUtils.isEmpty(p4)) {
            java.util.List v4_3 = this.findSourceSpecsByID(p4);
            if (v4_3 != null) {
                v4_1 = v4_3.getChilds();
                if (v4_1 == null) {
                    return 1;
                }
            } else {
                return 0;
            }
        } else {
            v4_1 = v0;
        }
        java.util.Collections.sort(v4_1, new com.bisimplex.firebooru.danbooru.UserConfiguration$3(this));
        if (p5) {
            java.util.Collections.reverse(v4_1);
        }
        this.setSourceSpecs(v0);
        return 1;
    }

    public void storeSpecs(com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        java.util.List v0 = this.getSourceSpecs();
        if (v0.contains(p2)) {
            this.setSourceSpecs(v0);
        }
        return;
    }

    public boolean streamVideo()
    {
        return this.preferences.getBoolean("uc_streamVideo", 1);
    }

    public void swapSourceSpecs(int p2, int p3, String p4)
    {
        java.util.List v4_1;
        if (!android.text.TextUtils.isEmpty(p4)) {
            v4_1 = this.findSourceSpecsByID(p4).getChilds();
            if (v4_1 == null) {
                return;
            }
        } else {
            v4_1 = this.getSourceSpecs();
        }
        java.util.Collections.swap(v4_1, p2, p3);
        this.setSourceSpecs(this.getSourceSpecs());
        return;
    }

    public int[] toIntArray(java.util.List p4)
    {
        int v0 = 0;
        if (p4 != null) {
            int[] v1_1 = new int[p4.size()];
            while (v0 < p4.size()) {
                v1_1[v0] = Integer.parseInt(((String) p4.get(v0)));
                v0++;
            }
            return v1_1;
        } else {
            int[] v4_1 = new int[0];
            return v4_1;
        }
    }

    public boolean useAutoCompleteFromServer()
    {
        return this.preferences.getBoolean("AUTOCOMPLETE_FROM_SERVER", 0);
    }

    public boolean useTopBar()
    {
        return this.preferences.getBoolean("UC_user_top_bar", 1);
    }
}
