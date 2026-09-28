package com.bisimplex.firebooru.model;
final class BooruTag_$BooruTagIdGetter implements io.objectbox.internal.IdGetter {

    BooruTag_$BooruTagIdGetter()
    {
        return;
    }

    public long getId(com.bisimplex.firebooru.model.BooruTag p3)
    {
        return p3.getId();
    }

    public bridge synthetic long getId(Object p3)
    {
        return this.getId(((com.bisimplex.firebooru.model.BooruTag) p3));
    }
}
