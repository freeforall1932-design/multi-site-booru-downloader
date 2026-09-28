package com.bisimplex.firebooru.danbooru;
public class TagItem {
    private boolean ambiguous;
    private int count;
    private int idx;
    private String name;
    private int type;

    public TagItem()
    {
        return;
    }

    public static int getColorIdByType(int p0)
    {
        switch (p0) {
            case 1:
                return 2131100484;
            case 2:
                return 2131100489;
            case 3:
                return 2131100487;
            case 4:
                return 2131100485;
            case 5:
                return 2131100490;
            case 6:
                return 2131100486;
            default:
                return com.bisimplex.firebooru.skin.SkinManager.getInstance().getTextColorRes();
        }
    }

    public int getCount()
    {
        return this.count;
    }

    public int getIdx()
    {
        return this.idx;
    }

    public String getName()
    {
        return this.name;
    }

    public int getType()
    {
        return this.type;
    }

    public int getTypeColorId()
    {
        return com.bisimplex.firebooru.danbooru.TagItem.getColorIdByType(this.getType());
    }

    public boolean isAmbiguous()
    {
        return this.ambiguous;
    }

    public void setAmbiguous(boolean p1)
    {
        this.ambiguous = p1;
        return;
    }

    public void setCount(int p1)
    {
        this.count = p1;
        return;
    }

    public void setIdx(int p1)
    {
        this.idx = p1;
        return;
    }

    public void setName(String p1)
    {
        this.name = p1;
        return;
    }

    public void setType(int p1)
    {
        this.type = p1;
        return;
    }

    public String toString()
    {
        return this.getName();
    }
}
