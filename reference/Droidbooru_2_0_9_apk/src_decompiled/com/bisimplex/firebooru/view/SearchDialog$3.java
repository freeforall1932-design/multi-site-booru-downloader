package com.bisimplex.firebooru.view;
 class SearchDialog$3 implements android.text.TextWatcher {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$3(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void afterTextChanged(android.text.Editable p1)
    {
        return;
    }

    public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fputcurrentText(this.this$0, String.format("%s", new Object[] {p1})));
        return;
    }

    public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        if (!this.this$0.autoCompleteTextView.isPerformingCompletion()) {
            com.bisimplex.firebooru.view.SearchDialog.-$$Nest$mbeginSearchTags(this.this$0, this.this$0.autoCompleteTextView.getText().toString());
        }
        return;
    }
}
