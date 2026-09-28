package com.bisimplex.firebooru.dataadapter.search.holder;
public class TextItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder {

    public TextItemHolder(android.view.View p2)
    {
        super(p2);
        super.setWidget(((com.google.android.material.textfield.TextInputLayout) p2.findViewById(2131362186)));
        return;
    }

    public String getLabel()
    {
        if ((this.getWidget() != null) && (((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getHint() != null)) {
            return ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getHint().toString();
        } else {
            return 0;
        }
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public String getValue()
    {
        if (((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText() == null) {
            return 0;
        } else {
            return ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText().getText().toString().trim();
        }
    }

    public void setLabel(String p2)
    {
        ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).setHint(p2);
        return;
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((String) p1));
        return;
    }

    public void setValue(String p2)
    {
        if (((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText() != null) {
            ((com.google.android.material.textfield.TextInputLayout) this.getWidget()).getEditText().setText(p2);
        }
        return;
    }
}
