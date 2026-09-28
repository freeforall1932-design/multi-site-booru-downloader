package com.bisimplex.firebooru.fragment;
 class SearchFragment$2 implements android.text.TextWatcher {
    final synthetic com.bisimplex.firebooru.fragment.SearchFragment this$0;

    SearchFragment$2(com.bisimplex.firebooru.fragment.SearchFragment p1)
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
        com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fputcurrentText(this.this$0, String.format("%s", new Object[] {p1})));
        android.util.Log.i("Gelbooru", String.format("beforeTextChanged sequence = %s", new Object[] {p1})));
        return;
    }

    public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        android.util.Log.i("Gelbooru", String.format("addTextChangedListener sequence = %s", new Object[] {p1})));
        if (!com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetautoCompleteTextView(this.this$0).isPerformingCompletion()) {
            String v1_5 = com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetautoCompleteTextView(this.this$0).getText().toString();
            android.util.Log.i("Gelbooru", v1_5);
            this.this$0.searchTags(v1_5);
        }
        return;
    }
}
