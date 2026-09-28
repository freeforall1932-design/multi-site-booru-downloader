package com.bisimplex.firebooru.model;
public class ProxyConfiguration {
    public static final int PROXY_TYPE_HTTP = 1;
    public static final int PROXY_TYPE_NONE = 0;
    public static final int PROXY_TYPE_SOCKS = 2;
    private String host;
    private String pass;
    private int port;
    private int type;
    private String user;

    public ProxyConfiguration()
    {
        this.setUser("");
        this.setPass("");
        this.setHost("");
        return;
    }

    public String getHost()
    {
        return this.host;
    }

    public String getPass()
    {
        return this.pass;
    }

    public int getPort()
    {
        return this.port;
    }

    public int getType()
    {
        return this.type;
    }

    public String getUser()
    {
        return this.user;
    }

    public boolean isEnabled()
    {
        if (this.getType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isValid()
    {
        if ((android.text.TextUtils.isEmpty(this.host)) || (!this.isEnabled())) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setHost(String p1)
    {
        this.host = p1;
        return;
    }

    public void setPass(String p1)
    {
        this.pass = p1;
        return;
    }

    public void setPort(int p1)
    {
        this.port = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }

    public void setUser(String p1)
    {
        this.user = p1;
        return;
    }
}
