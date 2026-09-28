package com.bisimplex.firebooru.data;
public class HomeItem {
    public static final int GROUP = 2;
    public static final int LOGO = 0;
    public static final int SUBLIST = 1;
    public static final int SUBLIST_COLLAPSED = 4;
    public static final int TITLE = 3;
    private boolean expanded;
    private boolean needsReload;
    private int scrollToIndex;
    private com.bisimplex.firebooru.network.SourcePostBasic source;
    private com.bisimplex.firebooru.model.SourceSpecs specs;
    private String title;
    private int type;

    public HomeItem()
    {
        this.setType(0);
        return;
    }

    public HomeItem(com.bisimplex.firebooru.model.SourceSpecs p4)
    {
        this.setSpecs(p4);
        int v1 = 1;
        if (p4.getType() != 4) {
            this.setType(1);
            if ((p4.getQuery() == null) || (p4.getProvider() == null)) {
                this.setType(4);
            }
        } else {
            this.setType(2);
        }
        if ((!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isAutoLoadPins()) && (com.bisimplex.firebooru.network.SourceFactory.getInstance().getSource(com.bisimplex.firebooru.network.SourceType.Permanent, p4.getKey()) == null)) {
            v1 = 0;
        }
        this.setExpanded(v1);
        return;
    }

    public HomeItem(String p1)
    {
        this.setTitle(p1);
        this.setType(3);
        return;
    }

    public int getScrollToIndex()
    {
        return this.scrollToIndex;
    }

    public com.bisimplex.firebooru.network.SourcePostBasic getSource()
    {
        if ((this.source == null) && (this.getType() != 2)) {
            this.source = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSourceForSpecs(this.specs);
            int v0_5 = ((int) this.specs.getQuery().getInitialPage());
            if (v0_5 > 0) {
                this.source.setPageOffset(v0_5);
            }
        }
        return this.source;
    }

    public com.bisimplex.firebooru.model.SourceSpecs getSpecs()
    {
        return this.specs;
    }

    public String getTitle()
    {
        return this.title;
    }

    public int getType()
    {
        int v0_0 = this.type;
        if (v0_0 == 1) {
            if (!this.expanded) {
                v0_0 = 4;
            } else {
                return 1;
            }
        }
        return v0_0;
    }

    public boolean isExpanded()
    {
        return this.expanded;
    }

    public boolean isNeedsReload()
    {
        return this.needsReload;
    }

    public void setExpanded(boolean p1)
    {
        this.expanded = p1;
        return;
    }

    public void setNeedsReload(boolean p1)
    {
        this.needsReload = p1;
        return;
    }

    public void setScrollToIndex(int p1)
    {
        this.scrollToIndex = p1;
        return;
    }

    public void setSource(com.bisimplex.firebooru.network.SourcePostBasic p1)
    {
        this.source = p1;
        return;
    }

    public void setSpecs(com.bisimplex.firebooru.model.SourceSpecs p1)
    {
        this.specs = p1;
        return;
    }

    public void setTitle(String p1)
    {
        this.title = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }
}
