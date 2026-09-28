package com.bisimplex.firebooru.network;
public abstract class ParserPosts extends com.bisimplex.firebooru.network.Parser {
    public static final String supportedExtensions = " jpg jpeg png gif mp4 webm webp ";
    protected String baseUrl;
    private final boolean includeBlacklisted;
    protected int removedCount;
    private String serverMessage;

    public ParserPosts(com.bisimplex.firebooru.network.ParserParams p1)
    {
        super(p1);
        if (super.provider == null) {
            super.baseUrl = "";
        } else {
            super.baseUrl = super.provider.getServerDescription().getUrl();
        }
        super.includeBlacklisted = super.params.getQuery().isIncludeBlacklisted();
        return;
    }

    private String addTimeParameterToURL(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            return android.net.Uri.parse(p3).buildUpon().appendQueryParameter("t", String.valueOf(System.currentTimeMillis())).toString();
        } else {
            return p3;
        }
    }

    protected void checkPostVisibility()
    {
        if ((com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().hideFavorites()) && (!this.params.isDisableVisibilityChecks())) {
            java.util.Iterator v0_2 = new java.util.ArrayList(this.data).iterator();
            while (v0_2.hasNext()) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v1_3 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v0_2.next());
                if (v1_3.isFavorite()) {
                    this.data.remove(v1_3);
                }
            }
        }
        return;
    }

    protected void checkPostsFavorited()
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper v0 = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance();
        java.util.Iterator v1_1 = this.data.iterator();
        while (v1_1.hasNext()) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v2_1 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v1_1.next());
            v2_1.setFavorite(v0.getIsFavByPost(v2_1.getPostId(), v2_1.getMd5()));
            if (v2_1.isFavorite()) {
                v0.updateFavoriteItem(v2_1);
            }
        }
        return;
    }

    protected void fixURLSForPolish(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if (p3.getFile() != null) {
            p3.getFile().setUrl(this.addTimeParameterToURL(p3.getFile().getUrl()));
        }
        if ((p3.getSample() != null) && (p3.getSample() != p3.getFile())) {
            p3.getSample().setUrl(this.addTimeParameterToURL(p3.getSample().getUrl()));
        }
        return;
    }

    public int getRemovedCount()
    {
        return this.removedCount;
    }

    public String getServerMessage()
    {
        return this.serverMessage;
    }

    protected boolean isIcludeBlacklisted()
    {
        return this.includeBlacklisted;
    }

    public boolean isSupportedExtension(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return " jpg jpeg png gif mp4 webm webp ".contains(p2);
        } else {
            return 0;
        }
    }

    protected void parseFinished()
    {
        if (com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().hideFavorites()) {
            this.checkPostsFavorited();
            this.checkPostVisibility();
        }
        return;
    }

    public void setServerMessage(String p1)
    {
        this.serverMessage = p1;
        return;
    }

    protected boolean shouldAddToResults(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        if ((this.provider.isBlacklisted(p3)) && ((!this.isIcludeBlacklisted()) && (!this.params.isDisableVisibilityChecks()))) {
            int v3_2 = 0;
        } else {
            v3_2 = 1;
        }
        if (v3_2 == 0) {
            this.removedCount = (this.removedCount + 1);
        }
        return v3_2;
    }
}
