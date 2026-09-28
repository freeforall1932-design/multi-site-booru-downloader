package com.bisimplex.firebooru.danbooru;
public class DanbooruClient extends com.bisimplex.firebooru.danbooru.SourceProvider {
    public static final String CONTENT_TYPE = "content-type";
    public static String ERROR_CODE = "ERROR_CODE";
    public static String PAGE_LOADED = "PAGE_LOADED";
    public static String PAGE_LOADED_SUCCESS = "PAGE_LOADED_SUCCESS";
    public static final String TEXT_HTML = "text/html";
    private static com.bisimplex.firebooru.danbooru.DanbooruClient sharedInstance;
    private final int DEFAULT_TIMEOUT;
    protected boolean isNewSearch;

    static DanbooruClient()
    {
        return;
    }

    protected DanbooruClient()
    {
        this.DEFAULT_TIMEOUT = 30000;
        return;
    }

    protected DanbooruClient(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        super(p1);
        super.DEFAULT_TIMEOUT = 30000;
        return;
    }

    public static com.bisimplex.firebooru.danbooru.DanbooruClient getInstance()
    {
        if (com.bisimplex.firebooru.danbooru.DanbooruClient.sharedInstance == null) {
            com.bisimplex.firebooru.danbooru.DanbooruClient.sharedInstance = new com.bisimplex.firebooru.danbooru.DanbooruClient();
        }
        return com.bisimplex.firebooru.danbooru.DanbooruClient.sharedInstance;
    }
}
