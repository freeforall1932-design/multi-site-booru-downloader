package com.bisimplex.firebooru.dataadapter.search.holder;
public class CheckboxItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder {

    public CheckboxItemHolder(android.view.View p2)
    {
        super(p2);
        super.setWidget(((android.widget.CheckBox) p2.findViewById(2131361939)));
        return;
    }

    public String getLabel()
    {
        return ((android.widget.CheckBox) this.getWidget()).getText().toString();
    }

    public Boolean getValue()
    {
        return Boolean.valueOf(((android.widget.CheckBox) this.getWidget()).isChecked());
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public void setLabel(String p2)
    {
        ((android.widget.CheckBox) this.getWidget()).setText(p2);
        return;
    }

    public void setValue(Boolean p2)
    {
        ((android.widget.CheckBox) this.getWidget()).setChecked(p2.booleanValue());
        return;
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((Boolean) p1));
        return;
    }
}
