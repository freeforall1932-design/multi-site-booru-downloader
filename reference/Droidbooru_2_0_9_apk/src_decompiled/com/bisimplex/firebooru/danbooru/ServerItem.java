package com.bisimplex.firebooru.danbooru;
public class ServerItem {
    private String apikey;
    private boolean isDefault;
    private boolean isSelected;
    private String password;
    private String passwordKey;
    private boolean ratingFilterEnabled;
    private java.net.URL realURL;
    private int serverId;
    private String serverName;
    private com.bisimplex.firebooru.danbooru.ServerItemType type;
    private String url;
    private boolean useNativeAutocomplete;
    private String userName;

    public ServerItem()
    {
        this.type = com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeGelbooru;
        this.ratingFilterEnabled = 0;
        return;
    }

    public String getApiKey()
    {
        return this.apikey;
    }

    public String getExtraInfo()
    {
        return this.getExtraInfo("");
    }

    public String getExtraInfo(String p4)
    {
        String v1_10;
        String vtmp1 = com.bisimplex.firebooru.danbooru.ServerItemType.publicName(this.type);
        if (!this.ratingFilterEnabled) {
            v1_10 = "Disabled";
        } else {
            v1_10 = "Enabled";
        }
        String v0_1 = String.format("Filter:%s  Type:%s", new Object[] {v1_10, vtmp1}));
        String v1_1 = com.bisimplex.firebooru.danbooru.ServerItem$1.$SwitchMap$com$bisimplex$firebooru$danbooru$ServerItemType[this.type.ordinal()];
        if ((v1_1 == 1) || ((v1_1 == 2) || ((v1_1 == 3) || (v1_1 == 4)))) {
            String v1_3;
            if (!this.validateIsLoggedIn()) {
                v1_3 = "No";
            } else {
                v1_3 = String.format("Yes (%s)", new Object[] {this.userName}));
            }
            return String.format("%s  %sLogged in:%s", new Object[] {v0_1, p4, v1_3}));
        } else {
            if ((v1_1 != 5) || (android.text.TextUtils.isEmpty(this.apikey))) {
                return v0_1;
            } else {
                return String.format("%s  %sLogged in: Yes", new Object[] {v0_1, p4}));
            }
        }
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
            if (!android.text.TextUtils.isEmpty(this.serverName)) {
                return this.serverName;
            } else {
                return this.url;
            }
        } else {
            return "Default";
        }
    }

    public com.bisimplex.firebooru.danbooru.ServerItemType getType()
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

    public void setType(com.bisimplex.firebooru.danbooru.ServerItemType p1)
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
        if ((android.text.TextUtils.isEmpty(this.password)) && (android.text.TextUtils.isEmpty(this.apikey))) {
            return 0;
        } else {
            return 1;
        }
    }
}
