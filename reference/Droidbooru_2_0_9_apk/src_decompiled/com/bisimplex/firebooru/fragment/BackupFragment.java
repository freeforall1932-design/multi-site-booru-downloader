package com.bisimplex.firebooru.fragment;
public class BackupFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.backup.BackupTask$BackupTaskListener {
    public static final int FILE_CODE = 1;
    public static final int REQUEST_CODE_STORAGE_ACCESS = 76;
    private com.google.android.material.switchmaterial.SwitchMaterial bannedTagSwitch;
    private com.google.android.material.switchmaterial.SwitchMaterial favoritesSwitch;
    private android.widget.RadioGroup formatRadioGroup;
    private com.google.android.material.switchmaterial.SwitchMaterial historySwitch;
    private final androidx.activity.result.ActivityResultLauncher openDocumentLauncher;
    private com.google.android.material.switchmaterial.SwitchMaterial pinsSwitch;
    protected com.bisimplex.firebooru.view.DelayedProgressDialog progressDialog;
    private com.google.android.material.switchmaterial.SwitchMaterial serversSwitch;

    static bridge synthetic void -$$Nest$mtriggerStorageAccessFramework(com.bisimplex.firebooru.fragment.BackupFragment p0)
    {
        p0.triggerStorageAccessFramework();
        return;
    }

    public BackupFragment()
    {
        this.openDocumentLauncher = this.registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts$OpenDocumentTree(), new com.bisimplex.firebooru.fragment.BackupFragment$3(this));
        return;
    }

    private void beginBackup()
    {
        return;
    }

    private void triggerStorageAccessFramework()
    {
        try {
            if ((this.serversSwitch.isChecked()) || (!this.pinsSwitch.isChecked())) {
                androidx.activity.result.ActivityResultLauncher v0_9 = this.getActivity();
                if (v0_9 != null) {
                    com.bumptech.glide.Glide.get(v0_9).clearMemory();
                    this.openDocumentLauncher.launch(0);
                    return;
                } else {
                    return;
                }
            } else {
                this.showMessage(2131887146, com.bisimplex.firebooru.activity.MessageType.Error);
                return;
            }
        } catch (androidx.activity.result.ActivityResultLauncher v0_5) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_5);
            this.showMessage(v0_5.getLocalizedMessage(), com.bisimplex.firebooru.activity.MessageType.Error);
            return;
        }
    }

    public void HideLoading()
    {
        int v0_0 = this.progressDialog;
        if (v0_0 != 0) {
            v0_0.cancel();
            this.progressDialog = 0;
            return;
        } else {
            return;
        }
    }

    public void ShowLoading()
    {
        if (this.progressDialog == null) {
            androidx.fragment.app.FragmentManager v0_1 = this.getActivity();
            if (v0_1 != null) {
                com.bisimplex.firebooru.view.DelayedProgressDialog v1_1 = new com.bisimplex.firebooru.view.DelayedProgressDialog();
                this.progressDialog = v1_1;
                v1_1.show(v0_1.getSupportFragmentManager(), "DelayedProgressDialog");
                return;
            }
        }
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.getView().findViewById(2131362492);
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public void onCreateOptionsMenu(android.view.Menu p1, android.view.MenuInflater p2)
    {
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558429, p3, 0);
        v2_1.findViewById(2131361901).setOnClickListener(new com.bisimplex.firebooru.fragment.BackupFragment$1(this));
        this.serversSwitch = ((com.google.android.material.switchmaterial.SwitchMaterial) v2_1.findViewById(2131362523));
        this.favoritesSwitch = ((com.google.android.material.switchmaterial.SwitchMaterial) v2_1.findViewById(2131362106));
        this.historySwitch = ((com.google.android.material.switchmaterial.SwitchMaterial) v2_1.findViewById(2131362158));
        this.bannedTagSwitch = ((com.google.android.material.switchmaterial.SwitchMaterial) v2_1.findViewById(2131361902));
        this.formatRadioGroup = ((android.widget.RadioGroup) v2_1.findViewById(2131362130));
        this.pinsSwitch = ((com.google.android.material.switchmaterial.SwitchMaterial) v2_1.findViewById(2131362422));
        this.setTitle(2131886166);
        return v2_1;
    }

    public void onResume()
    {
        super.onResume();
        if (com.bisimplex.firebooru.backup.BackupManager.getInstance().isWorking()) {
            this.ShowLoading();
        }
        return;
    }

    public void setSaveDirectory(android.net.Uri p4)
    {
        if (p4 != null) {
            com.bisimplex.firebooru.backup.BackupManager v1_9;
            this.ShowLoading();
            com.bisimplex.firebooru.backup.BackupConfiguration v0_1 = new com.bisimplex.firebooru.backup.BackupConfiguration();
            v0_1.setBannedTags(this.bannedTagSwitch.isChecked());
            v0_1.setServers(this.serversSwitch.isChecked());
            v0_1.setFavorites(this.favoritesSwitch.isChecked());
            v0_1.setHistory(this.historySwitch.isChecked());
            v0_1.setHomeSources(this.pinsSwitch.isChecked());
            if (this.formatRadioGroup.getCheckedRadioButtonId() != 2131361974) {
                v1_9 = 0;
            } else {
                v1_9 = 1;
            }
            v0_1.setToCSV(v1_9);
            com.bisimplex.firebooru.backup.BackupManager.getInstance().beginBackup(new ref.WeakReference(this), p4, v0_1);
            return;
        } else {
            return;
        }
    }

    public void workDone(android.net.Uri p3, String p4)
    {
        if (this.getActivity() != null) {
            this.HideLoading();
            com.google.android.material.dialog.MaterialAlertDialogBuilder v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
            if (p3 != null) {
                v0_2.setTitle(2131886166).setMessage(2131886169);
            } else {
                v0_2.setTitle(2131887211);
                if (!android.text.TextUtils.isEmpty(p4)) {
                    v0_2.setMessage(p4);
                } else {
                    v0_2.setMessage(2131886167);
                }
            }
            v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.BackupFragment$2(this));
            v0_2.setCancelable(0).show();
            return;
        } else {
            return;
        }
    }
}
