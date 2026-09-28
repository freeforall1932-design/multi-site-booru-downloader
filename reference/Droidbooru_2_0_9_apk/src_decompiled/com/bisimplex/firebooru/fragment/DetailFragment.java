package com.bisimplex.firebooru.fragment;
public class DetailFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.custom.SaveFilesTaskListener, com.bisimplex.firebooru.network.SourceListener, com.bisimplex.firebooru.fragment.ISourceFragment, com.bisimplex.firebooru.fragment.IServerChanger, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener {
    public static final int SECONDS = 10;
    public static final String SOURCE_KEY = "SOURCE_KEY";
    public static final String SOURCE_TYPE_KEY = "SOURCE_TYPE_KEY";
    private boolean _addToHistory;
    protected com.bisimplex.firebooru.network.SourcePostBasic _source;
    protected com.bisimplex.firebooru.fragment.DetailPagerAdapter adapter;
    com.mikepenz.iconics.view.IconicsImageButton downButton;
    private final java.util.concurrent.Executor executor;
    private android.view.MenuItem favMenuItem;
    private boolean isOnWifi;
    com.mikepenz.iconics.view.IconicsImageButton leftButton;
    com.mikepenz.iconics.view.IconicsImageButton maxButton;
    com.mikepenz.iconics.view.IconicsImageButton minButton;
    android.view.ViewGroup navigationLayout;
    private android.view.MenuItem notesMenuItem;
    private final androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback onPageChangeCallback;
    protected androidx.viewpager2.widget.ViewPager2 pager;
    private final com.bisimplex.firebooru.network.SourceListener reloadListener;
    private android.view.MenuItem reloadMenuItem;
    private int reloadPosition;
    com.mikepenz.iconics.view.IconicsImageButton rightButton;
    private boolean slideshowEnabled;
    private android.os.Handler slideshowHandler;
    private Runnable slideshowRunnable;
    private int slideshowSeconds;
    com.mikepenz.iconics.view.IconicsImageButton upButton;
    private int videoIndex;
    private long videoTime;
    private boolean wasTryingToDownload;
    private androidx.appcompat.widget.Toolbar xToolbar;

    public static synthetic void $r8$lambda$MEahRBsOODvOxj4erQhdTiiIahY(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1)
    {
        p0.lambda$moveToIndex$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$NXZggywEs-mBnHhqEyEMtGZvpVs(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1, com.bisimplex.firebooru.activity.MessageType p2)
    {
        p0.lambda$showMessageOnMain$0(p1, p2);
        return;
    }

    static bridge synthetic java.util.concurrent.Executor -$$Nest$fgetexecutor(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.executor;
    }

    static bridge synthetic int -$$Nest$fgetreloadPosition(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.reloadPosition;
    }

    static bridge synthetic boolean -$$Nest$fgetslideshowEnabled(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.slideshowEnabled;
    }

    static bridge synthetic android.os.Handler -$$Nest$fgetslideshowHandler(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.slideshowHandler;
    }

    static bridge synthetic Runnable -$$Nest$fgetslideshowRunnable(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.slideshowRunnable;
    }

    static bridge synthetic int -$$Nest$fgetvideoIndex(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.videoIndex;
    }

    static bridge synthetic long -$$Nest$fgetvideoTime(com.bisimplex.firebooru.fragment.DetailFragment p2)
    {
        return p2.videoTime;
    }

    static bridge synthetic void -$$Nest$fputreloadPosition(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1)
    {
        p0.reloadPosition = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputvideoIndex(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1)
    {
        p0.videoIndex = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputvideoTime(com.bisimplex.firebooru.fragment.DetailFragment p0, long p1)
    {
        p0.videoTime = p1;
        return;
    }

    static bridge synthetic boolean -$$Nest$mexecuteCommand(com.bisimplex.firebooru.fragment.DetailFragment p0, com.bisimplex.firebooru.model.ViewerCommandType p1)
    {
        return p0.executeCommand(p1);
    }

    static bridge synthetic android.net.Uri -$$Nest$mfileToSharingUri(com.bisimplex.firebooru.fragment.DetailFragment p0, java.io.File p1)
    {
        return p0.fileToSharingUri(p1);
    }

    static bridge synthetic boolean -$$Nest$misToolBarVisible(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        return p0.isToolBarVisible();
    }

    static bridge synthetic boolean -$$Nest$mmoveToDirecction(com.bisimplex.firebooru.fragment.DetailFragment p0, boolean p1)
    {
        return p0.moveToDirecction(p1);
    }

    static bridge synthetic void -$$Nest$mpreloadPostIfNeeded(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1)
    {
        p0.preloadPostIfNeeded(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mreloadIt(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        p0.reloadIt();
        return;
    }

    static bridge synthetic void -$$Nest$msendPostToHydrus(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        p0.sendPostToHydrus();
        return;
    }

    static bridge synthetic void -$$Nest$msetVisiblePendingCommand(com.bisimplex.firebooru.fragment.DetailFragment p0, com.bisimplex.firebooru.model.ViewerCommandType p1)
    {
        p0.setVisiblePendingCommand(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mslideshowTick(com.bisimplex.firebooru.fragment.DetailFragment p0)
    {
        p0.slideshowTick();
        return;
    }

    static bridge synthetic void -$$Nest$mtriggerSlideshowTick(com.bisimplex.firebooru.fragment.DetailFragment p0, long p1)
    {
        p0.triggerSlideshowTick(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mupdateTitle(com.bisimplex.firebooru.fragment.DetailFragment p0, int p1)
    {
        p0.updateTitle(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mwriteExif(com.bisimplex.firebooru.fragment.DetailFragment p0, String p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        p0.writeExif(p1, p2);
        return;
    }

    public DetailFragment()
    {
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.reloadPosition = -1;
        this.onPageChangeCallback = new com.bisimplex.firebooru.fragment.DetailFragment$1(this);
        this.isOnWifi = 1;
        this.reloadListener = new com.bisimplex.firebooru.fragment.DetailFragment$12(this);
        return;
    }

    private boolean executeCommand(com.bisimplex.firebooru.model.ViewerCommandType p4)
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_0 = this.getSource();
        if (v0_0 != null) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v0_1 = v0_0.getVisiblePost();
            if (v0_1 != null) {
                switch (com.bisimplex.firebooru.fragment.DetailFragment$23.$SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType[p4.ordinal()]) {
                    case 1:
                        return 0;
                    case 2:
                        this.showInfo(v0_1);
                        break;
                    case 3:
                        this.saveIt();
                        break;
                    case 4:
                        this.toogleFavorite();
                        break;
                    case 5:
                        v0_1.setEnforceOriginalImage(0);
                        this.reloadSelectedImage();
                        break;
                    case 6:
                        v0_1.setEnforceOriginalImage(1);
                        this.reloadSelectedImage();
                        break;
                    case 7:
                        this.shareIt();
                        break;
                    case 8:
                        this.toggleNotes();
                        break;
                    case 9:
                        this.goBackInStack();
                        break;
                    default:
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private android.net.Uri fileToSharingUri(java.io.File p4)
    {
        if (p4 != null) {
            String v1 = this.getFileAuthName();
            if (v1 != null) {
                androidx.fragment.app.FragmentActivity v2 = this.getActivity();
                if (v2 != null) {
                    return androidx.core.content.FileProvider.getUriForFile(v2, v1, p4);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static String formatElapsedTime(long p2)
    {
        return android.text.format.DateUtils.formatElapsedTime((p2 / 1000));
    }

    private String getCommandName(com.bisimplex.firebooru.model.ViewerCommandType p2)
    {
        switch (com.bisimplex.firebooru.fragment.DetailFragment$23.$SwitchMap$com$bisimplex$firebooru$model$ViewerCommandType[p2.ordinal()]) {
            case 2:
                return this.getString(2131887160);
            case 3:
                return this.getString(2131887099);
            case 4:
                return this.getString(2131887215);
            case 5:
                return this.getString(2131887257);
            case 6:
                return this.getString(2131887258);
            case 7:
                return this.getString(2131887153);
            case 8:
                return this.getString(2131887216);
            case 9:
                return this.getString(2131886164);
            default:
                return 0;
        }
    }

    private String getFileAuthName()
    {
        String v0_0 = this.getActivity();
        if (v0_0 != null) {
            return new StringBuilder().append(v0_0.getApplicationContext().getPackageName()).append(".custom.GenericFileProvider").toString();
        } else {
            return 0;
        }
    }

    private void invalidateToolbar()
    {
        com.mikepenz.iconics.IconicsDrawable v1_0;
        android.view.MenuItem v0_1 = this.xToolbar.getMenu();
        this.updateHydrusButton(v0_1);
        android.view.MenuItem v0_2 = v0_1.findItem(2131362539);
        if (!this.slideshowEnabled) {
            v1_0 = 2131887167;
        } else {
            v1_0 = 2131887179;
        }
        com.mikepenz.iconics.IconicsDrawable v1_2;
        v0_2.setTitle(v1_0);
        if (!this.slideshowEnabled) {
            v1_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_play_circle;
        } else {
            v1_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_pause_circle;
        }
        v0_2.setIcon(this.iconWithColor(v1_2, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        this.updateFavButton();
        this.updateNotesButton();
        return;
    }

    private boolean isToolBarVisible()
    {
        int v0_0 = this.xToolbar;
        if (v0_0 != 0) {
            if (v0_0.getVisibility() != 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    private synthetic void lambda$moveToIndex$1(int p2)
    {
        this.adapter.notifyItemChanged(p2);
        return;
    }

    private synthetic void lambda$showMessageOnMain$0(int p1, com.bisimplex.firebooru.activity.MessageType p2)
    {
        this.showMessage(p1, p2);
        return;
    }

    private boolean moveToDirecction(boolean p4)
    {
        if ((this.pager != null) && (this.getSource() != null)) {
            if (p4 == 0) {
                int v4_2 = (this.getSource().getVisiblePostIndex() - 1);
                if (v4_2 >= 0) {
                    this.moveToIndex(v4_2);
                    return 1;
                }
            } else {
                int v4_4 = this.getSource().getItemCount();
                int v2_2 = (this.getSource().getVisiblePostIndex() + 1);
                if (v2_2 < v4_4) {
                    this.moveToIndex(v2_2);
                    return 1;
                }
            }
        }
        return 0;
    }

    private void moveToIndex(int p3)
    {
        this.pager.setCurrentItem(p3, 0);
        this.pager.post(new com.bisimplex.firebooru.fragment.DetailFragment$$ExternalSyntheticLambda1(this, p3));
        return;
    }

    private void preloadPost(com.bisimplex.firebooru.danbooru.DanbooruPost p5, int p6)
    {
        if ((p5 != null) && (this.getSource() != null)) {
            com.bumptech.glide.request.RequestOptions v6_0 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPostPreview(this.getSource().getProvider(p5), p5);
            if (v6_0 != null) {
                com.bumptech.glide.Glide.with(this).load(v6_0).apply(com.bisimplex.firebooru.network.Utils.getInstance().getThumbsOptions()).preload();
            }
            if (p5.shouldPreload()) {
                com.bumptech.glide.request.RequestOptions v6_6 = p5.getVisibleVersion().getUrl();
                com.bumptech.glide.load.model.GlideUrl v0_4 = com.bisimplex.firebooru.network.Utils.getInstance().urlForPost(this.getSource().getProvider(p5), p5);
                if ((v0_4 != null) && (((com.bisimplex.firebooru.custom.DownloadTarget) com.bisimplex.firebooru.custom.MyGlideModule.find(v6_6)) == null)) {
                    if (!p5.getVisibleVersion().isVideo()) {
                        com.bisimplex.firebooru.custom.DownloadTarget v1_7 = new com.bisimplex.firebooru.custom.DownloadTarget(0, v6_6, p5, this._addToHistory);
                        com.bisimplex.firebooru.custom.MyGlideModule.expect(v6_6, v1_7);
                        com.bumptech.glide.Glide.with(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).load(v0_4).apply(com.bisimplex.firebooru.network.Utils.getInstance().getImageOptions()).into(v1_7);
                    } else {
                        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()) {
                            com.bisimplex.firebooru.custom.DownloadTarget v1_11 = new com.bisimplex.firebooru.custom.DownloadTarget(0, v6_6, p5, this._addToHistory);
                            com.bisimplex.firebooru.custom.MyGlideModule.expect(v6_6, v1_11);
                            com.bumptech.glide.Glide.with(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).asFile().load(v0_4).into(v1_11);
                            return;
                        }
                    }
                }
            }
        }
        return;
    }

    private void preloadPostIfNeeded(int p7)
    {
        int v0_0 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance();
        com.bisimplex.firebooru.network.SourcePostBasic v1 = this.getSource();
        if ((v1 != null) && (v0_0.getAllowPreload(this.isOnWifi))) {
            int v0_1 = v0_0.getPreloadCount();
            int v3 = 1;
            while (v3 <= v0_1) {
                int v4_1 = ((p7 + v3) + 1);
                this.preloadPost(((com.bisimplex.firebooru.danbooru.DanbooruPost) v1.getItemAt(v4_1)), v4_1);
                v3++;
            }
        }
        return;
    }

    private void reloadIt()
    {
        if (this.getSource() != null) {
            String v0_12 = this.getSource().getVisiblePost();
            com.bisimplex.firebooru.activity.MessageType v1_0 = this.getSource().getVisiblePostIndex();
            if (v0_12 != null) {
                com.bisimplex.firebooru.network.Utils v2_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstanceByUrl(v0_12.getPostUrl());
                if (v2_1 != null) {
                    com.bisimplex.firebooru.network.SourceQuery v3_1 = v2_1.getServerDescription().getType();
                    if ((v3_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) && ((v3_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) && ((v3_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) && (v3_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru)))) {
                        if (v3_1 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                            this.showMessage(this.getString(2131887233, new Object[] {v0_12.getPostUrl()})), com.bisimplex.firebooru.activity.MessageType.Error);
                            return;
                        } else {
                            com.bisimplex.firebooru.network.SourceQuery v3_3 = new com.bisimplex.firebooru.network.SourceQuery(String.format(java.util.Locale.US, "id=%s", new Object[] {v0_12.getPostId()})));
                            v3_3.setTitle(String.valueOf(v1_0));
                            String v0_13 = ((com.bisimplex.firebooru.network.SourcePost) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post, v2_1, v3_3));
                            v0_13.setDisableHistory(1);
                            this.ShowLoading();
                            v0_13.loadAnotherPage(this.reloadListener);
                            return;
                        }
                    } else {
                        com.bisimplex.firebooru.network.SourceQuery v3_5 = new com.bisimplex.firebooru.network.SourceQuery(String.format(java.util.Locale.US, "id:%s", new Object[] {v0_12.getPostId()})));
                        v3_5.setTitle(String.valueOf(v1_0));
                        com.bisimplex.firebooru.activity.MessageType v1_10 = ((com.bisimplex.firebooru.network.SourcePost) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post, v2_1, v3_5));
                        v1_10.setDisableHistory(1);
                        v1_10.setDisableVisiblityChecks(1);
                        this.ShowLoading();
                        com.bisimplex.firebooru.network.Utils.getInstance().logInfo(String.format("Reloading metadata for %s", new Object[] {v0_12.getPostUrl()})));
                        v1_10.loadAnotherPage(this.reloadListener);
                        return;
                    }
                } else {
                    this.showMessage(2131886217, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
        }
        return;
    }

    private void sendPostToHydrus()
    {
        com.bisimplex.firebooru.view.ServersDialog v0_4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadServersByType(com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus);
        if (!v0_4.isEmpty()) {
            if (v0_4.size() != 1) {
                androidx.fragment.app.FragmentManager v1_1 = new java.util.ArrayList(v0_4.size());
                com.bisimplex.firebooru.view.ServersDialog v0_1 = v0_4.iterator();
                while (v0_1.hasNext()) {
                    v1_1.add(((com.bisimplex.firebooru.danbooru.ServerItem) v0_1.next()).getUrl());
                }
                com.bisimplex.firebooru.view.ServersDialog v0_3 = new com.bisimplex.firebooru.view.ServersDialog();
                androidx.fragment.app.FragmentManager v1_4 = new android.os.Bundle(1);
                v1_4.putInt("SERVER_FILTER_TYPE_ID", com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus.getValue());
                v1_4.putString("SERVER_FILTER_TITLE", this.getString(2131887135));
                v1_4.putBoolean("SERVER_FILTER_CHANGE_ON_SELECT", 0);
                v0_3.setArguments(v1_4);
                v0_3.show(this.getActivity().getSupportFragmentManager(), "hydrus");
                return;
            } else {
                this.sendPostToHydrus(((com.bisimplex.firebooru.danbooru.ServerItem) v0_4.get(0)));
                return;
            }
        } else {
            return;
        }
    }

    private void sendPostToHydrus(com.bisimplex.firebooru.danbooru.ServerItem p6)
    {
        if ((p6 != null) && (p6.getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus)) {
            com.bisimplex.firebooru.network.Utils v0_7 = this.getSource();
            if (v0_7 != null) {
                com.bisimplex.firebooru.network.Utils v0_9 = v0_7.getVisiblePost();
                if (v0_9 != null) {
                    okhttp3.OkHttpClient v1_0 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
                    String v2_1 = new com.google.gson.JsonObject();
                    v2_1.addProperty("url", v0_9.getPostUrl());
                    v2_1.addProperty("destination_page_name", "Anime boxes");
                    try {
                        v1_0.newCall(new okhttp3.Request$Builder().url(String.format("%s/add_urls/add_url", new Object[] {p6.getUrl()}))).addHeader("Content-Type", "application/json").addHeader("Hydrus-Client-API-Access-Key", java.net.URLEncoder.encode(p6.getApiKey(), "utf-8")).post(okhttp3.RequestBody.create(v2_1.toString(), 0)).build()).enqueue(new com.bisimplex.firebooru.fragment.DetailFragment$11(this));
                        return;
                    } catch (java.io.UnsupportedEncodingException v6_10) {
                        com.bisimplex.firebooru.network.Utils.getInstance().logException(v6_10);
                    }
                }
            }
        }
        return;
    }

    private void setToolbarVisible(Boolean p2)
    {
        androidx.appcompat.widget.Toolbar v0 = this.xToolbar;
        if (v0 != null) {
            int v2_2;
            if (!p2.booleanValue()) {
                v2_2 = 8;
            } else {
                v2_2 = 0;
            }
            v0.setVisibility(v2_2);
            return;
        } else {
            return;
        }
    }

    private void setVisiblePendingCommand(com.bisimplex.firebooru.model.ViewerCommandType p4)
    {
        int v0_0 = this.getSource();
        if ((v0_0 != 0) && ((this.adapter != null) && (this.pager != null))) {
            int v0_1 = v0_0.getVisiblePostIndex();
            com.bisimplex.firebooru.data.DanbooruPostPage v1_0 = this.adapter.getItem(v0_1);
            if ((v1_0 != null) && (((p4 != com.bisimplex.firebooru.model.ViewerCommandType.ZoomIn) && (p4 != com.bisimplex.firebooru.model.ViewerCommandType.ZoomOut)) || (!v1_0.getPost().getVisibleVersion().isAnimated()))) {
                v1_0.setPendingCommand(p4);
                this.adapter.notifyItemChanged(v0_1);
            }
        }
        return;
    }

    private void slideshowTick()
    {
        if ((this.pager != null) && (this.getSource() != null)) {
            int v0_3 = this.getSource().getItemCount();
            int v1_2 = (this.getSource().getVisiblePostIndex() + 1);
            if (v1_2 < v0_3) {
                this.moveToIndex(v1_2);
            }
        }
        return;
    }

    private void syncFavoritedIfNeeded(com.bisimplex.firebooru.danbooru.DanbooruPost p5)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isFavSyncDanbooru2()) {
            ((com.bisimplex.firebooru.network.SourceFavorites) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Favorites, this.getSource().getProvider(p5), new com.bisimplex.firebooru.network.SourceQuery())).syncFavorite(p5, new com.bisimplex.firebooru.fragment.DetailFragment$18(this));
        }
        return;
    }

    private void triggerSlideshowTick(long p5)
    {
        if ((this.slideshowEnabled) && ((this.slideshowRunnable != null) && (this.slideshowHandler != null))) {
            long v5_1 = Math.max(p5, (((long) this.slideshowSeconds) * 1000));
            this.slideshowHandler.removeCallbacks(this.slideshowRunnable);
            this.slideshowHandler.postDelayed(this.slideshowRunnable, v5_1);
        }
        return;
    }

    private void updateHydrusButton(android.view.Menu p2)
    {
        android.view.MenuItem v2_1 = p2.findItem(2131362167);
        if (v2_1 != null) {
            v2_1.setVisible(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().hasHydrusServers());
        }
        return;
    }

    private void updateTitle(int p4)
    {
        this.xToolbar.setTitle(String.format(java.util.Locale.US, "%d/%d", new Object[] {Integer.valueOf((p4 + 1)), Integer.valueOf(this.getSource().getItemCount())})));
        return;
    }

    private boolean willMoveWithVolumeKey(boolean p5)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVolumeNavigation()) {
            int v0_5 = this.getSource();
            if ((v0_5 != 0) && ((v0_5.getVisiblePost() != null) && (this.pager != null))) {
                int v2_2 = v0_5.getVisiblePost();
                boolean v3 = this.isToolBarVisible();
                if ((!v2_2.getVisibleVersion().isVideo()) || (!v3)) {
                    int v1_0 = v0_5.getItemCount();
                    int v0_1 = v0_5.getVisiblePostIndex();
                    if (!p5) {
                        int v0_3 = (v0_1 - 1);
                        if (v0_3 >= 0) {
                            this.moveToIndex(v0_3);
                        }
                    } else {
                        int v0_4 = (v0_1 + 1);
                        if (v0_4 < v1_0) {
                            this.moveToIndex(v0_4);
                        }
                    }
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private void writeExif(String p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        return;
    }

    public void addToDownload()
    {
        this.wasTryingToDownload = 1;
        if ((this.savePermissionGranted()) && ((((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()) != null) && (this.getSource() != null))) {
            int v0_3 = this.getSource().getVisiblePost();
            com.bisimplex.firebooru.activity.MessageType v1_1 = new java.util.ArrayList(0);
            v1_1.add(v0_3);
            int v0_5 = new com.bisimplex.firebooru.services.DownloadOptions();
            v0_5.setAvoidDuplicates(0);
            v0_5.setExcludeAnimated(0);
            com.bisimplex.firebooru.services.DownloadService.getInstance().enqueueWork(this.getContext(), v1_1, v0_5, new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131887166)));
            this.showMessage(2131886165, com.bisimplex.firebooru.activity.MessageType.Minimal);
            this.checkNotificationPermission();
        }
        return;
    }

    public void browseIt()
    {
        this.stopSlideshow();
        String v0_1 = this.getSource().getVisiblePost();
        if (v0_1 != null) {
            this.launchBrowser(v0_1.getPostUrl());
            return;
        } else {
            return;
        }
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p3)
    {
        super.configureBar(p3);
        android.view.Menu v3_1 = p3.getMenu();
        this.setIconToMenuItem(v3_1, 2131362483, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_save);
        this.setIconToMenuItem(v3_1, 2131362184, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_info);
        this.setIconToMenuItem(v3_1, 2131362539, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_play_circle);
        this.setIconToMenuItem(v3_1, 2131362453, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_sync);
        this.setIconToMenuItem(v3_1, 2131362372, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_sticky_note1);
        this.setIconToMenuItem(v3_1, 2131361921, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_external_link_alt);
        this.setIconToMenuItem(v3_1, 2131362712, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_image1);
        this.updateHydrusButton(v3_1);
        return;
    }

    protected void configureInsets(androidx.core.graphics.Insets p6, androidx.core.graphics.Insets p7)
    {
        super.configureInsets(p6, p7);
        if (this.xToolbar != null) {
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
                this.xToolbar.setPadding((p6.left + p7.left), 0, (p6.right + p7.right), p7.bottom);
            } else {
                this.xToolbar.setPadding((p6.left + p7.left), Math.max(p6.top, p7.top), (p6.right + p7.right), 0);
            }
        }
        int vtmp4 = this.getResources().getDimensionPixelSize(2131166091);
        int v6_7 = this.adapter;
        if (v6_7 != 0) {
            v6_7.setVideoControlBottomMargin((p7.bottom + vtmp4));
        }
        return;
    }

    public boolean copy(java.io.File p5, java.io.File p6, boolean p7)
    {
        if (p5 != 0) {
            String[] v1_2 = new java.io.FileInputStream(p5);
            int v5_3 = new java.io.FileOutputStream(p6, 0);
            byte[] v2_1 = new byte[1024];
            while(true) {
                int v3 = v1_2.read(v2_1);
                if (v3 <= 0) {
                    break;
                }
                v5_3.write(v2_1, 0, v3);
            }
            v1_2.close();
            v5_3.close();
            if (p7 != null) {
                android.content.Context v7_1 = this.getContext();
                String[] v1_0 = new String[1];
                v1_0[0] = p6.getPath();
                android.media.MediaScannerConnection.scanFile(v7_1, v1_0, 0, 0);
            }
            return 1;
        } else {
            return 0;
        }
    }

    public void copyImageToClipBoard()
    {
        if ((this.getSource() != null) && ((com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance() != null) && ((this.getSource().getVisiblePost() != null) && (this.getSource().getVisiblePost().getVisibleVersion() != null)))) {
            com.bumptech.glide.RequestBuilder v0_5 = this.getSource().getVisiblePost();
            com.bisimplex.firebooru.activity.MessageType v1_0 = v0_5.getVisibleVersion();
            this.stopSlideshow();
            if (!v1_0.isVideo()) {
                if (!this.isLoadingCurrentImage()) {
                    com.bumptech.glide.RequestBuilder v2_2 = v1_0.getUrl();
                    if (!android.text.TextUtils.isEmpty(v2_2)) {
                        com.bumptech.glide.Glide.with(this).asFile().load(v2_2).listener(new com.bisimplex.firebooru.fragment.DetailFragment$14(this, v0_5, v1_0)).submit();
                    }
                } else {
                    this.showMessage(2131887261, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            } else {
                this.showMessage(2131886214, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            }
        }
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        this.showMessage(2131886490, com.bisimplex.firebooru.activity.MessageType.Minimal);
        return;
    }

    public com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion()
    {
        return 0;
    }

    protected android.view.View getInsetContentView()
    {
        return 0;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    protected android.view.View getSnackBarAnchorView()
    {
        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            return this.xToolbar;
        } else {
            return 0;
        }
    }

    public bridge synthetic com.bisimplex.firebooru.network.Source getSource()
    {
        return this.getSource();
    }

    public com.bisimplex.firebooru.network.SourcePostBasic getSource()
    {
        if (this._source == null) {
            com.bisimplex.firebooru.network.SourcePostBasic v0_6 = this.getArguments();
            if (v0_6 != null) {
                this._source = ((com.bisimplex.firebooru.network.SourcePostBasic) com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(com.bisimplex.firebooru.network.SourceType.fromInteger(v0_6.getInt("SOURCE_TYPE_KEY", com.bisimplex.firebooru.network.SourceType.Post.getValue())), v0_6.getString("SOURCE_KEY")));
            }
        }
        return this._source;
    }

    public boolean getWasTryingToDownload()
    {
        return this.wasTryingToDownload;
    }

    public String getiOsFragmentName()
    {
        return "PhotoViewController";
    }

    public boolean isLoadingCurrentImage()
    {
        boolean v0_2 = this.adapter.getItem(this.pager.getCurrentItem());
        if (v0_2) {
            return v0_2.isLoading();
        } else {
            return 0;
        }
    }

    public boolean isResourceReady()
    {
        boolean v0_7 = this.adapter.getItem(this.pager.getCurrentItem());
        if (v0_7) {
            if ((!v0_7.isReady()) && ((!v0_7.getPost().getVisibleVersion().isVideo()) || (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p3, android.view.ViewGroup p4, android.os.Bundle p5)
    {
        android.view.View v3_1 = p3.inflate(2131558428, p4, 0);
        if (p5) {
            this.videoIndex = p5.getInt("videoIndex", -1);
            this.videoTime = p5.getLong("videoTime", 0);
        }
        com.bisimplex.firebooru.network.SourcePostBasic v4_3 = this.getSource();
        if ((v4_3 != null) && (v4_3.getItemCount() != 0)) {
            this._addToHistory = (v4_3 instanceof com.bisimplex.firebooru.network.SourcePost);
            v4_3.setListener(this);
        }
        return v3_1;
    }

    public void onDestroy()
    {
        android.os.Handler v0_0 = this.pager;
        if (v0_0 != null) {
            Runnable v1_1 = this.onPageChangeCallback;
            if (v1_1 != null) {
                v0_0.unregisterOnPageChangeCallback(v1_1);
                this.pager.setAdapter(0);
            }
        }
        if (this.slideshowEnabled) {
            android.os.Handler v0_1 = this.slideshowHandler;
            if (v0_1 != null) {
                Runnable v1_0 = this.slideshowRunnable;
                if (v1_0 != null) {
                    v0_1.removeCallbacks(v1_0);
                }
            }
        }
        super.onDestroy();
        return;
    }

    public void onDialogNegativeClick(androidx.fragment.app.DialogFragment p1)
    {
        return;
    }

    public void onDialogNewFolderClick(androidx.fragment.app.DialogFragment p2, String p3, com.bisimplex.firebooru.network.SourceType p4)
    {
        if ((!android.text.TextUtils.isEmpty(p3)) && (p4 != null)) {
            com.google.android.material.dialog.MaterialAlertDialogBuilder v2_6 = ((com.bisimplex.firebooru.network.SourcePostBasic) com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(p4, p3));
            if (v2_6 != null) {
                com.google.android.material.dialog.MaterialAlertDialogBuilder v2_1 = com.bisimplex.firebooru.model.SourceSpecs.fromSource(v2_6);
                com.google.android.material.dialog.MaterialAlertDialogBuilder v3_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().similarSpecsExit(v2_1);
                if (v3_2 == null) {
                    this.askAddGroup(v2_1, this);
                } else {
                    com.google.android.material.dialog.MaterialAlertDialogBuilder v3_4;
                    if (v3_2.getType() != 4) {
                        v3_4 = this.getString(2131886662);
                    } else {
                        v3_4 = v3_2.getQuery().getText();
                    }
                    new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886445).setMessage(this.getString(2131886446, new Object[] {v3_4}))).setNegativeButton(2131886205, 0).setPositiveButton(2131887271, new com.bisimplex.firebooru.fragment.DetailFragment$22(this, v2_1)).show();
                    return;
                }
            }
        }
        return;
    }

    public void onDialogSpecsDuplicated(androidx.fragment.app.DialogFragment p3, com.bisimplex.firebooru.model.SourceSpecs p4, com.bisimplex.firebooru.model.SourceSpecs p5, com.bisimplex.firebooru.model.SourceSpecs p6)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v6_1;
        if (p6.getType() != 4) {
            v6_1 = this.getString(2131886662);
        } else {
            v6_1 = p6.getQuery().getText();
        }
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886445).setMessage(this.getString(2131886446, new Object[] {v6_1}))).setNegativeButton(2131886205, 0).setPositiveButton(2131887271, new com.bisimplex.firebooru.fragment.DetailFragment$21(this, p4, p5, p3)).show();
        return;
    }

    public void onDialogSpecsSaved(androidx.fragment.app.DialogFragment p2, com.bisimplex.firebooru.model.SourceSpecs p3, boolean p4)
    {
        if ((p3 != null) && (!android.text.TextUtils.isEmpty(p3.getKey()))) {
            if (p3.getType() != 4) {
                this.showMessage(2131887105, com.bisimplex.firebooru.activity.MessageType.Success);
                return;
            } else {
                this.showMessage(this.getString(2131887104, new Object[] {p3.getQuery().getTitle()})), com.bisimplex.firebooru.activity.MessageType.Success);
                return;
            }
        } else {
            this.showMessage(2131887105, com.bisimplex.firebooru.activity.MessageType.Success);
            return;
        }
    }

    public boolean onKeyDown(int p2, android.view.KeyEvent p3)
    {
        if (p2 != 25) {
            if (p2 != 24) {
                return super.onKeyDown(p2, p3);
            } else {
                return this.willMoveWithVolumeKey(1);
            }
        } else {
            return this.willMoveWithVolumeKey(0);
        }
    }

    public boolean onKeyUp(int p2, android.view.KeyEvent p3)
    {
        if (p2 != 21) {
            if (p2 != 22) {
                if (p2 != 20) {
                    if (p2 != 19) {
                        return super.onKeyUp(p2, p3);
                    } else {
                        return this.executeCommand(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeUpCommand());
                    }
                } else {
                    return this.executeCommand(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeDownCommand());
                }
            } else {
                return this.moveToDirecction(1);
            }
        } else {
            return this.moveToDirecction(0);
        }
    }

    public void onPause()
    {
        super.onPause();
        int v0_4 = this.getSource();
        if (v0_4 != 0) {
            int v0_5 = v0_4.getVisiblePost();
            if (v0_5 != 0) {
                if (!v0_5.getVisibleVersion().isVideo()) {
                    this.videoIndex = -1;
                    return;
                } else {
                    this.videoIndex = this.getSource().getVisiblePostIndex();
                    return;
                }
            }
        }
        return;
    }

    public void onResume()
    {
        super.onResume();
        android.view.Window v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (this.getSource() != null) {
            if (this.slideshowEnabled) {
                if (this.slideshowSeconds == 0) {
                    this.slideshowSeconds = 10;
                }
                this.startSlideshow(this.slideshowSeconds, 0);
            }
            this.updateTitle(this.pager.getCurrentItem());
            int v1_4 = this.getSource();
            com.bisimplex.firebooru.fragment.DetailPagerAdapter v2_1 = v1_4.getVisiblePost();
            if (v2_1 != null) {
                if (v2_1.getVisibleVersion().isVideo()) {
                    com.bisimplex.firebooru.fragment.DetailPagerAdapter v2_4 = this.adapter;
                    if (v2_4 != null) {
                        v2_4.notifyItemChanged(v1_4.getVisiblePostIndex());
                    }
                }
                if (v0_1 != null) {
                    android.view.Window v0_2 = v0_1.getWindow();
                    if ((v0_2 != null) && (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().keepScreenOn())) {
                        v0_2.addFlags(128);
                    }
                }
            }
        } else {
            if (v0_1 != null) {
                v0_1.showHome();
                return;
            }
        }
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p5)
    {
        super.onSaveInstanceState(p5);
        p5.putBoolean("isActionBarVisible", this.isToolBarVisible());
        p5.putBoolean("slideshowEnabled", this.slideshowEnabled);
        p5.putBoolean("wasTryingToDownload", this.wasTryingToDownload);
        p5.putInt("slideshowSeconds", this.slideshowSeconds);
        if (this.videoIndex >= 0) {
            String v0_3 = this.videoTime;
            if (v0_3 > 0) {
                p5.putLong("videoTime", v0_3);
                p5.putInt("videoIndex", this.videoIndex);
            }
        }
        return;
    }

    public void onStart()
    {
        super.onStart();
        int v0_1 = this.getSource();
        if ((v0_1 != 0) && ((v0_1.getItemCount() > 0) && (this.pager != null))) {
            this.moveToIndex(v0_1.getVisiblePostIndex());
        }
        return;
    }

    public void onStop()
    {
        super.onStop();
        android.view.Window v0_0 = this.adapter;
        if (v0_0 != null) {
            v0_0.resetVideoView();
        }
        android.view.Window v0_1 = this.getActivity();
        if (v0_1 != null) {
            android.view.Window v0_2 = v0_1.getWindow();
            if (v0_2 != null) {
                v0_2.clearFlags(128);
            }
        }
        return;
    }

    public void onViewCreated(android.view.View p5, android.os.Bundle p6)
    {
        super.onViewCreated(p5, p6);
        if (this.getSource() != null) {
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_46 = ((androidx.appcompat.widget.Toolbar) p5.findViewById(2131362726));
            this.xToolbar = v6_46;
            this.configureBar(v6_46);
            this.xToolbar.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$2(this));
            this.xToolbar.setNavigationOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$3(this));
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_34 = ((androidx.constraintlayout.widget.ConstraintLayout$LayoutParams) this.xToolbar.getLayoutParams());
            int v2 = 0;
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
                v6_34.topToTop = -1;
                v6_34.bottomToBottom = 0;
                this.xToolbar.setBackgroundResource(2131230847);
            } else {
                v6_34.topToTop = 0;
                v6_34.bottomToBottom = -1;
                this.xToolbar.setBackgroundResource(2131231138);
            }
            this.xToolbar.setLayoutParams(v6_34);
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_37 = this.xToolbar.getMenu();
            this.favMenuItem = v6_37.findItem(2131362105);
            this.reloadMenuItem = v6_37.findItem(2131362453);
            this.notesMenuItem = v6_37.findItem(2131362372);
            this.updateFavButton();
            this.updateNotesButton();
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_42 = ((androidx.viewpager2.widget.ViewPager2) p5.findViewById(2131362404));
            this.pager = v6_42;
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_43 = v6_42.getChildAt(0);
            if ((v6_43 instanceof androidx.recyclerview.widget.RecyclerView)) {
                androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_47 = ((androidx.recyclerview.widget.DefaultItemAnimator) ((androidx.recyclerview.widget.RecyclerView) v6_43).getItemAnimator());
                if (v6_47 != null) {
                    v6_47.setSupportsChangeAnimations(0);
                }
            }
            androidx.viewpager2.widget.ViewPager2$OnPageChangeCallback v6_49 = new com.bisimplex.firebooru.fragment.DetailPagerAdapter(this.requireContext(), this.getSource(), new com.bisimplex.firebooru.fragment.DetailFragment$4(this));
            this.adapter = v6_49;
            this.pager.setAdapter(v6_49);
            this.navigationLayout = ((android.view.ViewGroup) p5.findViewById(2131362353));
            this.leftButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362201));
            this.rightButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362467));
            this.upButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362680));
            this.downButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362009));
            this.maxButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362295));
            this.minButton = ((com.mikepenz.iconics.view.IconicsImageButton) p5.findViewById(2131362304));
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isShowViewerNavigationButtons()) {
                v2 = 8;
            }
            this.navigationLayout.setVisibility(v2);
            this.leftButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$5(this));
            this.rightButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$6(this));
            this.upButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$7(this));
            this.downButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$8(this));
            androidx.viewpager2.widget.ViewPager2 v5_9 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeUpCommand();
            if (v5_9 != com.bisimplex.firebooru.model.ViewerCommandType.None) {
                this.upButton.setContentDescription(this.getCommandName(v5_9));
            } else {
                this.upButton.setVisibility(8);
            }
            androidx.viewpager2.widget.ViewPager2 v5_13 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeDownCommand();
            if (v5_13 != com.bisimplex.firebooru.model.ViewerCommandType.None) {
                this.downButton.setContentDescription(this.getCommandName(v5_13));
            } else {
                this.downButton.setVisibility(8);
            }
            this.maxButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$9(this));
            this.minButton.setOnClickListener(new com.bisimplex.firebooru.fragment.DetailFragment$10(this));
            this.isOnWifi = com.bisimplex.firebooru.custom.Connectivity.isConnectedWifi(this.getActivity());
            this.pager.registerOnPageChangeCallback(this.onPageChangeCallback);
            return;
        } else {
            return;
        }
    }

    public void onViewStateRestored(android.os.Bundle p2)
    {
        super.onViewStateRestored(p2);
        if (p2 != 0) {
            this.slideshowEnabled = p2.getBoolean("slideshowEnabled");
            this.slideshowSeconds = p2.getInt("slideshowSeconds");
            this.wasTryingToDownload = p2.getBoolean("wasTryingToDownload");
            if ((this.getActivity() != null) && ((!p2.getBoolean("isActionBarVisible")) && (this.isToolBarVisible()))) {
                this.setToolbarVisible(Boolean.valueOf(0));
                this.changeStatusBar(0);
            }
        }
        return;
    }

    public java.util.List provideKeyboardShortcuts()
    {
        java.util.List v0 = super.provideKeyboardShortcuts();
        if (v0 != null) {
            java.util.ArrayList v1_1 = new java.util.ArrayList();
            v1_1.add(new android.view.KeyboardShortcutInfo(this.getString(2131886970), 21, 0));
            v1_1.add(new android.view.KeyboardShortcutInfo(this.getString(2131886971), 22, 0));
            android.view.KeyboardShortcutGroup v2_5 = this.getCommandName(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeUpCommand());
            String v3_4 = this.getCommandName(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeDownCommand());
            if (!android.text.TextUtils.isEmpty(v2_5)) {
                v1_1.add(new android.view.KeyboardShortcutInfo(v2_5, 19, 0));
            }
            if (!android.text.TextUtils.isEmpty(v3_4)) {
                v1_1.add(new android.view.KeyboardShortcutInfo(v3_4, 20, 0));
            }
            v0.add(new android.view.KeyboardShortcutGroup(this.getString(2131887259), v1_1));
        }
        return v0;
    }

    public void reloadSelectedImage()
    {
        com.bisimplex.firebooru.fragment.DetailPagerAdapter v0_0 = this.pager;
        if (v0_0 != null) {
            int v1_2 = this.adapter;
            if (v1_2 != 0) {
                v1_2.getItem(v0_0.getCurrentItem()).setReady(0);
                this.adapter.notifyItemChanged(this.pager.getCurrentItem());
            }
        }
        return;
    }

    public void reloadVisible()
    {
        if ((this.getActivity() != null) && (!this.isDetached())) {
            this.updateFavButton();
        }
        return;
    }

    public void saveIt()
    {
        this.wasTryingToDownload = 0;
        if ((this.savePermissionGranted()) && (this.getSource() != null)) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v3 = this.getSource().getVisiblePost();
            if (v3 != null) {
                com.bumptech.glide.RequestBuilder v0_3 = v3.getVisibleVersion().getUrl();
                if (this.isResourceReady()) {
                    String v1_2 = v3.getVisibleVersion();
                    if ((!v1_2.isVideo()) || (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo())) {
                        com.bisimplex.firebooru.fragment.DetailFragment v2_4 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getRootDocumentFile();
                        String v6 = v3.generateFileName();
                        String v4_0 = v1_2.getExtension();
                        if (android.text.TextUtils.isEmpty(v4_0)) {
                            v4_0 = "*";
                        }
                        String v4_2;
                        if (!v1_2.isWebM()) {
                            if (!v1_2.isMP4()) {
                                v4_2 = String.format("image/%s", new Object[] {v4_0}));
                            } else {
                                v4_2 = "video/mp4";
                            }
                        } else {
                            v4_2 = "video/webm";
                        }
                        String v4_3;
                        boolean v7 = v3.getVisibleVersion().isVideo();
                        android.content.ContentValues v5_5 = 0;
                        if ((v2_4 != null) && (v2_4.canWrite())) {
                            v4_3 = v2_4.createFile(v4_2, v6);
                        } else {
                            com.bisimplex.firebooru.fragment.DetailFragment v2_6 = new android.content.ContentValues();
                            v2_6.put("title", v6);
                            v2_6.put("_display_name", v6);
                            v2_6.put("mime_type", v4_2);
                            if ((v1_2.supportsExif()) && (!android.text.TextUtils.isEmpty(v3.getTags()))) {
                                v2_6.put("description", v3.getTags());
                            }
                            v4_3 = 0;
                            v5_5 = v2_6;
                        }
                        com.bumptech.glide.Glide.with(this).asFile().load(v0_3).listener(new com.bisimplex.firebooru.fragment.DetailFragment$16(this, v3, v4_3, v5_5, v6, v7)).submit();
                        return;
                    } else {
                        this.addToDownload();
                        return;
                    }
                } else {
                    this.showMessage(2131887262, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
        }
        return;
    }

    public void selectedServer(androidx.fragment.app.DialogFragment p1, com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        this.sendPostToHydrus(p2);
        return;
    }

    public void shareIt()
    {
        android.util.Log.i("Gelbooru", "ShareIt");
        this.stopSlideshow();
        if ((this.getSource() != null) && ((com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance() != null) && ((this.getSource().getVisiblePost() != null) && (this.getSource().getVisiblePost().getVisibleVersion() != null)))) {
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isShareAsImage()) {
                this.shareUrl(this.getSource().getVisiblePost().getPostUrl());
            } else {
                com.bumptech.glide.RequestBuilder v0_12 = this.getSource().getVisiblePost();
                com.bisimplex.firebooru.activity.MessageType v1_1 = v0_12.getVisibleVersion();
                com.bumptech.glide.RequestBuilder v2_0 = v1_1.getUrl();
                if (v2_0 != null) {
                    if (!this.isLoadingCurrentImage()) {
                        com.bumptech.glide.Glide.with(this).asFile().load(v2_0).listener(new com.bisimplex.firebooru.fragment.DetailFragment$15(this, v0_12, v1_1)).submit();
                        return;
                    } else {
                        this.showMessage(2131887262, com.bisimplex.firebooru.activity.MessageType.Error);
                        return;
                    }
                }
            }
        }
        return;
    }

    public void showActionbarIfHidden()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_0 = this.getActivity();
        if ((v0_0 instanceof com.bisimplex.firebooru.activity.MainActivity)) {
            ((com.bisimplex.firebooru.activity.MainActivity) v0_0).setScrimVisible(this.isToolBarVisible());
        }
        return;
    }

    public void showInfo(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        this.stopSlideshow();
        ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()).showSecondaryMenu(com.bisimplex.firebooru.custom.SecondaryMenuType.InfoPanel, p3);
        return;
    }

    public void showMessageOnMain(int p3, com.bisimplex.firebooru.activity.MessageType p4)
    {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new com.bisimplex.firebooru.fragment.DetailFragment$$ExternalSyntheticLambda0(this, p3, p4));
        return;
    }

    public void startSlideshow(int p4, boolean p5)
    {
        if (((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()) != null) {
            this.slideshowEnabled = 1;
            this.slideshowSeconds = p4;
            if (this.slideshowHandler == null) {
                this.slideshowHandler = new android.os.Handler();
            }
            if (this.slideshowRunnable == null) {
                this.slideshowRunnable = new com.bisimplex.firebooru.fragment.DetailFragment$19(this);
            }
            if (!this.isLoadingCurrentImage()) {
                this.triggerSlideshowTick(0);
            }
            this.invalidateToolbar();
            this.pager.setKeepScreenOn(1);
            if (p5 != null) {
                this.showMessage(2131887175, com.bisimplex.firebooru.activity.MessageType.Minimal);
            }
        }
        return;
    }

    public void stopSlideshow()
    {
        int v0_0 = this.slideshowHandler;
        if (v0_0 != 0) {
            com.bisimplex.firebooru.activity.MessageType v1_1 = this.slideshowRunnable;
            if (v1_1 != null) {
                v0_0.removeCallbacks(v1_1);
                this.slideshowRunnable = 0;
            }
        }
        this.pager.setKeepScreenOn(0);
        if (this.slideshowEnabled) {
            this.slideshowEnabled = 0;
            this.invalidateToolbar();
            this.showMessage(2131887180, com.bisimplex.firebooru.activity.MessageType.Minimal);
            return;
        } else {
            return;
        }
    }

    public void success(com.bisimplex.firebooru.network.Source p2, java.util.List p3)
    {
        if (this.getSource() == p2) {
            this.adapter.addData(p3);
            this.updateTitle(this.pager.getCurrentItem());
            if ((p3.isEmpty()) && (this.getSource().getLastPageRemovedCount() > 0)) {
                new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886987).setMessage(this.getString(2131887078, new Object[] {Integer.valueOf(this.getSource().getLastPageRemovedCount())}))).setPositiveButton(2131886799, new com.bisimplex.firebooru.fragment.DetailFragment$20(this)).setNegativeButton(2131886205, 0).show();
            }
        }
        return;
    }

    public void toggleFullScreen()
    {
        if (!this.isToolBarVisible()) {
            this.setToolbarVisible(Boolean.valueOf(1));
            this.changeStatusBar(1);
        } else {
            this.setToolbarVisible(Boolean.valueOf(0));
            this.changeStatusBar(0);
        }
        if ((this.getActivity() instanceof com.bisimplex.firebooru.activity.MainActivity)) {
            ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()).setScrimVisible(this.isToolBarVisible());
        }
        return;
    }

    public void toggleNotes()
    {
        int v0_1 = this.pager.getCurrentItem();
        com.bisimplex.firebooru.fragment.DetailPagerAdapter v1_2 = this.adapter.getItem(v0_1);
        if (v1_2 != null) {
            v1_2.setShowNotes((v1_2.isShowNotes() ^ 1));
        }
        this.adapter.notifyItemChanged(v0_1);
        return;
    }

    public void toggleSlideshow()
    {
        androidx.fragment.app.FragmentManager v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            if (!this.slideshowEnabled) {
                new com.bisimplex.firebooru.view.SlideshowDialog().show(v0_1.getSupportFragmentManager(), "slideshowSelector");
                return;
            } else {
                this.stopSlideshow();
                return;
            }
        } else {
            return;
        }
    }

    public void toogleFavorite()
    {
        int v0_2 = this.getSource().getVisiblePost();
        if (!v0_2.isDisableStorage()) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().toogleFavoriteItem(v0_2);
            this.syncFavoritedIfNeeded(v0_2);
            this.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
            this.updateFavButton();
            return;
        } else {
            com.bisimplex.firebooru.fragment.DetailFragment$17 v1_1 = this.getSource();
            com.bisimplex.firebooru.network.SourceFavorites v2_0 = v1_1.getProvider(v0_2);
            if ((v2_0.getServerDescription().getType() != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) || (android.text.TextUtils.isEmpty(v1_1.getRating_service_key()))) {
                this.showMessage(2131886220, com.bisimplex.firebooru.activity.MessageType.Info);
                return;
            } else {
                com.bisimplex.firebooru.network.SourceFavorites v2_2 = ((com.bisimplex.firebooru.network.SourceFavorites) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Favorites, v2_0, new com.bisimplex.firebooru.network.SourceQuery()));
                v2_2.setRating_service_key(v1_1.getRating_service_key());
                v0_2.setFavorite((v0_2.isFavorite() ^ 1));
                v2_2.syncFavorite(v0_2, new com.bisimplex.firebooru.fragment.DetailFragment$17(this, v0_2));
                return;
            }
        }
    }

    public void updateFavButton()
    {
        if ((this.getSource() != null) && ((this.getActivity() != null) && (!this.isDetached()))) {
            com.mikepenz.iconics.IconicsDrawable v0_2 = this.getSource().getVisiblePost();
            if ((this.favMenuItem != null) && (v0_2 != null)) {
                android.view.MenuItem v1_2;
                com.mikepenz.iconics.IconicsDrawable v0_3 = v0_2.isFavorite();
                this.reloadMenuItem.setVisible(v0_3);
                if (v0_3 == null) {
                    v1_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_heart;
                } else {
                    v1_2 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_heart1;
                }
                com.mikepenz.iconics.IconicsDrawable v0_5;
                if (v0_3 == null) {
                    v0_5 = com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes();
                } else {
                    v0_5 = com.bisimplex.firebooru.skin.SkinManager.getInstance().getAccentColorRes();
                }
                this.favMenuItem.setIcon(this.iconWithColor(v1_2, v0_5));
            }
        }
        return;
    }

    public void updateNotesButton()
    {
        boolean v0_2 = this.getSource().getVisiblePost();
        android.view.MenuItem v1 = this.notesMenuItem;
        if (v1 != null) {
            if (v0_2) {
                v1.setVisible(v0_2.getHas_notes());
                return;
            } else {
                v1.setVisible(0);
                return;
            }
        } else {
            return;
        }
    }

    public void wallpaperIt()
    {
        this.stopSlideshow();
        if (!this.isLoadingCurrentImage()) {
            if (this.getActivity() != null) {
                com.bumptech.glide.RequestBuilder v0_0 = this.getSource().getVisiblePost();
                com.bumptech.glide.Glide.with(this).asFile().load(v0_0.getVisibleVersion().getUrl()).listener(new com.bisimplex.firebooru.fragment.DetailFragment$13(this, v0_0)).submit();
                return;
            } else {
                return;
            }
        } else {
            this.showMessage(2131887262, com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }
}
