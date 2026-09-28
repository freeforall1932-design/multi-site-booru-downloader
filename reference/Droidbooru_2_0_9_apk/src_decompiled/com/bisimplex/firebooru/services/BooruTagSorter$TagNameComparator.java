package com.bisimplex.firebooru.services;
 class BooruTagSorter$TagNameComparator implements java.util.Comparator {
    private final java.text.Collator collator;
    final synthetic com.bisimplex.firebooru.services.BooruTagSorter this$0;

    public BooruTagSorter$TagNameComparator(com.bisimplex.firebooru.services.BooruTagSorter p1, java.text.Collator p2)
    {
        this.this$0 = p1;
        this.collator = p2;
        return;
    }

    public int compare(com.bisimplex.firebooru.danbooru.TagItem p2, com.bisimplex.firebooru.danbooru.TagItem p3)
    {
        if (p2.getName() != null) {
            if (p3.getName() != null) {
                return this.collator.compare(p2.getName(), p3.getName());
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    public bridge synthetic int compare(Object p1, Object p2)
    {
        return this.compare(((com.bisimplex.firebooru.danbooru.TagItem) p1), ((com.bisimplex.firebooru.danbooru.TagItem) p2));
    }
}
