package com.bisimplex.firebooru.danbooru;
 class UserConfiguration$3 implements java.util.Comparator {
    final synthetic com.bisimplex.firebooru.danbooru.UserConfiguration this$0;

    UserConfiguration$3(com.bisimplex.firebooru.danbooru.UserConfiguration p1)
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
