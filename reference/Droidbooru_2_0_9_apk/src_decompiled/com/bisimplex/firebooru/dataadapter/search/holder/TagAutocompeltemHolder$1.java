package com.bisimplex.firebooru.dataadapter.search.holder;
 class TagAutocompeltemHolder$1 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder this$0;
    final synthetic String val$filter;

    TagAutocompeltemHolder$1(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p1, String p2)
    {
        this.this$0 = p1;
        this.val$filter = p2;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder v0_1 = this.this$0.getAutocomplete();
        if ((v0_1.getContext() != null) && (v0_1.hasFocus())) {
            this.this$0.searchTags(this.val$filter);
        }
        return;
    }
}
