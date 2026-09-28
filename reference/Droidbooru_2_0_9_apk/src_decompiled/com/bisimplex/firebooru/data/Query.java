package com.bisimplex.firebooru.data;
public class Query {
    private boolean filterEnabled;
    private int maxPages;
    private String text;
    private String title;

    public Query()
    {
        this.text = "";
        this.maxPages = 2147483647;
        this.filterEnabled = 0;
        return;
    }

    public Query(String p1)
    {
        this.text = p1;
        return;
    }

    public Query(String p1, String p2)
    {
        this(p1);
        this.title = p2;
        return;
    }

    protected Query(String p1, boolean p2)
    {
        this(p1);
        this.filterEnabled = p2;
        return;
    }

    public boolean getFilterEnabled()
    {
        return this.filterEnabled;
    }

    public int getMaxPages()
    {
        return this.maxPages;
    }

    public String getText()
    {
        return this.text;
    }

    public String getTitle()
    {
        String v0 = this.title;
        if (v0 == null) {
            v0 = this.getText();
        }
        return v0;
    }

    public com.bisimplex.firebooru.data.Query minusQuery(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            if (!android.text.TextUtils.isEmpty(this.text)) {
                return new com.bisimplex.firebooru.data.Query(String.format("%s -%s", new Object[] {this.text, p3})), this.filterEnabled);
            } else {
                return new com.bisimplex.firebooru.data.Query(String.format("-%s", new Object[] {p3})), this.filterEnabled);
            }
        } else {
            return new com.bisimplex.firebooru.data.Query(this.text, this.filterEnabled);
        }
    }

    public com.bisimplex.firebooru.data.Query plusQuery(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            if (!android.text.TextUtils.isEmpty(this.text)) {
                return new com.bisimplex.firebooru.data.Query(String.format("%s %s", new Object[] {this.text, p3})));
            } else {
                return new com.bisimplex.firebooru.data.Query(p3);
            }
        } else {
            return new com.bisimplex.firebooru.data.Query(this.text);
        }
    }

    public void setMaxPages(int p1)
    {
        this.maxPages = p1;
        return;
    }

    public void setTitle(String p1)
    {
        this.title = p1;
        return;
    }
}
