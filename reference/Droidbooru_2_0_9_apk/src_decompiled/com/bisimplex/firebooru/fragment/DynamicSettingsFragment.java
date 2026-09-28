package com.bisimplex.firebooru.fragment;
public class DynamicSettingsFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    com.bisimplex.firebooru.dataadapter.SettingsDataAdapter adapter;
    private final com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener adapterListener;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private final androidx.activity.result.ActivityResultLauncher openDocumentLauncher;
    androidx.recyclerview.widget.RecyclerView recyclerView;

    public static synthetic void $r8$lambda$BQxjLxj80fz5wqt-HahROQ2BotE(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0, android.content.Context p1)
    {
        p0.lambda$clearCache$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$Ujdef3HgwYrY577BqFebQW35Mkk(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.lambda$updateCacheSize$3();
        return;
    }

    public static synthetic void $r8$lambda$cQHsJ_BBCyIzF6Ff2kSKo5HKPeo(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.lambda$clearCache$0();
        return;
    }

    public static synthetic void $r8$lambda$tui4nOBRqpG3S-a1xPOVu0vYEwg(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0, String p1)
    {
        p0.lambda$updateCacheSize$2(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mbackup(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.backup();
        return;
    }

    static bridge synthetic void -$$Nest$mclearCache(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.clearCache();
        return;
    }

    static bridge synthetic void -$$Nest$mclearCookies(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.clearCookies();
        return;
    }

    static bridge synthetic void -$$Nest$mconfigureProxy(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.configureProxy();
        return;
    }

    static bridge synthetic void -$$Nest$mconfigureScreenLock(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.configureScreenLock();
        return;
    }

    static bridge synthetic void -$$Nest$mdeleteFavorites(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.deleteFavorites();
        return;
    }

    static bridge synthetic void -$$Nest$mdeleteHistory(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.deleteHistory();
        return;
    }

    static bridge synthetic void -$$Nest$mdeletePostHistory(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.deletePostHistory();
        return;
    }

    static bridge synthetic void -$$Nest$meditFileNameFormatting(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.editFileNameFormatting();
        return;
    }

    static bridge synthetic void -$$Nest$mreloadTheme(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.reloadTheme();
        return;
    }

    static bridge synthetic void -$$Nest$mresetStorageLocation(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.resetStorageLocation();
        return;
    }

    static bridge synthetic void -$$Nest$mrestore(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.restore();
        return;
    }

    static bridge synthetic void -$$Nest$mselectStorageLocation(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.selectStorageLocation();
        return;
    }

    static bridge synthetic void -$$Nest$msendEventLog(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.sendEventLog();
        return;
    }

    static bridge synthetic void -$$Nest$msetSaveDirectory(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0, android.net.Uri p1)
    {
        p0.setSaveDirectory(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mshowCookies(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.showCookies();
        return;
    }

    static bridge synthetic void -$$Nest$mupdateApp(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.updateApp();
        return;
    }

    static bridge synthetic void -$$Nest$mupdateStatusBarVisible(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0, boolean p1)
    {
        p0.updateStatusBarVisible(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mvalidateClient(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.validateClient();
        return;
    }

    static bridge synthetic void -$$Nest$mviewChangeLog(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.viewChangeLog();
        return;
    }

    static bridge synthetic void -$$Nest$mviewEULA(com.bisimplex.firebooru.fragment.DynamicSettingsFragment p0)
    {
        p0.viewEULA();
        return;
    }

    public DynamicSettingsFragment()
    {
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.openDocumentLauncher = this.registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree(), new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$1(this));
        this.adapterListener = new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$2(this);
        return;
    }

    private void backup()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.BackupFragment());
        }
        return;
    }

    private void clearCache()
    {
        android.content.Context v0 = this.requireContext();
        this.ShowLoading();
        this.executor.execute(new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$$ExternalSyntheticLambda0(this, v0));
        return;
    }

    private void clearCookies()
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration v0_1 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().cookieJar();
        if ((v0_1 instanceof com.franmontiel.persistentcookiejar.PersistentCookieJar)) {
            ((com.franmontiel.persistentcookiejar.PersistentCookieJar) v0_1).clear();
        }
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().removeAllServerWithCookies();
        return;
    }

    private void configureProxy()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.ProxyFormFragment());
            return;
        } else {
            return;
        }
    }

    private void configureScreenLock()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            int v1_0;
            if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().existLock()) {
                v1_0 = com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_ENABLE;
            } else {
                v1_0 = com.bisimplex.firebooru.fragment.LockFragment.LOCK_MODE_DISABLE;
            }
            v0_1.showScreenLock(v1_0);
            return;
        } else {
            return;
        }
    }

    private void deleteFavorites()
    {
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_2.setMessage(2131886275).setTitle(2131886393);
        v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$3(this));
        v0_2.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$4(this));
        v0_2.create().show();
        return;
    }

    private void deleteHistory()
    {
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_2.setMessage(2131886275).setTitle(2131886394);
        v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$5(this));
        v0_2.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$6(this));
        v0_2.create().show();
        return;
    }

    private void deletePostHistory()
    {
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_2.setMessage(2131886275).setTitle(2131886395);
        v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$7(this));
        v0_2.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$8(this));
        v0_2.create().show();
        return;
    }

    private void editFileNameFormatting()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.DynamicFileNameFragment());
            return;
        } else {
            return;
        }
    }

    private synthetic void lambda$clearCache$0()
    {
        if ((this.getActivity() != null) && (this.adapter != null)) {
            com.bumptech.glide.Glide.get(this.getActivity()).clearMemory();
            this.HideLoading();
            this.showMessage(2131887193, com.bisimplex.firebooru.activity.MessageType.Success);
            this.updateCacheSize();
        }
        return;
    }

    private synthetic void lambda$clearCache$1(android.content.Context p5)
    {
        com.bumptech.glide.Glide.get(p5).clearDiskCache();
        String v0_9 = com.bumptech.glide.Glide.getPhotoCacheDir(p5, "image_cache");
        String v1_1 = p5.getExternalCacheDir();
        android.os.Handler v5_5 = p5.getCacheDir();
        String v2_1 = new java.util.ArrayList();
        if ((v5_5 != null) && (v5_5.canRead())) {
            this.listf(v5_5, v2_1);
        }
        if ((v0_9 != null) && (v0_9.canRead())) {
            this.listf(v0_9, v2_1);
        }
        if ((v1_1 != null) && (v1_1.canRead())) {
            this.listf(v1_1, v2_1);
        }
        android.os.Handler v5_3 = v2_1.iterator();
        while (v5_3.hasNext()) {
            String v0_6 = ((java.io.File) v5_3.next());
            android.util.Log.i("file", v0_6.getAbsolutePath());
            if (!v0_6.delete()) {
                android.util.Log.i("file", "can\'t delete file");
            }
        }
        this.enqueueHandler.post(new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$$ExternalSyntheticLambda2(this));
        return;
    }

    private synthetic void lambda$updateCacheSize$2(String p2)
    {
        if (this.getActivity() != null) {
            com.bisimplex.firebooru.dataadapter.SettingsDataAdapter v0_1 = this.adapter;
            if (v0_1 != null) {
                v0_1.updateCacheSize(p2);
            }
        }
        return;
    }

    private synthetic void lambda$updateCacheSize$3()
    {
        String v0_1 = new java.util.ArrayList();
        android.os.Handler v1_3 = this.requireContext();
        com.bisimplex.firebooru.fragment.DynamicSettingsFragment$$ExternalSyntheticLambda3 v2_3 = v1_3.getCacheDir();
        if ((v2_3 != null) && (v2_3.canRead())) {
            v0_1.add(v2_3);
        }
        android.os.Handler v1_0 = v1_3.getExternalCacheDir();
        if ((v1_0 != null) && (v1_0.canRead())) {
            v0_1.add(v1_0);
        }
        this.enqueueHandler.post(new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$$ExternalSyntheticLambda3(this, com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().dirSize(v0_1)));
        return;
    }

    private void listf(java.io.File p5, java.util.ArrayList p6)
    {
        java.io.File[] v5_1 = p5.listFiles();
        int v0 = v5_1.length;
        int v1 = 0;
        while (v1 < v0) {
            java.io.File v2 = v5_1[v1];
            if (!v2.isFile()) {
                if (v2.isDirectory()) {
                    this.listf(v2, p6);
                }
            } else {
                p6.add(v2);
            }
            v1++;
        }
        return;
    }

    private void reloadTheme()
    {
        com.bisimplex.firebooru.skin.SkinManager.getInstance().reloadTheme();
        this.restartActivity();
        return;
    }

    private void resetStorageLocation()
    {
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setSDPath(0);
        this.adapter.updateSavePath();
        return;
    }

    private void restartActivity()
    {
        androidx.core.app.TaskStackBuilder v0_3 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_3 != null) {
            androidx.core.app.TaskStackBuilder.create(this.requireContext()).addNextIntent(new android.content.Intent(this.requireContext(), com.bisimplex.firebooru.activity.MainActivity)).addNextIntent(v0_3.getIntent()).startActivities();
            return;
        } else {
            return;
        }
    }

    private void restore()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.RestoreFragment());
        }
        return;
    }

    private void selectStorageLocation()
    {
        if (this.getActivity() != null) {
            this.openDocumentLauncher.launch(0);
            return;
        } else {
            return;
        }
    }

    private void sendEventLog()
    {
        org.acra.ACRA.getErrorReporter().handleSilentException(new Exception(this.getString(2131887083)));
        return;
    }

    private void setSaveDirectory(android.net.Uri p2)
    {
        if (p2 != null) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setSDPath(p2.toString());
            this.adapter.updateSavePath();
            return;
        } else {
            return;
        }
    }

    private void showCookies()
    {
        try {
            int v1_3 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient().cookieJar();
            String v2_8 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
            StringBuilder v3_1 = okhttp3.HttpUrl.parse(v2_8.getUrl());
        } catch (com.google.android.material.dialog.MaterialAlertDialogBuilder v0_5) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_5);
            return;
        }
        if (v3_1 != null) {
            int v1_0 = v1_3.loadForRequest(v3_1);
            StringBuilder v3_3 = new StringBuilder(this.getString(2131887138));
            v3_3.append(": ");
            v3_3.append(v2_8.getServerName());
            v3_3.append("\n");
            if (!v1_0.isEmpty()) {
                int v1_2 = v1_0.iterator();
                while (v1_2.hasNext()) {
                    v3_3.append(((okhttp3.Cookie) v1_2.next()).toString());
                    v3_3.append("\n");
                }
            } else {
                v3_3.append(this.getString(2131886730));
            }
            com.google.android.material.dialog.MaterialAlertDialogBuilder v0_4 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
            v0_4.setTitle(2131887158);
            v0_4.setMessage(v3_3.toString());
            v0_4.setPositiveButton(2131886239, 0);
            v0_4.show();
        }
        return;
    }

    private void updateApp()
    {
        this.launchBrowser(this.getString(2131886151));
        return;
    }

    private void updateCacheSize()
    {
        this.executor.execute(new com.bisimplex.firebooru.fragment.DynamicSettingsFragment$$ExternalSyntheticLambda1(this));
        return;
    }

    private void updateStatusBarVisible(boolean p2)
    {
        ((com.bisimplex.firebooru.activity.MainActivity) this.requireActivity()).setStatusBarVisible(p2);
        return;
    }

    private void validateClient()
    {
        new com.bisimplex.firebooru.view.ValidateClientDialog().show(this.getParentFragmentManager(), "ValidateClientDialog");
        return;
    }

    private void viewChangeLog()
    {
        this.launchBrowser(this.getString(2131886150));
        return;
    }

    private void viewEULA()
    {
        this.launchBrowser(this.getString(2131886513));
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public String getiOsFragmentName()
    {
        return "SettingsViewController";
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558638, p3, 0);
    }

    public void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        int v2_6 = ((androidx.recyclerview.widget.RecyclerView) p2.findViewById(2131362449));
        this.recyclerView = v2_6;
        v2_6.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext()));
        int v2_2 = new com.bisimplex.firebooru.dataadapter.SettingsDataAdapter(this.requireContext());
        this.adapter = v2_2;
        v2_2.setListener(this.adapterListener);
        this.recyclerView.setAdapter(this.adapter);
        this.setTitle(2131886862);
        this.updateCacheSize();
        return;
    }
}
