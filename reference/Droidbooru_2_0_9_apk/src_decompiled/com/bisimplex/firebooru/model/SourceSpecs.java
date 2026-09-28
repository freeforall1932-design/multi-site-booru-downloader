package com.bisimplex.firebooru.model;
public class SourceSpecs {
    public static final int FAVORITE = 1;
    public static final int GROUP = 4;
    public static final int HISTORY = 2;
    public static final int NONE = 0;
    public static final int SEARCH = 3;
    private java.util.List childs;
    private String key;
    private transient com.bisimplex.firebooru.danbooru.BooruProvider provider;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private transient com.bisimplex.firebooru.danbooru.ServerItem server;
    private int type;
    private String url;

    public SourceSpecs()
    {
        return;
    }

    public static com.bisimplex.firebooru.model.SourceSpecs fromSource(com.bisimplex.firebooru.network.Source p3)
    {
        com.bisimplex.firebooru.model.SourceSpecs v0_1 = new com.bisimplex.firebooru.model.SourceSpecs();
        v0_1.setKey(java.util.UUID.randomUUID().toString());
        v0_1.setProvider(p3.getProvider());
        com.bisimplex.firebooru.danbooru.ServerItem v1_1 = com.bisimplex.firebooru.model.SourceSpecs$1.$SwitchMap$com$bisimplex$firebooru$network$SourceType[p3.getType().ordinal()];
        if (v1_1 == 1) {
            v0_1.setType(1);
        } else {
            if (v1_1 == 2) {
                v0_1.setType(2);
            } else {
                v0_1.setType(3);
            }
        }
        v0_1.setUrl(p3.getProvider().getServerDescription().getUrl());
        v0_1.setServer(p3.getProvider().getServerDescription());
        v0_1.setQuery(p3.getQuery());
        return v0_1;
    }

    public int getChildCount()
    {
        int v0_0 = this.childs;
        if (v0_0 != 0) {
            return v0_0.size();
        } else {
            return 0;
        }
    }

    public java.util.List getChilds()
    {
        return this.childs;
    }

    public String getKey()
    {
        return this.key;
    }

    public com.bisimplex.firebooru.danbooru.BooruProvider getProvider()
    {
        if (this.provider == null) {
            com.bisimplex.firebooru.danbooru.BooruProvider v0_1 = this.getServer();
            if (v0_1 != null) {
                this.provider = com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(v0_1);
            }
        }
        return this.provider;
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        return this.query;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServer()
    {
        if ((this.server == null) && ((!android.text.TextUtils.isEmpty(this.url)) && (!this.url.contains(" ")))) {
            this.server = com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getServerByUrl(com.bisimplex.firebooru.danbooru.BooruProvider.stringToURL(this.url));
        }
        return this.server;
    }

    public String getTitle()
    {
        String v0_0 = this.query;
        if ((v0_0 == null) || (android.text.TextUtils.isEmpty(v0_0.getTitle()))) {
            return "";
        } else {
            return this.query.getTitle();
        }
    }

    public int getType()
    {
        return this.type;
    }

    public String getUrl()
    {
        return this.url;
    }

    public boolean isEqualTo(com.bisimplex.firebooru.model.SourceSpecs p4)
    {
        if (p4 != 0) {
            if ((!android.text.TextUtils.equals(this.url, p4.url)) || ((this.type != p4.type) || (!this.query.isEqualTo(p4.getQuery())))) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void setChilds(java.util.List p1)
    {
        this.childs = p1;
        return;
    }

    public void setKey(String p1)
    {
        this.key = p1;
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
        return;
    }

    public void setServer(com.bisimplex.firebooru.danbooru.ServerItem p1)
    {
        this.server = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }

    public void setUrl(String p1)
    {
        this.url = p1;
        return;
    }

    public String toString()
    {
        String v0_0 = this.query;
        if ((v0_0 == null) || (android.text.TextUtils.isEmpty(v0_0.getTitle()))) {
            return new StringBuilder("SourceSpecs{key=\'").append(this.key).append("\', query=").append(this.query).append(", url=\'").append(this.url).append("\', type=").append(this.type).append(", childs=").append(this.childs).append(", server=").append(this.server).append(", provider=").append(this.provider).append(125).toString();
        } else {
            return this.query.getTitle();
        }
    }
}
