package com.bisimplex.firebooru.network;
public class SourceNote extends com.bisimplex.firebooru.network.Source {
    private com.bisimplex.firebooru.danbooru.DanbooruPost owner;
    private float proportion;

    public SourceNote(com.bisimplex.firebooru.danbooru.BooruProvider p1, int p2, com.bisimplex.firebooru.network.SourceQuery p3)
    {
        super(p1, p2, p3);
        return;
    }

    public void configureParserParams(com.bisimplex.firebooru.network.ParserParams p3)
    {
        super.configureParserParams(p3);
        if (this.proportion == 0) {
            this.proportion = 1065353216;
        }
        p3.setProportion(this.proportion);
        return;
    }

    protected String generateURLForCurrentPage()
    {
        if ((this.getProvider() != null) && (this.getQuery() != null)) {
            String v0_1 = this.getProvider().generateNoteRequestUrlForPostId(this.getQuery().getText());
            if (v0_1 != null) {
                return v0_1.toString();
            }
        }
        return 0;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getOwner()
    {
        return this.owner;
    }

    public com.bisimplex.firebooru.network.SourceType getType()
    {
        return com.bisimplex.firebooru.network.SourceType.Notes;
    }

    public boolean loadNotesFromPost(com.bisimplex.firebooru.danbooru.DanbooruPost p3, com.bisimplex.firebooru.danbooru.BooruProvider p4)
    {
        if (!this.notesAreFromPost(p3)) {
            if (p4 == null) {
                p4 = com.bisimplex.firebooru.danbooru.BooruProvider.getInstanceByUrl(p3.getPostUrl());
            }
            if (p4 != null) {
                this.setProvider(p4);
                this.setQuery(new com.bisimplex.firebooru.network.SourceQuery(p3.getPostId()));
                this.owner = p3;
                this.proportion = p3.getVisibleImageProportion();
                this.loadAnotherPage();
                return 1;
            } else {
                this.reset();
                return 0;
            }
        } else {
            this.notifySuccess(this.getData());
            return 0;
        }
    }

    public boolean notesAreFromPost(com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        if (p4) {
            com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_0 = this.owner;
            if (v1_0 != null) {
                if (p4 != v1_0) {
                    boolean v4_4 = p4.getFile();
                    com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_2 = this.owner.getFile();
                    if ((!android.text.TextUtils.isEmpty(v4_4.getUrl())) && (!android.text.TextUtils.isEmpty(v1_2.getUrl()))) {
                        return v4_4.getUrl().equalsIgnoreCase(v1_2.getUrl());
                    }
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
