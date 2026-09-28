package com.bisimplex.firebooru;
public class DroidBooruApplication extends com.activeandroid.app.Application {
    private static com.bisimplex.firebooru.DroidBooruApplication application;

    public DroidBooruApplication()
    {
        return;
    }

    public static android.content.Context getAppContext()
    {
        return com.bisimplex.firebooru.DroidBooruApplication.application.getApplicationContext();
    }

    protected void attachBaseContext(android.content.Context p12)
    {
        super.attachBaseContext(p12);
        org.acra.config.CoreConfigurationBuilder v12_7 = new org.acra.config.CoreConfigurationBuilder().withBuildConfigClass(com.bisimplex.firebooru.BuildConfig).withReportFormat(org.acra.data.StringFormat.JSON);
        org.acra.config.Configuration[] v0_0 = new org.acra.ReportField[12];
        v0_0[0] = org.acra.ReportField.REPORT_ID;
        v0_0[1] = org.acra.ReportField.APP_VERSION_CODE;
        v0_0[2] = org.acra.ReportField.PHONE_MODEL;
        v0_0[3] = org.acra.ReportField.ANDROID_VERSION;
        v0_0[4] = org.acra.ReportField.TOTAL_MEM_SIZE;
        v0_0[5] = org.acra.ReportField.AVAILABLE_MEM_SIZE;
        v0_0[6] = org.acra.ReportField.STACK_TRACE;
        v0_0[7] = org.acra.ReportField.STACK_TRACE_HASH;
        v0_0[8] = org.acra.ReportField.USER_APP_START_DATE;
        v0_0[9] = org.acra.ReportField.USER_CRASH_DATE;
        v0_0[10] = org.acra.ReportField.CRASH_CONFIGURATION;
        v0_0[11] = org.acra.ReportField.LOGCAT;
        org.acra.config.CoreConfigurationBuilder v12_4 = v12_7.withReportContent(v0_0);
        org.acra.config.Configuration[] v0_2 = new String[7];
        v0_2[0] = "-t";
        v0_2[1] = "200";
        v0_2[2] = "-v";
        v0_2[3] = "time";
        v0_2[4] = "ActivityManager:I";
        v0_2[5] = "AnimeBoxes:I";
        v0_2[6] = "*:S";
        org.acra.config.CoreConfigurationBuilder v12_6 = v12_4.withLogcatArguments(v0_2);
        org.acra.config.Configuration[] v0_4 = new org.acra.config.Configuration[2];
        v0_4[0] = new org.acra.config.DialogConfigurationBuilder().withTitle(this.getString(2131887085)).withText(this.getString(2131887082)).withPositiveButtonText(this.getString(2131887131)).withNegativeButtonText(this.getString(2131886205)).withResTheme(Integer.valueOf(2131951996)).withResIcon(Integer.valueOf(2131231028)).build();
        v0_4[1] = new org.acra.config.MailSenderConfigurationBuilder().withMailTo("support@bisimplex.com").withReportAsFile(1).withReportFileName("crash_log.json").withSubject(this.getString(2131887084)).withBody(this.getString(2131887081)).build();
        org.acra.ACRA.init(this, v12_6.withPluginConfigurations(v0_4));
        return;
    }

    public void onCreate()
    {
        super.onCreate();
        com.bisimplex.firebooru.DroidBooruApplication.application = this;
        com.mikepenz.iconics.Iconics.init(this);
        com.bisimplex.firebooru.model.ObjectBox.init(this);
        if (!org.acra.ACRA.isACRASenderServiceProcess()) {
            com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().checkUserAgent();
            org.acra.ACRA.getErrorReporter().setEnabled(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().askForCrashReport());
            return;
        } else {
            return;
        }
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        com.bumptech.glide.Glide.get(this).clearMemory();
        return;
    }

    public void onTrimMemory(int p2)
    {
        super.onTrimMemory(p2);
        com.bumptech.glide.Glide.get(this).trimMemory(p2);
        return;
    }
}
