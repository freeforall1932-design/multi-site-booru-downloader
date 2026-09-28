package com.bisimplex.firebooru.dataadapter.search;
public class CaptionItem extends com.bisimplex.firebooru.dataadapter.search.LabelItem {

    public CaptionItem(String p2, String p3)
    {
        super(p2, p3, 0);
        super.setType(com.bisimplex.firebooru.dataadapter.search.ItemType.Caption);
        return;
    }
}
