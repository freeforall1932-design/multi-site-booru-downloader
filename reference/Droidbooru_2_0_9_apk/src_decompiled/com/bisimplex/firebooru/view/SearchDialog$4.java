package com.bisimplex.firebooru.view;
 class SearchDialog$4 implements android.widget.AdapterView$OnItemClickListener {
    final synthetic com.bisimplex.firebooru.view.SearchDialog this$0;

    SearchDialog$4(com.bisimplex.firebooru.view.SearchDialog p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onItemClick(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        int v1_6 = com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgettagAdapter(this.this$0).getItem(p3);
        android.widget.AutoCompleteTextView v2_7 = com.bisimplex.firebooru.view.SearchDialog.-$$Nest$fgetcurrentText(this.this$0);
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
        this.this$0.autoCompleteTextView.setText(v1_4);
        this.this$0.autoCompleteTextView.setSelection(v1_4.length());
        return;
    }
}
