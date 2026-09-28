package com.bisimplex.firebooru.danbooru;
public class SourceProvider {
    protected com.bisimplex.firebooru.danbooru.TransactionAction callbackListener;
    protected String currentFilter;
    protected int currentPageLoaded;
    protected int currentVisibleIndex;
    protected boolean isLastPage;
    protected boolean isLoading;
    protected java.util.ArrayList posts;
    protected com.bisimplex.firebooru.danbooru.BooruProvider provider;

    public SourceProvider()
    {
        this.posts = new java.util.ArrayList();
        this.provider = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
        return;
    }

    public SourceProvider(com.bisimplex.firebooru.danbooru.BooruProvider p1)
    {
        this.provider = p1;
        return;
    }

    private com.bisimplex.firebooru.danbooru.DanbooruPost visiblePost()
    {
        com.bisimplex.firebooru.danbooru.DanbooruPost v0_0 = this.posts;
        if (v0_0 != null) {
            com.bisimplex.firebooru.danbooru.DanbooruPost v0_3 = v0_0.size();
            if (v0_3 != null) {
                int v2 = this.currentVisibleIndex;
                if (v2 < v0_3) {
                    return ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.posts.get(v2));
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    public void cleanPosts()
    {
        java.util.ArrayList v0 = this.posts;
        if (v0 != null) {
            v0.clear();
        }
        return;
    }

    public boolean existPostAt(int p2)
    {
        if ((p2 >= this.posts.size()) || (p2 < 0)) {
            return 0;
        } else {
            return 1;
        }
    }

    public int getCurrentVisibleIndex()
    {
        return this.currentVisibleIndex;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getCurrentVisiblePost()
    {
        return this.visiblePost();
    }

    public String getFilter()
    {
        return this.currentFilter;
    }

    public boolean getIsLastPage()
    {
        return this.isLastPage;
    }

    public boolean getIsLoading()
    {
        return this.isLoading;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getPostByIndex(int p2)
    {
        if (!this.existPostAt(p2)) {
            return 0;
        } else {
            return ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.posts.get(p2));
        }
    }

    public java.util.ArrayList getPosts()
    {
        return this.posts;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        return this.provider;
    }

    public int getTotalPostLoaded()
    {
        return this.posts.size();
    }

    public boolean isLastPost(com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        if (p4 != 0) {
            int v1_1 = this.posts;
            if ((v1_1 != 0) && (v1_1.indexOf(p4) == (this.posts.size() - 1))) {
                return 1;
            }
        }
        return 0;
    }

    public void loadAnOtherPage()
    {
        this.loadAnOtherPage(0);
        return;
    }

    public void loadAnOtherPage(com.bisimplex.firebooru.danbooru.TransactionAction p1)
    {
        this.setCallbackListener(p1);
        return;
    }

    public void replacePostAt(com.bisimplex.firebooru.danbooru.DanbooruPost p2, int p3)
    {
        java.util.ArrayList v0_0 = this.posts;
        if ((v0_0 != null) && (v0_0.size() > p3)) {
            this.posts.set(p3, p2);
        }
        return;
    }

    public void setCallbackListener(com.bisimplex.firebooru.danbooru.TransactionAction p1)
    {
        this.callbackListener = p1;
        return;
    }

    public void setCurrentVisibleIndex(int p3)
    {
        int v0_0 = this.posts;
        if ((v0_0 != 0) && (p3 >= 0)) {
            if (p3 <= (v0_0.size() - 1)) {
                this.currentVisibleIndex = p3;
                return;
            } else {
                this.currentVisibleIndex = 0;
                return;
            }
        } else {
            this.currentVisibleIndex = 0;
            return;
        }
    }

    public void setFilter(String p1)
    {
        this.currentFilter = p1;
        return;
    }

    public void setInitialPage(int p2)
    {
        this.currentPageLoaded = ((p2 + com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getInitialPageNumber()) - 1);
        return;
    }
}
