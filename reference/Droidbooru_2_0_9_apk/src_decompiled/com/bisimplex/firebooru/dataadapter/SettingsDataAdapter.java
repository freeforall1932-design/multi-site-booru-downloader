package com.bisimplex.firebooru.dataadapter;
public class SettingsDataAdapter extends com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter {
    public static final String APP_CHANGE_LOG_BUTTON = "APP_CHANGE_LOG_BUTTON";
    public static final String APP_EVENT_LOG_BUTTON = "APP_EVENT_LOG_BUTTON";
    public static final String APP_UPDATE_BUTTON = "APP_UPDATE_BUTTON";
    public static final String AUTO_LOAD_PINS = "AUTO_LOAD_PINS";
    public static final String BACKUP_BUTTONS = "BACKUP_BUTTONS";
    public static final String CACHE_SIZE = "CACHE_SIZE";
    public static final String CLEAR_CACHE = "CLEAR_CACHE";
    public static final String COOKIES_BUTTONS = "COOKIES_BUTTONS";
    public static final String CRASH_REPORT = "CRASH_REPORT";
    public static final String DELETE_FAVORITES = "DELETE_FAVORITES";
    public static final String DELETE_POST_HISTORY = "DELETE_POST_HISTORY";
    public static final String DELETE_SEARCH_HISTORY = "DELETE_SEARCH_HISTORY";
    public static final String DNS_PROVIDER = "DNS_PROVIDER";
    public static final String DOUBLE_TAP = "DOUBLE_TAP";
    private static final String DOWNLOAD_FILE = "DOWNLOAD_FILE";
    public static final String EDIT_FILE_NAME = "edit_file_name";
    public static final String EULA_BUTTON = "EULA_BUTTON";
    public static final String FILE_NAME = "FILE_NAME";
    public static final String FILE_NAME_FORMATTING = "FILE_NAME_FORMATTING";
    public static final String FIX_GELBOORU_VIDEO_LINKS = "FIX_GELBOORU_VIDEO_LINKS";
    public static final String HIDE_FAVORITES = "HIDE_FAVORITES";
    public static final String LOAD_NOTES = "LOAD_NOTES";
    public static final String LONG_TAP_DOWNLOAD = "LONG_TAP_DOWNLOAD";
    public static final String MIX_MULTI_SEARCH = "MIX_MULTI_SEARCH";
    public static final String NAVIGATE_TOOLBAR = "NAVIGATE_TOOLBAR";
    public static final String NAVIGATE_VOLUMEN = "NAVIGATE_VOLUMEN";
    public static final String NAVIGATION_BAR = "NAVIGATION_BAR";
    public static final String NAVIGATION_HIDE_BARS = "NAVIGATION_HIDE_BARS";
    public static final String PREFETCH = "PREFETCH";
    public static final String PREFETCH_COUNT = "PREFETCH_COUNT";
    public static final String PROXY_BUTTON = "PROXY_BUTTON";
    public static final String SAVE_TO = "SAVE_TO";
    public static final String SAVE_TO_BUTTONS = "SAVE_TO_BUTTONS";
    public static final String SCREEN_LOCK_BUTTON = "SCREEN_LOCK_BUTTON";
    public static final String SECURE_SCREEN = "SECURE_SCREEN";
    public static final String STATUS_BAR = "STATUS_BAR";
    public static final String STREAM_VIDEO = "STREAM_VIDEO";
    public static final String SWIPE_DOWN = "SWIPE_DOWN";
    public static final String SWIPE_UP = "SWIPE_UP";
    private static final String SYNC_FAVS = "SYNC_FAVS";
    public static final String THEME = "THEME";
    public static final String THUMBNAIL_SIZE = "thumbnail_size";
    public static final String VALIDATE = "VALIDATE";
    public static final String VIDEO_PLAYER = "VIDEO_PLAYER";
    private static final String VIDEO_PLAYER_HINT = "VIDEO_PLAYER_HINT";
    private static final String VIDEO_PLAYER_INFO = "VIDEO_PLAYER_INFO";
    public static final String VIEWER_SCREEN_ON = "VIEWER_SCREEN_ON";

    public SettingsDataAdapter(android.content.Context p1)
    {
        super(p1);
        super.fillForm();
        return;
    }

    private void fillForm()
    {
        this.data.clear();
        String v0_1 = this.getContext();
        java.util.List v1_0 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance();
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("version", v0_1.getString(2131887251, new Object[] {"2.0.9"}))));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("APP_CHANGE_LOG_BUTTON", v0_1.getString(2131887125), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("APP_EVENT_LOG_BUTTON", v0_1.getString(2131887132), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        if (v1_0.appUpdateAvailable()) {
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("APP_UPDATE_BUTTON", v0_1.getString(2131886980), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("network", v0_1.getString(2131886978), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("SYNC_FAVS", v0_1.getString(2131886482), Boolean.valueOf(v1_0.isFavSyncDanbooru2()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("sync_hint", v0_1.getString(2131886483)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("DOWNLOAD_FILE", v0_1.getString(2131886432), v1_0.isDownloadOriginalImage(), this.resourceArrayToList(2130903044), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("PREFETCH", v0_1.getString(2131887030), v1_0.getPreloadType(), this.resourceArrayToList(2130903054), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("PREFETCH_COUNT", v0_1.getString(2131887031), (v1_0.getPreloadCount() - 1), this.resourceArrayToList(2130903055), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("DNS_PROVIDER", v0_1.getString(2131886427), v1_0.getDNSType().getValue(), this.resourceArrayToList(2130903042), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("LONG_TAP_DOWNLOAD", v0_1.getString(2131886804), Boolean.valueOf(v1_0.isLongTapToDownload()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("AUTO_LOAD_PINS", v0_1.getString(2131886797), Boolean.valueOf(v1_0.isAutoLoadPins()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("VALIDATE", v0_1.getString(2131887244), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("validate_hint", v0_1.getString(2131887246)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("COOKIES_BUTTONS", v0_1.getString(2131887158), com.bisimplex.firebooru.dataadapter.search.ActionType.None, v0_1.getString(2131886233), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("search", v0_1.getString(2131887111), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("MIX_MULTI_SEARCH", v0_1.getString(2131886892), Boolean.valueOf(v1_0.isCombineMultiSearch()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("mix_hint", v0_1.getString(2131886893)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("viewer", v0_1.getString(2131887259), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("LOAD_NOTES", v0_1.getString(2131886162), Boolean.valueOf(v1_0.isAutoLoadNotes()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("NAVIGATE_VOLUMEN", v0_1.getString(2131886975), Boolean.valueOf(v1_0.isVolumeNavigation()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("NAVIGATE_TOOLBAR", v0_1.getString(2131886972), Boolean.valueOf(v1_0.isShowViewerNavigationButtons()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("navigate_toolbar_hint", v0_1.getString(2131886973)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("STREAM_VIDEO", v0_1.getString(2131887191), Boolean.valueOf(v1_0.streamVideo()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("stream_video_hint", v0_1.getString(2131887190)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("HIDE_FAVORITES", v0_1.getString(2131886654), Boolean.valueOf(v1_0.hideFavorites()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("VIEWER_SCREEN_ON", v0_1.getString(2131886734), Boolean.valueOf(v1_0.keepScreenOn()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("DOUBLE_TAP", v0_1.getString(2131886429), v1_0.getDoubleTapCommand().getValue(), this.resourceArrayToList(2130903043), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SWIPE_UP", v0_1.getString(2131887199), v1_0.getSwipeUpCommand().getValue(), this.resourceArrayToList(2130903040), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("SWIPE_DOWN", v0_1.getString(2131887198), v1_0.getSwipeDownCommand().getValue(), this.resourceArrayToList(2130903040), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("video_player_title", v0_1.getString(2131887255), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("VIDEO_PLAYER", v0_1.getString(2131887255), v1_0.getVideoDecoder().getValue(), this.videoDecoderOptions(v0_1), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("VIDEO_PLAYER_INFO", v0_1.getString(2131887026), this.videoPlayerInfoByType(v1_0.getVideoDecoder())));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("VIDEO_PLAYER_HINT", v0_1.getString(2131886224), v0_1.getString(2131886225)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("storage", v0_1.getString(2131887184), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("SAVE_TO", v0_1.getString(2131887100), this.getSaveToPathName()));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("SAVE_TO_BUTTONS", v0_1.getString(2131887126), com.bisimplex.firebooru.dataadapter.search.ActionType.None, v0_1.getString(2131887086), com.bisimplex.firebooru.dataadapter.search.ActionType.Reset, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("FILE_NAME", v0_1.getString(2131886587), v1_0.getFileNameType().getValue(), this.resourceArrayToList(2130903048), 1));
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getFileNameType() == com.bisimplex.firebooru.danbooru.FileNameType.MD5) {
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("FILE_NAME_FORMATTING", v0_1.getString(2131886588), this.generateFileNameFormatting(v1_0.getFileNameConfiguration())));
            this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("edit_file_name", v0_1.getString(2131886469), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        }
        com.bisimplex.firebooru.dataadapter.search.ActionType v3_107;
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("DELETE_FAVORITES", v0_1.getString(2131886393), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("DELETE_SEARCH_HISTORY", v0_1.getString(2131886394), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("DELETE_POST_HISTORY", v0_1.getString(2131886395), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.LabelItem("CACHE_SIZE", v0_1.getString(2131886195), v0_1.getString(2131886984)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("CLEAR_CACHE", v0_1.getString(2131886234), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("backup", v0_1.getString(2131886166), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem("BACKUP_BUTTONS", v0_1.getString(2131886285), com.bisimplex.firebooru.dataadapter.search.ActionType.Add, v0_1.getString(2131887088), com.bisimplex.firebooru.dataadapter.search.ActionType.Search, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("app", v0_1.getString(2131886155), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("thumbnail_size", v0_1.getString(2131886426), v1_0.getThumbDisplayMode().getValue(), this.resourceArrayToList(2130903041), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("THEME", v0_1.getString(2131887205), v1_0.getThemeSelected().getValue(), this.resourceArrayToList(2130903062), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem("NAVIGATION_BAR", v0_1.getString(2131886977), (v1_0.useTopBar() ^ 1), this.resourceArrayToList(2130903053), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("NAVIGATION_HIDE_BARS", v0_1.getString(2131886652), Boolean.valueOf(v1_0.hideNavBars()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("STATUS_BAR", v0_1.getString(2131887161), Boolean.valueOf(v1_0.isStatusBarVisible()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("SECURE_SCREEN", v0_1.getString(2131886715), Boolean.valueOf(v1_0.isSecureScreen()), 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("secure_screen_hint", v0_1.getString(2131886716)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CheckboxItem("CRASH_REPORT", v0_1.getString(2131886147), Boolean.valueOf(v1_0.askForCrashReport()), 1));
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().existLock()) {
            v3_107 = 2131886480;
        } else {
            v3_107 = 2131886424;
        }
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("SCREEN_LOCK_BUTTON", v0_1.getString(v3_107), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("screen_lock_hint", v0_1.getString(2131887107)));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("proxy", v0_1.getString(2131887039), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.CaptionItem("proxy_status", this.getProxyStatus()));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("PROXY_BUTTON", v0_1.getString(2131886266), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.SubtitleItem("other", v0_1.getString(2131887005), 0));
        this.data.add(new com.bisimplex.firebooru.dataadapter.search.ButtonItem("EULA_BUTTON", v0_1.getString(2131886512), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
        this.bindValueChangeListener();
        this.notifyDataSetChanged();
        return;
    }

    private String generateFileNameFormatting(java.util.List p6)
    {
        StringBuilder v0_1 = new StringBuilder();
        android.content.Context v1 = this.getContext();
        if (!p6.isEmpty()) {
            String v6_9 = p6.iterator();
            while (v6_9.hasNext()) {
                String v2_4 = com.bisimplex.firebooru.dataadapter.SettingsDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$FileNamePartType[((com.bisimplex.firebooru.model.FileNamePartType) v6_9.next()).ordinal()];
                if (v2_4 == 1) {
                    v0_1.append(v1.getString(2131886610));
                    v0_1.append(" ");
                } else {
                    if (v2_4 == 2) {
                        v0_1.append(v1.getString(2131886608));
                        v0_1.append(" ");
                    } else {
                        if (v2_4 == 3) {
                            v0_1.append(v1.getString(2131886611));
                            v0_1.append(" ");
                        } else {
                            if (v2_4 == 4) {
                                v0_1.append(v1.getString(2131886609));
                                v0_1.append(" ");
                            }
                        }
                    }
                }
            }
            v0_1.trimToSize();
            v0_1.deleteCharAt((v0_1.length() - 1));
            v0_1.append(".");
            v0_1.append(v1.getString(2131886607));
            return v0_1.toString();
        } else {
            return v1.getString(2131886984);
        }
    }

    private String getProxyStatus()
    {
        int v0_3 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getProxyConfiguration();
        if (v0_3 == 0) {
            v0_3 = new com.bisimplex.firebooru.model.ProxyConfiguration();
        }
        StringBuilder v1_1 = new StringBuilder();
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isNeedRestartProxyChange()) {
            v1_1.append(this.getContext().getString(2131887046));
        }
        if (!v0_3.isEnabled()) {
            v1_1.append(this.getContext().getString(2131887044));
        } else {
            v1_1.append(this.getContext().getString(2131887045));
            v1_1.append("\n");
            if (!android.text.TextUtils.isEmpty(v0_3.getUser())) {
                v1_1.append(v0_3.getUser());
                v1_1.append(":");
                if (!android.text.TextUtils.isEmpty(v0_3.getPass())) {
                    v1_1.append("####");
                }
                v1_1.append("@");
            }
            v1_1.append(v0_3.getHost());
            if (v0_3.getPort() > 0) {
                v1_1.append(":");
                v1_1.append(v0_3.getPort());
            }
        }
        return v1_1.toString();
    }

    private String getSaveToPathName()
    {
        String v0_7 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSDPath();
        if (!android.text.TextUtils.isEmpty(v0_7)) {
            String v0_13 = android.net.Uri.parse(v0_7);
            if (v0_13 != null) {
                try {
                    String v0_1 = androidx.documentfile.provider.DocumentFile.fromTreeUri(this.getContext(), v0_13);
                } catch (String v0_5) {
                    com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_5);
                    return this.getContext().getString(2131886984);
                }
                if (v0_1 != null) {
                    return v0_1.getName();
                } else {
                    return this.getContext().getString(2131886984);
                }
            } else {
                return this.getContext().getString(2131886984);
            }
        } else {
            return this.getContext().getString(2131886380);
        }
    }

    private java.util.List resourceArrayToList(int p7)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        String[] v7_1 = this.getContext().getResources().getStringArray(p7);
        int v1_0 = 0;
        while (v1_0 < v7_1.length) {
            v0_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(v1_0), v7_1[v1_0], 0));
            v1_0++;
        }
        return v0_1;
    }

    private java.util.List videoDecoderOptions(android.content.Context p6)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        v0_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.model.VideoDecoderType.Base.getValue()), p6.getString(2131887266), 0));
        v0_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.model.VideoDecoderType.VLC.getValue()), p6.getString(2131886791), 0));
        v0_1.add(new com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption(String.valueOf(com.bisimplex.firebooru.model.VideoDecoderType.EXO.getValue()), p6.getString(2131886571), 0));
        return v0_1;
    }

    private String videoPlayerInfoByType(com.bisimplex.firebooru.model.VideoDecoderType p3)
    {
        android.content.Context v0 = this.getContext();
        String v3_11 = com.bisimplex.firebooru.dataadapter.SettingsDataAdapter$1.$SwitchMap$com$bisimplex$firebooru$model$VideoDecoderType[p3.ordinal()];
        if (v3_11 == 1) {
            String v3_12 = androidx.webkit.WebViewCompat.getCurrentWebViewPackage(v0);
            if (v3_12 != null) {
                return v3_12.versionName;
            } else {
                return v0.getString(2131886988);
            }
        } else {
            if (v3_11 == 2) {
                return v0.getString(2131887260);
            } else {
                if (v3_11 == 3) {
                    return v0.getString(2131886553);
                } else {
                    return v0.getString(2131886984);
                }
            }
        }
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p11)
    {
        int v0_0 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance();
        if (!p11.getKey().equalsIgnoreCase("SYNC_FAVS")) {
            String v2_2 = 0;
            if (!p11.getKey().equalsIgnoreCase("DOWNLOAD_FILE")) {
                if (!p11.getKey().equalsIgnoreCase("PREFETCH")) {
                    if (!p11.getKey().equalsIgnoreCase("PREFETCH_COUNT")) {
                        if (!p11.getKey().equalsIgnoreCase("LONG_TAP_DOWNLOAD")) {
                            if (!p11.getKey().equalsIgnoreCase("AUTO_LOAD_PINS")) {
                                if (!p11.getKey().equalsIgnoreCase("FIX_GELBOORU_VIDEO_LINKS")) {
                                    if (!p11.getKey().equalsIgnoreCase("LOAD_NOTES")) {
                                        if (!p11.getKey().equalsIgnoreCase("MIX_MULTI_SEARCH")) {
                                            if (!p11.getKey().equalsIgnoreCase("NAVIGATE_VOLUMEN")) {
                                                if (!p11.getKey().equalsIgnoreCase("NAVIGATE_TOOLBAR")) {
                                                    if (!p11.getKey().equalsIgnoreCase("STREAM_VIDEO")) {
                                                        if (!p11.getKey().equalsIgnoreCase("HIDE_FAVORITES")) {
                                                            if (!p11.getKey().equalsIgnoreCase("VIEWER_SCREEN_ON")) {
                                                                if (!p11.getKey().equalsIgnoreCase("DOUBLE_TAP")) {
                                                                    if (!p11.getKey().equalsIgnoreCase("SWIPE_UP")) {
                                                                        if (!p11.getKey().equalsIgnoreCase("SWIPE_DOWN")) {
                                                                            if (!p11.getKey().equalsIgnoreCase("FILE_NAME")) {
                                                                                if (!p11.getKey().equalsIgnoreCase("THEME")) {
                                                                                    if (!p11.getKey().equalsIgnoreCase("NAVIGATION_BAR")) {
                                                                                        if (!p11.getKey().equalsIgnoreCase("NAVIGATION_HIDE_BARS")) {
                                                                                            if (!p11.getKey().equalsIgnoreCase("STATUS_BAR")) {
                                                                                                if (!p11.getKey().equalsIgnoreCase("SECURE_SCREEN")) {
                                                                                                    if (!p11.getKey().equalsIgnoreCase("CRASH_REPORT")) {
                                                                                                        if (!p11.getKey().equalsIgnoreCase("thumbnail_size")) {
                                                                                                            if (!p11.getKey().equalsIgnoreCase("VIDEO_PLAYER")) {
                                                                                                                if (p11.getKey().equalsIgnoreCase("DNS_PROVIDER")) {
                                                                                                                    v0_0.setDNSType(com.bisimplex.firebooru.model.DNSType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                                                                }
                                                                                                            } else {
                                                                                                                String v2_10 = com.bisimplex.firebooru.model.VideoDecoderType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex());
                                                                                                                v0_0.setVideoDecoderType(v2_10);
                                                                                                                int v0_3 = (this.data.indexOf(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11)) + 1);
                                                                                                                ((com.bisimplex.firebooru.dataadapter.search.LabelItem) this.data.get(v0_3)).setValue(this.videoPlayerInfoByType(v2_10));
                                                                                                                this.notifyItemChanged(v0_3);
                                                                                                            }
                                                                                                        } else {
                                                                                                            v0_0.setThumbDisplayMode(com.bisimplex.firebooru.custom.ThumbDisplayMode.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                                                        }
                                                                                                    } else {
                                                                                                        v0_0.setAskForCrashReport(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                                                                        org.acra.ACRA.getErrorReporter().setEnabled(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                                                                    }
                                                                                                } else {
                                                                                                    v0_0.setSecureScreen(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                                                                }
                                                                                            } else {
                                                                                                v0_0.setStatusBarVisible(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                                                            }
                                                                                        } else {
                                                                                            v0_0.setHideNavBars(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                                                        }
                                                                                    } else {
                                                                                        if (((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex() == 0) {
                                                                                            v2_2 = 1;
                                                                                        }
                                                                                        v0_0.setUseTopBar(v2_2);
                                                                                    }
                                                                                } else {
                                                                                    v0_0.setThemeSelected(com.bisimplex.firebooru.custom.ThemeType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                                }
                                                                            } else {
                                                                                String v2_16 = com.bisimplex.firebooru.danbooru.FileNameType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex());
                                                                                v0_0.setFileNameType(v2_16);
                                                                                String v5_1 = this.findEditableItemByKey("FILE_NAME_FORMATTING");
                                                                                if (v2_16 != com.bisimplex.firebooru.danbooru.FileNameType.MD5) {
                                                                                    if (v5_1 != null) {
                                                                                        int v0_6 = this.data.indexOf(v5_1);
                                                                                        this.data.remove(v0_6);
                                                                                        this.data.remove(v0_6);
                                                                                        this.notifyItemRangeRemoved(v0_6, 2);
                                                                                    }
                                                                                } else {
                                                                                    if (v5_1 == null) {
                                                                                        com.bisimplex.firebooru.model.DNSType v1_72 = (this.data.indexOf(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11)) + 1);
                                                                                        this.data.add(v1_72, new com.bisimplex.firebooru.dataadapter.search.ButtonItem("edit_file_name", this.getContext().getString(2131886469), com.bisimplex.firebooru.dataadapter.search.ActionType.None, 1));
                                                                                        this.data.add(v1_72, new com.bisimplex.firebooru.dataadapter.search.LabelItem("FILE_NAME", this.getContext().getString(2131886588), this.generateFileNameFormatting(v0_0.getFileNameConfiguration())));
                                                                                        this.notifyItemRangeInserted(v1_72, 2);
                                                                                    }
                                                                                }
                                                                            }
                                                                        } else {
                                                                            v0_0.setSwipeDownCommand(com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                        }
                                                                    } else {
                                                                        v0_0.setSwipeUpCommand(com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                    }
                                                                } else {
                                                                    v0_0.setDoubleTapCommand(com.bisimplex.firebooru.model.ViewerCommandType.fromInteger(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex()));
                                                                }
                                                            } else {
                                                                v0_0.setKeepScreenOn(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                            }
                                                        } else {
                                                            v0_0.setHideFavorites(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                        }
                                                    } else {
                                                        v0_0.setStreamVideo(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                    }
                                                } else {
                                                    v0_0.setShowViewerNavigationButtons(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                                }
                                            } else {
                                                v0_0.setVolumeNavigation(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                            }
                                        } else {
                                            v0_0.setCombineMultiSearch(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                        }
                                    } else {
                                        v0_0.setAutoLoadNotes(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                    }
                                } else {
                                    v0_0.setFixGelbooruVideoLinks(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                                }
                            } else {
                                v0_0.setAutoLoadPins(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                            }
                        } else {
                            v0_0.setLongTapToDownload(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
                        }
                    } else {
                        v0_0.setPreloadCount((((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex() + 1));
                    }
                } else {
                    v0_0.setPreloadType(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex());
                }
            } else {
                if (((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) p11).getSelectedIndex() == 1) {
                    v2_2 = 1;
                }
                v0_0.setDownloadOriginalImage(v2_2);
            }
        } else {
            v0_0.setFavSyncDanbooru2(((Boolean) ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) p11).getValue()).booleanValue());
        }
        super.notifyValueChanged(p11);
        return;
    }

    public void updateCacheSize(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            com.bisimplex.firebooru.dataadapter.search.LabelItem v0_3 = ((com.bisimplex.firebooru.dataadapter.search.LabelItem) this.findEditableItemByKey("CACHE_SIZE"));
            v0_3.setValue(p2);
            this.notifyItemChanged(this.data.indexOf(v0_3));
            return;
        } else {
            return;
        }
    }

    public void updateSavePath()
    {
        int v0_2 = ((com.bisimplex.firebooru.dataadapter.search.LabelItem) this.findEditableItemByKey("SAVE_TO"));
        v0_2.setValue(this.getSaveToPathName());
        this.notifyItemChanged(this.data.indexOf(v0_2));
        return;
    }
}
