package com.bisimplex.firebooru.services;
public class BooruTagHelper {
    public static final int TAG_AMBIGUOUS = 2;
    public static final int TAG_ARTIST = 1;
    public static final int TAG_CHARACTER = 4;
    public static final int TAG_COPY = 3;
    public static final int TAG_GENERAL = 0;
    public static final int TAG_HISTORY = 7;
    public static final int TAG_META = 5;
    private static final com.bisimplex.firebooru.services.BooruTagHelper ourInstance;
    private final io.objectbox.Box booruTagBox;
    private final android.os.Handler enqueueHandler;
    private final java.util.concurrent.Executor executor;
    private final io.objectbox.BoxStore store;

    public static synthetic void $r8$lambda$U4A2f7MZ0lEMA10xje3U6jiojoU(com.bisimplex.firebooru.services.BooruTagHelper p0, com.bisimplex.firebooru.danbooru.BooruProvider p1, String[] p2, com.bisimplex.firebooru.danbooru.DanbooruPost p3, ref.WeakReference p4)
    {
        p0.lambda$processTagsAsync$3(p1, p2, p3, p4);
        return;
    }

    public static synthetic void $r8$lambda$XxuUBbDLhsywJKIdMth1FBkKY5Y(com.bisimplex.firebooru.services.BooruTagHelper p0, java.util.List p1)
    {
        p0.lambda$feedWithSourceAsync$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$p2Jjqgosp1iScSSdD9eV1wadKqY(com.bisimplex.firebooru.services.BooruTagHelper p0, com.bisimplex.firebooru.danbooru.DanbooruPost p1, ref.WeakReference p2, com.bisimplex.firebooru.danbooru.BooruProvider p3)
    {
        p0.lambda$processTagsAsync$4(p1, p2, p3);
        return;
    }

    public static synthetic void $r8$lambda$x_lEUdf6buqMx07LdCGAGZLEa5k(com.bisimplex.firebooru.services.BooruTagHelper p0, java.util.List p1)
    {
        p0.lambda$feedWithSource$0(p1);
        return;
    }

    static BooruTagHelper()
    {
        com.bisimplex.firebooru.services.BooruTagHelper.ourInstance = new com.bisimplex.firebooru.services.BooruTagHelper();
        return;
    }

    private BooruTagHelper()
    {
        this.enqueueHandler = androidx.core.os.HandlerCompat.createAsync(android.os.Looper.getMainLooper());
        this.executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        io.objectbox.Box v0_0 = com.bisimplex.firebooru.model.ObjectBox.get();
        this.store = v0_0;
        this.booruTagBox = v0_0.boxFor(com.bisimplex.firebooru.model.BooruTag);
        return;
    }

    private void addTagsStringToMap(java.util.HashMap p5, String p6, int p7)
    {
        String[] v6_1 = this.spaceSeparatedToArray(p6);
        int v0 = v6_1.length;
        int v1 = 0;
        while (v1 < v0) {
            String v2 = v6_1[v1];
            if ((!android.text.TextUtils.isEmpty(v2)) && (((String) p5.get(v2)) == null)) {
                p5.put(v2, String.valueOf(p7));
                this.storeTagIfNeeded(v2, p7);
            }
            v1++;
        }
        return;
    }

    private void convertList(java.util.List p4, java.util.List p5)
    {
        java.util.Iterator v5_1 = p5.iterator();
        while (v5_1.hasNext()) {
            int v0_3 = ((com.bisimplex.firebooru.model.BooruTag) v5_1.next());
            com.bisimplex.firebooru.danbooru.TagItem v1_0 = new com.bisimplex.firebooru.danbooru.TagItem();
            v1_0.setName(v0_3.getName());
            v1_0.setType(v0_3.getType());
            p4.add(v1_0);
        }
        return;
    }

    public static com.bisimplex.firebooru.services.BooruTagHelper getInstance()
    {
        return com.bisimplex.firebooru.services.BooruTagHelper.ourInstance;
    }

    private synthetic void lambda$feedWithSource$0(java.util.List p3)
    {
        java.util.Iterator v3_1 = p3.iterator();
        while (v3_1.hasNext()) {
            int v0_3 = ((com.bisimplex.firebooru.danbooru.TagItem) v3_1.next());
            this.storeTagIfNeeded(v0_3.getName(), v0_3.getType());
        }
        return;
    }

    private synthetic void lambda$feedWithSourceAsync$1(java.util.List p7)
    {
        String v0_1 = new java.util.HashMap();
        long v1_1 = System.nanoTime();
        String v7_4 = p7.iterator();
        while (v7_4.hasNext()) {
            long v3_4 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v7_4.next());
            this.addTagsStringToMap(v0_1, v3_4.getTag_artist(), 1);
            this.addTagsStringToMap(v0_1, v3_4.getTag_character(), 4);
            this.addTagsStringToMap(v0_1, v3_4.getTag_copyright(), 3);
            this.addTagsStringToMap(v0_1, v3_4.getTag_meta(), 5);
            this.addTagsStringToMap(v0_1, v3_4.getTag_general(), 0);
        }
        android.util.Log.i("BooruTag", String.format("work time %d. Map size %d", new Object[] {Long.valueOf(((System.nanoTime() - v1_1) / 1000000)), Integer.valueOf(v0_1.size())})));
        return;
    }

    static synthetic void lambda$processTagsAsync$2(ref.WeakReference p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if (p1.get() != null) {
            ((com.bisimplex.firebooru.services.BooruTagListener) p1.get()).finishedLoading(p2);
        }
        return;
    }

    private synthetic void lambda$processTagsAsync$3(com.bisimplex.firebooru.danbooru.BooruProvider p15, String[] p16, com.bisimplex.firebooru.danbooru.DanbooruPost p17, ref.WeakReference p18)
    {
        ref.WeakReference v2_3 = new java.util.ArrayList();
        io.objectbox.query.Query v3_1 = this.booruTagBox.query().equal(com.bisimplex.firebooru.model.BooruTag_.name, "", io.objectbox.query.QueryBuilder$StringOrder.CASE_INSENSITIVE).build();
        int v6_0 = 0;
        if (!p15.canRequestTagMetadata()) {
            String[] v15_7 = p16.length;
            String v4_3 = 0;
            while (v4_3 < v15_7) {
                com.bisimplex.firebooru.danbooru.TagItem v7_2 = p16[v4_3];
                if (!v7_2.isEmpty()) {
                    int v8_5 = ((com.bisimplex.firebooru.model.BooruTag) v3_1.setParameter(com.bisimplex.firebooru.model.BooruTag_.name, v7_2).findFirst());
                    int v9_2 = new com.bisimplex.firebooru.danbooru.TagItem();
                    if (v8_5 != 0) {
                        v9_2.setType(v8_5.getType());
                        v9_2.setName(v8_5.getName());
                    } else {
                        v9_2.setType(0);
                        v9_2.setName(v7_2);
                    }
                    v2_3.add(v9_2);
                }
                v4_3++;
            }
        } else {
            String v4_5 = new StringBuilder();
            com.bisimplex.firebooru.danbooru.TagItem v7_5 = p16.length;
            int v8_0 = 0;
            int v9_0 = 0;
            while (v8_0 < v7_5) {
                String v12 = p16[v8_0];
                if (!v12.isEmpty()) {
                    com.bisimplex.firebooru.model.BooruTag v13_4 = ((com.bisimplex.firebooru.model.BooruTag) v3_1.setParameter(com.bisimplex.firebooru.model.BooruTag_.name, v12).findFirst());
                    if (v13_4 != null) {
                        com.bisimplex.firebooru.danbooru.TagItem v10_1 = new com.bisimplex.firebooru.danbooru.TagItem();
                        v10_1.setName(v13_4.getName());
                        v10_1.setType(v13_4.getType());
                        v2_3.add(v10_1);
                    } else {
                        if (v9_0 >= 100) {
                            com.bisimplex.firebooru.danbooru.TagItem v10_3 = new com.bisimplex.firebooru.danbooru.TagItem();
                            v10_3.setName(v12);
                            v10_3.setType(0);
                            v2_3.add(v10_3);
                        } else {
                            v4_5.append(v12);
                            v4_5.append(" ");
                        }
                        v9_0++;
                    }
                }
                v8_0++;
            }
            android.util.Log.i("BooruTag", String.format("Total tags %d", new Object[] {Integer.valueOf(p16.length)})));
            if (v9_0 > 0) {
                int v0_18 = new com.bisimplex.firebooru.network.SourceQuery(v4_5.toString().trim());
                android.util.Log.i("BooruTag", String.format("Requesting %d tags: %s", new Object[] {Integer.valueOf(v9_0), v0_18.getText()})));
                String v4_13 = new com.bisimplex.firebooru.network.SourceBooruTag(p15, 100, v0_18);
                v4_13.loadAnotherPageSync();
                String[] v15_8 = v4_13.getData();
                android.util.Log.i("BooruTag", String.format("Tags from server: %d", new Object[] {Integer.valueOf(v15_8.size())})));
                if (v15_8.size() <= 0) {
                    String[] v15_1 = v0_18.getText().split(" ");
                    int v0_1 = v15_1.length;
                    while (v6_0 < v0_1) {
                        String v4_0 = v15_1[v6_0];
                        com.bisimplex.firebooru.danbooru.TagItem v7_1 = new com.bisimplex.firebooru.danbooru.TagItem();
                        v7_1.setName(v4_0);
                        v2_3.add(v7_1);
                        v6_0++;
                    }
                } else {
                    v2_3.addAll(v4_13.getData());
                    String[] v15_2 = v15_8.iterator();
                    while (v15_2.hasNext()) {
                        int v0_11 = ((com.bisimplex.firebooru.danbooru.TagItem) v15_2.next());
                        this.storeTagIfNeeded(v0_11.getName(), v0_11.getType());
                    }
                }
            }
        }
        String[] v15_5 = new com.bisimplex.firebooru.services.BooruTagSorter().sortTags(v2_3);
        android.util.Log.i("BooruTag", String.format("Tags from ordered: %d", new Object[] {Integer.valueOf(v15_5.size())})));
        p17.setSeparateTags(v15_5);
        v3_1.close();
        this.enqueueHandler.post(new com.bisimplex.firebooru.services.BooruTagHelper$$ExternalSyntheticLambda0(p18, p17));
        return;
    }

    private synthetic void lambda$processTagsAsync$4(com.bisimplex.firebooru.danbooru.DanbooruPost p8, ref.WeakReference p9, com.bisimplex.firebooru.danbooru.BooruProvider p10)
    {
        String[] v4 = this.spaceSeparatedToArray(p8.getTags().trim());
        if (v4.length != 0) {
            this.store.runInTx(new com.bisimplex.firebooru.services.BooruTagHelper$$ExternalSyntheticLambda1(this, p10, v4, p8, p9));
            return;
        } else {
            if (p9.get() != null) {
                ((com.bisimplex.firebooru.services.BooruTagListener) p9.get()).finishedLoading(p8);
            }
            return;
        }
    }

    private String[] spaceSeparatedToArray(String p2)
    {
        if (!android.text.TextUtils.isEmpty(p2)) {
            return p2.split(" ");
        } else {
            String[] v2_3 = new String[0];
            return v2_3;
        }
    }

    private void storeTagIfNeeded(String p4, int p5)
    {
        com.bisimplex.firebooru.model.BooruTag v0_5 = this.booruTagBox.query().equal(com.bisimplex.firebooru.model.BooruTag_.name, p4, io.objectbox.query.QueryBuilder$StringOrder.CASE_INSENSITIVE).build();
        com.bisimplex.firebooru.model.BooruTag v1_1 = ((com.bisimplex.firebooru.model.BooruTag) v0_5.findFirst());
        v0_5.close();
        if (v1_1 != null) {
            if ((v1_1.getType() != p5) && ((p5 != 0) && (p5 != 7))) {
                if (p5 != 2) {
                    v1_1.setType(p5);
                } else {
                    v1_1.setType(0);
                }
                this.booruTagBox.put(v1_1);
            }
            return;
        } else {
            com.bisimplex.firebooru.model.BooruTag v0_3 = new com.bisimplex.firebooru.model.BooruTag();
            v0_3.setName(p4);
            v0_3.setType(p5);
            this.booruTagBox.put(v0_3);
            return;
        }
    }

    public void feedWithSource(java.util.List p3)
    {
        this.store.runInTx(new com.bisimplex.firebooru.services.BooruTagHelper$$ExternalSyntheticLambda4(this, p3));
        return;
    }

    public void feedWithSourceAsync(java.util.List p3)
    {
        if (!p3.isEmpty()) {
            this.store.runInTxAsync(new com.bisimplex.firebooru.services.BooruTagHelper$$ExternalSyntheticLambda2(this, p3), 0);
            return;
        } else {
            return;
        }
    }

    public void processTagsAsync(com.bisimplex.firebooru.danbooru.DanbooruPost p3, com.bisimplex.firebooru.danbooru.BooruProvider p4, com.bisimplex.firebooru.services.BooruTagListener p5)
    {
        if ((p3 != null) && (!p3.hasCalculatedTags())) {
            if (p4 == null) {
                p4 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstanceByUrl(p3.getPostUrl(), 1);
            }
            if (p4 != null) {
                this.executor.execute(new com.bisimplex.firebooru.services.BooruTagHelper$$ExternalSyntheticLambda3(this, p3, new ref.WeakReference(p5), p4));
                return;
            } else {
                p5.finishedLoading(p3);
                return;
            }
        } else {
            p5.finishedLoading(p3);
            return;
        }
    }

    public java.util.List tagsForAutocomplete(String p6)
    {
        java.util.ArrayList v0_1 = new java.util.ArrayList();
        if (!android.text.TextUtils.isEmpty(p6)) {
            java.util.List v6_3 = this.booruTagBox.query().startsWith(com.bisimplex.firebooru.model.BooruTag_.name, p6, io.objectbox.query.QueryBuilder$StringOrder.CASE_INSENSITIVE).build().find(0, 8);
            if (v6_3.size() > 0) {
                this.convertList(v0_1, v6_3);
            }
        }
        return v0_1;
    }
}
