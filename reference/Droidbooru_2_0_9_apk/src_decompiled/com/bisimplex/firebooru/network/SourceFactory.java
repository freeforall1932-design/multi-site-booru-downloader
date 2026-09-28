package com.bisimplex.firebooru.network;
public class SourceFactory {
    private static final com.bisimplex.firebooru.network.SourceFactory ourInstance;
    private java.util.Map sources;

    static SourceFactory()
    {
        com.bisimplex.firebooru.network.SourceFactory.ourInstance = new com.bisimplex.firebooru.network.SourceFactory();
        return;
    }

    private SourceFactory()
    {
        this.sources = new java.util.HashMap();
        return;
    }

    private com.bisimplex.firebooru.danbooru.BooruProvider createSeletedProvider()
    {
        return com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().getSelectedServer());
    }

    public static com.bisimplex.firebooru.network.SourceFactory getInstance()
    {
        return com.bisimplex.firebooru.network.SourceFactory.ourInstance;
    }

    private declared_synchronized java.util.Map getListByType(com.bisimplex.firebooru.network.SourceType p3)
    {
        if (this.sources.containsKey(p3)) {
            return ((java.util.Map) this.sources.get(p3));
        } else {
            java.util.Map v0_2 = new java.util.HashMap();
            this.sources.put(p3, v0_2);
            return v0_2;
        }
    }

    public void cookiesUpdatedFor(okhttp3.HttpUrl p5)
    {
        java.util.Iterator v0_2 = this.getListByType(com.bisimplex.firebooru.network.SourceType.Permanent);
        if ((v0_2 != null) && (!v0_2.isEmpty())) {
            java.util.Iterator v0_1 = v0_2.values().iterator();
            while (v0_1.hasNext()) {
                com.bisimplex.firebooru.network.Source v1_2 = ((com.bisimplex.firebooru.network.Source) v0_1.next());
                boolean v2_2 = ((com.bisimplex.firebooru.network.SourcePostBasic) v1_2).getProvider();
                if (v2_2) {
                    boolean v2_3 = v2_2.getServerDescription();
                    if ((v2_3) && (v2_3.getRealURL().getHost().equalsIgnoreCase(p5.host()))) {
                        v1_2.reset();
                    }
                }
            }
        }
        return;
    }

    public com.bisimplex.firebooru.network.Source copySource(com.bisimplex.firebooru.network.Source p4)
    {
        return this.createSource(p4.getType(), com.bisimplex.firebooru.danbooru.BooruProvider.getInstance(p4.getProvider().getServerDescription()), new com.bisimplex.firebooru.network.SourceQuery(p4.getQuery()));
    }

    public com.bisimplex.firebooru.network.Source createSource(com.bisimplex.firebooru.network.SourceType p3)
    {
        return this.createSource(p3, this.createSeletedProvider(), new com.bisimplex.firebooru.network.SourceQuery());
    }

    public com.bisimplex.firebooru.network.Source createSource(com.bisimplex.firebooru.network.SourceType p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        return this.createSource(p2, p3, new com.bisimplex.firebooru.network.SourceQuery());
    }

    public com.bisimplex.firebooru.network.Source createSource(com.bisimplex.firebooru.network.SourceType p3, com.bisimplex.firebooru.danbooru.BooruProvider p4, com.bisimplex.firebooru.network.SourceQuery p5)
    {
        java.util.Map v0 = this.getListByType(p3);
        if (p4 == null) {
            p4 = this.createSeletedProvider();
        }
        if (p5 == null) {
            p5 = new com.bisimplex.firebooru.network.SourceQuery();
        }
        com.bisimplex.firebooru.network.SourceMultiPost v3_4;
        switch (com.bisimplex.firebooru.network.SourceFactory$1.$SwitchMap$com$bisimplex$firebooru$network$SourceType[p3.ordinal()]) {
            case 1:
                v3_4 = new com.bisimplex.firebooru.network.SourcePost(p4, 100, p5);
                break;
            case 2:
                v3_4 = new com.bisimplex.firebooru.network.SourceNote(p4, 0, p5);
                break;
            case 3:
                v3_4 = new com.bisimplex.firebooru.network.SourcePool(p4, 20, p5);
                break;
            case 4:
                v3_4 = new com.bisimplex.firebooru.network.SourceTag(p4, 8, p5);
                break;
            case 5:
                v3_4 = new com.bisimplex.firebooru.network.SourceBooruTag(p4, 100, p5);
                break;
            case 6:
                v3_4 = new com.bisimplex.firebooru.network.SourceFavorites(p4, 10000, p5);
                break;
            case 7:
                v3_4 = new com.bisimplex.firebooru.network.SourceHistory(p4, 100, p5);
                break;
            case 8:
                v3_4 = new com.bisimplex.firebooru.network.SourceMultiPost(p4, 100, p5);
                break;
            default:
                v3_4 = 0;
        }
        if (v3_4 != null) {
            v0.put(v3_4.getKey(), v3_4);
        }
        return v3_4;
    }

    public com.bisimplex.firebooru.network.Source createSource(com.bisimplex.firebooru.network.SourceType p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        return this.createSource(p2, this.createSeletedProvider(), p3);
    }

    public com.bisimplex.firebooru.network.SourcePostBasic createSourceForSpecs(com.bisimplex.firebooru.model.SourceSpecs p6)
    {
        com.bisimplex.firebooru.network.SourceType v0_1 = this.getListByType(com.bisimplex.firebooru.network.SourceType.Permanent);
        com.bisimplex.firebooru.network.SourcePost v1_10 = ((com.bisimplex.firebooru.network.Source) v0_1.get(p6.getKey()));
        if (v1_10 == null) {
            com.bisimplex.firebooru.network.SourcePost v1_2;
            com.bisimplex.firebooru.network.SourcePost v1_0 = p6.getType();
            if (v1_0 == 1) {
                v1_2 = this.createSource(com.bisimplex.firebooru.network.SourceType.Favorites, p6.getProvider(), p6.getQuery());
            } else {
                if (v1_0 == 2) {
                    v1_2 = this.createSource(com.bisimplex.firebooru.network.SourceType.History, p6.getProvider(), p6.getQuery());
                } else {
                    if (v1_0 == 3) {
                        v1_2 = ((com.bisimplex.firebooru.network.SourcePost) this.createSource(com.bisimplex.firebooru.network.SourceType.Post, p6.getProvider(), p6.getQuery()));
                        v1_2.setDisableHistory(1);
                    } else {
                        v1_2 = 0;
                    }
                }
            }
            v0_1.put(p6.getKey(), v1_2);
            return ((com.bisimplex.firebooru.network.SourcePostBasic) v1_2);
        } else {
            if ((v1_10.getType() == com.bisimplex.firebooru.network.SourceType.History) || (v1_10.getType() == com.bisimplex.firebooru.network.SourceType.Favorites)) {
                v1_10.reset();
            }
            return ((com.bisimplex.firebooru.network.SourcePostBasic) v1_10);
        }
    }

    public com.bisimplex.firebooru.network.Source getSource(com.bisimplex.firebooru.network.SourceType p1, String p2)
    {
        return ((com.bisimplex.firebooru.network.Source) this.getListByType(p1).get(p2));
    }

    public void removeAllSources()
    {
        com.bisimplex.firebooru.network.SourceType[] v0 = com.bisimplex.firebooru.network.SourceType.values();
        int v1 = v0.length;
        int v2 = 0;
        while (v2 < v1) {
            java.util.Map v3_1 = v0[v2];
            if (v3_1 != com.bisimplex.firebooru.network.SourceType.Permanent) {
                java.util.Map v3_0 = this.getListByType(v3_1);
                java.util.Iterator v4_1 = v3_0.values().iterator();
                while (v4_1.hasNext()) {
                    com.bisimplex.firebooru.network.Source v5_2 = ((com.bisimplex.firebooru.network.Source) v4_1.next());
                    v5_2.cancelCurrentConnection();
                    v5_2.setListener(0);
                }
                v3_0.clear();
            }
            v2++;
        }
        return;
    }

    public void removeSource(com.bisimplex.firebooru.network.Source p3)
    {
        if (p3 != null) {
            java.util.Map v0_1 = this.getListByType(p3.getType());
            p3.setListener(0);
            p3.cancelCurrentConnection();
            v0_1.remove(p3.getKey());
            return;
        } else {
            return;
        }
    }

    public void removeSource(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        this.removeSource(p1);
        this.getListByType(com.bisimplex.firebooru.network.SourceType.Permanent).remove(p2.getKey());
        return;
    }
}
