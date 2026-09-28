package com.bisimplex.firebooru.custom;
public class PageViewHolder {
    public boolean isLoading;
    public me.zhanghai.android.materialprogressbar.MaterialProgressBar loading_bottom;
    public com.bisimplex.firebooru.view.PageView pageView;
    public int position;

    public PageViewHolder(android.view.View p2)
    {
        if (p2 != null) {
            this.pageView = ((com.bisimplex.firebooru.view.PageView) p2.findViewById(2131362403));
            return;
        } else {
            return;
        }
    }
}
