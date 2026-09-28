package com.bisimplex.firebooru.dataadapter.search.holder;
public class SingleSelectorItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder {
    private android.widget.Button deleteButton;

    public SingleSelectorItemHolder(android.view.View p2)
    {
        super(p2);
        super.setDeleteButton(((android.widget.Button) p2.findViewById(2131361985)));
        return;
    }

    public android.widget.AutoCompleteTextView getAutocomplete()
    {
        return ((android.widget.AutoCompleteTextView) ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText());
    }

    public android.widget.Button getDeleteButton()
    {
        return this.deleteButton;
    }

    public void setDeleteButton(android.widget.Button p1)
    {
        this.deleteButton = p1;
        return;
    }
}
