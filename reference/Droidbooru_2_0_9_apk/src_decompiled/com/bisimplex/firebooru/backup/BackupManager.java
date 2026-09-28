package com.bisimplex.firebooru.backup;
public class BackupManager {
    private static final com.bisimplex.firebooru.backup.BackupManager ourInstance;
    private com.bisimplex.firebooru.backup.BackupCVSTask backupCVSTask;
    private com.bisimplex.firebooru.backup.BackupTask backupTask;
    private com.bisimplex.firebooru.backup.RestoreTask restoreTask;

    static BackupManager()
    {
        com.bisimplex.firebooru.backup.BackupManager.ourInstance = new com.bisimplex.firebooru.backup.BackupManager();
        return;
    }

    private BackupManager()
    {
        return;
    }

    public static com.bisimplex.firebooru.backup.BackupManager getInstance()
    {
        return com.bisimplex.firebooru.backup.BackupManager.ourInstance;
    }

    public void beginBackup(ref.WeakReference p2, android.net.Uri p3, com.bisimplex.firebooru.backup.BackupConfiguration p4)
    {
        if (!this.isWorking()) {
            com.bisimplex.firebooru.backup.BackupTask v0_2 = new com.bisimplex.firebooru.backup.BackupTask(p2, p3, p4);
            this.backupTask = v0_2;
            v0_2.start();
            return;
        } else {
            this.backupTask.setListener(p2);
            return;
        }
    }

    public void beginBackupToCSV(com.bisimplex.firebooru.backup.BackupCVSOptions p3)
    {
        com.bisimplex.firebooru.backup.BackupCVSTask v0_1 = new com.bisimplex.firebooru.backup.BackupCVSTask(0, p3);
        this.backupCVSTask = v0_1;
        v0_1.start();
        return;
    }

    public void beginRestore(ref.WeakReference p2, com.bisimplex.firebooru.backup.LiveDB p3)
    {
        if (!this.isWorkingOnRestore()) {
            com.bisimplex.firebooru.backup.RestoreTask v0_2 = new com.bisimplex.firebooru.backup.RestoreTask(p2, p3);
            this.restoreTask = v0_2;
            v0_2.start();
            return;
        } else {
            this.restoreTask.setListener(p2);
            return;
        }
    }

    public void cancelBackup()
    {
        return;
    }

    public void cancelRestore()
    {
        return;
    }

    public boolean isWorking()
    {
        boolean v0_0 = this.backupTask;
        if (v0_0) {
            return v0_0.isWorking();
        } else {
            return 0;
        }
    }

    public boolean isWorkingOnRestore()
    {
        boolean v0_0 = this.restoreTask;
        if (v0_0) {
            return v0_0.isWorking();
        } else {
            return 0;
        }
    }
}
