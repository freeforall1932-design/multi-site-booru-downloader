package com.bisimplex.firebooru.fragment;
public class SortPinFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.view.SourceSpecsDialog$SourceSpecsDialogDialogListener {
    public static final String GROUP_ID = "GROUP_ID";
    private com.bisimplex.firebooru.dataadapter.SortPinAdapter adapter;
    private final com.bisimplex.firebooru.fragment.BaseFragment$AddGroupListener addGroupListener;
    private com.bisimplex.firebooru.model.SourceSpecs currentFolder;
    private boolean edited;
    private com.bisimplex.firebooru.model.EditedSourceKeys editedSourceKeys;
    private int editingPinIndex;
    private com.bisimplex.firebooru.model.SourceSpecs homeFolder;
    private androidx.recyclerview.widget.ItemTouchHelper itemTouchHelper;
    private androidx.recyclerview.widget.LinearLayoutManager manager;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    public final com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener searchDialogListener;
    public final com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog$OnDynamicFavoriteSearchDialogListener searchFavDialogListener;
    private final androidx.recyclerview.widget.ItemTouchHelper$SimpleCallback simpleCallback;
    private final com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener sortPinItemListener;

    public static synthetic boolean $r8$lambda$C-A26lioQC-UYBVLCqVImh8Iqfg(com.bisimplex.firebooru.fragment.SortPinFragment p0, android.view.MenuItem p1)
    {
        return p0.lambda$onViewCreated$2(p1);
    }

    public static synthetic void $r8$lambda$Dv7rgvVYnEZfxCIKvCN-V5wpMG4(com.bisimplex.firebooru.fragment.SortPinFragment p0, com.bisimplex.firebooru.model.SourceSpecs p1, android.widget.EditText p2, int p3, android.content.DialogInterface p4, int p5)
    {
        p0.lambda$askEditItem$0(p1, p2, p3, p4, p5);
        return;
    }

    public static synthetic void $r8$lambda$U45Qv3IZvPSZ9MS7UJpNjKZoRI8(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1, android.content.DialogInterface p2, int p3)
    {
        p0.lambda$askDeleteItem$1(p1, p2, p3);
        return;
    }

    static bridge synthetic com.bisimplex.firebooru.dataadapter.SortPinAdapter -$$Nest$fgetadapter(com.bisimplex.firebooru.fragment.SortPinFragment p0)
    {
        return p0.adapter;
    }

    static bridge synthetic androidx.recyclerview.widget.ItemTouchHelper -$$Nest$fgetitemTouchHelper(com.bisimplex.firebooru.fragment.SortPinFragment p0)
    {
        return p0.itemTouchHelper;
    }

    static bridge synthetic void -$$Nest$maskDeleteItem(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1)
    {
        p0.askDeleteItem(p1);
        return;
    }

    static bridge synthetic void -$$Nest$maskEditItem(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1)
    {
        p0.askEditItem(p1);
        return;
    }

    static bridge synthetic void -$$Nest$maskMoveToFolder(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1)
    {
        p0.askMoveToFolder(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mmoveItemToFolder(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1, java.util.List p2, int p3)
    {
        p0.moveItemToFolder(p1, p2, p3);
        return;
    }

    static bridge synthetic void -$$Nest$mselectFolder(com.bisimplex.firebooru.fragment.SortPinFragment p0, int p1)
    {
        p0.selectFolder(p1);
        return;
    }

    public SortPinFragment()
    {
        this.editingPinIndex = -1;
        this.edited = 0;
        this.sortPinItemListener = new com.bisimplex.firebooru.fragment.SortPinFragment$1(this);
        this.searchFavDialogListener = new com.bisimplex.firebooru.fragment.SortPinFragment$3(this);
        this.searchDialogListener = new com.bisimplex.firebooru.fragment.SortPinFragment$4(this);
        this.simpleCallback = new com.bisimplex.firebooru.fragment.SortPinFragment$5(this, 3, 0);
        this.addGroupListener = new com.bisimplex.firebooru.fragment.SortPinFragment$6(this);
        return;
    }

    private void askDeleteItem(int p5)
    {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext()).setTitle(2131886392).setMessage(2131886270).setNegativeButton(2131886205, 0).setPositiveButton(2131886392, new com.bisimplex.firebooru.fragment.SortPinFragment$$ExternalSyntheticLambda2(this, p5)).show();
        return;
    }

    private void askEditItem(int p9)
    {
        androidx.fragment.app.FragmentManager v0_6 = ((com.bisimplex.firebooru.model.SourceSpecs) this.adapter.getData().get(p9));
        if (v0_6.getType() != 3) {
            if (v0_6.getType() != 1) {
                com.google.android.material.dialog.MaterialAlertDialogBuilder v1_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
                android.view.View v2_3 = this.requireActivity().getLayoutInflater().inflate(2131558476, 0);
                com.google.gson.Gson v3_10 = ((android.widget.EditText) v2_3.findViewById(2131362442));
                int v5_3 = ((com.google.android.material.textfield.TextInputLayout) v2_3.findViewById(2131362443));
                if (v0_6.getType() != 4) {
                    v5_3.setHint(this.getString(2131887111));
                    v3_10.setText(v0_6.getQuery().getText());
                } else {
                    v5_3.setHint(this.getString(2131886598));
                    v3_10.setText(v0_6.getQuery().getText());
                }
                v1_3.setTitle(2131886468).setNegativeButton(2131886205, 0).setPositiveButton(2131886468, new com.bisimplex.firebooru.fragment.SortPinFragment$$ExternalSyntheticLambda1(this, v0_6, v3_10, p9)).setView(v2_3).show();
                return;
            } else {
                this.editingPinIndex = p9;
                com.google.android.material.dialog.MaterialAlertDialogBuilder v9_4 = new com.bisimplex.firebooru.view.DynamicFavoriteSearchDialog();
                v9_4.setListener(this.searchFavDialogListener);
                com.google.android.material.dialog.MaterialAlertDialogBuilder v1_9 = new android.os.Bundle(1);
                androidx.fragment.app.FragmentManager v0_7 = v0_6.getQuery();
                if (v0_7 != null) {
                    v1_9.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v0_7));
                }
                v9_4.setArguments(v1_9);
                v9_4.show(this.getParentFragmentManager(), "DynamicFavoriteSearchDialog_TAG");
                return;
            }
        } else {
            this.editingPinIndex = p9;
            com.google.android.material.dialog.MaterialAlertDialogBuilder v9_6 = new com.bisimplex.firebooru.view.DynamicSearchDialog();
            v9_6.setListener(this.searchDialogListener);
            com.google.android.material.dialog.MaterialAlertDialogBuilder v1_13 = new android.os.Bundle(3);
            v1_13.putBoolean("ALLOW_SERVER_LIST_MODIFICATION", 0);
            com.bisimplex.firebooru.fragment.SortPinFragment$$ExternalSyntheticLambda1 v4_2 = new java.util.ArrayList(1);
            if (v0_6.getServer() != null) {
                v4_2.add(Integer.valueOf(v0_6.getServer().getServerId()));
            }
            v1_13.putIntegerArrayList("MULTI_SERVER_SELECTED_IDS", v4_2);
            androidx.fragment.app.FragmentManager v0_1 = v0_6.getQuery();
            if (v0_1 != null) {
                v1_13.putString("QUERY_JSON", new com.google.gson.Gson().toJson(v0_1));
            }
            v9_6.setArguments(v1_13);
            v9_6.show(this.getParentFragmentManager(), "DynamicSearchDialog_TAG");
            return;
        }
    }

    private void askMoveToFolder(int p6)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_3 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        java.util.List v1_1 = this.getFolders();
        String[] v2_3 = new java.util.ArrayList();
        com.bisimplex.firebooru.fragment.SortPinFragment$2 v3_6 = v1_1.iterator();
        while (v3_6.hasNext()) {
            v2_3.add(((com.bisimplex.firebooru.model.SourceSpecs) v3_6.next()).getTitle());
        }
        com.bisimplex.firebooru.fragment.SortPinFragment$2 v3_1 = new String[0];
        v0_3.setTitle(2131886899).setNegativeButton(2131886205, 0).setItems(((String[]) v2_3.toArray(v3_1)), new com.bisimplex.firebooru.fragment.SortPinFragment$2(this, p6, v1_1)).show();
        return;
    }

    private void bindDataAdapter()
    {
        if (this.homeFolder == null) {
            com.bisimplex.firebooru.model.SourceSpecs v0_9 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().loadSourceSpecs();
            java.util.List v1_9 = new com.bisimplex.firebooru.model.SourceSpecs();
            this.homeFolder = v1_9;
            v1_9.setChilds(v0_9);
            this.homeFolder.setQuery(new com.bisimplex.firebooru.network.SourceQuery("", this.getString(2131886662)));
        }
        if (this.currentFolder == null) {
            this.currentFolder = this.homeFolder;
            com.bisimplex.firebooru.model.SourceSpecs v0_4 = this.getArguments();
            if (v0_4 != null) {
                com.bisimplex.firebooru.model.SourceSpecs v0_5 = v0_4.getString("GROUP_ID");
                if (!android.text.TextUtils.isEmpty(v0_5)) {
                    java.util.List v1_5 = this.homeFolder.getChilds();
                    int v2_2 = 0;
                    while (v2_2 < v1_5.size()) {
                        if (!((com.bisimplex.firebooru.model.SourceSpecs) v1_5.get(v2_2)).getKey().equalsIgnoreCase(v0_5)) {
                            v2_2++;
                        } else {
                            this.currentFolder = ((com.bisimplex.firebooru.model.SourceSpecs) v1_5.get(v2_2));
                            break;
                        }
                    }
                }
            }
        }
        this.adapter.setData(this.currentFolder.getChilds());
        this.updateTitle();
        this.updateMenu();
        return;
    }

    private void deleteItem(int p2)
    {
        this.adapter.deleteItem(p2);
        this.setEdited(1);
        return;
    }

    private void editItem(com.bisimplex.firebooru.model.SourceSpecs p3, String p4, int p5)
    {
        if (p3.getType() != 4) {
            this.editedSourceKeys.getKeys().add(p3.getKey());
        } else {
            if (android.text.TextUtils.isEmpty(p4)) {
                return;
            }
        }
        p3.getQuery().setText(p4);
        p3.getQuery().setTitle(p4);
        this.adapter.notifyItemChanged(p5);
        this.setEdited(1);
        return;
    }

    private java.util.List getFolders()
    {
        if (this.homeFolder != null) {
            java.util.ArrayList v0_4 = new java.util.ArrayList();
            com.bisimplex.firebooru.model.SourceSpecs v2_3 = this.homeFolder.getChilds().iterator();
            while (v2_3.hasNext()) {
                com.bisimplex.firebooru.model.SourceSpecs v3_3 = ((com.bisimplex.firebooru.model.SourceSpecs) v2_3.next());
                if (v3_3.getType() == 4) {
                    v0_4.add(v3_3);
                }
            }
            com.bisimplex.firebooru.model.SourceSpecs v3_1 = this.homeFolder;
            if ((this.currentFolder != v3_1) && (v3_1 != null)) {
                v0_4.add(0, v3_1);
            }
            return v0_4;
        } else {
            return new java.util.ArrayList(0);
        }
    }

    private synthetic void lambda$askDeleteItem$1(int p1, android.content.DialogInterface p2, int p3)
    {
        this.deleteItem(p1);
        return;
    }

    private synthetic void lambda$askEditItem$0(com.bisimplex.firebooru.model.SourceSpecs p1, android.widget.EditText p2, int p3, android.content.DialogInterface p4, int p5)
    {
        this.editItem(p1, p2.getText().toString(), p3);
        return;
    }

    private synthetic boolean lambda$onViewCreated$2(android.view.MenuItem p4)
    {
        if (p4.getItemId() != 2131362483) {
            if (p4.getItemId() != 2131361870) {
                if (p4.getItemId() != 2131362547) {
                    if (p4.getItemId() == 2131362546) {
                        this.sortPinsAlphabetically(1);
                    }
                } else {
                    this.sortPinsAlphabetically(0);
                }
            } else {
                this.askAddGroup(this.addGroupListener);
            }
        } else {
            this.save();
        }
        return 1;
    }

    private void moveItemToFolder(int p2, java.util.List p3, int p4)
    {
        com.bisimplex.firebooru.model.SourceSpecs v3_2 = ((com.bisimplex.firebooru.model.SourceSpecs) p3.get(p4));
        v3_2.getChilds().add(((com.bisimplex.firebooru.model.SourceSpecs) this.adapter.getData().get(p2)));
        this.adapter.removeAndReload(p2, v3_2);
        this.setEdited(1);
        return;
    }

    private void selectFolder(int p2)
    {
        this.currentFolder = ((com.bisimplex.firebooru.model.SourceSpecs) this.adapter.getData().get(p2));
        this.bindDataAdapter();
        return;
    }

    private void sortPinsAlphabetically(boolean p2)
    {
        this.adapter.sortAlphabetically(p2);
        this.setEdited(1);
        return;
    }

    private void updateMenu()
    {
        android.view.MenuItem v0_0 = this.getVisibleBar();
        if (v0_0 != null) {
            android.view.MenuItem v0_1 = v0_0.getMenu();
            if (v0_1 != null) {
                android.view.MenuItem v0_2 = v0_1.findItem(2131361870);
                if (v0_2 != null) {
                    int v1_3;
                    int v1_0 = this.currentFolder;
                    if ((v1_0 != 0) && (!android.text.TextUtils.isEmpty(v1_0.getKey()))) {
                        v1_3 = 0;
                    } else {
                        v1_3 = 1;
                    }
                    v0_2.setVisible(v1_3);
                    return;
                }
            }
        }
        return;
    }

    private void updateTitle()
    {
        String v0_1 = this.currentFolder.getTitle();
        if (android.text.TextUtils.isEmpty(this.currentFolder.getKey())) {
            v0_1 = this.getString(2131886662);
        }
        this.setTitle(v0_1);
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public boolean onBackPressed()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v1_0 = this.homeFolder;
        if (this.currentFolder == v1_0) {
            if (!this.edited) {
                return super.onBackPressed();
            } else {
                boolean v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
                v0_1.setTitle(2131887229).setMessage(2131886273).setPositiveButton(2131886736, new com.bisimplex.firebooru.fragment.SortPinFragment$7(this)).setNegativeButton(2131886205, 0);
                v0_1.show();
                return 1;
            }
        } else {
            boolean v0_3 = v1_0.getChilds().indexOf(this.currentFolder);
            this.currentFolder = this.homeFolder;
            this.bindDataAdapter();
            if (v0_3) {
                this.recyclerView.smoothScrollToPosition(v0_3);
            }
            return 1;
        }
    }

    public android.view.View onCreateView(android.view.LayoutInflater p6, android.view.ViewGroup p7, android.os.Bundle p8)
    {
        android.view.View v6_1 = p6.inflate(2131558444, p7, 0);
        this.setHasOptionsMenu(1);
        if (p8) {
            if (this.homeFolder == null) {
                com.bisimplex.firebooru.model.EditedSourceKeys v0_2 = p8.getString("homeFolder", 0);
                if (!android.text.TextUtils.isEmpty(v0_2)) {
                    this.homeFolder = ((com.bisimplex.firebooru.model.SourceSpecs) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_2, com.bisimplex.firebooru.model.SourceSpecs));
                }
            }
            if (this.currentFolder == null) {
                com.bisimplex.firebooru.model.EditedSourceKeys v0_7 = p8.getString("currentFolder", 0);
                if (android.text.TextUtils.isEmpty(v0_7)) {
                    this.currentFolder = this.homeFolder;
                } else {
                    this.currentFolder = ((com.bisimplex.firebooru.model.SourceSpecs) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_7, com.bisimplex.firebooru.model.SourceSpecs));
                }
            }
            if (this.editedSourceKeys == null) {
                com.bisimplex.firebooru.model.EditedSourceKeys v0_13 = p8.getString("editedSourceKeys", 0);
                if (!android.text.TextUtils.isEmpty(v0_13)) {
                    this.editedSourceKeys = ((com.bisimplex.firebooru.model.EditedSourceKeys) com.bisimplex.firebooru.network.HttpClient.getGson().fromJson(v0_13, com.bisimplex.firebooru.model.EditedSourceKeys));
                }
            }
            this.editingPinIndex = p8.getInt("editingPinIndex", -1);
            this.edited = p8.getBoolean("edited", 0);
        }
        if (this.editedSourceKeys == null) {
            this.editedSourceKeys = new com.bisimplex.firebooru.model.EditedSourceKeys();
        }
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) v6_1.findViewById(2131362449));
        boolean v8_11 = new androidx.recyclerview.widget.LinearLayoutManager(this.getContext(), 1, 0);
        this.manager = v8_11;
        this.recyclerView.setLayoutManager(v8_11);
        androidx.recyclerview.widget.ItemTouchHelper v7_6 = new com.bisimplex.firebooru.dataadapter.SortPinAdapter(this.requireContext(), this.sortPinItemListener);
        this.adapter = v7_6;
        this.recyclerView.setAdapter(v7_6);
        this.recyclerView.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this.recyclerView.getContext(), this.manager.getOrientation()));
        this.bindDataAdapter();
        androidx.recyclerview.widget.ItemTouchHelper v7_2 = new androidx.recyclerview.widget.ItemTouchHelper(this.simpleCallback);
        this.itemTouchHelper = v7_2;
        v7_2.attachToRecyclerView(this.recyclerView);
        return v6_1;
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
        return;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        if (this.homeFolder != null) {
            p3.putString("homeFolder", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.homeFolder));
        }
        String v0_1 = this.currentFolder;
        if ((v0_1 != null) && (v0_1 != this.homeFolder)) {
            p3.putString("currentFolder", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.currentFolder));
        }
        if (this.editedSourceKeys != null) {
            p3.putString("editedSourceKeys", com.bisimplex.firebooru.network.HttpClient.getGson().toJson(this.editedSourceKeys));
        }
        p3.putInt("editingPinIndex", this.editingPinIndex);
        p3.putBoolean("edited", this.edited);
        return;
    }

    public void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        int v0_5;
        super.onViewCreated(p3, p4);
        this.getVisibleBar().setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.SortPinFragment$$ExternalSyntheticLambda0(this));
        android.view.MenuItem v3_4 = this.getVisibleBar().getMenu();
        com.mikepenz.iconics.IconicsDrawable v4_2 = v3_4.findItem(2131361870);
        v4_2.setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_folder_plus, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        int v0_2 = this.currentFolder;
        if ((v0_2 != 0) && (!android.text.TextUtils.isEmpty(v0_2.getKey()))) {
            v0_5 = 0;
        } else {
            v0_5 = 1;
        }
        v4_2.setVisible(v0_5);
        v3_4.findItem(2131362483).setIcon(this.iconWithColor(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_save, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes()));
        this.updateTitle();
        return;
    }

    public void save()
    {
        if ((this.homeFolder != null) && (this.adapter != null)) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setSourceSpecs(this.homeFolder.getChilds());
            java.util.Iterator v0_1 = this.editedSourceKeys;
            if ((v0_1 != null) && (!v0_1.getKeys().isEmpty())) {
                java.util.Iterator v0_7 = this.editedSourceKeys.getKeys().iterator();
                while (v0_7.hasNext()) {
                    com.bisimplex.firebooru.network.SourceFactory v1_2 = ((String) v0_7.next());
                    com.bisimplex.firebooru.network.Source v2_1 = com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(com.bisimplex.firebooru.network.SourceType.Permanent, v1_2);
                    if (v2_1 != null) {
                        com.bisimplex.firebooru.model.SourceSpecs v3_2 = new com.bisimplex.firebooru.model.SourceSpecs();
                        v3_2.setKey(v1_2);
                        com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(v2_1, v3_2);
                    }
                }
            }
            this.setEdited(0);
            this.goBackInStack();
        }
        return;
    }

    public void setEdited(boolean p1)
    {
        this.edited = p1;
        return;
    }

    public void setTitle(String p3)
    {
        androidx.appcompat.widget.Toolbar v0 = this.getVisibleBar();
        if (v0 != null) {
            v0.setTitle(this.getString(2131886471, new Object[] {p3})));
            return;
        } else {
            return;
        }
    }

    protected void updateEditingSource(com.bisimplex.firebooru.network.SourceQuery p3, com.bisimplex.firebooru.danbooru.ServerItem p4)
    {
        if (this.editingPinIndex >= 0) {
            if (p4 == 0) {
                p4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer();
            }
            com.bisimplex.firebooru.model.SourceSpecs v0_2 = ((com.bisimplex.firebooru.model.SourceSpecs) this.adapter.getData().get(this.editingPinIndex));
            p3.setTitle(p3.getText());
            v0_2.setQuery(p3);
            v0_2.setUrl(p4.getUrl());
            v0_2.setServer(p4);
            this.editedSourceKeys.getKeys().add(v0_2.getKey());
            this.adapter.notifyItemChanged(this.editingPinIndex);
            this.editingPinIndex = -1;
            this.setEdited(1);
            return;
        } else {
            return;
        }
    }
}
