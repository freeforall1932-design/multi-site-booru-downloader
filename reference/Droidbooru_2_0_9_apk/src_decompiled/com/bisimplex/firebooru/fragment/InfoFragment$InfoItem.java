package com.bisimplex.firebooru.fragment;
 class InfoFragment$InfoItem {
    public int colorId;
    public boolean hasUrl;
    public boolean isSeparator;
    public String tag;
    final synthetic com.bisimplex.firebooru.fragment.InfoFragment this$0;
    public String url;

    public InfoFragment$InfoItem(com.bisimplex.firebooru.fragment.InfoFragment p1, String p2, String p3)
    {
        this.this$0 = p1;
        this.tag = p2;
        this.hasUrl = (android.text.TextUtils.isEmpty(p3) ^ 1);
        this.url = p3;
        this.isSeparator = 0;
        this.colorId = -1;
        return;
    }

    public InfoFragment$InfoItem(com.bisimplex.firebooru.fragment.InfoFragment p1, String p2, boolean p3)
    {
        this.this$0 = p1;
        this.tag = p2;
        this.isSeparator = p3;
        this.colorId = -1;
        return;
    }

    public InfoFragment$InfoItem(com.bisimplex.firebooru.fragment.InfoFragment p1, String p2, boolean p3, int p4)
    {
        this.this$0 = p1;
        this.tag = p2;
        this.isSeparator = p3;
        this.colorId = p4;
        return;
    }
}
