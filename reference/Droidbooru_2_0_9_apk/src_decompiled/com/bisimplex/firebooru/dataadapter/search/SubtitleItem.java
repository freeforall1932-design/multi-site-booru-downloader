package com.bisimplex.firebooru.dataadapter.search;
public class SubtitleItem extends com.bisimplex.firebooru.dataadapter.search.LabelItem {

    public SubtitleItem(String p1, String p2, String p3)
    {
        super(p1, p2, p3);
        super.setType(com.bisimplex.firebooru.dataadapter.search.ItemType.Subtitle);
        return;
    }
}
