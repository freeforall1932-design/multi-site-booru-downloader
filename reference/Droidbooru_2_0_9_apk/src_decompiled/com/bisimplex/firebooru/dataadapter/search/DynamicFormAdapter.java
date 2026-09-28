package com.bisimplex.firebooru.dataadapter.search;
public class DynamicFormAdapter extends androidx.recyclerview.widget.RecyclerView$Adapter {
    private final android.view.View$OnClickListener buttonClick;
    private final android.widget.CompoundButton$OnCheckedChangeListener checkedChangeListener;
    private android.content.Context context;
    protected java.util.List data;
    protected final com.bisimplex.firebooru.dataadapter.search.EditableItem$EditableItemListener editableItemListener;
    private final android.view.View$OnClickListener endIconClickListener;
    private com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener listener;

    public DynamicFormAdapter(android.content.Context p2)
    {
        this.editableItemListener = new com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$1(this);
        this.endIconClickListener = new com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$2(this);
        this.checkedChangeListener = new com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$3(this);
        this.buttonClick = new com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$4(this);
        this.setContext(p2);
        this.data = new java.util.ArrayList(0);
        return;
    }

    protected void bindValueChangeListener()
    {
        java.util.Iterator v0_1 = this.data.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.dataadapter.search.EditableItem v1_3 = ((com.bisimplex.firebooru.dataadapter.search.Item) v0_1.next());
            if ((v1_3 instanceof com.bisimplex.firebooru.dataadapter.search.EditableItem)) {
                ((com.bisimplex.firebooru.dataadapter.search.EditableItem) v1_3).setListener(this.editableItemListener);
            }
        }
        return;
    }

    public com.bisimplex.firebooru.dataadapter.search.Item findEditableItemByKey(String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            java.util.Iterator v0_2 = this.data.iterator();
            while (v0_2.hasNext()) {
                com.bisimplex.firebooru.dataadapter.search.Item v2_1 = ((com.bisimplex.firebooru.dataadapter.search.Item) v0_2.next());
                if ((p5.equalsIgnoreCase(v2_1.getKey())) && ((v2_1 instanceof com.bisimplex.firebooru.dataadapter.search.EditableItem))) {
                    return v2_1;
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    public android.content.Context getContext()
    {
        return this.context;
    }

    public com.bisimplex.firebooru.dataadapter.search.Item getItem(int p2)
    {
        if ((p2 < null) || (p2 >= this.data.size())) {
            return 0;
        } else {
            return ((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(p2));
        }
    }

    public int getItemCount()
    {
        return this.data.size();
    }

    public int getItemViewType(int p2)
    {
        return ((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(p2)).getType().getValue();
    }

    public com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener getListener()
    {
        return this.listener;
    }

    protected void notifyAction(int p2, com.bisimplex.firebooru.dataadapter.search.ActionType p3)
    {
        if (this.getListener() != null) {
            this.getListener().triggerAction(this, p3, p2);
        }
        return;
    }

    protected void notifyValueChanged(com.bisimplex.firebooru.dataadapter.search.Item p3)
    {
        com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener v0 = this.listener;
        if (v0 != null) {
            v0.valueChanged(this, this.data.indexOf(p3));
        }
        return;
    }

    public bridge synthetic void onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder p1, int p2)
    {
        this.onBindViewHolder(((com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder) p1), p2);
        return;
    }

    public void onBindViewHolder(com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder p6, int p7)
    {
        String v7_16 = ((com.bisimplex.firebooru.dataadapter.search.Item) this.data.get(p7));
        com.google.android.material.textfield.TextInputLayout v0_55 = this.getContext();
        switch (com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$5.$SwitchMap$com$bisimplex$firebooru$dataadapter$search$ItemType[v7_16.getType().ordinal()]) {
            case 1:
                String v7_15 = ((com.bisimplex.firebooru.dataadapter.search.NumberItem) v7_16);
                ((com.bisimplex.firebooru.dataadapter.search.holder.NumberItemHolder) p6).setLabel(v7_15.getLabel());
                ((com.bisimplex.firebooru.dataadapter.search.holder.NumberItemHolder) p6).setValue(((Long) v7_15.getValue()));
                ((com.bisimplex.firebooru.dataadapter.search.holder.NumberItemHolder) p6).setEnabled(v7_15.isEnabled());
                com.google.android.material.textfield.TextInputLayout v6_18 = ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.NumberItemHolder) p6).getWidget()).getEditText();
                if (v6_18 != null) {
                    v6_18.setOnKeyListener(v7_15.keyListener);
                    com.google.android.material.textfield.TextInputLayout v0_45 = ((String) v6_18.getTag(2131362477));
                    if (!android.text.TextUtils.isEmpty(v0_45)) {
                        com.google.android.material.textfield.TextInputLayout v0_47 = ((com.bisimplex.firebooru.dataadapter.search.NumberItem) this.findEditableItemByKey(v0_45));
                        if (v0_47 != null) {
                            v6_18.removeTextChangedListener(v0_47.textWatcher);
                        }
                    }
                    v6_18.setTag(2131362477, v7_15.getKey());
                    v6_18.addTextChangedListener(v7_15.textWatcher);
                } else {
                }
                break;
            case 2:
                String v7_13 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorItem) v7_16);
                ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getWidget()).setHint(v7_13.getLabel());
                ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getWidget()).setHelperText(((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v7_13.getValue()).getHint());
                ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getAutocomplete().setTag(2131362477, v7_13.getKey());
                ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getAutocomplete().setOnItemClickListener(v7_13.dropdownItemClickListener);
                ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getAutocomplete().setAdapter(new android.widget.ArrayAdapter(v0_55, 2131558481, v7_13.getOptions()));
                com.google.android.material.textfield.TextInputLayout v0_35 = ((com.bisimplex.firebooru.dataadapter.search.SingleSelectorOption) v7_13.getValue());
                if (v0_35 != null) {
                    ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getAutocomplete().setText(v0_35.getLabel(), 0);
                } else {
                    ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getAutocomplete().setText("", 0);
                }
                ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder) p6).getWidget()).setEnabled(v7_13.isEnabled());
                return;
            case 3:
                String v7_11 = ((com.bisimplex.firebooru.dataadapter.search.ButtonItem) v7_16);
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder) p6).getWidget()).setText(v7_11.getLabel());
                ((com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder) p6).setEnabled(v7_11.isEnabled());
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder) p6).getWidget()).setTag(2131362477, v7_11.getKey());
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder) p6).getWidget()).setTag(2131362474, v7_11.getValue());
                return;
            case 4:
                String v7_8 = ((com.bisimplex.firebooru.dataadapter.search.DoubleButtonItem) v7_16);
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getWidget()).setText(v7_8.getLabel());
                ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).setEnabled(v7_8.isEnabled());
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getWidget()).setTag(2131362477, v7_8.getKey());
                ((android.widget.Button) ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getWidget()).setTag(2131362474, v7_8.getValue());
                ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getSecondaryButton().setText(v7_8.getSecondaryLabel());
                ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getSecondaryButton().setTag(2131362477, v7_8.getKey());
                ((com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder) p6).getSecondaryButton().setTag(2131362474, v7_8.getSecondaryAction());
                return;
            case 5:
                String v7_4 = ((com.bisimplex.firebooru.dataadapter.search.CheckboxItem) v7_16);
                ((android.widget.CheckBox) ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).getWidget()).setText(v7_4.getLabel());
                ((android.widget.CheckBox) ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).getWidget()).setOnCheckedChangeListener(0);
                ((android.widget.CheckBox) ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).getWidget()).setChecked(((Boolean) v7_4.getValue()).booleanValue());
                ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).setEnabled(v7_4.isEnabled());
                ((android.widget.CheckBox) ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).getWidget()).setTag(2131362477, v7_4.getKey());
                ((android.widget.CheckBox) ((com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder) p6).getWidget()).setOnCheckedChangeListener(this.checkedChangeListener);
                return;
            case 6:
            case 12:
                String v7_21 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) v7_16);
                com.google.android.material.textfield.TextInputLayout v0_58 = ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6).getWidget()).getEditText();
                if (v0_58 != null) {
                    String v1_34 = ((String) v0_58.getTag(2131362477));
                    if (!android.text.TextUtils.isEmpty(v1_34)) {
                        String v1_36 = ((com.bisimplex.firebooru.dataadapter.search.TextItem) this.findEditableItemByKey(v1_34));
                        if (v1_36 != null) {
                            v0_58.removeTextChangedListener(v1_36.textWatcher);
                        }
                    }
                    ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6).setLabel(v7_21.getLabel());
                    ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6).setValue(((String) v7_21.getValue()));
                    ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6).setEnabled(v7_21.isEnabled());
                    v0_58.setOnKeyListener(v7_21.keyListener);
                    v0_58.setTag(2131362477, v7_21.getKey());
                    v0_58.addTextChangedListener(v7_21.textWatcher);
                    v0_58.setOnFocusChangeListener(v7_21.focusChangeListener);
                    if (v7_21.getEnterKeyAction() != com.bisimplex.firebooru.dataadapter.search.ActionType.Search) {
                        v0_58.setImeOptions(16777222);
                    } else {
                        if (!com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isSecureScreen()) {
                            v0_58.setImeOptions(3);
                        } else {
                            v0_58.setImeOptions(16777219);
                        }
                    }
                    if (v7_21.isFocusByDefault()) {
                        v0_58.requestFocus();
                        v7_21.setFocusByDefault(0);
                    }
                    if (((((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6) instanceof com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder)) && ((v7_21 instanceof com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem))) {
                        String v7_1 = ((com.bisimplex.firebooru.dataadapter.search.TagAutocompleteItem) v7_21);
                        ((com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder) ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6)).setServerID(v7_1.getServerID());
                        ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder) ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6)).getWidget()).setHelperText(v7_1.getHint());
                    }
                    ((com.google.android.material.textfield.TextInputLayout) ((com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder) p6).getWidget()).setEndIconMode(2);
                    return;
                } else {
                }
            case 7:
            case 8:
            case 10:
            case 11:
                String v7_6 = ((com.bisimplex.firebooru.dataadapter.search.LabelItem) v7_16);
                ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).setLabel(v7_6.getLabel());
                ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).setValue(((String) v7_6.getValue()));
                if (!android.text.TextUtils.isEmpty(v7_6.getLabel())) {
                    ((android.widget.TextView) ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).getWidget()).setVisibility(0);
                } else {
                    ((android.widget.TextView) ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).getWidget()).setVisibility(8);
                }
                if (((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).getValueTextView() == null) {
                } else {
                    if (!android.text.TextUtils.isEmpty(((CharSequence) v7_6.getValue()))) {
                        ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).getValueTextView().setVisibility(0);
                        return;
                    } else {
                        ((com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder) p6).getValueTextView().setVisibility(8);
                        return;
                    }
                }
            case 9:
            default:
                break;
        }
        return;
    }

    public bridge synthetic androidx.recyclerview.widget.RecyclerView$ViewHolder onCreateViewHolder(android.view.ViewGroup p1, int p2)
    {
        return this.onCreateViewHolder(p1, p2);
    }

    public com.bisimplex.firebooru.dataadapter.search.holder.EditableItemHolder onCreateViewHolder(android.view.ViewGroup p3, int p4)
    {
        switch (com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$5.$SwitchMap$com$bisimplex$firebooru$dataadapter$search$ItemType[com.bisimplex.firebooru.dataadapter.search.ItemType.fromInteger(p4).ordinal()]) {
            case 1:
                return new com.bisimplex.firebooru.dataadapter.search.holder.NumberItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558624, p3, 0));
            case 2:
                return new com.bisimplex.firebooru.dataadapter.search.holder.SingleSelectorItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558625, p3, 0));
            case 3:
                com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder v4_6 = new com.bisimplex.firebooru.dataadapter.search.holder.ButtonItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558618, p3, 0));
                ((android.widget.Button) v4_6.getWidget()).setOnClickListener(this.buttonClick);
                return v4_6;
            case 4:
                com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder v4_51 = new com.bisimplex.firebooru.dataadapter.search.holder.DoubleButtonItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558621, p3, 0));
                ((android.widget.Button) v4_51.getWidget()).setOnClickListener(this.buttonClick);
                v4_51.getSecondaryButton().setOnClickListener(this.buttonClick);
                return v4_51;
            case 5:
                com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder v4_46 = new com.bisimplex.firebooru.dataadapter.search.holder.CheckboxItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558620, p3, 0));
                ((android.widget.CheckBox) v4_46.getWidget()).setOnCheckedChangeListener(this.checkedChangeListener);
                return v4_46;
            case 6:
                return new com.bisimplex.firebooru.dataadapter.search.holder.TagAutocompeltemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558617, p3, 0));
            case 7:
                return new com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558627, p3, 0));
            case 8:
                return new com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558628, p3, 0));
            case 9:
                return new com.bisimplex.firebooru.dataadapter.search.holder.SeparatorItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558622, p3, 0));
            case 10:
                return new com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558619, p3, 0));
            case 11:
                return new com.bisimplex.firebooru.dataadapter.search.holder.LabelItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558623, p3, 0));
            default:
                return new com.bisimplex.firebooru.dataadapter.search.holder.TextItemHolder(android.view.LayoutInflater.from(this.getContext()).inflate(2131558626, p3, 0));
        }
    }

    public void setContext(android.content.Context p1)
    {
        this.context = p1;
        return;
    }

    public void setControlsEnabled(boolean p4)
    {
        java.util.Iterator v0_1 = this.data.iterator();
        while (v0_1.hasNext()) {
            com.bisimplex.firebooru.dataadapter.search.EditableItem v1_0 = ((com.bisimplex.firebooru.dataadapter.search.Item) v0_1.next());
            if ((v1_0 instanceof com.bisimplex.firebooru.dataadapter.search.EditableItem)) {
                ((com.bisimplex.firebooru.dataadapter.search.EditableItem) v1_0).setEnabled(p4);
            }
        }
        this.notifyDataSetChanged();
        return;
    }

    public void setListener(com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener p1)
    {
        this.listener = p1;
        return;
    }
}
