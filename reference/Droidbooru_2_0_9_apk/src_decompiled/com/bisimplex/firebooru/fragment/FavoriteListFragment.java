package com.bisimplex.firebooru.fragment;
public class FavoriteListFragment extends com.bisimplex.firebooru.fragment.PostListFragment {
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    public final com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog$OnDynamicFavoriteSearchDialogListener searchDialogListener;
    private final com.bisimplex.firebooru.services.UpdateMetadataService$UpdateMetadataServiceListener updateMetadataServiceListener;

    public static synthetic void $r8$lambda$-Kd55jVChB84emxxwIVqX66HOrg(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, android.content.DialogInterface p1, int p2)
    {
        p0.lambda$askReloadAllItems$1(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$-ZFoWn3kPm9kxpFXrLenFaBrov0(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, android.content.DialogInterface p1, int p2)
    {
        p0.lambda$askCancelReload$0(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$16GAJ3E1sdtaCBY4ehb4hR91pAg(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, io.objectbox.Box p1, androidx.appcompat.app.AlertDialog p2)
    {
        p0.lambda$reloadAllItems$2(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$GQwsz1-UcBYBrs1gid8ttXwAdQ0(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, java.util.List p1, io.objectbox.Box p2, java.util.Date p3, androidx.appcompat.app.AlertDialog p4)
    {
        p0.lambda$reloadAllItems$3(p1, p2, p3, p4);
        return;
    }

    public static synthetic void $r8$lambda$Hh5J4Zg06nNXTrNx86UgdFYOvYk(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, com.bisimplex.firebooru.network.SourceQuery p1, androidx.appcompat.app.AlertDialog p2)
    {
        p0.lambda$reloadAllItems$4(p1, p2);
        return;
    }

    static bridge synthetic void -$$Nest$mupdateSourceName(com.bisimplex.firebooru.fragment.FavoriteListFragment p0, String p1)
    {
        p0.updateSourceName(p1);
        return;
    }

    public FavoriteListFragment()
    {
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        this.updateMetadataServiceListener = new com.bisimplex.firebooru.fragment.FavoriteListFragment$1(this);
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.FavoriteListFragment$4(this);
        return;
    }

    private void askCancelReload()
    {
        com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda2 v0_0 = this.getActivity();
        if (v0_0 != null) {
            int v1_13 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.UpdateEntry);
            int v2_4 = v1_13.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build();
            try {
                String v3_4 = v1_13.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 2).build();
                try {
                    int v1_6 = v1_13.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 3).build();
                    try {
                        Long v4_3 = v2_4.count();
                        long v6 = v3_4.count();
                        long v8 = v1_6.count();
                    } catch (com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda2 v0_5) {
                        if (v1_6 != 0) {
                            try {
                                v1_6.close();
                            } catch (int v1_8) {
                                v0_5.addSuppressed(v1_8);
                            }
                        }
                        throw v0_5;
                    }
                    if (v1_6 != 0) {
                        v1_6.close();
                    }
                    if (v3_4 != null) {
                        v3_4.close();
                    }
                    if (v2_4 != 0) {
                        v2_4.close();
                    }
                    int v1_15;
                    int v1_12 = com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().isRunning();
                    int v2_7 = com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().isStopped();
                    String v3_6 = this.getString(2131887235);
                    int v10 = 1;
                    if ((v1_12 == 0) || (v2_7 == 0)) {
                        if (v2_7 == 0) {
                            if (v1_12 == 0) {
                                v1_15 = this.getString(2131886994);
                                v3_6 = this.getString(2131887073);
                                v10 = 0;
                            } else {
                                v1_15 = this.getString(2131887096);
                            }
                        } else {
                            v1_15 = this.getString(2131887181);
                        }
                    } else {
                        v1_15 = this.getString(2131887183);
                    }
                    int v1_1 = this.getString(2131887178, new Object[] {v1_15, Long.valueOf(v4_3), Long.valueOf(v6), Long.valueOf(v8)}));
                    int v2_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_0);
                    v2_2.setTitle(v3_6).setMessage(v1_1).setNegativeButton(2131886239, 0);
                    if (v10 != 0) {
                        v2_2.setPositiveButton(2131886208, new com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda2(this));
                    }
                    v2_2.show();
                    return;
                } catch (com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda2 v0_6) {
                    if (v3_4 != null) {
                        try {
                            v3_4.close();
                        } catch (int v1_9) {
                            v0_6.addSuppressed(v1_9);
                        }
                    }
                    throw v0_6;
                }
            } catch (com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda2 v0_7) {
                if (v2_4 != 0) {
                    try {
                        v2_4.close();
                    } catch (int v1_10) {
                        v0_7.addSuppressed(v1_10);
                    }
                }
                throw v0_7;
            }
        } else {
            return;
        }
    }

    private void askReloadAllItems()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_0 = this.getActivity();
        if (v0_0 != null) {
            new com.google.android.material.dialog.MaterialAlertDialogBuilder(v0_0).setTitle(2131886173).setMessage(2131887074).setNegativeButton(2131886205, 0).setPositiveButton(2131887072, new com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda3(this)).show();
            return;
        } else {
            return;
        }
    }

    private synthetic void lambda$askCancelReload$0(android.content.DialogInterface p1, int p2)
    {
        com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().cancelAll(this.requireContext());
        this.invalidateMenu();
        this.showMessage(2131886209, com.bisimplex.firebooru.activity.MessageType.Minimal);
        return;
    }

    private synthetic void lambda$askReloadAllItems$1(android.content.DialogInterface p1, int p2)
    {
        this.reloadAllItems();
        return;
    }

    private synthetic void lambda$reloadAllItems$2(io.objectbox.Box p6, androidx.appcompat.app.AlertDialog p7)
    {
        long v3 = p6.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build().count();
        this.HideLoading();
        p7.dismiss();
        if (v3 > 0) {
            com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().enqueueWork(this.requireActivity());
            this.showMessage(2131886157, com.bisimplex.firebooru.activity.MessageType.Minimal);
            this.invalidateMenu();
        }
        return;
    }

    private synthetic void lambda$reloadAllItems$3(java.util.List p7, io.objectbox.Box p8, java.util.Date p9, androidx.appcompat.app.AlertDialog p10)
    {
        long v0_1 = new java.util.ArrayList();
        android.os.Handler v7_7 = p7.iterator();
        while (v7_7.hasNext()) {
            int v1_1 = ((com.bisimplex.firebooru.model.Favorite) v7_7.next());
            if (p8.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.fav_id, v1_1.getId().longValue()).and().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 0).build().count() <= 0) {
                com.bisimplex.firebooru.model.UpdateEntry v2_8 = new com.bisimplex.firebooru.model.UpdateEntry();
                v2_8.setFav_id(v1_1.getId().longValue());
                v2_8.setAdded_date(p9);
                v2_8.setStatus(0);
                v0_1.add(v2_8);
            }
        }
        if (v0_1.size() > 0) {
            p8.put(v0_1);
        }
        p8.query().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 2).or().equal(com.bisimplex.firebooru.model.UpdateEntry_.status, 3).build().remove();
        this.enqueueHandler.postDelayed(new com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda0(this, p8, p10), 250);
        return;
    }

    private synthetic void lambda$reloadAllItems$4(com.bisimplex.firebooru.network.SourceQuery p9, androidx.appcompat.app.AlertDialog p10)
    {
        io.objectbox.BoxStore v0 = com.bisimplex.firebooru.model.ObjectBox.get();
        v0.runInTx(new com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda4(this, com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadRawFavoritesByFilter(p9, -1), v0.boxFor(com.bisimplex.firebooru.model.UpdateEntry), new java.util.Date(), p10));
        return;
    }

    private void reloadAllItems()
    {
        this.ShowLoading();
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        v0_2.setTitle(2131886173).setMessage(2131886141).setCancelable(0);
        this.executor.execute(new com.bisimplex.firebooru.fragment.FavoriteListFragment$$ExternalSyntheticLambda1(this, this.getSource().getQuery(), v0_2.show()));
        return;
    }

    private void updateSourceName(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            this.setSourceName(p2);
            return;
        } else {
            this.setSourceName(this.getString(2131886145));
            return;
        }
    }

    protected void configureBar(androidx.appcompat.widget.Toolbar p3)
    {
        super.configureBar(p3);
        android.view.Menu v3_1 = p3.getMenu();
        this.setIconToMenuItem(v3_1, 2131361987, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_trash);
        this.setIconToMenuItem(v3_1, 2131362453, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_sync);
        this.setIconToMenuItem(v3_1, 2131362451, com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_sync);
        this.invalidateMenu();
        return;
    }

    public void deleteItems()
    {
        androidx.appcompat.app.AlertDialog v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        v0_2.setMessage(2131886275).setTitle(2131886393);
        v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.FavoriteListFragment$2(this));
        v0_2.setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.FavoriteListFragment$3(this));
        v0_2.create().show();
        return;
    }

    protected int getMenuID()
    {
        return 2131689486;
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    protected com.bisimplex.firebooru.network.SourceType getSourceType()
    {
        return com.bisimplex.firebooru.network.SourceType.Favorites;
    }

    public String getiOsFragmentName()
    {
        return "FavoriteViewController";
    }

    protected void invalidateMenu()
    {
        super.invalidateMenu();
        android.view.MenuItem v0_1 = this.getVisibleBar();
        if (v0_1 != null) {
            android.view.MenuItem v0_2 = v0_1.getMenu();
            if (v0_2 != null) {
                android.view.MenuItem v1_1 = v0_2.findItem(2131362451);
                android.view.MenuItem v0_0 = v0_2.findItem(2131362453);
                if (!com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().isRunning()) {
                    if (v1_1 != null) {
                        v1_1.setVisible(0);
                    }
                    if (v0_0 != null) {
                        v0_0.setVisible(1);
                    }
                } else {
                    if (v1_1 != null) {
                        v1_1.setVisible(1);
                    }
                    if (v0_0 != null) {
                        v0_0.setVisible(0);
                        return;
                    }
                }
            }
        }
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p1, android.view.ViewGroup p2, android.os.Bundle p3)
    {
        android.view.View v1_1 = super.onCreateView(p1, p2, p3);
        this.setTitle(2131886853);
        return v1_1;
    }

    protected void onMenuClick(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131361987) {
            if (p3.getItemId() != 2131362498) {
                if (p3.getItemId() != 2131362519) {
                    if (p3.getItemId() != 2131362421) {
                        if (p3.getItemId() != 2131362010) {
                            if (p3.getItemId() != 2131362453) {
                                if (p3.getItemId() != 2131362451) {
                                    if (p3.getItemId() != 2131362007) {
                                        if (p3.getItemId() != 2131362167) {
                                            if (p3.getItemId() == 2131362100) {
                                                this.askToExportCSV();
                                            }
                                            return;
                                        } else {
                                            this.sendPostToHydrus();
                                            return;
                                        }
                                    } else {
                                        this.askDisplayMode();
                                        return;
                                    }
                                } else {
                                    this.askCancelReload();
                                    return;
                                }
                            } else {
                                this.askReloadAllItems();
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
            this.deleteItems();
            return;
        }
    }

    public void onPause()
    {
        super.onPause();
        com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().setListener(0);
        return;
    }

    public void onResume()
    {
        super.onResume();
        com.bisimplex.firebooru.services.UpdateMetadataService.getInstance().setListener(this.updateMetadataServiceListener);
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        if (this.getSource() != null) {
            this.updateSourceName(((String) this.getSource().getQuery().getExtraParams().get("FILTER_SERVER_URL")));
            if (!android.text.TextUtils.isEmpty(this.getSource().getQuery().getTitle())) {
                this.setTitle(this.getSource().getQuery().getTitle());
            }
        }
        return;
    }

    protected void sendPostToHydrus(com.bisimplex.firebooru.danbooru.ServerItem p3)
    {
        com.bisimplex.firebooru.activity.MessageType v0_0 = this.getSource();
        if ((v0_0 != null) && (p3 != 0)) {
            com.bisimplex.firebooru.network.HydrusClient.getInstance().sendToHydrus(p3, v0_0.getQuery());
            this.showMessage(2131887194, com.bisimplex.firebooru.activity.MessageType.Success);
        }
        return;
    }

    protected void setPageLabelVisibility(boolean p2)
    {
        this.pageTextView.setVisibility(8);
        return;
    }

    public void setTitle(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            super.setTitle(p2);
            return;
        } else {
            super.setTitle(this.getString(2131886853));
            return;
        }
    }

    protected void showSearchField()
    {
        androidx.fragment.app.FragmentManager v0_0 = this.getSource();
        if (v0_0 != null) {
            com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog v1_1 = new com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog();
            v1_1.setListener(this.searchDialogListener);
            String v2_3 = new android.os.Bundle(3);
            androidx.fragment.app.FragmentManager v0_1 = v0_0.getQuery();
            if (v0_1 != null) {
                v2_3.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v0_1));
            }
            v1_1.setArguments(v2_3);
            v1_1.show(this.getParentFragmentManager(), "SearchDialog_TAG");
            return;
        } else {
            return;
        }
    }
}
