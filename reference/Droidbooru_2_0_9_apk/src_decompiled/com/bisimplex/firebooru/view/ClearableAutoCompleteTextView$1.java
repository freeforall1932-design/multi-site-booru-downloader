package com.bisimplex.firebooru.view;
 class ClearableAutoCompleteTextView$1 implements com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnClearListener {
    final synthetic com.bisimplex.firebooru.view.ClearableAutoCompleteTextView this$0;

    ClearableAutoCompleteTextView$1(com.bisimplex.firebooru.view.ClearableAutoCompleteTextView p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClear()
    {
        this.this$0.setText("");
        return;
    }
}
