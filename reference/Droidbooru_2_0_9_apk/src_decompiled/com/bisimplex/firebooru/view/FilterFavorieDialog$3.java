package com.bisimplex.firebooru.view;
 class FilterFavorieDialog$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.FilterFavorieDialog this$0;

    FilterFavorieDialog$3(com.bisimplex.firebooru.view.FilterFavorieDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.this$0.mListener.onDialogFilterFavorite(this.this$0, 0, 0);
        return;
    }
}
