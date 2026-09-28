package com.bisimplex.firebooru.backup;
public class BackupCVSOptions {
    private boolean MD5;
    private String fileNamePrefix;
    private boolean fileURL;
    private boolean postURL;
    private java.util.List posts;
    private com.bisimplex.firebooru.network.SourceQuery query;
    private android.net.Uri uri;

    public BackupCVSOptions()
    {
        this.setFileURL(1);
        this.setPostURL(0);
        this.setMD5(0);
        return;
    }

    public BackupCVSOptions(boolean p1, boolean p2, java.util.List p3, com.bisimplex.firebooru.network.SourceQuery p4, android.net.Uri p5)
    {
        this.setFileURL(p1);
        this.setPostURL(p2);
        this.setPosts(p3);
        this.query = p4;
        this.uri = p5;
        if (p4 == null) {
            this.query = new com.bisimplex.firebooru.network.SourceQuery();
        }
        return;
    }

    public String getFileNamePrefix()
    {
        return this.fileNamePrefix;
    }

    public java.util.List getPosts()
    {
        return this.posts;
    }

    public com.bisimplex.firebooru.network.SourceQuery getQuery()
    {
        return this.query;
    }

    public android.net.Uri getUri()
    {
        return this.uri;
    }

    public boolean isFileURL()
    {
        return this.fileURL;
    }

    public boolean isMD5()
    {
        return this.MD5;
    }

    public boolean isPostURL()
    {
        return this.postURL;
    }

    public void setFileNamePrefix(String p1)
    {
        this.fileNamePrefix = p1;
        return;
    }

    public void setFileURL(boolean p1)
    {
        this.fileURL = p1;
        return;
    }

    public void setMD5(boolean p1)
    {
        this.MD5 = p1;
        return;
    }

    public void setPostURL(boolean p1)
    {
        this.postURL = p1;
        return;
    }

    public void setPosts(java.util.List p1)
    {
        this.posts = p1;
        return;
    }

    public void setQuery(com.bisimplex.firebooru.network.SourceQuery p1)
    {
        this.query = p1;
        return;
    }

    public void setUri(android.net.Uri p1)
    {
        this.uri = p1;
        return;
    }
}
