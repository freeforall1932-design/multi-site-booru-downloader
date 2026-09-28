package com.bisimplex.firebooru.dataadapter.search;
 class TextItem$2 implements android.text.TextWatcher {
    final synthetic com.bisimplex.firebooru.dataadapter.search.TextItem this$0;

    TextItem$2(com.bisimplex.firebooru.dataadapter.search.TextItem p1)
    {
        this.this$0 = p1;
        return;
    }

    public void afterTextChanged(android.text.Editable p3)
    {
        android.util.Log.i("AUTOCOMPLETE", new StringBuilder("afterTextChanged value: ").append(p3).toString());
        return;
    }

    public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        android.util.Log.i("AUTOCOMPLETE", new StringBuilder("beforeTextChanged value: ").append(p1).toString());
        return;
    }

    public void onTextChanged(CharSequence p3, int p4, int p5, int p6)
    {
        android.util.Log.i("AUTOCOMPLETE", new StringBuilder("onTextChanged value: ").append(p3).toString());
        this.this$0.onTextChangedEvent(p3, p4, p5, p6);
        return;
    }
}
