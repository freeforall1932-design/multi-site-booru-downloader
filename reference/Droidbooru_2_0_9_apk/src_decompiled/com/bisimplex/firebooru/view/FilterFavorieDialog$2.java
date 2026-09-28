package com.bisimplex.firebooru.view;
 class FilterFavorieDialog$2 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.FilterFavorieDialog this$0;

    FilterFavorieDialog$2(com.bisimplex.firebooru.view.FilterFavorieDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        this.this$0.mListener.onDialogNegativeClick(this.this$0);
        return;
    }
}
