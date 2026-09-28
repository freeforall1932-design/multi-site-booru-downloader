package com.bisimplex.firebooru.backup;
public class BackupConfiguration {
    private boolean bannedTags;
    private boolean favorites;
    private boolean history;
    private boolean homeSources;
    private boolean servers;
    private boolean toCSV;

    public BackupConfiguration()
    {
        this.servers = 1;
        this.favorites = 1;
        this.history = 1;
        this.bannedTags = 1;
        this.setToCSV(1);
        this.setHomeSources(1);
        return;
    }

    public boolean isBannedTags()
    {
        return this.bannedTags;
    }

    public boolean isFavorites()
    {
        return this.favorites;
    }

    public boolean isHistory()
    {
        return this.history;
    }

    public boolean isHomeSources()
    {
        return this.homeSources;
    }

    public boolean isServers()
    {
        return this.servers;
    }

    public boolean isToCSV()
    {
        return this.toCSV;
    }

    public void setBannedTags(boolean p1)
    {
        this.bannedTags = p1;
        return;
    }

    public void setFavorites(boolean p1)
    {
        this.favorites = p1;
        return;
    }

    public void setHistory(boolean p1)
    {
        this.history = p1;
        return;
    }

    public void setHomeSources(boolean p1)
    {
        this.homeSources = p1;
        return;
    }

    public void setServers(boolean p1)
    {
        this.servers = p1;
        return;
    }

    public void setToCSV(boolean p1)
    {
        this.toCSV = p1;
        return;
    }
}
