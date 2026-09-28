package com.bisimplex.firebooru.model;
public class PoolItem {
    public static final int TYPE_POOL = 0;
    public static final int TYPE_SOURCE = 1;
    private String label;
    private com.bisimplex.firebooru.model.Pool pool;
    private int type;

    public PoolItem(com.bisimplex.firebooru.model.Pool p1)
    {
        this.setPool(p1);
        this.setType(0);
        return;
    }

    public PoolItem(String p1)
    {
        this.setLabel(p1);
        this.setType(1);
        return;
    }

    public String getLabel()
    {
        return this.label;
    }

    public com.bisimplex.firebooru.model.Pool getPool()
    {
        return this.pool;
    }

    public int getType()
    {
        return this.type;
    }

    public void setLabel(String p1)
    {
        this.label = p1;
        return;
    }

    public void setPool(com.bisimplex.firebooru.model.Pool p1)
    {
        this.pool = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }
}
