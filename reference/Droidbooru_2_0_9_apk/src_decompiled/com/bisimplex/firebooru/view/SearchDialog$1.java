package com.bisimplex.firebooru.view;
 class SearchDialog$1 implements android.widget.AdapterView$OnItemSelectedListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$1(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onItemSelected(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        com.bisimplex.firebooru.view.SearchDialog.-$$Nest$mupdateSort(this.this$0);
        return;
    }

    public void onNothingSelected(android.widget.AdapterView p1)
    {
        return;
    }
}
