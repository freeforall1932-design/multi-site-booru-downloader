package com.bisimplex.firebooru.dataadapter;
 class SortPinAdapter$1 implements java.util.Comparator {
    final synthetic com.bisimplex.firebooru.dataadapter.SortPinAdapter this$0;

    SortPinAdapter$1(com.bisimplex.firebooru.dataadapter.SortPinAdapter p1)
    {
        this.this$0 = p1;
        return;
    }

    public int compare(com.bisimplex.firebooru.model.SourceSpecs p1, com.bisimplex.firebooru.model.SourceSpecs p2)
    {
        return p1.getTitle().compareToIgnoreCase(p2.getTitle());
    }

    public bridge synthetic int compare(Object p1, Object p2)
    {
        return this.compare(((com.bisimplex.firebooru.model.SourceSpecs) p1), ((com.bisimplex.firebooru.model.SourceSpecs) p2));
    }
}
