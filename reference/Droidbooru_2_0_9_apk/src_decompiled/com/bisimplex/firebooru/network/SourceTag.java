package com.bisimplex.firebooru.network;
public class SourceTag extends com.bisimplex.firebooru.network.Source {
    public static final String SERVER_ID_KEY = "SERVER_ID_KEY";
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;

    public static synthetic void $r8$lambda$2Mzc5XGGzb_RLHYAzHVt-WCY01Y(com.bisimplex.firebooru.network.SourceTag p0, com.bisimplex.firebooru.network.ParserTag p1)
    {
        p0.lambda$prepareLoadAnotherPage$0(p1);
        return;
    }

    public static synthetic void $r8$lambda$NA9kRGdgj5xReZOVInBQ1dRHVt0(com.bisimplex.firebooru.network.SourceTag p0)
    {
        p0.lambda$prepareLoadAnotherPage$1();
        return;
    }

    public SourceTag(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        super.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        return;
    }

    private synthetic void lambda$prepareLoadAnotherPage$0(com.bisimplex.firebooru.network.ParserTag p2)
    {
        if (this.isLoading) {
            this.notifySuccess(p2.getData());
        }
        return;
    }

    private synthetic void lambda$prepareLoadAnotherPage$1()
    {
        if (this.isLoading) {
            com.bisimplex.firebooru.network.ParserTag v0_2 = ((com.bisimplex.firebooru.network.ParserTag) com.bisimplex.firebooru.network.Parser.fromProvider(new com.bisimplex.firebooru.network.ParserParams(0, this.currentPage, this.getQuery(), this.provider, this.getType())));
            v0_2.parse();
            this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourceTag$$ExternalSyntheticLambda1(this, v0_2));
            return;
        } else {
            return;
        }
    }

    protected String generateURLForCurrentPage()
    {
        String v0_4 = this.provider.encodeToUTF8(this.getQuery().getText());
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().useAutoCompleteFromServer()) {
            String v1_5 = ((String) this.getQuery().getExtraParams().get("SERVER_ID_KEY"));
            if (!android.text.TextUtils.isEmpty(v1_5)) {
                String v1_6 = Integer.parseInt(v1_5);
                if (v1_6 != null) {
                    String v1_7 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerById(v1_6);
                    boolean v3_4 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().ServerToServerItem(v1_7);
                    if (v1_7 != null) {
                        String v1_8 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(v3_4);
                        this.setProvider(v1_8);
                        String v1_10 = v1_8.generateAutocompleteTagURL(v0_4);
                        if (!android.text.TextUtils.isEmpty(v1_10)) {
                            android.util.Log.i("sourcetag", "autocomplete to server url");
                            return v1_10;
                        }
                    }
                }
            }
        }
        android.util.Log.i("sourcetag", "autocomplete to default url");
        return String.format(java.util.Locale.US, "https://bisimplex.azurewebsites.net/api/tags?limit=%d&name=%s", new Object[] {Integer.valueOf(this.pageSize), v0_4}));
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.Tag;
    }

    protected void prepareLoadAnotherPage()
    {
        super.prepareLoadAnotherPage();
        this.executor.execute(new com.bisimplex.firebooru.network.SourceTag$$ExternalSyntheticLambda0(this));
        return;
    }
}
