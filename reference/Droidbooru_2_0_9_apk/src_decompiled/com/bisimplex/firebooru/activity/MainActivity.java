package com.bisimplex.firebooru.activity;
public class MainActivity extends com.bisimplex.firebooru.activity.MenuBaseActivity implements com.bisimplex.firebooru.view.ServersDialog$ServersDialogListener, com.bisimplex.firebooru.view.JumpToPageDialog$JumpToPageDialogListener, com.bisimplex.firebooru.view.SlideshowDialog$SlideshowDialogListener, com.bisimplex.firebooru.view.FilterFavorieDialog$FilterFavoriteDialogListener, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener, com.bisimplex.firebooru.view.SearchDialog$OnSearchDialogListener, com.bisimplex.firebooru.view.GroupSpecsDialog$GroupSpecsDialogListener, com.bisimplex.firebooru.view.RenameGroupSpecsDialog$RenameGroupSpecsDialogListener, com.bisimplex.firebooru.view.SourceSpecsEditDialog$SourceSpecsEditDialogListener, com.bisimplex.firebooru.view.ValidateClientDialog$ValidateClientDialogListener, com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener {
    public static final int NOTIFICATION_REQUEST = 68;
    private static final int REQUEST_WRITE_STORAGE = 65;
    private boolean alreadyExecutedIntent;
    private io.objectbox.Box downloadEntryBox;
    protected androidx.fragment.app.Fragment mContent;
    private android.app.NotificationManager mManager;
    private android.content.BroadcastReceiver mMessageReceiver;
    private android.view.View scrim_view;
    private boolean started;

    static bridge synthetic io.objectbox.Box -$$Nest$fgetdownloadEntryBox(com.bisimplex.firebooru.activity.MainActivity p0)
    {
        return p0.downloadEntryBox;
    }

    public MainActivity()
    {
        super(2131886156);
        super.mMessageReceiver = new com.bisimplex.firebooru.activity.MainActivity$2(super);
        return;
    }

    public MainActivity(int p1)
    {
        super(p1);
        super.mMessageReceiver = new com.bisimplex.firebooru.activity.MainActivity$2(super);
        return;
    }

    private void checkIntent(android.content.Intent p8)
    {
        if (!this.alreadyExecutedIntent) {
            this.alreadyExecutedIntent = 1;
            String v1 = p8.getStringExtra("query");
            String v3 = p8.getStringExtra("server");
            String v5 = p8.getStringExtra("user");
            com.bisimplex.firebooru.danbooru.BooruProvider v8_1 = p8.getData();
            if ((v8_1 != null) && (v8_1.isHierarchical())) {
                if (v3 == null) {
                    v3 = v8_1.getQueryParameter("server");
                }
                if (v1 == null) {
                    v1 = v8_1.getQueryParameter("query");
                }
                if (v5 == null) {
                    v5 = v8_1.getQueryParameter("user");
                }
            }
            if (v1 != null) {
                this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(v1), this.providerFromServerName(v3, v5));
            }
        }
        return;
    }

    private android.app.NotificationManager getNotificationManager()
    {
        if (this.mManager == null) {
            this.mManager = ((android.app.NotificationManager) this.getSystemService("notification"));
        }
        return this.mManager;
    }

    private void pauseDownloadService()
    {
        com.bisimplex.firebooru.services.DownloadService.getInstance().cancelAll();
        com.bisimplex.firebooru.model.ObjectBox.get().runInTx(new com.bisimplex.firebooru.activity.MainActivity$5(this));
        com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().cancelAll(this);
        return;
    }

    private com.bisimplex.firebooru.danbooru.BooruProvider providerFromServerName(String p3, String p4)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v3_4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerByNamePart(p3, p4, 1);
            if (v3_4 != null) {
                return com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(v3_4);
            } else {
                return 0;
            }
        } else {
            return com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
        }
    }

    private void requestStoragePermission()
    {
        String[] v0_1 = new String[1];
        v0_1[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
        androidx.core.app.ActivityCompat.requestPermissions(this, v0_1, 65);
        return;
    }

    private void resumeDownloadService()
    {
        com.bisimplex.firebooru.services.DownloadService.getInstance().checkIfMustLaunch(this, this.downloadEntryBox);
        com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().checkIfMustLaunch(this);
        return;
    }

    public void ValidateUser()
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().showLock()) {
            this.showScreenLock(com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_VALIDATE);
            return;
        } else {
            this.showHome();
            this.setDrawerEnabled(1);
            return;
        }
    }

    protected void backPressed()
    {
        boolean v0_0 = this.mContent;
        if (!(v0_0 instanceof com.bisimplex.firebooru.fragment.LockFragment)) {
            if (!(v0_0 instanceof com.bisimplex.firebooru.fragment.BaseFragment)) {
                if (!this.closeMenuIfOpen()) {
                    super.backPressed();
                    return;
                }
            } else {
                if ((!this.closeMenuIfOpen()) && (!((com.bisimplex.firebooru.fragment.BaseFragment) v0_0).onBackPressed())) {
                    super.backPressed();
                    return;
                }
            }
        } else {
            boolean v0_3 = ((com.bisimplex.firebooru.fragment.LockFragment) v0_0);
            if ((v0_3.isDisable()) || (v0_3.isEnable())) {
                super.backPressed();
                this.setDrawerEnabled(1);
                return;
            }
        }
        return;
    }

    public void checkYume()
    {
        this.reloadMenuOptions();
        this.invalidateOptionsMenu();
        return;
    }

    public com.bisimplex.firebooru.fragment.BaseFragment createDefaultFragment()
    {
        return new com.bisimplex.firebooru.fragment.HomeFragment();
    }

    public void createNotificationChannels()
    {
        android.app.NotificationChannel v0_1 = new android.app.NotificationChannel("com.bisimplex.firebooru.ANDROID", "Anime boxes", 2);
        v0_1.enableVibration(0);
        this.getNotificationManager().createNotificationChannel(v0_1);
        return;
    }

    protected void drawerOpened(android.view.View p1)
    {
        super.drawerOpened(p1);
        com.bisimplex.firebooru.fragment.IBaseFragment v1_1 = this.mContent;
        if (v1_1 != null) {
            ((com.bisimplex.firebooru.fragment.IBaseFragment) v1_1).menuOpened();
        }
        return;
    }

    protected com.bisimplex.firebooru.network.Source findOnScreenSource()
    {
        int v0_0 = this.mContent;
        if (!(v0_0 instanceof com.bisimplex.firebooru.fragment.ISourceFragment)) {
            return 0;
        } else {
            return ((com.bisimplex.firebooru.fragment.ISourceFragment) v0_0).getSource();
        }
    }

    protected com.bisimplex.firebooru.danbooru.BooruProvider getFrontProvider()
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_0 = this.findOnScreenSource();
        if (v0_0 == null) {
            return super.getFrontProvider();
        } else {
            return v0_0.getProvider();
        }
    }

    public com.bisimplex.firebooru.view.SearchDialog$OnSearchDialogListener getSearchDialogListener()
    {
        return this;
    }

    public boolean getStarted()
    {
        return this.started;
    }

    protected void historyPanelTap(com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p1, com.bisimplex.firebooru.model.TagHistory p2)
    {
        if ((p2 != null) && (p1 != null)) {
            this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(p2.search), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance());
            this.closeSecondaryMenu();
        }
        return;
    }

    protected void infoPanelTap(com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p6, com.bisimplex.firebooru.danbooru.DanbooruPost p7)
    {
        if ((p7 != null) && (p6 != null)) {
            com.bisimplex.firebooru.custom.SecondaryMenuActionType v0_0;
            com.bisimplex.firebooru.custom.SecondaryMenuActionType v0_1 = p6.getTag();
            if (v0_1 == null) {
                v0_0 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.fromInteger(((int) p6.getIdentifier()));
            } else {
                v0_0 = ((com.bisimplex.firebooru.custom.SecondaryMenuActionType) v0_1);
            }
            switch (com.bisimplex.firebooru.activity.MainActivity$6.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuActionType[v0_0.ordinal()]) {
                case 2:
                    if ((p7.getSample().renderResolution().equalsIgnoreCase(p7.getFile().renderResolution())) || (p7.getVisibleVersion() == p7.getSample())) {
                    } else {
                        p7.setEnforceOriginalImage(0);
                        this.reloadImage();
                    }
                    break;
                case 3:
                    if (p7.getFile().getUrl().equalsIgnoreCase(p7.getSample().getUrl())) {
                    } else {
                        p7.setEnforceOriginalImage(1);
                        this.reloadImage();
                    }
                    break;
                case 4:
                    if (!(p6 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                    } else {
                        this.openURL(((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) p6).getName().getText(this));
                    }
                    break;
                case 5:
                    if (!(p6 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                    } else {
                        String v7_5 = this.mContent;
                        if (!(v7_5 instanceof com.bisimplex.firebooru.fragment.ISourceFragment)) {
                        } else {
                            String v7_6 = ((com.bisimplex.firebooru.fragment.ISourceFragment) v7_5);
                            com.bisimplex.firebooru.danbooru.BooruProvider v1_3 = v7_6.getSource().getProvider();
                            if ((v7_6.getSource() instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
                                v1_3 = com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance();
                            }
                            this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) p6).getName().getText(this)), v1_3, 0);
                        }
                    }
                    break;
                case 6:
                    if (android.text.TextUtils.isEmpty(p7.getMd5())) {
                    } else {
                        this.copyToClipboard(p7.getMd5());
                        this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                    }
                    break;
                case 7:
                    if ((android.text.TextUtils.isEmpty(p7.getPostId())) || (!p7.getHas_children())) {
                    } else {
                        com.bisimplex.firebooru.network.SourceQuery v6_34 = this.mContent;
                        if (!(v6_34 instanceof com.bisimplex.firebooru.fragment.ISourceFragment)) {
                        } else {
                            com.bisimplex.firebooru.network.SourceQuery v6_35 = ((com.bisimplex.firebooru.fragment.ISourceFragment) v6_34);
                            com.bisimplex.firebooru.danbooru.BooruProvider v1_21 = v6_35.getSource().getProvider();
                            if ((v6_35.getSource() instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
                                v1_21 = com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance();
                            }
                            this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(String.format("parent:%s", new Object[] {p7.getPostId()}))), v1_21, 0);
                        }
                    }
                    break;
                case 8:
                    if (!p7.hasParent()) {
                    } else {
                        com.bisimplex.firebooru.network.SourceQuery v6_25 = this.mContent;
                        if (!(v6_25 instanceof com.bisimplex.firebooru.fragment.ISourceFragment)) {
                        } else {
                            com.bisimplex.firebooru.network.SourceQuery v6_26 = ((com.bisimplex.firebooru.fragment.ISourceFragment) v6_25);
                            com.bisimplex.firebooru.danbooru.BooruProvider v1_18 = v6_26.getSource().getProvider();
                            if ((v6_26.getSource() instanceof com.bisimplex.firebooru.network.SourceFavorites)) {
                                v1_18 = com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance();
                            }
                            this.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(String.format("id:%s", new Object[] {p7.getParent_id()}))), v1_18, 0);
                        }
                    }
                    break;
                case 9:
                    this.copyToClipboard(p7.getPostId());
                    this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                    break;
                case 10:
                    com.bisimplex.firebooru.network.SourceQuery v6_21 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this);
                    v6_21.setTitle(2131887237).setMessage(p7.getAuthor()).setPositiveButton(2131886176, new com.bisimplex.firebooru.activity.MainActivity$4(this, p7)).setNegativeButton(2131886239, 0).setCancelable(1).setNeutralButton(2131886281, new com.bisimplex.firebooru.activity.MainActivity$3(this, p7));
                    v6_21.show();
                    break;
                default:
            }
            if (v0_0 != com.bisimplex.firebooru.custom.SecondaryMenuActionType.None) {
                this.closeSecondaryMenu();
            }
        }
        return;
    }

    public void launchBrowser(String p1, boolean p2)
    {
        if (!android.text.TextUtils.isEmpty(p1)) {
            this.launchExternalBrowser(p1);
            return;
        } else {
            return;
        }
    }

    public void launchExternalBrowser(String p4)
    {
        try {
            if (android.util.Patterns.WEB_URL.matcher(p4).matches()) {
                this.startActivity(new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(p4)));
                return;
            } else {
                return;
            }
        } catch (android.content.Intent v0_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_2);
            this.ShowMessage(this.getString(2131886498, new Object[] {p4})), com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    public void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        Class v0_0 = 0;
        if (p5 != null) {
            this.alreadyExecutedIntent = p5.getBoolean("alreadyExecutedIntent", 0);
        }
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isSecureScreen()) {
            this.getWindow().setFlags(8192, 8192);
        }
        if (android.os.Build$VERSION.SDK_INT >= 27) {
            androidx.core.view.WindowCompat.setDecorFitsSystemWindows(this.getWindow(), 0);
        }
        if ((android.os.Build$VERSION.SDK_INT <= 29) && (androidx.core.content.ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)) {
            this.requestStoragePermission();
        }
        if (p5 == null) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getDefaultServer();
        } else {
            this.mContent = this.getSupportFragmentManager().getFragment(p5, "mContent");
            String v1_12 = p5.getBoolean("isLoading");
            if ((this.mContent instanceof com.bisimplex.firebooru.fragment.LockFragment)) {
                this.setDrawerEnabled(0);
            }
            v0_0 = v1_12;
        }
        if (this.mContent == null) {
            this.mContent = this.createDefaultFragment();
        }
        this.getSupportFragmentManager().beginTransaction().replace(2131361964, this.mContent).commit();
        this.getSupportFragmentManager().addOnBackStackChangedListener(new com.bisimplex.firebooru.activity.MainActivity$1(this));
        this.setIsSmokeScreenVisible(v0_0);
        this.getApplicationContext().getDatabasePath("idanbooru.db");
        this.buildMainMenu();
        if (p5 == null) {
            this.ValidateUser();
        }
        this.createNotificationChannels();
        this.downloadEntryBox = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        return;
    }

    public void onDialogFilterFavorite(androidx.fragment.app.DialogFragment p3, com.bisimplex.firebooru.danbooru.ServerItem p4, com.bisimplex.firebooru.danbooru.FavoriteSortType p5)
    {
        com.bisimplex.firebooru.fragment.ServerChangerFragment v0_0 = this.mContent;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.fragment.ServerChangerFragment))) {
            ((com.bisimplex.firebooru.fragment.ServerChangerFragment) v0_0).selectedServer(p3, p4, p5);
            this.reloadMenuOptions();
        }
        return;
    }

    public void onDialogGroupSpecsSaved(androidx.fragment.app.DialogFragment p2, com.bisimplex.firebooru.model.SourceSpecs p3)
    {
        com.bisimplex.firebooru.fragment.HomeFragment v2_0 = this.mContent;
        if ((v2_0 != null) && ((v2_0 instanceof com.bisimplex.firebooru.fragment.HomeFragment))) {
            ((com.bisimplex.firebooru.fragment.HomeFragment) v2_0).updateGroupName(p3);
        }
        return;
    }

    public void onDialogGroupSpecsSaved(androidx.fragment.app.DialogFragment p2, com.bisimplex.firebooru.model.SourceSpecs p3, String p4, boolean p5)
    {
        com.bisimplex.firebooru.fragment.HomeFragment v2_0 = this.mContent;
        if ((v2_0 != null) && ((v2_0 instanceof com.bisimplex.firebooru.fragment.HomeFragment))) {
            ((com.bisimplex.firebooru.fragment.HomeFragment) v2_0).addGroup(p3, p4, p5);
        }
        return;
    }

    public void onDialogJumpToPageSelected(androidx.fragment.app.DialogFragment p2, int p3)
    {
        com.bisimplex.firebooru.fragment.PostListFragment v2_0 = this.mContent;
        if ((v2_0 != null) && ((v2_0 instanceof com.bisimplex.firebooru.fragment.PostListFragment))) {
            ((com.bisimplex.firebooru.fragment.PostListFragment) v2_0).jumpToPage(p3);
        }
        return;
    }

    public void onDialogNegativeClick(androidx.fragment.app.DialogFragment p1)
    {
        return;
    }

    public void onDialogNewFolderClick(androidx.fragment.app.DialogFragment p3, String p4, com.bisimplex.firebooru.network.SourceType p5)
    {
        com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener v0_0 = this.mContent;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener))) {
            ((com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener) v0_0).onDialogNewFolderClick(p3, p4, p5);
        }
        return;
    }

    public void onDialogServerSelected(androidx.fragment.app.DialogFragment p3, com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        com.bisimplex.firebooru.fragment.IServerChanger v0_0 = this.mContent;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.fragment.IServerChanger))) {
            ((com.bisimplex.firebooru.fragment.IServerChanger) v0_0).selectedServer(p3, p4);
            this.reloadMenuOptions();
        }
        return;
    }

    public void onDialogSpecsDuplicated(androidx.fragment.app.DialogFragment p3, com.bisimplex.firebooru.model.SourceSpecs p4, com.bisimplex.firebooru.model.SourceSpecs p5, com.bisimplex.firebooru.model.SourceSpecs p6)
    {
        com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener v0_0 = this.mContent;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener))) {
            ((com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener) v0_0).onDialogSpecsDuplicated(p3, p4, p5, p6);
        }
        return;
    }

    public void onDialogSpecsEdited(androidx.fragment.app.DialogFragment p4, com.bisimplex.firebooru.model.SourceSpecs p5)
    {
        com.bisimplex.firebooru.fragment.HomeFragment v4_0 = this.mContent;
        if ((v4_0 != null) && ((v4_0 instanceof com.bisimplex.firebooru.fragment.HomeFragment))) {
            com.bisimplex.firebooru.fragment.HomeFragment v4_1 = ((com.bisimplex.firebooru.fragment.HomeFragment) v4_0);
            com.bisimplex.firebooru.network.Source v0_0 = com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(com.bisimplex.firebooru.network.SourceType.Permanent, p5.getKey());
            if (v0_0 != null) {
                com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(v0_0, p5);
            }
            v4_1.updateGroupName(p5);
        }
        return;
    }

    public void onDialogSpecsSaved(androidx.fragment.app.DialogFragment p3, com.bisimplex.firebooru.model.SourceSpecs p4, boolean p5)
    {
        com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener v0_0 = this.mContent;
        if ((v0_0 != null) && ((v0_0 instanceof com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener))) {
            ((com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener) v0_0).onDialogSpecsSaved(p3, p4, p5);
        }
        return;
    }

    public void onDialogStartSlideshow(androidx.fragment.app.DialogFragment p2, int p3)
    {
        com.bisimplex.firebooru.fragment.DetailFragment v2_0 = this.mContent;
        if ((v2_0 != null) && ((v2_0 instanceof com.bisimplex.firebooru.fragment.DetailFragment))) {
            ((com.bisimplex.firebooru.fragment.DetailFragment) v2_0).startSlideshow(p3, 1);
        }
        return;
    }

    public void onDialogValidateClientSaved(androidx.fragment.app.DialogFragment p1)
    {
        return;
    }

    public boolean onKeyDown(int p2, android.view.KeyEvent p3)
    {
        if ((!(this.mContent instanceof com.bisimplex.firebooru.fragment.BaseFragment)) || ((this.getOpenDrawerIndex() != 0) || (!((com.bisimplex.firebooru.fragment.BaseFragment) this.mContent).onKeyDown(p2, p3)))) {
            return super.onKeyDown(p2, p3);
        } else {
            return 1;
        }
    }

    public boolean onKeyUp(int p5, android.view.KeyEvent p6)
    {
        boolean v0_0 = this.getOpenDrawerIndex();
        if ((v0_0) || ((p5 != 111) && (p5 != 67))) {
            com.bisimplex.firebooru.fragment.BaseFragment v2_3 = this.mContent;
            if ((!(v2_3 instanceof com.bisimplex.firebooru.fragment.BaseFragment)) || ((v0_0) || (!((com.bisimplex.firebooru.fragment.BaseFragment) v2_3).onKeyUp(p5, p6)))) {
                return super.onKeyUp(p5, p6);
            } else {
                return 1;
            }
        } else {
            if (v0_0 != 1) {
                this.closeSecondaryMenu();
            } else {
                this.closeDrawer();
            }
            return 1;
        }
    }

    public boolean onOptionsItemSelected(android.view.MenuItem p3)
    {
        if (p3.getItemId() == 16908332) {
            if (this.isDrawerEnabled()) {
                this.openDrawer();
            }
            return 1;
        } else {
            return super.onOptionsItemSelected(p3);
        }
    }

    public void onProvideKeyboardShortcuts(java.util.List p3, android.view.Menu p4, int p5)
    {
        java.util.List v0_0 = this.mContent;
        if ((v0_0 instanceof com.bisimplex.firebooru.fragment.BaseFragment)) {
            java.util.List v0_2 = ((com.bisimplex.firebooru.fragment.BaseFragment) v0_0).provideKeyboardShortcuts();
            if ((v0_2 != null) && (!v0_2.isEmpty())) {
                p3.addAll(v0_2);
            }
        }
        super.onProvideKeyboardShortcuts(p3, p4, p5);
        return;
    }

    public void onRequestPermissionsResult(int p2, String[] p3, int[] p4)
    {
        super.onRequestPermissionsResult(p2, p3, p4);
        if (p2 == 8) {
            if ((p4.length <= 0) || (p4[0] != 0)) {
                this.ShowMessage(2131887269, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            } else {
                com.bisimplex.firebooru.fragment.PostListFragment v2_1 = this.mContent;
                if (!(v2_1 instanceof com.bisimplex.firebooru.fragment.DetailFragment)) {
                    if ((v2_1 instanceof com.bisimplex.firebooru.fragment.PostListFragment)) {
                        ((com.bisimplex.firebooru.fragment.PostListFragment) v2_1).addAllToDownloads();
                    }
                } else {
                    com.bisimplex.firebooru.fragment.PostListFragment v2_3 = ((com.bisimplex.firebooru.fragment.DetailFragment) v2_1);
                    if (!v2_3.getWasTryingToDownload()) {
                        v2_3.saveIt();
                        return;
                    } else {
                        v2_3.addToDownload();
                        return;
                    }
                }
            }
        } else {
            if (p2 == 65) {
                if (p4.length > 0) {
                    return;
                }
            } else {
                if (p2 == 68) {
                    if ((p4.length <= 0) || (p4[0] != 0)) {
                        android.util.Log.i("NOTIFICATION", "not granted");
                        return;
                    } else {
                        android.util.Log.i("NOTIFICATION", "Notifications request granted");
                        return;
                    }
                }
            }
        }
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p4)
    {
        super.onSaveInstanceState(p4);
        this.getSupportFragmentManager().putFragment(p4, "mContent", this.mContent);
        p4.putBoolean("isLoading", this.isLoadingSomething());
        p4.putBoolean("alreadyExecutedIntent", this.alreadyExecutedIntent);
        return;
    }

    public void onSearch(com.bisimplex.firebooru.network.SourceQuery p3, com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener v0_0 = this.mContent;
        if ((v0_0 instanceof com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver)) {
            ((com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver) v0_0).getSearchDialogListener().onSearch(p3, p4);
        }
        return;
    }

    public void onSearchSources(com.bisimplex.firebooru.network.SourceQuery p3, java.util.List p4)
    {
        com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener v0_0 = this.mContent;
        if ((v0_0 instanceof com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver)) {
            ((com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver) v0_0).getSearchDialogListener().onSearchSources(p3, p4);
        }
        return;
    }

    public void onStart()
    {
        super.onStart();
        this.checkYume();
        this.started = 1;
        this.resumeDownloadService();
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).registerReceiver(this.mMessageReceiver, new android.content.IntentFilter(com.bisimplex.firebooru.danbooru.UserConfiguration.NEWVERSIONAPP));
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().checkAppVersion();
        this.checkIntent(this.getIntent());
        return;
    }

    public void onStop()
    {
        super.onStop();
        this.started = 0;
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(com.bisimplex.firebooru.DroidBooruApplication.getAppContext()).unregisterReceiver(this.mMessageReceiver);
        this.pauseDownloadService();
        return;
    }

    public void reloadImage()
    {
        com.bisimplex.firebooru.fragment.DetailFragment v0_0 = this.mContent;
        if ((v0_0 instanceof com.bisimplex.firebooru.fragment.DetailFragment)) {
            ((com.bisimplex.firebooru.fragment.DetailFragment) v0_0).reloadSelectedImage();
        }
        this.closeDrawer();
        return;
    }

    public void searchQuery(com.bisimplex.firebooru.network.SourceQuery p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        this.searchQuery(p2, p3, 0);
        return;
    }

    public void searchQuery(com.bisimplex.firebooru.network.SourceQuery p6, com.bisimplex.firebooru.danbooru.BooruProvider p7, boolean p8)
    {
        com.bisimplex.firebooru.fragment.PostListFragment v0_1 = new com.bisimplex.firebooru.fragment.PostListFragment();
        v0_1.setShouldResetStack(p8);
        String v7_2 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post, p7, p6);
        if (p6.getInitialPage() > 0) {
            v7_2.setPageOffset(((int) (p6.getInitialPage() - 1)));
        }
        android.os.Bundle v8_4 = new android.os.Bundle(1);
        v8_4.putString("SOURCE_KEY", v7_2.getKey());
        v0_1.setArguments(v8_4);
        this.switchContent(v0_1);
        this.setTitle(p6.getTitle());
        return;
    }

    public void setScrimVisible(boolean p1)
    {
        return;
    }

    public void showHome()
    {
        androidx.fragment.app.FragmentManager v0 = this.getSupportFragmentManager();
        if (v0.getBackStackEntryCount() > 0) {
            v0.popBackStack(v0.getBackStackEntryAt(0).getName(), 1);
        }
        return;
    }

    public void showScreenLock(int p3)
    {
        android.os.Bundle v0_1 = new android.os.Bundle();
        v0_1.putInt(com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE, p3);
        int v3_2 = new com.bisimplex.firebooru.fragment.LockFragment();
        v3_2.setArguments(v0_1);
        this.switchContent(v3_2);
        this.setDrawerEnabled(0);
        return;
    }

    public void switchContent(androidx.fragment.app.Fragment p5)
    {
        this.HideLoading();
        if (((com.bisimplex.firebooru.fragment.IBaseFragment) p5).getShouldResetStack()) {
            this.showHome();
        }
        try {
            if (this.getCurrentFocus() != null) {
                ((android.view.inputmethod.InputMethodManager) this.getSystemService("input_method")).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (androidx.fragment.app.FragmentTransaction v1_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v1_1);
        }
        if ((!(p5 instanceof com.bisimplex.firebooru.fragment.HomeFragment)) || ((p5 instanceof com.bisimplex.firebooru.fragment.MultiSearchFragment))) {
            this.mContent = p5;
            p5.setHasOptionsMenu(1);
            androidx.fragment.app.FragmentTransaction v1_5 = this.getSupportActionBar();
            if ((v1_5 != null) && (!v1_5.isShowing())) {
                v1_5.show();
            }
            androidx.fragment.app.FragmentTransaction v1_8 = this.getSupportFragmentManager().beginTransaction();
            if (!((com.bisimplex.firebooru.fragment.IBaseFragment) p5).getShouldResetStack()) {
                v1_8.setTransition(4097);
            }
            v1_8.replace(2131361964, p5);
            v1_8.addToBackStack(0);
            v1_8.commit();
            this.closeDrawer();
            return;
        } else {
            this.closeDrawer();
            return;
        }
    }
}
