package com.bisimplex.firebooru.backup;
public class BackupServerItem {
    private String apiKey;
    private String apikey;
    private boolean isDefault;
    private boolean isSelected;
    private String password;
    private String passwordKey;
    private boolean ratingFilterEnabled;
    private java.net.URL realURL;
    private int serverId;
    private String serverName;
    private com.bisimplex.firebooru.backup.BackupServerItemType type;
    private String url;
    private boolean useNativeAutocomplete;
    private String userName;

    public BackupServerItem()
    {
        this.type = com.bisimplex.firebooru.backup.BackupServerItemType.ServerItemTypeGelbooru;
        this.ratingFilterEnabled = 1;
        return;
    }

    public BackupServerItem(com.bisimplex.firebooru.danbooru.ServerItem p2)
    {
        if (p2 != null) {
            boolean v0_7 = p2.getApiKey();
            this.apikey = v0_7;
            this.apiKey = v0_7;
            this.isDefault = p2.isDefault();
            this.url = p2.getUrl();
            this.password = p2.getPassword();
            this.passwordKey = p2.getPasswordKey();
            this.serverName = p2.getServerName();
            this.serverId = p2.getServerId();
            this.ratingFilterEnabled = p2.isRatingFilterEnabled();
            this.userName = p2.getUserName();
            this.realURL = p2.getRealURL();
            this.isSelected = p2.isSelected();
            this.useNativeAutocomplete = p2.isUseNativeAutocomplete();
            this.setType(com.bisimplex.firebooru.backup.BackupServerItemType.fromServerItemType(p2.getType()));
            return;
        } else {
            return;
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItem asServerItem()
    {
        com.bisimplex.firebooru.danbooru.ServerItem v0_1 = new com.bisimplex.firebooru.danbooru.ServerItem();
        if (!android.text.TextUtils.isEmpty(this.apikey)) {
            v0_1.setApiKey(this.apikey);
        } else {
            v0_1.setApiKey(this.apiKey);
        }
        v0_1.setDefault(this.isDefault);
        v0_1.setUrl(this.url);
        v0_1.setPassword(this.password);
        v0_1.setPasswordKey(this.passwordKey);
        v0_1.setServerName(this.serverName);
        v0_1.setServerId(((long) this.serverId));
        v0_1.setRatingFilterEnabled(this.ratingFilterEnabled);
        v0_1.setUserName(this.userName);
        v0_1.setRealURL(this.realURL);
        v0_1.setSelected(this.isSelected);
        v0_1.setUseNativeAutocomplete(this.useNativeAutocomplete);
        v0_1.setType(com.bisimplex.firebooru.backup.BackupServerItemType.toServerItemType(this.type));
        return v0_1;
    }

    public String getApiKey()
    {
        return this.apiKey;
    }

    public String getApikey()
    {
        return this.apikey;
    }

    public String getExtraInfo()
    {
        return this.getExtraInfo("");
    }

    public String getExtraInfo(String p1)
    {
        return "";
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getPasswordKey()
    {
        return this.passwordKey;
    }

    public java.net.URL getRealURL()
    {
        return this.realURL;
    }

    public int getServerId()
    {
        return this.serverId;
    }

    public String getServerName()
    {
        if (!this.isDefault) {
            return this.url;
        } else {
            return "Default";
        }
    }

    public com.bisimplex.firebooru.backup.BackupServerItemType getType()
    {
        return this.type;
    }

    public String getUrl()
    {
        return this.url;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public boolean isDefault()
    {
        return this.isDefault;
    }

    public boolean isRatingFilterEnabled()
    {
        return this.ratingFilterEnabled;
    }

    public boolean isSelected()
    {
        return this.isSelected;
    }

    public boolean isUseNativeAutocomplete()
    {
        return this.useNativeAutocomplete;
    }

    public void setApiKey(String p1)
    {
        if (p1 != null) {
            this.apiKey = p1;
            return;
        } else {
            this.apiKey = "";
            return;
        }
    }

    public void setApikey(String p1)
    {
        if (p1 != null) {
            this.apikey = p1;
            return;
        } else {
            this.apikey = "";
            return;
        }
    }

    public void setDefault(boolean p1)
    {
        this.isDefault = p1;
        return;
    }

    public void setPassword(String p1)
    {
        this.password = p1;
        return;
    }

    public void setPasswordKey(String p1)
    {
        this.passwordKey = p1;
        return;
    }

    public void setRatingFilterEnabled(boolean p1)
    {
        this.ratingFilterEnabled = p1;
        return;
    }

    public void setRealURL(java.net.URL p1)
    {
        this.realURL = p1;
        return;
    }

    public void setSelected(boolean p1)
    {
        this.isSelected = p1;
        return;
    }

    public void setServerId(long p1)
    {
        this.serverId = ((int) p1);
        return;
    }

    public void setServerName(String p1)
    {
        this.serverName = p1;
        return;
    }

    public void setType(com.bisimplex.firebooru.backup.BackupServerItemType p1)
    {
        this.type = p1;
        return;
    }

    public void setUrl(String p2)
    {
        this.url = p2;
        try {
            this.realURL = new java.net.URL(p2);
            return;
        } catch (int v2_1) {
            com.bisimplex.firebooru.network.Utils.getInstance().logException(v2_1);
            this.realURL = 0;
            return;
        }
    }

    public void setUseNativeAutocomplete(boolean p1)
    {
        this.useNativeAutocomplete = p1;
        return;
    }

    public void setUserName(String p1)
    {
        this.userName = p1;
        return;
    }

    public String toString()
    {
        return this.getServerName();
    }

    public boolean validateIsLoggedIn()
    {
        int v0_0 = this.userName;
        if ((v0_0 == 0) || (v0_0.length() <= 0)) {
            return 0;
        } else {
            return 1;
        }
    }
}
