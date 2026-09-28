package com.bisimplex.firebooru.model;
public class BannedTag extends com.activeandroid.Model {
    public int bannedTagid;
    public String tagText;

    public BannedTag()
    {
        return;
    }

    public BannedTag(String p1)
    {
        this.tagText = p1;
        return;
    }
}
