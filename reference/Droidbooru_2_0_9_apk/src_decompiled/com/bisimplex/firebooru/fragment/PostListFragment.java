package com.bisimplex.firebooru.fragment;
public class PostListFragment extends com.bisimplex.firebooru.fragment.ServerChangerFragment implements com.bisimplex.firebooru.dataadapter.GridPostDataAdapter$IPostItemClickListener, com.bisimplex.firebooru.network.SourceListener, com.bisimplex.firebooru.fragment.ISourceFragment, com.bisimplex.firebooru.fragment.ISavePermissionFragment, com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener {
    public static final String SOURCE_KEY = "SOURCE_KEY";
    protected static final int STORAGE_TARGET_DOWNLOAD_ALL = 1;
    protected static final int STORAGE_TARGET_EXPORT = 2;
    protected com.bisimplex.firebooru.network.SourcePostBasic _source;
    private String _sourceKey;
    private com.bisimplex.firebooru.backup.BackupCVSOptions backupCVSOptions;
    private com.bisimplex.firebooru.services.DownloadOptions batchDownloadOptions;
    private int currentStorageTarget;
    protected com.bisimplex.firebooru.dataadapter.GridPostDataAdapter dataAdapter;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private androidx.recyclerview.widget.RecyclerView listView;
    protected androidx.recyclerview.widget.RecyclerView$LayoutManager mLayoutManager;
    protected boolean mustScrollToTop;
    private int navigationBottomMargin;
    private android.widget.TextView noResultTextView;
    private final androidx.activity.result.ActivityResultLauncher openDocumentLauncher;
    protected android.widget.TextView pageTextView;
    private android.os.Parcelable recyclerViewState;
    private boolean resetStack;
    private boolean scrollToVisible;
    public final com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener searchDialogListener;
    private boolean shouldStarLoadingAPage;
    protected android.widget.TextView subtitleTextView;
    private int targetScrollEndIndex;
    private int targetScrollStartIndex;
    protected android.widget.TextView titleTextView;

    public static synthetic boolean $r8$lambda$QtZNy0rXaCngnaxtOeMXVzm9Z74(com.bisimplex.firebooru.fragment.PostListFragment p0, android.view.MenuItem p1)
    {
        return p0.lambda$onViewCreated$0(p1);
    }

    public static synthetic void $r8$lambda$dZmNK330GVv1z5Sx1VS-N5jbdPU(com.bisimplex.firebooru.fragment.PostListFragment p0, com.bisimplex.firebooru.network.SourceQuery p1, com.bisimplex.firebooru.services.DownloadOptions p2)
    {
        p0.lambda$downloadAllFavoritesWithQuery$2(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$wFzqXoqwrV3BFR2xnjBKFGf6dNA(com.bisimplex.firebooru.fragment.PostListFragment p0, java.util.List p1, com.bisimplex.firebooru.services.DownloadOptions p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        p0.lambda$downloadAllFavoritesWithQuery$1(p1, p2, p3);
        return;
    }

    static bridge synthetic int -$$Nest$fgetnavigationBottomMargin(com.bisimplex.firebooru.fragment.PostListFragment p0)
    {
        return p0.navigationBottomMargin;
    }

    static bridge synthetic void -$$Nest$fputbackupCVSOptions(com.bisimplex.firebooru.fragment.PostListFragment p0, com.bisimplex.firebooru.backup.BackupCVSOptions p1)
    {
        p0.backupCVSOptions = p1;
        return;
    }

    static bridge synthetic void -$$Nest$fputbatchDownloadOptions(com.bisimplex.firebooru.fragment.PostListFragment p0, com.bisimplex.firebooru.services.DownloadOptions p1)
    {
        p0.batchDownloadOptions = p1;
        return;
    }

    static bridge synthetic void -$$Nest$mdownloadAllFavoritesWithQuery(com.bisimplex.firebooru.fragment.PostListFragment p0, com.bisimplex.firebooru.services.DownloadOptions p1, com.bisimplex.firebooru.network.SourceQuery p2)
    {
        p0.downloadAllFavoritesWithQuery(p1, p2);
        return;
    }

    static bridge synthetic void -$$Nest$mrebindData(com.bisimplex.firebooru.fragment.PostListFragment p0)
    {
        p0.rebindData();
        return;
    }

    static bridge synthetic void -$$Nest$mtriggerStorageAccessFramework(com.bisimplex.firebooru.fragment.PostListFragment p0, int p1)
    {
        p0.triggerStorageAccessFramework(p1);
        return;
    }

    public PostListFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.PostListFragment$4(this);
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.openDocumentLauncher = this.registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree(), new com.bisimplex.firebooru.fragment.PostListFragment$14(this));
        this.navigationBottomMargin = 0;
        return;
    }

    private boolean addToDownload(com.bisimplex.firebooru.danbooru.DanbooruPost p6)
    {
        if (this.savePermissionGranted()) {
            if ((((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()) != null) && (p6 != 0)) {
                com.bisimplex.firebooru.activity.MessageType v0_1 = new java.util.ArrayList(0);
                v0_1.add(p6);
                int v6_2 = new com.bisimplex.firebooru.services.DownloadOptions();
                v6_2.setAvoidDuplicates(0);
                v6_2.setExcludeAnimated(0);
                com.bisimplex.firebooru.services.DownloadService.getInstance().enqueueWork(this.getContext(), v0_1, v6_2, new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131887166)));
                this.showMessage(2131886165, com.bisimplex.firebooru.activity.MessageType.Minimal);
                this.checkNotificationPermission();
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int calculateNoOfColumns(android.content.Context p1, float p2)
    {
        int v1_4 = p1.getResources().getDisplayMetrics();
        return ((int) Math.floor(((double) ((((float) v1_4.widthPixels) / v1_4.density) / p2))));
    }

    private void downloadAllFavoritesWithQuery(com.bisimplex.firebooru.services.DownloadOptions p3, com.bisimplex.firebooru.network.SourceQuery p4)
    {
        this.executor.execute(new com.bisimplex.firebooru.fragment.PostListFragment$$ExternalSyntheticLambda1(this, p4, p3));
        return;
    }

    private String getBatchSaveToLocation()
    {
        String v0_0 = this.batchDownloadOptions;
        if ((v0_0 != null) && (!android.text.TextUtils.isEmpty(v0_0.getTarget_folder()))) {
            return this.batchDownloadOptions.getTarget_folder();
        } else {
            return com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSDPath();
        }
    }

    private String getBatchSaveToName()
    {
        String v0_0 = this.getBatchSaveToLocation();
        if (!android.text.TextUtils.isEmpty(v0_0)) {
            try {
                String v0_8 = android.net.Uri.parse(v0_0);
            } catch (String v0_5) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_5);
                return this.getString(2131886380);
            }
            if (v0_8 != null) {
                String v0_1 = androidx.documentfile.provider.DocumentFile.fromTreeUri(this.requireActivity(), v0_8);
                if (v0_1 != null) {
                    return v0_1.getName();
                } else {
                    return this.getString(2131886380);
                }
            } else {
                return this.getString(2131886380);
            }
        } else {
            return this.getString(2131886380);
        }
    }

    private String getSourceKey()
    {
        if (android.text.TextUtils.isEmpty(this._sourceKey)) {
            String v0_7 = this.getArguments();
            if ((v0_7 == null) || (!v0_7.containsKey("SOURCE_KEY"))) {
                this._sourceKey = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post).getKey();
            } else {
                this._sourceKey = v0_7.getString("SOURCE_KEY");
            }
        }
        return this._sourceKey;
    }

    private synthetic void lambda$downloadAllFavoritesWithQuery$1(java.util.List p3, com.bisimplex.firebooru.services.DownloadOptions p4, com.bisimplex.firebooru.network.SourceQuery p5)
    {
        if (this.getActivity() != null) {
            com.bisimplex.firebooru.services.DownloadService.getInstance().enqueueWork(this.requireActivity(), p3, p4, p5);
        }
        return;
    }

    private synthetic void lambda$downloadAllFavoritesWithQuery$2(com.bisimplex.firebooru.network.SourceQuery p4, com.bisimplex.firebooru.services.DownloadOptions p5)
    {
        this.enqueueHandler.post(new com.bisimplex.firebooru.fragment.PostListFragment$$ExternalSyntheticLambda2(this, com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadFavoritesByFilter(p4, -1), p5, p4));
        return;
    }

    private synthetic boolean lambda$onViewCreated$0(android.view.MenuItem p1)
    {
        this.onMenuClick(p1);
        return 1;
    }

    private void rebindData()
    {
        if (this.listView != null) {
            androidx.recyclerview.widget.RecyclerView v0_2 = this.dataAdapter;
            if (v0_2 != null) {
                v0_2.setDisplayMode(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThumbDisplayMode());
                this.listView.setLayoutManager(this.generateLayoutManager());
                this.listView.setAdapter(this.dataAdapter);
            }
        }
        return;
    }

    private void shareThis()
    {
        String v0_1 = this.getSource().getProvider().generateSearchUrl(this.getSource().getQuery().getText());
        if (v0_1 != null) {
            this.shareUrl(v0_1.toString());
        }
        return;
    }

    private void showJumpToPage()
    {
        new com.bisimplex.firebooru.view.JumpToPageDialog().show(this.requireActivity().getSupportFragmentManager(), "jumpToPage");
        return;
    }

    private void triggerStorageAccessFramework(int p2)
    {
        if (this.getActivity() != null) {
            this.currentStorageTarget = p2;
            this.openDocumentLauncher.launch(0);
            return;
        } else {
            return;
        }
    }

    public void LoadData()
    {
        this.ShowLoading();
        this.getSource().loadAnotherPage(this);
        return;
    }

    public void RebindData()
    {
        if (this.getSource() != null) {
            int v0_3;
            this.dataAdapter.setItems(this.getSource().getData());
            if (this.getSource().getItemCount() != 0) {
                v0_3 = 0;
            } else {
                v0_3 = 1;
            }
            this.showNoResults(v0_3);
            return;
        } else {
            return;
        }
    }

    public void addAllToDownloads()
    {
        if (this.savePermissionGranted()) {
            com.google.android.material.dialog.MaterialAlertDialogBuilder v0_4 = this.getActivity();
            if ((v0_4 != null) && (this.getSource() != null)) {
                java.util.List v9 = this.getSource().getData();
                if ((v9 != null) && (!v9.isEmpty())) {
                    com.google.android.material.dialog.MaterialAlertDialogBuilder v1_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_4);
                    com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3 = this.getActivity().getLayoutInflater().inflate(2131558479, 0);
                    if (this.batchDownloadOptions == null) {
                        this.batchDownloadOptions = new com.bisimplex.firebooru.services.DownloadOptions();
                    }
                    int v4_0 = this.batchDownloadOptions;
                    android.widget.CheckBox v6_1 = ((android.widget.CheckBox) v0_3.findViewById(2131362021));
                    com.bisimplex.firebooru.services.DownloadOptions v5_1 = ((android.widget.CheckBox) v0_3.findViewById(2131361883));
                    android.widget.CheckBox v7_1 = ((android.widget.CheckBox) v0_3.findViewById(2131362396));
                    com.bisimplex.firebooru.fragment.PostListFragment$10 v2_12 = ((android.widget.TextView) v0_3.findViewById(2131362416));
                    android.widget.CheckBox v8_0 = this.getSource().getQuery();
                    v6_1.setChecked(v4_0.isAvoidDuplicates());
                    v5_1.setChecked(v4_0.isExcludeAnimated());
                    v7_1.setChecked(v4_0.isDownloadOriginal());
                    v2_12.setText(this.getBatchSaveToName());
                    v1_3.setTitle(2131886170).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.PostListFragment$13(this)).setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.PostListFragment$12(this, v4_0, v5_1, v6_1, v7_1, v8_0, v9)).setNeutralButton(2131887100, new com.bisimplex.firebooru.fragment.PostListFragment$11(this, v4_0, v5_1, v6_1, v7_1)).setOnDismissListener(new com.bisimplex.firebooru.fragment.PostListFragment$10(this)).setView(v0_3).show();
                }
            }
        }
        return;
    }

    protected void askDisplayMode()
    {
        com.bisimplex.firebooru.fragment.PostListFragment$6 v0_0 = this.getActivity();
        if (v0_0 != null) {
            com.google.android.material.dialog.MaterialAlertDialogBuilder v1_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_0);
            com.bisimplex.firebooru.fragment.PostListFragment$6 v0_4 = this.getResources().getStringArray(2130903041);
            v1_1.setTitle(2131886426);
            v1_1.setItems(v0_4, new com.bisimplex.firebooru.fragment.PostListFragment$5(this));
            v1_1.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.PostListFragment$6(this));
            v1_1.show();
            return;
        } else {
            return;
        }
    }

    void askShowServers()
    {
        this.showServers(this.getSource().getProvider().getServerDescription(), 0, 0);
        return;
    }

    protected void askToExportCSV()
    {
        if (this.savePermissionGranted()) {
            com.bisimplex.firebooru.fragment.PostListFragment$9 v0_8 = this.getActivity();
            if ((v0_8 != null) && (this.getSource() != null)) {
                if (this.backupCVSOptions == null) {
                    this.backupCVSOptions = new com.bisimplex.firebooru.backup.BackupCVSOptions();
                }
                int v1_2 = this.backupCVSOptions;
                com.google.android.material.dialog.MaterialAlertDialogBuilder v2_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_8);
                com.bisimplex.firebooru.fragment.PostListFragment$9 v0_2 = this.backupCVSOptions.isFileURL();
                com.bisimplex.firebooru.fragment.PostListFragment$7 v3_1 = this.backupCVSOptions.isPostURL();
                boolean v4_1 = this.backupCVSOptions.isMD5();
                boolean[] v5_1 = new boolean[3];
                v5_1[0] = v0_2;
                v5_1[1] = v3_1;
                v5_1[2] = v4_1;
                v2_1.setTitle(2131886574);
                v2_1.setMultiChoiceItems(2130903047, v5_1, new com.bisimplex.firebooru.fragment.PostListFragment$8(this, v1_2)).setPositiveButton(2131886573, new com.bisimplex.firebooru.fragment.PostListFragment$7(this, v1_2));
                v2_1.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.PostListFragment$9(this));
                v2_1.show();
            }
        }
        return;
    }

    public void attachedToWindow(int p4)
    {
        int v0_0 = this.getSource();
        if ((v0_0 != 0) && ((!v0_0.isNewSearch()) && (!v0_0.getQuery().isDisableAutoLoad()))) {
            boolean v1_0 = v0_0.getIsLoading();
            boolean v2 = v0_0.isLastPage();
            if ((!v1_0) && ((!v2) && (p4 >= (v0_0.getItemCount() - 4)))) {
                this.LoadData();
            }
        }
        return;
    }

    protected void beginBackupToCSV(android.net.Uri p3)
    {
        if (this.getSource() != null) {
            com.bisimplex.firebooru.activity.MessageType v0_16 = this.getSource().getQuery();
            if (v0_16 != null) {
                int v1_2 = new com.bisimplex.firebooru.network.SourceQuery(v0_16);
                if (this.backupCVSOptions == null) {
                    this.backupCVSOptions = new com.bisimplex.firebooru.backup.BackupCVSOptions();
                }
                if (p3 != 0) {
                    this.backupCVSOptions.setUri(p3);
                    this.backupCVSOptions.setQuery(v1_2);
                    if (this.getSource().getType() != com.bisimplex.firebooru.network.SourceType.Favorites) {
                        this.backupCVSOptions.setPosts(this.getSource().getData());
                        this.backupCVSOptions.setFileNamePrefix(this.getString(2131887111));
                    } else {
                        this.backupCVSOptions.setPosts(0);
                        this.backupCVSOptions.setFileNamePrefix(this.getString(2131886853));
                    }
                    com.bisimplex.firebooru.backup.BackupManager.getInstance().beginBackupToCSV(this.backupCVSOptions);
                    this.backupCVSOptions = 0;
                    this.showMessage(2131886586, com.bisimplex.firebooru.activity.MessageType.Success);
                    return;
                } else {
                    this.showMessage(2131886496, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
        }
        return;
    }

    protected void clearMemoryCache()
    {
        com.bumptech.glide.Glide v0_0 = this.getContext();
        if (v0_0 != null) {
            com.bumptech.glide.Glide.get(v0_0).clearMemory();
        }
        return;
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p3)
    {
        super.configureBar(p3);
        com.bisimplex.firebooru.fragment.PostListFragment$3 v0_4 = p3.getMenu().findItem(2131362167);
        if (v0_4 != null) {
            v0_4.setVisible(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().hasHydrusServers());
        }
        if ((p3 instanceof com.google.android.material.bottomappbar.BottomAppBar)) {
            ((com.google.android.material.bottomappbar.BottomAppBar) p3).addOnScrollStateChangedListener(new com.bisimplex.firebooru.fragment.PostListFragment$3(this));
        }
        return;
    }

    protected void configureInsets(androidx.core.graphics.Insets p5, androidx.core.graphics.Insets p6)
    {
        super.configureInsets(p5, p6);
        if (this.pageTextView != null) {
            int v6_1 = (p6.bottom + this.getResources().getDimensionPixelSize(2131165370));
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
                int v5_5 = new android.util.TypedValue();
                this.navigationBottomMargin = 0;
                android.util.DisplayMetrics v0_1 = this.requireActivity();
                if (v0_1.getTheme().resolveAttribute(16843499, v5_5, 1)) {
                    this.navigationBottomMargin = android.util.TypedValue.complexToDimensionPixelSize(v5_5.data, v0_1.getResources().getDisplayMetrics());
                }
                v6_1 += this.navigationBottomMargin;
            }
            ((android.view.ViewGroup$MarginLayoutParams) this.pageTextView.getLayoutParams()).bottomMargin = v6_1;
        }
        return;
    }

    protected void configureSearchDialog(com.bisimplex.firebooru.network.SourcePostBasic p6, com.bisimplex.firebooru.view.DynamicSearchDialog p7)
    {
        android.os.Bundle v0_1 = new android.os.Bundle(4);
        String v1_3 = new java.util.ArrayList();
        String v2_6 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getMultiSearchSelectedServerIds();
        if ((v2_6 != null) && (!v2_6.isEmpty())) {
            v1_3.addAll(v2_6);
        }
        String v2_2 = p6.getProvider().getServerDescription().getServerId();
        java.util.Iterator v3_1 = v1_3.iterator();
        while (v3_1.hasNext()) {
            if (((Integer) v3_1.next()).intValue() == v2_2) {
            }
            v0_1.putIntegerArrayList("MULTI_SERVER_SELECTED_IDS", v1_3);
            String v6_1 = p6.getQuery();
            if (v6_1 != null) {
                v0_1.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v6_1));
            }
            p7.setArguments(v0_1);
            return;
        }
        v1_3.add(Integer.valueOf(v2_2));
    }

    public void failure(com.bisimplex.firebooru.network.Source p2, com.bisimplex.firebooru.data.FailureType p3)
    {
        if ((this.getActivity() != null) && (!this.isDetached())) {
            this.HideLoading();
            this.showMessage(p3, p2.getLastErrorCode());
        }
        return;
    }

    protected androidx.recyclerview.widget.RecyclerView$LayoutManager generateLayoutManager()
    {
        int v0_5 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThumbDisplayMode();
        if (v0_5 != com.bisimplex.firebooru.custom.ThumbDisplayMode.Staggered) {
            if ((v0_5 != com.bisimplex.firebooru.custom.ThumbDisplayMode.Big) && (v0_5 != com.bisimplex.firebooru.custom.ThumbDisplayMode.BigNoCrop)) {
                int v0_1 = 1123024896;
            } else {
                v0_1 = 1127219200;
            }
            this.mLayoutManager = new androidx.recyclerview.widget.GridLayoutManager(this.getContext(), com.bisimplex.firebooru.fragment.PostListFragment.calculateNoOfColumns(this.requireContext(), v0_1), 1, 0);
        } else {
            int v0_4 = new androidx.recyclerview.widget.StaggeredGridLayoutManager(this.getResources().getInteger(2131427348), 1);
            v0_4.setGapStrategy(2);
            this.mLayoutManager = v0_4;
        }
        return this.mLayoutManager;
    }

    protected android.view.View getInsetContentView()
    {
        return this.listView;
    }

    protected int getMenuID()
    {
        return 2131689493;
    }

    public com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener getSearchDialogListener()
    {
        return this.searchDialogListener;
    }

    public boolean getShouldResetStack()
    {
        return this.resetStack;
    }

    public bridge synthetic com.bisimplex.firebooru.network.Source getSource()
    {
        return this.getSource();
    }

    public com.bisimplex.firebooru.network.SourcePostBasic getSource()
    {
        if (this._source == null) {
            this._source = ((com.bisimplex.firebooru.network.SourcePostBasic) com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(this.getSourceType(), this.getSourceKey()));
        }
        return this._source;
    }

    protected com.bisimplex.firebooru.network.SourceType getSourceType()
    {
        return com.bisimplex.firebooru.network.SourceType.Post;
    }

    public String getiOsFragmentName()
    {
        return "SearchViewController";
    }

    protected void invalidateMenu()
    {
        return;
    }

    public void itemClick(android.view.View p3, int p4)
    {
        int v3_3 = this.dataAdapter.getItem(p4);
        if ((v3_3 == 0) || (v3_3.getType() != 1)) {
            if (p4 != 0) {
                int v3_1 = this.dataAdapter.getItem(0);
                if ((v3_1 != 0) && (v3_1.getType() == 1)) {
                    p4--;
                }
            }
            this.showPostAtIndex(p4);
            return;
        } else {
            this.askShowServers();
            return;
        }
    }

    public void jumpToPage(int p2)
    {
        com.bisimplex.firebooru.network.SourcePostBasic v0_0 = this.dataAdapter;
        if (v0_0 != null) {
            v0_0.clearItems();
        }
        this.setPageLabelVisibility(0);
        com.bisimplex.firebooru.network.SourcePostBasic v0_2 = this.getSource();
        v0_2.reset();
        v0_2.setPageOffset(p2);
        this.mustScrollToTop = 1;
        this.clearMemoryCache();
        this.LoadData();
        return;
    }

    public boolean onBackPressed()
    {
        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(this.getSource());
        return super.onBackPressed();
    }

    public void onCreate(android.os.Bundle p3)
    {
        super.onCreate(p3);
        if (p3) {
            this.shouldStarLoadingAPage = p3.getBoolean("shouldStarLoadingAPage", 1);
            return;
        } else {
            this.shouldStarLoadingAPage = 1;
            return;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        if (p4 != 0) {
            this._sourceKey = p4.getString("SOURCE_KEY");
        }
        android.view.View v2_1 = p2.inflate(2131558435, p3, 0);
        com.bisimplex.firebooru.network.SourcePostBasic v3_1 = this.getSource();
        if (v3_1 != null) {
            v3_1.setListener(this);
            return v2_1;
        } else {
            return v2_1;
        }
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
                    new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886445).setMessage(this.getString(2131886446, new Object[] {v3_4}))).setNegativeButton(2131886205, 0).setPositiveButton(2131887271, new com.bisimplex.firebooru.fragment.PostListFragment$18(this, v2_1)).show();
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
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886445).setMessage(this.getString(2131886446, new Object[] {v6_1}))).setNegativeButton(2131886205, 0).setPositiveButton(2131887099, new com.bisimplex.firebooru.fragment.PostListFragment$17(this, p4, p5, p3)).show();
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

    public boolean onLongClick(android.view.View p3, int p4)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isLongTapToDownload()) {
            boolean v3_5 = this.dataAdapter.getItem(p4);
            if ((!v3_5) || (v3_5.getType() != 1)) {
                if (!v3_5) {
                    return 0;
                } else {
                    return this.addToDownload(v3_5.getPost());
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    protected void onMenuClick(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131362157) {
            if (p3.getItemId() != 2131362498) {
                if (p3.getItemId() != 2131362519) {
                    if (p3.getItemId() != 2131362421) {
                        if (p3.getItemId() != 2131362010) {
                            if (p3.getItemId() != 2131362193) {
                                if (p3.getItemId() != 2131362525) {
                                    if (p3.getItemId() != 2131362167) {
                                        if (p3.getItemId() != 2131362007) {
                                            if (p3.getItemId() != 2131362100) {
                                                if (p3.getItemId() == 2131362691) {
                                                    this.askToValidateClient();
                                                }
                                                return;
                                            } else {
                                                this.askToExportCSV();
                                                return;
                                            }
                                        } else {
                                            this.askDisplayMode();
                                            return;
                                        }
                                    } else {
                                        this.sendPostToHydrus();
                                        return;
                                    }
                                } else {
                                    this.shareThis();
                                    return;
                                }
                            } else {
                                this.showJumpToPage();
                                return;
                            }
                        } else {
                            this.addAllToDownloads();
                            return;
                        }
                    } else {
                        this.pinToHome();
                        return;
                    }
                } else {
                    this.askShowServers();
                    return;
                }
            } else {
                this.showSearchField();
                return;
            }
        } else {
            this.showSearchHistory();
            return;
        }
    }

    public void onResume()
    {
        super.onResume();
        int v0_1 = this.getSource();
        if (v0_1 == 0) {
            androidx.recyclerview.widget.GridLayoutManager v1_5 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
            if (v1_5 != null) {
                v1_5.showHome();
                return;
            }
        }
        if ((this.mLayoutManager == null) || ((this.dataAdapter == null) || ((v0_1 == 0) || (!this.scrollToVisible)))) {
            int v0_2 = 0;
        } else {
            int v0_0 = v0_1.getVisiblePostIndex();
            androidx.recyclerview.widget.GridLayoutManager v1_2 = this.mLayoutManager;
            if (!(v1_2 instanceof androidx.recyclerview.widget.GridLayoutManager)) {
            } else {
                androidx.recyclerview.widget.GridLayoutManager v1_3 = ((androidx.recyclerview.widget.GridLayoutManager) v1_2);
                androidx.recyclerview.widget.RecyclerView v3_1 = v1_3.findFirstVisibleItemPosition();
                com.bisimplex.firebooru.fragment.PostListFragment$16 v4_0 = v1_3.findLastVisibleItemPosition();
                if ((v3_1 == v4_0) || ((v0_0 >= v3_1) && (v0_0 <= v4_0))) {
                } else {
                    this.listView.postDelayed(new com.bisimplex.firebooru.fragment.PostListFragment$16(this, v1_3, v0_0), 200);
                    this.updatePageLabel(v0_0);
                    v0_2 = 1;
                }
            }
        }
        this.scrollToVisible = 0;
        if (v0_2 == 0) {
            this.updatePageLabel();
        }
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putBoolean("shouldStarLoadingAPage", this.shouldStarLoadingAPage);
        p3.putString("SOURCE_KEY", this.getSourceKey());
        String v0_13 = this.mLayoutManager;
        if (v0_13 != null) {
            String v0_1 = v0_13.onSaveInstanceState();
            this.recyclerViewState = v0_1;
            p3.putParcelable("recyclerViewState", v0_1);
        }
        p3.putInt("targetScrollStartIndex", this.targetScrollStartIndex);
        p3.putInt("targetScrollEndIndex", this.targetScrollEndIndex);
        p3.putInt("currentStorageTarget", this.currentStorageTarget);
        p3.putBoolean("scrollToVisible", this.scrollToVisible);
        if (this.batchDownloadOptions != null) {
            p3.putString("batchDownloadOptions", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.batchDownloadOptions));
        }
        if (this.backupCVSOptions != null) {
            p3.putString("backupCVSOptions", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.backupCVSOptions));
        }
        return;
    }

    public void onViewCreated(android.view.View p6, android.os.Bundle p7)
    {
        super.onViewCreated(p6, p7);
        com.bisimplex.firebooru.network.SourcePostBasic v0 = this.getSource();
        if (v0 != null) {
            com.bisimplex.firebooru.fragment.PostListFragment$$ExternalSyntheticLambda0 v7_1;
            if (p7 != null) {
                v7_1 = 0;
            } else {
                v7_1 = v0.getQuery().isDisableAutoLoad();
            }
            this.listView = ((androidx.recyclerview.widget.RecyclerView) p6.findViewById(2131362211));
            this.pageTextView = ((android.widget.TextView) p6.findViewById(2131362402));
            int v2_7 = new com.bisimplex.firebooru.dataadapter.GridPostDataAdapter(this.getActivity(), this, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThumbDisplayMode());
            this.dataAdapter = v2_7;
            v2_7.setAttempFixURLs((v0 instanceof com.bisimplex.firebooru.network.SourceFavorites));
            this.listView.setAdapter(this.dataAdapter);
            this.listView.setLayoutManager(this.generateLayoutManager());
            this.listView.setHasFixedSize(1);
            this.noResultTextView = ((android.widget.TextView) p6.findViewById(2131362365));
            this.titleTextView = ((android.widget.TextView) p6.findViewById(2131362651));
            this.subtitleTextView = ((android.widget.TextView) p6.findViewById(2131362584));
            if (v7_1 == null) {
                if (!this.shouldStarLoadingAPage) {
                    this.RebindData();
                } else {
                    this.clearMemoryCache();
                    this.LoadData();
                    this.shouldStarLoadingAPage = 0;
                }
            } else {
                this.shouldStarLoadingAPage = 0;
            }
            this.listView.addOnScrollListener(new com.bisimplex.firebooru.fragment.PostListFragment$1(this));
            this.setTitle(v0.getQuery().getTitle());
            this.setSourceName(v0.getProvider().getServerDescription().getServerName());
            androidx.appcompat.widget.Toolbar v6_10 = this.getVisibleBar();
            v6_10.getMenu().clear();
            v6_10.inflateMenu(this.getMenuID());
            v6_10.setNavigationOnClickListener(new com.bisimplex.firebooru.fragment.PostListFragment$2(this));
            this.configureBar(v6_10);
            v6_10.setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.PostListFragment$$ExternalSyntheticLambda0(this));
            return;
        } else {
            return;
        }
    }

    public void onViewStateRestored(android.os.Bundle p5)
    {
        super.onViewStateRestored(p5);
        if (p5 != null) {
            this._sourceKey = p5.getString("SOURCE_KEY");
            if (this.mLayoutManager != null) {
                com.google.gson.Gson v0_0 = p5.getParcelable("recyclerViewState");
                this.recyclerViewState = v0_0;
                this.mLayoutManager.onRestoreInstanceState(v0_0);
            }
            this.targetScrollStartIndex = p5.getInt("targetScrollStartIndex");
            this.targetScrollEndIndex = p5.getInt("targetScrollEndIndex");
            this.currentStorageTarget = p5.getInt("currentStorageTarget");
            this.scrollToVisible = p5.getBoolean("scrollToVisible");
            com.google.gson.Gson v0_11 = p5.getString("batchDownloadOptions", "");
            if (!android.text.TextUtils.isEmpty(v0_11)) {
                this.batchDownloadOptions = ((com.bisimplex.firebooru.services.DownloadOptions) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_11, com.bisimplex.firebooru.services.DownloadOptions));
            }
            com.bisimplex.firebooru.backup.BackupCVSOptions v5_1 = p5.getString("backupCVSOptions", "");
            if (!android.text.TextUtils.isEmpty(v5_1)) {
                this.backupCVSOptions = ((com.bisimplex.firebooru.backup.BackupCVSOptions) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v5_1, com.bisimplex.firebooru.backup.BackupCVSOptions));
            }
        }
        return;
    }

    protected void pinToHome()
    {
        androidx.fragment.app.FragmentManager v0_0 = this.getSource();
        if ((v0_0 != null) && (v0_0.getProvider() != null)) {
            com.bisimplex.firebooru.view.PinDialog v1_2 = new com.bisimplex.firebooru.view.PinDialog();
            String v2_0 = new android.os.Bundle(1);
            v2_0.putString("SOURCE_ID", v0_0.getKey());
            v2_0.putInt("SOURCE_TYPE", v0_0.getType().getValue());
            v1_2.setArguments(v2_0);
            v1_2.show(this.getParentFragmentManager(), "PinDialog_TAG");
        }
        return;
    }

    public void reloadData()
    {
        int v0_0 = this.dataAdapter;
        if (v0_0 != 0) {
            v0_0.clearItems();
        }
        this.getSource().reset();
        this.setPageLabelVisibility(0);
        this.mustScrollToTop = 1;
        this.clearMemoryCache();
        this.LoadData();
        this.invalidateMenu();
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    public void selectedServer(androidx.fragment.app.DialogFragment p2, com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        if ((!p2) || ((p2.getTag() == null) || (!p2.getTag().equalsIgnoreCase("hydrus")))) {
            this.getSource().setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p3));
            this.setSourceName(p3.getServerName());
            this.reloadData();
            return;
        } else {
            this.sendPostToHydrus(p3);
            return;
        }
    }

    protected void sendPostToHydrus()
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
                v0_3.setArguments(v1_4);
                v1_4.putBoolean("SERVER_FILTER_CHANGE_ON_SELECT", 0);
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

    protected void sendPostToHydrus(com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        com.bisimplex.firebooru.activity.MessageType v0_0 = this.getSource();
        if ((v0_0 != null) && (p3 != 0)) {
            com.bisimplex.firebooru.network.HydrusClient.getInstance().sendToHydrus(p3, v0_0.getData());
            this.showMessage(2131887194, com.bisimplex.firebooru.activity.MessageType.Success);
        }
        return;
    }

    protected void setPageLabelVisibility(boolean p2)
    {
        if (p2 == null) {
            this.pageTextView.setVisibility(8);
            return;
        } else {
            this.pageTextView.setVisibility(0);
            return;
        }
    }

    public void setSaveDirectory(android.net.Uri p3)
    {
        if (this.currentStorageTarget != 2) {
            if (this.batchDownloadOptions == null) {
                this.batchDownloadOptions = new com.bisimplex.firebooru.services.DownloadOptions();
            }
            if (p3 != null) {
                this.batchDownloadOptions.setTarget_folder(p3.toString());
                this.addAllToDownloads();
                return;
            } else {
                this.batchDownloadOptions.setTarget_folder("");
                return;
            }
        } else {
            this.beginBackupToCSV(p3);
            return;
        }
    }

    public void setShouldResetStack(boolean p1)
    {
        this.resetStack = p1;
        return;
    }

    protected void setSourceName(String p1)
    {
        this.setSubtitle(p1);
        return;
    }

    public void setSubtitle(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.subtitleTextView.setText(p3);
            this.subtitleTextView.setVisibility(0);
        } else {
            this.subtitleTextView.setVisibility(8);
        }
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useTopBar()) {
            this.getVisibleBar().setSubtitle(p3);
        }
        return;
    }

    public void setTitle(String p3)
    {
        super.setTitle(p3);
        androidx.appcompat.widget.Toolbar v0 = this.getVisibleBar();
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.titleTextView.setText(p3);
            v0.setTitle(p3);
            return;
        } else {
            this.titleTextView.setText(2131886860);
            v0.setTitle(2131886860);
            return;
        }
    }

    public void showNoResults(boolean p3)
    {
        int v1;
        if (!p3) {
            v1 = 8;
        } else {
            v1 = 0;
        }
        this.noResultTextView.setVisibility(v1);
        this.setPageLabelVisibility((p3 ^ 1));
        if (!p3) {
            this.updatePageLabel();
        }
        return;
    }

    public void showPostAtIndex(int p5)
    {
        if (p5 >= 0) {
            com.bisimplex.firebooru.fragment.DetailFragment v0_1 = new com.bisimplex.firebooru.fragment.DetailFragment();
            android.os.Bundle v1_1 = new android.os.Bundle(2);
            String v2_2 = this.getSource();
            v2_2.setVisiblePostIndex(p5);
            v1_1.putString("SOURCE_KEY", v2_2.getKey());
            v1_1.putInt("SOURCE_TYPE_KEY", v2_2.getType().getValue());
            v0_1.setArguments(v1_1);
            this.switchFragment(v0_1);
            this.scrollToVisible = 1;
            return;
        } else {
            return;
        }
    }

    protected void showSearchField()
    {
        androidx.fragment.app.FragmentManager v0_0 = this.getSource();
        if (v0_0 != null) {
            com.bisimplex.firebooru.view.DynamicSearchDialog v1_1 = new com.bisimplex.firebooru.view.DynamicSearchDialog();
            v1_1.setListener(this.searchDialogListener);
            this.configureSearchDialog(v0_0, v1_1);
            v1_1.show(this.getParentFragmentManager(), "SearchDialog_TAG");
            return;
        } else {
            return;
        }
    }

    public void success(com.bisimplex.firebooru.network.Source p4, java.util.List p5)
    {
        if ((this.getActivity() != null) && (!this.isDetached())) {
            this.dataAdapter.addItems(p5);
            int v0_12 = this.listView;
            int v1 = 0;
            if ((v0_12 != 0) && (this.mustScrollToTop)) {
                v0_12.scrollToPosition(0);
            }
            this.mustScrollToTop = 0;
            this.HideLoading();
            if ((p4.getData().isEmpty()) && (p4.isLastPage())) {
                v1 = 1;
            }
            this.showNoResults(v1);
            if ((v1 != 0) && ((p4 instanceof com.bisimplex.firebooru.network.SourcePostBasic))) {
                com.google.android.material.dialog.MaterialAlertDialogBuilder v4_2 = ((com.bisimplex.firebooru.network.SourcePostBasic) p4).getServerMessage();
                if (!android.text.TextUtils.isEmpty(v4_2)) {
                    this.showMessage(v4_2, com.bisimplex.firebooru.activity.MessageType.Info);
                }
            }
            android.util.Log.i("Source", String.format("final discarded %d", new Object[] {Integer.valueOf(this.getSource().getLastPageRemovedCount())})));
            if ((p5.isEmpty()) && (this.getSource().getLastPageRemovedCount() > 0)) {
                new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886987).setMessage(this.getString(2131887078, new Object[] {Integer.valueOf(this.getSource().getLastPageRemovedCount())}))).setPositiveButton(2131886799, new com.bisimplex.firebooru.fragment.PostListFragment$15(this)).setNegativeButton(2131886205, 0).show();
            }
        }
        return;
    }

    public void updatePageLabel()
    {
        int v0_0 = this.listView;
        if ((v0_0 != 0) && (this.pageTextView != null)) {
            int v0_2 = ((androidx.recyclerview.widget.GridLayoutManager) v0_0.getLayoutManager());
            if (v0_2 != 0) {
                this.updatePageLabel(v0_2.findLastVisibleItemPosition());
            }
        }
        return;
    }

    public void updatePageLabel(int p3)
    {
        if ((this.listView != null) && (this.pageTextView != null)) {
            if (p3 < null) {
                p3 = this.getSource().getItemCount();
            }
            this.pageTextView.setText(this.getString(2131887006, new Object[] {Integer.valueOf((this.getSource().pageNumberOfItemIndex(p3) + 1))})));
        }
        return;
    }
}
