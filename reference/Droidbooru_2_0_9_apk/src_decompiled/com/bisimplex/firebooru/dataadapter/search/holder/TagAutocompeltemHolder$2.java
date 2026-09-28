package com.bisimplex.firebooru.dataadapter.search.holder;
 class TagAutocompeltemHolder$2 implements com.bisimplex.firebooru.network.SourceListener {
    final synthetic com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder this$0;
    final synthetic android.widget.AutoCompleteTextView val$autoCompleteTextView;

    TagAutocompeltemHolder$2(com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder p1, android.widget.AutoCompleteTextView p2)
    {
        this.this$0 = p1;
        this.val$autoCompleteTextView = p2;
        return;
    }

    public void failure(com.bisimplex.firebooru.network.Source p1, com.bisimplex.firebooru.data.FailureType p2)
    {
        return;
    }

    public void reloadVisible()
    {
        return;
    }

    public void success(com.bisimplex.firebooru.network.Source p1, java.util.List p2)
    {
        if ((this.val$autoCompleteTextView.getContext() != null) && (this.val$autoCompleteTextView.hasFocus())) {
            com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$fgetadapter(this.this$0).clear();
            com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$fgetadapter(this.this$0).setData(p2);
            com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder.-$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            this.val$autoCompleteTextView.invalidate();
        }
        return;
    }
}
