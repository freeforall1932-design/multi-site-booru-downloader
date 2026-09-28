package com.bisimplex.firebooru.dataadapter.search;
public class SingleSelectorOption {
    private String hint;
    private String key;
    private String label;

    public SingleSelectorOption(String p1, String p2, String p3)
    {
        this.setKey(p1);
        this.setLabel(p2);
        this.setHint(p3);
        return;
    }

    public String getHint()
    {
        return this.hint;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getLabel()
    {
        return this.label;
    }

    public void setHint(String p1)
    {
        this.hint = p1;
        return;
    }

    public void setKey(String p1)
    {
        this.key = p1;
        return;
    }

    public void setLabel(String p1)
    {
        this.label = p1;
        return;
    }

    public String toString()
    {
        return this.getLabel();
    }
}
