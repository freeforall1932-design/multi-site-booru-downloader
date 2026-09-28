package com.bisimplex.firebooru.dataadapter.search;
public class SingleSelectorItem extends com.bisimplex.firebooru.dataadapter.search.EditableItem {
    final android.widget.AdapterView$OnItemClickListener dropdownItemClickListener;
    private java.util.List options;

    public SingleSelectorItem(String p7, String p8, int p9, java.util.List p10, boolean p11)
    {
        super(com.bisimplex.firebooru.dataadapter.search.ItemType.SingleSelectorField, p7, p8, 0, p11).dropdownItemClickListener = new com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem$1(super);
        super.setOptions(p10);
        if ((p9 < 0) || (p9 >= p10.size())) {
            android.util.Log.e("SingleSelectorItem", "bad selected index");
            return;
        } else {
            super.setValue(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) p10.get(p9)));
            return;
        }
    }

    public java.util.List getOptions()
    {
        return this.options;
    }

    public int getSelectedIndex()
    {
        if ((this.options != null) && (this.getValue() != null)) {
            return this.options.indexOf(this.getValue());
        } else {
            return -1;
        }
    }

    public void setOptions(java.util.List p1)
    {
        this.options = p1;
        return;
    }

    public void setSelectedIndex(int p2)
    {
        this.setValue(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) this.options.get(p2)));
        return;
    }
}
