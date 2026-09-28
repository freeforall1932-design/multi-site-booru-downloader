package com.bisimplex.firebooru.activity;
public abstract class SkeletonActivity extends androidx.appcompat.app.AppCompatActivity {
    private boolean paused;

    public SkeletonActivity()
    {
        this.paused = 0;
        return;
    }

    public boolean isPaused()
    {
        return this.paused;
    }

    protected void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        this.setTheme(com.bisimplex.firebooru.custom.ThemeType.styleFromType(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThemeSelected()));
        this.paused = 0;
        return;
    }

    protected void onPause()
    {
        super.onPause();
        this.paused = 1;
        return;
    }

    protected void onResume()
    {
        super.onResume();
        this.paused = 0;
        androidx.work.WorkManager.getInstance(this).cancelUniqueWork("CacheCleanUp");
        return;
    }

    protected void onStart()
    {
        super.onStart();
        this.paused = 0;
        return;
    }

    protected void onStop()
    {
        super.onStop();
        androidx.work.WorkManager.getInstance(this).enqueueUniqueWork("CacheCleanUp", androidx.work.ExistingWorkPolicy.KEEP, ((androidx.work.OneTimeWorkRequest) new androidx.work.OneTimeWorkRequest$Builder(com.bisimplex.firebooru.services.CacheCleanUpWorker).build()));
        return;
    }
}
