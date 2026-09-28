package com.bisimplex.firebooru.model;
public class UpdateEntry {
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_FINISHED = 2;
    public static final int STATUS_WAITING;
    private java.util.Date added_date;
    private long fav_id;
    private long id;
    private String message;
    private int status;
    private java.util.Date update_date;

    public UpdateEntry()
    {
        return;
    }

    public java.util.Date getAdded_date()
    {
        return this.added_date;
    }

    public long getFav_id()
    {
        return this.fav_id;
    }

    public long getId()
    {
        return this.id;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getStatus()
    {
        return this.status;
    }

    public java.util.Date getUpdate_date()
    {
        return this.update_date;
    }

    public boolean isFailed()
    {
        if (this.getStatus() != 3) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isFinished()
    {
        if (this.getStatus() != 2) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isWaiting()
    {
        if (this.getStatus() != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setAdded_date(java.util.Date p1)
    {
        this.added_date = p1;
        return;
    }

    public void setFav_id(long p1)
    {
        this.fav_id = p1;
        return;
    }

    public void setId(long p1)
    {
        this.id = p1;
        return;
    }

    public void setMessage(String p1)
    {
        this.message = p1;
        return;
    }

    public void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public void setUpdate_date(java.util.Date p1)
    {
        this.update_date = p1;
        return;
    }
}
