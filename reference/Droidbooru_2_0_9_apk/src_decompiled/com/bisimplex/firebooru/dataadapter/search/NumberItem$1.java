package com.bisimplex.firebooru.dataadapter.search;
 class NumberItem$1 implements android.text.TextWatcher {
    final synthetic com.bisimplex.firebooru.dataadapter.search.NumberItem this$0;

    NumberItem$1(com.bisimplex.firebooru.dataadapter.search.NumberItem p1)
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
        return;
    }

    public void onTextChanged(CharSequence p2, int p3, int p4, int p5)
    {
        p4 = 0;
        if (!android.text.TextUtils.isEmpty(p2)) {
            com.bisimplex.firebooru.dataadapter.search.NumberItem v3_2 = this.this$0;
            if (android.text.TextUtils.isDigitsOnly(p2)) {
                p4 = Long.parseLong(p2.toString());
            }
            v3_2.setValue(Long.valueOf(p4));
            return;
        } else {
            this.this$0.setValue(Long.valueOf(0));
            return;
        }
    }
}
