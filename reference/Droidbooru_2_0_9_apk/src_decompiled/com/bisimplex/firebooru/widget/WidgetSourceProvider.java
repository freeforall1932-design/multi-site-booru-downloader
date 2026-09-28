package com.bisimplex.firebooru.widget;
public class WidgetSourceProvider extends com.bisimplex.firebooru.danbooru.SourceProvider {

    public WidgetSourceProvider()
    {
        this.isLastPage = 1;
        this.isLoading = 0;
        return;
    }

    public void loadAnOtherPage(com.bisimplex.firebooru.danbooru.TransactionAction p2)
    {
        super.loadAnOtherPage(p2);
        this.loadPosts(this.getFilter());
        p2.success();
        return;
    }

    public java.util.List loadPosts(String p2)
    {
        this.posts = new java.util.ArrayList(0);
        return this.posts;
    }

    public void setPosts(java.util.ArrayList p2)
    {
        this.posts = p2;
        if (this.posts == null) {
            this.posts = new java.util.ArrayList(0);
        }
        return;
    }
}
