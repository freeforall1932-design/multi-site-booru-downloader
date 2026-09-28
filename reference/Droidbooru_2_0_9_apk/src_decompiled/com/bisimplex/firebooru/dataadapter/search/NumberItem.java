package com.bisimplex.firebooru.dataadapter.search;
public class NumberItem extends com.bisimplex.firebooru.dataadapter.search.EditableItem {
    protected final android.view.View$OnKeyListener keyListener;
    protected final android.text.TextWatcher textWatcher;

    public NumberItem(String p7, String p8, Long p9, boolean p10)
    {
        com.bisimplex.firebooru.dataadapter.search.NumberItem v0_1 = super(com.bisimplex.firebooru.dataadapter.search.ItemType.NumberField, p7, p8, p9, p10);
        v0_1.textWatcher = new com.bisimplex.firebooru.dataadapter.search.NumberItem$1(super);
        v0_1.keyListener = new com.bisimplex.firebooru.dataadapter.search.NumberItem$2(super);
        return;
    }

    public boolean onKeyEvent(android.view.View p1, int p2, android.view.KeyEvent p3)
    {
        if ((p2 != 66) || (p3.getAction() != 1)) {
            return 0;
        } else {
            this.triggerAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Search);
            return 1;
        }
    }
}
