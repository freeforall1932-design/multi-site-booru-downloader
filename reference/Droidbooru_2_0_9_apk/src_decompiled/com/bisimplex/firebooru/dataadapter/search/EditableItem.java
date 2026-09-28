package com.bisimplex.firebooru.dataadapter.search;
public class EditableItem extends com.bisimplex.firebooru.dataadapter.search.Item {
    private boolean enabled;
    private boolean focusByDefault;
    private com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener listener;
    private Object value;

    public EditableItem(com.bisimplex.firebooru.dataadapter.search.ItemType p1, String p2, String p3, Object p4, boolean p5)
    {
        super(p1, p2, p3);
        super.setValue(p4);
        super.setEnabled(p5);
        return;
    }

    public Object getValue()
    {
        return this.value;
    }

    public boolean isEnabled()
    {
        return this.enabled;
    }

    public boolean isFocusByDefault()
    {
        return this.focusByDefault;
    }

    public void setEnabled(boolean p1)
    {
        this.enabled = p1;
        return;
    }

    public void setFocusByDefault(boolean p1)
    {
        this.focusByDefault = p1;
        return;
    }

    public void setListener(com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener p1)
    {
        this.listener = p1;
        return;
    }

    public void setValue(Object p1)
    {
        this.value = p1;
        com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v1_1 = this.listener;
        if (v1_1 != null) {
            v1_1.valueChanged(this);
        }
        return;
    }

    public void triggerAction(com.bisimplex.firebooru.dataadapter.search.ActionType p2)
    {
        com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener v0 = this.listener;
        if (v0 != null) {
            v0.triggerAction(this, p2);
        }
        return;
    }
}
