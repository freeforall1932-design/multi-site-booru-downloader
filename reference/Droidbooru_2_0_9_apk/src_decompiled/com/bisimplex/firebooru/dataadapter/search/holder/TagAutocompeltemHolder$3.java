package com.bisimplex.firebooru.dataadapter.search.holder;
 class TagAutocompeltemHolder$3 implements android.text.TextWatcher {
    final synthetic com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder this$0;
    final synthetic android.widget.AutoCompleteTextView val$autoCompleteTextView;

    TagAutocompeltemHolder$3(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p1, android.widget.AutoCompleteTextView p2)
    {
        this.this$0 = p1;
        this.val$autoCompleteTextView = p2;
        return;
    }

    public void afterTextChanged(android.text.Editable p1)
    {
        return;
    }

    public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$fputcurrentText(this.this$0, p1.toString());
        return;
    }

    public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        if (!this.val$autoCompleteTextView.isPerformingCompletion()) {
            com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$mbeginSearchTags(this.this$0, this.val$autoCompleteTextView.getText().toString());
        }
        return;
    }
}
