package com.bisimplex.firebooru.dataadapter.search;
public class DoubleButtonItem extends com.bisimplex.firebooru.dataadapter.search.ButtonItem {
    private com.bisimplex.firebooru.dataadapter.search.ActionType secondaryAction;
    private String secondaryLabel;

    public DoubleButtonItem(String p1, String p2, com.bisimplex.firebooru.dataadapter.search.ActionType p3, String p4, com.bisimplex.firebooru.dataadapter.search.ActionType p5, boolean p6)
    {
        super(p1, p2, p3, p6);
        super.setSecondaryLabel(p4);
        super.setSecondaryAction(p5);
        super.setType(com.bisimplex.firebooru.dataadapter.search.ItemType.DoubleButton);
        return;
    }

    public com.bisimplex.firebooru.dataadapter.search.ActionType getSecondaryAction()
    {
        return this.secondaryAction;
    }

    public String getSecondaryLabel()
    {
        return this.secondaryLabel;
    }

    public void setSecondaryAction(com.bisimplex.firebooru.dataadapter.search.ActionType p1)
    {
        this.secondaryAction = p1;
        return;
    }

    public void setSecondaryLabel(String p1)
    {
        this.secondaryLabel = p1;
        return;
    }
}
