package com.bisimplex.firebooru.view;
 class SearchDialog$11 implements com.bisimplex.firebooru.network.SourceListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$11(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    public void success(com.bisimplex.firebooru.network.Source p1, java.util.List p2)
    {
        if ((!this.this$0.isDetached()) && ((com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgettagAdapter(this.this$0) != null) && (this.this$0.autoCompleteTextView != null))) {
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgettagAdapter(this.this$0).clear();
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgettagAdapter(this.this$0).setData(p2);
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgettagAdapter(this.this$0).notifyDataSetChanged();
            this.this$0.autoCompleteTextView.invalidate();
        }
        return;
    }
}
