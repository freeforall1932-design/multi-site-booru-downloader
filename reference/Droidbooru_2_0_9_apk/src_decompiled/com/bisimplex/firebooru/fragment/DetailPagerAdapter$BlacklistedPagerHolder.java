package com.bisimplex.firebooru.fragment;
public class DetailPagerAdapter$BlacklistedPagerHolder extends com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPagerHolder implements android.view.View$OnClickListener {
    android.widget.TextView ruleTextView;
    final synthetic com.bisimplex.firebooru.fragment.DetailPagerAdapter this$0;

    public DetailPagerAdapter$BlacklistedPagerHolder(com.bisimplex.firebooru.fragment.DetailPagerAdapter p1, android.view.View p2, com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener p3)
    {
        this.this$0 = p1;
        super(p1, p2, p3);
        super.ruleTextView = ((android.widget.TextView) p2.findViewById(2131362480));
        p2.setOnClickListener(super);
        return;
    }

    public void onClick(android.view.View p2)
    {
        this.listener.showBlacklisted(this.getBindingAdapterPosition());
        return;
    }
}
