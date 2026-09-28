package com.bisimplex.firebooru.backup;
public class LiveDB {
    public static String kLiveBDCreator = "Anime boxes (Android)";
    public static String kLiveBDVersion = "1.0";
    private java.util.Date backupTime;
    private String backupVersion;
    private java.util.List bannedTags;
    private String creatorName;
    private String creatorVersion;
    private java.util.List favorites;
    private java.util.List homePins;
    private java.util.List rawFavorites;
    private java.util.List searchHistory;
    private java.util.List servers;

    static LiveDB()
    {
        return;
    }

    public LiveDB()
    {
        this.favorites = new java.util.ArrayList(0);
        this.servers = new java.util.ArrayList(0);
        this.searchHistory = new java.util.ArrayList(0);
        this.bannedTags = new java.util.ArrayList(0);
        this.setHomePins(new java.util.ArrayList(0));
        this.setRawFavorites(new java.util.ArrayList(0));
        this.backupTime = new java.util.Date();
        this.creatorName = com.bisimplex.firebooru.backup.LiveDB.kLiveBDCreator;
        this.backupVersion = com.bisimplex.firebooru.backup.LiveDB.kLiveBDVersion;
        this.creatorVersion = "2.0.9";
        return;
    }

    public java.util.Date getBackupTime()
    {
        return this.backupTime;
    }

    public String getBackupVersion()
    {
        return this.backupVersion;
    }

    public java.util.List getBannedTags()
    {
        return this.bannedTags;
    }

    public String getCreatorName()
    {
        return this.creatorName;
    }

    public String getCreatorVersion()
    {
        return this.creatorVersion;
    }

    public java.util.List getFavorites()
    {
        return this.favorites;
    }

    public java.util.List getHomePins()
    {
        return this.homePins;
    }

    public java.util.List getRawFavorites()
    {
        return this.rawFavorites;
    }

    public java.util.List getSearchHistory()
    {
        return this.searchHistory;
    }

    public java.util.List getServers()
    {
        return this.servers;
    }

    public void setBackupTime(java.util.Date p1)
    {
        this.backupTime = p1;
        return;
    }

    public void setBackupVersion(String p1)
    {
        this.backupVersion = p1;
        return;
    }

    public void setBannedTags(java.util.List p1)
    {
        this.bannedTags = p1;
        return;
    }

    public void setCreatorName(String p1)
    {
        this.creatorName = p1;
        return;
    }

    public void setCreatorVersion(String p1)
    {
        this.creatorVersion = p1;
        return;
    }

    public void setFavorites(java.util.List p1)
    {
        this.favorites = p1;
        return;
    }

    public void setHomePins(java.util.List p1)
    {
        this.homePins = p1;
        return;
    }

    public void setRawFavorites(java.util.List p1)
    {
        this.rawFavorites = p1;
        return;
    }

    public void setSearchHistory(java.util.List p1)
    {
        this.searchHistory = p1;
        return;
    }

    public void setServers(java.util.List p1)
    {
        this.servers = p1;
        return;
    }
}
