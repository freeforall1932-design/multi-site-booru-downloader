package com.bisimplex.firebooru.dataadapter.search;
 class SingleSelectorItem$1 implements android.widget.AdapterView$OnItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem this$0;

    SingleSelectorItem$1(com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onItemClick(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        this.this$0.setValue(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) this.this$0.getOptions().get(p3)));
        return;
    }
}
