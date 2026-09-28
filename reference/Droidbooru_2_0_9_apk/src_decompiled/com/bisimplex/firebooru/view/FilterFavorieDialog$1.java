package com.bisimplex.firebooru.view;
 class FilterFavorieDialog$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.view.FilterFavorieDialog this$0;
    final synthetic android.widget.Spinner val$serverSpinner;
    final synthetic android.widget.Spinner val$sortSpinner;

    FilterFavorieDialog$1(com.bisimplex.firebooru.view.FilterFavorieDialog p1, android.widget.Spinner p2, android.widget.Spinner p3)
    {
        this.this$0 = p1;
        this.val$serverSpinner = p2;
        this.val$sortSpinner = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        this.this$0.mListener.onDialogFilterFavorite(this.this$0, ((com.bisimplex.firebooru.danbooru.ServerItem) this.val$serverSpinner.getSelectedItem()), com.bisimplex.firebooru.danbooru.FavoriteSortType.fromInteger(this.val$sortSpinner.getSelectedItemPosition()));
        return;
    }
}
