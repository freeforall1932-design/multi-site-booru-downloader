package com.bisimplex.firebooru.fragment;
public class DetailPagerAdapter$ImageDetailPagerHolder extends com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder {
    private final com.bisimplex.firebooru.custom.DownloadTargetListener drawableTargetListener;
    com.bisimplex.firebooru.view.BooruPhotoView imageView;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;

    static bridge synthetic com.bisimplex.firebooru.custom.DownloadTargetListener -$$Nest$fgetdrawableTargetListener(com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder p0)
    {
        return p0.drawableTargetListener;
    }

    public DetailPagerAdapter$ImageDetailPagerHolder(com.bisimplex.firebooru.fragment.DetailPagerAdapter p1, android.view.View p2, com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener p3)
    {
        this.this$0 = p1;
        super(p1, p2, p3);
        super.drawableTargetListener = new com.bisimplex.firebooru.fragment.DetailPagerAdapter$ImageDetailPagerHolder$1(super);
        com.bisimplex.firebooru.view.BooruPhotoView v1_6 = ((com.bisimplex.firebooru.view.BooruPhotoView) p2.findViewById(2131362178));
        super.imageView = v1_6;
        if (p3 != null) {
            v1_6.setOnLongClickListener(p3);
            super.imageView.setListener(p3);
        }
        return;
    }

    protected void finishedLoading(com.bisimplex.firebooru.danbooru.DanbooruPost p7, java.io.File p8)
    {
        super.finishedLoading(p7, p8);
        this.imageView.setFile(p8, p7.getVisibleVersion().getContentType());
        if (this.listener != null) {
            this.listener.pageViewLoaded(this.getBindingAdapterPosition(), this.imageView, p7, 0);
        }
        return;
    }
}
