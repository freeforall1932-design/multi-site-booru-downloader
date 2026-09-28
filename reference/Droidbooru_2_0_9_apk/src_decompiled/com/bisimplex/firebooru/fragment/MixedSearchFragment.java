package com.bisimplex.firebooru.fragment;
public class MixedSearchFragment extends com.bisimplex.firebooru.fragment.PostListFragment {
    private final com.bisimplex.firebooru.view.DynamicSearchDialog$OnDynamicSearchDialogListener mixedLitener;

    public MixedSearchFragment()
    {
        this.mixedLitener = new com.bisimplex.firebooru.fragment.MixedSearchFragment$1(this);
        return;
    }

    protected void configureSearchDialog(com.bisimplex.firebooru.network.SourcePostBasic p1, com.bisimplex.firebooru.view.DynamicSearchDialog p2)
    {
        super.configureSearchDialog(p1, p2);
        p2.setListener(this.mixedLitener);
        return;
    }

    protected int getMenuID()
    {
        return 2131689490;
    }

    public boolean getShouldResetStack()
    {
        return 1;
    }

    protected com.bisimplex.firebooru.network.SourceType getSourceType()
    {
        return com.bisimplex.firebooru.network.SourceType.MultiPost;
    }

    protected void setSourceName(String p2)
    {
        super.setSourceName(this.getString(2131886966, new Object[] {Integer.valueOf(((com.bisimplex.firebooru.network.SourceMultiPost) this.getSource()).getSourceCount())})));
        return;
    }
}
