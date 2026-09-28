package com.bisimplex.firebooru.model;
public class DownloadServiceProgress {
    private int failed;
    private int index;
    private int saved;
    private int skipped;

    public DownloadServiceProgress()
    {
        return;
    }

    public void addFailed()
    {
        this.failed = (this.failed + 1);
        return;
    }

    public void addSaved()
    {
        this.saved = (this.saved + 1);
        return;
    }

    public void addSkipped()
    {
        this.skipped = (this.skipped + 1);
        return;
    }

    public int getFailed()
    {
        return this.failed;
    }

    public int getIndex()
    {
        return this.index;
    }

    public int getSaved()
    {
        return this.saved;
    }

    public int getSkipped()
    {
        return this.skipped;
    }

    public void setFailed(int p1)
    {
        this.failed = p1;
        return;
    }

    public void setIndex(int p1)
    {
        this.index = p1;
        return;
    }

    public void setSaved(int p1)
    {
        this.saved = p1;
        return;
    }

    public void setSkipped(int p1)
    {
        this.skipped = p1;
        return;
    }
}
