package com.bisimplex.firebooru.dataadapter.search;
public class TitleItem extends com.bisimplex.firebooru.dataadapter.search.SubtitleItem {

    public TitleItem(String p1, String p2, String p3)
    {
        super(p1, p2, p3);
        super.setType(com.bisimplex.firebooru.dataadapter.search.ItemType.Title);
        return;
    }
}
