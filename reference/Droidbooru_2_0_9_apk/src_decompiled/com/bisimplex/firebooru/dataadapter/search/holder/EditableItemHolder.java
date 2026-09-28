package com.bisimplex.firebooru.dataadapter.search.holder;
public abstract class EditableItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder {
    private android.view.View widget;

    public EditableItemHolder(android.view.View p1)
    {
        super(p1);
        return;
    }

    public abstract String getLabel();

    public abstract Object getValue();

    public android.view.View getWidget()
    {
        return this.widget;
    }

    public boolean isEnabled()
    {
        return this.getWidget().isEnabled();
    }

    public void setEnabled(boolean p2)
    {
        this.getWidget().setEnabled(p2);
        return;
    }

    public abstract void setLabel(String p0);

    public abstract void setValue(Object p0);

    protected void setWidget(android.view.View p1)
    {
        this.widget = p1;
        return;
    }
}
