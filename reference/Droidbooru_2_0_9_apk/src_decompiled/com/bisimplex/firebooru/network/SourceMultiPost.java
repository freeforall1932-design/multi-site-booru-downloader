package com.bisimplex.firebooru.network;
public class SourceMultiPost extends com.bisimplex.firebooru.network.SourcePost implements com.bisimplex.firebooru.network.SourceListener {
    protected com.bisimplex.firebooru.network.SourceQuery iQuery;
    protected java.util.List pageMatrix;
    protected java.util.List sources;
    protected com.bisimplex.firebooru.danbooru.BooruProvider visibleProvider;

    public SourceMultiPost(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        super.sources = new java.util.ArrayList();
        super.pageMatrix = new java.util.ArrayList();
        return;
    }

    private void mixPageData()
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        com.bisimplex.firebooru.network.SourceListener v1_0 = 0;
        int v2_0 = 0;
        while (v2_0 < this.getPageSize()) {
            int v3_6 = this.pageMatrix.iterator();
            int v4 = 0;
            while (v3_6.hasNext()) {
                com.bisimplex.firebooru.danbooru.DanbooruPost v5_2 = ((java.util.List) v3_6.next());
                if (v2_0 < v5_2.size()) {
                    v0_1.add(((com.bisimplex.firebooru.danbooru.DanbooruPost) v5_2.get(v2_0)));
                    v4++;
                }
                this.setLastPageCount(v0_1.size());
                if (this.getLastPageCount() > 0) {
                    this.data.addAll(v0_1);
                    this.pageIndexes.add(Integer.valueOf(this.data.size()));
                    if (this.isNewSearch) {
                        this.setVisiblePostIndex(0);
                    }
                }
                this.isNewSearch = 0;
                if (this.getLastPageCount() == 0) {
                    v1_0 = 1;
                }
                this.isLastPage = v1_0;
                if (this.isLastPage) {
                    this.currentPage = (this.currentPage - 1);
                }
                this.pageMatrix.clear();
                if (this.getListener() != null) {
                    this.getListener().success(this, v0_1);
                }
                return;
            }
            if (v4 == 0) {
                break;
            }
            v2_0++;
        }
    }

    public void cancelCurrentConnection()
    {
        java.util.Iterator v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            ((com.bisimplex.firebooru.network.SourcePost) v0_1.next()).cancelCurrentConnection();
        }
        return;
    }

    public void cleanSource()
    {
        java.util.List v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.network.SourceFactory.getInstance().removeSource(((com.bisimplex.firebooru.network.SourcePost) v0_1.next()));
        }
        this.sources.clear();
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        if (this.getListener() != null) {
            this.getListener().failure(this, p2);
        }
        return;
    }

    public boolean getIsLoading()
    {
        int v0_2 = this.sources.iterator();
        while (v0_2.hasNext()) {
            if (((com.bisimplex.firebooru.network.SourcePost) v0_2.next()).getIsLoading()) {
                return 1;
            }
        }
        return 0;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_0 = this.visibleProvider;
        if (v0_0 == null) {
            return super.getProvider();
        } else {
            return v0_0;
        }
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider(com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        java.util.Iterator v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.network.SourcePost v1_1 = ((com.bisimplex.firebooru.network.SourcePost) v0_1.next());
            if (v1_1.getData().contains(p4)) {
                return v1_1.getProvider();
            }
        }
        return this.getProvider();
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        return this.iQuery;
    }

    public int getSourceCount()
    {
        return this.sources.size();
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.MultiPost;
    }

    public boolean isLastPage()
    {
        int v0_2 = this.sources.iterator();
        int v2 = 0;
        while (v0_2.hasNext()) {
            if (((com.bisimplex.firebooru.network.SourcePost) v0_2.next()).isLastPage()) {
                v2++;
            }
        }
        if (v2 != this.getSourceCount()) {
            return 0;
        } else {
            return 1;
        }
    }

    public void loadAnotherPage()
    {
        if (!this.isNewSearch) {
            this.currentPage = (this.currentPage + 1);
        } else {
            this.currentPage = this.pageOffset;
        }
        this.lastPageCount = 0;
        java.util.Iterator v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.network.SourcePost v1_2 = ((com.bisimplex.firebooru.network.SourcePost) v0_1.next());
            if ((!v1_2.isLastPage()) && (!v1_2.getIsLoading())) {
                v1_2.loadAnotherPage();
            }
        }
        return;
    }

    public void prepare(com.bisimplex.firebooru.network.SourceQuery p5, java.util.List p6)
    {
        this.iQuery = p5;
        if (p5.getInitialPage() > 0) {
            this.pageOffset = ((int) (p5.getInitialPage() - 1));
        }
        int v6_1 = p6.iterator();
        while (v6_1.hasNext()) {
            com.bisimplex.firebooru.network.SourcePost v1_2 = ((com.bisimplex.firebooru.network.SourcePost) com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Post, p5));
            v1_2.setProvider(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(((com.bisimplex.firebooru.danbooru.ServerItem) v6_1.next())));
            v1_2.setDisableHistory(1);
            this.sources.add(v1_2);
            v1_2.setListener(this);
        }
        if ((this.visibleProvider == null) && (!this.sources.isEmpty())) {
            this.visibleProvider = ((com.bisimplex.firebooru.network.SourcePost) this.sources.get(0)).getProvider();
        }
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    public void reset()
    {
        super.reset();
        this.pageMatrix.clear();
        this.visibleProvider = 0;
        return;
    }

    public void setPageOffset(int p3)
    {
        super.setPageOffset(p3);
        java.util.Iterator v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            ((com.bisimplex.firebooru.network.SourcePost) v0_1.next()).setPageOffset(p3);
        }
        return;
    }

    public void setQuery(com.bisimplex.firebooru.network.SourceQuery p3)
    {
        this.reset();
        java.util.Iterator v0_1 = this.sources.iterator();
        while (v0_1.hasNext()) {
            ((com.bisimplex.firebooru.network.SourcePost) v0_1.next()).setQuery(p3);
        }
        this.iQuery = p3;
        return;
    }

    public void setVisiblePostIndex(int p2)
    {
        super.setVisiblePostIndex(p2);
        if ((p2 < null) || (p2 >= this.data.size())) {
            this.visibleProvider = 0;
            return;
        } else {
            this.visibleProvider = this.getProvider(((com.bisimplex.firebooru.danbooru.DanbooruPost) this.data.get(p2)));
            return;
        }
    }

    public void success(com.bisimplex.firebooru.network.Source p1, java.util.List p2)
    {
        if ((p2 != null) && (!p2.isEmpty())) {
            this.pageMatrix.add(p2);
        }
        if (!this.getIsLoading()) {
            this.mixPageData();
        }
        return;
    }
}
