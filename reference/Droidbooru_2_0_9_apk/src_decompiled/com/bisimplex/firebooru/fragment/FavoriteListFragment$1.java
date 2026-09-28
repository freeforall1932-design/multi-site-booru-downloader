package com.bisimplex.firebooru.fragment;
 class FavoriteListFragment$1 implements com.bisimplex.firebooru.services.UpdateMetadataService$UpdateMetadataServiceListener {
    final synthetic com.bisimplex.firebooru.fragment.FavoriteListFragment this$0;

    FavoriteListFragment$1(com.bisimplex.firebooru.fragment.FavoriteListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void updateFinished()
    {
        com.google.android.material.snackbar.Snackbar v0_6 = ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.getActivity());
        com.bisimplex.firebooru.fragment.FavoriteListFragment$1$1 v1_3 = this.this$0.getView();
        if ((v0_6 != null) && ((v1_3 != null) && (!this.this$0.isDetached()))) {
            this.this$0.invalidateMenu();
            com.google.android.material.snackbar.Snackbar v0_4 = com.google.android.material.snackbar.Snackbar.make(v1_3, 2131886595, -2);
            v0_4.setAction(2131887072, new com.bisimplex.firebooru.fragment.FavoriteListFragment$1$1(this));
            v0_4.show();
        }
        return;
    }
}
