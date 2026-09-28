package com.bisimplex.firebooru.services;
public class DownloadOptions {
    private boolean avoidDuplicates;
    private boolean downloadOriginal;
    private boolean excludeAnimated;
    private String key;
    private String target_folder;

    public DownloadOptions()
    {
        this.setExcludeAnimated(0);
        this.setDownloadOriginal(1);
        this.setAvoidDuplicates(1);
        return;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getTarget_folder()
    {
        return this.target_folder;
    }

    public boolean isAvoidDuplicates()
    {
        return this.avoidDuplicates;
    }

    public boolean isDownloadOriginal()
    {
        return this.downloadOriginal;
    }

    public boolean isExcludeAnimated()
    {
        return this.excludeAnimated;
    }

    public void setAvoidDuplicates(boolean p1)
    {
        this.avoidDuplicates = p1;
        return;
    }

    public void setDownloadOriginal(boolean p1)
    {
        this.downloadOriginal = p1;
        return;
    }

    public void setExcludeAnimated(boolean p1)
    {
        this.excludeAnimated = p1;
        return;
    }

    public void setKey(String p1)
    {
        this.key = p1;
        return;
    }

    public void setTarget_folder(String p1)
    {
        this.target_folder = p1;
        return;
    }
}
