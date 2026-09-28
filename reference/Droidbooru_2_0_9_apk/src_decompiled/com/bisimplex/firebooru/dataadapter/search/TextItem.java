package com.bisimplex.firebooru.dataadapter.search;
public class TextItem extends com.bisimplex.firebooru.dataadapter.search.EditableItem {
    private com.bisimplex.firebooru.dataadapter.search.ActionType enterKeyAction;
    public android.view.View$OnFocusChangeListener focusChangeListener;
    protected final android.view.View$OnKeyListener keyListener;
    protected final android.text.TextWatcher textWatcher;

    public TextItem(String p7, String p8, String p9, boolean p10)
    {
        com.bisimplex.firebooru.dataadapter.search.TextItem v0_1 = super(com.bisimplex.firebooru.dataadapter.search.ItemType.TextField, p7, p8, p9, p10);
        v0_1.focusChangeListener = new com.bisimplex.firebooru.dataadapter.search.TextItem$1(super);
        v0_1.textWatcher = new com.bisimplex.firebooru.dataadapter.search.TextItem$2(super);
        v0_1.keyListener = new com.bisimplex.firebooru.dataadapter.search.TextItem$3(super);
        super.setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Next);
        return;
    }

    public com.bisimplex.firebooru.dataadapter.search.ActionType getEnterKeyAction()
    {
        return this.enterKeyAction;
    }

    public boolean onKeyEvent(android.view.View p1, int p2, android.view.KeyEvent p3)
    {
        if ((p2 != 66) || (p3.getAction() != 1)) {
            return 0;
        } else {
            this.triggerAction(this.getEnterKeyAction());
            return 1;
        }
    }

    protected void onTextChangedEvent(CharSequence p1, int p2, int p3, int p4)
    {
        boolean v2_1 = ((String) this.getValue());
        String v1_1 = p1.toString();
        if ((!v2_1) || (!v2_1.equals(v1_1))) {
            this.setValue(v1_1);
            return;
        } else {
            return;
        }
    }

    public void setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType p1)
    {
        this.enterKeyAction = p1;
        return;
    }
}
