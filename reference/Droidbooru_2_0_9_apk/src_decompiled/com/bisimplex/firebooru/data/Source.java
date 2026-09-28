package com.bisimplex.firebooru.data;
public class Source {
    protected com.bisimplex.firebooru.data.SearchListener callbackListener;
    protected boolean canBePurged;
    protected com.bisimplex.firebooru.data.Query currentFilter;
    protected int currentPageLoaded;
    protected int currentVisibleIndex;
    protected boolean isLastPage;
    protected boolean isLoading;
    protected boolean isNewSearch;
    protected java.util.ArrayList posts;
    protected com.bisimplex.firebooru.danbooru.BooruProvider provider;
    protected java.util.regex.Pattern thresholdPattern;

    public Source()
    {
        this.thresholdPattern = java.util.regex.Pattern.compile("threshold:\\d*", 2);
        this.posts = new java.util.ArrayList();
        this.provider = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
        this.canBePurged = 1;
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

    protected String generateRequestURL(int p1)
    {
        return 0;
    }

    public boolean getCanBePurged()
    {
        return this.canBePurged;
    }

    public int getCurrentVisibleIndex()
    {
        return this.currentVisibleIndex;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getCurrentVisiblePost()
    {
        return this.visiblePost();
    }

    public com.bisimplex.firebooru.data.Query getFilter()
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

    public boolean getIsNewSearch()
    {
        return this.isNewSearch;
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

    public int getThreshold()
    {
        int v0_0 = this.currentFilter;
        if (v0_0 != 0) {
            int v0_6 = this.thresholdPattern.matcher(v0_0.getText());
            if (!v0_6.find()) {
                return 0;
            } else {
                return Integer.parseInt(v0_6.group().split(":")[1]);
            }
        } else {
            return 0;
        }
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

    public void loadAnOtherPage(com.bisimplex.firebooru.data.SearchListener p1)
    {
        this.setCallbackListener(p1);
        return;
    }

    public void setCallbackListener(com.bisimplex.firebooru.data.SearchListener p1)
    {
        this.callbackListener = p1;
        return;
    }

    public void setCanBePurged(boolean p1)
    {
        this.canBePurged = p1;
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

    public void setFilter(com.bisimplex.firebooru.data.Query p1)
    {
        this.currentFilter = p1;
        return;
    }

    public void setInitialPage(int p2)
    {
        this.currentPageLoaded = ((p2 + com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getInitialPageNumber()) - 1);
        return;
    }

    public void setThreshold(int p3)
    {
        if (this.currentFilter == null) {
            this.currentFilter = new com.bisimplex.firebooru.data.Query();
        }
        String v3_2;
        if (p3 <= null) {
            v3_2 = "";
        } else {
            v3_2 = String.format("threshold:%d", new Object[] {Integer.valueOf(p3)}));
        }
        String v3_4;
        String v0_3 = this.thresholdPattern.matcher(this.currentFilter.getText());
        if (!v0_3.find()) {
            if (v3_2.length() <= 0) {
                v3_4 = this.currentFilter.getText();
            } else {
                v3_4 = String.format("%s %s", new Object[] {this.currentFilter.getText(), v3_2}));
            }
        } else {
            v3_4 = v0_3.replaceFirst(v3_2);
        }
        this.setFilter(new com.bisimplex.firebooru.data.Query(v3_4, this.currentFilter.getFilterEnabled()));
        return;
    }
}
