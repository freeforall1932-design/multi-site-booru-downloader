package com.bisimplex.firebooru.fragment;
 class SearchFragment$1 implements android.view.View$OnKeyListener {
    final synthetic com.bisimplex.firebooru.fragment.SearchFragment this$0;

    SearchFragment$1(com.bisimplex.firebooru.fragment.SearchFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onKey(android.view.View p1, int p2, android.view.KeyEvent p3)
    {
        if ((p2 != 66) || (p3.getAction() != 1)) {
            return 0;
        } else {
            com.bisimplex.firebooru.fragment.SearchFragment v1_3 = this.this$0;
            v1_3.searchText(com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetautoCompleteTextView(v1_3).getText().toString());
            return 1;
        }
    }
}
