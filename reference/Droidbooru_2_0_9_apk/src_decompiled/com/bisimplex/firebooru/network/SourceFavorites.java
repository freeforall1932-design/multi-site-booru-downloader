package com.bisimplex.firebooru.network;
public class SourceFavorites extends com.bisimplex.firebooru.network.SourcePostBasic {
    public static final String FILTER_EXCLUDE_TAGS = "FILTER_EXCLUDE_TAGS";
    public static final String FILTER_EXT = "FILTER_EXT";
    public static final String FILTER_PAGE = "FILTER_PAGE";
    public static final String FILTER_RATING = "FILTER_RATING";
    public static final String FILTER_SEARCH_TYPE = "FILTER_SEARCH_TYPE";
    public static final String FILTER_SERVER_URL = "FILTER_SERVER_URL";
    public static final String FILTER_SORT_ID = "FILTER_SORT_ID";
    public static final String FILTER_SOURCE = "FILTER_SOURCE";
    protected final android.os.Handler enqueueHandler;
    protected final java.util.concurrent.Executor executor;
    private okhttp3.Call favoriteCall;
    private com.bisimplex.firebooru.network.FavoriteSyncListener favoriteSyncListener;

    public static synthetic void $r8$lambda$5THQI5TTLcDashPW1Fv8G6TIGIY(com.bisimplex.firebooru.network.SourceFavorites p0, okhttp3.Response p1)
    {
        p0.lambda$onResponse$2(p1);
        return;
    }

    public static synthetic void $r8$lambda$KKN-BjgQ8dZs7urvn_Sqe30_jXs(com.bisimplex.firebooru.network.SourceFavorites p0, com.bisimplex.firebooru.network.SourceQuery p1, int p2)
    {
        p0.lambda$loadAnotherPage$1(p1, p2);
        return;
    }

    public static synthetic void $r8$lambda$hoY_2BBT4uEy02ikPnKSAZ9nJ6I(com.bisimplex.firebooru.network.SourceFavorites p0, java.util.List p1)
    {
        p0.lambda$loadAnotherPage$0(p1);
        return;
    }

    public static synthetic void $r8$lambda$ogWa9rHVce69UvJ4tFoOdQprMzg(com.bisimplex.firebooru.network.SourceFavorites p0)
    {
        p0.lambda$onFailure$3();
        return;
    }

    public SourceFavorites(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        super.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        return;
    }

    private synthetic void lambda$loadAnotherPage$0(java.util.List p2)
    {
        this.isLoading = 0;
        this.lastPageCount = p2.size();
        if (this.lastPageCount > 0) {
            this.data.addAll(p2);
        }
        this.notifySuccess(p2);
        return;
    }

    private synthetic void lambda$loadAnotherPage$1(com.bisimplex.firebooru.network.SourceQuery p3, int p4)
    {
        if (p3.getExtraParams() == null) {
            new java.util.HashMap(0);
        }
        this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourceFavorites$$ExternalSyntheticLambda0(this, com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().loadFavoritesByFilter(p3, p4)));
        return;
    }

    private synthetic void lambda$onFailure$3()
    {
        this.favoriteSyncListener.favoriteSyncComplete(this, 0);
        return;
    }

    private synthetic void lambda$onResponse$2(okhttp3.Response p2)
    {
        this.favoriteSyncListener.favoriteSyncComplete(this, p2.isSuccessful());
        return;
    }

    public boolean canSync(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if ((p3 == 0) || ((this.provider == null) || (this.provider.generateUrlForAddFavorite(p3) == null))) {
            return 0;
        } else {
            return 1;
        }
    }

    public void cancelCurrentConnection()
    {
        return;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.Favorites;
    }

    public void loadAnotherPage()
    {
        this.cancelCurrentConnection();
        if (!this.isNewSearch) {
            this.currentPage = (this.currentPage + 1);
        } else {
            this.currentPage = this.pageOffset;
        }
        this.isLoading = 1;
        this.lastPageCount = 0;
        this.executor.execute(new com.bisimplex.firebooru.network.SourceFavorites$$ExternalSyntheticLambda2(this, this.getQuery(), this.currentPage));
        return;
    }

    public void onFailure(okhttp3.Call p2, java.io.IOException p3)
    {
        if (this.favoriteCall != p2) {
            super.onFailure(p2, p3);
            return;
        } else {
            this.favoriteCall = 0;
            if (this.favoriteSyncListener != null) {
                this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourceFavorites$$ExternalSyntheticLambda1(this));
            }
            return;
        }
    }

    public void onResponse(okhttp3.Call p2, okhttp3.Response p3)
    {
        if (this.favoriteCall != p2) {
            super.onResponse(p2, p3);
            return;
        } else {
            this.favoriteCall = 0;
            if (this.favoriteSyncListener != null) {
                this.enqueueHandler.post(new com.bisimplex.firebooru.network.SourceFavorites$$ExternalSyntheticLambda3(this, p3));
            }
            return;
        }
    }

    public void syncFavorite(com.bisimplex.firebooru.danbooru.DanbooruPost p8, com.bisimplex.firebooru.network.FavoriteSyncListener p9)
    {
        okhttp3.Request v0_0 = 0;
        this.favoriteSyncListener = 0;
        if (this.provider != null) {
            okhttp3.Request$Builder v1_4 = this.provider.generateUrlForAddFavorite(p8);
            if (v1_4 != null) {
                okhttp3.OkHttpClient v2 = com.bisimplex.firebooru.network.HttpClient.getOkHttpClient();
                okhttp3.MediaType v3_1 = okhttp3.MediaType.parse("application/json; charset=utf-8");
                okhttp3.Request$Builder v1_2 = new okhttp3.Request$Builder().url(v1_4).header("User-Agent", this.provider.getUserAgent());
                com.google.gson.JsonObject v4_8 = this.provider.getServerDescription().getType();
                if (v4_8 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru2) {
                    if (v4_8 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) {
                        if (v4_8 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621) {
                            if (v4_8 != com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDerpibooru) {
                                if (v4_8 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeHydrus) {
                                    com.google.gson.JsonObject v4_10 = new com.google.gson.JsonObject();
                                    v4_10.add("hash", new com.google.gson.JsonPrimitive(p8.getMd5()));
                                    v4_10.add("rating_service_key", new com.google.gson.JsonPrimitive(this.getRating_service_key()));
                                    if (!p8.isFavorite()) {
                                        v4_10.add("rating", 0);
                                    } else {
                                        v4_10.add("rating", new com.google.gson.JsonPrimitive(Boolean.valueOf(p8.isFavorite())));
                                    }
                                    v4_10.add("Hydrus-Client-API-Access-Key", new com.google.gson.JsonPrimitive(this.provider.getServerDescription().getApiKey()));
                                    v0_0 = v1_2.post(okhttp3.RequestBody.create(v4_10.toString(), v3_1)).build();
                                }
                            } else {
                                if (!p8.isFavorite()) {
                                    v0_0 = v1_2.delete().build();
                                } else {
                                    v0_0 = v1_2.post(okhttp3.RequestBody.create("{\"_method\":\"POST\"}", v3_1)).build();
                                }
                            }
                        } else {
                            if (!p8.isFavorite()) {
                                v0_0 = v1_2.delete().build();
                            } else {
                                v0_0 = v1_2.post(okhttp3.RequestBody.create(String.format("{\"post_id\":%s}", new Object[] {p8.getPostId()})), v3_1)).build();
                            }
                        }
                    } else {
                        okhttp3.Request$Builder v8_7;
                        okhttp3.Request v0_2 = com.bisimplex.firebooru.network.HttpClient.getGson();
                        com.google.gson.JsonObject v4_1 = new java.util.HashMap();
                        v4_1.put("id", p8.getPostId());
                        if (!p8.isFavorite()) {
                            v8_7 = "2";
                        } else {
                            v8_7 = "3";
                        }
                        v4_1.put("score", v8_7);
                        v4_1.put("login", this.provider.getServerDescription().getUserName());
                        v4_1.put("password_hash", this.provider.getServerDescription().getPasswordKey());
                        v0_0 = v1_2.post(okhttp3.RequestBody.create(v0_2.toJson(v4_1), v3_1)).build();
                    }
                } else {
                    if (!p8.isFavorite()) {
                        v0_0 = v1_2.delete().build();
                    } else {
                        v0_0 = v1_2.post(okhttp3.RequestBody.create("{}", v3_1)).build();
                    }
                }
                if (v0_0 == null) {
                    p9.favoriteSyncComplete(this, 0);
                    return;
                } else {
                    this.favoriteSyncListener = p9;
                    okhttp3.Request$Builder v8_23 = v2.newCall(v0_0);
                    this.favoriteCall = v8_23;
                    v8_23.enqueue(this);
                    return;
                }
            }
        }
        return;
    }
}
