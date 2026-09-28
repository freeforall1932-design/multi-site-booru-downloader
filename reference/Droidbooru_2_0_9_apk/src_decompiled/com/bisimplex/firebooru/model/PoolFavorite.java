package com.bisimplex.firebooru.model;
public class PoolFavorite extends com.activeandroid.Model {
    public java.util.Date poolAddedDate;
    public String poolDescription;
    public String poolId;
    public int poolIsFavorite;
    public String poolName;
    public String poolUrl;
    public com.bisimplex.firebooru.model.Server server;

    public PoolFavorite()
    {
        this.poolAddedDate = new java.util.Date();
        return;
    }
}
