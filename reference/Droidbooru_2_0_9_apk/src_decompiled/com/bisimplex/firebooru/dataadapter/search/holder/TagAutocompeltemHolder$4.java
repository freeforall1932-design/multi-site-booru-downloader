package com.bisimplex.firebooru.dataadapter.search.holder;
 class TagAutocompeltemHolder$4 implements android.widget.AdapterView$OnItemClickListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder this$0;
    final synthetic android.widget.AutoCompleteTextView val$autoCompleteTextView;

    TagAutocompeltemHolder$4(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p1, android.widget.AutoCompleteTextView p2)
    {
        this.this$0 = p1;
        this.val$autoCompleteTextView = p2;
        return;
    }

    public void onItemClick(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        int v1_7 = ((com.bisimplex.firebooru.danbooru.TagItem) p1.getAdapter().getItem(p3));
        android.widget.AutoCompleteTextView v2_6 = com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$fgetcurrentText(this.this$0);
        if (v2_6 == null) {
            v2_6 = "";
        }
        int v1_4;
        String v3_2 = this.this$0.getTagSeparator();
        p4 = v2_6.lastIndexOf(v3_2);
        if (p4 >= 0) {
            v1_4 = String.format("%s%s%s", new Object[] {v2_6.subSequence(0, p4), v3_2, v1_7.getName()}));
        } else {
            v1_4 = v1_7.getName();
        }
        this.this$0.setValue(v1_4);
        this.val$autoCompleteTextView.setText(v1_4, 0);
        this.val$autoCompleteTextView.setSelection(v1_4.length());
        return;
    }
}
