package com.bisimplex.firebooru.fragment;
 class SearchFragment$3 implements android.widget.AdapterView$OnItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.SearchFragment this$0;

    SearchFragment$3(com.bisimplex.firebooru.fragment.SearchFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onItemClick(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        int v1_6 = com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetadapter(this.this$0).getItem(p3);
        android.widget.AutoCompleteTextView v2_7 = com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetcurrentText(this.this$0);
        if (v2_7 == null) {
            v2_7 = "";
        }
        int v1_4;
        int v3_2 = v2_7.lastIndexOf(" ");
        if (v3_2 >= 0) {
            v1_4 = String.format("%s %s", new Object[] {v2_7.subSequence(0, v3_2), v1_6.getName()}));
        } else {
            v1_4 = v1_6.getName();
        }
        com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetautoCompleteTextView(this.this$0).setText(v1_4);
        com.bisimplex.firebooru.fragment.SearchFragment.-$$Nest$fgetautoCompleteTextView(this.this$0).setSelection(v1_4.length());
        return;
    }
}
