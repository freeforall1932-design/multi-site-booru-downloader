package com.bisimplex.firebooru.fragment;
public class DetailPagerAdapter$DetailPagerHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder {
    com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener listener;
    me.zhanghai.android.materialprogressbar.MaterialProgressBar loading_bottom;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;
    android.widget.ImageView thumbImageView;

    public DetailPagerAdapter$DetailPagerHolder(com.bisimplex.firebooru.fragment.DetailPagerAdapter p1, android.view.View p2, com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener p3)
    {
        this.this$0 = p1;
        super(p2);
        super.loading_bottom = ((me.zhanghai.android.materialprogressbar.MaterialProgressBar) p2.findViewById(2131362215));
        super.thumbImageView = ((android.widget.ImageView) p2.findViewById(2131362635));
        super.listener = p3;
        return;
    }

    protected void applyFadeOutAnimation(android.view.View p3)
    {
        if (p3 != null) {
            p3.clearAnimation();
            android.view.animation.Animation v0_2 = android.view.animation.AnimationUtils.loadAnimation(this.itemView.getContext(), 2130771997);
            v0_2.setAnimationListener(new com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder$1(this, p3));
            p3.startAnimation(v0_2);
            return;
        } else {
            return;
        }
    }

    protected void finishedLoading(com.bisimplex.firebooru.danbooru.DanbooruPost p2, java.io.File p3)
    {
        com.bisimplex.firebooru.data.DanbooruPostPage v2_0 = this.getBindingAdapterPosition();
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener v0 = this.listener;
        if (v0 != null) {
            v0.postFinishedDownload(v2_0, p3);
        }
        com.bisimplex.firebooru.data.DanbooruPostPage v2_1 = this.this$0.getItem(v2_0);
        if (v2_1 != null) {
            v2_1.setReady(1);
            v2_1.setLoading(0);
            return;
        } else {
            return;
        }
    }

    public void reportLoadFailed(String p4)
    {
        int v0 = this.getBindingAdapterPosition();
        com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener v1_1 = this.this$0.getItem(v0);
        if (v1_1 != null) {
            v1_1.setLoading(0);
            com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener v1_2 = this.listener;
            if (v1_2 != null) {
                v1_2.loadFailed(v0, p4);
            }
        }
        return;
    }

    public void reportProgress(long p4, long p6)
    {
        me.zhanghai.android.materialprogressbar.MaterialProgressBar v0 = this.loading_bottom;
        if ((v0 != null) && (p6 > 0)) {
            v0.setProgress(((int) ((p4 * 100) / p6)));
        }
        return;
    }
}
