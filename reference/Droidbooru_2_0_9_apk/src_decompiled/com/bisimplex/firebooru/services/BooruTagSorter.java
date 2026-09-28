package com.bisimplex.firebooru.services;
public class BooruTagSorter {
    private final com.bisimplex.firebooru.services.BooruTagSorter$TagNameComparator nameComparator;
    private java.util.ArrayList tagMatrix;

    public BooruTagSorter()
    {
        this.tagMatrix = new java.util.ArrayList();
        this.nameComparator = new com.bisimplex.firebooru.services.BooruTagSorter$TagNameComparator(this, java.text.Collator.getInstance(java.util.Locale.ENGLISH));
        int v1_0 = 0;
        while (v1_0 < 10) {
            this.tagMatrix.add(new java.util.ArrayList(0));
            v1_0++;
        }
        return;
    }

    private void cleanUpTags()
    {
        int v0 = 0;
        while (v0 < this.tagMatrix.size()) {
            ((java.util.List) this.tagMatrix.get(v0)).clear();
            v0++;
        }
        return;
    }

    private java.util.List jointTagMatrix()
    {
        int[] v0 = this.tagTypeOrderArray();
        int v2 = 0;
        java.util.ArrayList v1_1 = new java.util.ArrayList(0);
        while (v2 < v0.length) {
            java.util.List v3_1 = ((java.util.List) this.tagMatrix.get(v0[v2]));
            java.util.Collections.sort(v3_1, this.nameComparator);
            v1_1.addAll(v3_1);
            v2++;
        }
        return v1_1;
    }

    public java.util.List sortTags(java.util.List p5)
    {
        if (p5 != null) {
            int v0 = 0;
            while (v0 < p5.size()) {
                com.bisimplex.firebooru.danbooru.TagItem v1_1 = ((com.bisimplex.firebooru.danbooru.TagItem) p5.get(v0));
                ((java.util.List) this.tagMatrix.get(v1_1.getType())).add(v1_1);
                v0++;
            }
            java.util.List v5_3 = this.jointTagMatrix();
            this.cleanUpTags();
            return v5_3;
        } else {
            return new java.util.ArrayList();
        }
    }

    public java.util.List sortTagsAlphabetically(java.util.List p2)
    {
        if (p2 != null) {
            java.util.ArrayList v0_1 = new java.util.ArrayList(p2);
            java.util.Collections.sort(v0_1, this.nameComparator);
            return v0_1;
        } else {
            return new java.util.ArrayList();
        }
    }

    protected int[] tagTypeOrderArray()
    {
        return new int[] {1, 3, 4, 0, 5});
    }
}
