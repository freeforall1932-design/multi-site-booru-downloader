package com.bisimplex.firebooru.data;
public class DanbooruPostPage {
    private long duration;
    private boolean isLoading;
    private boolean isReady;
    private com.bisimplex.firebooru.model.ViewerCommandType pendingCommand;
    private com.bisimplex.firebooru.danbooru.DanbooruPost post;
    private boolean showNotes;

    public DanbooruPostPage(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.post = p1;
        this.setPendingCommand(com.bisimplex.firebooru.model.ViewerCommandType.None);
        return;
    }

    public DanbooruPostPage(com.bisimplex.firebooru.danbooru.DanbooruPost p1, boolean p2)
    {
        this(p1);
        this.showNotes = p2;
        return;
    }

    public long getDuration()
    {
        return this.duration;
    }

    public com.bisimplex.firebooru.model.ViewerCommandType getPendingCommand()
    {
        return this.pendingCommand;
    }

    public com.bisimplex.firebooru.danbooru.DanbooruPost getPost()
    {
        return this.post;
    }

    public boolean isLoading()
    {
        return this.isLoading;
    }

    public boolean isReady()
    {
        return this.isReady;
    }

    public boolean isShowNotes()
    {
        return this.showNotes;
    }

    public void setDuration(long p1)
    {
        this.duration = p1;
        return;
    }

    public void setLoading(boolean p1)
    {
        this.isLoading = p1;
        return;
    }

    public void setPendingCommand(com.bisimplex.firebooru.model.ViewerCommandType p1)
    {
        this.pendingCommand = p1;
        return;
    }

    public void setPost(com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        this.post = p1;
        return;
    }

    public void setReady(boolean p1)
    {
        this.isReady = p1;
        return;
    }

    public void setShowNotes(boolean p1)
    {
        this.showNotes = p1;
        return;
    }
}
