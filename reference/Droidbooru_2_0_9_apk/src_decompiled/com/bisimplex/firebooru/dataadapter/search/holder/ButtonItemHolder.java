package com.bisimplex.firebooru.dataadapter.search.holder;
public class ButtonItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder {

    public ButtonItemHolder(android.view.View p2)
    {
        super(p2);
        super.setWidget(((android.widget.Button) p2.findViewById(2131361922)));
        return;
    }

    public String getLabel()
    {
        return ((android.widget.Button) this.getWidget()).getText().toString();
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public String getValue()
    {
        return 0;
    }

    public void setLabel(String p2)
    {
        ((android.widget.Button) this.getWidget()).setText(p2);
        return;
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((String) p1));
        return;
    }

    public void setValue(String p1)
    {
        return;
    }
}
