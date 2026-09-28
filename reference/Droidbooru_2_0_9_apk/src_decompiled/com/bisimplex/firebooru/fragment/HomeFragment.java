package com.bisimplex.firebooru.fragment;
public class HomeFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.dataadapter.HomeItemHolder$HomeItemListener, com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogReceiver, com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener {
    protected com.bisimplex.firebooru.dataadapter.HomeAdapter adapter;
    protected com.google.android.material.floatingactionbutton.FloatingActionButton floatingActionButton;
    protected androidx.recyclerview.widget.LinearLayoutManager manager;
    protected androidx.recyclerview.widget.RecyclerView recyclerView;
    public final com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener searchDialogListener;
    protected String selectedGroupID;

    public static synthetic boolean $r8$lambda$ZNWgUrFEbHkwO29P44j_bDHa-CE(com.bisimplex.firebooru.fragment.HomeFragment p0, android.view.MenuItem p1)
    {
        return p0.lambda$onViewCreated$0(p1);
    }

    public HomeFragment()
    {
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.HomeFragment$2(this);
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

    private void askAddToGroup(int p3, com.bisimplex.firebooru.data.HomeItem p4)
    {
        com.bisimplex.firebooru.view.GroupSpecsDialog v3_1 = new com.bisimplex.firebooru.view.GroupSpecsDialog();
        String v0_2 = new android.os.Bundle();
        v0_2.putString("SOURCE_SPECS_CHILD_ID", p4.getSpecs().getKey());
        if (!android.text.TextUtils.isEmpty(this.selectedGroupID)) {
            v0_2.putString("SOURCE_SPECS_PARENT_ID", this.selectedGroupID);
        }
        v3_1.setArguments(v0_2);
        v3_1.show(this.getParentFragmentManager(), "GroupSpecsDialog");
        return;
    }

    private void bindDataAdapter()
    {
        if (!android.text.TextUtils.isEmpty(this.selectedGroupID)) {
            this.selectSourceSpecs(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().findSourceSpecsByID(this.selectedGroupID), -1);
            return;
        } else {
            this.bindDataAdapter(this.adapter);
            return;
        }
    }

    private void confirmDeleteAtIndex(int p5, com.bisimplex.firebooru.data.HomeItem p6)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3;
        if (p6.getType() != 1) {
            v0_3 = 2131886269;
        } else {
            v0_3 = 2131886271;
        }
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886392).setMessage(v0_3).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.HomeFragment$8(this)).setPositiveButton(2131886392, new com.bisimplex.firebooru.fragment.HomeFragment$7(this, p5, p6)).show();
        return;
    }

    private void editSublist(int p3, com.bisimplex.firebooru.data.HomeItem p4)
    {
        if (p4.getType() == 1) {
            androidx.fragment.app.FragmentManager v3_6 = p4.getSpecs().getKey();
            com.bisimplex.firebooru.view.SourceSpecsEditDialog v4_2 = new com.bisimplex.firebooru.view.SourceSpecsEditDialog();
            String v0_1 = new android.os.Bundle();
            v0_1.putString("EDIT_ID", v3_6);
            if (!android.text.TextUtils.isEmpty(this.selectedGroupID)) {
                v0_1.putString("PARENT_GROUP_ID", this.selectedGroupID);
            }
            v4_2.setArguments(v0_1);
            v4_2.show(this.getParentFragmentManager(), "SourceSpecsEditDialog");
        }
        return;
    }

    private void expandSublist(int p2, com.bisimplex.firebooru.data.HomeItem p3)
    {
        p3.setExpanded(1);
        this.adapter.notifyItemChanged(p2);
        return;
    }

    private synthetic boolean lambda$onViewCreated$0(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131362157) {
            if (p3.getItemId() != 2131362498) {
                if (p3.getItemId() == 2131362519) {
                    this.showServersScreen();
                }
            } else {
                this.showSearchScreen();
            }
        } else {
            this.showSearchHistory();
        }
        return 1;
    }

    private void moveSublist(int p3, int p4)
    {
        if ((p3 != 0) && ((p4 != 0) && (p3 != p4))) {
            int vtmp1 = this.adapter.getItemCount();
            if ((p3 >= 0) && (p4 < vtmp1)) {
                this.adapter.move(p3, p4);
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().moveSourceSpecs((p3 - 1), (p4 - 1), this.selectedGroupID);
            }
        }
        return;
    }

    private void renameGroup(int p3, com.bisimplex.firebooru.data.HomeItem p4)
    {
        com.bisimplex.firebooru.view.RenameGroupSpecsDialog v3_1 = new com.bisimplex.firebooru.view.RenameGroupSpecsDialog();
        String v0_2 = new android.os.Bundle();
        v0_2.putString("SOURCE_SPECS_ID", p4.getSpecs().getKey());
        v3_1.setArguments(v0_2);
        v3_1.show(this.getParentFragmentManager(), "RenameGroupSpecsDialog");
        return;
    }

    private void selectSourceSpecs(com.bisimplex.firebooru.model.SourceSpecs p3, int p4)
    {
        if (p3 == null) {
            this.selectedGroupID = 0;
            this.bindDataAdapter(this.adapter);
            return;
        } else {
            java.util.ArrayList v3_2;
            this.selectedGroupID = p3.getKey();
            com.bisimplex.firebooru.dataadapter.HomeAdapter v4_0 = p3.getChilds();
            if (p3.getQuery() == null) {
                v3_2 = "";
            } else {
                v3_2 = p3.getQuery().getTitle();
            }
            com.bisimplex.firebooru.model.SourceSpecs v0_2 = new com.bisimplex.firebooru.data.HomeItem(v3_2);
            java.util.ArrayList v3_5 = new java.util.ArrayList();
            v3_5.add(v0_2);
            if (v4_0 != null) {
                com.bisimplex.firebooru.dataadapter.HomeAdapter v4_1 = v4_0.iterator();
                while (v4_1.hasNext()) {
                    v3_5.add(new com.bisimplex.firebooru.data.HomeItem(((com.bisimplex.firebooru.model.SourceSpecs) v4_1.next())));
                }
            }
            this.adapter.setItems(v3_5);
            return;
        }
    }

    private void showGroup(int p1, com.bisimplex.firebooru.data.HomeItem p2)
    {
        this.selectSourceSpecs(p2.getSpecs(), p1);
        return;
    }

    private void sortGroupContent(boolean p3)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().sortSourceSpecs(this.selectedGroupID, p3)) {
            this.bindDataAdapter();
        }
        return;
    }

    private void swapSublist(int p3, int p4)
    {
        if ((p3 != 0) && ((p4 != 0) && (p3 != p4))) {
            int vtmp1 = this.adapter.getItemCount();
            if ((p3 >= 0) && (p4 < vtmp1)) {
                this.adapter.swap(p3, p4);
                com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().swapSourceSpecs((p3 - 1), (p4 - 1), this.selectedGroupID);
            }
        }
        return;
    }

    public void actionClick(int p7, com.bisimplex.firebooru.data.ItemActionType p8)
    {
        com.bisimplex.firebooru.fragment.HomeFragment$6 v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            com.bisimplex.firebooru.fragment.HistoryFragment v1_7 = this.adapter.getItem(p7);
            switch (com.bisimplex.firebooru.fragment.HomeFragment$9.$SwitchMap$com$bisimplex$firebooru$data$ItemActionType[p8.ordinal()]) {
                case 2:
                    if (!v1_7.isExpanded()) {
                        String v7_23 = v1_7.getSpecs();
                        if (v7_23 != null) {
                            if (3 != v7_23.getType()) {
                                if (1 != v7_23.getType()) {
                                    if (2 == v7_23.getType()) {
                                        com.bisimplex.firebooru.fragment.HistoryFragment v1_11 = new com.bisimplex.firebooru.fragment.HistoryFragment();
                                        String v7_25 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.History, v7_23.getProvider(), v7_23.getQuery());
                                        if (v7_25 != null) {
                                            android.os.Bundle v3_10 = new android.os.Bundle(1);
                                            v3_10.putString("SOURCE_KEY", v7_25.getKey());
                                            v1_11.setArguments(v3_10);
                                            v0_1.switchContent(v1_11);
                                        }
                                    }
                                } else {
                                    com.bisimplex.firebooru.fragment.HistoryFragment v1_1 = new com.bisimplex.firebooru.fragment.FavoriteListFragment();
                                    String v7_2 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Favorites, v7_23.getProvider(), v7_23.getQuery());
                                    if (v7_2 != null) {
                                        android.os.Bundle v3_2 = new android.os.Bundle(1);
                                        v3_2.putString("SOURCE_KEY", v7_2.getKey());
                                        v1_1.setArguments(v3_2);
                                        v0_1.switchContent(v1_1);
                                        return;
                                    }
                                }
                            } else {
                                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(v1_7.getSpecs().getProvider().getServerDescription().getServerId());
                                v0_1.searchQuery(v1_7.getSpecs().getQuery(), v1_7.getSpecs().getProvider());
                                return;
                            }
                        }
                    } else {
                        if (v1_7.getSource().getType() != com.bisimplex.firebooru.network.SourceType.Favorites) {
                            if (v1_7.getSource().getType() == com.bisimplex.firebooru.network.SourceType.Post) {
                                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(v1_7.getSource().getProvider().getServerDescription().getServerId());
                                v0_1.searchQuery(v1_7.getSource().getQuery(), v1_7.getSource().getProvider());
                                return;
                            }
                        } else {
                            String v7_15 = new com.bisimplex.firebooru.fragment.FavoriteListFragment();
                            android.os.Bundle v3_5 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Favorites);
                            v3_5.setQuery(new com.bisimplex.firebooru.network.SourceQuery(v1_7.getSpecs().getQuery()));
                            com.bisimplex.firebooru.fragment.HistoryFragment v1_5 = new android.os.Bundle(1);
                            v1_5.putString("SOURCE_KEY", v3_5.getKey());
                            v7_15.setArguments(v1_5);
                            v0_1.switchContent(v7_15);
                            return;
                        }
                    }
                case 3:
                    this.browseAll();
                    return;
                case 4:
                    this.confirmDeleteAtIndex(p7, v1_7);
                    return;
                case 5:
                    this.recyclerView.post(new com.bisimplex.firebooru.fragment.HomeFragment$6(this, v1_7, p7));
                    return;
                case 6:
                    if ((!v1_7.getSource().isLastPage()) && (!v1_7.getSource().getIsLoading())) {
                        v1_7.getSource().loadAnotherPage();
                        return;
                    }
                case 7:
                    this.swapSublist(p7, (p7 - 1));
                    return;
                case 8:
                    this.swapSublist(p7, (p7 + 1));
                    return;
                case 9:
                    this.moveSublist(p7, 1);
                    return;
                case 10:
                    this.moveSublist(p7, (this.adapter.getItemCount() - 1));
                    return;
                case 11:
                    this.askAddToGroup(p7, v1_7);
                    return;
                case 12:
                    this.showGroup(p7, v1_7);
                    return;
                case 13:
                    this.renameGroup(p7, v1_7);
                    return;
                case 14:
                    this.sortGroupContent(0);
                    return;
                case 15:
                    this.sortGroupContent(1);
                    return;
                case 16:
                    this.editSublist(p7, v1_7);
                    return;
                case 17:
                    this.expandSublist(p7, v1_7);
                    return;
                default:
            }
        }
        return;
    }

    public void addGroup(com.bisimplex.firebooru.model.SourceSpecs p2, String p3, boolean p4)
    {
        com.bisimplex.firebooru.dataadapter.HomeAdapter v0 = this.adapter;
        if (v0 != null) {
            if (p4 == null) {
                v0.addItem(new com.bisimplex.firebooru.data.HomeItem(p2));
            } else {
                v0.updateItem(p2);
            }
            this.adapter.removeItem(p3);
            return;
        } else {
            return;
        }
    }

    public void addSpecs(com.bisimplex.firebooru.model.SourceSpecs p2, boolean p3)
    {
        com.bisimplex.firebooru.dataadapter.HomeAdapter v0 = this.adapter;
        if ((v0 != null) && (p2 != null)) {
            if (p3 == null) {
                v0.addItem(new com.bisimplex.firebooru.data.HomeItem(p2));
            } else {
                v0.updateItem(p2);
                return;
            }
        }
        return;
    }

    protected void bindDataAdapter(com.bisimplex.firebooru.dataadapter.HomeAdapter p5)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        java.util.Iterator v1_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSourceSpecs();
        com.bisimplex.firebooru.model.SourceSpecs v2_4 = new com.bisimplex.firebooru.data.HomeItem();
        v2_4.setType(0);
        v0_1.add(v2_4);
        java.util.Iterator v1_0 = v1_2.iterator();
        while (v1_0.hasNext()) {
            v0_1.add(new com.bisimplex.firebooru.data.HomeItem(((com.bisimplex.firebooru.model.SourceSpecs) v1_0.next())));
        }
        p5.setItems(v0_1);
        return;
    }

    public void browseAll()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.searchQuery(new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131886144)), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance());
        }
        return;
    }

    protected void configureInsets(androidx.core.graphics.Insets p3, androidx.core.graphics.Insets p4)
    {
        super.configureInsets(p3, p4);
        android.view.ViewGroup$MarginLayoutParams v3_1 = this.floatingActionButton;
        if (v3_1 != null) {
            android.view.ViewGroup$MarginLayoutParams v3_3 = ((android.view.ViewGroup$MarginLayoutParams) v3_1.getLayoutParams());
            v3_3.bottomMargin = (p4.bottom + this.getResources().getDimensionPixelSize(2131165370));
            this.floatingActionButton.setLayoutParams(v3_3);
        }
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    protected int getLayoutID()
    {
        return 2131558431;
    }

    public com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener getSearchDialogListener()
    {
        return this.searchDialogListener;
    }

    protected android.view.View getSnackBarAnchorView()
    {
        return this.floatingActionButton;
    }

    public String getiOsFragmentName()
    {
        return "ViewController";
    }

    public boolean isComputingLayout()
    {
        int v0_0 = this.recyclerView;
        if (v0_0 == 0) {
            return 0;
        } else {
            return v0_0.isComputingLayout();
        }
    }

    public boolean onBackPressed()
    {
        if (!android.text.TextUtils.isEmpty(this.selectedGroupID)) {
            this.selectSourceSpecs(0, -1);
            return 1;
        } else {
            return super.onBackPressed();
        }
    }

    public void onCreateOptionsMenu(android.view.Menu p3, android.view.MenuInflater p4)
    {
        p4.inflate(2131689489, p3);
        p3.findItem(2131362157).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.HomeFragment$3(this)).setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_history, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        p3.findItem(2131362498).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.HomeFragment$4(this)).setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_search, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        p3.findItem(2131362519).setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.HomeFragment$5(this)).setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_server, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p3, android.view.ViewGroup p4, android.os.Bundle p5)
    {
        android.view.View v3_1 = p3.inflate(this.getLayoutID(), p4, 0);
        this.setTitle(2131886156);
        if (p5 != null) {
            this.selectedGroupID = p5.getString("selectedGroupID", 0);
        }
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) v3_1.findViewById(2131362448));
        com.google.android.material.floatingactionbutton.FloatingActionButton v4_6 = new androidx.recyclerview.widget.LinearLayoutManager(this.getContext(), 1, 0);
        this.manager = v4_6;
        this.recyclerView.setLayoutManager(v4_6);
        com.google.android.material.floatingactionbutton.FloatingActionButton v4_8 = new com.bisimplex.firebooru.dataadapter.HomeAdapter(this.getContext(), this);
        this.adapter = v4_8;
        this.recyclerView.setAdapter(v4_8);
        com.google.android.material.floatingactionbutton.FloatingActionButton v4_11 = ((com.google.android.material.floatingactionbutton.FloatingActionButton) v3_1.findViewById(2131361869));
        this.floatingActionButton = v4_11;
        if (v4_11 != null) {
            v4_11.setImageDrawable(com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.requireContext(), com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_edit, 2131100494));
            this.floatingActionButton.setOnClickListener(new com.bisimplex.firebooru.fragment.HomeFragment$1(this));
            this.fixFloatButtonLayout(this.floatingActionButton);
        }
        this.bindDataAdapter();
        return v3_1;
    }

    public void onDialogNegativeClick(androidx.fragment.app.DialogFragment p1)
    {
        return;
    }

    public void onDialogNewFolderClick(androidx.fragment.app.DialogFragment p1, String p2, com.bisimplex.firebooru.network.SourceType p3)
    {
        return;
    }

    public void onDialogSpecsDuplicated(androidx.fragment.app.DialogFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2, com.bisimplex.firebooru.model.SourceSpecs p3, com.bisimplex.firebooru.model.SourceSpecs p4)
    {
        return;
    }

    public void onDialogSpecsSaved(androidx.fragment.app.DialogFragment p1, com.bisimplex.firebooru.model.SourceSpecs p2, boolean p3)
    {
        this.addSpecs(p2, p3);
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        if (!android.text.TextUtils.isEmpty(this.selectedGroupID)) {
            p3.putString("selectedGroupID", this.selectedGroupID);
        }
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.getVisibleBar().setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.HomeFragment$$ExternalSyntheticLambda0(this));
        return;
    }

    protected void showSearch(com.bisimplex.firebooru.network.SourceQuery p4, com.bisimplex.firebooru.danbooru.ServerItem p5)
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            int v5_1;
            if (p5 == 0) {
                v5_1 = 0;
            } else {
                com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().setSelectedServer(p5.getServerId());
                v5_1 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p5);
            }
            v0_1.searchQuery(p4, v5_1);
        }
        return;
    }

    public void showSearchScreen()
    {
        com.bisimplex.firebooru.view.DynamicSearchDialog v0_1 = new com.bisimplex.firebooru.view.DynamicSearchDialog();
        v0_1.setListener(this.searchDialogListener);
        v0_1.setArguments(new android.os.Bundle(1));
        v0_1.show(this.getParentFragmentManager(), "DynamicSearchDialog_TAG");
        return;
    }

    public void showServersScreen()
    {
        com.bisimplex.firebooru.activity.MainActivity v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this.getActivity());
        if (v0_1 != null) {
            v0_1.switchContent(new com.bisimplex.firebooru.fragment.ServersFragment());
        }
        return;
    }

    public void sublistClick(int p4, int p5)
    {
        if (((com.bisimplex.firebooru.activity.MainActivity) this.getActivity()) != null) {
            com.bisimplex.firebooru.fragment.DetailFragment v0_3 = new com.bisimplex.firebooru.fragment.DetailFragment();
            android.os.Bundle v1_1 = new android.os.Bundle(2);
            int v4_1 = this.adapter.getItem(p4);
            v4_1.getSource().setVisiblePostIndex(p5);
            v1_1.putString("SOURCE_KEY", v4_1.getSpecs().getKey());
            v1_1.putInt("SOURCE_TYPE_KEY", com.bisimplex.firebooru.network.SourceType.Permanent.getValue());
            v0_3.setArguments(v1_1);
            this.switchFragment(v0_3);
            return;
        } else {
            return;
        }
    }

    public boolean sublistLongClick(int p2, int p3)
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isLongTapToDownload()) {
            return this.addToDownload(((com.bisimplex.firebooru.danbooru.DanbooruPost) this.adapter.getItem(p2).getSource().getItemAt(p3)));
        } else {
            return 0;
        }
    }

    public void updateGroupName(com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        this.adapter.updateItem(p2);
        return;
    }
}
