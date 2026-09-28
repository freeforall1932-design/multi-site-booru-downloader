package com.bisimplex.firebooru.fragment;
public class DownloadsFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.dataadapter.DownloadsAdapter$DownloadsAdapterListener {
    protected com.bisimplex.firebooru.dataadapter.DownloadsAdapter adapter;
    private io.objectbox.Box downloadEntryBox;
    private final com.bisimplex.firebooru.services.DownloadService$DownloadServiceListener downloadServiceListener;
    private androidx.recyclerview.widget.LinearLayoutManager layoutManager;
    private io.objectbox.query.Query listQuery;
    private androidx.recyclerview.widget.RecyclerView recyclerView;

    public static synthetic boolean $r8$lambda$hFu4tfjN2wm9HxFyIW6dh8TYvlM(com.bisimplex.firebooru.fragment.DownloadsFragment p0, android.view.MenuItem p1)
    {
        return p0.lambda$onViewCreated$0(p1);
    }

    static bridge synthetic io.objectbox.Box -$$Nest$fgetdownloadEntryBox(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        return p0.downloadEntryBox;
    }

    static bridge synthetic void -$$Nest$mclearAll(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        p0.clearAll();
        return;
    }

    static bridge synthetic void -$$Nest$mclearFailed(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        p0.clearFailed();
        return;
    }

    static bridge synthetic void -$$Nest$mexecuteAction(com.bisimplex.firebooru.fragment.DownloadsFragment p0, com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction p1, int p2)
    {
        p0.executeAction(p1, p2);
        return;
    }

    static bridge synthetic void -$$Nest$mreloadAllData(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        p0.reloadAllData();
        return;
    }

    static bridge synthetic void -$$Nest$mretryFailed(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        p0.retryFailed();
        return;
    }

    static bridge synthetic void -$$Nest$mupdateMenuButtons(com.bisimplex.firebooru.fragment.DownloadsFragment p0)
    {
        p0.updateMenuButtons();
        return;
    }

    public DownloadsFragment()
    {
        this.downloadServiceListener = new com.bisimplex.firebooru.fragment.DownloadsFragment$9(this);
        return;
    }

    private void askClearAll()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        v0_1.setTitle(2131886231).setMessage(2131886232).setPositiveButton(2131886230, new com.bisimplex.firebooru.fragment.DownloadsFragment$8(this)).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DownloadsFragment$7(this));
        v0_1.show();
        return;
    }

    private void askClearFailed()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        v0_1.setTitle(2131886235).setMessage(2131886236).setPositiveButton(2131886230, new com.bisimplex.firebooru.fragment.DownloadsFragment$6(this)).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DownloadsFragment$5(this));
        v0_1.show();
        return;
    }

    private void askRetryFailed()
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v0_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        v0_1.setTitle(2131887094).setMessage(2131887095).setPositiveButton(2131887093, new com.bisimplex.firebooru.fragment.DownloadsFragment$3(this)).setNegativeButton(2131886205, new com.bisimplex.firebooru.fragment.DownloadsFragment$2(this));
        v0_1.show();
        return;
    }

    private void clearAll()
    {
        com.bisimplex.firebooru.services.DownloadService.getInstance().cancelAll();
        io.objectbox.query.Query v0_3 = this.downloadEntryBox.query().build();
        v0_3.remove();
        v0_3.close();
        this.reloadAllData();
        return;
    }

    private void clearFailed()
    {
        io.objectbox.query.Query v0_3 = this.downloadEntryBox.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 3).build();
        v0_3.remove();
        v0_3.close();
        this.reloadAllData();
        return;
    }

    private void clearFinished()
    {
        io.objectbox.query.Query v0_2 = this.downloadEntryBox.query().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 2).or().equal(com.bisimplex.firebooru.model.DownloadEntry_.status, 4).build();
        v0_2.remove();
        v0_2.close();
        this.reloadAllData();
        return;
    }

    private void executeAction(com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction p4, int p5)
    {
        android.content.Context v0_1 = this.adapter.getItem(p5);
        int v4_5 = com.bisimplex.firebooru.fragment.DownloadsFragment$13.$SwitchMap$com$bisimplex$firebooru$fragment$DownloadsFragment$DownloadAction[p4.ordinal()];
        if (v4_5 == 1) {
            ((com.bisimplex.firebooru.activity.MainActivity) this.requireActivity()).copyToClipboard(v0_1.getPost_url());
            this.showMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
            return;
        } else {
            if (v4_5 == 2) {
                v0_1.setStatus(0);
                v0_1.setError_message("");
                this.downloadEntryBox.put(v0_1);
                this.adapter.updateItemAt(p5);
                com.bisimplex.firebooru.services.DownloadService.getInstance().checkIfMustLaunch(this.requireContext(), 0);
                return;
            } else {
                return;
            }
        }
    }

    private androidx.recyclerview.widget.DefaultItemAnimator generateAnimator()
    {
        androidx.recyclerview.widget.DefaultItemAnimator v0_1 = new androidx.recyclerview.widget.DefaultItemAnimator();
        v0_1.setSupportsChangeAnimations(0);
        v0_1.setChangeDuration(0);
        v0_1.setAddDuration(0);
        v0_1.setMoveDuration(0);
        v0_1.setRemoveDuration(0);
        return v0_1;
    }

    private synthetic boolean lambda$onViewCreated$0(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131361945) {
            if (p3.getItemId() != 2131361947) {
                if (p3.getItemId() != 2131361948) {
                    if (p3.getItemId() != 2131362464) {
                        if (p3.getItemId() != 2131362463) {
                            if (p3.getItemId() == 2131362417) {
                                this.pauseService();
                            }
                        } else {
                            this.resumeService();
                        }
                    } else {
                        this.askRetryFailed();
                    }
                } else {
                    this.clearFinished();
                }
            } else {
                this.askClearFailed();
            }
        } else {
            this.askClearAll();
        }
        return 1;
    }

    private void pauseService()
    {
        com.bisimplex.firebooru.services.DownloadService.getInstance().cancelAll();
        com.bisimplex.firebooru.model.ObjectBox.get().runInTx(new com.bisimplex.firebooru.fragment.DownloadsFragment$1(this));
        this.updateMenuButtons();
        return;
    }

    private void reloadAllData()
    {
        this.adapter.reloadItems(this.listQuery.findLazy());
        return;
    }

    private void resumeService()
    {
        com.bisimplex.firebooru.services.DownloadService.getInstance().checkIfMustLaunch(this.requireContext(), this.downloadEntryBox);
        this.updateMenuButtons();
        return;
    }

    private void retryFailed()
    {
        com.bisimplex.firebooru.model.ObjectBox.get().runInTx(new com.bisimplex.firebooru.fragment.DownloadsFragment$4(this));
        return;
    }

    private void updateMenuButtons()
    {
        android.view.MenuItem v0_1 = this.getVisibleBar().getMenu();
        android.view.MenuItem v1_1 = v0_1.findItem(2131362417);
        android.view.MenuItem v0_2 = v0_1.findItem(2131362463);
        if (!com.bisimplex.firebooru.services.DownloadService.getInstance().isRunning()) {
            v1_1.setVisible(0);
            v0_2.setVisible(1);
            return;
        } else {
            v1_1.setVisible(1);
            v0_2.setVisible(0);
            return;
        }
    }

    public void attachedToWindow(int p1)
    {
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public void itemClick(android.view.View p5, int p6)
    {
        com.google.android.material.dialog.MaterialAlertDialogBuilder v5_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.requireContext());
        com.bisimplex.firebooru.fragment.DownloadsFragment$12 v0_5 = this.adapter.getItem(p6);
        v5_1.setTitle(2131886431).setMessage(v0_5.getPost_url()).setNegativeButton(2131886239, new com.bisimplex.firebooru.fragment.DownloadsFragment$11(this)).setPositiveButton(2131886284, new com.bisimplex.firebooru.fragment.DownloadsFragment$10(this, p6));
        if ((!v0_5.isDownloading()) && (v0_5.isFailed())) {
            v5_1.setNeutralButton(2131887093, new com.bisimplex.firebooru.fragment.DownloadsFragment$12(this, p6));
        }
        v5_1.show();
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558430, 0);
        this.setHasOptionsMenu(1);
        this.setTitle(2131886443);
        this.adapter = new com.bisimplex.firebooru.dataadapter.DownloadsAdapter(this.getActivity(), this);
        androidx.recyclerview.widget.DividerItemDecoration v3_3 = com.bisimplex.firebooru.model.ObjectBox.get().boxFor(com.bisimplex.firebooru.model.DownloadEntry);
        this.downloadEntryBox = v3_3;
        this.listQuery = v3_3.query().order(com.bisimplex.firebooru.model.DownloadEntry_.id).build();
        this.recyclerView = ((androidx.recyclerview.widget.RecyclerView) v2_1.findViewById(2131362449));
        androidx.recyclerview.widget.DividerItemDecoration v3_11 = new androidx.recyclerview.widget.LinearLayoutManager(this.getContext());
        this.layoutManager = v3_11;
        this.recyclerView.setLayoutManager(v3_11);
        this.recyclerView.setItemAnimator(this.generateAnimator());
        this.recyclerView.setAdapter(this.adapter);
        this.reloadAllData();
        this.recyclerView.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(this.recyclerView.getContext(), this.layoutManager.getOrientation()));
        return v2_1;
    }

    public boolean onLongClick(android.view.View p1, int p2)
    {
        return 0;
    }

    public void onPause()
    {
        super.onPause();
        com.bisimplex.firebooru.services.DownloadService.getInstance().setListener(0);
        return;
    }

    public void onResume()
    {
        super.onResume();
        com.bisimplex.firebooru.services.DownloadService.getInstance().setListener(this.downloadServiceListener);
        return;
    }

    public void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.getVisibleBar().setOnMenuItemClickListener(new com.bisimplex.firebooru.fragment.DownloadsFragment$$ExternalSyntheticLambda0(this));
        this.updateMenuButtons();
        return;
    }
}
