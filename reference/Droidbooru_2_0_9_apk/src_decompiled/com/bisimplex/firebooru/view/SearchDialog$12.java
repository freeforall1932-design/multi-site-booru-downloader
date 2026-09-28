package com.bisimplex.firebooru.view;
 class SearchDialog$12 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$12(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        if (this.this$0.autoCompleteTextView != null) {
            android.view.inputmethod.InputMethodManager v0_2 = ((android.view.inputmethod.InputMethodManager) this.this$0.autoCompleteTextView.getContext().getSystemService("input_method"));
            if (v0_2 != null) {
                v0_2.showSoftInput(this.this$0.autoCompleteTextView, 0);
            }
        }
        return;
    }
}
