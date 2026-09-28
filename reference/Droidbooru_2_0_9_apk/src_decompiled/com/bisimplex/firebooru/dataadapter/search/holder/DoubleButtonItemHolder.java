package com.bisimplex.firebooru.dataadapter.search.holder;
public class DoubleButtonItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder {
    private final android.widget.Button secondaryButton;

    public DoubleButtonItemHolder(android.view.View p2)
    {
        super(p2);
        super.secondaryButton = ((android.widget.Button) p2.findViewById(2131362511));
        return;
    }

    public android.widget.Button getSecondaryButton()
    {
        return this.secondaryButton;
    }

    public void setEnabled(boolean p2)
    {
        super.setEnabled(p2);
        this.secondaryButton.setEnabled(p2);
        return;
    }
}
