package com.bisimplex.firebooru.network;
public abstract class Source implements okhttp3.Callback {
    protected okhttp3.Call currentOperation;
    protected int currentPage;
    protected java.util.List data;
    protected boolean isLastPage;
    protected boolean isLoading;
    protected boolean isNewSearch;
    private String key;
    private int lastErrorCode;
    protected int lastPageCount;
    protected java.time.LocalDateTime lastRequestTime;
    private ref.WeakReference listener;
    private final android.os.Handler mainHandler;
    protected long minimumMilisecondDelay;
    protected java.util.List pageIndexes;
    protected int pageOffset;
    protected int pageSize;
    protected com.bisimplex.firebooru.danbooru.BooruProvider provider;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private final java.util.concurrent.ScheduledExecutorService scheduledExecutor;
    protected java.util.concurrent.ScheduledFuture scheduledFuture;

    static bridge synthetic void -$$Nest$mendParsingWithEmptyBody(com.bisimplex.firebooru.network.Source p0)
    {
        p0.endParsingWithEmptyBody();
        return;
    }

    public Source()
    {
        this.scheduledExecutor = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();
        this.mainHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.data = new java.util.ArrayList();
        this.isNewSearch = 1;
        this.query = new com.bisimplex.firebooru.network.SourceQuery();
        this.pageIndexes = new java.util.ArrayList(0);
        return;
    }

    public Source(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        this.query = p3;
        this.provider = p1;
        this.pageSize = p2;
        return;
    }

    private void clean()
    {
        java.util.List v0 = this.data;
        if (v0 != null) {
            v0.clear();
            this.resetPageNumber();
        }
        return;
    }

    private void endParsingOnMainThread(com.bisimplex.firebooru.network.Parser p2)
    {
        this.mainHandler.post(new com.bisimplex.firebooru.network.Source$3(this, p2));
        return;
    }

    private void endParsingWithEmptyBody()
    {
        this.currentOperation = 0;
        this.isLoading = 0;
        this.notifySuccess(new java.util.ArrayList(0));
        return;
    }

    private void endParsingWithEmptyBodyOnMainThread()
    {
        this.mainHandler.post(new com.bisimplex.firebooru.network.Source$1(this));
        return;
    }

    private void notifyFailureOnMainThread(com.bisimplex.firebooru.data.FailureType p2)
    {
        this.mainHandler.post(new com.bisimplex.firebooru.network.Source$2(this, p2));
        return;
    }

    public void block()
    {
        this.isNewSearch = 0;
        this.isLastPage = 1;
        return;
    }

    public void cancelCurrentConnection()
    {
        okhttp3.Call v0_0 = this.currentOperation;
        if ((v0_0 != null) && ((!v0_0.isCanceled()) && (!this.currentOperation.isExecuted()))) {
            this.currentOperation.cancel();
        }
        this.cancelScheduledRequest();
        this.currentOperation = 0;
        return;
    }

    protected void cancelScheduledRequest()
    {
        int v0_0 = this.scheduledFuture;
        if (v0_0 == 0) {
            return;
        } else {
            try {
                if (v0_0.get() != null) {
                    this.scheduledFuture.cancel(1);
                }
            } catch (int v0_4) {
                throw new RuntimeException(v0_4);
            }
            this.scheduledFuture = 0;
            return;
        }
    }

    public void configureParserParams(com.bisimplex.firebooru.network.ParserParams p1)
    {
        return;
    }

    protected void endParsing(com.bisimplex.firebooru.network.Parser p3)
    {
        this.currentOperation = 0;
        this.isLoading = 0;
        if (p3 == null) {
            this.notifyFailure(com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed);
            return;
        } else {
            this.data.addAll(p3.getData());
            if (!p3.getData().isEmpty()) {
                this.pageIndexes.add(Integer.valueOf(this.data.size()));
            }
            this.notifySuccess(p3.getData());
            return;
        }
    }

    public boolean existItemAt(int p3)
    {
        if (p3 >= 0) {
            int v1_0 = this.data;
            if ((v1_0 == 0) || (p3 >= v1_0.size())) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    protected abstract String generateURLForCurrentPage();

    public int getCurrentPage()
    {
        return this.currentPage;
    }

    public java.util.List getData()
    {
        return this.data;
    }

    public boolean getIsLoading()
    {
        return this.isLoading;
    }

    public Object getItemAt(int p2)
    {
        if (!this.existItemAt(p2)) {
            return 0;
        } else {
            return this.data.get(p2);
        }
    }

    public int getItemCount()
    {
        return this.getData().size();
    }

    public String getKey()
    {
        if (android.text.TextUtils.isEmpty(this.key)) {
            this.key = com.bisimplex.firebooru.network.Utils.generateEmptyKey();
        }
        return this.key;
    }

    public int getLastErrorCode()
    {
        return this.lastErrorCode;
    }

    public int getLastPageCount()
    {
        return this.lastPageCount;
    }

    public com.bisimplex.firebooru.network.SourceListener getListener()
    {
        return ((com.bisimplex.firebooru.network.SourceListener) this.listener.get());
    }

    public int getPageOffset()
    {
        return this.pageOffset;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        return this.provider;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        return this.getProvider();
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        if (this.query == null) {
            this.query = new com.bisimplex.firebooru.network.SourceQuery();
        }
        return this.query;
    }

    public abstract com.bisimplex.firebooru.network.SourceType getType();

    public boolean isLastItem(Object p3)
    {
        if (this.data.indexOf(p3) != (this.getItemCount() - 1)) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isLastPage()
    {
        return this.isLastPage;
    }

    public boolean isNewSearch()
    {
        return this.isNewSearch;
    }

    public void loadAnotherPage()
    {
        this.prepareLoadAnotherPage();
        String v0 = this.generateURLForCurrentPage();
        if (!android.text.TextUtils.isEmpty(v0)) {
            this.runLoadAnotherPage(v0);
            return;
        } else {
            return;
        }
    }

    public void loadAnotherPage(com.bisimplex.firebooru.network.SourceListener p1)
    {
        this.setListener(p1);
        this.loadAnotherPage();
        return;
    }

    public void loadAnotherPageSync()
    {
        this.prepareLoadAnotherPage();
        com.bisimplex.firebooru.network.Parser v0_2 = this.generateURLForCurrentPage();
        if (!android.text.TextUtils.isEmpty(v0_2)) {
            int v1_2 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
            com.bisimplex.firebooru.network.Parser v0_1 = new okhttp3.Request$Builder().url(v0_2).header("User-Agent", this.provider.getUserAgent());
            if (this.provider.getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
                v0_1.addHeader("Cookie", "agreed=true");
            }
            com.bisimplex.firebooru.network.Parser v0_4 = v1_2.newCall(v0_1.build());
            this.currentOperation = v0_4;
            try {
                com.bisimplex.firebooru.network.Utils v3_3 = v0_4.execute();
                try {
                    if (v3_3.isSuccessful()) {
                        int v5;
                        com.bisimplex.firebooru.network.Parser v0_6 = v3_3.body();
                        if (v0_6 == null) {
                            v5 = 0;
                        } else {
                            com.bisimplex.firebooru.network.ParserParams v4_0 = v0_6.string();
                            v0_6.close();
                            v5 = v4_0;
                        }
                        if (!android.text.TextUtils.isEmpty(v5)) {
                            com.bisimplex.firebooru.network.ParserParams v4_2 = new com.bisimplex.firebooru.network.ParserParams(v5, this.currentPage, this.query, this.provider, this.getType());
                            this.configureParserParams(v4_2);
                            com.bisimplex.firebooru.network.Parser v0_8 = com.bisimplex.firebooru.network.Parser.fromProvider(v4_2);
                            if (v0_8 != null) {
                                v0_8.parse();
                                this.endParsing(v0_8);
                            }
                        }
                    }
                } catch (com.bisimplex.firebooru.network.Parser v0_9) {
                    com.bisimplex.firebooru.network.ParserParams v4_3 = v0_9;
                    if (v3_3 != null) {
                        try {
                            v3_3.close();
                        } catch (com.bisimplex.firebooru.network.Parser v0_10) {
                            v4_3.addSuppressed(v0_10);
                        }
                    }
                    throw v4_3;
                }
                if (v3_3 == null) {
                    this.currentOperation = 0;
                    this.isLoading = 0;
                    return;
                } else {
                    v3_3.close();
                    this.currentOperation = 0;
                    this.isLoading = 0;
                    return;
                }
            } catch (com.bisimplex.firebooru.network.Parser v0_11) {
                com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_11);
                this.currentOperation = 0;
                this.isLoading = 0;
                return;
            } catch (com.bisimplex.firebooru.network.Parser v0_12) {
                this.currentOperation = 0;
                this.isLoading = 0;
                throw v0_12;
            }
        } else {
            return;
        }
    }

    protected void notifyFailure(com.bisimplex.firebooru.data.FailureType p2)
    {
        com.bisimplex.firebooru.network.SourceListener v0_0 = this.listener;
        if ((v0_0 != null) && (v0_0.get() != null)) {
            ((com.bisimplex.firebooru.network.SourceListener) this.listener.get()).failure(this, p2);
        }
        return;
    }

    protected void notifySuccess(java.util.List p2)
    {
        this.isNewSearch = 0;
        this.isLastPage = p2.isEmpty();
        com.bisimplex.firebooru.network.SourceListener v0_5 = this.listener;
        if ((v0_5 != null) && (v0_5.get() != null)) {
            ((com.bisimplex.firebooru.network.SourceListener) this.listener.get()).success(this, p2);
        }
        return;
    }

    public void onFailure(okhttp3.Call p1, java.io.IOException p2)
    {
        this.currentOperation = 0;
        this.isLoading = 0;
        com.bisimplex.firebooru.network.Utils.getInstance().logException(p2);
        this.notifyFailure(com.bisimplex.firebooru.data.FailureType.Connection);
        return;
    }

    public void onResponse(okhttp3.Call p7, okhttp3.Response p8)
    {
        try {
            if (this.currentOperation != null) {
                if (!p8.isSuccessful()) {
                    this.setLastErrorCode(p8.code());
                    if (!this.provider.isParseAsHTML()) {
                        this.notifyFailureOnMainThread(com.bisimplex.firebooru.data.FailureType.fromInteger(p8.code()));
                        return;
                    } else {
                        this.endParsingWithEmptyBodyOnMainThread();
                        return;
                    }
                } else {
                    com.bisimplex.firebooru.data.FailureType v8_1;
                    com.bisimplex.firebooru.network.Parser v7_4 = p8.body();
                    if (v7_4 == null) {
                        v8_1 = 0;
                    } else {
                        v8_1 = v7_4.string();
                        v7_4.close();
                    }
                    int v1 = v8_1;
                    if (!android.text.TextUtils.isEmpty(v1)) {
                        com.bisimplex.firebooru.network.ParserParams v0_1 = new com.bisimplex.firebooru.network.ParserParams(v1, this.currentPage, this.query, this.provider, this.getType());
                        this.configureParserParams(v0_1);
                        com.bisimplex.firebooru.network.Parser v7_7 = com.bisimplex.firebooru.network.Parser.fromProvider(v0_1);
                        if (v7_7 == null) {
                            this.notifyFailureOnMainThread(com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed);
                        } else {
                            v7_7.parse();
                        }
                        this.endParsingOnMainThread(v7_7);
                        return;
                    } else {
                        this.endParsingWithEmptyBodyOnMainThread();
                        return;
                    }
                }
            } else {
                return;
            }
        } catch (com.bisimplex.firebooru.network.ParserParams v0_2) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v0_2);
            this.notifyFailureOnMainThread(com.bisimplex.firebooru.data.FailureType.ContentCannotBeParsed);
            return;
        }
    }

    public int pageNumberOfItemIndex(int p3)
    {
        int v0_0 = 0;
        while (v0_0 < this.pageIndexes.size()) {
            if (p3 > ((Integer) this.pageIndexes.get(v0_0)).intValue()) {
                v0_0++;
            } else {
                return (v0_0 + this.pageOffset);
            }
        }
        return -1;
    }

    protected void prepareLoadAnotherPage()
    {
        this.cancelCurrentConnection();
        if (!this.isNewSearch) {
            this.currentPage = (this.currentPage + 1);
        } else {
            this.currentPage = (this.provider.getInitialPageNumber() + this.pageOffset);
        }
        this.isLoading = 1;
        this.lastPageCount = 0;
        this.setLastErrorCode(0);
        return;
    }

    public void replaceItemAt(int p2, Object p3)
    {
        if (this.existItemAt(p2)) {
            this.data.set(p2, p3);
        }
        return;
    }

    public void reset()
    {
        this.clean();
        this.cancelCurrentConnection();
        this.lastPageCount = 0;
        this.currentPage = this.provider.getInitialPageNumber();
        this.pageOffset = 0;
        this.isNewSearch = 1;
        this.isLastPage = 0;
        this.isLoading = 0;
        this.pageIndexes.clear();
        this.setLastErrorCode(0);
        return;
    }

    public void resetPageNumber()
    {
        this.currentPage = this.pageOffset;
        this.isLastPage = 0;
        return;
    }

    protected void runLoadAnotherPage(String p4)
    {
        okhttp3.OkHttpClient v0 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
        java.time.LocalDateTime v4_1 = new okhttp3.Request$Builder().url(p4).header("User-Agent", this.provider.getUserAgent());
        if (this.provider.getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeShimmie) {
            v4_1.addHeader("Cookie", "agreed=true");
        }
        if (this.provider.getServerDescription().getType() == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeBIO) {
            v4_1.addHeader("Accept-Encoding", "identity");
        }
        java.time.LocalDateTime v4_3 = v0.newCall(v4_1.build());
        this.currentOperation = v4_3;
        v4_3.enqueue(this);
        this.lastRequestTime = java.time.LocalDateTime.now();
        return;
    }

    protected void setCurrentPage(int p1)
    {
        this.currentPage = p1;
        return;
    }

    public void setData(java.util.List p1)
    {
        this.data = p1;
        return;
    }

    public void setLastErrorCode(int p1)
    {
        this.lastErrorCode = p1;
        return;
    }

    public void setLastPageCount(int p1)
    {
        this.lastPageCount = p1;
        return;
    }

    public void setListener(com.bisimplex.firebooru.network.SourceListener p2)
    {
        this.listener = new ref.WeakReference(p2);
        return;
    }

    public void setPageOffset(int p1)
    {
        this.pageOffset = p1;
        return;
    }

    public void setPageSize(int p1)
    {
        this.pageSize = p1;
        return;
    }

    public void setProvider(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        this.provider = p1;
        return;
    }

    public void setQuery(com.bisimplex.firebooru.network.SourceQuery p1)
    {
        this.query = p1;
        this.reset();
        return;
    }
}
