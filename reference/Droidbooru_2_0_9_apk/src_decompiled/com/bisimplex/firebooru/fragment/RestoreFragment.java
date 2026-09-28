package com.bisimplex.firebooru.fragment;
public class RestoreFragment extends com.bisimplex.firebooru.fragment.BaseFragment implements com.bisimplex.firebooru.backup.RestoreTask$RestoreTaskListener, com.bisimplex.firebooru.backup.LoadLiveDBTask$LoadLiveDBTaskListener {
    public static final int REQUEST_CODE_STORAGE_ACCESS = 57;
    private com.bisimplex.firebooru.backup.LiveDB liveDB;
    private com.bisimplex.firebooru.backup.LoadLiveDBTask loadTask;
    private final androidx.activity.result.ActivityResultLauncher openDocumentLauncher;
    protected com.bisimplex.firebooru.view.DelayedProgressDialog progressDialog;
    private android.widget.Button restoreButton;
    private android.net.Uri selectedUri;
    private android.widget.TextView statusTextView;

    static bridge synthetic void -$$Nest$mbeginRestore(com.bisimplex.firebooru.fragment.RestoreFragment p0)
    {
        p0.beginRestore();
        return;
    }

    static bridge synthetic void -$$Nest$mselectFile(com.bisimplex.firebooru.fragment.RestoreFragment p0)
    {
        p0.selectFile();
        return;
    }

    public RestoreFragment()
    {
        this.openDocumentLauncher = this.registerForActivityResult(new androidx.activity.result.contract.ActivityResultContracts$OpenDocument(), new com.bisimplex.firebooru.fragment.RestoreFragment$3(this));
        return;
    }

    private void beginRestore()
    {
        if ((!com.bisimplex.firebooru.backup.BackupManager.getInstance().isWorkingOnRestore()) && (this.liveDB != null)) {
            com.bisimplex.firebooru.backup.BackupManager.getInstance().beginRestore(new ref.WeakReference(this), this.liveDB);
            this.ShowLoading();
            return;
        } else {
            return;
        }
    }

    private void selectFile()
    {
        if ((!com.bisimplex.firebooru.backup.BackupManager.getInstance().isWorkingOnRestore()) && (this.getActivity() != null)) {
            String[] v0_4 = new String[1];
            v0_4[0] = "*/*";
            this.openDocumentLauncher.launch(v0_4);
            return;
        } else {
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

    public void loadDone(com.bisimplex.firebooru.backup.LiveDB p12)
    {
        if (this.getActivity() != null) {
            this.liveDB = p12;
            String v0_2 = new StringBuilder(this.getString(2131887090));
            if (this.liveDB == null) {
                this.restoreButton.setEnabled(0);
                v0_2.append("\n\n");
                v0_2.append(this.getString(2131886726));
            } else {
                this.restoreButton.setEnabled(1);
                v0_2.append("\n\n");
                int v1_2 = java.text.SimpleDateFormat.getDateTimeInstance();
                if (this.liveDB.getBackupTime() != null) {
                    v1_2.format(p12.getBackupTime());
                }
                int v1_6 = (p12.getFavorites().size() + p12.getRawFavorites().size());
                String v2_7 = p12.getSearchHistory().size();
                String v3_2 = p12.getServers().size();
                String v4_1 = p12.getBannedTags().size();
                Integer v6_1 = p12.getHomePins().size();
                Integer v7 = v2_7;
                String v2_8 = p12.getCreatorName();
                Integer v8 = v3_2;
                p12.getCreatorVersion();
                p12.getBackupVersion();
                int v1_9 = Integer.valueOf(v1_6);
                Integer.valueOf(v7);
                Integer.valueOf(v8);
                Integer.valueOf(v4_1);
                Integer v6 = v1_9;
                v0_2.append(this.getString(2131886795, new Object[] {v2_8, Integer.valueOf(v6_1)})));
            }
            this.statusTextView.setText(v0_2.toString());
            this.HideLoading();
            return;
        } else {
            return;
        }
    }

    public void loadFile(android.net.Uri p3)
    {
        this.selectedUri = p3;
        if (this.loadTask != null) {
            this.loadTask = 0;
        }
        if (p3 != null) {
            this.restoreButton.setEnabled(1);
            com.bisimplex.firebooru.backup.LoadLiveDBTask v3_1 = new com.bisimplex.firebooru.backup.LoadLiveDBTask(new ref.WeakReference(this), this.selectedUri);
            this.loadTask = v3_1;
            v3_1.start();
            this.ShowLoading();
            return;
        } else {
            this.restoreButton.setEnabled(0);
            return;
        }
    }

    public void onCreateOptionsMenu(android.view.Menu p1, android.view.MenuInflater p2)
    {
        return;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        android.view.View v2_1 = p2.inflate(2131558441, p3, 0);
        this.restoreButton = ((android.widget.Button) v2_1.findViewById(2131362462));
        this.statusTextView = ((android.widget.TextView) v2_1.findViewById(2131362576));
        this.restoreButton.setOnClickListener(new com.bisimplex.firebooru.fragment.RestoreFragment$1(this));
        v2_1.findViewById(2131362107).setOnClickListener(new com.bisimplex.firebooru.fragment.RestoreFragment$2(this));
        this.setTitle(2131887088);
        return v2_1;
    }

    public void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        String v0_0 = this.selectedUri;
        if (v0_0 != null) {
            p3.putString("selectedFile", v0_0.toString());
        }
        return;
    }

    public void onViewStateRestored(android.os.Bundle p3)
    {
        super.onViewStateRestored(p3);
        if (p3 != null) {
            android.net.Uri v3_4 = p3.getString("selectedFile", "");
            if (!android.text.TextUtils.isEmpty(v3_4)) {
                this.loadFile(android.net.Uri.parse(v3_4));
            }
        }
        if (com.bisimplex.firebooru.backup.BackupManager.getInstance().isWorkingOnRestore()) {
            this.ShowLoading();
        }
        return;
    }

    public void restoreDone(com.bisimplex.firebooru.backup.RestoreStatusType p3, String p4)
    {
        if (this.getActivity() != null) {
            this.HideLoading();
            com.google.android.material.dialog.MaterialAlertDialogBuilder v0_2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
            if (p3 != com.bisimplex.firebooru.backup.RestoreStatusType.Success) {
                v0_2.setTitle(2131887211);
                if (!android.text.TextUtils.isEmpty(p4)) {
                    v0_2.setMessage(p4);
                } else {
                    v0_2.setMessage(this.getString(2131886502));
                }
            } else {
                v0_2.setTitle(2131887088);
                v0_2.setMessage(2131887089);
            }
            v0_2.setPositiveButton(2131886997, new com.bisimplex.firebooru.fragment.RestoreFragment$4(this));
            v0_2.setCancelable(0).show();
            return;
        } else {
            return;
        }
    }
}
