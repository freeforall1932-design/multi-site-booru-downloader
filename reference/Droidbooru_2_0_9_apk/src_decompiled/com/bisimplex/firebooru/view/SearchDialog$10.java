package com.bisimplex.firebooru.view;
 class SearchDialog$10 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;
    final synthetic String val$filter;

    SearchDialog$10(com.bisimplex.firebooru.view.SearchDialog p1, String p2)
    {
        this.this$0 = p1;
        this.val$filter = p2;
        return;
    }

    public void run()
    {
        if ((!this.this$0.isDetached()) && ((this.this$0.getActivity() != null) && ((this.this$0.autoCompleteTextView != null) && (this.this$0.autoCompleteTextView.hasFocus())))) {
            this.this$0.searchTags(this.val$filter);
        }
        return;
    }
}
