package com.bisimplex.firebooru.dataadapter.search.holder;
public class NumberItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder {

    public NumberItemHolder(android.view.View p2)
    {
        super(p2);
        super.setWidget(((com.google.android.material.textfield.TextInputLayout) p2.findViewById(2131362186)));
        return;
    }

    public String getLabel()
    {
        return ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getHint().toString();
    }

    public Long getValue()
    {
        Long v0_1 = ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText().getText().toString().trim();
        boolean v1_0 = android.text.TextUtils.isEmpty(v0_1);
        Long v2_1 = Long.valueOf(0);
        if (!v1_0) {
            if (!android.text.TextUtils.isDigitsOnly(v0_1)) {
                return v2_1;
            } else {
                return Long.valueOf(Long.parseLong(v0_1));
            }
        } else {
            return v2_1;
        }
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public void setLabel(String p2)
    {
        ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).setHint(p2);
        return;
    }

    public void setValue(Long p5)
    {
        if (p5.longValue() <= 0) {
            ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText().setText("");
            return;
        } else {
            ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText().setText(p5.toString());
            return;
        }
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((Long) p1));
        return;
    }
}
