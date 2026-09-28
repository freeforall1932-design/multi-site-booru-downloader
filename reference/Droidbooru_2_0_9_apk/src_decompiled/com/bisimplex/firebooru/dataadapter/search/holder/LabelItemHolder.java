package com.bisimplex.firebooru.dataadapter.search.holder;
public class LabelItemHolder extends com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder {
    private android.widget.TextView valueTextView;

    public LabelItemHolder(android.view.View p2)
    {
        super(p2);
        super.setValueTextView(((android.widget.TextView) p2.findViewById(2131362692)));
        super.setWidget(((android.widget.TextView) p2.findViewById(2131362196)));
        return;
    }

    public String getLabel()
    {
        return ((android.widget.TextView) this.getWidget()).getText().toString();
    }

    public bridge synthetic Object getValue()
    {
        return this.getValue();
    }

    public String getValue()
    {
        if (this.getValueTextView() != null) {
            return this.getValueTextView().getText().toString();
        } else {
            return 0;
        }
    }

    public android.widget.TextView getValueTextView()
    {
        return this.valueTextView;
    }

    public void setLabel(String p2)
    {
        android.widget.TextView v0_1 = ((android.widget.TextView) this.getWidget());
        v0_1.setText(p2);
        if (!android.text.TextUtils.isEmpty(p2)) {
            v0_1.setVisibility(0);
            return;
        } else {
            v0_1.setVisibility(8);
            return;
        }
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((String) p1));
        return;
    }

    public void setValue(String p2)
    {
        if (this.getValueTextView() != null) {
            this.getValueTextView().setText(p2);
            if (!android.text.TextUtils.isEmpty(p2)) {
                this.getValueTextView().setVisibility(0);
                return;
            } else {
                this.getValueTextView().setVisibility(8);
                return;
            }
        } else {
            return;
        }
    }

    public void setValueTextView(android.widget.TextView p1)
    {
        this.valueTextView = p1;
        return;
    }
}
